package com.ts_voc_back.Post.report.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ts_voc_back.Post.report.model.param.PSelectPostList;
import com.ts_voc_back.Post.report.model.result.RSelectPostList;
import com.ts_voc_back.Post.report.service.PostReportService;
import com.ts_voc_back.common.model.ComResult;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PostReportController {
	@Autowired
	final PostReportService postReportService= null;

	/**
	 * 게시물 목록 조회
	 * @param param
	 * @return
	 */
	@PostMapping("/api/post/report/selectPostList")
    @ResponseBody
    public ComResult<RSelectPostList> selectPostList(@RequestBody PSelectPostList param) {
        return postReportService.selectPostList(param);
    }
}
