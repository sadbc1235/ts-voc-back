package com.ts_voc_back.user.logout.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ts_voc_back.common.model.ComResult;
import com.ts_voc_back.user.logout.mapper.LogoutMapper;
import com.ts_voc_back.user.logout.model.param.PDeleteUser;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogoutService {

	@Autowired
	final LogoutMapper logoutMapper = null;

	/**
	 * 사용자 삭제
	 * @param param
	 * @return
	 */
	public ComResult<Integer> deleteUser(PDeleteUser param) {
		ComResult<Integer> result = new ComResult<>();

		try {
			logoutMapper.deleteUser(param);
			result.setSuccess(0);
		} catch(Exception ex) {
			result.setError(ex);
		}

		return result;
	}
}
