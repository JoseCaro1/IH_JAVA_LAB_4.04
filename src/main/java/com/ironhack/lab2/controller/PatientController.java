package com.ironhack.lab2.controller;

import com.ironhack.lab2.dto.PatientDTO;
import com.ironhack.lab2.enums.Status;
import com.ironhack.lab2.models.Patient;
import com.ironhack.lab2.repository.DoctorRepository;
import com.ironhack.lab2.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class PatientController {

    @Autowired
    PatientRepository patientRepository;
    @Autowired
    DoctorRepository doctorRepository;

    @GetMapping("patient/find-all")
    public List<Patient> getAll() {
        return patientRepository.findAll();
    }

    @GetMapping("patient/find-all-date/")
    public List<Patient> getAllPatientByDate(@RequestParam Date min, @RequestParam Date max) {
        return patientRepository.findByDateOfBirthBetween(min, max);
    }

    @GetMapping("patient/find-all-department/{department}")
    public List<Patient> getAllPatientByDepartment(@PathVariable String department) {
        return patientRepository.findAllPatientByDepartament(department);
    }

    @GetMapping("patient/find-all-status/off")
    public List<Patient> getAllPatientByStatusOff() {
        ArrayList<Patient> result = new ArrayList<>();
        for (Patient patient : patientRepository.findAll()) {
            if (patient.getAdmittedBy().getStatus().equals(Status.OFF)) {
                result.add(patient);

            }
        }
        return result;
    }


    @PostMapping("patient/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Patient addPatient(@RequestBody PatientDTO patientDTO) {
        if (doctorRepository.findById(patientDTO.getAdmittedBy()).isPresent()) {
            return patientRepository.save(new Patient(patientDTO.getName(), patientDTO.getDateOfBirth(), doctorRepository.findById(patientDTO.getAdmittedBy()).get()));
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No sea encontrado el doctor asociado");
    }


    /* No se suele utilizar ya que lo normal es que te envien el Patient completo*/
    @PutMapping("patient/id/{id}/update-all/2")
    public Patient updatePatient2(@PathVariable long id, @RequestBody PatientDTO patientDTO) {

        if (patientRepository.findById(id).isPresent()) {
            Patient patient = patientRepository.findById(id).get();
            patient.setName(patientDTO.getName());
            patient.setDateOfBirth(patientDTO.getDateOfBirth());
            patient.setAdmittedBy(doctorRepository.findById(patientDTO.getAdmittedBy()).get());
            patientRepository.save(patient);
            return patient;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No sea encontrado el doctor asociado");

    }

    @PutMapping("patient/id/{id}/update-all")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Patient updatePatient(@PathVariable long id, @RequestBody Patient patient) {

        /*Hacemos eso para verificar que el id del doctor pasado es valido*/
        if (doctorRepository.findById(patient.getAdmittedBy().getId()).isPresent()) {
            patientRepository.save(patient);
            return patient;
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No sea encontrado el doctor asociado");

    }


}
