package com.qhit.dao;

import java.util.List;

import com.qhit.model.Employee;

public interface IEmployeeDao {

	/**
	 * 查询所有员工
	 * 
	 * @return
	 */
	public List<Employee> searchAll();

	/**
	 * 保存员工信息
	 * 
	 * @param employee
	 * @return
	 */
	public boolean save(Employee employee);

	/**
	 * 修改员工信息
	 * 
	 * @param employee
	 * @return
	 */
	public boolean update(Employee employee);

	/**
	 * 根据员工编号查询员工信息
	 * 
	 * @param id
	 * @return
	 */
	public Employee findById(int id);

	/**
	 * 登录
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	public Employee login(String userName, String password);

	/**
	 * 根据员工编号删除员工信息
	 * 
	 * @param id
	 * @return
	 */
	public boolean del(int id);
}