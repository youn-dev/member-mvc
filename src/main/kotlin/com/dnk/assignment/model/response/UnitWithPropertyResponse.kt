package com.dnk.assignment.model.response

import com.dnk.assignment.domain.unit.Unit
import com.dnk.assignment.dto.property.PropertyDto

data class UnitWithPropertyResponse(
    val id: Long,
    val property: PropertyDto?,
    val block: String,
    val name: String,
) {
    companion object {
        fun of(unit: Unit): UnitWithPropertyResponse = UnitWithPropertyResponse(
            id = unit.id,
            property = unit.property?.let(PropertyDto::of),
            block = unit.block,
            name = unit.name
        )
    }
}
