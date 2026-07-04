package com.example.jwt.student.controller

import com.example.jwt.student.dto.AuthResponseDTO
import com.example.jwt.student.dto.StudentLoginRequestDTO
import com.example.jwt.student.dto.StudentRegisterRequestDTO
import com.example.jwt.student.dto.StudentResponseDTO
import com.example.jwt.student.service.StudentService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/student")
class StudentController(
    private val studentService: StudentService
) {
    @PostMapping("/register")
    fun register(
        @Valid @RequestBody requestDTO: StudentRegisterRequestDTO
    ): StudentResponseDTO {
        return studentService.register(requestDTO)
    }

    @PostMapping("/login")
    fun login(
        @Valid @RequestBody requestDTO: StudentLoginRequestDTO
    ): AuthResponseDTO {
        return studentService.login(requestDTO)
    }
}