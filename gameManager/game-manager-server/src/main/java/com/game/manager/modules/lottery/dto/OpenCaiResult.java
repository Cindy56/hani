package com.game.manager.modules.lottery.dto;

import java.io.Serializable;

public class OpenCaiResult implements Serializable {
	private static final long serialVersionUID = 7323789488775757684L;
	/** 期号 */
	private String expect;
	/** 开奖号码 */
	private String opencode;
	/** 开奖时间 */
	private String opentime;
	/** 开奖时间戳 */
	private String opentimestamp;
	
	public String getExpect() {
		return expect;
	}
	public void setExpect(String expect) {
		this.expect = expect;
	}
	public String getOpencode() {
		return opencode;
	}
	public void setOpencode(String opencode) {
		this.opencode = opencode;
	}
	public String getOpentime() {
		return opentime;
	}
	public void setOpentime(String opentime) {
		this.opentime = opentime;
	}
	public String getOpentimestamp() {
		return opentimestamp;
	}
	public void setOpentimestamp(String opentimestamp) {
		this.opentimestamp = opentimestamp;
	}
}
