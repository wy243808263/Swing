package com.qhit.dao.impl;

import java.util.List;

import com.qhit.dao.IMemberDao;
import com.qhit.dao.base.BaseDao;
import com.qhit.model.Member;

public class MemberDaoImpl implements IMemberDao {

	@Override
	public List<Member> searchAll() {
		String sql = "select * from member";
		List<Member> members = BaseDao.findMoreResult(sql, Member.class);
		return members;
	}

	@Override
	public boolean del(int id) {
		boolean bool = false;
		try {
			String sql = "delete from member where id=?";
			bool = BaseDao.update(sql, new Object[] { id });
		} catch (Exception e) {
			e.printStackTrace();
			bool = false;
		}
		return bool;
	}

	@Override
	public Member findById(int id) {
		String sql = "select * from member where id=" + id + "";
		List<Member> members = BaseDao.findMoreResult(sql, Member.class);
		return members.size() > 0 ? members.get(0) : null;
	}

	@Override
	public boolean update(Member user) {
		boolean bool = false;
		try {
			String sql = "update member set userNo=?,userName=?,sex=?,age=?,userID=?,telePhone=?,address=?,joinDate=?,userCard=? where id=?";
			bool = BaseDao.update(sql, new Object[] { user.getUserNo(), user.getUserName(), user.getSex(), user.getAge(), user.getUserID(), user.getTelePhone(), user.getAddress(), user.getJoinDate(),
					user.getUserCard(), user.getId() });
		} catch (Exception e) {
			e.printStackTrace();
			bool = false;
		}
		return bool;
	}

	@Override
	public boolean save(Member user) {
		boolean bool = false;
		try {
			String sql = "insert into member(userNo,userName,sex,age,userID,telePhone,address,joinDate,userCard) values(?,?,?,?,?,?,?,?,?)";
			bool = BaseDao.update(sql, new Object[] { user.getUserNo(), user.getUserName(), user.getSex(), user.getAge(), user.getUserID(), user.getTelePhone(), user.getAddress(), user.getJoinDate(),
					user.getUserCard() });
		} catch (Exception e) {
			e.printStackTrace();
			bool = false;
		}
		return bool;
	}
}