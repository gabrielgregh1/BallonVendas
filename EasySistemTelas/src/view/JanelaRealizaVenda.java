/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import model.bean.Produto;
import model.bean.Vendedor;
import model.dao.ProdutoDAO;
import model.dao.VendedorDAO;
import view.JanelaColetaDadosCliente.StructureColetaDados;

/**
 *
 * @author gabri
 */
public class JanelaRealizaVenda extends javax.swing.JFrame {

    JanelaColetaDadosCliente coletaDados = new JanelaColetaDadosCliente();
    /**
     * Creates new form JanelaRealizaVenda
     */
    public JanelaRealizaVenda() {
        initComponents();
        condicaoInicial();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        
    }
    
    private void atalhos(KeyEvent evt){
        if(evt.getKeyCode() == KeyEvent.VK_F2)
            FecharVenda();
        
        if(evt.getKeyCode() == KeyEvent.VK_F1)
            visibilidadePreInsercao();
        
        if(evt.getKeyCode() == KeyEvent.VK_F12)
            this.setVisible(false);
        
        if(evt.getKeyCode() == KeyEvent.VK_F5){
            jTextFieldInsere.setText("");
            jTextFieldPreco.setText("");
            jTextFieldTotal.setText("");
            jTextFieldTotalVenda.setText("");
            jTextFieldQuantidadeTotal.setText("");
            jTextFieldDescontoTotal.setText("");
            jFormattedTextFieldDesconto.setText("");
            jFormattedTextFieldQtde.setText("");
            jComboBoxVendedor.setSelectedIndex(0);
            jLabelConsumidor.setText("CONSUMIDOR");
            jLabelRua.setText("Rua Consumidor");
            DefaultTableModel model = (DefaultTableModel) jTableVenda.getModel();
                while (jTableVenda.getModel().getRowCount() > 0)
                    model.removeRow(0);
            
        }
    }
  
    private void exibirNomeCliente(){
        String nome = StructureColetaDados.nome;
        String rua = StructureColetaDados.rua;
        if(!(nome.equals("")) || !(rua.equals(""))){
            jLabelConsumidor.setText(nome);
            jLabelRua.setText(rua);       
        }
    }
    
    private void RealizaDesconto(){
        final double MAX = 100;
        final double MIN = 0;
        try{
            realizarCalculoTotal();
            if(jTextFieldDescontoTotal.getText().equals(""))
                return;     
            double valor = Double.parseDouble(jTextFieldDescontoTotal.getText()); 
            if(valor < MAX && valor >= MIN){         
                double desconto;
                if(jTextFieldDescontoTotal.getText().equals(""))
                    desconto = 0;
               else
                    desconto = Double.parseDouble(jTextFieldDescontoTotal.getText());
                double totalBruto = Double.parseDouble(jTextFieldTotalVenda.getText());
                double totalComDesconto = totalBruto - totalBruto * (desconto/100);
                jTextFieldTotalVenda.setText(String.valueOf(totalComDesconto));
                jTextFieldTotalVenda.setText(limitarTamanhoCampo(jTextFieldTotalVenda.getText()));
            }else{
                jTextFieldDescontoTotal.setText("0");
                realizarCalculoTotal();
            }
        }catch(NumberFormatException ex){
            jTextFieldTotalVenda.setText("");
        }
    
    }
    
    private void realizarCalculoTotal(){
        DefaultTableModel model = (DefaultTableModel) jTableVenda.getModel();
        double preco;
        double total = 0;
        for(int i = 0; i < model.getRowCount() ; i++){
            preco = (double) model.getValueAt(i, 3);
            total += preco;
        }
        jTextFieldTotalVenda.setText(String.valueOf(total));
        jTextFieldQuantidadeTotal.setText(String.valueOf(model.getRowCount()));
        jTextFieldPreco.setText("");
        jFormattedTextFieldDesconto.setText("");
        jFormattedTextFieldQtde.setText("");
        jTextFieldTotal.setText("");
        
    }
    
    private void FecharVenda(){
        try{
            DefaultTableModel model = (DefaultTableModel) jTableVenda.getModel();
            List<Produto> lista = new ArrayList();
            Produto p;
            if(jTextFieldDescontoTotal.getText().equals(""))
                jTextFieldDescontoTotal.setText("0");
                   
            if(!(jTextFieldTotalVenda.getText().equals("")) || !(jTextFieldTotalVenda.getText().equals("0"))){
                String consumidor = jLabelConsumidor.getText();
                
                String vendedorString = String.valueOf(jComboBoxVendedor.getSelectedItem());
                int idx = vendedorString.indexOf(" ");
                int idVendedor = Integer.parseInt(vendedorString.substring(0, idx));
                String nomeVendedor = vendedorString.substring(idx+3);
                
                int itens = Integer.parseInt(jTextFieldQuantidadeTotal.getText());
                double desconto = Double.parseDouble(jTextFieldDescontoTotal.getText());
                double totalApagar = Double.parseDouble(jTextFieldTotalVenda.getText());
                        
                for(int i = 0; i < model.getRowCount() ; i++){        
                    int codigo = (int) model.getValueAt(i, 0);
                    String descricao = (String) model.getValueAt(i, 1);
                    double quantidade = (double) model.getValueAt(i, 2);
                    double preco = (double) model.getValueAt(i, 3);   
                
                    p =  new Produto(codigo, descricao, preco, quantidade);
                    lista.add(p);        
                }
               
                StructureRealizarVenda struct = new StructureRealizarVenda(consumidor, idVendedor, nomeVendedor ,
                                                                    itens, desconto, totalApagar, lista);
               
                JanelaFinalizarVenda janelaVenda = new JanelaFinalizarVenda();       
                    janelaVenda.setVisible(true);
                    this.setVisible(false);
            }
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(rootPane, "Informe apenas números no campos acima.");
        
        }catch(Exception ex){
            JOptionPane.showMessageDialog(rootPane, "Erro inesperado.");
        }
    }
        
    public void configuraComboBox(){
        VendedorDAO dao = new VendedorDAO(); 
            for(Vendedor vendedorPonteiro: dao.read()){
              jComboBoxVendedor.addItem(vendedorPonteiro.getCodigoVendedor()+" - "+vendedorPonteiro.getNome());
            }
    }
    
    private void condicaoInicial(){
        visibilidadePreInsercao();
        jTextFieldInsere.setEnabled(false);
        jTextFieldDescontoTotal.setEnabled(false);
        configuraComboBox();
    }
    
    private void cadastroCliente(){
        if(jComboBoxVendedor.getSelectedItem() == ""){
            JOptionPane.showMessageDialog(rootPane,"Informe o Vendedor");
        }else{       
            if(!(coletaDados.isVisible())){
                coletaDados.setVisible(true);
            }
            visibilidadePreInsercao();        
            
        }
    } 
    
    private void visibilidadePosInsercao(){
        jTextFieldInsere.setEnabled(false);
        jFormattedTextFieldDesconto.setEnabled(true);
        jTextFieldPreco.setEnabled(true);
        jTextFieldPreco.setEditable(false);
        jTextFieldTotal.setEnabled(true);
        jTextFieldTotal.setEditable(false);    
        jFormattedTextFieldQtde.setEnabled(true);
        jFormattedTextFieldQtde.requestFocus();
        
    }    
    
    private void realizaCalculo(){     
        try{
            int qtde;
            double precoProd = Double.parseDouble(jTextFieldPreco.getText());
            double desconto;
            if(jFormattedTextFieldQtde.getText().equals(""))
                qtde = 1;
            else
                qtde = Integer.parseInt(jFormattedTextFieldQtde.getText());
            
            if(jFormattedTextFieldDesconto.getText().equals("")){
               desconto = 0;
            }            
            else         
                if(Double.parseDouble(jFormattedTextFieldDesconto.getText()) > 0 && 
                   Double.parseDouble(jFormattedTextFieldDesconto.getText()) < 100)
                    desconto = Integer.parseInt(jFormattedTextFieldDesconto.getText());
                else{
                    desconto = 0;
                    jFormattedTextFieldDesconto.setText("");  
                }
            double semiTotal = (qtde * precoProd);
            semiTotal = semiTotal - semiTotal*(desconto/100);  
            jTextFieldTotal.setText(String.valueOf(semiTotal));
            jTextFieldTotal.setText(limitarTamanhoCampo(jTextFieldTotal.getText()));
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(rootPane, "Informar apenas números");
            jFormattedTextFieldDesconto.setText("");
            jFormattedTextFieldQtde.setText("");
        }
        
    }      

    private void inserirProduto(){
        int codigoProduto = Integer.parseInt(jTextFieldInsere.getText());
        double qtde = Double.parseDouble(jFormattedTextFieldQtde.getText());
        double semiTotal = Double.parseDouble(jTextFieldTotal.getText());
        String resumida = jLabelTipoProduto.getText();
        
        Produto p = new Produto(codigoProduto, resumida, semiTotal, qtde);
        DefaultTableModel model = (DefaultTableModel) jTableVenda.getModel();
        
            model.addRow(new Object[]{
                p.getCodigoProduto(),
                p.getResumida(),
                p.getQtdEstoque(),
                p.getPreco()
            });
        visibilidadePreInsercao();
        
    }
    
    private void visibilidadePreInsercao(){
        jTextFieldInsere.setEnabled(true);
        jTextFieldInsere.requestFocus();
        jTextFieldPreco.setEnabled(false);
        jTextFieldTotal.setEnabled(false);
        jFormattedTextFieldDesconto.setEnabled(false);
        jFormattedTextFieldQtde.setEnabled(false); 
        jTextFieldQuantidadeTotal.setEnabled(false);
        jTextFieldTotalVenda.setEnabled(false);
        jTextFieldTotalVenda.setEnabled(false);
        jTextFieldDescontoTotal.setEnabled(true);
    }
    
    private void InserirDados(){
        try{
            int produtoInformado = Integer.parseInt(jTextFieldInsere.getText());
            ProdutoDAO dao = new ProdutoDAO();
          
            for(Produto produtoPonteiro : dao.read()){
                if(produtoInformado == produtoPonteiro.getCodigoProduto()){
                    jLabelTipoProduto.setText(produtoPonteiro.getResumida());
                    jFormattedTextFieldQtde.setText("1");
                    jTextFieldPreco.setText(String.valueOf(produtoPonteiro.getPreco()));
                    visibilidadePosInsercao();
                    realizaCalculo();   
                    return;
                }          
            }
            JOptionPane.showMessageDialog(rootPane, "Produto não esta disponível em estoque");
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(rootPane, "Informe apenas números");
        }   
    }
    
    private String limitarTamanhoCampo(String campoJtextField){
        int idProcura = campoJtextField.indexOf(".");
        while(idProcura+3 < campoJtextField.length())
            campoJtextField = campoJtextField.substring(0, campoJtextField.length()-1);

        return campoJtextField;
    } 
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabelConsumidor = new javax.swing.JLabel();
        jLabelRua = new javax.swing.JLabel();
        jLabelTelefone = new javax.swing.JLabel();
        jComboBoxVendedor = new javax.swing.JComboBox<>();
        jButtonOn = new javax.swing.JButton();
        jLabelSequencial = new javax.swing.JLabel();
        jLabelControle = new javax.swing.JLabel();
        jLabelBarra = new javax.swing.JLabel();
        jTextFieldInsere = new javax.swing.JTextField();
        jLabelTipoProduto = new javax.swing.JLabel();
        jLabelQuatidade = new javax.swing.JLabel();
        jFormattedTextFieldQtde = new javax.swing.JFormattedTextField();
        jLabelQuatidade1 = new javax.swing.JLabel();
        jLabelPreco = new javax.swing.JLabel();
        jTextFieldPreco = new javax.swing.JTextField();
        jLabelDesc = new javax.swing.JLabel();
        jFormattedTextFieldDesconto = new javax.swing.JFormattedTextField();
        jTextFieldTotal = new javax.swing.JTextField();
        jLabelPreco4 = new javax.swing.JLabel();
        jLabelPreco3 = new javax.swing.JLabel();
        jLabelItens = new javax.swing.JLabel();
        jTextFieldQuantidadeTotal = new javax.swing.JTextField();
        jTextFieldDescontoTotal = new javax.swing.JTextField();
        jLabelDesconto = new javax.swing.JLabel();
        jLabelTotalVenda = new javax.swing.JLabel();
        jTextFieldTotalVenda = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabelAlteracaoLucrativo3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableVenda = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Realizar Venda - EasySistem");

        jPanel1.setToolTipText("");

        jLabelConsumidor.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabelConsumidor.setText("CONSUMIDOR");

        jLabelRua.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabelRua.setText("Rua Consumidor");

        jLabelTelefone.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabelTelefone.setText("Telefone (xx) xxxxx - xxxx");

        jComboBoxVendedor.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jComboBoxVendedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));

        jButtonOn.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jButtonOn.setText("ON");
        jButtonOn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOnActionPerformed(evt);
            }
        });

        jLabelSequencial.setBackground(new java.awt.Color(51, 51, 51));
        jLabelSequencial.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabelSequencial.setForeground(new java.awt.Color(51, 51, 51));
        jLabelSequencial.setText("Sequencial");

        jLabelControle.setBackground(new java.awt.Color(51, 51, 51));
        jLabelControle.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabelControle.setForeground(new java.awt.Color(51, 51, 51));
        jLabelControle.setText("Controle");

        jLabelBarra.setBackground(new java.awt.Color(51, 51, 51));
        jLabelBarra.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabelBarra.setForeground(new java.awt.Color(51, 51, 51));
        jLabelBarra.setText("Barra");

        jTextFieldInsere.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldInsere.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextFieldInsere.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldInsereKeyPressed(evt);
            }
        });

        jLabelTipoProduto.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelTipoProduto.setText("Tipo Produto");

        jLabelQuatidade.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabelQuatidade.setText("Quantidade");

        jFormattedTextFieldQtde.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextFieldQtde.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jFormattedTextFieldQtde.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jFormattedTextFieldQtdeFocusGained(evt);
            }
        });
        jFormattedTextFieldQtde.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldQtdeKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldQtdeKeyReleased(evt);
            }
        });

        jLabelQuatidade1.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabelQuatidade1.setText("x");

        jLabelPreco.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabelPreco.setText("Preço");

        jTextFieldPreco.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldPreco.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextFieldPreco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldPrecoKeyPressed(evt);
            }
        });

        jLabelDesc.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabelDesc.setText("Desc(%)");

        jFormattedTextFieldDesconto.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jFormattedTextFieldDesconto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jFormattedTextFieldDesconto.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jFormattedTextFieldDesconto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldDescontoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldDescontoKeyReleased(evt);
            }
        });

        jTextFieldTotal.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldTotal.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextFieldTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldTotalKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldTotalKeyReleased(evt);
            }
        });

        jLabelPreco4.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabelPreco4.setText("Total");

        jLabelPreco3.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabelPreco3.setText("=");

        jLabelItens.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabelItens.setText("Itens");

        jTextFieldQuantidadeTotal.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        jTextFieldQuantidadeTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldQuantidadeTotalKeyPressed(evt);
            }
        });

        jTextFieldDescontoTotal.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        jTextFieldDescontoTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldDescontoTotalKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldDescontoTotalKeyReleased(evt);
            }
        });

        jLabelDesconto.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabelDesconto.setText("Desconto");

        jLabelTotalVenda.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabelTotalVenda.setText("Total a Venda");

        jTextFieldTotalVenda.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        jTextFieldTotalVenda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldTotalVendaKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 18, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelRua)
                    .addComponent(jLabelConsumidor))
                .addGap(174, 174, 174)
                .addComponent(jLabelTelefone)
                .addGap(76, 76, 76))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(173, 173, 173)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldTotalVenda, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jLabelTotalVenda))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldQuantidadeTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelItens))
                        .addGap(177, 177, 177)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelDesconto, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldDescontoTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabelSequencial)
                            .addGap(18, 18, 18)
                            .addComponent(jLabelControle)
                            .addGap(18, 18, 18)
                            .addComponent(jLabelBarra))
                        .addComponent(jTextFieldInsere, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBoxVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGap(403, 403, 403)
                            .addComponent(jButtonOn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelTipoProduto)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jFormattedTextFieldQtde)
                                    .addComponent(jLabelQuatidade))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(33, 33, 33)
                                        .addComponent(jLabelQuatidade1)
                                        .addGap(33, 33, 33)
                                        .addComponent(jLabelPreco)
                                        .addGap(70, 70, 70)
                                        .addComponent(jLabelDesc))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addGap(55, 55, 55)
                                        .addComponent(jTextFieldPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(38, 38, 38)
                                        .addComponent(jFormattedTextFieldDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(46, 46, 46)
                                        .addComponent(jTextFieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabelPreco3)
                                        .addGap(41, 41, 41)
                                        .addComponent(jLabelPreco4)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabelConsumidor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelRua)
                    .addComponent(jLabelTelefone))
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonOn)
                    .addComponent(jComboBoxVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSequencial)
                    .addComponent(jLabelControle)
                    .addComponent(jLabelBarra))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldInsere, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelTipoProduto)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelQuatidade)
                    .addComponent(jLabelQuatidade1)
                    .addComponent(jLabelPreco)
                    .addComponent(jLabelDesc)
                    .addComponent(jLabelPreco4)
                    .addComponent(jLabelPreco3))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextFieldDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextFieldQtde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelItens)
                    .addComponent(jLabelDesconto))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldQuantidadeTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldDescontoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jLabelTotalVenda)
                .addGap(18, 18, 18)
                .addComponent(jTextFieldTotalVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(0, 153, 255));

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("F2 - Finalizar venda");

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("F1 - Escolher outro produto");

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("F5 - Limpar dados");

        jLabel11.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("F12 - Fechar janela");

        jLabelAlteracaoLucrativo3.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelAlteracaoLucrativo3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconBalloon_80px.png"))); // NOI18N
        jLabelAlteracaoLucrativo3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAlteracaoLucrativo3MouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("BALLOON");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(265, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelAlteracaoLucrativo3)
                .addGap(18, 18, 18))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelAlteracaoLucrativo3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10)
                    .addComponent(jLabel5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setForeground(new java.awt.Color(204, 204, 204));

        jTableVenda.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTableVenda.setForeground(new java.awt.Color(0, 0, 204));
        jTableVenda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "DESCRIÇÃO", "QUANTIDADE", "PREÇO"
            }
        ));
        jScrollPane1.setViewportView(jTableVenda);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 737, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(0, 153, 255));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 44, Short.MAX_VALUE)
        );

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 28)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 255));
        jLabel3.setText("Realizar Venda");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconVenda_80px.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2))
                    .addComponent(jLabel4))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldInsereKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldInsereKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            if(!(jTextFieldInsere.getText().equals(""))){
                InserirDados();
                exibirNomeCliente();
            }

        }
        atalhos(evt);

    }//GEN-LAST:event_jTextFieldInsereKeyPressed

    private void jFormattedTextFieldQtdeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldQtdeKeyPressed

        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            if(!(jTextFieldTotal.getText().equals("0.0"))){
                inserirProduto();
                realizarCalculoTotal();
            }
        }
        if(evt.getKeyCode() == KeyEvent.VK_SPACE){
            visibilidadePreInsercao();
        }
        atalhos(evt);
        
        
    }//GEN-LAST:event_jFormattedTextFieldQtdeKeyPressed

    private void jFormattedTextFieldQtdeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldQtdeKeyReleased
        if(jFormattedTextFieldQtde.getText().equals(""))
        if(evt.getKeyCode() == KeyEvent.VK_BACK_SPACE){
            jFormattedTextFieldQtde.setText("");
        }
        if(evt.getKeyCode() != KeyEvent.VK_ENTER)
        realizaCalculo();
    }//GEN-LAST:event_jFormattedTextFieldQtdeKeyReleased

    private void jTextFieldPrecoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPrecoKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_SPACE){
            visibilidadePreInsercao();
        }
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldPrecoKeyPressed

    private void jFormattedTextFieldDescontoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldDescontoKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            if(!(jTextFieldTotal.getText().equals("0.0"))){
                inserirProduto();
                realizarCalculoTotal();
            }
        }
        if(evt.getKeyCode() == KeyEvent.VK_SPACE){
            visibilidadePreInsercao();
        }
        atalhos(evt);
    }//GEN-LAST:event_jFormattedTextFieldDescontoKeyPressed

    private void jFormattedTextFieldDescontoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldDescontoKeyReleased
        realizaCalculo();
    }//GEN-LAST:event_jFormattedTextFieldDescontoKeyReleased

    private void jTextFieldTotalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTotalKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_SPACE){
            visibilidadePreInsercao();
        }
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldTotalKeyPressed

    private void jTextFieldTotalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTotalKeyReleased
        realizaCalculo();
    }//GEN-LAST:event_jTextFieldTotalKeyReleased

    private void jLabelAlteracaoLucrativo3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAlteracaoLucrativo3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelAlteracaoLucrativo3MouseClicked

    private void jButtonOnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOnActionPerformed
        cadastroCliente(); 
    }//GEN-LAST:event_jButtonOnActionPerformed

    private void jTextFieldDescontoTotalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldDescontoTotalKeyReleased
       RealizaDesconto();
    }//GEN-LAST:event_jTextFieldDescontoTotalKeyReleased

    private void jTextFieldDescontoTotalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldDescontoTotalKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            FecharVenda();
        
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldDescontoTotalKeyPressed

    private void jFormattedTextFieldQtdeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextFieldQtdeFocusGained
    
    }//GEN-LAST:event_jFormattedTextFieldQtdeFocusGained

    private void jTextFieldQuantidadeTotalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldQuantidadeTotalKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldQuantidadeTotalKeyPressed

    private void jTextFieldTotalVendaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTotalVendaKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldTotalVendaKeyPressed

        public static class StructureRealizarVenda {
        public static String consumidor;
        public static int idVendedor;
        public static String nomeVendedor;
        public static int  itens;
        public static double desconto;
        public static double totalApagar;
        public static List<Produto> lista = new ArrayList();

        public StructureRealizarVenda(String consumidor, int idVendedor,String nomeVendedor, int itens,
                                      double desconto, double totalApagar, List<Produto> lista) {
            StructureRealizarVenda.consumidor = consumidor;
            StructureRealizarVenda.idVendedor = idVendedor;
            StructureRealizarVenda.itens = itens; 
            StructureRealizarVenda.desconto = desconto;
            StructureRealizarVenda.totalApagar = totalApagar;
            StructureRealizarVenda.lista = lista;
            StructureRealizarVenda.nomeVendedor = nomeVendedor;
        } 
    }
    
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
            java.util.logging.Logger.getLogger(JanelaRealizaVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JanelaRealizaVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JanelaRealizaVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaRealizaVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JanelaRealizaVenda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonOn;
    private javax.swing.JComboBox<String> jComboBoxVendedor;
    private javax.swing.JFormattedTextField jFormattedTextFieldDesconto;
    private javax.swing.JFormattedTextField jFormattedTextFieldQtde;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelAlteracaoLucrativo3;
    private javax.swing.JLabel jLabelBarra;
    private javax.swing.JLabel jLabelConsumidor;
    private javax.swing.JLabel jLabelControle;
    private javax.swing.JLabel jLabelDesc;
    private javax.swing.JLabel jLabelDesconto;
    private javax.swing.JLabel jLabelItens;
    private javax.swing.JLabel jLabelPreco;
    private javax.swing.JLabel jLabelPreco3;
    private javax.swing.JLabel jLabelPreco4;
    private javax.swing.JLabel jLabelQuatidade;
    private javax.swing.JLabel jLabelQuatidade1;
    private javax.swing.JLabel jLabelRua;
    private javax.swing.JLabel jLabelSequencial;
    private javax.swing.JLabel jLabelTelefone;
    private javax.swing.JLabel jLabelTipoProduto;
    private javax.swing.JLabel jLabelTotalVenda;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableVenda;
    private javax.swing.JTextField jTextFieldDescontoTotal;
    private javax.swing.JTextField jTextFieldInsere;
    private javax.swing.JTextField jTextFieldPreco;
    private javax.swing.JTextField jTextFieldQuantidadeTotal;
    private javax.swing.JTextField jTextFieldTotal;
    private javax.swing.JTextField jTextFieldTotalVenda;
    // End of variables declaration//GEN-END:variables
}
