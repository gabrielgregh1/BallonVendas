
package model.bean;


public class Cliente {
    private String nome;
    private String endereco;
    private int cpf;

    public Cliente(String nome, String endereco, int cpf) {
        this.nome = nome;
        this.endereco = endereco;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public int getCpf() {
        return cpf;
    }
    
    
}
