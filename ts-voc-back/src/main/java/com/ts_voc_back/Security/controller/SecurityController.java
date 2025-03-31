package com.ts_voc_back.Security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SecurityController {
	 @GetMapping("/success")
    public String loginP() {

        return "<html>\r\n"
        		+ "<head>\r\n"
        		+ "    <meta charset=\"UTF-8\">\r\n"
        		+ "    <meta name=\"viewport\"\r\n"
        		+ "          content=\"width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0\">\r\n"
        		+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\r\n"
        		+ "    <title>Document</title>\r\n"
        		+ "</head>\r\n"
        		+ "<body>\r\n"
        		+ "    login 완료됨\r\n"
        		+ "</body>\r\n"
        		+ "</html>";
    }
}
