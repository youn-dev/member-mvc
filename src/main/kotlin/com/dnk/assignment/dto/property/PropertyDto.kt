package com.dnk.assignment.dto.property

import com.dnk.assignment.domain.property.Property

data class PropertyDto(
    val id: Long,
    val name: String,
) {
    companion object {
        fun of(property: Property): PropertyDto = PropertyDto(
            id = property.id,
            name = property.name,
        )
    }
}
