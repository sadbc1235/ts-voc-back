package com.ts_voc_back.common.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ts_voc_back.common.model.param.*;
import com.ts_voc_back.common.model.result.*;

@Mapper
public interface CommonMapper {

	/**
	 * 모듈리스트 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	List<RSelectModuleList> selectModuleList(PSelectModuleList param) throws Exception;

	/**
	 * 회사 리스트 조회
	 * @return
	 * @throws Exception
	 */
	List<RSelectCompList> selectCompList() throws Exception;
}
