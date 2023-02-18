package ma.enset.jdbc.dao;

import ma.enset.jdbc.dao.entities.Consultation;
import ma.enset.jdbc.dao.entities.Medecin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedecinDaoImpl implements MedecinDao{

    @Override

    public List<Medecin> findMedecinByMc(String mc){
        Connection connection = SingletonConnexionDb.getConnection();
        PreparedStatement statement = null;
        List<Medecin> medecins = new ArrayList<>();
        try {
            statement = connection.prepareStatement("select * from MEDECINS"+" where NOM like ?"+"OR PRENOM like ?"+"OR EMAIL LIKE ?"+"OR TELEPHONE LIKE ?");

            statement.setString(1, mc);
            statement.setString(2, mc);
            statement.setString(3, mc);
            statement.setString(4, mc);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                medecins.add(new Medecin(rs.getInt("ID_MEDECIN"),
                        rs.getString("NOM"),
                        rs.getString("PRENOM"),
                        rs.getString("EMAIL"),
                        rs.getString("TELEPHONE")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medecins;
    }
    @Override
    public List<Medecin> findAll(){
        Connection connection = SingletonConnexionDb.getConnection();
        List<Medecin> medecins = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from MEDECINS");
            ResultSet rs=statement.executeQuery();
            while (rs.next()) {
                Medecin c = new Medecin();
                c.setId_medecin(rs.getInt("ID_MEDECIN"));
                c.setNom(rs.getString("NOM"));
                c.setPrenom(rs.getString("PRENOM"));
                c.setEmail(rs.getString("EMAIL"));
                c.setTel(rs.getString("TELEPHONE"));
                medecins.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medecins;

    }

    @Override
    public Medecin findOne(int id) {
        Connection connection = SingletonConnexionDb.getConnection();
        PreparedStatement statement = null;
        Medecin medecin = new Medecin();
        try {
            statement = connection.prepareStatement("select * from MEDECINS where ID_MEDECIN = ?");

            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                medecin.setId_medecin(rs.getInt("ID_MEDECIN"));
                medecin.setNom(rs.getString("NOM"));
               medecin.setPrenom(rs.getString("PRENOM"));
               medecin.setEmail(rs.getString("EMAIL"));
               medecin.setTel(rs.getString("TELEPHONE"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medecin;
    }

    @Override
    public Medecin save(Medecin o) {
        Connection connection = SingletonConnexionDb.getConnection();

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("insert into MEDECINS(NOM,PRENOM,EMAIL,TELEPHONE)" + "values (?,?,?,?)");
            preparedStatement.setString(1, o.getNom());
            preparedStatement.setString(2, o.getPrenom());
            preparedStatement.setString(3, o.getEmail());
            preparedStatement.setString(4, o.getTel());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return o;
    }

    @Override
    public boolean delete(Medecin o) {
        try {
            Connection connection = SingletonConnexionDb.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from MEDECINS where ID_MEDECIN =?");
            preparedStatement.setInt(1, o.getId_medecin());
            preparedStatement.executeUpdate();
        } catch (SQLException sql) {
            return false;
        }
        return true;
    }


}
