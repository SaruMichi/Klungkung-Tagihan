/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.form.debiturbdnusaha;

/**
 *
 * @author m20n9
 */
import com.dimata.common.entity.logger.LogSysHistory;
import com.dimata.common.entity.logger.PstLogSysHistory;
import javax.servlet.http.*;
import com.dimata.util.*;
import com.dimata.util.lang.*;
import com.dimata.qdep.system.*;
import com.dimata.qdep.form.*;
import com.dimata.qdep.db.*;
import com.dimata.dslik.entity.debiturbdnusaha.*;
import com.dimata.dslik.entity.masterdata.Periode;
import com.dimata.dslik.entity.masterdata.PstPeriode;
import java.util.Date;
import java.util.Vector;

/*
 Description : Controll DebiturBdnUsaha
 Date : Tue Sep 20 2016
 Author : surya
 */
public class CtrlDebiturBdnUsaha extends Control implements I_Language {

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
    private DebiturBdnUsaha entDebiturBdnUsaha;
    private PstDebiturBdnUsaha pstDebiturBdnUsaha;
    private FrmDebiturBdnUsaha frmDebiturBdnUsaha;
    int language = LANGUAGE_DEFAULT;
    DebiturBdnUsaha prevDebiturBdnUsaha = null;

    public CtrlDebiturBdnUsaha(HttpServletRequest request) {
        msgString = "";
        entDebiturBdnUsaha = new DebiturBdnUsaha();
        try {
            pstDebiturBdnUsaha = new PstDebiturBdnUsaha(0);
        } catch (Exception e) {;
        }
        frmDebiturBdnUsaha = new FrmDebiturBdnUsaha(request, entDebiturBdnUsaha);
    }

    private String getSystemMessage(int msgCode) {
        switch (msgCode) {
            case I_DBExceptionInfo.MULTIPLE_ID:
                this.frmDebiturBdnUsaha.addError(frmDebiturBdnUsaha.FRM_FIELD_DEBITUR_BDN_USHA_OID, resultText[language][RSLT_EST_CODE_EXIST]);
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

    public DebiturBdnUsaha getDebiturBdnUsaha() {
        return entDebiturBdnUsaha;
    }

    public FrmDebiturBdnUsaha getForm() {
        return frmDebiturBdnUsaha;
    }

    public String getMessage() {
        return msgString;
    }

    public int getStart() {
        return start;
    }

    public int action(int cmd, long oidDebiturBdnUsaha, long userId, String namaUser) {
        msgString = "";
        int excCode = I_DBExceptionInfo.NO_EXCEPTION;
        int rsCode = RSLT_OK;
        switch (cmd) {
            case Command.ADD:
                break;

            case Command.SAVE:
                
                String kodeJenisNsb="";
                if (oidDebiturBdnUsaha != 0) {
                    try {
                        entDebiturBdnUsaha = PstDebiturBdnUsaha.fetchExc(oidDebiturBdnUsaha);
                        kodeJenisNsb =  entDebiturBdnUsaha.getKodeJenisNsb();
                    } catch (Exception exc) {
                    }
                    try {
                        prevDebiturBdnUsaha = PstDebiturBdnUsaha.fetchExc(oidDebiturBdnUsaha);
                    } catch (Exception exc) {
                    }
                }

                frmDebiturBdnUsaha.requestEntityObject(entDebiturBdnUsaha);

                if (frmDebiturBdnUsaha.errorSize() > 0) {
                    msgString = FRMMessage.getMsg(FRMMessage.MSG_INCOMPLATE);
                    return RSLT_FORM_INCOMPLETE;
                }
                //tambahkan fungsi pengecekan periode yang aktive, update dengan periode tsb
                Periode periode = new Periode();
                long periodeId=0;
                try{
                    Vector listPeriode = PstPeriode.list(0, 1, ""+PstPeriode.fieldNames[PstPeriode.FLD_POSTED]+"='0'", "");
                    if(listPeriode != null){
                        periode = (Periode) listPeriode.get(0);
                        periodeId = periode.getOID();
                    }
                }catch(Exception ex){
                }
                
                if(entDebiturBdnUsaha.getPeriodeId()==0){
                    entDebiturBdnUsaha.setPeriodeId(periodeId);
                }
                
                entDebiturBdnUsaha.setFlagDetail("D");
                entDebiturBdnUsaha.setKodeJenisNsb(kodeJenisNsb);
                if (entDebiturBdnUsaha.getOID() == 0) {
                    try {
                        entDebiturBdnUsaha.setOperasiData("0");
                        entDebiturBdnUsaha = pstDebiturBdnUsaha.insertExcObj(this.entDebiturBdnUsaha);
                        msgString = FRMMessage.getMsg(FRMMessage.MSG_SAVED);
                        if (entDebiturBdnUsaha.getOID() != 0) {
                            String queryInsertData  = entDebiturBdnUsaha.getSqlHistory();
                            insertHistory(userId, namaUser, cmd, entDebiturBdnUsaha.getOID(), queryInsertData);
                        }
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
                        entDebiturBdnUsaha.setOperasiData("1");
                        entDebiturBdnUsaha = pstDebiturBdnUsaha.updateExcObj(this.entDebiturBdnUsaha);
                        msgString = FRMMessage.getMsg(FRMMessage.MSG_UPDATED);
                        if (entDebiturBdnUsaha.getOID() != 0) {
                            String queryInsertData  = entDebiturBdnUsaha.getSqlHistory();
                            insertHistory(userId, namaUser, cmd, entDebiturBdnUsaha.getOID(),queryInsertData);
                        }
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }

                }
                break;

            case Command.EDIT:
                if (oidDebiturBdnUsaha != 0) {
                    try {
                        entDebiturBdnUsaha = PstDebiturBdnUsaha.fetchExc(oidDebiturBdnUsaha);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case Command.ASK:
                if (oidDebiturBdnUsaha != 0) {
                    try {
                        entDebiturBdnUsaha = PstDebiturBdnUsaha.fetchExc(oidDebiturBdnUsaha);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case Command.DELETE:
                if (oidDebiturBdnUsaha != 0) {
                    try {
                        long oid = PstDebiturBdnUsaha.deleteExc(oidDebiturBdnUsaha);
                        try {
                            prevDebiturBdnUsaha = PstDebiturBdnUsaha.fetchExc(oidDebiturBdnUsaha);
                        } catch (Exception exc) {
                        }
                        if (oid != 0) {
                            if (oid != 0) {
                                insertHistory(userId, namaUser, cmd, oid,"");
                            }
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
    
    /**
     * 
     * history
     */
    public void insertHistory(long userID, String nameUser, int cmd, long oid, String querySql) {
        try {
            Date dateLog = new  Date();
            LogSysHistory logSysHistory = new LogSysHistory();
            logSysHistory.setLogUserId(userID);
            logSysHistory.setLogLoginName(nameUser);
            logSysHistory.setLogApplication("DSlik");
            logSysHistory.setLogOpenUrl("debiturBadanUsaha.jsp");
            logSysHistory.setLogUpdateDate(dateLog);
            logSysHistory.setLogDocumentType("DebiturBadanUsaha");
            logSysHistory.setLogUserAction(Command.commandString[cmd]);
            logSysHistory.setLogDocumentNumber(this.entDebiturBdnUsaha.getCif());
            logSysHistory.setLogDocumentId(oid);
            logSysHistory.setLogDetail(this.entDebiturBdnUsaha.getLogDetail(prevDebiturBdnUsaha));
            logSysHistory.setQueryRoleBack(querySql);
            
            if (!logSysHistory.getLogDetail().equals("") || cmd == Command.DELETE) {
                long oid2 = PstLogSysHistory.insertLog(logSysHistory);
            }
            
        } catch (Exception e) {

        }
    }
}
