package com.dnk.assignment.domain.unit

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UnitRepository : JpaRepository<Unit, Long> {
    @Query("SELECT u FROM Unit u JOIN FETCH Property p ON p.id = u.property.id WHERE u.id = :id")
    fun findOneById(id: Long): Unit?
}
