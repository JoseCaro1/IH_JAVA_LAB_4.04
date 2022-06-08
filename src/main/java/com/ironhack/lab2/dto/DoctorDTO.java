package com.ironhack.lab2.dto;

import com.ironhack.lab2.enums.Status;

public class DoctorDTO {

    String department;

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

    Status status;
}
