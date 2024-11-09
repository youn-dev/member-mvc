package com.dnk.assignment.domain.unit

import com.dnk.assignment.domain.BaseEntity
import com.dnk.assignment.domain.property.Property
import jakarta.persistence.*

@Entity
@Table(name = "unit")
class Unit(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id")
    val property: Property,

    @Column(name = "block", columnDefinition = "varchar not null")
    val block: String,
) : BaseEntity()
