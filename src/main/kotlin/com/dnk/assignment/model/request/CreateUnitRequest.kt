package com.dnk.assignment.model.request

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import org.springframework.web.multipart.MultipartFile

@Schema(description = "공간 생성 정보", name = "CreateUnitRequest")
data class CreateUnitRequest(
    @Schema(description = "자산 ID")
    @field:Positive(message = "자산 ID는 필수입니다.")
    val propertyId: Long,

    @Schema(description = "공간 내역이 들어있는 파일")
    @field:NotNull(message = "공간 내역 파일은 필수입니다.")
    val file: MultipartFile,
)
