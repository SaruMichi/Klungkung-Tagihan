<%-- 
    Document   : report_phr_harian_excel
    Created on : Apr 22, 2016, 10:03:06 PM
    Author     : Administrator
--%>
<%-- 
    Document   : report_phr_harian
    Created on : Apr 22, 2016, 9:33:37 PM
    Author     : Administrator
--%>
<%-- 
    Document   : payment_phr
    Created on : May 19, 2015, 7:00:57 PM
    Author     : dimata005
--%>

<%@page import="com.dimata.webclient.AppSetting"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.dimata.dtaxintegration.session.DTaxIntegrationMonitor"%>
<%@page import="com.dimata.dtaxintegration.entity.payment.PaymentPhr"%>
<%@page import="com.dimata.dtaxintegration.entity.payment.PstPaymentPhr"%>
<%@page import="com.dimata.dtaxintegration.entity.inquery.Payment"%>
<%@page import="com.dimata.webclient.EchoLaporanPaymentDetail"%>
<%@page import="com.dimata.dtaxintegration.entity.laporan.LaporanPayment"%>
<%-- 
    Document   : report_bbm_summary
    Created on : May 1, 2015, 10:06:28 AM
    Author     : dimata005
--%>
<%@page import="com.dimata.util.Formater"%>
<%@page import="java.util.Date"%>
<%@page import="com.dimata.dtaxintegration.entity.inquery.Simpatda"%>
<%@page import="com.dimata.dtaxintegration.session.SessSimpatda"%>
<%@page import="com.dimata.dtaxintegration.session.SrcReport"%>
<%@page import="com.dimata.qdep.form.FRMHandler"%>
<%@page import="com.dimata.gui.jsp.ControlDate"%>
<%@page import="com.dimata.gui.jsp.ControlCombo"%>
<%@page import="com.dimata.util.Command"%>
<%@page import="com.dimata.gui.jsp.ControlLine"%>
<%@page import="com.dimata.qdep.form.FRMMessage"%>
<%@page import="com.dimata.qdep.form.FRMQueryString"%>
<%@page import="com.dimata.gui.jsp.ControlList"%>
<%@page import="java.util.Vector"%>
<!DOCTYPE html>
<%@ include file = "../../main/javainit.jsp" %>
<% //int  appObjCode = AppObjInfo.composeObjCode(AppObjInfo.G1_LOGIN, AppObjInfo.G2_LOGIN, AppObjInfo.OBJ_LOGIN_LOGIN); %>
<%@ include file = "../../main/checkuser.jsp" %>


<%!//proses yang akan ditampilkan

    public String drawListDataPaymentPhrDispenda(int iCommand, Vector objectClass, long gradeId) {

        ControlList ctrlist = new ControlList();
        ctrlist.setBorder(1);
        ctrlist.setAreaWidth("100%");
        ctrlist.setListStyle("listgen");
        ctrlist.setTitleStyle("tableheader");
        ctrlist.setCellStyle("cellStyle");
        ctrlist.setHeaderStyle("tableheader");
        
        ctrlist.addHeader("No", "200px");//1
        ctrlist.addHeader("TANGGAL", "200px");//2
        ctrlist.addHeader("POKOK", "200px");//3
        ctrlist.addHeader("DENDA", "200px");//4
        ctrlist.addHeader("JUMLAH_BAYAR", "200px");//5
        
        if (iCommand != Command.EDIT) {
            ctrlist.setLinkRow(0);
        }
        
        ctrlist.setLinkSufix("");
        Vector lstData = ctrlist.getData();
        Vector lstLinkData = ctrlist.getLinkData();
        ctrlist.reset();

        int index = -1;
        int count=0;
        Vector rowx = new Vector(1, 1);
        double total=0.0;
        double pokok=0.0;
        double denda=0.0;
        for (int i = 0; i < objectClass.size(); i++) {
            
            //Simpatda simpatda = (Simpatda) objectClass.get(i);
            PaymentPhr paymentPhr = (PaymentPhr) objectClass.get(i);
            count=count+1;
            rowx = new Vector(1, 1);
            
            rowx.add(""+count);//1
            rowx.add(""+paymentPhr.getTanggal());//2
            rowx.add(""+FRMHandler.userFormatStringDecimal(paymentPhr.getPokok()));//3
            rowx.add(""+Formater.formatNumber(paymentPhr.getDenda(),"#,###"));//4
            rowx.add(""+Formater.formatNumber(paymentPhr.getJumlahBayar(),"#,###"));//5
            total=total+paymentPhr.getJumlahBayar();
            pokok=pokok+paymentPhr.getPokok();
            denda=denda+paymentPhr.getDenda();
            lstData.add(rowx);
        }
        
        rowx = new Vector(1, 1);
        
        rowx.add("");//1
        rowx.add("<b>Total<b>");//7
        rowx.add(""+FRMHandler.userFormatStringDecimal(pokok));//8
        rowx.add(""+FRMHandler.userFormatStringDecimal(denda));//8
        rowx.add(""+FRMHandler.userFormatStringDecimal(total));//8
        
        lstData.add(rowx);

        return ctrlist.draw();
    }
%>

<% //proses yang tidak akan ditampilkan hanya diserver side

    int iCommand = FRMQueryString.requestCommand(request);
    int start = FRMQueryString.requestInt(request, "start");
    int prevCommand = FRMQueryString.requestInt(request, "prev_command");
    long oidDriver = FRMQueryString.requestLong(request, "hidden_driver_id");
    String gradeDelete = FRMQueryString.requestString(request, "gradeDelete");
    
    String startDate = FRMQueryString.requestString(request, "tanggalStart");
    String oDate = "";//FRMQueryString.requestString(request, "tanggalEnd");
    
    String startEnd = FRMQueryString.requestString(request, "tanggalEnd");
    String oEnd = "";//FRMQueryString.requestString(request, "tanggalEnd");
    
    double exchangeRate = FRMQueryString.requestDouble(request, "exchangeRate");
    double pemotongan = FRMQueryString.requestDouble(request, "pemotongan");
    
    /*variable declaration*/

    int recordToGet = 15;
    String msgString = "";
    int iErrCode = FRMMessage.NONE;
    String whereClause = "";
    String orderClause = "";//PstTransportServiceOrder.fieldNames[PstTransportServiceOrder.FLD_ID];
    ControlLine ctrLine = new ControlLine();
    Vector listReportDetail = new Vector(1, 1);

    //membuat command menjadi none kembali setelah menjalankan delete all
    if (iCommand == Command.DELETEALL && iErrCode == FRMMessage.NONE) {
        iCommand = Command.NONE;
    }

    /* end switch*/

    //int vectSize = PstTransportServiceOrder.getCount(whereClause);

    /*switch list Student*/

    if (iCommand == Command.FIRST || iCommand == Command.NEXT || iCommand == Command.PREV || iCommand == Command.LAST) {
        start = 0;//ctrlTransportServiceOrder.action(iCommand, start, vectSize, recordToGet);
    }

    /* get record to display */
    Date newDay=new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    if(startDate.equals("")){
        startDate=Formater.formatDate(newDay,"yyyy-MM-dd");
        startEnd=Formater.formatDate(newDay,"yyyy-MM-dd");
        Date transaksiDate = formatter.parse(startDate);
        oDate = Formater.formatDate(transaksiDate,"yyyy-MM-dd HH:mm:ss");
        oEnd = Formater.formatDate(transaksiDate,"yyyy-MM-dd HH:mm:ss");
    }else{
        String dateStringTransaksi = startDate;
        String dateEndStringTransaksi = startEnd;
        try {
                Date transaksiDate = formatter.parse(dateStringTransaksi);
                oDate = Formater.formatDate(transaksiDate,"yyyy-MM-dd HH:mm:ss");
                
                Date transaksiEndDate = formatter.parse(dateEndStringTransaksi);
                oEnd = Formater.formatDate(transaksiEndDate,"yyyy-MM-dd HH:mm:ss");
        } catch (Exception e) {
                e.printStackTrace();
        }
    }
    
    /*data table payment*/
    Vector listPaymentPhr = new Vector();
    /*order_date BETWEEN TO_DATE ('2014/02/01', 'yyyy/mm/dd')
      AND TO_DATE ('2014/02/28', 'yyyy/mm/dd');*/
    if(AppSetting.SQL_VERSION == AppSetting.DBSVR_ORACLE){
        whereClause = PstPaymentPhr.fieldNames[PstPaymentPhr.FLD_STATUS]+"='0'"
                  +" AND "+PstPaymentPhr.fieldNames[PstPaymentPhr.FLD_TANGGAL]
                  +" BETWEEN TO_DATE ('"+oDate+"','YYYY-MM-DD HH24:MI:SS')"
                  +" AND TO_DATE ('"+oEnd+"','YYYY-MM-DD HH24:MI:SS')";
    }else{
        whereClause = PstPaymentPhr.fieldNames[PstPaymentPhr.FLD_STATUS]+"='0'"
                  +" AND "+PstPaymentPhr.fieldNames[PstPaymentPhr.FLD_TANGGAL]
                  +" BETWEEN ('"+oDate+"')"
                  +" AND ('"+oEnd+"')";
    }
    
    
    //listPaymentPhr = PstPaymentPhr.list(0, 0, whereClause, "");
    listPaymentPhr = PstPaymentPhr.listSum(0, 0, whereClause, 
            ""+PstPaymentPhr.fieldNames[PstPaymentPhr.FLD_TANGGAL]+" ASC ", 
            ""+PstPaymentPhr.fieldNames[PstPaymentPhr.FLD_TANGGAL]);
    
%>
<%@ page contentType="application/x-msexcel" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Report PHR Summary</title>
    </head>
    <body class="skin-blue">
        <table width="100%" cellspacing="0" cellpadding="3">
            <tr align="left" valign="top">
                  <td valign="middle" colspan="3" class="command">
                      <h3> Laporan Pajak PHR Harian</h3>
                  </td>
            </tr>
             <tr align="left" valign="top">
                  <td  valign="middle" colspan="3" class="command">
                      <h2><%=AppSetting.REPORT_INSTANSI%></h2>
                  </td>
            </tr>
            <tr align="left" valign="top">
                  <td  valign="middle" colspan="6" class="command">
                      <h3><%=AppSetting.REPORT_DINAS%></h3>
                  </td>
            </tr>
            <tr align="left" valign="top">
                  <td  valign="middle" colspan="3" class="command">
                      <h4><%=AppSetting.REPORT_INSTANSI_ALAMAT%></h4>
                  </td>
            </tr>
            <tr align="left" valign="top">
                  <td valign="middle" colspan="3" class="command">
                      <h4>Dari Tanggal <%=startDate%> S/D <%=startEnd%></h4>
                  </td>
            </tr>
            <tr align="left" valign="top">
                <td valign="middle" colspan="3" class="command">&nbsp;
                </td>
            </tr>
        </table>
         <%=drawListDataPaymentPhrDispenda(iCommand, listPaymentPhr, oidDriver)%>
    </body>
</html>