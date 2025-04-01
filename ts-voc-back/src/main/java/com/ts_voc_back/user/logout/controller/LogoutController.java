package com.ts_voc_back.user.logout.controller;

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
import com.ts_voc_back.user.logout.service.LogoutService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LogoutController {
	@Autowired
	final LogoutService logoutService = null;

//	@GetMapping("/api/loginFail")
//    public ComResult<Map<String, String>> loginFail(@RequestParam("error") String errorMsg) {
//		ComResult<Map<String, String>> result = new ComResult<Map<String, String>>();
//		result.setFail(errorMsg);
//        return result;
//    }
//
//	@PostMapping("/api/joinProc")
//	@ResponseBody
//    public ComResult<Integer> joinProc(
//			HttpServletRequest req
//			, HttpServletResponse res
//			, @RequestBody PInsertUser pInsertUser
//	) {
//        return userService.insertUser(pInsertUser);
//    }
}
