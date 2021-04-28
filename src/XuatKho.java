import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

public class XuatKho extends JFrame implements ActionListener{
	JPanel p1,p2;
	JLabel l1,l2,l3;
	JTextField t1,t2,t3;
	JButton b1,b2;
	DefaultTableModel dataModel;
	ArrayList<HangHoa> hh = new ArrayList<HangHoa>();
	JDateChooser ng;
	public XuatKho(String s, ArrayList<HangHoa> h, DefaultTableModel model) {
		super(s);
		dataModel = model;
		hh = h;
		p1 = new JPanel();
		p2 = new JPanel();
		l1 = new JLabel("ID");
		l1.setHorizontalAlignment(SwingConstants.CENTER);
		l2 = new JLabel("SoLuong");
		l2.setHorizontalAlignment(SwingConstants.CENTER);
		t1 = new JTextField();
		t2 = new JTextField();
		l3 = new JLabel("NgayXuat");
		l3.setHorizontalAlignment(SwingConstants.CENTER);
		t3 = new JTextField("00-00-0000");
		p1.setLayout(new GridLayout(3,2));
		p1.add(l1);
		p1.add(t1);
		p1.add(l2);
		p1.add(t2);
		p1.add(l3);
		ng = new JDateChooser();
		ng.setDateFormatString("dd-MM-yyyy");
		p1.add(ng);
		this.add(p1,"North");
		
		p2.setLayout(new FlowLayout());
		b1 = new JButton("OK");
		b1.addActionListener(this);
		b2 = new JButton("Cancel");
		b2.addActionListener(this);
		p2.add(b1);
		p2.add(b2);
		this.add(p2,"South");
		setSize(350,250);
		setLocation(525,350);
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("OK")) {
			int id = Integer.valueOf(t1.getText());
			int soLuong = Integer.valueOf(t2.getText());
			//String ngayXuat = t3.getText();
			DateFormat gg = new SimpleDateFormat("dd-MM-yyyy");
			
			String ngayXuat = gg.format(ng.getDate());
			
			if(hh.get(id-1).getId().equals(String.valueOf(id)) 
						&& (hh.get(id-1).getSoLuong()>=soLuong)) {
				if(new JDBCConnection().xuatHangHoa(hh.get(id-1), String.valueOf(id), soLuong, ngayXuat)) {
					hh.set(id-1, new HangHoa(hh.get(id-1).getId()
							, hh.get(id-1).getTen(), hh.get(id-1).getGia()
							, hh.get(id-1).getSoLuong()-soLuong
							, hh.get(id-1).getDaNhap(),hh.get(id-1).getNgayNhap()
							, hh.get(id-1).getDaXuat()+soLuong, ngayXuat));
					this.dataModel.insertRow(id-1, new Object[] {hh.get(id-1).getId(),hh.get(id-1).getTen()
							,hh.get(id-1).getGia(),hh.get(id-1).getSoLuong()
							,hh.get(id-1).getDaNhap(),hh.get(id-1).getNgayNhap()
							,hh.get(id-1).getDaXuat(),hh.get(id-1).getNgayXuat()
					});
					this.dataModel.removeRow(id);
					this.dataModel.fireTableDataChanged();
					JOptionPane.showMessageDialog(rootPane, "Xuat hang hoa thanh cong!");
				}
			}
			else JOptionPane.showMessageDialog(rootPane, "Khong co hang hoa trong kho!");
		}
		else 
			if(e.getActionCommand().equals("Cancel")) {
				this.dispose();
			}
	}

}
