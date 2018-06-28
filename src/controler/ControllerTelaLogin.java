package controler;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import modelo.Cadastro;
import modelo.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.util.Optional;

public class ControllerTelaLogin {

    @FXML
    private VBox telaLogin;

    @FXML
    private TextField tfLogin;

    @FXML
    private PasswordField tfSenha;

    @FXML
    private Text erro01;

    @FXML
    private BorderPane telaJogador;

    private static Cadastro cadastro;

    private static String NOME_ARQ_BIN="arqSaida.bin";

    private ObservableList<User> usuarios;

    private static Cadastro instance;

    public void criarConta() {
        showJanelaCriarConta();
    }

    private void showJanelaCriarConta() {

        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        dialog.setTitle("Criar conta");
        dialog.initOwner(telaLogin.getScene().getWindow());

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../visao/telaCriarLogin.fxml"));

        try{
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }catch (IOException e){
            System.out.println("Deu ruim!!!" +e.getMessage());
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();

    }

    private void showJanelaAdmin(User item) {

        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        dialog.setTitle("Admin");
        dialog.initOwner(telaLogin.getScene().getWindow());

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../visao/telaAdmin.fxml"));
        try{
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }catch (IOException e){
            System.out.println("Deu ruim!!!" +e.getMessage());
            return;
        }

        ControllerTelaAdmin controllerTelaAdmin = fxmlLoader.getController();
        controllerTelaAdmin.setUser(item);

        dialog.getDialogPane().getButtonTypes().add(ButtonType.PREVIOUS);

        Optional<ButtonType> result = dialog.showAndWait();

    }

    private void showJanelaJogador(User item) {

        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        dialog.setTitle("Jogador");
        dialog.initOwner(telaLogin.getScene().getWindow());

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../visao/telaJogador.fxml"));

        try{
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }catch (IOException e){
            System.out.println("Deu ruim!!!" +e.getMessage());
            return;
        }

        ControllerTelaJogador controllerTelaJogador = fxmlLoader.getController();
        controllerTelaJogador.setUser(item);

        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();

    }


    public void logar() {
        String login = tfLogin.getText();
        String senha = tfSenha.getText();

        if(autenticar(login, senha) == true) {
            System.out.println("Autenticado!");

            User p =  Cadastro.getInstance().buscaUsuario(login, senha);

            if(p.getTipo() == 0) {
                showJanelaAdmin(p);
            } else if (p.getTipo() == 1) {
                showJanelaJogador(p);
            }

        } else {
            System.out.println("Falhou");
            erro01.setText("Usuário e senha incorretos!");
        }

    }

    private static void menuListar() {

        ArrayList<User> lista = new ArrayList<>(Cadastro.getInstance().lista());

        System.out.println("Existem " + lista.size() + " usuarios!");
        System.out.println("---");

        for (User u : lista) {
            mostraUsuario(u);
        }
    }

    private static void mostraUsuario(User u){
        System.out.println("Nome: " + u.getNome());
        System.out.println("Username: " + u.getUserName());
        System.out.println("Senha: " + u.getSenha());
        System.out.println("Tipo: " + u.getTipo());
        System.out.println("---");
    }

    public boolean autenticar(String login, String senha) {

        User p = Cadastro.getInstance().buscaUsuario(login, senha);

        if (p != null) {
            if(p.getUserName().equals(login) && p.getSenha().equals(senha)) {
                return true;
            }
        }

        return false;

    }

}
