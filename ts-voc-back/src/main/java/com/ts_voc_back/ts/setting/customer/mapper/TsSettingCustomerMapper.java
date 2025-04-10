package com.ts_voc_back.ts.setting.customer.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.ts_voc_back.ts.setting.customer.model.param.*;

@Mapper
public interface TsSettingCustomerMapper {
	/**
	 *  회사 추가
	 * @param param
	 * @return
	 * @throws Exception
	 */
	int insertCompList(PInsertCompList param) throws Exception;

	/**
	 * 회사 수정
	 * @param param
	 * @return
	 * @throws Exception
	 */
	int updateComp(PUpdateComp param) throws Exception;

	/**
	 * 회사 삭제
	 * @param param
	 * @return
	 * @throws Exception
	 */
	int deleteComp(PDeleteComp param) throws Exception;

	/**
	 *  모듈 추가
	 * @param param
	 * @return
	 * @throws Exception
	 */
	int insertModuleList(PInsertModuleList param) throws Exception;

	/**
	 * 모듈 수정
	 * @param param
	 * @return
	 * @throws Exception
	 */
	int updateModule(PUpdateModule param) throws Exception;

	/**
	 * 모듈 삭제
	 * @param param
	 * @return
	 * @throws Exception
	 */
	int deleteModule(PDeleteModule param) throws Exception;
}
