package com.qhit.dao;

import java.util.List;

import com.qhit.model.Storage;
import com.qhit.model.StorageInfo;

public interface IStorageDao {

	/**
	 * 保存商品库存
	 * 
	 * @param storage
	 * @return
	 */
	public boolean save(Storage storage);

	/**
	 * 获取年月日信息
	 * 
	 * @return
	 */
	public List<StorageInfo> searchByInfo();

	/**
	 * 查询所有入库记录
	 * 
	 * @param typeId
	 *            类型编号
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return
	 */
	public List<Storage> searchAll(String typeId, String startTime, String endTime);

	/**
	 * 根据条件查询商品信息
	 * 
	 * @param startNum
	 *            开始数量
	 * @param endNum
	 *            结束数量
	 * @return
	 */
	public List<Storage> getByAll(String typeId,String startNum, String endNum);
}