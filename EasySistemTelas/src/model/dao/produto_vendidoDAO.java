

package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Produto;
import model.bean.Venda;
import model.bean.Produto_vendido;


public class produto_vendidoDAO {
    public void create(Produto_vendido v){
        Connection con =  ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO produto_vendido(codigoProdutoVendido, fk_produto,"
                    + " fk_venda, qtdProdutoVendido) VALUES(default, ?, ?, ?)");
            stmt.setInt(1, v.getProduto().getCodigoProduto());
            stmt.setInt(2, v.getVenda().getCodigo());
            stmt.setDouble(3, v.getProduto().getQtdEstoque());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar inserir produto_vendido");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }  
    }
    
    public List<Produto_vendido> read(){
        Produto_vendido produto_vendidoObjeto;
        Venda vendaObjeto ;
        Produto produtoObjeto;
        List<Produto_vendido>lista = new ArrayList();
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("select resumida, dataVenda,  qtdProdutoVendido, (qtdProdutoVendido*preco) from  produto_vendido, produto, venda " +
                                        "where fk_produto = codigoProduto and fk_venda = codigoVenda " +
                                        "order by qtdProdutoVendido desc;");
           

            rs = stmt.executeQuery();
            
            while(rs.next()){
                String resumida = rs.getString(1);
                String dataVenda = rs.getString(2);
                double qtdProdutoVendido = rs.getDouble(3);
                double preco = rs.getDouble(4);
   
                produtoObjeto = new Produto(resumida);
                vendaObjeto = new Venda(dataVenda, preco);
                produto_vendidoObjeto = new Produto_vendido(produtoObjeto, vendaObjeto, qtdProdutoVendido);
                lista.add(produto_vendidoObjeto);
            }
            return lista;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao tentar Pesquisar Produto_vendido");
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    return null;
    }

}
