package com.example.jwt.student.service

interface PermissionService {
    fun hasPermission(email: String, method: String, path: String): Boolean
}