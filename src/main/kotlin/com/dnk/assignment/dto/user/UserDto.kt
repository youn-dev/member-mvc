package com.dnk.assignment.dto.user

import com.dnk.assignment.domain.user.User
import com.fasterxml.jackson.annotation.JsonProperty

data class UserDto(
    val id: Long,
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    val email: String,
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    val password: String,
    val name: String,
) {
    companion object {
        fun of(user: User): UserDto =
            UserDto(
                id = user.id,
                email = user.email,
                password = user.password,
                name = user.name,
            )
    }
}
