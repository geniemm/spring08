package com.care.root.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.care.root.dto.InfoDTO;
import com.care.root.service.InfoService;

import oracle.jdbc.proxy.annotation.Post;

@RestController
public class AjaxRestController {
	// 데이터만 return으로 돌려주는 컨트롤러, 페이지이동은 못함
	
	@GetMapping(value="rest", 
			produces="application/text; charset=utf-8")
	public String get() {
		System.out.println("get");
		return "get 데이터 요청할 때 사용";
	}
	
	@PostMapping(value="rest", 
			produces="application/text;charset=utf-8")
	public String post() {
		System.out.println("post");
		return "post 데이터 추가할때 사용";
	}
	
	@PutMapping(value="rest", 
			produces="application/text;charset=utf-8")
	public String put() {
		System.out.println("put");
		return "put 데이터 수정할 때 사용";
	}
	
	@DeleteMapping(value="rest", 
			produces="application/text;charset=utf-8")
	public String del() {
		System.out.println("del");
		return "delete 데이터 삭제할 때 사용";
	}
	
	@Autowired InfoService is;
	
	@GetMapping(value="users", produces="application/json;charset=utf-8")
	public List<InfoDTO> users(){
		return is.getList();
	}
	
	@GetMapping(value="getUser", produces="application/json;charset=utf-8")
	public InfoDTO getUser(@RequestParam String id){
		System.out.println(id);
		return is.getUser(id);
	}
	
	@GetMapping(value="getUser/{aaa}", produces="application/json;charset=utf-8")
	public InfoDTO getUser2(@PathVariable(value="aaa") String id){
		System.out.println("@PathVariable =>"+ id);
		return is.getUser(id);
	}	
	@PutMapping(value="modify",produces="application/json;charset=utf-8")
	public int modify(@RequestBody InfoDTO dto) {
		System.out.println(dto.getName());
		System.out.println(dto.getAge());
		int result = is.modify(dto);
		return result;
	}
	@PostMapping(value="insert",produces="application/json;charset=utf-8")
	public int insert(@RequestBody InfoDTO dto) {
		//dto로 받던 map으로 받던 상관없이 key:value로 들어옴
		System.out.println(dto.getName());
		System.out.println(dto.getAge());
		int result = is.insert(dto);
		return result;
	}
	@DeleteMapping(value="delete/{uId}",produces="application/json;charset=utf-8")
	public void delete(@PathVariable String uId) { 
		is.delete( uId );
	}
}
