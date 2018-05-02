package com.mec.net.test;

import java.lang.reflect.Method;

import com.mec.net.model.INetMessage;

public class Test2 implements INetMessage{
	private String from;
	private String to;
	private String command;
	private String message;
	private String action;
	private String parament;

	public String getParament() {
		return this.parament;
	}

	public INetMessage setParament(String parament) {
		this.parament = parament;
		return this;
	}

	@Override
	public INetMessage setFrom(String from) {
		this.from = from;
		return this;
	}

	@Override
	public INetMessage setTo(String to) {
		this.to = to;
		return this;
	}

	@Override
	public INetMessage setCommand(String command) {
		this.command = command;
		return this;
	}

	@Override
	public INetMessage setAction(String action) {
		this.action = action;
		return this;
	}

	@Override
	public INetMessage setMessage(String message) {
		this.message = message;
		return this;
	}

	@Override
	public String getFrom() {
		return this.from;
	}

	@Override
	public String getTo() {
		return this.to;
	}

	@Override
	public String getCommand() {
		return this.command;
	}

	@Override
	public String getAction() {
		return this.action;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

	//  from:to//command:action?parament=parament&message=message
	@Override
	public INetMessage stringToNetMessage(String message) {
		Object obj = null;
		Method method = null;
		try {
			obj = Test2.class.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] parts1 = message.split("//");
		
		String[] part1 = parts1[0].split(":");
		try {
			method = obj.getClass().getMethod("setFrom", String.class);
			method.invoke(obj, part1[0]);
			method = obj.getClass().getMethod("setTo", String.class);
			method.invoke(obj, part1[1]);
		} catch (Exception e) {
			e.printStackTrace();
		}
//SERVER:192.168.1.211.1512870095//ID:?parament=parament&message=192.168.1.211.1512870095
		String[] parts2 = parts1[1].split("\\?");
		
		String[] part2 = parts2[0].split(":");
		try {
			method = obj.getClass().getMethod("setCommand", String.class);
			method.invoke(obj, part2[0]);
			method = obj.getClass().getMethod("setAction", String.class);
			method.invoke(obj, part2[1]);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String[] parts3 = parts2[1].split("&");
		for(String part : parts3) {
			String[] part4 = part.split("=");
			String methodName = "set" + part4[0].substring(0,1).toUpperCase()
					+ part4[0].substring(1);
			try {
				
				method = obj.getClass().getMethod(
						methodName, String.class);
				method.invoke(obj, part4[1]);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return (INetMessage) obj;
	}

	//  from:to//command:action?&message=message
	@Override
	public String NetMessageToOgnl(INetMessage netMessage) {
		return getFrom() + ":" + getTo() + "//" + getCommand()
				+ ":" + getAction() + "?" + "&message=" + getMessage(); 
	}

	@Override
	public String toString() {
		return getFrom() + ":" + getTo() + "//" + getCommand()
		+ ":" + getAction() + "?" + "&message=" + getMessage();
	}
}
