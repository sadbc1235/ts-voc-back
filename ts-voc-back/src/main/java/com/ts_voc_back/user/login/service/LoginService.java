package com.ts_voc_back.user.login.service;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ts_voc_back.common.model.ComResult;
import com.ts_voc_back.user.login.mapper.LoginMapper;
import com.ts_voc_back.user.login.model.param.PSelectUserInfo;
import com.ts_voc_back.user.login.model.result.RSelectUserInfo;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {

	@Autowired
	final LoginMapper loginMapper = null;

	public ComResult<Map<String, String>> loginCheck(HttpServletResponse response) {
		ComResult<Map<String, String>> result = new ComResult<Map<String, String>>();
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Object principal = authentication.getPrincipal();

			if(principal.equals("anonymousUser")) {
				Cookie cookie = new Cookie("JSESSIONID", null);
				cookie.setPath("/");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
				result.setFail("세션이 만료 되었습니다.");
				return result;
			} else {
				String loginId = authentication.getName();

				PSelectUserInfo pSelectUserInfo = new PSelectUserInfo();
				pSelectUserInfo.setLoginId(loginId);
				RSelectUserInfo _userInfo = loginMapper.selectUserInfo(pSelectUserInfo);

				Map<String, String> userInfo = new HashMap<>();
				userInfo.put("userSeq", _userInfo.getUserSeq());
				userInfo.put("allowYn", _userInfo.getAllowYn());

				if(_userInfo.getAllowYn().equals("N")) {
					Cookie cookie = new Cookie("JSESSIONID", null);
					cookie.setPath("/");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
					result.setFail("해당계정은 승인되지 않았습니다. 담당에게 연락 부탁드립니다.");
					return result;
				}
		        result.setSuccess(userInfo);
			}
		} catch(Exception ex) {
			result.setError(ex);
		}

		return result;
	}
}
