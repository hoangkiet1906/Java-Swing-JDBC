import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

public class ChinhSua extends JFrame implements ActionListener{
	JPanel p1,p2;
	JLabel l1,l2,l3,l4,l5,l55,l6,l66;
	JTextField t1,t2,t3,t4,t5,t55,t6,t66;
	JButton b1,b2;
	DefaultTableModel dataModel;
	ArrayList<HangHoa> hh = new ArrayList<HangHoa>();
	int Row;
	JDateChooser ng,ng1;
	public ChinhSua(String s, ArrayList<HangHoa> h, DefaultTableModel model, int getRow) {
		super(s);
		Row = getRow;
		dataModel=model;
		hh=h;
		p1 = new JPanel();
		p1.setLayout(new GridLayout(8,2));
		l1 = new JLabel("ID");
		l1.setHorizontalAlignment(SwingConstants.CENTER);
		t1 = new JTextField(h.get(getRow).getId());
		t1.setEnabled(false);
		t1.setFont(new Font("Tahoma", Font.BOLD, 12));
		t1.setForeground(Color.RED);
		p1.add(l1);
		p1.add(t1);
		l2 = new JLabel("Ten");
		l2.setHorizontalAlignment(SwingConstants.CENTER);
		t2 = new JTextField(h.get(getRow).getTen());
		p1.add(l2);
		p1.add(t2);		
		l3 = new JLabel("Gia");
		l3.setHorizontalAlignment(SwingConstants.CENTER);
		t3 = new JTextField(String.valueOf(h.get(getRow).getGia()));
		p1.add(l3);
		p1.add(t3);		
		l4 = new JLabel("SoLuong");
		l4.setHorizontalAlignment(SwingConstants.CENTER);
		t4 = new JTextField(String.valueOf(h.get(getRow).getSoLuong()));
		p1.add(l4);
		p1.add(t4);	
		l5 = new JLabel("DaNhap");
		l5.setHorizontalAlignment(SwingConstants.CENTER);
		t5 = new JTextField(String.valueOf(h.get(getRow).getDaNhap()));
		p1.add(l5);
		p1.add(t5);
		l55 = new JLabel("NgayNhap");
		l55.setHorizontalAlignment(SwingConstants.CENTER);
		t55 = new JTextField(String.valueOf(h.get(getRow).getNgayNhap()));
		ng = new JDateChooser();
		ng.setDateFormatString("dd-MM-yyyy");
		p1.add(l55);
		p1.add(ng);
		l6 = new JLabel("DaXuat");
		l6.setHorizontalAlignment(SwingConstants.CENTER);
		t6= new JTextField(String.valueOf(h.get(getRow).getDaXuat()));
		p1.add(l6);
		p1.add(t6);
		l66 = new JLabel("NgayXuat");
		l66.setHorizontalAlignment(SwingConstants.CENTER);
		t66 = new JTextField(String.valueOf(h.get(getRow).getNgayXuat()));
		ng1 = new JDateChooser();
		ng1.setDateFormatString("dd-MM-yyyy");
		p1.add(l66);
		p1.add(ng1);
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
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("OK")) {
			String id = t1.getText();
			int row = Integer.valueOf(id);
			String ten = t2.getText();
			double gia = Double.valueOf(t3.getText());
			int soLuong = Integer.valueOf(t4.getText());
			int daNhap = Integer.valueOf(t5.getText());
			
			//String ngayNhap = t55.getText();
			DateFormat gg = new SimpleDateFormat("dd-MM-yyyy");			
			String ngayNhap = gg.format(ng.getDate());
			
			int daXuat = Integer.valueOf(t6.getText());
			
			//String ngayXuat = t66.getText();
			DateFormat gg1 = new SimpleDateFormat("dd-MM-yyyy");
			String ngayXuat = gg1.format(ng.getDate());
			
			HangHoa clone = new HangHoa(id, ten, gia, soLuong, daNhap, ngayNhap, daXuat, ngayXuat);
			if(new JDBCConnection().chinhSuaHangHoa(clone, clone.getId())) {
				hh.set(row-1, clone);
				this.dataModel.insertRow(row-1, new Object[] {
						clone.getId(),clone.getTen(),clone.getGia(),clone.getSoLuong(),
						clone.getDaNhap(),clone.getNgayNhap(),clone.getDaXuat(),clone.getNgayXuat()
				});
				this.dataModel.removeRow(row);
				this.dataModel.fireTableDataChanged();
				JOptionPane.showMessageDialog(rootPane, "Chinh sua hang hoa thanh cong!");
				this.dispose();
			}
		}
		else 
			if(e.getActionCommand().equals("Cancel")) {
				this.dispose();
			}
	}
	
}
