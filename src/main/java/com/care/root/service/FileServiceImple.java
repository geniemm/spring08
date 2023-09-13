package com.care.root.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.care.root.dto.FileDTO;
import com.care.root.mybatis.FileMapper;

@Service
public class FileServiceImple implements FileService {

	@Autowired
	FileMapper mapper;

	@Override
	public void fileProcess(String id, String name, MultipartFile file) {
		// if(file.getSize() != 0) {
		// 사이즈가 0과같지않다면 > 파일이 들어왔다 }

		FileDTO dto = new FileDTO();
		dto.setId(id);
		dto.setName(name);

		if (!file.isEmpty()) {
			// 파일이있다면 false(!)
			SimpleDateFormat fo = new SimpleDateFormat("yyyyMMddHHmmss-");
			// sysFileName =202309130102910-
			String sysFileName = fo.format(new Date());
			sysFileName += file.getOriginalFilename();
			System.out.println("sysFileName: " + sysFileName);
			File saveFile = new File(IMAGE_REPO + "/" + sysFileName);// 이 위치에 이러한 이름으로 저장하겠다
			try {
				file.transferTo(saveFile); // transferto : 알아서 저장해주는 기능
				dto.setImgName(sysFileName); // 변경된 파일이름 dto에 저장해줘
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// 파일이 존재하지 않는다면
			dto.setImgName("nan");
		}
		try {
			mapper.saveData(dto);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<FileDTO> getData() {
		List<FileDTO> list = null;
		try {
			list = mapper.getData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public void delete(String file, String id) {
		//db내용 삭제되면 이미지폴더이있는 파일 삭제
		try {
			int result = mapper.delete(id);
			if(result==1) {
				File d = new File(IMAGE_REPO+"/"+file);
				System.out.println(d.exists()); 
				if(d.exists()) {
				d.delete();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public FileDTO modifyView(String id) {
			FileDTO dto	= new FileDTO();
		try {
			dto = mapper.modifyView(id);
			System.out.println(dto.getId());
			System.out.println(dto.getImgName());
			System.out.println(dto.getName());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	@Override
	public void modify(String id, String name, MultipartFile file) {
		FileDTO dto = new FileDTO();
		dto.setId(id);
		dto.setName(name);
		if (!file.isEmpty()) {
			SimpleDateFormat fo = new SimpleDateFormat("yyyyMMddHHmmss-");
			String sysFileName = fo.format(new Date());
			sysFileName += file.getOriginalFilename();
			System.out.println("sysFileName: " + sysFileName);
			File saveFile = new File(IMAGE_REPO + "/" + sysFileName);
			try {
				file.transferTo(saveFile); 
				dto.setImgName(sysFileName); 
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			dto.setImgName("nan");
		}
		try {
			int result = mapper.modify(dto);
			System.out.println("result"+result);
			if (result == 1) {
				File d = new File(IMAGE_REPO + "/" + file);
				System.out.println(d.exists());
				if (d.exists()) {
					d.delete();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
