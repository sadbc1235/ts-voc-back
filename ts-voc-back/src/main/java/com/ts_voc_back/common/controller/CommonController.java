package com.ts_voc_back.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ts_voc_back.common.model.ComResult;
import com.ts_voc_back.common.model.param.PSelectModuleList;
import com.ts_voc_back.common.model.result.*;
import com.ts_voc_back.common.service.CommonService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CommonController {
	@Autowired
	final CommonService commonService= null;

	/**
	 * 모듈 리스트 조회
	 * @return
	 */
	@PostMapping("/api/common/selectModuleList")
    @ResponseBody
    public ComResult<RSelectModuleList> selectModuleList(@RequestBody PSelectModuleList param) {
        return commonService.selectModuleList(param);
    }

	/**
	 * 회사 리스트 조회
	 * @return
	 */
	@PostMapping("/api/common/selectCompList")
    @ResponseBody
    public ComResult<RSelectCompList> selectCompList() {
        return commonService.selectCompList();
    }
}
