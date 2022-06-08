package com.ironhack.lab2.dto;

import java.util.Date;

public class PatientDTO {

    String name;
    Date dateOfBirth;
    long admittedBy;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public long getAdmittedBy() {
        return admittedBy;
    }

    public void setAdmittedBy(long admittedBy) {
        this.admittedBy = admittedBy;
    }
}
