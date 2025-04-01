package com.ts_voc_back.Security.dto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationParam {

	public boolean checkKor(String str) {
		String chkKorReg = "^[A-Za-z0-9][A-Za-z0-9]*$";
		Pattern chkKorPatten = Pattern.compile(chkKorReg);
		Matcher chkKorMatcher = chkKorPatten.matcher(str.trim());

		return chkKorMatcher.find();
	}

	public boolean checkSqlExp(String str) {
		String sqlExpReg = "[&<>`\"';/()*]";
		Pattern sqlExpPatten = Pattern.compile(sqlExpReg);
		Matcher sqlExpMatcher = sqlExpPatten.matcher(str.trim());

		return sqlExpMatcher.find();
	}

	public boolean checkSqlInjection(String str) {
		String sqlIjtReg = "(OR|SELECT|INSERT|DELETE|UPDATE|CREATE|DROP|EXEC|UNION|FETCH|DECLARE|TRUNCATE|or|select|insert|delete|update|create|drop|exec|union|fetch|declare|truncate)\s";
		Pattern sqlIjtPatten = Pattern.compile(sqlIjtReg);
		Matcher sqlIjtMatcher = sqlIjtPatten.matcher(str);

		return sqlIjtMatcher.find();
	}

	public boolean checkEmail(String str) {
		String reg = "\\w+@\\w+\\.\\w+(\\.\\w+)?";
		Pattern patten = Pattern.compile(reg);
		Matcher matcher = patten .matcher(str.trim());

		return matcher.find();
	}
}
