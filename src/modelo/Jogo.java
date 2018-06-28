package modelo;

import java.time.LocalDate;
import java.util.Date;

public class Jogo {

    private String nomeTime1;
    private String nomeTime2;
    private int resultadoTime1;
    private int resultadoTime2;
    private LocalDate dataJogo;

    public Jogo(String nomeTime1, String nomeTime2, LocalDate dataJogo) {

        this.nomeTime1 = nomeTime1;
        this.nomeTime2 = nomeTime2;
        this.dataJogo = dataJogo;
    }

    public String getNomeTime1() {
        return nomeTime1;
    }

    public void setNomeTime1(String nomeTime1) {
        this.nomeTime1 = nomeTime1;
    }

    public String getNomeTime2() {
        return nomeTime2;
    }

    public void setNomeTime2(String nomeTime2) {
        this.nomeTime2 = nomeTime2;
    }

    public int getResultadoTime1() {
        return resultadoTime1;
    }

    public void setResultadoTime1(int resultadoTime1) {
        this.resultadoTime1 = resultadoTime1;
    }

    public int getResultadoTime2() {
        return resultadoTime2;
    }

    public void setResultadoTime2(int resultadoTime2) {
        this.resultadoTime2 = resultadoTime2;
    }

    public LocalDate getDataJogo() {
        return dataJogo;
    }

    public void setDataJogo(LocalDate dataJogo) {
        this.dataJogo = dataJogo;
    }
}
