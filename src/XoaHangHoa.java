import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class XoaHangHoa {
	ArrayList<HangHoa> hh;
	DefaultTableModel dataModel;
	int getRow;
	public XoaHangHoa(ArrayList<HangHoa> h,DefaultTableModel model,int row) {
		getRow = row;
		hh=h;
		dataModel = model;
		if(new JDBCConnection().xoaHangHoa(h.get(row), String.valueOf(row+1))) {
			h.remove(row);
			model.removeRow(row);
			for (int i = row; i <= h.size(); i++) {
				if(new JDBCConnection().chinhSuaID(String.valueOf(i+2))){
					
				}
				h.get(i).setId(String.valueOf(i+1));
				model.insertRow(i, new Object[] {
						h.get(i).getId(),h.get(i).getTen(),
						h.get(i).getGia(),h.get(i).getSoLuong(),
						h.get(i).getDaNhap(),h.get(i).getNgayNhap(),
						h.get(i).getDaXuat(),h.get(i).getNgayXuat()
				});
				model.removeRow(i+1);
			}
		}
	}
}
