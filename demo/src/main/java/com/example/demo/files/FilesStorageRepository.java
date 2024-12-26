package com.example.demo.files;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageRepository {
    public void init();

    public void save(List<MultipartFile> file);

    public void deleteAll();
    /* 
    public Resource load(String filename);

    public boolean delete(String filename);

    public Stream<Path> loadAll();*/

}
