package com.game.modules.lottery.dto;

import java.io.Serializable;
import java.util.List;

public class OpenCaiResp implements Serializable {
	private static final long serialVersionUID = 7077771033516129222L;
	/** 返回行数	仅按最新查询时有效，默认5行。例：rows=3 */
	private String rows; 
	/** 彩票代码 */
	private String code; 
	/** 最大IP调用次数 */
	private String remain; 
	/** 返回的开奖数据 */
	private List<OpenCaiResult> data;
	public String getRows() {
		return rows;
	}
	public void setRows(String rows) {
		this.rows = rows;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getRemain() {
		return remain;
	}
	public void setRemain(String remain) {
		this.remain = remain;
	}
	public List<OpenCaiResult> getData() {
		return data;
	}
	public void setData(List<OpenCaiResult> data) {
		this.data = data;
	}
}
