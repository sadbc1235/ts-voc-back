package com.ts_voc_back.user.login.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ts_voc_back.user.login.mapper.LoginMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {

	@Autowired
	final LoginMapper loginMapper = null;
}
