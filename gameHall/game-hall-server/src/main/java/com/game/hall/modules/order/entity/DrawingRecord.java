package com.game.hall.modules.order.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.game.modules.member.entity.MemberAccountCard;
import com.game.modules.sys.entity.User;

public class DrawingRecord {
	private static final long serialVersionUID = 1L;
	private String id;
	private String transactionNumber;
	private User user;		
	public String getTransactionNumber() {
		return transactionNumber;
	}
	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}
	private MemberAccountCard memberAccountCard;		
	private BigDecimal drawingBlance;		
	private Date drawingTime;
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
	public MemberAccountCard getMemberAccountCard() {
		return memberAccountCard;
	}
	public void setMemberAccountCard(MemberAccountCard memberAccountCard) {
		this.memberAccountCard = memberAccountCard;
	}
	public BigDecimal getDrawingBlance() {
		return drawingBlance;
	}
	public void setDrawingBlance(BigDecimal drawingBlance) {
		this.drawingBlance = drawingBlance;
	}
	public Date getDrawingTime() {
		return drawingTime;
	}
	public void setDrawingTime(Date drawingTime) {
		this.drawingTime = drawingTime;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}

}
