package com.dnk.assignment.model.request

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

@Schema(description = "Property 생성 정보", name = "CreatePropertyRequest")
data class CreatePropertyRequest(
    @Schema(description = "이름")
    @field:NotBlank(message = "이름은 필수입니다.")
    val name: String,
)
