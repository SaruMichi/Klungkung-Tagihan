/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dtaxintegration.form.bi;

import com.dimata.dtaxintegration.entity.bi.PajakTypeDetail;
import com.dimata.dtaxintegration.entity.bi.PstPajakTypeDetail;
import com.dimata.qdep.db.DBException;
import com.dimata.qdep.form.Control;
import com.dimata.qdep.form.FRMMessage;
import com.dimata.qdep.system.I_DBExceptionInfo;
import com.dimata.util.Command;
import com.dimata.util.lang.I_Language;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Ardiadi
 */
public class CtrlPajakTypeDetail extends Control implements I_Language {
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
private PajakTypeDetail entPajakTypeDetail;
private PstPajakTypeDetail pstPajakTypeDetail;
private FrmPajakTypeDetail frmPajakTypeDetail;
int language = LANGUAGE_DEFAULT;

public CtrlPajakTypeDetail(HttpServletRequest request) {
msgString = "";
entPajakTypeDetail = new PajakTypeDetail();
try {
pstPajakTypeDetail = new PstPajakTypeDetail(0);
} catch (Exception e) {;
}
frmPajakTypeDetail = new FrmPajakTypeDetail(request, entPajakTypeDetail);
}

private String getSystemMessage(int msgCode) {
switch (msgCode) {
case I_DBExceptionInfo.MULTIPLE_ID:
this.frmPajakTypeDetail.addError(frmPajakTypeDetail.FRM_FIELD_PAJAK_DETAIL_ID, resultText[language][RSLT_EST_CODE_EXIST]);
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

public PajakTypeDetail getPajakTypeDetail() {
return entPajakTypeDetail;
}

public FrmPajakTypeDetail getForm() {
return frmPajakTypeDetail;
}

public String getMessage() {
return msgString;
}

public int getStart() {
return start;
}

public int action(int cmd, long oidPajakTypeDetail) {
msgString = "";
int excCode = I_DBExceptionInfo.NO_EXCEPTION;
int rsCode = RSLT_OK;
switch (cmd) {
case Command.ADD:
break;

case Command.SAVE:
if (oidPajakTypeDetail != 0) {
try {
entPajakTypeDetail = PstPajakTypeDetail.fetchExc(oidPajakTypeDetail);
} catch (Exception exc) {
}
}

frmPajakTypeDetail.requestEntityObject(entPajakTypeDetail);

if (frmPajakTypeDetail.errorSize() > 0) {
msgString = FRMMessage.getMsg(FRMMessage.MSG_INCOMPLATE);
return RSLT_FORM_INCOMPLETE;
}

if (entPajakTypeDetail.getOID() == 0) {
try {
long oid = pstPajakTypeDetail.insertExc(this.entPajakTypeDetail);
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
long oid = pstPajakTypeDetail.updateExc(this.entPajakTypeDetail);
} catch (DBException dbexc) {
excCode = dbexc.getErrorCode();
msgString = getSystemMessage(excCode);
} catch (Exception exc) {
msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
}

}
break;

case Command.EDIT:
if (oidPajakTypeDetail != 0) {
try {
entPajakTypeDetail = PstPajakTypeDetail.fetchExc(oidPajakTypeDetail);
} catch (DBException dbexc) {
excCode = dbexc.getErrorCode();
msgString = getSystemMessage(excCode);
} catch (Exception exc) {
msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
}
}
break;

case Command.ASK:
if (oidPajakTypeDetail != 0) {
try {
entPajakTypeDetail = PstPajakTypeDetail.fetchExc(oidPajakTypeDetail);
} catch (DBException dbexc) {
excCode = dbexc.getErrorCode();
msgString = getSystemMessage(excCode);
} catch (Exception exc) {
msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
}
}
break;

case Command.DELETE:
if (oidPajakTypeDetail != 0) {
try {
long oid = PstPajakTypeDetail.deleteExc(oidPajakTypeDetail);
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
