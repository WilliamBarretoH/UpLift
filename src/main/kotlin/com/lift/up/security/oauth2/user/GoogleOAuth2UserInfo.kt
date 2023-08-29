package com.lift.up.security.oauth2.user


class GoogleOAuth2UserInfo(attributes: Map<String, Any>) : OAuth2UserInfo(attributes) {
    override val id: String
        get() = attributes.get("sub").toString()
    override val name: String
        get() = attributes.get("name").toString()
    override val email: String
        get() = attributes.get("email").toString()
    override val imageUrl: String
        get() = attributes.get("picture").toString()
}
