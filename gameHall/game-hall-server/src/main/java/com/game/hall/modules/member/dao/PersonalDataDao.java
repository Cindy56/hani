/**
 * 今日开奖
 */
package com.game.hall.modules.member.dao;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.game.hall.common.persistence.annotation.MyBatisDao;
import com.game.hall.modules.member.entity.MemberAccount;
import com.game.hall.modules.memberAccountCard.entity.MemberAccountCard;

/**
 * @author antonio
 *
 */

@MyBatisDao
public interface PersonalDataDao {
	
	//查看所有银行卡信息
	public List<Map<String,Object>> findAllCart();
	
	//查找某个用户银行卡信息
	public List<Map<String,Object>> get(String id);
	
	//修改账户信息
	public int updateMemberAccount(MemberAccount memberAccount);
	
	//更新安全码
	public int modifySec(@Param("id")String id,@Param("newPassWord")String newPassWord);
	
	//查询安全码
	public String getSec(String id);
	
	//增加银行卡绑定
	public int insertCard(MemberAccountCard memberAccountCard);
	
	//增加账户
	public int insert(MemberAccount memberAccount);

	//删除银行卡信息
	public int delCard(String bankCardNo);
	
	//根据账户user_id查询用户信息
	public Map<String, Object> memberAccount(String userId);

	//根据账户user_id更改账户信息
	public int modifyMemberAccount(Map<String, String> map);
	
	//根据账户user_id更改sys_user用户信息
	public int modifyUser(Map<String, String> map);
}
