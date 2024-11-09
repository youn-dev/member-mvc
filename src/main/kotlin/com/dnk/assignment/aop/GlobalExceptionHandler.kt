package com.dnk.assignment.aop

import com.dnk.assignment.helper.CustomException
import org.slf4j.LoggerFactory
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
class GlobalExceptionHandler {
    private val logger = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(CustomException::class)
    fun handleCustomException(e: CustomException): ResponseEntity<Any> {
        logger.warn("${e.message}", e)
        return ResponseEntity(
            mapOf(
                "error" to e.error,
                "errorCode" to e.error.errorCode,
                "message" to e.customMessage,
            ),
            null,
            HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(e: MethodArgumentNotValidException): ResponseEntity<Any> {
        val errors = e.bindingResult
            .fieldErrors
            .associate { it.field to (it.defaultMessage ?: "Invalid value") }

        return ResponseEntity(
            errors,
            null,
            HttpStatus.BAD_REQUEST
        )
    }
}
