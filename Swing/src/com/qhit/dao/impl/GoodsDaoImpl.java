package com.qhit.dao.impl;

import java.util.List;

import com.qhit.dao.IGoodsDao;
import com.qhit.dao.base.BaseDao;
import com.qhit.model.Goods;

public class GoodsDaoImpl implements IGoodsDao {

	@Override
	public boolean del(String id) {
		boolean bool = false;
		try {
			String sql = "delete from goods where gid=?";
			bool = BaseDao.update(sql, new Object[] { id });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bool;
	}

	@Override
	public Goods findById(String id) {
		String sql = "select * from goods g,goodstype t where t.typeId=g.typeId and gid='" + id + "'";
		List<Goods> goodsList = BaseDao.findMoreResult(sql, Goods.class);
		return goodsList.size() > 0 ? goodsList.get(0) : null;
	}

	@Override
	public boolean save(Goods goods) {
		boolean bool = false;
		try {
			String sql = "insert into goods(goodNo,goodName,goodPrice,goodIntegral,typeId) values(?,?,?,?,?)";
			bool = BaseDao.update(sql, new Object[] { goods.getGoodNo(), goods.getGoodName(), goods.getGoodPrice(), goods.getGoodIntegral(), goods.getTypeId() });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bool;
	}

	@Override
	public List<Goods> searchAll(String name, String typeName) {
		String sql = "SELECT * FROM goods g, goodstype t WHERE 1=1 and t.typeId = g.typeId";
		if (!"".equals(name)) {
			sql += " and g.goodNo like '%" + name + "%'";
		}
		if (!"".equals(typeName)) {
			if (!"--所有商品--".equals(typeName)) {
				sql += " and t.typeName like '%" + typeName + "%'";
			}			
		}
		List<Goods> goodsList = BaseDao.findMoreResult(sql, Goods.class);
		return goodsList;
	}

	@Override
	public boolean update(Goods goods) {
		boolean bool = false;
		try {
			String sql = "update goods set goodNo=?,goodName=?,goodPrice=?,goodIntegral=?,typeId=?,goodNum=? where gid=?";
			bool = BaseDao.update(sql, new Object[] { goods.getGoodNo(), goods.getGoodName(), goods.getGoodPrice(), goods.getGoodIntegral(), goods.getTypeId(), goods.getGoodNum(), goods.getGid() });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bool;
	}

	@Override
	public boolean check(String id) {
		String sql = "select * from goods g,goodstype t where t.typeId=g.typeId and goodNo = '" + id + "'";
		List<Goods> goods = BaseDao.findMoreResult(sql, Goods.class);
		return goods.size() > 0 ? true : false;
	}
}