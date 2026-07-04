package com.example.jwt.student.service

import com.example.jwt.student.dto.AuthResponseDTO
import com.example.jwt.student.dto.StudentLoginRequestDTO
import com.example.jwt.student.dto.StudentRegisterRequestDTO
import com.example.jwt.student.dto.StudentResponseDTO

interface StudentService {
    fun login(requestDTO: StudentLoginRequestDTO): AuthResponseDTO
    fun register(requestDTO: StudentRegisterRequestDTO): StudentResponseDTO
    fun getProfile(email: String): StudentResponseDTO

}