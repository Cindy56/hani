package com.entity;

import com.game.modules.lottery.entity.ResponseMsgData;

public class ResultData {

	private Boolean isSucceed;
	private String code;
	private String message;
	private Object data;
	
	public ResultData() {}
	public ResultData(boolean isSucceed,String message,Object data) {
		this.isSucceed = isSucceed;
		this.data= data;
	}
	
	public Boolean getIsSucceed() {
		return isSucceed;
	}
	public void setIsSucceed(Boolean isSucceed) {
		this.isSucceed = isSucceed;
	}
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}
	
	public static ResultData ok() {
		return new ResultData(true,"成功",null);
	}
	
	public static ResultData ok(Object data) {
		return new ResultData(true,"成功",data);
	}
	
	public static ResultData error(String message) {
		return new ResultData(false,message,null);
	}

}
