package com.dnk.assignment.service.property

import com.dnk.assignment.domain.property.Property
import com.dnk.assignment.domain.property.PropertyRepository
import com.dnk.assignment.dto.property.PropertyDto
import com.dnk.assignment.helper.CommonException
import com.dnk.assignment.helper.CustomException
import com.dnk.assignment.model.request.CreatePropertyRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PropertyService(
    private val propertyRepository: PropertyRepository,
) {
    @Transactional
    fun create(request: CreatePropertyRequest) {
        val property = propertyRepository.findByName(request.name)
            ?.let(PropertyDto::of)
        if (property != null) {
            throw CustomException(CommonException.PROPERTY_ALREADY_EXIST)
        }

        propertyRepository.save(
            Property(name = request.name)
        )
    }
}
