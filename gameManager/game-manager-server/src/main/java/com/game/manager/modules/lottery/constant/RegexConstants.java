package com.game.manager.modules.lottery.constant;

/**
 * 正则字符串常量类
 * @author Terry
 */
public interface RegexConstants {

    /**
     * 单词字符，长度1-50
     */
    String LETTER_AND_NUMBER_1_50 = "\\w{1,50}";

    /**
     * 长度为1-6的数字字符串
     */
    String NUM_1_6 = "\\d{1,6}";

    /**
     * 长度为1-255的数字字符串
     */
    String NUM_1_255 = "\\d{1,255}";

    /**
     * 长度 0
     */
    int LENGTH_0 = 0;

    /**
     * 长度 1
     */
    int LENGTH_1 = 1;

    /**
     * 长度 50
     */
    int LENGTH_50 = 50;

    /**
     * 时间字符串 HH:mm
     */
    String TIME_HH_MM = "([0-1]?[0-9]|2[0-3]):([0-5][0-9])";
}