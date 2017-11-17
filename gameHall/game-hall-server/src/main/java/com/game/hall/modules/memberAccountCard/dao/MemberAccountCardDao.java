package com.game.hall.modules.memberAccountCard.dao;


import java.util.List;

import com.game.hall.common.persistence.annotation.MyBatisDao;
import com.game.hall.modules.memberAccountCard.entity.MemberAccountCard;


@MyBatisDao
public interface MemberAccountCardDao {
	//根据账户id查询此账户下所有绑定的银行卡信息
	public List<MemberAccountCard> get(String id);
	//账户新增银行卡
	public int insert(MemberAccountCard memberAccountCard);
	//查询安全码
	public String getSec(String id);

	
}
