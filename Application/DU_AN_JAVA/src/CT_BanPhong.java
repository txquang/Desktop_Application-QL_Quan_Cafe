
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class CT_BanPhong 
{
    Statement truyvan;
    java.sql.Connection conn;
    ResultSet rs;
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
    public void CapNhatBan(String SoBan,int TrangThai)//Cạp nhất trang thai ban
    {
        LoadDB();
        String sql = "update BanPhong  set NoUse = ? where SoBan = ?";
        try 
        {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, TrangThai);
            ps.setString(2, SoBan);
            ps.executeUpdate();
           
        } 
        catch (Exception e) 
        {
            System.err.println(e);
        }
    }
    public String KiemTraBanPhong(String SoBan)//kiem tra xem phòng trống không
    {
        LoadDB();
        String TrangThai = "";
        String Sql = "SELECT NoUse FROM BanPhong WHERE SoBan= '"+SoBan+"'";
        try 
        {
            rs=truyvan.executeQuery(Sql);
             while (rs.next())
	    {
	        TrangThai = rs.getString(1);
	    }
        } 
        catch (Exception e) 
        {
            System.err.println(e);
        }
        return TrangThai;
    }
}
