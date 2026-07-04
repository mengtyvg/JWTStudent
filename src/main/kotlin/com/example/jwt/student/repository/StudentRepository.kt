package com.example.jwt.student.repository

import com.example.jwt.student.entity.StudentEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface StudentRepository : JpaRepository<StudentEntity, Long> {

    fun existsByEmail(email: String): Boolean

    fun findByEmail(email: String): StudentEntity?
}