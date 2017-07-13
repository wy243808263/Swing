package com.qhit.service.impl;

import java.util.List;

import com.qhit.dao.IGoodsDao;
import com.qhit.dao.impl.GoodsDaoImpl;
import com.qhit.model.Goods;
import com.qhit.service.IGoodsService;

public class GoodsServiceImpl implements IGoodsService {

	private IGoodsDao goodsDao = new GoodsDaoImpl();

	@Override
	public boolean save(Goods goods) {
		return this.goodsDao.save(goods);
	}

	@Override
	public boolean update(Goods goods) {
		return this.goodsDao.update(goods);
	}

	@Override
	public boolean del(String id) {
		return this.goodsDao.del(id);
	}

	@Override
	public Goods findById(String id) {
		return this.goodsDao.findById(id);
	}

	@Override
	public List<Goods> searchAll(String name, String typeName) {
		return this.goodsDao.searchAll(name, typeName);
	}

	@Override
	public boolean check(String id) {
		return this.goodsDao.check(id);
	}
}