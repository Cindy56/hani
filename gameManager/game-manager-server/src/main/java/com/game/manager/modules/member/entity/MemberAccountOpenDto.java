package com.game.manager.modules.member.entity;

import java.util.List;

import com.game.manager.common.persistence.DataEntity;
import com.game.manager.modules.lottery.entity.LotteryPlayConfig;
import com.game.manager.modules.sys.entity.User;

public class MemberAccountOpenDto extends DataEntity<MemberPlayConfig> {

	private User user;
	private MemberAccount account;
	private List<LotteryPlayConfig> playList;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public MemberAccount getAccount() {
		return account;
	}
	public void setAccount(MemberAccount account) {
		this.account = account;
	}
	public List<LotteryPlayConfig> getPlayList() {
		return playList;
	}
	public void setPlayList(List<LotteryPlayConfig> playList) {
		this.playList = playList;
	}
	
	
}