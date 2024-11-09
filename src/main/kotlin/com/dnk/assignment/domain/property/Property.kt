package com.dnk.assignment.domain.property

import com.dnk.assignment.domain.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "property")
class Property(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long = 0,

    @Column(name = "name", columnDefinition = "varchar not null")
    val name: String,
) : BaseEntity()
