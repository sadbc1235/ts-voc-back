package com.ts_voc_back.Post.use.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.ts_voc_back.Post.use.mapper.PostUseMapper;
import com.ts_voc_back.Post.use.model.param.*;
import com.ts_voc_back.Post.use.model.result.*;
import com.ts_voc_back.common.model.ComResult;
import com.ts_voc_back.user.login.model.result.RSelectUserInfo;
import com.ts_voc_back.user.login.service.LoginService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostUseService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	final PostUseMapper postUseMapper = null;
	@Autowired
	final LoginService loginService = null;

//	private String devDefaultPath = "C:\\Users\\eun_su_kim\\Documents\\web\\ts_voc_folder";
	private String devDefaultPath = "C:\\Users\\eun-su-kim\\Documents\\web\\ts_voc_folder";
	private String defaultPath = "";

    PostUseService(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

	/**
	 * 본문 이미지 조회
	 * @param filepath
	 * @param filename
	 * @return
	 */
	public ResponseEntity<byte[]> displayImg(String postSeq, String filepath, String filename) {

		//파일이 저장된 경로
		String savename = devDefaultPath+"\\content_img\\"+postSeq+"\\"+filepath+"\\"+filename;
		File file = new File(savename);

		//저장된 이미지파일의 이진데이터 형식을 구함
		byte[] result=null;//1. data
		ResponseEntity<byte[]> entity=null;

		try {
	    	result = FileCopyUtils.copyToByteArray(file);

			//2. header
			HttpHeaders header = new HttpHeaders();
			header.add("Content-type",Files.probeContentType(file.toPath())); //파일의 컨텐츠타입을 직접 구해서 header에 저장

			//3. 응답본문
			entity = new ResponseEntity<>(result,header,HttpStatus.OK);//데이터, 헤더, 상태값
		} catch (IOException e) {
			e.printStackTrace();
		}

		return entity;
	}

	/**
	 * 첨부파일 다운로드
	 * @param postAttachSeq
	 * @return
	 */
	public ResponseEntity<byte[]> downloadAttach(String postAttachSeq) {
		//저장된 이미지파일의 이진데이터 형식을 구함
		byte[] result=null;//1. data
		ResponseEntity<byte[]> entity=null;

		try {

			// 첨부파일 정보 조회
			PSelectAttachInfo pSelectAttachInfo = new PSelectAttachInfo();
			pSelectAttachInfo.setPostAttachSeq(postAttachSeq);
			RSelectAttachInfo attachInfo = postUseMapper.selectAttachInfo(pSelectAttachInfo);

			//파일이 저장된 경로
			String savename = attachInfo.getSavePath();
			File file = new File(savename);

	    	result = FileCopyUtils.copyToByteArray(file);

			//2. header
			HttpHeaders header = new HttpHeaders();
			header.add("Content-type",Files.probeContentType(file.toPath())); //파일의 컨텐츠타입을 직접 구해서 header에 저장

			//3. 응답본문
			entity = new ResponseEntity<>(result,header,HttpStatus.OK);//데이터, 헤더, 상태값
		} catch (Exception e) {
			e.printStackTrace();
		}

		return entity;
	}

	/**
	 * 게시물 해더 정보 저장
	 * @param param
	 * @return
	 */
	public ComResult<String> insertPost(PInsertPost param) {
		ComResult<String> result = new ComResult<String>(param);
		RSelectUserInfo _userInfo = loginService.getLoginInfo();

		try {
			// [1] 게시물 정보 저장 (본문 빼고!)
			param.setCompSeq(_userInfo.getCompSeq());
			param.setUserSeq(_userInfo.getUserSeq());
			postUseMapper.insertPost(param);
			result.setSuccess(param.getPostSeq());
		} catch(Exception ex) {
			result.setError(ex);
		}
		return result;
	}

	/**
	 * 게시물 수정
	 * @param param
	 * @return
	 */
	public ComResult<String> updatePost(PUpdatePost param) {
		ComResult<String> result = new ComResult<String>(param);
		RSelectUserInfo _userInfo = loginService.getLoginInfo();

		try {
			PSelectPostInfo pSelectPostInfo = new PSelectPostInfo();
			pSelectPostInfo.setCompSeq(_userInfo.getCompSeq());
			pSelectPostInfo.setPostSeq(param.getPostSeq());
			RSelectPostInfo postInfo = postUseMapper.selectPostInfo(pSelectPostInfo);

			if(postInfo == null || postInfo.getUserSeq().equals("")) {
				result.setFail("수정할 게시물을 찾을수 없습니다.");
				return result;
			}
			if(!_userInfo.getUserSeq().equals(postInfo.getUserSeq())) {
				result.setFail("타인의 게시물은 수정할 수 없습니다.");
				return result;
			}
			// [1] 기존 본문 기록 테이블에 저장
			PInsertPostContentHistory pInsertPostContentHistory = new PInsertPostContentHistory();
			pInsertPostContentHistory.setPostSeq(param.getPostSeq());
			pInsertPostContentHistory.setUserSeq(_userInfo.getUserSeq());
			postUseMapper.insertPostContentHistory(pInsertPostContentHistory);
			// [2] 게시물 수정 (본문 빼고!)
			param.setUserSeq(_userInfo.getUserSeq());
			postUseMapper.updatePost(param);
			result.setSuccess(param.getPostSeq());
		} catch(Exception ex) {
			result.setError(ex);
		}
		return result;
	}

	/**
	 * 본문 이미지 저장
	 */
	public ComResult<Map<String, String>> uploadContentImg(List<MultipartFile> contentImgList, String postSeq) {
		ComResult<Map<String, String>> result = new ComResult<Map<String, String>>();
		Map<String, String> urlMap = new HashMap<>();
		try {

	        for(MultipartFile file : contentImgList) {
	        	// 저장할 폴더명을 uuid로 생성
	        	String uuid = UUID.randomUUID().toString();
	        	// 저장할 폴더 경로
				String savePath = devDefaultPath+"\\content_img\\"+postSeq;
				File uploadPath = new File(savePath);
				// 해당경로에 폴더가 없으면 생성
		        if(uploadPath.exists() == false) {
		            uploadPath.mkdir();
		        }
		        uploadPath = new File((savePath+"\\"+uuid));
		        if(uploadPath.exists() == false) {
		            uploadPath.mkdir();
		        }

	        	// 저장을 위해 File 타입의 이미지 정보를 담고있는 변수 선언
                File saveFile = new File(uploadPath, file.getOriginalFilename());
                // 저장
                file.transferTo(saveFile);

                String imgUuid = file.getOriginalFilename().split("\\.")[0];
                urlMap.put(imgUuid, "/api/content/img/"+postSeq+"/"+uuid+"/"+file.getOriginalFilename());
	        }
	        result.setSuccess(urlMap);

		} catch(Exception ex) {
			result.setError(ex);
		}

		return result;
	}

	/**
	 * 본문 업데이트
	 * @param param
	 * @return
	 */
	public ComResult<Integer> updatePostContent(PInsertPost param) {
		ComResult<Integer> result = new ComResult<Integer>(param);

		try {
			result.setSuccess(postUseMapper.updatePostContent(param));
		} catch(Exception ex) {
			result.setError(ex);
		}
		return result;
	}

	/**
	 * 첨부파일 저장
	 * @param attachList
	 * @param postSeq
	 * @return
	 */
	public ComResult<Boolean> uploadAttach(List<MultipartFile> attachList, String postSeq, String postAttachSeqs) {
		ComResult<Boolean> result = new ComResult<Boolean>();
		RSelectUserInfo _userInfo = loginService.getLoginInfo();

		try {
			List<String> postAttachSeqList = new ArrayList<>();
	        for(MultipartFile file : attachList) {
	        	String uuid = UUID.randomUUID().toString();
	        	String ext = file.getOriginalFilename().split("\\.")[1];
	        	String displayName = file.getOriginalFilename();
	        	String realName = uuid+"."+ext;
	        	long fileSize = file.getSize();
	        	String type = file.getContentType().split("/")[0];

	        	// [1] 파일 저장
	        	// 저장할 폴더 경로
				String savePath = devDefaultPath+"\\content_attach\\"+postSeq;
				File uploadPath = new File(savePath);
				// 해당경로에 폴더가 없으면 생성
		        if(uploadPath.exists() == false) {
		            uploadPath.mkdir();
		        }
		        // 저장할 폴더명을 uuid로 생성
		        savePath +=  "\\"+uuid;
		        uploadPath = new File(savePath);
		        if(uploadPath.exists() == false) {
		            uploadPath.mkdir();
		        }

	        	// 저장을 위해 File 타입의 이미지 정보를 담고있는 변수 선언
                File saveFile = new File(uploadPath, realName);
                // 저장
                file.transferTo(saveFile);

                // [2] 파일 정보 저장
                PInsertPostAttach pInsertPostAttach = new PInsertPostAttach();
                pInsertPostAttach.setPostSeq(postSeq);
                pInsertPostAttach.setCompSeq(_userInfo.getCompSeq());
                pInsertPostAttach.setUserSeq(_userInfo.getUserSeq());
                pInsertPostAttach.setDisplayName(displayName);
                pInsertPostAttach.setRealName(realName);
                pInsertPostAttach.setExt(ext);
                pInsertPostAttach.setFileSize(fileSize);
                pInsertPostAttach.setSavePath(savePath);
                pInsertPostAttach.setType(type);
                postUseMapper.insertPostAttach(pInsertPostAttach);
                postAttachSeqList.add(pInsertPostAttach.getPostAttachSeq());
	        }

	        // 안쓰는 첨부파일 삭제
	        if(postAttachSeqs != null && !postAttachSeqs.equals("")) {
	        	String[] tempSeqList = postAttachSeqs.split(",");
	        	for(String postAttachSeq : tempSeqList) {
	        		postAttachSeqList.add(postAttachSeq);
	        	}
	        	PDeletePostAttach pDeletePostAttach = new PDeletePostAttach();
	        	pDeletePostAttach.setPostSeq(postSeq);
	        	pDeletePostAttach.setPostAttachSeqList(postAttachSeqList);
	        	postUseMapper.deletePostAttach(pDeletePostAttach);
	        }

	        result.setSuccess(true);

		} catch(Exception ex) {
			result.setError(ex);
		}

		return result;
	}

	/**
	 * 게시물 정보 조회
	 * @param param
	 * @return
	 */
	public ComResult<RSelectPostInfo> selectPostInfo(PSelectPostInfo param) {
		ComResult<RSelectPostInfo> result = new ComResult<>(param);
		RSelectPostInfo innerResult = new RSelectPostInfo();
		RSelectUserInfo _userInfo = loginService.getLoginInfo();
		try {
			param.setCompSeq(_userInfo.getCompSeq());
			innerResult = postUseMapper.selectPostInfo(param);
			if(innerResult.getUserSeq().equals(_userInfo.getUserSeq())) {
				innerResult.setIsMyPost(true);
			} else {
				innerResult.setIsMyPost(false);
			}
			innerResult.setAttachList(postUseMapper.selectPostAttachList(param));
			result.setSuccess(innerResult);
		} catch(Exception ex) {
			result.setError(ex);
		}

		return result;
	}

	/**
	 * 게시물 삭제
	 * @param param
	 * @return
	 */
	public ComResult<Integer> updatePostDelYn(PUpdatePostDelYn param) {
		ComResult<Integer> result = new ComResult<>(param);
		RSelectUserInfo _userInfo = loginService.getLoginInfo();

		try {
			PSelectPostInfo pSelectPostInfo = new PSelectPostInfo();
			pSelectPostInfo.setCompSeq(_userInfo.getCompSeq());
			pSelectPostInfo.setPostSeq(param.getPostSeq());
			RSelectPostInfo postInfo = postUseMapper.selectPostInfo(pSelectPostInfo);

			if(postInfo == null || postInfo.getUserSeq().equals("")) {
				result.setFail("삭제할 게시물을 찾을수 없습니다.");
				return result;
			}
			if(!_userInfo.getUserSeq().equals(postInfo.getUserSeq())) {
				result.setFail("타인의 게시물은 삭제할 수 없습니다.");
				return result;
			}
			param.setUserSeq(_userInfo.getUserSeq());
			result.setSuccess(postUseMapper.updatePostDelYn(param));
		} catch(Exception ex) {
			result.setError(ex);
		}

		return result;
	}

	/**
	 * 게시물 회신 조회
	 * @param param
	 * @return
	 */
	public ComResult<RSelectPostComment> selectPostComment(PSelectPostComment param) {
		ComResult<RSelectPostComment> result = new ComResult<>(param);

		try {
			result.setSuccess(postUseMapper.selectPostComment(param));
		} catch(Exception ex) {
			result.setError(ex);
		}

		return result;
	}
}
