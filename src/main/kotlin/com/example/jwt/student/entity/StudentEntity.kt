package com.example.jwt.student.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
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
    var updatedAt: LocalDateTime = LocalDateTime.now(),

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role", referencedColumnName = "name", nullable = false)
    var role: RoleEntity? = null

)

@Entity
@Table(name = "roles")
data class RoleEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false, unique = true)
    var name: String = "",

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "role_permissions",
        joinColumns = [JoinColumn(name = "role_id")],
        inverseJoinColumns = [JoinColumn(name = "permission_id")]
    )
    var permissions: MutableSet<PermissionEntity> = mutableSetOf()
)

@Entity
@Table(name = "permissions")
data class PermissionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var method: String = "",

    @Column(name = "api_path", nullable = false)
    var apiPath: String = ""
)
