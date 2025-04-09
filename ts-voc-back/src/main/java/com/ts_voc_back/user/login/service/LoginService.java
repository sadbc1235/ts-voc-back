package com.ts_voc_back.user.login.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ts_voc_back.Security.dto.ValidationParam;
import com.ts_voc_back.common.model.ComResult;
import com.ts_voc_back.user.login.mapper.LoginMapper;
import com.ts_voc_back.user.login.model.param.PSelectUserInfo;
import com.ts_voc_back.user.login.model.param.PUpdateUser;
import com.ts_voc_back.user.login.model.param.PUpdateUserPwd;
import com.ts_voc_back.user.login.model.result.RSelectUserInfo;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {

	@Autowired
	final LoginMapper loginMapper = null;
	@Autowired
	final BCryptPasswordEncoder bCryptPasswordEncoder = null;

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
				result.setFail("세션이 만료 되었습니다.2");
				return result;
			} else {
				String loginId = authentication.getName();

				PSelectUserInfo pSelectUserInfo = new PSelectUserInfo();
				pSelectUserInfo.setLoginId(loginId);
				RSelectUserInfo _userInfo = loginMapper.selectUserInfo(pSelectUserInfo);

				Map<String, String> userInfo = new HashMap<>();
				userInfo.put("userSeq", _userInfo.getUserSeq());
				userInfo.put("allowYn", _userInfo.getAllowYn());
				userInfo.put("role", _userInfo.getRole());

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

	/**
	 * 로그인 정보 조회
	 * @return
	 */
	public RSelectUserInfo getLoginInfo() {
		RSelectUserInfo _userInfo = new RSelectUserInfo();
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String loginId = authentication.getName();

			PSelectUserInfo pSelectUserInfo = new PSelectUserInfo();
			pSelectUserInfo.setLoginId(loginId);
			_userInfo = loginMapper.selectUserInfo(pSelectUserInfo);

			return _userInfo;
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return _userInfo;
	}

	/**
	 * 사용자 정보 수정
	 * @param param
	 * @return
	 */
	public ComResult<Integer> updateUser(PUpdateUser param) {
		ComResult<Integer> result = new ComResult<>();
		RSelectUserInfo _userInfo = getLoginInfo();
		try {
			ValidationParam validationParam = new ValidationParam();
			// 공통 체크 (특수 문자 및 sql 인젝션 체크)
			// 특수문자
			if(
					validationParam.checkSqlExp(param.getUserName()) ||
					validationParam.checkSqlExp(param.getPwd()) ||
					validationParam.checkSqlExp(param.getEmail())
			) {
				result.setFail("해당 특수문자는 입력이 불가능합니다.\\n( ex : &, <, >, `, \", \\', ;, /, (, ), * )");
				return result;
			}

			// sql 인젝션
			if(
					validationParam.checkSqlInjection(param.getUserName()) ||
					validationParam.checkSqlInjection(param.getPwd()) ||
					validationParam.checkSqlInjection(param.getEmail())
			) {
				result.setFail("예약어는 입력이 불가능합니다.");
				return result;
			}
			// email 체크
			if(!validationParam.checkEmail(param.getEmail())) {
				result.setFail("Email 주소를 확인해 주세요.");
				return result;
			}

			if(param.getPwd() != null && !param.getPwd().equals("")) {
				// pw 체크
				if(param.getPwd().trim().length() < 8) {
					result.setFail("비밀번호는 8글자 이상 입력해주세요.");
					return result;
				}

				PUpdateUserPwd pUpdateUserPwd = new PUpdateUserPwd();
				pUpdateUserPwd.setUserSeq(_userInfo.getUserSeq());
				pUpdateUserPwd.setPwd(bCryptPasswordEncoder.encode(param.getPwd()));
				loginMapper.updateUserPwd(pUpdateUserPwd);
			}

			param.setUserSeq(_userInfo.getUserSeq());
			loginMapper.updateUser(param);

			result.setSuccess(0);
		} catch(Exception ex) {
			result.setError(ex);
		}

		return result;
	}
}
