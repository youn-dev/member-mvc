package com.dnk.assignment.service.user

import com.dnk.assignment.domain.user.User
import com.dnk.assignment.domain.user.UserRepository
import com.dnk.assignment.dto.user.UserDto
import com.dnk.assignment.enums.UserRequestType
import com.dnk.assignment.helper.CommonException
import com.dnk.assignment.helper.CustomException
import com.dnk.assignment.model.request.SignupRequest
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun signup(request: SignupRequest) {
        println("111111111 $request")
        validateUser(email = request.email, type = UserRequestType.SIGNUP)
        userRepository.save(
            User(
                email = request.email,
                password = request.password,
                name = request.name,
            )
        )
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
}
