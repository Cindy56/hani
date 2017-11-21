package com.game.hall.modules.order.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.game.hall.modules.sys.entity.Office;
import com.game.hall.modules.sys.entity.User;

public class RechargeRecord {	
	private static final long serialVersionUID = 1L;
	private String id;
	private User user;		
	private String serialNumber;		
	private Date applyTime;		
	private BigDecimal applyAmount;
	private BigDecimal arrivalAmount;
	private char rechargeMode;
	private char status;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	public BigDecimal getApplyAmount() {
		return applyAmount;
	}
	public void setApplyAmount(BigDecimal applyAmount) {
		this.applyAmount = applyAmount;
	}
	public BigDecimal getArrivalAmount() {
		return arrivalAmount;
	}
	public void setArrivalAmount(BigDecimal arrivalAmount) {
		this.arrivalAmount = arrivalAmount;
	}
	public char getRechargeMode() {
		return rechargeMode;
	}
	public void setRechargeMode(char rechargeMode) {
		this.rechargeMode = rechargeMode;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	
	
}
