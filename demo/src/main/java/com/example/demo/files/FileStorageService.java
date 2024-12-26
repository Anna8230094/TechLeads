package com.example.demo.files;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.nio.file.Files;

import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class FileStorageService implements FilesStorageRepository {

    private final Path root = Paths.get("./uploads2n");

    @Override
    public void init() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public void save(List <MultipartFile> file) {
        try {
            file.forEach(f->{
                try {
                    Files.copy(f.getInputStream(), this.root.resolve(f.getOriginalFilename()));
                } catch (IOException e) {
                    System.err.println("Saving list is not possible");
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }
}
