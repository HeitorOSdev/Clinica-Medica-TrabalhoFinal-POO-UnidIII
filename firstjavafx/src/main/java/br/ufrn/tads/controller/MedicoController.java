package br.ufrn.tads.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import br.ufrn.tads.model.Medico;
import br.ufrn.tads.service.MedicoService;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.util.Duration;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MedicoController {
    private MedicoService medicoService;

    @FXML
    private Label lblDateTime;

    @FXML
    private Button listButton;

    @FXML
    private Button addButton;

    @FXML
    private Button delButton;

    @FXML
    private Button updButton;

    @FXML
    private Button clnButton;

    @FXML
    private TableView<Medico> tbvMedicos;

    @FXML
    private TableColumn<Medico, Long> colId;

    @FXML
    private TableColumn<Medico, String> colCrm;

    @FXML
    private TableColumn<Medico, String> colNome;

    @FXML
    private TableColumn<Medico, String> colEspecialidade;

    @FXML
    private TableColumn<Medico, String> colTelefone;
    
    @FXML
    private TableColumn<Medico, String> colEmail;

    @FXML
    private ImageView imgvLogo;

    @FXML
    private TextField tfID;

    @FXML
    private TextField tfNome;
    
    @FXML
    private TextField tfCrm;
    
    @FXML
    private TextField tfEspecialidade;
    
    @FXML
    private TextField tfTelefone;
    
    @FXML
    private TextField tfEmail;


    public MedicoController() {
        // EXECUTADO PRIMEIRO
    	medicoService = new MedicoService();
    }

    @FXML
    public void initialize() {
        //EXECUTADO DEPOIS DO CONSTRUTOR
        
        Image img = new Image("listicon.png"); // Baixar uma imagem "de medico" para ca
        ImageView imgView = new ImageView(img);
        listButton.setGraphic(imgView);

        img = new Image("nometads.png"); //Vou tentar apenar melhorar essa imagem, na tela inicial (e deixar somente na inicial)
        imgvLogo.setImage(img);
        
        animateTimeLabel();

        //SE NÃO CRIOU COLUNAS NO SCENE BUILDER, ADICIONARIA AQUI OS TABLECOLUMNS
        //tbvProducts.getColumns().addAll(colId, colName, colQuantity, colValue);
        
        colId.setCellValueFactory(new PropertyValueFactory<>("id")); // colunas tal como no BD
        colCrm.setCellValueFactory(new PropertyValueFactory<>("crm"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colEspecialidade.setCellValueFactory(new PropertyValueFactory<>("especialidade"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        addButton.setVisible(true);
        updButton.setDisable(true);
        delButton.setDisable(true);
    }
    //método para obter itens da linha selecionada da tabela e copiar para o form com os text fields
    @FXML
    void getItem(MouseEvent event) {
        Integer idx = tbvMedicos.getSelectionModel().getSelectedIndex();
        if (idx <= -1) return;
        tfID.setText(String.valueOf(colId.getCellData(idx)));
        tfNome.setText(colNome.getCellData(idx));
        tfCrm.setText(colCrm.getCellData(idx));
        tfEspecialidade.setText(colEspecialidade.getCellData(idx));
        tfTelefone.setText(colTelefone.getCellData(idx));
        tfEmail.setText(colEmail.getCellData(idx));
        
        addButton.setDisable(true);
        updButton.setDisable(false);
        delButton.setDisable(false);
    }
    
    @FXML
    private void listMedicos(ActionEvent event) throws IOException {
                
        ObservableList<Medico> list = FXCollections.observableArrayList(medicoService.getMedicos());
        tbvMedicos.setItems(list);     
        
    }

    @FXML
    private void addMedico(ActionEvent event) throws IOException {
    	
    	
    	
        if (!tfNome.getText().isEmpty() && !tfCrm.getText().isEmpty()&& !tfEspecialidade.getText().isEmpty()&& !tfTelefone.getText().isEmpty()&& !tfEmail.getText().isEmpty()) {
           
        	String nome = tfNome.getText();
        	String crm = tfCrm.getText();
        	String especialidade= tfEspecialidade.getText();
        	String telefone= tfTelefone.getText();
        	String email = tfEmail.getText();
            Medico medico = new Medico(nome, crm, especialidade, telefone, email);

            if (medicoService.save(medico)) {
                tbvMedicos.getItems().add(medico); //inclui na tableview, mas sem novo ID, pois primeiro precisa persistir no BD
            }
        }   
    }

    @FXML
    void delMedico(ActionEvent event) {
        if (!tfNome.getText().isEmpty() && !tfCrm.getText().isEmpty() && !tfEspecialidade.getText().isEmpty() && !tfTelefone.getText().isEmpty() && !tfEmail.getText().isEmpty()) {
           if (medicoService.delete(Long.parseLong(tfID.getText()))) {
                int idx = tbvMedicos.getSelectionModel().getSelectedIndex();
                tbvMedicos.getItems().remove(idx); //inclui na tableview, mas sem novo ID, pois primeiro precisa persistir no BD
                tfID.clear();
                tfNome.clear();
                tfCrm.clear();
                tfEspecialidade.clear();
                tfTelefone.clear();
                tfEmail.clear();
                
            }
        }
    }

    @FXML
    void cleanForm(ActionEvent event) {
        if (!tfNome.getText().isEmpty() && !tfCrm.getText().isEmpty() && !tfEspecialidade.getText().isEmpty() && !tfTelefone.getText().isEmpty() && !tfEmail.getText().isEmpty()) {
        	tfID.clear();
            tfNome.clear();
            tfCrm.clear();
            tfEspecialidade.clear();
            tfTelefone.clear();
            tfEmail.clear();
            addButton.setDisable(false);
            updButton.setDisable(true);
            delButton.setDisable(true);
        }
    }

    @FXML
    void updProduct(ActionEvent event) {
        if (!tfNome.getText().isEmpty() && !tfCrm.getText().isEmpty() && !tfEspecialidade.getText().isEmpty() && !tfTelefone.getText().isEmpty() && !tfEmail.getText().isEmpty()) {
        	String nome = tfNome.getText();
        	String crm = tfCrm.getText();
        	String especialidade = tfEspecialidade.getText();
        	String telefone= tfTelefone.getText();
        	String email = tfEmail.getText();

        	
            Medico medico = new Medico (nome, crm, especialidade, telefone, email);
            medicoService.update(medico, null);        
        }
    }

    void animateTimeLabel() {
        DateTimeFormatter dtFmt = DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm:ss");

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            lblDateTime.setText(dtFmt.format(LocalDateTime.now()));
        }));
        timeline.setCycleCount(-1); //Animation.INDEFINITE (-1)
        timeline.play();
    }
}
