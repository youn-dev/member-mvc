package com.dnk.assignment.model.request

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

@Schema(description = "회원가입 요청 정보", name = "SignupRequest")
data class SignupRequest(
    @Schema(description = "이메일")
    @field:Email(message = "유효한 이메일을 입력해주세요.")
    val email: String,

    @Schema(description = "비밀번호")
    @field:Size(min = 8, max = 64, message = "비밀번호는 8자 이상 64자 이하여야 합니다.")
    val password: String,

    @Schema(description = "이름")
    @field:NotBlank(message = "이름은 필수입니다.")
    val name: String,
)
