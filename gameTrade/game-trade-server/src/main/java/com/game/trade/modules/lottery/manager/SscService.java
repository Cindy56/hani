package com.game.trade.modules.lottery.manager;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.game.common.mapper.JsonMapper;
import com.game.common.utils.SpringContextHolder;
import com.game.common.utils.StringUtils;
import com.game.modules.lottery.entity.GameErrorEnum;
import com.game.modules.lottery.entity.LotteryPlayConfig;
import com.game.modules.lottery.entity.LotteryTimeNum;
import com.game.modules.lottery.entity.ResponseMsgData;
import com.game.modules.lottery.entity.lotteryPlayMult;
import com.game.modules.member.entity.MemberPlayConfig;
import com.game.modules.member.service.MemberPlayConfigService;
import com.game.modules.order.entity.LotteryOrder;
import com.game.trade.modules.lottery.MoneyTypeDecimal;
import com.game.trade.modules.lottery.service.LotteryPlayConfigServiceImpl;
import com.game.trade.util.CheckString;

/**
 * 时时彩玩法 将通用的重构出来，在子枚举类里通过supper调用
 * 
 * @author Administrator
 */
public enum SscService implements LotteryService {

	/** 时时彩1星定位 **/
	SSC_1XING_DINGWEI("SSC_1XING_DINGWEI", "时时彩1星定位") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {
			// TODO Auto-generated method stub
		}

		@Override
		public ResponseMsgData checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
			// 1、对注单进行基础校验（注单期号、投注时间有效性、返水范围校验）
			ResponseMsgData responseMsgData = super.checkOrder(lotteryOrder, betLotteryTimeNum);
			if (!responseMsgData.getIsSucceed()) {
				return responseMsgData;
			}
			// 2、数据格式校验，投注内容必须是数字或减号以英文逗号或空格隔开的字符串，且至少包含一个数字
			String betNumber = lotteryOrder.getBetDetail();
			String formatRegex = "((\\d{1,10}|-){1},{1}){4}(\\d{1,10}|-){1}";
			String containRegex = ".*\\d.*";
			if (betNumber.matches(formatRegex) && betNumber.matches(containRegex)) {
				return ResponseMsgData.ok();
			}
			// 3、内容有效性校验，判断内容合法性，同位不允许出现重复号码
			if (!LotteryUtils.checkRepeatNumber(betNumber)) {
				return ResponseMsgData.ok();
			}
			// 4、校验订单金额
			int amount = LotteryUtils.orderCount1XingZhiXuan(lotteryOrder.getBetDetail());
			// return super.checkAmount(lotteryOrder, amount);
			return ResponseMsgData.ok();
		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if (null == lotteryOrder || null == openLotteryTimeNum) {
				return false;
			}
			return LotteryUtils.checkWinSsc1XingDingWei(openLotteryTimeNum.getOpenNum(), lotteryOrder.getBetDetail());
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			if (null == lotteryOrder || null == openlotteryTimeNum) {
				return BigDecimal.ZERO;
			}

			// 判断一下是否中奖，未中奖直接返回金额为0
			int winCount = LotteryUtils.winCountSsc1XinDingWei(openlotteryTimeNum.getOpenNum(),
					lotteryOrder.getBetDetail());
			if (winCount == 0) {
				return BigDecimal.ZERO;
			}

			// 投注奖金组、投注倍数、投注模式、中奖注数
			BigDecimal playModeMoney = new BigDecimal(lotteryOrder.getPlayModeMoney());
			BigDecimal betRate = new BigDecimal(lotteryOrder.getBetRate());
			BigDecimal playModeMoneyType = getParamByType(lotteryOrder);

			// 中奖金额 = 奖金组 * 投注倍数 * 投注模式对应面值 * 中奖注数
			return playModeMoney.multiply(betRate).multiply(playModeMoneyType).multiply(new BigDecimal(winCount));
		}
	},

	/** 时时彩后2大小单双 */
	SSC_HOU2_DAXIAODANSHUANG("SSC_HOU2_DAXIAODANSHUANG", "时时彩后2大小单双") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if (null == lotteryOrder || null == openLotteryTimeNum) {
				return false;
			}
			return LotteryUtils.checkWinSscDaXiaoDanShuang(openLotteryTimeNum.getOpenNum().substring(6),
					lotteryOrder.getBetDetail());
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			return super.calculateOrderBonus(lotteryOrder, openlotteryTimeNum);
		}
	},

	/** 时时彩前2直选单式 */
	SSC_QIAN2_ZHIXUANDAN("SSC_QIAN2_ZHIXUANDAN", "时时彩前2直选单式") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {

		}

		@Override
		public ResponseMsgData checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {

			return ResponseMsgData.ok();
		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if (null == lotteryOrder || null == openLotteryTimeNum) {
				return false;
			}
			return LotteryUtils.checkWinSscZhiXuanDan(openLotteryTimeNum.getOpenNum().substring(0, 3),
					lotteryOrder.getBetDetail());
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			return super.calculateOrderBonus(lotteryOrder, openlotteryTimeNum);
		}
	},
	/** 时时彩后2直选单式 */
	SSC_HOU2_ZHIXUANDAN("SSC_HOU2_ZHIXUANDAN", "时时彩后2直选单式") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {

		}

		@Override
		public ResponseMsgData checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {

			return ResponseMsgData.ok();
		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if (null == lotteryOrder || null == openLotteryTimeNum) {
				return false;
			}
			return LotteryUtils.checkWinSscZhiXuanDan(openLotteryTimeNum.getOpenNum().substring(6),
					lotteryOrder.getBetDetail());
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			return super.calculateOrderBonus(lotteryOrder, openlotteryTimeNum);
		}
	},

	/** 时时彩前2直选复式 */
	SSC_QIAN2_ZHIXUANFU("SSC_QIAN2_ZHIXUANFU", "时时彩前2直选复式") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {
			// TODO Auto-generated method stub
		}

		@Override
		public ResponseMsgData checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
			// 1、对注单进行基础校验（注单期号、投注时间有效性、返水范围校验）
			/*if (super.checkOrder(lotteryOrder, betLotteryTimeNum) != 0) {
				return 1;
			}*/
			// TODO 2、数据格式校验
			// TODO 3、内容有效性校验
			// 4、校验订单金额
			/*return super.checkAmount(lotteryOrder,
					LotteryUtils.orderCountSsc2XingZuXuanFu(lotteryOrder.getBetDetail()));*/
			
			return ResponseMsgData.ok();
		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if (null == lotteryOrder || null == openLotteryTimeNum) {
				return false;
			}
			return LotteryUtils.checkWinSscZhiXuanFu(openLotteryTimeNum.getOpenNum().substring(0, 3),
					lotteryOrder.getBetDetail());
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			return super.calculateOrderBonus(lotteryOrder, openlotteryTimeNum);
		}
	},
	/** 时时彩后2直选复式 */
	SSC_HOU2_ZHIXUANFU("SSC_HOU2_ZHIXUANFU", "时时彩后2直选复式") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {
			// TODO Auto-generated method stub
		}

		@Override
		public ResponseMsgData checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
			/*// 1、对注单进行基础校验（注单期号、投注时间有效性、返水范围校验）
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
			return super.checkAmount(lotteryOrder, LotteryUtils.orderCountSscZhiXuanFu(lotteryOrder.getBetDetail()));*/
			return ResponseMsgData.ok();
		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if (null == lotteryOrder || null == openLotteryTimeNum) {
				return false;
			}
			return LotteryUtils.checkWinSscZhiXuanFu(openLotteryTimeNum.getOpenNum().substring(6),
					lotteryOrder.getBetDetail());
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			return super.calculateOrderBonus(lotteryOrder, openlotteryTimeNum);
		}
	},

	/** 时时彩前2直选和值 */
	SSC_QIAN2_ZHIXUANHE("SSC_QIAN2_ZHIXUANHE", "时时彩前2直选和值") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {
			// TODO Auto-generated method stub
		}

		@Override
		public ResponseMsgData checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
			return super.checkOrder(lotteryOrder, betLotteryTimeNum);
		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if (null == lotteryOrder || null == openLotteryTimeNum) {
				return false;
			}
			return LotteryUtils.checkWinSscZhiXuanHe(openLotteryTimeNum.getOpenNum().substring(0, 3),
					lotteryOrder.getBetDetail());
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			if (!checkWin(lotteryOrder, openlotteryTimeNum)) {
				return BigDecimal.ZERO;
			}
			return super.calculateOrderBounsHe(lotteryOrder, openlotteryTimeNum.getOpenNum().substring(0, 3));
		}
	},
	/** 时时彩后2直选和值 */
	SSC_HOU2_ZHIXUANHE("SSC_HOU2_ZHIXUANHE", "时时彩后2直选和值") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {
			// TODO Auto-generated method stub
		}

		@Override
		public ResponseMsgData checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
			return super.checkOrder(lotteryOrder, betLotteryTimeNum);
		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if (null == lotteryOrder || null == openLotteryTimeNum) {
				return false;
			}
			return LotteryUtils.checkWinSscZhiXuanHe(openLotteryTimeNum.getOpenNum().substring(6),
					lotteryOrder.getBetDetail());
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			if (!checkWin(lotteryOrder, openlotteryTimeNum)) {
				return BigDecimal.ZERO;
			}
			return super.calculateOrderBounsHe(lotteryOrder, openlotteryTimeNum.getOpenNum().substring(6));
		}
	},

	/** 时时彩前2组选复式 */
	SSC_QIAN2_ZUXUANFU("SSC_QIAN2_ZUXUANFU", "时时彩前2组选复式") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {
			// TODO Auto-generated method stub
		}

		@Override
		public ResponseMsgData checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
			/*// 1、对注单进行基础校验（注单期号、投注时间有效性、返水范围校验）
			if (super.checkOrder(lotteryOrder, betLotteryTimeNum) != 0) {
				return 1;
			}
			// TODO 2、数据格式校验
			// TODO 3、内容有效性校验
			// 4、校验订单金额
			return super.checkAmount(lotteryOrder,
					LotteryUtils.orderCountSsc2XingZuXuanFu(lotteryOrder.getBetDetail()));*/
			return ResponseMsgData.ok();
		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if (null == lotteryOrder || null == openLotteryTimeNum) {
				return false;
			}
			return LotteryUtils.checkWinSsc2XingZuXuanFu(openLotteryTimeNum.getOpenNum().substring(0, 3),
					lotteryOrder.getBetDetail());
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			return super.calculateOrderBonus(lotteryOrder, openlotteryTimeNum);
		}
	},
	/** 时时彩后2组选复式 */
	SSC_HOU2_ZUXUANFU("SSC_HOU2_ZUXUANFU", "时时彩后2组选复式") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {
			// TODO Auto-generated method stub
		}

		@Override
		public ResponseMsgData checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
			/*// 1、对注单进行基础校验（注单期号、投注时间有效性、返水范围校验）
			if (super.checkOrder(lotteryOrder, betLotteryTimeNum) != 0) {
				return 1;
			}
			// TODO 2、数据格式校验
			// TODO 3、内容有效性校验
			// 4、校验订单金额
			return super.checkAmount(lotteryOrder,
					LotteryUtils.orderCountSsc2XingZuXuanFu(lotteryOrder.getBetDetail()));*/
			
			return ResponseMsgData.ok();
		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if (null == lotteryOrder || null == openLotteryTimeNum) {
				return false;
			}
			return LotteryUtils.checkWinSsc2XingZuXuanFu(openLotteryTimeNum.getOpenNum().substring(6),
					lotteryOrder.getBetDetail());
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			return super.calculateOrderBonus(lotteryOrder, openlotteryTimeNum);
		}
	},

	/** 时时彩前3直选单式 */
	SSC_QIAN3_ZHIXUANDAN("SSC_QIAN3_ZHIXUANDAN", "时时彩前3直选单式") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {
			// TODO Auto-generated method stub
		}

		@Override
		public ResponseMsgData checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
/*			// 1、对注单进行基础校验（注单期号、投注时间有效性、返水范围校验）
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
			return super.checkAmount(lotteryOrder, LotteryUtils.orderCountSscZhiXuanDan(lotteryOrder.getBetDetail()));*/
			return ResponseMsgData.ok();
		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if (null == lotteryOrder || null == openLotteryTimeNum) {
				return false;
			}
			return LotteryUtils.checkWinSscZhiXuanDan(openLotteryTimeNum.getOpenNum().substring(0, 5),
					lotteryOrder.getBetDetail());
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			return super.calculateOrderBonus(lotteryOrder, openlotteryTimeNum);
		}

	},
	/** 时时彩中3直选单式 */
	SSC_ZHONG3_ZHIXUANDAN("SSC_ZHONG3_ZHIXUANDAN", "时时彩中3直选单式") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {
			// TODO Auto-generated method stub
		}

		@Override
		public ResponseMsgData checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
		/*	// 1、对注单进行基础校验（注单期号、投注时间有效性、返水范围校验）
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
			return super.checkAmount(lotteryOrder, LotteryUtils.orderCountSscZhiXuanDan(lotteryOrder.getBetDetail()));*/
			return ResponseMsgData.ok();
		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if (null == lotteryOrder || null == openLotteryTimeNum) {
				return false;
			}
			return LotteryUtils.checkWinSscZhiXuanDan(openLotteryTimeNum.getOpenNum().substring(2, 7),
					lotteryOrder.getBetDetail());
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			return super.calculateOrderBonus(lotteryOrder, openlotteryTimeNum);
		}
	},
	/** 时时彩后3直选单式 */
	SSC_HOU3_ZHIXUANDAN("SSC_HOU3_ZHIXUANDAN", "时时彩后3直选单式") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {
			// TODO Auto-generated method stub
		}

		@Override
		public ResponseMsgData checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
			/*// 1、对注单进行基础校验（注单期号、投注时间有效性、返水范围校验）
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
			return super.checkAmount(lotteryOrder, LotteryUtils.orderCountSscZhiXuanDan(lotteryOrder.getBetDetail()));*/
			return ResponseMsgData.ok();
		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if (null == lotteryOrder || null == openLotteryTimeNum) {
				return false;
			}
			return LotteryUtils.checkWinSscZhiXuanDan(openLotteryTimeNum.getOpenNum().substring(4),
					lotteryOrder.getBetDetail());
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			return super.calculateOrderBonus(lotteryOrder, openlotteryTimeNum);
		}
	},

	/** 时时彩前3直选复式 */
	SSC_QIAN3_ZHIXUANFU("SSC_QIAN3_ZHIXUANFU", "时时彩前3直选复式") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {
			// TODO Auto-generated method stub
		}

		@Override
		public ResponseMsgData checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
		/*	// 1、对注单进行基础校验
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
			return super.checkAmount(lotteryOrder, LotteryUtils.orderCountSscZhiXuanFu(lotteryOrder.getBetDetail()));*/
			return ResponseMsgData.ok();
		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if (null == lotteryOrder || null == openLotteryTimeNum) {
				return false;
			}
			return LotteryUtils.checkWinSscZhiXuanFu(openLotteryTimeNum.getOpenNum().substring(0, 5),
					lotteryOrder.getBetDetail());
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			return super.calculateOrderBonus(lotteryOrder, openlotteryTimeNum);
		}
	},
	/** 时时彩中3直选复式 */
	SSC_ZHONG3_ZHIXUANFU("SSC_ZHONG3_ZHIXUANFU", "时时彩中3直选复式") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {
			// TODO Auto-generated method stub
		}

		@Override
		public ResponseMsgData checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
			/*// 1、对注单进行基础校验
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
			return super.checkAmount(lotteryOrder, LotteryUtils.orderCountSscZhiXuanFu(lotteryOrder.getBetDetail()));*/
			return ResponseMsgData.ok();
		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if (null == lotteryOrder || null == openLotteryTimeNum) {
				return false;
			}
			return LotteryUtils.checkWinSscZhiXuanFu(openLotteryTimeNum.getOpenNum().substring(2, 7),
					lotteryOrder.getBetDetail());
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			return super.calculateOrderBonus(lotteryOrder, openlotteryTimeNum);
		}
	},
	/** 时时彩后3直选复式 */
	SSC_HOU3_ZHIXUANFU("SSC_HOU3_ZHIXUANFU", "时时彩后3直选复式") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {
			// TODO Auto-generated method stub
		}

		@Override
		public ResponseMsgData checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
			/*// 1、对注单进行基础校验
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
			return super.checkAmount(lotteryOrder, LotteryUtils.orderCountSscZhiXuanFu(lotteryOrder.getBetDetail()));*/
			return ResponseMsgData.ok();
		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if (null == lotteryOrder || null == openLotteryTimeNum) {
				return false;
			}
			return LotteryUtils.checkWinSscZhiXuanFu(openLotteryTimeNum.getOpenNum().substring(4),
					lotteryOrder.getBetDetail());
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			return super.calculateOrderBonus(lotteryOrder, openlotteryTimeNum);
		}
	},

	/** 时时彩前3直选和值 */
	SSC_QIAN3_ZHIXUANHE("SSC_QIAN3_ZHIXUANHE", "时时彩前3直选和值") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {
			// TODO Auto-generated method stub
		}

		@Override
		public ResponseMsgData checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
			// TODO Auto-generated method stub
			return super.checkOrder(lotteryOrder, betLotteryTimeNum);
		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if (null == lotteryOrder || null == openLotteryTimeNum) {
				return false;
			}
			return LotteryUtils.checkWinSscZhiXuanHe(openLotteryTimeNum.getOpenNum().substring(0, 5),
					lotteryOrder.getBetDetail());
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			if (!checkWin(lotteryOrder, openlotteryTimeNum)) {
				return BigDecimal.ZERO;
			}
			return super.calculateOrderBounsHe(lotteryOrder, openlotteryTimeNum.getOpenNum().substring(0, 5));
		}
	},
	/** 时时彩中3直选和值 */
	SSC_ZHONG3_ZHIXUANHE("SSC_ZHONG3_ZHIXUANHE", "时时彩中3直选和值") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {
			// TODO Auto-generated method stub
		}

		@Override
		public ResponseMsgData checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
			// TODO Auto-generated method stub
			return super.checkOrder(lotteryOrder, betLotteryTimeNum);
		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if (null == lotteryOrder || null == openLotteryTimeNum) {
				return false;
			}
			return LotteryUtils.checkWinSscZhiXuanHe(openLotteryTimeNum.getOpenNum().substring(2, 7),
					lotteryOrder.getBetDetail());
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			if (!checkWin(lotteryOrder, openlotteryTimeNum)) {
				return BigDecimal.ZERO;
			}
			return super.calculateOrderBounsHe(lotteryOrder, openlotteryTimeNum.getOpenNum().substring(2, 7));
		}
	},
	/** 时时彩后3直选和值 */
	SSC_HOU3_ZHIXUANHE("SSC_HOU3_ZHIXUANHE", "时时彩后3直选和值") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if (null == lotteryOrder || null == openLotteryTimeNum) {
				return false;
			}
			return LotteryUtils.checkWinSscZhiXuanHe(openLotteryTimeNum.getOpenNum().substring(4),
					lotteryOrder.getBetDetail());
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			if (!checkWin(lotteryOrder, openlotteryTimeNum)) {
				return BigDecimal.ZERO;
			}
			return super.calculateOrderBounsHe(lotteryOrder, openlotteryTimeNum.getOpenNum().substring(4));
		}

	},

	/** 时时彩前3组选3 */
	SSC_QIAN3_ZUXUAN3("SSC_QIAN3_ZUXUAN3", "时时彩前3组选3") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {
			// TODO Auto-generated method stub
		}

		@Override
		public ResponseMsgData checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
			/*// 基础校验，如果不能通过直接校验失败
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
			}*/
			return ResponseMsgData.ok();
		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if (null == lotteryOrder || null == openLotteryTimeNum) {
				return false;
			}
			return LotteryUtils.checkWinSsc3XingZuXuan3(openLotteryTimeNum.getOpenNum().substring(0, 5),
					lotteryOrder.getBetDetail());
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			return super.calculateOrderBonus(lotteryOrder, openlotteryTimeNum);
		}
	},
	/** 时时彩中3组选3 */
	SSC_ZHONG3_ZUXUAN3("SSC_ZHONG3_ZUXUAN3", "时时彩中3组选3") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {
			// TODO Auto-generated method stub
		}

		@Override
		public ResponseMsgData checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
			/*// 基础校验，如果不能通过直接校验失败
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
			}*/
			return ResponseMsgData.ok();
		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if (null == lotteryOrder || null == openLotteryTimeNum) {
				return false;
			}
			return LotteryUtils.checkWinSsc3XingZuXuan3(openLotteryTimeNum.getOpenNum().substring(2, 7),
					lotteryOrder.getBetDetail());
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			return super.calculateOrderBonus(lotteryOrder, openlotteryTimeNum);
		}
	},
	/** 时时彩后3组选3 */
	SSC_HOU3_ZUXUAN3("SSC_HOU3_ZUXUAN3", "时时彩后3组选3") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {
			// TODO Auto-generated method stub
		}

		@Override
		public ResponseMsgData checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
			/*// 基础校验，如果不能通过直接校验失败
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
			}*/
			return ResponseMsgData.ok();
		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if (null == lotteryOrder || null == openLotteryTimeNum) {
				return false;
			}
			return LotteryUtils.checkWinSsc3XingZuXuan3(openLotteryTimeNum.getOpenNum().substring(4),
					lotteryOrder.getBetDetail());
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			return super.calculateOrderBonus(lotteryOrder, openlotteryTimeNum);
		}
	},

	/** 时时彩前3组选6 */
	SSC_QIAN3_ZUXUAN6("SSC_QIAN3_ZUXUAN6", "时时彩前3组选6") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {
			// TODO Auto-generated method stub
		}

		@Override
		public ResponseMsgData checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
			/*// 对注单进行基础校验
			if (super.checkOrder(lotteryOrder, betLotteryTimeNum) != 0) {
				return 1;
			}
			// 正则验证投注号码,必须是0-9的数字以逗号分隔
			String regex = "^\\d+(,\\d{0,2})*$";
			if (!lotteryOrder.getBetDetail().matches(regex)) {
				return 1;
			}
			// 校验订单金额
			return super.checkAmount(lotteryOrder, LotteryUtils.orderCountSsc3XingZuXuan6(lotteryOrder));*/
			
			return ResponseMsgData.ok();
		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if (null == lotteryOrder || null == openLotteryTimeNum) {
				return false;
			}
			return LotteryUtils.checkWinSsc3XingZuxuan6(openLotteryTimeNum.getOpenNum().substring(0, 5),
					lotteryOrder.getBetDetail());
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			return super.calculateOrderBonus(lotteryOrder, openlotteryTimeNum);
		}
	},
	/** 时时彩中3组选6 */
	SSC_ZHONG3_ZUXUAN6("SSC_ZHONG3_ZUXUAN6", "时时彩中3组选6") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {
			// TODO Auto-generated method stub

		}

		@Override
		public ResponseMsgData checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
			/*// 对注单进行基础校验
			if (super.checkOrder(lotteryOrder, betLotteryTimeNum) != 0) {
				return 1;
			}
			// 正则验证投注号码,必须是0-9的数字以逗号分隔
			String regex = "^\\d+(,\\d{0,2})*$";
			if (!lotteryOrder.getBetDetail().matches(regex)) {
				return 1;
			}
			// 校验订单金额
			return super.checkAmount(lotteryOrder, LotteryUtils.orderCountSsc3XingZuXuan6(lotteryOrder));*/
			return ResponseMsgData.ok();
		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if (null == lotteryOrder || null == openLotteryTimeNum) {
				return false;
			}
			return LotteryUtils.checkWinSsc3XingZuxuan6(openLotteryTimeNum.getOpenNum().substring(2, 7),
					lotteryOrder.getBetDetail());
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			return super.calculateOrderBonus(lotteryOrder, openlotteryTimeNum);
		}

	},
	/** 时时彩后3组选6 */
	SSC_HOU3_ZUXUAN6("SSC_HOU3_ZUXUAN6", "时时彩后3组选6") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {
			// TODO Auto-generated method stub

		}

		@Override
		public ResponseMsgData checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
			/*// 对注单进行基础校验
			if (super.checkOrder(lotteryOrder, betLotteryTimeNum) != 0) {
				return 1;
			}
			// 正则验证投注号码,必须是0-9的数字以逗号分隔
			String regex = "^\\d+(,\\d{0,2})*$";
			if (!lotteryOrder.getBetDetail().matches(regex)) {
				return 1;
			}
			// 校验订单金额
			return super.checkAmount(lotteryOrder, LotteryUtils.orderCountSsc3XingZuXuan6(lotteryOrder));*/
			
			return ResponseMsgData.ok();
		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if (null == lotteryOrder || null == openLotteryTimeNum) {
				return false;
			}
			return LotteryUtils.checkWinSsc3XingZuxuan6(openLotteryTimeNum.getOpenNum().substring(4),
					lotteryOrder.getBetDetail());
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			return super.calculateOrderBonus(lotteryOrder, openlotteryTimeNum);
		}
	},

	/** 时时彩前3混合组选 */
	SSC_QIAN3_ZUXUANHUN("SSC_QIAN3_ZUXUANHUN", "时时彩前3混合组选") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {

		}

		@Override
		public ResponseMsgData checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {

			return ResponseMsgData.ok();
		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if (null == lotteryOrder || null == openLotteryTimeNum) {
				return false;
			}
			return LotteryUtils.checkWinSsc3XingZuXuanHun(openLotteryTimeNum.getOpenNum().substring(0, 5),
					lotteryOrder.getBetDetail());
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if (!checkWin(lotteryOrder, openLotteryTimeNum)) {
				return BigDecimal.ZERO;
			}
			return super.calculateOrderBounsHun(lotteryOrder, openLotteryTimeNum.getOpenNum().substring(0, 5));
		}
	},
	/** 时时彩中3混合组选 */
	SSC_ZHONG3_ZUXUANHUN("SSC_ZHONG3_ZUXUANHUN", "时时彩中3混合组选") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {

		}

		@Override
		public ResponseMsgData checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {

			return ResponseMsgData.ok();
		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if (null == lotteryOrder || null == openLotteryTimeNum) {
				return false;
			}
			return LotteryUtils.checkWinSsc3XingZuXuanHun(openLotteryTimeNum.getOpenNum().substring(2, 7),
					lotteryOrder.getBetDetail());
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if (!checkWin(lotteryOrder, openLotteryTimeNum)) {
				return BigDecimal.ZERO;
			}
			return super.calculateOrderBounsHun(lotteryOrder, openLotteryTimeNum.getOpenNum().substring(2, 7));
		}
	},
	/** 时时彩后3混合组选 */
	SSC_HOU3_ZUXUANHUN("SSC_HOU3_ZUXUANHUN", "时时彩后3混合组选") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {

		}

		@Override
		public ResponseMsgData checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {

			return ResponseMsgData.ok();
		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if (null == lotteryOrder || null == openLotteryTimeNum) {
				return false;
			}
			return LotteryUtils.checkWinSsc3XingZuXuanHun(openLotteryTimeNum.getOpenNum().substring(4),
					lotteryOrder.getBetDetail());
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if (!checkWin(lotteryOrder, openLotteryTimeNum)) {
				return BigDecimal.ZERO;
			}
			return super.calculateOrderBounsHun(lotteryOrder, openLotteryTimeNum.getOpenNum().substring(4));
		}
	},

	/** 时时彩前3和值尾数 */
	SSC_QIAN3_HEZHIWEISHU("SSC_QIAN3_HEZHIWEISHU", "时时彩前3和值尾数") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {
			// TODO Auto-generated method stub
		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if (null == lotteryOrder || null == openLotteryTimeNum) {
				return false;
			}
			return LotteryUtils.checkWinSsc3XingHeZhiWeiShu(openLotteryTimeNum.getOpenNum().substring(0, 5),
					lotteryOrder.getBetDetail());
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			return super.calculateOrderBonus(lotteryOrder, openlotteryTimeNum);
		}
	},
	/** 时时彩中3和值尾数 */
	SSC_ZHONG3_HEZHIWEISHU("SSC_ZHONG3_HEZHIWEISHU", "时时彩中3和值尾数") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {
			// TODO Auto-generated method stub

		}

		@Override
		public ResponseMsgData checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
			// TODO Auto-generated method stub
			return super.checkOrder(lotteryOrder, betLotteryTimeNum);
		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if (null == lotteryOrder || null == openLotteryTimeNum) {
				return false;
			}
			return LotteryUtils.checkWinSsc3XingHeZhiWeiShu(openLotteryTimeNum.getOpenNum().substring(2, 7),
					lotteryOrder.getBetDetail());
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			return super.calculateOrderBonus(lotteryOrder, openlotteryTimeNum);
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
			if (null == lotteryOrder || null == openLotteryTimeNum) {
				return false;
			}
			return LotteryUtils.checkWinSsc3XingHeZhiWeiShu(openLotteryTimeNum.getOpenNum().substring(4),
					lotteryOrder.getBetDetail());
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			return super.calculateOrderBonus(lotteryOrder, openlotteryTimeNum);
		}

	},

	/** 时时彩4星直选单式 */
	SSC_4XING_ZHIXUANDAN("SSC_4XING_ZHIXUANDAN", "时时彩4星直选单式") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {
			// TODO Auto-generated method stub
		}

		@Override
		public ResponseMsgData checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
			return super.checkOrder(lotteryOrder, betLotteryTimeNum);
		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if (null == lotteryOrder || null == openLotteryTimeNum) {
				return false;
			}
			return LotteryUtils.checkWinSscZhiXuanDan(openLotteryTimeNum.getOpenNum().substring(2),
					lotteryOrder.getBetDetail());
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			return super.calculateOrderBonus(lotteryOrder, openlotteryTimeNum);
		}
	},
	/** 时时彩4星直选复式 */
	SSC_4XING_ZHIXUANFU("SSC_4XING_ZHIXUANFU", "时时彩4星直选复式") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {
			// TODO Auto-generated method stub
		}

		@Override
		public ResponseMsgData checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
			return super.checkOrder(lotteryOrder, betLotteryTimeNum);
		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if (null == lotteryOrder || null == openLotteryTimeNum) {
				return false;
			}
			return LotteryUtils.checkWinSscZhiXuanFu(openLotteryTimeNum.getOpenNum().substring(2),
					lotteryOrder.getBetDetail());
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			return super.calculateOrderBonus(lotteryOrder, openlotteryTimeNum);
		}
	},

	/** 时时彩4星组选24 */
	SSC_4XING_ZUXUAN24("SSC_4XING_ZUXUAN24", "时时彩4星组选24") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {
			// TODO Auto-generated method stub
		}

		@Override
		public ResponseMsgData checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
			return super.checkOrder(lotteryOrder, betLotteryTimeNum);
		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if (null == lotteryOrder || null == openLotteryTimeNum) {
				return false;
			}
			return LotteryUtils.checkWinSscZuXuan24_120(openLotteryTimeNum.getOpenNum().substring(2),
					lotteryOrder.getBetDetail(), 4);
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			return super.calculateOrderBonus(lotteryOrder, openlotteryTimeNum);
		}
	},
	/** 时时彩4星组选12 */
	SSC_4XING_ZUXUAN12("SSC_4XING_ZUXUAN12", "时时彩4星组选12") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {
			// TODO Auto-generated method stub
		}

		@Override
		public ResponseMsgData checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
			return super.checkOrder(lotteryOrder, betLotteryTimeNum);
		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if (null == lotteryOrder || null == openLotteryTimeNum) {
				return false;
			}
			return LotteryUtils.checkWinSsc4XingZuXuan12(openLotteryTimeNum.getOpenNum().substring(2),
					lotteryOrder.getBetDetail());
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			return super.calculateOrderBonus(lotteryOrder, openlotteryTimeNum);
		}
	},
	/** 时时彩4星组选6 */
	SSC_4XING_ZUXUAN6("SSC_4XING_ZUXUAN6", "时时彩4星组选6") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {
			// TODO Auto-generated method stub
		}

		@Override
		public ResponseMsgData checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
			return super.checkOrder(lotteryOrder, betLotteryTimeNum);
		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if (null == lotteryOrder || null == openLotteryTimeNum) {
				return false;
			}
			return LotteryUtils.checkWinSsc4XingZuXuan6(openLotteryTimeNum.getOpenNum().substring(2),
					lotteryOrder.getBetDetail());
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			return super.calculateOrderBonus(lotteryOrder, openlotteryTimeNum);
		}
	},
	/** 时时彩4星组选4 */
	SSC_4XING_ZUXUAN4("SSC_4XING_ZUXUAN4", "时时彩4星组选4") {
		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {
			// TODO Auto-generated method stub
		}

		@Override
		public ResponseMsgData checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
			// TODO Auto-generated method stub
			return ResponseMsgData.ok();
		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if (null == lotteryOrder || null == openLotteryTimeNum) {
				return false;
			}
			return LotteryUtils.checkWinSscZuXuan4_20(openLotteryTimeNum.getOpenNum().substring(2),
					lotteryOrder.getBetDetail(), 2);
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			return super.calculateOrderBonus(lotteryOrder, openlotteryTimeNum);
		}
	},

	/** 时时彩5星直选单式 */
	SSC_5XING_ZHIXUANDAN("SSC_5XING_ZHIXUANDAN", "时时彩5星直选单式") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {
			// TODO Auto-generated method stub
		}

		@Override
		public ResponseMsgData checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
			// 1、对注单进行基础校验（注单期号、投注时间有效性、返水范围校验）
			ResponseMsgData superRet = super.checkOrder(lotteryOrder, betLotteryTimeNum);
			if (!superRet.getIsSucceed()) {
				return superRet;
			}
			// 2、数据格式校验，
			// 个十百千万位各至少选一个号码，组成一注，以逗号分割。不同位置可以重复
			String betNumber = lotteryOrder.getBetDetail();
			String formatRegex = "^\\d{5}(,\\d{5})*$";

			if (!betNumber.matches(formatRegex)) {
				return ResponseMsgData.error(GameErrorEnum.BET_DETIAL_INVALID);
			}
			String[] arrSubBet = betNumber.split(",");

			List<String> lsSubBet = Arrays.asList(arrSubBet);
			// 4 校验投注金额 amount = betno * 2 * rate * moneytype
			int betNum = LotteryUtils.calBetNum5XingZhiXuanDanShi(lotteryOrder.getBetDetail());
			boolean ret1 = super.chkAmount(lotteryOrder, betNum);
			if (!ret1)
				return ResponseMsgData.error(GameErrorEnum.BETTING_MONEY_INVALID);
			return ResponseMsgData.ok();
		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if (null == lotteryOrder || null == openLotteryTimeNum) {
				return false;
			}
			return LotteryUtils.checkWinSscZhiXuanDan(openLotteryTimeNum.getOpenNum(), lotteryOrder.getBetDetail());
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			return super.calculateOrderBonus(lotteryOrder, openlotteryTimeNum);
		}

		public int calBetNum(String bet) {
			// TODO Auto-generated method stub

			String[] betNumList = bet.split(",");

			return betNumList.length;

		}

	},
	/** 时时彩5星直选复式 */
	SSC_5XING_ZHIXUANFU("SSC_5XING_ZHIXUANFU", "时时彩5星直选复式") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {
			// TODO Auto-generated method stub
		}

		@Override
		public ResponseMsgData checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
			// 1、对注单进行基础校验（注单期号、投注时间有效性、返水范围校验）
			ResponseMsgData responseMsgData=super.checkOrder(lotteryOrder, betLotteryTimeNum);
			if (!responseMsgData.getIsSucceed()) {
				return responseMsgData;
			}
			// 2、数据格式校验，
			// 个十百千万位各至少选一个号码，组成一注，以逗号分割。不同位置可以重复
			String betNumber = lotteryOrder.getBetDetail();
			String formatRegex = "^\\d{0,10},\\d{0,10},\\d{0,10},\\d{0,10},\\d{0,10}$";

			if (!betNumber.matches(formatRegex)) {
				return ResponseMsgData.error(GameErrorEnum.BET_DETIAL_INVALID);
			}

			String[] arrSubBet = betNumber.split(",");
			List<String> lsSubBet = Arrays.asList(arrSubBet);
			/*for (int j = 0; j < arrSubBet.length; j++) {
				CheckString.hasSameNum(ls)hasSameLetter(arrSubBet[j]);
			}*/
			Boolean isSameNum = CheckString.hasSameNum(lsSubBet);
			if(isSameNum)
				return ResponseMsgData.error(GameErrorEnum.BET_DETIAL_INVALID);

			// 4 校验投注金额 amount = betno * 2 * rate * moneytype
			int betNum = LotteryUtils.calBetNum5XingZhiXuanFuShi(lotteryOrder.getBetDetail());
			boolean ret1 = super.chkAmount(lotteryOrder, betNum);

			if (!ret1)
				return ResponseMsgData.error(GameErrorEnum.BETTING_MONEY_INVALID);

			return ResponseMsgData.ok();
		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if (null == lotteryOrder || null == openLotteryTimeNum) {
				return false;
			}
			return LotteryUtils.checkWinSscZhiXuanFu(openLotteryTimeNum.getOpenNum(), lotteryOrder.getBetDetail());
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			return super.calculateOrderBonus(lotteryOrder, openlotteryTimeNum);
		}

		public int calBetNum(String bet) {
			// TODO Auto-generated method stub

			String[] betNumList = bet.split(",");
			int count = 0;
			for (String string : betNumList) {
				count *= string.length();
			}

			return count;

		}

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

	/** 时时彩5星组选120 */
	SSC_5XING_ZUXUAN120("SSC_5XING_ZUXUAN120", "时时彩5星组选120") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {
			// TODO Auto-generated method stub
		}

		@Override
		public ResponseMsgData checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
			// 1、对注单进行基础校验（注单期号、投注时间有效性、返水范围校验）
			/*if (super.checkOrder(lotteryOrder, betLotteryTimeNum) != 0) {
				return 1;
			}
			// 2、数据格式校验，
			// 选择0~9中的5个数字作为一注，以逗号分割。数值不可以重复。
			String betNumber = lotteryOrder.getBetDetail();
			String formatRegex = "^\\d{1}(,\\d{1}){4,}$";

			if (!betNumber.matches(formatRegex)) {
				return GameError.errCodeBetDetial;
			}

			// 4 校验投注金额 amount = betno * 2 * rate * moneytype
			int betNum = LotteryUtils.calBetNum5XingZuXuan120(lotteryOrder.getBetDetail());
			boolean ret1 = super.chkAmount(lotteryOrder, betNum);

			if (!ret1)
				return GameError.errCodeBettingMoney;*/
			return ResponseMsgData.ok();
		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if (null == lotteryOrder || null == openLotteryTimeNum) {
				return false;
			}
			return LotteryUtils.checkWinSscZuXuan24_120(openLotteryTimeNum.getOpenNum(), lotteryOrder.getBetDetail(),
					5);
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			return super.calculateOrderBonus(lotteryOrder, openlotteryTimeNum);
		}

	},
	/** 时时彩5星组选60 */
	SSC_5XING_ZUXUAN60("SSC_5XING_ZUXUAN60", "时时彩5星组选60") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {
			// TODO Auto-generated method stub
		}

		@Override
		public ResponseMsgData checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
			// 1、对注单进行基础校验（注单期号、投注时间有效性、返水范围校验）
			/*if (super.checkOrder(lotteryOrder, betLotteryTimeNum) != 0) {
				return 1;
			}
			// 2、数据格式校验，
			// 选择0~9中的一个数字作为二重号，选择3个单号，作为一注，以逗号分割。
			String betNumber = lotteryOrder.getBetDetail();
			String formatRegex = "^\\d{1,9},\\d{1,9}$";

			if (!betNumber.matches(formatRegex)) {
				return GameError.errCodeBetDetial;
			}

			String[] arrSubBet = betNumber.split(",");

			List<String> lsSubBets = Arrays.asList(arrSubBet);

			Boolean bRet = CheckString.hasSameNum(lsSubBets);

			if (bRet)
				return GameError.errCodeBetDetial;

			// 4 校验投注金额 amount = betno * 2 * rate * moneytype
			int betNum = LotteryUtils.calBetNum5XingZuXuan60(lotteryOrder.getBetDetail());
			boolean ret1 = super.chkAmount(lotteryOrder, betNum);

			if (!ret1)
				return GameError.errCodeBettingMoney;*/
			return ResponseMsgData.ok();
		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if (null == lotteryOrder || null == openLotteryTimeNum) {
				return false;
			}
			return LotteryUtils.checkWinSsc5XingZuXuan60(openLotteryTimeNum.getOpenNum(), lotteryOrder.getBetDetail());
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			return super.calculateOrderBonus(lotteryOrder, openlotteryTimeNum);
		}

	},
	/** 时时彩5星组选30 */
	SSC_5XING_ZUXUAN30("SSC_5XING_ZUXUAN30", "时时彩5星组选30") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {
			// TODO Auto-generated method stub
		}

		@Override
		public ResponseMsgData checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
			// 1、对注单进行基础校验（注单期号、投注时间有效性、返水范围校验）
			/*if (super.checkOrder(lotteryOrder, betLotteryTimeNum) != 0) {
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
			int betNum = LotteryUtils.calBetNum5XingZuXuan30(lotteryOrder.getBetDetail());
			boolean ret1 = super.chkAmount(lotteryOrder, betNum);

			if (!ret1)
				return GameError.errCodeBettingMoney;*/

			return ResponseMsgData.ok();
		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if (null == lotteryOrder || null == openLotteryTimeNum) {
				return false;
			}
			return LotteryUtils.checkWinSsc5XingZuXuan30(openLotteryTimeNum.getOpenNum(), lotteryOrder.getBetDetail());
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			return super.calculateOrderBonus(lotteryOrder, openlotteryTimeNum);
		}

		public int calBetNum(String bet) {
			// TODO Auto-generated method stub

			String[] betNumList = bet.split(",");

			return betNumList.length;

		}

		public boolean checkAmount(LotteryOrder lotteryOrder) {
			// TODO Auto-generated method stub

			// 校验投注金额 amount = betno * 2 * rate * moneytype
			int betNum = calBetNum(lotteryOrder.getBetDetail());
			return super.chkAmount(lotteryOrder, betNum);

		}
	},
	/** 时时彩5星组选20 */
	SSC_5XING_ZUXUAN20("SSC_5XING_ZUXUAN20", "时时彩5星组选20") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {
			// TODO Auto-generated method stub
		}

		@Override
		public ResponseMsgData checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
			// 1、对注单进行基础校验（注单期号、投注时间有效性、返水范围校验）
			/*if (super.checkOrder(lotteryOrder, betLotteryTimeNum) != 0) {
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
			int betNum = LotteryUtils.calBetNum5XingZuXuan30(lotteryOrder.getBetDetail());
			boolean ret1 = super.chkAmount(lotteryOrder, betNum);

			if (!ret1)
				return GameError.errCodeBettingMoney;*/

			return ResponseMsgData.ok();
		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if (null == lotteryOrder || null == openLotteryTimeNum) {
				return false;
			}
			return LotteryUtils.checkWinSscZuXuan4_20(openLotteryTimeNum.getOpenNum(), lotteryOrder.getBetDetail(), 3);
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			return super.calculateOrderBonus(lotteryOrder, openlotteryTimeNum);
		}

		public int calBetNum(String bet) {
			// TODO Auto-generated method stub

			String[] betNumList = bet.split(",");

			return betNumList.length;

		}

		public boolean checkAmount(LotteryOrder lotteryOrder) {
			// TODO Auto-generated method stub

			// 校验投注金额 amount = betno * 2 * rate * moneytype
			int betNum = calBetNum(lotteryOrder.getBetDetail());
			return super.chkAmount(lotteryOrder, betNum);

		}
	},
	/** 时时彩5星组选10 */
	SSC_5XING_ZUXUAN10("SSC_5XING_ZUXUAN10", "时时彩5星组选10") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {
			// TODO Auto-generated method stub
		}

		@Override
		public ResponseMsgData checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
			// 1、对注单进行基础校验（注单期号、投注时间有效性、返水范围校验）
			/*if (super.checkOrder(lotteryOrder, betLotteryTimeNum) != 0) {
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
			int betNum = LotteryUtils.calBetNum5XingZuXuan30(lotteryOrder.getBetDetail());
			boolean ret1 = super.chkAmount(lotteryOrder, betNum);

			if (!ret1)
				return GameError.errCodeBettingMoney;*/

			return ResponseMsgData.ok();
		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if (null == lotteryOrder || null == openLotteryTimeNum) {
				return false;
			}
			return LotteryUtils.checkWinSsc5XingZuXuan10(openLotteryTimeNum.getOpenNum(), lotteryOrder.getBetDetail());
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			return super.calculateOrderBonus(lotteryOrder, openlotteryTimeNum);
		}
	},
	/** 时时彩5星组选5 */
	SSC_5XING_ZUXUAN5("SSC_5XING_ZUXUAN5", "时时彩5星组选5") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {
			// TODO Auto-generated method stub
		}

		@Override
		public ResponseMsgData checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
			// 1、对注单进行基础校验（注单期号、投注时间有效性、返水范围校验）
			/*if (super.checkOrder(lotteryOrder, betLotteryTimeNum) != 0) {
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
			int betNum = LotteryUtils.calBetNum5XingZuXuan30(lotteryOrder.getBetDetail());
			boolean ret1 = super.chkAmount(lotteryOrder, betNum);

			if (!ret1)
				return GameError.errCodeBettingMoney;*/

			return ResponseMsgData.ok();
		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if (null == lotteryOrder || null == openLotteryTimeNum) {
				return false;
			}
			return LotteryUtils.checkWinSsc5XingZuXuan5(openLotteryTimeNum.getOpenNum(), lotteryOrder.getBetDetail());
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			return super.calculateOrderBonus(lotteryOrder, openlotteryTimeNum);
		}

		public int calBetNum(String bet) {
			// TODO Auto-generated method stub

			String[] betNumList = bet.split(",");

			return betNumList.length;

		}

		public boolean checkAmount(LotteryOrder lotteryOrder) {
			// TODO Auto-generated method stub

			// 校验投注金额 amount = betno * 2 * rate * moneytype
			int betNum = calBetNum(lotteryOrder.getBetDetail());
			return super.chkAmount(lotteryOrder, betNum);
		}
	},

	/** 时时彩一帆风顺 */
	SSC_QUWEI_1FANFENGSHUN("SSC_QUWEI_1FANFENGSHUN", "时时彩一帆风顺") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if (null == lotteryOrder || null == openLotteryTimeNum) {
				return false;
			}
			return LotteryUtils.checkWin1FanFengShun(openLotteryTimeNum.getOpenNum(), lotteryOrder.getBetDetail());
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			return super.calculateOrderBonus(lotteryOrder, openlotteryTimeNum);
		}

	},
	/** 时时彩好事成双 */
	SSC_QUWEI_HAOSHICHENGSHUANG("SSC_QUWEI_HAOSHICHENGSHUANG", "时时彩好事成双") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if (null == lotteryOrder || null == openLotteryTimeNum) {
				return false;
			}
			return LotteryUtils.checkWinQuWei(openLotteryTimeNum.getOpenNum(), lotteryOrder.getBetDetail(), 4);
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			return super.calculateOrderBonus(lotteryOrder, openlotteryTimeNum);
		}
	},
	/** 时时彩三星报喜 */
	SSC_QUWEI_3XINGBAOXI("SSC_QUWEI_3XINGBAOXI", "时时彩三星报喜") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if (null == lotteryOrder || null == openLotteryTimeNum) {
				return false;
			}
			return LotteryUtils.checkWinQuWei(openLotteryTimeNum.getOpenNum(), lotteryOrder.getBetDetail(), 3);
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			return super.calculateOrderBonus(lotteryOrder, openlotteryTimeNum);
		}

	},
	/** 时时彩四季发财 */
	SSC_QUWEI_4JIFACAI("SSC_QUWEI_4JIFACAI", "时时彩四季发财") {

		@Override
		public void trend(LotteryTimeNum openLotteryTimeNum) {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
			if (null == lotteryOrder || null == openLotteryTimeNum) {
				return false;
			}
			return LotteryUtils.checkWinQuWei(openLotteryTimeNum.getOpenNum(), lotteryOrder.getBetDetail(), 2);
		}

		@Override
		public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum) {
			return super.calculateOrderBonus(lotteryOrder, openlotteryTimeNum);
		}
	};

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

	// MemberPlayConfig memCfg =
	// myMemberPlayConfigService.getMemberPlayConfigByUserId(lotteryOrder.getUser().getId());
	//
	// if(memCfg==null)return 1;
	// String jsPlayCfg = memCfg.getPlayConfig();
	//
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
	 * @return 返回对应奖金计算参数元（1）、角（0.1）、分（0.01）、厘（0.001），默认为元
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
		case "3":
			param = new BigDecimal("0.001");
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
	public ResponseMsgData checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
		// 如果传入参数为null，返回false，校验失败
		if (null == lotteryOrder || null == betLotteryTimeNum) {
			return ResponseMsgData.error(GameErrorEnum.ORDER_INVALID);
		}
		// 期号检查,投注期号与当前期号作对比，如果不一致，返回false，校验失败
		String currentIssueNo = lotteryOrder.getBetIssueNo();
		if (StringUtils.isBlank(currentIssueNo) || !currentIssueNo.equals(betLotteryTimeNum.getLotteryIssueNo())) {
			return ResponseMsgData.error(GameErrorEnum.ISSUSE_NO_INVALID);
		}
		// 投注有效性检查，当前时间是否在当前有效投注时间段内，如果不在，返回false，校验失败
		Long currentDate = System.currentTimeMillis();
		Long startDate = betLotteryTimeNum.getBetStartDate().getTime();
		Long endDate = betLotteryTimeNum.getBetHaltDate().getTime();
		if (currentDate <= startDate || currentDate >= endDate) {
			return ResponseMsgData.error(GameErrorEnum.ISSUSE_NO_INVALID);
		}

		// 返水范围校验
		MemberPlayConfigService myMemberPlayConfigService = SpringContextHolder.getBean("memberPlayConfigService");

		MemberPlayConfig memCfg = myMemberPlayConfigService.getMemberPlayConfigByUserId(lotteryOrder.getUser().getId());

		if (memCfg == null) {
			System.out.println("userid = null ==> " + lotteryOrder.getUser().getId());
			return ResponseMsgData.error(GameErrorEnum.UNKNOWN_MEMBER_INVALID);
		}
		String jsPlayCfg = memCfg.getPlayConfig();
		// LotteryPlayConfig lotPlayCfg = (LotteryPlayConfig)
		// JsonMapper.fromJsonString(jsPlayCfg,
		// LotteryPlayConfig.class);

		List<LotteryPlayConfig> playConfigList = (List<LotteryPlayConfig>) JsonMapper.getInstance().fromJson(jsPlayCfg,
				JsonMapper.getInstance().createCollectionType(List.class, LotteryPlayConfig.class));

		String playCode = lotteryOrder.getBetType();
		List<LotteryPlayConfig> lsPlayCfg = playConfigList.stream().filter(s -> playCode.equals(s.getPlayCode()))
				.collect(Collectors.toList());

		if (lsPlayCfg == null || lsPlayCfg.size() <= 0)
			return ResponseMsgData.error(GameErrorEnum.PLAY_CODE_INVALID);
		LotteryPlayConfig lotPlayCfg = lsPlayCfg.get(0);
		if (lotPlayCfg == null)
			return ResponseMsgData.error(GameErrorEnum.PLAY_CODE_INVALID);

		// 倍数限制

		int betRateLimitcfg = lotPlayCfg.getBetRateLimit();
		int betRate = lotteryOrder.getBetRate();
		if (betRate <= 0 && betRate > betRateLimitcfg)
			return ResponseMsgData.error(GameErrorEnum.BET_RATE_INVALID);

		BigDecimal winningProbabilityCfg = new BigDecimal(lotPlayCfg.getWinningProbability());
		BigDecimal CommissionRateMaxCfg = lotPlayCfg.getCommissionRateMax();
		BigDecimal CommissionRateMinCfg = lotPlayCfg.getCommissionRateMin();

		// 根据中奖概率和返水范围计算奖金组和返点
		BigDecimal playModeMoney = new BigDecimal(lotteryOrder.getPlayModeMoney());// 奖金模式
		playModeMoney.setScale(2);
		BigDecimal playModeCommissionRate = lotteryOrder.getPlayModeCommissionRate();// 奖金模式返水比例
		BigDecimal winAmount = lotteryOrder.getWinAmount();// 中奖金额

		// 校验奖金模式是否在范围内
		BigDecimal playModeMoneyMax;
		BigDecimal playModeMoneyMin;

		// 中奖概率
		BigDecimal winningProbability = new BigDecimal(lotPlayCfg.getWinningProbability());
		BigDecimal maxWin = new BigDecimal("2").divide(winningProbability);

		playModeMoneyMax = maxWin.multiply(new BigDecimal("1").subtract(CommissionRateMaxCfg));

		playModeMoneyMin = maxWin.multiply(new BigDecimal("1").subtract(CommissionRateMinCfg));

		// 奖金组是否在范围内
		if (playModeMoney.compareTo(playModeMoneyMin) > 0 || playModeMoney.compareTo(playModeMoneyMax) < 0) {
			return ResponseMsgData.error(GameErrorEnum.PLAY_MODE_MONEY_INVALID);
		}

		// 校验返水比例，奖金组是否符合规则
		// 个人投注返点 = 个人最高奖金组 - 投注时奖金组 / 2000 × 投注金额
		BigDecimal playModeCommissionRateCfg = (playModeMoneyMin.subtract(playModeMoney)).divide(new BigDecimal("2")).multiply(winningProbability);

		// 校验返点，是否正确
		if (playModeCommissionRate.compareTo(playModeCommissionRateCfg) != 0)
			return ResponseMsgData.error(GameErrorEnum.PLAY_MODE_COMMISSION_RATE_INVALID);
		return ResponseMsgData.ok();
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

		// 中奖金额 = 奖金组 * 投注倍数 * 投注模式对应面值 * 中奖注数
		return playModeMoney.multiply(betRate).multiply(playModeMoneyType).multiply(BigDecimal.ONE);
	}

	/**
	 * 计算和值订单中奖金额
	 * @param lotteryOrder 订单数据
	 * @param openNum 开奖号码
	 * @return 返回中奖金额
	 * @author Terry
	 */
	public BigDecimal calculateOrderBounsHe(LotteryOrder lotteryOrder, String openNum) {
		// 获取中奖概率
		String lotteryPlayCode = lotteryOrder.getBetType();
		LotteryPlayConfig playConfig = new LotteryPlayConfigServiceImpl().getByCode(lotteryPlayCode);
		BigDecimal winProbability = calculateHeZhiProbability(playConfig, openNum);
		// 获取平台抽水、单注金额、投注倍数、玩法模式计算参数、中奖注数
		BigDecimal playModeCommissionRate = lotteryOrder.getPlayModeCommissionRate();
		BigDecimal price = new BigDecimal(playConfig.getBetUnit());
		BigDecimal betRate = new BigDecimal(lotteryOrder.getBetRate());
		BigDecimal playModeMoneyType = getParamByType(lotteryOrder);

		// 奖金组 = 单注金额/中奖概率*（1-平台抽水）
		BigDecimal amountUnit = price.divide(winProbability).multiply(BigDecimal.ONE.subtract(playModeCommissionRate));

		// 中奖金额 = 奖金组 * 倍数 * 玩法模式 * 中奖注数
		BigDecimal winAmount = amountUnit.multiply(betRate).multiply(playModeMoneyType).multiply(BigDecimal.ONE);
		return winAmount;
	}

	/**
	 * 混合组选计算订单中奖金额
	 * @param lotteryOrder 订单数据
	 * @param openNum 开奖号码
	 * @return 返回订单中奖金额
	 * @author Terry
	 */
	public BigDecimal calculateOrderBounsHun(LotteryOrder lotteryOrder, String openNum) {
		String lotteryPlayCode = lotteryOrder.getBetType();
		LotteryPlayConfig playConfig = new LotteryPlayConfigServiceImpl().getByCode(lotteryPlayCode);

		// 获取中奖概率、单注金额、平台抽水
		BigDecimal winProbability = calculateHunHeProbability(playConfig, openNum);
		BigDecimal price = new BigDecimal(playConfig.getBetUnit());
		BigDecimal playModeCommissionRate = lotteryOrder.getPlayModeCommissionRate();

		// 奖金组 = 单注金额/中奖概率*（1-平台抽水）
		BigDecimal amountUnit = price.divide(winProbability).multiply(BigDecimal.ONE.subtract(playModeCommissionRate));

		// 投注倍数、玩法模式
		BigDecimal betRate = new BigDecimal(lotteryOrder.getBetRate());
		BigDecimal playModeMoneyType = getParamByType(lotteryOrder);

		// 中奖金额 = 奖金组 * 倍数 * 玩法模式 * 中奖注数
		BigDecimal winAmount = amountUnit.multiply(betRate).multiply(playModeMoneyType).multiply(BigDecimal.ONE);

		return winAmount;
	}

	/**
	 * 校验订单金额
	 * 
	 * @param lotteryOrder
	 *            订单对象
	 * @param betCount
	 *            投注注数
	 * @return 通过注单注数、单注金额、投注倍数预算投注金额，是否与订单金额保持一致，返回校验结果，通过返回true
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
	 * @param lotteryOrder
	 * @param betNum
	 * @return
	 */
	public boolean chkAmount(LotteryOrder lotteryOrder, int betNum) {
		// 校验投注金额 amount = betno * 2 * rate * moneytype

		BigDecimal moneyType = MoneyTypeDecimal.getInstants().findMoneyType(lotteryOrder.getPlayModeMoneyType());
		BigDecimal rate = new BigDecimal(lotteryOrder.getBetRate());
		BigDecimal amount = rate.multiply(moneyType).multiply(new BigDecimal(betNum * 2));
		return (amount.compareTo(lotteryOrder.getBetAmount()) == 0);
	}

	/**
	 * 获取和值号码中奖概率
	 * @param playConfig 玩法配置数据
	 * @param openNum 开奖号码
	 * @return 返回开奖号码和值中奖概率
	 * @author Terry
	 */
	private BigDecimal calculateHeZhiProbability(LotteryPlayConfig playConfig, String openNum) {
		// 根据玩法类型获取玩法多概率配置
		String playMultStr = playConfig.getLotteryPlayMult();
		// 转换配置数据为List集合方便迭代
		JsonMapper jsonMapper = JsonMapper.getInstance();
		List<lotteryPlayMult> multList = jsonMapper.fromJson(playMultStr,
				jsonMapper.createCollectionType(List.class, lotteryPlayMult.class));
		// 获取和值号码中奖概率
		String openSum = LotteryUtils.openSum(openNum);
		for (lotteryPlayMult lotteryPlayMult : multList) {
			if (openSum.equals(lotteryPlayMult.getNumber())) {
				BigDecimal result = new BigDecimal(lotteryPlayMult.getWinningProbability());
				return result;
			}
		}
		return BigDecimal.ZERO;
	}

	/**
	 * 获取混合组选中奖概率
	 * @param playConfig 玩法配置数据
	 * @param openNum 开奖号码
	 * @return 开奖号码中奖概率
	 */
	private BigDecimal calculateHunHeProbability(LotteryPlayConfig playConfig, String openNum) {
		// 判断开奖号码是否是组3
		String key = "HUNHE_ZU6";
		if (openNum.matches(".*(\\d).*\\1.*")) {
			key = "HUNHE_ZU3";
		}
		// 获取多概率数据
		String winningProbability = playConfig.getLotteryPlayMult();
		// 转换数据为List集合
		JsonMapper jsonMapper = JsonMapper.getInstance();
		List<lotteryPlayMult> probabilityList = jsonMapper.fromJson(winningProbability,
				jsonMapper.createCollectionType(List.class, lotteryPlayMult.class));
		// 迭代返回对应的概率
		for (lotteryPlayMult lotteryPlayMult : probabilityList) {
			if (key.equals(lotteryPlayMult.getNumber())) {
				BigDecimal result = new BigDecimal(lotteryPlayMult.getWinningProbability());
				return result;
			}
		}
		return BigDecimal.ZERO;
	}
}