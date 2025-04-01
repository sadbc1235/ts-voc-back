package com.ts_voc_back.user.login.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.ts_voc_back.user.login.model.param.PSelectUserInfo;
import com.ts_voc_back.user.login.model.result.RSelectUserInfo;

@Mapper
public interface LoginMapper {

	/**
	 * 사용자 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	RSelectUserInfo selectUserInfo(PSelectUserInfo param) throws Exception;

}
