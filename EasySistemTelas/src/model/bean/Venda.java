
package model.bean;




public class Venda {
    
    private int codigo;
    private Vendedor vendedor;
    private String dataVenda;
    private double total;

    public Venda(int codigo, Vendedor vendedor, String dataVenda, double total) {
        this.codigo = codigo;
        this.vendedor = vendedor;
        this.dataVenda = dataVenda;
        this.total = total;
    }
    
    public Venda(Vendedor vendedor, double total) {
        this.vendedor = vendedor;
        this.total = total;
    }
    
    public Venda(){}
    
    public Venda(String dataVenda, double total) {
        this.dataVenda = dataVenda;
        this.total = total;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public double getTotal() {
        return total;
    }
    
    public String getDataVenda() {
        return dataVenda;
    }
    
}
