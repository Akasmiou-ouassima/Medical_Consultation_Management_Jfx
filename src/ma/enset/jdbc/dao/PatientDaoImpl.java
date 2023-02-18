package ma.enset.jdbc.dao;

import ma.enset.jdbc.dao.entities.Medecin;
import ma.enset.jdbc.dao.entities.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientDaoImpl implements PatientDao{
    @Override
    public List<Patient> findAll() {
        Connection connection = SingletonConnexionDb.getConnection();
        List<Patient> patients = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from PATIENTS");
            ResultSet rs=statement.executeQuery();
            while (rs.next()) {
                Patient p = new Patient();
                p.setId_patient(rs.getInt("ID_PATIENT"));
                p.setNom(rs.getString("NOM"));
                p.setPrenom(rs.getString("PRENOM"));
                p.setCin(rs.getString("CIN"));
                p.setTelephone(rs.getString("TELEPHONE"));
                p.setEmail(rs.getString("EMAIL"));
                p.setDate_naissance(rs.getString("DATE_NAISSANCE"));
                patients.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;

    }

    @Override
    public Patient findOne(int id) {
        Connection connection = SingletonConnexionDb.getConnection();
        PreparedStatement statement = null;
        Patient patient = new Patient();
        try {
            statement = connection.prepareStatement("select * from PATIENTS where ID_PATIENT= ?");

            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                patient.setId_patient(rs.getInt("ID_PATIENT"));
                patient.setNom(rs.getString("NOM"));
                patient.setPrenom(rs.getString("PRENOM"));
                patient.setCin(rs.getString("CIN"));
                patient.setEmail(rs.getString("EMAIL"));
                patient.setTelephone(rs.getString("TELEPHONE"));
                patient.setDate_naissance(rs.getString("DATE_NAISSANCE"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patient;
    }

    @Override
    public Patient save(Patient o) {
        Connection connection = SingletonConnexionDb.getConnection();

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("insert into PATIENTS(NOM,PRENOM,CIN,EMAIL,TELEPHONE,DATE_NAISSANCE)" + "values (?,?,?,?,?,?)");
            preparedStatement.setString(1, o.getNom());
            preparedStatement.setString(2, o.getPrenom());
            preparedStatement.setString(3, o.getCin());
            preparedStatement.setString(4, o.getEmail());
            preparedStatement.setString(5, o.getTelephone());
            preparedStatement.setString(6, o.getDate_naissance());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return o;
    }

    @Override
    public boolean delete(Patient o) {
        try {
            Connection connection = SingletonConnexionDb.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from PATIENTS where ID_PATIENT=?");
            preparedStatement.setInt(1, o.getId_patient());
            preparedStatement.executeUpdate();
        } catch (SQLException sql) {
            return false;
        }
        return true;
    }

    @Override
    public List<Patient> findPatientByMc(String mc) {
        Connection connection = SingletonConnexionDb.getConnection();
        PreparedStatement statement = null;
        List<Patient> patients = new ArrayList<>();
        try {
            statement = connection.prepareStatement("select * from PATIENTS"+" where NOM like ?"+"OR PRENOM like ?"+"OR CIN LIKE ?"+"OR TELEPHONE LIKE ?"+"OR EMAIL LIKE ?"+"OR DATE_NAISSANCE LIKE ?");

            statement.setString(1, mc);
            statement.setString(2, mc);
            statement.setString(3, mc);
            statement.setString(4, mc);
            statement.setString(5, mc);
            statement.setString(6, mc);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                patients.add(new Patient(rs.getInt("ID_PATIENT"),
                        rs.getString("NOM"),
                        rs.getString("PRENOM"),
                        rs.getString("CIN"),
                        rs.getString("TELEPHONE"),
                        rs.getString("EMAIL"),
                        rs.getString("DATE_NAISSANCE")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }
}
