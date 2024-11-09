package com.dnk.assignment.service.user

import com.dnk.assignment.domain.user.User
import com.dnk.assignment.domain.user.UserRepository
import com.dnk.assignment.dto.user.UserDto
import com.dnk.assignment.helper.CommonException
import com.dnk.assignment.helper.CustomException
import com.dnk.assignment.jwt.DnkJwt
import com.dnk.assignment.jwt.DnkTokenRequest
import com.dnk.assignment.model.request.LoginRequest
import com.dnk.assignment.model.request.SignupRequest
import com.dnk.assignment.model.response.JwtResponse
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder,
    private val dnkJwt: DnkJwt,
) {
    @Transactional
    fun signup(request: SignupRequest): JwtResponse {
        val user = userRepository.findOneByEmail(request.email)?.let(UserDto::of)
        if (user != null) {
            throw CustomException(CommonException.USER_ALREADY_EXIST)
        }

        val encryptedPassword = bCryptPasswordEncoder.encode(request.password)
        val newUser = userRepository.save(
            User(
                email = request.email,
                password = encryptedPassword,
                name = request.name,
            )
        )
        val jwt = makeJwt(sub = newUser.id, email = newUser.email, name = newUser.email)
        return JwtResponse(jwt)
    }

    @Transactional(readOnly = true)
    fun login(request: LoginRequest): JwtResponse {
        val user = userRepository.findOneByEmail(request.email)?.let(UserDto::of)
            ?: throw CustomException(CommonException.USER_NOT_EXIST)
        authenticate(user = user, password = request.password)
        val jwt = makeJwt(sub = user.id, email = user.email, name = user.email)
        return JwtResponse(jwt)
    }

    private fun authenticate(user: UserDto, password: String): Boolean {
        return when (bCryptPasswordEncoder.matches(password, user.password)) {
            true -> true
            false -> throw CustomException(CommonException.INVALID_PASSWORD)
        }
    }

    private fun makeJwt(sub: Long, email: String, name: String): String =
        dnkJwt.encode(
            DnkTokenRequest(
                sub = sub,
                email = email,
                name = name,
            )
        )
}
