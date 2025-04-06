package com.ts_voc_back.common.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ts_voc_back.Post.use.model.param.PInsertPost;
import com.ts_voc_back.common.model.param.PSelectModuleList;
import com.ts_voc_back.common.model.result.RSelectModuleList;

@Mapper
public interface CommonMapper {

	/**
	 * 모듈리스트 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	List<RSelectModuleList> selectModuleList(PSelectModuleList param) throws Exception;
}
