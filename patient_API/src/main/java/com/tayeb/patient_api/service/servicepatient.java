package com.tayeb.patient_api.service;


import com.tayeb.patient_api.entity.patient;
import com.tayeb.patient_api.repository.patientrepository;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class servicepatient implements Iservicepatient{
    @Autowired
    private patientrepository patientrepository;


    @Override
    @McpTool(description = "ajouter ou enregistrer patient")
    public patient createPatient(@McpToolParam(description = "patient avec nom,prenom,ville") patient patient) {
         return patientrepository.save(patient);
    }

    @Override
    @McpTool(description = "update patient ")
    public patient updatePatient(patient patient) {
        return patientrepository.save(patient);
    }

    @Override
    @McpTool(description = "delate patient by id")
    public void deletePatient(@McpToolParam(description = "this is patient id") long id) {
       patientrepository.deleteById(id);
    }



    @Override
    @McpTool(description = "find the patient by id")
    public patient getPatientById(@McpToolParam(description = "this is patient id")Long id) {
        return patientrepository.findById(id).orElse(null);
    }


    @Override
    @McpTool(description = "find all patient")
    public List<patient> getAllPatients() {
        return patientrepository.findAll();
    }
}
