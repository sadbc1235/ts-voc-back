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

import jakarta.servlet.http.HttpSession;

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
    		.requestMatchers("/api/loginProc", "/api/joinProc", "/api/loginCheck", "/api/loginFail", "/api/logout").permitAll()
    		.requestMatchers("/api/ts/**").hasAnyRole("ADMIN")
    		.requestMatchers("/api/**").hasAnyRole("USER", "ADMIN")
    		.anyRequest().authenticated()
        );

        http.formLogin((auth) -> auth
        	.usernameParameter("loginId")
        	.passwordParameter("pwd")
        	.loginProcessingUrl("/api/loginProc")
        	.defaultSuccessUrl("/api/loginCheck")
        	.failureHandler(authFailureHandler)
        );

        http.logout( logout -> logout
        		.logoutUrl("/api/logout")
        		.addLogoutHandler((request, response, authentication) -> {
                    HttpSession session = request.getSession();
                    if (session != null) {
                        session.invalidate();
                    }
                })
        		.logoutSuccessHandler((request, response, authentication) ->
                	response.sendRedirect("/api/loginCheck")
                )
        		.deleteCookies("JSESSIONID")
        );

        http.sessionManagement( auth -> auth
        	.maximumSessions(1)
        	.maxSessionsPreventsLogin(false)
        	.expiredUrl("/api/loginCheck")
        );

        http.sessionManagement( auth -> auth
        	.sessionFixation().changeSessionId()
        	.invalidSessionUrl("/api/loginCheck")
        );

        return http.build();
    }
}
