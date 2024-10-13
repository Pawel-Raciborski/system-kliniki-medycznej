package org.back.systemklinikimedycznej.patient.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.global_services.DirectoryService;
import org.back.systemklinikimedycznej.patient.repositories.entities.Patient;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class PatientFileService {
    public static final Path PATIENTS = Paths.get("patients");
    private final DirectoryService directoryService;

    public Path createDirectoryForPatient(Patient patient){
        String patientPesel = patient.getPersonalDetails().getPesel();

        Path patientDirectory = PATIENTS.resolve(Paths.get(patientPesel));
        return directoryService.createDirectory(patientDirectory);
    }

    public void deleteFile(Path fileToRemove) {
        directoryService.removeDirectory(fileToRemove);
    }
}
