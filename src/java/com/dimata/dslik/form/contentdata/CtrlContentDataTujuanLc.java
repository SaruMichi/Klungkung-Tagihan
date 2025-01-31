/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.form.contentdata;

import com.dimata.dslik.entity.contentdata.ContentDataTujuanLc;
import com.dimata.dslik.entity.contentdata.PstContentDataTujuanLc;
import javax.servlet.http.*;
import com.dimata.util.*;
import com.dimata.util.lang.*;
import com.dimata.qdep.system.*;
import com.dimata.qdep.form.*;
import com.dimata.qdep.db.*;

/*
 Description : Controll ContentDataTujuanLc
 Date : Sat Sep 24 2016
 Author : Dewa
 */
public class CtrlContentDataTujuanLc extends Control implements I_Language {

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
    private ContentDataTujuanLc entContentDataTujuanLc;
    private PstContentDataTujuanLc pstContentDataTujuanLc;
    private FrmContentDataTujuanLc frmContentDataTujuanLc;
    int language = LANGUAGE_DEFAULT;

    public CtrlContentDataTujuanLc(HttpServletRequest request) {
        msgString = "";
        entContentDataTujuanLc = new ContentDataTujuanLc();
        try {
            pstContentDataTujuanLc = new PstContentDataTujuanLc(0);
        } catch (Exception e) {;
        }
        frmContentDataTujuanLc = new FrmContentDataTujuanLc(request, entContentDataTujuanLc);
    }

    private String getSystemMessage(int msgCode) {
        switch (msgCode) {
            case I_DBExceptionInfo.MULTIPLE_ID:
                this.frmContentDataTujuanLc.addError(frmContentDataTujuanLc.FRM_FIELD_TUJUAN_LC_OID, resultText[language][RSLT_EST_CODE_EXIST]);
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

    public ContentDataTujuanLc getContentDataTujuanLc() {
        return entContentDataTujuanLc;
    }

    public FrmContentDataTujuanLc getForm() {
        return frmContentDataTujuanLc;
    }

    public String getMessage() {
        return msgString;
    }

    public int getStart() {
        return start;
    }

    public int action(int cmd, long oidContentDataTujuanLc, String oidDelete) {
        msgString = "";
        int excCode = I_DBExceptionInfo.NO_EXCEPTION;
        int rsCode = RSLT_OK;
        switch (cmd) {
            case Command.ADD:
                break;

            case Command.SAVE:
                if (oidContentDataTujuanLc != 0) {
                    try {
                        entContentDataTujuanLc = PstContentDataTujuanLc.fetchExc(oidContentDataTujuanLc);
                    } catch (Exception exc) {
                    }
                }

                frmContentDataTujuanLc.requestEntityObject(entContentDataTujuanLc);

                if (frmContentDataTujuanLc.errorSize() > 0) {
                    msgString = FRMMessage.getMsg(FRMMessage.MSG_INCOMPLATE);
                    return RSLT_FORM_INCOMPLETE;
                }

                if (entContentDataTujuanLc.getOID() == 0) {
                    try {
                        long oid = pstContentDataTujuanLc.insertExc(this.entContentDataTujuanLc);
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
                        long oid = pstContentDataTujuanLc.updateExc(this.entContentDataTujuanLc);
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
                if (oidContentDataTujuanLc != 0) {
                    try {
                        entContentDataTujuanLc = PstContentDataTujuanLc.fetchExc(oidContentDataTujuanLc);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case Command.ASK:
                if (oidContentDataTujuanLc != 0) {
                    try {
                        entContentDataTujuanLc = PstContentDataTujuanLc.fetchExc(oidContentDataTujuanLc);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case Command.DELETE:
                if (oidContentDataTujuanLc != 0) {
                    try {
                        long oid = PstContentDataTujuanLc.deleteExc(oidContentDataTujuanLc);
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
