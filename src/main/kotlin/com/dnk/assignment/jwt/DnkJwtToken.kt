package com.dnk.assignment.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws

class DnkJwtToken(
    private val claims: Jws<Claims>
) {
    fun getUserId(): Long = claims.body.subject.toLong()

    fun getEmail(): String = claims.body["email"].toString()

    fun getName(): String = claims.body["name"].toString()
}
