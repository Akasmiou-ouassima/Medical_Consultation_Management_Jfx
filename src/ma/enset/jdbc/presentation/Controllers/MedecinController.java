package ma.enset.jdbc.presentation.Controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.enset.jdbc.dao.ConsultationDaoImpl;
import ma.enset.jdbc.dao.MedecinDaoImpl;
import ma.enset.jdbc.dao.PatientDaoImpl;
import ma.enset.jdbc.dao.entities.Medecin;
import ma.enset.jdbc.dao.entities.Patient;
import ma.enset.jdbc.services.ICabinetMetier;
import ma.enset.jdbc.services.ICabinetMetierImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class MedecinController implements Initializable {
    @FXML
    private TextField textNom;
    @FXML
    private TextField textPrenom;
    @FXML
    private TextField textEmail;
    @FXML
    private TextField textTel;

    @FXML
    private TableView<Medecin> tableMedecin;
    @FXML
    private TableColumn<Medecin,Integer> colId;
    @FXML
    private TableColumn<Medecin,String> colNom;
    @FXML
    private TableColumn<Medecin,String> colPrenom;
    @FXML
    private TableColumn<Medecin,String> colEmail;
    @FXML
    private TableColumn<Medecin,String> colTel;

    @FXML
    private TextField textSearch;

    ObservableList<Medecin> medecins= FXCollections.observableArrayList();
    private ICabinetMetier cabinetMetierM;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cabinetMetierM=new ICabinetMetierImpl(new MedecinDaoImpl(), new PatientDaoImpl(),new ConsultationDaoImpl());
        colId.setCellValueFactory(new PropertyValueFactory<>("id_medecin"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        tableMedecin.setItems(medecins);
        medecins.addAll(cabinetMetierM.getAllMedecins());
        loadPatients();
        textSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                medecins.clear();
                medecins.addAll(cabinetMetierM.getMedecinByMc(newValue));
            }
        });
    }
        public void addMedecin(){
        String nom= textNom.getText();
        String prenom= textPrenom.getText();
        String email= textEmail.getText();
        String tel= textTel.getText();
        Medecin c=new Medecin();
        c.setNom(nom);
        c.setPrenom(prenom);
        c.setEmail(email);
        c.setTel(tel);
        cabinetMetierM.AddMedecin(c);
        textNom.clear();
        textPrenom.clear();
            textEmail.clear();
        textTel.clear();
        loadPatients();
    }
    public void deleteMedecin(){
        Medecin c=tableMedecin.getSelectionModel().getSelectedItem();
        cabinetMetierM.DeleteMedecin(c);
        loadPatients();
    }
    private void loadPatients(){
       medecins.clear();
        medecins.addAll(cabinetMetierM.getAllMedecins());
    }
}
