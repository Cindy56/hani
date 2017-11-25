package com.game.modules.lottery.entity;

public class GameError {
	
	public static final Integer errCodeOkay = 0;
	public static final String errOkay = "成功";
	

	public static final Integer errCodeInvalid = 1;
	public static final String errInvalid = "无效";
	
	public static final Integer errCodeCountInvalid = 101;
	public static final String errCountInvalid = "计数无效";

	public static final Integer errCodeBettingCount = 102;
	public static final String errBettingCountInvalid = "投注数无效";

	public static final Integer errCodeBettingIssuseNo = 103;
	public static final String errBettingIssuseNo = "期号无效";

	public static final Integer errCodeBettingModel = 104;
	public static final String errBettingModel = "投注模式无效";

	public static final Integer errCodeBettingMoney = 105;
	public static final String errBettingMoney = "投注金额无效";

	public static final Integer errCodeBettingNumber = 106;
	public static final String errBettingNumber = "投注数无效";

	public static final Integer errCodeBettingPoint = 107;
	public static final String errBettingPoint = "投注返点无效";

	public static final Integer errCodeGraduationCount = 108;
	public static final String errGraduationCount = "投注倍数无效";

	public static final Integer errCodeLotteryCode = 109;
	public static final String errLotteryCode = "彩票代码无效";

	public static final Integer errCodePlayDetailCode = 110;
	public static final String errPlayDetailCode = "玩法代码无效";

	public static final Integer errCodePlayModeMoney = 111;
	public static final String errPlayModeMoney = "奖金模式不在范围内";
	
	public static final Integer errCodeBetDetial = 112;
	public static final String errBetDetial = "投注号码格式不正确";

	
}
