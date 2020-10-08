
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class CT_BaoCao 
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
    public DefaultTableModel HienDSBaoCao(Date DateCu, Date DateMoi)
    {
        LoadDB();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String NGC = f.format( DateCu );
        System.err.println(NGC);
        String NamCu = NGC.substring(0, 4);
        String ThangCu = NGC.substring(5, 7);
        String NgayCu = NGC.substring(8, 10);
        System.err.println("Năm Cũ"+NamCu+"  "+ThangCu+"  "+NgayCu);
         
        String NGM = f.format( DateMoi );
        String NamMoi = NGM.substring(0, 4);
        String ThangMoi = NGM.substring(5, 7);
        String NgayMoi = NGM.substring(8, 10);
        System.err.println("Năm Cũ"+NamMoi+"  "+ThangMoi+"  "+NgayMoi);
       
        String Sql = "SELECT  MaSP AS 'Mã SP', (select TenSP From SanPham Where MaSP = CT.MaSP) AS 'Tên Sản Phẩm',\n" +
                        "		(select  DISTINCT DongCT.MaDVT  from DongCT where MaSP = CT.MaSP) AS 'ĐVT', \n" +
                        "		SUM (SoLuong) AS 'Số Lượng', (select  DISTINCT DonGia  from DongCT where MaSP = CT.MaSP) AS 'Giá Bán',\n" +
                        "		(select  DISTINCT DonGia*SUM (CT.SoLuong)  from DongCT where MaSP = CT.MaSP) AS 'Doanh Số' 		\n" +
                        "From DongCT CT\n" +
                        "	WHERE YEAR(GioVao)  BETWEEN "+NamCu+" AND "+NamMoi+"\n" +
                        "			AND MONTH(GioVao) BETWEEN "+ThangCu+" AND "+ThangMoi+"\n" +
                        "			AND DAY(GioVao) BETWEEN "+NgayCu+" AND "+NgayMoi+"\n" +
                        "GROUP BY MaSP";
        try 
        {
            dtm.setRowCount(0);
            Vector<String> row,colum;//lay gia tri
	    colum=new Vector<String>();
            rs=truyvan.executeQuery(Sql);
            ResultSetMetaData metaData =rs.getMetaData();
	          
	    for (int i=1; i<=6;i++)//so co
	       colum.add(metaData.getColumnName(i));
	    dtm.setColumnIdentifiers(colum);
	    while (rs.next())
	    {	
	        row = new Vector<String>();
	        for (int i=1; i<=6;i++)//so hang
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
       
        
    
}
