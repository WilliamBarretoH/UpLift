package com.lift.up.security.oauth2

import com.lift.up.domain.entity.User
import com.lift.up.domain.model.AuthProvider
import com.lift.up.domain.repository.UserRepository
import com.lift.up.exception.OAuth2AuthenticationProcessingException
import com.lift.up.security.UserPrincipal
import com.lift.up.security.oauth2.user.OAuth2UserInfo
import com.lift.up.security.oauth2.user.OAuth2UserInfoFactory
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.InternalAuthenticationServiceException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.OAuth2AuthenticationException
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import java.util.*


@Service
@RequiredArgsConstructor
class CustomOAuth2UserService(private val userRepository: UserRepository) : DefaultOAuth2UserService() {

    @Throws(OAuth2AuthenticationException::class)
    override fun loadUser(oAuth2UserRequest: OAuth2UserRequest): OAuth2User {
        val oAuth2User = super.loadUser(oAuth2UserRequest)
        return try {
            processOAuth2User(oAuth2UserRequest, oAuth2User)
        } catch (ex: AuthenticationException) {
            throw ex
        } catch (ex: Exception) {
            // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
            throw InternalAuthenticationServiceException(ex.message, ex.cause)
        }
    }

    private fun processOAuth2User(oAuth2UserRequest: OAuth2UserRequest, oAuth2User: OAuth2User): OAuth2User {
        val oAuth2UserInfo: OAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(
            oAuth2UserRequest.clientRegistration.registrationId,
            oAuth2User.attributes
        )
        if (StringUtils.isEmpty(oAuth2UserInfo.email)) {
            throw OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider")
        }
        val userOptional: Optional<User> = userRepository.findByEmail(oAuth2UserInfo.email) ?: throw UsernameNotFoundException("")
        var user: User
        if (userOptional.isPresent) {
            user = userOptional.get()
            if (!user.provider?.equals(AuthProvider.valueOf(oAuth2UserRequest.clientRegistration.registrationId))!!) {
                throw OAuth2AuthenticationProcessingException(
                    (("Looks like you're signed up with " +
                            user.provider).toString() + " account. Please use your " + user.provider).toString() +
                            " account to login."
                )
            }
            user = updateExistingUser(user, oAuth2UserInfo)
        } else {
            user = registerNewUser(oAuth2UserRequest, oAuth2UserInfo)
        }
        return UserPrincipal.create(user, oAuth2User.attributes)
    }

    private fun registerNewUser(oAuth2UserRequest: OAuth2UserRequest, oAuth2UserInfo: OAuth2UserInfo): User {
        val user = User()
        user.provider = AuthProvider.valueOf(oAuth2UserRequest.clientRegistration.registrationId)
        user.providerId = (oAuth2UserInfo.id)
        user.fullName = (oAuth2UserInfo.name)
        user.email = (oAuth2UserInfo.email)
        user.imageUrl = (oAuth2UserInfo.imageUrl)
        return userRepository.save(user)
    }

    private fun updateExistingUser(existingUser: User, oAuth2UserInfo: OAuth2UserInfo): User {
        existingUser.fullName = (oAuth2UserInfo.name)
        existingUser.imageUrl = (oAuth2UserInfo.imageUrl)
        return userRepository.save(existingUser)
    }
}

