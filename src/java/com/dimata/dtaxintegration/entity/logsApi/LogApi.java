package com.dimata.dtaxintegration.entity.logsApi;


import com.dimata.qdep.entity.Entity;
import java.util.Date; 

public class LogApi extends Entity {

    private int oidApi = 0;
    private String apiName = "";
    private String modulName = "";
    private Date reqDate = null;
    private String status = "";
    private String message = "";
    private String request = "";
    private String respon = "";
    private long parent = 0;
    private String ip = "";

    public int getOidApi() {
        return oidApi;
    }

    public void setOidApi(int oidApi) {
        this.oidApi = oidApi;
    }
    
    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getModulName() {
        return modulName;
    }

    public void setModulName(String modulName) {
        this.modulName = modulName;
    }

    public Date getReqDate() {
        return reqDate;
    }

    public void setReqDate(Date reqDate) {
        this.reqDate = reqDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the request
     */
    public String getRequest() {
        return request;
    }

    /**
     * @param request the request to set
     */
    public void setRequest(String request) {
        this.request = request;
    }

    /**
     * @return the respon
     */
    public String getRespon() {
        return respon;
    }

    /**
     * @param respon the respon to set
     */
    public void setRespon(String respon) {
        this.respon = respon;
    }
    
    

    /**
     * @return the parent
     */
    public long getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(long parent) {
        this.parent = parent;
    }
    
    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }
}
