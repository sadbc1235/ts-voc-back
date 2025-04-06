package com.ts_voc_back.Post.use.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.ts_voc_back.Post.use.model.param.PInsertPost;

@Mapper
public interface PostUseMapper {

	/**
	 * 게시물 정보 저장
	 * @param param
	 * @return
	 * @throws Exception
	 */
	int insertPost(PInsertPost param) throws Exception;

	/**
	 * 본문 업데이트
	 * @param param
	 * @return
	 * @throws Exception
	 */
	int updatePostContent(PInsertPost param) throws Exception;
}
