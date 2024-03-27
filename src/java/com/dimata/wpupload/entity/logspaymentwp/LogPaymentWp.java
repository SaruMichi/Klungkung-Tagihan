package com.dimata.wpupload.entity.logspaymentwp;

import com.dimata.qdep.entity.Entity;

public class LogPaymentWp extends Entity {

    private long oidLogPay = 0;
    private String npwpd = "";
    private String masaPajak = "";
    private int tahunPajak = 0;
    private int typePaymnet = 0;
    private int status = 0;
    private String qrvalue = "";
    private long billnumber = 0;

    public long getOidLogPay() {
        return oidLogPay;
    }

    public void setOidLogPay(long oidLogPay) {
        this.oidLogPay = oidLogPay;
    }

    public String getNpwpd() {
        return npwpd;
    }

    public void setNpwpd(String npwpd) {
        this.npwpd = npwpd;
    }

    public String getMasaPajak() {
        return masaPajak;
    }

    public void setMasaPajak(String masaPajak) {
        this.masaPajak = masaPajak;
    }

    public int getTahunPajak() {
        return tahunPajak;
    }

    public void setTahunPajak(int tahunPajak) {
        this.tahunPajak = tahunPajak;
    }

    public int getTypePaymnet() {
        return typePaymnet;
    }

    public void setTypePaymnet(int typePaymnet) {
        this.typePaymnet = typePaymnet;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getQrvalue() {
        return qrvalue;
    }
    public void setQrvalue(String qrvalue) {
        this.qrvalue = qrvalue;
    }

    public long getBillnumber() {
        return billnumber;
    }
    public void setBillnumber(long billnumber) {
        this.billnumber = billnumber;
    }
}
