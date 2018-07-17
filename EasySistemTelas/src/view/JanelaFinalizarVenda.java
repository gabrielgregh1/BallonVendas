/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.KeyEvent;
import static java.awt.image.ImageObserver.HEIGHT;
import static java.awt.image.ImageObserver.WIDTH;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.bean.Produto;
import model.bean.Produto_vendido;
import model.bean.Venda;
import model.bean.Vendedor;
import model.dao.ProdutoDAO;
import model.dao.VendaDAO;
import model.dao.produto_vendidoDAO;

/**
 *
 * @author gabri
 */
public class JanelaFinalizarVenda extends javax.swing.JFrame {

    /**
     * Creates new form JanelaFinalizarVenda
     */
    public JanelaFinalizarVenda() {
        initComponents();
        condicaoInicial();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); 

    }

    private void atalhos(KeyEvent evt){
        if(evt.getKeyCode() == KeyEvent.VK_F2)
            FecharCompra();   
        if(evt.getKeyCode() == KeyEvent.VK_F5)
            condicaoInicial();
        if(evt.getKeyCode() == KeyEvent.VK_F12)
            this.setVisible(false);
        
        
    }
    
    private void condicaoInicial(){
      //  resto = JanelaRealizarVenda.StructureRealizarVenda.totalApagar;
        DefaultTableModel model = (DefaultTableModel) jTableResultado.getModel();
        double totalApagar = JanelaRealizaVenda.StructureRealizarVenda.totalApagar;
        jTextFieldTotalApagar.setText(String.valueOf(totalApagar)); 
        jTextFieldTotalApagar.setEditable(false);
        jTextFieldTituloCheque.setEditable(false);
        jTextFieldTituloCrediario.setEditable(false);
        jTextFieldTroco.setEditable(false);
        jTextFieldTituloCredito.setEditable(false);
        jTextFieldTituloDebito.setEditable(false);
        jTextFieldTituloDinheiro.setEditable(false);
        jTextFieldTituloPre.setEditable(false);
        jTextFieldFalta.setEditable(false);
        jTextFieldTituloVale.setEditable(false);
        camposVazios();
        jTextFieldValeCompras.setText(""); 
        jComboBoxCheque.setSelectedIndex(0);
        jComboBoxCredito.setSelectedIndex(0);
        
        double inicial = 0;
        model.setValueAt(inicial, 1, 2);
        model.setValueAt(inicial, 3, 2);
        model.setValueAt(inicial, 4, 2);
        model.setValueAt(inicial, 6, 2);
        model.setValueAt(inicial, 7, 2);
        jTextFieldDinheiro.requestFocus();
        
    } 
    
    private void camposVazios(){
        jTextFieldPre.setText("");  
        jTextFieldCheque.setText("");  
        jTextFieldCartaoDebito.setText("");  
        jTextFieldCredito.setText("");  
        jTextFieldCrediario.setText("");  
        jTextFieldDinheiro.setText("");  
        jTextFieldValeCompras.setText(""); 
    }
    
    private String configuraValorMaximo(java.awt.event.KeyEvent evt, String campoJtextField , int row, int col, int formaPagamento){
        try{
            DefaultTableModel model = (DefaultTableModel) jTableResultado.getModel();
            if(verificaNaoEspacos(campoJtextField)){
            
                double total = Double.parseDouble(jTextFieldTotalApagar.getText());
                double valorCampo = Double.parseDouble(campoJtextField);
                double lerCampo = (double) model.getValueAt(row, col); 

                   // limitarTamanhoCampo(campoJtextField);
                
                if(KeyEvent.VK_ENTER == evt.getKeyCode() && verificaPagamento(valorCampo, formaPagamento)) {
                    model.setValueAt(valorCampo, row, col);  
                    camposVazios();
                }                
                
            }
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(rootPane, "Informar apenas números");   
            return "";
        }
        return campoJtextField;
    }
    
    private void venderProdutos(){
        
        
    
    }
    
    private void showMessage(){
        
        String[] opcao = {"Sim", "Não"}; 
        
        int escolha = JOptionPane.showOptionDialog(rootPane, "Deseja finalizar venda?",
                                     "Escolha um opção", WIDTH, HEIGHT, null, opcao, opcao[0]);
        
        if(escolha == 0){
            JanelaRealizaVenda janelaRealizaVenda = new JanelaRealizaVenda();
            janelaRealizaVenda.setVisible(true);
            this.setVisible(false);
            String nomeVendedor = JanelaRealizaVenda.StructureRealizarVenda.nomeVendedor;        
            int idVendedor = JanelaRealizaVenda.StructureRealizarVenda.idVendedor;
            double totalApagar = JanelaRealizaVenda.StructureRealizarVenda.totalApagar;
                    
            Vendedor vendedor = new Vendedor(idVendedor, nomeVendedor);
            Venda venda = new Venda(vendedor, totalApagar);
            VendaDAO venDAO = new VendaDAO();
            venDAO.create(venda);
            Venda vendinha = new Venda();
         
            for(Venda v :venDAO.read()){
                if(vendinha.getCodigo() < v.getCodigo())
                    vendinha = v;
            }
            
            Produto_vendido produto_vendido;
            List<Produto> lista = JanelaRealizaVenda.StructureRealizarVenda.lista;
            ProdutoDAO dao = new ProdutoDAO();
            produto_vendidoDAO daoVendido = new produto_vendidoDAO();
            for(Produto p : lista){
                dao.venderProdutoUpdate(p.getQtdEstoque(), p.getCodigoProduto()); 
                produto_vendido = new Produto_vendido(p, vendinha);
                daoVendido.create(produto_vendido);
            }

            venderProdutos();
            
        }
        
    }
    
    private double pegarValoresJTable(int campoTable){
        DefaultTableModel model = (DefaultTableModel) jTableResultado.getModel();
        //Parece complexo, porém é apenas um vetor que consulta os dados da table        
        double[] lerCampo = {(double)model.getValueAt(1, 2),    // 0 para dinheiro
                             (double)model.getValueAt(3, 2),    // 1 para cheque a vista
                             (double)model.getValueAt(4, 2),    // 2 para cheque pre
                             (double)model.getValueAt(6, 2),    // 3 para cartao Debito
                             (double)model.getValueAt(7, 2)};   // 4 para cartao credito
        
        
        return lerCampo[campoTable];
    }
    
    private void FecharCompra(){
        try{
            DefaultTableModel model = (DefaultTableModel) jTableResultado.getModel();
            double totalVenda = Double.parseDouble(jTextFieldTotalApagar.getText());
        
            double totalFormasPagar = (double)model.getValueAt(8, 2); 

            if(totalFormasPagar >= totalVenda){
                jTextFieldTroco.setText(String.valueOf(totalFormasPagar - totalVenda));
                jTextFieldTroco.setText(limitarTamanhoCampo(jTextFieldTroco.getText()));
                showMessage();
            }else{
                JOptionPane.showMessageDialog(rootPane,"Dinheiro Insuficiente para finalizar compra");
            }
        }catch(NullPointerException ex){
            JOptionPane.showMessageDialog(rootPane, "Informe a forma de pagamento");
        }
    }
        
    private boolean verificaPagamento(double valor, int campo){
        if(!(campo == 2 && jComboBoxCheque.getSelectedIndex() == 0) && !(campo == 5 && jComboBoxCredito.getSelectedIndex() == 0)){
        DefaultTableModel model = (DefaultTableModel) jTableResultado.getModel();
        double totalVenda = Double.parseDouble(jTextFieldTotalApagar.getText());
        
        double dinheiro = pegarValoresJTable(0);         
        double chequeAvista = pegarValoresJTable(1);
        double chequePre = pegarValoresJTable(2);
        double cartaoDebito = pegarValoresJTable(3);
        double cartaoCredito = pegarValoresJTable(4);
                    
        switch(campo){
            case 1:
                dinheiro = valor;
                break;
            case 2:
                chequeAvista = valor;
                break;
            case 3:
                chequePre = valor;
                break;
            case 4:
                cartaoDebito = valor;
                break;
            case 5:
                cartaoCredito = valor;
                break;
        }
                
        double totalFormasPagar = chequeAvista + chequePre + cartaoDebito + cartaoCredito + dinheiro;
       // resto = totalVenda - totalFormasPagar;      
      //  if(resto < 0)
        //    resto = totalFormasPagar - totalVenda;
        model.setValueAt(totalFormasPagar, 8, 2); 
        
        if(totalFormasPagar >= totalVenda){
            jTextFieldTroco.setText(String.valueOf(totalFormasPagar - totalVenda));
            jTextFieldTroco.setText(limitarTamanhoCampo(jTextFieldTroco.getText()));
            showMessage();
        }else{
            jTextFieldFalta.setText(String.valueOf(totalVenda - totalFormasPagar)); 
        }
        
        return totalVenda >= totalFormasPagar;
        
        }
        JOptionPane.showMessageDialog(rootPane, "Informe as parcelas");
        return false;
    }
    
    private String limitarTamanhoCampo(String campoJtextField){
        int idProcura = campoJtextField.indexOf(".");
        while(idProcura+3 < campoJtextField.length())
            campoJtextField = campoJtextField.substring(0, campoJtextField.length()-1);

        return campoJtextField;
    } 
    
    private boolean verificaNaoEspacos(String campo){
        int espaco = campo.indexOf(" ");  
        return espaco == -1 && !(campo.equals(""));
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelRaiz = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldFalta = new javax.swing.JTextField();
        jTextFieldTotalApagar = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldTituloVale = new javax.swing.JTextField();
        jTextFieldValeCompras = new javax.swing.JTextField();
        jTextFieldTituloDinheiro = new javax.swing.JTextField();
        jTextFieldDinheiro = new javax.swing.JTextField();
        jTextFieldTituloCheque = new javax.swing.JTextField();
        jTextFieldCheque = new javax.swing.JTextField();
        jTextFieldTituloDebito = new javax.swing.JTextField();
        jTextFieldCartaoDebito = new javax.swing.JTextField();
        jTextFieldTituloCredito = new javax.swing.JTextField();
        jTextFieldCredito = new javax.swing.JTextField();
        jTextFieldTituloPre = new javax.swing.JTextField();
        jTextFieldPre = new javax.swing.JTextField();
        jTextFieldTituloCrediario = new javax.swing.JTextField();
        jTextFieldCrediario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldTroco = new javax.swing.JTextField();
        jButtonLimpar = new javax.swing.JButton();
        jButtonCrediarioOk = new javax.swing.JButton();
        jButtonPreOk = new javax.swing.JButton();
        jComboBoxCredito = new javax.swing.JComboBox<>();
        jComboBoxCheque = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableResultado = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabelLogoBalloon = new javax.swing.JLabel();
        jLabelBalloon = new javax.swing.JLabel();
        jLabelF2 = new javax.swing.JLabel();
        jLabelF5 = new javax.swing.JLabel();
        jLabelF12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconFinalizaVenda_80px.png"))); // NOI18N

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
        jLabel38.setText("Forma de Pagamento");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel38)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel38)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 51, 51));
        jLabel4.setText("FALTA:");

        jTextFieldFalta.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jTextFieldFalta.setForeground(new java.awt.Color(255, 0, 0));
        jTextFieldFalta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        jTextFieldFalta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFaltaActionPerformed(evt);
            }
        });
        jTextFieldFalta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldFaltaKeyPressed(evt);
            }
        });

        jTextFieldTotalApagar.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jTextFieldTotalApagar.setForeground(new java.awt.Color(0, 0, 255));
        jTextFieldTotalApagar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 204)));
        jTextFieldTotalApagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTotalApagarActionPerformed(evt);
            }
        });
        jTextFieldTotalApagar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldTotalApagarKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 255));
        jLabel3.setText("TOTAL A PAGAR:");

        jTextFieldTituloVale.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jTextFieldTituloVale.setForeground(new java.awt.Color(0, 153, 255));
        jTextFieldTituloVale.setText("Vale Compras");
        jTextFieldTituloVale.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldTituloValeMouseClicked(evt);
            }
        });
        jTextFieldTituloVale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTituloValeActionPerformed(evt);
            }
        });
        jTextFieldTituloVale.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldTituloValeKeyPressed(evt);
            }
        });

        jTextFieldValeCompras.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jTextFieldValeCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldValeComprasActionPerformed(evt);
            }
        });
        jTextFieldValeCompras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldValeComprasKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldValeComprasKeyReleased(evt);
            }
        });

        jTextFieldTituloDinheiro.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jTextFieldTituloDinheiro.setForeground(new java.awt.Color(0, 153, 255));
        jTextFieldTituloDinheiro.setText("Dinheiro");
        jTextFieldTituloDinheiro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldTituloDinheiroMouseClicked(evt);
            }
        });
        jTextFieldTituloDinheiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTituloDinheiroActionPerformed(evt);
            }
        });
        jTextFieldTituloDinheiro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldTituloDinheiroKeyPressed(evt);
            }
        });

        jTextFieldDinheiro.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jTextFieldDinheiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDinheiroActionPerformed(evt);
            }
        });
        jTextFieldDinheiro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldDinheiroKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldDinheiroKeyReleased(evt);
            }
        });

        jTextFieldTituloCheque.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jTextFieldTituloCheque.setForeground(new java.awt.Color(0, 153, 255));
        jTextFieldTituloCheque.setText("Cheque");
        jTextFieldTituloCheque.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldTituloChequeMouseClicked(evt);
            }
        });
        jTextFieldTituloCheque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTituloChequeActionPerformed(evt);
            }
        });
        jTextFieldTituloCheque.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldTituloChequeKeyPressed(evt);
            }
        });

        jTextFieldCheque.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jTextFieldCheque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldChequeActionPerformed(evt);
            }
        });
        jTextFieldCheque.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldChequeKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldChequeKeyReleased(evt);
            }
        });

        jTextFieldTituloDebito.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jTextFieldTituloDebito.setForeground(new java.awt.Color(0, 153, 255));
        jTextFieldTituloDebito.setText("Cart.Débito");
        jTextFieldTituloDebito.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldTituloDebitoMouseClicked(evt);
            }
        });
        jTextFieldTituloDebito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTituloDebitoActionPerformed(evt);
            }
        });
        jTextFieldTituloDebito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldTituloDebitoKeyPressed(evt);
            }
        });

        jTextFieldCartaoDebito.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jTextFieldCartaoDebito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCartaoDebitoActionPerformed(evt);
            }
        });
        jTextFieldCartaoDebito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldCartaoDebitoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldCartaoDebitoKeyReleased(evt);
            }
        });

        jTextFieldTituloCredito.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jTextFieldTituloCredito.setForeground(new java.awt.Color(0, 153, 255));
        jTextFieldTituloCredito.setText("Cart.Crédito");
        jTextFieldTituloCredito.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldTituloCreditoMouseClicked(evt);
            }
        });
        jTextFieldTituloCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTituloCreditoActionPerformed(evt);
            }
        });
        jTextFieldTituloCredito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldTituloCreditoKeyPressed(evt);
            }
        });

        jTextFieldCredito.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jTextFieldCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCreditoActionPerformed(evt);
            }
        });
        jTextFieldCredito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldCreditoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldCreditoKeyReleased(evt);
            }
        });

        jTextFieldTituloPre.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jTextFieldTituloPre.setForeground(new java.awt.Color(0, 153, 255));
        jTextFieldTituloPre.setText("Cheque Pré");
        jTextFieldTituloPre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldTituloPreMouseClicked(evt);
            }
        });
        jTextFieldTituloPre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTituloPreActionPerformed(evt);
            }
        });
        jTextFieldTituloPre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldTituloPreKeyPressed(evt);
            }
        });

        jTextFieldPre.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jTextFieldPre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPreActionPerformed(evt);
            }
        });
        jTextFieldPre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldPreKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldPreKeyReleased(evt);
            }
        });

        jTextFieldTituloCrediario.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jTextFieldTituloCrediario.setForeground(new java.awt.Color(0, 153, 255));
        jTextFieldTituloCrediario.setText("Crediário");
        jTextFieldTituloCrediario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldTituloCrediarioMouseClicked(evt);
            }
        });
        jTextFieldTituloCrediario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTituloCrediarioActionPerformed(evt);
            }
        });
        jTextFieldTituloCrediario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldTituloCrediarioKeyPressed(evt);
            }
        });

        jTextFieldCrediario.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jTextFieldCrediario.setForeground(new java.awt.Color(255, 0, 0));
        jTextFieldCrediario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCrediarioActionPerformed(evt);
            }
        });
        jTextFieldCrediario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldCrediarioKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 51, 51));
        jLabel2.setText("TROCO:");

        jTextFieldTroco.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jTextFieldTroco.setForeground(new java.awt.Color(255, 0, 0));
        jTextFieldTroco.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0)));
        jTextFieldTroco.setCaretColor(new java.awt.Color(255, 255, 255));
        jTextFieldTroco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTrocoActionPerformed(evt);
            }
        });
        jTextFieldTroco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldTrocoKeyPressed(evt);
            }
        });

        jButtonLimpar.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jButtonLimpar.setText("LIMPAR");
        jButtonLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimparActionPerformed(evt);
            }
        });

        jButtonCrediarioOk.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jButtonCrediarioOk.setText("OK");

        jButtonPreOk.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jButtonPreOk.setText("OK");

        jComboBoxCredito.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jComboBoxCredito.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        jComboBoxCredito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBoxCreditoKeyPressed(evt);
            }
        });

        jComboBoxCheque.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jComboBoxCheque.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        jComboBoxCheque.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBoxChequeKeyPressed(evt);
            }
        });

        jTableResultado.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jTableResultado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Dinheiro", null, null},
                {null, "Valor", ""},
                {"Cheque", null, null},
                {null, "Cheque À vista", ""},
                {"", "Cheque Pré", ""},
                {"Cartões", null, null},
                {null, "Cartão de Debito", ""},
                {null, "Cartão de Credito", ""},
                {"Total", null, null}
            },
            new String [] {
                " ", "Operação", "Valor"
            }
        ));
        jTableResultado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTableResultadoKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTableResultado);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldTotalApagar, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldFalta, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldTituloVale, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldTituloDinheiro, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldTituloCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldTituloDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldTituloCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldTituloCrediario, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldTituloPre, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(33, 33, 33)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jTextFieldCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jComboBoxCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jTextFieldCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextFieldDinheiro, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextFieldCartaoDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addComponent(jComboBoxCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jTextFieldValeCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextFieldCrediario, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextFieldPre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButtonPreOk)
                                            .addComponent(jButtonCrediarioOk)))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(33, 33, 33)
                                .addComponent(jTextFieldTroco, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(jButtonLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(51, 51, 51)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextFieldTotalApagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jTextFieldFalta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldTituloVale)
                            .addComponent(jTextFieldValeCompras))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldTituloDinheiro)
                            .addComponent(jTextFieldDinheiro))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldTituloCheque)
                            .addComponent(jComboBoxCheque)
                            .addComponent(jTextFieldCheque))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldTituloDebito)
                            .addComponent(jTextFieldCartaoDebito))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldTituloCredito)
                    .addComponent(jComboBoxCredito)
                    .addComponent(jTextFieldCredito))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jTextFieldTituloPre, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonPreOk)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jTextFieldPre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jTextFieldTituloCrediario, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonCrediarioOk)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jTextFieldCrediario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(37, 37, 37)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldTroco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonLimpar))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(0, 153, 255));

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

        jLabelF2.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabelF2.setForeground(new java.awt.Color(255, 255, 255));
        jLabelF2.setText("F2 - Finalizar Venda");

        jLabelF5.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabelF5.setForeground(new java.awt.Color(255, 255, 255));
        jLabelF5.setText("F5 - Limpar dados");

        jLabelF12.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabelF12.setForeground(new java.awt.Color(255, 255, 255));
        jLabelF12.setText("F12 - Fechar janela");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelF2)
                .addGap(36, 36, 36)
                .addComponent(jLabelF5)
                .addGap(18, 18, 18)
                .addComponent(jLabelF12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelBalloon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelLogoBalloon)
                .addGap(44, 44, 44))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelLogoBalloon, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelF2)
                        .addComponent(jLabelF5)
                        .addComponent(jLabelF12))
                    .addComponent(jLabelBalloon)))
        );

        javax.swing.GroupLayout jPanelRaizLayout = new javax.swing.GroupLayout(jPanelRaiz);
        jPanelRaiz.setLayout(jPanelRaizLayout);
        jPanelRaizLayout.setHorizontalGroup(
            jPanelRaizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelRaizLayout.setVerticalGroup(
            jPanelRaizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRaizLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelRaiz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelRaiz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelLogoBalloonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLogoBalloonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelLogoBalloonMouseClicked

    private void jButtonLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimparActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableResultado.getModel();
        model.setValueAt(0.0, 1, 2);
        model.setValueAt(0.0, 3, 2);
        model.setValueAt(0.0, 4, 2);
        model.setValueAt(0.0, 6, 2);
        model.setValueAt(0.0, 7, 2);
        model.setValueAt(0.0, 8, 2);
        jTextFieldTroco.setText("");
    }//GEN-LAST:event_jButtonLimparActionPerformed

    private void jTextFieldTrocoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTrocoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTrocoActionPerformed

    private void jTextFieldCrediarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCrediarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCrediarioActionPerformed

    private void jTextFieldTituloCrediarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTituloCrediarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTituloCrediarioActionPerformed

    private void jTextFieldTituloCrediarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldTituloCrediarioMouseClicked
        jTextFieldCrediario.requestFocus();
    }//GEN-LAST:event_jTextFieldTituloCrediarioMouseClicked

    private void jTextFieldPreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPreKeyReleased
        if(!(evt.getKeyCode() == KeyEvent.VK_ENTER)){
            if(verificaNaoEspacos(jTextFieldPre.getText())){
                jTextFieldPre.setText(configuraValorMaximo(evt,jTextFieldPre.getText(), 4, 2, 3));
                //    jTextFieldPre.setText(limitarTamanhoCampo(jTextFieldPre.getText()));
            }
            else{
                JOptionPane.showMessageDialog(rootPane, "Insira apenas números");
                jTextFieldPre.setText("");
            }
        }
    }//GEN-LAST:event_jTextFieldPreKeyReleased

    private void jTextFieldPreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPreKeyPressed
        configuraValorMaximo(evt,jTextFieldPre.getText(), 4, 2, 3);
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldPreKeyPressed

    private void jTextFieldPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPreActionPerformed

    private void jTextFieldTituloPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTituloPreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTituloPreActionPerformed

    private void jTextFieldTituloPreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldTituloPreMouseClicked
        jTextFieldPre.requestFocus();
    }//GEN-LAST:event_jTextFieldTituloPreMouseClicked

    private void jTextFieldCreditoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCreditoKeyReleased
        if(!(evt.getKeyCode() == KeyEvent.VK_ENTER)){
            if(verificaNaoEspacos(jTextFieldCredito.getText())){
                jTextFieldCredito.setText(configuraValorMaximo(evt,jTextFieldCredito.getText(), 7, 2, 5));
                //   jTextFieldCredito.setText(limitarTamanhoCampo(jTextFieldCredito.getText()));
            }
            else{
                JOptionPane.showMessageDialog(rootPane, "Insira apenas números");
                jTextFieldCredito.setText("");
            }
        }
    }//GEN-LAST:event_jTextFieldCreditoKeyReleased

    private void jTextFieldCreditoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCreditoKeyPressed
        configuraValorMaximo(evt,jTextFieldCredito.getText(), 7, 2, 5);
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldCreditoKeyPressed

    private void jTextFieldCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCreditoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCreditoActionPerformed

    private void jTextFieldTituloCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTituloCreditoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTituloCreditoActionPerformed

    private void jTextFieldTituloCreditoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldTituloCreditoMouseClicked
        jTextFieldCredito.requestFocus();
    }//GEN-LAST:event_jTextFieldTituloCreditoMouseClicked

    private void jTextFieldCartaoDebitoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCartaoDebitoKeyReleased
        if(!(evt.getKeyCode() == KeyEvent.VK_ENTER)){
            if(verificaNaoEspacos(jTextFieldCartaoDebito.getText())){
                jTextFieldCartaoDebito.setText(configuraValorMaximo(evt,jTextFieldCartaoDebito.getText(), 6, 2, 4));
                //  jTextFieldCartaoDebito.setText(limitarTamanhoCampo(jTextFieldCartaoDebito.getText()));
            }
            else{
                JOptionPane.showMessageDialog(rootPane, "Insira apenas números");
                jTextFieldCartaoDebito.setText("");
            }
        }
    }//GEN-LAST:event_jTextFieldCartaoDebitoKeyReleased

    private void jTextFieldCartaoDebitoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCartaoDebitoKeyPressed
        configuraValorMaximo(evt,jTextFieldCartaoDebito.getText(), 6, 2, 4);
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldCartaoDebitoKeyPressed

    private void jTextFieldCartaoDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCartaoDebitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCartaoDebitoActionPerformed

    private void jTextFieldTituloDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTituloDebitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTituloDebitoActionPerformed

    private void jTextFieldTituloDebitoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldTituloDebitoMouseClicked
        jTextFieldCartaoDebito.requestFocus();
    }//GEN-LAST:event_jTextFieldTituloDebitoMouseClicked

    private void jTextFieldChequeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldChequeKeyReleased
        if(!(evt.getKeyCode() == KeyEvent.VK_ENTER)){
            if(verificaNaoEspacos(jTextFieldCheque.getText())){
                jTextFieldCheque.setText(configuraValorMaximo(evt,jTextFieldCheque.getText(), 3, 2, 2));
                //  jTextFieldCheque.setText(limitarTamanhoCampo(jTextFieldCheque.getText()));
            }
            else{
                JOptionPane.showMessageDialog(rootPane, "Insira apenas números");
                jTextFieldCheque.setText("");
            }
        }
    }//GEN-LAST:event_jTextFieldChequeKeyReleased

    private void jTextFieldChequeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldChequeKeyPressed
        jTextFieldCheque.setText(configuraValorMaximo(evt,jTextFieldCheque.getText(), 3, 2, 2));
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldChequeKeyPressed

    private void jTextFieldChequeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldChequeActionPerformed

    }//GEN-LAST:event_jTextFieldChequeActionPerformed

    private void jTextFieldTituloChequeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTituloChequeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTituloChequeActionPerformed

    private void jTextFieldTituloChequeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldTituloChequeMouseClicked
        jTextFieldCheque.requestFocus();
    }//GEN-LAST:event_jTextFieldTituloChequeMouseClicked

    private void jTextFieldDinheiroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldDinheiroKeyReleased
        if(!(evt.getKeyCode() == KeyEvent.VK_ENTER)){
            if(verificaNaoEspacos(jTextFieldDinheiro.getText())){
                jTextFieldDinheiro.setText(configuraValorMaximo(evt,jTextFieldDinheiro.getText(), 1, 2, 1));
                //      jTextFieldDinheiro.setText(limitarTamanhoCampo(jTextFieldDinheiro.getText()));
            }
            else{
                JOptionPane.showMessageDialog(rootPane, "Insira apenas números");
                jTextFieldDinheiro.setText("");
            }
        }
    }//GEN-LAST:event_jTextFieldDinheiroKeyReleased

    private void jTextFieldDinheiroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldDinheiroKeyPressed
        configuraValorMaximo(evt,jTextFieldDinheiro.getText(), 1, 2, 1);
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldDinheiroKeyPressed

    private void jTextFieldDinheiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDinheiroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDinheiroActionPerformed

    private void jTextFieldTituloDinheiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTituloDinheiroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTituloDinheiroActionPerformed

    private void jTextFieldTituloDinheiroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldTituloDinheiroMouseClicked
        jTextFieldDinheiro.requestFocus();
    }//GEN-LAST:event_jTextFieldTituloDinheiroMouseClicked

    private void jTextFieldValeComprasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldValeComprasKeyReleased

    }//GEN-LAST:event_jTextFieldValeComprasKeyReleased

    private void jTextFieldValeComprasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldValeComprasKeyPressed
        configuraValorMaximo(evt,jTextFieldValeCompras.getText(), 1, 2, 1);
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldValeComprasKeyPressed

    private void jTextFieldValeComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldValeComprasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldValeComprasActionPerformed

    private void jTextFieldTituloValeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTituloValeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTituloValeActionPerformed

    private void jTextFieldTituloValeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldTituloValeMouseClicked
        jTextFieldValeCompras.requestFocus();
    }//GEN-LAST:event_jTextFieldTituloValeMouseClicked

    private void jTextFieldTotalApagarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTotalApagarKeyPressed

    }//GEN-LAST:event_jTextFieldTotalApagarKeyPressed

    private void jTextFieldTotalApagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTotalApagarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTotalApagarActionPerformed

    private void jTextFieldFaltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFaltaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFaltaActionPerformed

    private void jTextFieldCrediarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCrediarioKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldCrediarioKeyPressed

    private void jTextFieldTituloValeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTituloValeKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldTituloValeKeyPressed

    private void jTextFieldTituloDinheiroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTituloDinheiroKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldTituloDinheiroKeyPressed

    private void jTextFieldTituloChequeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTituloChequeKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldTituloChequeKeyPressed

    private void jTextFieldTituloDebitoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTituloDebitoKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldTituloDebitoKeyPressed

    private void jTextFieldTituloCreditoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTituloCreditoKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldTituloCreditoKeyPressed

    private void jTextFieldTituloPreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTituloPreKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldTituloPreKeyPressed

    private void jTextFieldTituloCrediarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTituloCrediarioKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldTituloCrediarioKeyPressed

    private void jTextFieldTrocoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTrocoKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldTrocoKeyPressed

    private void jComboBoxChequeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBoxChequeKeyPressed
        jTextFieldCheque.setText(configuraValorMaximo(evt,jTextFieldCheque.getText(), 3, 2, 2));
        atalhos(evt);
    }//GEN-LAST:event_jComboBoxChequeKeyPressed

    private void jComboBoxCreditoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBoxCreditoKeyPressed
        configuraValorMaximo(evt,jTextFieldCredito.getText(), 7, 2, 5);
        atalhos(evt);
    }//GEN-LAST:event_jComboBoxCreditoKeyPressed

    private void jTextFieldFaltaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldFaltaKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldFaltaKeyPressed

    private void jTableResultadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableResultadoKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTableResultadoKeyPressed

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
            java.util.logging.Logger.getLogger(JanelaFinalizarVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JanelaFinalizarVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JanelaFinalizarVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaFinalizarVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JanelaFinalizarVenda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCrediarioOk;
    private javax.swing.JButton jButtonLimpar;
    private javax.swing.JButton jButtonPreOk;
    private javax.swing.JComboBox<String> jComboBoxCheque;
    private javax.swing.JComboBox<String> jComboBoxCredito;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelBalloon;
    private javax.swing.JLabel jLabelF12;
    private javax.swing.JLabel jLabelF2;
    private javax.swing.JLabel jLabelF5;
    private javax.swing.JLabel jLabelLogoBalloon;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelRaiz;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableResultado;
    private javax.swing.JTextField jTextFieldCartaoDebito;
    private javax.swing.JTextField jTextFieldCheque;
    private javax.swing.JTextField jTextFieldCrediario;
    private javax.swing.JTextField jTextFieldCredito;
    private javax.swing.JTextField jTextFieldDinheiro;
    private javax.swing.JTextField jTextFieldFalta;
    private javax.swing.JTextField jTextFieldPre;
    private javax.swing.JTextField jTextFieldTituloCheque;
    private javax.swing.JTextField jTextFieldTituloCrediario;
    private javax.swing.JTextField jTextFieldTituloCredito;
    private javax.swing.JTextField jTextFieldTituloDebito;
    private javax.swing.JTextField jTextFieldTituloDinheiro;
    private javax.swing.JTextField jTextFieldTituloPre;
    private javax.swing.JTextField jTextFieldTituloVale;
    private javax.swing.JTextField jTextFieldTotalApagar;
    private javax.swing.JTextField jTextFieldTroco;
    private javax.swing.JTextField jTextFieldValeCompras;
    // End of variables declaration//GEN-END:variables
}
