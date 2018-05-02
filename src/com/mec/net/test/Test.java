package com.mec.net.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mec.deal_mapping.model.StudentModel;
import com.mec.net.util.ParameterParse;

public class Test {
	public static void main(String[] args) {
		ParameterParse para = new ParameterParse();
		String id = "id";
		String value = "03151352";
		para.addParameter(id, value);
		
		String listId = "modelList";
		List<StudentModel> list = new ArrayList<StudentModel>();
		
		StudentModel model1 = new StudentModel();
		model1.setId("song");
		model1.setPassword("1234");
		list.add(model1);
		
		StudentModel model2 = new StudentModel();
		model2.setId("li");
		model2.setPassword("4321");
		list.add(model2);
		
		para.addParameter(listId, list);
		
		String mapId = "modelMap";
		Map<String, StudentModel> map = new HashMap<>();
		
		StudentModel model3 = new StudentModel();
		model3.setId("wang");
		model3.setPassword("1234");
		map.put("wang", model3);
		
		StudentModel model4 = new StudentModel();
		model4.setId("liu");
		model4.setPassword("4321");
		map.put("liu", model4);
		
		para.addParameter(mapId, map);
		
		System.out.println(para.getJSONObject());
	}
}
