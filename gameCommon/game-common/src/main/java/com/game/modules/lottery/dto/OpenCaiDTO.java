package com.game.modules.lottery.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
/**
 * 开奖网请求/响应对象
 * http://face.opencai.net/?token=tc173e9f673c13ak&verify=fb9d497d963c
 * @author Administrator
 *
 */
public class OpenCaiDTO {
	@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
	public class Req {
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
	@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
	public class Resp{
		/** 返回行数	仅按最新查询时有效，默认5行。例：rows=3 */
		private String rows; 
		/** 彩票代码 */
		private String code; 
		/** 最大IP调用次数 */
		private String remain; 
		/** 返回的开奖数据 */
		private List<LotteryNum> data;
		
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
		public List<LotteryNum> getData() {
			return data;
		}
		public void setData(List<LotteryNum> data) {
			this.data = data;
		}
	}
	@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
	public class LotteryNum {
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
}

//====================req
//http://d.apiplus.net/newly.do?token=tc173e9f673c13ak&code=xjssc&format=json

//=====================resp
//{
//	"rows": 5,
//	"code": "cqssc",
//	"remain": "4219hrs",
//	"data": [
//		{
//			"expect": "20171107064",
//			"opencode": "5,0,3,5,6",
//			"opentime": "2017-11-07 16:41:04",
//			"opentimestamp": 1510044064
//		}
//	]
//}