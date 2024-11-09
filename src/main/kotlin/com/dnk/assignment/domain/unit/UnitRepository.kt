package com.dnk.assignment.domain.unit

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UnitRepository : JpaRepository<Unit, Long>
