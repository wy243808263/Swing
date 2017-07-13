package com.qhit.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import com.qhit.dao.IEmployeeDao;
import com.qhit.dao.impl.EmployeeDaoImpl;
import com.qhit.model.Employee;
import com.qhit.tools.Tools;

public class EmployeeFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel panel;

	private JTextField txtNo;
	private JTextField txtName;
	private JTextField txtPosition;
	private JTextField txtDate;
	private JTextField txtAddr;
	private JTextField txtPhone;
	private JTextField txtIdNo;
	private JTextField txtAge;

	private JRadioButton femaleRadioBtn;
	private JRadioButton maleRadioBtn;

	private JButton addBtn;
	private JButton updatBtn;
	private JButton delbtn;
	private JButton saleBtn;
	private JButton productBtn;
	private JButton memberBtn;
	private JButton employeeBtn;
	private JButton shopBtn;

	private JLabel lblNo;
	private JLabel lblName;
	private JLabel lblPhone;
	private JLabel lblIdNo;
	private JLabel lblAddr;
	private JLabel lblAge;
	private JLabel lblDate;
	private JLabel lblSex;
	private JLabel lblPosition;

	// 表格面板表格头部
	String[] title = { "员工编号", "姓     名", "性     别", "年     龄", "身份证号", "联系方式", "地     址", "时     间", "职     位" };
	// 表格面板表格模型
	private DefaultTableModel tableModel;
	// 表格面板滚动组件
	private JScrollPane scrollPane;
	// 表格面板表格组件
	private JTable table;

	private IEmployeeDao employeeDao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeFrame frame = new EmployeeFrame();
					Tools.setBackImg(frame, "bk.png");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public EmployeeFrame() {
		employeeDao = new EmployeeDaoImpl();
		init();
	}

	/**
	 * 初始化
	 */
	public void init() {
		setBounds(100, 100, 980, 730);
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
		employeeBtn.setForeground(Color.YELLOW);
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

		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 50, 729, 500);

		table = new JTable();
		table.setBounds(0, 50, 729, 500);
		// 调用查询数据表更新表格的方法
		fullEmp();
		// 调用工具Tools类中的设置表格样式方法
		Tools.setTableStyle(table);
		scrollPane.setViewportView(table);
		getContentPane().add(scrollPane);

		lblNo = new JLabel("\u5458\u5DE5\u7F16\u53F7");
		lblNo.setForeground(Color.WHITE);
		lblNo.setBounds(739, 75, 54, 15);
		getContentPane().add(lblNo);

		lblName = new JLabel("\u5458\u5DE5\u59D3\u540D");
		lblName.setForeground(Color.WHITE);
		lblName.setBounds(739, 121, 54, 15);
		getContentPane().add(lblName);

		lblSex = new JLabel("\u6027     \u522B");
		lblSex.setForeground(Color.WHITE);
		lblSex.setBounds(739, 168, 54, 15);
		getContentPane().add(lblSex);

		lblAge = new JLabel("\u5E74     \u9F84");
		lblAge.setForeground(Color.WHITE);
		lblAge.setBounds(739, 210, 54, 15);
		getContentPane().add(lblAge);

		lblIdNo = new JLabel("\u8EAB\u4EFD\u8BC1\u53F7");
		lblIdNo.setForeground(Color.WHITE);
		lblIdNo.setBounds(739, 261, 54, 15);
		getContentPane().add(lblIdNo);

		lblPhone = new JLabel("\u8054\u7CFB\u7535\u8BDD");
		lblPhone.setForeground(Color.WHITE);
		lblPhone.setBounds(739, 309, 54, 15);
		getContentPane().add(lblPhone);

		lblAddr = new JLabel("\u8054\u7CFB\u5730\u5740");
		lblAddr.setForeground(Color.WHITE);
		lblAddr.setBounds(739, 357, 54, 15);
		getContentPane().add(lblAddr);

		lblDate = new JLabel("\u5165\u804C\u65E5\u671F");
		lblDate.setForeground(Color.WHITE);
		lblDate.setBounds(739, 408, 54, 15);
		getContentPane().add(lblDate);

		lblPosition = new JLabel("\u804C    \u4F4D");
		lblPosition.setForeground(Color.WHITE);
		lblPosition.setBounds(739, 470, 54, 15);
		getContentPane().add(lblPosition);

		txtNo = new JTextField();
		txtNo.setForeground(Color.WHITE);
		txtNo.setBounds(800, 72, 120, 25);
		txtNo.setOpaque(false);
		getContentPane().add(txtNo);
		txtNo.setColumns(10);

		txtName = new JTextField();
		txtName.setForeground(Color.WHITE);
		txtName.setColumns(10);
		txtName.setOpaque(false);
		txtName.setBounds(800, 118, 120, 25);
		getContentPane().add(txtName);

		txtPosition = new JTextField();
		txtPosition.setForeground(Color.WHITE);
		txtPosition.setColumns(10);
		txtPosition.setBounds(800, 467, 120, 25);
		txtPosition.setOpaque(false);
		getContentPane().add(txtPosition);

		txtDate = new JTextField();
		txtDate.setForeground(Color.WHITE);
		txtDate.setColumns(10);
		txtDate.setOpaque(false);
		txtDate.setBounds(800, 405, 120, 25);
		getContentPane().add(txtDate);

		txtAddr = new JTextField();
		txtAddr.setForeground(Color.WHITE);
		txtAddr.setColumns(10);
		txtAddr.setOpaque(false);
		txtAddr.setBounds(800, 354, 120, 25);
		getContentPane().add(txtAddr);

		txtPhone = new JTextField();
		txtPhone.setForeground(Color.WHITE);
		txtPhone.setColumns(10);
		txtPhone.setOpaque(false);
		txtPhone.setBounds(800, 306, 120, 25);
		getContentPane().add(txtPhone);

		txtIdNo = new JTextField();
		txtIdNo.setForeground(Color.WHITE);
		txtIdNo.setColumns(10);
		txtIdNo.setOpaque(false);
		txtIdNo.setBounds(800, 258, 120, 25);
		getContentPane().add(txtIdNo);

		txtAge = new JTextField();
		txtAge.setForeground(Color.WHITE);
		txtAge.setColumns(10);
		txtAge.setOpaque(false);
		txtAge.setBounds(800, 207, 120, 25);
		getContentPane().add(txtAge);

		maleRadioBtn = new JRadioButton("\u7537");
		maleRadioBtn.setForeground(Color.BLACK);
		maleRadioBtn.setBounds(800, 165, 45, 23);
		maleRadioBtn.setOpaque(false);
		maleRadioBtn.setContentAreaFilled(false);
		maleRadioBtn.setBorder(null);
		getContentPane().add(maleRadioBtn);

		femaleRadioBtn = new JRadioButton("\u5973");
		femaleRadioBtn.setForeground(Color.BLACK);
		femaleRadioBtn.setBounds(860, 165, 46, 23);
		femaleRadioBtn.setOpaque(false);
		femaleRadioBtn.setContentAreaFilled(false);
		femaleRadioBtn.setBorder(null);
		getContentPane().add(femaleRadioBtn);

		ButtonGroup group = new ButtonGroup();
		group.add(maleRadioBtn);
		group.add(femaleRadioBtn);

		addBtn = new JButton("");
		addBtn.setIcon(new ImageIcon("D:\\repository\\items\\image\\add.png"));
		addBtn.setBounds(46, 571, 125, 56);
		addBtn.setOpaque(false);
		addBtn.setContentAreaFilled(false);
		addBtn.setBorder(null);
		getContentPane().add(addBtn);

		updatBtn = new JButton("");
		updatBtn.setIcon(new ImageIcon("D:\\repository\\items\\image\\modify.png"));
		updatBtn.setBounds(284, 571, 125, 56);
		updatBtn.setOpaque(false);
		updatBtn.setContentAreaFilled(false);
		updatBtn.setBorder(null);
		getContentPane().add(updatBtn);

		delbtn = new JButton("");
		delbtn.setIcon(new ImageIcon("D:\\repository\\items\\image\\del.png"));
		delbtn.setBounds(531, 571, 125, 56);
		delbtn.setOpaque(false);
		delbtn.setContentAreaFilled(false);
		delbtn.setBorder(null);
		getContentPane().add(delbtn);

		// 修改事件
		updatBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Employee employee = getAllTxt();
				boolean bool = employeeDao.update(employee);
				if (bool) {
					JOptionPane.showMessageDialog(addBtn, "修改成功");
					success();
					cleanTxt();// 清空文本框值
				} else {
					JOptionPane.showMessageDialog(addBtn, "修改失败", "温馨提示", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		// 添加事件
		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Employee employee = getAllTxt();
				boolean bool = employeeDao.save(employee);
				if (bool) {
					JOptionPane.showMessageDialog(null, "添加成功");
					success();
					cleanTxt();// 清空文本框值
				} else {
					JOptionPane.showMessageDialog(addBtn, "添加失败", "温馨提示", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		// 删除事件
		delbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if (row > 0) {
					int uid = Integer.valueOf(table.getValueAt(row, 0).toString());
					Object[] options = { "确定", "取消" };
					int bool = JOptionPane.showOptionDialog(table, "确认要删除选中的商品", "温馨提示", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
					if (bool == 0) {
						boolean flag = employeeDao.del(uid);
						if (flag) {
							JOptionPane.showMessageDialog(table, "删除成功");
							tableModel.removeRow(row);
							success();
						} else {
							JOptionPane.showMessageDialog(table, "删除失败");
						}
					}
				}
			}
		});

		/**
		 * 创建鼠标监听事件，监听点击表格时所发生的事件
		 */
		table.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int row = table.getSelectedRow();
				if (row > 0) {
					txtNo.setText(table.getValueAt(row, 0).toString());
					txtName.setText(table.getValueAt(row, 1).toString());
					txtAge.setText(table.getValueAt(row, 3).toString());
					String sex = table.getValueAt(row, 2).toString();
					if ("男".equals(sex)) {
						maleRadioBtn.setSelected(true);
					} else {
						femaleRadioBtn.setSelected(true);
					}
					txtAddr.setText(table.getValueAt(row, 6).toString());
					txtPhone.setText(table.getValueAt(row, 5).toString());
					txtPosition.setText(table.getValueAt(row, 8).toString());
					txtDate.setText(table.getValueAt(row, 7).toString());
					txtIdNo.setText(table.getValueAt(row, 4).toString());
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
	}

	/**
	 * 清空文本框
	 */
	private void cleanTxt() {
		txtNo.setText("");
		txtName.setText("");
		txtAge.setText("");
		txtPhone.setText("");
		txtAddr.setText("");
		txtDate.setText("");
		txtPosition.setText("");
		txtIdNo.setText("");
	}

	/**
	 * 成功事件
	 */
	public void success() {
		tableModel.fireTableDataChanged();
		fullEmp();
	}

	/**
	 * 获取所有文本框的值
	 * 
	 * @return
	 */
	public Employee getAllTxt() {
		Employee emp = new Employee();
		if (maleRadioBtn.isSelected()) {
			emp.setSex("男");
		} else {
			emp.setSex("女");
		}
		String empNo = txtNo.getText();
		String name = txtName.getText();
		String age = txtAge.getText();
		String phoneNo = txtPhone.getText();
		String addr = txtAddr.getText();
		String date = txtDate.getText();
		String postion = txtPosition.getText();
		String card = txtIdNo.getText();

		emp.setAddress(addr);
		emp.setToDate(date);
		emp.setTelephone(phoneNo);
		emp.setEmployee(postion);
		emp.setAge(Integer.valueOf(age));
		emp.setEmpName(name);
		emp.setEmpNo(Integer.valueOf(empNo));
		emp.setEmpCard(card);
		return emp;
	}

	/**
	 * 填充数据
	 */
	private void fullEmp() {
		List<Employee> emList = employeeDao.searchAll();
		tableModel = new DefaultTableModel(null, title);// 设置表格头部以及显示信息
		for (int i = 0; i < emList.size(); i++) {
			Employee employee = (Employee) emList.get(i);
			Object[] rowData = { employee.getEmpNo(), employee.getEmpName(), employee.getSex(), employee.getAge(), employee.getEmpCard(), employee.getTelephone(), employee.getAddress(),
					employee.getToDate(), employee.getEmployee() };
			tableModel.addRow(rowData);
		}
		table.setModel(tableModel);// 设置表格
		// 隐藏表格中的：身份证号、联系方式、联系地址、入职时间列
		Tools.HiddenCell(table, 4);
		Tools.HiddenCell(table, 5);
		Tools.HiddenCell(table, 6);
		Tools.HiddenCell(table, 7);
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