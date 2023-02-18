package ma.enset.jdbc.dao;

import ma.enset.jdbc.dao.entities.Medecin;

import java.util.List;

public interface MedecinDao extends Dao<Medecin>{
    List<Medecin> findMedecinByMc(String mc);
}
