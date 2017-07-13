package com.qhit.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.qhit.dao.IGoodsDao;
import com.qhit.dao.impl.GoodsDaoImpl;
import com.qhit.model.Goods;
import com.qhit.tools.StringUtil;
import com.qhit.tools.Tools;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel panel;

	// 表格面板滚动组件
	private JScrollPane scrollPane;
	// 表格面板表格组件
	private JTable table;
	// 表格面板表格模型
	private DefaultTableModel tableModel;
	// 表格面板表格头部
	private String[] title = { "产品编号", "产品名称", "价       格", "购买数量", "合计金额" };

	private IGoodsDao goodsDao;

	private JLabel lblPrice;
	private JLabel lblTotal;
	private JLabel lblId;// 产品ID
	private JLabel lblNum;// 产品数量

	private JTextField txtNum;
	private JTextField txtId;

	private JButton saleBtn;
	private JButton productBtn;
	private JButton memberBtn;
	private JButton employeeBtn;
	private JButton shopBtn;
	private JButton okBtn;
	private JButton delBtn;
	private JButton clearBtn;
	private JButton totalBtn;

	/** The menu bar. */
//	private JMenuBar menuBar = null;
//
//	private JMenu fileMenu;
//
//	private JMenuItem aboutMenu;
//	private JMenuItem exitMenu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public void initGUI() {
		setBounds(100, 100, 980, 730);
		getContentPane().setLayout(null);

//		menuBar = new JMenuBar();
//		this.setJMenuBar(menuBar);
//
//		fileMenu = new JMenu("文件");
//		
//		aboutMenu = new JMenuItem("关于");
//		exitMenu = new JMenuItem("退出");
//		
//		aboutMenu.setMnemonic('A');
//		aboutMenu.setMnemonic('X');
//		
//		fileMenu.add(aboutMenu);
//		fileMenu.addSeparator();
//		fileMenu.add(exitMenu);
//
//		menuBar.add(fileMenu);

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

		Font f = new Font("隶书", Font.PLAIN, 15);
		try {
			// 设置面板的样式为当前系统的样式
			BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;
			BeautyEyeLNFHelper.launchBeautyEyeLNF();
			UIManager.put("RootPane.setupButtonVisible", false);// 隐藏设置按钮
			UIManager.setLookAndFeel(BeautyEyeLNFHelper.getBeautyEyeLNFCrossPlatform());
			UIManager.put("Button.font", f);

			shopBtn = new JButton("\u5E97\u94FA\u6536\u6B3E");
			shopBtn.setBounds(10, 10, 93, 39);
			shopBtn.setOpaque(false);
			shopBtn.setContentAreaFilled(false);
			shopBtn.setForeground(Color.YELLOW);
			shopBtn.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.normal));
			getContentPane().add(shopBtn);

			employeeBtn = new JButton("\u96C7\u5458\u7BA1\u7406");
			employeeBtn.setForeground(Color.WHITE);
			employeeBtn.setBounds(108, 10, 93, 39);
			employeeBtn.setOpaque(false);
			employeeBtn.setContentAreaFilled(false);
			employeeBtn.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.normal));
			getContentPane().add(employeeBtn);

			memberBtn = new JButton("\u4F1A\u5458\u7BA1\u7406");
			memberBtn.setForeground(Color.WHITE);
			memberBtn.setBounds(206, 10, 93, 39);
			memberBtn.setOpaque(false);
			memberBtn.setContentAreaFilled(false);
			memberBtn.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.normal));
			getContentPane().add(memberBtn);

			productBtn = new JButton("\u4EA7\u54C1\u7BA1\u7406");
			productBtn.setForeground(Color.WHITE);
			productBtn.setBounds(304, 10, 93, 39);
			productBtn.setOpaque(false);
			productBtn.setContentAreaFilled(false);
			productBtn.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.normal));
			getContentPane().add(productBtn);

			saleBtn = new JButton("\u9500\u552E\u7EDF\u8BA1");
			saleBtn.setForeground(Color.WHITE);
			saleBtn.setBounds(406, 10, 93, 39);
			saleBtn.setOpaque(false);
			saleBtn.setContentAreaFilled(false);
			saleBtn.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.normal));
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

			lblId = new JLabel("输入产品编号");
			lblId.setBounds(10, 55, 127, 50);
			lblId.setOpaque(false);
			lblId.setBorder(null);
			lblId.setForeground(Color.WHITE);
			lblId.setFont(new Font("新宋体", Font.PLAIN, 15));
			getContentPane().add(lblId);

			txtId = new JTextField();
			txtId.setBounds(120, 55, 160, 50);
			txtId.setOpaque(false);
			txtId.setForeground(Color.WHITE);
			getContentPane().add(txtId);

			lblNum = new JLabel("数量");
			lblNum.setForeground(Color.WHITE);
			lblNum.setBounds(290, 53, 127, 50);
			lblNum.setFont(new Font("新宋体", Font.PLAIN, 15));
			lblNum.setOpaque(false);
			lblNum.setBorder(null);
			getContentPane().add(lblNum);

			txtNum = new JTextField();
			txtNum.setOpaque(false);
			txtNum.setForeground(Color.WHITE);
			txtNum.setBounds(340, 53, 160, 50);
			getContentPane().add(txtNum);

			okBtn = new JButton("");
			okBtn.setIcon(new ImageIcon("D:\\repository\\items\\image\\confirm.png"));
			okBtn.setBounds(570, 57, 90, 40);
			okBtn.setOpaque(false);
			okBtn.setContentAreaFilled(false);
			okBtn.setBorder(null);
			getContentPane().add(okBtn);

			scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 105, 693, 450);

			table = new JTable();
			table.setBounds(0, 105, 694, 418);
			// 设置模型
			tableModel = new DefaultTableModel(null, title);
			// 设置表格头部
			table.setModel(tableModel);

			// 调用工具Tools类中的设置表格样式方法
			Tools.setTableStyle(table);
			scrollPane.setViewportView(table);

			getContentPane().add(scrollPane);

			delBtn = new JButton("");
			delBtn.setIcon(new ImageIcon("D:\\repository\\items\\image\\del.png"));
			delBtn.setBounds(42, 580, 125, 56);
			delBtn.setOpaque(false);
			delBtn.setContentAreaFilled(false);
			delBtn.setBorder(null);
			getContentPane().add(delBtn);

			clearBtn = new JButton("");
			clearBtn.setIcon(new ImageIcon("D:\\repository\\items\\image\\clear.png"));
			clearBtn.setBounds(502, 580, 125, 56);
			clearBtn.setOpaque(false);
			clearBtn.setContentAreaFilled(false);
			clearBtn.setBorder(null);
			getContentPane().add(clearBtn);

			lblTotal = new JLabel("\u5408\u8BA1\uFF1A");
			lblTotal.setForeground(Color.WHITE);
			lblTotal.setFont(new Font("宋体", Font.PLAIN, 16));
			lblTotal.setBounds(753, 194, 65, 39);
			getContentPane().add(lblTotal);

			lblPrice = new JLabel();
			lblPrice.setForeground(Color.WHITE);
			lblPrice.setFont(new Font("宋体", Font.PLAIN, 16));
			lblPrice.setBounds(837, 194, 65, 39);
			getContentPane().add(lblPrice);

			totalBtn = new JButton("");
			totalBtn.setIcon(new ImageIcon("D:\\repository\\items\\image\\jiezhang.png"));
			totalBtn.setBounds(756, 320, 125, 56);
			totalBtn.setOpaque(false);
			totalBtn.setContentAreaFilled(false);
			totalBtn.setBorder(null);
			getContentPane().add(totalBtn);

			// 查询事件
			okBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// 获取产品编号
					String id = txtId.getText();
					// 获取产品数量
					String num = txtNum.getText();

					if (id.isEmpty()) {
						JOptionPane.showMessageDialog(null, "请输入需要购买的产品编号！");
						return;
					}

					if (num.isEmpty()) {// 1.判断是否输入产品数量
						JOptionPane.showMessageDialog(null, "请输入需要购买的产品数量！");
						return;
					}

					if (!StringUtil.isNum(num) || Integer.valueOf(num) == 0) {// 2.判断输入的是否为合法的数字
						JOptionPane.showMessageDialog(null, "购买数量不合法，请输入正整数");
						// 提示的同时清空该文本框
						txtNum.setText("1");
						return;
					}

					while (!goodsDao.check(id)) {// 3.检查产品是否存在
						JOptionPane.showMessageDialog(null, "抱歉，没有该产品，请重新输入");
						txtId.setText("");// 提示的同时清空该文本框
						return;
					}

					if (!goodsDao.check(id)) {// 4.检查库存中是否有该产品
						JOptionPane.showMessageDialog(null, "该产品没有库存量，无法购买，请店主进货");
						txtId.setText("");// 提示的同时清空该文本框
						return;
					}

					Goods goods = goodsDao.searchAll(id, "").get(0);

					// 创建一个数组存放内容
					Object row[] = new Object[5];
					// 存放产品编号
					row[0] = goods.getGoodNo();
					// 存放产品名称
					row[1] = goods.getGoodName();
					// 存放产品价格
					row[2] = goods.getGoodPrice();
					// 存放产品销售数量
					row[3] = num;
					// 存放产品合计金额
					row[4] = goods.getGoodPrice().intValue() * Integer.valueOf(num);
					// 添加表格的行
					tableModel.addRow(row);
					// 调用统计总金额的方法
					showTitleMoney();
					// 清空文本框内的内容
					txtId.setText("");
					txtNum.setText("1");
				}
			});

			// 删除事件
			delBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int row = table.getSelectedRow();

					if (row == -1) {
						JOptionPane.showMessageDialog(table, "请选择行数据进行删除");
						return;
					} else {
						Object[] options = { "确定", "取消" };
						int bool = JOptionPane.showOptionDialog(table, "确认要删除选中的商品", "温馨提示", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
						if (bool == 0) {
							tableModel.removeRow(row);
							tableModel.fireTableDataChanged();
							// 删除后重新统计总金额
							showTitleMoney();
						}
					}
				}
			});

			// 清空事件
			clearBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Object[] options = { "确定", "取消" };
					int bool = JOptionPane.showOptionDialog(table, "是否要清空购买的商品", "温馨提示", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
					if (bool == 0) {
						while (tableModel.getRowCount() > 0) {
							tableModel.removeRow(tableModel.getRowCount() - 1);
						}
					}
				}
			});

			// 结算事件
			totalBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String option = JOptionPane.showInputDialog("输入实收金额");

					if (StringUtil.isBlank(option)) {
						JOptionPane.showMessageDialog(null, "你取消当前金额");
					} else {
						MainFrameDialog dialog = new MainFrameDialog();
						dialog.init(option, lblPrice.getText());
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		goodsDao = new GoodsDaoImpl();
		initGUI();
	}

	/**
	 * 计算总金额
	 */
	public void showTitleMoney() {
		int a = table.getRowCount();// 获得表格中某一行
		double allmaoney = 0; // 定义变量保存
		for (int i = 0; i < a; i++) {
			// 循环取得表格中合计金额列中的金额并相加保存至变量中
			allmaoney += Double.parseDouble(tableModel.getValueAt(i, 4).toString());
		}
		lblPrice.setText(" " + allmaoney);// 设置文本框的内容
	}
}