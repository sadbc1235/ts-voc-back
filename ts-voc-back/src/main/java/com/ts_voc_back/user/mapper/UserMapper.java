package com.ts_voc_back.user.mapper;


import org.apache.ibatis.annotations.Mapper;
import com.ts_voc_back.user.model.param.*;
import com.ts_voc_back.user.model.result.*;

@Mapper
public interface UserMapper {

	/**
	 * 사용자 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	RSelectUserInfo selectUserInfo(PSelectUserInfo param) throws Exception;

	/**
	 * 로그인 아이디 중복 체크
	 * @param pInsertUser
	 * @return
	 * @throws Exception
	 */
	String selectExistUser(PInsertUser param) throws Exception;

	/**
	 * 사용자정보 생성
	 * @param pInsertUser
	 * @return
	 * @throws Exception
	 */
	int insertUser(PInsertUser param) throws Exception;
}
