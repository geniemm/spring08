package com.care.root.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.care.root.dto.InfoDTO;

@Service
public class InfoService {
	Map<String, InfoDTO> DB;
	ArrayList<InfoDTO> list;
	public InfoService() {
		DB = new HashMap<String, InfoDTO>();
		list = new ArrayList<InfoDTO>();
		for(int i=0;i<5;i++) {
			InfoDTO dto = new InfoDTO();
			
			dto.setName("È«±æµ¿"+i);
			dto.setAge(i);
			
			list.add(dto);
			DB.put("È«±æµ¿"+i, dto);
		}
	}
	public List<InfoDTO> getList() {
		return list;
	}
	public InfoDTO getUser(String id) {
		
		return DB.get(id);
	}
	public int modify(InfoDTO dto) {
		//update table set ¾îÂ¼°íÀúÂ¼°í
		
		DB.replace(dto.getName(), dto);
		return 1;
	}
	public int insert(InfoDTO dto) {
		DB.put(dto.getName(),dto);
		list.add(dto);
		return 1;
	}
	public void delete(String uId) {
		DB.remove(uId);
		for(int i=0;i<list.size();i++) {
			InfoDTO dto = list.get(i);
			if(dto.getName().equals(uId)) {
				list.remove( i );
			}
		}
	}
}
