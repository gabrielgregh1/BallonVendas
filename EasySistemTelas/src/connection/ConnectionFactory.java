
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class ConnectionFactory {
    
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/easySistem";
    private static final String USERNAME = "root";
    private static final String PASS = "";
    
    public static Connection getConnection(){  
        try{
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USERNAME, PASS);
        }catch(ClassNotFoundException | SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao tentar manter conexão! (getConnection)");
            return null;
        }
    }
    
    public static void closeConnection(Connection con){
        try{
            if(con!=null){
                con.close();
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao tentar finalizar conexão! (Connection)");
        }
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt){
        closeConnection(con);
        try{
            if(stmt!=null){
                stmt.close();
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao tentar finalizar conexão! (PreparedStatement)");
        }
    }
   
    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs){
        closeConnection(con, stmt);
        try{
            if(rs!=null){
                rs.close();
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao tentar finalizar conexão! (Resullt)");
        }
    }
}


