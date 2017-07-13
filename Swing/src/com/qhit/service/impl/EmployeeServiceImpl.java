package com.qhit.service.impl;

import java.util.List;

import com.qhit.dao.IEmployeeDao;
import com.qhit.dao.impl.EmployeeDaoImpl;
import com.qhit.model.Employee;
import com.qhit.service.IEmployeeService;

public class EmployeeServiceImpl implements IEmployeeService {

	private IEmployeeDao employeeDao = new EmployeeDaoImpl();

	@Override
	public List<Employee> searchAll() {
		return this.employeeDao.searchAll();
	}

	@Override
	public boolean save(Employee employee) {
		return this.employeeDao.save(employee);
	}

	@Override
	public boolean update(Employee employee) {
		return this.employeeDao.update(employee);
	}

	@Override
	public Employee findById(int id) {
		return this.employeeDao.findById(id);
	}

	@Override
	public Employee login(String userName, String password) {
		return this.employeeDao.login(userName, password);
	}

	@Override
	public boolean del(int id) {
		return this.employeeDao.del(id);
	}
}