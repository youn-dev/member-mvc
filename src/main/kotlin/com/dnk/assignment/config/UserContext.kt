package com.dnk.assignment.config

import com.dnk.assignment.dto.user.UserContextDto
import com.dnk.assignment.helper.CommonException
import com.dnk.assignment.helper.CustomException

object UserContext {
    private val threadLocal = ThreadLocal<UserContextDto?>()

    fun set(user: UserContextDto?) {
        threadLocal.set(user)
    }

    fun get(): UserContextDto =
        threadLocal.get()
            ?: throw CustomException(CommonException.NOT_AUTHORIZED)

    fun remove() {
        threadLocal.remove()
    }
}
