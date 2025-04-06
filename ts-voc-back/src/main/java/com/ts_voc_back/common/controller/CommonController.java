package com.ts_voc_back.common.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ts_voc_back.common.model.ComResult;
import com.ts_voc_back.common.model.result.RSelectModuleList;
import com.ts_voc_back.common.service.CommonService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CommonController {
	@Autowired
	final CommonService commonService= null;

	@PostMapping("/api/common/selectModuleList")
    @ResponseBody
    public ComResult<RSelectModuleList> selectModuleList() {
        return commonService.selectModuleList();
    }
}
