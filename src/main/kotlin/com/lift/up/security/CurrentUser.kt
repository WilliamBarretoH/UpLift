package com.lift.up.security

import org.springframework.security.core.annotation.AuthenticationPrincipal


@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@AuthenticationPrincipal
annotation class CurrentUser