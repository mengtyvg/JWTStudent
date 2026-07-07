package com.example.jwt.student.service.Impl

import com.example.jwt.student.repository.StudentRepository
import com.example.jwt.student.service.PermissionService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service


@Service
class PermissionServiceImpl(
    private val studentRepository: StudentRepository
) : PermissionService {
    private val logger = LoggerFactory.getLogger(PermissionServiceImpl::class.java)

    override fun hasPermission(email: String, method: String, path: String): Boolean {
        val user = studentRepository.findByEmail(email)
            ?: run {
                logger.warn("Permission denied: user not found. email={}, method={}, path={}", email, method, path)
                return false
            }

        val permissions = user.role?.permissions.orEmpty()
        val normalizedMethod = method.trim().uppercase()
        val normalizedPath = normalizePath(path)

        val allowed = permissions.any { permission ->
            permission.method.trim().uppercase() == normalizedMethod &&
                pathMatches(permission.apiPath, normalizedPath)
        }

        if (!allowed) {
            logger.warn(
                "Permission denied: email={}, role={}, method={}, path={}, permissions={}",
                email,
                user.role?.name,
                normalizedMethod,
                normalizedPath,
                permissions.map { "${it.method} ${it.apiPath}" }
            )
        }

        return allowed
    }

    private fun pathMatches(pattern: String, path: String): Boolean {
        val regex = buildRegexFromPattern(normalizePath(pattern))

        return regex.matches(path)
    }

    private fun buildRegexFromPattern(pattern: String): Regex {
        val regex = StringBuilder("^")
        var index = 0

        while (index < pattern.length) {
            when {
                pattern[index] == '*' -> {
                    regex.append(".*")
                    index++
                }

                pattern[index] == '{' -> {
                    val endIndex = pattern.indexOf('}', startIndex = index + 1)
                    if (endIndex > index) {
                        regex.append("[^/]+")
                        index = endIndex + 1
                    } else {
                        regex.append(Regex.escape(pattern[index].toString()))
                        index++
                    }
                }

                else -> {
                    regex.append(Regex.escape(pattern[index].toString()))
                    index++
                }
            }
        }

        regex.append("$")
        return regex.toString().toRegex()
    }

    private fun normalizePath(path: String): String {
        val withoutQuery = path.substringBefore("?").trim()
        return if (withoutQuery.length > 1) withoutQuery.trimEnd('/') else withoutQuery
    }
}
