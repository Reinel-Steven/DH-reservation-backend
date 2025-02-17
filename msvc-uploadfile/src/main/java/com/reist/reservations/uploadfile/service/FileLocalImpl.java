package com.reist.reservations.uploadfile.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileLocalImpl implements IFileLocalService{

    private static final Logger log = LoggerFactory.getLogger(FileLocalImpl.class);
    private static final String UPLOADS_FOLDER = "D:\\test\\";
    private static final String FILE_SAVE = "Archivo Guardado en " + UPLOADS_FOLDER;

    @Override
    public void init(String uploadFolder) throws IOException {
        Files.createDirectory(Paths.get(uploadFolder));
    }
    @Override
    public Resource load(String filename) throws MalformedURLException {
        Path pathFoto = getPath(filename);
        log.info(FILE_SAVE);

        Resource recurso = new UrlResource(pathFoto.toUri());

        if (!recurso.exists() || !recurso.isReadable()) {
            throw new RuntimeException("Error: no se puede cargar la imagen: " + pathFoto.toString());
        }
        return recurso;
    }

    @Override
    public String copy(MultipartFile file) throws IOException {
        String uniqueFilename = file.getOriginalFilename();
        Path rootPath = getPath(uniqueFilename);

        log.info(FILE_SAVE);

        Files.copy(file.getInputStream(), rootPath);

        return uniqueFilename;
    }

    @Override
    public boolean delete(String filename) {
        Path rootPath = getPath(filename);
        File archivo = rootPath.toFile();

        if (archivo.exists() && archivo.canRead()) {
            if (archivo.delete()) {
                return true;
            }
        }
        return false;
    }

    public Path getPath(String filename) {
        return Paths.get(UPLOADS_FOLDER).resolve(filename).toAbsolutePath();
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(Paths.get(UPLOADS_FOLDER).toFile());

    }
}
