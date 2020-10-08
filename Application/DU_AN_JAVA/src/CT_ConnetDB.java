
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class CT_ConnetDB 
{
    
    Statement truyvan;
    java.sql.Connection conn;
    Connection ConnetDB()
    {
        try
	{   
            conn=DriverManager.getConnection("jdbc:sqlserver://DESKTOP-37PC9RI:1433; databaseName =DuAn","sa","sa");             
        }
        catch (SQLException e)
        {
            System.err.println("KẾT NỐI THẤT BẠI");   
          
             
        }
        return conn; 
    }
}
