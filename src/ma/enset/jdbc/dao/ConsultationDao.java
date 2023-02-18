package ma.enset.jdbc.dao;

import ma.enset.jdbc.dao.entities.Consultation;
import ma.enset.jdbc.dao.entities.Medecin;

import java.util.List;

public interface ConsultationDao extends Dao<Consultation>{
    List<Consultation> findConsultationByDate(String date);
}
