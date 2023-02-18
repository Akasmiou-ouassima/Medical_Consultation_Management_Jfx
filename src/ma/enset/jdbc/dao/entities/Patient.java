package ma.enset.jdbc.dao.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Patient {
    private int id_patient;
    private String nom;
    private String prenom;
    private String cin;
    private String telephone;
    private String email;
    private String date_naissance;
    List<Consultation> consultationsPatient=new ArrayList<>();

    public Patient() {
    }

    public Patient(int id_patient, String nom, String prenom, String cin, String telephone, String email, String date_naissance) {
        this.id_patient = id_patient;
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.telephone = telephone;
        this.email = email;
        this.date_naissance = date_naissance;
    }

    public Patient(String nom, String prenom, String cin, String telephone, String email, String date_naissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.telephone = telephone;
        this.email = email;
        this.date_naissance = date_naissance;
    }

    public int getId_patient() {
        return id_patient;
    }

    public void setId_patient(int id_patient) {
        this.id_patient = id_patient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(String date_naissance) {
        this.date_naissance = date_naissance;
    }

    public List<Consultation> getConsultationsPatient() {
        return consultationsPatient;
    }

    public void setConsultationsPatient(List<Consultation> consultationsPatient) {
        this.consultationsPatient = consultationsPatient;
    }

    @Override
    public String toString() {
        return  nom+" "+prenom ;
    }
}
