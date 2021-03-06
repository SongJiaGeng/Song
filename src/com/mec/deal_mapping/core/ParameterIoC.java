package com.mec.deal_mapping.core;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.mec.deal_mapping.annotation.AParameter;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ParameterIoC {
	public ParameterIoC() {
	}
	
	public Object[] addParameter(Object object, Method method, 
			String message) {
		Map<String, String> paraMap = new HashMap<String, String>();
		List<Object> paras = new ArrayList<Object>();
		
		JSONObject jsonObject = JSONObject.fromObject(message);
		Iterator<?> iterator = jsonObject.keys();
		while(iterator.hasNext()) {
			String key = (String) iterator.next();
			String value = jsonObject.getString(key);
			paraMap.put(key, value);
		}
		
		Parameter[] parameters = method.getParameters();
		for(Parameter parameter : parameters) {
			Object obj = new Object();
			AParameter aParameter = parameter.getAnnotation(
					AParameter.class);
			String id = aParameter.value();
			String paraValue = paraMap.get(id);
			Type paraType = parameter.getParameterizedType();
			
			obj = dealParameter(paraType, paraValue);
			paras.add(obj);
		}
		
		return paras.toArray();
	}
	
	@SuppressWarnings("unchecked")
	public <T> Object dealParameter(Type type, String value) {
		Object object = null;
		
		if(type instanceof ParameterizedType) {
			Type[] typeArguments = ((ParameterizedType)type).
					getActualTypeArguments();
			Type typeName = 
					((ParameterizedType) type).getRawType();
			if(typeName.equals(List.class)) {
				List<T> list = new ArrayList<T>();
				for(int i = 0; i < typeArguments.length;i++) {
					JSONArray jsonArray = JSONArray.fromObject(value);
					Iterator<?> it = jsonArray.iterator();
					while(it.hasNext()) {
						JSONObject obj = (JSONObject) it.next();
//						System.out.println("list类型:" + typeArguments[i] 
//								+ " "
//								+ "list参数:" 
//								+ obj.toString());
						T obje = (T) dealParameter(
								 typeArguments[i], obj.toString());
						list.add((T) obje);
					}
				}
				object = list;
			} else if(typeName.equals(Map.class)) {
				Map<T, T> map = new HashMap<T, T>();
				
				JSONArray jsonObject1 = JSONArray.fromObject(value);
				Iterator<?> iterator1 = jsonObject1.iterator();
				while(iterator1.hasNext()) {
					JSONObject jsonObject = (JSONObject) iterator1.next();
					Iterator<?> iterator = jsonObject.keys();
					while(iterator.hasNext()) {
						String key = (String) iterator.next();
						T realKey = (T) dealParameter(typeArguments[0], key);
						String keyValue = jsonObject.getString(key);
						T realValue = (T) dealParameter(typeArguments[1], keyValue);
//						System.out.println("map键类型:" + typeArguments[0] + " "
//								+ "map键的值为" + realKey
//								+ " "
//								+ "map值类型为:" + typeArguments[1] + " "
//								+ "map值的值为:" 
//								+ realValue);
						map.put(realKey, realValue);
					}
				}
			
				object = map;
			}
		} else if(type instanceof Class) {
			object = dealBaseData(type, value);
		}
		
		return object;
	}
	
	public Object dealBaseData(Type klass, String message) {
		Object obj = new Object();
		
		if(klass.equals(Integer.class)) {
			obj = Integer.valueOf(message);
		} else if(klass.equals(Short.class)) {
			obj = Short.valueOf(message);
		} else if(klass.equals(Long.class)) {
			obj = Long.valueOf(message);
		} else if(klass.equals(Double.class)) {
			obj = Double.valueOf(message);
		} else if(klass.equals(Float.class)) {
			obj = Float.valueOf(message);
		} else if(klass.equals(Character.class)) {
			obj = message.charAt(0);
		} else if(klass.equals(Byte.class)) {
			obj = Byte.valueOf(message);
		} else if(klass.equals(Boolean.class)) {
			obj = Boolean.valueOf(message);
		} else if(klass.equals(String.class)) {
			obj = message.toString();
		} else {
			obj = dealPOJO(klass, message);
		}
		return obj;
	}

	public Object dealPOJO(Type klass, String message) {
		Object object = null;
		Map<String, String> parameterMap = new HashMap<String, String>();
		JSONObject jsonObject = JSONObject.fromObject(message);
		Iterator<?> iterator = jsonObject.keys();
		while(iterator.hasNext()) {
			String key = (String) iterator.next();
			String name = "set" + key.substring(0, 1).toUpperCase()
								+ key.substring(1);
			String value = jsonObject.getString(key);
			parameterMap.put(name, value);
		}
		
		try {
			Class<?> clazz = Class.forName(klass.getTypeName());
			object = clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Method[] methods = object.getClass().getDeclaredMethods();
		for(Method method : methods) {
			String methodName = method.getName();
			if(!parameterMap.containsKey(methodName)) {
				continue;
			}
			try {
				method.invoke(object, parameterMap.get(methodName));
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		return object;
	}
}
