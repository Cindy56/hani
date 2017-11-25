package com.game.trade.util;

import java.util.ArrayList;
import java.util.List;

public class Combination {

	// 组合数，通过返回值返回结果
	public static ArrayList<ArrayList<Character>> Combination2(char[] A, int n, int m, ArrayList<Character> t) {
		ArrayList<ArrayList<Character>> res = new ArrayList<ArrayList<Character>>();
		if (m == 0) {
			ArrayList<Character> temp = new ArrayList<Character>(t);
			res.add(temp);
			return res;
		} else {
			// for(int i=n-1;i>=m-1;i--)
			// {
			// t.add(A[i]);
			// Combination(A,i,m-1,t,res);
			// t.remove(t.size()-1);
			// }

			for (int i = A.length - n; i <= A.length - m; i++) {
				t.add(A[i]);
				res.addAll(Combination2(A, A.length - i - 1, m - 1, t));
				t.remove(t.size() - 1);
			}
			return res;
		}
	}
	

	 
	/**组合数，通过返回值返回结果
	 * 
	 * @param A  数组
	 * @param n  
	 * @param m
	 * @param t
	 * @return
	 */
	public static ArrayList<ArrayList<Integer>> Combination(int[] A, int n, int m, ArrayList<Integer> t) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		if (m == 0) {
			ArrayList<Integer> temp = new ArrayList<Integer>(t);
			res.add(temp);
			return res;
		} else {
			// for(int i=n-1;i>=m-1;i--)
			// {
			// t.add(A[i]);
			// Combination(A,i,m-1,t,res);
			// t.remove(t.size()-1);
			// }

			for (int i = A.length - n; i <= A.length - m; i++) {
				t.add(A[i]);
				res.addAll(Combination(A, A.length - i - 1, m - 1, t));
				t.remove(t.size() - 1);
			}
			return res;
		}
	}
	
	/**组合数List版本，通过返回值返回结果
	 * 
	 * @param A  数组
	 * @param n  
	 * @param m
	 * @param t
	 * @return
	 */
	public static ArrayList<ArrayList<Integer>> Combination(List<Integer> A, int n, int m, ArrayList<Integer> t) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		if (m == 0) {
			ArrayList<Integer> temp = new ArrayList<Integer>(t);
			res.add(temp);
			return res;
		} else {
			// for(int i=n-1;i>=m-1;i--)
			// {
			// t.add(A[i]);
			// Combination(A,i,m-1,t,res);
			// t.remove(t.size()-1);
			// }

			for (int i = A.size() - n; i <= A.size() - m; i++) {
				t.add(A.get(i));
				res.addAll(Combination(A, A.size() - i - 1, m - 1, t));
				t.remove(t.size() - 1);
			}
			return res;
		}
	}

}
