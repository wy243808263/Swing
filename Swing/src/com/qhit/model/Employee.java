package com.qhit.model;

/**
 * 雇员实体类
 * 
 * @author Administrator
 * 
 */
public class Employee {
	private int empNo;// 员工编号
	private String empName;// 员工姓名
	private String empPwd;// 员工密码
	private String sex;// 性别
	private int age;// 年龄
	private String employee;// 职位
	private String empCard;// 员工身份证号
	private String telephone;// 联系电话
	private String address;// 地址
	private String toDate;// 入职日期

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpPwd() {
		return empPwd;
	}

	public void setEmpPwd(String empPwd) {
		this.empPwd = empPwd;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmployee() {
		return employee;
	}

	public void setEmployee(String employee) {
		this.employee = employee;
	}

	public String getEmpCard() {
		return empCard;
	}

	public void setEmpCard(String empCard) {
		this.empCard = empCard;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
}