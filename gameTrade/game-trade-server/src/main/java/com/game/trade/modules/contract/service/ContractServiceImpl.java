/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.trade.modules.contract.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.game.common.persistence.Page;
import com.game.common.service.CrudService;
import com.game.common.utils.StringUtils;
import com.game.modules.contract.entity.Contract;
import com.game.modules.contract.entity.ContractConfig;
import com.game.modules.contract.service.ContractService;
import com.game.trade.modules.contract.dao.ContractConfigDao;
import com.game.trade.modules.contract.dao.ContractDao;

/**
 * 开设分公司Service
 * @author freeman
 * @version 2017-11-22
 */
@Service("contractService")
@Transactional(readOnly = true)
public class ContractServiceImpl extends CrudService<ContractDao, Contract> implements ContractService {

	@Autowired
	private ContractConfigDao contractConfigDao;
	
	public Contract get(String id) {
		Contract contract = super.get(id);
		contract.setContractConfigList(contractConfigDao.findList(new ContractConfig(contract)));
		return contract;
	}
	
	public List<Contract> findList(Contract contract) {
		return super.findList(contract);
	}
	
	public Page<Contract> findPage(Page<Contract> page, Contract contract) {
		return super.findPage(page, contract);
	}
	
	@Transactional(readOnly = false)
	public void save(Contract contract) {
		super.save(contract);
		for (ContractConfig contractConfig : contract.getContractConfigList()){
			if (contractConfig.getId() == null){
				continue;
			}
			if (ContractConfig.DEL_FLAG_NORMAL.equals(contractConfig.getDelFlag())){
				if (StringUtils.isBlank(contractConfig.getId())){
					contractConfig.setContractId(contract);
					contractConfig.preInsert();
					contractConfigDao.insert(contractConfig);
				}else{
					contractConfig.preUpdate();
					contractConfigDao.update(contractConfig);
				}
			}else{
				contractConfigDao.delete(contractConfig);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(Contract contract) {
		super.delete(contract);
		contractConfigDao.delete(new ContractConfig(contract));
	}
	
}