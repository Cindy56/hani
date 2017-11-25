/**
 * 
 */
package com.api;

import java.util.List;
import java.util.Map;

import com.game.modules.member.entity.MemberAccount;
import com.game.modules.member.entity.MemberAccountCard;



/**
 * 账号管理
 * 
 * @author antonio
 *
 */
public interface AccountMgrApi {
	
	//查找所有会员用户列表
	public List<Map<String,Object>> findAllCart();
	
	//查找某个用户信息
	public List<Map<String,Object>> MemberAccount(String name);
	
	//修改用户账户信息
	public int updateMemberAccount(MemberAccount memberAccount);
	
	//更新安全码
	public int modifySec(String id,String newPassWord);
	
	//增加银行卡绑定
	public int insertCard(MemberAccountCard memberAccountCard);
	
	//增加账户
	public int insert(MemberAccount memberAccount);

	
	

}
