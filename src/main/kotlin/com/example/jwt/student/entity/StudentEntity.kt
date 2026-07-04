package com.example.jwt.student.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "students")
data class StudentEntity(

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @field:Column(name = "full_name", nullable = false)
    var fullName: String? = null,

    @field:Column(nullable = false, unique = true)
    var email: String? = null,

    @field:Column(nullable = false)
    var password: String? = null,

    var phone: String? = null,

    var gender: String? = null,

    var address: String? = null,

    var status: Boolean = true,

    @field:Column(name = "created_at")
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @field:Column(name = "updated_at")
    var updatedAt: LocalDateTime = LocalDateTime.now()

)
