package com.dimata.wpupload.entity.paymentchanel;


import com.dimata.qdep.entity.Entity;

public class PaymentChanel extends Entity {

    private long paymentId = 0;
    private String paymentName = "";
    private String paymentCode = "";
    private int idx = 0;
    private String code = "";
    private String qrvalue = "";
    private long billnumber = 0;

    public long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }
    
    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
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
