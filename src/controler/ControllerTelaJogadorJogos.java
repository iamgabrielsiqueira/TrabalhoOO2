package controler;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.util.Callback;
import modelo.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Optional;

public class ControllerTelaJogadorJogos {

    private Torneio item;

    private Jogo j;

    private ObservableList<Time> listaTime;

    private ObservableList<Jogo> lista;

    @FXML
    private Text txTime1;

    @FXML
    private Text txTime2;

    @FXML
    private ContextMenu listContextMenu;

    @FXML
    private javafx.scene.text.Text IdTorneio;

    @FXML
    private ListView<Jogo> ltvwJogos;

    @FXML
    private DialogPane telaJogadorJogos;

    @FXML
    private Text nomeJogador;

    private String jogador;

    private String torneio;

    public void setTorneio(Torneio item, String jogador) {

        this.item = item;
        IdTorneio.setText(item.getNome());
        nomeJogador.setText(jogador);

    }

    public void showJanelaPalpite(Jogo item) {

        jogador = nomeJogador.getText();
        torneio = IdTorneio.getText();

        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        dialog.setTitle("Palpitar");
        dialog.initOwner(telaJogadorJogos.getScene().getWindow());

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../visao/telaPalpitar.fxml"));

        try{
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }catch (IOException e){
            System.out.println("Deu ruim ao abrir a janela que palpita!!!" +e.getMessage());
            e.printStackTrace();
            return;
        }

        ControllerTelaPalpitar controllerTelaPalpitar = fxmlLoader.getController();
        controllerTelaPalpitar.setJogo(item, jogador, torneio);

        System.out.println("Controlador: " + item.getNomeTime1());

        dialog.getDialogPane().getButtonTypes().add(ButtonType.PREVIOUS);

        Optional<ButtonType> result = dialog.showAndWait();
    }

    public void initialize() {

        listaTime = ListaTime.getInstance().lista();

        lista = ListaJogo.getInstance().lista();

        listContextMenu = new ContextMenu();

        MenuItem palpitar = new MenuItem("Palpitar");

        palpitar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Jogo item = ltvwJogos.getSelectionModel().getSelectedItem();

                LocalDate hoje = LocalDate.now();

                if (item.getDataJogo().isAfter(hoje)) {
                    System.out.println("É depois de hoje");
                    showJanelaPalpite(item);
                } else {
                    System.out.println("É antes de hoje");
                }
            }
        });

        listContextMenu.getItems().addAll(palpitar);

        ltvwJogos.setItems(lista);

        ltvwJogos
                .getSelectionModel()
                .setSelectionMode(SelectionMode.SINGLE);

        ltvwJogos.setCellFactory(new Callback<ListView<Jogo>, ListCell<Jogo>>() {
            @Override
            public ListCell<Jogo> call(ListView<Jogo> param) {
                ListCell<Jogo> cell = new ListCell<Jogo>(){
                    @Override
                    protected void updateItem(Jogo item, boolean empty) {
                        super.updateItem(item, empty);
                        if(empty){
                            setText(null);
                        }else{
                            setText(item.getNomeTime1() + " x " + item.getNomeTime2());
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

}
