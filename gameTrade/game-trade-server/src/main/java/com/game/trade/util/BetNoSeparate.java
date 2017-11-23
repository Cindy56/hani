package com.game.trade.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.game.trade.model.Star2;
import com.game.trade.model.Star3;
import com.game.trade.model.Star4;
import com.game.trade.model.Star5;

public class BetNoSeparate {

	/**
	 * 分离5星投注号
	 * 
	 * @param betNos
	 * @return
	 */
	static public List<Star5> separateStar5(int[][] betNos) {
		// TODO Auto-generated method stub

		List<Star5> lsStar5 = new ArrayList<Star5>();

		int a0 = 0;
		int a1 = 0;
		int a2 = 0;
		int a3 = 0;
		int a4 = 0;

		// 第一组数据
		for (int i0 = 0; i0 < betNos[0].length; i0++) {

			a0 = betNos[0][i0];
			// 第二组数据
			for (int i1 = 0; i1 < betNos[1].length; i1++) {
				a1 = betNos[1][i1];
				// 第三组数据
				for (int i2 = 0; i2 < betNos[2].length; i2++) {
					a2 = betNos[2][i2];
					// 第四组数据
					for (int i3 = 0; i3 < betNos[3].length; i3++) {
						a3 = betNos[3][i3];
						// 第五组数据
						for (int i4 = 0; i4 < betNos[4].length; i4++) {
							a4 = betNos[4][i4];

							Star5 s5 = new Star5(a0, a1, a2, a3, a4);

							lsStar5.add(s5);
						}
					}
				}
			}
		}

		return lsStar5;

	}

	/**
	 * 分离组60
	 * 
	 * @param betNos
	 * @return
	 */
	public static List<Star5> separateGroup60(int[][] betNos) {
		// TODO Auto-generated method stub

		List<Star5> lsStar5 = new ArrayList<Star5>();

		int a0 = 0;
		int a1 = 0;
		int a2 = 0;
		int a3 = 0;
		int a4 = 0;

		// double No array
		int[] doubleNo = betNos[0];

		// single No array
		int[] singleNo = betNos[1];

		// single No array => combination N,3
		ArrayList<Integer> t = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> arr = Combination.Combination(singleNo, singleNo.length, 3, t);

		for (int idb = 0; idb < doubleNo.length; idb++) {

			a0 = a1 = doubleNo[idb];

			for (int isingle = 0; isingle < arr.size(); isingle++) {

				a2 = arr.get(isingle).get(0);
				a3 = arr.get(isingle).get(1);
				a4 = arr.get(isingle).get(2);

				int arrAN[] = new int[5];
				arrAN[0] = a0;
				arrAN[1] = a1;
				arrAN[2] = a2;
				arrAN[3] = a3;
				arrAN[4] = a4;

				Arrays.sort(arrAN);

				Star5 s5 = new Star5(arrAN);

				lsStar5.add(s5);
			}

		}

		return lsStar5;
	}

	/**
	 * 分离组30
	 * 
	 * @param betNos
	 * @return
	 */
	public static List<Star5> separateGroup30(int[][] betNos) {

		List<Star5> lsStar5 = new ArrayList<Star5>();

		int a0 = 0;
		int a1 = 0;
		int a2 = 0;
		int a3 = 0;
		int a4 = 0;

		// double No array
		int[] doubleNo = betNos[0];

		// double No array => combination N,2
		ArrayList<Integer> t = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> arrDbNo_C_N_2 = Combination.Combination(doubleNo, doubleNo.length, 2, t);

		// single No array
		int[] singleNo = betNos[1];

		for (int idb = 0; idb < arrDbNo_C_N_2.size(); idb++) {

			a0 = a1 = arrDbNo_C_N_2.get(idb).get(0);
			a2 = a3 = arrDbNo_C_N_2.get(idb).get(1);

			for (int isingle = 0; isingle < singleNo.length; isingle++) {

				a4 = singleNo[isingle];

				int arrAN[] = new int[5];
				arrAN[0] = a0;
				arrAN[1] = a1;
				arrAN[2] = a2;
				arrAN[3] = a3;
				arrAN[4] = a4;

				Arrays.sort(arrAN);

				Star5 s5 = new Star5(arrAN);

				lsStar5.add(s5);
			}

		}

		return lsStar5;
	}

	/**
	 * 分离组20
	 * 
	 * @param betNos
	 * @return
	 */
	public static List<Star5> separateGroup20(int[][] betNos) {

		List<Star5> lsStar5 = new ArrayList<Star5>();

		int a0 = 0;
		int a1 = 0;
		int a2 = 0;
		int a3 = 0;
		int a4 = 0;

		// treble No array
		int[] trebleNo = betNos[0];

		// single No array
		int[] singleNo = betNos[1];

		// single No array => combination N,2
		ArrayList<Integer> t = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> arrSingleNo_C_N_2 = Combination.Combination(singleNo, singleNo.length, 2, t);

		for (int itb = 0; itb < trebleNo.length; itb++) {

			a0 = a1 = a2 = trebleNo[itb];

			for (int isingle = 0; isingle < arrSingleNo_C_N_2.size(); isingle++) {

				a3 = arrSingleNo_C_N_2.get(isingle).get(0);
				a4 = arrSingleNo_C_N_2.get(isingle).get(1);

				int arrAN[] = new int[5];
				arrAN[0] = a0;
				arrAN[1] = a1;
				arrAN[2] = a2;
				arrAN[3] = a3;
				arrAN[4] = a4;

				Arrays.sort(arrAN);

				Star5 s5 = new Star5(arrAN);

				lsStar5.add(s5);
			}

		}

		return lsStar5;
	}

	/**
	 * 分离组10
	 * 
	 * @param betNos
	 * @return
	 */
	public static List<Star5> separateGroup10(int[][] betNos) {

		List<Star5> lsStar5 = new ArrayList<Star5>();

		int a0 = 0;
		int a1 = 0;
		int a2 = 0;
		int a3 = 0;
		int a4 = 0;

		// treble No array
		int[] trebleNo = betNos[0];

		// double No array
		int[] doubleNo = betNos[1];

		for (int itb = 0; itb < trebleNo.length; itb++) {

			a0 = a1 = a2 = trebleNo[itb];

			for (int idb = 0; idb < doubleNo.length; idb++) {

				a3 = a4 = doubleNo[idb];

				int arrAN[] = new int[5];
				arrAN[0] = a0;
				arrAN[1] = a1;
				arrAN[2] = a2;
				arrAN[3] = a3;
				arrAN[4] = a4;

				Arrays.sort(arrAN);

				Star5 s5 = new Star5(arrAN);

				lsStar5.add(s5);
			}

		}

		return lsStar5;
	}

	/**
	 * 分离组5
	 * 
	 * @param betNos
	 * @return
	 */
	public static List<Star5> separateGroup5(int[][] betNos) {
		List<Star5> lsStar5 = new ArrayList<Star5>();

		int a0 = 0;
		int a1 = 0;
		int a2 = 0;
		int a3 = 0;
		int a4 = 0;

		// fourfold No array
		int[] fourfoldNo = betNos[0];

		// single No array
		int[] singleNo = betNos[1];

		for (int itb = 0; itb < fourfoldNo.length; itb++) {

			a0 = a1 = a2 = a3 = fourfoldNo[itb];

			for (int idb = 0; idb < singleNo.length; idb++) {

				a4 = singleNo[idb];

				int arrAN[] = new int[5];
				arrAN[0] = a0;
				arrAN[1] = a1;
				arrAN[2] = a2;
				arrAN[3] = a3;
				arrAN[4] = a4;

				Arrays.sort(arrAN);

				Star5 s5 = new Star5(arrAN);

				lsStar5.add(s5);
			}

		}

		return lsStar5;
	}

	/**
	 * 分离5星2码不定位
	 * 
	 * @param betNos
	 * @return
	 */
	public static List<Star2> separate5_2_nofix(int[][] betNos) {
		List<Star2> lsStar2 = new ArrayList<Star2>();

		int a0 = 0;
		int a1 = 0;
		int a2 = 0;
		int a3 = 0;
		int a4 = 0;

		// _2nofix No array
		int[] _2nofixNo = betNos[0];

		// _2nofix No array => combination N,2
		ArrayList<Integer> t = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> arr2nofix_C_N_2 = Combination.Combination(_2nofixNo, _2nofixNo.length, 2, t);

		for (int i = 0; i < arr2nofix_C_N_2.size(); i++) {

			a0 = arr2nofix_C_N_2.get(i).get(0);
			a1 = arr2nofix_C_N_2.get(i).get(1);

			int arrAN[] = new int[2];
			arrAN[0] = a0;
			arrAN[1] = a1;

			Arrays.sort(arrAN);

			Star2 s2 = new Star2(arrAN);

			lsStar2.add(s2);
		}

		return lsStar2;

	}

	/**
	 * 分离5星3码不定位
	 * 
	 * @param betNos
	 * @return
	 */
	public static List<Star3> separate5_3_nofix(int[][] betNos) {
		List<Star3> lsStar3 = new ArrayList<Star3>();

		int a0 = 0;
		int a1 = 0;
		int a2 = 0;

		// _3nofix No array
		int[] _3nofixNo = betNos[0];

		// _2nofix No array => combination N,3
		ArrayList<Integer> t = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> arr3nofix_C_N_3 = Combination.Combination(_3nofixNo, _3nofixNo.length, 3, t);

		for (int i = 0; i < arr3nofix_C_N_3.size(); i++) {

			a0 = arr3nofix_C_N_3.get(i).get(0);
			a1 = arr3nofix_C_N_3.get(i).get(1);
			a2 = arr3nofix_C_N_3.get(i).get(2);

			int arrAN[] = new int[3];
			arrAN[0] = a0;
			arrAN[1] = a1;
			arrAN[2] = a2;

			Arrays.sort(arrAN);

			Star3 s3 = new Star3(arrAN);

			lsStar3.add(s3);
		}

		return lsStar3;
	}

	/**
	 * 分离4星
	 * 
	 * @param betNos
	 * @return
	 */
	public static List<Star4> separateStar4(int[][] betNos) {

		List<Star4> lsStar4 = new ArrayList<Star4>();

		int a0 = 0;
		int a1 = 0;
		int a2 = 0;
		int a3 = 0;
		// int a4 = 0;

		// 第一组数据
		for (int i0 = 0; i0 < betNos[0].length; i0++) {

			a0 = betNos[0][i0];
			// 第二组数据
			for (int i1 = 0; i1 < betNos[1].length; i1++) {
				a1 = betNos[1][i1];
				// 第三组数据
				for (int i2 = 0; i2 < betNos[2].length; i2++) {
					a2 = betNos[2][i2];
					// 第四组数据
					for (int i3 = 0; i3 < betNos[3].length; i3++) {
						a3 = betNos[3][i3];

						Star4 s4 = new Star4(a0, a1, a2, a3);

						lsStar4.add(s4);

					}
				}
			}
		}

		return lsStar4;
	}

	/**
	 * 分离组12
	 * 
	 * @param betNos
	 * @return
	 */
	public static List<Star4> separateGroup12(int[][] betNos) {
		// TODO Auto-generated method stub

		List<Star4> lsStar4 = new ArrayList<Star4>();

		int a0 = 0;
		int a1 = 0;
		int a2 = 0;
		int a3 = 0;

		// double No array
		int[] doubleNo = betNos[0];

		// single No array
		int[] singleNo = betNos[1];

		// single No array => combination N,3
		ArrayList<Integer> t = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> arr = Combination.Combination(singleNo, singleNo.length, 2, t);

		for (int idb = 0; idb < doubleNo.length; idb++) {

			a0 = a1 = doubleNo[idb];

			for (int isingle = 0; isingle < arr.size(); isingle++) {

				a2 = arr.get(isingle).get(0);
				a3 = arr.get(isingle).get(1);

				int arrAN[] = new int[4];
				arrAN[0] = a0;
				arrAN[1] = a1;
				arrAN[2] = a2;
				arrAN[3] = a3;

				Arrays.sort(arrAN);

				Star4 s4 = new Star4(arrAN);

				lsStar4.add(s4);
			}

		}

		return lsStar4;

	}

	/**
	 * 分离4星组6
	 * 
	 * @param betNos
	 * @return
	 */
	public static List<Star4> separateS4Group6(int[][] betNos) {

		List<Star4> lsStar4 = new ArrayList<Star4>();

		int a0 = 0;
		int a1 = 0;
		int a2 = 0;
		int a3 = 0;

		// double No array
		int[] doubleNo = betNos[0];

		// double No array => combination N,2
		ArrayList<Integer> t = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> arrDbNo_C_N_2 = Combination.Combination(doubleNo, doubleNo.length, 2, t);

		for (int idb = 0; idb < arrDbNo_C_N_2.size(); idb++) {

			a0 = a1 = arrDbNo_C_N_2.get(idb).get(0);
			a2 = a3 = arrDbNo_C_N_2.get(idb).get(1);

			int arrAN[] = new int[4];
			arrAN[0] = a0;
			arrAN[1] = a1;
			arrAN[2] = a2;
			arrAN[3] = a3;

			Arrays.sort(arrAN);

			Star4 s4 = new Star4(arrAN);

			lsStar4.add(s4);

		}

		return lsStar4;

	}

	/**
	 * 
	 * @param betNos
	 * @return
	 */
	public static List<Star4> separateGroup4(int[][] betNos) {

		List<Star4> lsStar4 = new ArrayList<Star4>();

		int a0 = 0;
		int a1 = 0;
		int a2 = 0;
		int a3 = 0;

		// treble No array
		int[] trebleNo = betNos[0];

		// single No array
		int[] singleNo = betNos[1];

		for (int itb = 0; itb < trebleNo.length; itb++) {

			a0 = a1 = a2 = trebleNo[itb];

			for (int is = 0; is < singleNo.length; is++) {

				a3 = singleNo[is];

				int arrAN[] = new int[4];
				arrAN[0] = a0;
				arrAN[1] = a1;
				arrAN[2] = a2;
				arrAN[3] = a3;

				Arrays.sort(arrAN);

				Star4 s4 = new Star4(arrAN);

				lsStar4.add(s4);
			}

		}

		return lsStar4;

	}

	/**
	 * 分离三星
	 * 
	 * @param betNos
	 * @return
	 */
	public static List<Star3> separateStar3(int[][] betNos) {

		List<Star3> lsStar3 = new ArrayList<Star3>();

		int a0 = 0;
		int a1 = 0;
		int a2 = 0;

		// 第一组数据
		for (int i0 = 0; i0 < betNos[0].length; i0++) {

			a0 = betNos[0][i0];
			// 第二组数据
			for (int i1 = 0; i1 < betNos[1].length; i1++) {
				a1 = betNos[1][i1];
				// 第三组数据
				for (int i2 = 0; i2 < betNos[2].length; i2++) {
					a2 = betNos[2][i2];

					Star3 s3 = new Star3(a0, a1, a2);

					lsStar3.add(s3);

				}
			}
		}

		return lsStar3;
	}

	/**
	 * 分离组3，一个二重号，1个单号
	 * 
	 * @param betNos
	 * @return
	 */
	public static List<Star3> separateGroup3(int[][] betNos) {

		List<Star3> lsStar3 = new ArrayList<Star3>();

		int a0 = 0;
		int a1 = 0;
		int a2 = 0;

		// single No array
		int[] singleNo = betNos[0];

		// C_N_2
		ArrayList<Integer> t = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> arr_C_N_2 = Combination.Combination(singleNo, singleNo.length, 2, t);

		for (int i = 0; i < arr_C_N_2.size(); i++) {

			a0 = a1 = arr_C_N_2.get(i).get(0);
			a2 = arr_C_N_2.get(i).get(1);

			int an1[] = { a0, a1, a2 };
			Arrays.sort(an1);
			Star3 s3 = new Star3(an1);
			lsStar3.add(s3);

			// -----------------------------------

			a0 = arr_C_N_2.get(i).get(0);
			a1 = a2 = arr_C_N_2.get(i).get(1);

			int an2[] = { a0, a1, a2 };
			Arrays.sort(an2);
			Star3 s3_2 = new Star3(an2);
			lsStar3.add(s3_2);

		}

		return lsStar3;

	}

	/**
	 * 分离三星组6,3个单号
	 * 
	 * @param betNos
	 * @return
	 */
	public static List<Star3> separateS3Group6(int[][] betNos) {

		List<Star3> lsStar3 = new ArrayList<Star3>();

		// single No array
		int[] singleNo = betNos[0];

		// double No array => combination N,3
		ArrayList<Integer> t = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> arr_C_N_3 = Combination.Combination(singleNo, singleNo.length, 3, t);

		for (int i = 0; i < arr_C_N_3.size(); i++) {

			ArrayList<Integer> ls3 = arr_C_N_3.get(i);
			int an3[] = { ls3.get(0), ls3.get(1), ls3.get(2) };
			Arrays.sort(an3);
			Star3 s3 = new Star3(an3);
			lsStar3.add(s3);
		}

		return lsStar3;
	}

	/**
	 * 分离2星
	 * 
	 * @param betNos
	 * @return
	 */
	public static List<Star2> separateStar2(int[][] betNos) {

		List<Star2> lsStar2 = new ArrayList<Star2>();

		int a0 = 0;
		int a1 = 0;

		// 第一组数据
		for (int i0 = 0; i0 < betNos[0].length; i0++) {

			a0 = betNos[0][i0];
			// 第二组数据
			for (int i1 = 0; i1 < betNos[1].length; i1++) {
				a1 = betNos[1][i1];

				Star2 s2 = new Star2(a0, a1);

				lsStar2.add(s2);

			}
		}

		return lsStar2;

	}

	/**
	 * 2星组选复式
	 * 
	 * @param betNos
	 * @return
	 */
	public static List<Star2> separateS2GroupFu(int[][] betNos) {

		List<Star2> lsStar2 = new ArrayList<Star2>();

		// single No array
		int[] singleNo = betNos[0];

		// single No array => combination N,2
		ArrayList<Integer> t = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> arr_C_N_2 = Combination.Combination(singleNo, singleNo.length, 2, t);

		for (int i = 0; i < arr_C_N_2.size(); i++) {

			ArrayList<Integer> ls = arr_C_N_2.get(i);
			int an[] = { ls.get(0), ls.get(1) };
			Arrays.sort(an);
			Star2 s = new Star2(an);
			lsStar2.add(s);
		}

		return lsStar2;
	}

	/**
	 * 分离三星2码不定位
	 * 
	 * @param betNos
	 * @return
	 */
	public static List<Star2> separate3_2_nofix(int[][] betNos) {

		List<Star2> lsStar2 = new ArrayList<Star2>();

		int a0 = 0;
		int a1 = 0;
		int a2 = 0;

		// _2nofix No array
		int[] _2nofixNo = betNos[0];

		// _2nofix No array => combination N,2
		ArrayList<Integer> t = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> arr2nofix_C_N_2 = Combination.Combination(_2nofixNo, _2nofixNo.length, 2, t);

		for (int i = 0; i < arr2nofix_C_N_2.size(); i++) {

			a0 = arr2nofix_C_N_2.get(i).get(0);
			a1 = arr2nofix_C_N_2.get(i).get(1);

			int arrAN[] = new int[2];
			arrAN[0] = a0;
			arrAN[1] = a1;

			Arrays.sort(arrAN);

			Star2 s2 = new Star2(arrAN);

			lsStar2.add(s2);
		}

		return lsStar2;

	}

	/**
	 * 分离2星大小单双
	 * 
	 * @param betNos
	 * @return
	 */
	public static List<Star2> separateS2BSDS(int[][] betNos) {

		List<Star2> lsStar2 = new ArrayList<Star2>();

		int a0 = 0;// 十位 大,小,单，双9210
		int a1 = 0;// 个位 大,小,单，双9210

		// 第一组数据
		for (int i0 = 0; i0 < betNos[0].length; i0++) {

			a0 = betNos[0][i0];
			// 第二组数据
			for (int i1 = 0; i1 < betNos[1].length; i1++) {
				a1 = betNos[1][i1];

				Star2 s2 = new Star2(a0, a1);

				lsStar2.add(s2);

			}
		}

		return lsStar2;

	}

}
