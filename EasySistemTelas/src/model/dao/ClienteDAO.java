

package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Cliente;



public class ClienteDAO {
    
    public void create(Cliente c){
        Connection con =  ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO CLIENTE(cpf, nome,"
                    + " endereco) VALUES(?, ?, ?)");
            stmt.setInt(1, c.getCpf());
            stmt.setString(2,c.getNome());
            stmt.setString(3, c.getEndereco());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cliente inserido com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar inserir produto");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }  
    }
    
    public List<Cliente> read(){
        Cliente c ;
        List<Cliente>lista = new ArrayList();
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM CLIENTE");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                int cpf = rs.getInt("cpf");
                String nome = rs.getString("nome");
                String endereco = rs.getString("endereco");
                
                c = new Cliente(nome, endereco, cpf);
                lista.add(c);
            }
            return lista;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao tentar Pesquisar Cliente");
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    return null;
    }
    
    
}
