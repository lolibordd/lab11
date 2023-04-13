package com.example.iab11.entities;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Data
@Named
@SessionScoped
@Table(name = "patient")
@NamedQueries({
        @NamedQuery(name = "Patient.findAll", query = "select p from Patient p"),

        //a) список пацієнтів, які мають вказаний діагноз в порядку зростання номерів медичної картки
        //b) Список пацієнтів, номер медичної карти у яких знаходиться в заданому інтервалі
        //c) Кількість та список пацієнтів, номер телефона яких починається з вказаної цифри
        //d) Список діагнозів пацієнтів (без повторів) із вказанням кількості пацієнтів, що мають цей діагноз у порядку спадання цієї кількості
        //e) Список діагнозів пацієнтів, зареєстрованих у системі без повторів
        //f) Для кожного діагнозу визначити кількість пацієнтів, яким він поставлений

        @NamedQuery(name = "Patient.FindPatientsByDiagnosis", query = "SELECT p FROM Patient p WHERE p.diagnosis LIKE :diagnosis ORDER BY p.cardNumber ASC"),
        @NamedQuery(name = "Patient.FindPatientsByCardNumberRange", query = "SELECT p FROM Patient p WHERE p.cardNumber BETWEEN :start AND :end"),
        @NamedQuery(name = "Patient.FindPatientsByPhoneNumberPrefix", query = "SELECT p FROM Patient p WHERE p.phoneNumber LIKE :prefix"),
        @NamedQuery(name = "Patient.CountPatientsByPhoneNumberPrefix", query = "SELECT COUNT(p) FROM Patient p WHERE p.phoneNumber LIKE :prefix"),
        @NamedQuery(name = "Patient.GetDiagnosisCount", query = "SELECT p.diagnosis, COUNT(p) FROM Patient p GROUP BY p.diagnosis ORDER BY COUNT(p) DESC"),
        @NamedQuery(name = "Patient.GetUniqueDiagnoses", query = "SELECT DISTINCT p.diagnosis FROM Patient p"),
        @NamedQuery(name = "Patient.GetPatientCountByDiagnosis", query = "SELECT p.diagnosis, COUNT(p) FROM Patient p GROUP BY p.diagnosis"),

})

public class Patient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @Column(name = "fullName", length = 50)
    private String fullName;

    @Size(max = 50)
    @Column(name = "address", length = 50)
    private String address;

    @Size(max = 50)
    @Column(name = "phoneNumber", length = 50)
    private String phoneNumber;

    @Column(name = "cardNumber")
    private Integer cardNumber;

    @Size(max = 50)
    @Column(name = "diagnosis", length = 50)
    private String diagnosis;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String  getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Integer cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

}