package com.tayeb.patient_api.service;

import com.tayeb.patient_api.entity.patient;

import java.util.List;

public interface Iservicepatient {
    public patient createPatient(patient patient);
    public patient updatePatient(patient patient);
    public void deletePatient(long id);
    public patient getPatientById(Long id);
    public List<patient> getAllPatients();
}
