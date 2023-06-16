package com.example.config

import com.example.service.IdGenerator
import io.netty.handler.codec.http.HttpMethod
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity.AuthorizeExchangeSpec
import org.springframework.security.config.web.server.ServerHttpSecurity.OAuth2ResourceServerSpec
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder
import org.springframework.security.web.server.SecurityWebFilterChain
import reactor.core.publisher.Mono

@Configuration
class Configuration {

    @Bean
    fun idGenerator(): IdGenerator = IdGenerator.instance

    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain? {
        // @formatter:off
        http
            .authorizeExchange { authorize: AuthorizeExchangeSpec ->
                authorize
                    .pathMatchers(HttpMethod.GET.toString(), "/actuator/**").permitAll()
                    .anyExchange().authenticated()
            }
            .oauth2ResourceServer { resourceServer: OAuth2ResourceServerSpec ->
                resourceServer
                    .jwt(withDefaults())
            }
        // @formatter:on
        return http.build()
    } @Bean
    fun jwtReactiveDecoder(): ReactiveJwtDecoder {
        return ReactiveJwtDecoder {
            val jwt = jwt().claim("scope", "").build()
            Mono.just(jwt)
        }
    }
}

fun jwt(): Jwt.Builder {
    return Jwt.withTokenValue("token").header("alg", "none")
}
