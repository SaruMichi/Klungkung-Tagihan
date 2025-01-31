/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.form.irrevocablelc;

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
import com.dimata.dslik.entity.irrevocablelc.*;
import com.dimata.dslik.entity.masterdata.Periode;
import com.dimata.dslik.entity.masterdata.PstPeriode;
import java.util.Date;
import java.util.Vector;

/*
 Description : Controll IrrevocableLc
 Date : Wed Sep 21 2016
 Author : surya
 */
public class CtrlIrrevocableLc extends Control implements I_Language {

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
    private IrrevocableLc entIrrevocableLc;
    private PstIrrevocableLc pstIrrevocableLc;
    private FrmIrrevocableLc frmIrrevocableLc;
    int language = LANGUAGE_DEFAULT;
    IrrevocableLc prevIrrevocableLc = null;

    public CtrlIrrevocableLc(HttpServletRequest request) {
        msgString = "";
        entIrrevocableLc = new IrrevocableLc();
        try {
            pstIrrevocableLc = new PstIrrevocableLc(0);
        } catch (Exception e) {;
        }
        frmIrrevocableLc = new FrmIrrevocableLc(request, entIrrevocableLc);
    }

    private String getSystemMessage(int msgCode) {
        switch (msgCode) {
            case I_DBExceptionInfo.MULTIPLE_ID:
                this.frmIrrevocableLc.addError(frmIrrevocableLc.FRM_FIELD_IRREVOCABLE_LC, resultText[language][RSLT_EST_CODE_EXIST]);
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

    public IrrevocableLc getIrrevocableLc() {
        return entIrrevocableLc;
    }

    public FrmIrrevocableLc getForm() {
        return frmIrrevocableLc;
    }

    public String getMessage() {
        return msgString;
    }

    public int getStart() {
        return start;
    }

    public int action(int cmd, long oidIrrevocableLc, long userId, String namaUser) {
        msgString = "";
        int excCode = I_DBExceptionInfo.NO_EXCEPTION;
        int rsCode = RSLT_OK;
        switch (cmd) {
            case Command.ADD:
                break;

            case Command.SAVE:
                if (oidIrrevocableLc != 0) {
                    try {
                        entIrrevocableLc = PstIrrevocableLc.fetchExc(oidIrrevocableLc);
                    } catch (Exception exc) {
                    }
                    //history
                    try {
                        prevIrrevocableLc = PstIrrevocableLc.fetchExc(oidIrrevocableLc);
                    } catch (Exception exc) {
                    }
                }

                frmIrrevocableLc.requestEntityObject(entIrrevocableLc);

                if (frmIrrevocableLc.errorSize() > 0) {
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
                
                if(entIrrevocableLc.getPeriodeId()==0){
                    entIrrevocableLc.setPeriodeId(periodeId);
                }
                entIrrevocableLc.setFlagDetail("D");
                
                if (entIrrevocableLc.getOID() == 0) {
                    try {
                        entIrrevocableLc.setOperasiData("0");
                        entIrrevocableLc = pstIrrevocableLc.insertExcObj(this.entIrrevocableLc);
                        msgString = FRMMessage.getMsg(FRMMessage.MSG_SAVED);
                        if (entIrrevocableLc.getOID() != 0) {
                            String queryInsertData  = entIrrevocableLc.getSqlHIstory();
                            insertHistory(userId, namaUser, cmd, entIrrevocableLc.getOID(), queryInsertData);
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
                        entIrrevocableLc.setOperasiData("1");
                        entIrrevocableLc = pstIrrevocableLc.updateExcObj(this.entIrrevocableLc);
                        msgString = FRMMessage.getMsg(FRMMessage.MSG_UPDATED);
                        if (entIrrevocableLc.getOID() != 0) {
                            String queryInsertData  = entIrrevocableLc.getSqlHIstory();
                            insertHistory(userId, namaUser, cmd, entIrrevocableLc.getOID(),queryInsertData);
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
                if (oidIrrevocableLc != 0) {
                    try {
                        entIrrevocableLc = PstIrrevocableLc.fetchExc(oidIrrevocableLc);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case Command.ASK:
                if (oidIrrevocableLc != 0) {
                    try {
                        entIrrevocableLc = PstIrrevocableLc.fetchExc(oidIrrevocableLc);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case Command.DELETE:
                if (oidIrrevocableLc != 0) {
                    try {
                        long oid = PstIrrevocableLc.deleteExc(oidIrrevocableLc);
                        try {
                            prevIrrevocableLc = PstIrrevocableLc.fetchExc(oidIrrevocableLc);
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
            logSysHistory.setLogOpenUrl("irrevocableLc.jsp");
            logSysHistory.setLogUpdateDate(dateLog);
            logSysHistory.setLogDocumentType("IrrevocableLc");
            logSysHistory.setLogUserAction(Command.commandString[cmd]);
            logSysHistory.setLogDocumentNumber(this.entIrrevocableLc.getCif());
            logSysHistory.setLogDocumentId(oid);
            try {
                logSysHistory.setLogDetail(this.entIrrevocableLc.getLogDetail(prevIrrevocableLc));
            } catch (Exception e) {
            }
            
            logSysHistory.setQueryRoleBack(querySql);
            
            if (logSysHistory.getLogDetail()!=null || cmd == Command.DELETE) {
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
                                long oid = pstIrrevocableLc.deleteExc(oidRel);
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
