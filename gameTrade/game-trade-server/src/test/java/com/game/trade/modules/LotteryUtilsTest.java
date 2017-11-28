package com.game.trade.modules;

import com.game.trade.modules.lottery.manager.LotteryUtils;

public class LotteryUtilsTest {

	/**
	 * 一星定位胆
	 * 
	 * @author Terry
	 */
	public static void ssc1XingDingWei() {
		long start = System.currentTimeMillis();
		boolean isWin = false;
		for (int i = 0; i < 10000000; i++) {
			isWin = LotteryUtils.checkWinSsc1XingDingWei("2,5,2,4,1",
					"2103597486,2974103586,7410329586,5867034912,5128670349");
		}
		long end = System.currentTimeMillis();
		System.out.println("计算结果：" + (isWin ? "已中奖，恭喜你，再接再厉。" : "未中奖，别灰心，再来一次。"));
		System.out.println("计算耗时：" + (end - start) + "ms");
	}

	/**
	 * 二星直选单式
	 * 
	 * @author Terry
	 */
	public static void ssc2XingZhiXuanDan() {
		long start = System.currentTimeMillis();
		boolean isWin = false;
		for (int i = 0; i < 10000000; i++) {
			isWin = LotteryUtils.checkWinSscZhiXuanDan("2,7", "26,28,56,99,20,01,57,45,00,25");
		}
		long end = System.currentTimeMillis();
		System.out.println("计算结果：" + (isWin ? "已中奖，恭喜你，再接再厉。" : "未中奖，别灰心，再来一次。"));
		System.out.println("计算耗时：" + (end - start) + "ms");
	}

	/**
	 * 二星直选复式
	 * 
	 * @author Terry
	 */
	public static void ssc2XingZhiXuanFu() {
		long start = System.currentTimeMillis();
		boolean isWin = false;
		for (int i = 0; i < 10000000; i++) {
			isWin = LotteryUtils.checkWinSscZhiXuanFu("0,9", "26248091375,1693240857");
		}
		long end = System.currentTimeMillis();
		System.out.println("计算结果：" + (isWin ? "已中奖，恭喜你，再接再厉。" : "未中奖，别灰心，再来一次。"));
		System.out.println("计算耗时：" + (end - start) + "ms");
	}

	/**
	 * 二星组选复式
	 * 
	 * @author Terry
	 */
	public static void ssc2XingZuXuanFu() {
		long start = System.currentTimeMillis();
		boolean isWin = false;
		for (int i = 0; i < 10000000; i++) {
			isWin = LotteryUtils.checkWinSsc2XingZuXuanFu("9,8", "2,6,8,5,1,9,3,0,4");
		}
		long end = System.currentTimeMillis();
		System.out.println("计算结果：" + (isWin ? "已中奖，恭喜你，再接再厉。" : "未中奖，别灰心，再来一次。"));
		System.out.println("计算耗时：" + (end - start) + "ms");
	}

	/**
	 * 3星直选单式
	 * 
	 * @author Terry
	 */
	public static void ssc3XingZhiXuanDan() {
		long start = System.currentTimeMillis();
		boolean isWin = false;
		for (int i = 0; i < 10000000; i++) {
			isWin = LotteryUtils.checkWinSscZhiXuanDan("0,5,9", "258,569,541,254,789,465,132,159,759,059");
		}
		long end = System.currentTimeMillis();
		System.out.println("计算结果：" + (isWin ? "已中奖，恭喜你，再接再厉。" : "未中奖，别灰心，再来一次。"));
		System.out.println("计算耗时：" + (end - start) + "ms");
	}

	/**
	 * 3星直选复式
	 * 
	 * @author Terry
	 */
	public static void sscQian3ZhiXuanFu() {
		long start = System.currentTimeMillis();
		boolean isWin = false;
		for (int i = 0; i < 10000000; i++) {
			isWin = LotteryUtils.checkWinSscZhiXuanFu("0,5,9", "4501278369,5012784369,783450126");
		}
		long end = System.currentTimeMillis();
		System.out.println("计算结果：" + (isWin ? "已中奖，恭喜你，再接再厉。" : "未中奖，别灰心，再来一次。"));
		System.out.println("计算耗时：" + (end - start) + "ms");
	}

	/**
	 * 三星组选3
	 * 
	 * @author Terry
	 */
	public static void ssc3XingZuXuan3() {
		long start = System.currentTimeMillis();
		boolean isWin = false;
		for (int i = 0; i < 10000000; i++) {
			isWin = LotteryUtils.checkWinSsc3XingZuXuan3("0,7,0", "4,5,0,1,2,7,8,3,6,9");
		}
		long end = System.currentTimeMillis();
		System.out.println("计算结果：" + (isWin ? "已中奖，恭喜你，再接再厉。" : "未中奖，别灰心，再来一次。"));
		System.out.println("计算耗时：" + (end - start) + "ms");
	}

	/**
	 * 五星组选120
	 * 
	 * @author Terry
	 */
	public static void checkWinSsc5XingZuXuan120() {
		long start = System.currentTimeMillis();
		boolean isWin = false;
		for (int i = 0; i < 10000000; i++) {
			isWin = LotteryUtils.checkWinSscZuXuan24_120("1,5,4,6,0", "1,5,4,9,3,2,6,7,8,0", 5);
		}
		long end = System.currentTimeMillis();
		System.out.println("计算结果：" + (isWin ? "已中奖，恭喜你，再接再厉。" : "未中奖，别灰心，再来一次。"));
		System.out.println("计算耗时：" + (end - start) + "ms");
	}

	/**
	 * 五星组选60
	 * 
	 * @author Terry
	 */
	public static void checkWinSsc5XingZuXuan60() {
		long start = System.currentTimeMillis();
		boolean isWin = false;
		for (int i = 0; i < 10000000; i++) {
			isWin = LotteryUtils.checkWinSsc5XingZuXuan60("1,5,3,9,9", "1549326780,6150493782");
		}
		long end = System.currentTimeMillis();
		System.out.println("计算结果：" + (isWin ? "已中奖，恭喜你，再接再厉。" : "未中奖，别灰心，再来一次。"));
		System.out.println("计算耗时：" + (end - start) + "ms");
	}

	/**
	 * 五星组选30
	 * 
	 * @author Terry
	 */
	public static void checkWinSsc5XingZuXuan30() {
		long start = System.currentTimeMillis();
		boolean isWin = false;
		for (int i = 0; i < 10000000; i++) {
			isWin = LotteryUtils.checkWinSsc5XingZuXuan30("1,1,2,9,9", "1549326780,6150493782");
		}
		long end = System.currentTimeMillis();
		System.out.println("计算结果：" + (isWin ? "已中奖，恭喜你，再接再厉。" : "未中奖，别灰心，再来一次。"));
		System.out.println("计算耗时：" + (end - start) + "ms");
	}

	/**
	 * 五星组选10
	 * 
	 * @author Terry
	 */
	public static void checkWinSsc5XingZuXuan10() {
		long start = System.currentTimeMillis();
		boolean isWin = false;
		for (int i = 0; i < 10000000; i++) {
			isWin = LotteryUtils.checkWinSsc5XingZuXuan10("1,1,1,1,5", "1549326780,6150493782");
		}
		long end = System.currentTimeMillis();
		System.out.println("计算结果：" + (isWin ? "已中奖，恭喜你，再接再厉。" : "未中奖，别灰心，再来一次。"));
		System.out.println("计算耗时：" + (end - start) + "ms");
	}

	/**
	 * 五星组选5
	 * 
	 * @author Terry
	 */
	public static void checkWinSsc5XingZuXuan5() {
		long start = System.currentTimeMillis();
		boolean isWin = false;
		for (int i = 0; i < 10000000; i++) {
			isWin = LotteryUtils.checkWinSsc5XingZuXuan5("1,1,1,1,0", "1549326780,6150493782");
		}
		long end = System.currentTimeMillis();
		System.out.println("计算结果：" + (isWin ? "已中奖，恭喜你，再接再厉。" : "未中奖，别灰心，再来一次。"));
		System.out.println("计算耗时：" + (end - start) + "ms");
	}

	/**
	 * 五星组选5
	 * 
	 * @author Terry
	 */
	public static void ssc4XinZuXuan4() {
		long start = System.currentTimeMillis();
		boolean isWin = false;
		for (int i = 0; i < 10000000; i++) {
			isWin = LotteryUtils.checkWinSscZuXuan4_20("1,5,1,1", "1549326780,6150493782", 2);
		}
		long end = System.currentTimeMillis();
		System.out.println("计算结果：" + (isWin ? "已中奖，恭喜你，再接再厉。" : "未中奖，别灰心，再来一次。"));
		System.out.println("计算耗时：" + (end - start) + "ms");
	}

	/**
	 * 五星组选5
	 * 
	 * @author Terry
	 */
	public static void ssc4XinZuXuan6() {
		long start = System.currentTimeMillis();
		boolean isWin = false;
		for (int i = 0; i < 10000000; i++) {
			isWin = LotteryUtils.checkWinSsc4XingZuXuan6("5,5,1,1", "1549326780");
		}
		long end = System.currentTimeMillis();
		System.out.println("计算结果：" + (isWin ? "已中奖，恭喜你，再接再厉。" : "未中奖，别灰心，再来一次。"));
		System.out.println("计算耗时：" + (end - start) + "ms");
	}

	/**
	 * 五星组选5
	 * 
	 * @author Terry
	 */
	public static void ssc4XinZuXuan12() {
		long start = System.currentTimeMillis();
		boolean isWin = false;
		for (int i = 0; i < 10000000; i++) {
			isWin = LotteryUtils.checkWinSsc4XingZuXuan12("5,5,0,1", "1549326780,6150493782");
		}
		long end = System.currentTimeMillis();
		System.out.println("计算结果：" + (isWin ? "已中奖，恭喜你，再接再厉。" : "未中奖，别灰心，再来一次。"));
		System.out.println("计算耗时：" + (end - start) + "ms");
	}

	/**
	 * 五星组选20
	 * 
	 * @author Terry
	 */
	public static void ssc4XinZuXuan20() {
		long start = System.currentTimeMillis();
		boolean isWin = false;
		for (int i = 0; i < 10000000; i++) {
			isWin = LotteryUtils.checkWinSscZuXuan4_20("5,5,5,1,0", "1549326780,6150493782", 3);
		}
		long end = System.currentTimeMillis();
		System.out.println("计算结果：" + (isWin ? "已中奖，恭喜你，再接再厉。" : "未中奖，别灰心，再来一次。"));
		System.out.println("计算耗时：" + (end - start) + "ms");
	}

	/**
	 * 大小单双
	 * 
	 * @author Terry
	 */
	public static void checkWinSscDaXiaoDanShuang() {
		long start = System.currentTimeMillis();
		boolean isWin = false;
		for (int i = 0; i < 10000000; i++) {
			isWin = LotteryUtils.checkWinSscDaXiaoDanShuang("1,1", "DA XIAO DAN SHUANG,DA XIAO DAN SHUANG");
		}
		long end = System.currentTimeMillis();
		System.out.println("计算结果：" + (isWin ? "已中奖，恭喜你，再接再厉。" : "未中奖，别灰心，再来一次。"));
		System.out.println("计算耗时：" + (end - start) + "ms");
	}

	/**
	 * 一帆风顺
	 * 
	 * @author Terry
	 */
	public static void checkWin1FanFengShun() {
		long start = System.currentTimeMillis();
		boolean isWin = false;
		for (int i = 0; i < 10000000; i++) {
			isWin = LotteryUtils.checkWin1FanFengShun("1,5,4,0,3", "1,4,9,6,3,2,5,8,7,0");
		}
		long end = System.currentTimeMillis();
		System.out.println("计算结果：" + (isWin ? "已中奖，恭喜你，再接再厉。" : "未中奖，别灰心，再来一次。"));
		System.out.println("计算耗时：" + (end - start) + "ms");
	}

	/**
	 * 好事成双
	 * 
	 * @author Terry
	 */
	public static void checkWinHaoShiChengShuang() {
		long start = System.currentTimeMillis();
		boolean isWin = false;
		for (int i = 0; i < 10000000; i++) {
			isWin = LotteryUtils.checkWinQuWei("1,5,4,3,4", "1,4,9,6,3,2,5,8,7,0", 2);
		}
		long end = System.currentTimeMillis();
		System.out.println("计算结果：" + (isWin ? "已中奖，恭喜你，再接再厉。" : "未中奖，别灰心，再来一次。"));
		System.out.println("计算耗时：" + (end - start) + "ms");
	}

	/**
	 * 三星报喜
	 * 
	 * @author Terry
	 */
	public static void checkWin3XingBaoXi() {
		long start = System.currentTimeMillis();
		boolean isWin = false;
		for (int i = 0; i < 10000000; i++) {
			isWin = LotteryUtils.checkWinQuWei("1,5,4,4,4", "1,4,9,6,3,2,5,8,7,0", 3);
		}
		long end = System.currentTimeMillis();
		System.out.println("计算结果：" + (isWin ? "已中奖，恭喜你，再接再厉。" : "未中奖，别灰心，再来一次。"));
		System.out.println("计算耗时：" + (end - start) + "ms");
	}

	/**
	 * 好事成双
	 * 
	 * @author Terry
	 */
	public static void checkWin4JiFaCai() {
		long start = System.currentTimeMillis();
		boolean isWin = false;
		for (int i = 0; i < 10000000; i++) {
			isWin = LotteryUtils.checkWinQuWei("1,5,5,5,5", "1,4,9,6,3,2,5,8,7,0", 4);
		}
		long end = System.currentTimeMillis();
		System.out.println("计算结果：" + (isWin ? "已中奖，恭喜你，再接再厉。" : "未中奖，别灰心，再来一次。"));
		System.out.println("计算耗时：" + (end - start) + "ms");
	}

	/**
	 * 二星直选和值
	 * 
	 * @author Terry
	 */
	public static void checkWinSsc2XingZhiXuanHeZhi() {
		long start = System.currentTimeMillis();
		boolean isWin = false;
		for (int i = 0; i < 10000000; i++) {
			isWin = LotteryUtils.checkWinSscZhiXuanHe("1,5", "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18");
		}
		long end = System.currentTimeMillis();
		System.out.println("计算结果：" + (isWin ? "已中奖，恭喜你，再接再厉。" : "未中奖，别灰心，再来一次。"));
		System.out.println("计算耗时：" + (end - start) + "ms");
	}

	/**
	 * 二星直选和值
	 * 
	 * @author Terry
	 */
	public static void checkWinSsc3XingHunHeZuXuan() {
		long start = System.currentTimeMillis();
		boolean isWin = false;
		for (int i = 0; i < 10000000; i++) {
			isWin = LotteryUtils.checkWinSsc3XingZuXuanHun("1,5,5", "123,456");
		}
		long end = System.currentTimeMillis();
		System.out.println("计算结果：" + (isWin ? "已中奖，恭喜你，再接再厉。" : "未中奖，别灰心，再来一次。"));
		System.out.println("计算耗时：" + (end - start) + "ms");
	}

	public static void main(String[] args) {
		// 一星定位胆
		// ssc1XingDingWei();
		// 二星直选单式
		// ssc2XingZhiXuanDan();
		// 二星直选和值
		// checkWinSsc2XingZhiXuanHeZhi();
		// 二星直选复式
		// ssc2XingZhiXuanFu();
		// 二星组选复式
		// ssc2XingZuXuanFu();
		// 三星直选单式
		// ssc3XingZhiXuanDan();
		// 三星直选复式
		// sscQian3ZhiXuanFu();
		// 三星组选3
		// ssc3XingZuXuan3();
		// 三星混合组选
		checkWinSsc3XingHunHeZuXuan();
		// 五星组选120
		// checkWinSsc5XingZuXuan120();
		// 五星组选60
		// checkWinSsc5XingZuXuan60();
		// 五星组选30
		// checkWinSsc5XingZuXuan30();
		// 五星组选10
		// checkWinSsc5XingZuXuan10();
		// 五星组选5
		// checkWinSsc5XingZuXuan5();
		// 四星组选4
		// ssc4XinZuXuan4();
		// 四星组选6
		// ssc4XinZuXuan6();
		// 四星组选12
		// ssc4XinZuXuan12();
		// 五星组选20
		// ssc4XinZuXuan20();
		// 大小单双
		// checkWinSscDaXiaoDanShuang();
		// 一帆风顺
		// checkWin1FanFengShun();
		// 好事成双
		// checkWinHaoShiChengShuang();
		// 三星报喜
		// checkWin3XingBaoXi();
		// 四季发财
		// checkWin4JiFaCai();
	}
}