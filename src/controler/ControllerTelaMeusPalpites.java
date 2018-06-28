package controler;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Callback;
import modelo.*;

import java.time.LocalDate;

public class ControllerTelaMeusPalpites {

    @FXML
    public Text nomeJogador;

    private ObservableList<Palpite> listaPalpite;

    @FXML
    private ContextMenu listContextMenu;

    @FXML
    private ListView<Palpite> ltvwPalpite;

    public void setJogador(String jogador) {
        nomeJogador.setText(jogador);

        populaListView(jogador);

    }

    public void initialize() {

        ltvwPalpite
                .getSelectionModel()
                .setSelectionMode(SelectionMode.SINGLE);

        ltvwPalpite.setCellFactory(new Callback<ListView<Palpite>, ListCell<Palpite>>() {
            @Override
            public ListCell<Palpite> call(ListView<Palpite> param) {
                ListCell<Palpite> cell = new ListCell<Palpite>(){
                    @Override
                    protected void updateItem(Palpite item, boolean empty) {
                        super.updateItem(item, empty);
                        if(empty){
                            setText(null);
                        } else {
                            setText(item.getTime1() + ":" + item.getPalpite1() + " x " + item.getTime2() + ":" + item.getPalpite2());
                            if(item.getStatus()==1) {
                                setTextFill(Color.GREEN);
                            }
                        }
                    }
                };

                cell.emptyProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> obs, Boolean wasEmpty, Boolean isNowEmpty) {

                        if(isNowEmpty){
                            cell.setContextMenu(null);
                        }else{
                            cell.setContextMenu(listContextMenu);
                        }
                    }});


                return cell;

            }
        });

    }

    public void populaListView(String jogador) {

        ltvwPalpite.getItems().clear();

        for(Palpite p:ListaPalpite.getInstance().lista()) {
            if(p.getJogador().equals(jogador)) {
                ltvwPalpite.getItems().add(p);
            }
        }

    }

}
