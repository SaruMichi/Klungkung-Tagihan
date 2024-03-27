/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.form.kreditjoinaccount;

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
import com.dimata.dslik.entity.kreditjoinaccount.*;
import com.dimata.dslik.entity.masterdata.Periode;
import com.dimata.dslik.entity.masterdata.PstPeriode;
import java.util.Date;
import java.util.Vector;

/*
 Description : Controll KreditJoinAccount
 Date : Wed Sep 21 2016
 Author : surya
 */
public class CtrlKreditJoinAccount extends Control implements I_Language {

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
    private KreditJoinAccount entKreditJoinAccount;
    private PstKreditJoinAccount pstKreditJoinAccount;
    private FrmKreditJoinAccount frmKreditJoinAccount;
    int language = LANGUAGE_DEFAULT;
    KreditJoinAccount prevKreditJoinAccount = null;

    public CtrlKreditJoinAccount(HttpServletRequest request) {
        msgString = "";
        entKreditJoinAccount = new KreditJoinAccount();
        try {
            pstKreditJoinAccount = new PstKreditJoinAccount(0);
        } catch (Exception e) {;
        }
        frmKreditJoinAccount = new FrmKreditJoinAccount(request, entKreditJoinAccount);
    }

    private String getSystemMessage(int msgCode) {
        switch (msgCode) {
            case I_DBExceptionInfo.MULTIPLE_ID:
                this.frmKreditJoinAccount.addError(frmKreditJoinAccount.FRM_FIELD_KREDIT_JOIN_ACNT_OID, resultText[language][RSLT_EST_CODE_EXIST]);
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

    public KreditJoinAccount getKreditJoinAccount() {
        return entKreditJoinAccount;
    }

    public FrmKreditJoinAccount getForm() {
        return frmKreditJoinAccount;
    }

    public String getMessage() {
        return msgString;
    }

    public int getStart() {
        return start;
    }

    public int action(int cmd, long oidKreditJoinAccount, long userId, String namaUser) {
        msgString = "";
        int excCode = I_DBExceptionInfo.NO_EXCEPTION;
        int rsCode = RSLT_OK;
        switch (cmd) {
            case Command.ADD:
                break;

            case Command.SAVE:
                if (oidKreditJoinAccount != 0) {
                    try {
                        entKreditJoinAccount = PstKreditJoinAccount.fetchExc(oidKreditJoinAccount);
                    } catch (Exception exc) {
                    }
                    
                    //history
                    try {
                        prevKreditJoinAccount = PstKreditJoinAccount.fetchExc(oidKreditJoinAccount);
                    } catch (Exception exc) {
                    }
                }

                frmKreditJoinAccount.requestEntityObject(entKreditJoinAccount);

                if (frmKreditJoinAccount.errorSize() > 0) {
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
                
                if(entKreditJoinAccount.getPeriodeId()==0){
                    entKreditJoinAccount.setPeriodeId(periodeId);
                }
                entKreditJoinAccount.setFlagDetail("D");
                
                if (entKreditJoinAccount.getOID() == 0) {
                    try {
                        entKreditJoinAccount.setOperasiData("0");
                        entKreditJoinAccount = pstKreditJoinAccount.insertExcObj(this.entKreditJoinAccount);
                        msgString = FRMMessage.getMsg(FRMMessage.MSG_SAVED);
                        //history
                        if (entKreditJoinAccount.getOID() != 0) {
                            String queryInsertData  = entKreditJoinAccount.getSqlHIstory();
                            insertHistory(userId, namaUser, cmd, entKreditJoinAccount.getOID(), queryInsertData);
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
                        entKreditJoinAccount.setOperasiData("1");
                        entKreditJoinAccount = pstKreditJoinAccount.updateExcObj(this.entKreditJoinAccount);
                        msgString = FRMMessage.getMsg(FRMMessage.MSG_UPDATED);
                        //history
                        if (entKreditJoinAccount.getOID() != 0) {
                            String queryInsertData  = entKreditJoinAccount.getSqlHIstory();
                            insertHistory(userId, namaUser, cmd, entKreditJoinAccount.getOID(),queryInsertData);
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
                if (oidKreditJoinAccount != 0) {
                    try {
                        entKreditJoinAccount = PstKreditJoinAccount.fetchExc(oidKreditJoinAccount);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case Command.ASK:
                if (oidKreditJoinAccount != 0) {
                    try {
                        entKreditJoinAccount = PstKreditJoinAccount.fetchExc(oidKreditJoinAccount);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case Command.DELETE:
                if (oidKreditJoinAccount != 0) {
                    try {
                        long oid = PstKreditJoinAccount.deleteExc(oidKreditJoinAccount);
                        try {
                            prevKreditJoinAccount = PstKreditJoinAccount.fetchExc(oidKreditJoinAccount);
                        } catch (Exception exc) {
                        }
                        if (oid != 0) {
                            //history
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
            logSysHistory.setLogOpenUrl("kreditJoinAccount.jsp");
            logSysHistory.setLogUpdateDate(dateLog);
            logSysHistory.setLogDocumentType("KreditJoinAccount");
            logSysHistory.setLogUserAction(Command.commandString[cmd]);
            logSysHistory.setLogDocumentNumber(this.entKreditJoinAccount.getCif());
            logSysHistory.setLogDocumentId(oid);
            logSysHistory.setLogDetail(this.entKreditJoinAccount.getLogDetail(prevKreditJoinAccount));
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
                                long oid = pstKreditJoinAccount.deleteExc(oidRel);
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