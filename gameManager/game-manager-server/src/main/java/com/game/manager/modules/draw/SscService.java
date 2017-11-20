package com.game.manager.modules.draw;

import java.math.BigDecimal;

import com.game.manager.modules.lottery.entity.LotteryTimeNum;
import com.game.manager.modules.order.entity.LotteryOrder;

/**
 * 时时彩玩法
 * 将通用的重构出来，在子枚举类里通过supper调用
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
        public boolean checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
            // 1、对注单进行基础校验（注单期号、投注时间有效性、返水范围校验）
            baseCheck(lotteryOrder, betLotteryTimeNum);
            // 2、数据格式校验，投注内容必须是数字或减号以英文逗号隔开的字符串，且至少包含一个数字
            String betNumber = lotteryOrder.getBetDetail();
            String formatRegex = "((\\d+|-){1},{1}){4}(\\d+|-){1}";
            String containRegex = ".*\\d.*";
            if (betNumber.matches(formatRegex) && betNumber.matches(containRegex)) {
                return false;
            }
            // 3、校验订单金额
            return super.checkAmount(lotteryOrder);
        }

        @Override
        public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
            if (null == lotteryOrder || null == openLotteryTimeNum) {
                return false;
            }
            return LotteryUtils.checkWinSscDanFu(openLotteryTimeNum.getOpenNum(), lotteryOrder.getBetDetail());
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
            // 中奖金额 = 奖金组 * 投注倍数 * 投注模式对应面值
            return playModeMoney.multiply(betRate).multiply(playModeMoneyType);
        }

    },
    /** 时时彩前3直选 */
    SSC_QIAN3_ZHIXUAN("SSC_QIAN3_ZHIXUAN", "时时彩前3直选 ") {

        @Override
        public void trend(LotteryTimeNum openLotteryTimeNum) {
            // TODO Auto-generated method stub
        }

        @Override
        public boolean checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum) {
            // 1、对注单进行基础校验
            baseCheck(lotteryOrder, betLotteryTimeNum);
            // 2、数据格式校验，万位、千位、百位由至少一位数字组成，十位、个位为减号，每个位置间以英文逗号隔开
            String betNumber = lotteryOrder.getBetDetail();
            String formatRegex = "(\\d+,{1}){3}-,-";
            if (betNumber.matches(formatRegex)) {
                return false;
            }
            // 3、校验订单金额
            return super.checkAmount(lotteryOrder);
        }

        @Override
        public boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum) {
            if (null == lotteryOrder || null == openLotteryTimeNum) {
                return false;
            }
            return LotteryUtils.checkWinSscZhiDan(openLotteryTimeNum.getOpenNum(), lotteryOrder.getBetDetail());
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
            // 中奖金额 = 奖金组 * 投注倍数 * 投注模式对应面值
            return playModeMoney.multiply(betRate).multiply(playModeMoneyType);
        }

    },
    //
    // /** 时时彩前3组选6 */
    // SSC_QIAN3_ZUXUAN6("SSC_QIAN3_ZUXUAN6", "时时彩前3组选6") {},
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

    /**  玩法代码 */
    private String playCode;
    /**  玩法名称 */
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
     * @param lotteryOrder 包含玩法模式数据的实体对象
     * @return 返回对应奖金计算参数
     * @author Terry
     */
    private static BigDecimal getParamByType(LotteryOrder lotteryOrder) {
        BigDecimal param = null;
        switch (lotteryOrder.getPlayModeMoneyType())
        {
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
     * @param lotteryOrder 注单信息
     * @param lotteryTimeNum 当前期开奖号码
     * @return 基本校验不通过返回false，通过返回true
     * @author Terry
     */
    private static boolean baseCheck(LotteryOrder lotteryOrder, LotteryTimeNum lotteryTimeNum) {
        // 如果传入参数为null，返回false，校验失败
        if (null == lotteryOrder || null == lotteryTimeNum) {
            return false;
        }
        // 期号检查,投注期号与当前期号作对比，如果不一致，返回false，校验失败
        String currentIssueNo = lotteryTimeNum.getLotteryIssueNo();
        if (!lotteryOrder.getBetIssueNo().equals(currentIssueNo)) {
            return false;
        }
        // 投注有效性检查，当前时间是否在当前有效投注时间段内，如果不在，返回false，校验失败
        // TODO 这里需要确认是否使用当前系统时间
        Long currentDate = System.currentTimeMillis();
        Long startDate = lotteryTimeNum.getBetStartDate().getTime();
        Long endDate = lotteryTimeNum.getBetEndDate().getTime();
        if (currentDate <= startDate || currentDate >= endDate) {
            return false;
        }

        // TODO 返水范围校验 等待freeman提供接口，获取用户的返回范围

        return true;
    }

    /**
     * 校验订单金额
     * @param lotteryOrder 订单对象
     * @return 通过注单注数、单注金额、投注倍数预算投注金额，是否与订单金额保持一致，返回校验结果，通过返回true
     * @author Terry
     */
    private boolean checkAmount(LotteryOrder lotteryOrder) {
        // 计算投注注数、获取投注倍数、单注金额
        int betCount = LotteryUtils.sscZhiXuanZhuShu(lotteryOrder.getBetDetail());
        Integer betRate = lotteryOrder.getBetRate();
        BigDecimal price = new BigDecimal(2);
        // 注单金额 = 单注金额 * 投注注数 * 投注倍数
        BigDecimal estimatedAmount = price.multiply(new BigDecimal(betCount)).multiply(new BigDecimal(betRate));
        // 如果计算出的投注金额与注单金额不一致，校验失败，返回false
        if (estimatedAmount.equals(lotteryOrder.getBetAmount())) {
            return true;
        }
        return false;
    }
}
