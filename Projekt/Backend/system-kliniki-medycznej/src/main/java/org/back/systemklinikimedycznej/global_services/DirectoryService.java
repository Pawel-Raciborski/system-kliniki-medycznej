package org.back.systemklinikimedycznej.global_services;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class DirectoryService {
    public static final Path ROOT = Paths.get(".");

    public Path createDirectory(Path path){
        Path finalPath = ROOT.resolve(path);
        if(Files.exists(finalPath)){
            return finalPath;
        }
        System.out.println("creating");
        try {
            return Files.createDirectory(finalPath);
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
}
