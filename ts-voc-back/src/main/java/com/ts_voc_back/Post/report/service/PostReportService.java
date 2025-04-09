package com.ts_voc_back.Post.report.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ts_voc_back.Post.report.mapper.PostReportMapper;
import com.ts_voc_back.Post.report.model.param.PSelectPostList;
import com.ts_voc_back.Post.report.model.result.RSelectPostList;
import com.ts_voc_back.common.model.ComResult;
import com.ts_voc_back.user.login.model.result.RSelectUserInfo;
import com.ts_voc_back.user.login.service.LoginService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostReportService {

	@Autowired
	final PostReportMapper postReportMapper = null;
	@Autowired
	final LoginService loginService = null;

	/**
	 * 게시물 목록 조회
	 * @param param
	 * @return
	 */
	public ComResult<RSelectPostList> selectPostList(PSelectPostList param) {
		ComResult<RSelectPostList> result = new ComResult<>();
		RSelectUserInfo _userInfo = loginService.getLoginInfo();

		try {
			param.setCompSeq(_userInfo.getCompSeq());
			if(param.getType() != null && !param.getType().equals("")) {
				param.setUserSeq(_userInfo.getUserSeq());
			}

			result.setSuccess(postReportMapper.selectPostList(param));
		} catch(Exception ex) {

		}

		return result;
	}
}
