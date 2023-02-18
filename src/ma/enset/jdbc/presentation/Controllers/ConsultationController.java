package ma.enset.jdbc.presentation.Controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.enset.jdbc.dao.ConsultationDaoImpl;
import ma.enset.jdbc.dao.MedecinDaoImpl;
import ma.enset.jdbc.dao.PatientDaoImpl;
import ma.enset.jdbc.dao.entities.Consultation;
import ma.enset.jdbc.dao.entities.Medecin;
import ma.enset.jdbc.dao.entities.Patient;
import ma.enset.jdbc.services.ICabinetMetier;
import ma.enset.jdbc.services.ICabinetMetierImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class ConsultationController implements Initializable {
    @FXML
    private TextField textDate;
    @FXML
    private ComboBox<Patient> patientComboBox;
    @FXML
    private ComboBox<Medecin> medecinComboBox;
    @FXML
    private TableView<Consultation> tableConsultation;
    @FXML
    private TableColumn<Consultation, Integer> colID;
    @FXML
    private TableColumn<Consultation, String> colDate;
    @FXML
    private TableColumn<Consultation, Patient> colPt;
    @FXML
    private TableColumn<Consultation, Medecin> colMed;
    @FXML
    private TextField textSearch;
    private ObservableList<Consultation> consultations = FXCollections.observableArrayList();
    private ICabinetMetier cabinetMetierC;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cabinetMetierC = new ICabinetMetierImpl(new MedecinDaoImpl(), new PatientDaoImpl(),new ConsultationDaoImpl());
        colID.setCellValueFactory(new PropertyValueFactory<>("id_consultation"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date_consultation"));
        colPt.setCellValueFactory(new PropertyValueFactory<>("patient"));
        colMed.setCellValueFactory(new PropertyValueFactory<>("medecin"));
        patientComboBox.getItems().addAll(cabinetMetierC.getAllPatients());
        medecinComboBox.getItems().addAll(cabinetMetierC.getAllMedecins());
        tableConsultation.setItems(consultations);
        consultations.addAll(cabinetMetierC.getAllConsultations());
        loadConsultations();
        textSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
               consultations.clear();
                consultations.addAll(cabinetMetierC.findConsultationByDate(newValue));
            }
        });
    }

    @FXML
    private void addConsultation() {
        String date= textDate.getText();
        Patient patient = patientComboBox.getSelectionModel().getSelectedItem();
        Medecin medecin=medecinComboBox.getSelectionModel().getSelectedItem();
        if (date.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(" Veuillez saisir la date de consultation !!!");
            alert.show();
        } else {
            Consultation p= new Consultation();
            p.setDate_consultation(date);
            p.setPatient(patient);
            p.setMedecin(medecin);
            cabinetMetierC.AddConsultation(p);
            textDate.clear();
           patientComboBox.getItems().clear();
           medecinComboBox.getItems().clear();
            loadConsultations();


        }
    }

    @FXML
    private void deleteConsultation() {
        MultipleSelectionModel<Consultation> mp=tableConsultation.getSelectionModel();

        if(mp!=null) {
            cabinetMetierC.DeleteConsultation(mp.getSelectedItem());
          consultations.remove((mp.getSelectedIndex()));
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(" Veuillez sélectionner un élement!!!");
            alert.show();
        }
    }



    private void loadConsultations() {
        consultations.clear();
        consultations.addAll(cabinetMetierC.getAllConsultations());

    }
}
