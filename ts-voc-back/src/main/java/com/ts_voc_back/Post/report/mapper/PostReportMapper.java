package com.ts_voc_back.Post.report.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ts_voc_back.Post.report.model.param.*;
import com.ts_voc_back.Post.report.model.result.*;

@Mapper
public interface PostReportMapper {

	/**
	 * 게시물 목록 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	List<RSelectPostList> selectPostList(PSelectPostList param) throws Exception;
}
