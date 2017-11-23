package com.entity;

public class ResultData {

	static public ResultData ResultDataOK()
	{
		ResultData rd = new ResultData();
		rd.errorCode = 0;
		rd.message = "OK";
		return rd;
	}
	
	static public ResultData ResultDataFail()
	{
		ResultData rd = new ResultData();
		rd.errorCode = 1;
		rd.message = "Fail";
		return rd;
	}
	/**
	 * @return the errorCode
	 */
	public int getErrorCode() {
		return errorCode;
	}
	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
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
	private int errorCode;
	private String message;
	private Object data;

}