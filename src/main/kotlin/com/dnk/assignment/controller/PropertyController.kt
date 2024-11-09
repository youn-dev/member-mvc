package com.dnk.assignment.controller

import com.dnk.assignment.model.request.CreatePropertyRequest
import com.dnk.assignment.service.property.PropertyService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "자산")
@RestController
@RequestMapping("/v1/properties")
@Validated
class PropertyController(
    private val propertyService: PropertyService,
) {
    @Operation(summary = "자산 추가")
    @PostMapping
    fun create(
        @RequestBody @Valid request: CreatePropertyRequest,
    ): ResponseEntity<Unit> {
        return propertyService.create(request).let {
            ResponseEntity.ok().build()
        }
    }
}
