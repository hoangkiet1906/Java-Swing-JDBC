

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.chrono.JapaneseDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

public class NhapKho extends JFrame implements ActionListener{
	JPanel p1,p2;
	JLabel l1,l2,l3,l4,l5;
	JTextField t1,t2,t3,t4;
	JComboBox t5;
	JDateChooser ng;
	JButton b1,b2;
	DefaultTableModel dataModel;
	ArrayList<HangHoa> hh = new ArrayList<HangHoa>();
	public NhapKho(String s, ArrayList<HangHoa> h,DefaultTableModel model) {
		super(s);
		dataModel=model;
		hh=h;
		p1 = new JPanel();
		p1.setLayout(new GridLayout(5,2));
		l1 = new JLabel("ID");
		l1.setHorizontalAlignment(SwingConstants.CENTER);
		t1 = new JTextField();
		t1.setText(String.valueOf(h.size()+1));
		t1.setEnabled(false);
		p1.add(l1);
		p1.add(t1);
		l2 = new JLabel("Ten");
		l2.setHorizontalAlignment(SwingConstants.CENTER);
		t2 = new JTextField();
		p1.add(l2);
		p1.add(t2);		
		l3 = new JLabel("Gia");
		l3.setHorizontalAlignment(SwingConstants.CENTER);
		t3 = new JTextField();
		p1.add(l3);
		p1.add(t3);		
		l4 = new JLabel("SoLuong");
		l4.setHorizontalAlignment(SwingConstants.CENTER);
		t4 = new JTextField();
		p1.add(l4);
		p1.add(t4);	
		l5 = new JLabel("Ngay Nhap");
		l5.setHorizontalAlignment(SwingConstants.CENTER);
		ng = new JDateChooser();
		ng.setDateFormatString("dd-MM-yyyy");
		
		
		p1.add(l5);
		p1.add(ng);
		this.add(p1,"North");
		
		p2 = new JPanel();
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
			String id=t1.getText();
			String ten=t2.getText();
			float gia=Float.valueOf(t3.getText());
			int soLuong=Integer.valueOf(t4.getText());
			//String ngayNhap = t5.getSelectedItem().toString();
			DateFormat gg = new SimpleDateFormat("dd-MM-yyyy");
			
			String ngayNhap = gg.format(ng.getDate());
			
			HangHoa hanghoa = new HangHoa(id, ten, gia, soLuong, soLuong, ngayNhap, 0, "00-00-0000");
			if((new JDBCConnection().addHangHoa(hanghoa))) {
				hh.add(hanghoa);
				dataModel.addRow(new Object[] {hanghoa.getId(),hanghoa.getTen(),hanghoa.getGia(),
						hanghoa.getSoLuong(),hanghoa.getDaNhap(),hanghoa.getNgayNhap()
						,hanghoa.getDaXuat(),hanghoa.getNgayXuat()
				});
				JOptionPane.showMessageDialog(rootPane, "Them hang hoa thanh cong!");
				this.dispose();
				new NhapKho("Nhap Kho", hh, dataModel);	
			}	
		}
		else if(e.getActionCommand().equals("Cancel")) {
					this.dispose();
		}
	}
}

