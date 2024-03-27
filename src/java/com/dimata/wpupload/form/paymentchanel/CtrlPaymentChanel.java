package com.dimata.wpupload.form.paymentchanel;


import javax.servlet.http.*;
import com.dimata.util.*;
import com.dimata.util.lang.*;
import com.dimata.qdep.system.*;
import com.dimata.qdep.form.*;
import com.dimata.qdep.db.*;
import com.dimata.wpupload.entity.paymentchanel.PaymentChanel;
import com.dimata.wpupload.entity.paymentchanel.PstPaymentChanel;

/*
Description : Controll PaymentChanel
Date : Tue Aug 23 2022
Author : 2017-132
 */
public class CtrlPaymentChanel extends Control implements I_Language {

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
    private PaymentChanel entPaymentChanel;
    private PstPaymentChanel pstPaymentChanel;
    private FrmPaymentChanel frmPaymentChanel;
    int language = LANGUAGE_DEFAULT;

    public CtrlPaymentChanel(HttpServletRequest request) {
        msgString = "";
        entPaymentChanel = new PaymentChanel();
        try {
            pstPaymentChanel = new PstPaymentChanel(0);
        } catch (Exception e) {;
        }
        frmPaymentChanel = new FrmPaymentChanel(request, entPaymentChanel);
    }

    private String getSystemMessage(int msgCode) {
        switch (msgCode) {
            case I_DBExceptionInfo.MULTIPLE_ID:
                this.frmPaymentChanel.addError(frmPaymentChanel.FRM_FIELD_PAYMENT_ID, resultText[language][RSLT_EST_CODE_EXIST]);
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

    public PaymentChanel getPaymentChanel() {
        return entPaymentChanel;
    }

    public FrmPaymentChanel getForm() {
        return frmPaymentChanel;
    }

    public String getMessage() {
        return msgString;
    }

    public int getStart() {
        return start;
    }

    public int action(int cmd, long oidPaymentChanel) {
        msgString = "";
        int excCode = I_DBExceptionInfo.NO_EXCEPTION;
        int rsCode = RSLT_OK;
        switch (cmd) {
            case Command.ADD:
                break;

            case Command.SAVE:
                if (oidPaymentChanel != 0) {
                    try {
                        entPaymentChanel = PstPaymentChanel.fetchExc(oidPaymentChanel);
                    } catch (Exception exc) {
                    }
                }

                frmPaymentChanel.requestEntityObject(entPaymentChanel);

                if (frmPaymentChanel.errorSize() > 0) {
                    msgString = FRMMessage.getMsg(FRMMessage.MSG_INCOMPLATE);
                    return RSLT_FORM_INCOMPLETE;
                }

                if (entPaymentChanel.getOID() == 0) {
                    try {
                        long oid = pstPaymentChanel.insertExc(this.entPaymentChanel);
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
                        long oid = pstPaymentChanel.updateExc(this.entPaymentChanel);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }

                }
                break;

            case Command.EDIT:
                if (oidPaymentChanel != 0) {
                    try {
                        entPaymentChanel = PstPaymentChanel.fetchExc(oidPaymentChanel);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case Command.ASK:
                if (oidPaymentChanel != 0) {
                    try {
                        entPaymentChanel = PstPaymentChanel.fetchExc(oidPaymentChanel);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case Command.DELETE:
                if (oidPaymentChanel != 0) {
                    try {
                        long oid = PstPaymentChanel.deleteExc(oidPaymentChanel);
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
