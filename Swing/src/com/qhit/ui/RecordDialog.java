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
import com.qhit.model.StorageInfo;
import com.qhit.tools.Tools;

/**
 * 入库记录
 * 
 * @author Administrator
 * 
 */
@SuppressWarnings("deprecation")
public class RecordDialog extends JDialog {

	private static final long serialVersionUID = -8253248299245515585L;

	private IGoodTypeDao goodTypeDao;
	private IStorageDao storageDao;

	private JLabel toLabel;

	private JComboBox monthComboBox;
	private JComboBox categoryBox;
	private JComboBox yearComboBox;
	private JComboBox dateComboBox;
	private JComboBox yearBox;
	private JComboBox monthBox;
	private JComboBox dateBox;

	private JButton btnFind;

	// 表格面板表格头部
	String[] title = { "产品ID", "产品编号", "产品名称", "产品价格", "产品积分", "产品种类" };
	// 表格面板表格模型
	private DefaultTableModel tableModel;
	// 表格面板滚动组件
	private JScrollPane scrollPane;
	// 表格面板表格组件
	private JTable table;

	private List<Storage> reList;

	public RecordDialog() {
		goodTypeDao = new GoodTypeDaoImpl();
		storageDao = new StorageDaoImpl();
	}

	/**
	 * 初始化
	 */
	public void init() {
		setSize(980, 470);
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
		categoryBox.setBounds(94, 39, 120, 40);
		getContentPane().add(categoryBox);

		List<StorageInfo> infoList = storageDao.searchByInfo();
		Vector<Object> objects = new Vector<Object>();
		if (infoList.size() > 0) {
			for (StorageInfo storageInfo : infoList) {
				objects.add(storageInfo.getYear());
			}
		}

		yearComboBox = new JComboBox(objects);
		yearComboBox.setBounds(244, 39, 100, 40);
		getContentPane().add(yearComboBox);

		infoList = storageDao.searchByInfo();
		objects = new Vector<Object>();
		if (infoList.size() > 0) {
			for (StorageInfo storageInfo : infoList) {
				objects.add(storageInfo.getMonth());
			}
		}

		monthComboBox = new JComboBox(objects);
		monthComboBox.setBounds(344, 39, 100, 40);
		getContentPane().add(monthComboBox);

		infoList = storageDao.searchByInfo();
		objects = new Vector<Object>();
		if (infoList.size() > 0) {
			for (StorageInfo storageInfo : infoList) {
				objects.add(storageInfo.getDate());
			}
		}

		dateComboBox = new JComboBox(objects);
		dateComboBox.setBounds(440, 39, 100, 40);
		getContentPane().add(dateComboBox);

		toLabel = new JLabel("--至--");
		toLabel.setBounds(550, 39, 70, 40);
		getContentPane().add(toLabel);

		infoList = storageDao.searchByInfo();
		objects = new Vector<Object>();
		if (infoList.size() > 0) {
			for (StorageInfo storageInfo : infoList) {
				objects.add(storageInfo.getYear());
			}
		}

		yearBox = new JComboBox(objects);
		yearBox.setBounds(600, 39, 100, 40);
		getContentPane().add(yearBox);

		infoList = storageDao.searchByInfo();
		objects = new Vector<Object>();
		if (infoList.size() > 0) {
			for (StorageInfo storageInfo : infoList) {
				objects.add(storageInfo.getMonth());
			}
		}

		monthBox = new JComboBox(objects);
		monthBox.setBounds(700, 39, 70, 40);
		getContentPane().add(monthBox);

		infoList = storageDao.searchByInfo();
		objects = new Vector<Object>();
		if (infoList.size() > 0) {
			for (StorageInfo storageInfo : infoList) {
				objects.add(storageInfo.getDate());
			}
		}

		dateBox = new JComboBox(objects);
		dateBox.setBounds(770, 39, 70, 40);
		getContentPane().add(dateBox);

		btnFind = new JButton("查询");
		btnFind.setOpaque(false);
		btnFind.setBorder(null);
		btnFind.setBounds(850, 39, 57, 40);
		getContentPane().add(btnFind);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 85, 910, 300);

		table = new JTable();
		table.setBounds(10, 85, 910, 300);
		// 调用查询数据表更新表格的方法
		fullEmp("", "", "");
		// 调用工具Tools类中的设置表格样式方法
		Tools.setTableStyle(table);
		scrollPane.setViewportView(table);
		getContentPane().add(scrollPane);

		// 查询事件
		btnFind.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String option = categoryBox.getSelectedItem().toString();
				String beginTime = yearComboBox.getSelectedItem().toString() + '-' + monthComboBox.getSelectedItem().toString() + '-' + dateComboBox.getSelectedItem().toString();
				String endTime = yearBox.getSelectedItem().toString() + "-" + monthBox.getSelectedItem().toString() + "-" + dateBox.getSelectedItem().toString();

				GoodType goodType = null;
				if (!"--所有商品--".equals(option)) {
					goodType = goodTypeDao.searchByName(option);
				}

				fullEmp(beginTime, endTime, goodType == null ? "" : goodType.getTypeId() + "");
			}
		});

		show();
	}

	/**
	 * 填充数据
	 * 
	 * @param option
	 *            商品类型名称
	 * @param beginTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param typeId
	 *            类型编号
	 */
	private void fullEmp(String beginTime, String endTime, String typeId) {
		tableModel = new DefaultTableModel(null, title);// 设置表格头部以及显示信息
		reList = storageDao.searchAll(typeId, beginTime, endTime);
		for (int i = 0; i < reList.size(); i++) {
			Storage r = (Storage) reList.get(i);
			Object[] rowData = { r.getId(), r.getGoodNo(), r.getGoodName(), r.getGoodPrice(), r.getGoodIntegral(), r.getTypeName() };
			tableModel.addRow(rowData);
		}
		// 设置表格
		table.setModel(tableModel);
	}
}