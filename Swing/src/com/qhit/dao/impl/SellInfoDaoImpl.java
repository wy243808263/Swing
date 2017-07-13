package com.qhit.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.qhit.dao.ISellInfoDao;
import com.qhit.dao.base.BaseDao;
import com.qhit.model.SellInfo;

public class SellInfoDaoImpl implements ISellInfoDao {

	@Override
	public List<SellInfo> searchAll(String IdNo, String typeName) {
		// String sql = "select * from storage s,goods g,goodstype t,SellInfo i where g.gid=s.goodsId and t.typeId = g.typeId AND i.Pid = g.gid";
		String sql = "SELECT DISTINCT i.SIid, g.gid, g.goodNo, g.goodName, g.goodIntegral, g.goodPrice, i.Num, i.OutDate,	t.typeName,	i.Pid FROM STORAGE s INNER JOIN sellinfo i ON i.Pid = s.goodsId INNER JOIN goods g ON g.gid = i.Pid INNER JOIN goodstype t ON t.typeId = g.typeId";
		if (!"".equals(IdNo)) {
			sql += " and g.goodNo like '%" + IdNo + "%'";
		}
		if (!"".equals(typeName)) {
			sql += " and t.typeName like '%" + typeName + "%'";
		}
		return BaseDao.findMoreResult(sql, SellInfo.class);
	}

	@Override
	public Double find() {
		Connection conn = BaseDao.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Double result = 0.0;
		try {
			String sql = "SELECT MONTH (s.storageDate) AS maxMonth FROM STORAGE s, goods g, goodstype t, sellinfo i WHERE g.gid = s.goodsId AND t.typeId = g.typeId AND i.Pid = g.gid GROUP BY MONTH (i.OutDate) ASC";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				result = rs.getDouble("maxMonth");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public CategoryDataset getYearsum() {
		String sql = "SELECT DATE_FORMAT(a.OutDate, '%Y') YEAR, sum(a.Num * b.goodPrice) total FROM SellInfo a, goods b WHERE a.Pid = b.gid GROUP BY DATE_FORMAT(a.OutDate, '%Y')";
		List<Map<String, Object>> list = BaseDao.getList(sql, null);
		DefaultCategoryDataset dateset = new DefaultCategoryDataset();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			dateset.addValue(Double.valueOf(map.get("total").toString()), "aaa", map.get("YEAR").toString());
		}
		return dateset;
	}

	@Override
	public DefaultCategoryDataset getMonthsum(String year) {
		String sql = "SELECT DATE_FORMAT(s.OutDate, '%Y') month, sum(s.Num * g.goodPrice) total FROM SellInfo s,goods g WHERE s.Pid = g.gid AND YEAR (s.OutDate) = YEAR (NOW())";
		if (!"".equals(year)) {
			sql += "  AND DATE_FORMAT(s.OutDate, '%Y') = " + year + "";
		}
		sql +=" GROUP BY DATE_FORMAT(s.OutDate, '%Y'), YEAR (s.OutDate) = YEAR (NOW())";
		List<Map<String, Object>> list = BaseDao.getList(sql, null);
		DefaultCategoryDataset dateset = new DefaultCategoryDataset();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			if (map.get("total") != null && map.get("month") != null)
				dateset.addValue(Double.valueOf(map.get("total").toString()), "aaa", map.get("month").toString());
			else
				dateset = null;
		}
		return dateset;
	}

	@Override
	public Double getAvgMonthMoney() {
		Connection conn = BaseDao.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Double result = 0.0;
		try {
			String sql = "SELECT avg(g.goodPrice) AS goodPrice FROM STORAGE s, goods g, goodstype t, SellInfo i WHERE g.gid = s.goodsId AND t.typeId = g.typeId AND i.Pid = g.gid AND DATE_SUB(CURDATE(), INTERVAL 30 DAY) <= date(s.storageDate) GROUP BY 	DATE_SUB(CURDATE(), INTERVAL 30 DAY) <= date(s.storageDate)";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				result = rs.getDouble("goodPrice");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Double getYearMoney() {
		Connection conn = BaseDao.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Double result = 0.0;
		try {
			String sql = "SELECT sum(g.goodPrice) AS goodPrice FROM STORAGE s, goods g, goodstype t, SellInfo i WHERE g.gid = s.goodsId AND t.typeId = g.typeId AND i.Pid = g.gid and  YEAR(s.storageDate)=YEAR(NOW()) GROUP BY YEAR(s.storageDate)=YEAR(NOW())";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				result = rs.getDouble("goodPrice");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}