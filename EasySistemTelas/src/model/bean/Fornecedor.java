
package model.bean;

public class Fornecedor {
    private int codigo;
    private String nome;

    public Fornecedor(int codigo) {
        this.codigo = codigo;
    }

    public Fornecedor(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }


}
