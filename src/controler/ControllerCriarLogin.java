package controler;

import javafx.fxml.FXML;
import javafx.scene.control.DialogPane;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import modelo.Cadastro;
import modelo.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class ControllerCriarLogin {

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfUsername;

    @FXML
    private PasswordField tfSenha;

    @FXML
    private DialogPane criarLogin;

    @FXML
    private Text mensagem01;

    public void btEnviar() {
        String nome = tfNome.getText();
        String username = tfUsername.getText();
        String senha = tfSenha.getText();

        User user = new User(nome, username, senha);

        if(Cadastro.getInstance().buscaUsuarioLogin(username)) {
            System.out.printf("Esse usuario já existe");
            mensagem01.setText("Esse usuario já existe!");
        } else {
            Cadastro.getInstance().addUsuario(user);
            try {
                Cadastro.getInstance().salvarBIN();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mensagem01.setText("Usuário criado com sucesso!");
        }

    }

}