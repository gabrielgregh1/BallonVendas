/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javax.swing.JFrame;
import model.bean.Produto_vendido;
import model.dao.produto_vendidoDAO;

/**
 *
 * @author gabri
 */
public class JanelaEstatisticas extends javax.swing.JFrame {

    private final JFXPanel jfxPanel = new JFXPanel();
    
    private ObservableList<PieChart.Data> data2d = FXCollections.observableArrayList();
   
    private PieChart pieChart = new PieChart();
    
    public JanelaEstatisticas() {
        initComponents();
        setLocationRelativeTo(null);
        createScene();
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        jPanelGraficoProdutos.setLayout(new BorderLayout());
        jPanelGraficoProdutos.add(jfxPanel,BorderLayout.CENTER);
        condicaoInicial();
        
    }

   private void createScene(){
        Platform.runLater(() -> {
            pieChart.setTitle("Estatísticas de Março");
            pieChart.setLegendSide(Side.LEFT);
            pieChart.setData(getChartData());
            pieChart.setPrefSize(800, 400);
            pieChart.setMaxSize(800, 400);
            pieChart.setMinSize(800, 400);
            updateColors();
            jfxPanel.setScene(new Scene(pieChart));
        });
    }
    
    private ObservableList<PieChart.Data> getChartData(){
        data2d.addAll(new PieChart.Data("Comida Caseira",22.5), 
                      new PieChart.Data("Cantinho de Minas",20.5),
                      new PieChart.Data("Resenha Carioca",18.9),
                      new PieChart.Data("Comida Bahiana",15.8),
                      new PieChart.Data("Salada a Moda da Casa",11.8),
                      new PieChart.Data("Comida Massonica",10.4)         
        
        
        );
       
        return data2d;
    }
    
    private void updateColors(){
        Color[] colors = {Color.web("#00008B"), Color.web("0000FF"),
                          Color.web("#6495ED"), Color.web("00BFFF"),
                          Color.web("#87CEFA"), Color.web("B0C4DE") };
        int i = 0;
        
        for(PieChart.Data data : data2d){
            String hex = String.format("#%02X%02X%02X",
                        (int) (colors[i].getRed() * 225),
                        (int) (colors[i].getGreen() * 225),        
                        (int) (colors[i].getBlue() * 225) );
            data.getNode().setStyle("-fx-pie-color: "+hex+";");
            i++;        
            
        }
    
        Set<Node> items; items = pieChart.lookupAll("Label.chart-legend-item");
        i = 0;
        for(Node item: items){
            Label label = (Label) item;
            final Rectangle rectangle = new Rectangle(5, 5, colors[i]);
            label.setGraphic(rectangle);
            i++;
        }
    }
    
    private void setChartData(String resumida, double percentual){
        Platform.runLater(() -> {
           // data2d.clear();
            data2d.add(new PieChart.Data(resumida, percentual));
            updateColors();        

        });
        
        
        
        
    }
    public void condicaoFinal(){
        jLabel2.setVisible(true);
        jTextFieldTotalBruto.setVisible(true);
        jLabel1.setVisible(true);
        jLabel10.setVisible(true);
        jLabel11.setVisible(true);
        jLabel18.setVisible(true);
        jLabel19.setVisible(true);
        jLabel2.setVisible(true);
        jLabel20.setVisible(true);
        jLabel26.setVisible(true);
        jLabel6.setVisible(true);
        jLabel7.setVisible(true);
        jLabel8.setVisible(true);
        jLabelPrimeiro.setVisible(true);
        jLabelPrimeiroLucro.setVisible(true);
        jLabelPrimeiroLucrov.setVisible(true);
        jLabelSegundo.setVisible(true);
        jLabelSegundoLucro.setVisible(true);
        jLabelSegundoLucrov.setVisible(true);
        jLabelSextoLucrov.setVisible(true);
        jLabelSextoLucro.setVisible(true);
        jLabelSexto.setVisible(true);
        jLabelQuartoLucrov.setVisible(true);
        jLabelQuartoLucro.setVisible(true);
        jLabelQuarto.setVisible(true);
        jLabelQuintoLucrov.setVisible(true);
        jLabelQuintoLucro.setVisible(true);
        jLabelQuinto.setVisible(true);
        jLabelTerceiroLucrov.setVisible(true);
        jLabelTerceiroLucro.setVisible(true);
        jLabelTerceiro.setVisible(true);
        jLabelTerceiro.setVisible(true);
        jPanelGraficoProdutos.setVisible(true);
    }
    public void condicaoInicial(){
        jLabel2.setVisible(false);
        jTextFieldTotalBruto.setVisible(false);
        jLabel1.setVisible(false);
        jLabel10.setVisible(false);
        jLabel11.setVisible(false);
        jLabel18.setVisible(false);
        jLabel19.setVisible(false);
        jLabel20.setVisible(false);
        jLabel26.setVisible(false);
        jLabel6.setVisible(false);
        jLabel7.setVisible(false);
        jLabel8.setVisible(false);
        jLabelPrimeiro.setVisible(false);
        jLabelPrimeiroLucro.setVisible(false);
        jLabelPrimeiroLucrov.setVisible(false);
        jLabelSegundo.setVisible(false);
        jLabelSegundoLucro.setVisible(false);
        jLabelSegundoLucrov.setVisible(false);
        jLabelSextoLucrov.setVisible(false);
        jLabelSextoLucro.setVisible(false);
        jLabelSexto.setVisible(false);
        jLabelQuartoLucrov.setVisible(false);
        jLabelQuartoLucro.setVisible(false);
        jLabelQuarto.setVisible(false);
        jLabelQuintoLucrov.setVisible(false);
        jLabelQuintoLucro.setVisible(false);
        jLabelQuinto.setVisible(false);
        jLabelTerceiroLucrov.setVisible(false);
        jLabelTerceiroLucro.setVisible(false);
        jLabelTerceiro.setVisible(false);
        jLabelTerceiro.setVisible(false);
        jPanelGraficoProdutos.setVisible(false);
    }
    
    public void alteraGrafico(){
        Platform.runLater(() -> {
            data2d.clear();
        });
        condicaoFinal();
        if(jComboBoxMes.getSelectedIndex() != 0){
            produto_vendidoDAO daoVendido = new produto_vendidoDAO();
            int data = 0;
            double percentualTotal = 0;
            
            for(Produto_vendido pv : daoVendido.read()){
                data = Integer.parseInt(pv.getVenda().getDataVenda().substring(5, 7));
                if(data ==  jComboBoxMes.getSelectedIndex())
                    percentualTotal = percentualTotal + pv.getQtdProdutoVendido();
            }
            
            double soma = 0 ;
            int contador = 1;
            int limite = 0;
            for(Produto_vendido pv : daoVendido.read()){
                data = Integer.parseInt(pv.getVenda().getDataVenda().substring(5, 7));
                System.out.println(data);
                double percentual;
                List<PieChart>listaValores = new ArrayList(); 
                
                if(data ==  jComboBoxMes.getSelectedIndex()){                          
                    percentual = pv.getQtdProdutoVendido()*100/percentualTotal;

                    setChartData(pv.getProduto().getResumida() , percentual);
                    
                    soma = soma + pv.getVenda().getTotal();
                    
                    switch(contador){
                        case 1:
                            jLabelPrimeiro.setText(pv.getProduto().getResumida()+" ("+ pv.getQtdProdutoVendido() +")");
                            jLabelPrimeiroLucro.setText(pv.getProduto().getResumida() +" gerou um lucro bruto de R$");                        
                            jLabelPrimeiroLucrov.setText(String.valueOf(pv.getVenda().getTotal())+"0");                        
                            break;
                        case 2:
                            jLabelSegundo.setText(pv.getProduto().getResumida()+" ("+ pv.getQtdProdutoVendido() +")");
                            jLabelSegundoLucro.setText(pv.getProduto().getResumida() +" gerou um lucro bruto de R$");                   
                            jLabelSegundoLucrov.setText(String.valueOf(pv.getVenda().getTotal())+"0");                   
                            break;
                        case 3:
                            jLabelTerceiro.setText(pv.getProduto().getResumida()+" ("+ pv.getQtdProdutoVendido() +")");
                            jLabelTerceiroLucro.setText(pv.getProduto().getResumida() +" gerou um lucro bruto de R$");                    
                            jLabelTerceiroLucrov.setText(String.valueOf(pv.getVenda().getTotal())+"0");                   
                            break;
                        case 4:
                            jLabelQuarto.setText(pv.getProduto().getResumida()+" ("+ pv.getQtdProdutoVendido() +")");
                            jLabelQuartoLucro.setText(pv.getProduto().getResumida() +" gerou um lucro bruto de R$");                   
                            jLabelQuartoLucrov.setText(String.valueOf(pv.getVenda().getTotal())+"0");                  
                            break;
                        case 5:
                            jLabelQuinto.setText(pv.getProduto().getResumida()+"("+ pv.getQtdProdutoVendido() +")");
                            jLabelQuintoLucro.setText(pv.getProduto().getResumida() +" gerou um lucro bruto de R$");                   
                            jLabelQuintoLucrov.setText(String.valueOf(pv.getVenda().getTotal())+"0");                    
                            break;
                        case 6:
                            jLabelSexto.setText(pv.getProduto().getResumida()+" ("+ pv.getQtdProdutoVendido() +")");
                            jLabelSextoLucro.setText(pv.getProduto().getResumida() +" gerou um lucro bruto de R$");                   
                            jLabelSextoLucrov.setText(String.valueOf(pv.getVenda().getTotal())+"0");                 
                            break;
                        default:
                            break;
                    }
                    contador++;
                    limite++;
                    if(limite >= 6)
                        break;
                }
                
            }
            jTextFieldTotalBruto.setText(String.valueOf(soma));
            
        }
            
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane6 = new javax.swing.JTabbedPane();
        jPanelExibeGraficoProdutos5 = new javax.swing.JPanel();
        jPanelGraficoProdutos = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabelPrimeiro = new javax.swing.JLabel();
        jLabelSegundo = new javax.swing.JLabel();
        jLabelTerceiro = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jComboBoxMes = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabelPrimeiroLucro = new javax.swing.JLabel();
        jLabelSegundoLucro = new javax.swing.JLabel();
        jLabelTerceiroLucro = new javax.swing.JLabel();
        jLabelSexto = new javax.swing.JLabel();
        jLabelQuinto = new javax.swing.JLabel();
        jLabelQuarto = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabelSextoLucro = new javax.swing.JLabel();
        jLabelQuintoLucro = new javax.swing.JLabel();
        jLabelQuartoLucro = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jTextFieldTotalBruto = new javax.swing.JTextField();
        jButtonOk = new javax.swing.JButton();
        jLabelPrimeiroLucro1 = new javax.swing.JLabel();
        jLabelSegundoLucrov = new javax.swing.JLabel();
        jLabelTerceiroLucrov = new javax.swing.JLabel();
        jLabelQuartoLucrov = new javax.swing.JLabel();
        jLabelQuintoLucrov = new javax.swing.JLabel();
        jLabelSextoLucrov = new javax.swing.JLabel();
        jLabelPrimeiroLucrov = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabeIcon = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabelBalao = new javax.swing.JLabel();
        jLabelBallom = new javax.swing.JLabel();
        jLabeSair = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane6.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N

        jPanelExibeGraficoProdutos5.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanelGraficoProdutosLayout = new javax.swing.GroupLayout(jPanelGraficoProdutos);
        jPanelGraficoProdutos.setLayout(jPanelGraficoProdutosLayout);
        jPanelGraficoProdutosLayout.setHorizontalGroup(
            jPanelGraficoProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 487, Short.MAX_VALUE)
        );
        jPanelGraficoProdutosLayout.setVerticalGroup(
            jPanelGraficoProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 425, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel2.setText("Produtos mais ");

        jLabelPrimeiro.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelPrimeiro.setText("Comida Caseira (80 vendas)");

        jLabelSegundo.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelSegundo.setText("Cantinho de Minas (72 vendas)");

        jLabelTerceiro.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelTerceiro.setText("Resenha Carioca (67 vendas)");

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 153));
        jLabel6.setText("1°");

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 51, 204));
        jLabel7.setText("2°");

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 51, 204));
        jLabel8.setText("3°");

        jComboBoxMes.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jComboBoxMes.setForeground(new java.awt.Color(51, 51, 255));
        jComboBoxMes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione o mês", "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro" }));
        jComboBoxMes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setText("VENDIDOS:");

        jLabel11.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 255));
        jLabel11.setText("MARÇO");

        jLabelPrimeiroLucro.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelPrimeiroLucro.setText("Comida Caseira gerou um lucro bruto de R$");

        jLabelSegundoLucro.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelSegundoLucro.setText("Cantinho de Minas gerou um lucro bruto de R$");

        jLabelTerceiroLucro.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelTerceiroLucro.setText("Resenha Carioca gerou um lucro bruto de R$");

        jLabelSexto.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelSexto.setText("Comida Masonica (37 vendas)");

        jLabelQuinto.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelQuinto.setText("Salada a Moda da Casa (42 vendas)");

        jLabelQuarto.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelQuarto.setText("Comida Bahiana (56 vendas)");

        jLabel18.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 153));
        jLabel18.setText("4°");

        jLabel19.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 51, 204));
        jLabel19.setText("5°");

        jLabel20.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 51, 204));
        jLabel20.setText("6°");

        jLabelSextoLucro.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelSextoLucro.setText("Comida Masonica gerou um lucro bruto de R$");

        jLabelQuintoLucro.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelQuintoLucro.setText("Salada a Moda da Casa gerou um lucro bruto de R$");

        jLabelQuartoLucro.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelQuartoLucro.setText("Comida Bahiana gerou um lucro bruto de R$");

        jLabel26.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(51, 51, 255));
        jLabel26.setText("Lucro bruto total: ");

        jTextFieldTotalBruto.setForeground(new java.awt.Color(0, 0, 102));
        jTextFieldTotalBruto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 255)));

        jButtonOk.setText("OK");
        jButtonOk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButtonOkKeyPressed(evt);
            }
        });

        jLabelPrimeiroLucro1.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N

        jLabelSegundoLucrov.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelSegundoLucrov.setForeground(new java.awt.Color(0, 153, 255));
        jLabelSegundoLucrov.setText("000,00");

        jLabelTerceiroLucrov.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelTerceiroLucrov.setForeground(new java.awt.Color(0, 153, 255));
        jLabelTerceiroLucrov.setText("000,00");

        jLabelQuartoLucrov.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelQuartoLucrov.setForeground(new java.awt.Color(0, 153, 255));
        jLabelQuartoLucrov.setText("000,00");

        jLabelQuintoLucrov.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelQuintoLucrov.setForeground(new java.awt.Color(0, 153, 255));
        jLabelQuintoLucrov.setText("000,00");

        jLabelSextoLucrov.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelSextoLucrov.setForeground(new java.awt.Color(0, 153, 255));
        jLabelSextoLucrov.setText("000,00");

        jLabelPrimeiroLucrov.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelPrimeiroLucrov.setForeground(new java.awt.Color(0, 153, 255));
        jLabelPrimeiroLucrov.setText("000,00");

        javax.swing.GroupLayout jPanelExibeGraficoProdutos5Layout = new javax.swing.GroupLayout(jPanelExibeGraficoProdutos5);
        jPanelExibeGraficoProdutos5.setLayout(jPanelExibeGraficoProdutos5Layout);
        jPanelExibeGraficoProdutos5Layout.setHorizontalGroup(
            jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelExibeGraficoProdutos5Layout.createSequentialGroup()
                .addGroup(jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelExibeGraficoProdutos5Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelExibeGraficoProdutos5Layout.createSequentialGroup()
                                .addGroup(jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelExibeGraficoProdutos5Layout.createSequentialGroup()
                                        .addComponent(jComboBoxMes, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButtonOk))
                                    .addGroup(jPanelExibeGraficoProdutos5Layout.createSequentialGroup()
                                        .addGap(243, 243, 243)
                                        .addComponent(jLabel11))
                                    .addGroup(jPanelExibeGraficoProdutos5Layout.createSequentialGroup()
                                        .addGap(251, 251, 251)
                                        .addGroup(jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel18)
                                            .addComponent(jLabel19)
                                            .addComponent(jLabel20))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabelQuinto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabelSexto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabelQuarto, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanelExibeGraficoProdutos5Layout.createSequentialGroup()
                                        .addGap(64, 64, 64)
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel1)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel8))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabelPrimeiro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabelSegundo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabelTerceiro, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE))
                            .addGroup(jPanelExibeGraficoProdutos5Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelExibeGraficoProdutos5Layout.createSequentialGroup()
                                        .addComponent(jLabelPrimeiroLucro)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabelPrimeiroLucrov, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabelPrimeiroLucro1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelExibeGraficoProdutos5Layout.createSequentialGroup()
                                            .addComponent(jLabelSextoLucro)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabelSextoLucrov, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelExibeGraficoProdutos5Layout.createSequentialGroup()
                                            .addComponent(jLabelSegundoLucro)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabelSegundoLucrov, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGap(13, 13, 13))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(jPanelExibeGraficoProdutos5Layout.createSequentialGroup()
                                                .addComponent(jLabelQuintoLucro)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabelQuintoLucrov, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelExibeGraficoProdutos5Layout.createSequentialGroup()
                                                .addComponent(jLabelTerceiroLucro)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabelTerceiroLucrov, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelExibeGraficoProdutos5Layout.createSequentialGroup()
                                                .addComponent(jLabelQuartoLucro)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabelQuartoLucrov, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelExibeGraficoProdutos5Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldTotalBruto, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPanelGraficoProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanelExibeGraficoProdutos5Layout.setVerticalGroup(
            jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelExibeGraficoProdutos5Layout.createSequentialGroup()
                .addGroup(jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelExibeGraficoProdutos5Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonOk))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11)
                        .addGap(15, 15, 15)
                        .addGroup(jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(jLabel1))
                            .addGroup(jPanelExibeGraficoProdutos5Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabelPrimeiro)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabelSegundo)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabelTerceiro)
                                    .addComponent(jLabel8))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelQuarto)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelQuinto)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelSexto)
                            .addComponent(jLabel20))
                        .addGap(27, 27, 27)
                        .addGroup(jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelPrimeiroLucro)
                            .addComponent(jLabelPrimeiroLucro1)
                            .addComponent(jLabelPrimeiroLucrov))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelSegundoLucro)
                            .addComponent(jLabelSegundoLucrov, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelTerceiroLucro)
                            .addComponent(jLabelTerceiroLucrov))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelQuartoLucro)
                            .addComponent(jLabelQuartoLucrov))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelQuintoLucro)
                            .addComponent(jLabelQuintoLucrov))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelSextoLucro)
                            .addComponent(jLabelSextoLucrov))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldTotalBruto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26)))
                    .addGroup(jPanelExibeGraficoProdutos5Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jPanelGraficoProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(87, Short.MAX_VALUE))
        );

        jTabbedPane6.addTab("Gráfico dos Produtos", jPanelExibeGraficoProdutos5);

        jPanel1.setBackground(new java.awt.Color(0, 153, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 37, Short.MAX_VALUE)
        );

        jLabeIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconEstatisticas_80px.png"))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 153, 255));
        jLabel10.setText("Estátisticas Avançadas");

        jPanel2.setBackground(new java.awt.Color(0, 153, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));

        jLabelBalao.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelBalao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconBalloon_80px.png"))); // NOI18N

        jLabelBallom.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabelBallom.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBallom.setText("BALLOON");

        jLabeSair.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabeSair.setForeground(new java.awt.Color(255, 255, 255));
        jLabeSair.setText("F4 - Sair");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabeSair)
                .addGap(348, 348, 348)
                .addComponent(jLabelBallom)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelBalao)
                .addGap(4, 4, 4))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabelBallom)
                .addComponent(jLabeSair))
            .addComponent(jLabelBalao, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane6)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabeIcon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabeIcon)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane6)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonOkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonOkKeyPressed
        alteraGrafico();
    }//GEN-LAST:event_jButtonOkKeyPressed

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
            java.util.logging.Logger.getLogger(JanelaEstatisticas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JanelaEstatisticas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JanelaEstatisticas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaEstatisticas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JanelaEstatisticas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonOk;
    private javax.swing.JComboBox<String> jComboBoxMes;
    private javax.swing.JLabel jLabeIcon;
    private javax.swing.JLabel jLabeSair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelBalao;
    private javax.swing.JLabel jLabelBallom;
    private javax.swing.JLabel jLabelPrimeiro;
    private javax.swing.JLabel jLabelPrimeiroLucro;
    private javax.swing.JLabel jLabelPrimeiroLucro1;
    private javax.swing.JLabel jLabelPrimeiroLucrov;
    private javax.swing.JLabel jLabelQuarto;
    private javax.swing.JLabel jLabelQuartoLucro;
    private javax.swing.JLabel jLabelQuartoLucrov;
    private javax.swing.JLabel jLabelQuinto;
    private javax.swing.JLabel jLabelQuintoLucro;
    private javax.swing.JLabel jLabelQuintoLucrov;
    private javax.swing.JLabel jLabelSegundo;
    private javax.swing.JLabel jLabelSegundoLucro;
    private javax.swing.JLabel jLabelSegundoLucrov;
    private javax.swing.JLabel jLabelSexto;
    private javax.swing.JLabel jLabelSextoLucro;
    private javax.swing.JLabel jLabelSextoLucrov;
    private javax.swing.JLabel jLabelTerceiro;
    private javax.swing.JLabel jLabelTerceiroLucro;
    private javax.swing.JLabel jLabelTerceiroLucrov;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelExibeGraficoProdutos5;
    private javax.swing.JPanel jPanelGraficoProdutos;
    private javax.swing.JTabbedPane jTabbedPane6;
    private javax.swing.JTextField jTextFieldTotalBruto;
    // End of variables declaration//GEN-END:variables
}
