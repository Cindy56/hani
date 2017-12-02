/**
 * 
 */
package com.game.modules.contract.service;

import java.util.List;

import com.game.common.persistence.Page;
import com.game.modules.contract.entity.Contract;
import com.game.modules.sys.entity.User;

/**
 * 开公司、会员service
 * @author freeman
 * 2017年11月23日 下午1:59:02
 */
public interface ContractService {

	public Contract get(String id) ;
	
	public List<Contract> findList(Contract contract) ;
	
	public Page<Contract> findPage(Page<Contract> page, Contract contract);
	
	public Contract save(Contract contract);
	
	public void delete(Contract contract);
	
	public Contract saveAgent(Contract contract);
	
	/**
	 * 根据userid和公司id查出代理合同信息
	 * @author freemam
	 * 2017年12月1日 下午1:58:03
	 * @param userId 
	 * @param companyId 公司id
	 * @return
	 */
	public Contract getContractByUserId(String userId,String companyId);
}
