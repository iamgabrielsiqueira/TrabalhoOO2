package modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;

public class Cadastro {

    private ObservableList<User> usuarios;
    private static Cadastro instance;

    private static String NOME_ARQ_BIN="arqSaida.bin";

    public static Cadastro getInstance(){

        if(instance==null) {
            instance = new Cadastro();
        }

        return instance;
    }

    private Cadastro(){

        usuarios = FXCollections.observableArrayList();

    }

    public void addUsuario(User u) {
        usuarios.add(u);

    }

    public boolean buscaUsuarioLogin(String login){

        for(User u:usuarios){
            if(u.getUserName().equals(login)){
                return true;
            }
        }

        return false;
    }

    public User buscaUsuario(String login, String senha){

        for(User u:usuarios){
            if(u.getUserName().equals(login) && u.getSenha().equals(senha)){
                return u;
            }
        }

        return null;
    }

    public ObservableList<User> lista(){
        return this.usuarios;
    }

    public void salvarBIN() throws IOException{

        try(FileOutputStream fs = new FileOutputStream(new File(NOME_ARQ_BIN));
            ObjectOutputStream os = new ObjectOutputStream(fs)){

            for(User u:usuarios){
                os.writeObject(u);
            }

        }
    }

    public void lerBIN() throws IOException{

        try(FileInputStream fi = new FileInputStream(new File(NOME_ARQ_BIN));
            ObjectInputStream oi = new ObjectInputStream(fi)){

            usuarios.clear();

            try {

                while(true) {

                    User u = (User) oi.readObject();
                    usuarios.add(u);
                }

            }catch (ClassNotFoundException e){
                System.out.println("Problema!!!"+e.getMessage());
            }catch (EOFException e){
                System.out.println("Tudo lido!!!");
            }

        }
    }

}
