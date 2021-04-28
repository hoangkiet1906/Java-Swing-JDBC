import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class ThongKeTime extends JFrame implements ActionListener{
	ArrayList<HangHoa> hh;
	JPanel p,p1,p2;
	JScrollPane spN,spX;
	JTable tbNhap,tbXuat;
	DefaultTableModel dataNhap,dataXuat;
	JTextField time1,time2;
	JButton ok,x,rf;
	public ThongKeTime(String s, ArrayList<HangHoa> h) {
		super(s);
		hh = h;
		dataNhap = new DefaultTableModel();
		dataNhap.addColumn("Da Nhap");
		dataNhap.addColumn("So Luong Nhap");
		dataNhap.addColumn("Thoi Gian Nhap");
		tbNhap = new JTable();
		tbNhap.setModel(dataNhap);
		
		dataXuat = new DefaultTableModel();
		dataXuat.addColumn("Da Xuat");
		dataXuat.addColumn("So Luong Xuat");
		dataXuat.addColumn("Thoi Gian Xuat");
		tbXuat = new JTable();
		tbXuat.setModel(dataXuat);
		
		spN = new JScrollPane(tbNhap);
		spX = new JScrollPane(tbXuat);
		
		p = new JPanel();
		p.setLayout(new GridLayout(1,2));
		p.add(spN);
		p.add(spX);
		
		p1 = new JPanel();
		JLabel ltitle = new JLabel("Thong Ke Trong Khoang Thoi Gian");
		p1.add(ltitle);
		
		p2 = new JPanel();
		p2.setLayout(new GridLayout(2,4));
		JLabel l1 = new JLabel("Thoi gian tu : ");
		l1.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel l2 = new JLabel("Cho den : ");
		l2.setHorizontalAlignment(SwingConstants.CENTER);
		time1 = new JTextField();
		time2 = new JTextField();
		ok = new JButton("OK");
		ok.addActionListener(this);
		x = new JButton("Cancel");
		x.setForeground(new Color(255, 0, 0));
		x.addActionListener(this);
		rf = new JButton("Refresh");
		rf.addActionListener(this);
		p2.add(l1);
		p2.add(time1);
		p2.add(l2);
		p2.add(time2);
		p2.add(new JLabel());
		p2.add(ok);
		p2.add(rf);
		p2.add(x);
		
		this.add(p1,BorderLayout.NORTH);
		this.add(p,BorderLayout.CENTER);
		this.add(p2,BorderLayout.SOUTH);
		setLocation(400,200);
		setSize(600, 300);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("OK")) {
			
			String timeB = time1.getText();
			String timeE = time2.getText();
			Date timeBegin = null,timeEnd = null;
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			try {	
				timeBegin = format.parse(timeB);
				timeEnd = format.parse(timeE);
			} catch (ParseException o) {
				o.printStackTrace();
			}	
			if(timeBegin.before(timeEnd)) {
				 Collections.sort(hh, new Comparator<HangHoa>() {
				        DateFormat f = new SimpleDateFormat("dd-MM-yyyy");
				        public int compare(HangHoa h1, HangHoa h2) {
				        	String o1 = new String(h1.getNgayNhap());
				        	String o2 = new String(h2.getNgayNhap());
				            try {
				                return f.parse(o1).compareTo(f.parse(o2));
				            } catch (ParseException e) {
				                throw new IllegalArgumentException(e);
				            }
				        }
				    });
				 for (HangHoa k : hh) {
						{
							Date cloneN;
							try {
								cloneN = format.parse(k.getNgayNhap());							
								if(timeBegin.before(cloneN)&&cloneN.before(timeEnd)) {
									dataNhap.addRow(new Object[] {
											k.getTen(),k.getSoLuong(),k.getNgayNhap()
									});
									tbNhap.setModel(dataNhap);
								}															
							}catch (ParseException o) {
								o.printStackTrace();
							}
						}
					}
				 Collections.sort(hh, new Comparator<HangHoa>() {
				        DateFormat f = new SimpleDateFormat("dd-MM-yyyy");
				        public int compare(HangHoa h1, HangHoa h2) {
				        	String o1 = new String(h1.getNgayXuat());
				        	String o2 = new String(h2.getNgayXuat());
				            try {
				                return f.parse(o1).compareTo(f.parse(o2));
				            } catch (ParseException e) {
				                throw new IllegalArgumentException(e);
				            }
				        }
				    });
				 for (HangHoa k : hh) {
						{
							Date cloneX;
							try {
								cloneX = format.parse(k.getNgayXuat());							
								if(timeBegin.before(cloneX)&&cloneX.before(timeEnd)) {
									dataXuat.addRow(new Object[] {
											k.getTen(),k.getSoLuong(),k.getNgayXuat()
									});
									tbXuat.setModel(dataXuat);
								}															
							}catch (ParseException o) {
								o.printStackTrace();
							}
						}
					}
//				for (HangHoa k : hh) {
//					{
//						Date cloneN,cloneX;
//						try {
//							cloneN = format.parse(k.getNgayNhap());
//							cloneX = format.parse(k.getNgayXuat());
//							
//							if(timeBegin.before(cloneN)&&cloneN.before(timeEnd)) {
//								dataNhap.addRow(new Object[] {
//										k.getTen(),k.getSoLuong(),k.getNgayNhap()
//								});
//								tbNhap.setModel(dataNhap);
//							}
//							if(timeBegin.before(cloneX)&&cloneX.before(timeEnd)) {
//								dataXuat.addRow(new Object[] {
//										k.getTen(),k.getSoLuong(),k.getNgayXuat()
//								});
//								tbXuat.setModel(dataXuat);
//							}
//							
//							
//						}catch (ParseException o) {
//							o.printStackTrace();
//						}
//					}
//				}
				JOptionPane.showMessageDialog(rootPane, "Thong ke Thanh Cong!");
			}
			else
				JOptionPane.showMessageDialog(rootPane, "Loi! Ngay bat dau > Ngay ket thuc");
		}
		else if (e.getActionCommand().equals("Refresh")) {
			this.dispose();
			new ThongKeTime("Thong Ke Hang Hoa Theo Thoi Gian", hh);
		}
		else if (e.getActionCommand().equals("Cancel"))
			this.dispose();
	}
}
