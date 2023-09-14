package com.care.root.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.care.root.dto.InfoDTO;

@Controller
public class AjaxController {
	@GetMapping("non_ajax")
	public String nonAjax() {
		System.out.println("non ajax");
		return "ajax/non_ajax";
		
	}
	@GetMapping("ajax01")
	public String ajax01() {
		System.out.println("ajax01");
		return "ajax/ajax01";
		
	}
	@GetMapping("ajax_test")
	public void ajaxFetch() {
		System.out.println("ajax fetch 출력");
	}
	
	@GetMapping("ajax02")
	public String ajax02() {
		return "ajax/ajax02";
	}
	int cnt = 0;
	@GetMapping("result02")
	@ResponseBody
	public String result02() {
		// data를 return으로 돌려주고싶으면 responseBody 해야댐
		return "" + cnt++; 
	}
	
	@GetMapping("ajax03")
	public String ajax03() {
		return "ajax/ajax03";
	}
	
	@PostMapping(value="result03",
				produces = "application/json; charset=utf-8")
	@ResponseBody
	public InfoDTO result03(@RequestBody InfoDTO dto) {
		System.out.println("이름: "+dto.getName());
		System.out.println("나이: "+dto.getAge());
		
		dto.setName("서버 수정 이름");
		dto.setAge(11111);
		
		return dto;
	}
	
	@GetMapping("ajax04")
	public String ajax04() {
		return "ajax/ajax04";
	}
	
	@PostMapping(value="result04",
				produces = "application/json; charset=utf-8")
	@ResponseBody
	public  Map<String, String> result04(@RequestBody Map<String, String> map) {
		System.out.println( map );
		System.out.println(map.get("name"));
		
		map.put("nick", "점심 먹을 시간이네용");
		
		return map;
	}
	
	//이때까지 컨트롤러로 사용한 기능은 jsp페이지로 이동하거나 데이터 돌려주는기능 
	// 페이지 이동하는거 하나, 데이터돌려주는거 하나 나눠서 사용해 > 데이터 돌려주는거:@RestController
	
	@GetMapping("rest01")
	public String rest01() {
		return "ajax/rest01";
	}
	
	
	@GetMapping("member")
	public String member() {
		
		return "ajax/member";
	}
	
	
}
