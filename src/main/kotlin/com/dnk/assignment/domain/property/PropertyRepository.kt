package com.dnk.assignment.domain.property

import org.springframework.data.jpa.repository.JpaRepository

interface PropertyRepository : JpaRepository<Property, Long>
