package com.ts_voc_back.Security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//		http.csrf((auth) -> auth.disable());

        http.authorizeHttpRequests((auth) -> auth
    		.requestMatchers("/login", "/loginProc", "/join", "/joinProc").permitAll()
    		.requestMatchers("/admin").hasRole("ADMIN")
    		.requestMatchers("/success").hasRole("USER")
    		.anyRequest().authenticated()
        );

        http.formLogin((auth) -> auth
        	.loginPage("/login")
        	.usernameParameter("loginId")
        	.passwordParameter("pwd")
        	.loginProcessingUrl("/loginProc")
        	.defaultSuccessUrl("/loginOk")
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
}
