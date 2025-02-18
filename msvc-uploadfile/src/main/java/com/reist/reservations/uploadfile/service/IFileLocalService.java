package com.reist.reservations.uploadfile.service;

import java.io.IOException;

public interface IFileLocalService {


    String saveFile(String base64File, String nameFile) throws IOException;

    boolean deleteFile(String filename);

}
