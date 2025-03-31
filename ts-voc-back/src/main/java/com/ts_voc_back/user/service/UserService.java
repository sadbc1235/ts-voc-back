package com.ts_voc_back.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ts_voc_back.common.model.ComResult;
import com.ts_voc_back.user.mapper.UserMapper;
import com.ts_voc_back.user.model.param.*;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	@Autowired
	final UserMapper userMapper = null;
	@Autowired
	final BCryptPasswordEncoder bCryptPasswordEncoder = null;

	/**
	 * 사용자 정보 생성
	 * @param pInsertUser
	 * @return
	 */
	public ComResult<Integer> insertUser(PInsertUser pInsertUser) {
		ComResult<Integer> result = new ComResult<Integer>(pInsertUser);

		try {

			// DB에 동일한 loginId 가 있는지 확인
			String existLoginId = userMapper.selectExistUser(pInsertUser);
			if(existLoginId.equals("Y")) {
				result.setFail("이미 가입되어 있는 아이디 입니다.");
				return result;
			}

			pInsertUser.setPwd(bCryptPasswordEncoder.encode(pInsertUser.getPwd()));
			pInsertUser.setRole("ROLE_USER");

			result.setSuccess(userMapper.insertUser(pInsertUser));
		} catch(Exception ex) {
			result.setError(ex);
		}

		return result;
	}
}
