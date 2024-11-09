package com.dnk.assignment.controller

import com.dnk.assignment.helper.CookieConstant
import com.dnk.assignment.helper.CookieHelper
import com.dnk.assignment.model.request.LoginRequest
import com.dnk.assignment.model.request.SignupRequest
import com.dnk.assignment.model.response.JwtResponse
import com.dnk.assignment.service.user.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.HttpServletResponse
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
        httpServletResponse: HttpServletResponse,
    ): ResponseEntity<JwtResponse> {
        return userService.signup(request).let { response ->
            ResponseEntity.ok().body(response).also {
                CookieHelper.setCookie(
                    domain = CookieConstant.DKN_DOMAIN,
                    key = CookieConstant.DNK_AUTH_COOKIE,
                    value = response.jwt,
                    response = httpServletResponse,
                    maxAge = CookieConstant.THIRTY_DAYS,
                )
            }
        }
    }

    @Operation(summary = "로그인")
    @PostMapping("/login")
    fun login(
        @Valid @RequestBody request: LoginRequest,
        httpServletResponse: HttpServletResponse,
    ): ResponseEntity<JwtResponse> {
        return userService.login(request).let { response ->
            ResponseEntity.ok().body(response).also {
                CookieHelper.setCookie(
                    domain = CookieConstant.DKN_DOMAIN,
                    key = CookieConstant.DNK_AUTH_COOKIE,
                    value = response.jwt,
                    response = httpServletResponse,
                    maxAge = CookieConstant.THIRTY_DAYS,
                )
            }
        }
    }
}
