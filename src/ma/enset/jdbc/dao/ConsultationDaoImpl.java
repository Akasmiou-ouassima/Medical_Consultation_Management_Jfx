package ma.enset.jdbc.dao;

import ma.enset.jdbc.dao.entities.Consultation;
import ma.enset.jdbc.dao.entities.Medecin;
import ma.enset.jdbc.dao.entities.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultationDaoImpl implements ConsultationDao{
    @Override
    public List<Consultation> findConsultationByDate(String date) {
        Connection connection = SingletonConnexionDb.getConnection();
        PreparedStatement statement = null;
        List<Consultation> consultations = new ArrayList<>();
        try {
            statement = connection.prepareStatement("select * from CONSULTATIONS"+" where DATE_CONSULTATION like ?");

            statement.setString(1, date);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                consultations.add(new Consultation(rs.getInt("ID_CONSULTATION"),
                        rs.getString("DATE_CONSULTATION")
                      ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consultations;
    }

    @Override
    public List<Consultation> findAll() {
        Connection connection = SingletonConnexionDb.getConnection();
        List<Consultation> consultations = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("select CL.ID_CONSULTATION,CL.DATE_CONSULTATION,MED.ID_MEDECIN,MED.NOM as 'NOM_MED',MED.PRENOM as 'PRENOM_MED',PT.ID_PATIENT,PT.NOM as 'NOM_PT',PT.PRENOM as 'PRENOM_PT' from CONSULTATIONS CL, MEDECINS MED, PATIENTS PT where CL.ID_PATIENT=PT.ID_PATIENT and CL.ID_MEDECIN=MED.ID_MEDECIN");
            ResultSet rs=null;

            rs = statement.executeQuery();


            while (rs.next()) {
                Consultation c = new Consultation();
               c.setId_consultation(rs.getInt("ID_CONSULTATION"));
                c.setDate_consultation(rs.getString("DATE_CONSULTATION"));
                Medecin med=new Medecin();
                med.setId_medecin(rs.getInt("ID_MEDECIN"));
                med.setNom(rs.getString("NOM_MED"));
                med.setPrenom(rs.getString("PRENOM_MED"));
                c.setMedecin(med);
                Patient pt=new Patient();
                pt.setId_patient(rs.getInt("ID_PATIENT"));
                pt.setNom(rs.getString("NOM_PT"));
                pt.setPrenom(rs.getString("PRENOM_PT"));
                c.setPatient(pt);
                consultations.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consultations;
    }

    @Override
    public Consultation findOne(int id) {
        Connection connection = SingletonConnexionDb.getConnection();
        PreparedStatement statement = null;
        Consultation consultation = new Consultation();
        try {
            statement=connection.prepareStatement("select CL.ID_CONSULTATION,CL.DATE_CONSULTATION,MED.ID_MEDECIN,MED.NOM as 'NOM_MED',MED.PRENOM as 'PRENOM_MED',PT.ID_PATIENT,PT.NOM as 'NOM_PT',PT.PRENOM as 'PRENOM_PT' from CONSULTATIONS CL, MEDECINS MED, PATIENTS PT where ID_CONSULTATION = ? and CL.ID_PATIENT=PT.ID_PATIENT and CL.ID_MEDECIN=MED.ID_MEDECIN");

            //statement = connection.prepareStatement("select * from CONSULTATIONS where ID_CONSULTATION = ?");

            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                consultation.setId_consultation(rs.getInt("ID_CONSULTATION"));
                consultation.setDate_consultation(rs.getString("DATE_CONSULTATION"));
                Medecin med=new Medecin();
                med.setId_medecin(rs.getInt("ID_MEDECIN"));
                med.setNom(rs.getString("NOM_MED"));
                med.setPrenom(rs.getString("PRENOM_MED"));
                consultation.setMedecin(med);
                Patient pt=new Patient();
                pt.setId_patient(rs.getInt("ID_PATIENT"));
                pt.setNom(rs.getString("NOM_PT"));
                pt.setPrenom(rs.getString("PRENOM_PT"));
                consultation.setPatient(pt);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consultation;
    }

    @Override
    public Consultation save(Consultation o) {
        Connection connection = SingletonConnexionDb.getConnection();

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("insert into CONSULTATIONS(DATE_CONSULTATION,ID_MEDECIN,ID_PATIENT)" + "values (?,?,?)");
            preparedStatement.setString(1,o.getDate_consultation());
            preparedStatement.setInt(2,o.getMedecin().getId_medecin());
            preparedStatement.setInt(3,o.getPatient().getId_patient());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return o;
    }

    @Override
    public boolean delete(Consultation o) {
        try {
            Connection connection = SingletonConnexionDb.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from CONSULTATIONS where ID_CONSULTATION =?");
            preparedStatement.setInt(1, o.getId_consultation());
            preparedStatement.executeUpdate();
        } catch (SQLException sql) {
            return false;
        }
        return true;
    }
}
