package com.lift.up.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Service


@ConfigurationProperties(prefix = "app")
@Service
class AppProperties(val auth: Auth = Auth(),
                    val oauth2: OAuth2 = OAuth2()) {


    class Auth {
        var tokenSecret: String? = null
        var tokenExpirationMsec: Long = 0
    }

    class OAuth2 {
        var authorizedRedirectUris: List<String> = ArrayList()
            private set

        fun authorizedRedirectUris(authorizedRedirectUris: List<String>): OAuth2 {
            this.authorizedRedirectUris = authorizedRedirectUris
            return this
        }
    }
}
