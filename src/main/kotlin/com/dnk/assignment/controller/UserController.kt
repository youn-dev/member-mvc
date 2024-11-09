package com.dnk.assignment.controller

import com.dnk.assignment.model.request.SignupRequest
import com.dnk.assignment.model.response.SignupResponse
import com.dnk.assignment.service.user.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "사용자")
@RestController
@RequestMapping("/v1/users")
@Validated
class UserController(
    private val userService: UserService,
) {
    @Operation(summary = "회원가입")
    @PostMapping("/signup")
    fun signup(
        @Valid @RequestBody request: SignupRequest,
    ): ResponseEntity<SignupResponse> {
        return userService.signup(request).let {
            ResponseEntity.ok().body(it)
        }
    }
}
