package com.reist.reservations.uploadfile.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

@Service
public class FileLocalImpl implements IFileLocalService{

    private static final Logger log = LoggerFactory.getLogger(FileLocalImpl.class);

    @Value("${config.storage-url}")
    private static String urlStorage;
    public static String getUrlStorage() {
        return urlStorage;
    }

    private static final String UPLOADS_FOLDER = "D://DigitalHouse//ProyectoReservacion//FrontReact//reservation//images-vehicles//";
    private static final String FILE_SAVE = "Archivo Guardado en " + UPLOADS_FOLDER;

    public String saveFile(String base64File, String fileName) throws IOException {
        byte[] decodedBytes = Base64.getDecoder().decode(base64File);

        File file = new File(UPLOADS_FOLDER + fileName);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(decodedBytes);
        }catch (IOException e){
            log.error(e.getMessage());
            return e.getMessage();
        }
        log.info(FILE_SAVE);
        return file.getAbsolutePath();
    }

    public boolean deleteFile(String fileName){
        try {
            File file = new File(UPLOADS_FOLDER + fileName);
            if (file.exists()) {
                file.delete();
                return true;
            } else {
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }
}
