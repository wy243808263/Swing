package com.qhit.dao.base;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 数据库工具类
 * 
 * @author Administrator
 * 
 */
public class BaseDao {
	/**
	 * 打开链接
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			// 加载属性文件，读取数据库连接配置信息
			Properties pro = new Properties();
			pro.load(BaseDao.class.getResourceAsStream("/jdbc.properties"));

			Class.forName(pro.getProperty("jdbc.driver"));
			conn = DriverManager.getConnection(pro.getProperty("jdbc.url"), pro.getProperty("jdbc.username"), pro.getProperty("jdbc.password"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 执行查询，返回多行结果List<Map>
	 * 
	 * @param sql
	 * @param parms
	 * @return
	 */
	public static List<Map<String, Object>> getList(String sql, Object[] parms) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection con = getConnection();
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
			closeResource(con, rs, st);
		}
		return list;
	}

	/**
	 * 通过反射查询多条结果集
	 * 
	 * @param <T>
	 *            范型
	 * @param sql
	 *            语句
	 * @param params
	 *            参数
	 * @param beanClass
	 *            类
	 * @return
	 */
	public static <T> List<T> findMoreResult(String sql, Class<T> beanClass) {
		List<T> lists = new ArrayList<T>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery(sql);
			Field[] fields = beanClass.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
			}

			while (null != rs && rs.next()) {
				T t = beanClass.newInstance();
				for (Field f : fields) {
					String name = f.getName();
					Object value = rs.getObject(name);
					setValue(t, f, value);
				}
				lists.add(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lists;
	}

	/**
	 * 设置value值
	 * 
	 * @param <T>
	 *            泛型
	 * @param t
	 *            模型
	 * @param f
	 *            字段
	 * @param value
	 *            值
	 * @throws IllegalAccessException
	 */
	private static <T> void setValue(T t, Field f, Object value) throws IllegalAccessException {
		// 以数据库类型为准绳，还是以java数据类型为准绳？还是混合两种方式？
		if (null == value)
			return;

		String v = value.toString();
		String n = f.getType().getName();

		if ("java.lang.Byte".equals(n) || "byte".equals(n)) {
			f.set(t, Byte.parseByte(v));
		} else if ("java.lang.Short".equals(n) || "short".equals(n)) {
			f.set(t, Short.parseShort(v));
		} else if ("java.lang.Integer".equals(n) || "int".equals(n)) {
			f.set(t, Integer.parseInt(v));
		} else if ("java.lang.Long".equals(n) || "long".equals(n)) {
			f.set(t, Long.parseLong(v));
		} else if ("java.lang.Float".equals(n) || "float".equals(n)) {
			f.set(t, Float.parseFloat(v));
		} else if ("java.lang.Double".equals(n) || "double".equals(n)) {
			f.set(t, Double.parseDouble(v));
		} else if ("java.lang.String".equals(n)) {
			f.set(t, value.toString());
		} else if ("java.lang.Character".equals(n) || "char".equals(n)) {
			f.set(t, (Character) value);
		} else if ("java.lang.Date".equals(n)) {
			f.set(t, new Date(((java.sql.Date) value).getTime()));
		} else if ("java.lang.Timer".equals(n)) {
			f.set(t, new Time(((java.sql.Time) value).getTime()));
		} else if ("java.sql.Timestamp".equals(n)) {
			f.set(t, (java.sql.Timestamp) value);
		} else if ("java.math.BigDecimal".equals(n)) {
			f.set(t, (java.math.BigDecimal) value);
		} else {
			System.out.println("SqlError：暂时不支持此数据类型，请使用其他类型代替此类型！");
		}
	}

	/**
	 * 执行增删改SQL
	 * 
	 * @param sql
	 */
	public static boolean update(String sql, Object[] parms) {
		Connection con = getConnection();
		PreparedStatement st = null;
		boolean bool = true;
		try {
			st = con.prepareStatement(sql);
			if (parms != null) {
				for (int i = 0; i < parms.length; i++) {
					st.setObject(i + 1, parms[i]);
				}
			}
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("执行修改出错:" + sql);
			bool = false;
			throw new RuntimeException(e);
		} finally {
			closeResource(con, null, st);
		}
		return bool;
	}

	/**
	 * 关闭资源
	 * 
	 * @param conn
	 * @param rs
	 * @param ps
	 * @return
	 */
	public static boolean closeResource(Connection conn, ResultSet rs, Statement ps) {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}