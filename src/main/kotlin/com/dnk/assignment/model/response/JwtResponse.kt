package com.dnk.assignment.model.response

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "인증 요청 결과", name = "JwtResponse")
data class JwtResponse(
    @Schema(description = "JWT")
    val jwt: String,
)
