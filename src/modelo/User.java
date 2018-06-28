package modelo;

import java.io.Serializable;

public class User implements Serializable {

    private String nome;
    private String userName;
    private String senha;
    private int tipo;

    public User(String nome, String userName, String senha) {
        this.nome = nome;
        this.userName = userName;
        this.senha = senha;

        if(nome.contains("admin")) {
            this.tipo = 0;
        } else {
            this.tipo = 1;
        }

    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getTipoStrig() {
        if(tipo==0){return "Administrador";}else{return "jogador";}
    }
}
