package com.ts_voc_back.Post.use.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.ts_voc_back.Post.use.mapper.PostUseMapper;
import com.ts_voc_back.common.model.ComResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostUseService {

	@Autowired
	final PostUseMapper postUseMapper = null;

	private String devDefaultPath = "C:\\Users\\eun_su_kim\\Documents\\web\\ts_voc_folder\\content_img";
	private String defaultPath = "";

	public ComResult<Map<String, Object>> uploadContentImg(List<MultipartFile> fileList) {
		ComResult<Map<String, Object>> result = new ComResult<Map<String, Object>>();
		Map<String, Object> innerResult = new HashMap<>();
		try {

	        for(MultipartFile file : fileList) {
	        	// 저장할 폴더명을 uuid로 생성
	        	String uuid = UUID.randomUUID().toString();
	        	// 저장할 폴더 경로
				String savePath = devDefaultPath+"\\"+uuid;
				File uploadPath = new File(savePath);
				// 해당경로에 폴더가 없으면 생성
		        if(uploadPath.exists() == false) {
		            uploadPath.mkdir();
		        }

	        	// 저장을 위해 File 타입의 이미지 정보를 담고있는 변수 선언
                File saveFile = new File(uploadPath, file.getOriginalFilename());
                // 저장
                file.transferTo(saveFile);

                String imgUuid = file.getOriginalFilename().split("\\.")[0];
                innerResult.put(imgUuid, "/api/content/img/"+uuid+"/"+file.getOriginalFilename());
	        }

	        result.setSuccess(innerResult);

		} catch(Exception ex) {
			result.setError(ex);
		}

		return result;
	}

	public ResponseEntity<byte[]> displayImg(String filepath, String filename) {

		//파일이 저장된 경로
		String savename = devDefaultPath+"\\"+filepath+"\\"+filename;
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
}
