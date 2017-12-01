package com.game.modules.lottery.entity;

public class ResponseMsgData {
	
	private Boolean isSucceed;
	private String msg;//错误消息
	
	public ResponseMsgData() {}
	public ResponseMsgData(boolean isSucceed,String msg) {
		this.isSucceed = isSucceed;
		this.msg = msg;
	}
	private ResponseMsgData(boolean isSucceed,GameErrorEnum gameErrorEnum) {
		this.isSucceed = isSucceed;
		this.msg = gameErrorEnum.toString();
	}
	public Boolean getIsSucceed() {
		return isSucceed;
	}
	public void setIsSucceed(Boolean isSucceed) {
		this.isSucceed = isSucceed;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public static ResponseMsgData ok() {
		return new ResponseMsgData(true,GameErrorEnum.OK);
	} 
	public static ResponseMsgData error(GameErrorEnum gameErrorEnum) {
		return new ResponseMsgData(false,gameErrorEnum);
	} 
	public static void main(String[] args) {
		System.out.println(ResponseMsgData.error(GameErrorEnum.ORDER_INVALID).isSucceed);
	}
}
