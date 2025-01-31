/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.form.masterdata;

import com.dimata.dslik.entity.masterdata.CabangBank;
import com.dimata.dslik.entity.masterdata.Periode;
import com.dimata.dslik.entity.masterdata.PeriodeCabang;
import com.dimata.dslik.entity.masterdata.PstCabangBank;
import com.dimata.dslik.entity.masterdata.PstPeriode;
import com.dimata.dslik.entity.masterdata.PstPeriodeCabang;
import com.dimata.dslik.session.proses.MoveDataCrosPeriode;
import javax.servlet.http.*;
import com.dimata.util.*;
import com.dimata.util.lang.*;
import com.dimata.qdep.system.*;
import com.dimata.qdep.form.*;
import com.dimata.qdep.db.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author Dewa
 */
/*
 Description : Controll Periode
 Date : Tue Oct 04 2016
 Author : Dewa
 */
public class CtrlPeriode extends Control implements I_Language {

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
    private Periode entPeriode;
    private PstPeriode pstPeriode;
    private FrmPeriode frmPeriode;
    int language = LANGUAGE_DEFAULT;

    public CtrlPeriode(HttpServletRequest request) {
        msgString = "";
        entPeriode = new Periode();
        try {
            pstPeriode = new PstPeriode(0);
        } catch (Exception e) {;
        }
        frmPeriode = new FrmPeriode(request, entPeriode);
    }

    private String getSystemMessage(int msgCode) {
        switch (msgCode) {
            case I_DBExceptionInfo.MULTIPLE_ID:
                this.frmPeriode.addError(frmPeriode.FRM_FIELD_PERIODE_ID, resultText[language][RSLT_EST_CODE_EXIST]);
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

    public Periode getPeriode() {
        return entPeriode;
    }

    public FrmPeriode getForm() {
        return frmPeriode;
    }

    public String getMessage() {
        return msgString;
    }

    public int getStart() {
        return start;
    }

    public int action(int cmd, long oidPeriode, String oidDelete) {
        msgString = "";
        int excCode = I_DBExceptionInfo.NO_EXCEPTION;
        int rsCode = RSLT_OK;
        switch (cmd) {
            case Command.ADD:
                break;

            case Command.SAVE:
                if (oidPeriode != 0) {
                    try {
                        entPeriode = PstPeriode.fetchExc(oidPeriode);
                    } catch (Exception exc) {
                    }
                }

                frmPeriode.requestEntityObject(entPeriode);

                if (frmPeriode.errorSize() > 0) {
                    msgString = FRMMessage.getMsg(FRMMessage.MSG_INCOMPLATE);
                    return RSLT_FORM_INCOMPLETE;
                }

                if (entPeriode.getOID() == 0) {
                    try {
                        long oid = pstPeriode.insertExc(this.entPeriode);

                        if (oid != 0) {

                            Vector listCabang = PstCabangBank.listAll();
                            for (int i = 0; i <= listCabang.size() - 1; i++) {
                                CabangBank cabangBank = (CabangBank) listCabang.get(i);
                                PeriodeCabang periodeCabang = new PeriodeCabang();
                                periodeCabang.setPeriodeId(oid);
                                periodeCabang.setCabangId(cabangBank.getOID());
                                periodeCabang.setKeterangan(this.entPeriode.getKeterangan());
                                periodeCabang.setStatus(0);
                                periodeCabang.setUserId(0);
                                periodeCabang.setTanggalCreate(new Date());
                                periodeCabang.setTanggalPosting(new Date());
                                long oidPeriodeCabang = PstPeriodeCabang.insertExc(periodeCabang);
                            }
                            
                        }

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
                            if(entPeriode.getPosted()==1){
                                long oid = pstPeriode.updateExc(this.entPeriode);
                                //proses buat periode baru, dan replace data ke data lama
                                Periode periode = new Periode();
                                //cek apakah periode sblmnya sudah ada
                                //firstdate
                                Date startFirstDateNextMonth = (Date)entPeriode.getTglAwal().clone();
                                startFirstDateNextMonth.setMonth(startFirstDateNextMonth.getMonth()+1);
                                Calendar calendar = Calendar.getInstance();
                                calendar.setTime(startFirstDateNextMonth);
                                int month = calendar.get(Calendar.MONTH);
                                int lastDate = calendar.getActualMaximum(Calendar.DATE);
                                
                                String[] indoMonth = {
                                    "Januari",
                                    "Februari",
                                    "Maret",
                                    "April",
                                    "Mei",
                                    "Juni",
                                    "Juli",
                                    "Agustus",
                                    "September",
                                    "Oktober",
                                    "November",
                                    "Desember"
                                };
                                //lastdate
                                Date startLastDateNextMonth = (Date)entPeriode.getTglAwal().clone();
                                startLastDateNextMonth.setMonth(startLastDateNextMonth.getMonth()+1);
                                startLastDateNextMonth.setDate(lastDate);
                                
                                periode.setTglAwal(startFirstDateNextMonth);
                                periode.setTglAkhir(startLastDateNextMonth);
                                periode.setNama(indoMonth[month] + " "+Formater.formatDate(startFirstDateNextMonth, "yyyy"));
                                periode.setKeterangan("");
                                periode.setPosted(0);
                                periode.setTglTerakhirEntry(startLastDateNextMonth);
                                
                                //cek apakah ada tanggal yang sama
                                long nextPeriodeExist = PstPeriode.checkOidNextPeriode(Formater.formatDate(startFirstDateNextMonth, "yyyy-MM-dd"));
                                if(nextPeriodeExist==0){
                                    long oidNextPeriode = pstPeriode.insertExc(periode);
                                    if (oidNextPeriode != 0) {
                                        Vector listCabang = PstCabangBank.listAll();
                                        for (int i = 0; i <= listCabang.size() - 1; i++) {
                                            CabangBank cabangBank = (CabangBank) listCabang.get(i);
                                            PeriodeCabang periodeCabang = new PeriodeCabang();
                                            periodeCabang.setPeriodeId(oidNextPeriode);
                                            periodeCabang.setCabangId(cabangBank.getOID());
                                            periodeCabang.setKeterangan(this.entPeriode.getKeterangan());
                                            periodeCabang.setStatus(0);
                                            periodeCabang.setUserId(0);
                                            periodeCabang.setTanggalCreate(new Date());
                                            periodeCabang.setTanggalPosting(new Date());
                                            long oidPeriodeCabang = PstPeriodeCabang.insertExc(periodeCabang);
                                        }
                                    }
                                    
                                    //proses transfer data dari periode awal ke periode baru
                                    
                                    long movePengurusPemilikId = MoveDataCrosPeriode.movePengurusPemilik(oid, oidNextPeriode);
                                    long oidPenjamin = MoveDataCrosPeriode.movePenjamin(oid, oidNextPeriode);
                                    long moveLapKeuanganId = MoveDataCrosPeriode.moveLapKeuangan(oid, oidNextPeriode);
                                    long moveKreditJoinId = MoveDataCrosPeriode.moveKreditJoin(oid, oidNextPeriode);
                                    long moveIrrevocableId= MoveDataCrosPeriode.moveIrrevocable(oid, oidNextPeriode);
                                    //long moveKreditId = MoveDataCrosPeriode.moveKredit(oid, oidNextPeriode);
                                    long moveFasilitasLainId = MoveDataCrosPeriode.moveFasilitasLain(oid, oidNextPeriode);
                                    //long moveDebiturId = MoveDataCrosPeriode.moveDebitur(oid, oidNextPeriode);
                                    //long moveBankGaransiId = MoveDataCrosPeriode.moveBankGaransi(oid, oidNextPeriode);
                                    //long moveAgunanId = MoveDataCrosPeriode.moveAgunan(oid, oidNextPeriode);
                                }else{
                                    //update next periode jadi open
                                    Periode entPeriodeNext = new Periode();
                                    if(nextPeriodeExist!=0){
                                        entPeriodeNext = PstPeriode.fetchExc(nextPeriodeExist);
                                        entPeriodeNext.setPosted(0);
                                        long oidNextPeriode = pstPeriode.updateExc(entPeriodeNext);
                                    }
                                }    
                            }else{
                                 //update jadi close semua, baru open
                                 long updateClosed = pstPeriode.updateAllPeriodeClosed();
                                 long oid = pstPeriode.updateExc(this.entPeriode);
                            }
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
                if (oidPeriode != 0) {
                    try {
                        entPeriode = PstPeriode.fetchExc(oidPeriode);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case Command.ASK:
                if (oidPeriode != 0) {
                    try {
                        entPeriode = PstPeriode.fetchExc(oidPeriode);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case Command.DELETE:
                if (oidPeriode != 0) {
                    try {
                        long oid = PstPeriode.deleteExc(oidPeriode);
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
