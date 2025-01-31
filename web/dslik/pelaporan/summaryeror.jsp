<%-- 
    Document   : summaryeror
    Created on : Jan 13, 2017, 9:03:43 AM
    Author     : dimata005
--%>
<%-- 
    Document   : pelaporan_per_segment
    Created on : Oct 2, 2016, 6:41:31 PM
    Author     : dimata005
--%>
<%@page import="com.dimata.gui.jsp.ControlList"%>
<%@page import="com.dimata.dslik.entity.debiturindividu.PstDebiturIndividu"%>
<%@page import="com.dimata.dslik.entity.suratberharga.PstSuratBerharga"%>
<%@page import="com.dimata.dslik.entity.penjamin.PstPenjamin"%>
<%@page import="com.dimata.dslik.entity.pengurusataupemilik.PstPengurusAtauPemilik"%>
<%@page import="com.dimata.dslik.entity.laporankeuangandebitur.PstLaporanKeuanganDebitur"%>
<%@page import="com.dimata.dslik.entity.kreditjoinaccount.PstKreditJoinAccount"%>
<%@page import="com.dimata.dslik.entity.kredit.PstKredit"%>
<%@page import="com.dimata.dslik.entity.irrevocablelc.PstIrrevocableLc"%>
<%@page import="com.dimata.dslik.entity.fasilitaslain.PstFasilitasLain"%>
<%@page import="com.dimata.dslik.entity.debiturbdnusaha.PstDebiturBdnUsaha"%>
<%@page import="com.dimata.dslik.entity.bankgaransi.PstBankGaransi"%>
<%@page import="com.dimata.dslik.entity.agunan.PstAgunan"%>
<%@page import="com.dimata.dslik.ajax.AjaxPelaporan"%>
<%@page import="com.dimata.dslik.entity.masterdata.CabangBank"%>
<%@page import="com.dimata.dslik.entity.masterdata.PstCabangBank"%>
<%@page import="com.dimata.dslik.session.proses.ManagerTransferData"%>
<%@page import="com.dimata.qdep.form.FRMQueryString"%>
<%@page import="com.dimata.dslik.form.masterdata.FrmConnection"%>
<%@page import="com.dimata.gui.jsp.ControlCombo"%>
<%@page import="java.util.Vector"%>
<%@page import="com.dimata.util.Command"%>
<!DOCTYPE html>
<%!
    public final static int SEGMEN_DEBITUR_INDIVIDU = 0;
    public final static int SEGMEN_DEBITUR_BADAN_USAHA = 1;
    public final static int SEGMEN_PENGURUS_PEMILIK = 2;
    public final static int SEGMEN_KREDIT = 3;
    public final static int SEGMEN_BANK_GARANSI = 4;
    public final static int SEGMEN_AGUNAN = 5;
    public final static int SEGMEN_PENJAMIN = 6;
    public final static int SEGMEN_LAPORAN_KEUANGAN_DEBITUR = 7;
    public final static int SEGMEN_SURAT_BERHARGA = 8;
    public final static int SEGMEN_KREDIT_JOIN = 9;
    public final static int SEGMEN_IRREVOCABLE_LC = 10;
    public final static int SEGMEN_FASILITAS_LAINNYA = 11;
    
    public final static String[] segmenNames = {
        "Debitur Individu",
        "Debitur Badan Usaha",
        "Pengurus/Pemilik",
        "Kredit",
        "Bank Garansi",
        "Agunan",
        "Penjamin",
        "Laporan Keuangan Debitur",
        "Surat Berharga",
        "Kredit Join",
        "Irrevocable L/C",
        "Fasilitas Lainnya"
    };

    public String getPerSegmentReport(JspWriter outObj, long periode, String cabang, int segment, int kelengkapan){
        String returnData = "";
        String whereClause = "";
        Vector listObj = new Vector(1,1);
        switch(segment){
            case SEGMEN_AGUNAN :
                if(cabang.length() > 0){
                    whereClause +=" AND "+PstAgunan.fieldNames[PstAgunan.FLD_KODE_KANTOR_CABANG]+"='"+cabang+"' ";
                }
                if(kelengkapan==1){
                    whereClause +=" AND "+PstAgunan.fieldNames[PstAgunan.FLD_STATUS_DATA_PERUBAHAN]+"='0' ";
                }else if(kelengkapan ==2 ){
                    whereClause +=" AND "+PstAgunan.fieldNames[PstAgunan.FLD_STATUS_DATA_PERUBAHAN]+" IS NULL ";
                }else{}

                listObj = PstAgunan.list(0,0,PstAgunan.fieldNames[PstAgunan.FLD_PERIODE_ID]+"='"+periode+"'"
                        + ""+whereClause, ""+PstAgunan.fieldNames[PstAgunan.FLD_KODE_REGISTER_AGUNAN]+" ASC");
            break;
                
            case SEGMEN_BANK_GARANSI :
                
                if(cabang.length() > 0){
                    whereClause +=" AND "+PstBankGaransi.fieldNames[PstBankGaransi.FLD_KODE_KANTOR_CABANG]+"='"+cabang+"' ";
                }
                if(kelengkapan==1){
                    whereClause +=" AND "+PstBankGaransi.fieldNames[PstBankGaransi.FLD_STATUS_PERUBAHAN_DATA]+"='0' ";
                }else if(kelengkapan ==2 ){
                    whereClause +=" AND "+PstBankGaransi.fieldNames[PstBankGaransi.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL ";
                }else{}
                listObj = PstBankGaransi.list(0,0, PstBankGaransi.fieldNames[PstBankGaransi.FLD_PERIODE_ID]+"='"+periode+"'"
                        + ""+whereClause,""+PstBankGaransi.fieldNames[PstBankGaransi.FLD_NO_REKENING]+" ASC");
            break;
                
            case SEGMEN_DEBITUR_BADAN_USAHA :
                if(cabang.length() > 0){
                   // whereClause +=" AND dup."+PstKredit.fieldNames[PstKredit.FLD_KODE_KANTOR_CABANG]+"='"+cabang+"' ";
                }
                if(kelengkapan==1){
                    whereClause +=" AND "+PstKredit.fieldNames[PstKredit.FLD_STATUS_PERUBAHAN_DATA]+"='0' ";
                }else if(kelengkapan ==2 ){
                    whereClause +=" AND "+PstKredit.fieldNames[PstKredit.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL ";
                }else{}
                listObj = PstDebiturBdnUsaha.listJoinReport(0,0,"dslik_debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_PERIODE_ID]+"='"+periode+"' "
                        + "AND dslik_debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_KODE_JENIS_NSB]+"!='1'"
                        + ""+whereClause, "dslik_debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_CIF]+" ASC",periode,cabang);
            break;
                
            case SEGMEN_FASILITAS_LAINNYA :
                if(cabang.length() > 0){
                    whereClause +=" AND "+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_KODE_KANTOR_CABANG]+"='"+cabang+"' ";
                }
                if(kelengkapan==1){
                    whereClause +=" AND "+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_STATUS_PERUBAHAN_DATA]+"='0' ";
                }else if(kelengkapan ==2 ){
                    whereClause +=" AND "+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL ";
                }else{}
                listObj = PstFasilitasLain.list(0,0,""+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_PERIODE_ID]+"='"+periode+"'"
                        + ""+whereClause, PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_NO_REKENING]+" ASC");
            break;
                
            case SEGMEN_IRREVOCABLE_LC :
                if(cabang.length() > 0){
                    whereClause +=" AND "+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_KODE_KANTOR_CABANG]+"='"+cabang+"' ";
                }
                if(kelengkapan==1){
                    whereClause +=" AND "+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_STATUS_PERUBAHAN_DATA]+"='0' ";
                }else if(kelengkapan ==2 ){
                    whereClause +=" AND "+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL ";
                }else{}
                listObj = PstIrrevocableLc.list(0,0,""+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_PERIODE_ID]+"='"+periode+"'"
                        + ""+whereClause, PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_NO_REKENING]+" ASC");
            break;
                
            case SEGMEN_KREDIT :
                if(cabang.length() > 0){
                    whereClause +=" AND "+PstKredit.fieldNames[PstKredit.FLD_KODE_KANTOR_CABANG]+"='"+cabang+"' ";
                }
                if(kelengkapan==1){
                    whereClause +=" AND "+PstKredit.fieldNames[PstKredit.FLD_STATUS_PERUBAHAN_DATA]+"='0' ";
                }else if(kelengkapan ==2 ){
                    whereClause +=" AND "+PstKredit.fieldNames[PstKredit.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL ";
                }else{}
                listObj = PstKredit.list(0,0,""+PstKredit.fieldNames[PstKredit.FLD_PERIODE_ID]+"='"+periode+"'"
                        + ""+whereClause, PstKredit.fieldNames[PstKredit.FLD_NO_REKENING]+" ASC");
            break;
                
            case SEGMEN_KREDIT_JOIN :
                if(cabang.length() > 0){
                    whereClause +=" AND "+PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_KODE_KANTOR_CABANG]+"='"+cabang+"' ";
                }
                if(kelengkapan==1){
                    whereClause +=" AND "+PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_STATUS_PERUBAHAN_DATA]+"='0' ";
                }else if(kelengkapan ==2 ){
                    whereClause +=" AND "+PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL ";
                }else{}
                listObj = PstKreditJoinAccount.list(0,0,""+PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_PERIODE_ID]+"='"+periode+"'"
                        + ""+whereClause, PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_NO_REKENING]+" ASC");
            break;
                
            case SEGMEN_LAPORAN_KEUANGAN_DEBITUR :
                if(cabang.length() > 0){
                    whereClause +=" AND "+PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_KODE_KANTOR_CABANG]+"='"+cabang+"' ";
                }
                if(kelengkapan==1){
                    whereClause +=" AND "+PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_STATUS_PERUBAHAN_DATA]+"='0' ";
                }else if(kelengkapan ==2 ){
                    whereClause +=" AND "+PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL ";
                }else{}
                listObj = PstLaporanKeuanganDebitur.list(0,0,""+PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_PERIODE_ID]+"='"+periode+"'"
                        + ""+whereClause, PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_CIF]+" ASC");
            break;
                
            case SEGMEN_PENGURUS_PEMILIK :
                if(cabang.length() > 0){
                    whereClause +=" AND "+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_KODE_KANTOR_CABANG]+"='"+cabang+"' ";
                }
                if(kelengkapan==1){
                    whereClause +=" AND "+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_STATUS_PERUBAHAN_DATA]+"='0' ";
                }else if(kelengkapan ==2 ){
                    whereClause +=" AND "+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL ";
                }else{}
                listObj = PstPengurusAtauPemilik.list(0,0,""+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_PERIODE_ID]+"='"+periode+"'"
                        + ""+whereClause, PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_NO_IDENTITAS]+" ASC");
            break;
                
            case SEGMEN_PENJAMIN : 
                if(cabang.length() > 0){
                    whereClause +=" AND "+PstPenjamin.fieldNames[PstPenjamin.FLD_KODE_KANTOR_CABANG]+"='"+cabang+"' ";
                }
                if(kelengkapan==1){
                    whereClause +=" AND "+PstPenjamin.fieldNames[PstPenjamin.FLD_STATUS_PERUBAHAN_DATA]+"='0' ";
                }else if(kelengkapan ==2 ){
                    whereClause +=" AND "+PstPenjamin.fieldNames[PstPenjamin.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL ";
                }else{}
                listObj = PstPenjamin.list(0,0,""+PstPenjamin.fieldNames[PstPenjamin.FLD_PERIODE_ID]+"='"+periode+"'"
                        + ""+whereClause, PstPenjamin.fieldNames[PstPenjamin.FLD_NO_ID_PENJAMIN]+" ASC");
            break;
                
            case SEGMEN_SURAT_BERHARGA :
                if(cabang.length() > 0){
                    whereClause +=" AND "+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_KODE_KANTOR_CABANG]+"='"+cabang+"' ";
                }
                if(kelengkapan==1){
                    whereClause +=" AND "+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_STATUS_PERUBAHAN_DATA]+"='0' ";
                }else if(kelengkapan ==2 ){
                    whereClause +=" AND "+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL ";
                }else{}
                listObj = PstSuratBerharga.list(0,0,""+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_PERIODE_ID]+"='"+periode+"'"
                        + ""+whereClause, PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_NO_REKENING]+" ASC");
            break;
            default:
                if(cabang.length() > 0){
                    //whereClause +=" AND dup."+PstKredit.fieldNames[PstKredit.FLD_KODE_KANTOR_CABANG]+"='"+cabang+"' ";
                }
                if(kelengkapan==1){
                    whereClause +=" AND "+PstKredit.fieldNames[PstKredit.FLD_STATUS_PERUBAHAN_DATA]+"='0' ";
                }else if(kelengkapan ==2 ){
                    whereClause +=" AND "+PstKredit.fieldNames[PstKredit.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL ";
                }else{}
                listObj = PstDebiturIndividu.listJoinReport(0,0,"dslik_debitur."+PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_PERIODE_ID]+"='"+periode+"' "
                        + "AND dslik_debitur."+PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_KODE_JENIS_NSB]+"='1'"
                        + ""+whereClause, "dslik_debitur."+PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_CIF]+" ASC",periode,cabang);
            break;
        }
        
        drawReportPerSegment( outObj, 0, listObj, segment);
        return"";
    }


    public void drawReportPerSegment(JspWriter outObj, int iCommand, Vector objectClass, int segment) {

        ControlList ctrlist = new ControlList();

        ctrlist.setAreaWidth("100%");
        ctrlist.setListStyle("listgen");
        ctrlist.setTitleStyle("tableheader");
        ctrlist.setCellStyle("cellStyle");
        ctrlist.setHeaderStyle("tableheader");
        
        ctrlist.addHeader("NO", "200px");//1
        ctrlist.addHeader("Cabang", "200px");//2
        ctrlist = AjaxPelaporan.draControlList(ctrlist, segment);
        
        ctrlist.setLinkSufix("");
        Vector lstData = ctrlist.getData();
        Vector lstLinkData = ctrlist.getLinkData();
        ctrlist.reset();

        int index = -1;
        int count=0;
        Vector rowx = new Vector(1, 1);
        double total=0.0;
        
        lstData = AjaxPelaporan.drawRowx(objectClass,segment,lstData);

        ctrlist.drawBootstrapStrip(outObj);
    }


%>
<%@include file="../../main/javainit_slik.jsp" %>
<%    
    int iCommand = FRMQueryString.requestCommand(request);
    String startDate = FRMQueryString.requestString(request, "tanggalStart");
    String oDate = "";
    String startEnd = FRMQueryString.requestString(request, "tanggalEnd");
    int start = FRMQueryString.requestInt(request, "start");
    int prevCommand = FRMQueryString.requestInt(request, "prev_command");
    String cabang = FRMQueryString.requestString(request, "cabangpersegment");
    long periodeId = FRMQueryString.requestLong(request, "periodepersegment");
    int getSegment = FRMQueryString.requestInt(request, "segment");
    int kelengkapan = FRMQueryString.requestInt(request, "kelengkapan");
    int summary = FRMQueryString.requestInt(request, "summary");
    
    Vector cabangKey = new Vector(1,1);
    Vector cabangVal = new Vector(1,1);
    String whereCabang="";
    if(!kd_bank.equals("")){
        whereCabang=PstCabangBank.fieldNames[PstCabangBank.FLD_KODE_CABANG]+"='"+kd_bank+"'";
    }
    Vector listCabang = PstCabangBank.list(0, 0, ""+whereCabang, ""+PstCabangBank.fieldNames[PstCabangBank.FLD_KODE_CABANG]+" ASC");
    if(listCabang.size() > 0){
        for(int i = 0; i < listCabang.size(); i++){
            CabangBank cabangBank = (CabangBank) listCabang.get(i);
            cabangKey.add(""+cabangBank.getKodeCabang());
            cabangVal.add("("+cabangBank.getKodeCabang()+") "+cabangBank.getNamaCabang());
        }
    }
    
    Vector periodeKey = new Vector(1,1);
    Vector periodeVal = new Vector(1,1);
    Vector listPeriode = PstPeriode.list(0, 0, "", ""+PstPeriode.fieldNames[PstPeriode.FLD_TGL_AKHIR]+" DESC");
    if(listPeriode.size() > 0){
        for(int i = 0; i < listPeriode.size(); i++){
            Periode entPeriode = (Periode) listPeriode.get(i);
            periodeKey.add(""+entPeriode.getOID());
            periodeVal.add(""+entPeriode.getNama());
        }
    }
    
    Vector segmentKey = new Vector(1,1);
    Vector segmentVal = new Vector(1,1);
    for(int i = 0; i < AjaxPelaporan.segmenNames.length; i++){
        segmentKey.add(""+i);
        segmentVal.add(""+AjaxPelaporan.segmenNames[i]);
    }
    
    Vector lengkapKey = new Vector(1,1);
    Vector lengkapVal = new Vector(1,1);
    lengkapKey.add("1");
    lengkapVal.add("Lengkap");
    lengkapKey.add("2");
    lengkapVal.add("Tidak Lengkap");
    

    Vector errorKey = new Vector(1,1);
    Vector errorVal = new Vector(1,1);
    errorKey.add("0");
    errorVal.add("Detail");
    errorKey.add("1");
    errorVal.add("Summary");
%>

<html>
    <head>
        <meta charset="UTF-8">
        <title>SLIK | Laporan Per Segment Detail</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <%@include file="../../template-component/css-component.jsp" %>
        <script language="JavaScript">

            function cmdStart() {
                document.driver.command.value = "<%=Command.START%>";
                document.driver.hidden_driver_id.value = "0";
                document.driver.action = "pbb.jsp";
                document.driver.submit();
            }

            function cmdStop() {

                document.driver.command.value = "<%=Command.STOP%>";
                document.driver.start.value = "0";
                document.driver.action = "pbb.jsp";
                document.driver.submit();

            }

            function lockScreen(str)
            {
                var lock = document.getElementById('theLockPane');
                if (lock)
                    lock.className = 'LockOn';

                lock.innerHTML = str;
            }
            
            function cmdSearch(){
                document.driver.command.value="<%=Command.SEARCH%>";
                document.driver.prev_command.value="<%=prevCommand%>";
                document.driver.action="pelaporan_per_segment_detail.jsp";
                document.driver.submit();
            }
            
            function cmdSearchPrint(){
                document.driver.command.value="<%=Command.PRINT%>";
                document.driver.prev_command.value="<%=prevCommand%>";
                document.driver.action="pelaporan_per_segment_detail.jsp";
                document.driver.submit();
            }
            
            function cmdSearchExcel(){
                document.driver.command.value="<%=Command.SEARCH%>";
                document.driver.prev_command.value="<%=prevCommand%>";
                document.driver.action="pelaporan_per_segment_detail_excel.jsp?cabangpersegment=<%=cabang%>&periodepersegment=<%=periodeId%>&segment=<%=getSegment%>&kelengkapan=<%=kelengkapan%>";
                document.driver.submit();
            }
            
        </script>
    </head>
    <body class="<%= skin%>">
        <input type="hidden" name="command" id="command" value="<%= Command.NONE%>">
        <input type="hidden" name="approot" id="approot" value="<%= approot%>">
        <div class="wrapper">

            <%@include file="../../template-component/header-component.jsp" %>
            <%@include file="../../template-component/sidebar-component.jsp" %>

            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Laporan 
                        <small>Data Per Segment Detail</small>
                    </h1>
                </section>
                <!-- Main content -->
               <section class="content">
                    <form name="driver"  method ="post" action="" role="form">
                        <input type="hidden" name="command" value="<%=iCommand%>">
                        <input type="hidden" name="start" value="<%=start%>">
                        <input type="hidden" name="prev_command" value="<%=prevCommand%>">
                        <input type="hidden" name="menu" value="16">
                        <input type="hidden" name="tree" value="5">
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="box">
                                    <div class="box-body">
                                        <div class="row">
                                            <div class="col-xs-2"><label>Summary</label>
                                                <%=ControlCombo.drawBootsratap("summary", null, ""+summary, errorKey, errorVal, "", "form-control") %>
                                            </div>
                                            <div class="col-xs-3"><label>Cabang</label>
                                                <%=ControlCombo.drawBootsratap("cabangpersegment", "Semua Cabang", ""+cabang, cabangKey, cabangVal, "", "form-control") %>
                                            </div>
                                            <div class="col-xs-2"><label>Periode</label>
                                                <%= ControlCombo.draw("periodepersegment", null, ""+periodeId, periodeKey, periodeVal, "", "form-control") %>
                                            </div>
                                            <div class="col-xs-2"><label>Segment</label>  
                                                    <%= ControlCombo.draw("segment", "Semua Segment", ""+getSegment, segmentKey, segmentVal, "", "form-control") %>
                                            </div>
                                            <div class="col-xs-3"><label>Kelengkapan</label>  
                                                <div class="input-group"> 
                                                    <%= ControlCombo.draw("kelengkapan", "Semua", ""+kelengkapan, lengkapKey, lengkapVal, "", "form-control") %>
                                                 <span class="input-group-btn">
                                                     <button type='button' name='seach' id='search-btn-segment' class="btn btn-success pull-right" onclick="javascript:cmdSearch()" ><i class="fa fa-search"></i></button>
                                                 </span>
                                                 <span class="input-group-btn">
                                                     <button type='button' name='seach' id='search-btn-segment' class="btn btn-danger pull-right" onclick="javascript:cmdSearchPrint()" ><i class="fa fa-print"></i></button>
                                                 </span>
                                                 </div>
                                            </div>     
                                        </div>
                                        <div class="row">
                                            <div class="col-md-12">
                                                <h3 class="box-title">Detail per Segment</h3>
                                            </div>
                                        </div>
                                        <%if(iCommand==Command.SEARCH){%>
                                            <div class="row">
                                                <div class="col-md-12" id="reportsummarypersegment">
                                                    <div class='row'>
                                                        <div class='col-md-12'>
                                                            <div class='form-group'>
                                                                <%if(summary==0){%>
                                                                    <%if(getSegment==-1){
                                                                        for(int i = 0; i < AjaxPelaporan.segmenNames.length; i++){%>
                                                                            <b><%=segmenNames[i]%></b><br>
                                                                            <%=getPerSegmentReport(out, periodeId, cabang, i,kelengkapan)%>
                                                                        <%}
                                                                        %>
                                                                    <%}else{%>
                                                                        <b><%=segmenNames[getSegment]%></b><br>
                                                                        <%=getPerSegmentReport(out, periodeId, cabang, getSegment,kelengkapan)%>
                                                                    <%}%>
                                                                <%}else{%>
                                                                    <%if(getSegment==-1){
                                                                        for(int i = 0; i < AjaxPelaporan.segmenNames.length; i++){%>
                                                                            <b><%=segmenNames[i]%></b><br>
                                                                            <%=getPerSegmentReport(out, periodeId, cabang, i,kelengkapan)%>
                                                                        <%}
                                                                        %>
                                                                    <%}else{%>
                                                                        <b><%=segmenNames[getSegment]%></b><br>
                                                                        <%=getPerSegmentReport(out, periodeId, cabang, getSegment,kelengkapan)%>
                                                                    <%}%>
                                                                    <%
                                                                    for(int i = 0; i < AjaxPelaporan.segmenEror.length; i++){
                                                                    }    
                                                                    %>
                                                                <%}%>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class='row'>
                                                        <div class='col-md-12'>
                                                            <div class='form-group'>
                                                                &nbsp;
                                                            </div>
                                                        </div>
                                                    </div>  
                                                </div>
                                            </div>
                                        <%}%>
                                    </div>
                                    <div class="row" style="margin-top:10px;">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <button class="btn btn-primary" onclick="javascript:cmdSearchExcel()"><i class="fa fa-save"></i> Export Excel</button>
                                                <button class="btn btn-danger btnback hidden" type="button"><i class="fa fa-save"></i> Export Pdf</button>
                                              </div>
                                        </div>
                                    </div>        
                                </div><!-- /.box -->
                            </div>
                        </div>         
                    </form>
                </section><!-- /.content -->
            </div><!-- /.content-wrapper -->
            <!-- Add the sidebar's background. This div must be placed
                 immediately after the control sidebar -->
            <div class='control-sidebar-bg'></div>
            <%@include file="../../template-component/footer-component.jsp" %>
            <%@include file="../../template-component/plugins-component.jsp" %>
        </div><!-- ./wrapper -->
        <%
        if(iCommand==Command.PRINT){
            %>
            <script language="JavaScript">
            cmdSearchExcel();
            </script>
        <%
        }
        %>
        
    </body>
</html>
