package com.dimata.dtaxintegration.form.logsApi;

import com.dimata.dtaxintegration.entity.logsApi.LogApi;
import com.dimata.qdep.form.FRMHandler;
import com.dimata.qdep.form.I_FRMInterface;
import com.dimata.qdep.form.I_FRMType;
import javax.servlet.http.HttpServletRequest; 

public class FrmLogApi extends FRMHandler implements I_FRMInterface, I_FRMType {

    private LogApi entLogApi;
    public static final String FRM_NAME_LOG_API = "FRM_NAME_LOG_API";
    public static final int FRM_FIELD_OID_API = 0;
    public static final int FRM_FIELD_USER_ACCES = 1;
    public static final int FRM_FIELD_API_NAME = 2;
    public static final int FRM_FIELD_MODUL_NAME = 3;
    public static final int FRM_FIELD_REQ_DATE = 4;
    public static final int FRM_FIELD_STATUS = 5;
    public static final int FRM_FIELD_MESSAGE = 6;

    public static String[] fieldNames = {
        "FRM_FIELD_OID_API",
        "FRM_FIELD_USER_ACCES",
        "FRM_FIELD_API_NAME",
        "FRM_FIELD_MODUL_NAME",
        "FRM_FIELD_REQ_DATE",
        "FRM_FIELD_STATUS",
        "FRM_FIELD_MESSAGE"
    };

    public static int[] fieldTypes = {
        TYPE_LONG,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_DATE,
        TYPE_STRING,
        TYPE_STRING
    };

    public FrmLogApi() {
    }

    public FrmLogApi(LogApi entLogApi) {
        this.entLogApi = entLogApi;
    }

    public FrmLogApi(HttpServletRequest request, LogApi entLogApi) {
        super(new FrmLogApi(entLogApi), request);
        this.entLogApi = entLogApi;
    }

    public String getFormName() {
        return FRM_NAME_LOG_API;
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

    public LogApi getEntityObject() {
        return entLogApi;
    }

    public void requestEntityObject(LogApi entLogApi) {
        try {
            this.requestParam(); 
            entLogApi.setApiName(getString(FRM_FIELD_API_NAME));
            entLogApi.setModulName(getString(FRM_FIELD_MODUL_NAME));
            entLogApi.setReqDate(getDate(FRM_FIELD_REQ_DATE));
            entLogApi.setStatus(getString(FRM_FIELD_STATUS));
            entLogApi.setMessage(getString(FRM_FIELD_MESSAGE));
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }

}
