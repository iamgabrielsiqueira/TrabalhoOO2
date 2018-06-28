package controler;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import modelo.*;

public class ControllerTelaPalpitar {

    public Jogo jogo;

    @FXML
    public Label lbTime1;

    @FXML
    public Label lbTime2;

    @FXML
    public TextField tfPonto1;

    @FXML
    public TextField tfPonto2;

    @FXML
    public Text nomeJogador;

    @FXML
    public Text nomeTorneio;

    public int ponto1;

    public int ponto2;

    public String time1;

    public String time2;

    public String jogador;

    public String torneio;

    public void setJogo(Jogo item, String jogador, String torneio) {

        lbTime1.setText(item.getNomeTime1());
        lbTime2.setText(item.getNomeTime2());

        nomeJogador.setText(jogador);
        nomeTorneio.setText(torneio);

        //Palpite p = new Palpite();
    }

    @FXML
    public Button salvarPalpite() {

        int status = 0;

        ponto1 = Integer.parseInt(tfPonto1.getText());
        ponto2 = Integer.parseInt(tfPonto2.getText());
        time1 = lbTime1.getText();
        time2 = lbTime2.getText();
        jogador = nomeJogador.getText();
        torneio = nomeTorneio.getText();

        Torneio t = ListaTorneio.getInstance().buscaTorneio(torneio);

        System.out.println(t.getNome());

        for (Palpite p:ListaPalpite.getInstance().lista()) {
            if(p.getJogador().equals(jogador)) {
                System.out.println(p.getJogador());
                status++;
                if(p.getTorneio().equals(t)) {
                    System.out.println(t.getNome());
                }
            }
        }

        System.out.println(status);

        try {
            Palpite p = new Palpite(jogador, torneio, time1, time2, ponto1, ponto2);
            ListaPalpite.getInstance().addPalpite(p);
            System.out.println("Palpite adicionado!");
        } catch (Exception e) {
            System.out.println("Erro ao adicionar palpite!");
        }

        return null;
    }

    public void initialize() {

    }
}
