package com.ts_voc_back.Security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ts_voc_back.Security.dto.CustomUserDetails;
import com.ts_voc_back.user.mapper.UserMapper;
import com.ts_voc_back.user.model.param.PSelectUserInfo;
import com.ts_voc_back.user.model.result.RSelectUserInfo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SecurityService implements UserDetailsService {
	@Autowired
	final UserMapper userMapper = null;

	@Override
	public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
		try {
			PSelectUserInfo pSelectUserInfo = new PSelectUserInfo();
			pSelectUserInfo.setLoginId(loginId);
			RSelectUserInfo userInfo = userMapper.selectUserInfo(pSelectUserInfo);

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
