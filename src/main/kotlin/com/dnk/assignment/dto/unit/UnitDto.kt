package com.dnk.assignment.dto.unit

import com.dnk.assignment.domain.unit.Unit

data class UnitDto(
    val id: Long,
    val propertyId: Long?,
    val block: String,
    val name: String,
) {
    companion object {
        fun of(unit: Unit): UnitDto = UnitDto(
            id = unit.id,
            propertyId = unit.property?.id,
            block = unit.block,
            name = unit.name,
        )
    }
}
