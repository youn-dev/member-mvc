package com.dnk.assignment.model.request

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Size

@Schema(description = "로그인 요청 정보", name = "LoginRequest")
data class LoginRequest(
    @Schema(description = "이메일")
    @field:Email(message = "유효한 이메일을 입력해주세요.")
    val email: String,

    @Schema(description = "비밀번호")
    @field:Size(min = 8, max = 64, message = "비밀번호는 8자 이상 64자 이하여야 합니다.")
    val password: String,
)
