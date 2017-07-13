package com.qhit.service;

import java.util.List;

import com.qhit.model.Member;

public interface IMemberService {

	/**
	 * 查询全部用户
	 * 
	 * @return
	 */
	public List<Member> searchAll();

	/**
	 * 修改用户信息
	 * 
	 * @param user
	 * @return
	 */
	public boolean update(Member user);

	/**
	 * 删除用户信息
	 * 
	 * @param user
	 * @return
	 */
	public boolean del(int id);

	/**
	 * 根据用户编号查询用户信息
	 * 
	 * @param id
	 * @return
	 */
	public Member findById(int id);

	/**
	 * 保存用户信息
	 * 
	 * @param user
	 * @return
	 */
	public boolean save(Member user);
}