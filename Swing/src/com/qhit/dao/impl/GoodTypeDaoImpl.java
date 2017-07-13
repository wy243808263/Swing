package com.qhit.dao.impl;

import java.util.List;

import com.qhit.dao.IGoodTypeDao;
import com.qhit.dao.base.BaseDao;
import com.qhit.model.GoodType;

public class GoodTypeDaoImpl implements IGoodTypeDao {

	@Override
	public List<GoodType> searchAll() {
		String sql = "select * from goodstype";
		List<GoodType> goodTypes = BaseDao.findMoreResult(sql, GoodType.class);
		return goodTypes;
	}

	@Override
	public GoodType searchByName(String name) {
		String sql = "select * from goodstype where typeName = '" + name + "'";
		List<GoodType> goodTypes = BaseDao.findMoreResult(sql, GoodType.class);
		return goodTypes.size() > 0 ? goodTypes.get(0) : null;
	}
}