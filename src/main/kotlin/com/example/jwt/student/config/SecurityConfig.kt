package com.example.jwt.student.config


import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class SecurityConfig(
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
    private val dynamicAuthorizationManager: DynamicAuthorizationManager
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .csrf { it.disable() }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authorizeHttpRequests {
                it.requestMatchers(
                    "/",
                    "/index.html",
                    "/styles.css",
                    "/app.js",
                    "/favicon.ico",
                    "/api/anime/image"
                ).permitAll()

                it.requestMatchers(
                    HttpMethod.POST,
                    "/api/student/register",
                    "/api/student/login"
                ).permitAll()

                it.anyRequest().access(dynamicAuthorizationManager)
            }
            .addFilterBefore(
                jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter::class.java
            )
            .build()
    }
}
