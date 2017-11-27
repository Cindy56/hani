package com.game.trade.modules.lottery;

import java.math.BigDecimal;

public class MoneyType {

	enum MoneyTypeName implements MoneyTypeNum {
		YUAN("元") {
			public BigDecimal getVal() {
				return new BigDecimal(1);
			}
		},
		JIAO("角") {
			public BigDecimal getVal() {
				return new BigDecimal(0.1);
			}
		},
		FEN("分") {
			public BigDecimal getVal() {
				return new BigDecimal(0.01);
			}
		},

		LI("厘") {

			public BigDecimal getVal() {
				return new BigDecimal(0.001);
			}
		},;

		private String v;

		private MoneyTypeName(String v) {
			this.v = v;
		}
	}

	interface MoneyTypeNum {

		public BigDecimal getVal();
	}

	/**
	 * 获得模式对应金额
	 * 
	 * @param moneyType
	 * @return
	 */
	static public BigDecimal getMoneyType(String moneyType) {

		MoneyTypeName moneyTypeName = MoneyTypeName.valueOf(moneyType);

		return moneyTypeName.getVal();

	}

}
