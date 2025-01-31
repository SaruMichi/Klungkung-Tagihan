/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.form.contentdata;

import com.dimata.dslik.entity.contentdata.ContentDataSumberPenghasilan;
import com.dimata.dslik.entity.contentdata.PstContentDataSumberPenghasilan;
import javax.servlet.http.*;
import com.dimata.util.*;
import com.dimata.util.lang.*;
import com.dimata.qdep.system.*;
import com.dimata.qdep.form.*;
import com.dimata.qdep.db.*;

/*
 Description : Controll ContentDataSumberPenghasilan
 Date : Sat Sep 24 2016
 Author : Dewa
 */
public class CtrlContentDataSumberPenghasilan extends Control implements I_Language {

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
    private ContentDataSumberPenghasilan entContentDataSumberPenghasilan;
    private PstContentDataSumberPenghasilan pstContentDataSumberPenghasilan;
    private FrmContentDataSumberPenghasilan frmContentDataSumberPenghasilan;
    int language = LANGUAGE_DEFAULT;

    public CtrlContentDataSumberPenghasilan(HttpServletRequest request) {
        msgString = "";
        entContentDataSumberPenghasilan = new ContentDataSumberPenghasilan();
        try {
            pstContentDataSumberPenghasilan = new PstContentDataSumberPenghasilan(0);
        } catch (Exception e) {;
        }
        frmContentDataSumberPenghasilan = new FrmContentDataSumberPenghasilan(request, entContentDataSumberPenghasilan);
    }

    private String getSystemMessage(int msgCode) {
        switch (msgCode) {
            case I_DBExceptionInfo.MULTIPLE_ID:
                this.frmContentDataSumberPenghasilan.addError(frmContentDataSumberPenghasilan.FRM_FIELD_SUMBER_PENGHASILAN_OID, resultText[language][RSLT_EST_CODE_EXIST]);
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

    public ContentDataSumberPenghasilan getContentDataSumberPenghasilan() {
        return entContentDataSumberPenghasilan;
    }

    public FrmContentDataSumberPenghasilan getForm() {
        return frmContentDataSumberPenghasilan;
    }

    public String getMessage() {
        return msgString;
    }

    public int getStart() {
        return start;
    }

    public int action(int cmd, long oidContentDataSumberPenghasilan, String oidDelete) {
        msgString = "";
        int excCode = I_DBExceptionInfo.NO_EXCEPTION;
        int rsCode = RSLT_OK;
        switch (cmd) {
            case Command.ADD:
                break;

            case Command.SAVE:
                if (oidContentDataSumberPenghasilan != 0) {
                    try {
                        entContentDataSumberPenghasilan = PstContentDataSumberPenghasilan.fetchExc(oidContentDataSumberPenghasilan);
                    } catch (Exception exc) {
                    }
                }

                frmContentDataSumberPenghasilan.requestEntityObject(entContentDataSumberPenghasilan);

                if (frmContentDataSumberPenghasilan.errorSize() > 0) {
                    msgString = FRMMessage.getMsg(FRMMessage.MSG_INCOMPLATE);
                    return RSLT_FORM_INCOMPLETE;
                }

                if (entContentDataSumberPenghasilan.getOID() == 0) {
                    try {
                        long oid = pstContentDataSumberPenghasilan.insertExc(this.entContentDataSumberPenghasilan);
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
                        long oid = pstContentDataSumberPenghasilan.updateExc(this.entContentDataSumberPenghasilan);
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
                if (oidContentDataSumberPenghasilan != 0) {
                    try {
                        entContentDataSumberPenghasilan = PstContentDataSumberPenghasilan.fetchExc(oidContentDataSumberPenghasilan);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case Command.ASK:
                if (oidContentDataSumberPenghasilan != 0) {
                    try {
                        entContentDataSumberPenghasilan = PstContentDataSumberPenghasilan.fetchExc(oidContentDataSumberPenghasilan);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case Command.DELETE:
                if (oidContentDataSumberPenghasilan != 0) {
                    try {
                        long oid = PstContentDataSumberPenghasilan.deleteExc(oidContentDataSumberPenghasilan);
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
