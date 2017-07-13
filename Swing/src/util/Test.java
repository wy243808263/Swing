package util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qhit.dao.base.BaseDao;

public class Test {
	public static void main(String[] args) {
		BaseDao.getConnection();

	}

	/**
	 * ִ�в�ѯ,���ص���ֵ
	 * 
	 * @param sql
	 * @return
	 */
	public static Object getValue(String sql) {
		Connection con = BaseDao.getConnection();// getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			if (rs.next()) {
				return rs.getObject(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ִ�в�ѯ����:" + sql);
			throw new RuntimeException(e);
		} finally {
			// closeResource(con, rs, st);
		}
		return null;
	}

	/**
	 * ִ�в�ѯ�����ض��н��List<Map>
	 * 
	 * @param sql
	 * @param parms
	 * @return
	 */
	public static List<Map<String, Object>> getList(String sql, Object[] parms) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection con = BaseDao.getConnection();// getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = con.prepareStatement(sql);
			if (parms != null) {
				for (int i = 0; i < parms.length; i++) {
					st.setObject(i + 1, parms[i]);
				}
			}
			rs = st.executeQuery();
			int colCnt = rs.getMetaData().getColumnCount();
			String colName = null;
			Object value = null;
			while (rs.next()) {
				Map<String, Object> row = new HashMap<String, Object>();
				for (int i = 1; i <= colCnt; i++) {
					value = rs.getObject(i);
					colName = rs.getMetaData().getColumnName(i);
					row.put(colName, value);
				}
				list.add(row);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// closeResource(con, rs, st);
		}
		return list;
	}
}