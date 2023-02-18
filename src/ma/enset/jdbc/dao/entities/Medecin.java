package ma.enset.jdbc.dao.entities;

import java.util.ArrayList;
import java.util.List;

public class Medecin {
    private int id_medecin;
    private String nom;
    private String prenom;
    private String email;
    private String tel;
    List<Consultation> consultationsMedecin =new ArrayList<>();

    public Medecin() {
    }

    public Medecin(int id_medecin, String nom, String prenom, String email, String tel) {
        this.id_medecin = id_medecin;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.tel = tel;
    }

    public Medecin(String nom, String prenom, String email, String tel) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.tel = tel;
    }

    public int getId_medecin() {
        return id_medecin;
    }

    public void setId_medecin(int id_medecin) {
        this.id_medecin = id_medecin;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public List<Consultation> getConsultationsMedecin() {
        return consultationsMedecin;
    }

    public void setConsultationsMedecin(List<Consultation> consultationsMedecin) {
        this.consultationsMedecin = consultationsMedecin;
    }

    @Override
    public String toString() {
        return   nom+" "+prenom;
    }
}
