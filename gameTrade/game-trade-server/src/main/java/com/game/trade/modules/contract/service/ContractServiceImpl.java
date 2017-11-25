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
import com.game.modules.member.service.MemberAccountService;
import com.game.modules.sys.entity.Office;
import com.game.modules.sys.entity.Role;
import com.game.modules.sys.entity.User;
import com.game.modules.sys.service.OfficeServiceFacade;
import com.game.modules.sys.service.SystemServiceFacade;
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
	/**
	 * 市场模板id
	 */
	private final static String MARKET_HOUSE_TEMPLET_ID="1724d89569ab48208824b2b47e7f76a6";
	/**
	 * 财务模板id
	 */
	private final static String FINANCE_TEMPLET_ID="87aa06e95bbc44bba59c0c44a6b10da3";
	/**
	 * 推广模板id
	 */
	private final static String POPULARIZE_TEMPLET_ID="e6debb2c9a2c41a7b52b488287c84514";
	/**
	 * 客服模板id
	 */
	private final static String CUSTOM_SERVICE_TEMPLET_ID="dce6062d5a1848689a644549e4d1e9cb";
	
	/*************************角色模板*************************/
	/**
	 * 公司股东
	 */
	private final static String SHAREHOLDER_ID="cf94fb8be8e74a6b847e9be418c3efa5";
	/**
	 * 客服专员
	 */
	private final static String CUSTOM_SERVICE_ID="7fa24f3b31644f5f8b8e39abaf1e20c3";
	/**
	 * 客服主管
	 */
	private final static String CUSTOM_SERVICE_SUPERVISO_ID="646785a40d89486bb69cdbf79999e0cc";
	/**
	 * 财务专员 
	 */
	private final static String FINANCE_ID="266b49ce68ab450399dbc9dc9cee0cff";
	/**
	 * 财务主管
	 */
	private final static String FINANCE_SUPERVISO_ID="292fd51d14a34f1fb652e42510b0edf6";
	
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
		
		/****************************************************
		 ********************* 复制模板start********************
		 ****************************************************/
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
				role = systemServiceFacade.saveRole(role);
			}
		}
		//复制模板公司
		/*Office company=officeServiceFacade.get(COMPANY_TEMPLET_ID);
		company.setId(null);
		company.setName(contract.getOrgName());
		company=officeServiceFacade.save(company);
		System.out.println(company.getName()+"id："+company.getId());
		
		//复制市场模板
		Office marketHouse=officeServiceFacade.get(MARKET_HOUSE_TEMPLET_ID);
		marketHouse.setId(null);
		marketHouse.setName(contract.getOrgName()+"市场部");
		marketHouse.setParent(company);
		marketHouse.setParentIds(company.getParentId()+","+company.getId());
		marketHouse=officeServiceFacade.save(marketHouse);
		System.out.println(marketHouse.getName()+"id："+marketHouse.getId());
		
		//财务模板
		Office finance=officeServiceFacade.get(FINANCE_TEMPLET_ID);
		finance.setId(null);
		finance.setName(contract.getOrgName()+"财务部");
		finance.setParent(company);
		finance.setParentIds(company.getParentId()+","+company.getId());
		finance=officeServiceFacade.save(finance);
		System.out.println(finance.getName()+"id："+finance.getId());
		
		//推广模板
		Office popularize=officeServiceFacade.get(POPULARIZE_TEMPLET_ID);
		popularize.setId(null);
		popularize.setName(contract.getOrgName()+"推广部");
		popularize.setParent(company);
		popularize.setParentIds(company.getParentId()+","+company.getId());
		popularize=officeServiceFacade.save(popularize);
		System.out.println(popularize.getName()+"id："+popularize.getId());
		
		//客服模板
		Office customService=officeServiceFacade.get(CUSTOM_SERVICE_TEMPLET_ID);
		customService.setId(null);
		customService.setName(contract.getOrgName()+"客服部");
		customService.setParent(company);
		customService.setParentIds(company.getParentId()+","+company.getId());
		customService=officeServiceFacade.save(customService);
		System.out.println(customService.getName()+"id："+customService.getId());*/
		/****************************************************
		 ********************* 复制模板end	*********************
		 ****************************************************/
		
		//用户公司
		/*user.setCompany(company);
		//用户的部门为新建的公司模板
		user.setOffice(company);
		//用户姓名
		user.setName(contract.getUserName());
		user.setPassword(systemServiceFacade.entryptPassword(user.getPassword()));
		user.setNo("");*/
		
		/****************************************************
		 ********************* 复制角色start********************
		 ****************************************************/
		
		
		
		/*for (Role role : mubanList) {
			//Role yongRole = new Role();
			role.setId(null);
			role.setOffice(company);
			role.setName(contract.getOrgName() + role.getName());
			role.setEnname(contract.getOffice().getCode() + role.getEnname());
			systemServiceFacade.saveRole(role);
		}*/
		
		/*//股东角色
		Role shareholder=systemServiceFacade.getRole(SHAREHOLDER_ID);
		shareholder.setId(null);
		shareholder.setOffice(company);
		shareholder.setName(contract.getOrgName()+shareholder.getName());
		shareholder.setEnname(contract.getOffice().getCode()+shareholder.getEnname());
		systemServiceFacade.saveRole(shareholder);
		
		//客服角色
		Role custom=systemServiceFacade.getRole(CUSTOM_SERVICE_ID);
		custom.setId(null);
		custom.setOffice(company);
		custom.setName(contract.getOrgName()+custom.getName());
		custom.setEnname(contract.getOffice().getCode()+custom.getEnname());
		systemServiceFacade.saveRole(custom);
		
		//客服主管
		Role customServiceSuperviso=systemServiceFacade.getRole(CUSTOM_SERVICE_SUPERVISO_ID);
		customServiceSuperviso.setId(null);
		customServiceSuperviso.setOffice(company);
		customServiceSuperviso.setName(contract.getOrgName()+customServiceSuperviso.getName());
		customServiceSuperviso.setEnname(contract.getOffice().getCode()+customServiceSuperviso.getEnname());
		systemServiceFacade.saveRole(customServiceSuperviso);
		
		//财务角色
		Role fin=systemServiceFacade.getRole(FINANCE_ID);
		fin.setId(null);
		fin.setOffice(company);
		fin.setName(contract.getOrgName()+fin.getName());
		fin.setEnname(contract.getOffice().getCode()+fin.getEnname());
		systemServiceFacade.saveRole(fin);
		
		//财务主管
		Role financeSuperviso=systemServiceFacade.getRole(FINANCE_SUPERVISO_ID);
		financeSuperviso.setId(null);
		financeSuperviso.setOffice(company);
		financeSuperviso.setName(contract.getOrgName()+financeSuperviso.getName());
		financeSuperviso.setEnname(contract.getOffice().getCode()+financeSuperviso.getEnname());
		systemServiceFacade.saveRole(financeSuperviso);
		*/
		
		/****************************************************
		 ********************* 复制角色end**********************
		 ****************************************************/
		
		//保存的公司的机构为新建的公司模板
		/*contract.setOffice(company);
		
		//保存用户信息
		systemServiceFacade.assignUserToRole(companyRole,user);
		systemServiceFacade.saveUser(user);*/
		
		
		
		//保存账户信息
		
		//保存contract
		
		//保存config
		
		//分配角色
		
		//公司模板
		
		
		/*MemberAccount memberAccount=new MemberAccount();
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
		memberAccount.setSecPassword(systemServiceFacade.entryptPassword(contract.getSecPassword()));
		//账户状态1正常，2冻结
		memberAccount.setStatus("1");
		//保存会员信息
		memberAccountService.save(memberAccount);*/
		
		
		//保存代办任务
		/*TodoTask todo=new TodoTask();
		todo.setTitle("公司开户提醒");	//任务标题
		todo.setContent("");	//任务正文
		todo.setType("1"); 	//任务类型
		todo.setStatus("1"); 	//任务状态
		todo.setSenderId(user); 	//申请者
		todo.setReceiverId(seesionUser);	//处理者
		todoTaskService.save(todo);*/
		
		
//		contract.setAccountId(memberAccount.getId());
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