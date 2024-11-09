package com.dnk.assignment.config

import com.dnk.assignment.aop.DnkAuthenticationInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(
    private val dnkAuthenticationInterceptor: DnkAuthenticationInterceptor,
) : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**").allowedMethods("*")
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(dnkAuthenticationInterceptor)
            .addPathPatterns("/v1/users/**")
            .addPathPatterns("/v1/properties/**")
            .excludePathPatterns("/v1/users/signup")
            .excludePathPatterns("/v1/users/login")
    }
}
