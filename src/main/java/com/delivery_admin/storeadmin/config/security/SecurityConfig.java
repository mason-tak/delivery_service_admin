package com.delivery_admin.storeadmin.config.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity // security 활성화
public class SecurityConfig {
    private List<String> SWAGGER = List.of(
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/api-docs/**"
    );

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(it -> {
                    it
                            // resource에 대해서는 모든 요청 허용
                            .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                            // swagger 는 인증 없이 통과
                            .requestMatchers(SWAGGER.toArray(new String[0])).permitAll()
                            .requestMatchers("/open-api/**").permitAll()
                            .anyRequest().authenticated();

                })
                .formLogin(Customizer.withDefaults());

        http.csrf(csrf -> csrf.disable());

        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // hash 방식으로 암호화
        return new BCryptPasswordEncoder();
    }
}
