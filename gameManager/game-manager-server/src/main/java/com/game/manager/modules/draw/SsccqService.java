package com.game.manager.modules.draw;

import java.util.EnumSet;

public enum SsccqService implements LotteryCalculateService {
    /** 单星直选 */
    // SSC_DAN1_ZHIXUAN("SSC_DAN1_ZHIXUAN", "单星直选") {
    // @Override
    // public void trend(LotteryTimeNum lotteryTimeNum) {
    // // TODO Auto-generated method stub
    // }
    // @Override
    // public boolean checkOrder(LotteryOrder lotteryOrder) {
    // String betNumber = "1";
    // return StringUtils.contains("0123456789", betNumber);
    // }
    //
    // @Override
    // public boolean checkWin() {
    // String betNumber = "1";
    // return StringUtils.contains("0123456789", betNumber);
    // }
    // @Override
    // public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder) {
    // return new BigDecimal(0);
    // }
    // },
    /** xxxxxx */
    /** xxxxxx */
    /** xxxxxx */
    /** xxxxxx */
    /** xxxxxx */
    /** xxxxxx */
    /** xxxxxx */

    // /** 时时彩前3直选 */
    // SSC_QIAN3_ZHIXUAN("SSC_QIAN3_ZHIXUAN", "时时彩前3直选 ") {},
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

    public static void main(String[] args) {
/*        EnumSet<SsccqService> ssccq = EnumSet.allOf(SsccqService.class);
        for (SsccqService playcode : ssccq) {
            // System.out.println(playcode);
            if ("SSC_DAN1_ZHIXUAN".equalsIgnoreCase(playcode.getPlayCode())) {
                System.out.println(playcode.checkWin());
                break;
            }
        }*/
    }

}
