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

public class ListaPalpite {
    private ObservableList<Palpite> palpites;

    private static ListaPalpite instance;

    private static String NOME_ARQ_JSON="lista_palpites.json";

    public static ListaPalpite getInstance(){

        if(instance==null){
            instance = new ListaPalpite();
        }

        return instance;
    }

    private ListaPalpite(){

        palpites = FXCollections.observableArrayList();

    }

    public void addPalpite(Palpite p){
        palpites.add(p);
    }

    public Palpite buscaPalpite(String jogador){

        for(Palpite p:palpites){
            if(p.getJogador() == jogador){
                return p;
            }
        }

        return null;
    }

    public ObservableList<Palpite> lista() {

        return this.palpites;
    }

    public void salvarJSon() throws IOException {
        Gson palpiteGson = new GsonBuilder().create();

        try(FileWriter fw = new FileWriter(NOME_ARQ_JSON)){
            ArrayList x = new ArrayList(palpites);
            palpiteGson.toJson(x,fw);
        }catch (IOException e) {
            throw e;
        }
    }

    public void lerJSon() throws IOException {

        Gson palpiteGson = new GsonBuilder().create();
        Type tipoLista = new TypeToken<ArrayList<Palpite>>(){}.getType();

        ArrayList<Palpite> listaTemp = new ArrayList<>();

        try(FileReader fr = new FileReader(NOME_ARQ_JSON)){
            listaTemp = palpiteGson.fromJson(fr, tipoLista);
            palpites = FXCollections.observableArrayList(listaTemp);
        }catch (IOException e){
            throw e;
        }

    }

    public static void limpar() {
        instance = null;
    }
}
