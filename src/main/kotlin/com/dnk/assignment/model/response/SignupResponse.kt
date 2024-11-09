package com.dnk.assignment.model.response

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "회원가입 요청 결과", name = "SignupResponse")
data class SignupResponse(
    @Schema(description = "JWT")
    val jwt: String,
)
