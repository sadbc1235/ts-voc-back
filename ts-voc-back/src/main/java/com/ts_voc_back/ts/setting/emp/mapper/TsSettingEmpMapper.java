package com.ts_voc_back.ts.setting.emp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ts_voc_back.ts.setting.emp.model.param.PInsertTsUser;
import com.ts_voc_back.ts.setting.emp.model.result.RSelectTsUserList;

@Mapper
public interface TsSettingEmpMapper {

	/**
	 * TS 사용자 저장
	 * @param param
	 * @return
	 * @throws Exception
	 */
	int insertTsUser(PInsertTsUser param) throws Exception;

	/**
	 * TS 사용자 목록 조회
	 * @return
	 * @throws Exception
	 */
	List<RSelectTsUserList> selectTsUserList() throws Exception;
}
