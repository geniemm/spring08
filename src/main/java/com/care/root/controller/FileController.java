package com.care.root.controller;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.root.dto.FileDTO;
import com.care.root.service.FileService;

@Controller
@RequestMapping("file")
public class FileController {
	@Autowired FileService fs;
	
	@GetMapping("form")
	public String form() {
		return "file/uploadForm";
	}
	@PostMapping("upload1")
	public String upload1(@RequestParam String id,
							@RequestParam String name,
							@RequestParam MultipartFile file) {
		System.out.println("id: "+id);
		System.out.println("name: "+name);
		System.out.println("file: "+file.getOriginalFilename());
		
		fs.fileProcess(id,name,file);
		
		return "redirect:form";
	}
	@PostMapping("upload2")
	public String upload2(MultipartHttpServletRequest mt) {
		System.out.println("mt id: "+mt.getParameter("id"));
		System.out.println("mt name: "+mt.getParameter("name"));
		MultipartFile file = mt.getFile("file");
		System.out.println("mt file: "+file.getOriginalFilename());
		return "redirect:form";
	}
	// 위의 두개다 결과값은 동일, 사용하는방식 조금 다를뿐
	
	@GetMapping("views")
	public String views(Model model) {
		model.addAttribute("list",fs.getData());
		return "file/result";
	}
	@GetMapping("download")
	public void download(@RequestParam String file, HttpServletResponse res) throws Exception{
		res.addHeader("Content-disposition", "attachment; fileName="+URLEncoder.encode(file,"utf-8"));
		//다운로드방식으로 통신할거고 파일이름붙여서 파일다운할거다
		File f= new File(FileService.IMAGE_REPO+"/"+file); // 이미지가 저장된 위치로부터 이러이러한 파일을 가져오겠다
		FileInputStream in = new FileInputStream( f );
		FileCopyUtils.copy(in,res.getOutputStream()); //우리가 갖고온내용(in)을 사용자에게 응답해주겠다(아웃풋)
		in.close();
	}
	@GetMapping("delete")
	public String delete(@RequestParam String file,@RequestParam String id) {
		fs.delete(file,id);
		return "redirect:views";
	}
	@GetMapping("modify_view")
	public String modify(@RequestParam String id,Model model) {
		FileDTO dto = fs.modifyView(id);
		model.addAttribute("dto",dto);
		return "file/modify_view";
	}
	@PostMapping("modify")
	public String modify(@RequestParam String id,@RequestParam String name, @RequestParam MultipartFile file) {
		System.out.println("id: "+id);
		System.out.println("name: "+name);
		System.out.println("file: "+file.getOriginalFilename());
		fs.modify(id,name,file);
		
		return "redirect:views";
	}
}
