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
import com.game.modules.member.entity.MemberAccount;
import com.game.modules.member.service.MemberAccountService;
import com.game.modules.sys.entity.Office;
import com.game.modules.sys.entity.Role;
import com.game.modules.sys.entity.User;
import com.game.modules.sys.service.OfficeServiceFacade;
import com.game.modules.sys.service.SystemServiceFacade;
import com.game.modules.todo.entity.TodoTask;
import com.game.modules.todo.service.TodoTaskService;
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

	/*************************机构模板*************************/
	/**
	 * 公司模板id
	 */
	private final static String COMPANY_TEMPLET_ID="ce6f9bcd5512471cabd6a281d4f0f19c";
	
	@Autowired
	private ContractConfigDao contractConfigDao;
	
	@Autowired
	private SystemServiceFacade systemServiceFacade;
	
	@Autowired
	private OfficeServiceFacade officeServiceFacade;
	
	@Autowired
	private MemberAccountService memberAccountService;
	
	@Autowired
	private TodoTaskService todoTaskService;
	
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
	public Contract save(Contract contract) {
		
		
		/************************************************************/
		//保存用户信息
		User user=contract.getUser();
		
		String userName=contract.getUserName();
		
		user.setLoginName(userName);
		
		//查询出模板公司
		Office company=officeServiceFacade.get(COMPANY_TEMPLET_ID);
		//查询模板公司下的股东角色
		Role companyRole=this.systemServiceFacade.findRoleByOfficeId(company.getId()).get(0);
		//查询模板公司下面所有机构部门
		List<Office> officeList=officeServiceFacade.findOfficesByParentId(company.getParentId()+","+company.getId());
		//复制模板公司数据插入数据库
		company.setId(null);
		company.setName(contract.getOrgName());
		company=officeServiceFacade.save(company);
		
		companyRole.setId(null);
		companyRole.setName(contract.getOrgName()+companyRole.getName());
		companyRole.setEnname(contract.getOffice().getCode()+companyRole.getEnname());
		//设置股东角色隶属部门
		companyRole.setOffice(company);
		companyRole.setCurrentUser(contract.getCurrentUser());
		companyRole = systemServiceFacade.saveRole(companyRole);
		//循环得到模板公司下面部门
		for (Office office : officeList) {
			//查询机构下的所有角色
			List<Role> mubanList = this.systemServiceFacade.findRoleByOfficeId(office.getId());
			office.setId(null);
			office.setName(contract.getOrgName()+office.getName());
			office.setParent(company);
			office.setParentIds(company.getParentId()+","+company.getId());
			//复制机构保存
			office=officeServiceFacade.save(office);
			for (Role role : mubanList) {
				//复制角色,保存角色
				role.setId(null);
				role.setName(contract.getOrgName()+role.getName());
				role.setEnname(contract.getOffice().getCode()+role.getEnname());
				//设置角色隶属部门
				role.setOffice(office);
				role.setCurrentUser(contract.getCurrentUser());
				role = systemServiceFacade.saveRole(role);
			}
		}
		
		
		//用户公司
		user.setCompany(company);
		//用户的部门为新建的公司模板
		user.setOffice(company);
		//用户姓名
		user.setName(contract.getUserName());
		user.setNo("");
		
		
		//保存的公司的机构为新建的公司模板
		contract.setOffice(company);
		
		//保存用户信息
		user.getRoleList().add(companyRole);
		user.setCurrentUser(contract.getCurrentUser());
		user = systemServiceFacade.saveUser(user);
		systemServiceFacade.assignUserToRole(companyRole,user);
		
		
		
		
		//保存账户信息
		
		//保存contract
		
		//保存config
		
		//分配角色
		
		//公司模板
		
		
		MemberAccount memberAccount=new MemberAccount();
		//获取当前用户信息 
		User seesionUser = contract.getCurrentUser();
		memberAccount.setParentAgentId(seesionUser.getId());
		memberAccount.setParentAgentIds(seesionUser.getId()+","+user.getId());
		memberAccount.setOrgId(company);
		memberAccount.setBlance("0");
		memberAccount.setBlanceFrozen("0");
		memberAccount.setStatus("0");
		memberAccount.setUser(user);
		//会员类型为股东
		memberAccount.setAccountType("3");
		memberAccount.setQqNo(contract.getQqNo());
		memberAccount.setMobileNo(contract.getMobileNo());
		memberAccount.setSecPassword(contract.getSecPassword());
		//账户状态1正常，2冻结
		memberAccount.setStatus("1");
		//保存会员信息
		memberAccount.setCurrentUser(contract.getCurrentUser());
		memberAccountService.save(memberAccount);
		
		
		//保存代办任务
		TodoTask todo=new TodoTask();
		todo.setTitle("公司开户提醒");	//任务标题
		todo.setContent("");	//任务正文
		todo.setType("1"); 	//任务类型
		todo.setStatus("1"); 	//任务状态
		todo.setSenderId(user); 	//申请者
		todo.setReceiverId(seesionUser);	//处理者
		todoTaskService.save(todo);
		
		
		contract.setAccountId(memberAccount.getId());
		/************************************************************/
		contract.setOffice(company);
		contract.setUser(user);
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
		return contract;
	}
	
	@Transactional(readOnly = false)
	public void delete(Contract contract) {
		super.delete(contract);
		contractConfigDao.delete(new ContractConfig(contract));
	}
	
}