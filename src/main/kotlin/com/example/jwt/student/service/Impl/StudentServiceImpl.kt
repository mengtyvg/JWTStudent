package com.example.jwt.student.service.Impl

import com.example.jwt.student.config.PasswordEncoderConfig
import com.example.jwt.student.dto.AuthResponseDTO
import com.example.jwt.student.dto.StudentLoginRequestDTO
import com.example.jwt.student.dto.StudentRegisterRequestDTO
import com.example.jwt.student.dto.StudentResponseDTO
import com.example.jwt.student.entity.StudentEntity
import com.example.jwt.student.repository.StudentRepository
import com.example.jwt.student.service.JwtService
import com.example.jwt.student.service.StudentService
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDateTime

@Service
class StudentServiceImpl(
    val studentRepository: StudentRepository,
    val passwordEncoder: PasswordEncoder,
    val jwtService: JwtService,
) : StudentService {

    override fun login(requestDTO: StudentLoginRequestDTO): AuthResponseDTO {
        val email = requestDTO.email
            ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Email cannot be empty")

        val password = requestDTO.password
        ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Password cannot be empty")

        val student = studentRepository.findByEmail(email)
        ?: throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "invalid Email bro")

        if (!passwordEncoder.matches(password, student.password)) {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Passwords do not match")
        }

        val token = jwtService.generateToken(student.email!!)

        return AuthResponseDTO(
            token = token,
            student = StudentResponseDTO(
                id = student.id,
                fullName = student.fullName,
                email = student.email,
                phone = student.phone,
                gender = student.gender,
                address = student.address,
                status = student.status
            )
        )
    }





    override fun register(requestDTO: StudentRegisterRequestDTO): StudentResponseDTO {
        val fullName = requestDTO.fullName?.trim()
            ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Full name cannot be empty")

        val email = requestDTO.email?.trim()
            ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Email cannot be empty")

        val password = requestDTO.password
            ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Password cannot be empty")

        if (studentRepository.existsByEmail(email)) {
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Email already exists find a new email bro"
            )
        }


        val student = StudentEntity(
            email = email,
            fullName = fullName,
            password = passwordEncoder.encode(password),
            phone = requestDTO.phone,
            gender = requestDTO.gender,
            address = requestDTO.address,
            status = true,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )

        val savedStudent = studentRepository.save(student)

        return StudentResponseDTO(savedStudent)

    }

    override fun getProfile(email: String): StudentResponseDTO {
        val student = studentRepository.findByEmail(email)
            ?: throw ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Student not found"
            )

        return StudentResponseDTO(student)
    }




    private fun StudentResponseDTO(student: StudentEntity): StudentResponseDTO {
        return StudentResponseDTO(
            id = student.id,
            fullName = student.fullName,
            email = student.email,
            phone = student.phone,
            gender = student.gender,
            address = student.address,
            status = student.status,
        )
    }


}