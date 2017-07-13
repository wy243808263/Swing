package com.qhit.service.impl;

import java.util.List;

import com.qhit.dao.IGoodTypeDao;
import com.qhit.dao.impl.GoodTypeDaoImpl;
import com.qhit.model.GoodType;
import com.qhit.service.IGoodTypeService;

public class GoodTypeServiceImpl implements IGoodTypeService {

	private IGoodTypeDao goodTypeDao = new GoodTypeDaoImpl();

	@Override
	public List<GoodType> searchAll() {
		return this.goodTypeDao.searchAll();
	}

	@Override
	public GoodType searchByName(String name) {
		return this.goodTypeDao.searchByName(name);
	}
}