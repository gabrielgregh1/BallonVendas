
package model.bean;


public class Produto_vendido {
    
    private int produto_vendido_codigo;
    private Produto produto;
    private Venda venda;
    private double qtdProdutoVendido;

    public Produto_vendido(int produto_vendido, Produto produto, Venda venda, double qtdProdutoVendido) {
        this.produto_vendido_codigo = produto_vendido;
        this.produto = produto;
        this.venda = venda;
        this.qtdProdutoVendido = qtdProdutoVendido;
    }
    
    public Produto_vendido(Produto produto, Venda venda) {
        this.produto = produto;
        this.venda = venda;
    }
    
    public Produto_vendido(Produto produto, Venda venda, double qtdProdutoVendido) {
        this.produto = produto;
        this.venda = venda;
        this.qtdProdutoVendido = qtdProdutoVendido;
    }

    public int getProduto_vendido() {
        return produto_vendido_codigo;
    }

    public Produto getProduto() {
        return produto;
    }

    public Venda getVenda() {
        return venda;
    }

    public int getProduto_vendido_codigo() {
        return produto_vendido_codigo;
    }

    public double getQtdProdutoVendido() {
        return qtdProdutoVendido;
    }
    
    
    
}
