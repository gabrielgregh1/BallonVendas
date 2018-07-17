/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.event.KeyEvent;
import static java.awt.image.ImageObserver.HEIGHT;
import static java.awt.image.ImageObserver.WIDTH;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.bean.Fornecedor;
import model.bean.Produto;
import model.dao.FornecedorDAO;
import model.dao.ProdutoDAO;

/**
 *
 * @author gabri
 */
public class JanelaManutencaoEstoque extends javax.swing.JFrame {

    /**
     * Creates new form JanelaManutencaoEstoque
     */
    public JanelaManutencaoEstoque() {
        initComponents();
        condicaoInicial();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
    }

    private void condicaoInicial(){
        visibilidadePreInsercao();
        visibilidadePreAlteracao();
        visibilidadePreExclusao();
        configuraComboBox();
   
    }
    
    private void atalhos(KeyEvent evt){
        if(evt.getKeyCode() == KeyEvent.VK_F2){
            RealizarInclusao();
            
        }
        if(evt.getKeyCode() == KeyEvent.VK_F4)
            RealizarAlteracao();
        if(evt.getKeyCode() == KeyEvent.VK_F6){
            try{
                if(!(jTextFieldExclusaoCodigo.getText().equals(""))){
                    int codigo = Integer.parseInt(jTextFieldExclusaoCodigo.getText());
                    excluirItem(codigo);  
                }
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(rootPane, "Informe apenas números");
            }
        }
            
    }
    
    private void configuraComboBox(){
        FornecedorDAO daoForn = new FornecedorDAO();
        for(Fornecedor ponteiroFor : daoForn.read()){
            jComboBoxInsersaoFornecedor.addItem(ponteiroFor.getNome());
            jComboBoxAlteracaoFornecedor.addItem(ponteiroFor.getNome());
            jComboBoxExclusaoFornecedor.addItem(ponteiroFor.getNome());
        }
    }
    
    private String limitarTamanhoCampo(String campoJtextField){
        int idProcura = campoJtextField.indexOf(".");
        while(idProcura+3 < campoJtextField.length())
            campoJtextField = campoJtextField.substring(0, campoJtextField.length()-1);

        return campoJtextField;
    } 
    
    private void visibilidadePreInsercao(){
        jTextFieldInclusaoCodigo.setEnabled(false);
        
        ProdutoDAO dao = new ProdutoDAO();
        int i = 0;
        for(Produto p : dao.read()){
            if(i < p.getCodigoProduto())
                i = p.getCodigoProduto();
        }
        i++;
        jTextFieldInclusaoCodigo.setText(String.valueOf(i));
        
        jTextFieldInclusaoBarras.setText("");
        jTextFieldInclusaoControle.setText("");
        jTextFieldInclusaoResumida.setText("");
        jTextFieldInclusaoCompleta.setText("");
        jComboBoxInsersaoFornecedor.setSelectedIndex(0);
        jComboBoxInclusaoUnidade.setSelectedIndex(0);
        jTextFieldInclusaoNcm.setText("");
        jTextFieldInclusaoCest.setText("");
        jTextFieldInclusaoCustoUnid.setText("");
        jTextFieldInclusaoCustoImp.setText("");
        jTextFieldInclusaoLucrativa.setText("");
        jTextFieldInclusaoValorVenda.setText("");
        jTextFieldInclusaoDesconto.setText("");
        jTextFieldInclusaoCusto.setText("");
        jTextFieldInclusaoEstoque.setText("");
        jTextFieldInclusaoMinima.setText("");
        jTextFieldInclusaoMaxima.setText("");
    //    jCheckBoxInclusaoInativo.setText("");
          
    }

    private void visibilidadePreAlteracao(){
        jTextFieldAlteracaoBarras.setEnabled(false);
        jTextFieldAlteracaoControle.setEnabled(false);
        jTextFieldAlteracaoResumida.setEnabled(false);
        jTextFieldAlteracaoCompleta.setEnabled(false);
        jComboBoxAlteracaoImpressao.setEnabled(false);
        jComboBoxAlteracaoFornecedor.setEnabled(false);
        jComboBoxAlteracaoUnidade.setEnabled(false);
        jTextFieldAlteracaoNcm.setEnabled(false);
        jTextFieldAlteracaoCest.setEnabled(false);
        jTextFieldAlteracaoCustoUnid.setEnabled(false);
        jTextFieldAlteracaoCustoImp.setEnabled(false);
        jTextFieldAlteracaoLucrativa.setEnabled(false);
        jTextFieldAlteracaoValorVenda.setEnabled(false);
        jTextFieldAlteracaoDesconto.setEnabled(false);
        jTextFieldAlteracaoCusto.setEnabled(false);
        jTextFieldAlteracaoEstoque.setEnabled(false);
        jTextFieldAlteracaoMinima.setEnabled(false);
        jTextFieldAlteracaoMaxima.setEnabled(false);
        
        jButtonAlteracaoSalvar.setEnabled(false);
        jCheckBoxAlteracaoInativo.setEnabled(false);
        jTextFieldAlteracaoCodigo.setEnabled(true);
    }

    private void visibilidadePosAlteracao(){
        jTextFieldAlteracaoBarras.setEnabled(true);
        jTextFieldAlteracaoControle.setEnabled(true);
        jTextFieldAlteracaoResumida.setEnabled(true);
        jTextFieldAlteracaoCompleta.setEnabled(true);
        jComboBoxAlteracaoImpressao.setEnabled(true);
        jComboBoxAlteracaoFornecedor.setEnabled(true);
        jComboBoxAlteracaoUnidade.setEnabled(true);
        jTextFieldAlteracaoNcm.setEnabled(true);
        jTextFieldAlteracaoCest.setEnabled(true);
        jTextFieldAlteracaoCustoUnid.setEnabled(true);
        jTextFieldAlteracaoCustoImp.setEnabled(true);
        jTextFieldAlteracaoLucrativa.setEnabled(true);
        jTextFieldAlteracaoValorVenda.setEnabled(true);
        jTextFieldAlteracaoDesconto.setEnabled(true);
        jTextFieldAlteracaoCusto.setEnabled(true);
        jTextFieldAlteracaoEstoque.setEnabled(true);
        jTextFieldAlteracaoMinima.setEnabled(true);
        jTextFieldAlteracaoMaxima.setEnabled(true);
   
        jButtonAlteracaoSalvar.setEnabled(true);
        jCheckBoxAlteracaoInativo.setEnabled(true);
        
     }

    private void visibilidadePreExclusao(){
        jTextFieldExclusaoBarras.setEnabled(false);
        jTextFieldExclusaoControle.setEnabled(false);
        jTextFieldExclusaoResumida.setEnabled(false);
        jTextFieldExclusaoCompleta.setEnabled(false);
        jComboBoxExclusaoImpressao.setEnabled(false);
        jComboBoxExclusaoFornecedor.setEnabled(false);
        jComboBoxExclusaoUnidade.setEnabled(false);
        jTextFieldExclusaoNcm.setEnabled(false);
        jTextFieldExclusaoCest.setEnabled(false);
        jTextFieldExclusaoCustoUnid.setEnabled(false);
        jTextFieldExclusaoCustoImp.setEnabled(false);
        jTextFieldExclusaoLucrativa.setEnabled(false);
        jTextFieldExclusaoValorVenda.setEnabled(false);
        jTextFieldExclusaoDesconto.setEnabled(false);
        jTextFieldExclusaoCusto.setEnabled(false);
        jTextFieldExclusaoEstoque.setEnabled(false);
        jTextFieldExclusaoMinima.setEnabled(false);
        jTextFieldExclusaoMaxima.setEnabled(false);
        jButtonExclusaoExcluir.setEnabled(false);
        jCheckBoxExclusaoInativo.setEnabled(false);
        
        jTextFieldExclusaoCodigo.setText(""); 
        jTextFieldExclusaoBarras.setText("");
        jTextFieldExclusaoControle.setText("");
        jTextFieldExclusaoResumida.setText("");
        jTextFieldExclusaoCompleta.setText("");
        jComboBoxExclusaoFornecedor.setSelectedIndex(0);
        jComboBoxExclusaoUnidade.setSelectedIndex(0);
        jTextFieldExclusaoNcm.setText("");
        jTextFieldExclusaoCest.setText("");
        jTextFieldExclusaoCustoUnid.setText("");
        jTextFieldExclusaoCustoImp.setText("");
        jTextFieldExclusaoLucrativa.setText("");
        jTextFieldExclusaoValorVenda.setText("");
        jTextFieldExclusaoDesconto.setText("");
        jTextFieldExclusaoCusto.setText("");
        jTextFieldExclusaoEstoque.setText("");
        jTextFieldExclusaoMinima.setText("");
        jTextFieldExclusaoMaxima.setText("");
        
        
    }
    
    private void ExibirAlteracao(){
        try{
            int codigoProduto = Integer.parseInt(jTextFieldAlteracaoCodigo.getText());
            FornecedorDAO forDAO = new FornecedorDAO();
            ProdutoDAO prodDAO = new ProdutoDAO();
            boolean achou = false;
            for(Produto p : prodDAO.read()){
                if(codigoProduto == p.getCodigoProduto()){
                    for(Fornecedor f : forDAO.read()){               
                        if(f.getCodigo() == p.getFornecedor().getCodigo()){
                            jComboBoxAlteracaoFornecedor.setSelectedItem(f.getNome());
                        
                            jComboBoxAlteracaoUnidade.setSelectedItem(p.getUnidadeMedida());
                            jTextFieldAlteracaoBarras.setText(String.valueOf(p.getCodigoBarras()));    
                            jTextFieldAlteracaoControle.setText(String.valueOf(p.getControleExtra()));               
                            jTextFieldAlteracaoResumida.setText(p.getResumida());
                            jTextFieldAlteracaoCompleta.setText(p.getCompleta());            
                            jTextFieldAlteracaoNcm.setText(String.valueOf(p.getNcm()));      
                            jTextFieldAlteracaoCest.setText(String.valueOf(p.getCest()));
                            jComboBoxAlteracaoUnidade.setSelectedItem(p.getUnidadeMedida());
                            jTextFieldAlteracaoCustoImp.setText(String.valueOf(p.getCustoSemImposto()));
                            jTextFieldAlteracaoCustoUnid.setText(String.valueOf(p.getCustoUnidade())); 
                            jTextFieldAlteracaoLucrativa.setText(String.valueOf(p.getLucrativo()));       
                            jTextFieldAlteracaoValorVenda.setText(String.valueOf(p.getValorVenda()));         
                            jTextFieldAlteracaoDesconto.setText(String.valueOf(p.getDesconto()));
                            jTextFieldAlteracaoCusto.setText(String.valueOf(p.getPreco()));
                            jTextFieldAlteracaoMinima.setText(String.valueOf(p.getQtdMinima()));
                            jTextFieldAlteracaoMaxima.setText(String.valueOf(p.getQtdMaxima()));
                            jTextFieldAlteracaoEstoque.setText(String.valueOf(p.getQtdEstoque()));
                            jLabelAlteracaoDataInclusao.setText(p.getDataInclusao());
                            jTextFieldAlteracaoCodigo.setEnabled(false);
                            achou = true;
                            visibilidadePosAlteracao();
                        }
                    }
                }
            }
            if(!achou)
                JOptionPane.showMessageDialog(null,"Produto não encontrado"); 
        }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null,"Informe apenas numeros");
            }
    }
    
    private void ExibirExclusao(){
        try{
            int codigoProduto = Integer.parseInt(jTextFieldExclusaoCodigo.getText());
            FornecedorDAO forDAO = new FornecedorDAO();
            ProdutoDAO prodDAO = new ProdutoDAO();
            boolean achou = false;
            for(Produto p : prodDAO.read()){
                if(codigoProduto == p.getCodigoProduto()){
                    for(Fornecedor f :forDAO.read()){
                        if(f.getCodigo() == p.getFornecedor().getCodigo()){
                            jComboBoxExclusaoFornecedor.setSelectedItem(f.getNome());
                        
                            jComboBoxExclusaoUnidade.setSelectedItem(p.getUnidadeMedida());
                            jTextFieldExclusaoBarras.setText(String.valueOf(p.getCodigoBarras()));    
                            jTextFieldExclusaoControle.setText(String.valueOf(p.getControleExtra()));               
                            jTextFieldExclusaoResumida.setText(p.getResumida());
                            jTextFieldExclusaoCompleta.setText(p.getCompleta());            
                            jTextFieldExclusaoNcm.setText(String.valueOf(p.getNcm()));      
                            jTextFieldExclusaoCest.setText(String.valueOf(p.getCest()));
                            jComboBoxExclusaoUnidade.setSelectedItem(p.getUnidadeMedida());
                            jTextFieldExclusaoCustoImp.setText(String.valueOf(p.getCustoSemImposto()));
                            jTextFieldExclusaoCustoUnid.setText(String.valueOf(p.getCustoUnidade())); 
                            jTextFieldExclusaoLucrativa.setText(String.valueOf(p.getLucrativo()));       
                            jTextFieldExclusaoValorVenda.setText(String.valueOf(p.getValorVenda()));         
                            jTextFieldExclusaoDesconto.setText(String.valueOf(p.getDesconto()));
                            jTextFieldExclusaoCusto.setText(String.valueOf(p.getPreco()));
                            jTextFieldExclusaoMinima.setText(String.valueOf(p.getQtdMinima()));
                            jTextFieldExclusaoMaxima.setText(String.valueOf(p.getQtdMaxima()));
                            jTextFieldExclusaoEstoque.setText(String.valueOf(p.getQtdEstoque()));
                            jLabelExclusaoDataInclusao.setText(p.getDataInclusao());
                            achou = true; 
                        }                     
                    }    
                }
            }
            jButtonExclusaoExcluir.setEnabled(achou);
            if(!achou){
                JOptionPane.showMessageDialog(null,"Produto não encontrado");
                visibilidadePreExclusao();
            }
        
        }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null,"Informe apenas numeros");
        }
    }
            
    private void RealizarAlteracao(){
        try{
            int codigo = Integer.parseInt(jTextFieldAlteracaoCodigo.getText());
            int controle = Integer.parseInt(jTextFieldAlteracaoControle.getText());
            int codigoBarras = Integer.parseInt(jTextFieldAlteracaoBarras.getText());
        
            String descResumida = jTextFieldAlteracaoResumida.getText();
            String descCompleta = jTextFieldAlteracaoCompleta.getText();
        
            String fornecedorCampo = String.valueOf(jComboBoxAlteracaoFornecedor.getSelectedItem());
    
            Long ncm = Long.parseLong(jTextFieldAlteracaoNcm.getText());
            Long codigoCest = Long.parseLong(jTextFieldAlteracaoCest.getText());
        
            double qtdMinima = Double.parseDouble(jTextFieldAlteracaoMinima.getText());
            double qtdMaxima = Double.parseDouble(jTextFieldAlteracaoMaxima.getText());
            double estoque = Double.parseDouble(jTextFieldAlteracaoEstoque.getText());
        
            String unidadeDeMedida = String.valueOf(jComboBoxAlteracaoUnidade.getSelectedItem());
            double custoSemImposto = Double.parseDouble(jTextFieldAlteracaoCustoImp.getText());
            double custoUnidade = Double.parseDouble(jTextFieldAlteracaoCustoUnid.getText());
            double valorVenda = Double.parseDouble(jTextFieldAlteracaoValorVenda.getText());
            double desconto = Double.parseDouble(jTextFieldAlteracaoDesconto.getText());
            double preco = Double.parseDouble(jTextFieldAlteracaoCustoUnid.getText());
            double lucrativo = Double.parseDouble(jTextFieldAlteracaoLucrativa.getText());

            FornecedorDAO daoForn = new FornecedorDAO();
            ProdutoDAO daoProd = new ProdutoDAO();
            int idFornece = 0; 
            String nomeFornece = "Default"; 
        
            for(Fornecedor ponteiroFor : daoForn.read()){
                if(fornecedorCampo.equals(ponteiroFor.getNome())){
                    idFornece = ponteiroFor.getCodigo();
                    nomeFornece = ponteiroFor.getNome();
                }
            }
            Fornecedor fornecedor = new Fornecedor(idFornece, nomeFornece);
        
            Produto p = new Produto( codigo,  controle,  codigoBarras,  descResumida,
                        descCompleta,  unidadeDeMedida,  preco,  valorVenda,  custoSemImposto,
                        custoUnidade,  lucrativo, desconto,  fornecedor,  ncm,  codigoCest,
                        qtdMinima,  qtdMaxima,  estoque);
        
            daoProd.update(p);
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(rootPane, "Verifique as informações");
        }
        
    }
    
    private void realizaCalculoAlteracao(int campo){
     try{
            double custoSImp;
            double custoUni;
            double custoFinal = 0;
            double lucrativo;
            double valorVenda;
            double desconto;
            
            if(jTextFieldAlteracaoCustoImp.getText().equals(""))
                custoSImp = 0;
            else
                custoSImp = Double.parseDouble(jTextFieldAlteracaoCustoImp.getText());
            
            if(jTextFieldAlteracaoCustoUnid.getText().equals(""))
                custoUni = 0;
            else
                custoUni = Double.parseDouble(jTextFieldAlteracaoCustoUnid.getText());
            
            if(jTextFieldAlteracaoCusto.getText().equals(""))
                custoFinal = 0;
            else
                Double.parseDouble(jTextFieldAlteracaoCusto.getText());
            
            if(jTextFieldAlteracaoLucrativa.getText().equals(""))
                lucrativo = 0;
            else
                lucrativo = Double.parseDouble(jTextFieldAlteracaoLucrativa.getText());
            
            if(jTextFieldAlteracaoValorVenda.getText().equals(""))
                valorVenda = 0;
            else
                valorVenda = Double.parseDouble(jTextFieldAlteracaoValorVenda.getText());
            
            if(jTextFieldAlteracaoDesconto.getText().equals(""))
                desconto = 0;
            
            else
                desconto = Double.parseDouble(jTextFieldAlteracaoDesconto.getText());

            /*
                1 - Lucrativo
                2 - Valor Venda
                3 - Desconto
                4 - Custo
            */
            
            switch(campo){
                case 1:
                    if(custoUni != 0)
                        valorVenda = custoUni + custoUni * (lucrativo/100);
                        custoFinal = valorVenda;
                        desconto = 0;
                        
                    break;
                case 2:
                    double diferenca;
                    if(custoUni != 0){
                        diferenca = valorVenda - custoUni;
                        lucrativo = (diferenca * 100)/ custoUni;
                        custoFinal = valorVenda;
                        desconto = 0;        
                    }
                    break;
                case 3:
                    custoFinal = valorVenda - valorVenda * (desconto/100);
                    break;
                case 4:
                    if(custoUni != 0){
                        double diferencaDesc = custoFinal - custoUni;
                        lucrativo = (diferencaDesc * 100)/ custoUni;
                        valorVenda = custoFinal - desconto;
                    }
                    
                    break;
            }
            
            if(campo!= 1)
                if(lucrativo > 0){
                    jTextFieldAlteracaoLucrativa.setText(String.valueOf(lucrativo));
                    jTextFieldAlteracaoLucrativa.setText(limitarTamanhoCampo(jTextFieldAlteracaoLucrativa.getText()));
                }
                else
                    jTextFieldAlteracaoLucrativa.setText(String.valueOf(0));
            if(campo!= 2)
                if(valorVenda > 0){
                    jTextFieldAlteracaoValorVenda.setText(String.valueOf(valorVenda));
                    jTextFieldAlteracaoValorVenda.setText(limitarTamanhoCampo(jTextFieldAlteracaoValorVenda.getText()));
                }
                else
                    jTextFieldAlteracaoValorVenda.setText(String.valueOf(0));
            if(campo!= 4)
                if(valorVenda > 0){
                    jTextFieldAlteracaoCusto.setText(String.valueOf(custoFinal));
                    jTextFieldAlteracaoCusto.setText(limitarTamanhoCampo(jTextFieldAlteracaoCusto.getText()));
                }
                else
                    jTextFieldAlteracaoCusto.setText(String.valueOf(0));
            
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(rootPane, "Informar apenas números");
        }    
    
    }
    
    private void RealizarInclusao(){  
        try{
            double desconto;
            int codigo = Integer.parseInt(jTextFieldInclusaoCodigo.getText());
            int controle = Integer.parseInt(jTextFieldInclusaoControle.getText());
            int codigoBarras = Integer.parseInt(jTextFieldInclusaoBarras.getText());
        
            String descResumida = jTextFieldInclusaoResumida.getText();
            String descCompleta = jTextFieldInclusaoCompleta.getText();
        
            String fornecedorCampo = String.valueOf(jComboBoxInsersaoFornecedor.getSelectedItem());
    
            Long ncm = Long.parseLong(jTextFieldInclusaoNcm.getText());
            Long codigoCest = Long.parseLong(jTextFieldInclusaoCest.getText());
        
            double qtdMinima = Double.parseDouble(jTextFieldInclusaoMinima.getText());
            double qtdMaxima = Double.parseDouble(jTextFieldInclusaoMaxima.getText());
            double estoque = Double.parseDouble(jTextFieldInclusaoEstoque.getText());
        
            String unidadeDeMedida = String.valueOf(jComboBoxInclusaoUnidade.getSelectedItem());
            double custoSemImposto = Double.parseDouble(jTextFieldInclusaoCustoImp.getText());
            double custoUnidade = Double.parseDouble(jTextFieldInclusaoCustoUnid.getText());
            double valorVenda = Double.parseDouble(jTextFieldInclusaoValorVenda.getText());
            if(!(jTextFieldInclusaoDesconto.getText().equals("")))
                 desconto = Double.parseDouble(jTextFieldInclusaoDesconto.getText());
            else
                desconto = 0;
            double preco = Double.parseDouble(jTextFieldInclusaoCustoUnid.getText());
            double lucrativo = Double.parseDouble(jTextFieldInclusaoLucrativa.getText());

            FornecedorDAO daoForn = new FornecedorDAO();
            ProdutoDAO daoProd = new ProdutoDAO();
            int idFornece = 0; 
            String nomeFornece = "Default"; 
        
            for(Fornecedor ponteiroFor : daoForn.read()){
                if(fornecedorCampo.equals(ponteiroFor.getNome())){
                    idFornece = ponteiroFor.getCodigo();
                    nomeFornece = ponteiroFor.getNome();
                }
            }
            Fornecedor fornecedor = new Fornecedor(idFornece, nomeFornece);
        
            Produto p = new Produto( codigo,  controle,  codigoBarras,  descResumida,
                        descCompleta,  unidadeDeMedida,  preco,  valorVenda,  custoSemImposto,
                        custoUnidade,  lucrativo, desconto,  fornecedor,  ncm,  codigoCest,
                        qtdMinima,  qtdMaxima,  estoque);
        
            daoProd.create(p);
            visibilidadePreInsercao();
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(null,"Verifique as informações");
             jTextFieldAlteracaoCusto.setText("");
             jTextFieldAlteracaoLucrativa.setText("");
             jTextFieldAlteracaoValorVenda.setText("");
             jTextFieldAlteracaoCustoImp.setText("");
             jTextFieldAlteracaoDesconto.setText("");
             jTextFieldAlteracaoCustoUnid.setText("");
        }
    }
    
    private void excluirItem(int codigoInformado){
       
        
        String[] opcao = {"Sim", "Não"}; 
        
        int escolha = JOptionPane.showOptionDialog(rootPane, "Tem certeza que quer excluir o produto de codigo "+
                codigoInformado+" ?",
                                     "Escolha um opção", WIDTH, HEIGHT, null, opcao, opcao[0]);
        
        if(escolha == 0){
            ProdutoDAO dao = new ProdutoDAO();
            dao.delete(codigoInformado);
            visibilidadePreExclusao();
        }
        
    
    }
   
    private void realizaCalculoInsercao(int campo){
        try{
            double custoSImp;
            double custoUni;
            double custoFinal = 0;
            double lucrativo;
            double valorVenda;
            double desconto;
            
            if(jTextFieldInclusaoCustoImp.getText().equals(""))
                custoSImp = 0;
            else
                custoSImp = Double.parseDouble(jTextFieldInclusaoCustoImp.getText());
            
            if(jTextFieldInclusaoCustoUnid.getText().equals(""))
                custoUni = 0;
            else
                custoUni = Double.parseDouble(jTextFieldInclusaoCustoUnid.getText());
            
            if(jTextFieldInclusaoCusto.getText().equals(""))
                custoFinal = 0;
            else
                Double.parseDouble(jTextFieldInclusaoCusto.getText());
            
            if(jTextFieldInclusaoLucrativa.getText().equals(""))
                lucrativo = 0;
            else
                lucrativo = Double.parseDouble(jTextFieldInclusaoLucrativa.getText());
            
            if(jTextFieldInclusaoValorVenda.getText().equals(""))
                valorVenda = 0;
            else
                valorVenda = Double.parseDouble(jTextFieldInclusaoValorVenda.getText());
            
            if(jTextFieldInclusaoDesconto.getText().equals(""))
                desconto = 0;
            
            else
                desconto = Double.parseDouble(jTextFieldInclusaoDesconto.getText());
            
           
            /*
                1 - Lucrativo
                2 - Valor Venda
                3 - Desconto
                4 - Custo
            */
            
            switch(campo){
                case 1:
                    if(custoUni != 0)
                        valorVenda = custoUni + custoUni * (lucrativo/100);
                        custoFinal = valorVenda;
                        desconto = 0;
                        
                    break;
                case 2:
                    double diferenca;
                    if(custoUni != 0){
                        diferenca = valorVenda - custoUni;
                        lucrativo = (diferenca * 100)/ custoUni;
                        custoFinal = valorVenda;
                        desconto = 0;        
                    }
                    break;
                case 3:
                    custoFinal = valorVenda - valorVenda * (desconto/100);
                    break;
                case 4:
                    if(custoUni != 0){
                        double diferencaDesc = custoFinal - custoUni;
                        lucrativo = (diferencaDesc * 100)/ custoUni;
                        valorVenda = custoFinal - desconto;
                    }
                    
                    break;
            }
            
            if(campo!= 1)
                if(lucrativo > 0){
                    jTextFieldInclusaoLucrativa.setText(String.valueOf(lucrativo));
                    jTextFieldInclusaoLucrativa.setText(limitarTamanhoCampo(jTextFieldInclusaoLucrativa.getText()));
                }
                    
                else
                    jTextFieldInclusaoLucrativa.setText(String.valueOf(0));
            if(campo!= 2)
                if(valorVenda > 0){
                    jTextFieldInclusaoValorVenda.setText(String.valueOf(valorVenda));
                    jTextFieldInclusaoValorVenda.setText(limitarTamanhoCampo(jTextFieldInclusaoValorVenda.getText()));
                }
                else
                    jTextFieldInclusaoValorVenda.setText(String.valueOf(0));
            if(campo!= 4)
                if(valorVenda > 0){
                    jTextFieldInclusaoCusto.setText(String.valueOf(custoFinal));
                    jTextFieldInclusaoCusto.setText(limitarTamanhoCampo(jTextFieldInclusaoCusto.getText()));
                }
                else
                    jTextFieldInclusaoCusto.setText(String.valueOf(0));
            
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(rootPane, "Informar apenas números");
             jTextFieldInclusaoCusto.setText("");
             jTextFieldInclusaoLucrativa.setText("");
             jTextFieldInclusaoValorVenda.setText("");
             jTextFieldInclusaoCustoImp.setText("");
             jTextFieldInclusaoDesconto.setText("");
             jTextFieldInclusaoCustoUnid.setText("");
        }    
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabelInclusaoTituloCodigo = new javax.swing.JLabel();
        jTextFieldInclusaoCodigo = new javax.swing.JTextField();
        jLabelInclusaoControle = new javax.swing.JLabel();
        jTextFieldInclusaoControle = new javax.swing.JTextField();
        jLabelInclusaoBarras = new javax.swing.JLabel();
        jTextFieldInclusaoBarras = new javax.swing.JTextField();
        jCheckBoxInclusaoInativo = new javax.swing.JCheckBox();
        jLabelInclusaoDescricao = new javax.swing.JLabel();
        jLabelInclusaoResumida = new javax.swing.JLabel();
        jLabelInclusaoCompleta = new javax.swing.JLabel();
        jTextFieldInclusaoCompleta = new javax.swing.JTextField();
        jTextFieldInclusaoResumida = new javax.swing.JTextField();
        jButtonInclusaoNaoGerar = new javax.swing.JButton();
        jLabelInclusaoImpressao = new javax.swing.JLabel();
        jLabelInclusaoData = new javax.swing.JLabel();
        jLabelInclusaoDataInclusao = new javax.swing.JLabel();
        jLabelInclusaoFornecedor = new javax.swing.JLabel();
        jComboBoxInsersaoFornecedor = new javax.swing.JComboBox<>();
        jTextFieldInclusaoNcm = new javax.swing.JTextField();
        jLabelInclusaoNcm = new javax.swing.JLabel();
        jLabelInclusaoCest = new javax.swing.JLabel();
        jTextFieldInclusaoCest = new javax.swing.JTextField();
        jLabelInclusaoPesquisaNcm = new javax.swing.JLabel();
        jLabelInclusaoPesquisaCest = new javax.swing.JLabel();
        jButtonInclusaoHistorico = new javax.swing.JButton();
        jLabelInclusaoQtdEstoque = new javax.swing.JLabel();
        jLabelInclusaoMinima = new javax.swing.JLabel();
        jLabelInclusaoMaxima = new javax.swing.JLabel();
        jLabelInclusaoEmEstoque = new javax.swing.JLabel();
        jTextFieldInclusaoEstoque = new javax.swing.JTextField();
        jTextFieldInclusaoMaxima = new javax.swing.JTextField();
        jLabelInsercaoUn2 = new javax.swing.JLabel();
        jLabelInsercaoUn = new javax.swing.JLabel();
        jLabelInsercaoUn3 = new javax.swing.JLabel();
        jTextFieldInclusaoMinima = new javax.swing.JTextField();
        jLabelInclusaoVarejo = new javax.swing.JLabel();
        jComboBoxInclusaoUnidade = new javax.swing.JComboBox<>();
        jLabelInclusaoUnidade = new javax.swing.JLabel();
        jLabelInclusaoCustoImp = new javax.swing.JLabel();
        jTextFieldInclusaoCustoImp = new javax.swing.JTextField();
        jLabelInclusaoCustoUnid = new javax.swing.JLabel();
        jTextFieldInclusaoCustoUnid = new javax.swing.JTextField();
        jLabelInclusaoLucrativa = new javax.swing.JLabel();
        jTextFieldInclusaoLucrativa = new javax.swing.JTextField();
        jLabelInclusaoValorVenda = new javax.swing.JLabel();
        jTextFieldInclusaoValorVenda = new javax.swing.JTextField();
        jLabelInclusaoDesconto = new javax.swing.JLabel();
        jTextFieldInclusaoDesconto = new javax.swing.JTextField();
        jLabelInclusaoCusto = new javax.swing.JLabel();
        jTextFieldInclusaoCusto = new javax.swing.JTextField();
        jButtonInclusaoSalvar = new javax.swing.JButton();
        jButtonInclusaoSair = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabelAlteracaoTituloCodigo = new javax.swing.JLabel();
        jTextFieldAlteracaoCodigo = new javax.swing.JTextField();
        jLabelAlteracaoControle = new javax.swing.JLabel();
        jTextFieldAlteracaoControle = new javax.swing.JTextField();
        jLabelAlteracaoBarras = new javax.swing.JLabel();
        jTextFieldAlteracaoBarras = new javax.swing.JTextField();
        jCheckBoxAlteracaoInativo = new javax.swing.JCheckBox();
        jLabelAlteracaoDescricao = new javax.swing.JLabel();
        jLabelAlteracaoResumida = new javax.swing.JLabel();
        jLabelAlteracaoCompleta = new javax.swing.JLabel();
        jTextFieldAlteracaoCompleta = new javax.swing.JTextField();
        jTextFieldAlteracaoResumida = new javax.swing.JTextField();
        jLabelAlteracaoImpressao = new javax.swing.JLabel();
        jLabelAlteracaoData = new javax.swing.JLabel();
        jLabelAlteracaoDataInclusao = new javax.swing.JLabel();
        jLabelAlteracaoFornecedor = new javax.swing.JLabel();
        jComboBoxAlteracaoFornecedor = new javax.swing.JComboBox<>();
        jTextFieldAlteracaoNcm = new javax.swing.JTextField();
        jLabelAlteracaoNcm = new javax.swing.JLabel();
        jLabelAlteracaoCest = new javax.swing.JLabel();
        jTextFieldAlteracaoCest = new javax.swing.JTextField();
        jLabeAlteracaoPesquisaNcmm = new javax.swing.JLabel();
        jLabelAlteracaoPesquisaCest = new javax.swing.JLabel();
        jButtonAlteracaoHistorico = new javax.swing.JButton();
        jLabelAlteracaoQtdEstoque = new javax.swing.JLabel();
        jLabelAlteracaoMinima = new javax.swing.JLabel();
        jLabelAlteracaoMaxima = new javax.swing.JLabel();
        jLabelAlteracaoEmEstoque = new javax.swing.JLabel();
        jTextFieldAlteracaoEstoque = new javax.swing.JTextField();
        jTextFieldAlteracaoMaxima = new javax.swing.JTextField();
        jLabelAlteracaoUn2 = new javax.swing.JLabel();
        jLabelInsercaoUn1 = new javax.swing.JLabel();
        jLabelAlteracaoUn = new javax.swing.JLabel();
        jTextFieldAlteracaoMinima = new javax.swing.JTextField();
        jLabelAlteracaoVarejo = new javax.swing.JLabel();
        jComboBoxAlteracaoUnidade = new javax.swing.JComboBox<>();
        jLabelAlteracaoUnidade = new javax.swing.JLabel();
        jLabelAlteracaoCustoImp = new javax.swing.JLabel();
        jTextFieldAlteracaoCustoImp = new javax.swing.JTextField();
        jLabelAlteracaoCustoUnid = new javax.swing.JLabel();
        jTextFieldAlteracaoCustoUnid = new javax.swing.JTextField();
        jLabelAlteracaoLucrativa = new javax.swing.JLabel();
        jTextFieldAlteracaoLucrativa = new javax.swing.JTextField();
        jLabelAlteracaoValorVenda = new javax.swing.JLabel();
        jTextFieldAlteracaoValorVenda = new javax.swing.JTextField();
        jLabelAlteracaoDesconto = new javax.swing.JLabel();
        jTextFieldAlteracaoDesconto = new javax.swing.JTextField();
        jLabelAlteracaoCusto = new javax.swing.JLabel();
        jTextFieldAlteracaoCusto = new javax.swing.JTextField();
        jButtonAlteracaoSalvar = new javax.swing.JButton();
        jButtonAlteracaoSair = new javax.swing.JButton();
        jLabelAlteracaoPesquisaNcm = new javax.swing.JLabel();
        jComboBoxAlteracaoImpressao = new javax.swing.JComboBox<>();
        jPanel8 = new javax.swing.JPanel();
        jLabelExclusaoTituloCodigo = new javax.swing.JLabel();
        jTextFieldExclusaoCodigo = new javax.swing.JTextField();
        jLabelExclusaoControle = new javax.swing.JLabel();
        jTextFieldExclusaoControle = new javax.swing.JTextField();
        jLabelExclusaoBarras = new javax.swing.JLabel();
        jTextFieldExclusaoBarras = new javax.swing.JTextField();
        jCheckBoxExclusaoInativo = new javax.swing.JCheckBox();
        jLabelExclusaoDescricao = new javax.swing.JLabel();
        jLabelExclusaoResumida = new javax.swing.JLabel();
        jLabelExclusaoCompleta = new javax.swing.JLabel();
        jTextFieldExclusaoCompleta = new javax.swing.JTextField();
        jTextFieldExclusaoResumida = new javax.swing.JTextField();
        jLabelExclusaoImpressao = new javax.swing.JLabel();
        jLabelExclusaoData = new javax.swing.JLabel();
        jLabelExclusaoDataInclusao = new javax.swing.JLabel();
        jLabelExclusaoFornecedor = new javax.swing.JLabel();
        jComboBoxExclusaoFornecedor = new javax.swing.JComboBox<>();
        jTextFieldExclusaoNcm = new javax.swing.JTextField();
        jLabelExclusaoNcm = new javax.swing.JLabel();
        jLabelExclusaoCest = new javax.swing.JLabel();
        jTextFieldExclusaoCest = new javax.swing.JTextField();
        jLabelExclusaoPesquisaNcm = new javax.swing.JLabel();
        jLabelExclusaoPesquisaCest = new javax.swing.JLabel();
        jButtonExclusaoHistorico = new javax.swing.JButton();
        jLabelExclusaoQtdEstoque = new javax.swing.JLabel();
        jLabelExclusaoMinima = new javax.swing.JLabel();
        jLabelExclusaoMaxima = new javax.swing.JLabel();
        jLabelExclusaoEmEstoque = new javax.swing.JLabel();
        jTextFieldExclusaoEstoque = new javax.swing.JTextField();
        jTextFieldExclusaoMaxima = new javax.swing.JTextField();
        jLabelExclusaoUn2 = new javax.swing.JLabel();
        jLabelExclusaoUn3 = new javax.swing.JLabel();
        jLabelExclusaoUn = new javax.swing.JLabel();
        jTextFieldExclusaoMinima = new javax.swing.JTextField();
        jLabelExclusaoVarejo = new javax.swing.JLabel();
        jComboBoxExclusaoUnidade = new javax.swing.JComboBox<>();
        jLabelExclusaoUnidade = new javax.swing.JLabel();
        jLabelExclusaoCustoImp = new javax.swing.JLabel();
        jTextFieldExclusaoCustoImp = new javax.swing.JTextField();
        jLabelExclusaoCustoUnid = new javax.swing.JLabel();
        jTextFieldExclusaoCustoUnid = new javax.swing.JTextField();
        jLabelExclusaoLucrativa = new javax.swing.JLabel();
        jTextFieldExclusaoLucrativa = new javax.swing.JTextField();
        jLabelInclusaoValorVenda2 = new javax.swing.JLabel();
        jTextFieldExclusaoValorVenda = new javax.swing.JTextField();
        jLabelInclusaoDesconto2 = new javax.swing.JLabel();
        jTextFieldExclusaoDesconto = new javax.swing.JTextField();
        jLabelExclusaoCusto = new javax.swing.JLabel();
        jTextFieldExclusaoCusto = new javax.swing.JTextField();
        jButtonExclusaoExcluir = new javax.swing.JButton();
        jButtonExclusaoSair = new javax.swing.JButton();
        jLabelExclusaoPesquisa = new javax.swing.JLabel();
        jComboBoxExclusaoImpressao = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jLabelF5 = new javax.swing.JLabel();
        jLabelF12 = new javax.swing.JLabel();
        jLabelF1 = new javax.swing.JLabel();
        jLabelF2 = new javax.swing.JLabel();
        jLabelLogoBalloon = new javax.swing.JLabel();
        jLabelBalloon = new javax.swing.JLabel();
        jLabelF6 = new javax.swing.JLabel();
        jLabelF7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconsContabilidade_80px.png"))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(0, 153, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 46, Short.MAX_VALUE)
        );

        jLabel38.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 153, 255));
        jLabel38.setText("Manutenção de Estoque");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel38)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jLabel1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N

        jLabelInclusaoTituloCodigo.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelInclusaoTituloCodigo.setText("Código");
        jLabelInclusaoTituloCodigo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelInclusaoTituloCodigoMouseClicked(evt);
            }
        });

        jTextFieldInclusaoCodigo.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldInclusaoCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldInclusaoCodigoKeyPressed(evt);
            }
        });

        jLabelInclusaoControle.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelInclusaoControle.setText("Controle (Extra)");
        jLabelInclusaoControle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelInclusaoControleMouseClicked(evt);
            }
        });

        jTextFieldInclusaoControle.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldInclusaoControle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldInclusaoControleKeyPressed(evt);
            }
        });

        jLabelInclusaoBarras.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelInclusaoBarras.setText("Código de Barras");
        jLabelInclusaoBarras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelInclusaoBarrasMouseClicked(evt);
            }
        });

        jTextFieldInclusaoBarras.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldInclusaoBarras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldInclusaoBarrasActionPerformed(evt);
            }
        });
        jTextFieldInclusaoBarras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldInclusaoBarrasKeyPressed(evt);
            }
        });

        jCheckBoxInclusaoInativo.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jCheckBoxInclusaoInativo.setForeground(new java.awt.Color(255, 0, 0));
        jCheckBoxInclusaoInativo.setText("Inativo");

        jLabelInclusaoDescricao.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabelInclusaoDescricao.setText("Descrição");

        jLabelInclusaoResumida.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelInclusaoResumida.setText("Resumida");
        jLabelInclusaoResumida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelInclusaoResumidaMouseClicked(evt);
            }
        });

        jLabelInclusaoCompleta.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelInclusaoCompleta.setText("Completa");
        jLabelInclusaoCompleta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelInclusaoCompletaMouseClicked(evt);
            }
        });

        jTextFieldInclusaoCompleta.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldInclusaoCompleta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldInclusaoCompletaMouseClicked(evt);
            }
        });
        jTextFieldInclusaoCompleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldInclusaoCompletaActionPerformed(evt);
            }
        });
        jTextFieldInclusaoCompleta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldInclusaoCompletaKeyPressed(evt);
            }
        });

        jTextFieldInclusaoResumida.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldInclusaoResumida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldInclusaoResumidaKeyPressed(evt);
            }
        });

        jButtonInclusaoNaoGerar.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jButtonInclusaoNaoGerar.setForeground(new java.awt.Color(0, 153, 255));
        jButtonInclusaoNaoGerar.setText("<<Não Gerar Impressão>>");

        jLabelInclusaoImpressao.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelInclusaoImpressao.setText("Setor de produção/Impressão");

        jLabelInclusaoData.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabelInclusaoData.setText("Inclusão");

        jLabelInclusaoDataInclusao.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabelInclusaoDataInclusao.setForeground(new java.awt.Color(255, 0, 0));
        jLabelInclusaoDataInclusao.setText("26/06/2018");

        jLabelInclusaoFornecedor.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelInclusaoFornecedor.setText("Fornecedor");
        jLabelInclusaoFornecedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelInclusaoFornecedorMouseClicked(evt);
            }
        });

        jComboBoxInsersaoFornecedor.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jComboBoxInsersaoFornecedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Default" }));
        jComboBoxInsersaoFornecedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBoxInsersaoFornecedorKeyPressed(evt);
            }
        });

        jTextFieldInclusaoNcm.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldInclusaoNcm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldInclusaoNcmActionPerformed(evt);
            }
        });
        jTextFieldInclusaoNcm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldInclusaoNcmKeyPressed(evt);
            }
        });

        jLabelInclusaoNcm.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelInclusaoNcm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconsSoma_16px.png"))); // NOI18N
        jLabelInclusaoNcm.setText("NCM");
        jLabelInclusaoNcm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelInclusaoNcmMouseClicked(evt);
            }
        });

        jLabelInclusaoCest.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelInclusaoCest.setText("Código CEST");
        jLabelInclusaoCest.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelInclusaoCestMouseClicked(evt);
            }
        });

        jTextFieldInclusaoCest.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldInclusaoCest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldInclusaoCestActionPerformed(evt);
            }
        });
        jTextFieldInclusaoCest.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldInclusaoCestKeyPressed(evt);
            }
        });

        jLabelInclusaoPesquisaNcm.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelInclusaoPesquisaNcm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconSeach.png"))); // NOI18N

        jLabelInclusaoPesquisaCest.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelInclusaoPesquisaCest.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconSeach.png"))); // NOI18N

        jButtonInclusaoHistorico.setBackground(new java.awt.Color(0, 153, 255));
        jButtonInclusaoHistorico.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jButtonInclusaoHistorico.setForeground(new java.awt.Color(0, 153, 255));
        jButtonInclusaoHistorico.setText("Histórico De Manutenção");
        jButtonInclusaoHistorico.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonInclusaoHistorico.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);

        jLabelInclusaoQtdEstoque.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabelInclusaoQtdEstoque.setText("Quantidade em Estoque");

        jLabelInclusaoMinima.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelInclusaoMinima.setText("Quantidade Mínima");
        jLabelInclusaoMinima.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelInclusaoMinimaMouseClicked(evt);
            }
        });
        jLabelInclusaoMinima.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLabelInclusaoMinimaKeyPressed(evt);
            }
        });

        jLabelInclusaoMaxima.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelInclusaoMaxima.setText("Quantidade Máxima");
        jLabelInclusaoMaxima.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelInclusaoMaximaMouseClicked(evt);
            }
        });

        jLabelInclusaoEmEstoque.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabelInclusaoEmEstoque.setText("Quantidade Estoque");
        jLabelInclusaoEmEstoque.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelInclusaoEmEstoqueMouseClicked(evt);
            }
        });

        jTextFieldInclusaoEstoque.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jTextFieldInclusaoEstoque.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldInclusaoEstoqueKeyPressed(evt);
            }
        });

        jTextFieldInclusaoMaxima.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldInclusaoMaxima.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldInclusaoMaximaKeyPressed(evt);
            }
        });

        jLabelInsercaoUn2.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelInsercaoUn2.setText("UN");

        jLabelInsercaoUn.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabelInsercaoUn.setText("UN");

        jLabelInsercaoUn3.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelInsercaoUn3.setText("UN");

        jTextFieldInclusaoMinima.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldInclusaoMinima.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldInclusaoMinimaKeyPressed(evt);
            }
        });

        jLabelInclusaoVarejo.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelInclusaoVarejo.setText("Varejo");

        jComboBoxInclusaoUnidade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "UN", "KILO", "KILOGRAMAS" }));
        jComboBoxInclusaoUnidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBoxInclusaoUnidadeKeyPressed(evt);
            }
        });

        jLabelInclusaoUnidade.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelInclusaoUnidade.setText("Unidade");
        jLabelInclusaoUnidade.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelInclusaoUnidadeMouseClicked(evt);
            }
        });

        jLabelInclusaoCustoImp.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelInclusaoCustoImp.setText("Custo S/ Imp.");
        jLabelInclusaoCustoImp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelInclusaoCustoImpMouseClicked(evt);
            }
        });

        jTextFieldInclusaoCustoImp.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldInclusaoCustoImp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldInclusaoCustoImpKeyPressed(evt);
            }
        });

        jLabelInclusaoCustoUnid.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelInclusaoCustoUnid.setText("Custo Unid. $");
        jLabelInclusaoCustoUnid.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelInclusaoCustoUnidMouseClicked(evt);
            }
        });

        jTextFieldInclusaoCustoUnid.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldInclusaoCustoUnid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldInclusaoCustoUnidKeyPressed(evt);
            }
        });

        jLabelInclusaoLucrativa.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelInclusaoLucrativa.setText("Lucrativ. %");
        jLabelInclusaoLucrativa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelInclusaoLucrativaMouseClicked(evt);
            }
        });

        jTextFieldInclusaoLucrativa.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldInclusaoLucrativa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldInclusaoLucrativaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldInclusaoLucrativaKeyReleased(evt);
            }
        });

        jLabelInclusaoValorVenda.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelInclusaoValorVenda.setText("Valor Venda $");
        jLabelInclusaoValorVenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelInclusaoValorVendaMouseClicked(evt);
            }
        });

        jTextFieldInclusaoValorVenda.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldInclusaoValorVenda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldInclusaoValorVendaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldInclusaoValorVendaKeyReleased(evt);
            }
        });

        jLabelInclusaoDesconto.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelInclusaoDesconto.setText("Desconto %");
        jLabelInclusaoDesconto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelInclusaoDescontoMouseClicked(evt);
            }
        });

        jTextFieldInclusaoDesconto.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldInclusaoDesconto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldInclusaoDescontoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldInclusaoDescontoKeyReleased(evt);
            }
        });

        jLabelInclusaoCusto.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelInclusaoCusto.setText("Custo");
        jLabelInclusaoCusto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelInclusaoCustoMouseClicked(evt);
            }
        });

        jTextFieldInclusaoCusto.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldInclusaoCusto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldInclusaoCustoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldInclusaoCustoKeyReleased(evt);
            }
        });

        jButtonInclusaoSalvar.setBackground(new java.awt.Color(0, 153, 255));
        jButtonInclusaoSalvar.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jButtonInclusaoSalvar.setForeground(new java.awt.Color(0, 153, 255));
        jButtonInclusaoSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconsSalvar_40px.png"))); // NOI18N
        jButtonInclusaoSalvar.setText("Gravar");
        jButtonInclusaoSalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonInclusaoSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInclusaoSalvarActionPerformed(evt);
            }
        });

        jButtonInclusaoSair.setBackground(new java.awt.Color(0, 153, 255));
        jButtonInclusaoSair.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jButtonInclusaoSair.setForeground(new java.awt.Color(0, 153, 255));
        jButtonInclusaoSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconsSair_40px.png"))); // NOI18N
        jButtonInclusaoSair.setText("Sair");
        jButtonInclusaoSair.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabelInclusaoTituloCodigo)
                                .addGap(17, 17, 17)
                                .addComponent(jTextFieldInclusaoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(71, 71, 71)
                                .addComponent(jCheckBoxInclusaoInativo)
                                .addGap(29, 29, 29)
                                .addComponent(jLabelInclusaoDescricao))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabelInclusaoControle)
                                .addGap(9, 9, 9)
                                .addComponent(jTextFieldInclusaoControle, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jLabelInclusaoResumida)
                                .addGap(20, 20, 20)
                                .addComponent(jTextFieldInclusaoResumida, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabelInclusaoBarras)
                                .addGap(5, 5, 5)
                                .addComponent(jTextFieldInclusaoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jLabelInclusaoCompleta)
                                .addGap(21, 21, 21)
                                .addComponent(jTextFieldInclusaoCompleta, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabelInclusaoData)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelInclusaoDataInclusao))
                            .addComponent(jLabelInclusaoImpressao)
                            .addComponent(jButtonInclusaoNaoGerar, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(100, 100, 100))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabelInclusaoMinima)
                                .addGap(17, 17, 17)
                                .addComponent(jTextFieldInclusaoMinima, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(jLabelInsercaoUn3)
                                .addGap(139, 139, 139)
                                .addComponent(jLabelInclusaoUnidade)
                                .addGap(22, 22, 22)
                                .addComponent(jLabelInclusaoCustoImp, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelInclusaoCustoUnid, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(jLabelInclusaoLucrativa)
                                .addGap(48, 48, 48)
                                .addComponent(jLabelInclusaoValorVenda)
                                .addGap(29, 29, 29)
                                .addComponent(jLabelInclusaoDesconto)
                                .addGap(44, 44, 44)
                                .addComponent(jLabelInclusaoCusto))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabelInclusaoMaxima)
                                .addGap(15, 15, 15)
                                .addComponent(jTextFieldInclusaoMaxima, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(jLabelInsercaoUn2)
                                .addGap(79, 79, 79)
                                .addComponent(jLabelInclusaoVarejo)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxInclusaoUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldInclusaoCustoImp, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldInclusaoCustoUnid, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldInclusaoLucrativa, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldInclusaoValorVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldInclusaoDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldInclusaoCusto, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabelInclusaoEmEstoque)
                                .addGap(10, 10, 10)
                                .addComponent(jTextFieldInclusaoEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(jLabelInsercaoUn)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButtonInclusaoSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonInclusaoSair, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabelInclusaoQtdEstoque)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelInclusaoFornecedor)
                                    .addComponent(jComboBoxInsersaoFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(82, 82, 82)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelInclusaoNcm)
                                    .addComponent(jTextFieldInclusaoNcm, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(8, 8, 8)
                                .addComponent(jLabelInclusaoPesquisaNcm)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelInclusaoCest)
                                    .addComponent(jTextFieldInclusaoCest, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(4, 4, 4)
                                .addComponent(jLabelInclusaoPesquisaCest)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonInclusaoHistorico)))
                        .addGap(180, 180, 180))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelInclusaoData)
                            .addComponent(jLabelInclusaoDataInclusao))
                        .addGap(13, 13, 13)
                        .addComponent(jLabelInclusaoImpressao)
                        .addGap(11, 11, 11)
                        .addComponent(jButtonInclusaoNaoGerar))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelInclusaoTituloCodigo)
                            .addComponent(jTextFieldInclusaoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBoxInclusaoInativo)
                            .addComponent(jLabelInclusaoDescricao))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelInclusaoControle)
                            .addComponent(jTextFieldInclusaoControle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelInclusaoResumida)
                            .addComponent(jTextFieldInclusaoResumida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelInclusaoBarras)
                            .addComponent(jTextFieldInclusaoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelInclusaoCompleta)
                            .addComponent(jTextFieldInclusaoCompleta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabelInclusaoFornecedor)
                        .addGap(12, 12, 12)
                        .addComponent(jComboBoxInsersaoFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabelInclusaoNcm)
                        .addGap(6, 6, 6)
                        .addComponent(jTextFieldInclusaoNcm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabelInclusaoPesquisaNcm))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabelInclusaoCest)
                        .addGap(6, 6, 6)
                        .addComponent(jTextFieldInclusaoCest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabelInclusaoPesquisaCest))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jButtonInclusaoHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addComponent(jLabelInclusaoQtdEstoque)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelInclusaoMinima)
                    .addComponent(jTextFieldInclusaoMinima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelInsercaoUn3)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabelInclusaoUnidade))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabelInclusaoCustoImp))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabelInclusaoCustoUnid))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabelInclusaoLucrativa))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabelInclusaoValorVenda))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabelInclusaoDesconto))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabelInclusaoCusto)))
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelInclusaoMaxima)
                    .addComponent(jTextFieldInclusaoMaxima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelInsercaoUn2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabelInclusaoVarejo))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jComboBoxInclusaoUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jTextFieldInclusaoCustoImp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jTextFieldInclusaoCustoUnid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jTextFieldInclusaoLucrativa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jTextFieldInclusaoValorVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jTextFieldInclusaoDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jTextFieldInclusaoCusto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelInclusaoEmEstoque)
                    .addComponent(jTextFieldInclusaoEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelInsercaoUn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonInclusaoSalvar)
                    .addComponent(jButtonInclusaoSair))
                .addContainerGap())
        );

        jTabbedPane1.addTab("INCLUSÃO", jPanel3);

        jLabelAlteracaoTituloCodigo.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelAlteracaoTituloCodigo.setText("Código");
        jLabelAlteracaoTituloCodigo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAlteracaoTituloCodigoMouseClicked(evt);
            }
        });

        jTextFieldAlteracaoCodigo.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldAlteracaoCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldAlteracaoCodigoKeyPressed(evt);
            }
        });

        jLabelAlteracaoControle.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelAlteracaoControle.setText("Controle (Extra)");
        jLabelAlteracaoControle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAlteracaoControleMouseClicked(evt);
            }
        });

        jTextFieldAlteracaoControle.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldAlteracaoControle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldAlteracaoControleKeyPressed(evt);
            }
        });

        jLabelAlteracaoBarras.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelAlteracaoBarras.setText("Código de Barras");
        jLabelAlteracaoBarras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAlteracaoBarrasMouseClicked(evt);
            }
        });

        jTextFieldAlteracaoBarras.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldAlteracaoBarras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldAlteracaoBarrasActionPerformed(evt);
            }
        });
        jTextFieldAlteracaoBarras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldAlteracaoBarrasKeyPressed(evt);
            }
        });

        jCheckBoxAlteracaoInativo.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jCheckBoxAlteracaoInativo.setForeground(new java.awt.Color(255, 0, 0));
        jCheckBoxAlteracaoInativo.setText("Inativo");

        jLabelAlteracaoDescricao.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabelAlteracaoDescricao.setText("Descrição");

        jLabelAlteracaoResumida.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelAlteracaoResumida.setText("Resumida");
        jLabelAlteracaoResumida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAlteracaoResumidaMouseClicked(evt);
            }
        });

        jLabelAlteracaoCompleta.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelAlteracaoCompleta.setText("Completa");
        jLabelAlteracaoCompleta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAlteracaoCompletaMouseClicked(evt);
            }
        });

        jTextFieldAlteracaoCompleta.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldAlteracaoCompleta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldAlteracaoCompletaMouseClicked(evt);
            }
        });
        jTextFieldAlteracaoCompleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldAlteracaoCompletaActionPerformed(evt);
            }
        });
        jTextFieldAlteracaoCompleta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldAlteracaoCompletaKeyPressed(evt);
            }
        });

        jTextFieldAlteracaoResumida.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldAlteracaoResumida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldAlteracaoResumidaKeyPressed(evt);
            }
        });

        jLabelAlteracaoImpressao.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelAlteracaoImpressao.setText("Setor de produção/Impressão");

        jLabelAlteracaoData.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabelAlteracaoData.setText("Inclusão");

        jLabelAlteracaoDataInclusao.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabelAlteracaoDataInclusao.setForeground(new java.awt.Color(255, 0, 0));
        jLabelAlteracaoDataInclusao.setText("26/06/2018");

        jLabelAlteracaoFornecedor.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelAlteracaoFornecedor.setText("Fornecedor");
        jLabelAlteracaoFornecedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAlteracaoFornecedorMouseClicked(evt);
            }
        });

        jComboBoxAlteracaoFornecedor.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jComboBoxAlteracaoFornecedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Default" }));
        jComboBoxAlteracaoFornecedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBoxAlteracaoFornecedorKeyPressed(evt);
            }
        });

        jTextFieldAlteracaoNcm.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldAlteracaoNcm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldAlteracaoNcmActionPerformed(evt);
            }
        });
        jTextFieldAlteracaoNcm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldAlteracaoNcmKeyPressed(evt);
            }
        });

        jLabelAlteracaoNcm.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelAlteracaoNcm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconsAlteracao_16px.png"))); // NOI18N
        jLabelAlteracaoNcm.setText("NCM");
        jLabelAlteracaoNcm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAlteracaoNcmMouseClicked(evt);
            }
        });

        jLabelAlteracaoCest.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelAlteracaoCest.setText("Código CEST");
        jLabelAlteracaoCest.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAlteracaoCestMouseClicked(evt);
            }
        });

        jTextFieldAlteracaoCest.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldAlteracaoCest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldAlteracaoCestActionPerformed(evt);
            }
        });
        jTextFieldAlteracaoCest.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldAlteracaoCestKeyPressed(evt);
            }
        });

        jLabeAlteracaoPesquisaNcmm.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabeAlteracaoPesquisaNcmm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconSeach.png"))); // NOI18N

        jLabelAlteracaoPesquisaCest.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelAlteracaoPesquisaCest.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconSeach.png"))); // NOI18N

        jButtonAlteracaoHistorico.setBackground(new java.awt.Color(0, 153, 255));
        jButtonAlteracaoHistorico.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jButtonAlteracaoHistorico.setForeground(new java.awt.Color(0, 153, 255));
        jButtonAlteracaoHistorico.setText("Histórico De Manutenção");
        jButtonAlteracaoHistorico.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonAlteracaoHistorico.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);

        jLabelAlteracaoQtdEstoque.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabelAlteracaoQtdEstoque.setText("Quantidade em Estoque");

        jLabelAlteracaoMinima.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelAlteracaoMinima.setText("Quantidade Mínima");
        jLabelAlteracaoMinima.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAlteracaoMinimaMouseClicked(evt);
            }
        });
        jLabelAlteracaoMinima.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLabelAlteracaoMinimaKeyPressed(evt);
            }
        });

        jLabelAlteracaoMaxima.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelAlteracaoMaxima.setText("Quantidade Máxima");
        jLabelAlteracaoMaxima.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAlteracaoMaximaMouseClicked(evt);
            }
        });

        jLabelAlteracaoEmEstoque.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabelAlteracaoEmEstoque.setText("Quantidade Estoque");
        jLabelAlteracaoEmEstoque.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAlteracaoEmEstoqueMouseClicked(evt);
            }
        });

        jTextFieldAlteracaoEstoque.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jTextFieldAlteracaoEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldAlteracaoEstoqueActionPerformed(evt);
            }
        });
        jTextFieldAlteracaoEstoque.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldAlteracaoEstoqueKeyPressed(evt);
            }
        });

        jTextFieldAlteracaoMaxima.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldAlteracaoMaxima.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldAlteracaoMaximaKeyPressed(evt);
            }
        });

        jLabelAlteracaoUn2.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelAlteracaoUn2.setText("UN");

        jLabelInsercaoUn1.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabelInsercaoUn1.setText("UN");

        jLabelAlteracaoUn.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelAlteracaoUn.setText("UN");

        jTextFieldAlteracaoMinima.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldAlteracaoMinima.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldAlteracaoMinimaKeyPressed(evt);
            }
        });

        jLabelAlteracaoVarejo.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelAlteracaoVarejo.setText("Varejo");

        jComboBoxAlteracaoUnidade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "UN", "KILO", "KILOGRAMAS" }));

        jLabelAlteracaoUnidade.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelAlteracaoUnidade.setText("Unidade");
        jLabelAlteracaoUnidade.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAlteracaoUnidadeMouseClicked(evt);
            }
        });

        jLabelAlteracaoCustoImp.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelAlteracaoCustoImp.setText("Custo S/ Imp.");
        jLabelAlteracaoCustoImp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAlteracaoCustoImpMouseClicked(evt);
            }
        });

        jTextFieldAlteracaoCustoImp.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldAlteracaoCustoImp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldAlteracaoCustoImpKeyPressed(evt);
            }
        });

        jLabelAlteracaoCustoUnid.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelAlteracaoCustoUnid.setText("Custo Unid. $");
        jLabelAlteracaoCustoUnid.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAlteracaoCustoUnidMouseClicked(evt);
            }
        });

        jTextFieldAlteracaoCustoUnid.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldAlteracaoCustoUnid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldAlteracaoCustoUnidKeyPressed(evt);
            }
        });

        jLabelAlteracaoLucrativa.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelAlteracaoLucrativa.setText("Lucrativ. %");
        jLabelAlteracaoLucrativa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAlteracaoLucrativaMouseClicked(evt);
            }
        });

        jTextFieldAlteracaoLucrativa.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldAlteracaoLucrativa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldAlteracaoLucrativaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldAlteracaoLucrativaKeyReleased(evt);
            }
        });

        jLabelAlteracaoValorVenda.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelAlteracaoValorVenda.setText("Valor Venda $");
        jLabelAlteracaoValorVenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAlteracaoValorVendaMouseClicked(evt);
            }
        });

        jTextFieldAlteracaoValorVenda.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldAlteracaoValorVenda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldAlteracaoValorVendaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldAlteracaoValorVendaKeyReleased(evt);
            }
        });

        jLabelAlteracaoDesconto.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelAlteracaoDesconto.setText("Desconto %");
        jLabelAlteracaoDesconto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAlteracaoDescontoMouseClicked(evt);
            }
        });

        jTextFieldAlteracaoDesconto.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldAlteracaoDesconto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldAlteracaoDescontoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldAlteracaoDescontoKeyReleased(evt);
            }
        });

        jLabelAlteracaoCusto.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelAlteracaoCusto.setText("Custo");
        jLabelAlteracaoCusto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAlteracaoCustoMouseClicked(evt);
            }
        });

        jTextFieldAlteracaoCusto.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldAlteracaoCusto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldAlteracaoCustoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldAlteracaoCustoKeyReleased(evt);
            }
        });

        jButtonAlteracaoSalvar.setBackground(new java.awt.Color(0, 153, 255));
        jButtonAlteracaoSalvar.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jButtonAlteracaoSalvar.setForeground(new java.awt.Color(0, 153, 255));
        jButtonAlteracaoSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconsSalvar_40px.png"))); // NOI18N
        jButtonAlteracaoSalvar.setText("Gravar");
        jButtonAlteracaoSalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonAlteracaoSalvar.setMaximumSize(new java.awt.Dimension(179, 49));
        jButtonAlteracaoSalvar.setMinimumSize(new java.awt.Dimension(179, 49));
        jButtonAlteracaoSalvar.setPreferredSize(new java.awt.Dimension(179, 49));
        jButtonAlteracaoSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlteracaoSalvarActionPerformed(evt);
            }
        });

        jButtonAlteracaoSair.setBackground(new java.awt.Color(0, 153, 255));
        jButtonAlteracaoSair.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jButtonAlteracaoSair.setForeground(new java.awt.Color(0, 153, 255));
        jButtonAlteracaoSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconsSair_40px.png"))); // NOI18N
        jButtonAlteracaoSair.setText("Sair");
        jButtonAlteracaoSair.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonAlteracaoSair.setMaximumSize(new java.awt.Dimension(179, 49));
        jButtonAlteracaoSair.setMinimumSize(new java.awt.Dimension(179, 49));
        jButtonAlteracaoSair.setPreferredSize(new java.awt.Dimension(179, 49));

        jLabelAlteracaoPesquisaNcm.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelAlteracaoPesquisaNcm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconSeach.png"))); // NOI18N
        jLabelAlteracaoPesquisaNcm.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelAlteracaoPesquisaNcm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAlteracaoPesquisaNcmMouseClicked(evt);
            }
        });

        jComboBoxAlteracaoImpressao.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jComboBoxAlteracaoImpressao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabelAlteracaoTituloCodigo)
                                .addGap(17, 17, 17)
                                .addComponent(jTextFieldAlteracaoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelAlteracaoPesquisaNcm)
                                .addGap(37, 37, 37)
                                .addComponent(jCheckBoxAlteracaoInativo)
                                .addGap(29, 29, 29)
                                .addComponent(jLabelAlteracaoDescricao))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabelAlteracaoControle)
                                .addGap(9, 9, 9)
                                .addComponent(jTextFieldAlteracaoControle, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jLabelAlteracaoResumida)
                                .addGap(20, 20, 20)
                                .addComponent(jTextFieldAlteracaoResumida, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabelAlteracaoBarras)
                                .addGap(5, 5, 5)
                                .addComponent(jTextFieldAlteracaoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jLabelAlteracaoCompleta)
                                .addGap(21, 21, 21)
                                .addComponent(jTextFieldAlteracaoCompleta, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabelAlteracaoData)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelAlteracaoDataInclusao))
                            .addComponent(jLabelAlteracaoImpressao)
                            .addComponent(jComboBoxAlteracaoImpressao, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(100, 100, 100))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabelAlteracaoMinima)
                                .addGap(17, 17, 17)
                                .addComponent(jTextFieldAlteracaoMinima, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(jLabelAlteracaoUn)
                                .addGap(139, 139, 139)
                                .addComponent(jLabelAlteracaoUnidade)
                                .addGap(22, 22, 22)
                                .addComponent(jLabelAlteracaoCustoImp, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelAlteracaoCustoUnid, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(jLabelAlteracaoLucrativa)
                                .addGap(48, 48, 48)
                                .addComponent(jLabelAlteracaoValorVenda)
                                .addGap(29, 29, 29)
                                .addComponent(jLabelAlteracaoDesconto)
                                .addGap(44, 44, 44)
                                .addComponent(jLabelAlteracaoCusto))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabelAlteracaoMaxima)
                                .addGap(15, 15, 15)
                                .addComponent(jTextFieldAlteracaoMaxima, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(jLabelAlteracaoUn2)
                                .addGap(79, 79, 79)
                                .addComponent(jLabelAlteracaoVarejo)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxAlteracaoUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldAlteracaoCustoImp, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldAlteracaoCustoUnid, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldAlteracaoLucrativa, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldAlteracaoValorVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldAlteracaoDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldAlteracaoCusto, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabelAlteracaoEmEstoque)
                                .addGap(10, 10, 10)
                                .addComponent(jTextFieldAlteracaoEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(jLabelInsercaoUn1)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButtonAlteracaoSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonAlteracaoSair, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelAlteracaoQtdEstoque)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelAlteracaoFornecedor)
                                            .addComponent(jComboBoxAlteracaoFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(82, 82, 82)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelAlteracaoNcm)
                                            .addComponent(jTextFieldAlteracaoNcm, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(8, 8, 8)
                                        .addComponent(jLabeAlteracaoPesquisaNcmm)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelAlteracaoCest)
                                            .addComponent(jTextFieldAlteracaoCest, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(4, 4, 4)
                                        .addComponent(jLabelAlteracaoPesquisaCest)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonAlteracaoHistorico)))
                        .addGap(180, 180, 180))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelAlteracaoData)
                            .addComponent(jLabelAlteracaoDataInclusao))
                        .addGap(13, 13, 13)
                        .addComponent(jLabelAlteracaoImpressao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBoxAlteracaoImpressao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelAlteracaoTituloCodigo)
                            .addComponent(jTextFieldAlteracaoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBoxAlteracaoInativo)
                            .addComponent(jLabelAlteracaoDescricao)
                            .addComponent(jLabelAlteracaoPesquisaNcm))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelAlteracaoControle)
                            .addComponent(jTextFieldAlteracaoControle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelAlteracaoResumida)
                            .addComponent(jTextFieldAlteracaoResumida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelAlteracaoBarras)
                            .addComponent(jTextFieldAlteracaoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelAlteracaoCompleta)
                            .addComponent(jTextFieldAlteracaoCompleta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabelAlteracaoFornecedor)
                                .addGap(12, 12, 12)
                                .addComponent(jComboBoxAlteracaoFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabelAlteracaoNcm)
                                .addGap(6, 6, 6)
                                .addComponent(jTextFieldAlteracaoNcm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jLabeAlteracaoPesquisaNcmm))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabelAlteracaoCest)
                                .addGap(6, 6, 6)
                                .addComponent(jTextFieldAlteracaoCest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jLabelAlteracaoPesquisaCest)))
                        .addGap(25, 25, 25))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAlteracaoHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)))
                .addComponent(jLabelAlteracaoQtdEstoque)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelAlteracaoMinima)
                    .addComponent(jTextFieldAlteracaoMinima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelAlteracaoUn)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabelAlteracaoUnidade))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabelAlteracaoCustoImp))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabelAlteracaoCustoUnid))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabelAlteracaoLucrativa))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabelAlteracaoValorVenda))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabelAlteracaoDesconto))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabelAlteracaoCusto)))
                .addGap(5, 5, 5)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelAlteracaoMaxima)
                    .addComponent(jTextFieldAlteracaoMaxima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelAlteracaoUn2)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabelAlteracaoVarejo))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jComboBoxAlteracaoUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jTextFieldAlteracaoCustoImp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jTextFieldAlteracaoCustoUnid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jTextFieldAlteracaoLucrativa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jTextFieldAlteracaoValorVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jTextFieldAlteracaoDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jTextFieldAlteracaoCusto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelAlteracaoEmEstoque)
                    .addComponent(jTextFieldAlteracaoEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelInsercaoUn1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAlteracaoSair, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAlteracaoSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("ALTERAÇÃO", jPanel6);

        jLabelExclusaoTituloCodigo.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelExclusaoTituloCodigo.setText("Código");
        jLabelExclusaoTituloCodigo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelExclusaoTituloCodigoMouseClicked(evt);
            }
        });

        jTextFieldExclusaoCodigo.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldExclusaoCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldExclusaoCodigoKeyPressed(evt);
            }
        });

        jLabelExclusaoControle.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelExclusaoControle.setText("Controle (Extra)");
        jLabelExclusaoControle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelExclusaoControleMouseClicked(evt);
            }
        });

        jTextFieldExclusaoControle.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldExclusaoControle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldExclusaoControleKeyPressed(evt);
            }
        });

        jLabelExclusaoBarras.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelExclusaoBarras.setText("Código de Barras");
        jLabelExclusaoBarras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelExclusaoBarrasMouseClicked(evt);
            }
        });

        jTextFieldExclusaoBarras.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldExclusaoBarras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldExclusaoBarrasActionPerformed(evt);
            }
        });
        jTextFieldExclusaoBarras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldExclusaoBarrasKeyPressed(evt);
            }
        });

        jCheckBoxExclusaoInativo.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jCheckBoxExclusaoInativo.setForeground(new java.awt.Color(255, 0, 0));
        jCheckBoxExclusaoInativo.setText("Inativo");

        jLabelExclusaoDescricao.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabelExclusaoDescricao.setText("Descrição");

        jLabelExclusaoResumida.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelExclusaoResumida.setText("Resumida");
        jLabelExclusaoResumida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelExclusaoResumidaMouseClicked(evt);
            }
        });

        jLabelExclusaoCompleta.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelExclusaoCompleta.setText("Completa");
        jLabelExclusaoCompleta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelExclusaoCompletaMouseClicked(evt);
            }
        });

        jTextFieldExclusaoCompleta.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldExclusaoCompleta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldExclusaoCompletaMouseClicked(evt);
            }
        });
        jTextFieldExclusaoCompleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldExclusaoCompletaActionPerformed(evt);
            }
        });

        jTextFieldExclusaoResumida.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldExclusaoResumida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldExclusaoResumidaKeyPressed(evt);
            }
        });

        jLabelExclusaoImpressao.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelExclusaoImpressao.setText("Setor de produção/Impressão");

        jLabelExclusaoData.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabelExclusaoData.setText("Inclusão");

        jLabelExclusaoDataInclusao.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabelExclusaoDataInclusao.setForeground(new java.awt.Color(255, 0, 0));
        jLabelExclusaoDataInclusao.setText("26/06/2018");

        jLabelExclusaoFornecedor.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelExclusaoFornecedor.setText("Fornecedor");
        jLabelExclusaoFornecedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelExclusaoFornecedorMouseClicked(evt);
            }
        });

        jComboBoxExclusaoFornecedor.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jComboBoxExclusaoFornecedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Default" }));
        jComboBoxExclusaoFornecedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBoxExclusaoFornecedorKeyPressed(evt);
            }
        });

        jTextFieldExclusaoNcm.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldExclusaoNcm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldExclusaoNcmActionPerformed(evt);
            }
        });
        jTextFieldExclusaoNcm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldExclusaoNcmKeyPressed(evt);
            }
        });

        jLabelExclusaoNcm.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelExclusaoNcm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconsSubtracao_16px.png"))); // NOI18N
        jLabelExclusaoNcm.setText("NCM");
        jLabelExclusaoNcm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelExclusaoNcmMouseClicked(evt);
            }
        });

        jLabelExclusaoCest.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelExclusaoCest.setText("Código CEST");
        jLabelExclusaoCest.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelExclusaoCestMouseClicked(evt);
            }
        });

        jTextFieldExclusaoCest.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldExclusaoCest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldExclusaoCestActionPerformed(evt);
            }
        });
        jTextFieldExclusaoCest.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldExclusaoCestKeyPressed(evt);
            }
        });

        jLabelExclusaoPesquisaNcm.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelExclusaoPesquisaNcm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconSeach.png"))); // NOI18N

        jLabelExclusaoPesquisaCest.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelExclusaoPesquisaCest.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconSeach.png"))); // NOI18N

        jButtonExclusaoHistorico.setBackground(new java.awt.Color(0, 153, 255));
        jButtonExclusaoHistorico.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jButtonExclusaoHistorico.setForeground(new java.awt.Color(0, 153, 255));
        jButtonExclusaoHistorico.setText("Histórico De Manutenção");
        jButtonExclusaoHistorico.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonExclusaoHistorico.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);

        jLabelExclusaoQtdEstoque.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabelExclusaoQtdEstoque.setText("Quantidade em Estoque");

        jLabelExclusaoMinima.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelExclusaoMinima.setText("Quantidade Mínima");
        jLabelExclusaoMinima.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelExclusaoMinimaMouseClicked(evt);
            }
        });
        jLabelExclusaoMinima.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLabelExclusaoMinimaKeyPressed(evt);
            }
        });

        jLabelExclusaoMaxima.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelExclusaoMaxima.setText("Quantidade Máxima");
        jLabelExclusaoMaxima.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelExclusaoMaximaMouseClicked(evt);
            }
        });

        jLabelExclusaoEmEstoque.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabelExclusaoEmEstoque.setText("Quantidade Estoque");
        jLabelExclusaoEmEstoque.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelExclusaoEmEstoqueMouseClicked(evt);
            }
        });

        jTextFieldExclusaoEstoque.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jTextFieldExclusaoEstoque.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldExclusaoEstoqueKeyPressed(evt);
            }
        });

        jTextFieldExclusaoMaxima.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldExclusaoMaxima.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldExclusaoMaximaKeyPressed(evt);
            }
        });

        jLabelExclusaoUn2.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelExclusaoUn2.setText("UN");

        jLabelExclusaoUn3.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabelExclusaoUn3.setText("UN");

        jLabelExclusaoUn.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelExclusaoUn.setText("UN");

        jTextFieldExclusaoMinima.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldExclusaoMinima.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldExclusaoMinimaKeyPressed(evt);
            }
        });

        jLabelExclusaoVarejo.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelExclusaoVarejo.setText("Varejo");

        jComboBoxExclusaoUnidade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "UN", "KILO", "KILOGRAMAS" }));
        jComboBoxExclusaoUnidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBoxExclusaoUnidadeKeyPressed(evt);
            }
        });

        jLabelExclusaoUnidade.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelExclusaoUnidade.setText("Unidade");
        jLabelExclusaoUnidade.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelExclusaoUnidadeMouseClicked(evt);
            }
        });

        jLabelExclusaoCustoImp.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelExclusaoCustoImp.setText("Custo S/ Imp.");
        jLabelExclusaoCustoImp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelExclusaoCustoImpMouseClicked(evt);
            }
        });

        jTextFieldExclusaoCustoImp.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldExclusaoCustoImp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldExclusaoCustoImpKeyPressed(evt);
            }
        });

        jLabelExclusaoCustoUnid.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelExclusaoCustoUnid.setText("Custo Unid. $");
        jLabelExclusaoCustoUnid.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelExclusaoCustoUnidMouseClicked(evt);
            }
        });

        jTextFieldExclusaoCustoUnid.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldExclusaoCustoUnid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldExclusaoCustoUnidKeyPressed(evt);
            }
        });

        jLabelExclusaoLucrativa.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelExclusaoLucrativa.setText("Lucrativ. %");
        jLabelExclusaoLucrativa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelExclusaoLucrativaMouseClicked(evt);
            }
        });

        jTextFieldExclusaoLucrativa.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldExclusaoLucrativa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldExclusaoLucrativaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldExclusaoLucrativaKeyReleased(evt);
            }
        });

        jLabelInclusaoValorVenda2.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelInclusaoValorVenda2.setText("Valor Venda $");
        jLabelInclusaoValorVenda2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelInclusaoValorVenda2MouseClicked(evt);
            }
        });

        jTextFieldExclusaoValorVenda.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldExclusaoValorVenda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldExclusaoValorVendaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldExclusaoValorVendaKeyReleased(evt);
            }
        });

        jLabelInclusaoDesconto2.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelInclusaoDesconto2.setText("Desconto %");
        jLabelInclusaoDesconto2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelInclusaoDesconto2MouseClicked(evt);
            }
        });

        jTextFieldExclusaoDesconto.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldExclusaoDesconto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldExclusaoDescontoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldExclusaoDescontoKeyReleased(evt);
            }
        });

        jLabelExclusaoCusto.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelExclusaoCusto.setText("Custo");
        jLabelExclusaoCusto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelExclusaoCustoMouseClicked(evt);
            }
        });

        jTextFieldExclusaoCusto.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldExclusaoCusto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldExclusaoCustoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldExclusaoCustoKeyReleased(evt);
            }
        });

        jButtonExclusaoExcluir.setBackground(new java.awt.Color(0, 153, 255));
        jButtonExclusaoExcluir.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jButtonExclusaoExcluir.setForeground(new java.awt.Color(0, 153, 255));
        jButtonExclusaoExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconsLixeira_40px.png"))); // NOI18N
        jButtonExclusaoExcluir.setText("Excluir");
        jButtonExclusaoExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonExclusaoExcluir.setMaximumSize(new java.awt.Dimension(179, 49));
        jButtonExclusaoExcluir.setMinimumSize(new java.awt.Dimension(179, 49));
        jButtonExclusaoExcluir.setPreferredSize(new java.awt.Dimension(179, 49));
        jButtonExclusaoExcluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonExclusaoExcluirMouseEntered(evt);
            }
        });
        jButtonExclusaoExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExclusaoExcluirActionPerformed(evt);
            }
        });

        jButtonExclusaoSair.setBackground(new java.awt.Color(0, 153, 255));
        jButtonExclusaoSair.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jButtonExclusaoSair.setForeground(new java.awt.Color(0, 153, 255));
        jButtonExclusaoSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconsSair_40px.png"))); // NOI18N
        jButtonExclusaoSair.setText("Sair");
        jButtonExclusaoSair.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonExclusaoSair.setMaximumSize(new java.awt.Dimension(179, 49));
        jButtonExclusaoSair.setMinimumSize(new java.awt.Dimension(179, 49));
        jButtonExclusaoSair.setPreferredSize(new java.awt.Dimension(179, 49));

        jLabelExclusaoPesquisa.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelExclusaoPesquisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconSeach.png"))); // NOI18N
        jLabelExclusaoPesquisa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelExclusaoPesquisa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelExclusaoPesquisaMouseClicked(evt);
            }
        });

        jComboBoxExclusaoImpressao.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jComboBoxExclusaoImpressao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabelExclusaoTituloCodigo)
                                .addGap(17, 17, 17)
                                .addComponent(jTextFieldExclusaoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelExclusaoPesquisa)
                                .addGap(37, 37, 37)
                                .addComponent(jCheckBoxExclusaoInativo)
                                .addGap(29, 29, 29)
                                .addComponent(jLabelExclusaoDescricao))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabelExclusaoControle)
                                .addGap(9, 9, 9)
                                .addComponent(jTextFieldExclusaoControle, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jLabelExclusaoResumida)
                                .addGap(20, 20, 20)
                                .addComponent(jTextFieldExclusaoResumida, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabelExclusaoBarras)
                                .addGap(5, 5, 5)
                                .addComponent(jTextFieldExclusaoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jLabelExclusaoCompleta)
                                .addGap(21, 21, 21)
                                .addComponent(jTextFieldExclusaoCompleta, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabelExclusaoData)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelExclusaoDataInclusao))
                            .addComponent(jLabelExclusaoImpressao)
                            .addComponent(jComboBoxExclusaoImpressao, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(100, 100, 100))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelExclusaoQtdEstoque)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabelExclusaoMinima)
                                .addGap(17, 17, 17)
                                .addComponent(jTextFieldExclusaoMinima, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(jLabelExclusaoUn)
                                .addGap(139, 139, 139)
                                .addComponent(jLabelExclusaoUnidade)
                                .addGap(22, 22, 22)
                                .addComponent(jLabelExclusaoCustoImp, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelExclusaoCustoUnid, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(jLabelExclusaoLucrativa)
                                .addGap(48, 48, 48)
                                .addComponent(jLabelInclusaoValorVenda2)
                                .addGap(29, 29, 29)
                                .addComponent(jLabelInclusaoDesconto2)
                                .addGap(44, 44, 44)
                                .addComponent(jLabelExclusaoCusto))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabelExclusaoEmEstoque)
                                .addGap(10, 10, 10)
                                .addComponent(jTextFieldExclusaoEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(jLabelExclusaoUn3))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabelExclusaoMaxima)
                                .addGap(15, 15, 15)
                                .addComponent(jTextFieldExclusaoMaxima, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(jLabelExclusaoUn2)
                                .addGap(79, 79, 79)
                                .addComponent(jLabelExclusaoVarejo)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxExclusaoUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldExclusaoCustoImp, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldExclusaoCustoUnid, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldExclusaoLucrativa, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldExclusaoValorVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldExclusaoDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldExclusaoCusto, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(97, 213, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButtonExclusaoSair, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonExclusaoExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelExclusaoFornecedor)
                                    .addComponent(jComboBoxExclusaoFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(82, 82, 82)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelExclusaoNcm)
                                    .addComponent(jTextFieldExclusaoNcm, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(8, 8, 8)
                                .addComponent(jLabelExclusaoPesquisaNcm)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelExclusaoCest)
                                    .addComponent(jTextFieldExclusaoCest, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(4, 4, 4)
                                .addComponent(jLabelExclusaoPesquisaCest)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonExclusaoHistorico)))
                        .addGap(180, 180, 180))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelExclusaoData)
                            .addComponent(jLabelExclusaoDataInclusao))
                        .addGap(13, 13, 13)
                        .addComponent(jLabelExclusaoImpressao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBoxExclusaoImpressao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelExclusaoTituloCodigo)
                            .addComponent(jTextFieldExclusaoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBoxExclusaoInativo)
                            .addComponent(jLabelExclusaoDescricao)
                            .addComponent(jLabelExclusaoPesquisa))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelExclusaoControle)
                            .addComponent(jTextFieldExclusaoControle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelExclusaoResumida)
                            .addComponent(jTextFieldExclusaoResumida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelExclusaoBarras)
                            .addComponent(jTextFieldExclusaoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelExclusaoCompleta)
                            .addComponent(jTextFieldExclusaoCompleta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(23, 23, 23)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabelExclusaoFornecedor)
                                .addGap(12, 12, 12)
                                .addComponent(jComboBoxExclusaoFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabelExclusaoNcm)
                                .addGap(6, 6, 6)
                                .addComponent(jTextFieldExclusaoNcm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jLabelExclusaoPesquisaNcm))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabelExclusaoCest)
                                .addGap(6, 6, 6)
                                .addComponent(jTextFieldExclusaoCest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jLabelExclusaoPesquisaCest)))
                        .addGap(25, 25, 25)
                        .addComponent(jLabelExclusaoQtdEstoque))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jButtonExclusaoHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelExclusaoMinima)
                    .addComponent(jTextFieldExclusaoMinima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelExclusaoUn)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabelExclusaoUnidade))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabelExclusaoCustoImp))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabelExclusaoCustoUnid))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabelExclusaoLucrativa))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabelInclusaoValorVenda2))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabelInclusaoDesconto2))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabelExclusaoCusto)))
                .addGap(5, 5, 5)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelExclusaoMaxima)
                    .addComponent(jTextFieldExclusaoMaxima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelExclusaoUn2)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabelExclusaoVarejo))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jComboBoxExclusaoUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jTextFieldExclusaoCustoImp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jTextFieldExclusaoCustoUnid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jTextFieldExclusaoLucrativa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jTextFieldExclusaoValorVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jTextFieldExclusaoDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jTextFieldExclusaoCusto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelExclusaoEmEstoque)
                    .addComponent(jTextFieldExclusaoEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelExclusaoUn3))
                .addGap(29, 29, 29)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonExclusaoSair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonExclusaoExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("EXCLUSÃO", jPanel8);

        jPanel5.setBackground(new java.awt.Color(0, 153, 255));

        jLabelF5.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabelF5.setForeground(new java.awt.Color(255, 255, 255));
        jLabelF5.setText("F5 - Limpar dados");

        jLabelF12.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabelF12.setForeground(new java.awt.Color(255, 255, 255));
        jLabelF12.setText("F12 - Fechar janela");

        jLabelF1.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabelF1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelF1.setText("F1 - Historico de Manutenção");

        jLabelF2.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabelF2.setForeground(new java.awt.Color(255, 255, 255));
        jLabelF2.setText("F2 - Gravar Inserção");

        jLabelLogoBalloon.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelLogoBalloon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconBalloon_80px.png"))); // NOI18N
        jLabelLogoBalloon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLogoBalloonMouseClicked(evt);
            }
        });

        jLabelBalloon.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabelBalloon.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBalloon.setText("BALLOON");

        jLabelF6.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabelF6.setForeground(new java.awt.Color(255, 255, 255));
        jLabelF6.setText("F4 - Gravar Alteração");

        jLabelF7.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabelF7.setForeground(new java.awt.Color(255, 255, 255));
        jLabelF7.setText("F6 - Gravar Exclusão");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(jLabelF1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabelF2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabelF6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabelF5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabelF7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabelF12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(114, 114, 114)
                .addComponent(jLabelBalloon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelLogoBalloon)
                .addGap(80, 80, 80))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelLogoBalloon, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelF2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelF1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelF12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelF5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelF6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelF7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(70, 70, 70))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabelBalloon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(56, 56, 56))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelInclusaoTituloCodigoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelInclusaoTituloCodigoMouseClicked
        jTextFieldInclusaoCodigo.requestFocus();
    }//GEN-LAST:event_jLabelInclusaoTituloCodigoMouseClicked

    private void jLabelInclusaoControleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelInclusaoControleMouseClicked
        jTextFieldInclusaoControle.requestFocus();
    }//GEN-LAST:event_jLabelInclusaoControleMouseClicked

    private void jLabelInclusaoBarrasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelInclusaoBarrasMouseClicked
        jTextFieldInclusaoBarras.requestFocus();
    }//GEN-LAST:event_jLabelInclusaoBarrasMouseClicked

    private void jTextFieldInclusaoBarrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldInclusaoBarrasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldInclusaoBarrasActionPerformed

    private void jLabelInclusaoResumidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelInclusaoResumidaMouseClicked
        jTextFieldInclusaoResumida.requestFocus();
    }//GEN-LAST:event_jLabelInclusaoResumidaMouseClicked

    private void jLabelInclusaoCompletaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelInclusaoCompletaMouseClicked
        jTextFieldInclusaoCompleta.requestFocus();
    }//GEN-LAST:event_jLabelInclusaoCompletaMouseClicked

    private void jTextFieldInclusaoCompletaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldInclusaoCompletaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldInclusaoCompletaMouseClicked

    private void jTextFieldInclusaoCompletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldInclusaoCompletaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldInclusaoCompletaActionPerformed

    private void jLabelInclusaoFornecedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelInclusaoFornecedorMouseClicked
        jComboBoxInsersaoFornecedor.requestFocus();
    }//GEN-LAST:event_jLabelInclusaoFornecedorMouseClicked

    private void jTextFieldInclusaoNcmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldInclusaoNcmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldInclusaoNcmActionPerformed

    private void jLabelInclusaoNcmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelInclusaoNcmMouseClicked
        jTextFieldInclusaoNcm.requestFocus();
    }//GEN-LAST:event_jLabelInclusaoNcmMouseClicked

    private void jLabelInclusaoCestMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelInclusaoCestMouseClicked
        jTextFieldInclusaoCest.requestFocus();
    }//GEN-LAST:event_jLabelInclusaoCestMouseClicked

    private void jTextFieldInclusaoCestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldInclusaoCestActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldInclusaoCestActionPerformed

    private void jLabelInclusaoMinimaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelInclusaoMinimaMouseClicked
        jTextFieldInclusaoMinima.requestFocus();
    }//GEN-LAST:event_jLabelInclusaoMinimaMouseClicked

    private void jLabelInclusaoMinimaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabelInclusaoMinimaKeyPressed
        jTextFieldInclusaoMinima.requestFocus();
    }//GEN-LAST:event_jLabelInclusaoMinimaKeyPressed

    private void jLabelInclusaoMaximaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelInclusaoMaximaMouseClicked
        jTextFieldInclusaoMaxima.requestFocus();
    }//GEN-LAST:event_jLabelInclusaoMaximaMouseClicked

    private void jLabelInclusaoEmEstoqueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelInclusaoEmEstoqueMouseClicked
        jTextFieldInclusaoEstoque.requestFocus();
    }//GEN-LAST:event_jLabelInclusaoEmEstoqueMouseClicked

    private void jLabelInclusaoUnidadeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelInclusaoUnidadeMouseClicked
        jComboBoxInclusaoUnidade.requestFocus();
    }//GEN-LAST:event_jLabelInclusaoUnidadeMouseClicked

    private void jLabelInclusaoCustoImpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelInclusaoCustoImpMouseClicked
        jTextFieldInclusaoCustoImp.requestFocus();
    }//GEN-LAST:event_jLabelInclusaoCustoImpMouseClicked

    private void jLabelInclusaoCustoUnidMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelInclusaoCustoUnidMouseClicked
        jTextFieldInclusaoCustoUnid.requestFocus();
    }//GEN-LAST:event_jLabelInclusaoCustoUnidMouseClicked

    private void jLabelInclusaoLucrativaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelInclusaoLucrativaMouseClicked
        jTextFieldInclusaoLucrativa.requestFocus();
    }//GEN-LAST:event_jLabelInclusaoLucrativaMouseClicked

    private void jTextFieldInclusaoLucrativaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldInclusaoLucrativaKeyReleased
        if(evt.getKeyCode() != KeyEvent.VK_ENTER)
        realizaCalculoInsercao(1);
    }//GEN-LAST:event_jTextFieldInclusaoLucrativaKeyReleased

    private void jLabelInclusaoValorVendaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelInclusaoValorVendaMouseClicked
        jTextFieldInclusaoValorVenda.requestFocus();
    }//GEN-LAST:event_jLabelInclusaoValorVendaMouseClicked

    private void jTextFieldInclusaoValorVendaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldInclusaoValorVendaKeyReleased
        if(evt.getKeyCode() != KeyEvent.VK_ENTER)
        realizaCalculoInsercao(2);
    }//GEN-LAST:event_jTextFieldInclusaoValorVendaKeyReleased

    private void jLabelInclusaoDescontoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelInclusaoDescontoMouseClicked
        jTextFieldInclusaoDesconto.requestFocus();
    }//GEN-LAST:event_jLabelInclusaoDescontoMouseClicked

    private void jTextFieldInclusaoDescontoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldInclusaoDescontoKeyReleased
        if(evt.getKeyCode() != KeyEvent.VK_ENTER)
        realizaCalculoInsercao(3);
    }//GEN-LAST:event_jTextFieldInclusaoDescontoKeyReleased

    private void jLabelInclusaoCustoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelInclusaoCustoMouseClicked
        jTextFieldInclusaoCusto.requestFocus();
    }//GEN-LAST:event_jLabelInclusaoCustoMouseClicked

    private void jTextFieldInclusaoCustoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldInclusaoCustoKeyReleased
        if(evt.getKeyCode() != KeyEvent.VK_ENTER)
        realizaCalculoInsercao(4);
    }//GEN-LAST:event_jTextFieldInclusaoCustoKeyReleased

    private void jButtonInclusaoSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInclusaoSalvarActionPerformed
        RealizarInclusao();
        visibilidadePreInsercao();
    }//GEN-LAST:event_jButtonInclusaoSalvarActionPerformed

    private void jLabelLogoBalloonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLogoBalloonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelLogoBalloonMouseClicked

    private void jLabelAlteracaoTituloCodigoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAlteracaoTituloCodigoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelAlteracaoTituloCodigoMouseClicked

    private void jLabelAlteracaoControleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAlteracaoControleMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelAlteracaoControleMouseClicked

    private void jLabelAlteracaoBarrasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAlteracaoBarrasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelAlteracaoBarrasMouseClicked

    private void jTextFieldAlteracaoBarrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldAlteracaoBarrasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldAlteracaoBarrasActionPerformed

    private void jLabelAlteracaoResumidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAlteracaoResumidaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelAlteracaoResumidaMouseClicked

    private void jLabelAlteracaoCompletaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAlteracaoCompletaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelAlteracaoCompletaMouseClicked

    private void jTextFieldAlteracaoCompletaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldAlteracaoCompletaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldAlteracaoCompletaMouseClicked

    private void jTextFieldAlteracaoCompletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldAlteracaoCompletaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldAlteracaoCompletaActionPerformed

    private void jLabelAlteracaoFornecedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAlteracaoFornecedorMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelAlteracaoFornecedorMouseClicked

    private void jTextFieldAlteracaoNcmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldAlteracaoNcmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldAlteracaoNcmActionPerformed

    private void jLabelAlteracaoNcmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAlteracaoNcmMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelAlteracaoNcmMouseClicked

    private void jLabelAlteracaoCestMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAlteracaoCestMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelAlteracaoCestMouseClicked

    private void jTextFieldAlteracaoCestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldAlteracaoCestActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldAlteracaoCestActionPerformed

    private void jLabelAlteracaoMinimaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAlteracaoMinimaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelAlteracaoMinimaMouseClicked

    private void jLabelAlteracaoMinimaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabelAlteracaoMinimaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelAlteracaoMinimaKeyPressed

    private void jLabelAlteracaoMaximaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAlteracaoMaximaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelAlteracaoMaximaMouseClicked

    private void jLabelAlteracaoEmEstoqueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAlteracaoEmEstoqueMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelAlteracaoEmEstoqueMouseClicked

    private void jLabelAlteracaoUnidadeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAlteracaoUnidadeMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelAlteracaoUnidadeMouseClicked

    private void jLabelAlteracaoCustoImpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAlteracaoCustoImpMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelAlteracaoCustoImpMouseClicked

    private void jLabelAlteracaoCustoUnidMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAlteracaoCustoUnidMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelAlteracaoCustoUnidMouseClicked

    private void jLabelAlteracaoLucrativaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAlteracaoLucrativaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelAlteracaoLucrativaMouseClicked

    private void jTextFieldAlteracaoLucrativaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAlteracaoLucrativaKeyReleased
        realizaCalculoAlteracao(1);
    }//GEN-LAST:event_jTextFieldAlteracaoLucrativaKeyReleased

    private void jLabelAlteracaoValorVendaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAlteracaoValorVendaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelAlteracaoValorVendaMouseClicked

    private void jTextFieldAlteracaoValorVendaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAlteracaoValorVendaKeyReleased
        realizaCalculoAlteracao(2);
    }//GEN-LAST:event_jTextFieldAlteracaoValorVendaKeyReleased

    private void jLabelAlteracaoDescontoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAlteracaoDescontoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelAlteracaoDescontoMouseClicked

    private void jTextFieldAlteracaoDescontoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAlteracaoDescontoKeyReleased
        realizaCalculoAlteracao(3);
    }//GEN-LAST:event_jTextFieldAlteracaoDescontoKeyReleased

    private void jLabelAlteracaoCustoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAlteracaoCustoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelAlteracaoCustoMouseClicked

    private void jTextFieldAlteracaoCustoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAlteracaoCustoKeyReleased
        realizaCalculoAlteracao(4);
    }//GEN-LAST:event_jTextFieldAlteracaoCustoKeyReleased

    private void jButtonAlteracaoSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlteracaoSalvarActionPerformed
        RealizarAlteracao();
    }//GEN-LAST:event_jButtonAlteracaoSalvarActionPerformed

    private void jLabelExclusaoTituloCodigoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelExclusaoTituloCodigoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelExclusaoTituloCodigoMouseClicked

    private void jLabelExclusaoControleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelExclusaoControleMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelExclusaoControleMouseClicked

    private void jLabelExclusaoBarrasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelExclusaoBarrasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelExclusaoBarrasMouseClicked

    private void jTextFieldExclusaoBarrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldExclusaoBarrasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldExclusaoBarrasActionPerformed

    private void jLabelExclusaoResumidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelExclusaoResumidaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelExclusaoResumidaMouseClicked

    private void jLabelExclusaoCompletaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelExclusaoCompletaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelExclusaoCompletaMouseClicked

    private void jTextFieldExclusaoCompletaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldExclusaoCompletaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldExclusaoCompletaMouseClicked

    private void jTextFieldExclusaoCompletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldExclusaoCompletaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldExclusaoCompletaActionPerformed

    private void jLabelExclusaoFornecedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelExclusaoFornecedorMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelExclusaoFornecedorMouseClicked

    private void jTextFieldExclusaoNcmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldExclusaoNcmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldExclusaoNcmActionPerformed

    private void jLabelExclusaoNcmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelExclusaoNcmMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelExclusaoNcmMouseClicked

    private void jLabelExclusaoCestMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelExclusaoCestMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelExclusaoCestMouseClicked

    private void jTextFieldExclusaoCestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldExclusaoCestActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldExclusaoCestActionPerformed

    private void jLabelExclusaoMinimaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelExclusaoMinimaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelExclusaoMinimaMouseClicked

    private void jLabelExclusaoMinimaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabelExclusaoMinimaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelExclusaoMinimaKeyPressed

    private void jLabelExclusaoMaximaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelExclusaoMaximaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelExclusaoMaximaMouseClicked

    private void jLabelExclusaoEmEstoqueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelExclusaoEmEstoqueMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelExclusaoEmEstoqueMouseClicked

    private void jLabelExclusaoUnidadeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelExclusaoUnidadeMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelExclusaoUnidadeMouseClicked

    private void jLabelExclusaoCustoImpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelExclusaoCustoImpMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelExclusaoCustoImpMouseClicked

    private void jLabelExclusaoCustoUnidMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelExclusaoCustoUnidMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelExclusaoCustoUnidMouseClicked

    private void jLabelExclusaoLucrativaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelExclusaoLucrativaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelExclusaoLucrativaMouseClicked

    private void jTextFieldExclusaoLucrativaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldExclusaoLucrativaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldExclusaoLucrativaKeyReleased

    private void jLabelInclusaoValorVenda2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelInclusaoValorVenda2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelInclusaoValorVenda2MouseClicked

    private void jTextFieldExclusaoValorVendaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldExclusaoValorVendaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldExclusaoValorVendaKeyReleased

    private void jLabelInclusaoDesconto2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelInclusaoDesconto2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelInclusaoDesconto2MouseClicked

    private void jTextFieldExclusaoDescontoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldExclusaoDescontoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldExclusaoDescontoKeyReleased

    private void jLabelExclusaoCustoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelExclusaoCustoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelExclusaoCustoMouseClicked

    private void jTextFieldExclusaoCustoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldExclusaoCustoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldExclusaoCustoKeyReleased

    private void jButtonExclusaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExclusaoExcluirActionPerformed
        try{
            if(!(jTextFieldExclusaoCodigo.getText().equals(""))){
                int codigo = Integer.parseInt(jTextFieldExclusaoCodigo.getText());
                excluirItem(codigo);  
            }
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(rootPane, "Informe apenas números");
        }
    }//GEN-LAST:event_jButtonExclusaoExcluirActionPerformed

    private void jButtonExclusaoExcluirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonExclusaoExcluirMouseEntered
       jButtonExclusaoExcluir.setBackground(Color.red);
    }//GEN-LAST:event_jButtonExclusaoExcluirMouseEntered

    private void jTextFieldAlteracaoCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAlteracaoCodigoKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            ExibirAlteracao();
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldAlteracaoCodigoKeyPressed

    private void jLabelAlteracaoPesquisaNcmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAlteracaoPesquisaNcmMouseClicked
        ExibirAlteracao();
        visibilidadePreAlteracao();
    }//GEN-LAST:event_jLabelAlteracaoPesquisaNcmMouseClicked

    private void jTextFieldExclusaoCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldExclusaoCodigoKeyPressed
       if(evt.getKeyCode() == KeyEvent.VK_ENTER)
           ExibirExclusao();
       atalhos(evt);
    }//GEN-LAST:event_jTextFieldExclusaoCodigoKeyPressed

    private void jLabelExclusaoPesquisaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelExclusaoPesquisaMouseClicked
        ExibirExclusao();
    }//GEN-LAST:event_jLabelExclusaoPesquisaMouseClicked

    private void jTextFieldExclusaoControleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldExclusaoControleKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldExclusaoControleKeyPressed

    private void jTextFieldExclusaoBarrasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldExclusaoBarrasKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldExclusaoBarrasKeyPressed

    private void jTextFieldExclusaoResumidaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldExclusaoResumidaKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldExclusaoResumidaKeyPressed

    private void jTextFieldAlteracaoCompletaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAlteracaoCompletaKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldAlteracaoCompletaKeyPressed

    private void jComboBoxAlteracaoFornecedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBoxAlteracaoFornecedorKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jComboBoxAlteracaoFornecedorKeyPressed

    private void jTextFieldAlteracaoNcmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAlteracaoNcmKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldAlteracaoNcmKeyPressed

    private void jTextFieldAlteracaoCestKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAlteracaoCestKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldAlteracaoCestKeyPressed

    private void jTextFieldAlteracaoMinimaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAlteracaoMinimaKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldAlteracaoMinimaKeyPressed

    private void jTextFieldAlteracaoMaximaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAlteracaoMaximaKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldAlteracaoMaximaKeyPressed

    private void jTextFieldAlteracaoEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldAlteracaoEstoqueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldAlteracaoEstoqueActionPerformed

    private void jTextFieldAlteracaoEstoqueKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAlteracaoEstoqueKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldAlteracaoEstoqueKeyPressed

    private void jTextFieldAlteracaoCustoImpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAlteracaoCustoImpKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldAlteracaoCustoImpKeyPressed

    private void jTextFieldAlteracaoCustoUnidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAlteracaoCustoUnidKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldAlteracaoCustoUnidKeyPressed

    private void jTextFieldAlteracaoLucrativaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAlteracaoLucrativaKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldAlteracaoLucrativaKeyPressed

    private void jTextFieldAlteracaoValorVendaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAlteracaoValorVendaKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldAlteracaoValorVendaKeyPressed

    private void jTextFieldAlteracaoDescontoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAlteracaoDescontoKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldAlteracaoDescontoKeyPressed

    private void jTextFieldAlteracaoCustoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAlteracaoCustoKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldAlteracaoCustoKeyPressed

    private void jTextFieldAlteracaoControleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAlteracaoControleKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldAlteracaoControleKeyPressed

    private void jTextFieldAlteracaoBarrasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAlteracaoBarrasKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldAlteracaoBarrasKeyPressed

    private void jTextFieldAlteracaoResumidaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAlteracaoResumidaKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldAlteracaoResumidaKeyPressed

    private void jComboBoxExclusaoFornecedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBoxExclusaoFornecedorKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jComboBoxExclusaoFornecedorKeyPressed

    private void jTextFieldExclusaoNcmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldExclusaoNcmKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldExclusaoNcmKeyPressed

    private void jTextFieldExclusaoCestKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldExclusaoCestKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldExclusaoCestKeyPressed

    private void jTextFieldExclusaoMinimaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldExclusaoMinimaKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldExclusaoMinimaKeyPressed

    private void jTextFieldExclusaoMaximaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldExclusaoMaximaKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldExclusaoMaximaKeyPressed

    private void jTextFieldExclusaoEstoqueKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldExclusaoEstoqueKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldExclusaoEstoqueKeyPressed

    private void jComboBoxExclusaoUnidadeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBoxExclusaoUnidadeKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jComboBoxExclusaoUnidadeKeyPressed

    private void jTextFieldExclusaoCustoImpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldExclusaoCustoImpKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldExclusaoCustoImpKeyPressed

    private void jTextFieldExclusaoCustoUnidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldExclusaoCustoUnidKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldExclusaoCustoUnidKeyPressed

    private void jTextFieldExclusaoLucrativaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldExclusaoLucrativaKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldExclusaoLucrativaKeyPressed

    private void jTextFieldExclusaoValorVendaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldExclusaoValorVendaKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldExclusaoValorVendaKeyPressed

    private void jTextFieldExclusaoDescontoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldExclusaoDescontoKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldExclusaoDescontoKeyPressed

    private void jTextFieldExclusaoCustoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldExclusaoCustoKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldExclusaoCustoKeyPressed

    private void jTextFieldInclusaoCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldInclusaoCodigoKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldInclusaoCodigoKeyPressed

    private void jTextFieldInclusaoControleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldInclusaoControleKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldInclusaoControleKeyPressed

    private void jTextFieldInclusaoBarrasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldInclusaoBarrasKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldInclusaoBarrasKeyPressed

    private void jTextFieldInclusaoResumidaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldInclusaoResumidaKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldInclusaoResumidaKeyPressed

    private void jTextFieldInclusaoCompletaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldInclusaoCompletaKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldInclusaoCompletaKeyPressed

    private void jComboBoxInsersaoFornecedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBoxInsersaoFornecedorKeyPressed
        atalhos(evt);        
    }//GEN-LAST:event_jComboBoxInsersaoFornecedorKeyPressed

    private void jTextFieldInclusaoNcmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldInclusaoNcmKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldInclusaoNcmKeyPressed

    private void jTextFieldInclusaoCestKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldInclusaoCestKeyPressed
        atalhos(evt); 
    }//GEN-LAST:event_jTextFieldInclusaoCestKeyPressed

    private void jTextFieldInclusaoMinimaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldInclusaoMinimaKeyPressed
        atalhos(evt);  
    }//GEN-LAST:event_jTextFieldInclusaoMinimaKeyPressed

    private void jTextFieldInclusaoMaximaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldInclusaoMaximaKeyPressed
        atalhos(evt);   
    }//GEN-LAST:event_jTextFieldInclusaoMaximaKeyPressed

    private void jTextFieldInclusaoEstoqueKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldInclusaoEstoqueKeyPressed
        atalhos(evt);    
    }//GEN-LAST:event_jTextFieldInclusaoEstoqueKeyPressed

    private void jComboBoxInclusaoUnidadeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBoxInclusaoUnidadeKeyPressed
        atalhos(evt);       
    }//GEN-LAST:event_jComboBoxInclusaoUnidadeKeyPressed

    private void jTextFieldInclusaoCustoImpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldInclusaoCustoImpKeyPressed
        atalhos(evt);      
    }//GEN-LAST:event_jTextFieldInclusaoCustoImpKeyPressed

    private void jTextFieldInclusaoCustoUnidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldInclusaoCustoUnidKeyPressed
        atalhos(evt);      
    }//GEN-LAST:event_jTextFieldInclusaoCustoUnidKeyPressed

    private void jTextFieldInclusaoLucrativaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldInclusaoLucrativaKeyPressed
        atalhos(evt);    
    }//GEN-LAST:event_jTextFieldInclusaoLucrativaKeyPressed

    private void jTextFieldInclusaoValorVendaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldInclusaoValorVendaKeyPressed
        atalhos(evt);        
    }//GEN-LAST:event_jTextFieldInclusaoValorVendaKeyPressed

    private void jTextFieldInclusaoDescontoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldInclusaoDescontoKeyPressed
        atalhos(evt);    
    }//GEN-LAST:event_jTextFieldInclusaoDescontoKeyPressed

    private void jTextFieldInclusaoCustoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldInclusaoCustoKeyPressed
        atalhos(evt);    
    }//GEN-LAST:event_jTextFieldInclusaoCustoKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JanelaManutencaoEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JanelaManutencaoEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JanelaManutencaoEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaManutencaoEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JanelaManutencaoEstoque().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAlteracaoHistorico;
    private javax.swing.JButton jButtonAlteracaoSair;
    private javax.swing.JButton jButtonAlteracaoSalvar;
    private javax.swing.JButton jButtonExclusaoExcluir;
    private javax.swing.JButton jButtonExclusaoHistorico;
    private javax.swing.JButton jButtonExclusaoSair;
    private javax.swing.JButton jButtonInclusaoHistorico;
    private javax.swing.JButton jButtonInclusaoNaoGerar;
    private javax.swing.JButton jButtonInclusaoSair;
    private javax.swing.JButton jButtonInclusaoSalvar;
    private javax.swing.JCheckBox jCheckBoxAlteracaoInativo;
    private javax.swing.JCheckBox jCheckBoxExclusaoInativo;
    private javax.swing.JCheckBox jCheckBoxInclusaoInativo;
    private javax.swing.JComboBox<String> jComboBoxAlteracaoFornecedor;
    private javax.swing.JComboBox<String> jComboBoxAlteracaoImpressao;
    private javax.swing.JComboBox<String> jComboBoxAlteracaoUnidade;
    private javax.swing.JComboBox<String> jComboBoxExclusaoFornecedor;
    private javax.swing.JComboBox<String> jComboBoxExclusaoImpressao;
    private javax.swing.JComboBox<String> jComboBoxExclusaoUnidade;
    private javax.swing.JComboBox<String> jComboBoxInclusaoUnidade;
    private javax.swing.JComboBox<String> jComboBoxInsersaoFornecedor;
    private javax.swing.JLabel jLabeAlteracaoPesquisaNcmm;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabelAlteracaoBarras;
    private javax.swing.JLabel jLabelAlteracaoCest;
    private javax.swing.JLabel jLabelAlteracaoCompleta;
    private javax.swing.JLabel jLabelAlteracaoControle;
    private javax.swing.JLabel jLabelAlteracaoCusto;
    private javax.swing.JLabel jLabelAlteracaoCustoImp;
    private javax.swing.JLabel jLabelAlteracaoCustoUnid;
    private javax.swing.JLabel jLabelAlteracaoData;
    private javax.swing.JLabel jLabelAlteracaoDataInclusao;
    private javax.swing.JLabel jLabelAlteracaoDesconto;
    private javax.swing.JLabel jLabelAlteracaoDescricao;
    private javax.swing.JLabel jLabelAlteracaoEmEstoque;
    private javax.swing.JLabel jLabelAlteracaoFornecedor;
    private javax.swing.JLabel jLabelAlteracaoImpressao;
    private javax.swing.JLabel jLabelAlteracaoLucrativa;
    private javax.swing.JLabel jLabelAlteracaoMaxima;
    private javax.swing.JLabel jLabelAlteracaoMinima;
    private javax.swing.JLabel jLabelAlteracaoNcm;
    private javax.swing.JLabel jLabelAlteracaoPesquisaCest;
    private javax.swing.JLabel jLabelAlteracaoPesquisaNcm;
    private javax.swing.JLabel jLabelAlteracaoQtdEstoque;
    private javax.swing.JLabel jLabelAlteracaoResumida;
    private javax.swing.JLabel jLabelAlteracaoTituloCodigo;
    private javax.swing.JLabel jLabelAlteracaoUn;
    private javax.swing.JLabel jLabelAlteracaoUn2;
    private javax.swing.JLabel jLabelAlteracaoUnidade;
    private javax.swing.JLabel jLabelAlteracaoValorVenda;
    private javax.swing.JLabel jLabelAlteracaoVarejo;
    private javax.swing.JLabel jLabelBalloon;
    private javax.swing.JLabel jLabelExclusaoBarras;
    private javax.swing.JLabel jLabelExclusaoCest;
    private javax.swing.JLabel jLabelExclusaoCompleta;
    private javax.swing.JLabel jLabelExclusaoControle;
    private javax.swing.JLabel jLabelExclusaoCusto;
    private javax.swing.JLabel jLabelExclusaoCustoImp;
    private javax.swing.JLabel jLabelExclusaoCustoUnid;
    private javax.swing.JLabel jLabelExclusaoData;
    private javax.swing.JLabel jLabelExclusaoDataInclusao;
    private javax.swing.JLabel jLabelExclusaoDescricao;
    private javax.swing.JLabel jLabelExclusaoEmEstoque;
    private javax.swing.JLabel jLabelExclusaoFornecedor;
    private javax.swing.JLabel jLabelExclusaoImpressao;
    private javax.swing.JLabel jLabelExclusaoLucrativa;
    private javax.swing.JLabel jLabelExclusaoMaxima;
    private javax.swing.JLabel jLabelExclusaoMinima;
    private javax.swing.JLabel jLabelExclusaoNcm;
    private javax.swing.JLabel jLabelExclusaoPesquisa;
    private javax.swing.JLabel jLabelExclusaoPesquisaCest;
    private javax.swing.JLabel jLabelExclusaoPesquisaNcm;
    private javax.swing.JLabel jLabelExclusaoQtdEstoque;
    private javax.swing.JLabel jLabelExclusaoResumida;
    private javax.swing.JLabel jLabelExclusaoTituloCodigo;
    private javax.swing.JLabel jLabelExclusaoUn;
    private javax.swing.JLabel jLabelExclusaoUn2;
    private javax.swing.JLabel jLabelExclusaoUn3;
    private javax.swing.JLabel jLabelExclusaoUnidade;
    private javax.swing.JLabel jLabelExclusaoVarejo;
    private javax.swing.JLabel jLabelF1;
    private javax.swing.JLabel jLabelF12;
    private javax.swing.JLabel jLabelF2;
    private javax.swing.JLabel jLabelF5;
    private javax.swing.JLabel jLabelF6;
    private javax.swing.JLabel jLabelF7;
    private javax.swing.JLabel jLabelInclusaoBarras;
    private javax.swing.JLabel jLabelInclusaoCest;
    private javax.swing.JLabel jLabelInclusaoCompleta;
    private javax.swing.JLabel jLabelInclusaoControle;
    private javax.swing.JLabel jLabelInclusaoCusto;
    private javax.swing.JLabel jLabelInclusaoCustoImp;
    private javax.swing.JLabel jLabelInclusaoCustoUnid;
    private javax.swing.JLabel jLabelInclusaoData;
    private javax.swing.JLabel jLabelInclusaoDataInclusao;
    private javax.swing.JLabel jLabelInclusaoDesconto;
    private javax.swing.JLabel jLabelInclusaoDesconto2;
    private javax.swing.JLabel jLabelInclusaoDescricao;
    private javax.swing.JLabel jLabelInclusaoEmEstoque;
    private javax.swing.JLabel jLabelInclusaoFornecedor;
    private javax.swing.JLabel jLabelInclusaoImpressao;
    private javax.swing.JLabel jLabelInclusaoLucrativa;
    private javax.swing.JLabel jLabelInclusaoMaxima;
    private javax.swing.JLabel jLabelInclusaoMinima;
    private javax.swing.JLabel jLabelInclusaoNcm;
    private javax.swing.JLabel jLabelInclusaoPesquisaCest;
    private javax.swing.JLabel jLabelInclusaoPesquisaNcm;
    private javax.swing.JLabel jLabelInclusaoQtdEstoque;
    private javax.swing.JLabel jLabelInclusaoResumida;
    private javax.swing.JLabel jLabelInclusaoTituloCodigo;
    private javax.swing.JLabel jLabelInclusaoUnidade;
    private javax.swing.JLabel jLabelInclusaoValorVenda;
    private javax.swing.JLabel jLabelInclusaoValorVenda2;
    private javax.swing.JLabel jLabelInclusaoVarejo;
    private javax.swing.JLabel jLabelInsercaoUn;
    private javax.swing.JLabel jLabelInsercaoUn1;
    private javax.swing.JLabel jLabelInsercaoUn2;
    private javax.swing.JLabel jLabelInsercaoUn3;
    private javax.swing.JLabel jLabelLogoBalloon;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextFieldAlteracaoBarras;
    private javax.swing.JTextField jTextFieldAlteracaoCest;
    private javax.swing.JTextField jTextFieldAlteracaoCodigo;
    private javax.swing.JTextField jTextFieldAlteracaoCompleta;
    private javax.swing.JTextField jTextFieldAlteracaoControle;
    private javax.swing.JTextField jTextFieldAlteracaoCusto;
    private javax.swing.JTextField jTextFieldAlteracaoCustoImp;
    private javax.swing.JTextField jTextFieldAlteracaoCustoUnid;
    private javax.swing.JTextField jTextFieldAlteracaoDesconto;
    private javax.swing.JTextField jTextFieldAlteracaoEstoque;
    private javax.swing.JTextField jTextFieldAlteracaoLucrativa;
    private javax.swing.JTextField jTextFieldAlteracaoMaxima;
    private javax.swing.JTextField jTextFieldAlteracaoMinima;
    private javax.swing.JTextField jTextFieldAlteracaoNcm;
    private javax.swing.JTextField jTextFieldAlteracaoResumida;
    private javax.swing.JTextField jTextFieldAlteracaoValorVenda;
    private javax.swing.JTextField jTextFieldExclusaoBarras;
    private javax.swing.JTextField jTextFieldExclusaoCest;
    private javax.swing.JTextField jTextFieldExclusaoCodigo;
    private javax.swing.JTextField jTextFieldExclusaoCompleta;
    private javax.swing.JTextField jTextFieldExclusaoControle;
    private javax.swing.JTextField jTextFieldExclusaoCusto;
    private javax.swing.JTextField jTextFieldExclusaoCustoImp;
    private javax.swing.JTextField jTextFieldExclusaoCustoUnid;
    private javax.swing.JTextField jTextFieldExclusaoDesconto;
    private javax.swing.JTextField jTextFieldExclusaoEstoque;
    private javax.swing.JTextField jTextFieldExclusaoLucrativa;
    private javax.swing.JTextField jTextFieldExclusaoMaxima;
    private javax.swing.JTextField jTextFieldExclusaoMinima;
    private javax.swing.JTextField jTextFieldExclusaoNcm;
    private javax.swing.JTextField jTextFieldExclusaoResumida;
    private javax.swing.JTextField jTextFieldExclusaoValorVenda;
    private javax.swing.JTextField jTextFieldInclusaoBarras;
    private javax.swing.JTextField jTextFieldInclusaoCest;
    private javax.swing.JTextField jTextFieldInclusaoCodigo;
    private javax.swing.JTextField jTextFieldInclusaoCompleta;
    private javax.swing.JTextField jTextFieldInclusaoControle;
    private javax.swing.JTextField jTextFieldInclusaoCusto;
    private javax.swing.JTextField jTextFieldInclusaoCustoImp;
    private javax.swing.JTextField jTextFieldInclusaoCustoUnid;
    private javax.swing.JTextField jTextFieldInclusaoDesconto;
    private javax.swing.JTextField jTextFieldInclusaoEstoque;
    private javax.swing.JTextField jTextFieldInclusaoLucrativa;
    private javax.swing.JTextField jTextFieldInclusaoMaxima;
    private javax.swing.JTextField jTextFieldInclusaoMinima;
    private javax.swing.JTextField jTextFieldInclusaoNcm;
    private javax.swing.JTextField jTextFieldInclusaoResumida;
    private javax.swing.JTextField jTextFieldInclusaoValorVenda;
    // End of variables declaration//GEN-END:variables
}
