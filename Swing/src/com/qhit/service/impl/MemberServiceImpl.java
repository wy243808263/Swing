package com.qhit.service.impl;

import java.util.List;

import com.qhit.dao.IMemberDao;
import com.qhit.dao.impl.MemberDaoImpl;
import com.qhit.model.Member;
import com.qhit.service.IMemberService;

public class MemberServiceImpl implements IMemberService {

	private IMemberDao memberDao = new MemberDaoImpl();

	@Override
	public List<Member> searchAll() {
		return this.memberDao.searchAll();
	}

	@Override
	public boolean update(Member user) {
		return this.memberDao.update(user);
	}

	@Override
	public boolean del(int id) {
		return this.memberDao.del(id);
	}

	@Override
	public Member findById(int id) {
		return this.memberDao.findById(id);
	}

	@Override
	public boolean save(Member user) {
		return this.memberDao.save(user);
	}
}