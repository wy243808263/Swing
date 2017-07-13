package com.qhit.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.util.List;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import com.qhit.dao.IGoodTypeDao;
import com.qhit.dao.IGoodsDao;
import com.qhit.dao.impl.GoodTypeDaoImpl;
import com.qhit.dao.impl.GoodsDaoImpl;
import com.qhit.model.GoodType;
import com.qhit.model.Goods;
import com.qhit.tools.Tools;

@SuppressWarnings({ "deprecation" })
public class ProductFrame extends JFrame {
	private static final long serialVersionUID = 2954186645850594831L;

	private JPanel panel;
	private JTextField txtName;
	private JTextField txtScore;
	private JTextField txtNo;
	private JTextField txtPrice;

	private IGoodsDao goodsDao;
	private IGoodTypeDao goodClassDao;
	private IGoodTypeDao goodTypeDao;

	private JLabel lblName;
	private JLabel lblCase;
	private JLabel lblType;
	private JLabel lblScore;
	private JLabel lblClose;
	private JLabel lblNo;
	private JLabel lblPrice;

	private JComboBox comboType;
	private JComboBox leibieComboBox;

	private JButton saleBtn;
	private JButton productBtn;
	private JButton memberBtn;
	private JButton employeeBtn;
	private JButton shopBtn;
	private JButton findBtn;
	private JButton subButton;
	private JButton cacelBtn;

	private Integer id;

	// 表格面板表格头部
	String[] title = { "产品ID", "产品编号", "产品名称", "产品价格", "产品积分", "产品种类" };
	// 表格面板表格模型
	private DefaultTableModel tableModel;
	// 表格面板滚动组件
	private JScrollPane scrollPane;
	// 表格面板表格组件
	private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductFrame frame = new ProductFrame();
					Tools.setBackImg(frame, "bk.png");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 初始化
	 */
	public void init() {
		setBounds(100, 100, 1030, 730);
		getContentPane().setLayout(null);

		try {
			Img();
			// 设置面板的样式为当前系统的样式
			BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;
			BeautyEyeLNFHelper.launchBeautyEyeLNF();
			UIManager.put("RootPane.setupButtonVisible", false);// 隐藏设置按钮
			UIManager.setLookAndFeel(BeautyEyeLNFHelper.getBeautyEyeLNFCrossPlatform());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Font f = new Font("隶书", Font.PLAIN, 15);

		shopBtn = new JButton("\u5E97\u94FA\u6536\u85CF");
		shopBtn.setForeground(Color.WHITE);
		shopBtn.setBounds(10, 10, 99, 40);
		shopBtn.setOpaque(false);
		shopBtn.setContentAreaFilled(false);
		shopBtn.setBorder(null);
		getContentPane().add(shopBtn);
		UIManager.put("Button.font", f);

		employeeBtn = new JButton("\u96C7\u5458\u7BA1\u7406");
		employeeBtn.setForeground(Color.WHITE);
		employeeBtn.setBounds(117, 10, 99, 40);
		employeeBtn.setOpaque(false);
		employeeBtn.setContentAreaFilled(false);
		employeeBtn.setBorder(null);
		getContentPane().add(employeeBtn);

		memberBtn = new JButton("\u4F1A\u5458\u7BA1\u7406");
		memberBtn.setForeground(Color.WHITE);
		memberBtn.setBounds(226, 10, 99, 40);
		memberBtn.setOpaque(false);
		memberBtn.setContentAreaFilled(false);
		memberBtn.setBorder(null);
		getContentPane().add(memberBtn);

		productBtn = new JButton("\u4EA7\u54C1\u7BA1\u7406");
		productBtn.setForeground(Color.YELLOW);
		productBtn.setBounds(335, 10, 99, 40);
		productBtn.setOpaque(false);
		productBtn.setContentAreaFilled(false);
		productBtn.setBorder(null);
		getContentPane().add(productBtn);

		saleBtn = new JButton("\u9500\u552E\u7EDF\u8BA1");
		saleBtn.setForeground(Color.WHITE);
		saleBtn.setBounds(444, 10, 99, 40);
		saleBtn.setOpaque(false);
		saleBtn.setContentAreaFilled(false);
		saleBtn.setBorder(null);
		getContentPane().add(saleBtn);

		panel = new JPanel();
		panel.setBackground(Color.BLUE);
		panel.setBounds(0, 0, 980, 50);
		getContentPane().add(panel);

		// shopBtn事件
		shopBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				shopBtn.setForeground(Color.YELLOW);
				saleBtn.setForeground(Color.WHITE);
				employeeBtn.setForeground(Color.WHITE);
				productBtn.setForeground(Color.WHITE);
				memberBtn.setForeground(Color.WHITE);

				MainFrame frame = new MainFrame();
				frame.setVisible(true);

				setVisible(false);
			}
		});

		// saleBtn事件
		saleBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				shopBtn.setForeground(Color.WHITE);
				saleBtn.setForeground(Color.YELLOW);
				employeeBtn.setForeground(Color.WHITE);
				productBtn.setForeground(Color.WHITE);
				memberBtn.setForeground(Color.WHITE);

				SalcFrame frame = new SalcFrame();
				frame.setVisible(true);

				setVisible(false);
			}
		});

		// employeeBtn事件
		employeeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				shopBtn.setForeground(Color.WHITE);
				saleBtn.setForeground(Color.WHITE);
				employeeBtn.setForeground(Color.YELLOW);
				productBtn.setForeground(Color.WHITE);
				memberBtn.setForeground(Color.WHITE);

				EmployeeFrame frame = new EmployeeFrame();
				frame.setVisible(true);

				setVisible(false);
			}
		});

		// productBtn事件
		productBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				shopBtn.setForeground(Color.WHITE);
				saleBtn.setForeground(Color.WHITE);
				employeeBtn.setForeground(Color.WHITE);
				productBtn.setForeground(Color.YELLOW);
				memberBtn.setForeground(Color.WHITE);

				ProductFrame frame = new ProductFrame();
				frame.setVisible(true);

				setVisible(false);
			}
		});

		// memberBtn事件
		memberBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				shopBtn.setForeground(Color.WHITE);
				saleBtn.setForeground(Color.WHITE);
				employeeBtn.setForeground(Color.WHITE);
				productBtn.setForeground(Color.WHITE);
				memberBtn.setForeground(Color.YELLOW);

				MemberFrame frame = new MemberFrame();
				frame.setVisible(true);

				setVisible(false);
			}
		});

		lblName = new JLabel("\u4EA7\u54C1\u7F16\u53F7\u6216\u4EA7\u54C1\u540D\u79F0");
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("宋体", Font.PLAIN, 14));
		lblName.setBounds(20, 75, 140, 25);
		getContentPane().add(lblName);

		lblCase = new JLabel("\uFF08\u4E0D\u533A\u5206\u5927\u5C0F\u5199\uFF09");
		lblCase.setForeground(Color.WHITE);
		lblCase.setFont(new Font("宋体", Font.PLAIN, 13));
		lblCase.setBounds(30, 99, 113, 29);
		getContentPane().add(lblCase);

		txtName = new JTextField();
		txtName.setBounds(185, 85, 125, 23);
		txtName.setOpaque(false);
		txtName.setBorder(null);
		getContentPane().add(txtName);

		findBtn = new JButton("");
		findBtn.setIcon(new ImageIcon("D:\\repository\\items\\image\\find.png"));
		findBtn.setBounds(356, 78, 90, 40);
		findBtn.setOpaque(false);
		findBtn.setContentAreaFilled(false);
		findBtn.setBorder(null);
		getContentPane().add(findBtn);

		lblType = new JLabel("\u4EA7\u54C1\u79CD\u7C7B");
		lblType.setForeground(Color.WHITE);
		lblType.setFont(new Font("宋体", Font.PLAIN, 16));
		lblType.setBounds(471, 75, 73, 40);
		getContentPane().add(lblType);

		List<GoodType> goodsList = goodClassDao.searchAll();

		Vector<Object> vector = new Vector<Object>();
		vector.add("--所有商品--");
		for (int i = 0; i < goodsList.size(); i++) {
			GoodType goodType = (GoodType) goodsList.get(i);
			vector.add(goodType.getTypeName());
		}

		comboType = new JComboBox(vector);
		comboType.setBounds(567, 75, 105, 40);
		getContentPane().add(comboType);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 130, 729, 450);

		table = new JTable();
		table.setBounds(0, 130, 729, 450);
		// 调用查询数据表更新表格的方法
		fullEmp("", "");
		// 调用工具Tools类中的设置表格样式方法
		Tools.setTableStyle(table);
		scrollPane.setViewportView(table);
		getContentPane().add(scrollPane);

		JButton in_storeBtn = new JButton("");
		in_storeBtn.setIcon(new ImageIcon("D:\\repository\\items\\image\\put_in_storage.png"));
		in_storeBtn.setBounds(745, 150, 210, 81);
		getContentPane().add(in_storeBtn);

		JButton in_recordBtn = new JButton("");
		in_recordBtn.setIcon(new ImageIcon("D:\\repository\\items\\image\\record.png"));
		in_recordBtn.setBounds(745, 275, 210, 81);
		getContentPane().add(in_recordBtn);

		JButton in_quesBtn = new JButton("");
		in_quesBtn.setIcon(new ImageIcon("D:\\repository\\items\\image\\lookstcok.png"));
		in_quesBtn.setBounds(745, 400, 210, 81);
		getContentPane().add(in_quesBtn);

		// 查询事件
		findBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = txtName.getText();
				String typeId = comboType.getSelectedItem().toString();

				fullEmp(name, typeId);
			}
		});

		// 入库登记事件
		in_storeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int rows = table.getSelectedRow();
				if (rows > 0) {
					StorageDialog dialog = new StorageDialog();
					dialog.init(table);
				}
			}
		});

		// 入库记录事件
		in_recordBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				RecordDialog dialog = new RecordDialog();
				dialog.init();
			}
		});

		// 库存情况事件
		in_quesBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				QuesDialog dialog = new QuesDialog();
				dialog.init();
			}
		});

		JButton addBtn = new JButton("");
		addBtn.setIcon(new ImageIcon("D:\\repository\\items\\image\\add.png"));
		addBtn.setBounds(20, 590, 125, 56);
		addBtn.setOpaque(false);
		addBtn.setContentAreaFilled(false);
		addBtn.setBorder(null);
		getContentPane().add(addBtn);

		JButton updateBtn = new JButton("");
		updateBtn.setIcon(new ImageIcon("D:\\repository\\items\\image\\modify.png"));
		updateBtn.setBounds(261, 590, 125, 56);
		updateBtn.setOpaque(false);
		updateBtn.setContentAreaFilled(false);
		updateBtn.setBorder(null);
		getContentPane().add(updateBtn);

		final JButton delBtn = new JButton("");
		delBtn.setIcon(new ImageIcon("D:\\repository\\items\\image\\del.png"));
		delBtn.setBounds(533, 590, 125, 56);
		delBtn.setOpaque(false);
		delBtn.setContentAreaFilled(false);
		delBtn.setBorder(null);
		getContentPane().add(delBtn);

		// 添加事件
		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				initDialog(null, tableModel);
			}
		});

		// 修改事件
		updateBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initDialog(table, tableModel);
			}
		});

		// 删除事件
		delBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				if (row > 0) {
					String idNo = table.getValueAt(row, 0).toString();
					boolean bool = goodsDao.del(idNo);
					if (bool) {
						JOptionPane.showMessageDialog(delBtn, "删除成功");
						tableModel.fireTableDataChanged();
						fullEmp("", "");
					} else {
						JOptionPane.showMessageDialog(delBtn, "删除失败", "温馨提示", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
	}

	public ProductFrame() {
		goodsDao = new GoodsDaoImpl();
		goodClassDao = new GoodTypeDaoImpl();
		goodTypeDao = new GoodTypeDaoImpl();
		init();
	}

	String leibie;

	/**
	 * 初始化添加弹出窗口
	 */
	public void initDialog(final JTable table, final DefaultTableModel tableModel) {
		final JDialog dialog = new JDialog();
		dialog.setTitle(table != null ? "修改商品" : "添加商品");
		dialog.setSize(340, 370);
		dialog.setLayout(null);

		String goodsNo = null;
		String goodsName = null;
		Double price = null;
		BigDecimal goodsPrice = null;
		Integer goodsScore = null;
		leibie = "";
		id = null;
		if (table != null) {
			id = Integer.valueOf(table.getValueAt(table.getSelectedRow(), 0).toString());
			goodsNo = (String) table.getValueAt(table.getSelectedRow(), 1);
			goodsName = (String) table.getValueAt(table.getSelectedRow(), 2);
			price = Double.valueOf(table.getValueAt(table.getSelectedRow(), 3).toString());
			goodsPrice = new BigDecimal(price.toString());
			goodsScore = Integer.valueOf(table.getValueAt(table.getSelectedRow(), 4).toString());
			leibie = (String) table.getValueAt(table.getSelectedRow(), 5);
		}

		Icon icon = new ImageIcon("D:\\repository\\items\\image\\JDialogClose.png");

		lblClose = new JLabel();
		lblClose.setIcon(icon);
		lblClose.setOpaque(false);
		lblClose.setBorder(null);
		lblClose.setBounds(277, 0, 22, 22);
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

		lblNo = new JLabel("产品编号");
		lblNo.setBounds(24, 39, 60, 20);

		txtNo = new JTextField(goodsNo);
		txtNo.setBounds(100, 37, 154, 30);

		lblName = new JLabel("产品名称");
		lblName.setBounds(24, 80, 60, 20);

		txtName = new JTextField(goodsName);
		txtName.setBounds(100, 75, 154, 30);

		lblPrice = new JLabel("产品价格");
		lblPrice.setBounds(24, 120, 100, 20);

		txtPrice = new JTextField(goodsPrice == null ? "" : goodsPrice.toString());
		txtPrice.setBounds(100, 115, 154, 30);

		lblScore = new JLabel("产品积分");
		lblScore.setBounds(24, 160, 100, 20);

		txtScore = new JTextField(goodsScore == null ? "" : goodsScore.toString());
		txtScore.setBounds(100, 155, 154, 30);

		lblType = new JLabel("产品类别");
		lblType.setBounds(24, 205, 100, 20);

		List<GoodType> goodsList = goodTypeDao.searchAll();

		Vector<Object> vector = new Vector<Object>();
		vector.add("--所有商品--");
		for (int i = 0; i < goodsList.size(); i++) {
			GoodType goodType = (GoodType) goodsList.get(i);
			vector.add(goodType.getTypeName());
		}

		leibieComboBox = new JComboBox(vector);
		leibieComboBox.setBounds(100, 200, 154, 30);

		subButton = new JButton("确    定");
		subButton.setBounds(60, 240, 90, 40);
		subButton.setBorderPainted(false);// 设置按钮边界不显示
		subButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String idNo = txtNo.getText();
				String name = txtName.getText();
				double price = Double.valueOf(txtPrice.getText());
				int score = Integer.valueOf(txtScore.getText());
				String type = leibieComboBox.getSelectedItem().toString();

				Goods goods = new Goods();
				goods.setGoodPrice(new BigDecimal(price));
				goods.setGoodIntegral(score);
				goods.setGoodNo(idNo);
				goods.setGoodName(name);
				GoodType goodType = null;
				if (table != null) {
					goods.setGid(id);
					goodType = goodTypeDao.searchByName("--所有商品--".equals(type) ? leibie : type);
				} else {
					goodType = goodTypeDao.searchByName(type);
				}

				goods.setTypeId(goodType.getTypeId());

				boolean bool = table == null ? goodsDao.save(goods) : goodsDao.update(goods);

				if (bool) {
					JOptionPane.showMessageDialog(subButton, table != null ? "修改成功" : "添加成功");
					dialog.hide();
					tableModel.fireTableDataChanged();
					fullEmp("", "");

					txtNo.setText("");
					txtName.setText("");
					txtPrice.setText("");
					txtScore.setText("");
				} else {
					JOptionPane.showMessageDialog(subButton, table != null ? "修改成功" : "添加成功", "温馨提示", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		cacelBtn = new JButton("取    消");
		cacelBtn.setBounds(170, 240, 90, 40);
		cacelBtn.setBorderPainted(false);// 设置按钮边界不显示
		cacelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.hide();
			}
		});

		dialog.getContentPane().setLayout(null);
		dialog.getContentPane().add(lblScore);
		dialog.getContentPane().add(lblNo);
		dialog.getContentPane().add(txtScore);
		dialog.getContentPane().add(lblName);
		dialog.getContentPane().add(txtPrice);
		dialog.getContentPane().add(txtNo);
		dialog.getContentPane().add(txtName);
		dialog.getContentPane().add(lblPrice);
		dialog.getContentPane().add(subButton);
		dialog.getContentPane().add(cacelBtn);
		dialog.getContentPane().add(lblType);
		dialog.getContentPane().add(leibieComboBox);
		dialog.getContentPane().add(lblClose);

		dialog.getRootPane().setOpaque(false);
		dialog.setLocationRelativeTo(null);
		dialog.show();
	}

	/**
	 * 填充数据
	 * 
	 * @param name
	 *            名字
	 * @param typeId
	 *            类型
	 */
	private void fullEmp(String name, String typeId) {
		List<Goods> goodsList = goodsDao.searchAll(name, typeId);
		tableModel = new DefaultTableModel(null, title);// 设置表格头部以及显示信息
		for (int i = 0; i < goodsList.size(); i++) {
			Goods goods = (Goods) goodsList.get(i);
			Object[] rowData = { goods.getGid(), goods.getGoodNo(), goods.getGoodName(), goods.getGoodPrice(), goods.getGoodIntegral(), goods.getTypeName() };
			tableModel.addRow(rowData);
		}
		table.setModel(tableModel);// 设置表格
		Tools.HiddenCell(table, 0); // 隐藏表格中的：身份证号、联系方式、联系地址、入职时间列
	}

	public void Img() {
		Icon icon = new ImageIcon("D:\\repository\\items\\image\\bk.png");
		// 要设置的背景图片
		JLabel imgLabel = new JLabel(icon);
		// 将背景图放在标签里。
		this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		// 将背景标签添加到jfram的LayeredPane面板里。
		imgLabel.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());

		// 设置窗体没有边框
		this.setUndecorated(true);
		// 设置窗口的关闭方式
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		// 设置窗体居中
		this.setLocationRelativeTo(null);

		Container cp = this.getContentPane();
		cp.setLayout(null); // 这里选择绝对布局管理器，对于边界布局管理器，放入控件后，无法显示背景图片；因为将整个面板都填充满了；
		((JPanel) cp).setOpaque(false); // 这样就能显示出背景图片出来了
	}
}