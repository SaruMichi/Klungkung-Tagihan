<%-- 
    Document   : rekapsummaryabankgaransi_lbu
    Created on : Jan 20, 2017, 2:41:18 PM
    Author     : dimata005
--%>
<%@page import="com.dimata.dslik.entity.contentdata.ContentDataJenisKredit"%>
<%@page import="com.dimata.dslik.entity.contentdata.PstContentDataJenisKredit"%>
<%@page import="com.dimata.dslik.entity.bankgaransi.BankGaransi"%>
<%@page import="com.dimata.dslik.entity.contentdata.ContentDataJenisGaransi"%>
<%@page import="com.dimata.dslik.entity.contentdata.PstContentDataJenisGaransi"%>
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
<%@include file="../../main/javainit_slik.jsp" %>
<%@include file="../../main/checkuser_slik.jsp" %>
<%
    boolean privView = userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_PROSES_DELIMITED_TEXT, AppObjInfo.G2_BULANAN, AppObjInfo.OBJ_BULANAN, AppObjInfo.COMMAND_VIEW);
%>
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


%>
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
    String statusreport = FRMQueryString.requestString(request, "statusreport");
    String cabangparent = FRMQueryString.requestString(request, "parentcabangpersegment");
    
    Vector cabangKey = new Vector(1,1);
    Vector cabangVal = new Vector(1,1);
    String where="";
   
    String whereCabang="";
    if(kd_bank!=null){
        if(!kd_bank.equals("") && privView==false ){
             if(cabangparent.equals("")){
                whereCabang=whereCabang+""+PstCabangBank.fieldNames[PstCabangBank.FLD_KODE_CABANG]+"='"+kd_bank+"'";
            }else{
                whereCabang=whereCabang+""+PstCabangBank.fieldNames[PstCabangBank.FLD_KODE_CABANG]+"='"+kd_bank+"'";
            }
        }
    }
    
    if(iCommand==Command.LOAD || iCommand==Command.SEARCH){
        if(!cabangparent.equals("")){
                where=" PARENT_CODE='"+cabangparent+"'";
                if(whereCabang.length()>0){
                    whereCabang=whereCabang+" AND PARENT_CODE='"+cabangparent+"'";
                }else{
                    whereCabang=whereCabang+" PARENT_CODE='"+cabangparent+"'";
                }
                
        }
    }else{
        if(!cabangparent.equals("") && privView==false ){
                where=" PARENT_CODE='"+cabangparent+"'";
                if(whereCabang.length()>0){
                    whereCabang=whereCabang+" AND PARENT_CODE='"+cabangparent+"'";
                }else{
                    whereCabang=whereCabang+" PARENT_CODE='"+cabangparent+"'";
                }
        }
    }
    Vector listCabang = PstCabangBank.list(0, 0, ""+whereCabang, ""+PstCabangBank.fieldNames[PstCabangBank.FLD_KODE_CABANG]+" ASC");
    String parentCode="";
    
    if(privView) {
        cabangKey.add("");
        cabangVal.add("Semua Cabang/Capem");
    }
    
    if(listCabang.size() > 0){
        for(int i = 0; i < listCabang.size(); i++){
            CabangBank cabangBank = (CabangBank) listCabang.get(i);
            cabangKey.add(""+cabangBank.getKodeCabang());
            cabangVal.add("("+cabangBank.getKodeCabang()+") "+cabangBank.getNamaCabang());
             if(listCabang.size()==1){
                parentCode=cabangBank.getKodeParentCabang();
            }
        }
    }
    
    Vector parentcabangKey = new Vector(1,1);
    Vector parentcabangVal = new Vector(1,1);
     String whereParentCabang="";
     if(parentCode != null){
        if(!parentCode.equals("")){
            whereParentCabang="KODE_PARENT='"+parentCode+"'";
        }
     }
    if(privView) {
        parentcabangKey.add("");
        parentcabangVal.add("Semua Cabang");
    }
    Vector parentlistCabang = PstCabangBank.listParent(0, 0, ""+whereParentCabang, "KODE_PARENT ASC");
    if(listCabang.size() > 0){
        for(int i = 0; i < parentlistCabang.size(); i++){
            CabangBank cabangBank = (CabangBank) parentlistCabang.get(i);
            parentcabangKey.add(""+cabangBank.getKodeCabang());
            parentcabangVal.add(""+cabangBank.getKodeCabang()+" - "+cabangBank.getNamaBank());
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
        <title>SLIK | Laporan Rekap Summary Bank Garansi Versi LBU</title>
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
                document.driver.action="rekapsummaryabankgaransi_lbu.jsp";
                document.driver.submit();
                lockScreen('Proses Reporting Sedang Berlangsung, Silahkan menunggu...');
            }
            
            function cmdSearchExcel(){
                document.driver.command.value="<%=Command.SEARCH%>";
                document.driver.action="<%=approot%>/dslik/pelaporan/rekapsummarybankgaransi_lbu_excel.jsp";
                document.driver.submit();
            }
            
            function cmdloadparent(){
                document.driver.command.value="<%=Command.LOAD%>";
                document.driver.action="rekapsummaryabankgaransi_lbu.jsp";
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
                        <small>Rekap Summary Bank Garansi Versi LBU</small>
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
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label>Cabang</label>
                                                    <%=ControlCombo.drawBootsratap("parentcabangpersegment",null, ""+cabangparent, parentcabangKey, parentcabangVal, "onchange=\"javascript:cmdloadparent()\"", "form-control") %>
                                            
                                                </div>
                                            </div> 
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label>Cabang/Capem</label>
                                                    <%=ControlCombo.drawBootsratap("cabangpersegment",null, ""+cabang, cabangKey, cabangVal, "", "form-control") %>
                                            
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label>Periode</label>
                                                    <div class="input-group">
                                                        <%= ControlCombo.draw("periodepersegment", null, ""+periodeId, periodeKey, periodeVal, "", "form-control") %>
                                                        <span class="input-group-btn">
                                                            <button type='button' name='seach' id='search-btn-segment' class="btn btn-success pull-right" onclick="javascript:cmdSearch()" ><i class="fa fa-search"></i></button>
                                                        </span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-12">
                                                <h3 class="box-title">Laporan Rekap Summary Bank Garansi Versi LBU</h3>
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
                                                                    if(cabangparent.length()>0){
                                                                        whereClauseCabang = " PARENT_CODE='"+cabangparent+"'";
                                                                        if(cabang.length() > 0){
                                                                            whereClauseCabang = whereClauseCabang+" AND "+PstCabangBank.fieldNames[PstCabangBank.FLD_KODE_CABANG]+"='"+cabang+"'";
                                                                        }
                                                                    }else{
                                                                        if(cabang.length() > 0){
                                                                            whereClauseCabang = PstCabangBank.fieldNames[PstCabangBank.FLD_KODE_CABANG]+"='"+cabang+"'";
                                                                        }
                                                                    }
                                                                    Vector listCabangReport = PstCabangBank.list(0, 0, whereClauseCabang, PstCabangBank.fieldNames[PstCabangBank.FLD_KODE_CABANG]+" ASC");
                                                                %>
                                                                <table class='table table-bordered'>
                                                                    <thead>
                                                                        <tr>
                                                                            <td align='left' colspan='2'>
                                                                                PT. BANK PEMBANGUNAN DAERAH BALI<br>
                                                                                JL. RAYA PUPUTAN - NITI MANDALA, RENON, DENPASAR
                                                                            </td>
                                                                            <td></td>
                                                                            <td align='right' colspan='2'>
                                                                                Rpt-ID : <%= userName %><br>
                                                                                Tgl-Rpt : <%= Formater.formatDate(new Date(), "dd/MM/yyyy") %>
                                                                            </td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td colspan='5' align='center'>
                                                                                REKAP SUMMARY BANK GARANSI VERSI LBU<br>
                                                                                PER : <%= periodeName %><br>
                                                                            </td>
                                                                        </tr>
                                                                    <%
                                                                        int sumtotalAgunan=0;
                                                                        int sumtotalRekening=0;
                                                                        double sumtotalbakidebet=0;
                                                                        double sumtotalNilaiAgunan=0;
                                                                        if(listCabangReport.size() > 0){
                                                                            for(int i = 0; i < listCabangReport.size(); i++){
                                                                                CabangBank cabangBank = (CabangBank) listCabangReport.get(i);
                                                                    %>
                                                                        <tr>
                                                                            <td align='left' colspan='5'>
                                                                                <b><%= cabangBank.getNamaCabang().toUpperCase()+"("+cabangBank.getKodeCabang()+")" %></b>
                                                                            </td>
                                                                        </tr>
                                                                        <tr>
                                                                            <th>NO</th>
                                                                            <th>KODE</th>
                                                                            <th>NAMA</th>
                                                                            <th>TOTAL BANK GARANSI</th>
                                                                            <th>NOMINAL BANK GARANSI</th>
                                                                        </tr>
                                                                        <tr>
                                                                    </thead>
                                                                    <%
                                                                    int nourut=0;
                                                                    int totalAgunan=0;
                                                                    int totalRekening=0;
                                                                    double totalbakidebet=0;
                                                                    double totalNilaiAgunan=0;
                                                                    %>
                                                                            <tbody>
                                                                                <%
                                                                                Vector listKredit = PstBankGaransi.listSummaryBankGaransiLBU(cabangBank.getKodeCabang(), Formater.formatDate(periode.getTglAwal(),"yyyy-MM-dd"), Formater.formatDate(periode.getTglAkhir(),"yyyy-MM-dd"), periodeId);
                                                                                if(listKredit.size() > 0){
                                                                                    for(int iA = 0; iA < listKredit.size(); iA++){
                                                                                        BankGaransi bankGaransi = new BankGaransi();
                                                                                        try{
                                                                                            bankGaransi = (BankGaransi) listKredit.get(iA);
                                                                                        }catch(Exception ex){
                                                                                            bankGaransi = new BankGaransi();
                                                                                        }
                                                                                         nourut=nourut+1;
                                                                                         totalbakidebet=totalbakidebet+bankGaransi.getTotalBankGaransi();
                                                                                         totalNilaiAgunan=totalNilaiAgunan+bankGaransi.getTotNominalBankGaransi();
                                                                                        
                                                                                         sumtotalbakidebet=sumtotalbakidebet+bankGaransi.getTotalBankGaransi();
                                                                                         sumtotalNilaiAgunan=sumtotalNilaiAgunan+bankGaransi.getTotNominalBankGaransi();
                                                                                        %>
                                                                                            <tr>
                                                                                                <td><%= (nourut) %></td>
                                                                                                <td align="right"><%= bankGaransi.getKodeBankGransiReport() %></td>
                                                                                                <td align="right" ><%= bankGaransi.getNamaBankGaransiReport() %></td>
                                                                                                <td align="right"><%=bankGaransi.getTotalBankGaransi() %></td>
                                                                                                <td align="right"><%=Formater.formatNumber(bankGaransi.getTotNominalBankGaransi(), "#,###.##")  %></td>
                                                                                            </tr>
                                                                                        <%
                                                                                    }
                                                                                }
                                                                       // }
                                                                                %>
                                                                            </tbody>
                                                                    <%
                                                                    //}
                                                                    %>
                                                                    <tr>
                                                                        <td align="right"></td>
                                                                        <td align="right"></td>
                                                                        <td align="right">Total Per Cabang</td>
                                                                        <td align="right"><%= Formater.formatNumber(totalbakidebet, "#,###.##") %></td>
                                                                        <td align="right"><%= Formater.formatNumber(totalNilaiAgunan, "#,###.##") %></td>
                                                                    </tr>
                                                                    <%
                                                                }
                                                                %>
                                                                    <tr>
                                                                        <td align="right" ></td>
                                                                        <td align="right"></td>
                                                                        <td align="right">Total</td>
                                                                        <td align="right"><%= Formater.formatNumber(sumtotalbakidebet, "#,###.##") %></td>
                                                                        <td align="right"><%= Formater.formatNumber(sumtotalNilaiAgunan, "#,###.##") %></td>
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
                                                                <button class="btn btn-primary" type="button" onclick="javascript:cmdSearchExcel()"><i class="fa fa-save"></i> Export Excel</button>
                                                                <button class="btn btn-danger btnback hidden" type="button"><i class="fa fa-save"></i> Export Pdf</button>
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
            </div><!-- /.content-wrapper -->
            <!-- Add the sidebar's background. This div must be placed
                 immediately after the control sidebar -->
            <div class='control-sidebar-bg'></div>
            <%@include file="../../template-component/footer-component.jsp" %>
            <%@include file="../../template-component/plugins-component.jsp" %>
        </div><!-- ./wrapper -->
        
        <%@include file="export.jsp" %>
    </body>
</html>

