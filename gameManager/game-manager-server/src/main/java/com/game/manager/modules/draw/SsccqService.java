package com.game.manager.modules.draw;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

import com.game.manager.modules.lottery.entity.LotteryTimeNum;
import com.game.manager.modules.order.entity.LotteryOrder;

public enum SsccqService implements LotteryCalculateService {

    /** 单星直选 */
    SSC_DAN1_ZHIXUAN("SSC_DAN1_ZHIXUAN", "单星直选") {
        @Override
        public void trend(LotteryTimeNum lotteryTimeNum) {
            // TODO Auto-generated method stub
        }

        @Override
        public boolean checkOrder(LotteryOrder lotteryOrder) {
            // 如果传入参数为null，返回false
            if (null == lotteryOrder) {
                return false;
            }
            // 获取投注内容
            String betNumber = lotteryOrder.getBetDetail();
            if (StringUtils.isBlank(betNumber)) {
                return false;
            }
            // 定义校验正则表达式，第一个为格式校验(数字或减号以英文逗号隔开)，第二个是必须包含数字
            String formatRegex = "((\\d+|-){1},{1}){4}(\\d+|-){1}";
            String containRegex = ".*\\d.*";
            // 返回正则匹配结果
            return betNumber.matches(formatRegex) && betNumber.matches(containRegex);
        }

        @Override
        public boolean checkWin(LotteryOrder lotteryOrder) {
            String betNumber = "1";
            return StringUtils.contains("0123456789", betNumber);
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
