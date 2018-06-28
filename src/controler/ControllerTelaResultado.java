package controler;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import modelo.*;

import java.util.List;

public class ControllerTelaResultado {

    @FXML
    public Text nomeTorneio;

    @FXML
    public Label lbTime1;

    @FXML
    public Label lbTime2;

    @FXML
    public TextField tfPonto1;

    @FXML
    public TextField tfPonto2;

    private Torneio item;

    public int ponto1;

    public int ponto2;

    public void setJogo(Jogo item, String nomeTorneio) {
        this.nomeTorneio.setText(nomeTorneio);
        this.lbTime1.setText(item.getNomeTime1());
        this.lbTime2.setText(item.getNomeTime2());
    }

    public Button salvarResultado() {
        ponto1 = Integer.parseInt(tfPonto1.getText());
        ponto2 = Integer.parseInt(tfPonto2.getText());

        String nomeDoTorneio;
        String time1;
        String time2;

        nomeDoTorneio = nomeTorneio.getText();
        time1 = lbTime1.getText();
        time2 = lbTime2.getText();

        Torneio t = ListaTorneio.getInstance().buscaTorneio(nomeDoTorneio);

        Jogo j = ListaJogo.getInstance().buscaJogo(time1, time2, t);

        try {
            j.setResultadoTime1(ponto1);
            j.setResultadoTime2(ponto2);
            System.out.println("Adicionado!");

        } catch (Exception e) {
            System.out.println("Erro!");
        }

        return null;
    }

    public void initialize() {

    }
}
