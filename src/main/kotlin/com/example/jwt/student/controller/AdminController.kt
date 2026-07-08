package com.example.jwt.student.controller

import com.example.jwt.student.dto.ChangeRoleRequest
import com.example.jwt.student.dto.StudentResponseDTO
import com.example.jwt.student.service.StudentService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/admin")
class AdminController(
    private val studentService: StudentService
) {
    @PutMapping("/students/{email}/role")
    fun changeStudentRole(
        @PathVariable email: String,
        @RequestBody request: ChangeRoleRequest
    ): StudentResponseDTO {
        return studentService.changeRole(email, request.role)
    }

}
