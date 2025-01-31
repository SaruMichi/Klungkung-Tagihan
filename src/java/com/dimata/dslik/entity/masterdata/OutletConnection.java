
/* Created on 	:  [date] [time] AM/PM
 *
 * @author  	: karya
 * @version  	: 01
 */
/*******************************************************************
 * Class Description 	: [project description ... ]
 * Imput Parameters 	: [input parameter ...]
 * Output 		: [output ...]
 *******************************************************************/
package com.dimata.dslik.entity.masterdata;

/* package java */
import java.io.*;

/* package qdep */
import com.dimata.qdep.entity.*;
/**
 *
 * @author dimata005
 * update opie-eyek 20130805
 */
public class OutletConnection extends Entity implements Serializable {

    private String dbdriver = "";
    private String dburl = "";
    private String dbuser = "";
    private String dbpasswd = "";
    private String dbminconn = "";
    private String dbmaxconn = "";
    private String logconn = "";
    private String logapp = "";
    private String logsize = "";
    private String fordate = "";
    private String fordecimal = "";
    private String forcurrency = "";
    private Long cash_master_id ;
    private int typeConnection=0;
    
    private String jenisLJK="";
    private String kodeLJK="";
    private String pathDelimitedText="";
    private String pathDelimitedTextSummary="";
    private String dbDatabase="";
    private String dbDatabaseBackup="";
    private int typeCreateFile=0;
    
    public String getPstClassName() {
        return "com.dimata.common.entity.location.PstLocation";
    }

    /**
     * @return the dbdriver
     */
    public String getDbdriver() {
        return dbdriver;
    }

    /**
     * @param dbdriver the dbdriver to set
     */
    public void setDbdriver(String dbdriver) {
        this.dbdriver = dbdriver;
    }

    /**
     * @return the dburl
     */
    public String getDburl() {
        return dburl;
    }

    /**
     * @param dburl the dburl to set
     */
    public void setDburl(String dburl) {
        this.dburl = dburl;
    }

    /**
     * @return the dbuser
     */
    public String getDbuser() {
        return dbuser;
    }

    /**
     * @param dbuser the dbuser to set
     */
    public void setDbuser(String dbuser) {
        this.dbuser = dbuser;
    }

    /**
     * @return the dbpasswd
     */
    public String getDbpasswd() {
        return dbpasswd;
    }

    /**
     * @param dbpasswd the dbpasswd to set
     */
    public void setDbpasswd(String dbpasswd) {
        this.dbpasswd = dbpasswd;
    }

    /**
     * @return the dbminconn
     */
    public String getDbminconn() {
        return dbminconn;
    }

    /**
     * @param dbminconn the dbminconn to set
     */
    public void setDbminconn(String dbminconn) {
        this.dbminconn = dbminconn;
    }

    /**
     * @return the dbmaxconn
     */
    public String getDbmaxconn() {
        return dbmaxconn;
    }

    /**
     * @param dbmaxconn the dbmaxconn to set
     */
    public void setDbmaxconn(String dbmaxconn) {
        this.dbmaxconn = dbmaxconn;
    }

    /**
     * @return the logconn
     */
    public String getLogconn() {
        return logconn;
    }

    /**
     * @param logconn the logconn to set
     */
    public void setLogconn(String logconn) {
        this.logconn = logconn;
    }

    /**
     * @return the logsize
     */
    public String getLogsize() {
        return logsize;
    }

    /**
     * @param logsize the logsize to set
     */
    public void setLogsize(String logsize) {
        this.logsize = logsize;
    }

    /**
     * @return the fordate
     */
    public String getFordate() {
        return fordate;
    }

    /**
     * @param fordate the fordate to set
     */
    public void setFordate(String fordate) {
        this.fordate = fordate;
    }

    /**
     * @return the fordecimal
     */
    public String getFordecimal() {
        return fordecimal;
    }

    /**
     * @param fordecimal the fordecimal to set
     */
    public void setFordecimal(String fordecimal) {
        this.fordecimal = fordecimal;
    }

    /**
     * @return the forcurrency
     */
    public String getForcurrency() {
        return forcurrency;
    }

    /**
     * @param forcurrency the forcurrency to set
     */
    public void setForcurrency(String forcurrency) {
        this.forcurrency = forcurrency;
    }

    /**
     * @return the logapp
     */
    public String getLogapp() {
        return logapp;
    }

    /**
     * @param logapp the logapp to set
     */
    public void setLogapp(String logapp) {
        this.logapp = logapp;
    }

    /**
     * @return the cash_master_id
     */
    public Long getCash_master_id() {
        return cash_master_id;
    }

    /**
     * @param cash_master_id the cash_master_id to set
     */
    public void setCash_master_id(Long cash_master_id) {
        this.cash_master_id = cash_master_id;
    }

    /**
     * @return the typeConnection
     */
    public int getTypeConnection() {
        return typeConnection;
    }

    /**
     * @param typeConnection the typeConnection to set
     */
    public void setTypeConnection(int typeConnection) {
        this.typeConnection = typeConnection;
    }

    /**
     * @return the jenisLJK
     */
    public String getJenisLJK() {
        return jenisLJK;
    }

    /**
     * @param jenisLJK the jenisLJK to set
     */
    public void setJenisLJK(String jenisLJK) {
        this.jenisLJK = jenisLJK;
    }

    /**
     * @return the kodeLJK
     */
    public String getKodeLJK() {
        return kodeLJK;
    }

    /**
     * @param kodeLJK the kodeLJK to set
     */
    public void setKodeLJK(String kodeLJK) {
        this.kodeLJK = kodeLJK;
    }

    /**
     * @return the pathDelimitedText
     */
    public String getPathDelimitedText() {
        return pathDelimitedText;
    }

    /**
     * @param pathDelimitedText the pathDelimitedText to set
     */
    public void setPathDelimitedText(String pathDelimitedText) {
        this.pathDelimitedText = pathDelimitedText;
    }

    /**
     * @return the pathDelimitedTextSummary
     */
    public String getPathDelimitedTextSummary() {
        return pathDelimitedTextSummary;
    }

    /**
     * @param pathDelimitedTextSummary the pathDelimitedTextSummary to set
     */
    public void setPathDelimitedTextSummary(String pathDelimitedTextSummary) {
        this.pathDelimitedTextSummary = pathDelimitedTextSummary;
    }

    /**
     * @return the dbDatabase
     */
    public String getDbDatabase() {
        return dbDatabase;
    }

    /**
     * @param dbDatabase the dbDatabase to set
     */
    public void setDbDatabase(String dbDatabase) {
        this.dbDatabase = dbDatabase;
    }

    /**
     * @return the dbDatabaseBackup
     */
    public String getDbDatabaseBackup() {
        return dbDatabaseBackup;
    }

    /**
     * @param dbDatabaseBackup the dbDatabaseBackup to set
     */
    public void setDbDatabaseBackup(String dbDatabaseBackup) {
        this.dbDatabaseBackup = dbDatabaseBackup;
    }

    /**
     * @return the typeCreateFile
     */
    public int getTypeCreateFile() {
        return typeCreateFile;
    }

    /**
     * @param typeCreateFile the typeCreateFile to set
     */
    public void setTypeCreateFile(int typeCreateFile) {
        this.typeCreateFile = typeCreateFile;
    }

    /**
     * @return the dateFrom
     */
   
}
