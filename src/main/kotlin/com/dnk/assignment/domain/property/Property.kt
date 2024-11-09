package com.dnk.assignment.domain.property

import com.dnk.assignment.domain.BaseEntity
import com.dnk.assignment.domain.unit.Unit
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

    @OneToMany(mappedBy = "property", fetch = FetchType.LAZY)
    val unitList: List<Unit> = mutableListOf()
) : BaseEntity()
