package com.ironhack.lab2.controller;

import com.ironhack.lab2.dto.DoctorDTO;
import com.ironhack.lab2.enums.Status;
import com.ironhack.lab2.models.Doctor;
import com.ironhack.lab2.models.Patient;
import com.ironhack.lab2.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.print.Doc;
import java.util.List;

@RestController
public class DoctorController {


    @Autowired
    DoctorRepository doctorRepository;

    @GetMapping("doctor/find-all")
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    @GetMapping("doctor/id/{id}")
    public Doctor findDoctorById(@PathVariable long id) {
        if (doctorRepository.findById(id).isPresent()) {
            return doctorRepository.findById(id).get();
        }
        return null;
    }

    @GetMapping("doctor/status/{status}")
    public List<Doctor> findDoctorByStatus(@PathVariable Status status) {
        return doctorRepository.findByStatus(status);
    }

    @GetMapping("doctor/department")
    public List<Doctor> findDoctorByDepartament(@RequestParam String department) {
        return doctorRepository.findByDepartment(department);
    }

    @PostMapping("doctor/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Doctor addDoctor(@RequestBody Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @PatchMapping("doctor/id/{id}/update/status")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Doctor updateDoctorStatus(@PathVariable long id, @RequestBody DoctorDTO doctorDTO) {

        if (doctorRepository.findById(id).isPresent()) {
            Doctor doctor = doctorRepository.findById(id).get();
            doctor.setStatus(doctorDTO.getStatus());
            doctorRepository.save(doctor);
            return doctor;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No sea encontrado el doctor a editar");
    }

    @PatchMapping("doctor/id/{id}/update/department")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Doctor updateDoctorDepartment(@PathVariable long id, @RequestBody DoctorDTO doctorDTO) {
        if (doctorRepository.findById(id).isPresent()) {
            Doctor doctor = doctorRepository.findById(id).get();
            doctor.setDepartment(doctorDTO.getDepartment());
            doctorRepository.save(doctor);
            return doctor;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No sea encontrado el doctor a editar");
    }


    @DeleteMapping("doctor/id/{id}/remove")
    @ResponseStatus(value = HttpStatus.ACCEPTED, reason = "Se ha eliminado correctamente")
    public void removeDoctor(@PathVariable long id) {
        if (doctorRepository.findById(id).isPresent()) {
            doctorRepository.delete(doctorRepository.findById(id).get());
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No sea encontrado el doctor ha eliminar");

    }


}
