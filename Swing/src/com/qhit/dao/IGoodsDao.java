package com.qhit.dao;

import java.util.List;

import com.qhit.model.Goods;

public interface IGoodsDao {

	/**
	 * 保存商品信息
	 * 
	 * @param goods
	 * @return
	 */
	public boolean save(Goods goods);

	/**
	 * 修改商品信息
	 * 
	 * @param goods
	 * @return
	 */
	public boolean update(Goods goods);

	/**
	 * 根据编号删除商品信息
	 * 
	 * @param id
	 * @return
	 */
	public boolean del(String id);

	/**
	 * 根据编号查询商品信息
	 * 
	 * @param id
	 * @return
	 */
	public Goods findById(String id);

	/**
	 * 查询所有商品
	 * 
	 * @return
	 */
	public List<Goods> searchAll(String name,String typeName);
	
	/**
	 * 用于检查添加的购买商品产品信息表中是否存在
	 * @param id
	 * @return
	 */
	public boolean check(String id);
}