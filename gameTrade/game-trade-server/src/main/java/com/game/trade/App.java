package com.game.trade;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.game.trade.algorithm.SSC;
import com.game.trade.model.OpenLottery;
import com.game.trade.util.Combination;
import com.game.trade.util.Common;
import com.game.trade.util.math;

/**
 * Hello world!
 *
 */
public class App {
	private static final Logger LOG = Logger.getLogger(App.class);

	public static void main(String[] args) {
		LOG.debug("Hello World!");

		// testCombination();
		 testOpenLottery();

		// int[] array0 = {1,1,1,1,5,5,8,9};
		// Common.SameInArray(array0);

		// int ret = Common.IsBigOrSmall(5);
		// LOG.info(ret);
		// ret = Common.IsBigOrSmall(4);
		// LOG.info(ret);
		//
		// ret = Common.IsSingelOrDouble(5);
		// LOG.info(ret);
		//
		// ret = Common.IsSingelOrDouble(4);
		// LOG.info(ret);

	}

	static void test3_Z_Sum() {
		int sum = 0;
		int n = 3;
		int[] ret = new int[3];
		int[] resCount = { 0 };

		for (int i = 0; i <= 27; i++) {
			sum = i;
			Common.PrintAllArrangeSum(sum, n, ret, resCount);
			LOG.info(i + "," + resCount[0]);

			resCount[0] = 0;
		}

	}

	static void testCombination() {

		int[] A = { 0, 1, 2, 3, 4 };
		int n = 5;
		int m = 5;
		ArrayList<Integer> t = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> arr = Combination.Combination(A, n, m, t);

		int i = 0;

		StringBuilder lsStar5I = new StringBuilder();
		lsStar5I.append(arr.get(i).get(0));
		lsStar5I.append(arr.get(i).get(1));
		lsStar5I.append(arr.get(i).get(2));
		lsStar5I.append(arr.get(i).get(3));
		lsStar5I.append(arr.get(i).get(4));

		LOG.debug(lsStar5I);

		// LOG.info(arr.get(i).get(0) + arr.get(i).get(1) + arr.get(i).get(2) +
		// arr.get(i).get(3) + arr.get(i).get(4) );

		return;

	}

	static void testOpenLottery() {
		SSC ssc = new SSC();

		// int[] winNo = { 0, 2, 2, 2, 1 };
		int[] winNo = { 7,2 };

		/*
		 * ***************************************************************** 5星
		 */

		// int[][] betNos = { { 0, 1, 2, 3, 4 }, { 0, 1, 2, 3, 5 }, { 0, 1, 2, 3, 6 }, {
		// 0, 1, 2, 3, 7 },
		// { 0, 1, 2, 3, 8 } };5星直选单式

		// int[][] betNos = { { 0, 1 }, { 0, 1 }, { 0, 1, 2, 3, 6 }, { 0, 1, 2, 3, 7 },
		// { 0, 1, 2, 3, 4 } };5星直选复式

		// int[][] betNos = { { 0, 1, 2, 3, 4, 5, 6 } };5星组选120

		// int[][] betNos = { { 0 }, { 9 } }; 5星组选5，10

		// int[][] betNos = { { 0, 1, 3, 4, 5 } }; // 5星二码不定位，三码不定位

		// int[][] betNos = { { 2, 1 } }; // 双星报喜

		// String lotteryCode = "10H11";
		// String lotteryCode = "10H12";
		// String lotteryCode = "10H21";

		// String lotteryCode = "10H22"; String lotteryName = "_5_Group_60";
		String lotteryCode = "10H23";
		// String lotteryName = "_5_Group_30";
		// String lotteryName = "_5_Group_20";
		// String lotteryName = "_5_Group_10";
		// String lotteryName = "_5_Group_5";
		// String lotteryName = "_5_2_nofix";
		// String lotteryName = "_5_3_nofix";
		// String lotteryName = "_5_1_nofix";
		// String lotteryName = "_5_double_nofix";//好事成双
		// String lotteryName = "_5_treble_nofix";//三星报喜
		// String lotteryName = "_5_fourfold_nofix";//四季发财

		/*
		 * ***************************************************************** 4星
		 */
		// int[][] betNos = { { 0, 1, 2, 3 }, { 0, 1, 2, 4 }, { 0, 1, 2, 5 }, {
		// 0, 1, 2, 6 },
		// { 0, 1, 2, 7 } };//4星直选单式

		// int[][] betNos = { { 0, 1 }, { 0, 1 }, { 0, 1, 2, 3, 6 }, { 0, 1, 2, 3, 7 }
		// };//4星直选复式

		// int[][] betNos = { { 2} ,{ 1 } };// 4星组选24

		// int[][] betNos = { { 0, 1, 2, 3 }, { 0, 1, 2, 4 }, { 0, 1, 2, 5 }, {
		// 0, 1, 2, 6 },
		// { 0, 1, 2, 7 } };//4星直选单式

		// int[][] betNos = { { 0, 1 }, { 0, 1 }, { 0, 1, 2, 3, 6 }, { 0, 1, 2, 3, 7 }
		// };//4星直选复式

		/*
		 * ***************************************************************** 3星
		 */
		// int[][] betNos = { { 0, 1, 2 }, { 1, 2, 4 }, { 0, 2, 3 },};//3星直选单式

		// int[][] betNos = { { 0, 1,2 }, { 0, 2 }, { 0, 1, 2, 3, 6 } };//3星直选复式
		// int[][] betNos = { { 0, 1, 2 } };// 3星组3

		// int[][] betNos = { { 0, 1 }, { 0, 1 }, { 0, 1 }, { 0, 1 }, { 0, 1 }
		// };//2星组选单式

		int[][] betNos = { {  1, 2, 9 } };
		// 2星大小单双 16注 2X2X2X2 10位大小 10位单双，个位大小，个位单双

		// String lotteryName = "_4_Z_Dan";// 4星直选单式
		// String lotteryName = "_4_Z_Fu";// 4星直选复式
		// String lotteryName = "_4_Group_24";// 4星组选24
		// String lotteryName = "_4_Group_12";// 4星组选12
		// String lotteryName = "_4_Group_6";// 4星组选6
		// String lotteryName = "_4_Group_4";// 4星组选4
		// String lotteryName = "_3_Z_Dan";// 3星直选单式
		// String lotteryName = "_3_Z_Fu";// 3星直选复式
		// String lotteryName = "_3_Group_3";// 3星组3
		// String lotteryName = "_3_Group_6";// 3星组6
		// String lotteryName = "_3_Group_Mix";// 3星组mix
		// String lotteryName = "_3_Treble";// 3星豹子
		// String lotteryName = "_3_double";// 3星对子
		// String lotteryName = "_3_Sequence";// 3星顺子
		// String lotteryName = "_2_Group_Fu";// 2星复式

		// String lotteryName = "_2_Group_Dan";// 2星单式
		// String lotteryName = "_1_Fix_Dan";// 1星定位胆

		//String lotteryName = "_2_BigSmall_Double_Sigle";// 2星大小单双
		//String lotteryName = "_3_Group_Sum";// 3星组选和值
		//String lotteryName = "_3_Z_Sum";// 3星直选和值
		//String lotteryName = "_2_Group_Sum";// 2星组选和值
		String lotteryName = "_2_Z_Sum";// 2星直选和值
				
		

		// OpenLottery ol = (OpenLottery) ssc.reflectMethod(winNo, betNos, lotteryCode,
		// lotteryName);

		/*
		 * 性能比较
		 *
		 * String betNostr1 = "11152,11327,11469,11975"; String winNostr1 = "12345";
		 * 
		 * int[] winNo2 = {1,2,3,4,5}; int[][] betNos2 = {
		 * {1,1,1,5,2},{1,1,3,2,7},{1,1,4,6,9},{1,1,9,7,5} };
		 */

		// 伪代码
		long startTime = System.currentTimeMillis(); // 获取开始时间

		OpenLottery ol = (OpenLottery) ssc.reflectMethod(winNo, betNos, lotteryCode, lotteryName);

		long endTime = System.currentTimeMillis(); // 获取结束时间
		System.out.println("程序运行时间： " + (endTime - startTime) + "ms");

		LOG.info("bet=" + ol.betNum);
		LOG.info("win=" + ol.winNum);
		LOG.info("winlvl=" + ol.winLvl);

	}

	/**
	 * 组选60
	 * 
	 * @param betStr
	 *            12,456
	 * @param betData
	 *            2,2,4,5,6
	 * @return
	 */
	// public static boolean zuXuan60(final String betStr, final String betData) {
	// if(CommonUtils.getBetDataNum(betData) == 4){
	// String[] betStrs = betStr.split(",");
	// String[] betDatas = betData.split(",");
	// char[] charBet1 = betStrs[0].toCharArray();
	// char[] charBet2 = betStrs[1].toCharArray();
	//
	// ConcurrentHashMap<String,String> betMap = new
	// ConcurrentHashMap<String,String>();
	// for(int i=0;i<betDatas.length;i++){
	// betMap.put(betDatas[i], betDatas[i]);
	// }
	// Arrays.sort(betDatas);
	// char sameNum [] = null;
	// for (int i = 0; i < betDatas.length; i++) {
	// if (betDatas[i].equals(betDatas[i + 1])) {
	// betMap.remove(betDatas[i]);
	// sameNum = betDatas[i].toCharArray();
	// break;
	// }
	// }
	//
	// if(CommonUtils.getSamesNum(charBet1, sameNum) == 1){
	// StringBuffer noSameNum = new StringBuffer();
	// for(String key:betMap.keySet()){
	// noSameNum.append(key);
	// }
	// if(CommonUtils.getSamesNum(charBet2,noSameNum.toString().toCharArray()) ==
	// 3){
	// return true;
	// }
	// }
	//
	// }
	// return false;
	//
	// }

	/**
	 * 直选单式
	 * 
	 * @param betStr
	 *            投注列表：152,327,469,975
	 * @param betData
	 *            1,3,4
	 * @return 中奖注数
	 */
	public static int zxDanShi(final String betStr, final String betData) {
		int n = 0;
		String[] betStrs = betStr.split(",");
		String betDatas = betData.replaceAll(",", "");
		for (String str : betStrs) {
			if (betDatas.equals(str))
				n = n + 1;
		}
		return n;
	}

	/**
	 * 5星直选单式
	 * 
	 * @param winNo
	 * @param betNo
	 * @return
	 */
	public static OpenLottery _5_Z_Dan(int[] winNo, int[][] betNos) {
		// TODO Auto-generated method stub

		OpenLottery ol = new OpenLottery();
		ol.betNum = betNos.length;

		int wbCount = 0;// win bet count

		for (int i = 0; i < betNos.length; i++) {

			// StringBuilder lsStar5I = new StringBuilder();
			// lsStar5I.append(betNos[i][0]);
			// lsStar5I.append(betNos[i][1]);
			// lsStar5I.append(betNos[i][2]);
			// lsStar5I.append(betNos[i][3]);
			// lsStar5I.append(betNos[i][4]);
			//
			// LOG.debug(lsStar5I);

			if (betNos[i][0] == winNo[0] && betNos[i][1] == winNo[1] && betNos[i][2] == winNo[2]
					&& betNos[i][3] == winNo[3] && betNos[i][4] == winNo[4])// win
			{
				wbCount++;
			}
		}

		ol.winNum = wbCount;

		return ol;

	}

}
