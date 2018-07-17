
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Produto;
import model.bean.Venda;
import model.bean.Vendedor;


public class VendaDAO {
    
    public void create(Venda v){
        Connection con =  ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO VENDA(codigoVenda, dataVenda,"
                    + " total, fk_vendedor) VALUES(default, now(), ?, ?)");
            stmt.setDouble(1, v.getTotal());
            stmt.setInt(2, v.getVendedor().getCodigoVendedor());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Venda inserido com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar inserir venda");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }  
    }
    
    public List<Venda> read(){
        Venda vendaObjeto ;
        Produto produtoObjeto;
        Vendedor vendedorObjeto;
        List<Venda>lista = new ArrayList();
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("SELECT codigoVenda, dataVenda, fk_vendedor, total, nome FROM VENDA,VENDEDOR "
                                       +"WHERE fk_vendedor = codigoVendedor;");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                int codigoVenda = rs.getInt("codigoVenda");
                String dataVenda = rs.getString("dataVenda");
                int idVendedor = rs.getInt("fk_vendedor");
                double total = rs.getDouble("total");
                String nome = rs.getString("nome");
                vendedorObjeto = new Vendedor(idVendedor, nome);
                vendaObjeto = new Venda(codigoVenda, vendedorObjeto, dataVenda, total);
                lista.add(vendaObjeto);
            }
            return lista;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao tentar Pesquisar Venda");
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    return null;
    }
    
}
