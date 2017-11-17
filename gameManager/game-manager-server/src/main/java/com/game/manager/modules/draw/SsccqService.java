package com.game.manager.modules.draw;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.game.manager.modules.lottery.entity.LotteryTimeNum;
import com.game.manager.modules.lottery.service.LotteryTimeNumService;
import com.game.manager.modules.order.entity.LotteryOrder;

public enum SsccqService implements LotteryCalculateService {

    /** 单星直选 */
    SSC_DAN1_ZHIXUAN("SSC_DAN1_ZHIXUAN", "单星直选") {

        /**
         * 开奖时刻管理Service
         */
        @Autowired
        private LotteryTimeNumService lotteryTimeNumService;

        @Override
        public void trend(LotteryTimeNum lotteryTimeNum) {
            // TODO Auto-generated method stub
        }

        @Override
        public boolean checkOrder(LotteryOrder lotteryOrder) {
            // 如果传入参数为null，返回false，校验失败
            if (null == lotteryOrder) {
                return false;
            }
            // 期号检查,投注期号与当前期号作对比，如果不一致，返回false，校验失败
            LotteryTimeNum lotteryTimeNum = lotteryTimeNumService.findCurrentIssueNo(lotteryOrder.getLotteryCode());
            if (null == lotteryTimeNum) {
                return false;
            }
            String currentIssueNo = lotteryTimeNum.getLotteryIssueNo();
            if (!lotteryOrder.getBetIssueNo().equals(currentIssueNo)) {
                return false;
            }
            // 投注有效性检查，当前时间是否在当前有效投注时间段内，如果不在，返回false，校验失败
            Long currentDate = System.currentTimeMillis();
            Long startDate = lotteryTimeNum.getBetStartDate().getTime();
            Long endDate = lotteryTimeNum.getBetEndDate().getTime();
            if (currentDate <= startDate || currentDate >= endDate) {
                return false;
            }
            // 数据格式校验，投注内容必须是数字或减号以英文逗号隔开的字符串，且至少包含一个数字
            String betNumber = lotteryOrder.getBetDetail();
            String formatRegex = "((\\d+|-){1},{1}){4}(\\d+|-){1}";
            String containRegex = ".*\\d.*";
            if (betNumber.matches(formatRegex) && betNumber.matches(containRegex)) {
                return false;
            }

            // TODO 返水范围校验 等待freeman提供接口

            // 投注金额校验，如果传入的不是一个有效的金额数据，返回false，校验失败
            String betAmount = lotteryOrder.getBetAmount();
            if (StringUtils.isBlank(betAmount)) {
                return false;
            }
            else {
                try {
                    return new BigDecimal(betAmount).compareTo(new BigDecimal("0")) > 0;
                }
                catch (Exception e) {
                    return false;
                }
            }
        }

        @Override
        public boolean checkWin(LotteryOrder lotteryOrder) {
            // 如果传入参数为null，返回false，未中奖
            if (null == lotteryOrder) {
                return false;
            }

            // TODO 获取当前期数开奖号码，等待jerry提供接口
            String openNum = "3,5,6,9,4";

            // 用户投注号码
            String betNumber = lotteryOrder.getBetDetail();
            // 返回判断结果
            return LotteryUtils.checkWinSscZhi(openNum, betNumber);
        }

        @Override
        public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder) {
            return new BigDecimal(0);
        }
    },
    /** xxxxxx */
    /** xxxxxx */
    /** xxxxxx */
    /** xxxxxx */
    /** xxxxxx */
    /** xxxxxx */
    /** xxxxxx */

    // /** 时时彩前3直选 */
    SSC_QIAN3_ZHIXUAN("SSC_QIAN3_ZHIXUAN", "时时彩前3直选 ") {

        @Override
        public void trend(LotteryTimeNum lotteryTimeNum) {
            // TODO Auto-generated method stub

        }

        @Override
        public boolean checkOrder(LotteryOrder lotteryOrder) {
            // TODO Auto-generated method stub

            // LotteryUtils.ssc3XinPailie.contains("4,5,6");
            return false;
        }

        @Override
        public boolean checkWin(LotteryOrder lotteryOrder) {
            // 获取中奖号码
            // 生成护院投注的排列
            // 然后判断中奖号码有没有在投注的排列中
            return false;
        }

        @Override
        public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder) {
            // TODO Auto-generated method stub
            if (checkWin(lotteryOrder)) {
                // BigDecimal
                // return lotteryOrder.getBetRate() * 奖金组
                return null;
            }
            return null;
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

    private SsccqService(String playCode, String playName) {
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

    /*
     * public static void main(String[] args) { EnumSet<SsccqService> ssccq =
     * EnumSet.allOf(SsccqService.class); for (SsccqService playcode : ssccq) { //
     * System.out.println(playcode); if
     * ("SSC_DAN1_ZHIXUAN".equalsIgnoreCase(playcode.getPlayCode())) {
     * System.out.println(playcode.checkWin()); break; } } }
     */
}