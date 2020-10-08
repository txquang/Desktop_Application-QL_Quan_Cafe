
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CT_HoaDon 
{
    
    Statement truyvan;
    java.sql.Connection conn;
    ResultSet rs;
    DefaultTableModel dtm=new DefaultTableModel();
    public void LoadDB()
    {
        try
	{   
            CT_ConnetDB x =new CT_ConnetDB();
            conn = x.ConnetDB();
            truyvan = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);          
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Kết nối sql thất bại");
        }
    }
    public DefaultTableModel HienCT(String SoBan)
    {
        LoadDB();
        try 
	{
            dtm.setRowCount(0);
            Vector<String> row,colum;//lay gia tri
	    colum=new Vector<String>();
	    rs=truyvan.executeQuery("SELECT SP.TenSP AS 'Tên Móm ',CT.SoLuong AS 'Số Lượng',SP.GTDK AS 'Giá'\n" +
                                        "	FROM DongCT CT \n" +
                                        "		JOIN SanPham SP\n" +
                                        "			ON SP.MaSP = CT.MaSP\n" +
                                        "	\n" +
                                        "	WHERE CT.SoCT = (SELECT TOP 1 SoCT\n" +
                                        "                           FROM ChungTu\n" +
                                        "                           WHERE SoBan = '"+SoBan+"' AND TrangThai = 0\n" +
                                        "						ORDER BY SoCT DESC)");//thay sau
	    ResultSetMetaData metaData =rs.getMetaData();
	          
	    for (int i=1; i<=3;i++)//so co
	       colum.add(metaData.getColumnName(i));
	    dtm.setColumnIdentifiers(colum);
	    while (rs.next())
	    {	
	        row = new Vector<String>();
	        for (int i=1; i<=3;i++)//so hang
	           row.add(rs.getString(i));
                dtm.addRow(row);         
	    }
	    return dtm;
	}      
	catch (SQLException ex) 
        {   	
	    System.out.print("loi"+ex);
	}
        return dtm;
    }
    public DefaultTableModel HienDSHD()
    {
        LoadDB();
        try 
	{
            dtm.setRowCount(0);
            Vector<String> row,colum;//lay gia tri
	    colum=new Vector<String>();
	    rs=truyvan.executeQuery("SELECT \n" +
                                    "	SoCT AS 'Số CT', SoBan AS 'Số Bàn', MaKH AS 'Mã KH', SoKhach AS 'Số KH',\n" +
                                    "	 NoiDung AS 'Nội Dung', MaNhanVien AS 'Mã NV', MaThuNgan AS 'Mã TN',\n" +
                                    "	 SoTien AS 'Tổng Tiền' , convert(varchar, NgayCT, 20) AS 'Ngày Lập', convert(varchar, NgayDat, 20) AS 'Ngày TT', TrangThai AS 'Trạng Thái'\n" +
                                    "FROM ChungTu ");//thay sau
	    ResultSetMetaData metaData =rs.getMetaData();
	          
	    for (int i=1; i<=11;i++)//so co
	       colum.add(metaData.getColumnName(i));
	    dtm.setColumnIdentifiers(colum);
	    while (rs.next())
	    {	
	        row = new Vector<String>();
	        for (int i=1; i<=11;i++)//so hang
	           row.add(rs.getString(i));
                dtm.addRow(row);         
	    }
	   
	}      
	catch (SQLException ex) 
        {   	
	    System.out.print("loi"+ex);
	}
        return dtm;
    }
    public String LayMaHD()//dể tao hd
    {
        LoadDB();
        String MaHD = "";
        String Sql = "SELECT COUNT(SoCT)+1 as CT  FROM ChungTu";
        try 
        {
            rs=truyvan.executeQuery(Sql);
             while (rs.next())
	    {
	        MaHD = "CT0"+ rs.getString(1);
	    }
        } 
        catch (Exception e) 
        {
            System.err.println(e);
        }
        return MaHD;
    }
    public String LayMaCT(String SoBan)//de lấy ct
    {
        String MaCT = "";
        String Sql = "SELECT TOP 1 SoCT\n" +
"			FROM ChungTu\n" +
"                       WHERE SoBan = '"+SoBan+"' AND TrangThai = 0\n" +
"                           ORDER BY SoCT DESC";
        try 
        {
            rs=truyvan.executeQuery(Sql);
             while (rs.next())
	    {
	        MaCT = rs.getString(1).trim();
	    }
        } 
        catch (Exception e) 
        {
            System.err.println(e);
        }
        return MaCT;
    }
    public void TinhTongTien(String MaHD)
    {
        String Sql = "UPDATE ChungTu\n" +
                    "	SET SoTien = (SELECT sum (DonGia * (SoLuong - TraLai) )\n" +
                                        "FROM DongCT\n" +
                                        "WHERE  SoCT = '"+MaHD+"')\n" +
                    "FROM ChungTu\n" +
                    "WHERE  SoCT = '"+MaHD+"'";
        try 
        {
            PreparedStatement ps = conn.prepareStatement(Sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    public void TaoHD(String SoBan,String MaNV) 
    {
        String MaHD = LayMaHD();
        Date now = new Date();//lay gio hien tai
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String S  =f.format(now);
        
        String sql = "INSERT ChungTu ([SoCT], [NgayCT], [SoBan],[MaNhanVien],[TrangThai],[SoKhach],[Giam],[ThueVAT],[PhiDV],[NoiDung]) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try 
        {
            
            PreparedStatement ps = conn.prepareStatement(sql);
           
            ps.setString(1, MaHD);
            ps.setString(2, S);
            ps.setString(3, SoBan);
            ps.setString(4, MaNV);
            ps.setInt(5, 0);
            ps.setInt(6, 1);
            ps.setFloat(7, 0);
            ps.setFloat(8, 0);
            ps.setFloat(9, 0);
            ps.setString(10, "Bán Lẻ");
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Mã Chứng Từ "+MaHD+" Đã Được Tạo");
            
            CT_BanPhong  BanPhong = new CT_BanPhong();
            BanPhong.CapNhatBan(SoBan, 1);
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, "Tạo Hóa Đơn Thất Bại"+e);
        }
       
    }
    public void XoaHD( String MaHD, String Ban)
    {
        LoadDB();
        String Sql = "BEGIN\n" +
                     "	DELETE DongCT WHERE SoCT = '"+MaHD+"'\n" +
                     "	DELETE ChungTu WHERE SoCT = '"+MaHD+"'\n" +
                     "END";
        try {
            PreparedStatement ps = conn.prepareStatement(Sql);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Mã Chứng Từ "+MaHD+" Đã Được Xóa");
            CT_BanPhong  BanPhong = new CT_BanPhong();
            BanPhong.CapNhatBan(Ban, 0);
        } catch (Exception e) {
        }
    }
    
    public void ThemSPVaoHD(String MaHD , String MaSP ,int SoLuong)//them cong sua
    {
        LoadDB();
        String sql =    "IF EXISTS(SELECT SoCT FROM DongCT WHERE SoCT = '"+MaHD+"' AND MaSP = '"+MaSP+"')\n" +
                        "	BEGIN\n" +
                        "		DECLARE @SoLuongC int = (SELECT SoLuong FROM DongCT WHERE SoCT = '"+MaHD+"' AND MaSP = '"+MaSP+"');--TRA VE SL CU\n" +
                        "		DECLARE @GiaC int = (SELECT GTDK FROM SanPham WHERE MaSP = '"+MaSP+"');\n" +
                        "		DECLARE @DVTC NVARCHAR(7) = (SELECT MaDVT FROM SanPham WHERE MaSP = '"+MaSP+"');\n" +
                        "		UPDATE DongCT --CAP NHAT SL\n" +
                        "			SET SoLuong = @SoLuongC +"+SoLuong+",\n" +
                        "				DonGia = @GiaC,\n" +
                        "				TraLai = 0,\n" +
                        "				MaDVT = @DVTC\n" +
                        "		WHERE SoCT = '"+MaHD+"' AND MaSP = '"+MaSP+"';\n" +
                        "	END\n" +
                        "ELSE\n" +
                        "	BEGIN \n"+
                        "		DECLARE @GiaM int = (SELECT GTDK FROM SanPham WHERE MaSP = '"+MaSP+"');\n" +
                        "		DECLARE @DVTM NVARCHAR(7) = (SELECT MaDVT FROM SanPham WHERE MaSP = '"+MaSP+"');\n" +
                        "		INSERT DongCT ([SoCT], [MaSP], [MaDVT], [SoLuong], [DonGia], [TraLai]) VALUES ('"+MaHD+"', '"+MaSP+"', @DVTM, "+SoLuong+", @GiaM, 0)\n" +
                        "	END";
        try 
        {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Thêm Sản Phẩm Thành Công");
            TinhTongTien(MaHD);
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, "Thêm Sản Phẩm Thất Bại");
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    public void XoaSP(String MaHD, String MaSP)
    {
        LoadDB();
        String Sql = "DELETE DongCT WHERE SoCT = '"+MaHD+"' AND MaSP = '"+MaSP+"'";
        try 
        {
            PreparedStatement ps = conn.prepareStatement(Sql);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Xóa Sản Phẩm Có Mã: "+MaSP+" Thành Công");
            TinhTongTien(MaHD);
        } catch (Exception e) {
        }
    }
    public void ChuyenBan(String BanCu, String BanMoi, String MaHD)
    {
        LoadDB();
        String Sql = "UPDATE ChungTu SET SoBan = '"+BanMoi+"' WHERE SoCT = '"+MaHD+"'";
        try 
        {
            PreparedStatement ps = conn.prepareStatement(Sql);
            ps.executeUpdate();
            CT_BanPhong  BanPhong = new CT_BanPhong();
            BanPhong.CapNhatBan(BanCu, 0);
            BanPhong.CapNhatBan(BanMoi, 1);
            JOptionPane.showMessageDialog(null, "Chuyển Bàn Thành Công");
        } 
        catch (Exception e) 
        {
             JOptionPane.showMessageDialog(null, e);
        }
    }
    public boolean LuuHD(String MaHD, int SoKhach, float Giam, float VAT, float PhiDV, float TraTruoc)
    {
        LoadDB();
        String Sql = "UPDATE ChungTu\n" +
                    "	SET SoKhach = "+SoKhach+",\n" +
                    "		Giam = "+Giam+",\n" +
                    "		ThueVAT = "+VAT+",\n" +
                    "		PhiDV = "+PhiDV+",\n" +
                    "		TraTruoc = "+TraTruoc+"\n" +
                    "WHERE SoCT = '"+MaHD+"'";
        try 
        {
            PreparedStatement ps = conn.prepareStatement(Sql);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Lưu Thành Công");
            return true;
        } catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
        
    }
    public void ThanhToan( String MaHD, String SoBan ,int SoKhach, float Giam, float VAT, float PhiDV, float TraTruoc, String MaNV, String GioVao)
    {
        LoadDB();
        Date now = new Date();//lay gio hien tai
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String S  =f.format(now);
        if ( LuuHD(MaHD, SoKhach, Giam, VAT, PhiDV, TraTruoc) == true )
        {
            String Sql = "UPDATE ChungTu\n" +
                        "	SET MaThuNgan = '"+MaNV+"',\n" +
                        "		ConNo = TraTruoc - SoTien,\n" +
                        "		NgayDat = '"+S+"',\n" +
                        "		TrangThai = 1\n" +
                        "WHERE SoCT = '"+MaHD+"'";
            String Sql1= "UPDATE DongCT\n" +
                            "SET GioVao = (select NgayCT from ChungTu WHERE SoCT = '"+MaHD+"') \n" +
                          "WHERE SoCT = '"+MaHD+"'";
            try 
            {
                PreparedStatement ps = conn.prepareStatement(Sql);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Thanh toán Thành Công");
                
                ps = conn.prepareStatement(Sql1);
                ps.executeUpdate();
                
                CT_BanPhong  BanPhong = new CT_BanPhong();
                BanPhong.CapNhatBan(SoBan, 0);
            } catch (Exception e) 
            {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        else 
            JOptionPane.showMessageDialog(null, "Thanh toán thất bại");
        
        
    }
    
    
}
