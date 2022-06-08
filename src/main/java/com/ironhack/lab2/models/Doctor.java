package com.ironhack.lab2.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.lab2.enums.Status;

import javax.persistence.*;
import java.util.List;

@Entity
public class Doctor {

    @Id
    private long id;
    private String name;
    private String department;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToMany(mappedBy = "admittedBy", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Patient> patients;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }


}
