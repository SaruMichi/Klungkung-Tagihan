package com.dimata.wpupload.form.logpaaymentwp;


import com.dimata.qdep.form.FRMHandler;
import com.dimata.qdep.form.I_FRMInterface;
import com.dimata.qdep.form.I_FRMType;
import com.dimata.wpupload.entity.logspaymentwp.LogPaymentWp;
import javax.servlet.http.HttpServletRequest;

public class FrmLogPaymentWp extends FRMHandler implements I_FRMInterface, I_FRMType {

    private LogPaymentWp entLogPaymentWp;
    public static final String FRM_NAME_LOG_PAYMENT_WP = "FRM_NAME_LOG_PAYMENT_WP";
    public static final int FRM_FIELD_OID_LOG_PAY = 0;
    public static final int FRM_FIELD_NPWPD = 1;
    public static final int FRM_FIELD_MASA_PAJAK = 2;
    public static final int FRM_FIELD_TAHUN_PAJAK = 3;
    public static final int FRM_FIELD_TYPE_PAYMNET = 4;
    public static final int FRM_FIELD_STATUS = 5;

    public static String[] fieldNames = {
        "FRM_FIELD_OID_LOG_PAY",
        "FRM_FIELD_NPWPD",
        "FRM_FIELD_MASA_PAJAK",
        "FRM_FIELD_TAHUN_PAJAK",
        "FRM_FIELD_TYPE_PAYMNET",
        "FRM_FIELD_STATUS"
    };

    public static int[] fieldTypes = {
        TYPE_LONG,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_INT,
        TYPE_INT,
        TYPE_INT
    };

    public FrmLogPaymentWp() {
    }

    public FrmLogPaymentWp(LogPaymentWp entLogPaymentWp) {
        this.entLogPaymentWp = entLogPaymentWp;
    }

    public FrmLogPaymentWp(HttpServletRequest request, LogPaymentWp entLogPaymentWp) {
        super(new FrmLogPaymentWp(entLogPaymentWp), request);
        this.entLogPaymentWp = entLogPaymentWp;
    }

    public String getFormName() {
        return FRM_NAME_LOG_PAYMENT_WP;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int getFieldSize() {
        return fieldNames.length;
    }

    public LogPaymentWp getEntityObject() {
        return entLogPaymentWp;
    }

    public void requestEntityObject(LogPaymentWp entLogPaymentWp) {
        try {
            this.requestParam();
            entLogPaymentWp.setOidLogPay(getLong(FRM_FIELD_OID_LOG_PAY));
            entLogPaymentWp.setNpwpd(getString(FRM_FIELD_NPWPD));
            entLogPaymentWp.setMasaPajak(getString(FRM_FIELD_MASA_PAJAK));
            entLogPaymentWp.setTahunPajak(getInt(FRM_FIELD_TAHUN_PAJAK));
            entLogPaymentWp.setTypePaymnet(getInt(FRM_FIELD_TYPE_PAYMNET));
            entLogPaymentWp.setStatus(getInt(FRM_FIELD_STATUS));
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }

}
