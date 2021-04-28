import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TimKiem extends JFrame implements ActionListener{
	JPanel p1,p2;
	JLabel l1,l2,l3,l4,l5,l6;
	JButton ok;
	ArrayList<HangHoa> hh = new ArrayList<HangHoa>();
	public TimKiem(String s, ArrayList<HangHoa> h, String tk) {
		super(s);
		hh=h;
		p1 = new JPanel();
		p1.setLayout(new GridLayout(6,1));
		for (HangHoa o : h) {
			if ((o.getId().equals(tk))||(o.getTen().equals(tk))) {
				int row = Integer.valueOf(o.getId());
				l1 = new JLabel("Id : "+o.getId());
				l1.setHorizontalAlignment(SwingConstants.CENTER);
				l2 = new JLabel("Ten : "+o.getTen());
				l2.setHorizontalAlignment(SwingConstants.CENTER);
				l3 = new JLabel("Gia : "+String.valueOf(o.getGia()));
				l3.setHorizontalAlignment(SwingConstants.CENTER);
				l4 = new JLabel("So luong trong kho : "+String.valueOf(o.getSoLuong()));
				l4.setHorizontalAlignment(SwingConstants.CENTER);
				l5 = new JLabel("So luong da Nhap kho : "+String.valueOf(o.getDaNhap()));
				l5.setHorizontalAlignment(SwingConstants.CENTER);
				l6 = new JLabel("So luong da Xuat kho : "+String.valueOf(o.getDaXuat()));
				l6.setHorizontalAlignment(SwingConstants.CENTER);
			}
		}
		p1.add(l1);
		p1.add(l2);
		p1.add(l3);
		p1.add(l4);
		p1.add(l5);
		p1.add(l6);
		ok = new JButton("Cancel");
		ok.addActionListener(this);
		this.add(p1,BorderLayout.CENTER);
		p2 = new JPanel();
		p2.setLayout(new FlowLayout());
		p2.add(ok);
		this.add(p2,BorderLayout.SOUTH);
		setLocation(450,200);
		setSize(500, 300);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Cancel")) {
			this.dispose();
		}
	}
}
