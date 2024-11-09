package com.dnk.assignment.domain.user

import com.dnk.assignment.domain.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "user")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long = 0,

    @Column(name = "email", columnDefinition = "varchar not null")
    val email: String,

    @Column(name = "password", columnDefinition = "varchar not null")
    val password: String,

    @Column(name = "name")
    val name: String,
) : BaseEntity()
