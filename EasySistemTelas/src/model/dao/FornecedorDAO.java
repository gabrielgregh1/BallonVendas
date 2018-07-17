/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Fornecedor;

/**
 *
 * @author gabri
 */
public class FornecedorDAO {
     public void create(Fornecedor f){
        Connection con =  ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO fornecedor(codigoFornecedor, nomeFornecedor) VALUES(default,?)");
            
            stmt.setString(1,f.getNome());

            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Fornecedor inserido com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar inserir Fornecedor");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }  
    }
    
    public List<Fornecedor> read(){
        Fornecedor f;
        List<Fornecedor>lista = new ArrayList();
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM fornecedor");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                int codigoFornecedor = rs.getInt("codigoFornecedor");
                String nomeFornecedor = rs.getString("nomeFornecedor");
           
                f = new Fornecedor(codigoFornecedor, nomeFornecedor);

                lista.add(f);
            }
            return lista;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao tentar Pesquisar Fornecedor");
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    return null;
    }
}
