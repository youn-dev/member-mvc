package com.dnk.assignment.helper

enum class CommonException(
    override val errorCode: String,
    override val message: String,
) : CommonError {
    USER_ALREADY_EXIST("1001", "이미 회원가입 된 이메일입니다."),
    USER_NOT_EXIST("1002", "회원가입 되지 않은 이메일입니다."),
    INVALID_TOKEN("1003", "토큰이 유효하지 않습니다."),
    INVALID_PASSWORD("1004", "비밀번호가 일치하지 않습니다."),
    NOT_AUTHORIZED("1005", "인증되지 않았습니다."),
    PROPERTY_ALREADY_EXIST("1006", "해당 자산은 이미 존재합니다."),
    ;
}
