package com.game.manager.modules.lottery.constant;

/**
 * 彩种管理操作信息常量类
 * @author Terry
 */
public interface LotteryConstants {

    /**
     * 保存成功
     */
    String SAVE_SUCCESS = "保存成功";

    /**
     * 删除成功
     */
    String REMOVE_SUCCESS = "删除成功";

    /**
     * 数字或者字母1-50位校验提示信息
     */
    String NUM_OR_LETTER_1_50 = "长度必须介于 1 和 50 之间，且必须是数字或字母";

    /**
     * 长度1-6校验提示信息
     */
    String LENGTH_1_6 = "长度必须介于 1 和 6 之间";

    /**
     * 长度1-6位的小数校验提示信息
     */
    String NUM_OR_FLOAT_1_6 = "长度必须介于 1 和 6 之间,且必须时数字或小数点";

    /**
     * 长度1-500校验提示信息
     */
    String LENGTH_1_500 = "长度必须介于 1 和 500 之间";

    /**
     * 长度1-50校验提示信息
     */
    String LENGTH_1_50 = "长度必须介于 1 和 50 之间";

    /**
     * 长度为1校验提示信息
     */
    String LENGTH_1 = "长度必须为 1";

    /**
     * 时间字符串格式校验提示信息，格式HH:mm
     */
    String TIME_HH_MM = "格式有误，正确格式：HH:mm";

    /**
     * 数字长度1-4校验提示信息
     */
    String NUM_1_4 = "长度必须介于 1 和 4 之间，且必须为数字";

    /**
     * 数字长度1-6校验提示信息
     */
    String NUM_1_6 = "长度必须介于 1 和 6 之间，且必须为数字";

    /**
     * 数字长度1-255校验提示信息
     */
    String NUM_1_255 = "长度必须介于 1 和 255 之间，且必须为数字";
}