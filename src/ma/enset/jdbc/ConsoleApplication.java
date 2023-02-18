package ma.enset.jdbc;

import ma.enset.jdbc.dao.*;
import ma.enset.jdbc.dao.entities.Consultation;
import ma.enset.jdbc.dao.entities.Medecin;
import ma.enset.jdbc.dao.entities.Patient;
import ma.enset.jdbc.services.ICabinetMetier;
import ma.enset.jdbc.services.ICabinetMetierImpl;

import java.util.ArrayList;
import java.util.List;

public class ConsoleApplication {
    public static void main(String[] args) {
        MedecinDao medecinDao = new MedecinDaoImpl();
        PatientDao patientDao = new PatientDaoImpl();
        ConsultationDao consultationDao=new ConsultationDaoImpl();
        ICabinetMetier cabinetMetier = new ICabinetMetierImpl(medecinDao, patientDao,consultationDao);
        System.out.println("*************Medecins**********************");
        List<Medecin> medecins = cabinetMetier.getAllMedecins();
        cabinetMetier.getAllMedecins().forEach(System.out::println);
        System.out.println("##########################################################");
        System.out.println(cabinetMetier.getMedecinById(2));

        Medecin m = new Medecin(5, "akasmiou", "ouassima", "ouassima@gmail", "0629595898");
        System.out.println("##########################################################");
        //cabinetMetier.AddMedecin(m);
        System.out.println("##########################################################");
        //cabinetMetier.DeleteMedecin(m);
        System.out.println("##########################################################");
        cabinetMetier.getMedecinByMc("ouassima").forEach(System.out::println);

        System.out.println("*************Patients**********************");
        List<Patient> patients = cabinetMetier.getAllPatients();
        cabinetMetier.getAllPatients().forEach(System.out::println);
        System.out.println("##########################################################");
        System.out.println(cabinetMetier.getPatientById(1));
        Patient p = new Patient(3,"doctor","mohamed","LG5965","0156496549","mohamed@gmail.com","20/08/1987");
        System.out.println("##########################################################");
        //cabinetMetier.AddPatient(p);
        System.out.println("##########################################################");
        cabinetMetier.DeletePatient(p);
        System.out.println("##########################################################");
        cabinetMetier.findPatientByMc("allach").forEach(System.out::println);

        System.out.println("*************Consultations**********************");
        List<Consultation> consultations = cabinetMetier.getAllConsultations();
        cabinetMetier.getAllConsultations().forEach(System.out::println);
        System.out.println("##########################################################");
        System.out.println(cabinetMetier.getConsultationById(2));
       Consultation c = new Consultation(1,"2022/02/02",patients.get(1),medecins.get(1));
        System.out.println("##########################################################");
        //cabinetMetier.AddConsultation(c);
        System.out.println("##########################################################");
        //cabinetMetier.DeleteConsultation(c);
        System.out.println("##########################################################");
        cabinetMetier.findConsultationByDate("2022/02/02").forEach(System.out::println);

    }


}
