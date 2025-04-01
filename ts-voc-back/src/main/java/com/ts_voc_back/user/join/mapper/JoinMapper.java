package com.ts_voc_back.user.join.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.ts_voc_back.user.join.model.param.PInsertUser;

@Mapper
public interface JoinMapper {

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
