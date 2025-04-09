package com.ts_voc_back.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ts_voc_back.common.mapper.CommonMapper;
import com.ts_voc_back.common.model.ComResult;
import com.ts_voc_back.common.model.param.*;
import com.ts_voc_back.common.model.result.*;
import com.ts_voc_back.user.login.model.result.RSelectUserInfo;
import com.ts_voc_back.user.login.service.LoginService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommonService {

	@Autowired
	final CommonMapper commonMapper = null;
	@Autowired
	final LoginService loginService = null;

	/**
	 * 모듈 리스트 조회
	 * @param param
	 * @return
	 */
	public ComResult<RSelectModuleList> selectModuleList() {
		PSelectModuleList param = new PSelectModuleList();
		ComResult<RSelectModuleList> result = new ComResult<RSelectModuleList>(param);
		RSelectUserInfo _userInfo = loginService.getLoginInfo();

		try {
			param.setCompSeq(_userInfo.getCompSeq());
			result.setSuccess(commonMapper.selectModuleList(param));
		} catch(Exception ex) {
			result.setError(ex);
		}

		return result;
	}

	/**
	 * 회사 리스트 조회
	 * @return
	 */
	public ComResult<RSelectCompList> selectCompList() {
		ComResult<RSelectCompList> result = new ComResult<>();

		try {
			result.setSuccess(commonMapper.selectCompList());
		} catch(Exception ex) {
			result.setError(ex);
		}

		return result;
	}
}
