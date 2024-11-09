package com.dnk.assignment.domain.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findOneById(id: Long): User?
    fun findOneByEmail(email: String): User?
}
