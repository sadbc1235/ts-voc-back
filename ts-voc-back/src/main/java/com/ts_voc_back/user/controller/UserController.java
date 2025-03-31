package com.ts_voc_back.user.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ts_voc_back.common.model.ComResult;
import com.ts_voc_back.user.model.param.PInsertUser;
import com.ts_voc_back.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
	@Autowired
	final UserService userService= null;

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

	@GetMapping("/loginOk")
    public ResponseEntity<Map<String, String>> loginOk() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loginId = authentication.getName();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> roleList = new ArrayList<>();
        for(GrantedAuthority auth : authorities) {
        	roleList.add(auth.getAuthority());
        }

        Map<String, String> userInfo = new HashMap<>();
        System.out.println("로그인한 유저 loginId:" + loginId);
        for(String role : roleList) {
        	System.out.println("유저 권한:" + role);
        	userInfo.put("role", role);
        }



        userInfo.put("loginId", loginId);

        return ResponseEntity.ok(userInfo);
    }


	@GetMapping("/join")
    public String joinP() {

        return "<form action=\"/joinProc\" method=\"post\" name=\"joinForm\">\r\n"
        		+ "    <input type=\"text\" name=\"loginId\" placeholder=\"loginId\"/>\r\n"
        		+ "    <input type=\"text\" name=\"userName\" placeholder=\"userName\"/>\r\n"
        		+ "    <input type=\"password\" name=\"pwd\" placeholder=\"Password\"/>\r\n"
        		+ "    <input type=\"text\" name=\"email\" placeholder=\"email\"/>\r\n"
        		+ "		<input type=\"submit\" value=\"Join\"/>\r\n"
        		+ "</form>";
    }

	@PostMapping("/joinProc")
	@ResponseBody
    public ComResult<Integer> joinProc(
			HttpServletRequest req
			, HttpServletResponse res
			, @RequestBody PInsertUser pInsertUser
	) {
        return userService.insertUser(pInsertUser);
    }
}
