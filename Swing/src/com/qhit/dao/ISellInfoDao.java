package com.qhit.dao;

import java.util.List;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.qhit.model.SellInfo;

public interface ISellInfoDao {

	/**
	 * 查询所有信息
	 * 
	 * @param IdNo
	 * @param typeName
	 * @return
	 */
	public List<SellInfo> searchAll(String IdNo, String typeName);

	/**
	 * 用于得到销售信息表存在的年份和月份
	 * 
	 * @return 返回找到的年份或者是月份
	 */
	public Double find();

	/**
	 * 传入条件得到需要的数据集，用以完成不同需要报表数据集的创建
	 * 
	 * @return
	 */
	public CategoryDataset getYearsum();

	/**
	 * 统计每年销售统计
	 * 
	 * @param year
	 * @return
	 */
	DefaultCategoryDataset getMonthsum(String year);

	/**
	 * 获取月平均销售额
	 * 
	 * @return 销售额
	 */
	Double getAvgMonthMoney();

	/**
	 * 获取年销售总额
	 * 
	 * @return
	 */
	Double getYearMoney();
}