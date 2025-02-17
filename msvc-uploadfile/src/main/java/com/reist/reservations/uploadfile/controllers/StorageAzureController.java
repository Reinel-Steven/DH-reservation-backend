package com.reist.reservations.uploadfile.controllers;

import com.reist.reservations.uploadfile.model.FileDto;
import com.reist.reservations.uploadfile.service.IFileAzureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.Base64;

@RestController
@RequestMapping("/api/storage")
public class StorageAzureController {

	private static final Logger log = LoggerFactory.getLogger(StorageAzureController.class);

	private static final String URL_STORAGE_AZURE = "https://resourcesfile.blob.core.windows.net/images-vehicles/";
	@Autowired
	private IFileAzureService service;

	@PostMapping("/upload-file-azure")
	public ResponseEntity<String> uploadFileBlobStorage( @RequestBody FileDto file){
		try {
			if(file.getFileName() != null && file.getFile() != null) {
				service.uploadFileAzure(file.getFileName(), file.getFile());
				return ResponseEntity.ok(URL_STORAGE_AZURE + file.getFileName());
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

	@DeleteMapping("/delete-file-azure/{nameFile}")
	public ResponseEntity<String> deleteFileBlobStorage(@PathVariable String nameFile){
		try{
			boolean response = service.deleteFileAzure(nameFile);
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
