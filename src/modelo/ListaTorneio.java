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

public class ListaTorneio {

    private ObservableList<Torneio> torneios;

    private static ListaTorneio instance;

    private static String NOME_ARQ_JSON="lista_torneios.json";

    public static DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static ListaTorneio getInstance(){

        if(instance==null){
            instance = new ListaTorneio();
        }

        return instance;
    }

    private ListaTorneio(){

        torneios = FXCollections.observableArrayList();

    }

    public void addTorneio(Torneio t){
        torneios.add(t);
    }

    public Torneio buscaTorneio(String id){

        for(Torneio t:torneios){
            if(t.getNome() == id){
                return t;
            }
        }

        return null;
    }

    public boolean removeTorneio(String id){

        Torneio t = buscaTorneio(id);

        if(t != null){
            torneios.remove(t);
            return true;
        }

        return false;
    }

    public ObservableList<Torneio> lista() {

        return this.torneios;
    }

    public void salvarJSon() throws IOException {
        Gson tarefaGson = new GsonBuilder().create();

        try(FileWriter fw = new FileWriter(NOME_ARQ_JSON)){
            ArrayList x = new ArrayList(torneios);
            tarefaGson.toJson(x,fw);
        }catch (IOException e) {
            throw e;
        }
    }

    public void lerJSon() throws IOException {

        Gson tarefaGson = new GsonBuilder().create();
        Type tipoLista = new TypeToken<ArrayList<Torneio>>(){}.getType();

        ArrayList<Torneio> listaTemp = new ArrayList<>();

        try(FileReader fr = new FileReader(NOME_ARQ_JSON)){
            listaTemp = tarefaGson.fromJson(fr, tipoLista);
            torneios = FXCollections.observableArrayList(listaTemp);
        }catch (IOException e){
            throw e;
        }

    }

}
