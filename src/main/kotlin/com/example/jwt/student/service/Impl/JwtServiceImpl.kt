package com.example.jwt.student.service.Impl

import com.example.jwt.student.service.JwtService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.Date
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys



@Service
class JwtServiceImpl (


    @Value("\${jwt.secret}")
    private val secret: String,

    @Value("\${jwt.expiration}")
    private val expiration: Long
): JwtService {

    override fun generateToken(email: String, role: String): String {

        val now = Date()
        val expiredDate = Date(now.time + expiration)

        val key = Keys.hmacShaKeyFor(secret.toByteArray())

        return Jwts.builder()
            .subject(email)
            .claim("role", role)
            .issuedAt(now)
            .expiration(expiredDate)
            .signWith(key)
            .compact()
    }

    override fun extractEmail(token: String): String? {
        return try {
            val key = Keys.hmacShaKeyFor(secret.toByteArray())

            Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .payload
                .subject

        } catch (e: Exception) {
            null
        }
    }

    override fun extractRole(token: String): String? {
        return try {
            val key = Keys.hmacShaKeyFor(secret.toByteArray())

            Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .payload
                .get("role", String::class.java)
        } catch (e: Exception) {
            null
        }
    }


}
