package modelo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ListaJogo {

    private ObservableList<Jogo> jogos;
    private ObservableList<Jogo> jogosAux = FXCollections.observableArrayList();

    private static ListaJogo instance;
    public static DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static ListaJogo getInstance(){

        if(instance==null){
            instance = new ListaJogo();
        }

        return instance;
    }

    public Jogo buscaJogo(String time1, String time2, Torneio t){

        for (Jogo jogo: t.getJogo()) {
            if(jogo.getNomeTime1().equals(time1) && jogo.getNomeTime2().equals(time2)) {
                return jogo;
            }
        }

        return null;
    }

    private ListaJogo(){

        jogos = FXCollections.observableArrayList();

    }

    public ObservableList<Jogo> lista() {
        return this.jogos;
    }

    public void addJogo(Jogo j){
        jogos.add(j);
    }

    public static void limpar() {
        instance = null;
    }

}
