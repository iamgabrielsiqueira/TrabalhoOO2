package controler;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import modelo.ListaTime;
import modelo.Time;

public class ControllerTelaTimes {
    @FXML
    private TextField tfNomeTime;

    @FXML
    private Button salvarTime(){
        String nomeTime = tfNomeTime.getText();

        Time time = new Time(nomeTime);

        if(ListaTime.getInstance().buscaTime(nomeTime) != null) {
            System.out.println("Esse time já existe!");
        } else {
            ListaTime.getInstance().addTime(time);
            System.out.println("Time inserido!");
        }

        return null;
    }



}
