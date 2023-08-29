package com.lift.up.api.payload

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank


class LoginRequest {
    var email: @NotBlank @Email String? = null
    var password: @NotBlank String? = null
}
