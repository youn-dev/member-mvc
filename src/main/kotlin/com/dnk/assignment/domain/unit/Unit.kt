package com.dnk.assignment.domain.unit

import com.dnk.assignment.domain.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "unit")
class Unit(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "propertyId", columnDefinition = "bigint not null")
    val propertyId: Long,

    @Column(name = "block", columnDefinition = "varchar not null")
    val block: String,
) : BaseEntity()
