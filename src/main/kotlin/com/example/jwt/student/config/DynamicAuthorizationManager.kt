package com.example.jwt.student.config

import com.example.jwt.student.service.PermissionService
import org.springframework.security.authorization.AuthorizationDecision
import org.springframework.security.authorization.AuthorizationManager
import org.springframework.security.core.Authentication
import org.springframework.security.web.access.intercept.RequestAuthorizationContext
import org.springframework.stereotype.Component
import java.util.function.Supplier


@Component
class DynamicAuthorizationManager(
    private val permissionService: PermissionService
) : AuthorizationManager<RequestAuthorizationContext> {

    override fun authorize(
        authentication: Supplier<out Authentication?>,
        context: RequestAuthorizationContext
    ): AuthorizationDecision {
        val auth = authentication.get()
            ?: return AuthorizationDecision(false)

        if (!auth.isAuthenticated) {
            return AuthorizationDecision(false)
        }

        val email = auth.name
        val request = context.request

        val allowed = permissionService.hasPermission(
            email = email,
            method = request.method,
            path = request.requestURI
        )

        return AuthorizationDecision(allowed)
    }
}
