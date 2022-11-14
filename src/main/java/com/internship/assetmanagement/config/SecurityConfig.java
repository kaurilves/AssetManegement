package com.internship.assetmanagement.config;

import com.internship.assetmanagement.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private static final String URL_SECURE = "/vendor/*";

    private final UserService userService;

    @Bean
    public FilterRegistrationBean<SecurityFilter> securityFilterUser() {
        FilterRegistrationBean<SecurityFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setName("UserFilter");
        registrationBean.setFilter(new SecurityFilter(userService));
        registrationBean.addUrlPatterns(
                URL_SECURE
        );

        return registrationBean;
    }
}
