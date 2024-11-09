package org.back.systemklinikimedycznej.patient.services;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.global_services.DirectoryService;
import org.back.systemklinikimedycznej.patient.repositories.entities.Patient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class PatientFileService {
    @Value("${project.patients}")
    private String PATIENT_DIR;
    private Path BASE_PATIENT_DIR;
    private final DirectoryService directoryService;
    @PostConstruct
    public void init(){
        BASE_PATIENT_DIR = Paths.get(PATIENT_DIR);
        this.directoryService.createDirectory(BASE_PATIENT_DIR);
    }

    public Path createDirectoryForPatient(Patient patient){
        String patientPesel = patient.getPersonalDetails().getPesel();

        Path patientDirectory = BASE_PATIENT_DIR.resolve(patientPesel);
        return directoryService.createDirectory(patientDirectory);
    }

    public void deleteFile(Path fileToRemove) {
        directoryService.removeDirectory(fileToRemove);
    }
}
