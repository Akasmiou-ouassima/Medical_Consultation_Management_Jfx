package ma.enset.jdbc.dao.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Consultation {
    private int id_consultation;
    private String date_consultation;
private Patient patient;
private Medecin medecin;


    public Consultation() {
    }

    public Consultation(int id_consultation, String date_consultation) {
        this.id_consultation = id_consultation;
        this.date_consultation = date_consultation;
    }

    public Consultation(int id_consultation, String date_consultation, Patient patient, Medecin medecin) {
        this.id_consultation = id_consultation;
        this.date_consultation = date_consultation;
        this.patient = patient;
        this.medecin = medecin;
    }

    public Consultation(String date_consultation, Patient patient, Medecin medecin) {
        this.date_consultation = date_consultation;
        this.patient = patient;
        this.medecin = medecin;
    }

    public int getId_consultation() {
        return id_consultation;
    }

    public void setId_consultation(int id_consultation) {
        this.id_consultation = id_consultation;
    }

    public String getDate_consultation() {
        return date_consultation;
    }

    public void setDate_consultation(String date_consultation) {
        this.date_consultation = date_consultation;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    @Override
    public String toString() {
        return "date_consultation='" + date_consultation + '\'' +
                ", patient=" + patient +
                ", medecin=" + medecin;
    }
}
