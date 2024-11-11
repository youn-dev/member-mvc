package com.dnk.assignment.controller

import com.dnk.assignment.model.response.PropertyWithUnitResponse
import com.dnk.assignment.model.response.UnitWithPropertyResponse
import com.dnk.assignment.service.unit.UnitService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@Tag(name = "공간")
@RestController
@RequestMapping("/v1/units")
@Validated
class UnitController(
    private val unitService: UnitService,
) {
    @Operation(summary = "공간 추가")
    @PutMapping("/upload")
    fun upload(
        @Parameter(description = "자산 ID", schema = Schema(type = "integer", format = "int64"))
        @RequestParam propertyId: Long,
        @Parameter(
            description = "공간 정보 파일",
            content = [Content(
                mediaType = "application/octet-stream",
                schema = Schema(type = "string", format = "binary")
            )]
        )
        @RequestParam file: MultipartFile,
    ): ResponseEntity<PropertyWithUnitResponse> {
        return unitService.upload(file = file, propertyId = propertyId).let {
            ResponseEntity.ok().body(it)
        }
    }

    @Operation(summary = "공간 조회")
    @GetMapping("/{unitId}")
    fun getUnit(
        @Parameter(description = "공간 ID", schema = Schema(type = "integer", format = "int64"))
        @PathVariable("unitId") unitId: Long,
    ): ResponseEntity<UnitWithPropertyResponse> {
        return unitService.getUnit(unitId).let {
            ResponseEntity.ok().body(it)
        }
    }
}
