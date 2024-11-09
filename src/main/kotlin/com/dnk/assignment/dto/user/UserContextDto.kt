package com.dnk.assignment.dto.user

import com.dnk.assignment.domain.user.User

data class UserContextDto(
    val id: Long,
    val email: String,
    val name: String,
) {
    companion object {
        fun of(user: User): UserContextDto =
            UserContextDto(
                id = user.id,
                email = user.email,
                name = user.name,
            )
    }
}
