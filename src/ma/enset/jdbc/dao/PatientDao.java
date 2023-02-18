package ma.enset.jdbc.dao;


import ma.enset.jdbc.dao.entities.Patient;

import java.util.List;

public interface PatientDao extends Dao<Patient>{
    List<Patient> findPatientByMc(String mc);

}
