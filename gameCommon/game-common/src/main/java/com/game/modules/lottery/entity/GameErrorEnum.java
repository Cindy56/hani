package com.game.modules.lottery.entity;

public enum GameErrorEnum {
	
	OK("0000","成功"),
	ORDER_INVALID("0001","订单无效"),
	BETTING_COUNT_INVALID("0002","投注数无效"),
	ISSUSE_NO_INVALID("0003","期号无效"),
	BETTING_MONEY_INVALID("0004","投注金额无效"),
	BETTING_NUMBER_INVALID("0005","投注数无效"),
	BETTING_POINT_INVALID("0006","投注返点无效"),
	PLAY_MODE_MONEY_INVALID("0007","奖金模式不在范围内"),
	BET_DETIAL_INVALID("0008","投注号码格式不正确"),
	BET_RATE_INVALID("0009","投注倍数不正确"),
	PLAY_MODE_COMMISSION_RATE_INVALID("0010","返点比例不正确"),
	PLAY_CODE_INVALID("0011","没有配置这个玩法"),
	UNKNOWN_MEMBER_INVALID("0012","没有这个用户"),
	UNKNOWN_ERROR("9999","未知错误");
	
	private String code;
	private String msg;
	
	private GameErrorEnum(String code,String msg) {
		this.code=code;
		this.msg = msg;
	} 
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "["+this.code+"]"+msg;
	}
	
}
