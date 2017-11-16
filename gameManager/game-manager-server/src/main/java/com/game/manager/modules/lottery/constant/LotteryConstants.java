<<<<<<< HEAD
package com.game.manager.modules.lottery.constant;

/**
 * 彩种管理操作信息常量类
 * @author Terry
 */
public interface LotteryConstants {

    /**
     * 保存成功
     */
	String SAVE_SUCCESS = "保存彩种基本信息成功";

    /**
     * 删除成功
     */
    String REMOVE_SUCCESS = "删除彩种基本信息成功";

    /**
     * 彩种代码校验提示信息
     */
    String CODE_CHECK_MESSAGE = "彩种代码长度必须介于 1 和 50 之间，且必须是数字或字母";

    /**
     * 彩种类型校验提示信息
     */
    String PARENTCODE_CHECK_MESSAGE = "彩种类型长度必须介于 1 和 50 之间，且必须是数字或字母";

    /**
     * 彩种名称校验提示信息
     */
    String NAME_CHECK_MESSAGE = "彩种名称长度必须介于 1 和 50 之间";

    /**
     * 是否自动开奖校验提示信息
     */
    String ISAUTO_CHECK_MESSAGE = "是否自动开奖长度必须为 1";

    /**
     * 是否自动开奖校验提示信息
     */
    String ISENABLE_CHECK_MESSAGE = "是否有效长度必须为 1";

    /**
     * 开售时间校验提示信息
     */
    String STARTDATE_CHECK_MESSAGE = "每日开售时刻格式有误，正确格式：HH:mm";

    /**
     * 停售时间校验提示信息
     */
    String ENDDATE_CHECK_MESSAGE = "每日停售时刻格式有误，正确格式：HH:mm";

    /**
     * 每日期数校验提示信息
     */
    String TIMES_CHECK_MESSAGE = "每日期数长度必须介于 1 和 6 之间，且必须为数字";

    /**
     * 开奖周期校验提示信息
     */
    String PERIODTOTALTIME_CHECK_MESSAGE = "开奖周期长度必须介于 1 和 255 之间，且必须为数字";

    /**
     * 封单时间校验提示信息
     */
    String PERIODHALTTIMEE_CHECK_MESSAGE = "封单时间长度必须介于 1 和 255 之间，且必须为数字";

    /**
     * 当前期号校验提示信息
     */
    String CURRENTISSUENO_CHECK_MESSAGE = "当前期号长度必须介于 0 和 50 之间";

    /**
     * 下一次开奖期号校验提示信息
     */
    String NEXTISSUNO_CHECK_MESSAGE = "下期期号长度必须介于 0 和 50 之间";
=======
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
     * 长度1-10校验提示信息
     */
    String LENGTH_1_10 = "长度必须介于 1 和 10 之间";

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
>>>>>>> refs/remotes/origin/master
}