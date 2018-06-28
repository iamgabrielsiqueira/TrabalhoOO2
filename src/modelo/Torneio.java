package modelo;


import java.util.ArrayList;

public class Torneio {

    private String tipo;
    private String nome;
    private int idTorneio;
    private ArrayList<Jogo> jogo;

    public Torneio(String tipo, String nome, int idTorneio) {
        this.tipo = tipo;
        this.nome = nome;
        this.jogo = new ArrayList<>();
        this.idTorneio = idTorneio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String toString(){
        return this.nome;
    }

    public int getIdTorneio() {
        return idTorneio;
    }

    public void setIdTorneio(int idTorneio) {
        this.idTorneio = idTorneio;
    }

    public void addJogo(Jogo jogo) {
        this.jogo.add(jogo);
    }

    public ArrayList<Jogo> getJogo() {
        return jogo;
    }
}
