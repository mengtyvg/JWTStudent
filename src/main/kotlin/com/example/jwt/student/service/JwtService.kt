package com.example.jwt.student.service

interface JwtService {
    fun generateToken(email: String, role: String): String
    fun extractEmail(token: String): String?
    fun extractRole(token: String): String?
}
