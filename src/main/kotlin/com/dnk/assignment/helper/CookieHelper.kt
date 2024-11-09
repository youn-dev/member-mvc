package com.dnk.assignment.helper

import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.ResponseCookie

class CookieHelper {
    companion object {
        fun setCookie(
            key: String,
            value: String,
            response: HttpServletResponse,
            maxAge: Long,
            domain: String,
        ) {
            val cookie = ResponseCookie.from(key, value)
                .domain(domain)
                .path("/")
                .sameSite("Strict")
                .httpOnly(true)
                .secure(true)
                .maxAge(maxAge)
                .build()
            response.addHeader("Set-Cookie", cookie.toString())
        }
    }
}