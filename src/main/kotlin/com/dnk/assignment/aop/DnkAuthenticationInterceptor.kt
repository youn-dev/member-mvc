package com.dnk.assignment.aop

import com.dnk.assignment.config.UserContext
import com.dnk.assignment.domain.user.UserRepository
import com.dnk.assignment.dto.user.UserContextDto
import com.dnk.assignment.helper.CommonException
import com.dnk.assignment.helper.CookieConstant.DNK_AUTH_COOKIE
import com.dnk.assignment.helper.CustomException
import com.dnk.assignment.jwt.DnkJwt
import com.dnk.assignment.jwt.DnkJwtToken
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor

@Component
class DnkAuthenticationInterceptor(
	private val userRepository: UserRepository,
	private val dnkJwt: DnkJwt
) : HandlerInterceptor {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        request.cookies?.firstOrNull { it.name == DNK_AUTH_COOKIE }?.let { token ->
            setUserContext(token.value)
        } ?: run {
            val bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION)
            if (bearerToken == null || bearerToken.length < 7) {
                UserContext.set(null)
                throw CustomException(CommonException.NOT_AUTHORIZED)
            }
            setUserContext(bearerToken.substring("Bearer ".length))
        }

        return true
    }

    private fun setUserContext(token: String) {
        try {
            val dnkJwtToken: DnkJwtToken = dnkJwt.decode(token = token)
            val userId = dnkJwtToken.getUserId()
            val user = userRepository.findOneById(userId)?.let(UserContextDto::of)
                ?: throw CustomException(CommonException.NOT_AUTHORIZED)
            UserContext.set(user)
        } catch (e: Exception) {
            throw CustomException(CommonException.NOT_AUTHORIZED)
        }
    }

    override fun afterCompletion(request: HttpServletRequest, response: HttpServletResponse, handler: Any, ex: Exception?) {
        UserContext.remove()
    }
}
