package com.lift.up.security.oauth2

import com.lift.up.config.AppProperties
import com.lift.up.exception.BadRequestException
import com.lift.up.security.TokenProvider
import com.lift.up.security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository.Companion.REDIRECT_URI_PARAM_COOKIE_NAME
import com.lift.up.util.CookieUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.stereotype.Component
import org.springframework.web.util.UriComponentsBuilder
import java.io.IOException
import java.net.URI
import java.util.*
import javax.servlet.ServletException
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
class OAuth2AuthenticationSuccessHandler @Autowired internal constructor(
    tokenProvider: TokenProvider, appProperties: AppProperties,
    httpCookieOAuth2AuthorizationRequestRepository: HttpCookieOAuth2AuthorizationRequestRepository
) : SimpleUrlAuthenticationSuccessHandler() {
    private final val tokenProvider: TokenProvider
    private final val appProperties: AppProperties
    private final val httpCookieOAuth2AuthorizationRequestRepository: HttpCookieOAuth2AuthorizationRequestRepository

    init {
        this.tokenProvider = tokenProvider
        this.appProperties = appProperties
        this.httpCookieOAuth2AuthorizationRequestRepository = httpCookieOAuth2AuthorizationRequestRepository
    }

    @Throws(IOException::class, ServletException::class)
    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val targetUrl = determineTargetUrl(request, response, authentication)
        if (response.isCommitted) {
            logger.debug("Response has already been committed. Unable to redirect to $targetUrl")
            return
        }
        clearAuthenticationAttributes(request, response)
        redirectStrategy.sendRedirect(request, response, targetUrl)
    }

    override fun determineTargetUrl(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ): String {
        val redirectUri: Optional<String> = CookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
            .map { obj: Cookie -> obj.value }
        if (redirectUri.isPresent && !isAuthorizedRedirectUri(redirectUri.get())) {
            throw BadRequestException("Sorry! We've got an Unauthorized Redirect URI and can't proceed with the authentication")
        }
        val targetUrl = redirectUri.orElse(defaultTargetUrl)
        val token: String = tokenProvider.createToken(authentication)
        return UriComponentsBuilder.fromUriString(targetUrl)
            .queryParam("token", token)
            .build().toUriString()
    }

    protected fun clearAuthenticationAttributes(request: HttpServletRequest, response: HttpServletResponse) {
        super.clearAuthenticationAttributes(request)
        httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response)
    }

    private fun isAuthorizedRedirectUri(uri: String): Boolean {
        val clientRedirectUri = URI.create(uri)
        return appProperties.oauth2.authorizedRedirectUris
            .stream()
            .anyMatch { authorizedRedirectUri ->
                // Only validate host and port. Let the clients use different paths if they want to
                val authorizedURI = URI.create(authorizedRedirectUri)
                (authorizedURI.host.equals(clientRedirectUri.host, ignoreCase = true)
                        && authorizedURI.port == clientRedirectUri.port)
            }
    }
}

