
package model.bean;


class Telefone {
    private int codigo;
    private String telefone;

    public Telefone() {
    }

    public Telefone(int codigo, String telefone) {
        this.codigo = codigo;
        this.telefone = telefone;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getTelefone() {
        return telefone;
    }
    
}
