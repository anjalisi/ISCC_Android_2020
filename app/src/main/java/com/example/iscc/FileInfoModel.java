package com.example.iscc;

public class FileInfoModel {
    String filename;
    String fileurl;

    public String getKeydoes() {
        return keydoes;
    }

    public void setKeydoes(String keydoes) {
        this.keydoes = keydoes;
    }

    String keydoes;
    int star;

    public FileInfoModel(){}
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }

    public FileInfoModel(String filename, String fileurl, String keydoes, int star) {
        this.filename = filename;
        this.fileurl = fileurl;
        this.keydoes = keydoes;
        this.star = star;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }
}
