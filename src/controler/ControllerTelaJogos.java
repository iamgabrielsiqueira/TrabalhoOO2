package controler;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.util.Callback;
import modelo.*;

import javax.xml.soap.Text;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

public class ControllerTelaJogos {
    
    @FXML
    private javafx.scene.text.Text IdTorneio;

    @FXML
    private DatePicker dataJogo;

    @FXML
    private ListView<Jogo> ltvwJogos;

    private ObservableList<Jogo> lista;

    private ObservableList<Time> listaTime;

    @FXML
    private ContextMenu listContextMenu;

    private Torneio item;

    private String nome;

    @FXML
    private ComboBox<Time> cbTime;

    @FXML
    private ComboBox<Time> cbTime2;

    @FXML
    private DialogPane telaJogos;

    public String nomeTorneio;

    public Button salvarJogo() {

        String time1 = String.valueOf(cbTime.getValue());
        String time2 = String.valueOf(cbTime2.getValue());
        LocalDate datadoJogo = dataJogo.getValue();

        if(datadoJogo == null) {
            System.out.println("Data invalida!");
        } else {
            System.out.println(datadoJogo);

            if(time1.equals(time2)) {
                System.out.println("Não é possivel!!!");
            } else {
                Jogo jogo = new Jogo(time1, time2, datadoJogo);

                Torneio t = ListaTorneio.getInstance().buscaTorneio(IdTorneio.getText());

                try {
                    t.addJogo(jogo);
                    ltvwJogos.setItems(FXCollections.observableArrayList(t.getJogo()));
                    System.out.println("Adicionado!");
                } catch (Exception e) {
                    System.out.println("Erro!!!");
                }
            }
        }

        return null;
    }

    @FXML
    private void actionDelete(){ }

    public void setTorneio(Torneio item) {

        this.item = item;
        IdTorneio.setText(item.getNome());

    }

    public void showJanelaTime(){

        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        dialog.setTitle("Times");
        dialog.initOwner(telaJogos.getScene().getWindow());

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../visao/telaTimes.fxml"));

        try{
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }catch (IOException e){
            System.out.println("Deu ruim ao abrir a janela que mantem jogos!!!" +e.getMessage());
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.PREVIOUS);

        Optional<ButtonType> result = dialog.showAndWait();

    }

    public Button addTime(){
        showJanelaTime();
        return null;
    }

    public void lancarResultado(Jogo item) {

        LocalDate hoje = LocalDate.now();

        if(item.getDataJogo().isAfter(hoje)) {
            System.out.println("Pode!!");
            System.out.println(item.getDataJogo());
            showJanelaResultado(item);
        } else if(item.getDataJogo().equals(hoje)) {
            System.out.println("É hoje");
        } else {
            System.out.println("Não pode");
        }
    }

    public void showJanelaResultado(Jogo item) {

        nomeTorneio = IdTorneio.getText();

        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        dialog.setTitle("Lançar Resultado");
        dialog.initOwner(telaJogos.getScene().getWindow());

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../visao/telaResultado.fxml"));

        try{
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }catch (IOException e){
            System.out.println("Deu ruim ao abrir a janela que lança resultado!!!" +e.getMessage());
            e.printStackTrace();
            return;
        }

        ControllerTelaResultado controllerTelaResultado = fxmlLoader.getController();
        controllerTelaResultado.setJogo(item, nomeTorneio);

        dialog.getDialogPane().getButtonTypes().add(ButtonType.PREVIOUS);

        Optional<ButtonType> result = dialog.showAndWait();
    }

    public void initialize() {

        listaTime = ListaTime.getInstance().lista();

        cbTime.setItems(listaTime);

        cbTime2.setItems(listaTime);

        lista = ListaJogo.getInstance().lista();

        listContextMenu = new ContextMenu();

        listContextMenu = new ContextMenu();

        MenuItem lancarResultado = new MenuItem("Lançar resultado");

        lancarResultado.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Jogo item = ltvwJogos.getSelectionModel().getSelectedItem();
                lancarResultado(item);
            }
        });


        listContextMenu.getItems().addAll(lancarResultado);

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
