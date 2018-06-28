package modelo;

public class Palpite {
    private String jogador;
    private String torneio;
    private String time1;
    private String time2;
    private int palpite1;
    private int palpite2;
    private int status;

    public Palpite(String jogador, String torneio, String time1, String time2, int palpite1, int palpite2) {

        this.jogador = jogador;
        this.torneio = torneio;
        this.time1 = time1;
        this.time2 = time2;
        this.palpite1 = palpite1;
        this.palpite2 = palpite2;

    }

    public String getJogador() {
        return jogador;
    }

    public void setJogador(String jogador) {
        this.jogador = jogador;
    }

    public String getTorneio() {
        return torneio;
    }

    public void setTorneio(String torneio) {
        this.torneio = torneio;
    }

    public String getTime1() {
        return time1;
    }

    public void setTime1(String time1) {
        this.time1 = time1;
    }

    public String getTime2() {
        return time2;
    }

    public void setTime2(String time2) {
        this.time2 = time2;
    }

    public int getPalpite1() {
        return palpite1;
    }

    public void setPalpite1(int palpite1) {
        this.palpite1 = palpite1;
    }

    public int getPalpite2() {
        return palpite2;
    }

    public void setPalpite2(int palpite2) {
        this.palpite2 = palpite2;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
