/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.form.agunan;

import com.dimata.common.entity.logger.LogSysHistory;
import com.dimata.common.entity.logger.PstLogSysHistory;
import com.dimata.dslik.entity.agunan.Agunan;
import com.dimata.dslik.entity.agunan.PstAgunan;
import com.dimata.dslik.entity.masterdata.Periode;
import com.dimata.dslik.entity.masterdata.PstPeriode;
import javax.servlet.http.*;
import com.dimata.util.*;
import com.dimata.util.lang.*;
import com.dimata.qdep.system.*;
import com.dimata.qdep.form.*;
import com.dimata.qdep.db.*;
import java.util.Date;
import java.util.Vector;

/*
 Description : Controll Agunan
 Date : Wed Sep 21 2016
 Author : Dewa
 */
public class CtrlAgunan extends Control implements I_Language {

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
    private Agunan entAgunan;
    private PstAgunan pstAgunan;
    private FrmAgunan frmAgunan;
    int language = LANGUAGE_DEFAULT;
    Agunan prevAgunan = null;
    
    public CtrlAgunan(HttpServletRequest request) {
        msgString = "";
        entAgunan = new Agunan();
        try {
            pstAgunan = new PstAgunan(0);
        } catch (Exception e) {;
        }
        frmAgunan = new FrmAgunan(request, entAgunan);
    }

    private String getSystemMessage(int msgCode) {
        switch (msgCode) {
            case I_DBExceptionInfo.MULTIPLE_ID:
                this.frmAgunan.addError(frmAgunan.FRM_FIELD_AGUNAN_OID, resultText[language][RSLT_EST_CODE_EXIST]);
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

    public Agunan getAgunan() {
        return entAgunan;
    }

    public FrmAgunan getForm() {
        return frmAgunan;
    }

    public String getMessage() {
        return msgString;
    }

    public int getStart() {
        return start;
    }

    public int action(int cmd, long oidAgunan, long userId, String namaUser) {
        msgString = "";
        int excCode = I_DBExceptionInfo.NO_EXCEPTION;
        int rsCode = RSLT_OK;
        switch (cmd) {
            case Command.ADD:
                break;

            case Command.SAVE:
                if (oidAgunan != 0) {
                    
                    try {
                        entAgunan = PstAgunan.fetchExc(oidAgunan);
                    } catch (Exception exc) {
                        exc.printStackTrace();
                    }
                    
                    //history
                    try {
                        prevAgunan = PstAgunan.fetchExc(oidAgunan);
                    } catch (Exception exc) {
                        exc.printStackTrace();
                    }
                    
                }

                frmAgunan.requestEntityObject(entAgunan);

                if (frmAgunan.errorSize() > 0) {
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
                
                if(entAgunan.getPeriodeId()==0){
                    entAgunan.setPeriodeId(periodeId);
                }
                entAgunan.setFlagDetail("D");
                
                if (entAgunan.getOID() == 0) {
                    try {
                        entAgunan.setOperasiData("0");
                        entAgunan = pstAgunan.insertExcObj(this.entAgunan);
                        msgString = FRMMessage.getMsg(FRMMessage.MSG_SAVED);
                        if (entAgunan.getOID() != 0) {
                            //history
                            String queryInsertData  = entAgunan.getSqlHistory();
                            insertHistory(userId, namaUser, cmd, entAgunan.getOID(), queryInsertData);
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
                        entAgunan.setOperasiData("1");
                        entAgunan = pstAgunan.updateExcObj(this.entAgunan);
                        msgString = FRMMessage.getMsg(FRMMessage.MSG_UPDATED);
                        if (entAgunan.getOID() != 0) {
                            //history
                            String queryInsertData  = entAgunan.getSqlHistory();
                            insertHistory(userId, namaUser, cmd, entAgunan.getOID(),queryInsertData);
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
                if (oidAgunan != 0) {
                    try {
                        entAgunan = PstAgunan.fetchExc(oidAgunan);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case Command.ASK:
                if (oidAgunan != 0) {
                    try {
                        entAgunan = PstAgunan.fetchExc(oidAgunan);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case Command.DELETE:
                if (oidAgunan != 0) {
                    try {
                        long oid = PstAgunan.deleteExc(oidAgunan);
                        
                        try {
                            prevAgunan = PstAgunan.fetchExc(oidAgunan);
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
            logSysHistory.setLogOpenUrl("agunan.jsp");
            logSysHistory.setLogUpdateDate(dateLog);
            logSysHistory.setLogDocumentType("Agunan");
            logSysHistory.setLogUserAction(Command.commandString[cmd]);
            logSysHistory.setLogDocumentNumber(this.entAgunan.getKodeRegisterAgunan());
            logSysHistory.setLogDocumentId(oid);
            try {
                logSysHistory.setLogDetail(this.entAgunan.getLogDetail(prevAgunan));
            } catch (Exception e) {
            }
            
            logSysHistory.setQueryRoleBack(querySql);
            
            if (logSysHistory!=null || cmd == Command.DELETE) {
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
                                long oid = pstAgunan.deleteExc(oidRel);
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
