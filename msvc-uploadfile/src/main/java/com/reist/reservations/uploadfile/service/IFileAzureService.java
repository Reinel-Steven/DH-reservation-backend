package com.reist.reservations.uploadfile.service;

public interface IFileAzureService {

    public String uploadFileAzure(String nameFile, String bytes64);

    public boolean deleteFileAzure(String nameFile);

    public String getUrlFileAzure(String nameFile);
    public boolean existFileAzure(String nameFile);
}
