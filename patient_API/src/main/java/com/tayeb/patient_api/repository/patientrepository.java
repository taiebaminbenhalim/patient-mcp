package com.tayeb.patient_api.repository;

import com.tayeb.patient_api.entity.patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface patientrepository extends JpaRepository<patient, Long> {
}
