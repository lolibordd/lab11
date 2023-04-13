package com.example.iab11.dao;

import com.example.iab11.entities.Patient;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.*;

@Stateless
public class PatientDao {
    @PersistenceContext
    private EntityManager em;

    public List<Patient> findAll() {
        return em.createNamedQuery("Patient.findAll", Patient.class).getResultList();
    }

    //a) список пацієнтів, які мають вказаний діагноз в порядку зростання номерів медичної картки
    public List<Patient> findPatientsByDiagnosis(String diagnosis) {
        TypedQuery<Patient> query = em.createNamedQuery("Patient.FindPatientsByDiagnosis", Patient.class);
        query.setParameter("diagnosis", diagnosis);
        return query.getResultList();
    }

    //b) Список пацієнтів, номер медичної карти у яких знаходиться в заданому інтервалі
    public List<Patient> findPatientsByCardNumberRange(int start, int end) {
        TypedQuery<Patient> query = em.createNamedQuery("Patient.FindPatientsByCardNumberRange", Patient.class);
        query.setParameter("start", start);
        query.setParameter("end", end);
        return query.getResultList();
    }

    //c) Кількість та список пацієнтів, номер телефона яких починається з вказаної цифри
    public List<Patient> findPatientsByPhoneNumberPrefix(String prefix) {
        TypedQuery<Patient> query = em.createNamedQuery("Patient.FindPatientsByPhoneNumberPrefix", Patient.class);
        query.setParameter("prefix", prefix + "%");
        List<Patient> results = query.getResultList();
        return results;
    }

    public Long countPatientsByPhoneNumberPrefix(String prefix) {
        TypedQuery<Long> query = em.createNamedQuery("Patient.CountPatientsByPhoneNumberPrefix", Long.class);
        query.setParameter("prefix", prefix + "%");
        return query.getSingleResult();
    }

    //d) Список діагнозів пацієнтів (без повторів) із вказанням кількості пацієнтів, що мають цей діагноз у порядку спадання цієї кількості
    public Map<String, Long> getDiagnosisCount() {
        TypedQuery<Object[]> query = em.createNamedQuery("Patient.GetDiagnosisCount", Object[].class);
        List<Object[]> results = query.getResultList();
        Map<String, Long> getDiagnosisCount = new LinkedHashMap<>();
        for (Object[] result : results) {
            String diagnosis = (String) result[0];
            Long count = (Long) result[1];
            getDiagnosisCount.put(diagnosis, count);
        }
        return getDiagnosisCount;
    }

    //e) Список діагнозів пацієнтів, зареєстрованих у системі без повторів
    public List<String> getUniqueDiagnoses() {
        TypedQuery<String> query = em.createNamedQuery("Patient.GetUniqueDiagnoses", String.class);
        return query.getResultList();
    }

    //f) Для кожного діагнозу визначити кількість пацієнтів, яким він поставлений
    public Map<String, Long> getPatientCountByDiagnosis() {
        TypedQuery<Object[]> query = em.createNamedQuery("Patient.GetPatientCountByDiagnosis", Object[].class);
        List<Object[]> results = query.getResultList();
        Map<String, Long> getPatientCountByDiagnosis = new LinkedHashMap<>();
        for (Object[] result : results) {
            String diagnosis = (String) result[0];
            Long count = (Long) result[1];
            getPatientCountByDiagnosis.put(diagnosis, count);
        }
        return getPatientCountByDiagnosis;
    }

}