/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.form.summaryfasilitas;

import com.dimata.dslik.entity.masterdata.Periode;
import com.dimata.dslik.entity.masterdata.PstPeriode;
import com.dimata.dslik.entity.summaryfasilitas.PstSummaryFasilitas;
import com.dimata.dslik.entity.summaryfasilitas.SummaryFasilitas;
import javax.servlet.http.*;
import com.dimata.util.*;
import com.dimata.util.lang.*;
import com.dimata.qdep.system.*;
import com.dimata.qdep.form.*;
import com.dimata.qdep.db.*;
import java.util.Vector;

/*
 Description : Controll SummaryFasilitas
 Date : Tue Sep 20 2016
 Author : Dewa
 */
public class CtrlSummaryFasilitas extends Control implements I_Language {

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
    private SummaryFasilitas entSummaryFasilitas;
    private PstSummaryFasilitas pstSummaryFasilitas;
    private FrmSummaryFasilitas frmSummaryFasilitas;
    int language = LANGUAGE_DEFAULT;

    public CtrlSummaryFasilitas(HttpServletRequest request) {
        msgString = "";
        entSummaryFasilitas = new SummaryFasilitas();
        try {
            pstSummaryFasilitas = new PstSummaryFasilitas(0);
        } catch (Exception e) {;
        }
        frmSummaryFasilitas = new FrmSummaryFasilitas(request, entSummaryFasilitas);
    }

    private String getSystemMessage(int msgCode) {
        switch (msgCode) {
            case I_DBExceptionInfo.MULTIPLE_ID:
                this.frmSummaryFasilitas.addError(frmSummaryFasilitas.FRM_FIELD_SUMMARY_FASILITAS_OID, resultText[language][RSLT_EST_CODE_EXIST]);
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

    public SummaryFasilitas getSummaryFasilitas() {
        return entSummaryFasilitas;
    }

    public FrmSummaryFasilitas getForm() {
        return frmSummaryFasilitas;
    }

    public String getMessage() {
        return msgString;
    }

    public int getStart() {
        return start;
    }

    public int action(int cmd, long oidSummaryFasilitas) {
        msgString = "";
        int excCode = I_DBExceptionInfo.NO_EXCEPTION;
        int rsCode = RSLT_OK;
        switch (cmd) {
            case Command.ADD:
                break;

            case Command.SAVE:
                if (oidSummaryFasilitas != 0) {
                    try {
                        entSummaryFasilitas = PstSummaryFasilitas.fetchExc(oidSummaryFasilitas);
                    } catch (Exception exc) {
                    }
                }

                frmSummaryFasilitas.requestEntityObject(entSummaryFasilitas);

                if (frmSummaryFasilitas.errorSize() > 0) {
                    msgString = FRMMessage.getMsg(FRMMessage.MSG_INCOMPLATE);
                    return RSLT_FORM_INCOMPLETE;
                }
                
                 
                
                if (entSummaryFasilitas.getOID() == 0) {
                    try {
                        long oid = pstSummaryFasilitas.insertExc(this.entSummaryFasilitas);
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
                        long oid = pstSummaryFasilitas.updateExc(this.entSummaryFasilitas);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }

                }
                break;

            case Command.EDIT:
                if (oidSummaryFasilitas != 0) {
                    try {
                        entSummaryFasilitas = PstSummaryFasilitas.fetchExc(oidSummaryFasilitas);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case Command.ASK:
                if (oidSummaryFasilitas != 0) {
                    try {
                        entSummaryFasilitas = PstSummaryFasilitas.fetchExc(oidSummaryFasilitas);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case Command.DELETE:
                if (oidSummaryFasilitas != 0) {
                    try {
                        long oid = PstSummaryFasilitas.deleteExc(oidSummaryFasilitas);
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
