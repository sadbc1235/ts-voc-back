package com.ts_voc_back.Security.dto;

import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	@Override
    public void onAuthenticationFailure(
    		HttpServletRequest request, HttpServletResponse response, AuthenticationException exception
    	) throws IOException, ServletException {

        String errorMessage = null;

        if (exception instanceof BadCredentialsException || exception instanceof InternalAuthenticationServiceException || exception instanceof UsernameNotFoundException) {
            errorMessage = "아이디와 비밀번호를 확인해주세요.";
        } else {
            errorMessage = "알 수없는 오류입니다.";
        }

        errorMessage = URLEncoder.encode(errorMessage, "UTF-8");
        setDefaultFailureUrl("/api/loginFail?error=" + errorMessage);
        super.onAuthenticationFailure(request, response, exception);
    }
}
