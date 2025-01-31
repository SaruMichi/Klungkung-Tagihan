/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.session.proses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author dimata005
 */
public class FileSent {

    private String fileName = "";
    private String location = "";
    private String tahun = "";
    private String bulan = "";
    private int typeSegment = 0;
    private String operasiData = "";
    private boolean createSummary = false;
    private long periodeId=0;
    private Date startDateSummary = new Date();
    private Date endDateSummary =  new Date();
    private int typeDilimitedSummary=0;
    private Date endPeriodeDate =  new Date();
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
     * @return the typeSegment
     */
    public int getTypeSegment() {
        return typeSegment;
    }

    /**
     * @param typeSegment the typeSegment to set
     */
    public void setTypeSegment(int typeSegment) {
        this.typeSegment = typeSegment;
    }

    /**
     * @return the tahun
     */
    public String getTahun() {
        return tahun;
    }

    /**
     * @param tahun the tahun to set
     */
    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    /**
     * @return the bulan
     */
    public String getBulan() {
        return bulan;
    }

    /**
     * @param bulan the bulan to set
     */
    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    /**
     * @return the operasiData
     */
    public String getOperasiData() {
        return operasiData;
    }

    /**
     * @param operasiData the operasiData to set
     */
    public void setOperasiData(String operasiData) {
        this.operasiData = operasiData;
    }

    /**
     * @return the createSummary
     */
    public boolean isCreateSummary() {
        return createSummary;
    }

    /**
     * @param createSummary the createSummary to set
     */
    public void setCreateSummary(boolean createSummary) {
        this.createSummary = createSummary;
    }

    public static void create(String approot, String bulan, String tahun) {
        String locationDocument = approot + "" + bulan + "_" + tahun;
        Path path = Paths.get(locationDocument);
        File directory = new File(locationDocument);
        //if directory exists?
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                //fail to create directory
                e.printStackTrace();
            }
        } else {
            try {
                delete(directory);
                if (!Files.exists(path)) {
                    try {
                        Files.createDirectories(path);
                    } catch (IOException e) {
                        //fail to create directory
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
    }
    public boolean checkDirectori(String approot, String bulan, String tahun){
        boolean isExist=false;
        String locationDocument = approot + "" + bulan + "_" + tahun;
        Path path = Paths.get(locationDocument);
        File directory = new File(locationDocument);
        if (Files.exists(path)) {
            isExist=true;
        }
        return isExist;
    }
    public static void delete(File file)
            throws IOException {

        if (file.isDirectory()) {

            //directory is empty, then delete it
            if (file.list().length == 0) {

                file.delete();
                System.out.println("Directory is deleted : "
                        + file.getAbsolutePath());

            } else {

                //list all the directory contents
                String files[] = file.list();

                for (String temp : files) {
                    //construct the file structure
                    File fileDelete = new File(file, temp);

                    //recursive delete
                    delete(fileDelete);
                }

                //check the directory again, if empty then delete it
                if (file.list().length == 0) {
                    file.delete();
                    System.out.println("Directory is deleted : "
                            + file.getAbsolutePath());
                }
            }

        } else {
            //if file, then delete it
            file.delete();
            System.out.println("File is deleted : " + file.getAbsolutePath());
        }
    }

    static public void zipFolder(String srcFolder, String destZipFile) throws Exception {
        ZipOutputStream zip = null;
        FileOutputStream fileWriter = null;

        fileWriter = new FileOutputStream(destZipFile);
        zip = new ZipOutputStream(fileWriter);

        addFolderToZip("", srcFolder, zip);
        zip.flush();
        zip.close();
    }

    static private void addFileToZip(String path, String srcFile, ZipOutputStream zip)
            throws Exception {

        File folder = new File(srcFile);
        if (folder.isDirectory()) {
            addFolderToZip(path, srcFile, zip);
        } else {
            byte[] buf = new byte[1024];
            int len;
            FileInputStream in = new FileInputStream(srcFile);
            zip.putNextEntry(new ZipEntry(path + "/" + folder.getName()));
            while ((len = in.read(buf)) > 0) {
                zip.write(buf, 0, len);
            }
        }
    }

    static private void addFolderToZip(String path, String srcFolder, ZipOutputStream zip)
            throws Exception {
        File folder = new File(srcFolder);

        for (String fileName : folder.list()) {
            if (path.equals("")) {
                addFileToZip(folder.getName(), srcFolder + "/" + fileName, zip);
            } else {
                addFileToZip(path + "/" + folder.getName(), srcFolder + "/" + fileName, zip);
            }
        }
    }

    /**
     * @return the periodeId
     */
    public long getPeriodeId() {
        return periodeId;
    }

    /**
     * @param periodeId the periodeId to set
     */
    public void setPeriodeId(long periodeId) {
        this.periodeId = periodeId;
    }

    /**
     * @return the startDateSummary
     */
    public Date getStartDateSummary() {
        return startDateSummary;
    }

    /**
     * @param startDateSummary the startDateSummary to set
     */
    public void setStartDateSummary(Date startDateSummary) {
        this.startDateSummary = startDateSummary;
    }

    /**
     * @return the endDateSummary
     */
    public Date getEndDateSummary() {
        return endDateSummary;
    }

    /**
     * @param endDateSummary the endDateSummary to set
     */
    public void setEndDateSummary(Date endDateSummary) {
        this.endDateSummary = endDateSummary;
    }

    /**
     * @return the typeDilimitedSummary
     */
    public int getTypeDilimitedSummary() {
        return typeDilimitedSummary;
    }

    /**
     * @param typeDilimitedSummary the typeDilimitedSummary to set
     */
    public void setTypeDilimitedSummary(int typeDilimitedSummary) {
        this.typeDilimitedSummary = typeDilimitedSummary;
    }

    /**
     * @return the endPeriodeDate
     */
    public Date getEndPeriodeDate() {
        return endPeriodeDate;
    }

    /**
     * @param endPeriodeDate the endPeriodeDate to set
     */
    public void setEndPeriodeDate(Date endPeriodeDate) {
        this.endPeriodeDate = endPeriodeDate;
    }

}
