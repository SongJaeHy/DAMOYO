package com.damoyo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.damoyo.domain.PhotocommentsVO;
import com.damoyo.service.PCommentsService;


@RestController
@RequestMapping("/replies/*")
public class PReplyController {
	
	@Autowired
	private PCommentsService service;
	
	@PostMapping(value="", consumes="application/json",
			produces= {MediaType.TEXT_PLAIN_VALUE})
	
	public ResponseEntity<String> regitster (
			// rest컨트롤러에서 받는 파라미터 앞에
			// @RequestBody 어노테이션이 붙어야
			// consumes와 연결됨
			@RequestBody PhotocommentsVO vo){
		ResponseEntity<String> entity=null;
		try {
			service.addcomments(vo);
			entity = new ResponseEntity<String>(
					"SUCCESS", HttpStatus.OK);
		}catch(Exception e) {
			// catch로 넘어왔다라는건 글쓰기 로직에 문제가 생긴 상황
			entity = new ResponseEntity<String>(
					e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}
