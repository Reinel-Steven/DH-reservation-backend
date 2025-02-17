package com.reist.reservations.uploadfile.model;

public class FileDto {
    private String fileName;
    private String file;

    public FileDto() {
        super();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
