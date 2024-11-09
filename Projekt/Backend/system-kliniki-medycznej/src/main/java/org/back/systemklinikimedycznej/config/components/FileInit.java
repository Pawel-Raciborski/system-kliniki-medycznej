package org.back.systemklinikimedycznej.config.components;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.doctor.services.DoctorFileService;
import org.back.systemklinikimedycznej.global_services.DirectoryService;
import org.back.systemklinikimedycznej.patient.services.PatientFileService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@RequiredArgsConstructor
public class FileInit implements CommandLineRunner {
    private final PatientFileService patientFileService;
    private final DoctorFileService doctorFileService;

    @Override
    public void run(String... args) throws Exception {
        patientFileService.init();
        doctorFileService.init();
    }
}
