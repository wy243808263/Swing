package com.qhit.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

@SuppressWarnings("deprecation")
public class MainFrameDialog extends JDialog {

	private static final long serialVersionUID = -7539359909458103899L;

	/**
	 * 初始化
	 * 
	 * @param option
	 *            选项
	 * @param lblPrice
	 *            价格
	 */
	public void init(String option, String lblPrice) {
		setTitle("完成交易");

//		Icon icon = new ImageIcon("D:\\repository\\items\\image\\JDialogClose.png");
//		JLabel lblClose = new JLabel();
//		lblClose.setIcon(icon);
//		lblClose.setOpaque(false);
//		lblClose.setBorder(null);
//		lblClose.setBounds(217, 0, 22, 22);
//		lblClose.addMouseListener(new MouseListener() {
//			@Override
//			public void mouseReleased(MouseEvent arg0) {
//				hide();
//			}
//
//			@Override
//			public void mousePressed(MouseEvent arg0) {
//			}
//
//			@Override
//			public void mouseExited(MouseEvent arg0) {
//			}
//
//			@Override
//			public void mouseEntered(MouseEvent arg0) {
//			}
//
//			@Override
//			public void mouseClicked(MouseEvent arg0) {
//			}
//		});

		JLabel hejiLabel = new JLabel("合计：" + lblPrice);
		hejiLabel.setBounds(54, 0, 100, 100);

		JLabel shishouLabel = new JLabel("实收：" + option);
		shishouLabel.setBounds(54, 30, 60, 100);

		Double sum = Double.valueOf(lblPrice);
		Double from = Double.valueOf(option);

		if (sum.intValue() > from.intValue()) {
			option = JOptionPane.showInputDialog(null, "<html><font style = 'font-size:12'>重新输入：", "实收金额小于合计金额", JOptionPane.PLAIN_MESSAGE);
		}

		int total = from.intValue() - sum.intValue();
		JLabel totalLabel = new JLabel("应找：" + total);
		totalLabel.setBounds(54, 90, 160, 100);
		totalLabel.setForeground(Color.BLUE);
		totalLabel.setFont(new Font("Dialog", 1, 30));

		// final JButton yesButton = new JButton("是(Y)");
		// yesButton.setBounds(54, 180, 100, 30);
		//
		// final JButton noButton = new JButton("否(N)");
		// noButton.setBounds(164, 180, 100, 30);

		getContentPane().setLayout(null);
		getContentPane().add(hejiLabel);
		getContentPane().add(shishouLabel);
		getContentPane().add(totalLabel);
		//getContentPane().add(lblClose);
		// dialog.getContentPane().add(yesButton);
		// dialog.getContentPane().add(noButton);

		setSize(300, 300);
		setLocation(200, 200);

		setLocationRelativeTo(null);
		setUndecorated(true);
		getContentPane().setBackground(new Color(0, 0, 0, 0));
		getRootPane().setOpaque(false);
		show();
	}
}