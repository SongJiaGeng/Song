package com.mec.deal_mapping.test;

import com.mec.deal_mapping.core.ParameterIoC;
import com.mec.deal_mapping.core.Running;
import com.mec.net.server.RequestAction;

public class Test {
	public static void main(String[] args) {
//String message1 = "[{\"123\":{\"id\":\"123\",\"password\":\"123\"},\"321\":{\"id\":\"321\",\"password\":\"321\"}}]";
//String message2 = "[{\"id\":\"123\",\"password\":\"123\"},{\"id\":\"321\",\"password\":\"321\"}]";
//		
//		String message3 = "{\"id\":\"123\",\"password\":\"321\"}";
//	//	ParameterIoC paraIoC = new ParameterIoC();
//		
		Running context = new Running();
		ParameterIoC paraIoC = new ParameterIoC();
//		Map<String, ActionDefinition> annotationMap = context.getMap();
		
		String action = "getStudent";
		String message = "{\"id\": \"03151352\",\"modelList\":"
				+ "[{\"id\":\"123\",\"password\":\"123\"},{\"id\":\"321\",\"password\":\"321\"}],"
				+ "\"modelMap\":[{\"123\":{\"id\":\"123\",\"password\":\"123\"},\"321\":{\"id\":\"321\",\"password\":\"321\"}}]}";
//		String testMessage = "{\"modelList\":[\"oneList\":{\"id\":\"123\",\"password\":\"123\"},{\"id\":\"321\",\"password\":\"321\"},\"twoList\":{\"id\":\"123\",\"password\":\"123\"},{\"id\":\"321\",\"password\":\"321\"}]}";
		String mess = "{\"id\":\"03151352\",\"modelList\":"
				+ "[{\"id\":\"song\"},{\"id\":\"li\"}],"
				+ "\"modelMap\":[{\"wang\":{\"id\":\"wang\",\"password\":\"1234\"},\"liu\":{\"id\":\"liu\",\"password\":\"4321\"}}]}";
		RequestAction requestAction = new RequestAction();
		requestAction.dealRequest(action, message);
//		oneList = {\"id\":\"123\",\"password\":\"123\"}",{\"id\":\"321\",\"password\":\"321\"}
//		String id = "03151352";
				
//		List<StudentModel> list = new ArrayList<>();
//		Map<String, StudentModel> map = new HashMap<>();
//		StudentModel stuModel1 = new StudentModel();
//		stuModel1.setId("1");
//		stuModel1.setPassword("1");
//		StudentModel stuModel2 = new StudentModel();
//		stuModel2.setId("2");
//		stuModel2.setPassword("2");
//		
//		list.add(stuModel1);
//		list.add(stuModel2);
//		
//		StudentModel stuModel3 = new StudentModel();
//		stuModel3.setId("3");
//		stuModel3.setPassword("3");
//		map.put("3", stuModel3);
//		
//		StudentModel stuModel4 = new StudentModel();
//		stuModel4.setId("4");
//		stuModel4.setPassword("4");
//		map.put("4", stuModel4);
		
//		System.out.println(list);
//		System.out.println("-----------------------------");
//		System.out.println(map);
//		List<T> paras = new ArrayList<>();
//		paras.add((T) id);
//		paras.add((T) list);
//		paras.add((T) map);
//		System.out.println(paras);
//		
//		try {
//			Object obj = Information.class.newInstance();
//			Method method = obj.getClass().getMethod("getStudentInfo", 
//					String.class, List.class, Map.class);
//			System.out.println(method.getName());
//			
//			method.invoke(obj, paras);
//		} catch (InstantiationException e) {
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		} catch (NoSuchMethodException e) {
//			e.printStackTrace();
//		} catch (SecurityException e) {
//			e.printStackTrace();
//		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
//		} catch (InvocationTargetException e) {
//			e.printStackTrace();
//		}
		
	}
}
