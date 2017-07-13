package com.qhit.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.qhit.dao.IGoodTypeDao;
import com.qhit.dao.IStorageDao;
import com.qhit.dao.impl.GoodTypeDaoImpl;
import com.qhit.dao.impl.StorageDaoImpl;
import com.qhit.model.GoodType;
import com.qhit.model.Storage;
import com.qhit.tools.Tools;

/**
 * 库存情况
 * 
 * @author Administrator
 * 
 */
@SuppressWarnings("deprecation")
public class QuesDialog extends JDialog {

	private static final long serialVersionUID = -6015290954495377316L;

	private IGoodTypeDao goodTypeDao;
	private IStorageDao storageDao;

	private JButton btnFind;

	private JComboBox beginBox;
	private JComboBox endBox;
	private JComboBox categoryBox;

	private JLabel fromLabel;
	private JLabel betLabel;

	// 表格面板表格头部
	String[] title = { "产品编号", "产品名称", "产品类别", "库存数量" };
	// 表格面板表格模型
	private DefaultTableModel tableModel;
	// 表格面板滚动组件
	private JScrollPane scrollPane;
	// 表格面板表格组件
	private JTable table;

	private List<Storage> reList;

	public QuesDialog() {
		goodTypeDao = new GoodTypeDaoImpl();
		storageDao = new StorageDaoImpl();
	}

	/**
	 * 初始化
	 */
	public void init() {
		setSize(800, 450);
		setLayout(null);
		// 设置窗体居中
		setLocationRelativeTo(null);

		Icon icon = new ImageIcon("D:\\repository\\items\\image\\JDialogClose.png");

		JLabel lblClose = new JLabel();
		lblClose.setIcon(icon);
		lblClose.setOpaque(false);
		lblClose.setBorder(null);
		lblClose.setBounds(777, 0, 22, 22);
		lblClose.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				hide();
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});

		List<GoodType> goodsList = goodTypeDao.searchAll();

		Vector<Object> vector = new Vector<Object>();
		vector.add("--所有商品--");
		for (int i = 0; i < goodsList.size(); i++) {
			GoodType goodType = (GoodType) goodsList.get(i);
			vector.add(goodType.getTypeName());
		}

		categoryBox = new JComboBox(vector);
		categoryBox.setBounds(94, 39, 100, 40);
		getContentPane().add(categoryBox);

		betLabel = new JLabel("产品库存区间:");
		betLabel.setBounds(224, 39, 130, 40);
		getContentPane().add(betLabel);

		String arry[] = new String[68];
		for (int i = 0; i < arry.length; i++) {
			arry[i - 0] = i + "";
		}

		beginBox = new JComboBox(arry);
		beginBox.setBounds(310, 39, 120, 40);
		getContentPane().add(beginBox);

		fromLabel = new JLabel("----");
		fromLabel.setBounds(450, 39, 50, 40);
		getContentPane().add(fromLabel);

		endBox = new JComboBox(arry);
		endBox.setBounds(490, 39, 120, 40);
		getContentPane().add(endBox);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 85, 735, 300);

		table = new JTable();
		table.setBounds(10, 85, 735, 300);
		// 调用查询数据表更新表格的方法
		fullEmp("", "", "");
		// 调用工具Tools类中的设置表格样式方法
		Tools.setTableStyle(table);
		scrollPane.setViewportView(table);
		getContentPane().add(scrollPane);

		btnFind = new JButton("查询");
		btnFind.setOpaque(false);
		btnFind.setBorder(null);
		btnFind.setBounds(630, 39, 57, 40);
		getContentPane().add(btnFind);

		// 查询事件
		btnFind.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String beginNum = beginBox.getSelectedItem().toString();
				String endNum = endBox.getSelectedItem().toString();
				String option = categoryBox.getSelectedItem().toString();

				GoodType goodType = null;
				if (!"--所有商品--".equals(option)) {
					goodType = goodTypeDao.searchByName(option);
				}

				if ("0".equals(endNum)) {
					beginNum = "";
				}
				if ("0".equals(endNum)) {
					endNum = "";
				}

				if (!"--所有商品--".equals(option)) {
					fullEmp(goodType.getTypeId() + "", beginNum, endNum);
				} else {
					fullEmp("", beginNum, endNum);
				}
			}
		});

		show();
	}

	/**
	 * 填充数据
	 * 
	 * @param startNum
	 *            开始数量
	 * @param endNum
	 *            结束数量
	 */
	private void fullEmp(String option, String startNum, String endNum) {
		tableModel = new DefaultTableModel(null, title);
		reList = storageDao.getByAll(option, startNum, endNum);
		for (int i = 0; i < reList.size(); i++) {
			Storage r = (Storage) reList.get(i);
			Object[] rowData = { r.getId(), r.getGoodName(), r.getTypeName(), r.getGoodNum(), r.getStorageDate(), r.getStorhead() };
			tableModel.addRow(rowData);
		}
		// 设置表格
		table.setModel(tableModel);
	}
}