import com.sun.org.apache.xml.internal.security.Init;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modelo.*;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void init() throws Exception {

        try {
            ListaTorneio.getInstance().lerJSon();
            ListaTime.getInstance().lerJSon();
            ListaPalpite.getInstance().lerJSon();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

        try {
            Cadastro.getInstance().lerBIN();
        }catch (IOException e){
            System.out.println("Problema ao ler os usuarios!! "+e.getMessage());
        }
    }

    @Override
    public void stop() throws Exception{
        try {
            ListaTorneio.getInstance().salvarJSon();
            ListaTime.getInstance().salvarJSon();
            ListaPalpite.getInstance().salvarJSon();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

        try {
            Cadastro.getInstance().salvarBIN();
        }catch (IOException e){
            System.out.println("Problema ao salvar os usuarios!! "+e.getMessage());
        }
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("visao/telaLogin.fxml"));
        primaryStage.setTitle("Palpiteros");
        primaryStage.setScene(new Scene(root, 500, 475));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
