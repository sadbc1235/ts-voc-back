package com.ts_voc_back.Post.use.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ts_voc_back.Post.use.model.param.*;
import com.ts_voc_back.Post.use.model.result.*;
import com.ts_voc_back.Post.use.service.PostUseService;
import com.ts_voc_back.common.model.ComResult;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PostUseController {
	@Autowired
	final PostUseService postUserService= null;

	/**
	 * 게시물 정보 저장
	 * @param param
	 * @return
	 */
	@PostMapping("/api/post/use/insertPost")
    @ResponseBody
    public ComResult<String> insertPost(@RequestBody PInsertPost param) {
        return postUserService.insertPost(param);
    }

	/**
	 * 게시물 수정
	 * @param param
	 * @return
	 */
	@PostMapping("/api/post/use/updatePost")
    @ResponseBody
    public ComResult<String> updatePost(@RequestBody PUpdatePost param) {
        return postUserService.updatePost(param);
    }

	/**
	 * 본문 이미지 저장
	 * @param fileList
	 * @return
	 */
	@PostMapping(value = "/api/post/use/uploadContentImg", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ComResult<Map<String, String>> uploadContentImg(
    		@RequestParam(value="contentImgList", required=false) List<MultipartFile> contentImgList
    		, @RequestParam("postSeq") String postSeq
    ) {
        return postUserService.uploadContentImg( contentImgList, postSeq );
    }

	/**
	 * 본문 업데이트
	 * @param param
	 * @return
	 */
	@PostMapping("/api/post/use/updatePostContent")
    @ResponseBody
    public ComResult<Integer> updatePostContent(@RequestBody PInsertPost param) {
        return postUserService.updatePostContent(param);
    }

	/**
	 * 첨부파일 저장
	 * @param contentImgList
	 * @param postSeq
	 * @return
	 */
	@PostMapping(value = "/api/post/use/uploadAttach", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ComResult<Boolean> uploadAttach(
    		@RequestParam(value="attachList", required=false) List<MultipartFile> attachList
    		, @RequestParam("postSeq") String postSeq
    		, @RequestParam(value="postAttachSeqs", required=false) String postAttachSeqs
    ) {
        return postUserService.uploadAttach( attachList, postSeq, postAttachSeqs );
    }

	/**
	 * 본문 이미지 조회
	 * @param filepath
	 * @param filename
	 * @return
	 */
	@GetMapping("/api/content/img/{postSeq}/{filepath}/{filename}")
	public ResponseEntity<byte[]> displayImg(
			@PathVariable("postSeq")String postSeq,
			@PathVariable("filepath")String filepath,
			@PathVariable("filename")String filename
	) {

		return postUserService.displayImg(postSeq, filepath, filename);
	}

	/**
	 * 첨부파일 다운로드
	 * @param postAttachSeq
	 * @return
	 */
	@GetMapping("/api/content/attach/{postAttachSeq}")
	public ResponseEntity<byte[]> downloadAttach(
			@PathVariable("postAttachSeq")String postAttachSeq
	) {

		return postUserService.downloadAttach(postAttachSeq);
	}

	/**
	 * 게시물 정보 조회
	 * @param param
	 * @return
	 */
	@PostMapping("/api/post/use/selectPostInfo")
    @ResponseBody
    public ComResult<RSelectPostInfo> selectPostInfo(@RequestBody PSelectPostInfo param) {
        return postUserService.selectPostInfo(param);
    }

	/**
	 * 게시물 삭제
	 * @param param
	 * @return
	 */
	@PostMapping("/api/post/use/updatePostDelYn")
    @ResponseBody
    public ComResult<Integer> updatePostDelYn(@RequestBody PUpdatePostDelYn param) {
        return postUserService.updatePostDelYn(param);
    }

	/**
	 * 게시물 회신 조회
	 * @param param
	 * @return
	 */
	@PostMapping("/api/post/use/selectPostComment")
    @ResponseBody
    public ComResult<RSelectPostComment> selectPostComment(@RequestBody PSelectPostComment param) {
        return postUserService.selectPostComment(param);
    }
}
