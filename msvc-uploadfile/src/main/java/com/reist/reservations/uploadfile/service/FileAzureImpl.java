package com.reist.reservations.uploadfile.service;

import com.microsoft.azure.storage.StorageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import com.microsoft.azure.storage.core.Base64;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageUri;
import com.microsoft.azure.storage.blob.CloudBlob;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

@Service
public class FileAzureImpl implements IFileAzureService {

    private static final Logger log = LoggerFactory.getLogger(FileAzureImpl.class);

    @Value("${config.azure.storage-connection}")
    private static String keyStorageConnection;

    public static String getAzureStorageKey() {
        return keyStorageConnection;
    }

    private static final String FILE_SAVE = "Archivo Guardado en el blob de Azure";
    private static final String STORAGE_CONNECTION = getAzureStorageKey();
    private static final String NAME_CONTAINER = "images-vehicles";

    private static CloudBlobContainer getCloudBlobContainer() throws URISyntaxException, InvalidKeyException, StorageException {
        CloudStorageAccount account = CloudStorageAccount.parse(STORAGE_CONNECTION);
        CloudBlobClient serviceClient = account.createCloudBlobClient();
        return serviceClient.getContainerReference(NAME_CONTAINER);
    }

    @Override
    public String uploadFileAzure(String nameFile, String bytes64) {
        try {
            CloudBlobContainer container = getCloudBlobContainer();
            byte[] decodeBytes = Base64.decode(bytes64);
            CloudBlob blob = container.getBlockBlobReference(nameFile);
            blob.uploadFromByteArray(decodeBytes, 0, decodeBytes.length);
            log.info(FILE_SAVE);
            return "ok";

        } catch (Exception e) {
            log.error(e.getMessage());
            return e.getMessage();
        }
    }

    @Override
    public boolean deleteFileAzure(String nameFile) {
        try {
            CloudBlobContainer container = getCloudBlobContainer();
            CloudBlockBlob blockBlob = container.getBlockBlobReference(nameFile);
            blockBlob.deleteIfExists();
            log.info("archivo eliminado");
            return true;

        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public String getUrlFileAzure(String nameFile) {
        try {
            CloudBlobContainer container = getCloudBlobContainer();
            CloudBlockBlob blockBlob = container.getBlockBlobReference(nameFile);
            StorageUri uri = blockBlob.getStorageUri();
            log.info("archivo encontrado");
            return uri.getPrimaryUri().getPath();

        } catch (Exception e) {
            log.error(e.getMessage());
            return "Archivo no exixte";
        }
    }

    @Override
    public boolean existFileAzure(String nameFile) {
        try {
            CloudBlobContainer container = getCloudBlobContainer();
            CloudBlockBlob blockBlob = container.getBlockBlobReference(nameFile);
            StorageUri uri = blockBlob.getStorageUri();
            if(!uri.getPrimaryUri().getPath().isEmpty()){
                log.info("archivo encontrado");
                return true;
            }else {
                log.info("archivo no encontrado");
                return true;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
