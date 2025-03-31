package com.ts_voc_back.Security.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.ts_voc_back.Security.dto.AuthFailureHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		AuthFailureHandler authFailureHandler = new AuthFailureHandler();
		
		http.csrf((auth) -> auth.disable());

        http.authorizeHttpRequests((auth) -> auth
    		.requestMatchers("/api/loginProc", "/api/joinProc").permitAll()
    		.requestMatchers("/api/admin/**").hasAnyRole("ADMIN")
    		.requestMatchers("/api/user/**").hasAnyRole("USER", "ADMIN")
    		.anyRequest().authenticated()
        );

        http.formLogin((auth) -> auth
        	.usernameParameter("loginId")
        	.passwordParameter("pwd")
        	.loginProcessingUrl("/api/loginProc")
        	.defaultSuccessUrl("/api/loginOk")
        	.failureHandler(authFailureHandler)
        );

        http.sessionManagement( auth -> auth
        	.maximumSessions(2)
        	.maxSessionsPreventsLogin(true)
        );

        http.sessionManagement( auth -> auth
        	.sessionFixation().changeSessionId()
        );

        return http.build();
    }
	
	@Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:3000");// 리액트 서버
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
