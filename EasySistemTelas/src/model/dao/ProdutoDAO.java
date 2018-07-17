
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
import model.bean.Produto;


public class ProdutoDAO {
    
    public void create(Produto p){
        Connection con =  ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO produto(codigoProduto, controleExtra,"
                    + " codigoBarras, resumida, completa, dataInclusao, qtdMinima, qtdMaxima, qtdEstoque,"
                    + "unidadeMedida, valorVenda, custoSemImposto, custoUnidade, lucrativo"
                    + ", desconto, ncm, cest,preco, fk_fornecedor) VALUES(default, ?, ?, ?, ?, now(), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?)");
            
            stmt.setInt(1,p.getControleExtra());
            stmt.setInt(2, p.getCodigoBarras());
            stmt.setString(3, p.getResumida());
            stmt.setString(4, p.getCompleta());
            stmt.setDouble(5, p.getQtdMinima());
            stmt.setDouble(6, p.getQtdMaxima());
            stmt.setDouble(7, p.getQtdEstoque());
            stmt.setString(8, p.getUnidadeMedida());
            stmt.setDouble(9, p.getValorVenda());
            stmt.setDouble(10, p.getCustoSemImposto());
            stmt.setDouble(11, p.getCustoUnidade());
            stmt.setDouble(12, p.getLucrativo());
            stmt.setDouble(13, p.getDesconto());
            stmt.setLong(14, p.getNcm());
            stmt.setLong(15, p.getCest());
            stmt.setDouble(16, p.getPreco());
            stmt.setInt(17, p.getFornecedor().getCodigo());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto inserido com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar inserir produto");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }  
    }
    
    public List<Produto> read(){
        Produto p ;
        List<Produto>lista = new ArrayList();
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        
        try {
            stmt = con.prepareStatement("SELECT codigoProduto, controleExtra,"
                    + " codigoBarras, resumida, completa, dataInclusao, qtdMinima, qtdMaxima, qtdEstoque,"
                    + "unidadeMedida, valorVenda, custoSemImposto, custoUnidade, lucrativo"
                    + ", desconto, ncm, cest,preco, fk_fornecedor, nomeFornecedor FROM PRODUTO, FORNECEDOR "
                    + "WHERE fk_fornecedor = codigoFornecedor");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                int codigoProduto = rs.getInt("codigoProduto");
                int controleExtra = rs.getInt("controleExtra");
                int codigoBarras = rs.getInt("codigoBarras");            
               
                String resumida  = rs.getString("resumida");
                String completa = rs.getString("completa");
                String dataInclusao = rs.getString("dataInclusao");
                double minima = rs.getDouble("qtdMinima");
                double maxima = rs.getDouble("qtdMaxima");
                double estoque = rs.getDouble("qtdEstoque");
                String unidadeMedida = rs.getString("unidadeMedida");
             
                
                double valorVenda = rs.getDouble("valorVenda");
                double custoSemImposto = rs.getDouble("custoSemImposto");
                double custoUnidade = rs.getDouble("custoUnidade");
                double lucrativo = rs.getDouble("lucrativo");
                double desconto = rs.getDouble("desconto");
                
                long ncm = rs.getLong("ncm");
                long cest = rs.getLong("cest");
                double preco = rs.getLong("preco");
                int idFornecedor = rs.getInt("fk_fornecedor");
                String nomeFornecedor = rs.getString("nomeFornecedor");
               
                Fornecedor forn = new Fornecedor(idFornecedor, nomeFornecedor);
                
                p = new Produto(codigoProduto, controleExtra, codigoBarras, resumida, completa,
                        dataInclusao, unidadeMedida, preco, valorVenda, custoSemImposto, custoUnidade,
                        lucrativo, desconto,forn, ncm, cest, minima, maxima, estoque);
                
                lista.add(p);
            }
            return lista;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao tentar Pesquisar Produto");
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    return null;
    }
    
    public void update(Produto p){
     Connection con =  ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE PRODUTO SET controleExtra = ?, codigoBarras = ?, resumida = ?, "
                    + "completa = ?, dataInclusao = now(), qtdMinima = ?, qtdMaxima = ?, "
                    + "qtdEstoque = ?, unidadeMedida = ?, valorVenda = ?, custoSemImposto = ?, "
                    + "custoUnidade = ?, lucrativo = ?, desconto = ?, "
                    + "ncm = ?, cest = ?, "
                    + "preco = ?, fk_fornecedor = ? "
                    + "WHERE codigoProduto = ?");
            
            stmt.setInt(1,p.getControleExtra());
            stmt.setInt(2, p.getCodigoBarras());
            stmt.setString(3, p.getResumida());
            stmt.setString(4, p.getCompleta());
            stmt.setDouble(5, p.getQtdMinima());
            stmt.setDouble(6, p.getQtdMaxima());
            stmt.setDouble(7, p.getQtdEstoque());
            stmt.setString(8, p.getUnidadeMedida());
            stmt.setDouble(9, p.getValorVenda());
            stmt.setDouble(10, p.getCustoSemImposto());
            stmt.setDouble(11, p.getCustoUnidade());
            stmt.setDouble(12, p.getLucrativo());
            stmt.setDouble(13, p.getDesconto());
            stmt.setLong(14, p.getNcm());
            stmt.setLong(15, p.getCest());
            stmt.setDouble(16, p.getPreco());
            stmt.setInt(17, p.getFornecedor().getCodigo());
            stmt.setInt(18, p.getCodigoProduto());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar atualizar produto");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }  
    }
    
    public void delete(int produto){
    Connection con =  ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM PRODUTO WHERE codigoProduto = ?");
            stmt.setInt(1 ,produto);
     
            
            if(stmt.executeUpdate() == 0){
                JOptionPane.showMessageDialog(null, "Produto não foi deletado.");
            }
            else
                JOptionPane.showMessageDialog(null, "Produto deletado com sucesso.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar deletar produto.");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    } 
    
    public void venderProdutoUpdate(double quantidade, int codigo){
        Connection con =  ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE PRODUTO SET qtdEstoque =  qtdEstoque - ? WHERE codigoProduto = ?;");
            stmt.setDouble(1, quantidade);
            stmt.setInt(2, codigo);
            
            stmt.execute();
        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar deletar produto.");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    } 
}
