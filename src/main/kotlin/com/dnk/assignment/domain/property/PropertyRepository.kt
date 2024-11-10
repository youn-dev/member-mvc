package com.dnk.assignment.domain.property

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PropertyRepository : JpaRepository<Property, Long> {
    fun findOneById(id: Long): Property?
    fun findByName(name: String): Property?
    @Query("SELECT p FROM Property p LEFT JOIN FETCH p.unitList")
    fun findByIdWithUnits(id: Long): Property?
}
