/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dtaxintegration.entity.tagihan;

/**
 *
 * @author dimata005
 */
public class FileSent {

    /**
     * @return the fileNameZip
     */
    public String getFileNameZip() {
        return fileNameZip;
    }

    /**
     * @param fileNameZip the fileNameZip to set
     */
    public void setFileNameZip(String fileNameZip) {
        this.fileNameZip = fileNameZip;
    }
    private String sUser = "";
    private String sPassword = "";
    private String sInstansi = "";
    private String fileName="";
    private String fileNameZip="";
    private String location="";
    private String tahunStart="";
    private String tahunEnd="";
    /**
     * @return the sUser
     */
    public String getsUser() {
        return sUser;
    }

    /**
     * @param sUser the sUser to set
     */
    public void setsUser(String sUser) {
        this.sUser = sUser;
    }

    /**
     * @return the sPassword
     */
    public String getsPassword() {
        return sPassword;
    }

    /**
     * @param sPassword the sPassword to set
     */
    public void setsPassword(String sPassword) {
        this.sPassword = sPassword;
    }

    /**
     * @return the sInstansi
     */
    public String getsInstansi() {
        return sInstansi;
    }

    /**
     * @param sInstansi the sInstansi to set
     */
    public void setsInstansi(String sInstansi) {
        this.sInstansi = sInstansi;
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the tahunStart
     */
    public String getTahunStart() {
        return tahunStart;
    }

    /**
     * @param tahunStart the tahunStart to set
     */
    public void setTahunStart(String tahunStart) {
        this.tahunStart = tahunStart;
    }

    /**
     * @return the tahunEnd
     */
    public String getTahunEnd() {
        return tahunEnd;
    }

    /**
     * @param tahunEnd the tahunEnd to set
     */
    public void setTahunEnd(String tahunEnd) {
        this.tahunEnd = tahunEnd;
    }
    
}
