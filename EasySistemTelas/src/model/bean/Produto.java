
package model.bean;




public class Produto {
    
    private int codigoProduto;
    private int controleExtra;
    private int codigoBarras;
    private String resumida;
    private String completa;
    private String dataInclusao;
    private String unidadeMedida;
    private double preco;
    private double valorVenda;
    private double custoSemImposto;
    private double custoUnidade;
    private double lucrativo;
    private double desconto;
    private double qtdMinima;
    private double qtdMaxima;
    private double qtdEstoque;
    private Fornecedor fornecedor;
    private long ncm;
    private long cest;

    public Produto(String resumida) {
        this.resumida = resumida;
    }
    
    public Produto(int codigoProduto, String resumida, double preco, double qtdEstoque){
        this.codigoProduto = codigoProduto;
        this.resumida = resumida;
        this.preco = preco;
        this.qtdEstoque = qtdEstoque;
    }
    
    public Produto(int codigoProduto, int controleExtra, int codigoBarras, String resumida,
                   String completa, String dataInclusao, String unidadeMedida, double preco, 
                   double valorVenda, double custoSemImposto, double custoUnidade, double lucrativo,
                   double desconto, Fornecedor fornecedor, long ncm, long cest,
                   double qtdMinima, double qtdMaxima, double qtdEstoque) {
        this.codigoProduto = codigoProduto;
        this.controleExtra = controleExtra;
        this.codigoBarras = codigoBarras;
        this.resumida = resumida;
        this.completa = completa;
        this.dataInclusao = dataInclusao;
        this.unidadeMedida = unidadeMedida;
        this.preco = preco;
        this.valorVenda = valorVenda;
        this.custoSemImposto = custoSemImposto;
        this.custoUnidade = custoUnidade;
        this.lucrativo = lucrativo;
        this.desconto = desconto;
        this.fornecedor = fornecedor;
        this.ncm = ncm;
        this.cest = cest;
        this.qtdMinima = qtdMinima;
        this.qtdMaxima = qtdMaxima;
        this.qtdEstoque = qtdEstoque;   
    }
    
    public Produto(int codigoProduto, int controleExtra, int codigoBarras, String resumida,
                   String completa, String unidadeMedida, double preco, 
                   double valorVenda, double custoSemImposto, double custoUnidade, double lucrativo,
                   double desconto, Fornecedor fornecedor, long ncm, long cest,
                   double qtdMinima, double qtdMaxima, double qtdEstoque) {
        this.codigoProduto = codigoProduto;
        this.controleExtra = controleExtra;
        this.codigoBarras = codigoBarras;
        this.resumida = resumida;
        this.completa = completa;
        this.unidadeMedida = unidadeMedida;
        this.preco = preco;
        this.valorVenda = valorVenda;
        this.custoSemImposto = custoSemImposto;
        this.custoUnidade = custoUnidade;
        this.lucrativo = lucrativo;
        this.desconto = desconto;
        this.fornecedor = fornecedor;
        this.ncm = ncm;
        this.cest = cest;
        this.qtdMinima = qtdMinima;
        this.qtdMaxima = qtdMaxima;
        this.qtdEstoque = qtdEstoque;
        
        
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public long getNcm() {
        return ncm;
    }

    public long getCest() {
        return cest;
    }

    public int getCodigoProduto() {
        return codigoProduto;
    }

    public int getControleExtra() {
        return controleExtra;
    }

    public int getCodigoBarras() {
        return codigoBarras;
    }

    public String getResumida() {
        return resumida;
    }

    public String getCompleta() {
        return completa;
    }

    public String getDataInclusao() {
        return dataInclusao;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public double getPreco() {
        return preco;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public double getCustoSemImposto() {
        return custoSemImposto;
    }

    public double getCustoUnidade() {
        return custoUnidade;
    }

    public double getLucrativo() {
        return lucrativo;
    }

    public double getDesconto() {
        return desconto;
    }

    public double getQtdMinima() {
        return qtdMinima;
    }

    public double getQtdMaxima() {
        return qtdMaxima;
    }

    public double getQtdEstoque() {
        return qtdEstoque;
    }

 




}