package com.tayeb.patient_api;

import com.tayeb.patient_api.entity.patient;
import com.tayeb.patient_api.repository.patientrepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PatientApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientApiApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(patientrepository pr) {
        return args -> {

// Ajouter des patients
            pr.save(new patient(null, "Ali", "Ahmed", "Tunis"));
            pr.save(new patient(null, "Ben", "Salah", "Sfax"));
            pr.save(new patient(null, "Khaled", "Mourad", "Sousse"));

// Afficher les patients
            pr.findAll().forEach(p -> {
                System.out.println("ID: " + p.getId());
                System.out.println("Nom: " + p.getNom());
                System.out.println("Prénom: " + p.getPrenom());
                System.out.println("Ville: " + p.getVille());
                System.out.println("----------------------");
            });
        };
    };
}
