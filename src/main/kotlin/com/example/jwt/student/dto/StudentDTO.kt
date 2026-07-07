package com.example.jwt.student.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class StudentRegisterRequestDTO(

    @field:NotBlank(message = "Full name cannot be empty")
    val fullName: String? = null,

    @field:NotBlank(message = "Email cannot be empty")
    @field:Email(message = "Email format is invalid")
    val email: String? = null,

    @field:NotBlank(message = "Password cannot be empty")
    @field:Size(min = 6, message = "Password must be at least 6 characters")
    val password: String? = null,

    val phone: String? = null,

    val gender: String? = null,

    val address: String? = null
)


data class StudentLoginRequestDTO(

    @field:NotBlank(message = "Email cannot be empty")
    @field:Email(message = "Email format is invalid")
    val email: String? = null,

    @field:NotBlank(message = "Password cannot be empty")
    val password: String? = null
)

data class StudentResponseDTO(
    val id: Long? = null,
    val fullName: String? = null,
    val email: String? = null,
    val phone: String? = null,
    val gender: String? = null,
    val address: String? = null,
    val status: Boolean? = null,
    val role: String? = null
)

data class StudentUpdateRequestDTO(
    val fullName: String? = null,
    val phone: String? = null,
    val gender: String? = null,
    val address: String? = null
)

data class AuthResponseDTO(
    val token: String,
    val tokenType: String = "Bearer",
    val student: StudentResponseDTO
)


data class ChangeRoleRequest(
    val role: String
)
