package com.lift.up.security.oauth2.user

import com.lift.up.domain.model.AuthProvider
import com.lift.up.exception.OAuth2AuthenticationProcessingException


object OAuth2UserInfoFactory {
    fun getOAuth2UserInfo(registrationId: String, attributes: Map<String, Any>): OAuth2UserInfo {
        return if (registrationId.equals(AuthProvider.google.toString(), ignoreCase = true)) {
            GoogleOAuth2UserInfo(attributes)
        } else {
            throw OAuth2AuthenticationProcessingException("Sorry! Login with $registrationId is not supported yet.")
        }
    }
}
