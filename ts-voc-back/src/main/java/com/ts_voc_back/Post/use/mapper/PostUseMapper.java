package com.ts_voc_back.Post.use.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ts_voc_back.Post.use.model.param.*;
import com.ts_voc_back.Post.use.model.result.*;

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

	/**
	 * 첨부파일 정보 저장
	 * @param param
	 * @return
	 * @throws Exception
	 */
	int insertPostAttach(PInsertPostAttach param) throws Exception;

	/**
	 * 첨부파일 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	RSelectAttachInfo selectAttachInfo(PSelectAttachInfo param) throws Exception;

	/**
	 * 게시물 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	RSelectPostInfo selectPostInfo(PSelectPostInfo param) throws Exception;

	/**
	 * 게시물 첨부파일 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	List<RSelectPostAttachList> selectPostAttachList(PSelectPostInfo param) throws Exception;
}
