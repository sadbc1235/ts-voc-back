package com.ts_voc_back.user.login.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.ts_voc_back.user.login.model.param.*;
import com.ts_voc_back.user.login.model.result.*;

@Mapper
public interface LoginMapper {

	/**
	 * 사용자 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	RSelectUserInfo selectUserInfo(PSelectUserInfo param) throws Exception;

	/**
	 * 사용자 정보 수정
	 * @param param
	 * @return
	 * @throws Exception
	 */
	int updateUser(PUpdateUser param) throws Exception;

	/**
	 * 사용자 비밀번호 수정
	 * @param param
	 * @return
	 * @throws Exception
	 */
	int updateUserPwd(PUpdateUserPwd param) throws Exception;

}
