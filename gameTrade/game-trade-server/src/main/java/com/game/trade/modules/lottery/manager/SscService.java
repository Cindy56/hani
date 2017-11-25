package com.game.trade.modules.lottery.manager;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.game.common.utils.StringUtils;
import com.game.modules.lottery.entity.GameError;
import com.game.modules.lottery.entity.LotteryTimeNum;
import com.game.modules.order.entity.LotteryOrder;
import com.game.trade.model.OpenLottery;
import com.game.trade.model.Star5;
import com.game.trade.modules.lottery.MoneyType;
import com.game.trade.util.BetNoSeparate;
import com.game.trade.util.CheckString;

/**
 * 时时彩玩法 将通用的重构出来，在子枚举类里通过supper调用
 * 
 * @author Administrator
 */
public enum SscService implements LotteryService {

	/** 单星直选 */
	SSC_DAN1_ZHIXUAN("SSC_DAN1_ZHIXUAN", "单星直选") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {
			// TODO Auto-generated method stub
		}

		@Override
		public int checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
			// 1、对注单进行基础校验（注单期号、投注时间有效性、返水范围校验）
			if(super.checkOrder(lotteryOrder,betLotteryTimeNum)!=0){return 0;}
			// 2、数据格式校验，投注内容必须是数字或减号以英文逗号或空格隔开的字符串，且至少包含一个数字
			String betNumber=lotteryOrder.getBetDetail();String formatRegex="((\\d{1,10}|-){1},{1}){4}(\\d{1,10}|-){1}";String containRegex=".*\\d.*";if(betNumber.matches(formatRegex)&&betNumber.matches(containRegex)){return 0;}
			// 3、内容有效性校验，判断内容合法性，同位不允许出现重复号码
			if(!LotteryUtils.checkRepeatNumber(betNumber)){return 0;}
			// 4、校验订单金额
			int amount=LotteryUtils.orderCount1XingZhiXuan(lotteryOrder.getBetDetail());return super.checkAmount(lotteryOrder,amount);
		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if(null==lotteryOrder||null==openLotteryTimeNum){return false;}return LotteryUtils.checkWinSsc1XingDingWei(openLotteryTimeNum.getOpenNum(),lotteryOrder.getBetDetail());
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			BigDecimal zero=new BigDecimal(0);if(null==lotteryOrder||null==openlotteryTimeNum){return zero;}
			// 判断一下是否中奖，未中奖直接返回金额为0
			int winCount=LotteryUtils.winCountSsc1XinDingWei(openlotteryTimeNum.getOpenNum(),lotteryOrder.getBetDetail());if(!(winCount>0)){return zero;}
			// 投注奖金组、投注倍数、投注模式、中奖注数
			BigDecimal playModeMoney=new BigDecimal(lotteryOrder.getPlayModeMoney());BigDecimal betRate=new BigDecimal(lotteryOrder.getBetRate());BigDecimal playModeMoneyType=getParamByType(lotteryOrder);
			// 中奖金额 = 奖金组 * 投注倍数 * 投注模式对应面值 * 中奖注数
			return playModeMoney.multiply(betRate).multiply(playModeMoneyType).multiply(new BigDecimal(winCount));
		}

		@Override
		public int calBetNum(String bet) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public boolean checkAmount(LotteryOrder lotteryOrder) {
			// TODO Auto-generated method stub
			return false;
		}
	},
	/** 时时彩前2直选单式 */
	SSC_QIAN2_ZHIXUANDAN("SSC_QIAN2_ZHIXUANDAN", "时时彩前2直选单式 ") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {

		}

		@Override
		public int checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {

			return 1;
		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if(null==lotteryOrder||null==openLotteryTimeNum){return false;}return LotteryUtils.ssc3XinHunHeZuXuan(openLotteryTimeNum.getOpenNum().substring(0,5),lotteryOrder.getBetDetail());
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			// 如果没有中奖，直接返回金额为0
			if(!checkWin(lotteryOrder,openlotteryTimeNum)){return BigDecimal.ZERO;}
			// 投注奖金组、投注倍数、投注模式
			BigDecimal playModeMoney=new BigDecimal(lotteryOrder.getPlayModeMoney());BigDecimal betRate=new BigDecimal(lotteryOrder.getBetRate());BigDecimal playModeMoneyType=getParamByType(lotteryOrder);BigDecimal winCount=new BigDecimal(1);
			// 中奖金额 = 奖金组 * 投注倍数 * 投注模式对应面值
			return playModeMoney.multiply(betRate).multiply(playModeMoneyType).multiply(winCount);
		}
	},



	// /** 时时彩中3组选6 */
	SSC_ZHONG3_ZUXUAN6("SSC_ZHONG3_ZUXUAN6", "时时彩中3组选6") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {
			// TODO Auto-generated method stub

		}

		@Override
		public int checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
			// 对注单进行基础校验
			if(super.checkOrder(lotteryOrder,betLotteryTimeNum)!=0){return 1;}
			// 正则验证投注号码,必须是0-9的数字以逗号分隔
			String regex="^\\d+(,\\d{0,2})*$";if(!lotteryOrder.getBetDetail().matches(regex)){return 1;}
			// 校验订单金额
			return super.checkAmount(lotteryOrder,LotteryUtils.ssc3XinBetCount(lotteryOrder));
		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if(null==lotteryOrder||null==openLotteryTimeNum){return false;}return LotteryUtils.ssc3XinZuxuan6(openLotteryTimeNum.getOpenNum().trim().substring(2,7),lotteryOrder.getBetDetail());
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			// 如果没有中奖，直接返回金额为0
			if(!checkWin(lotteryOrder,openlotteryTimeNum)){return BigDecimal.ZERO;}
			// 投注奖金组、投注倍数、投注模式
			BigDecimal playModeMoney=new BigDecimal(lotteryOrder.getPlayModeMoney());BigDecimal betRate=new BigDecimal(lotteryOrder.getBetRate());BigDecimal playModeMoneyType=getParamByType(lotteryOrder);
			// 中奖金额 = 奖金组 * 投注倍数 * 投注模式对应面值
			return playModeMoney.multiply(betRate).multiply(playModeMoneyType);
		}

	},
	SSC_ZHONG3_HUNHEZUXUN("SSC_ZHONG3_HUNHEZUXUN", "时时彩中3混合组选") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {

		}

		@Override
		public int checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {

			return 1;
		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if(null==lotteryOrder||null==openLotteryTimeNum){return false;}return LotteryUtils.ssc3XinHunHeZuXuan(openLotteryTimeNum.getOpenNum().substring(2,7),lotteryOrder.getBetDetail());
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			// 如果没有中奖，直接返回金额为0
			if(!checkWin(lotteryOrder,openlotteryTimeNum)){return BigDecimal.ZERO;}
			// 投注奖金组、投注倍数、投注模式
			BigDecimal playModeMoney=new BigDecimal(lotteryOrder.getPlayModeMoney());BigDecimal betRate=new BigDecimal(lotteryOrder.getBetRate());BigDecimal playModeMoneyType=getParamByType(lotteryOrder);BigDecimal winCount=new BigDecimal(1);
			// 中奖金额 = 奖金组 * 投注倍数 * 投注模式对应面值
			return playModeMoney.multiply(betRate).multiply(playModeMoneyType).multiply(winCount);
		}
	},

	/** 时时彩中3和值尾数 */
	SSC_ZHONG3_HEZHIWEISHU("SSC_ZHONG3_HEZHIWEISHU", "时时彩中3和值尾数") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if(null==lotteryOrder||null==openLotteryTimeNum){return false;}return LotteryUtils.ssc3XinHeZhiWeiShu(openLotteryTimeNum.getOpenNum().substring(2,7),lotteryOrder.getBetDetail());
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			// 如果没有中奖，直接返回金额为0
			if(!checkWin(lotteryOrder,openlotteryTimeNum)){return BigDecimal.ZERO;}
			// 投注奖金组、投注倍数、投注模式、中奖注数
			BigDecimal playModeMoney=new BigDecimal(lotteryOrder.getPlayModeMoney());BigDecimal betRate=new BigDecimal(lotteryOrder.getBetRate());BigDecimal playModeMoneyType=getParamByType(lotteryOrder);BigDecimal winCount=new BigDecimal(1);return playModeMoney.multiply(betRate).multiply(playModeMoneyType).multiply(winCount);
		}

	},

	/** 时时彩中3直选和值 */
	SSC_ZHONG3_ZHIXUANHEZHI("SSC_ZHONG3_ZHIXUANHEZHI", "时时彩中3直选和值") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if(null==lotteryOrder||null==openLotteryTimeNum){return false;}return LotteryUtils.ssc3XinZhiXuanHeZhi(openLotteryTimeNum.getOpenNum().substring(2,7),lotteryOrder.getBetDetail());
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			// 如果没有中奖，直接返回金额为0
			if(!checkWin(lotteryOrder,openlotteryTimeNum)){return BigDecimal.ZERO;}
			// 投注奖金组、投注倍数、投注模式、中奖注数
			BigDecimal playModeMoney=new BigDecimal(lotteryOrder.getPlayModeMoney());BigDecimal betRate=new BigDecimal(lotteryOrder.getBetRate());BigDecimal playModeMoneyType=getParamByType(lotteryOrder);BigDecimal winCount=new BigDecimal(1);return playModeMoney.multiply(betRate).multiply(playModeMoneyType).multiply(winCount);
		}

	},

	// /** 时时彩后3组选6 */
	SSC_HOU3_ZUXUAN6("SSC_HOU3_ZUXUAN6", "时时彩后3组选6") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {
			// TODO Auto-generated method stub

		}

		@Override
		public int checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
			// 对注单进行基础校验
			if(super.checkOrder(lotteryOrder,betLotteryTimeNum)!=0){return 1;}
			// 正则验证投注号码,必须是0-9的数字以逗号分隔
			String regex="^\\d+(,\\d{0,2})*$";if(!lotteryOrder.getBetDetail().matches(regex)){return 1;}
			// 校验订单金额
			return super.checkAmount(lotteryOrder,LotteryUtils.ssc3XinBetCount(lotteryOrder));
		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if(null==lotteryOrder||null==openLotteryTimeNum){return false;}return LotteryUtils.ssc3XinZuxuan6(openLotteryTimeNum.getOpenNum().trim().substring(0,5),lotteryOrder.getBetDetail());
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			// 如果没有中奖，直接返回金额为0
			if(!checkWin(lotteryOrder,openlotteryTimeNum)){return BigDecimal.ZERO;}
			// 投注奖金组、投注倍数、投注模式
			BigDecimal playModeMoney=new BigDecimal(lotteryOrder.getPlayModeMoney());BigDecimal betRate=new BigDecimal(lotteryOrder.getBetRate());BigDecimal playModeMoneyType=getParamByType(lotteryOrder);
			// 中奖金额 = 奖金组 * 投注倍数 * 投注模式对应面值
			return playModeMoney.multiply(betRate).multiply(playModeMoneyType);
		}

	},
	// 时时彩后3混合组选
	SSC_HOU3_HUNHEZUXUN("SSC_HOU3_HUNHEZUXUN", "时时彩后3混合组选") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {

		}

		@Override
		public int checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {

			return 1;
		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if(null==lotteryOrder||null==openLotteryTimeNum){return false;}return LotteryUtils.ssc3XinHunHeZuXuan(openLotteryTimeNum.getOpenNum().substring(0,5),lotteryOrder.getBetDetail());
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			// 如果没有中奖，直接返回金额为0
			if(!checkWin(lotteryOrder,openlotteryTimeNum)){return BigDecimal.ZERO;}
			// 投注奖金组、投注倍数、投注模式
			BigDecimal playModeMoney=new BigDecimal(lotteryOrder.getPlayModeMoney());BigDecimal betRate=new BigDecimal(lotteryOrder.getBetRate());BigDecimal playModeMoneyType=getParamByType(lotteryOrder);BigDecimal winCount=new BigDecimal(1);
			// 中奖金额 = 奖金组 * 投注倍数 * 投注模式对应面值
			return playModeMoney.multiply(betRate).multiply(playModeMoneyType).multiply(winCount);
		}
	},

	/** 时时彩后3和值尾数 */
	SSC_HOU3_HEZHIWEISHU("SSC_HOU3_HEZHIWEISHU", "时时彩后3和值尾数") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if(null==lotteryOrder||null==openLotteryTimeNum){return false;}return LotteryUtils.ssc3XinHeZhiWeiShu(openLotteryTimeNum.getOpenNum().substring(openLotteryTimeNum.getOpenNum().length()-5),lotteryOrder.getBetDetail());
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			// 如果没有中奖，直接返回金额为0
			if(!checkWin(lotteryOrder,openlotteryTimeNum)){return BigDecimal.ZERO;}
			// 投注奖金组、投注倍数、投注模式、中奖注数
			BigDecimal playModeMoney=new BigDecimal(lotteryOrder.getPlayModeMoney());BigDecimal betRate=new BigDecimal(lotteryOrder.getBetRate());BigDecimal playModeMoneyType=getParamByType(lotteryOrder);BigDecimal winCount=new BigDecimal(1);return playModeMoney.multiply(betRate).multiply(playModeMoneyType).multiply(winCount);
		}

	},

	/** 时时彩后3直选和值 */
	SSC_HOU3_ZHIXUANHEZHI("SSC_HOU3_ZHIXUANHEZHI", "时时彩后3直选和值") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if(null==lotteryOrder||null==openLotteryTimeNum){return false;}return LotteryUtils.ssc3XinZhiXuanHeZhi(openLotteryTimeNum.getOpenNum().substring(openLotteryTimeNum.getOpenNum().length()-5),lotteryOrder.getBetDetail());
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			// 如果没有中奖，直接返回金额为0
			if(!checkWin(lotteryOrder,openlotteryTimeNum)){return BigDecimal.ZERO;}
			// 投注奖金组、投注倍数、投注模式、中奖注数
			BigDecimal playModeMoney=new BigDecimal(lotteryOrder.getPlayModeMoney());BigDecimal betRate=new BigDecimal(lotteryOrder.getBetRate());BigDecimal playModeMoneyType=getParamByType(lotteryOrder);BigDecimal winCount=new BigDecimal(1);return playModeMoney.multiply(betRate).multiply(playModeMoneyType).multiply(winCount);
		}

	},


	/** 时时彩4星组选4 */
	SSC_4XING_ZUXUAN4("SSC_4XING_ZUXUAN4", "时时彩4星组选4") {
		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {
			// TODO Auto-generated method stub
		}

		@Override
		public int checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
			// TODO Auto-generated method stub
			return 1;
		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if(null==lotteryOrder||null==openLotteryTimeNum){return false;}return LotteryUtils.ssc4XinZuXuan4(openLotteryTimeNum.getOpenNum().substring(openLotteryTimeNum.getOpenNum().length()-7),lotteryOrder.getBetDetail(),2);
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			// 如果没有中奖，直接返回金额为0
			if(!checkWin(lotteryOrder,openlotteryTimeNum)){return BigDecimal.ZERO;}
			// 投注奖金组、投注倍数、投注模式、中奖注数
			BigDecimal playModeMoney=new BigDecimal(lotteryOrder.getPlayModeMoney());BigDecimal betRate=new BigDecimal(lotteryOrder.getBetRate());BigDecimal playModeMoneyType=getParamByType(lotteryOrder);BigDecimal winCount=new BigDecimal(1);return playModeMoney.multiply(betRate).multiply(playModeMoneyType).multiply(winCount);
		}
	},
	//
	// /** 时时彩前3组选3 */
	// SSC_QIAN3_ZUXUAN3("SSC_QIAN3_ZUXUAN3", "时时彩前3组选3") {},
	//
	// /** 时时彩中3直选 */
	// SSC_ZHONG3_ZHIXUAN("SSC_ZHONG3_ZHIXUAN", "时时彩中3直选 ") {},
	//
	// /** 时时彩中3组选6 */
	// SSC_ZHONG3_ZUXUAN6("SSC_ZHONG3_ZUXUAN6", "时时彩中3组选6") {},
	//
	// /** 时时彩中3组选3 */
	// SSC_ZHONG3_ZUXUAN3("SSC_ZHONG3_ZUXUAN3", "时时彩中3组选3") {},
	//
	// /** 时时彩后3直选 */
	// SSC_HOU3_ZHIXUAN("SSC_HOU3_ZHIXUAN", "时时彩后3直选") {},
	//
	// /** 时时彩后3组6 */
	// SSC_HOU3_ZUXUAN6("SSC_HOU3_ZUXUAN6", "时时彩后3组6") {},
	//
	// /** 时时彩后3组3 */
	// SSC_HOU3_ZUXUAN3("SSC_HOU3_ZUXUAN3", "时时彩后3组3") {}
	
	


	
	/** 时时彩后2直选复式 */
	SSC_HOU2_ZHIXUANFU("SSC_HOU2_ZHIXUANFU","时时彩后2直选复式 "){

	@Override
	public void trend(LotteryTimeNum openLotteryTimeNum) {
		// TODO Auto-generated method stub
	}

	@Override
	public int checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
		// 1、对注单进行基础校验（注单期号、投注时间有效性、返水范围校验）
		if (super.checkOrder(lotteryOrder, betLotteryTimeNum) != 0) {
			return 1;
		}
		// 2、数据格式校验，两个位，以逗号或空格隔开
		String betNumber = lotteryOrder.getBetDetail();
		String formatRegex = "\\d{1,10},{1}\\d{1,10}";
		if (betNumber.matches(formatRegex)) {
			return 1;
		}
		// 3、内容有效性校验，判断内容合法性，同位不允许出现重复号码
		if (!LotteryUtils.checkRepeatNumber(betNumber)) {
			return 1;
		}
		// 4、校验订单金额
		return super.checkAmount(lotteryOrder, LotteryUtils.orderCountSscZhiXuanFu(lotteryOrder.getBetDetail()));
	}

	@Override
	public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
		if (null == lotteryOrder || null == openLotteryTimeNum) {
			return false;
		}
		String openNum = openLotteryTimeNum.getOpenNum();
		if (StringUtils.isBlank(openNum)) {
			return false;
		}
		return LotteryUtils.checkWinSscZhiXuanFu(openNum.substring(6), lotteryOrder.getBetDetail());
	}

	@Override
	public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
		// 如果没有中奖，直接返回金额为0
		if (!checkWin(lotteryOrder, openlotteryTimeNum)) {
			BigDecimal zero = new BigDecimal(0);
			return zero;
		}
		// 投注奖金组、投注倍数、投注模式
		BigDecimal playModeMoney = new BigDecimal(lotteryOrder.getPlayModeMoney());
		BigDecimal betRate = new BigDecimal(lotteryOrder.getBetRate());
		BigDecimal playModeMoneyType = getParamByType(lotteryOrder);
		// 若中奖，只可能有一注号码
		BigDecimal winCount = new BigDecimal(1);
		// 中奖金额 = 奖金组 * 投注倍数 * 投注模式对应面值 *中奖注数
		return playModeMoney.multiply(betRate).multiply(playModeMoneyType).multiply(winCount);
	}

	@Override
	public int calBetNum(String bet) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
		public boolean checkAmount(LotteryOrder lotteryOrder) {
			// TODO Auto-generated method stub
			return false;
		}

	},
	/** 时时彩前2组选复式 */
	SSC_QIAN2_ZUXUANFU("SSC_QIAN2_ZUXUANFU","时时彩前2组选复式"){

	@Override
	public void trend(LotteryTimeNum openLotteryTimeNum) {
		// TODO Auto-generated method stub
	}

	@Override
	public int checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
		// 1、对注单进行基础校验（注单期号、投注时间有效性、返水范围校验）
		if (super.checkOrder(lotteryOrder, betLotteryTimeNum) != 0) {
			return 1;
		}
		// TODO 2、数据格式校验
		// TODO 3、内容有效性校验
		// 4、校验订单金额
		return super.checkAmount(lotteryOrder, LotteryUtils.orderCountSsc2XingZuXuanFu(lotteryOrder.getBetDetail()));
	}

	@Override
	public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
		if (null == lotteryOrder || null == openLotteryTimeNum) {
			return false;
		}
		String openNum = openLotteryTimeNum.getOpenNum();
		if (StringUtils.isBlank(openNum)) {
			return false;
		}
		return LotteryUtils.checkWinSsc2XingZuXuanFu(openNum.substring(0, 3), lotteryOrder.getBetDetail());
	}

	@Override
	public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
		// 如果没有中奖，直接返回金额为0
		if (!checkWin(lotteryOrder, openlotteryTimeNum)) {
			BigDecimal zero = new BigDecimal(0);
			return zero;
		}
		// 投注奖金组、投注倍数、投注模式
		BigDecimal playModeMoney = new BigDecimal(lotteryOrder.getPlayModeMoney());
		BigDecimal betRate = new BigDecimal(lotteryOrder.getBetRate());
		BigDecimal playModeMoneyType = getParamByType(lotteryOrder);
		// 若中奖，只可能有一注号码
		BigDecimal winCount = new BigDecimal(1);
		// 中奖金额 = 奖金组 * 投注倍数 * 投注模式对应面值 *中奖注数
		return playModeMoney.multiply(betRate).multiply(playModeMoneyType).multiply(winCount);
	}

	@Override
	public int calBetNum(String bet) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
		public boolean checkAmount(LotteryOrder lotteryOrder) {
			// TODO Auto-generated method stub
			return false;
		}

	},
	/** 时时彩后2组选复式 */
	SSC_HOU2_ZUXUANFU("SSC_HOU2_ZUXUANFU","时时彩后2组选复式"){

	@Override
	public void trend(LotteryTimeNum openLotteryTimeNum) {
		// TODO Auto-generated method stub
	}

	@Override
	public int checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
		// 1、对注单进行基础校验（注单期号、投注时间有效性、返水范围校验）
		if (super.checkOrder(lotteryOrder, betLotteryTimeNum) != 0) {
			return 1;
		}
		// TODO 2、数据格式校验
		// TODO 3、内容有效性校验
		// 4、校验订单金额
		return super.checkAmount(lotteryOrder, LotteryUtils.orderCountSsc2XingZuXuanFu(lotteryOrder.getBetDetail()));
	}

	@Override
	public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
		if (null == lotteryOrder || null == openLotteryTimeNum) {
			return false;
		}
		String openNum = openLotteryTimeNum.getOpenNum();
		if (StringUtils.isBlank(openNum)) {
			return false;
		}
		return LotteryUtils.checkWinSsc2XingZuXuanFu(openNum.substring(6), lotteryOrder.getBetDetail());
	}

	@Override
	public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
		// 如果没有中奖，直接返回金额为0
		if (!checkWin(lotteryOrder, openlotteryTimeNum)) {
			BigDecimal zero = new BigDecimal(0);
			return zero;
		}
		// 投注奖金组、投注倍数、投注模式
		BigDecimal playModeMoney = new BigDecimal(lotteryOrder.getPlayModeMoney());
		BigDecimal betRate = new BigDecimal(lotteryOrder.getBetRate());
		BigDecimal playModeMoneyType = getParamByType(lotteryOrder);
		// 若中奖，只可能有一注号码
		BigDecimal winCount = new BigDecimal(1);
		// 中奖金额 = 奖金组 * 投注倍数 * 投注模式对应面值 *中奖注数
		return playModeMoney.multiply(betRate).multiply(playModeMoneyType).multiply(winCount);
	}

	@Override
	public int calBetNum(String bet) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
		public boolean checkAmount(LotteryOrder lotteryOrder) {
			// TODO Auto-generated method stub
			return false;
		}

	},
	/** 时时彩前3直选单式 */
	SSC_QIAN3_ZHIXUANDAN("SSC_QIAN3_ZHIXUANDAN","时时彩前3直选单式"){

	@Override
	public void trend(LotteryTimeNum openLotteryTimeNum) {
		// TODO Auto-generated method stub
	}

	@Override
	public int checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
		// 1、对注单进行基础校验（注单期号、投注时间有效性、返水范围校验）
		if (super.checkOrder(lotteryOrder, betLotteryTimeNum) != 0) {
			return 1;
		}
		// 2、数据格式校验，两位数字组成，单式多注以空格隔开
		String betNumber = lotteryOrder.getBetDetail();
		String formatRegex = "\\d{3}(,{1}\\d{3})*";
		if (betNumber.matches(formatRegex)) {
			return 1;
		}
		// 3、内容有效性校验，判断内容合法性，不允许出现重复投注
		if (!LotteryUtils.checkRepeatBet(betNumber)) {
			return 1;
		}
		// 4、校验订单金额
		return super.checkAmount(lotteryOrder, LotteryUtils.orderCountSscZhiXuanDan(lotteryOrder.getBetDetail()));
	}

	@Override
	public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
		if (null == lotteryOrder || null == openLotteryTimeNum) {
			return false;
		}
		String openNum = openLotteryTimeNum.getOpenNum();
		if (StringUtils.isBlank(openNum)) {
			return false;
		}
		return LotteryUtils.checkWinSscZhiXuanDan(openNum.substring(0, 5), lotteryOrder.getBetDetail());
	}

	@Override
	public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
		// 如果没有中奖，直接返回金额为0
		if (!checkWin(lotteryOrder, openlotteryTimeNum)) {
			BigDecimal zero = new BigDecimal(0);
			return zero;
		}
		// 投注奖金组、投注倍数、投注模式
		BigDecimal playModeMoney = new BigDecimal(lotteryOrder.getPlayModeMoney());
		BigDecimal betRate = new BigDecimal(lotteryOrder.getBetRate());
		BigDecimal playModeMoneyType = getParamByType(lotteryOrder);
		// 中奖号码只可能有一注
		BigDecimal winCount = new BigDecimal(1);
		// 中奖金额 = 奖金组 * 投注倍数 * 投注模式对应面值 * 中奖注数
		return playModeMoney.multiply(betRate).multiply(playModeMoneyType).multiply(winCount);
	}

	@Override
	public int calBetNum(String bet) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
		public boolean checkAmount(LotteryOrder lotteryOrder) {
			// TODO Auto-generated method stub
			return false;
		}

	},
	/** 时时彩中3直选单式 */
	SSC_ZHONG3_ZHIXUANDAN("SSC_ZHONG3_ZHIXUANDAN","时时彩中3直选单式"){

	@Override
	public void trend(LotteryTimeNum openLotteryTimeNum) {
		// TODO Auto-generated method stub
	}

	@Override
	public int checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
		// 1、对注单进行基础校验（注单期号、投注时间有效性、返水范围校验）
		if (super.checkOrder(lotteryOrder, betLotteryTimeNum) != 0) {
			return 1;
		}
		// 2、数据格式校验，两位数字组成，单式多注以空格隔开
		String betNumber = lotteryOrder.getBetDetail();
		String formatRegex = "\\d{3}(,{1}\\d{3})*";
		if (betNumber.matches(formatRegex)) {
			return 1;
		}
		// 3、内容有效性校验，判断内容合法性，不允许出现重复投注
		if (!LotteryUtils.checkRepeatBet(betNumber)) {
			return 1;
		}
		// 4、校验订单金额
		return super.checkAmount(lotteryOrder, LotteryUtils.orderCountSscZhiXuanDan(lotteryOrder.getBetDetail()));
	}

	@Override
	public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
		if (null == lotteryOrder || null == openLotteryTimeNum) {
			return false;
		}
		String openNum = openLotteryTimeNum.getOpenNum();
		if (StringUtils.isBlank(openNum)) {
			return false;
		}
		return LotteryUtils.checkWinSscZhiXuanDan(openNum.substring(2, 7), lotteryOrder.getBetDetail());
	}

	@Override
	public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
		// 如果没有中奖，直接返回金额为0
		if (!checkWin(lotteryOrder, openlotteryTimeNum)) {
			BigDecimal zero = new BigDecimal(0);
			return zero;
		}
		// 投注奖金组、投注倍数、投注模式
		BigDecimal playModeMoney = new BigDecimal(lotteryOrder.getPlayModeMoney());
		BigDecimal betRate = new BigDecimal(lotteryOrder.getBetRate());
		BigDecimal playModeMoneyType = getParamByType(lotteryOrder);
		// 中奖号码只可能有一注
		BigDecimal winCount = new BigDecimal(1);
		// 中奖金额 = 奖金组 * 投注倍数 * 投注模式对应面值 * 中奖注数
		return playModeMoney.multiply(betRate).multiply(playModeMoneyType).multiply(winCount);
	}

	@Override
	public int calBetNum(String bet) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
		public boolean checkAmount(LotteryOrder lotteryOrder) {
			// TODO Auto-generated method stub
			return false;
		}

	},
	/** 时时彩后3直选单式 */
	SSC_HOU3_ZHIXUANDAN("SSC_HOU3_ZHIXUANDAN","时时彩后3直选单式"){

	@Override
	public void trend(LotteryTimeNum openLotteryTimeNum) {
		// TODO Auto-generated method stub
	}

	@Override
	public int checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
		// 1、对注单进行基础校验（注单期号、投注时间有效性、返水范围校验）
		if (super.checkOrder(lotteryOrder, betLotteryTimeNum) != 0) {
			return 1;
		}
		// 2、数据格式校验，两位数字组成，单式多注以空格隔开
		String betNumber = lotteryOrder.getBetDetail();
		String formatRegex = "\\d{3}(,{1}\\d{3})*";
		if (betNumber.matches(formatRegex)) {
			return 1;
		}
		// 3、内容有效性校验，判断内容合法性，不允许出现重复投注
		if (!LotteryUtils.checkRepeatBet(betNumber)) {
			return 1;
		}
		// 4、校验订单金额
		return super.checkAmount(lotteryOrder, LotteryUtils.orderCountSscZhiXuanDan(lotteryOrder.getBetDetail()));
	}

	@Override
	public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
		if (null == lotteryOrder || null == openLotteryTimeNum) {
			return false;
		}
		String openNum = openLotteryTimeNum.getOpenNum();
		if (StringUtils.isBlank(openNum)) {
			return false;
		}
		return LotteryUtils.checkWinSscZhiXuanDan(openNum.substring(4), lotteryOrder.getBetDetail());
	}

	@Override
	public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
		// 如果没有中奖，直接返回金额为0
		if (!checkWin(lotteryOrder, openlotteryTimeNum)) {
			BigDecimal zero = new BigDecimal(0);
			return zero;
		}
		// 投注奖金组、投注倍数、投注模式
		BigDecimal playModeMoney = new BigDecimal(lotteryOrder.getPlayModeMoney());
		BigDecimal betRate = new BigDecimal(lotteryOrder.getBetRate());
		BigDecimal playModeMoneyType = getParamByType(lotteryOrder);
		// 中奖号码只可能有一注
		BigDecimal winCount = new BigDecimal(1);
		// 中奖金额 = 奖金组 * 投注倍数 * 投注模式对应面值 * 中奖注数
		return playModeMoney.multiply(betRate).multiply(playModeMoneyType).multiply(winCount);
	}

	@Override
	public int calBetNum(String bet) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
		public boolean checkAmount(LotteryOrder lotteryOrder) {
			// TODO Auto-generated method stub
			return false;
		}

	},
	/** 时时彩前3直选复式 */
	SSC_QIAN3_ZHIXUANFU("SSC_QIAN3_ZHIXUANFU","时时彩前3直选复式"){

	@Override
	public void trend(LotteryTimeNum openLotteryTimeNum) {
		// TODO Auto-generated method stub
	}

	@Override
	public int checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
		// 1、对注单进行基础校验
		checkOrder(lotteryOrder, betLotteryTimeNum);
		// 2、数据格式校验，万位、千位、百位由至少一位数字组成，十位、个位为减号，每个位置间以英文逗号隔开
		String betNumber = lotteryOrder.getBetDetail();
		String formatRegex = "(\\d{1,10},{1}){3}";
		if (betNumber.matches(formatRegex)) {
			return 1;
		}
		// 3、内容有效性校验，判断内容合法性，同位不允许出现重复号码
		if (!LotteryUtils.checkRepeatNumber(betNumber)) {
			return 1;
		}
		// 4、校验订单金额
		return super.checkAmount(lotteryOrder, LotteryUtils.orderCountSscZhiXuanFu(lotteryOrder.getBetDetail()));
	}

	@Override
	public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
		if (null == lotteryOrder || null == openLotteryTimeNum) {
			return false;
		}
		String openNum = openLotteryTimeNum.getOpenNum();
		if (StringUtils.isBlank(openNum)) {
			return false;
		}
		return LotteryUtils.checkWinSscZhiXuanFu(openNum.substring(0, 5), lotteryOrder.getBetDetail());
	}

	@Override
	public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
		// 如果没有中奖，直接返回金额为0
		if (!checkWin(lotteryOrder, openlotteryTimeNum)) {
			BigDecimal zero = new BigDecimal(0);
			return zero;
		}
		// 投注奖金组、投注倍数、投注模式
		BigDecimal playModeMoney = new BigDecimal(lotteryOrder.getPlayModeMoney());
		BigDecimal betRate = new BigDecimal(lotteryOrder.getBetRate());
		BigDecimal playModeMoneyType = getParamByType(lotteryOrder);
		// 只可能有一注中奖号码
		BigDecimal winCount = new BigDecimal(1);
		// 中奖金额 = 奖金组 * 投注倍数 * 投注模式对应面值 * 中奖注数
		return playModeMoney.multiply(betRate).multiply(playModeMoneyType).multiply(winCount);
	}

	@Override
	public int calBetNum(String bet) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
		public boolean checkAmount(LotteryOrder lotteryOrder) {
			// TODO Auto-generated method stub
			return false;
		}

	},
	/** 时时彩中3直选复式 */
	SSC_ZHONG3_ZHIXUANFU("SSC_ZHONG3_ZHIXUANFU","时时彩中3直选复式"){

	@Override
	public void trend(LotteryTimeNum openLotteryTimeNum) {
		// TODO Auto-generated method stub
	}

	@Override
	public int checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
		// 1、对注单进行基础校验
		checkOrder(lotteryOrder, betLotteryTimeNum);
		// 2、数据格式校验，万位、千位、百位由至少一位数字组成，十位、个位为减号，每个位置间以英文逗号隔开
		String betNumber = lotteryOrder.getBetDetail();
		String formatRegex = "(\\d{1,10},{1}){3}";
		if (betNumber.matches(formatRegex)) {
			return 1;
		}
		// 3、内容有效性校验，判断内容合法性，同位不允许出现重复号码
		if (!LotteryUtils.checkRepeatNumber(betNumber)) {
			return 1;
		}
		// 4、校验订单金额
		return super.checkAmount(lotteryOrder, LotteryUtils.orderCountSscZhiXuanFu(lotteryOrder.getBetDetail()));
	}

	@Override
	public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
		if (null == lotteryOrder || null == openLotteryTimeNum) {
			return false;
		}
		String openNum = openLotteryTimeNum.getOpenNum();
		if (StringUtils.isBlank(openNum)) {
			return false;
		}
		return LotteryUtils.checkWinSscZhiXuanFu(openNum.substring(2, 7), lotteryOrder.getBetDetail());
	}

	@Override
	public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
		// 如果没有中奖，直接返回金额为0
		if (!checkWin(lotteryOrder, openlotteryTimeNum)) {
			BigDecimal zero = new BigDecimal(0);
			return zero;
		}
		// 投注奖金组、投注倍数、投注模式
		BigDecimal playModeMoney = new BigDecimal(lotteryOrder.getPlayModeMoney());
		BigDecimal betRate = new BigDecimal(lotteryOrder.getBetRate());
		BigDecimal playModeMoneyType = getParamByType(lotteryOrder);
		// 只可能有一注中奖号码
		BigDecimal winCount = new BigDecimal(1);
		// 中奖金额 = 奖金组 * 投注倍数 * 投注模式对应面值 * 中奖注数
		return playModeMoney.multiply(betRate).multiply(playModeMoneyType).multiply(winCount);
	}

	@Override
	public int calBetNum(String bet) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
		public boolean checkAmount(LotteryOrder lotteryOrder) {
			// TODO Auto-generated method stub
			return false;
		}

	},
	/** 时时彩后3直选复式 */
	SSC_HOU3_ZHIXUANFU("SSC_HOU3_ZHIXUANFU","时时彩后3直选复式"){

	@Override
	public void trend(LotteryTimeNum openLotteryTimeNum) {
		// TODO Auto-generated method stub
	}

	@Override
	public int checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
		// 1、对注单进行基础校验
		checkOrder(lotteryOrder, betLotteryTimeNum);
		// 2、数据格式校验，万位、千位、百位由至少一位数字组成，十位、个位为减号，每个位置间以英文逗号隔开
		String betNumber = lotteryOrder.getBetDetail();
		String formatRegex = "(\\d{1,10},{1}){3}";
		if (betNumber.matches(formatRegex)) {
			return 1;
		}
		// 3、内容有效性校验，判断内容合法性，同位不允许出现重复号码
		if (!LotteryUtils.checkRepeatNumber(betNumber)) {
			return 1;
		}
		// 4、校验订单金额
		return super.checkAmount(lotteryOrder, LotteryUtils.orderCountSscZhiXuanFu(lotteryOrder.getBetDetail()));
	}

	@Override
	public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
		if (null == lotteryOrder || null == openLotteryTimeNum) {
			return false;
		}
		String openNum = openLotteryTimeNum.getOpenNum();
		if (StringUtils.isBlank(openNum)) {
			return false;
		}
		return LotteryUtils.checkWinSscZhiXuanFu(openNum.substring(4), lotteryOrder.getBetDetail());
	}

	@Override
	public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
		// 如果没有中奖，直接返回金额为0
		if (!checkWin(lotteryOrder, openlotteryTimeNum)) {
			BigDecimal zero = new BigDecimal(0);
			return zero;
		}
		// 投注奖金组、投注倍数、投注模式
		BigDecimal playModeMoney = new BigDecimal(lotteryOrder.getPlayModeMoney());
		BigDecimal betRate = new BigDecimal(lotteryOrder.getBetRate());
		BigDecimal playModeMoneyType = getParamByType(lotteryOrder);
		// 只可能有一注中奖号码
		BigDecimal winCount = new BigDecimal(1);
		// 中奖金额 = 奖金组 * 投注倍数 * 投注模式对应面值 * 中奖注数
		return playModeMoney.multiply(betRate).multiply(playModeMoneyType).multiply(winCount);
	}

	@Override
	public int calBetNum(String bet) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
		public boolean checkAmount(LotteryOrder lotteryOrder) {
			// TODO Auto-generated method stub
			return false;
		}

	},
	/** 时时彩前3组选3 */
	SSC_QIAN3_ZUXUAN3("SSC_QIAN3_ZUXUAN3","时时彩前3组选3"){

	@Override
	public void trend(LotteryTimeNum openLotteryTimeNum) {
		// TODO Auto-generated method stub
	}

	@Override
	public int checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
		// 基础校验，如果不能通过直接校验失败
		if (super.checkOrder(lotteryOrder, betLotteryTimeNum) != 0) {
			return 1;
		}
		// 校验投注号码的有效性,格式校验
		String regexStr = "\\d{1}(,\\d{1}){1,9}";
		String betNum = lotteryOrder.getBetDetail();
		if (StringUtils.isBlank(betNum) || !betNum.matches(regexStr)) {
			return 1;
		}
		// 匹配用户的投注号码是否存在于三星组三大底
		Set<String> betSet = LotteryUtils.ssc3XinZuXuan3(betNum);
		if (betSet.isEmpty()) {
			return 1;
		}
		Set<String> dadi = LotteryUtils.ssc3XinZuXuan3();
		// 只要有一注不在组三范围内，注单校验不通过
		for (String bet : betSet) {
			if (!dadi.contains(bet)) {
				return 1;
			}
		}
		return 0;
	}

	@Override
	public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
		if (null == lotteryOrder || null == openLotteryTimeNum) {
			return false;
		}
		String openNum = openLotteryTimeNum.getOpenNum();
		return LotteryUtils.checkWinSsc3XingZuXuan3(openNum.substring(0, 5), lotteryOrder.getBetDetail());
	}

	@Override
	public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
		// 如果没有中奖，直接返回金额为0
		if (!checkWin(lotteryOrder, openlotteryTimeNum)) {
			BigDecimal zero = new BigDecimal(0);
			return zero;
		}
		// 投注奖金组、投注倍数、投注模式、中奖注数
		BigDecimal playModeMoney = new BigDecimal(lotteryOrder.getPlayModeMoney());
		BigDecimal betRate = new BigDecimal(lotteryOrder.getBetRate());
		BigDecimal playModeMoneyType = getParamByType(lotteryOrder);
		String openNum = openlotteryTimeNum.getOpenNum();
		BigDecimal winCount = new BigDecimal(
				LotteryUtils.winCountSsc3XingZu3(openNum.substring(0, 5), lotteryOrder.getBetDetail()));
		// 中奖金额 = 奖金组 * 投注倍数 * 投注模式对应面值 * 中奖注数
		return playModeMoney.multiply(betRate).multiply(playModeMoneyType).multiply(winCount);
	}

	@Override
	public int calBetNum(String bet) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
		public boolean checkAmount(LotteryOrder lotteryOrder) {
			// TODO Auto-generated method stub
			return false;
		}

	},
	/** 时时彩中3组选3 */
	SSC_ZHONG3_ZUXUAN3("SSC_ZHONG3_ZUXUAN3","时时彩中3组选3"){

	@Override
	public void trend(LotteryTimeNum openLotteryTimeNum) {
		// TODO Auto-generated method stub
	}

	@Override
	public int checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
		// 基础校验，如果不能通过直接校验失败
		if (super.checkOrder(lotteryOrder, betLotteryTimeNum) != 0) {
			return 1;
		}
		// 校验投注号码的有效性,格式校验
		String regexStr = "\\d{1}(,\\d{1}){1,9}";
		String betNum = lotteryOrder.getBetDetail();
		if (StringUtils.isBlank(betNum) || !betNum.matches(regexStr)) {
			return 1;
		}
		// 匹配用户的投注号码是否存在于三星组三大底
		Set<String> betSet = LotteryUtils.ssc3XinZuXuan3(betNum);
		if (betSet.isEmpty()) {
			return 1;
		}
		Set<String> dadi = LotteryUtils.ssc3XinZuXuan3();
		// 只要有一注不在组三范围内，注单校验不通过
		for (String bet : betSet) {
			if (!dadi.contains(bet)) {
				return 1;
			}
		}
		return 0;
	}

	@Override
	public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
		if (null == lotteryOrder || null == openLotteryTimeNum) {
			return false;
		}
		String openNum = openLotteryTimeNum.getOpenNum();
		return LotteryUtils.checkWinSsc3XingZuXuan3(openNum.substring(2, 7), lotteryOrder.getBetDetail());
	}

	@Override
	public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
		// 如果没有中奖，直接返回金额为0
		if (!checkWin(lotteryOrder, openlotteryTimeNum)) {
			BigDecimal zero = new BigDecimal(0);
			return zero;
		}
		// 投注奖金组、投注倍数、投注模式、中奖注数
		BigDecimal playModeMoney = new BigDecimal(lotteryOrder.getPlayModeMoney());
		BigDecimal betRate = new BigDecimal(lotteryOrder.getBetRate());
		BigDecimal playModeMoneyType = getParamByType(lotteryOrder);
		String openNum = openlotteryTimeNum.getOpenNum();
		BigDecimal winCount = new BigDecimal(
				LotteryUtils.winCountSsc3XingZu3(openNum.substring(0, 5), lotteryOrder.getBetDetail()));
		// 中奖金额 = 奖金组 * 投注倍数 * 投注模式对应面值 * 中奖注数
		return playModeMoney.multiply(betRate).multiply(playModeMoneyType).multiply(winCount);
	}

	@Override
	public int calBetNum(String bet) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
		public boolean checkAmount(LotteryOrder lotteryOrder) {
			// TODO Auto-generated method stub
			return false;
		}

	},
	/** 时时彩后3组选3 */
	SSC_HOU3_ZUXUAN3("SSC_HOU3_ZUXUAN3","时时彩后3组选3"){

	@Override
	public void trend(LotteryTimeNum openLotteryTimeNum) {
		// TODO Auto-generated method stub
	}

	@Override
	public int checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
		// 基础校验，如果不能通过直接校验失败
		if (super.checkOrder(lotteryOrder, betLotteryTimeNum) != 0) {
			return 1;
		}
		// 校验投注号码的有效性,格式校验
		String regexStr = "\\d{1}(,\\d{1}){1,9}";
		String betNum = lotteryOrder.getBetDetail();
		if (StringUtils.isBlank(betNum) || !betNum.matches(regexStr)) {
			return 1;
		}
		// 匹配用户的投注号码是否存在于三星组三大底
		Set<String> betSet = LotteryUtils.ssc3XinZuXuan3(betNum);
		if (betSet.isEmpty()) {
			return 1;
		}
		Set<String> dadi = LotteryUtils.ssc3XinZuXuan3();
		// 只要有一注不在组三范围内，注单校验不通过
		for (String bet : betSet) {
			if (!dadi.contains(bet)) {
				return 1;
			}
		}
		return 0;
	}

	@Override
	public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
		if (null == lotteryOrder || null == openLotteryTimeNum) {
			return false;
		}
		String openNum = openLotteryTimeNum.getOpenNum();
		return LotteryUtils.checkWinSsc3XingZuXuan3(openNum.substring(4), lotteryOrder.getBetDetail());
	}

	@Override
	public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
		// 如果没有中奖，直接返回金额为0
		if (!checkWin(lotteryOrder, openlotteryTimeNum)) {
			BigDecimal zero = new BigDecimal(0);
			return zero;
		}
		// 投注奖金组、投注倍数、投注模式、中奖注数
		BigDecimal playModeMoney = new BigDecimal(lotteryOrder.getPlayModeMoney());
		BigDecimal betRate = new BigDecimal(lotteryOrder.getBetRate());
		BigDecimal playModeMoneyType = getParamByType(lotteryOrder);
		String openNum = openlotteryTimeNum.getOpenNum();
		BigDecimal winCount = new BigDecimal(
				LotteryUtils.winCountSsc3XingZu3(openNum.substring(0, 5), lotteryOrder.getBetDetail()));
		// 中奖金额 = 奖金组 * 投注倍数 * 投注模式对应面值 * 中奖注数
		return playModeMoney.multiply(betRate).multiply(playModeMoneyType).multiply(winCount);
	}

	@Override
	public int calBetNum(String bet) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
		public boolean checkAmount(LotteryOrder lotteryOrder) {
			// TODO Auto-generated method stub
			return false;
		}

	},

	// /** 时时彩前3组选6 */
	SSC_QIAN3_ZUXUAN6("SSC_QIAN3_ZUXUAN6","时时彩前3组选6"){

	@Override
	public void trend(LotteryTimeNum openLotteryTimeNum) {
		// TODO Auto-generated method stub

	}

	@Override
	public int checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
		// 对注单进行基础校验
		if (super.checkOrder(lotteryOrder, betLotteryTimeNum) != 0) {
			return 1;
		}
		// 正则验证投注号码,必须是0-9的数字以逗号分隔
		String regex = "^\\d+(,\\d{0,2})*$";
		if (!lotteryOrder.getBetDetail().matches(regex)) {
			return 1;
		}
		// 校验订单金额
		return super.checkAmount(lotteryOrder, LotteryUtils.ssc3XinBetCount(lotteryOrder));
	}

	@Override
	public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
		if (null == lotteryOrder || null == openLotteryTimeNum) {
			return false;
		}
		return LotteryUtils.ssc3XinZuxuan6(openLotteryTimeNum.getOpenNum().trim().substring(0, 5),
				lotteryOrder.getBetDetail());
	}

	@Override
	public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
		// 如果没有中奖，直接返回金额为0
		if (!checkWin(lotteryOrder, openlotteryTimeNum)) {
			return BigDecimal.ZERO;
		}
		// 投注奖金组、投注倍数、投注模式
		BigDecimal playModeMoney = new BigDecimal(lotteryOrder.getPlayModeMoney());
		BigDecimal betRate = new BigDecimal(lotteryOrder.getBetRate());
		BigDecimal playModeMoneyType = getParamByType(lotteryOrder);
		// 中奖金额 = 奖金组 * 投注倍数 * 投注模式对应面值
		return playModeMoney.multiply(betRate).multiply(playModeMoneyType);
	}

	@Override
	public int calBetNum(String bet) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
		public boolean checkAmount(LotteryOrder lotteryOrder) {
			// TODO Auto-generated method stub
			return false;
		}

	},SSC_QIAN3_HUNHEZUXUN("SSC_QIAN3_HUNHEZUXUN","时时彩前3混合组选"){

	@Override
	public void trend(LotteryTimeNum openLotteryTimeNum) {

	}

	@Override
	public int checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {

		return 1;
	}

	@Override
	public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
		if (null == lotteryOrder || null == openLotteryTimeNum) {
			return false;
		}
		return LotteryUtils.ssc3XinHunHeZuXuan(openLotteryTimeNum.getOpenNum().substring(0, 5),
				lotteryOrder.getBetDetail());
	}

	@Override
	public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
		// 如果没有中奖，直接返回金额为0
		if (!checkWin(lotteryOrder, openlotteryTimeNum)) {
			return BigDecimal.ZERO;
		}
		// 投注奖金组、投注倍数、投注模式
		BigDecimal playModeMoney = new BigDecimal(lotteryOrder.getPlayModeMoney());
		BigDecimal betRate = new BigDecimal(lotteryOrder.getBetRate());
		BigDecimal playModeMoneyType = getParamByType(lotteryOrder);
		BigDecimal winCount = new BigDecimal(1);
		// 中奖金额 = 奖金组 * 投注倍数 * 投注模式对应面值
		return playModeMoney.multiply(betRate).multiply(playModeMoneyType).multiply(winCount);
	}

	@Override
	public int calBetNum(String bet) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
		public boolean checkAmount(LotteryOrder lotteryOrder) {
			// TODO Auto-generated method stub
			return false;
		}

	},
	/** 时时彩前3组选和值 */
	SSC_QIAN3_ZUXUANHEZHI("SSC_QIAN3_ZUXUAN3","时时彩前3组选和值"){

	@Override
	public void trend(LotteryTimeNum openLotteryTimeNum) {
		// TODO Auto-generated method stub
	}

	@Override
	public int checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
		if (null == lotteryOrder || null == openLotteryTimeNum) {
			return false;
		}
		return LotteryUtils.ssc3XinZuxuanHeZhi(
				openLotteryTimeNum.getOpenNum().substring(openLotteryTimeNum.getOpenNum().length() - 5),
				lotteryOrder.getBetDetail());
	}

	@Override
	public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
		// 如果没有中奖，直接返回金额为0
		if (!checkWin(lotteryOrder, openlotteryTimeNum)) {
			return BigDecimal.ZERO;
		}
		// 投注奖金组、投注倍数、投注模式、中奖注数
		BigDecimal playModeMoney = new BigDecimal(lotteryOrder.getPlayModeMoney());
		BigDecimal betRate = new BigDecimal(lotteryOrder.getBetRate());
		BigDecimal playModeMoneyType = getParamByType(lotteryOrder);
		BigDecimal winCount = new BigDecimal(1);
		return playModeMoney.multiply(betRate).multiply(playModeMoneyType).multiply(winCount);
	}

	@Override
	public int calBetNum(String bet) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
		public boolean checkAmount(LotteryOrder lotteryOrder) {
			// TODO Auto-generated method stub
			return false;
		}

	},
	/** 时时彩前3和值尾数 */
	SSC_QIAN3_HEZHIWEISHU("SSC_QIAN3_HEZHIWEISHU","时时彩前3和值尾数"){

	@Override
	public void trend(LotteryTimeNum openLotteryTimeNum) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
		if (null == lotteryOrder || null == openLotteryTimeNum) {
			return false;
		}
		return LotteryUtils.ssc3XinHeZhiWeiShu(
				openLotteryTimeNum.getOpenNum().substring(openLotteryTimeNum.getOpenNum().length() - 5),
				lotteryOrder.getBetDetail());
	}

	@Override
	public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
		// 如果没有中奖，直接返回金额为0
		if (!checkWin(lotteryOrder, openlotteryTimeNum)) {
			return BigDecimal.ZERO;
		}
		// 投注奖金组、投注倍数、投注模式、中奖注数
		BigDecimal playModeMoney = new BigDecimal(lotteryOrder.getPlayModeMoney());
		BigDecimal betRate = new BigDecimal(lotteryOrder.getBetRate());
		BigDecimal playModeMoneyType = getParamByType(lotteryOrder);
		BigDecimal winCount = new BigDecimal(1);
		return playModeMoney.multiply(betRate).multiply(playModeMoneyType).multiply(winCount);
	}

	@Override
	public int calBetNum(String bet) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
		public boolean checkAmount(LotteryOrder lotteryOrder) {
			// TODO Auto-generated method stub
			return false;
		}

	},

	/** 时时彩前3 豹子 */
	SSC_QIAN3_BAOZI("SSC_QIAN3_ZUXUAN3","时时彩前3豹子"){

	@Override
	public void trend(LotteryTimeNum openLotteryTimeNum) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
		if (null == lotteryOrder || null == openLotteryTimeNum) {
			return false;
		}
		return LotteryUtils.ssc3XinHeZhiWeiShu(
				openLotteryTimeNum.getOpenNum().substring(openLotteryTimeNum.getOpenNum().length() - 5),
				lotteryOrder.getBetDetail());
	}

	@Override
	public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
		// 如果没有中奖，直接返回金额为0
		if (!checkWin(lotteryOrder, openlotteryTimeNum)) {
			return BigDecimal.ZERO;
		}
		// 投注奖金组、投注倍数、投注模式、中奖注数
		BigDecimal playModeMoney = new BigDecimal(lotteryOrder.getPlayModeMoney());
		BigDecimal betRate = new BigDecimal(lotteryOrder.getBetRate());
		BigDecimal playModeMoneyType = getParamByType(lotteryOrder);
		BigDecimal winCount = new BigDecimal(1);
		return playModeMoney.multiply(betRate).multiply(playModeMoneyType).multiply(winCount);
	}

	@Override
	public int calBetNum(String bet) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
		public boolean checkAmount(LotteryOrder lotteryOrder) {
			// TODO Auto-generated method stub
			return false;
		}

	},
	/** 时时彩前3直选和值 */
	SSC_QIAN3_ZHIXUANHEZHI("SSC_HOU3_ZHIXUAN","时时彩前3直选和值"){

	@Override
	public void trend(LotteryTimeNum openLotteryTimeNum) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
		if (null == lotteryOrder || null == openLotteryTimeNum) {
			return false;
		}
		return LotteryUtils.ssc3XinZhiXuanHeZhi(
				openLotteryTimeNum.getOpenNum().substring(openLotteryTimeNum.getOpenNum().length() - 5),
				lotteryOrder.getBetDetail());
	}

	@Override
	public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
		// 如果没有中奖，直接返回金额为0
		if (!checkWin(lotteryOrder, openlotteryTimeNum)) {
			return BigDecimal.ZERO;
		}
		// 投注奖金组、投注倍数、投注模式、中奖注数
		BigDecimal playModeMoney = new BigDecimal(lotteryOrder.getPlayModeMoney());
		BigDecimal betRate = new BigDecimal(lotteryOrder.getBetRate());
		BigDecimal playModeMoneyType = getParamByType(lotteryOrder);
		BigDecimal winCount = new BigDecimal(1);
		return playModeMoney.multiply(betRate).multiply(playModeMoneyType).multiply(winCount);
	}

	@Override
	public int calBetNum(String bet) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
		public boolean checkAmount(LotteryOrder lotteryOrder) {
			// TODO Auto-generated method stub
			return false;
		}

	},

	// --------------------------------------------------

	/** 5星直选复式 */
	SSC_5_ZHIXUANFUSHI("SSC_5_ZHIXUANFUSHI","5星直选复式"){

	@Override
	public void trend(LotteryTimeNum openLotteryTimeNum) {
		// TODO Auto-generated method stub
	}

	@Override
	public int checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
		// 1、对注单进行基础校验（注单期号、投注时间有效性、返水范围校验）
		if (super.checkOrder(lotteryOrder, betLotteryTimeNum) != 0) {
			return 1;
		}
		// 2、数据格式校验，
		// 个十百千万位各至少选一个号码，组成一注，以逗号分割。不同位置可以重复
		String betNumber = lotteryOrder.getBetDetail();
		String formatRegex = "^\\d{0,9},\\d{0,9},\\d{0,9},\\d{0,9},\\d{0,9}$";

		if (!betNumber.matches(formatRegex)) {
			return 1;
		}

		String[] arrSubBet = betNumber.split(",");
		for (int j = 0; j < arrSubBet.length; j++) {
			CheckString.hasSameLetter(arrSubBet[j]);
		}

		// 4 校验投注金额 amount = betno * 2 * rate * moneytype
		boolean ret = checkAmount(lotteryOrder);
		if (!ret)
			return 1;

		return 0;
	}

	@Override
	public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int calBetNum(String bet) {
		// TODO Auto-generated method stub

		String[] betNumList = bet.split(",");
		int count = 0;
		for (String string : betNumList) {
			count *= string.length();
		}

		return count;

	}

	@Override
		public boolean checkAmount(LotteryOrder lotteryOrder) {
			// TODO Auto-generated method stub

			// 校验投注金额 amount = betno * 2 * rate * moneytype
			int betNum = calBetNum(lotteryOrder.getBetDetail());
			return super.chkAmount(lotteryOrder, betNum);

			// int betNum = calBetNum(lotteryOrder.getBetDetail());
			//
			// BigDecimal moneyType =
			// MoneyType.getMoneyType(lotteryOrder.getPlayModeMoneyType());
			// BigDecimal rate = new BigDecimal(lotteryOrder.getBetRate());
			//
			// BigDecimal amount = rate.multiply(moneyType).multiply(new BigDecimal(betNum *
			// 2));
			//
			// return amount.equals(lotteryOrder.getBetAmount());
		}

	},

	/** 5星直选单式 */
	SSC_5_ZHIXUANDANSHI("SSC_5_ZHIXUANDANSHI","5星直选单式"){

	@Override
	public void trend(LotteryTimeNum openLotteryTimeNum) {
		// TODO Auto-generated method stub
	}

	@Override
	public int checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
		// 1、对注单进行基础校验（注单期号、投注时间有效性、返水范围校验）
		if (super.checkOrder(lotteryOrder, betLotteryTimeNum) != 0) {
			return 1;
		}
		// 2、数据格式校验，
		// 个十百千万位各至少选一个号码，组成一注，以逗号分割。不同位置可以重复
		String betNumber = lotteryOrder.getBetDetail();
		String formatRegex = "^\\d{5}(,\\d{5})*$";

		if (!betNumber.matches(formatRegex)) {
			return 1;
		}

		String[] arrSubBet = betNumber.split(",");
		for (int j = 0; j < arrSubBet.length; j++) {
			CheckString.hasSameLetter(arrSubBet[j]);
		}

		// 4 校验投注金额 amount = betno * 2 * rate * moneytype
		boolean ret = checkAmount(lotteryOrder);
		if (!ret)
			return 1;
		return 0;
	}

	@Override
	public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int calBetNum(String bet) {
		// TODO Auto-generated method stub

		String[] betNumList = bet.split(",");

		return betNumList.length;

	}

	@Override
		public boolean checkAmount(LotteryOrder lotteryOrder) {
			// TODO Auto-generated method stub

			// 校验投注金额 amount = betno * 2 * rate * moneytype
			int betNum = calBetNum(lotteryOrder.getBetDetail());
			return super.chkAmount(lotteryOrder, betNum);

		}

	},

	/** 5星直选和值 */
	SSC_5_ZHIXUANHEZHI("SSC_5_ZHIXUANHEZHI","5星直选和值"){

	@Override
	public void trend(LotteryTimeNum openLotteryTimeNum) {
		// TODO Auto-generated method stub
	}

	@Override
	public int checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
		// 1、对注单进行基础校验（注单期号、投注时间有效性、返水范围校验）
		if (super.checkOrder(lotteryOrder, betLotteryTimeNum) != 0) {
			return 1;
		}
		// 2、数据格式校验，
		// 个十百千万位各至少选一个号码，组成一注，以逗号分割。不同位置可以重复
		String betNumber = lotteryOrder.getBetDetail();
		String formatRegex = "^\\d{1,}(,\\d{1,})*$";

		if (!betNumber.matches(formatRegex)) {
			return 1;
		}

		String[] arrSubBet = betNumber.split(",");

		List<String> lsSubBets = Arrays.asList(arrSubBet);

		if (CheckString.hasSameLetterStream(lsSubBets))
			return 1;

		for (int j = 0; j < arrSubBet.length; j++) {
			CheckString.hasSameLetter(arrSubBet[j]);
		}

		// 4 校验投注金额 amount = betno * 2 * rate * moneytype
		boolean ret = checkAmount(lotteryOrder);
		if (!ret)
			return 1;
		return 0;
	}

	@Override
	public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int calBetNum(String bet) {
		// TODO Auto-generated method stub

		String[] betNumList = bet.split(",");

		return betNumList.length;

	}

	@Override
	public boolean checkAmount(LotteryOrder lotteryOrder) {
		// TODO Auto-generated method stub

		// 校验投注金额 amount = betno * 2 * rate * moneytype
		int betNum = calBetNum(lotteryOrder.getBetDetail());
		return super.chkAmount(lotteryOrder, betNum);

	}},

	//
	// /** 时时彩前3组选3 */
	// SSC_QIAN3_ZUXUAN3("SSC_QIAN3_ZUXUAN3", "时时彩前3组选3") {},
	//
	// /** 时时彩中3直选 */
	// SSC_ZHONG3_ZHIXUAN("SSC_ZHONG3_ZHIXUAN", "时时彩中3直选 ") {},
	//
	// /** 时时彩中3组选6 */
	// SSC_ZHONG3_ZUXUAN6("SSC_ZHONG3_ZUXUAN6", "时时彩中3组选6") {},
	//
	// /** 时时彩中3组选3 */
	// SSC_ZHONG3_ZUXUAN3("SSC_ZHONG3_ZUXUAN3", "时时彩中3组选3") {},
	//
	// /** 时时彩后3直选 */
	// SSC_HOU3_ZHIXUAN("SSC_HOU3_ZHIXUAN", "时时彩后3直选") {},
	//
	// /** 时时彩后3组6 */
	// SSC_HOU3_ZUXUAN6("SSC_HOU3_ZUXUAN6", "时时彩后3组6") {},
	//
	// /** 时时彩后3组3 */
	// SSC_HOU3_ZUXUAN3("SSC_HOU3_ZUXUAN3", "时时彩后3组3") {}
	;

	/** 玩法代码 */
	private String playCode;
	/** 玩法名称 */
	private String playName;

	private SscService(String playCode, String playName) {
		this.playCode = playCode;
		this.playName = playName;
	}

	public String getPlayCode() {
		return playCode;
	}

	public void setPlayCode(String playCode) {
		this.playCode = playCode;
	}

	public String getPlayName() {
		return playName;
	}

	public void setPlayName(String playName) {
		this.playName = playName;
	}

	/**
	 * 根据不同的玩法模式，返回对应的计算数值，默认为元（1）
	 * 
	 * @param lotteryOrder
	 *            包含玩法模式数据的实体对象
	 * @return 返回对应奖金计算参数
	 * @author Terry
	 */
	private static BigDecimal getParamByType(LotteryOrder lotteryOrder) {
		BigDecimal param = null;
		switch (lotteryOrder.getPlayModeMoneyType()) {
		case "0":
			param = new BigDecimal("1");
			break;
		case "1":
			param = new BigDecimal("0.1");
			break;
		case "2":
			param = new BigDecimal("0.01");
			break;
		default:
			param = new BigDecimal("1");
			break;
		}
		return param;
	}

	/**
	 * 注单基础校验，校验期号、有效性、金额合法性
	 * 
	 * @param lotteryOrder
	 *            注单信息
	 * @param lotteryTimeNum
	 *            当前期开奖号码
	 * @return 基本校验不通过返回false，通过返回true
	 * @author Terry
	 */
	@Override
	public int checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
		// 如果传入参数为null，返回false，校验失败
		if (null == lotteryOrder || null == betLotteryTimeNum) {
			return GameError.errCodeInvalid;
		}
		// 期号检查,投注期号与当前期号作对比，如果不一致，返回false，校验失败
		String currentIssueNo = lotteryOrder.getBetIssueNo();
		if (StringUtils.isBlank(currentIssueNo) || !currentIssueNo.equals(betLotteryTimeNum.getLotteryIssueNo())) {
			return GameError.errCodeInvalid;
		}
		// 投注有效性检查，当前时间是否在当前有效投注时间段内，如果不在，返回false，校验失败
		Long currentDate = System.currentTimeMillis();
		Long startDate = betLotteryTimeNum.getBetStartDate().getTime();
		Long endDate = betLotteryTimeNum.getBetHaltDate().getTime();
		if (currentDate <= startDate || currentDate >= endDate) {
			return GameError.errCodeInvalid;
		}

		// TODO 返水范围校验 等待freeman提供接口，获取用户的返回范围

		return 0;
	}

	/**
	 * 校验订单金额
	 * 
	 * @param lotteryOrder
	 *            订单对象
	 * @param betCount
	 *            投注注数
	 * @return 通过注单注数、单注金额、投注倍数预算投注金额，是否与订单金额保持一致，返回校验结果，通过返回true
	 * @author Terry
	 */
	private int checkAmount(LotteryOrder lotteryOrder, int betCount) {
		// 获取投注倍数、单注金额
		Integer betRate = lotteryOrder.getBetRate();
		BigDecimal price = new BigDecimal(2);
		// 注单金额 = 单注金额 * 投注注数 * 投注倍数
		BigDecimal estimatedAmount = price.multiply(new BigDecimal(betCount)).multiply(new BigDecimal(betRate));
		// 如果计算出的投注金额与注单金额不一致，校验失败，返回false
		if (estimatedAmount.equals(lotteryOrder.getBetAmount())) {
			return 0;
		}
		return 1;
	}

	/**
	 * 校验投注金额
	 * 
	 * @param lotteryOrder
	 * @param betNum
	 * @return
	 */
	public boolean chkAmount(LotteryOrder lotteryOrder, int betNum) {
		// 校验投注金额 amount = betno * 2 * rate * moneytype
		BigDecimal moneyType = MoneyType.getMoneyType(lotteryOrder.getPlayModeMoneyType());
		BigDecimal rate = new BigDecimal(lotteryOrder.getBetRate());
		BigDecimal amount = rate.multiply(moneyType).multiply(new BigDecimal(betNum * 2));
		return amount.equals(lotteryOrder.getBetAmount());
	}

	@Override
	public void trend(LotteryTimeNum openLotteryTimeNum) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int calBetNum(String bet) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean checkAmount(LotteryOrder lotteryOrder) {
		// TODO Auto-generated method stub
		return false;
	}

}
