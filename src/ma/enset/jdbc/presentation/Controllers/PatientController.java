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
import ma.enset.jdbc.dao.entities.Patient;
import ma.enset.jdbc.services.ICabinetMetier;
import ma.enset.jdbc.services.ICabinetMetierImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class PatientController implements Initializable {
    @FXML
    private TextField textNom;
    @FXML
    private TextField textPrenom;
    @FXML
    private TextField textCin;
    @FXML
    private TextField textTel;
    @FXML
    private TextField textEmail;
    @FXML
    private TextField textDate;
    @FXML
    private TableView<Patient> tablePatient;
    @FXML
    private TableColumn<Patient,Integer> colId;
    @FXML
    private TableColumn<Patient,String> colNom;
    @FXML
    private TableColumn<Patient,String> colPrenom;
    @FXML
    private TableColumn<Patient,String> colCin;
    @FXML
    private TableColumn<Patient,String> colTel;
    @FXML
    private TableColumn<Patient,String> colEmail;
    @FXML
    private TableColumn<Patient,String> colDate;
    @FXML
    private TextField textSearch;

    ObservableList<Patient> patients= FXCollections.observableArrayList();
    private ICabinetMetier cabinetMetierP;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cabinetMetierP=new ICabinetMetierImpl(new MedecinDaoImpl(), new PatientDaoImpl(),new ConsultationDaoImpl());
        colId.setCellValueFactory(new PropertyValueFactory<>("id_patient"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colCin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));
        tablePatient.setItems(patients);
        patients.addAll(cabinetMetierP.getAllPatients());
        loadPatients();
        textSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                patients.clear();
                patients.addAll(cabinetMetierP.findPatientByMc(newValue));

            }
        });
    }
    public void addPatient(){
        String nom= textNom.getText();
        String prenom= textPrenom.getText();
        String cin= textCin.getText();
        String tel= textTel.getText();
        String email= textEmail.getText();
        String date= textDate.getText();
        Patient c=new Patient();
        c.setNom(nom);
        c.setPrenom(prenom);
        c.setCin(cin);
        c.setTelephone(tel);
        c.setEmail(email);
        c.setDate_naissance(date);
        cabinetMetierP.AddPatient(c);
        textNom.clear();
        textPrenom.clear();
        textCin.clear();
        textTel.clear();
        textEmail.clear();
        textDate.clear();
        loadPatients();
    }
    public void deletePatient(){
        Patient c=tablePatient.getSelectionModel().getSelectedItem();
        cabinetMetierP.DeletePatient(c);
        loadPatients();
    }
    private void loadPatients(){
        patients.clear();
        patients.addAll(cabinetMetierP.getAllPatients());
    }
}
