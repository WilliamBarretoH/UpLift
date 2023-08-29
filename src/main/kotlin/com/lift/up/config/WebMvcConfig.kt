package com.lift.up.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.util.Arrays


@Configuration
class WebMvcConfig : WebMvcConfigurer {

    @Value("\${app.cors.allowedOrigins}")
    private val allowedOrigins: Array<String> = arrayOf("")
    override fun addCorsMappings(registry: CorsRegistry) {
        val MAX_AGE_SECS: Long = 3600
        registry.addMapping("/**")
            .allowedOrigins(*allowedOrigins)
            .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
            .allowedHeaders("*")
            .allowCredentials(true)
            .maxAge(MAX_AGE_SECS)
    }
}
