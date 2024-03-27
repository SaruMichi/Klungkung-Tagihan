/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.form.suratberharga;

/**
 *
 * @author m20n9
 */
import com.dimata.common.entity.logger.LogSysHistory;
import com.dimata.common.entity.logger.PstLogSysHistory;
import com.dimata.dslik.entity.masterdata.Periode;
import com.dimata.dslik.entity.masterdata.PstPeriode;
import javax.servlet.http.*;
import com.dimata.util.*;
import com.dimata.util.lang.*;
import com.dimata.qdep.system.*;
import com.dimata.qdep.form.*;
import com.dimata.qdep.db.*;
import com.dimata.dslik.entity.suratberharga.*;
import java.util.Date;
import java.util.Vector;

/*
 Description : Controll SuratBerharga
 Date : Wed Sep 21 2016
 Author : surya
 */
public class CtrlSuratBerharg extends Control implements I_Language {

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
    private SuratBerharga entSuratBerharga;
    private PstSuratBerharga pstSuratBerharga;
    private FrmSuratBerharga frmSuratBerharga;
    int language = LANGUAGE_DEFAULT;
    SuratBerharga prevSuratBerharga = null;

    public CtrlSuratBerharg(HttpServletRequest request) {
        msgString = "";
        entSuratBerharga = new SuratBerharga();
        try {
            pstSuratBerharga = new PstSuratBerharga(0);
        } catch (Exception e) {;
        }
        frmSuratBerharga = new FrmSuratBerharga(request, entSuratBerharga);
    }

    private String getSystemMessage(int msgCode) {
        switch (msgCode) {
            case I_DBExceptionInfo.MULTIPLE_ID:
                this.frmSuratBerharga.addError(frmSuratBerharga.FRM_FIELD_SURAT_BERHARGA_OID, resultText[language][RSLT_EST_CODE_EXIST]);
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

    public SuratBerharga getSuratBerharga() {
        return entSuratBerharga;
    }

    public FrmSuratBerharga getForm() {
        return frmSuratBerharga;
    }

    public String getMessage() {
        return msgString;
    }

    public int getStart() {
        return start;
    }

    public int action(int cmd, long oidSuratBerharga, long userId, String namaUser) {
        msgString = "";
        int excCode = I_DBExceptionInfo.NO_EXCEPTION;
        int rsCode = RSLT_OK;
        switch (cmd) {
            case Command.ADD:
                break;

            case Command.SAVE:
                if (oidSuratBerharga != 0) {
                    try {
                        entSuratBerharga = PstSuratBerharga.fetchExc(oidSuratBerharga);
                    } catch (Exception exc) {
                    }
                    try {
                        prevSuratBerharga = PstSuratBerharga.fetchExc(oidSuratBerharga);
                    } catch (Exception exc) {
                    }
                }

                frmSuratBerharga.requestEntityObject(entSuratBerharga);

                if (frmSuratBerharga.errorSize() > 0) {
                    msgString = FRMMessage.getMsg(FRMMessage.MSG_INCOMPLATE);
                    return RSLT_FORM_INCOMPLETE;
                }
                
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
                
                if(entSuratBerharga.getPeriodeId()==0){
                    entSuratBerharga.setPeriodeId(periodeId);
                }
                entSuratBerharga.setFlagDetail("D");
                
                
                
                if (entSuratBerharga.getOID() == 0) {
                    try {
                        entSuratBerharga = pstSuratBerharga.insertExcObj(this.entSuratBerharga);
                        msgString = FRMMessage.getMsg(FRMMessage.MSG_SAVED);
                        if (entSuratBerharga.getOID() != 0) {
                            //history
                            String queryInsertData  = entSuratBerharga.getSqlHistory();
                            insertHistory(userId, namaUser, cmd, entSuratBerharga.getOID(), queryInsertData);
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
                        entSuratBerharga = pstSuratBerharga.updateExcObj(this.entSuratBerharga);
                        msgString = FRMMessage.getMsg(FRMMessage.MSG_UPDATED);
                        if (entSuratBerharga.getOID() != 0) {
                            //history
                            String queryInsertData  = entSuratBerharga.getSqlHistory();
                            insertHistory(userId, namaUser, cmd, entSuratBerharga.getOID(),queryInsertData);
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
                if (oidSuratBerharga != 0) {
                    try {
                        entSuratBerharga = PstSuratBerharga.fetchExc(oidSuratBerharga);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case Command.ASK:
                if (oidSuratBerharga != 0) {
                    try {
                        entSuratBerharga = PstSuratBerharga.fetchExc(oidSuratBerharga);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case Command.DELETE:
                if (oidSuratBerharga != 0) {
                    try {
                        long oid = PstSuratBerharga.deleteExc(oidSuratBerharga);
                        try {
                            prevSuratBerharga = PstSuratBerharga.fetchExc(oidSuratBerharga);
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
            logSysHistory.setLogOpenUrl("suratBerharga.jsp");
            logSysHistory.setLogUpdateDate(dateLog);
            logSysHistory.setLogDocumentType("SuratBerharga");
            logSysHistory.setLogUserAction(Command.commandString[cmd]);
            logSysHistory.setLogDocumentNumber(this.entSuratBerharga.getCif());
            logSysHistory.setLogDocumentId(oid);
            logSysHistory.setLogDetail(this.entSuratBerharga.getLogDetail(prevSuratBerharga));
            logSysHistory.setQueryRoleBack(querySql);
            
            if (!logSysHistory.getLogDetail().equals("") || cmd == Command.DELETE) {
                long oid2 = PstLogSysHistory.insertLog(logSysHistory);
            }
            
        } catch (Exception e) {

        }
    }
    
    public int action(int cmd, long oidReligion, String religionDeleteReligion) {
        msgString = "";
        int excCode = I_DBExceptionInfo.NO_EXCEPTION;
        int rsCode = RSLT_OK;
        switch (cmd) {

            case Command.DELETEALL:
                //splits => untuk mengurutkan beberapa data menjadi baris data
                String[] splits = religionDeleteReligion.split(",");
                for (String asset : splits) {
                    if (asset != "") {
                        long oidRel = Long.parseLong(asset);
                        if (oidRel != 0) {
                            try {
                                long oid = pstSuratBerharga.deleteExc(oidRel);
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
                    }
                }
                break;

            default:

        }
        return rsCode;

    }
}
