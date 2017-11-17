package com.game.hall.modules.memberAccountCard.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.MemberAccountCard;
import com.game.manager.common.persistence.annotation.MyBatisDao;



@MyBatisDao
public interface MemberAccountCardDao {
	//根据账户id查询此账户下所有绑定的银行卡信息
	public List<MemberAccountCard> get(String id);
	//账户新增银行卡
	public int insert(MemberAccountCard memberAccountCard);
	//查询安全码
	public String getSec(String id);
 
	//更新安全码
	public int modifySec(@Param("id")String id,@Param("newPassWord")String newPassWord);
	
}
