package com.ts_voc_back.Post.use.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ts_voc_back.Post.use.service.PostUseService;
import com.ts_voc_back.common.model.ComResult;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PostUseController {
	@Autowired
	final PostUseService postUserService= null;


	@PostMapping(value = "/api/uploadContentImg", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ComResult<Map<String, Object>> uploadContentImg(@RequestParam("fileList") List<MultipartFile> fileList) {
        return postUserService.uploadContentImg( fileList );
    }

	@GetMapping("/api/content/img/{filepath}/{filename}")
	public ResponseEntity<byte[]> displayImg(
			@PathVariable("filepath")String filepath,
			@PathVariable("filename")String filename
	) {

		return postUserService.displayImg(filepath, filename);
	}
}
