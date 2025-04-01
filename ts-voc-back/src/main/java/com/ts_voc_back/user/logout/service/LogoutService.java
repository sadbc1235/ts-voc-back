package com.ts_voc_back.user.logout.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ts_voc_back.common.model.ComResult;
import com.ts_voc_back.user.logout.mapper.LogoutMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogoutService {

	@Autowired
	final LogoutMapper logoutMapper = null;
}
