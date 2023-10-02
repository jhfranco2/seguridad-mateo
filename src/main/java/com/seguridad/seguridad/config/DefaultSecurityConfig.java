package com.seguridad.seguridad.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.seguridad.seguridad.dao.UserService;

@EnableWebSecurity
@Configuration
public class DefaultSecurityConfig {

    /*
     * @Bean
     * InMemoryUserDetailsManager inMemoryUserDetailsManager() {
     * String generatePassword = "12345";
     * String encryptedPassword = passwordEncoder().encode(generatePassword);
     * return new InMemoryUserDetailsManager(
     * User.withUsername("user")
     * .password(encryptedPassword)
     * .passwordEncoder(p -> passwordEncoder().encode(generatePassword))
     * .roles("USER").build());
     * 
     * }
     */

    @Bean
    public UserService userDetailsService() {
        return new UserService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http

                .formLogin(form -> form
                        .loginPage("/custom-login")

                        .permitAll()

                ).authorizeHttpRequests((authorize)-> authorize.anyRequest().authenticated());

        http.userDetailsService(userDetailsService());

        return http.build();
    }

    @Bean
    @ConditionalOnMissingBean(AuthenticationEventPublisher.class)
    DefaultAuthenticationEventPublisher defaultAuthenticationEventPublisher(ApplicationEventPublisher delegate) {
        return new DefaultAuthenticationEventPublisher(delegate);
    }
}
