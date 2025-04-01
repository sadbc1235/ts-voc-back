package com.ts_voc_back.user.login.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ts_voc_back.common.model.ComResult;
import com.ts_voc_back.user.login.service.LoginService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LoginController {
	@Autowired
	final LoginService loginService = null;

	@GetMapping("/login")
    public String loginP() {
        return "<html>\r\n"
        		+ "<head>\r\n"
        		+ "    <meta charset=\"UTF-8\">\r\n"
        		+ "    <meta name=\"viewport\"\r\n"
        		+ "          content=\"width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0\">\r\n"
        		+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\r\n"
        		+ "    <title>Document</title>\r\n"
        		+ "</head>\r\n"
        		+ "<body>\r\n"
        		+ "    login\r\n"
        		+ "    <hr>\r\n"
        		+ "    <form action=\"/loginProc\" method=\"post\" name=\"loginForm\">\r\n"
        		+ "        <input id=\"loginId\" type=\"text\" name=\"loginId\" placeholder=\"id\"/>\r\n"
        		+ "        <input id=\"pwd\" type=\"password\" name=\"pwd\" placeholder=\"password\"/>\r\n"
        		+ "        <input type=\"submit\" value=\"login\"/>\r\n"
        		+ "    </form>\r\n"
        		+ "</body>\r\n"
        		+ "</html>";
    }

	@GetMapping("/api/loginCheck")
    public ComResult<Map<String, String>> loginOk() {
		ComResult<Map<String, String>> result = new ComResult<Map<String, String>>();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		System.out.println("principal : "+principal);
		if(principal.equals("anonymousUser")) {
			result.setFail("세션이 만료 되었습니다.");
			return result;
		} else {
			String loginId = authentication.getName();
	        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
	        List<String> roleList = new ArrayList<>();
	        for(GrantedAuthority auth : authorities) {
	        	roleList.add(auth.getAuthority());
	        }
	        Map<String, String> userInfo = new HashMap<>();
	        for(String role : roleList) {
	        	userInfo.put("role", role);
	        }
	        userInfo.put("loginId", loginId);

	        result.setSuccess(userInfo);
	        return result;
		}
    }

	@GetMapping("/api/loginFail")
    public ComResult<Map<String, String>> loginFail(@RequestParam("error") String errorMsg) {
		ComResult<Map<String, String>> result = new ComResult<Map<String, String>>();
		result.setFail(errorMsg);
        return result;
    }
}
