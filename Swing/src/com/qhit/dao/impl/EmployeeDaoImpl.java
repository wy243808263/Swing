package com.qhit.dao.impl;

import java.util.List;

import com.qhit.dao.IEmployeeDao;
import com.qhit.dao.base.BaseDao;
import com.qhit.model.Employee;

public class EmployeeDaoImpl implements IEmployeeDao {

	@Override
	public boolean del(int id) {
		boolean bool = false;
		try {
			String sql = "delete from employee where empNo=?";
			bool = BaseDao.update(sql, new Object[] { id });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bool;
	}

	@Override
	public Employee findById(int id) {
		String sql = "select * from employee where empNo=" + id + "";
		List<Employee> employeeList = BaseDao.findMoreResult(sql, Employee.class);
		return employeeList.size() > 0 ? employeeList.get(0) : null;
	}

	@Override
	public Employee login(String userName, String password) {
		String sql = "select * from employee where empName='" + userName + "' and empPwd='" + password + "'";
		List<Employee> employeeList = BaseDao.findMoreResult(sql, Employee.class);
		return employeeList.size() > 0 ? employeeList.get(0) : null;
	}

	@Override
	public boolean save(Employee employee) {
		boolean bool = false;
		try {
			String sql = "insert into employee(empName,sex,age,employee,empCard,telephone,address,toDate) values(?,?,?,?,?,?,?,?)";
			Object[] params = { employee.getEmpName(), employee.getSex(), employee.getAge(), employee.getEmployee(), employee.getEmpCard(), employee.getTelephone(), employee.getAddress(),
					employee.getToDate() };
			bool = BaseDao.update(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bool;
	}

	@Override
	public List<Employee> searchAll() {
		String sql = "select * from employee";
		List<Employee> employeeList = BaseDao.findMoreResult(sql, Employee.class);
		return employeeList;
	}

	@Override
	public boolean update(Employee employee) {
		boolean bool = false;
		try {
			String sql = "update employee set empName=?,sex=?,age=?,employee=?,empCard=?,telephone=?,address=?,toDate=? where empNo=?";
			Object[] params = { employee.getEmpName(), employee.getSex(), employee.getAge(), employee.getEmployee(), employee.getEmpCard(), employee.getTelephone(), employee.getAddress(),
					employee.getToDate(), employee.getEmpNo() };
			bool = BaseDao.update(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bool;
	}
}