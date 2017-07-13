package com.qhit.tools;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Enumeration;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;

/**
 * 工具集，用来收集一些公共的函数做成静态，减少代码冗余，提高代码的复用
 * 
 * @version 2013-07-22
 * 
 * @成员方法 {@link #isNum(String)} 用于判断是否是合法的数字<br>
 *       {@link #selectAll(JTextField)} 选中一个文本框的所有文本<br>
 *       {@link #setTableStyle(JTable)} 设置表格的公共样式<br>
 *       {@link #fitTableColumns(JTable)} 根据表格的内容自适应表格每一列的宽度<br>
 *       {@link #setJspStyle(JScrollPane, String)} 设置滚动面板的公共样式<br>
 *       {@link #getlocaldatetime()} 获取本地的系统时间(y-m-d h:m:s)<br>
 *       {@link #setReportStyle(JFreeChart)} 设置报表的公共样式
 * 
 */
public class Tools {

	/**
	 * 用于选中一个文本框中的所有文本
	 * 
	 * @param jlb
	 *            文本框
	 * 
	 * 
	 */
	public static void selectAll(JTextField jlb) {
		jlb.setSelectionStart(0);
		jlb.setSelectionColor(new Color(60, 148, 212));
		jlb.setSelectionEnd(jlb.getText().length());
	}

	/**
	 * 设置背景图
	 * 
	 * @param frame
	 */
	public static void setBackImg(JFrame frame, String imgName) {
		Icon icon = new ImageIcon("D:\\repository\\items\\image\\" + imgName);
		// 要设置的背景图片
		JLabel imgLabel = new JLabel(icon);
		// 将背景图放在标签里。
		frame.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		// 将背景标签添加到jfram的LayeredPane面板里。
		imgLabel.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());

		// 设置窗体没有边框
		frame.setUndecorated(true);
		// 设置窗口的关闭方式
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		// 设置窗体居中
		frame.setLocationRelativeTo(null);

		Container cp = frame.getContentPane();
		cp.setLayout(null); // 这里选择绝对布局管理器，对于边界布局管理器，放入控件后，无法显示背景图片；因为将整个面板都填充满了；
		((JPanel) cp).setOpaque(false); // 这样就能显示出背景图片出来了
	}

	/**
	 * 设置表格的公共样式
	 * 
	 * @param jtb
	 *            需要设置的表格对象实例
	 * 
	 */
	@SuppressWarnings("static-access")
	public static void setTableStyle(JTable jtb) {
		// 设置选中行的背景色
		jtb.setSelectionBackground(new Color(51, 153, 255));
		// 设置表格每行的高度
		jtb.setRowHeight(40);
		// 设置点击表头自动实现排序
		jtb.setAutoCreateRowSorter(true);
		// 设置表头文字居中显示
		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) jtb.getTableHeader().getDefaultRenderer();
		renderer.setHorizontalAlignment(renderer.CENTER);

		// 设置表格中的数据居中显示
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		jtb.setDefaultRenderer(Object.class, r);

		// 设置单选模式
		jtb.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// 设置单元格不可拖动
		jtb.getTableHeader().setReorderingAllowed(false);
		// 设置不可改变列宽
		jtb.getTableHeader().setResizingAllowed(false);
		// 设定表格在面板上的大小
		jtb.setPreferredScrollableViewportSize(new Dimension(200, 70));
		// 设置表格填充整个视图
		jtb.setFillsViewportHeight(true);
		// 设置表头行高
		jtb.getTableHeader().setPreferredSize(new Dimension(0, 30));
		// 设置表内容行高
		jtb.setRowHeight(25);
		// 超出大小后，JScrollPane自动出现滚动条
		jtb.setAutoscrolls(true);
		jtb.setOpaque(false);
		jtb.setBorder(null);
		// 设置字体
		jtb.setFont(new Font("新宋体", Font.PLAIN, 15));
		jtb.getTableHeader().setBorder(null);
		jtb.setBackground(Color.WHITE);
		fitTableColumns(jtb);
	}

	/**
	 * 根据表格列的内容自适应表格的每一列宽度
	 * 
	 * @param myTable
	 *            对应的表格对象实例
	 * 
	 */
	@SuppressWarnings("rawtypes")
	private static void fitTableColumns(JTable myTable) {
		myTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JTableHeader header = myTable.getTableHeader();
		int rowCount = myTable.getRowCount();
		Enumeration columns = myTable.getColumnModel().getColumns();
		while (columns.hasMoreElements()) {
			TableColumn column = (TableColumn) columns.nextElement();
			int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
			int width = (int) header.getDefaultRenderer().getTableCellRendererComponent(myTable, column.getIdentifier(), false, false, -1, col).getPreferredSize().getWidth();
			for (int row = 0; row < rowCount; row++) {
				int preferedWidth = (int) myTable.getCellRenderer(row, col).getTableCellRendererComponent(myTable, myTable.getValueAt(row, col), false, false, row, col).getPreferredSize().getWidth();
				width = Math.max(width, preferedWidth);
			}
			header.setResizingColumn(column); // 此行很重要
			column.setWidth(width + myTable.getIntercellSpacing().width);
		}
	}

	/**
	 * 设置滚动面板的公共样式, 注意将滚动面板设置成全局的变量，不然会出现刷新不了表格
	 * 
	 * @param jsp
	 *            滚动面板对象实例
	 * 
	 * @param type
	 *            设置的类型
	 */
	public static void setJspStyle(JScrollPane jsp, String type) {
		if (type.equals("enter")) {
			jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		} else {
			jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
			jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		}
		jsp.getViewport().setOpaque(false);
		jsp.getVerticalScrollBar().setOpaque(false);
	}

	/**
	 * 获取当前系统的时间
	 * 
	 * @return （yyyy-MM-dd HH:mm:ss）格式的日期字符串
	 */
	public static String getlocaldatetime() {
		java.util.Calendar c = java.util.Calendar.getInstance();
		java.text.SimpleDateFormat f = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return f.format(c.getTime());
	}

	/**
	 * 隐藏指定JTable的指定列
	 * 
	 * @see 要隐藏列的JTable对象, 要隐藏的列从0开始
	 * @param table
	 * @param column
	 */
	public static void HiddenCell(JTable table, int column) {
		TableColumn tc = table.getTableHeader().getColumnModel().getColumn(column);
		tc.setMaxWidth(0);
		tc.setPreferredWidth(0);
		tc.setWidth(0);
		tc.setMinWidth(0);
		table.getTableHeader().getColumnModel().getColumn(column).setMaxWidth(0);
		table.getTableHeader().getColumnModel().getColumn(column).setMinWidth(0);
	}

	/**
	 * 显示指定JTable的指定列
	 * 
	 * @see 要显示列的JTable对象, 要显示的列从0开始,列宽
	 * @param table
	 * @param column
	 * @param width
	 */
	public static void showColumn(JTable table, int column, int width) {
		TableColumn tc = table.getColumnModel().getColumn(column);
		tc.setMaxWidth(width);
		tc.setPreferredWidth(width);
		tc.setWidth(width);
		tc.setMinWidth(width);
		table.getTableHeader().getColumnModel().getColumn(column).setMaxWidth(width);
		table.getTableHeader().getColumnModel().getColumn(column).setMinWidth(width);
	}

	/**
	 * 设置报表的公共样式
	 * 
	 * @param chart
	 *            需要设置的报表对象实例
	 * 
	 */
	public static void setReportStyle(JFreeChart chart) {
		// 获取图表区域对象
		CategoryPlot plot = chart.getCategoryPlot();
		plot.setBackgroundPaint(new Color(51, 153, 255));
		plot.setBackgroundAlpha(0.3f);
		// 水平底部列表
		CategoryAxis domainAxis = plot.getDomainAxis();
		// 隐藏尺度线
		domainAxis.setAxisLineVisible(false);
		domainAxis.setAxisLineStroke(new BasicStroke(5));
		domainAxis.setCategoryMargin(0.6);
		// 水平底部标题
		domainAxis.setLabelFont(new Font("新宋体", Font.BOLD, 14));
		// 垂直标题
		domainAxis.setTickLabelFont(new Font("新宋体", Font.BOLD, 12));
		// 获取柱状,可以设置柱形的大小
		ValueAxis rangeAxis = plot.getRangeAxis();
		rangeAxis.setLabelFont(new Font("新宋体", Font.BOLD, 14));
		// 设置标题字体
		chart.getTitle().setFont(new Font("宋体", Font.BOLD, 22));
		BarRenderer redBarRenderer = (BarRenderer) plot.getRenderer();
		StandardBarPainter barpaint = new StandardBarPainter();
		// 设置为普通的柱形
		redBarRenderer.setBarPainter(barpaint);
		redBarRenderer.setSeriesPaint(0, new Color(51, 153, 255));
	}
}