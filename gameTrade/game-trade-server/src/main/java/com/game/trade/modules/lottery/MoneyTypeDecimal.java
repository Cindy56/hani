package com.game.trade.modules.lottery;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;

public class MoneyTypeDecimal {

	private static final  Map<String, BigDecimal> moneyType = new HashedMap();
	
	private MoneyTypeDecimal()
	{
		moneyType.put("0",new BigDecimal("1") );
		moneyType.put("1",new BigDecimal("0.1") );
		moneyType.put("2",new BigDecimal("0.01") );
		moneyType.put("3",new BigDecimal("0.001") );
		
	}
	
	public static MoneyTypeDecimal getInstants()
	{
		return new MoneyTypeDecimal();
	}

	public BigDecimal findMoneyType(String key)
	{
		return moneyType.get(key);
	}
	
	
	

}
