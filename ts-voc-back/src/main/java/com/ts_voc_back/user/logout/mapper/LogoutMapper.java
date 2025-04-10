package com.ts_voc_back.user.logout.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.ts_voc_back.user.logout.model.param.PDeleteUser;

@Mapper
public interface LogoutMapper {

	/**
	 * 사용자 삭제
	 * @param param
	 * @return
	 * @throws Exception
	 */
	int deleteUser(PDeleteUser param) throws Exception;
}
