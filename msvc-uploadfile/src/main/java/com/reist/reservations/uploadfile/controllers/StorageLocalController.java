package com.reist.reservations.uploadfile.controllers;

import com.reist.reservations.uploadfile.model.FileDto;
import com.reist.reservations.uploadfile.service.IFileLocalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/storage")
public class StorageLocalController {

    private static final Logger log = LoggerFactory.getLogger(StorageLocalController.class);

    @Autowired
    private IFileLocalService service;


    @PostMapping("/save-file")
    public ResponseEntity<String> uploadFileBlobStorage(@RequestBody FileDto file){
        try {
            if(file.getFileName() != null && file.getFile() != null) {
                service.saveFile(file.getFile(), file.getFileName());
                log.info("File saved: " + file.getFileName());
                return ResponseEntity.ok(file.getFileName());
            }else if(file.getFileName().isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "El el nombre es nesesario");
            }else {
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "El Archivo esta dañado");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar imagen", e);
        }
    }

    @GetMapping("/exist/{filename}")
    public ResponseEntity<String> existFile(@PathVariable String filename) {
        if(filename.isEmpty() || service.existsNameFile(filename)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(filename);
    }

    @DeleteMapping("/delete-file/{nameFile}")
    public ResponseEntity<String> deleteFileBlobStorage(@PathVariable String nameFile){
        try{
            boolean response = service.deleteFile(nameFile);
            if(response){
                return ResponseEntity.accepted().build();
            }
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "El archivo no existe");
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar imagen", e);
        }
    }
}

