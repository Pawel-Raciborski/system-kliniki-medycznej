package org.back.systemklinikimedycznej.global_services;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Objects;

@Service
public class DirectoryService {
    public static final Path ROOT = Paths.get(".");

    public Path createDirectory(Path path){
        if(Files.exists(path)){
            return path;
        }
        System.out.println("creating");
        try {
            return Files.createDirectory(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeDirectory(Path fileToRemove) {
        try {
            FileUtils.deleteDirectory(fileToRemove.toFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Path createFile(Path path) {
        Path finalPath = ROOT.resolve(path);

        if(Files.exists(finalPath)){
            return finalPath;
        }

        try{
            return Files.createFile(finalPath);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public void saveImage(Path imagePath, MultipartFile profileImage) {
        try {
            profileImage.transferTo(imagePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveImage(Path dest, Path source) {
        try {
            Files.copy(source,dest, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
