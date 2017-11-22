package com.game.modules.lottery.exception;

import org.apache.commons.lang3.StringUtils;

public class LotteryNumDrawException extends Exception {
	private static final long serialVersionUID = -4283569126010801774L;

	
	public LotteryNumDrawException() {
		super("获取开奖号码异常");
	}
	public LotteryNumDrawException(Throwable e) {
		super("获取开奖号码异常", e);
	}
	
	public LotteryNumDrawException(String message) {
		super(StringUtils.isEmpty(message) ? "获取开奖号码异常" : message);
	}
}
