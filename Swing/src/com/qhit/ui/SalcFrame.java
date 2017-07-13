package com.qhit.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;

import com.qhit.dao.IGoodTypeDao;
import com.qhit.dao.ISellInfoDao;
import com.qhit.dao.IStorageDao;
import com.qhit.dao.impl.GoodTypeDaoImpl;
import com.qhit.dao.impl.SellInfoDaoImpl;
import com.qhit.dao.impl.StorageDaoImpl;
import com.qhit.model.GoodType;
import com.qhit.model.SellInfo;
import com.qhit.model.StorageInfo;
import com.qhit.tools.Tools;

public class SalcFrame extends JFrame {

	private static final long serialVersionUID = 4518472095854280750L;

	private JPanel contentPane;

	private JTextField txtNo;

	private IGoodTypeDao goodClassDao;
	private ISellInfoDao sellInfoDao;
	private IStorageDao storageDao;

	private List<SellInfo> list;

	private JButton saleBtn;
	private JButton productBtn;
	private JButton memberBtn;
	private JButton employeeBtn;
	private JButton shopBtn;
	private JButton findBtn;

	private JLabel lblCase;
	private JLabel lblName;
	private JLabel lblLeiBie;
	private JLabel lblYear;
	private JLabel lblMonth;
	private JLabel lbl_total;
	private JLabel lblPrice;
	private JLabel lblMinMonth;
	private JLabel lblMouth;
	private JLabel lblMaxMouth;
	private JLabel lblMouthAnsw;
	private JLabel lblMouthSale;
	private JLabel lblMouthSaleAnsw;
	private JLabel lblUnit;
	private JLabel lblMoney;
	private JLabel lblBottom;
	private JLabel lblYear_mouth;
	private JLabel lblTotal;
	private JLabel lbl_mouthYear;

	private JComboBox leiBieCom;
	private JComboBox yearCom;
	private JComboBox monthCom;
	private JComboBox yearTotalCom;

	// 表格面板表格头部
	String[] title = { "A", "B", "C", "D", "E", "F", "G", "H" };
	// 表格面板表格模型
	private DefaultTableModel tableModel;
	// 表格面板滚动组件
	private JScrollPane scrollPane;
	// 表格面板表格组件
	private JTable table;
	private CategoryDataset dateset;
	private JFreeChart chart;
	private ChartPanel chartpanel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalcFrame frame = new SalcFrame();
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
		setBounds(100, 100, 1035, 747);
		getContentPane().setLayout(null);

		// 设置面板的样式为当前系统的样式
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
		productBtn.setForeground(Color.WHITE);
		productBtn.setBounds(335, 10, 99, 40);
		productBtn.setOpaque(false);
		productBtn.setContentAreaFilled(false);
		productBtn.setBorder(null);
		getContentPane().add(productBtn);

		saleBtn = new JButton("\u9500\u552E\u7EDF\u8BA1");
		saleBtn.setForeground(Color.YELLOW);
		saleBtn.setBounds(444, 10, 99, 40);
		saleBtn.setOpaque(false);
		saleBtn.setContentAreaFilled(false);
		saleBtn.setBorder(null);
		getContentPane().add(saleBtn);

		contentPane = new JPanel();
		contentPane.setBackground(Color.BLUE);
		contentPane.setBounds(0, 0, 980, 50);
		getContentPane().add(contentPane);

		contentPane = new JPanel();
		contentPane.setBackground(Color.BLUE);
		contentPane.setBounds(0, 0, 980, 50);
		getContentPane().add(contentPane);

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

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.CYAN);
		tabbedPane.setBounds(0, 49, 980, 620);
		getContentPane().add(tabbedPane);

		JPanel saleRrcord = new JPanel();
		saleRrcord.setBackground(Color.CYAN);
		saleRrcord.setLayout(null);
		tabbedPane.addTab("\u4EA7\u54C1\u9500\u552E\u7EAA\u5F55", null, saleRrcord, "\u4EA7\u54C1\u9500\u552E\u7EAA\u5F55");

		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 60, 980, 430);

		table = new JTable();
		table.setBounds(0, 60, 980, 430);
		// 调用查询数据表更新表格的方法
		fullEmp("", "");
		// 调用工具Tools类中的设置表格样式方法
		Tools.setTableStyle(table);
		scrollPane.setViewportView(table);
		saleRrcord.add(scrollPane);

		lblName = new JLabel("\u4EA7\u54C1\u7F16\u53F7\u6216\u4EA7\u54C1\u540D\u79F0");
		lblName.setBounds(10, 10, 140, 25);
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("宋体", Font.PLAIN, 14));
		saleRrcord.add(lblName);

		lblCase = new JLabel("\uFF08\u4E0D\u533A\u5206\u5927\u5C0F\u5199\uFF09");
		lblCase.setBounds(20, 30, 113, 29);
		lblCase.setForeground(Color.WHITE);
		lblCase.setFont(new Font("宋体", Font.PLAIN, 13));
		saleRrcord.add(lblCase);

		txtNo = new JTextField();
		txtNo.setBounds(150, 20, 126, 22);
		txtNo.setOpaque(false);
		txtNo.setBorder(null);
		saleRrcord.add(txtNo);

		findBtn = new JButton("");
		findBtn.setBounds(350, 10, 90, 40);
		findBtn.setIcon(new ImageIcon("D:\\repository\\items\\image\\find.png"));
		findBtn.setOpaque(false);
		findBtn.setContentAreaFilled(false);
		findBtn.setBorder(null);
		saleRrcord.add(findBtn);

		lblLeiBie = new JLabel("\u4EA7\u54C1\u79CD\u7C7B");
		lblLeiBie.setBounds(600, 10, 70, 40);
		lblLeiBie.setForeground(Color.WHITE);
		lblLeiBie.setFont(new Font("宋体", Font.PLAIN, 16));
		saleRrcord.add(lblLeiBie);

		final List<GoodType> goodsList = goodClassDao.searchAll();

		Vector<Object> vector = new Vector<Object>();
		vector.add("--所有商品--");
		for (int i = 0; i < goodsList.size(); i++) {
			GoodType goodType = (GoodType) goodsList.get(i);
			vector.add(goodType.getTypeName());
		}

		leiBieCom = new JComboBox(vector);
		leiBieCom.setBounds(740, 10, 125, 40);
		saleRrcord.add(leiBieCom);

		List<StorageInfo> infoList = storageDao.searchByInfo();
		Vector<Object> objects = new Vector<Object>();
		if (infoList.size() > 0) {
			for (StorageInfo storageInfo : infoList) {
				objects.add(storageInfo.getYear());
			}
		}

		yearCom = new JComboBox(objects);
		yearCom.setBounds(33, 500, 77, 27);
		saleRrcord.add(yearCom);

		infoList = storageDao.searchByInfo();
		objects = new Vector<Object>();
		if (infoList.size() > 0) {
			for (StorageInfo storageInfo : infoList) {
				objects.add(storageInfo.getMonth());
			}
		}

		monthCom = new JComboBox(objects);
		monthCom.setBounds(33, 540, 77, 27);
		saleRrcord.add(monthCom);

		lblYear = new JLabel("\u5E74\u9500\u552E\u603B\u989D：");
		lblYear.setBounds(128, 500, 87, 21);
		lblYear.setFont(new Font("宋体", Font.PLAIN, 14));
		saleRrcord.add(lblYear);

		lblMonth = new JLabel("\u6708\u9500\u552E\u603B\u989D：");
		lblMonth.setBounds(128, 540, 87, 21);
		lblMonth.setFont(new Font("宋体", Font.PLAIN, 14));
		saleRrcord.add(lblMonth);

		lbl_total = new JLabel(this.sellInfoDao.getYearMoney().intValue() + "");
		lbl_total.setBounds(216, 545, 54, 15);
		saleRrcord.add(lbl_total);

		lblPrice = new JLabel(this.sellInfoDao.getYearMoney().intValue() + "");
		lblPrice.setBounds(216, 503, 54, 15);
		saleRrcord.add(lblPrice);

		lblMinMonth = new JLabel("\u6700\u5C0F\u9500\u989D\u6708\u4EFD：");
		lblMinMonth.setBounds(275, 500, 100, 21);
		lblMinMonth.setFont(new Font("宋体", Font.PLAIN, 14));
		saleRrcord.add(lblMinMonth);

		lblMouth = new JLabel(sellInfoDao.find().intValue() + "月");
		lblMouth.setBounds(375, 500, 32, 21);
		lblMouth.setFont(new Font("宋体", Font.PLAIN, 14));
		saleRrcord.add(lblMouth);

		lblMaxMouth = new JLabel("\u6700\u5927\u9500\u989D\u6708\u4EFD：");
		lblMaxMouth.setBounds(420, 500, 100, 21);
		lblMaxMouth.setFont(new Font("宋体", Font.PLAIN, 14));
		saleRrcord.add(lblMaxMouth);

		lblMouthAnsw = new JLabel(sellInfoDao.find().intValue() + "月");
		lblMouthAnsw.setBounds(520, 500, 32, 21);
		lblMouthAnsw.setFont(new Font("宋体", Font.PLAIN, 14));
		saleRrcord.add(lblMouthAnsw);

		lblMouthSale = new JLabel("月平均销售额：");
		lblMouthSale.setBounds(560, 500, 120, 21);
		lblMouthSale.setFont(new Font("宋体", Font.PLAIN, 14));
		saleRrcord.add(lblMouthSale);

		lblMouthSaleAnsw = new JLabel(this.sellInfoDao.getAvgMonthMoney().intValue() + "");
		lblMouthSaleAnsw.setBounds(670, 500, 54, 21);
		lblMouthSaleAnsw.setFont(new Font("宋体", Font.PLAIN, 14));
		saleRrcord.add(lblMouthSaleAnsw);

		lblUnit = new JLabel("\u5355\u4F4D:");
		lblUnit.setBounds(850, 540, 44, 21);
		lblUnit.setFont(new Font("宋体", Font.PLAIN, 14));
		saleRrcord.add(lblUnit);

		lblMoney = new JLabel("\u5143");
		lblMoney.setBounds(900, 540, 32, 21);
		lblMoney.setFont(new Font("宋体", Font.PLAIN, 14));
		saleRrcord.add(lblMoney);

		// 历年销售统计
		JPanel countRrcord = new JPanel();
		countRrcord.setBackground(Color.CYAN);
		countRrcord.setLayout(null);
		tabbedPane.addTab("\u4EA7\u54C1\u9500\u552E\u7EAA\u5F55", null, countRrcord, null);
		dateset = sellInfoDao.getYearsum();// 2.得到数据集，将表格标题传递给操作方法
		// 3.创建JfreeChart对象
		chart = ChartFactory.createBarChart(dateset.getColumnKeys().get(0) + "年—" + dateset.getColumnKeys().get(dateset.getRowCount() - 1) + "年" + "销售情况统计(单位:元)", // 图表标题
				"", // X轴标签
				"销售额", // Y轴标签
				dateset, // 数据集
				PlotOrientation.VERTICAL, // 图表方向
				false, // 是否显示图例(对于简单的柱状图必须是false)
				true, // 是否生成工具
				false // 是否生成URL链接
				);
		chartpanel = new ChartPanel(chart);// 1.用JfreeChart对象创建一个ChartPanel面板
		chartpanel.setOpaque(false);
		chartpanel.setBounds(0, 20, 980, 500);
		Tools.setReportStyle(chart);// 设置样式
		countRrcord.add(chartpanel);

		lblBottom = new JLabel("\u5E74\u4EFD\uFF08\u53EA\u5B58\u5728\u9500\u552E\u7EAA\u5F55\u7684\u5E74\u4EFD\uFF09");
		lblBottom.setBounds(351, 530, 185, 35);
		countRrcord.add(lblBottom);

		// 历年销售统计
		final JPanel saleCount = new JPanel();
		saleCount.setBackground(Color.CYAN);
		saleCount.setLayout(null);
		tabbedPane.addTab("\u4EA7\u54C1\u9500\u552E\u7EAA\u5F55", null, saleCount, null);
		dateset = sellInfoDao.getMonthsum("");// 2.得到数据集，将表格标题传递给操作方法
		// 3.创建JfreeChart对象
		chart = ChartFactory.createBarChart("", // 图表标题
				"", // X轴标签
				"销售额", // Y轴标签
				dateset, // 数据集
				PlotOrientation.VERTICAL, // 图表方向
				false, // 是否显示图例(对于简单的柱状图必须是false)
				true, // 是否生成工具
				false // 是否生成URL链接
				);
		chartpanel = new ChartPanel(chart);// 1.用JfreeChart对象创建一个ChartPanel面板
		chartpanel.setOpaque(false);
		chartpanel.setBounds(0, 50, 980, 470);
		Tools.setReportStyle(chart);// 设置样式
		saleCount.add(chartpanel);

		List<StorageInfo> storeList = this.storageDao.searchByInfo();
		Vector<Object> vectors = new Vector<Object>();
		for (StorageInfo info : storeList) {
			vectors.add(info.getYear());
		}

		yearTotalCom = new JComboBox(vectors);
		yearTotalCom.setBounds(214, 10, 87, 31);
		saleCount.add(yearTotalCom);

		lblYear_mouth = new JLabel("\u5E74");
		lblYear_mouth.setFont(new Font("宋体", Font.PLAIN, 16));
		lblYear_mouth.setBounds(311, 13, 29, 24);
		saleCount.add(lblYear_mouth);

		lblTotal = new JLabel("\u6BCF\u5E74\u9500\u552E\u60C5\u51B5\u7EDF\u8BA1");
		lblTotal.setFont(new Font("宋体", Font.PLAIN, 16));
		lblTotal.setBounds(364, 14, 143, 23);
		saleCount.add(lblTotal);

		lbl_mouthYear = new JLabel("\u5E74\u4EFD\uFF08\u53EA\u5B58\u5728\u9500\u552E\u7EAA\u5F55\u7684\u5E74\u4EFD\uFF09");
		lbl_mouthYear.setBounds(350, 530, 185, 35);
		saleCount.add(lbl_mouthYear);

		// 查询事件
		findBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String name = txtNo.getText();
				String typeName = (String) leiBieCom.getSelectedItem();

				if (!"".equals(typeName)) {
					if ("--所有商品--".equals(typeName)) {
						typeName = "";
					}
				}
				fullEmp(name, typeName);
			}
		});

		// 选择事件
		yearTotalCom.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				String year = yearTotalCom.getSelectedItem().toString();
				CategoryDataset dateset = sellInfoDao.getMonthsum(year);
				JFreeChart chart = ChartFactory.createBarChart("", // 图表标题
						"", // X轴标签
						"销售额", // Y轴标签
						dateset, // 数据集
						PlotOrientation.VERTICAL, // 图表方向
						false, // 是否显示图例(对于简单的柱状图必须是false)
						true, // 是否生成工具
						false // 是否生成URL链接
						);
				ChartPanel chartpanel = new ChartPanel(chart);// 1.用JfreeChart对象创建一个ChartPanel面板
				chartpanel.setOpaque(false);
				chartpanel.setBounds(0, 50, 920, 470);
				Tools.setReportStyle(chart);// 设置样式
				saleCount.add(chartpanel);
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SalcFrame() {
		goodClassDao = new GoodTypeDaoImpl();
		sellInfoDao = new SellInfoDaoImpl();
		storageDao = new StorageDaoImpl();
		init();
	}

	/**
	 * 填充数据
	 */
	private void fullEmp(String name, String typeName) {
		list = sellInfoDao.searchAll(name, typeName);
		// 设置表格头部以及显示信息
		tableModel = new DefaultTableModel(null, title);
		for (int i = 0; i < list.size(); i++) {
			SellInfo sellInfo = (SellInfo) list.get(i);
			Object[] rowData = { sellInfo.getSIid(), sellInfo.getGoodNo(), sellInfo.getGoodName(), sellInfo.getGoodPrice(), sellInfo.getNum(), sellInfo.getGoodIntegral(), sellInfo.getOutDate(),
					sellInfo.getTypeName() };
			tableModel.addRow(rowData);
		}
		table.setModel(tableModel);// 设置表格
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