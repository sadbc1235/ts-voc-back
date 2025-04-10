package com.ts_voc_back.ts.setting.emp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ts_voc_back.common.model.ComResult;
import com.ts_voc_back.ts.setting.emp.mapper.TsSettingEmpMapper;
import com.ts_voc_back.ts.setting.emp.model.param.PInsertTsUser;
import com.ts_voc_back.ts.setting.emp.model.result.RSelectTsUserList;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TsSettingEmpService {

	@Autowired
	final TsSettingEmpMapper tsSettingEmpMapper = null;
	@Autowired
	final BCryptPasswordEncoder bCryptPasswordEncoder = null;

	/**
	 * TS 사용자 저장
	 * @param param
	 * @return
	 */
	public ComResult<Integer> insertTsUser(PInsertTsUser param) {
		ComResult<Integer> result = new ComResult<>();
		try {
			param.setPwd(bCryptPasswordEncoder.encode("12345678"));
			tsSettingEmpMapper.insertTsUser(param);
			result.setSuccess(0);
		} catch(Exception ex) {
			result.setError(ex);
		}

		return result;
	}

	/**
	 * TS 사용자 목록 조회
	 * @return
	 */
	public ComResult<RSelectTsUserList> selectTsUserList() {
		ComResult<RSelectTsUserList> result = new ComResult<>();
		try {
			result.setSuccess(tsSettingEmpMapper.selectTsUserList());
		} catch(Exception ex) {
			result.setError(ex);
		}

		return result;
	}
}
