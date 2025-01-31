<%-- 
    Document   : pelaporan_per_segment
    Created on : Oct 2, 2016, 6:41:31 PM
    Author     : dimata005
--%>
<%@page import="com.dimata.dslik.entity.agunan.Agunan"%>
<%@page import="com.dimata.dslik.entity.kredit.Kredit"%>
<%@page import="java.util.Date"%>
<%@page import="com.dimata.util.Formater"%>
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
<%@include file="../../main/javainit_slik.jsp" %>
<%@include file="../../main/checkuser_slik.jsp" %>
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


%>
<%    
    boolean privView = userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_MODUL_LAPORAN, AppObjInfo.G2_NOMINATIF_AGUNAN, AppObjInfo.OBJ_NOMINATIF_AGUNAN, AppObjInfo.COMMAND_VIEW);
    boolean privPrint = userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_MODUL_LAPORAN, AppObjInfo.G2_NOMINATIF_AGUNAN, AppObjInfo.OBJ_NOMINATIF_AGUNAN, AppObjInfo.COMMAND_PRINT);
    boolean privViewCheck = userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_PROSES_DELIMITED_TEXT, AppObjInfo.G2_BULANAN, AppObjInfo.OBJ_BULANAN, AppObjInfo.COMMAND_VIEW);
    
    
    int iCommand = FRMQueryString.requestCommand(request);
    String startDate = FRMQueryString.requestString(request, "tanggalStart");
    String oDate = "";
    String startEnd = FRMQueryString.requestString(request, "tanggalEnd");
    int start = FRMQueryString.requestInt(request, "start");
    int prevCommand = FRMQueryString.requestInt(request, "prev_command");
    String cabang = FRMQueryString.requestString(request, "cabangpersegment");
    long periodeId = FRMQueryString.requestLong(request, "periodepersegment");
    int getSegment = FRMQueryString.requestInt(request, "segment");
    String statusreport = FRMQueryString.requestString(request, "statusreport");
    
    Vector cabangKey = new Vector(1,1);
    Vector cabangVal = new Vector(1,1);
    if(privViewCheck) {
        cabangKey.add("");
        cabangVal.add("Semua Cabang/Capem");
    }
    String whereCabang="";
     if(kd_bank!=null){
        if(!kd_bank.equals("") && privViewCheck==false){
            whereCabang=PstCabangBank.fieldNames[PstCabangBank.FLD_KODE_CABANG]+"='"+kd_bank+"'";
        }
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
    
    Vector statusKey = new Vector(1,1);
    Vector statusVal = new Vector(1,1);
    statusKey.add("0");
    statusVal.add("Semua");
    for(int i = 0; i < PstAgunan.statusKeys.length; i++){
        statusKey.add(""+PstAgunan.statusKeys[i]);
        statusVal.add(""+PstAgunan.statusValues[i]);
    }
    
    String periodeName = "";
    Periode entPeriode = new Periode();
    if(periodeId != 0){
        try{
            entPeriode = PstPeriode.fetchExc(periodeId);
            periodeName = entPeriode.getNama();
        }catch(Exception ex){
            
        }
    }
%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>SLIK | Laporan Nominatif Agunan</title>
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
                document.driver.action="nominatifagunan.jsp";
                document.driver.submit();
                lockScreen('Proses Reporting Sedang Berlangsung, Silahkan menunggu...');
            }
            
            function cmdSearchExcel(){
                document.driver.command.value="<%=Command.SEARCH%>";
                document.driver.action="<%=approot%>/dslik/export/exportnominatifagunanexcel.jsp";
                document.driver.submit();
            }
            
        </script>
    </head>
    <body class="<%= skin%>">
        <div id="theLockPane" class="LockOff"></div>
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
                        <small>Nominatif Agunan</small>
                    </h1>
                </section>
                <!-- Main content -->
                <%
                    if(privView){
                %>
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
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label>Cabang/Capem</label>
                                                    <%=ControlCombo.drawBootsratap("cabangpersegment", null, ""+cabang, cabangKey, cabangVal, "", "form-control") %>
                                            
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label>Periode</label>
                                                    <%= ControlCombo.draw("periodepersegment", null, ""+periodeId, periodeKey, periodeVal, "", "form-control") %>
                                                    
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label>Status</label>
                                                    <div class="input-group">
                                                        <%= ControlCombo.draw("statusreport", null, ""+statusreport, statusKey, statusVal, "", "form-control") %>
                                                        <span class="input-group-btn">
                                                            <button type='button' name='seach' id='search-btn-segment' class="btn btn-success pull-right" onclick="javascript:cmdSearch()" ><i class="fa fa-search"></i></button>
                                                        </span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-12">
                                                <h3 class="box-title">Laporan Nominatif Agunan</h3>
                                            </div>
                                        </div>
                                        <%if(iCommand==Command.SEARCH){%>
                                            <div class="row">
                                                <div class="col-md-12" id="reportsummarypersegment">
                                                    <div class='row'>
                                                        <div class='col-md-12'>
                                                            <div class='form-group' id="exportelement">
                                                                <%
                                                                    String whereClauseCabang = "";
                                                                    if(cabang.length() > 0){
                                                                        whereClauseCabang = PstCabangBank.fieldNames[PstCabangBank.FLD_KODE_CABANG]+"='"+cabang+"'";
                                                                    }
                                                                    Vector listCabangReport = PstCabangBank.list(0, 0, whereClauseCabang, PstCabangBank.fieldNames[PstCabangBank.FLD_KODE_CABANG]+" ASC");
                                                                %>
                                                                <table class='table table-bordered'>
                                                                    <thead>
                                                                        <tr>
                                                                            <td align='left' colspan='5'>
                                                                                PT. BANK PEMBANGUNAN DAERAH BALI<br>
                                                                                JL. RAYA PUPUTAN - NITI MANDALA, RENON, DENPASAR
                                                                            </td>
                                                                            <td></td>
                                                                            <td align='right' colspan='5'>
                                                                                Rpt-ID : <%= userName %><br>
                                                                                Tgl-Rpt : <%= Formater.formatDate(new Date(), "dd/MM/yyyy") %>
                                                                            </td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td colspan='11' align='center'>
                                                                                DAFTAR NOMINATIF AGUNAN KREDIT<br>
                                                                                PER : <%= periodeName %>
                                                                            </td>
                                                                        </tr>
                                                                    <%

                                                                        if(listCabangReport.size() > 0){
                                                                            double totallTaksasi=0;
                                                                            double totallbaki=0;
                                                                            for(int i = 0; i < listCabangReport.size(); i++){
                                                                                CabangBank cabangBank = (CabangBank) listCabangReport.get(i);
                                                                    %>
                                                                        <tr>
                                                                            <td align='left' colspan='5'>
                                                                                <%= cabangBank.getNamaCabang().toUpperCase()+"("+cabangBank.getKodeCabang()+")" %>
                                                                            </td>
                                                                            <td></td>
                                                                            <td align='right' colspan='5'>
                                                                            </td>
                                                                        </tr>
                                                                        <tr>
                                                                            <th>NO</th>
                                                                            <th>NO REKENING</th>
                                                                            <th colspan='2'>NAMA SINGKAT</th>
                                                                            <th>PLAFOND</th>
                                                                            <th colspan='6'>BAKI DEBET</th>
                                                                        </tr>
                                                                        <tr>
                                                                            <th></th>
                                                                            <th>NO URUT AGN</th>
                                                                            <th>ID AGUNAN</th>
                                                                            <th>JNS AGN</th>
                                                                            <th>ATAS NAMA JAMINAN</th>
                                                                            <th>JNS IKT</th>
                                                                            <th>NO BUKTI</th>
                                                                            <th>COL ID</th>
                                                                            <th>TGL RETAKSASI</th>
                                                                            <th>NILAI RETAKSASI</th>
                                                                            <th>NILAI TAKSASI</th>
                                                                        </tr>
                                                                    </thead>
                                                                    <%
                                                                    int noUrut=0;
                                                                    String whereClauseKredit = "kredit."+PstKredit.fieldNames[PstKredit.FLD_KODE_KANTOR_CABANG]+"='"+cabangBank.getKodeCabang()+"' "
                                                                            + "AND kredit."+PstKredit.fieldNames[PstKredit.FLD_PERIODE_ID]+"='"+periodeId+"'";
                                                                    if(!statusreport.equals("0")){
                                                                        whereClauseKredit +=" AND kredit."+PstKredit.fieldNames[PstKredit.FLD_KODE_KONDISI]+"='"+statusreport+"'";
                                                                    }
                                                                    Vector listKredit = PstKredit.listJoinReport(0, 0, whereClauseKredit, ""+PstKredit.fieldNames[PstKredit.FLD_NO_REKENING]+" ASC");
                                                                    
                                                                    if(listKredit.size() > 0){
                                                                        int no = 1;
                                                                        totallbaki=0;
                                                                        totallTaksasi=0;
                                                                        for(int iK = 0; iK < listKredit.size(); iK++){
                                                                            Kredit kredit = (Kredit) listKredit.get(iK);
                                                                                
                                                                                Vector listAgunan = PstAgunan.list(0, 0, PstAgunan.fieldNames[PstAgunan.FLD_NO_REKENING]+"='"+kredit.getNoRekening()+"' "
                                                                                        + "AND "+PstAgunan.fieldNames[PstAgunan.FLD_KODE_KANTOR_CABANG]+"='"+cabangBank.getKodeCabang()+"' "
                                                                                        + "AND "+PstAgunan.fieldNames[PstAgunan.FLD_PERIODE_ID]+"='"+periodeId+"'", PstAgunan.fieldNames[PstAgunan.FLD_KODE_REGISTER_AGUNAN]+" ASC");
                                                                                if(listAgunan.size() > 0){
                                                                                   
                                                                            %>
                                                                                <tbody>
                                                                                    <tr>
                                                                                        <td><%= no %></td>
                                                                                        <td><%= PstKredit.formatCredit("###.##.##.#####-#", kredit.getNoRekening()) %></td>
                                                                                        <td colspan='2'><%= kredit.getNamaSingkat() %></td>
                                                                                        <td><%= Formater.formatNumber(kredit.getPlafon(),"#,###")%></td>
                                                                                        <td colspan='6'><%= Formater.formatNumber(kredit.getBakiDebet(),"#,###")%></td>
                                                                                    </tr>
                                                                                    <%
                                                                                         totallbaki=totallbaki+kredit.getBakiDebet();
                                                                                         
                                                                                    if(listAgunan.size() > 0){
                                                                                        for(int iA = 0; iA < listAgunan.size(); iA++){
                                                                                            Agunan agunan = (Agunan) listAgunan.get(iA);
                                                                                            noUrut=noUrut+1;
                                                                                            totallTaksasi=totallTaksasi+agunan.getNilaiAgunanLjk();
                                                                                            %>
                                                                                                <tr>
                                                                                                    <td></td>
                                                                                                    <td align="right"><%=noUrut%></td>
                                                                                                    <td><%= agunan.getKodeRegisterAgunan() %></td>
                                                                                                    <td><%= agunan.getKodeJenisAgunan() %></td>
                                                                                                    <td><%= agunan.getNamaPemilikAgunan() %></td>
                                                                                                    <td><%= agunan.getKodeJenisPengikatan() %></td>
                                                                                                    <td><%= agunan.getBuktiKepemilikan() %></td>
                                                                                                    <td></td>
                                                                                                    <td></td>
                                                                                                    <td></td>
                                                                                                    <td><%= Formater.formatNumber(agunan.getNilaiAgunanLjk(),"#,###")%></td>
                                                                                                </tr>
                                                                                            <%
                                                                                        }
                                                                                        no+=1;
                                                                                    }
                                                                                }
                                                                        }
                                                                                    %>
                                                                                </tbody>
                                                                    <%
                                                                    }
                                                                }%>
                                                                <tr>
                                                                    <td></td>
                                                                    <td></td>
                                                                    <td colspan='2'>Total Baki Debet</td>
                                                                    <td></td>
                                                                    <td colspan='6'><%= Formater.formatNumber(totallbaki,"#,###")%></td>
                                                                </tr>
                                                                <tr>
                                                                    <td></td>
                                                                    <td></td>
                                                                    <td colspan='2'>Total Taksasi</td>
                                                                    <td></td>
                                                                    <td colspan='6'><%= Formater.formatNumber(totallTaksasi,"#,###")%></td>
                                                                </tr>
                                                                <%
                                                            }
                                                            %>
                                                                </table>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class='row'>
                                                        <div class='col-md-12'>
                                                            <div class="form-group">
                                                                <%
                                                                        if(privPrint){
                                                                            %>
                                                                                <button type="button" class="btn btn-primary" onclick="javascript:cmdSearchExcel()"><i class="fa fa-save"></i> Export Excel</button>
                                                                                <button class="btn btn-danger btnback hidden" type="button"><i class="fa fa-save"></i> Export Pdf</button>
                                                                            <%
                                                                        }
                                                                %>
                                                            </div>
                                                        </div>
                                                    </div>  
                                                </div>
                                            </div>
                                        <%}%>
                                    </div>        
                                </div><!-- /.box -->
                            </div>
                        </div>         
                    </form>
                </section><!-- /.content -->
                <%
                    }
                %>
            </div><!-- /.content-wrapper -->
            <div class='control-sidebar-bg'></div>
            <%@include file="../../template-component/footer-component.jsp" %>
            <%@include file="../../template-component/plugins-component.jsp" %>
        </div><!-- ./wrapper -->
        
    </body>
</html>
