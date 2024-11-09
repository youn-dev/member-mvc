package com.dnk.assignment.helper

open class CommonRuntimeException(
    val error: CommonError,
    val customMessage: String? = null
) : RuntimeException(customMessage ?: error.message)

class CustomException(error: CommonException, vararg variables: String) :
    CommonRuntimeException(error = error, customMessage = replaceCustomVariables(error.message, variables))

private fun replaceCustomVariables(original: String?, variables: Array<out String>): String? {
    var message = original
    for (i: Int in variables.indices) {
        message = message?.replace("{$i}", variables[i])
    }
    return message
}
