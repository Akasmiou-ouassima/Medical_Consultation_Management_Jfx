package ma.enset.jdbc.services;

import ma.enset.jdbc.dao.entities.Consultation;
import ma.enset.jdbc.dao.entities.Medecin;
import ma.enset.jdbc.dao.entities.Patient;

import java.util.List;

public interface ICabinetMetier {

    //Medecin

    List<Medecin> getAllMedecins();
    void AddMedecin(Medecin c);
    void DeleteMedecin(Medecin c);
    Medecin getMedecinById(int id);
    List<Medecin> getMedecinByMc(String mc);

    //Patient

    List<Patient> getAllPatients();
    void AddPatient(Patient p);
    void DeletePatient(Patient p);
    Patient getPatientById(int id);
    List<Patient> findPatientByMc(String mc);

    //Consultation

    List<Consultation> getAllConsultations();
    void AddConsultation(Consultation c);
    void DeleteConsultation(Consultation c);
    Consultation getConsultationById(int id);
    List<Consultation> findConsultationByDate(String date);
}
