package com.dnk.assignment.model.response

import com.dnk.assignment.domain.user.User
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "사용자 조회 결과", name = "UserResponse")
data class UserResponse(
    @Schema(description = "사용자 ID")
    val userId: Long,

    @Schema(description = "이메일")
    val email: String,

    @Schema(description = "이름")
    val name: String,
) {
    companion object {
        fun of(user: User) = UserResponse(
            userId = user.id,
            email = user.email,
            name = user.name,
        )
    }
}
