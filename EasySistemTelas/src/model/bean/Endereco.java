
package model.bean;


public class Endereco {
    private String rua;
    private String numero;

    public Endereco(String rua, String numero) {
        this.rua = rua;
        this.numero = numero;
    }

    public String getRua() {
        return rua;
    }

    public String getNumero() {
        return numero;
    }
    
}
