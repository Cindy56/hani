package com.game.modules.lottery.dto;

import java.io.Serializable;

public class OpenCaiReq implements Serializable {
	private static final long serialVersionUID = 2501664638025438051L;
	/** 请求地址 */
	private String url;
	/** 用户账号	付费用户必填，唯一标识。例：token=a1b2c3d4f5 */
	private String token;
	/** 彩票代码	部份接口格式支持逗号分割的多种彩票。例：code=cqssc,cqklsf */
	private String code;
	/** 返回行数	仅按最新查询时有效，默认5行。例：rows=3 */
	private String rows;
	/** 数据格式	支持xml与json格式，默认json。例：format=json */
	private String format;
	/** 日期参数	仅按日期查询(daily.do)时有效，格式yyyyMMdd或yyyy-MM-dd ，低频按年返回*/
	private String date;
	/** Jsonp回调函数	可选 */
	private String callback;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getRows() {
		return rows;
	}
	public void setRows(String rows) {
		this.rows = rows;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCallback() {
		return callback;
	}
	public void setCallback(String callback) {
		this.callback = callback;
	}
}
