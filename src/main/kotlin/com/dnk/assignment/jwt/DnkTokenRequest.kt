package com.dnk.assignment.jwt

data class DnkTokenRequest(
	val sub: Long,
	val email: String,
	val name: String,
)
