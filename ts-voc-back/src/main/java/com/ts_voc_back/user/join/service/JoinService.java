package com.ts_voc_back.user.join.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ts_voc_back.Security.dto.ValidationParam;
import com.ts_voc_back.common.model.ComResult;
import com.ts_voc_back.user.join.mapper.JoinMapper;
import com.ts_voc_back.user.join.model.param.PInsertUser;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JoinService {

	@Autowired
	final JoinMapper joinMapper = null;
	@Autowired
	final BCryptPasswordEncoder bCryptPasswordEncoder = null;

	/**
	 * 사용자 정보 생성
	 * @param pInsertUser
	 * @return
	 */
	public ComResult<Integer> insertUser(PInsertUser pInsertUser) {
		ComResult<Integer> result = new ComResult<Integer>(pInsertUser);

		try {
			ValidationParam validationParam = new ValidationParam();
			// 공통 체크 (특수 문자 및 sql 인젝션 체크)
			// 특수문자
			if(
					validationParam.checkSqlExp(pInsertUser.getCompSeq()) ||
					validationParam.checkSqlExp(pInsertUser.getLoginId()) ||
					validationParam.checkSqlExp(pInsertUser.getUserName()) ||
					validationParam.checkSqlExp(pInsertUser.getPwd()) ||
					validationParam.checkSqlExp(pInsertUser.getEmail())
			) {
				result.setFail("해당 특수문자는 입력이 불가능합니다.\\n( ex : &, <, >, `, \", \\', ;, /, (, ), * )");
				return result;
			}

			// sql 인젝션
			if(
					validationParam.checkSqlInjection(pInsertUser.getCompSeq()) ||
					validationParam.checkSqlInjection(pInsertUser.getLoginId()) ||
					validationParam.checkSqlInjection(pInsertUser.getUserName()) ||
					validationParam.checkSqlInjection(pInsertUser.getPwd()) ||
					validationParam.checkSqlInjection(pInsertUser.getEmail())
			) {
				result.setFail("예약어는 입력이 불가능합니다.");
				return result;
			}

			// 회사 체크
			if(pInsertUser.getCompSeq().trim().equals("")) {
				result.setFail("회사를 선택해주세요.");
				return result;
			}
			// id 체크
			if(pInsertUser.getLoginId().trim().length() < 4 || pInsertUser.getLoginId().trim().length() > 12) {
				result.setFail("아이디는 4글자 이상, 12글자 이하로 입력해주세요.");
				return result;
			}
			if(!validationParam.checkKor(pInsertUser.getLoginId())) {
				result.setFail("아이디는 영어, 숫자로만 입력해주세요.");
				return result;
			}
			// pw 체크
			if(pInsertUser.getPwd().trim().length() < 8) {
				result.setFail("비밀번호는 8글자 이상 입력해주세요.");
				return result;
			}
			// email 체크
			if(!validationParam.checkEmail(pInsertUser.getEmail())) {
				result.setFail("Email 주소를 확인해 주세요.");
				return result;
			}
			// role 체크
			if(pInsertUser.getRole() != null) {
				result.setFail("어디서 장난질이고...");
				return result;
			}

			// DB에 동일한 loginId 가 있는지 확인
			String existLoginId = joinMapper.selectExistUser(pInsertUser);
			if(existLoginId.equals("Y")) {
				result.setFail("이미 가입되어 있는 아이디 입니다.");
				return result;
			}

			pInsertUser.setPwd(bCryptPasswordEncoder.encode(pInsertUser.getPwd()));
			pInsertUser.setRole("ROLE_USER");

			result.setSuccess(joinMapper.insertUser(pInsertUser));
			result.setResultMessage("회원가입이 완료되었습니다.\n관리자 승인 후 이용 가능하니 해당 담당자에게 연락 부탁드립니다.");
		} catch(Exception ex) {
			result.setError(ex);
		}

		return result;
	}

	public ComResult<Integer> selectExistUser(PInsertUser pInsertUser) {
		ComResult<Integer> result = new ComResult<Integer>(pInsertUser);

		try {
			ValidationParam validationParam = new ValidationParam();
			// 공통 체크 (특수 문자 및 sql 인젝션 체크)
			// 특수문자
			if(
					validationParam.checkSqlExp(pInsertUser.getLoginId())
			) {
				result.setFail("해당 특수문자는 입력이 불가능합니다.\\n( ex : &, <, >, `, \", ', ;, /, (, ), * )");
				return result;
			}

			// sql 인젝션
			if(
					validationParam.checkSqlInjection(pInsertUser.getLoginId())
			) {
				result.setFail("예약어는 입력이 불가능합니다.");
				return result;
			}

			// id 체크
			if(pInsertUser.getLoginId().trim().length() < 4 || pInsertUser.getLoginId().trim().length() > 12) {
				result.setFail("아이디는 4글자 이상, 12글자 이하로 입력해주세요.");
				return result;
			}
			if(!validationParam.checkKor(pInsertUser.getLoginId())) {
				result.setFail("아이디는 영어, 숫자로만 입력해주세요.");
				return result;
			}

			// DB에 동일한 loginId 가 있는지 확인
			String existLoginId = joinMapper.selectExistUser(pInsertUser);
			if(existLoginId.equals("Y")) {
				result.setFail("이미 가입되어 있는 아이디 입니다.");
				return result;
			}

			result.setSuccess(0);
		} catch(Exception ex) {
			result.setError(ex);
		}

		return result;
	}
}
