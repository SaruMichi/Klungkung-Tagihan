package com.dimata.wpupload.form.logpaaymentwp;


import javax.servlet.http.*;
import com.dimata.util.*;
import com.dimata.util.lang.*;
import com.dimata.qdep.system.*;
import com.dimata.qdep.form.*;
import com.dimata.qdep.db.*;
import com.dimata.wpupload.entity.logspaymentwp.LogPaymentWp;
import com.dimata.wpupload.entity.logspaymentwp.PstLogPaymentWp;

/*
Description : Controll LogPaymentWp
Date : Fri Sep 02 2022
Author : 2017-132
 */
public class CtrlLogPaymentWp extends Control implements I_Language {

    public static int RSLT_OK = 0;
    public static int RSLT_UNKNOWN_ERROR = 1;
    public static int RSLT_EST_CODE_EXIST = 2;
    public static int RSLT_FORM_INCOMPLETE = 3;
    public static String[][] resultText = {
        {"Berhasil", "Tidak dapat diproses", "NoPerkiraan sudah ada", "Data tidak lengkap"},
        {"Succes", "Can not process", "Estimation code exist", "Data incomplete"}
    };
    private int start;
    private String msgString;
    private LogPaymentWp entLogPaymentWp;
    private PstLogPaymentWp pstLogPaymentWp;
    private FrmLogPaymentWp frmLogPaymentWp;
    int language = LANGUAGE_DEFAULT;

    public CtrlLogPaymentWp(HttpServletRequest request) {
        msgString = "";
        entLogPaymentWp = new LogPaymentWp();
        try {
            pstLogPaymentWp = new PstLogPaymentWp(0);
        } catch (Exception e) {;
        }
        frmLogPaymentWp = new FrmLogPaymentWp(request, entLogPaymentWp);
    }

    private String getSystemMessage(int msgCode) {
        switch (msgCode) {
            case I_DBExceptionInfo.MULTIPLE_ID:
                this.frmLogPaymentWp.addError(frmLogPaymentWp.FRM_FIELD_OID_LOG_PAY, resultText[language][RSLT_EST_CODE_EXIST]);
                return resultText[language][RSLT_EST_CODE_EXIST];
            default:
                return resultText[language][RSLT_UNKNOWN_ERROR];
        }
    }

    private int getControlMsgId(int msgCode) {
        switch (msgCode) {
            case I_DBExceptionInfo.MULTIPLE_ID:
                return RSLT_EST_CODE_EXIST;
            default:
                return RSLT_UNKNOWN_ERROR;
        }
    }

    public int getLanguage() {
        return language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }

    public LogPaymentWp getLogPaymentWp() {
        return entLogPaymentWp;
    }

    public FrmLogPaymentWp getForm() {
        return frmLogPaymentWp;
    }

    public String getMessage() {
        return msgString;
    }

    public int getStart() {
        return start;
    }

    public int action(int cmd, long oidLogPaymentWp) {
        msgString = "";
        int excCode = I_DBExceptionInfo.NO_EXCEPTION;
        int rsCode = RSLT_OK;
        switch (cmd) {
            case Command.ADD:
                break;

            case Command.SAVE:
                if (oidLogPaymentWp != 0) {
                    try {
                        entLogPaymentWp = PstLogPaymentWp.fetchExc(oidLogPaymentWp);
                    } catch (Exception exc) {
                    }
                }

                frmLogPaymentWp.requestEntityObject(entLogPaymentWp);

                if (frmLogPaymentWp.errorSize() > 0) {
                    msgString = FRMMessage.getMsg(FRMMessage.MSG_INCOMPLATE);
                    return RSLT_FORM_INCOMPLETE;
                }

                if (entLogPaymentWp.getOID() == 0) {
                    try {
                        long oid = pstLogPaymentWp.insertExc(this.entLogPaymentWp);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                        return getControlMsgId(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                        return getControlMsgId(I_DBExceptionInfo.UNKNOWN);
                    }

                } else {
                    try {
                        long oid = pstLogPaymentWp.updateExc(this.entLogPaymentWp);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }

                }
                break;

            case Command.EDIT:
                if (oidLogPaymentWp != 0) {
                    try {
                        entLogPaymentWp = PstLogPaymentWp.fetchExc(oidLogPaymentWp);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case Command.ASK:
                if (oidLogPaymentWp != 0) {
                    try {
                        entLogPaymentWp = PstLogPaymentWp.fetchExc(oidLogPaymentWp);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case Command.DELETE:
                if (oidLogPaymentWp != 0) {
                    try {
                        long oid = PstLogPaymentWp.deleteExc(oidLogPaymentWp);
                        if (oid != 0) {
                            msgString = FRMMessage.getMessage(FRMMessage.MSG_DELETED);
                            excCode = RSLT_OK;
                        } else {
                            msgString = FRMMessage.getMessage(FRMMessage.ERR_DELETED);
                            excCode = RSLT_FORM_INCOMPLETE;
                        }
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }
                }
                break;

            default:

        }
        return rsCode;
    }
}
