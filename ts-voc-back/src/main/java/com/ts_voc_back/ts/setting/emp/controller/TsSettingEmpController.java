package com.ts_voc_back.ts.setting.emp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ts_voc_back.common.model.ComResult;
import com.ts_voc_back.ts.setting.emp.model.param.PInsertTsUser;
import com.ts_voc_back.ts.setting.emp.model.result.RSelectTsUserList;
import com.ts_voc_back.ts.setting.emp.service.TsSettingEmpService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TsSettingEmpController {
	@Autowired
	final TsSettingEmpService tsSettingEmpService= null;

	/**
	 * TS 사용자 저장
	 * @param param
	 * @return
	 */
	@PostMapping("/api/ts/setting/emp/insertTsUser")
    @ResponseBody
    public ComResult<Integer> insertTsUser(@RequestBody PInsertTsUser param) {
        return tsSettingEmpService.insertTsUser(param);
    }

	/**
	 * TS 사용자 목록 조회
	 * @return
	 */
	@PostMapping("/api/ts/setting/emp/selectTsUserList")
    @ResponseBody
    public ComResult<RSelectTsUserList> selectTsUserList() {
        return tsSettingEmpService.selectTsUserList();
    }
}
