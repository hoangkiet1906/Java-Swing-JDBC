import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SapXep extends JFrame implements ActionListener{
	
	JScrollPane sp;
	JPanel p,TieuDe;
	JTable tb;
	DefaultTableModel model;
	ArrayList<HangHoa> hh;
	JButton ok;
	public SapXep(String s, ArrayList<HangHoa> h, String name) {
		super(s);
		hh=h;
		setLayout(new BorderLayout());
		tb = new JTable();
		model = new DefaultTableModel();
		model.addColumn("Id");
		model.addColumn("Ten");
		model.addColumn("Gia");
		model.addColumn("So Luong");
		model.addColumn("Da Nhap");
		model.addColumn("Ngay Nhap");
		model.addColumn("Da Xuat");
		model.addColumn("Ngay Xuat");
		if(name=="Ten") {
			Collections.sort(h, new Comparator<HangHoa>() {
				public int compare(HangHoa h1, HangHoa h2) {

					return h1.getTen().compareTo(h2.getTen());
				}
			});
		}
		else
			if (name=="Gia"){
				Collections.sort(h, new Comparator<HangHoa>() {
					public int compare(HangHoa h1, HangHoa h2) {
						Double g1 = Double.valueOf(h1.getGia());
						Double g2 = Double.valueOf(h2.getGia());
						return g1.compareTo(g2);
					}
				});
			}
			else 
				if(name=="So Luong") {
					Collections.sort(h, new Comparator<HangHoa>() {
						public int compare(HangHoa h1, HangHoa h2) {
							Integer s1 = h1.getSoLuong();
							Integer s2 = h2.getSoLuong();
							return s1.compareTo(s2);
						}
					});
				}
				else
					if(name=="Ngay Nhap Kho") {
						 Collections.sort(h, new Comparator<HangHoa>() {
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
					}
//						Collections.sort(h, new Comparator<HangHoa>() {
//							public int compare(HangHoa h1, HangHoa h2) {
//								String s1 = h1.getNgayNhap();
//								String d1 = new String(s1).substring(0, 2);
//								String m1 = new String(s1).substring(3, 5);
//								String y1 = new String(s1).substring(6, 10);
//								
//								String s2 = h2.getNgayNhap();
//								String d2 = new String(s2).substring(0, 2);
//								String m2 = new String(s2).substring(3, 5);
//								String y2 = new String(s2).substring(6, 10);
//								if(y1==y2) {
//									if(m1==m2) {
//										return d1.compareTo(d2);
//									}
//									else
//										return m1.compareTo(m2);
//								}
//								else return y1.compareTo(y2);
//						});
					else
						if(name=="Ngay Xuat Kho") {
							Collections.sort(h, new Comparator<HangHoa>() {
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
						}
		
		for (HangHoa e : h) {
			model.addRow(new Object[] {e.getId(),e.getTen(),e.getGia(),e.getSoLuong(),
					e.getDaNhap(),e.getNgayNhap(),e.getDaXuat(),e.getNgayXuat()});
		}
		tb.setModel(model);
		sp = new JScrollPane(tb);
		this.add(sp,BorderLayout.CENTER);
		
		TieuDe = new JPanel();
		TieuDe.setLayout(new FlowLayout());
		JLabel ten = new JLabel("Sap Xep Theo "+name);
		ten.setFont(new Font("Sitka Text", Font.BOLD, 20));
		TieuDe.add(ten,BorderLayout.CENTER);
		
		this.add(TieuDe,BorderLayout.NORTH);
		ok = new JButton("Cancel");
		ok.addActionListener(this);
		p = new JPanel();
		p.add(ok);
		p.setLayout(new FlowLayout());
		this.add(p,BorderLayout.SOUTH);
		setLocation(400,200);
		setSize(600,300);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Cancel")) {
			this.dispose();
		}
	}
}
