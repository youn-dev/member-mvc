package com.dnk.assignment.model.response

import com.dnk.assignment.domain.property.Property
import com.dnk.assignment.dto.unit.UnitDto
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "자산 및 공간 정보", name = "PropertyWithUnitResponse")
data class PropertyWithUnitResponse(
    @Schema(description = "자산 ID")
    val id: Long,
    @Schema(description = "자산명")
    val name: String,
    @Schema(description = "공간 정보")
    val unitList: List<UnitDto>,
) {
    companion object {
        fun of(property: Property): PropertyWithUnitResponse =
            PropertyWithUnitResponse(
                id = property.id,
                name = property.name,
                unitList = property.unitList.map(UnitDto::of)
            )
    }
}
