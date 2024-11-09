package com.dnk.assignment.jwt

import com.dnk.assignment.helper.CommonException
import com.dnk.assignment.helper.CustomException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

@Component
class DnkJwt {
    @Value("\${jwt.secret-key}")
    lateinit var secretKey: String

    fun encode(request: DnkTokenRequest): String {
        val key = Keys.hmacShaKeyFor(secretKey.toByteArray(StandardCharsets.UTF_8))
        val now = Date()
        val expiration = Instant.from(
            LocalDate.now().plusDays(30).atStartOfDay(ZoneId.of("Asia/Seoul"))
        )

        return Jwts.builder()
            .setHeaderParam("typ", "JWT")
            .claim("sub", request.sub)
            .claim("email", request.email)
            .claim("name", request.name)
            .setIssuedAt(now)
            .setExpiration(Date.from(expiration))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

    fun decode(token: String): DnkJwtToken {
        val key = Keys.hmacShaKeyFor(secretKey.toByteArray(StandardCharsets.UTF_8))
        try {
            return DnkJwtToken(
                claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
            )
        } catch (e: Exception) {
            throw CustomException(CommonException.INVALID_TOKEN)
        }
    }
}
