package com.qhit.ui;

import java.awt.AWTException;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.UIManager;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import com.qhit.dao.IEmployeeDao;
import com.qhit.dao.impl.EmployeeDaoImpl;
import com.qhit.model.Employee;
import com.qhit.model.UserInfo;
import com.qhit.tools.RegExpValidatorUtils;
import com.qhit.tools.StringUtil;
import com.qhit.tools.Tools;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPasswordField txtUserPwd;

	private JButton minBtn;
	private JButton closeBtn;
	private JButton loginBtn;

	private JCheckBox remberBox;
	private JCheckBox loginBox;

	private JComboBox userComBox;

	private IEmployeeDao employeeDao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					Tools.setBackImg(frame, "loginbk.png");
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
		setBounds(100, 100, 400, 293);
		getContentPane().setLayout(null);

		try {
			// 设置面板的样式为当前系统的样式
			BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;
			BeautyEyeLNFHelper.launchBeautyEyeLNF();
			UIManager.put("RootPane.setupButtonVisible", false);// 隐藏设置按钮
			UIManager.setLookAndFeel(BeautyEyeLNFHelper.getBeautyEyeLNFCrossPlatform());
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<Employee> employeeList = this.employeeDao.searchAll();
		Vector<Object> objects = new Vector<Object>();
		for (Employee e : employeeList) {
			objects.add(e.getEmpName());
		}

		userComBox = new JComboBox(objects);
		userComBox.setBounds(130, 147, 190, 26);
		getContentPane().add(userComBox);

		txtUserPwd = new JPasswordField(10);
		txtUserPwd.setEchoChar('*');

		UserInfo userInfo = readProperties("src/dbConfig.properties");
		if (userInfo != null) {
			if (true == userInfo.isFlag()) {
				txtUserPwd.setText(userInfo.getUserPwd());
				userComBox.setSelectedItem(userInfo.getUserName());

				MainFrame mainFrame = new MainFrame();
				mainFrame.setVisible(true);

				setVisible(false);
			} else {
				txtUserPwd.setText(userInfo.getUserPwd());
			}
		}

		txtUserPwd.setBounds(130, 182, 190, 26);
		getContentPane().add(txtUserPwd);

		loginBtn = new JButton();
		loginBtn.setIcon(new ImageIcon("D:\\repository\\items\\image\\loginqueding.png"));
		loginBtn.setBounds(110, 254, 180, 31);
		loginBtn.setOpaque(false);
		loginBtn.setContentAreaFilled(false);
		loginBtn.setBorder(null);
		getContentPane().add(loginBtn);

		minBtn = new JButton();
		minBtn.setIcon(new ImageIcon("D:\\repository\\items\\image\\Loginmin.png"));
		minBtn.setBounds(348, 0, 27, 21);
		minBtn.setOpaque(false);
		minBtn.setContentAreaFilled(false);
		minBtn.setBorder(null);
		getContentPane().add(minBtn);

		closeBtn = new JButton();
		closeBtn.setIcon(new ImageIcon("D:\\repository\\items\\image\\Loginclose.png"));
		closeBtn.setBounds(372, 0, 27, 21);
		closeBtn.setOpaque(false);
		closeBtn.setContentAreaFilled(false);
		closeBtn.setBorder(null);
		getContentPane().add(closeBtn);

		remberBox = new JCheckBox("\u8BB0\u4F4F\u5BC6\u7801");
		remberBox.setBounds(130, 214, 98, 23);
		remberBox.setOpaque(false);
		remberBox.setContentAreaFilled(false);
		getContentPane().add(remberBox);

		loginBox = new JCheckBox("\u81EA\u52A8\u767B\u5F55");
		loginBox.setBounds(235, 214, 98, 23);
		loginBox.setOpaque(false);
		loginBox.setContentAreaFilled(false);
		getContentPane().add(loginBox);

		// 登陆事件
		loginBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 获取用户名
				String userName = userComBox.getSelectedItem().toString();
				// 获取密码
				String userPwd = new String(txtUserPwd.getPassword());

				if (remberBox.isSelected()) {
					writeProperties("", userPwd);
				}

				if (loginBox.isSelected()) {
					writeProperties(userName, userPwd);
				}

				if (checkName(userName) && checkPwd(userPwd)) {// 验证用户名或密码
					if (employeeDao.login(userName, userPwd) == null) {// 判断用户名是否存在
						JOptionPane.showMessageDialog(null, "登录失败");
						return;
					} else {
						JOptionPane.showMessageDialog(null, "登录成功");
						MainFrame frame = new MainFrame();
						frame.setVisible(true);
						setVisible(false);
					}
				}
			}
		});

		// 关闭窗口
		closeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		// 窗口最小化
		minBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TrayIcon trayIcon = null;
				if (SystemTray.isSupported()) { // 判断系统是否支持系统托盘
					SystemTray tray = SystemTray.getSystemTray(); // 创建系统托盘
					try {
						Image image = Toolkit.getDefaultToolkit().getImage("D:\\repository\\items\\image\\Loginmin.png");// 载入图片,这里要写你的图标路径哦
						trayIcon = new TrayIcon(image);// 创建trayIcon
						tray.add(trayIcon);
						setVisible(false);
					} catch (AWTException e1) {
						e1.printStackTrace();
					}

					trayIcon.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {
							if (e.getClickCount() == 1) {// 双击托盘窗口再现
								setExtendedState(Frame.NORMAL);
								setVisible(true);
							}
						}
					});
				}
			}
		});
	}

	/**
	 * 验证用户名
	 * 
	 * @param userName
	 *            用户名
	 */
	public boolean checkName(String userName) {
		if (StringUtil.isBlank(userName)) {
			JOptionPane.showMessageDialog(loginBtn, "用户名不能为空");
			return false;
		} else {
			if (RegExpValidatorUtils.isUser(userName)) {
				return true;
			} else {
				JOptionPane.showMessageDialog(loginBtn, "用户名输入格式错误");
				return false;
			}
		}
	}

	/**
	 * 验证密码
	 * 
	 * @param password
	 *            密码
	 * @return
	 */
	public boolean checkPwd(String password) {
		if (StringUtil.isBlank(password)) {
			JOptionPane.showMessageDialog(loginBtn, "密码不能为空");
			return false;
		} else {
			return true;
			// if (RegExpValidatorUtils.IsPassword(password)) {
			// return true;
			// } else {
			// JOptionPane.showMessageDialog(loginBtn, "密码入格式错误");
			// return false;
			// }
		}
	}

	/**
	 * 写入文件
	 * 
	 * @param userName
	 *            用户名
	 * @param userPwd
	 *            密码
	 */
	public void writeProperties(String userName, String userPwd) {
		Properties properties = new Properties();
		properties.put("userPwd", userPwd);

		if (!"".equals(userName)) {
			properties.put("userName", userName);
			properties.put("flag", "true");
		} else {
			properties.put("flag", "false");
		}

		try {
			properties.store(new FileOutputStream(new File("src/dbConfig.properties")), "『comments』Update key：");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * 读取属性文件
	 * 
	 * @param fileName
	 *            文件名称
	 * @return 用户对象
	 */
	private UserInfo readProperties(String fileName) {
		UserInfo userInfo = new UserInfo();
		try {
			File file = new File(fileName);
			FileInputStream fis = null;

			if (file.exists()) {
				fis = new FileInputStream(file);
			} else {
				Properties properties = new Properties();
				properties.load(fis);
				
				String flag = properties.getProperty("flag");
				String userPwd = properties.getProperty("userPwd");
				
				if ("false".equals(flag)) {
					userInfo.setUserPwd(userPwd);
					userInfo.setFlag(Boolean.valueOf(flag));
				} else {
					String userName = properties.getProperty("userName");

					userInfo.setUserPwd(userPwd);
					userInfo.setUserName(userName);
					userInfo.setFlag(Boolean.valueOf(flag));
				}
			}
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
			userInfo = null;
		}
		return userInfo;
	}

	public LoginFrame() {
		employeeDao = new EmployeeDaoImpl();
		initGUI();
	}
}