package com.dnk.assignment.controller

import com.dnk.assignment.model.request.CreateUnitRequest
import com.dnk.assignment.model.response.PropertyWithUnitResponse
import com.dnk.assignment.service.unit.UnitService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "공간")
@RestController
@RequestMapping("/v1/units")
@Validated
class UnitController(
    private val unitService: UnitService,
) {
    @Operation(summary = "공간 추가")
    @PostMapping
    fun create(
        @RequestBody @Valid request: CreateUnitRequest,
    ): ResponseEntity<PropertyWithUnitResponse> {
        return unitService.create(request).let {
            ResponseEntity.ok().body(it)
        }
    }
}
