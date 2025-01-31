/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.form.contentdata;

import com.dimata.dslik.entity.contentdata.ContentDataSukuBunga;
import com.dimata.dslik.entity.contentdata.PstContentDataSukuBunga;
import javax.servlet.http.*;
import com.dimata.util.*;
import com.dimata.util.lang.*;
import com.dimata.qdep.system.*;
import com.dimata.qdep.form.*;
import com.dimata.qdep.db.*;

/*
 Description : Controll ContentDataSukuBunga
 Date : Sat Sep 24 2016
 Author : Dewa
 */
public class CtrlContentDataSukuBunga extends Control implements I_Language {

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
    private ContentDataSukuBunga entContentDataSukuBunga;
    private PstContentDataSukuBunga pstContentDataSukuBunga;
    private FrmContentDataSukuBunga frmContentDataSukuBunga;
    int language = LANGUAGE_DEFAULT;

    public CtrlContentDataSukuBunga(HttpServletRequest request) {
        msgString = "";
        entContentDataSukuBunga = new ContentDataSukuBunga();
        try {
            pstContentDataSukuBunga = new PstContentDataSukuBunga(0);
        } catch (Exception e) {;
        }
        frmContentDataSukuBunga = new FrmContentDataSukuBunga(request, entContentDataSukuBunga);
    }

    private String getSystemMessage(int msgCode) {
        switch (msgCode) {
            case I_DBExceptionInfo.MULTIPLE_ID:
                this.frmContentDataSukuBunga.addError(frmContentDataSukuBunga.FRM_FIELD_SUKU_BUNGA_OID, resultText[language][RSLT_EST_CODE_EXIST]);
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

    public ContentDataSukuBunga getContentDataSukuBunga() {
        return entContentDataSukuBunga;
    }

    public FrmContentDataSukuBunga getForm() {
        return frmContentDataSukuBunga;
    }

    public String getMessage() {
        return msgString;
    }

    public int getStart() {
        return start;
    }

    public int action(int cmd, long oidContentDataSukuBunga, String oidDelete) {
        msgString = "";
        int excCode = I_DBExceptionInfo.NO_EXCEPTION;
        int rsCode = RSLT_OK;
        switch (cmd) {
            case Command.ADD:
                break;

            case Command.SAVE:
                if (oidContentDataSukuBunga != 0) {
                    try {
                        entContentDataSukuBunga = PstContentDataSukuBunga.fetchExc(oidContentDataSukuBunga);
                    } catch (Exception exc) {
                    }
                }

                frmContentDataSukuBunga.requestEntityObject(entContentDataSukuBunga);

                if (frmContentDataSukuBunga.errorSize() > 0) {
                    msgString = FRMMessage.getMsg(FRMMessage.MSG_INCOMPLATE);
                    return RSLT_FORM_INCOMPLETE;
                }

                if (entContentDataSukuBunga.getOID() == 0) {
                    try {
                        long oid = pstContentDataSukuBunga.insertExc(this.entContentDataSukuBunga);
                        msgString = FRMMessage.getMsg(FRMMessage.MSG_SAVED);
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
                        long oid = pstContentDataSukuBunga.updateExc(this.entContentDataSukuBunga);
                        msgString = FRMMessage.getMsg(FRMMessage.MSG_UPDATED);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }

                }
                break;

            case Command.EDIT:
                if (oidContentDataSukuBunga != 0) {
                    try {
                        entContentDataSukuBunga = PstContentDataSukuBunga.fetchExc(oidContentDataSukuBunga);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case Command.ASK:
                if (oidContentDataSukuBunga != 0) {
                    try {
                        entContentDataSukuBunga = PstContentDataSukuBunga.fetchExc(oidContentDataSukuBunga);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case Command.DELETE:
                if (oidContentDataSukuBunga != 0) {
                    try {
                        long oid = PstContentDataSukuBunga.deleteExc(oidContentDataSukuBunga);
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
