package com.qhit.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.qhit.dao.IStorageDao;
import com.qhit.dao.base.BaseDao;
import com.qhit.model.Storage;
import com.qhit.model.StorageInfo;

public class StorageDaoImpl implements IStorageDao {

	@Override
	public boolean save(Storage storage) {
		boolean bool = false;
		try {
			String sql = "insert into storage(goodsId,storhead,storageDate) values(?,?,?)";
			bool = BaseDao.update(sql, new Object[] { storage.getGoodsId(), storage.getStorhead(), storage.getStorageDate() });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bool;
	}

	@Override
	public List<Storage> searchAll(String typeId, String startTime, String endTime) {
		String sql = "select * from storage s,goods g,goodstype t where g.gid=s.goodsId and t.typeId = g.typeId";
		if (!"".equals(typeId)) {
			sql += " and t.typeId = " + typeId + "";
		}
		if (!"".equals(startTime) && !"".equals(endTime)) {
			sql += " and s.storageDate between DATE_FORMAT('" + startTime + "','%Y-%m-%d') and DATE_FORMAT('" + endTime + "','%Y-%m-%d')";
		}
		sql +=" order by s.id asc";
		List<Storage> storages = BaseDao.findMoreResult(sql, Storage.class);
		return storages;
	}

	public List<StorageInfo> searchByInfo() {
		Connection conn = BaseDao.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<StorageInfo> storageInfos = new ArrayList<StorageInfo>();
		try {
			pst = conn
					.prepareStatement("select distinct DATE_FORMAT(i.OutDate,'%Y') as year,DATE_FORMAT(i.OutDate,'%m') as month ,DATE_FORMAT(i.OutDate,'%d') as date from storage s,goods g,goodstype t, sellinfo i where g.gid=s.goodsId and t.typeId = g.typeId AND i.Pid = g.gid GROUP BY DATE_FORMAT(s.storageDate, '%Y') order by YEAR ,month,date asc");
			rs = pst.executeQuery();
			while (rs.next()) {
				StorageInfo info = new StorageInfo();

				info.setDate(rs.getLong("date"));
				info.setYear(rs.getLong("year"));
				info.setMonth(rs.getLong("month"));
				storageInfos.add(info);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return storageInfos;
	}

	@Override
	public List<Storage> getByAll(String typeId, String startNum, String endNum) {
		String sql = "select * from storage s,goods g,goodstype t where g.gid=s.goodsId and t.typeId = g.typeId";
		if (!"".equals(startNum) && !"".equals(endNum)) {
			sql += " and g.goodNum BETWEEN '" + startNum + "' and '" + endNum + "'";
		}
		if (!"".equals(typeId)) {
			sql += " and t.typeId = " + typeId + "";
		}
		List<Storage> storages = BaseDao.findMoreResult(sql, Storage.class);
		return storages;
	}
}