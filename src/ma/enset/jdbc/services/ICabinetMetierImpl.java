package ma.enset.jdbc.services;

import ma.enset.jdbc.dao.ConsultationDao;
import ma.enset.jdbc.dao.MedecinDao;
import ma.enset.jdbc.dao.PatientDao;
import ma.enset.jdbc.dao.entities.Consultation;
import ma.enset.jdbc.dao.entities.Medecin;
import ma.enset.jdbc.dao.entities.Patient;

import java.util.List;

public class ICabinetMetierImpl implements ICabinetMetier {
    private MedecinDao medecinDao;
    private PatientDao patientDao;
    private ConsultationDao consultationDao;

    public ICabinetMetierImpl(MedecinDao medecinDao, PatientDao patientDao,ConsultationDao consultationDao) {
        this.medecinDao = medecinDao;
        this.patientDao = patientDao;
        this.consultationDao=consultationDao;
    }

    @Override
    public List<Medecin> getAllMedecins() {
        return medecinDao.findAll();
    }

    @Override
    public void AddMedecin(Medecin c) {
        medecinDao.save(c);
    }

    @Override
    public void DeleteMedecin(Medecin c) {
        medecinDao.delete(c);
    }

    @Override
    public Medecin getMedecinById(int id) {
        return medecinDao.findOne(id);
    }

    @Override
    public List<Medecin> getMedecinByMc(String mc) {
        return medecinDao.findMedecinByMc(mc);
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientDao.findAll();
    }

    @Override
    public void AddPatient(Patient p) {
        patientDao.save(p);
    }

    @Override
    public void DeletePatient(Patient p) {
        patientDao.delete(p);
    }

    @Override
    public Patient getPatientById(int id) {
        return patientDao.findOne(id);
    }

    @Override
    public List<Patient> findPatientByMc(String mc) {
        return patientDao.findPatientByMc(mc);
    }

    @Override
    public List<Consultation> getAllConsultations() {
        return consultationDao.findAll();
    }

    @Override
    public void AddConsultation(Consultation c) {
    consultationDao.save(c);
    }

    @Override
    public void DeleteConsultation(Consultation c) {
            consultationDao.delete(c);
    }

    @Override
    public Consultation getConsultationById(int id) {
        return consultationDao.findOne(id);
    }

    @Override
    public List<Consultation> findConsultationByDate(String date) {
        return consultationDao.findConsultationByDate(date);
    }
}
