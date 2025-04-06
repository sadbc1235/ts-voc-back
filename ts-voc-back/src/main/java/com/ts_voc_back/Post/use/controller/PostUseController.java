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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ts_voc_back.Post.use.model.param.PInsertPost;
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
}
