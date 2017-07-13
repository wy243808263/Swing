package com.qhit.service.impl;

import java.util.List;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.qhit.dao.ISellInfoDao;
import com.qhit.dao.impl.SellInfoDaoImpl;
import com.qhit.model.SellInfo;
import com.qhit.service.ISellInfoService;

public class SellInfoServiceImpl implements ISellInfoService {

	private ISellInfoDao sellInfoDao = new SellInfoDaoImpl();

	@Override
	public List<SellInfo> searchAll(String IdNo, String typeName) {
		return this.sellInfoDao.searchAll(IdNo, typeName);
	}

	@Override
	public Double find() {
		return this.sellInfoDao.find();
	}

	@Override
	public CategoryDataset getYearsum() {
		return this.sellInfoDao.getYearsum();
	}

	@Override
	public DefaultCategoryDataset getMonthsum(String year) {
		return this.sellInfoDao.getMonthsum(year);
	}

	@Override
	public Double getAvgMonthMoney() {
		return this.sellInfoDao.getAvgMonthMoney();
	}

	@Override
	public Double getYearMoney() {
		return this.getYearMoney();
	}
}