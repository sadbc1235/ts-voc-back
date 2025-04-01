package com.ts_voc_back.Security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ts_voc_back.Security.dto.CustomUserDetails;
import com.ts_voc_back.Security.dto.ValidationParam;
import com.ts_voc_back.user.login.mapper.LoginMapper;
import com.ts_voc_back.user.login.model.param.PSelectUserInfo;
import com.ts_voc_back.user.login.model.result.RSelectUserInfo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SecurityService implements UserDetailsService {
	@Autowired
	final LoginMapper loginMapper = null;

	@Override
	public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
		try {
			ValidationParam validationParam = new ValidationParam();
			if(validationParam.checkSqlExp(loginId)) {
				throw new UsernameNotFoundException("해당 특수문자는 입력이 불가능합니다.\\n( ex : &, <, >, `, \", \\', ;, /, (, ), * )");
			}
			if(validationParam.checkSqlInjection(loginId)) {
				throw new UsernameNotFoundException("예약어는 입력이 불가능합니다.");
			}
			if(loginId.length() < 4 || loginId.length() > 12) {
				throw new UsernameNotFoundException("아이디는 4글자 이상, 12글자 이하로 입력해주세요.");
			}
			if(!validationParam.checkKor(loginId)) {
				throw new UsernameNotFoundException("아이디는 영어, 숫자로만 입력해주세요.");
			}
			PSelectUserInfo pSelectUserInfo = new PSelectUserInfo();
			pSelectUserInfo.setLoginId(loginId);
			RSelectUserInfo userInfo = loginMapper.selectUserInfo(pSelectUserInfo);

			if(userInfo == null) {
				throw new UsernameNotFoundException("User not found with loginId: " + loginId);
			} else if(userInfo != null) {
				return new CustomUserDetails(userInfo);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}


}
