package org.back.systemklinikimedycznej.doctor.services;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.doctor.controller.dto.RegisterDoctorDetails;
import org.back.systemklinikimedycznej.global_services.DirectoryService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DoctorFileService {
    private final DirectoryService directoryService;
    @Value("${project.default-image}")
    private String DEFAULT_IMAGE_PATH;
    @Value("${project.doctor}")
    private String DOCTOR_DIR;
    @Value("${base.url}")
    private String baseUrl;
    private Path DOCTOR_BASE_DIR;

    @PostConstruct
    public void init() {
        DOCTOR_BASE_DIR = Paths.get(DOCTOR_DIR);
        directoryService.createDirectory(DOCTOR_BASE_DIR);
    }

    public URI saveProfileImage(RegisterDoctorDetails registerDoctorDetails, MultipartFile profileImage) {
        Path doctorPath;
        String pwzNumber = registerDoctorDetails.pwzNumber();
        doctorPath = DOCTOR_BASE_DIR.resolve(pwzNumber); // doctors/132123
        doctorPath = directoryService.createDirectory(doctorPath);

        try {
            URI start = new URI(baseUrl);

            Path finalPath;
            if (Objects.isNull(profileImage) || Objects.isNull(profileImage.getOriginalFilename())) {
                String fileName = Paths.get(DEFAULT_IMAGE_PATH).getFileName().toString();
                fileName = rename(pwzNumber, fileName);

                finalPath = doctorPath.resolve(fileName);
                directoryService.saveImage(finalPath, Paths.get(DEFAULT_IMAGE_PATH));

                URI end = new URI(replaceSlashes(finalPath));
                return start.resolve(end);
            }

            String originalFilename = profileImage.getOriginalFilename();
            String fileName = rename(pwzNumber, originalFilename);

            finalPath = doctorPath.resolve(fileName);
            directoryService.saveImage(finalPath, profileImage);

            URI end = new URI(replaceSlashes(finalPath));
            return start.resolve(end);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private String replaceSlashes(Path path) {
        return path.toString().replace("\\", "/");
    }

    private String rename(String pwzNumber, String fileName) {
        return "%s.%s".formatted(pwzNumber, fileName.split("\\.")[1]);
    }

    public void deleteDirectory(String directory) {
        Path dest = Paths.get(directory.replace(baseUrl,""));
        directoryService.removeDirectory(dest.getParent());
    }

    public Resource findFile(String dest, String fileName) {
        Path destPath = Paths.get(dest.replace(baseUrl,""));
        return new FileSystemResource(destPath.toFile());
    }
}
