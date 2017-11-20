package com.game.manager.modules.draw;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import com.game.manager.common.utils.SpringContextHolder;
import com.game.manager.modules.lottery.entity.LotteryTimeNum;
import com.game.manager.modules.lottery.service.LotteryTimeNumService;
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
            // 对注单进行基础校验
            baseCheck(lotteryOrder);
            // 数据格式校验，投注内容必须是数字或减号以英文逗号隔开的字符串，且至少包含一个数字
            String betNumber = lotteryOrder.getBetDetail();
            String formatRegex = "((\\d+|-){1},{1}){4}(\\d+|-){1}";
            String containRegex = ".*\\d.*";
            if (betNumber.matches(formatRegex) && betNumber.matches(containRegex)) {
                return false;
            }
            return true;
        }

        @Override
        public boolean checkWin(LotteryOrder lotteryOrder) {
            return super.sscZhiXuanCheckWid(lotteryOrder);
        }

        @Override
        public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder) {
            return super.sscZhiXuanCalculate(lotteryOrder);
        }

    },
    /** 时时彩前3直选 */
    SSC_QIAN3_ZHIXUAN("SSC_QIAN3_ZHIXUAN", "时时彩前3直选 ") {

        @Override
        public void trend(LotteryTimeNum lotteryTimeNum) {
            // TODO Auto-generated method stub
        }

        @Override
        public boolean checkOrder(LotteryOrder lotteryOrder) {
            // 对注单进行基础校验
            baseCheck(lotteryOrder);
            // 数据格式校验，投注内容必须是数字或减号以英文逗号隔开的字符串，且至少包含一个数字
            String betNumber = lotteryOrder.getBetDetail();
            String formatRegex = "(\\d+,{1}){3}-,-";
            if (betNumber.matches(formatRegex)) {
                return false;
            }
            return true;
        }

        @Override
        public boolean checkWin(LotteryOrder lotteryOrder) {
        	return super.sscZhiXuanCheckWid(lotteryOrder);
        }

        @Override
        public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder) {
            return super.sscZhiXuanCalculate(lotteryOrder);
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
    /**
     * 开奖时刻管理Service
     */
    @Autowired
    private LotteryTimeNumService lotteryTimeNumService;

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
     * @return 基本校验不通过返回false，通过返回true
     * @author Terry
     */
    private static boolean baseCheck(LotteryOrder lotteryOrder) {
        // 如果传入参数为null，返回false，校验失败
        if (null == lotteryOrder) {
            return false;
        }
        // 期号检查,投注期号与当前期号作对比，如果不一致，返回false，校验失败
        LotteryTimeNum lotteryTimeNum = ((LotteryTimeNumService)SpringContextHolder.getBean("lotteryTimeNumService")).findCurrentIssueNo(lotteryOrder.getLotteryCode());
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

        // TODO 返水范围校验 等待freeman提供接口

        // 投注金额校验，如果传入的不是一个有效的金额数据，返回false，校验失败
        BigDecimal betAmount = lotteryOrder.getBetAmount();
        if (null == betAmount) {
            return false;
        }
        return betAmount.compareTo(new BigDecimal("0")) > 0;
    }

    /**
     * 时时彩直选是否中奖判断
     * @param lotteryOrder 投注订单数据实体对象
     * @return 中奖返回true
     * @author Terry
     */
    private static boolean sscZhiXuanCheckWid(LotteryOrder lotteryOrder) {
        // 如果传入参数为null，返回false，未中奖
        if (null == lotteryOrder) {
            return false;
        }

        
        // 获取开奖号码
        LotteryTimeNum lotteryTimeNum = ((LotteryTimeNumService)SpringContextHolder.getBean("lotteryTimeNumService")).findByLotteryCodeIssueNo(lotteryOrder.getLotteryCode(), lotteryOrder.getBetIssueNo());

        // 返回判断结果
        String openNum = lotteryTimeNum.getOpenNum();//1,2,3,4,5
        return LotteryUtils.checkWinSscZhiDan(openNum.substring(0, 5), lotteryOrder.getBetDetail());
    }

    /**
     * 时时彩单式投注奖金计算
     * @param lotteryOrder
     * @return 返回注单的中奖金额，如果没有中奖，返回金额为0
     * @author Terry
     */
    private static BigDecimal sscZhiXuanCalculate(LotteryOrder lotteryOrder) {
        // 如果没有中奖，直接返回金额为0
        if (!sscZhiXuanCheckWid(lotteryOrder)) {
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
}
