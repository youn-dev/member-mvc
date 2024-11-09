package com.dnk.assignment.domain.property

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PropertyRepository : JpaRepository<Property, Long> {
    fun findByName(name: String): Property?
}
