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

public class ListaTime {

    private ObservableList<Time> times;

    private static ListaTime instance;

    private static String NOME_ARQ_JSON="lista_time.json";

    public static DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static ListaTime getInstance(){

        if(instance==null){
            instance = new ListaTime();
        }

        return instance;
    }

    private ListaTime(){

        times = FXCollections.observableArrayList();

    }

    public void addTime(Time tm){
        times.add(tm);
    }

    public Time buscaTime(String nome){

        for(Time tm:times){
            if(tm.getNome() == nome){
                return tm;
            }
        }

        return null;
    }

    public boolean removeTime(String nome){

        Time tm = buscaTime(nome);

        if(tm != null){
            times.remove(tm);
            return true;
        }

        return false;
    }

    public ObservableList<Time> lista() {

        return this.times;
    }

    public void salvarJSon() throws IOException {
        Gson tarefaGson = new GsonBuilder().create();

        try(FileWriter fw = new FileWriter(NOME_ARQ_JSON)){
            ArrayList x = new ArrayList(times);
            tarefaGson.toJson(x,fw);
        }catch (IOException e) {
            throw e;
        }
    }

    public void lerJSon() throws IOException {

        Gson tarefaGson = new GsonBuilder().create();
        Type tipoLista = new TypeToken<ArrayList<Time>>(){}.getType();

        ArrayList<Time> listaTemp = new ArrayList<>();

        try(FileReader fr = new FileReader(NOME_ARQ_JSON)){
            listaTemp = tarefaGson.fromJson(fr, tipoLista);
            times = FXCollections.observableArrayList(listaTemp);
        }catch (IOException e){
            throw e;
        }

    }

}
