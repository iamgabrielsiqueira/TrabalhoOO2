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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import modelo.*;

import javax.swing.plaf.basic.BasicButtonUI;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

public class ControllerTelaJogador {
    @FXML
    private javafx.scene.text.Text txNomeUser;
    @FXML
    private javafx.scene.text.Text txLoginUser;
    @FXML
    private javafx.scene.text.Text txTipoUser;
    @FXML
    private ListView<Torneio> ltvwTorneio;
    @FXML
    private ContextMenu listContextMenu;

    @FXML
    private BorderPane telaJogador;

    private ObservableList<Torneio> lista;

    private User item;

    private String jogador;

    public Button meusPalpites() {

        jogador = txLoginUser.getText();

        showJanelaMeusPalpites(jogador);

        return null;
    }

    private void showJanelaMeusPalpites(String jogador) {

        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        dialog.setTitle("Meus Palpites");
        dialog.initOwner(telaJogador.getScene().getWindow());

        FXMLLoader fxmlLoader = new FXMLLoader();

        fxmlLoader.setLocation(getClass().getResource("../visao/telaMeusPalpites.fxml"));

        try{
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }catch (IOException e){
            System.out.println("Deu ruim ao abrir a janela dos meus palpites!!!" +e.getMessage());
            e.printStackTrace();
            return;
        }

        ControllerTelaMeusPalpites controllerTelaMeusPalpites = fxmlLoader.getController();
        controllerTelaMeusPalpites.setJogador(jogador);

        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);

        Optional<ButtonType> result = dialog.showAndWait();

    }

    public User setUser(User item) {

        String tipo = "";

        this.item = item;

        txNomeUser.setText(this.item.getNome());
        txLoginUser.setText(this.item.getUserName());

        tipo = "Jogador";

        txTipoUser.setText(tipo);

        return item;

    }

    public void showJanelaJogos(Torneio item) {

        jogador = txLoginUser.getText();

        //System.out.println(jogador);

        ListaJogo.limpar();

        LocalDate hoje = LocalDate.now();

        for(Jogo j:item.getJogo()) {
            if(j.getDataJogo().isAfter(hoje)) {
                ListaJogo.getInstance().addJogo(j);
            }
        }

        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        dialog.setTitle("Jogos");
        dialog.initOwner(telaJogador.getScene().getWindow());

        FXMLLoader fxmlLoader = new FXMLLoader();

        fxmlLoader.setLocation(getClass().getResource("../visao/telaJogadorJogos.fxml"));

        try{
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }catch (IOException e){
            System.out.println("Deu ruim ao abrir a janela que mantem jogos!!!" +e.getMessage());
            e.printStackTrace();
            return;
        }

        ControllerTelaJogadorJogos controllerTelaJogadorJogos = fxmlLoader.getController();
        controllerTelaJogadorJogos.setTorneio(item, jogador);

        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);

        Optional<ButtonType> result = dialog.showAndWait();
    }

    public void initialize(){

        lista = ListaTorneio.getInstance().lista();

        listContextMenu = new ContextMenu();

        MenuItem mostrarJogos = new MenuItem("Mostrar jogos");

        mostrarJogos.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Torneio item = ltvwTorneio.getSelectionModel().getSelectedItem();
                showJanelaJogos(item);
            }
        });


        listContextMenu.getItems().addAll(mostrarJogos);

        ltvwTorneio.setItems(lista);

        ltvwTorneio
                .getSelectionModel()
                .setSelectionMode(SelectionMode.SINGLE);

        ltvwTorneio.setCellFactory(new Callback<ListView<Torneio>, ListCell<Torneio>>() {
            @Override
            public ListCell<Torneio> call(ListView<Torneio> param) {
                ListCell<Torneio> cell = new ListCell<Torneio>(){
                    @Override
                    protected void updateItem(Torneio item, boolean empty) {
                        super.updateItem(item, empty);
                        if(empty){
                            setText(null);
                        }else{
                            setText(item.getNome());
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
