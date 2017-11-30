/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.trade.modules.contract.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.game.common.mapper.JsonMapper;
import com.game.common.persistence.Page;
import com.game.common.service.CrudService;
import com.game.common.utils.StringUtils;
import com.game.modules.contract.entity.Contract;
import com.game.modules.contract.entity.ContractConfig;
import com.game.modules.contract.service.ContractService;
import com.game.modules.lottery.entity.LotteryPlayConfig;
import com.game.modules.member.entity.MemberAccount;
import com.game.modules.member.entity.MemberPlayConfig;
import com.game.modules.member.service.MemberAccountService;
import com.game.modules.member.service.MemberPlayConfigService;
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
	private MemberPlayConfigService memberPlayConfigService;
	
	@Autowired
	private TodoTaskService todoTaskService;
	
	//开户类型1公司，2代理
	private static final String COMPANY ="1";
	private static final String AGENT ="2";
	
	
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
	
	
	/**
	 * 保存公司
	 */
	@Transactional(readOnly = false)
	public Contract save(Contract contract) {
		
		//保存用户信息
		if(StringUtils.isBlank(contract.getId())) {
			User user=contract.getUser();
			String officeCode=getFixLenthString(5);
			
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
			company.setCode(officeCode);
			company=officeServiceFacade.save(company);
			contract.setOffice(company);
			
			companyRole.setId(null);
			companyRole.setName(contract.getOrgName()+companyRole.getName());
			companyRole.setEnname(contract.getOffice().getCode()+companyRole.getEnname());
			//设置股东角色隶属部门
			companyRole.setOffice(company);
			companyRole.setCurrentUser(contract.getCurrentUser());
			companyRole = systemServiceFacade.saveRole(companyRole);
			//循环得到模板公司下面部门
			int codeCount=1;
			for (Office office : officeList) {
				//查询机构下的所有角色
				List<Role> mubanList = this.systemServiceFacade.findRoleByOfficeId(office.getId());
				office.setId(null);
				office.setName(contract.getOrgName()+office.getName());
				office.setParent(company);
				office.setParentIds(company.getParentId()+","+company.getId());
				office.setCode(officeCode+"00"+codeCount++);
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
			
			//保存用户信息
			user.getRoleList().add(companyRole);
			user.setCurrentUser(contract.getCurrentUser());
			user = systemServiceFacade.saveUser(user);
			systemServiceFacade.assignUserToRole(companyRole,user);
			company.setPrimaryPerson(user);
			company=officeServiceFacade.save(company);
			
			MemberAccount memberAccount=new MemberAccount();
			//获取当前用户信息 
			User seesionUser = contract.getCurrentUser();
			memberAccount.setParentAgentId(seesionUser.getId());
			memberAccount.setParentAgentIds(seesionUser.getId()+","+user.getId());
			memberAccount.setOrgId(company);
			memberAccount.setBlance(new BigDecimal(0));
			memberAccount.setBlanceFrozen("0");
			memberAccount.setStatus("0");
			memberAccount.setUser(user);
			//会员类型为股东
			memberAccount.setAccountType("1");
			memberAccount.setQqNo(contract.getQqNo());
			memberAccount.setMobileNo(contract.getMobileNo());
			memberAccount.setSecPassword(contract.getSecPassword());
			//账户状态1正常，2冻结
			memberAccount.setStatus("1");
			//保存会员信息
			memberAccount.setCurrentUser(contract.getCurrentUser());
			memberAccountService.save(memberAccount);
			
			contract.setAccountId(memberAccount.getId());
			contract.setOffice(company);
			contract.setUser(user);
			
			//设置公司状态为审核中
			contract.setStatus("0");
			//1公司开户，2代理开户
			contract.setOpenType("1");
			//保存代办任务
			TodoTask todo=new TodoTask();
			/*if(COMPANY.equals(contract.getOpenType())) {
				todo.setTitle("公司开户提醒");	//任务标题
			}else if(AGENT.equals(contract.getOpenType())){
				todo.setTitle("代理开户提醒");	//任务标题
			}*/
			todo.setTitle("公司开户提醒");	//任务标题
			todo.setContent(contract.getOrgName()+"开户");	//任务正文
			todo.setType("1"); 	//任务类型
			todo.setStatus("1"); 	//任务状态
			todo.setSenderId(contract.getUser()); 	//申请者
			todo.setReceiverId(contract.getCurrentUser());	//处理者
			todo.setCurrentUser(contract.getCurrentUser());
			todoTaskService.save(todo);
			
		}
		super.save(contract);
		for (ContractConfig contractConfig : contract.getContractConfigList()){
			if (contractConfig.getId() == null){
				continue;
			}
			if (ContractConfig.DEL_FLAG_NORMAL.equals(contractConfig.getDelFlag())){
				if (StringUtils.isBlank(contractConfig.getId())){
					contractConfig.setContractId(contract);
					contractConfig.setCurrentUser(contract.getCurrentUser());
					contractConfig.preInsert();
					//将页面上的分红百分比转换为小数
					BigDecimal beniftRate = contractConfig.getBeniftRate().divide(new BigDecimal(100),4,RoundingMode.HALF_UP);
					//将页面上万元转换为元
					contractConfig.setRangeStart(contractConfig.getRangeStart().multiply(new BigDecimal(10000)));
					contractConfig.setRangeEnd(contractConfig.getRangeEnd().multiply(new BigDecimal(10000)));
					contractConfig.setBeniftRate(beniftRate);
					contractConfigDao.insert(contractConfig);
				}else{
					contractConfig.setCurrentUser(contract.getCurrentUser());
					contractConfig.preUpdate();
					//将页面上的分红百分比转换为小数
					BigDecimal beniftRate = contractConfig.getBeniftRate().divide(new BigDecimal(100),4,RoundingMode.HALF_UP);
					//将页面上万元转换为元
					contractConfig.setRangeStart(contractConfig.getRangeStart().multiply(new BigDecimal(10000)));
					contractConfig.setRangeEnd(contractConfig.getRangeEnd().multiply(new BigDecimal(10000)));
					contractConfig.setBeniftRate(beniftRate);
					contractConfigDao.update(contractConfig);
				}
			}else{
				contractConfig.setCurrentUser(contract.getCurrentUser());
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
	/* 
	 * 返回长度为【strLength】的随机数加5位时间毫秒值
	 */  
	private String getFixLenthString(int strLength) {  
	      
	    Random rm = new Random();
	    String returnStr="";
	    int i=0;
	    while(i<strLength) {
	    	int ma=rm.nextInt(10);
	    	i++;
	    	returnStr+=ma;
	    }
	    
	    Long time=new Date().getTime();
	    String strTime=time.toString();
	  
	    return returnStr+strTime.substring(strTime.length()-5,strTime.length());
	}

	/**
	 * 保存代理
	 */
	@Override
	@Transactional(readOnly = false)
	public Contract saveAgent(Contract contract) {
		//this.save(contract);
		//保存用户信息
		if(StringUtils.isBlank(contract.getId())) {
			User user=contract.getUser();
			
			String userName=contract.getUserName();
			
			user.setLoginName(userName);
			
			User sessionUser=contract.getCurrentUser();
			//根据当前userid查询会员信息
			MemberAccount sessionMember=memberAccountService.getByUserId(sessionUser.getId());
			
			//当前登录用户机构
			Office userOffice=officeServiceFacade.get(sessionUser.getOffice().getId());
			
			//代理下的角色
			Role agentRole = this.systemServiceFacade.findRoleByOfficeId(userOffice.getId()).get(0);
			
				/*//查询机构下面所有机构部门
				List<Office> officeList=officeServiceFacade.findOfficesByParentId(userOffice.getParentId()+","+userOffice.getId());
				Office agentOffice=null;
				Role agentRole=null;
				for (Office office : officeList) {
					//查找公司下面的代理部门
					if(-1 != office.getName().indexOf("代理")) {
						agentOffice=office;
						//代理下的角色
						agentRole = this.systemServiceFacade.findRoleByOfficeId(office.getId()).get(0);
					}
				}*/
				//用户的部门为新建的公司模板
			user.setOffice(userOffice);
			
			//用户公司
			user.setCompany(sessionUser.getOffice());
			
			//用户姓名
			user.setName(contract.getUserName());
			user.setNo("");
			
			//保存用户信息
			user.getRoleList().add(agentRole);
			user.setCurrentUser(contract.getCurrentUser());
			user = systemServiceFacade.saveUser(user);
			systemServiceFacade.assignUserToRole(agentRole,user);
			
			MemberAccount memberAccount=new MemberAccount();
			//获取当前用户信息 
			User seesionUser = contract.getCurrentUser();
			memberAccount.setParentAgentId(seesionUser.getId());
			memberAccount.setParentAgentIds(seesionUser.getId()+","+user.getId());
			memberAccount.setOrgId(userOffice);
			memberAccount.setBlance(new BigDecimal(0));
			memberAccount.setBlanceFrozen("0");
			memberAccount.setStatus("0");
			memberAccount.setUser(user);
			//会员类型为代理
			memberAccount.setAccountType("2");
			memberAccount.setQqNo(contract.getQqNo());
			memberAccount.setMobileNo(contract.getMobileNo());
			memberAccount.setSecPassword(contract.getSecPassword());
			//账户状态1正常，2冻结
			memberAccount.setStatus("1");
			//保存会员信息
			memberAccount.setCurrentUser(contract.getCurrentUser());
			memberAccountService.save(memberAccount);
			
			//保存代理返点配置信息
			List<LotteryPlayConfig> playConfigList = contract.getPlayList();
			String playConfig = JsonMapper.toJsonString(playConfigList);
			MemberPlayConfig memberPlayConfig=new MemberPlayConfig();
			memberPlayConfig.setUser(user);
			memberPlayConfig.setAccountId(memberAccount.getId());
			memberPlayConfig.setPlayConfig(playConfig);
			memberPlayConfig.setUserName(contract.getUser().getName());
			memberPlayConfig.setCurrentUser(contract.getCurrentUser());
			memberPlayConfigService.save(memberPlayConfig);
			
			contract.setAccountId(memberAccount.getId());
			contract.setOffice(userOffice);
			contract.setUser(user);
			
			//设置代理状态为审核中
			contract.setStatus("0");
			//1公司开户，2代理开户
			contract.setOpenType("2");
			//保存代办任务
			TodoTask todo=new TodoTask();
			todo.setTitle("代理开户提醒");	//任务标题
			todo.setContent(contract.getOrgName()+"开户");	//任务正文
			todo.setType("1"); 	//任务类型
			todo.setStatus("1"); 	//任务状态
			todo.setSenderId(contract.getUser()); 	//申请者
			todo.setReceiverId(contract.getCurrentUser());	//处理者
			todo.setCurrentUser(contract.getCurrentUser());
			todoTaskService.save(todo);
			
		}
		super.save(contract);
		for (ContractConfig contractConfig : contract.getContractConfigList()){
			if (contractConfig.getId() == null){
				continue;
			}
			if (ContractConfig.DEL_FLAG_NORMAL.equals(contractConfig.getDelFlag())){
				if (StringUtils.isBlank(contractConfig.getId())){
					contractConfig.setContractId(contract);
					contractConfig.setCurrentUser(contract.getCurrentUser());
					contractConfig.preInsert();
					//将页面上的分红百分比转换为小数
					BigDecimal beniftRate = contractConfig.getBeniftRate().divide(new BigDecimal(100),4,RoundingMode.HALF_UP);
					//将页面上万元转换为元
					contractConfig.setRangeStart(contractConfig.getRangeStart().multiply(new BigDecimal(10000)));
					contractConfig.setRangeEnd(contractConfig.getRangeEnd().multiply(new BigDecimal(10000)));
					contractConfig.setBeniftRate(beniftRate);
					contractConfigDao.insert(contractConfig);
				}else{
					contractConfig.setCurrentUser(contract.getCurrentUser());
					contractConfig.preUpdate();
					//将页面上的分红百分比转换为小数
					BigDecimal beniftRate = contractConfig.getBeniftRate().divide(new BigDecimal(100),4,RoundingMode.HALF_UP);
					//将页面上万元转换为元
					contractConfig.setRangeStart(contractConfig.getRangeStart().multiply(new BigDecimal(10000)));
					contractConfig.setRangeEnd(contractConfig.getRangeEnd().multiply(new BigDecimal(10000)));
					contractConfig.setBeniftRate(beniftRate);
					contractConfigDao.update(contractConfig);
				}
			}else{
				contractConfig.setCurrentUser(contract.getCurrentUser());
				contractConfigDao.delete(contractConfig);
			}
		}
		return contract;
	} 
	
}