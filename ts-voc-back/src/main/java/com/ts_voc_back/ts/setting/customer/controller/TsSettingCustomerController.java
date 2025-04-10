package com.ts_voc_back.ts.setting.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ts_voc_back.common.model.ComResult;
import com.ts_voc_back.ts.setting.customer.model.param.*;
import com.ts_voc_back.ts.setting.customer.service.TsSettingCustomerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TsSettingCustomerController {
	@Autowired
	final TsSettingCustomerService tsSettingCustomerService= null;

	/**
	 * 회사 추가
	 * @param param
	 * @return
	 */
	@PostMapping("/api/ts/setting/customer/insertCompList")
    @ResponseBody
    public ComResult<Integer> insertCompList(@RequestBody PInsertCompList param) {
        return tsSettingCustomerService.insertCompList(param);
    }

	/**
	 * 회사 수정
	 * @param param
	 * @return
	 */
	@PostMapping("/api/ts/setting/customer/updateCompList")
    @ResponseBody
    public ComResult<Integer> updateComp(@RequestBody PUpdateComp param) {
        return tsSettingCustomerService.updateComp(param);
    }

	/**
	 * 회사 삭제
	 * @param param
	 * @return
	 */
	@PostMapping("/api/ts/setting/customer/deleteComp")
    @ResponseBody
    public ComResult<Integer> deleteComp(@RequestBody PDeleteComp param) {
        return tsSettingCustomerService.deleteComp(param);
    }

	/**
	 * 모듈 추가
	 * @param param
	 * @return
	 */
	@PostMapping("/api/ts/setting/customer/insertModuleList")
    @ResponseBody
    public ComResult<Integer> insertModuleList(@RequestBody PInsertModuleList param) {
        return tsSettingCustomerService.insertModuleList(param);
    }

	/**
	 * 모듈 수정
	 * @param param
	 * @return
	 */
	@PostMapping("/api/ts/setting/customer/updateModuleList")
    @ResponseBody
    public ComResult<Integer> updateModule(@RequestBody PUpdateModule param) {
        return tsSettingCustomerService.updateModule(param);
    }

	/**
	 * 모듈 삭제
	 * @param param
	 * @return
	 */
	@PostMapping("/api/ts/setting/customer/deleteModule")
    @ResponseBody
    public ComResult<Integer> deleteModule(@RequestBody PDeleteModule param) {
        return tsSettingCustomerService.deleteModule(param);
    }
}
