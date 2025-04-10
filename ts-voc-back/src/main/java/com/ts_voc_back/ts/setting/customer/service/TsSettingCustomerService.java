package com.ts_voc_back.ts.setting.customer.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ts_voc_back.common.model.ComResult;
import com.ts_voc_back.ts.setting.customer.mapper.TsSettingCustomerMapper;
import com.ts_voc_back.ts.setting.customer.model.param.*;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TsSettingCustomerService {

	@Autowired
	final TsSettingCustomerMapper tsSettingCustomerMapper = null;

	/**
	 * 회사 추가
	 * @param param
	 * @return
	 */
	public ComResult<Integer> insertCompList(PInsertCompList param) {
		ComResult<Integer> result = new ComResult<>(param);

		try {
			tsSettingCustomerMapper.insertCompList(param);
			result.setSuccess(0);
		} catch(Exception ex) {
			result.setError(ex);
		}

		return result;
	}

	/**
	 * 회사 수정
	 * @param param
	 * @return
	 */
	public ComResult<Integer> updateComp(PUpdateComp param) {
		ComResult<Integer> result = new ComResult<>(param);

		try {
			for(Map<String, String> compInfo : param.getUpdateCompList()) {
				param.setCompName(compInfo.get("compName"));
				param.setCompSeq(compInfo.get("compSeq"));
				tsSettingCustomerMapper.updateComp(param);
			}
			result.setSuccess(0);
		} catch(Exception ex) {
			result.setError(ex);
		}

		return result;
	}

	/**
	 * 회사 삭제
	 * @param param
	 * @return
	 */
	public ComResult<Integer> deleteComp(PDeleteComp param) {
		ComResult<Integer> result = new ComResult<>(param);

		try {
			tsSettingCustomerMapper.deleteComp(param);
			result.setSuccess(0);
		} catch(Exception ex) {
			result.setError(ex);
		}

		return result;
	}

	/**
	 * 모듈 추가
	 * @param param
	 * @return
	 */
	public ComResult<Integer> insertModuleList(PInsertModuleList param) {
		ComResult<Integer> result = new ComResult<>(param);

		try {
			tsSettingCustomerMapper.insertModuleList(param);
			result.setSuccess(0);
		} catch(Exception ex) {
			result.setError(ex);
		}

		return result;
	}

	/**
	 * 모듈 수정
	 * @param param
	 * @return
	 */
	public ComResult<Integer> updateModule(PUpdateModule param) {
		ComResult<Integer> result = new ComResult<>(param);

		try {
			for(Map<String, String> compInfo : param.getUpdateModuleList()) {
				param.setModuleName(compInfo.get("moduleName"));
				param.setModuleSeq(compInfo.get("moduleSeq"));
				tsSettingCustomerMapper.updateModule(param);
			}
			result.setSuccess(0);
		} catch(Exception ex) {
			result.setError(ex);
		}

		return result;
	}

	/**
	 * 모듈 삭제
	 * @param param
	 * @return
	 */
	public ComResult<Integer> deleteModule(PDeleteModule param) {
		ComResult<Integer> result = new ComResult<>(param);

		try {
			tsSettingCustomerMapper.deleteModule(param);
			result.setSuccess(0);
		} catch(Exception ex) {
			result.setError(ex);
		}

		return result;
	}
}
