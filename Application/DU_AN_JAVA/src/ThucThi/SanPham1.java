/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThucThi;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Kirito
 */
public class SanPham1 
{
    Statement truyvan;
    java.sql.Connection conn;
    public void LoadDB()
    {
        try
	{   
            ConnetDB x =new ConnetDB();
            conn = x.ConnetDB();
            truyvan = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);          
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Kết nối sql thất bại");         
        }
    }
    public int  ThemSP( SanPham SP )
    {
        LoadDB();
        String sql = "insert into sanPham values(?,?,?,?,?,?,?)";
        try 
        {
            
            PreparedStatement ps = conn.prepareStatement(sql);
           
            ps.setString(1, SP.getMaSP());
            ps.setString(2, SP.getMaNhom());
            ps.setString(3, SP.getTenSP());
            ps.setString(4, SP.getMaDVT());
            ps.setInt(5, SP.getSLDK());
            ps.setFloat(6, SP.getSLCK());
            ps.setInt(7, 0);
            ps.executeUpdate();
            return 1;
        } 
        catch (Exception e) 
        {
            System.err.println(e);
            return 0;
        }
    }
    public int CapNhatSP( SanPham SP)
    {
        LoadDB();
        String sql = "update SanPham  set MaNhom = ?,TenSP = ?,MaDVT = ?,SLDK = ?,GTDK = ? where MaSP = ?";
        try 
        {
            
            PreparedStatement ps = conn.prepareStatement(sql);
           
            ps.setString(6, SP.getMaSP());
            ps.setString(1, SP.getMaNhom());
            ps.setString(2, SP.getTenSP());
            ps.setString(3, SP.getMaDVT());
            ps.setInt(4, SP.getSLDK());
            ps.setFloat(5, SP.getSLCK());
            ps.executeUpdate();
            return 1;
        } 
        catch (Exception e) 
        {
            System.err.println(e);
            return 0;
        }
    }
    public int XoaSP( String a)
    {
        LoadDB();
        String sql = "delete from SanPham where MaSP = ?";
        try 
        {
            
            PreparedStatement ps = conn.prepareStatement(sql);
           
           
            ps.setString(1, a);
           
            ps.executeUpdate();
            return 1;
        } 
        catch (Exception e) 
        {
            System.err.println(e);
            return 0;
        }
    }
}
