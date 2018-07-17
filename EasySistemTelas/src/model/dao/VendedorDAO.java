
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Vendedor;


public class VendedorDAO {

    public void create(Vendedor v){
        Connection con =  ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO VENDEDOR(codigoVendedor, nome) VALUES(default, ?)");
            stmt.setString(1, v.getNome());  
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Vendedor inserido com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar inserir Vendedor");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }  
    }
    
    public List<Vendedor> read(){
        Vendedor vendedorObjeto;
        List<Vendedor>lista = new ArrayList();
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM VENDEDOR");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                int codigoVendedor = rs.getInt("codigoVendedor");
                String nome = rs.getString("nome");
                
                vendedorObjeto = new Vendedor(codigoVendedor, nome);
                lista.add(vendedorObjeto);
            }
            return lista;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao tentar Pesquisar Vendedor");
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    return null;
    }
    
}
