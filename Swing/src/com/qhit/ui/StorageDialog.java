package com.qhit.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.qhit.dao.IGoodsDao;
import com.qhit.dao.IStorageDao;
import com.qhit.dao.impl.GoodsDaoImpl;
import com.qhit.dao.impl.StorageDaoImpl;
import com.qhit.model.Goods;
import com.qhit.model.Storage;

/**
 * 入库登记
 * 
 * @author Administrator
 * 
 */
@SuppressWarnings("deprecation")
public class StorageDialog extends JDialog {

	private static final long serialVersionUID = 6094347240437302887L;

	private JButton cacelButton;
	private JButton subButton;

	private JTextField txtPerson;
	private JTextField txtDate;
	private JTextField txtNum;
	private JTextField txtNo;

	private JLabel lblNo;
	private JLabel lblNum;
	//private JLabel lblClose;
	private JLabel lblPerson;
	private JLabel lblDate;

	private IGoodsDao goodsDao;
	private IStorageDao storageDao;

	public StorageDialog() {
		goodsDao = new GoodsDaoImpl();
		storageDao = new StorageDaoImpl();
	}

	/**
	 * 初始化
	 */
	public void init(final JTable table) {
		setTitle("产品入库");
		setSize(350, 320);
		setLayout(null);
		// 设置窗体居中
		setLocationRelativeTo(null);

//		Icon icon = new ImageIcon("D:\\repository\\items\\image\\JDialogClose.png");
//
//		lblClose = new JLabel();
//		lblClose.setIcon(icon);
//		lblClose.setOpaque(false);
//		lblClose.setBorder(null);
//		lblClose.setBounds(277, 0, 22, 22);
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

		lblNo = new JLabel("产品编号");
		lblNo.setBounds(24, 39, 60, 15);

		txtNo = new JTextField(table.getValueAt(table.getSelectedRow(), 0).toString());
		txtNo.setBounds(100, 37, 154, 22);

		lblNum = new JLabel("产品数量");
		lblNum.setBounds(24, 80, 60, 15);

		txtNum = new JTextField();
		txtNum.setBounds(100, 75, 154, 22);

		lblDate = new JLabel("入库日期");
		lblDate.setBounds(24, 120, 100, 15);

		txtDate = new JTextField(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		txtDate.setBounds(100, 115, 154, 22);

		lblPerson = new JLabel("登记负责人");
		lblPerson.setBounds(24, 155, 154, 22);

		txtPerson = new JTextField();
		txtPerson.setBounds(100, 155, 154, 22);

		subButton = new JButton("确    定");
		subButton.setBounds(60, 200, 90, 30);
		subButton.setBorderPainted(false);// 设置按钮边界不显示
		subButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();

				if (row == -1) {
					JOptionPane.showMessageDialog(null, "请选择需要登记的商品记录");
				} else {
					txtNo.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
					String id = txtNo.getText();
					String num = txtNum.getText();
					String date = txtDate.getText();
					String person = txtPerson.getText();

					Goods goods = goodsDao.findById(id);
					goods.setGoodNum(Integer.valueOf(num));
					goodsDao.update(goods);

					Storage storage = new Storage();
					storage.setGoodsId(goods.getGid());
					storage.setStorhead(person);
					storage.setStorageDate(date);

					boolean bool = storageDao.save(storage);
					if (bool) {
						JOptionPane.showMessageDialog(null, "录入成功");
					} else {
						JOptionPane.showMessageDialog(null, "录入失败");
					}
					hide();
				}
			}
		});

		cacelButton = new JButton("取    消");
		cacelButton.setBounds(170, 200, 90, 30);
		cacelButton.setBorderPainted(false);// 设置按钮边界不显示
		cacelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				hide();
			}
		});

		getContentPane().add(lblNo);
		getContentPane().add(txtNo);
		getContentPane().add(lblNum);
		getContentPane().add(txtNum);
		getContentPane().add(lblDate);
		getContentPane().add(txtDate);
		getContentPane().add(lblPerson);
		getContentPane().add(txtPerson);
		getContentPane().add(subButton);
		getContentPane().add(cacelButton);
		//getContentPane().add(lblClose);

		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		getContentPane().setBackground(new Color(0, 0, 0, 0));
		getRootPane().setOpaque(false);
		show();
	}
}