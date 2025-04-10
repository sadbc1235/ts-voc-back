package com.ts_voc_back.user.logout.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ts_voc_back.common.model.ComResult;
import com.ts_voc_back.user.logout.model.param.PDeleteUser;
import com.ts_voc_back.user.logout.service.LogoutService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LogoutController {
	@Autowired
	final LogoutService logoutService = null;

	/**
	 * 사용자 삭제
	 * @param req
	 * @param res
	 * @param param
	 * @return
	 */
	@PostMapping("/api/user/deleteUser")
	@ResponseBody
    public ComResult<Integer> deleteUser(
			HttpServletRequest req
			, HttpServletResponse res
			, @RequestBody PDeleteUser param
	) {
        return logoutService.deleteUser(param);
    }
}
