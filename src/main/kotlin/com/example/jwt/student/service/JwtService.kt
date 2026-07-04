package com.example.jwt.student.service

interface JwtService {
    fun generateToken(email: String): String
    fun extractEmail(token: String): String?
}