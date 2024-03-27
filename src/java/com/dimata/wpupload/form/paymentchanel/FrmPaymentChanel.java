package com.dimata.wpupload.form.paymentchanel;


import com.dimata.qdep.form.FRMHandler;
import com.dimata.qdep.form.I_FRMInterface;
import com.dimata.qdep.form.I_FRMType;
import com.dimata.wpupload.entity.paymentchanel.PaymentChanel;
import javax.servlet.http.HttpServletRequest;

public class FrmPaymentChanel extends FRMHandler implements I_FRMInterface, I_FRMType {

    private PaymentChanel entPaymentChanel;
    public static final String FRM_NAME_PAYMENT_CHANEL = "FRM_NAME_PAYMENT_CHANEL";
    public static final int FRM_FIELD_PAYMENT_ID = 0;
    public static final int FRM_FIELD_PAYMENT_NAME = 1;
    public static final int FRM_FIELD_PAYMENT_CODE = 2;
    public static final int FRM_FIELD_IDX = 3;

    public static String[] fieldNames = {
        "FRM_FIELD_PAYMENT_ID",
        "FRM_FIELD_PAYMENT_NAME",
        "FRM_FIELD_PAYMENT_CODE",
        "FRM_FIELD_IDX"
    };

    public static int[] fieldTypes = {
        TYPE_LONG,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_INT
    };

    public FrmPaymentChanel() {
    }

    public FrmPaymentChanel(PaymentChanel entPaymentChanel) {
        this.entPaymentChanel = entPaymentChanel;
    }

    public FrmPaymentChanel(HttpServletRequest request, PaymentChanel entPaymentChanel) {
        super(new FrmPaymentChanel(entPaymentChanel), request);
        this.entPaymentChanel = entPaymentChanel;
    }

    public String getFormName() {
        return FRM_NAME_PAYMENT_CHANEL;
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

    public PaymentChanel getEntityObject() {
        return entPaymentChanel;
    }

    public void requestEntityObject(PaymentChanel entPaymentChanel) {
        try {
            this.requestParam();
            entPaymentChanel.setPaymentId(getLong(FRM_FIELD_PAYMENT_ID));
            entPaymentChanel.setPaymentName(getString(FRM_FIELD_PAYMENT_NAME));
            entPaymentChanel.setPaymentCode(getString(FRM_FIELD_PAYMENT_CODE));
            entPaymentChanel.setIdx(getInt(FRM_FIELD_IDX));
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }

}
