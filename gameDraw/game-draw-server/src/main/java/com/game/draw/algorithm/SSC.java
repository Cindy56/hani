package com.game.draw.algorithm;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.draw.model.OpenLottery;
import com.game.draw.model.Star2;
import com.game.draw.model.Star3;
import com.game.draw.model.Star4;
import com.game.draw.model.Star5;
import com.game.draw.util.BetNoSeparate;
import com.game.draw.util.Combination;
import com.game.draw.util.Common;
import com.game.draw.util.Constant;

public class SSC {

	private static final Logger LOG = Logger.getLogger(SSC.class);

	public Object reflectMethod(int[] winNo, int[][] betNos, String lotteryCode, String lotteryName) {
		Class clazz = SSC.class;
		Object obj = null;
		try {
			obj = clazz.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			LOG.error("", e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			LOG.error("", e);
		}

		Method method = null;
		try {
			method = clazz.getMethod(lotteryName, int[].class, int[][].class);

			return method.invoke(obj, winNo, betNos);

		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			LOG.error("", e);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			LOG.error("", e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			LOG.error("", e);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			LOG.error("", e);
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			LOG.error("", e);
		}

		return null;

	}

	/**
	 * 时时彩开奖 五星可扩展为四星
	 * 
	 * @param winNo
	 *            本期时时彩开奖号码
	 * @param betNo
	 *            投注号码
	 * @param lotteryCode
	 *            彩种编码
	 * @return
	 */
	public OpenLottery openSSCLottery(int[] winNo, int[][] betNos, String lotteryCode) {

		int ret = 0;
		OpenLottery ol = new OpenLottery();
		// if (lotteryCode.equals("10H11")) {
		// /*************************************************
		// * 五星
		// *********************************************/
		// // 五星直选复式
		// ol = _5_Z_Fu(winNo, betNos);
		//
		// } else if (lotteryCode.equals("10H12")) {// 五星直选单式
		// ol = _5_Z_Dan(winNo, betNos);
		// } else if (lotteryCode.equals("10H21")) {// 五星组选120
		// ol = _5_Group_120(winNo, betNos);
		// } else if (lotteryCode.equals("10H22")) {// 组选60
		// ol = _5_Group_60(winNo, betNos);
		// } else if (lotteryCode.equals("10H23")) {// 组选30
		// ol = _5_Group_301(winNo, betNos);
		// }

		return ol;

	}

	/**
	 * 5星 3No no fix 选择3个号，包含即中奖。
	 * 
	 * @param winNo
	 * @param betNos
	 * @return
	 */
	public OpenLottery _5_3_nofix(int[] winNo, int[][] betNos) {
		// TODO Auto-generated method stub

		// separate
		List<Star3> lsStar3 = BetNoSeparate.separate5_3_nofix(betNos);

		OpenLottery ol = new OpenLottery();
		ol.betNum = lsStar3.size();

		// sort winNo
		Arrays.sort(winNo);

		// compare
		int wbCount = 0;
		for (int i = 0; i < lsStar3.size(); i++) {
			if (Common.IsInArray(lsStar3.get(i).star3[0], winNo) && Common.IsInArray(lsStar3.get(i).star3[1], winNo)
					&& Common.IsInArray(lsStar3.get(i).star3[2], winNo)) {

				wbCount++;
			}

		}

		ol.winNum = wbCount;

		return ol;
	}

	/**
	 * 5星 2No no fix 选择2个号，包含即中奖。
	 * 
	 * @param winNo
	 * @param betNos
	 * @return
	 */
	public OpenLottery _5_2_nofix(int[] winNo, int[][] betNos) {
		// TODO Auto-generated method stub

		// separate
		List<Star2> lsStar2 = BetNoSeparate.separate5_2_nofix(betNos);

		OpenLottery ol = new OpenLottery();
		ol.betNum = lsStar2.size();

		// sort winNo
		Arrays.sort(winNo);

		// compare
		int wbCount = 0;
		for (int i = 0; i < lsStar2.size(); i++) {
			if (Common.IsInArray(lsStar2.get(i).star2[0], winNo) && Common.IsInArray(lsStar2.get(i).star2[1], winNo)) {

				wbCount++;
			}

		}

		ol.winNum = wbCount;

		return ol;
	}

	/**
	 * 5星 1No no fix 选择1个号，包含即中奖。一帆风顺
	 * 
	 * @param winNo
	 * @param betNos
	 * @return
	 */
	public OpenLottery _5_1_nofix(int[] winNo, int[][] betNos) {
		// TODO Auto-generated method stub

		// separate
		// List<Integer> lsStar1 = BetNoSeparate.separate5_1_nofix(betNos);
		int[] betNo = betNos[0];

		OpenLottery ol = new OpenLottery();
		ol.betNum = betNo.length;

		// sort winNo
		Arrays.sort(winNo);

		// compare
		int wbCount = 0;
		for (int i = 0; i < betNo.length; i++) {
			if (Common.IsInArray(betNo[i], winNo)) {

				wbCount++;
			}

		}

		ol.winNum = wbCount;

		return ol;
	}

	/**
	 * 1doubleNo no fix 选择1个号，如果出现2次， 即中奖。好事成双
	 * 
	 * @param winNo
	 * @param betNos
	 * @return
	 */
	public OpenLottery _5_double_nofix(int[] winNo, int[][] betNos) {
		// TODO Auto-generated method stub

		// separate
		// List<Integer> lsStar1 = BetNoSeparate.separate5_1_nofix(betNos);
		int[] betNo = betNos[0];

		OpenLottery ol = new OpenLottery();
		ol.betNum = betNo.length;

		// sort winNo
		Arrays.sort(winNo);

		// compare
		int wbCount = 0;
		for (int i = 0; i < betNo.length; i++) {
			if (Common.CountInArray(betNo[i], winNo) >= 2) {

				wbCount++;
			}

		}

		ol.winNum = wbCount;

		return ol;
	}

	/**
	 * 1trebleNo no fix 选择1个号，如果出现3次， 即中奖。三星报喜
	 * 
	 * @param winNo
	 * @param betNos
	 * @return
	 */
	public OpenLottery _5_treble_nofix(int[] winNo, int[][] betNos) {
		// TODO Auto-generated method stub

		// separate
		// List<Integer> lsStar1 = BetNoSeparate.separate5_1_nofix(betNos);
		int[] betNo = betNos[0];

		OpenLottery ol = new OpenLottery();
		ol.betNum = betNo.length;

		// sort winNo
		Arrays.sort(winNo);

		// compare
		int wbCount = 0;
		for (int i = 0; i < betNo.length; i++) {
			if (Common.CountInArray(betNo[i], winNo) >= 3) {

				wbCount++;
			}

		}

		ol.winNum = wbCount;

		return ol;
	}

	/**
	 * 1fourfoldNo no fix 选择1个号，如果出现4次， 即中奖。四季发财
	 * 
	 * @param winNo
	 * @param betNos
	 * @return
	 */
	public OpenLottery _5_fourfold_nofix(int[] winNo, int[][] betNos) {
		// TODO Auto-generated method stub

		// separate
		// List<Integer> lsStar1 = BetNoSeparate.separate5_1_nofix(betNos);
		int[] betNo = betNos[0];

		OpenLottery ol = new OpenLottery();
		ol.betNum = betNo.length;

		// sort winNo
		Arrays.sort(winNo);

		// compare
		int wbCount = 0;
		for (int i = 0; i < betNo.length; i++) {
			if (Common.CountInArray(betNo[i], winNo) >= 4) {

				wbCount++;
			}

		}

		ol.winNum = wbCount;

		return ol;
	}

	/**
	 * 5星组选5 选择1个四重号，和1个单号。号码一致，顺序不限。
	 * 
	 * @param winNo
	 * @param betNos
	 * @return
	 */
	public OpenLottery _5_Group_5(int[] winNo, int[][] betNos) {
		// TODO Auto-generated method stub

		// separate
		List<Star5> lsStar5 = BetNoSeparate.separateGroup5(betNos);

		OpenLottery ol = new OpenLottery();
		ol.betNum = lsStar5.size();

		// sort winNo
		Arrays.sort(winNo);

		// compare
		int wbCount = 0;
		for (int i = 0; i < lsStar5.size(); i++) {
			if (lsStar5.get(i).star5[0] == winNo[0] && lsStar5.get(i).star5[1] == winNo[1]
					&& lsStar5.get(i).star5[2] == winNo[2] && lsStar5.get(i).star5[3] == winNo[3]
					&& lsStar5.get(i).star5[4] == winNo[4])// win
			{

				wbCount++;
			}

		}

		ol.winNum = wbCount;

		return ol;
	}

	/**
	 * 5星组选10 选择1个三重号，和1个二重号。号码一致，顺序不限。
	 * 
	 * @param winNo
	 * @param betNos
	 * @return
	 */
	public OpenLottery _5_Group_10(int[] winNo, int[][] betNos) {
		// TODO Auto-generated method stub

		// separate
		List<Star5> lsStar5 = BetNoSeparate.separateGroup10(betNos);

		OpenLottery ol = new OpenLottery();
		ol.betNum = lsStar5.size();

		// sort winNo
		Arrays.sort(winNo);

		// compare
		int wbCount = 0;
		for (int i = 0; i < lsStar5.size(); i++) {
			if (lsStar5.get(i).star5[0] == winNo[0] && lsStar5.get(i).star5[1] == winNo[1]
					&& lsStar5.get(i).star5[2] == winNo[2] && lsStar5.get(i).star5[3] == winNo[3]
					&& lsStar5.get(i).star5[4] == winNo[4])// win
			{

				wbCount++;
			}

		}

		ol.winNum = wbCount;

		return ol;
	}

	/**
	 * 5星组选20 选择1个三重号，和2个单号。号码一致，顺序不限。
	 * 
	 * @param winNo
	 * @param betNos
	 * @return
	 */
	public OpenLottery _5_Group_20(int[] winNo, int[][] betNos) {
		// TODO Auto-generated method stub

		// separate
		List<Star5> lsStar5 = BetNoSeparate.separateGroup20(betNos);

		OpenLottery ol = new OpenLottery();
		ol.betNum = lsStar5.size();

		// sort winNo
		Arrays.sort(winNo);

		// compare
		int wbCount = 0;
		for (int i = 0; i < lsStar5.size(); i++) {
			if (lsStar5.get(i).star5[0] == winNo[0] && lsStar5.get(i).star5[1] == winNo[1]
					&& lsStar5.get(i).star5[2] == winNo[2] && lsStar5.get(i).star5[3] == winNo[3]
					&& lsStar5.get(i).star5[4] == winNo[4])// win
			{

				wbCount++;
			}

		}

		ol.winNum = wbCount;

		return ol;
	}

	/**
	 * 5星组选30 选择2个二重号，和一个单号。号码一致，顺序不限。
	 * 
	 * @param winNo
	 * @param betNos
	 * @return
	 */
	public OpenLottery _5_Group_30(int[] winNo, int[][] betNos) {
		// TODO Auto-generated method stub

		// separate
		List<Star5> lsStar5 = BetNoSeparate.separateGroup30(betNos);

		OpenLottery ol = new OpenLottery();
		ol.betNum = lsStar5.size();

		// sort winNo
		Arrays.sort(winNo);

		// compare
		int wbCount = 0;
		for (int i = 0; i < lsStar5.size(); i++) {
			if (lsStar5.get(i).star5[0] == winNo[0] && lsStar5.get(i).star5[1] == winNo[1]
					&& lsStar5.get(i).star5[2] == winNo[2] && lsStar5.get(i).star5[3] == winNo[3]
					&& lsStar5.get(i).star5[4] == winNo[4])// win
			{

				wbCount++;
			}

		}

		ol.winNum = wbCount;

		return ol;
	}

	/**
	 * 五星组选60 选择一个二重号，和三个单号组成一注。号码一致，顺序不限
	 * 
	 * @param winNo
	 * @param betNos
	 * @return
	 */
	public OpenLottery _5_Group_60(int[] winNo, int[][] betNos) {
		// TODO Auto-generated method stub

		// separate
		List<Star5> lsStar5 = BetNoSeparate.separateGroup60(betNos);

		OpenLottery ol = new OpenLottery();
		ol.betNum = lsStar5.size();

		// sort winNo
		Arrays.sort(winNo);

		// compare
		int wbCount = 0;
		for (int i = 0; i < lsStar5.size(); i++) {
			if (lsStar5.get(i).star5[0] == winNo[0] && lsStar5.get(i).star5[1] == winNo[1]
					&& lsStar5.get(i).star5[2] == winNo[2] && lsStar5.get(i).star5[3] == winNo[3]
					&& lsStar5.get(i).star5[4] == winNo[4])// win
			{

				wbCount++;
			}

		}

		ol.winNum = wbCount;

		return ol;
	}

	/**
	 * 五星组选120 选择5个投注号码，号码一致，顺序不限
	 * 
	 * @param winNo
	 * @param betNo
	 * @return
	 */
	public OpenLottery _5_Group_120(int[] winNo, int[][] betNos) {
		// TODO Auto-generated method stub

		int[] betNo = betNos[0];

		// sort winNo
		Arrays.sort(winNo);

		// get combination
		ArrayList<Integer> t = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> arrCombs = Combination.Combination(betNo, betNo.length, 5, t);

		OpenLottery ol = new OpenLottery();
		ol.betNum = arrCombs.size();

		// compare
		int wbCount = 0;
		for (int i = 0; i < arrCombs.size(); i++) {
			if (arrCombs.get(i).get(0) == winNo[0] && arrCombs.get(i).get(1) == winNo[1]
					&& arrCombs.get(i).get(2) == winNo[2] && arrCombs.get(i).get(3) == winNo[3]
					&& arrCombs.get(i).get(4) == winNo[4])// win
			{

				wbCount++;
			}

		}

		ol.winNum = wbCount;

		return ol;
	}

	/**
	 * 5星直选单式
	 * 
	 * @param winNo
	 * @param betNo
	 * @return
	 */
	public OpenLottery _5_Z_Dan(int[] winNo, int[][] betNos) {
		// TODO Auto-generated method stub

		OpenLottery ol = new OpenLottery();
		ol.betNum = betNos.length;

		int wbCount = 0;// win bet count

		for (int i = 0; i < betNos.length; i++) {

			StringBuilder lsStar5I = new StringBuilder();
			lsStar5I.append(betNos[i][0]);
			lsStar5I.append(betNos[i][1]);
			lsStar5I.append(betNos[i][2]);
			lsStar5I.append(betNos[i][3]);
			lsStar5I.append(betNos[i][4]);

			LOG.debug(lsStar5I);

			if (betNos[i][0] == winNo[0] && betNos[i][1] == winNo[1] && betNos[i][2] == winNo[2]
					&& betNos[i][3] == winNo[3] && betNos[i][4] == winNo[4])// win
			{
				wbCount++;
			}
		}

		ol.winNum = wbCount;

		return ol;

	}

	/**
	 * 5星直选复式 betNos 在前端已经拆过号，这里只需要对比是否中奖
	 * 
	 * @return
	 */
	public OpenLottery _5_Z_Fu(int[] winNo, int[][] betNos) {

		OpenLottery ol = new OpenLottery();

		List<Star5> lsStar5 = BetNoSeparate.separateStar5(betNos);

		ol.betNum = lsStar5.size();

		int wbCount = 0;// win bet count

		for (int i = 0; i < lsStar5.size(); i++) {

			StringBuilder lsStar5I = new StringBuilder();
			lsStar5I.append(lsStar5.get(i).star5[0]);
			lsStar5I.append(lsStar5.get(i).star5[1]);
			lsStar5I.append(lsStar5.get(i).star5[2]);
			lsStar5I.append(lsStar5.get(i).star5[3]);
			lsStar5I.append(lsStar5.get(i).star5[4]);

			LOG.debug(lsStar5I);

			if (lsStar5.get(i).star5[0] == winNo[0] && lsStar5.get(i).star5[1] == winNo[1]
					&& lsStar5.get(i).star5[2] == winNo[2] && lsStar5.get(i).star5[3] == winNo[3]
					&& lsStar5.get(i).star5[4] == winNo[4])// win
			{
				wbCount++;
			}
		}

		ol.winNum = wbCount;

		return ol;
	}

	/**
	 * 5星直选和值
	 * 
	 * @param winNo
	 * @param betNos
	 *            { 1,1,0,0,1... }投注和值数
	 * @return
	 */
	public OpenLottery _5_Z_Sum(int[] winNo, int[][] betNos) {

		OpenLottery ol = new OpenLottery();

		// separate
		for (int i = 0; i < betNos[0].length; i++) {
			if (betNos[0][i] == 1)
				ol.betNum += Constant.S5_Z_Sum[i][1];
		}

		int winSum = Common.SumOfArray(winNo);

		if (Common.IsInArray(winSum, betNos[0])) {
			ol.winNum++;
		}

		return ol;
	}

	/*
	 * **********************************************************************
	 */

	/**
	 * 4星直选单式
	 * 
	 * @param winNo
	 * @param betNo
	 * @return
	 */
	public OpenLottery _4_Z_Dan(int[] winNo, int[][] betNos) {
		// TODO Auto-generated method stub

		OpenLottery ol = new OpenLottery();
		ol.betNum = betNos.length;

		int wbCount = 0;// win bet count

		for (int i = 0; i < betNos.length; i++) {

			StringBuilder lsStar5I = new StringBuilder();
			lsStar5I.append(betNos[i][0]);
			lsStar5I.append(betNos[i][1]);
			lsStar5I.append(betNos[i][2]);
			lsStar5I.append(betNos[i][3]);

			LOG.debug(lsStar5I);

			if (betNos[i][0] == winNo[1] && betNos[i][1] == winNo[2] && betNos[i][2] == winNo[3]
					&& betNos[i][3] == winNo[4])// win
			{
				wbCount++;
			}
		}

		ol.winNum = wbCount;

		return ol;

	}

	/**
	 * 4星直选复式 betNos 在前端已经拆过号，这里只需要对比是否中奖
	 * 
	 * @return
	 */
	public OpenLottery _4_Z_Fu(int[] winNo, int[][] betNos) {

		OpenLottery ol = new OpenLottery();

		List<Star4> lsStar4 = BetNoSeparate.separateStar4(betNos);

		ol.betNum = lsStar4.size();

		int wbCount = 0;// win bet count

		for (int i = 0; i < lsStar4.size(); i++) {

			StringBuilder lsStar5I = new StringBuilder();
			lsStar5I.append(lsStar4.get(i).star4[0]);
			lsStar5I.append(lsStar4.get(i).star4[1]);
			lsStar5I.append(lsStar4.get(i).star4[2]);
			lsStar5I.append(lsStar4.get(i).star4[3]);

			LOG.debug(lsStar5I);

			if (lsStar4.get(i).star4[0] == winNo[1] && lsStar4.get(i).star4[1] == winNo[2]
					&& lsStar4.get(i).star4[2] == winNo[3] && lsStar4.get(i).star4[3] == winNo[4])// win
			{
				wbCount++;
			}
		}

		ol.winNum = wbCount;

		return ol;
	}

	/**
	 * 四星组选24 选择4个投注号码，号码一致，顺序不限
	 * 
	 * @param winNo
	 * @param betNo
	 * @return
	 */
	public OpenLottery _4_Group_24(int[] winNo5, int[][] betNos) {
		// TODO Auto-generated method stub

		int[] betNo = betNos[0];

		// sort winNo
		int[] winNo = new int[4];
		winNo[0] = winNo5[1];
		winNo[1] = winNo5[2];
		winNo[2] = winNo5[3];
		winNo[3] = winNo5[4];

		Arrays.sort(winNo);

		// get combination
		ArrayList<Integer> t = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> arrCombs = Combination.Combination(betNo, betNo.length, 4, t);

		OpenLottery ol = new OpenLottery();
		ol.betNum = arrCombs.size();

		// compare
		int wbCount = 0;
		for (int i = 0; i < arrCombs.size(); i++) {
			if (arrCombs.get(i).get(0) == winNo[0] && arrCombs.get(i).get(1) == winNo[1]
					&& arrCombs.get(i).get(2) == winNo[2] && arrCombs.get(i).get(3) == winNo[3])// win
			{

				wbCount++;
			}

		}

		ol.winNum = wbCount;

		return ol;
	}

	/**
	 * 四星组选12 选择一个二重号，和2个单号组成一注。号码一致，顺序不限
	 * 
	 * @param winNo
	 * @param betNos
	 * @return
	 */
	public OpenLottery _4_Group_12(int[] winNo5, int[][] betNos) {
		// TODO Auto-generated method stub

		// separate
		List<Star4> lsStar4 = BetNoSeparate.separateGroup12(betNos);

		OpenLottery ol = new OpenLottery();
		ol.betNum = lsStar4.size();

		// sort winNo
		int[] winNo = new int[4];
		winNo[0] = winNo5[1];
		winNo[1] = winNo5[2];
		winNo[2] = winNo5[3];
		winNo[3] = winNo5[4];

		Arrays.sort(winNo);

		// compare
		int wbCount = 0;
		for (int i = 0; i < lsStar4.size(); i++) {
			if (lsStar4.get(i).star4[0] == winNo[0] && lsStar4.get(i).star4[1] == winNo[1]
					&& lsStar4.get(i).star4[2] == winNo[2] && lsStar4.get(i).star4[3] == winNo[3]
					&& lsStar4.get(i).star4[4] == winNo[4])// win
			{

				wbCount++;
			}

		}

		ol.winNum = wbCount;

		return ol;
	}

	/**
	 * 4星组选6 选择2个二重号。号码一致，顺序不限。
	 * 
	 * @param winNo
	 * @param betNos
	 * @return
	 */
	public OpenLottery _4_Group_6(int[] winNo5, int[][] betNos) {
		// TODO Auto-generated method stub

		// separate
		List<Star4> lsStar4 = BetNoSeparate.separateS4Group6(betNos);

		OpenLottery ol = new OpenLottery();
		ol.betNum = lsStar4.size();

		// sort winNo
		int[] winNo = { winNo5[1], winNo5[2], winNo5[3], winNo5[4] };
		Arrays.sort(winNo);

		// compare
		int wbCount = 0;
		for (int i = 0; i < lsStar4.size(); i++) {

			Star4 s4 = lsStar4.get(i);

			if (s4.star4[0] == winNo[0] && s4.star4[1] == winNo[1] && s4.star4[2] == winNo[2]
					&& s4.star4[3] == winNo[3])// win
			{

				wbCount++;
			}

		}

		ol.winNum = wbCount;

		return ol;
	}

	/**
	 * 4星组选4 选择1个三重号，和1个单号。号码一致，顺序不限。
	 * 
	 * @param winNo
	 * @param betNos
	 * @return
	 */
	public OpenLottery _4_Group_4(int[] winNo5, int[][] betNos) {
		// TODO Auto-generated method stub

		// separate
		List<Star4> lsStar4 = BetNoSeparate.separateGroup4(betNos);

		OpenLottery ol = new OpenLottery();
		ol.betNum = lsStar4.size();

		// sort winNo
		int[] winNo = { winNo5[1], winNo5[2], winNo5[3], winNo5[4] };
		Arrays.sort(winNo);

		// compare
		int wbCount = 0;
		for (int i = 0; i < lsStar4.size(); i++) {

			Star4 s4 = lsStar4.get(i);

			if (s4.star4[0] == winNo[0] && s4.star4[1] == winNo[1] && s4.star4[2] == winNo[2]
					&& s4.star4[3] == winNo[3])// win
			{

				wbCount++;
			}

		}

		ol.winNum = wbCount;

		return ol;
	}

	/*
	 * ********************************************************************** 3星
	 */

	/**
	 * 3星直选单式（前，中，后）
	 * 
	 * @param winNo
	 *            3位
	 * @param betNo
	 * @return
	 */
	public OpenLottery _3_Z_Dan(int[] winNo, int[][] betNos) {
		// TODO Auto-generated method stub

		OpenLottery ol = new OpenLottery();
		ol.betNum = betNos.length;

		int wbCount = 0;// win bet count

		for (int i = 0; i < betNos.length; i++) {

			if (betNos[i][0] == winNo[0] && betNos[i][1] == winNo[1] && betNos[i][2] == winNo[2])// win
			{
				wbCount++;
			}
		}

		ol.winNum = wbCount;

		return ol;

	}

	/**
	 * 3星直选复式 betNos 在前端已经拆过号，这里只需要对比是否中奖
	 * 
	 * @return
	 */
	public OpenLottery _3_Z_Fu(int[] winNo, int[][] betNos) {

		OpenLottery ol = new OpenLottery();

		List<Star3> lsStar3 = BetNoSeparate.separateStar3(betNos);

		ol.betNum = lsStar3.size();

		int wbCount = 0;// win bet count

		for (int i = 0; i < lsStar3.size(); i++) {

			Star3 s3 = lsStar3.get(i);

			if (s3.star3[0] == winNo[0] && s3.star3[1] == winNo[1] && s3.star3[2] == winNo[2])// win
			{
				wbCount++;
			}
		}

		ol.winNum = wbCount;

		return ol;
	}

	/**
	 * 三星组选3 选择一个二重号，和1个单号组成一注。号码一致，顺序不限
	 * 
	 * @param winNo
	 *            3位开奖号（前中后）
	 * @param betNos
	 * @return
	 */
	public OpenLottery _3_Group_3(int[] winNo, int[][] betNos) {
		// TODO Auto-generated method stub

		// separate
		List<Star3> lsStar3 = BetNoSeparate.separateGroup3(betNos);

		OpenLottery ol = new OpenLottery();
		ol.betNum = lsStar3.size();

		// sort winNo
		Arrays.sort(winNo);

		// compare
		int wbCount = 0;
		for (int i = 0; i < lsStar3.size(); i++) {

			Star3 s3 = lsStar3.get(i);
			if (s3.star3[0] == winNo[0] && s3.star3[1] == winNo[1] && s3.star3[2] == winNo[2])// win
			{

				wbCount++;
			}

		}

		ol.winNum = wbCount;

		return ol;
	}

	/**
	 * 三星组选6 选择3个单号组成一注。号码一致，顺序不限
	 * 
	 * @param winNo
	 *            3位开奖号（前中后）
	 * @param betNos
	 * @return
	 */
	public OpenLottery _3_Group_6(int[] winNo, int[][] betNos) {
		// TODO Auto-generated method stub

		// separate
		List<Star3> lsStar3 = BetNoSeparate.separateS3Group6(betNos);

		OpenLottery ol = new OpenLottery();
		ol.betNum = lsStar3.size();

		// sort winNo
		Arrays.sort(winNo);

		// compare
		int wbCount = 0;
		for (int i = 0; i < lsStar3.size(); i++) {

			Star3 s3 = lsStar3.get(i);

			if (s3.star3[0] == winNo[0] && s3.star3[1] == winNo[1] && s3.star3[2] == winNo[2])// win
			{

				wbCount++;
			}

		}

		ol.winNum = wbCount;

		return ol;
	}

	/**
	 * 三星混合组选 选择3个单号组成一注。号码一致，顺序不限
	 * 
	 * @param winNo
	 *            3位开奖号（前中后）
	 * @param betNos
	 * @return
	 */
	public OpenLottery _3_Group_Mix(int[] winNo, int[][] betNos) {
		// TODO Auto-generated method stub

		// get betnos
		// int[][] arrStar3 = betNos[0];

		OpenLottery ol = new OpenLottery();
		ol.betNum = betNos.length;

		// if treble, win zero
		int sameInArray = Common.SameInArray(winNo);
		if (sameInArray >= 3) {
			ol.winNum = 0;
			return ol;
		}

		else if (sameInArray == 2) {
			ol.winLvl = 1;
		} else {
			ol.winLvl = 2;
		}

		// sort winNo
		Arrays.sort(winNo);

		// compare
		int wbCount = 0;
		for (int i = 0; i < betNos.length; i++) {

			int[] arr3 = betNos[i];

			Arrays.sort(arr3);

			if (arr3[0] == winNo[0] && arr3[1] == winNo[1] && arr3[2] == winNo[2])// win
			{

				wbCount++;
			}

		}

		ol.winNum = wbCount;

		return ol;
	}

	/**
	 * 三星豹子
	 * 
	 * @param winNo
	 *            3位开奖号（前中后）
	 * @param betNos
	 * @return
	 */
	public OpenLottery _3_Treble(int[] winNo, int[][] betNos) {

		OpenLottery ol = new OpenLottery();
		ol.betNum = 1;

		// if treble, win zero
		int sameInArray = Common.SameInArray(winNo);
		if (sameInArray >= 3) {
			ol.winNum = 1;
			return ol;
		}

		ol.winNum = 0;
		return ol;

	}

	/**
	 * 三星对子
	 * 
	 * @param winNo
	 *            3位开奖号（前中后）
	 * @param betNos
	 * @return
	 */
	public OpenLottery _3_double(int[] winNo, int[][] betNos) {

		OpenLottery ol = new OpenLottery();
		ol.betNum = 1;

		// if treble, win zero
		int sameInArray = Common.SameInArray(winNo);
		if (sameInArray == 2) {
			ol.winNum = 1;
			return ol;
		}

		ol.winNum = 0;
		return ol;

	}

	/**
	 * 三星顺子
	 * 
	 * @param winNo
	 *            3位开奖号（前中后）
	 * @param betNos
	 * @return
	 */
	public OpenLottery _3_Sequence(int[] winNo, int[][] betNos) {

		OpenLottery ol = new OpenLottery();
		ol.betNum = 1;

		// if treble, win zero
		if (Common.IsSequence(winNo)) {
			ol.winNum = 1;
			return ol;
		}

		ol.winNum = 0;
		return ol;

	}

	/**
	 * 3星 2No no fix 选择2个号，包含即中奖。
	 * 
	 * @param winNo
	 * @param betNos
	 * @return
	 */
	public OpenLottery _3_2_nofix(int[] winNo, int[][] betNos) {
		// TODO Auto-generated method stub

		// separate
		List<Star2> lsStar2 = BetNoSeparate.separate3_2_nofix(betNos);

		OpenLottery ol = new OpenLottery();
		ol.betNum = lsStar2.size();

		// sort winNo
		Arrays.sort(winNo);

		// compare
		int wbCount = 0;
		for (int i = 0; i < lsStar2.size(); i++) {
			if (Common.IsInArray(lsStar2.get(i).star2[0], winNo) && Common.IsInArray(lsStar2.get(i).star2[1], winNo)) {

				wbCount++;
			}

		}

		ol.winNum = wbCount;

		return ol;
	}

	/**
	 * 3星 1No no fix 选择1个号，包含即中奖。一帆风顺
	 * 
	 * @param winNo
	 * @param betNos
	 * @return
	 */
	public OpenLottery _3_1_nofix(int[] winNo, int[][] betNos) {
		// TODO Auto-generated method stub

		// separate
		// List<Integer> lsStar1 = BetNoSeparate.separate5_1_nofix(betNos);
		int[] betNo = betNos[0];

		OpenLottery ol = new OpenLottery();
		ol.betNum = betNo.length;

		// sort winNo
		Arrays.sort(winNo);

		// compare
		int wbCount = 0;
		for (int i = 0; i < betNo.length; i++) {
			if (Common.IsInArray(betNo[i], winNo)) {

				wbCount++;
			}

		}

		ol.winNum = wbCount;

		return ol;
	}

	/**
	 * 3星直选和值
	 * 
	 * @param winNo
	 * @param betNos
	 *            { 1,2,3,5,10... }投注和值数
	 * @return
	 */
	public OpenLottery _3_Z_Sum(int[] winNo, int[][] betNos) {

		OpenLottery ol = new OpenLottery();

		// separate
		for (int i = 0; i < betNos[0].length; i++) {
			int betNo = betNos[0][i];
			ol.betNum += Constant.S3_Z_Sum[betNo][1];
		}

		int winSum = Common.SumOfArray(winNo);

		if (Common.IsInArray(winSum, betNos[0])) {
			ol.winNum++;
		}

		return ol;
	}

	/**
	 * 3星组选和值
	 * 
	 * @param winNo
	 * @param betNos
	 *            { 1,2,3,5,10... }投注和值数
	 * @return
	 */
	public OpenLottery _3_Group_Sum(int[] winNo, int[][] betNos) {

		OpenLottery ol = new OpenLottery();

		// separate
		for (int i = 0; i < betNos[0].length; i++) {
			int betNo = betNos[0][i];
			ol.betNum += Constant.S3_Group_Sum[betNo-1][1];
		}

		int winSum = Common.SumOfArray(winNo);

		if (Common.IsInArray(winSum, betNos[0])) {
			ol.winNum++;
		}

		return ol;
	}

	/*
	 * *********************************************************************** 2星
	 */

	/**
	 * 2星直选单式（前，后）
	 * 
	 * @param winNo
	 *            3位
	 * @param betNo
	 * @return
	 */
	public OpenLottery _2_Z_Dan(int[] winNo, int[][] betNos) {
		// TODO Auto-generated method stub

		OpenLottery ol = new OpenLottery();
		ol.betNum = betNos.length;

		int wbCount = 0;// win bet count

		for (int i = 0; i < betNos.length; i++) {

			if (betNos[i][0] == winNo[0] && betNos[i][1] == winNo[1])// win
			{
				wbCount++;
			}
		}

		ol.winNum = wbCount;

		return ol;

	}

	/**
	 * 2星直选复式 betNos 在前端已经拆过号，这里只需要对比是否中奖
	 * 
	 * @return
	 */
	public OpenLottery _2_Z_Fu(int[] winNo, int[][] betNos) {

		OpenLottery ol = new OpenLottery();

		List<Star2> lsStar2 = BetNoSeparate.separateStar2(betNos);

		ol.betNum = lsStar2.size();

		int wbCount = 0;// win bet count

		for (int i = 0; i < lsStar2.size(); i++) {

			Star2 s2 = lsStar2.get(i);

			if (s2.star2[0] == winNo[0] && s2.star2[1] == winNo[1] && s2.star2[2] == winNo[2])// win
			{
				wbCount++;
			}
		}

		ol.winNum = wbCount;

		return ol;
	}

	/**
	 * 2星组选复式 选择2个单号组成一注。号码一致，顺序不限
	 * 
	 * @param winNo
	 *            2位开奖号（前后）
	 * @param betNos
	 * @return
	 */
	public OpenLottery _2_Group_Fu(int[] winNo, int[][] betNos) {
		// TODO Auto-generated method stub

		// separate
		List<Star2> lsStar2 = BetNoSeparate.separateS2GroupFu(betNos);

		OpenLottery ol = new OpenLottery();
		ol.betNum = lsStar2.size();

		// sort winNo
		Arrays.sort(winNo);

		// compare
		int wbCount = 0;
		for (int i = 0; i < lsStar2.size(); i++) {

			Star2 s2 = lsStar2.get(i);

			if (s2.star2[0] == winNo[0] && s2.star2[1] == winNo[1])// win
			{

				wbCount++;
			}

		}

		ol.winNum = wbCount;

		return ol;
	}

	/**
	 * 2星组选单式 选择2个单号组成一注。号码一致，顺序不限
	 * 
	 * @param winNo
	 *            2位开奖号（前后）
	 * @param betNos
	 * @return
	 */
	public OpenLottery _2_Group_Dan(int[] winNo, int[][] betNos) {
		// TODO Auto-generated method stub

		OpenLottery ol = new OpenLottery();
		ol.betNum = betNos.length;

		// sort winNo
		Arrays.sort(winNo);

		// compare
		int wbCount = 0;
		for (int i = 0; i < betNos.length; i++) {

			int[] betNo2 = { betNos[i][0], betNos[i][1] };

			Arrays.sort(betNo2);

			if (betNo2[0] == winNo[0] && betNo2[1] == winNo[1])// win
			{

				wbCount++;
			}

		}

		ol.winNum = wbCount;

		return ol;
	}

	/**
	 * 2星大小单双（前后） 在个位和十位上，选择大小单号组成一注。
	 * 
	 * @param winNo
	 *            2位开奖号（前后）
	 * @param betNos
	 *            {{大小单双},{大小单双}} 10位大小 10位单双，个位大小，个位单双
	 * @return
	 */
	public OpenLottery _2_BigSmall_Double_Sigle(int[] winNo, int[][] betNos) {

		OpenLottery ol = new OpenLottery();

		// 十位 大小单双 9，2，1，0
		// 可投入为P42=16，中奖00 ，01，02，03-33 ，16种
		// separate bet
		List<Star2> lsStar2 = BetNoSeparate.separateS2BSDS(betNos);

		ol.betNum = lsStar2.size();

		// transform winNo to BSDS
		int[] result = { 0, 0 };// 十位大小，十位单双，个位大小，个位单双
		// 十大小，个大小 = 大大，大小，小小，小大
		// 十大小，个单双 = 大单，大双，小单，小双
		// 十单双，个大小 = 单大，单小，双大，双小
		// 十单双，个单双 = 单单，单双，双单，双双

		// 10大10单，个大个单 = { 大大，大单，单大，单单 }

		if (Common.IsBigOrSmall(winNo[0]) == 0) {// 十位小
			result[0] = 2;
		} else {// 十位大
			result[0] = 9;
		}

		if (Common.IsSingelOrDouble(winNo[0]) == 0) {// 十位双
			result[0] = 0;
		} else {// 十位单
			result[0] = 1;
		}

		if (Common.IsBigOrSmall(winNo[1]) == 0) {// 个位小
			result[1] = 2;
		} else {// 个位大
			result[1] = 9;
		}

		if (Common.IsSingelOrDouble(winNo[1]) == 0) {// 个位双
			result[1] = 0;
		} else {// 个位单
			result[1] = 1;
		}

		// separate winNo
		int[] arrSepWinNo = new int[2];

		arrSepWinNo[0] = result[0];// 大小单双;
		arrSepWinNo[1] = result[1];// 大小单双;

		for (int i = 0; i < lsStar2.size(); i++) {
			Star2 s2 = lsStar2.get(i);

			// 结果组和投注组比较
			// 十位 大,小,单，双 0，1，2，3
			if (arrSepWinNo[0] == s2.star2[0] && arrSepWinNo[1] == s2.star2[1]) {// 十位小，个位小
				ol.winNum++;
			}

		}

		return ol;

	}
	
	

	/**
	 * 2星直选和值
	 * 
	 * @param winNo 0-18
	 * @param betNos
	 *            { 1,2,3,5,10... }投注和值数
	 * @return
	 */
	public OpenLottery _2_Z_Sum(int[] winNo, int[][] betNos) {

		OpenLottery ol = new OpenLottery();

		// separate
		for (int i = 0; i < betNos[0].length; i++) {
			int betNo = betNos[0][i];
			ol.betNum += Constant.S2_Z_Sum[betNo][1];
		}

		int winSum = Common.SumOfArray(winNo);

		if (Common.IsInArray(winSum, betNos[0])) {
			ol.winNum++;
		}

		return ol;
	}

	/**
	 * 2星组选和值
	 * 
	 * @param winNo 1-17
	 * @param betNos
	 *            { 1,2,3,5,10... }投注和值数
	 * @return
	 */
	public OpenLottery _2_Group_Sum(int[] winNo, int[][] betNos) {

		OpenLottery ol = new OpenLottery();

		// separate
		for (int i = 0; i < betNos[0].length; i++) {
			int betNo = betNos[0][i];
			ol.betNum += Constant.S2_Group_Sum[betNo-1][1];
		}

		int winSum = Common.SumOfArray(winNo);

		if (Common.IsInArray(winSum, betNos[0])) {
			ol.winNum++;
		}

		return ol;
	}


	/*
	 * ***************************************************************************
	 * 
	 * 一星
	 */

	/**
	 * 1星定位胆
	 * 
	 * @param winNo
	 *            2位开奖号（前后）
	 * 
	 * @param betNos
	 * 
	 * @return
	 */
	public OpenLottery _1_Fix_Dan(int[] winNo, int[][] betNos) {
		// TODO Auto-generated method stub

		OpenLottery ol = new OpenLottery();

		// if no bet is -1

		for (int i = 0; i < betNos.length; i++) {
			for (int ipos = 0; ipos < betNos[i].length; ipos++) {// 万位
				int betNo = betNos[i][ipos];
				if (betNo >= 0) {
					ol.betNum++;
				}

				if (betNo == winNo[i]) {
					ol.winNum++;
				}
			}
		}
		return ol;

	}

}
