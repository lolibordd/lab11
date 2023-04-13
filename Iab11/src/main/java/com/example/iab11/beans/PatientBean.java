package com.example.iab11.beans;

import com.example.iab11.dao.PatientDao;
import com.example.iab11.entities.Patient;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@Named
@SessionScoped
public class PatientBean implements Serializable {

    @EJB
    private PatientDao patientDao;
    private Patient patient = new Patient();

    private String diagnosis;
    private int start;
    private int end;
    private String prefix;

    public List<Patient> getPatients() {
        return patientDao.findAll();
    }

    public List<Patient> findPatientsByDiagnosis() {
        return patientDao.findPatientsByDiagnosis(diagnosis);
    }

    public List<Patient> findPatientsByCardNumberRange() {
        return patientDao.findPatientsByCardNumberRange(start, end);
    }

    public List<Patient> findPatientsByPhoneNumberPrefix() {
        return patientDao.findPatientsByPhoneNumberPrefix(prefix);
    }

    public Long countPatientsByPhoneNumberPrefix() {
        return patientDao.countPatientsByPhoneNumberPrefix(prefix);
    }

    public Map<String, Long> getDiagnosisCount() {
        return patientDao.getDiagnosisCount();
    }

    public List<String> getUniqueDiagnoses() {
        return patientDao.getUniqueDiagnoses();
    }

    public Map<String, Long> getPatientCountByDiagnosis() {
        return patientDao.getPatientCountByDiagnosis();
    }

}
