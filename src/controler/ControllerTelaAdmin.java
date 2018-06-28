package controler;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import modelo.*;

import java.io.IOException;
import java.util.Optional;

public class ControllerTelaAdmin {

    @FXML
    private ListView<Torneio> ltvwTorneio;

    private ObservableList<Torneio> lista;

    @FXML
    private ContextMenu listContextMenu;

    @FXML
    private TextField txTipo;

    @FXML
    private TextField textNome;

    @FXML
    private BorderPane telaAdmin;

    private User item;

    @FXML
    private Text txNomeUser;
    @FXML
    private Text txLoginUser;
    @FXML
    private Text txTipoUser;

    public Button salvarTorneio(){

        String tipo = txTipo.getText();
        String nome = textNome.getText();

        int id = 1;

        Torneio t = new Torneio(tipo, nome, id);

        ListaTorneio.getInstance().addTorneio(t);

        return null;
    }


    public void showJanelaJogo(Torneio item) {

        ListaJogo.limpar();

        for(Jogo j:item.getJogo()) {
            ListaJogo.getInstance().addJogo(j);
            System.out.println(j.getNomeTime1());
        }

        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        dialog.setTitle("Jogos");
        dialog.initOwner(telaAdmin.getScene().getWindow());

        FXMLLoader fxmlLoader = new FXMLLoader();

        fxmlLoader.setLocation(getClass().getResource("../visao/telaAdminJogos.fxml"));

        try{
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }catch (IOException e){
            System.out.println("Deu ruim ao abrir a janela que mantem jogos!!!" +e.getMessage());
            e.printStackTrace();
            return;
        }

        ControllerTelaJogos controllerTelaJogos = fxmlLoader.getController();
        controllerTelaJogos.setTorneio(item);

        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);

        Optional<ButtonType> result = dialog.showAndWait();

    }

    private void deleteTorneio(Torneio t){

        if(t != null){
            ListaTorneio.getInstance().removeTorneio(t.getNome());
        }
    }

    @FXML
    private void actionDelete(){

        Torneio t = ltvwTorneio.getSelectionModel().getSelectedItem();

        deleteTorneio(t);
    }

    public User setUser(User item) {

        String tipo = "";

        this.item = item;

        txNomeUser.setText(this.item.getNome());
        txLoginUser.setText(this.item.getUserName());

        tipo = "Administrador";

        txTipoUser.setText(tipo);

        return item;

    }

    public void initialize() {

        lista = ListaTorneio.getInstance().lista();

        listContextMenu = new ContextMenu();

        MenuItem deleteMenuItem = new MenuItem("Deletar");

        MenuItem gerarJogos = new MenuItem("Gerar jogos");

        deleteMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Torneio item = ltvwTorneio.getSelectionModel().getSelectedItem();
                deleteTorneio(item);
            }
        });

        gerarJogos.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Torneio item = ltvwTorneio.getSelectionModel().getSelectedItem();
                showJanelaJogo(item);
            }
        });


        listContextMenu.getItems().addAll(deleteMenuItem);
        listContextMenu.getItems().addAll(gerarJogos);

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

    private void populaListView() {

        ltvwTorneio.getItems().clear();

        for(Torneio t:ListaTorneio.getInstance().lista()) {
            ltvwTorneio.getItems().add(t);
        }

    }

}
