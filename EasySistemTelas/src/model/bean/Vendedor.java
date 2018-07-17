

package model.bean;



public class Vendedor {
    
    private String nome;
    private int codigoVendedor;

    public Vendedor(int codigoVendedor) {
        this.codigoVendedor = codigoVendedor;
    }
    
    public Vendedor(String nome) {
        this.nome = nome;
    }
    public Vendedor(int codigoVendedor, String nome) {
        this.codigoVendedor = codigoVendedor;
        this.nome = nome;
    }
    
    public String getNome() {
        return nome;
    }

    public int getCodigoVendedor() {
        return codigoVendedor;
    }

    
}
