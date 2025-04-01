package com.ts_voc_back.user.join.controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ts_voc_back.common.model.ComResult;
import com.ts_voc_back.user.join.model.param.PInsertUser;
import com.ts_voc_back.user.join.service.JoinService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class JoinController {
	@Autowired
	final JoinService joinService = null;

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

	@PostMapping("/api/joinProc")
	@ResponseBody
    public ComResult<Integer> joinProc(
			HttpServletRequest req
			, HttpServletResponse res
			, @RequestBody PInsertUser pInsertUser
	) {
        return joinService.insertUser(pInsertUser);
    }
}
