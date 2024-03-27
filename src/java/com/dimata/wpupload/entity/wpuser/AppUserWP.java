package com.dimata.wpupload.entity.wpuser;
import com.dimata.qdep.entity.Entity;
import java.util.Date;

public class AppUserWP extends Entity {

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public final static int STATUS_NEW =0;
    public final static int STATUS_LOGOUT =1;
    public final static int STATUS_LOGIN =2;
    
    public final static String[] statusTxt= {"New", "Logged out", "Logged In"};
    
    
    private int userGroupNew = 0;
    private String wpUserId = "";
    private String wpUserNama = "";
    private String wpUserPassword = "";
    private Date wpUserCreateDate = null;
    private Date wpUserLastLogin = null;
    private String wpUserNIP = "";
    private String wpUserKDJabatan = "";
    private String wpUserNPWPD = "";
    private String wpUserKDJenisUsaha = "";
    private String wpUserKDJenisWP = "";
    private String wpUserKDSubjenisWP = "";
    private String wpUserLokasi = "";
    private String wpUserSublokasi = "";
    private String wpUserNoTelp = "";
    
    private int userStatus=0;
    private String lastLoginIp="";
    private Date lastLoginDate=new Date();
    private String userId="";
    public String getWpUserId(){
	return wpUserId;
    }

    public void setWpUserId(String wpUserId){
	this.wpUserId = wpUserId;
    }

    public String getWpUserNama(){
	return wpUserNama;
    }

    public void setWpUserNama(String wpUserNama){
	this.wpUserNama = wpUserNama;
    }

    public String getWpUserPassword(){
	return wpUserPassword;
    }

    public void setWpUserPassword(String wpUserPassword){
	this.wpUserPassword = wpUserPassword;
    }

    public Date getWpUserCreateDate(){
	return wpUserCreateDate;
    }

    public void setWpUserCreateDate(Date wpUserCreateDate){
	this.wpUserCreateDate = wpUserCreateDate;
    }

    public Date getWpUserLastLogin(){
	return wpUserLastLogin;
    }

    public void setWpUserLastLogin(Date wpUserLastLogin){
	this.wpUserLastLogin = wpUserLastLogin;
    }

    public String getWpUserNIP(){
	return wpUserNIP;
    }

    public void setWpUserNIP(String wpUserNIP){
	this.wpUserNIP = wpUserNIP;
    }

    public String getWpUserKDJabatan(){
	return wpUserKDJabatan;
    }

    public void setWpUserKDJabatan(String wpUserKDJabatan){
	this.wpUserKDJabatan = wpUserKDJabatan;
    }

    public String getWpUserNPWPD(){
	return wpUserNPWPD;
    }

    public void setWpUserNPWPD(String wpUserNPWPD){
	this.wpUserNPWPD = wpUserNPWPD;
    }

    public String getWpUserKDJenisUsaha(){
	return wpUserKDJenisUsaha;
    }

    public void setWpUserKDJenisUsaha(String wpUserKDJenisUsaha){
	this.wpUserKDJenisUsaha = wpUserKDJenisUsaha;
    }

    public String getWpUserKDJenisWP(){
	return wpUserKDJenisWP;
    }

    public void setWpUserKDJenisWP(String wpUserKDJenisWP){
	this.wpUserKDJenisWP = wpUserKDJenisWP;
    }

    public String getWpUserKDSubjenisWP(){
	return wpUserKDSubjenisWP;
    }

    public void setWpUserKDSubjenisWP(String wpUserKDSubjenisWP){
	this.wpUserKDSubjenisWP = wpUserKDSubjenisWP;
    }

    public String getWpUserLokasi(){
	return wpUserLokasi;
    }

    public void setWpUserLokasi(String wpUserLokasi){
	this.wpUserLokasi = wpUserLokasi;
    }

    public String getWpUserSublokasi(){
	return wpUserSublokasi;
    }

    public void setWpUserSublokasi(String wpUserSublokasi){
	this.wpUserSublokasi = wpUserSublokasi;
    }

    /**
     * @return the userGroupNew
     */
    public int getUserGroupNew() {
	return userGroupNew;
    }

    /**
     * @param userGroupNew the userGroupNew to set
     */
    public void setUserGroupNew(int userGroupNew) {
	this.userGroupNew = userGroupNew;
    }

    /**
     * @return the userStatus
     */
    public int getUserStatus() {
	return userStatus;
    }

    /**
     * @param userStatus the userStatus to set
     */
    public void setUserStatus(int userStatus) {
	this.userStatus = userStatus;
    }

    /**
     * @return the lastLoginIp
     */
    public String getLastLoginIp() {
	return lastLoginIp;
    }

    /**
     * @param lastLoginIp the lastLoginIp to set
     */
    public void setLastLoginIp(String lastLoginIp) {
	this.lastLoginIp = lastLoginIp;
    }

    /**
     * @return the lastLoginDate
     */
    public Date getLastLoginDate() {
	return lastLoginDate;
    }

    /**
     * @param lastLoginDate the lastLoginDate to set
     */
    public void setLastLoginDate(Date lastLoginDate) {
	this.lastLoginDate = lastLoginDate;
    }

    /**
     * @return the wpUserNoTelp
     */
    public String getWpUserNoTelp() {
	return wpUserNoTelp;
    }

    /**
     * @param wpUserNoTelp the wpUserNoTelp to set
     */
    public void setWpUserNoTelp(String wpUserNoTelp) {
	this.wpUserNoTelp = wpUserNoTelp;
    }

}