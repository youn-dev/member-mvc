package com.dnk.assignment.service.user

import com.dnk.assignment.domain.user.User
import com.dnk.assignment.domain.user.UserRepository
import com.dnk.assignment.dto.user.UserDto
import com.dnk.assignment.enums.UserRequestType
import com.dnk.assignment.helper.CommonException
import com.dnk.assignment.helper.CustomException
import com.dnk.assignment.jwt.DnkJwt
import com.dnk.assignment.jwt.DnkTokenRequest
import com.dnk.assignment.model.request.SignupRequest
import com.dnk.assignment.model.response.SignupResponse
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val dnkJwt: DnkJwt,
) {
    fun signup(request: SignupRequest): SignupResponse {
        validateUser(email = request.email, type = UserRequestType.SIGNUP)
        val newUser = userRepository.save(
            User(
                email = request.email,
                password = request.password,
                name = request.name,
            )
        )
        val jwt = makeJwt(sub = newUser.id, email = newUser.email, name = newUser.email)
        return SignupResponse(jwt)
    }

    private fun validateUser(email: String, type: UserRequestType) {
        val user = userRepository.findOneByEmail(email)?.let(UserDto::of)
        if (user != null && type == UserRequestType.SIGNUP) {
            throw CustomException(CommonException.USER_ALREADY_EXIST, email)
        }
        if (user == null && type == UserRequestType.LOGIN) {
            throw CustomException(CommonException.USER_NOT_EXIST)
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
