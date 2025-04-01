package com.ts_voc_back.Security.dto;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ts_voc_back.user.login.model.result.RSelectUserInfo;

public class CustomUserDetails implements UserDetails {

	private RSelectUserInfo userInfo;

	public CustomUserDetails(RSelectUserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collection = new ArrayList<>();
		collection.add(new GrantedAuthority() {
			@Override
			public String getAuthority() {
				return userInfo.getRole();
			}
		});

		return collection;
	}

	@Override
	public String getPassword() {
		return userInfo.getPwd();
	}

	@Override
	public String getUsername() {
		return userInfo.getLoginId();
	}

}
