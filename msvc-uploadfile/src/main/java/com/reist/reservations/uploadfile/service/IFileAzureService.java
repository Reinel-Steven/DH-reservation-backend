package com.reist.reservations.uploadfile.service;

public interface IFileAzureService {

    String uploadFileAzure(String nameFile, String bytes64);

    boolean deleteFileAzure(String nameFile);

    String getUrlFileAzure(String nameFile);
    boolean existFileAzure(String nameFile);
}
