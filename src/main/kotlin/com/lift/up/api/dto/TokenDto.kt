package com.lift.up.api.dto

import java.util.Date

data class TokenDto(
    val userName: String? = null,
    val authenticated: Boolean = false,
    val created: Date? = null,
    val expiration: Date? = null,
    val accessToken: String? = null,
    val refreshToken: String? = null,
)