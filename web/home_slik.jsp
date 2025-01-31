<%-- 
    Document   : home_marketing
    Created on : May 5, 2016, 11:23:02 AM
    Author     : dimata005
--%>
<%@page import="com.dimata.dslik.entity.debiturindividu.PstDebiturIndividu"%>
<%@page import="com.dimata.dslik.entity.admin.PstAppUser"%>
<%@page import="com.dimata.qdep.form.FRMQueryString"%>
<%@page import="com.dimata.gui.jsp.ControlCombo"%>
<%@page import="com.dimata.dslik.entity.masterdata.CabangBank"%>
<%@page import="com.dimata.dslik.entity.masterdata.PstCabangBank"%>
<%@page import="com.dimata.dslik.session.proses.SessionProsesPerlengkapan"%>
<%@page import="com.dimata.dslik.entity.kreditjoinaccount.PstKreditJoinAccount"%>
<%@page import="java.util.Date"%>
<%@page import="com.dimata.dslik.entity.pengurusataupemilik.PstPengurusAtauPemilik"%>
<%@page import="com.dimata.dslik.entity.laporankeuangandebitur.PstLaporanKeuanganDebitur"%>
<%@page import="com.dimata.dslik.entity.fasilitaslain.PstFasilitasLain"%>
<%@page import="com.dimata.dslik.entity.bankgaransi.PstBankGaransi"%>
<%@page import="com.dimata.dslik.entity.irrevocablelc.PstIrrevocableLc"%>
<%@page import="com.dimata.dslik.entity.suratberharga.PstSuratBerharga"%>
<%@page import="com.dimata.dslik.entity.kredit.PstKredit"%>
<%@page import="com.dimata.dslik.entity.penjamin.PstPenjamin"%>
<%@page import="com.dimata.dslik.entity.agunan.PstAgunan"%>
<%@page import="com.dimata.dslik.entity.debitur.PstDebitur"%>
<%@page import="com.dimata.util.Formater"%>
<%@page import="java.util.Vector"%>
<%@page import="com.dimata.dslik.entity.masterdata.PstPeriode"%>
<%@page import="com.dimata.dslik.entity.masterdata.Periode"%>
<%@page import="com.dimata.util.Command"%>
<!DOCTYPE html>
<%@include file="main/javainit_slik.jsp" %>
<%@include file="main/checkuser_slik.jsp" %>
<%
    boolean privView = userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_HOME, AppObjInfo.G2_HOME, AppObjInfo.OBJ_HOME, AppObjInfo.COMMAND_VIEW);
    boolean privDelete = userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_HOME, AppObjInfo.G2_HOME, AppObjInfo.OBJ_HOME, AppObjInfo.COMMAND_DELETE);
    boolean privUpdate = userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_HOME, AppObjInfo.G2_HOME, AppObjInfo.OBJ_HOME, AppObjInfo.COMMAND_UPDATE);
    boolean privViewMaster = userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_PROFILE_BANK, AppObjInfo.OBJ_BANK, AppObjInfo.COMMAND_VIEW);
%>
<%
String cabang = FRMQueryString.requestString(request, "cabangpersegment");
int iCommand = FRMQueryString.requestCommand(request);
if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_ADMIN, AppObjInfo.G2_ADMIN_USER, AppObjInfo.OBJ_ADMIN_USER_USER)){
    if(iCommand==Command.UPDATE){
        if(userId!=0){
            long xxx = PstAppUser.updateCabangBank(cabang, userId);
            String remoteIP = request.getRemoteAddr();
            SessUserSession userSess = new SessUserSession(remoteIP );
            int dologinx=userSess.reDoLogin(userId);
            session.putValue(SessUserSession.HTTP_SESSION_NAME, userSess);
            kd_bank=cabang;
        }
    }
}
%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>SLIK | HOME</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <%@include file="template-component/css-component.jsp" %>
    </head>
    <body class="<%=skin%>">
        <div class="wrapper">
            <input type="hidden" name="usergroup" id="usergroup" value="<%= getUserGroup %>">
            <input type="hidden" name="kodecabang" id="kodecabang" value="<%= kd_bank %>">
            <input type="hidden" name="periode" id="periode" value="<%= periode.getOID() %>">
            <input type="hidden" name="privupdate" id="privupdate" value="<%= privUpdate %>">
            <input type="hidden" name="privview" id="privview" value="<%= privView %>">
            <%@include file="template-component/header-component.jsp" %>
            <%@include file="template-component/sidebar-component.jsp" %>
            <!-- Content Wrapper. Contains page content -->
            <script language="JavaScript">
                 function cmdChangeCabang(){
                    document.driver.command.value="<%=Command.UPDATE%>";
                    document.driver.action="home_slik.jsp";
                    document.driver.submit();
                }    
            </script>
            <%
                if(privView){
            %>
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Dashboard
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-home"></i> Dashboard</a></li>
                    </ol>
                </section>

                <!-- Main content -->
                <section class="content">
                    <div class="row">
                        <div class="col-md-12">
                            <div class='box box-primary'>
                                <div class="box-header">
                                    <h3 class="box-title">
                                       Periode yang aktif : <%= (periode.getTglAwal() == null ? "" : Formater.formatDate(periode.getTglAwal(), "MMMM yyyy"))%>
                                    </h3>
                                </div>
                                <div class="box-body">
                                    <%--
                                    <div class="row">
                                        <div class="col-md-12">
                                            <b>Data Kredit Yang Tidak Punya Debitur :  <span id="summaryotherkredit" class="summary" data-target="AjaxKredit" data-for="loadsummaryotherkredit" data-selector="#summaryotherkredit"><i class="fa fa-spin fa-refresh"></i></span></b> <a href="#" class="deletelist" data-target="AjaxKredit" data-for="showotherform" data-command="<%= Command.NONE %>"><i class="fa fa-eye"></i> View</a><br>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <b>Data Debitur Yang Tidak Punya Kredit :  <span id="summaryotherdebitur" class="summary" data-target="AjaxDebiturIndividu" data-for="loadsummaryotherdebitur" data-selector="#summaryotherdebitur"><i class="fa fa-spin fa-refresh"></i></span></b> <a href="#" class="deletelist" data-target="AjaxDebiturIndividu" data-for="showotherform" data-command="<%= Command.NONE %>"><i class="fa fa-eye"></i> View</a><br>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <b>Data Bank Garansi Yang Tidak Punya Debitur :  <span id="summaryotherbankgaransi" class="summary" data-target="AjaxBankGaransi" data-for="loadsummaryotherbankgaransi" data-selector="#summaryotherbankgaransi"><i class="fa fa-spin fa-refresh"></i></span></b> <a href="#" class="deletelist" data-target="AjaxBankGaransi" data-for="showotherform" data-command="<%= Command.NONE %>"><i class="fa fa-eye"></i> View</a><br>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <b>Data Agunan Yang Tidak Punya Debitur :  <span id="summaryotheragunan" class="summary" data-target="AjaxAgunan" data-for="loadsummaryotheragunan" data-selector="#summaryotheragunan"><i class="fa fa-spin fa-refresh"></i></span></b> <a href="#" class="deletelist" data-target="AjaxAgunan" data-for="showotherform" data-command="<%= Command.NONE %>"><i class="fa fa-eye"></i> View</a><br>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <b>Data Pengurus/Pemilik Yang Tidak Punya Debitur :  <span id="summaryotherpenguruspemilik" class="summary" data-target="AjaxPengurusPemilik" data-for="loadsummaryotherpenguruspemilik" data-selector="#summaryotherpenguruspemilik"><i class="fa fa-spin fa-refresh"></i></span></b> <a href="#" class="deletelist" data-target="AjaxPengurusPemilik" data-for="showotherform" data-command="<%= Command.NONE %>"><i class="fa fa-eye"></i> View</a> <br>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <b>Data Debitur Individu Yang Mempunyai NIK Yang Sama :  <span id="individuniksama" class="summary" data-target="AjaxDebiturIndividu" data-for="loadniksama" data-selector="#individuniksama"><i class="fa fa-spin fa-refresh"></i></span></b> <a href="#" class="deletelist" data-target="AjaxDebiturIndividu" data-for="showniksamaform" data-command="<%= Command.NONE %>"><i class="fa fa-eye"></i> View</a> <br>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <b>Data Debitur Badan Usaha Yang Mempunyai No Identitas Yang Sama : <span id="badanusahaniksama" class="summary" data-target="AjaxDebiturBadanUsaha" data-for="loadniksama" data-selector="#badanusahaniksama"><i class="fa fa-spin fa-refresh"></i></span></b> <a href="#" class="deletelist" data-target="AjaxDebiturBadanUsaha" data-for="showniksamaform" data-command="<%= Command.NONE %>"><i class="fa fa-eye"></i> View</a> <br>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <hr>
                                        </div>
                                    </div> 
                                    --%>
                                    <%if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_ADMIN, AppObjInfo.G2_ADMIN_USER, AppObjInfo.OBJ_ADMIN_USER_USER)){
                                    %>
                                    <form name="driver"  method ="post" action="" role="form">
                                        <input type="hidden" name="command" value="<%=iCommand%>">
                                        <div class="row">
                                                <div class="col-md-2">
                                                    <b>Cek Data Cabang/Capem</b>
                                                </div>
                                                <div class="col-md-4">
                                                    <%
                                                        Vector cabangKey = new Vector(1,1);
                                                        Vector cabangVal = new Vector(1,1);
                                                        Vector listCabang = PstCabangBank.list(0, 0, "", ""+PstCabangBank.fieldNames[PstCabangBank.FLD_KODE_CABANG]+" ASC");
                                                        if(listCabang.size() > 0){
                                                            for(int i = 0; i < listCabang.size(); i++){
                                                                CabangBank cabangBank = (CabangBank) listCabang.get(i);
                                                                cabangKey.add(""+cabangBank.getKodeCabang());
                                                                cabangVal.add("("+cabangBank.getKodeCabang()+") "+cabangBank.getNamaCabang());
                                                            }
                                                        }
                                                    %>
                                                    <%=ControlCombo.drawBootsratap("cabangpersegment", "Semua Cabang", ""+kd_bank, cabangKey, cabangVal, "onchange=\"javascript:cmdChangeCabang()\"", "form-control") %>
                                                </div>
                                                <div class="col-md-6">
                                                </div>
                                            </div>
                                        </form>     
                                    <%}%>     
                                    <div class="row">
                                        <div class="col-md-12">
                                            <hr>
                                        </div>
                                    </div>    
                                    <div class="row">
                                        <div class="col-md-12">
                                            <b>Konfirmasi Data Segment Lengkap </b><br>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            &nbsp;
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-2 col-xs-6">
                                            <!-- small box -->
                                            <div class="small-box bg-aqua">
                                                <div class="inner">
                                                    <h3 id="AjaxDebiturIndividu" class="summary" data-target="AjaxDebiturIndividu" data-for="loadsummary" data-selector="#AjaxDebiturIndividu">
                                                        <i class="fa fa-spin fa-refresh"></i>
                                                    </h3>
                                                    <p>
                                                        Debitur Individu
                                                    </p>
                                                </div>
                                                <div class="icon">
                                                    <i class="ion ion-bag"></i>
                                                </div>
                                                <a href="javascript:" class="small-box-footer showlist" data-command="<%= Command.NONE %>" data-for="showlistindividu" data-status="0" data-target="AjaxDebiturIndividu">More info <i class="fa fa-arrow-circle-right"></i>
                                                </a>
                                            </div>
                                        </div><!-- ./col -->
                                        <div class="col-lg-2 col-xs-6">
                                            <!-- small box -->
                                            <div class="small-box bg-green">
                                                <div class="inner">
                                                    <h3 id="AjaxDebiturBadanUsaha" class="summary" data-target="AjaxDebiturBadanUsaha" data-for="loadsummary" data-selector="#AjaxDebiturBadanUsaha">
                                                        <i class="fa fa-spin fa-refresh"></i>
                                                    </h3>
                                                    <p>
                                                        Badan Usaha
                                                    </p>
                                                </div>
                                                <div class="icon">
                                                    <i class="ion ion-stats-bars"></i>
                                                </div>
                                                <a href="javascript:" class="small-box-footer showlist" data-command="<%= Command.NONE %>" data-for="showlistbadanusaha" data-status="0" data-target="AjaxDebiturBadanUsaha">
                                                    More info <i class="fa fa-arrow-circle-right"></i>
                                                </a>
                                            </div>
                                        </div><!-- ./col -->
                                        <div class="col-lg-2 col-xs-6">
                                            <!-- small box -->
                                            <div class="small-box bg-yellow">
                                                <div class="inner">
                                                    <h3 id="AjaxAgunan" class="summary" data-target="AjaxAgunan" data-for="loadsummary" data-selector="#AjaxAgunan">
                                                        <i class="fa fa-spin fa-refresh"></i>
                                                    </h3>
                                                    <p>
                                                        Agunan
                                                    </p>
                                                </div>
                                                <div class="icon">
                                                    <i class="ion ion-person-add"></i>
                                                </div>
                                                <a href="javascript:" class="small-box-footer showlist" data-command="<%= Command.NONE %>" data-for="showlistagunan" data-status="0" data-target="AjaxAgunan">
                                                    More info <i class="fa fa-arrow-circle-right"></i>
                                                </a>
                                            </div>
                                        </div><!-- ./col -->
                                        <div class="col-lg-2 col-xs-6">
                                            <!-- small box -->
                                            <div class="small-box bg-red">
                                                <div class="inner">
                                                    <h3 id="AjaxPenjamin" class="summary" data-target="AjaxPenjamin" data-for="loadsummary" data-selector="#AjaxPenjamin">
                                                        <i class="fa fa-spin fa-refresh"></i>
                                                    </h3>
                                                    <p>
                                                        Penjamin
                                                    </p>
                                                </div>
                                                <div class="icon">
                                                    <i class="ion ion-pie-graph"></i>
                                                </div>
                                                <a href="javascript:" class="small-box-footer showlist" data-command="<%= Command.NONE %>" data-for="showlistpenjamin" data-status="0" data-target="AjaxPenjamin">
                                                    More info <i class="fa fa-arrow-circle-right"></i>
                                                </a>
                                            </div>
                                        </div><!-- ./col -->
                                        <div class="col-lg-2 col-xs-6">
                                            <!-- small box -->
                                            <div class="small-box bg-blue">
                                                <div class="inner">
                                                    <h3 id="AjaxKredit" class="summary" data-target="AjaxKredit" data-for="loadsummary" data-selector="#AjaxKredit">
                                                        <i class="fa fa-spin fa-refresh"></i>
                                                    </h3>
                                                    <p>
                                                        Kredit
                                                    </p>
                                                </div>
                                                <div class="icon">
                                                    <i class="ion ion-ios7-cart-outline"></i>
                                                </div>
                                                <a href="javascript:" class="small-box-footer showlist" data-command="<%= Command.NONE %>" data-for="showlistkredit" data-status="0" data-target="AjaxKredit">
                                                    More info <i class="fa fa-arrow-circle-right"></i>
                                                </a>
                                            </div>
                                        </div><!-- ./col -->
                                        <div class="col-lg-2 col-xs-6">
                                            <!-- small box -->
                                            <div class="small-box bg-purple">
                                                <div class="inner">
                                                    <h3 id="AjaxSuratBerharga" class="summary" data-target="AjaxSuratBerharga" data-for="loadsummary" data-selector="#AjaxSuratBerharga">
                                                        <i class="fa fa-spin fa-refresh"></i>
                                                    </h3>
                                                    <p>
                                                        Surat Berharga
                                                    </p>
                                                </div>
                                                <div class="icon">
                                                    <i class="ion ion-ios7-briefcase-outline"></i>
                                                </div>
                                                <a href="javascript:" class="small-box-footer showlist" data-command="<%= Command.NONE %>" data-for="showlistsuratberharga" data-status="0" data-target="AjaxSuratBerharga">
                                                    More info <i class="fa fa-arrow-circle-right"></i>
                                                </a>
                                            </div>
                                        </div><!-- ./col -->
                                    </div><!-- /.row -->

                                    <div class="row">
                                        <div class="col-lg-2 col-xs-6">
                                            <!-- small box -->
                                            <div class="small-box bg-blue">
                                                <div class="inner">
                                                    <h3 id="AjaxKreditJoin" class="summary" data-target="AjaxKreditJoin" data-for="loadsummary" data-selector="#AjaxKreditJoin">
                                                        <i class="fa fa-spin fa-refresh"></i>
                                                    </h3>
                                                    <p>
                                                        Kredit Join
                                                    </p>
                                                </div>
                                                <div class="icon">
                                                    <i class="ion ion-ios7-cart-outline"></i>
                                                </div>
                                                <a href="javascript:" class="small-box-footer showlist" data-command="<%= Command.NONE %>" data-for="showlistkreditjoin" data-status="0" data-target="AjaxKreditJoin">
                                                    More info <i class="fa fa-arrow-circle-right"></i>
                                                </a>
                                            </div>
                                        </div><!-- ./col -->
                                        <div class="col-lg-2 col-xs-6">
                                            <!-- small box -->
                                            <div class="small-box bg-teal">
                                                <div class="inner">
                                                    <h3 id="AjaxIrrevocable" class="summary" data-target="AjaxIrrevocable" data-for="loadsummary" data-selector="#AjaxIrrevocable">
                                                        <i class="fa fa-spin fa-refresh"></i>
                                                    </h3>
                                                    <p>
                                                        Irrevocable L/C
                                                    </p>
                                                </div>
                                                <div class="icon">
                                                    <i class="ion ion-ios7-alarm-outline"></i>
                                                </div>
                                                <a href="javascript:" class="small-box-footer showlist" data-command="<%= Command.NONE %>" data-for="showlistirrevocable" data-status="0" data-target="AjaxIrrevocable">
                                                    More info <i class="fa fa-arrow-circle-right"></i>
                                                </a>
                                            </div>
                                        </div><!-- ./col -->
                                        <div class="col-lg-2 col-xs-6">
                                            <!-- small box -->
                                            <div class="small-box bg-maroon">
                                                <div class="inner">
                                                    <h3 id="AjaxBankGaransi" class="summary" data-target="AjaxBankGaransi" data-for="loadsummary" data-selector="#AjaxBankGaransi">
                                                        <i class="fa fa-spin fa-refresh"></i>
                                                    </h3>
                                                    <p>
                                                        Bank Garansi
                                                    </p>
                                                </div>
                                                <div class="icon">
                                                    <i class="ion ion-ios7-pricetag-outline"></i>
                                                </div>
                                                <a href="javascript:" class="small-box-footer showlist" data-command="<%= Command.NONE %>" data-for="showlistbankgaransi" data-status="0" data-target="AjaxBankGaransi">
                                                    More info <i class="fa fa-arrow-circle-right"></i>
                                                </a>
                                            </div>
                                        </div><!-- ./col -->
                                        <div class="col-lg-2 col-xs-6">
                                            <!-- small box -->
                                            <div class="small-box bg-olive">
                                                <div class="inner">
                                                    <h3 id="AjaxFasilitasLainnya" class="summary" data-target="AjaxFasilitasLainnya" data-for="loadsummary" data-selector="#AjaxFasilitasLainnya">
                                                        <i class="fa fa-spin fa-refresh"></i>
                                                    </h3>
                                                    <p>
                                                        Fasilitas Lainnya
                                                    </p>
                                                </div>
                                                <div class="icon">
                                                    <i class="ion ion-ios7-cart-outline"></i>
                                                </div>
                                                <a href="javascript:" class="small-box-footer showlist" data-command="<%= Command.NONE %>" data-for="showlistfasilitaslain" data-status="0" data-target="AjaxFasilitasLainnya">
                                                    More info <i class="fa fa-arrow-circle-right"></i>
                                                </a>
                                            </div>
                                        </div><!-- ./col -->
                                        
                                        <div class="col-lg-2 col-xs-6">
                                            <!-- small box -->
                                            <div class="small-box bg-navy">
                                                <div class="inner">
                                                    <h3 id="AjaxLaporanKeuanganDebitur" class="summary" data-target="AjaxLaporanKeuanganDebitur" data-for="loadsummary" data-selector="#AjaxLaporanKeuanganDebitur">
                                                        <i class="fa fa-spin fa-refresh"></i>
                                                    </h3>
                                                    <p>
                                                        Lap. Keuangan
                                                    </p>
                                                </div>
                                                <div class="icon">
                                                    <i class="ion ion-ios7-alarm-outline"></i>
                                                </div>
                                                <a href="javascript:" class="small-box-footer showlist" data-command="<%= Command.NONE %>" data-for="showlistlaporankeuangandebitur" data-status="0" data-target="AjaxLaporanKeuanganDebitur">
                                                    More info <i class="fa fa-arrow-circle-right"></i>
                                                </a>
                                            </div>
                                        </div><!-- ./col -->
                                        <div class="col-lg-2 col-xs-6">
                                            <!-- small box -->
                                            <div class="small-box bg-fuchsia">
                                                <div class="inner">
                                                    <h3 id="AjaxPengurusPemilik" class="summary" data-target="AjaxPengurusPemilik" data-for="loadsummary" data-selector="#AjaxPengurusPemilik">
                                                        <i class="fa fa-spin fa-refresh"></i>
                                                    </h3>
                                                    <p>
                                                            Pengurus/Pemilik
                                                    </p>
                                                </div>
                                                <div class="icon">
                                                    <i class="ion ion-ios7-pricetag-outline"></i>
                                                </div>
                                                <a href="javascript:" class="small-box-footer showlist" data-command="<%= Command.NONE %>" data-for="showlistpenguruspemilik" data-status="0" data-target="AjaxPengurusPemilik">
                                                    More info <i class="fa fa-arrow-circle-right"></i>
                                                </a>
                                            </div>
                                        </div><!-- ./col -->
                                    </div><!-- /.row -->
                                </div>
                            </div>
                        </div><!-- ./col -->
                    </div><!-- /.row -->
                    <!-- Small boxes (Stat box) -->
                    <%-- finish final --%>
                    <div class="row">
                        <div class="col-md-12">
                            <div class='box box-primary'>
                                <div class="box-body">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <b>Daftar Data Segment Yang Tidak Lengkap</b><br>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            &nbsp;
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-2 col-xs-6">
                                            <!-- small box -->
                                            <div class="small-box bg-aqua">
                                                <div class="inner">
                                                    <h3 id="AjaxDebiturIndividuNull" class="summary" data-target="AjaxDebiturIndividu" data-for="loadsummarynull" data-selector="#AjaxDebiturIndividuNull">
                                                        <i class="fa fa-spin fa-refresh"></i>
                                                    </h3>
                                                    <p>
                                                        Debitur Individu
                                                    </p>
                                                </div>
                                                <div class="icon">
                                                    <i class="ion ion-bag"></i>
                                                </div>
                                                <a href="javascript:" class="small-box-footer showlist" data-command="<%= Command.NONE %>" data-for="showlistindividu" data-status="null" data-target="AjaxDebiturIndividu">More info <i class="fa fa-arrow-circle-right"></i>
                                                </a>
                                            </div>
                                        </div><!-- ./col -->
                                        <div class="col-lg-2 col-xs-6">
                                            <!-- small box -->
                                            <div class="small-box bg-green">
                                                <div class="inner">
                                                    <h3 id="AjaxDebiturBadanUsahaNull" class="summary" data-target="AjaxDebiturBadanUsaha" data-for="loadsummarynull" data-selector="#AjaxDebiturBadanUsahaNull">
                                                        <i class="fa fa-spin fa-refresh"></i>
                                                    </h3>
                                                    <p>
                                                        Badan Usaha
                                                    </p>
                                                </div>
                                                <div class="icon">
                                                    <i class="ion ion-stats-bars"></i>
                                                </div>
                                                <a href="javascript:" class="small-box-footer showlist" data-command="<%= Command.NONE %>" data-for="showlistbadanusaha" data-status="null" data-target="AjaxDebiturBadanUsaha">
                                                    More info <i class="fa fa-arrow-circle-right"></i>
                                                </a>
                                            </div>
                                        </div><!-- ./col -->
                                        <div class="col-lg-2 col-xs-6">
                                            <!-- small box -->
                                            <div class="small-box bg-green">
                                                <div class="inner">
                                                    <h3 id="AjaxTanpaPengurus" class="summary" data-target="AjaxDebiturBadanUsaha" data-for="loadtanpapengurus" data-selector="#AjaxTanpaPengurus">
                                                        <i class="fa fa-spin fa-refresh"></i>
                                                    </h3>
                                                    <p>
                                                        Badan Usaha Tanpa Pengurus
                                                    </p>
                                                </div>
                                                <div class="icon">
                                                    <i class="ion ion-stats-bars"></i>
                                                </div>
                                                <a href="javascript:" class="small-box-footer showlist" data-command="<%= Command.NONE %>" data-for="showlisttanpapengurus" data-status="null" data-target="AjaxDebiturBadanUsaha">
                                                    More info <i class="fa fa-arrow-circle-right"></i>
                                                </a>
                                            </div>
                                        </div><!-- ./col -->
                                        <div class="col-lg-2 col-xs-6">
                                            <!-- small box -->
                                            <div class="small-box bg-yellow">
                                                <div class="inner">
                                                    <h3 id="AjaxAgunanNull" class="summary" data-target="AjaxAgunan" data-for="loadsummarynull" data-selector="#AjaxAgunanNull">
                                                        <i class="fa fa-spin fa-refresh"></i>
                                                    </h3>
                                                    <p>
                                                        Agunan
                                                    </p>
                                                </div>
                                                <div class="icon">
                                                    <i class="ion ion-person-add"></i>
                                                </div>
                                                <a href="javascript:" class="small-box-footer showlist" data-command="<%= Command.NONE %>" data-for="showlistagunan" data-status="null" data-target="AjaxAgunan">
                                                    More info <i class="fa fa-arrow-circle-right"></i>
                                                </a>
                                            </div>
                                        </div><!-- ./col -->
                                        <div class="col-lg-2 col-xs-6">
                                            <!-- small box -->
                                            <div class="small-box bg-red">
                                                <div class="inner">
                                                    <h3 id="AjaxPenjaminNull" class="summary" data-target="AjaxPenjamin" data-for="loadsummarynull" data-selector="#AjaxPenjaminNull">
                                                        <i class="fa fa-spin fa-refresh"></i>
                                                    </h3>
                                                    <p>
                                                        Penjamin
                                                    </p>
                                                </div>
                                                <div class="icon">
                                                    <i class="ion ion-pie-graph"></i>
                                                </div>
                                                <a href="javascript:" class="small-box-footer showlist" data-command="<%= Command.NONE %>" data-for="showlistpenjamin" data-status="null" data-target="AjaxPenjamin">
                                                    More info <i class="fa fa-arrow-circle-right"></i>
                                                </a>
                                            </div>
                                        </div><!-- ./col -->
                                        <div class="col-lg-2 col-xs-6">
                                            <!-- small box -->
                                            <div class="small-box bg-blue">
                                                <div class="inner">
                                                    <h3 id="AjaxKreditNull" class="summary" data-target="AjaxKredit" data-for="loadsummarynull" data-selector="#AjaxKreditNull">
                                                        <i class="fa fa-spin fa-refresh"></i>
                                                    </h3>
                                                    <p>
                                                        Kredit
                                                    </p>
                                                </div>
                                                <div class="icon">
                                                    <i class="ion ion-ios7-cart-outline"></i>
                                                </div>
                                                <a href="javascript:" class="small-box-footer showlist" data-command="<%= Command.NONE %>" data-for="showlistkredit" data-status="null" data-target="AjaxKredit">
                                                    More info <i class="fa fa-arrow-circle-right"></i>
                                                </a>
                                            </div>
                                        </div><!-- ./col -->
                                    </div><!-- /.row -->

                                    <div class="row">
                                        <div class="col-lg-2 col-xs-6">
                                            <!-- small box -->
                                            <div class="small-box bg-purple">
                                                <div class="inner">
                                                    <h3 id="AjaxSuratBerhargaNull" class="summary" data-target="AjaxSuratBerharga" data-for="loadsummarynull" data-selector="#AjaxSuratBerhargaNull">
                                                        <i class="fa fa-spin fa-refresh"></i>
                                                    </h3>
                                                    <p>
                                                        Surat Berharga
                                                    </p>
                                                </div>
                                                <div class="icon">
                                                    <i class="ion ion-ios7-briefcase-outline"></i>
                                                </div>
                                                <a href="javascript:" class="small-box-footer showlist" data-command="<%= Command.NONE %>" data-for="showlistsuratberharga" data-status="null" data-target="AjaxSuratBerharga">
                                                    More info <i class="fa fa-arrow-circle-right"></i>
                                                </a>
                                            </div>
                                        </div><!-- ./col -->
                                        <div class="col-lg-2 col-xs-6">
                                            <!-- small box -->
                                            <div class="small-box bg-blue">
                                                <div class="inner">
                                                    <h3 id="AjaxKreditJoinNull" class="summary" data-target="AjaxKreditJoin" data-for="loadsummarynull" data-selector="#AjaxKreditJoinNull">
                                                        <i class="fa fa-spin fa-refresh"></i>
                                                    </h3>
                                                    <p>
                                                        Kredit Join
                                                    </p>
                                                </div>
                                                <div class="icon">
                                                    <i class="ion ion-ios7-cart-outline"></i>
                                                </div>
                                                <a href="javascript:" class="small-box-footer showlist" data-command="<%= Command.NONE %>" data-for="showlistkreditjoin" data-status="null" data-target="AjaxKreditJoin">
                                                    More info <i class="fa fa-arrow-circle-right"></i>
                                                </a>
                                            </div>
                                        </div><!-- ./col -->
                                        <div class="col-lg-2 col-xs-6">
                                            <!-- small box -->
                                            <div class="small-box bg-black">
                                                <div class="inner">
                                                    <h3 id="AjaxIrrevocableNull" class="summary" data-target="AjaxIrrevocable" data-for="loadsummarynull" data-selector="#AjaxIrrevocableNull">
                                                        <i class="fa fa-spin fa-refresh"></i>
                                                    </h3>
                                                    <p>
                                                        Irrevocable L/C
                                                    </p>
                                                </div>
                                                <div class="icon">
                                                    <i class="ion ion-ios7-alarm-outline"></i>
                                                </div>
                                                <a href="javascript:" class="small-box-footer showlist" data-command="<%= Command.NONE %>" data-for="showlistirrevocable" data-status="null" data-target="AjaxIrrevocable">
                                                    More info <i class="fa fa-arrow-circle-right"></i>
                                                </a>
                                            </div>
                                        </div><!-- ./col -->
                                        <div class="col-lg-2 col-xs-6">
                                            <!-- small box -->
                                            <div class="small-box bg-maroon">
                                                <div class="inner">
                                                    <h3 id="AjaxBankGaransiNull" class="summary" data-target="AjaxBankGaransi" data-for="loadsummarynull" data-selector="#AjaxBankGaransiNull">
                                                        <i class="fa fa-spin fa-refresh"></i>
                                                    </h3>
                                                    <p>
                                                        Bank Garansi
                                                    </p>
                                                </div>
                                                <div class="icon">
                                                    <i class="ion ion-ios7-pricetag-outline"></i>
                                                </div>
                                                <a href="javascript:" class="small-box-footer showlist" data-command="<%= Command.NONE %>" data-for="showlistbankgaransi" data-status="null" data-target="AjaxBankGaransi">
                                                    More info <i class="fa fa-arrow-circle-right"></i>
                                                </a>
                                            </div>
                                        </div><!-- ./col -->
                                        <div class="col-lg-2 col-xs-6">
                                            <!-- small box -->
                                            <div class="small-box bg-olive">
                                                <div class="inner">
                                                    <h3 id="AjaxFasilitasLainnyaNull" class="summary" data-target="AjaxFasilitasLainnya" data-for="loadsummarynull" data-selector="#AjaxFasilitasLainnyaNull">
                                                        <i class="fa fa-spin fa-refresh"></i>
                                                    </h3>
                                                    <p>
                                                        Fasilitas Lainnya
                                                    </p>
                                                </div>
                                                <div class="icon">
                                                    <i class="ion ion-ios7-cart-outline"></i>
                                                </div>
                                                <a href="javascript:" class="small-box-footer showlist" data-command="<%= Command.NONE %>" data-for="showlistfasilitaslain" data-status="null" data-target="AjaxFasilitasLainnya">
                                                    More info <i class="fa fa-arrow-circle-right"></i>
                                                </a>
                                            </div>
                                        </div><!-- ./col -->
                                        <div class="col-lg-2 col-xs-6">
                                            <!-- small box -->
                                            <div class="small-box bg-navy">
                                                <div class="inner">
                                                    <h3 id="AjaxLaporanKeuanganDebiturNull" class="summary" data-target="AjaxLaporanKeuanganDebitur" data-for="loadsummarynull" data-selector="#AjaxLaporanKeuanganDebiturNull">
                                                        <i class="fa fa-spin fa-refresh"></i>
                                                    </h3>
                                                    <p>
                                                        Lap. Keuangan 
                                                    </p>
                                                </div>
                                                <div class="icon">
                                                    <i class="ion ion-ios7-alarm-outline"></i>
                                                </div>
                                                <a href="javascript:" class="small-box-footer showlist" data-command="<%= Command.NONE %>" data-for="showlistlaporankeuangandebitur" data-status="null" data-target="AjaxLaporanKeuanganDebitur">
                                                    More info <i class="fa fa-arrow-circle-right"></i>
                                                </a>
                                            </div>
                                        </div><!-- ./col -->
                                        <div class="col-lg-2 col-xs-6">
                                            <!-- small box -->
                                            <div class="small-box bg-fuchsia">
                                                <div class="inner">
                                                    <h3 id="AjaxPengurusPemilikNull" class="summary" data-target="AjaxPengurusPemilik" data-for="loadsummarynull" data-selector="#AjaxPengurusPemilikNull">
                                                        <i class="fa fa-spin fa-refresh"></i>
                                                    </h3>
                                                    <p>
                                                            Pengurus/Pemilik
                                                    </p>
                                                </div>
                                                <div class="icon">
                                                    <i class="ion ion-ios7-pricetag-outline"></i>
                                                </div>
                                                <a href="javascript:" class="small-box-footer showlist" data-command="<%= Command.NONE %>" data-for="showlistpenguruspemilik" data-status="null" data-target="AjaxPengurusPemilik">
                                                    More info <i class="fa fa-arrow-circle-right"></i>
                                                </a>
                                            </div>
                                        </div><!-- ./col -->
                                    </div><!-- /.row -->
                                    
                                    <%--cek --%>
                                    <div class="row">
                                        
                                    </div><!-- /.row -->
                                    
                                </div>
                            </div>
                        </div><!-- ./col -->
                    </div><!-- /.row -->

                </section><!-- /.content -->
            </div><!-- /.content-wrapper -->
            <%
                }
            %>
            <div class='control-sidebar-bg'></div>
            
            
            <div id="showlistmodal" class="modal fade" tabindex="-1">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title"></h4>
                        </div>
                        <div class="modal-body ">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="box-body" id="showlistmodal-body">

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="othermodal" class="modal fade" tabindex="-1">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title"></h4>
                        </div>
                        <form id="clear">
                            <input type="hidden" name="FRM_FIELD_DATA_FOR" value="clearlist">
                            <input type="hidden" name="command" value="<%= Command.DELETEALL %>">
                            <input type="hidden" name="target" id="target">
                            <div class="modal-body ">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="box-body" id="otherform-body">
                                            
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <%
                                    if(privDelete){
                                        %>
                                            <button type="submit" class="btn btn-danger" id="savebtn"><i class="fa fa-trash"></i> Delete</button>
                                        <%
                                    }
                                %>

                                <button type="button" class="btn btn-danger" data-dismiss="modal"><i class="fa fa-close"></i> Close</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <%@include file="template-component/footer-component.jsp" %>

            <%@include file="template-component/plugins-component.jsp" %>
            <script type="text/javascript">
                $(document).ready(function () {
                    //SET ACTIVE MENU
                    var approot = "<%= approot %>";
                    var privupdate = $("#privupdate").val();
                    var privview = $("#privview").val();
                    var activeMenu = function (parentId, childId) {
                        $(parentId).addClass("active").find(".treeview-menu").slideDown();
                        $(childId).addClass("active");
                    }
                    
                    //MODAL SETTING
                    var modalSetting = function(elementId, backdrop, keyboard, show){
                        $(elementId).modal({
                            backdrop	: backdrop,
                            keyboard	: keyboard,
                            show	: show
                        });
                    };
                    
                    function checkItem(selector, selector2){
                        $(selector).click(function(){
                            if ($(this).is(":checked")) {
                                $(selector2).attr("checked",true);
                            } else {
                                $(selector2).removeAttr("checked");
                            }
                        });

                    }
                    function showForm(selector, modal){
                        $(selector).click(function(){
                            $(modal).modal("show");
                            var datafor = $(this).data("for");
                            var oid = $("#oid").val();
                            var command = $(this).data("command");
                            var target = $(this).data("target");
                            var modaltitle = "";
                            var status = $(this).data("status");
                            var datasend = {
                                "FRM_FIELD_OID" : oid,
                                "FRM_FIELD_DATA_FOR" : datafor,
                                "command" : command,
                                "privupdate" : privupdate,
                                "privview" : privview
                            };
                            var onDone = function(data){
                                dataTablesOptions("#showListElement", "tableShowListElement", target, datafor, null, status);
                                vieweditdata("#showListElement", ".edit");
                            };
                            var onSuccess = function(data){
                            };
                            
                            switch(datafor){
                                case "showlistindividu" :
                                    modaltitle = "Debitur Individu";
                                break;
                                
                                case "showlistbadanusaha" :
                                    modaltitle = "Debitur Badan Usaha";
                                break;
                                
                                case "showlistagunan" :
                                    modaltitle = "Agunan";
                                break;
                                
                                case "showlistpenjamin" :
                                    modaltitle = "Penjamin";
                                break;
                                
                                case "showlistkredit" :
                                    modaltitle = "Kredit";
                                break;
                                
                                case "showlistkreditjoin" :
                                    modaltitle = "Kredit Join";
                                break;
                                
                                case "showlistsuratberharga" :
                                    modaltitle = "Surat Berharga";
                                break;
                                
                                case "showlistirrevocable" :
                                    modaltitle = "Irrevocable LC";
                                break;
                                
                                case "showlistbankgaransi" :
                                    modaltitle = "Bank Garansi";
                                break;
                                
                                case "showlistfasilitaslain" :
                                    modaltitle = "Fasilitas Lainnya";
                                break;
                                
                                case "showlistlaporankeuangandebitur" :
                                    modaltitle = "Laporan Keuangan Debitur";
                                break;
                                
                                case "showlistpenguruspemilik" :
                                    modaltitle = "Pengurus/Pemilik";
                                break;
                                
                                case "showlisttanpapengurus" :
                                    modaltitle = "Debitur Badan Usaha";
                                break;
                            }
                            
                            if(status == 0){
                                modaltitle = "Daftar Perubahan Data "+modaltitle;
                            }else{
                                modaltitle = "Daftar Data Nasabah "+modaltitle+" Yang Tidak Lengkap"
                            }
                            
                            if(datafor == "showlisttanpapengurus"){
                                modaltitle = "Daftar Data Nasabah Debitu Badan Usaha Yang Tidak Mempunyai Pengurus";
                            }
                            $(modal).find(".modal-title").html(modaltitle);
                            getDataFunction(onDone, onSuccess, approot, command, datasend, target, "#showlistmodal-body", false);

                        });
                    };
                    
                    function showDeleteList(selector,modal){
                        $(selector).click(function(){
                            $(modal).modal("show");
                            var datafor = $(this).data("for");
                            var command = $(this).data("command");
                            var target = $(this).data("target");
                            var usergroup = $("#usergroup").val();
                            var kodecabang = $("#kodecabang").val();
                            var periode = $("#periode").val();
                            $("#target").val(target);
                            var modaltitle = "";
                            var datasend = {
                                "FRM_FIELD_DATA_FOR" : datafor,
                                "command" : command,
                                "usergroup" : usergroup,
                                "kodecabang" : kodecabang,
                                "periode" : periode,
                                "privupdate" : privupdate,
                                "FRM_FIELD_APPROOT" : "<%= approot %>"
                            };
                            var onDone = function(data){
                                checkItem(".checkall", ".deletelist");
                            };
                            var onSuccess = function(data){
                            };
                            
                            switch(target){
                                case "AjaxDebiturIndividu" :
                                    modaltitle = "Data Debitur Yang Tidak Punya Kredit";
                                    if(datafor == "showniksamaform"){
                                        modaltitle = "Data Debitur Individu Yang Mempunyai NIK Yang Sama";
                                    }
                                break;
                                case "AjaxDebiturBadanUsaha" :
                                    modaltitle = "Data Debitur Badan Usaha Yang Mempunyai No Identitas Yang Sama";
                                break;
                                case "AjaxKredit" :
                                    modaltitle = " Data Kredit Yang Tidak Punya Debitur ";
                                break;
                                
                                case "AjaxBankGaransi" :
                                    modaltitle = "Data Bank Garansi Yang Tidak Punya Debitur";
                                break;
                                
                                case "AjaxAgunan" :
                                    modaltitle = " Data Agunan Yang Tidak Punya Debitur";
                                break;
                                
                                case "AjaxPengurusPemilik" :
                                    modaltitle = "Data Pengurus/Pemilik Yang Tidak Punya Debitur ";
                                break;
                                
                                
                            }
                            $(modal).find(".modal-title").html(modaltitle);
                            getDataFunction(onDone, onSuccess, approot, command, datasend, target, "#otherform-body", false);

                        });
                    };
                    
                    var getDataFunction = function(onDone, onSuccess, approot, command, dataSend, servletName, dataAppendTo, notification){
                        /*
                         * getDataFor	: # FOR PROCCESS FILTER
                         * onDone	: # ON DONE FUNCTION,
                         * onSuccess	: # ON ON SUCCESS FUNCTION,
                         * approot	: # APPLICATION ROOT,
                         * dataSend	: # DATA TO SEND TO THE SERVLET,
                         * servletName  : # SERVLET'S NAME
                         */
                        $(this).getData({
                           onDone	: function(data){
                               onDone(data);
                           },
                           onSuccess	: function(data){
                                onSuccess(data);
                           },
                           approot	: approot,
                           dataSend	: dataSend,
                           servletName	: servletName,
                           dataAppendTo	: dataAppendTo,
                           notification : notification
                        });
                    };
                    
                    function dataTablesOptions(elementIdParent, elementId, servletName, dataFor, callBackDataTables, status){
                        var kodeCabang = $("#kodecabang").val();
                        var userGroup = $("#usergroup").val();
                        var privUpdate = $("#privupdate").val();
                        var privView = $("#privview").val();
                        var approot = "<%= approot %>";
                        $(elementIdParent).find('table').addClass('table-bordered table-striped table-hover').attr({'id':elementId});
                        $("#"+elementId).dataTable({"bDestroy": true,
                            "iDisplayLength": 10,
                            "bProcessing" : true,
                            "oLanguage" : {
                                "sProcessing" : "<div class='progress progress-striped active'><div class='progress-bar progress-bar-primary' style='width: 100%'><b>Please Wait...</b></div></div>"
                            },
                            "bServerSide" : true,
                            "sAjaxSource" : "<%= approot %>/"+servletName+"?command=<%= Command.LIST%>&FRM_FIELD_DATA_FOR="+dataFor+"&FRM_FIELD_STATUS_DATA="+status+"&usergroup="+userGroup+"&kodecabang="+kodeCabang+"&privupdate="+privUpdate+"&privview="+privView+"&FRM_FIELD_APPROOT="+approot,
                            aoColumnDefs: [
                                {
                                   bSortable: false,
                                   aTargets: [ -1 ]
                                }
                              ],
                            "initComplete": function(settings, json) {
                                if(callBackDataTables != null){
                                    callBackDataTables();
                                }
                            },
                            "fnDrawCallback": function( oSettings ) {
                                if(callBackDataTables != null){
                                    callBackDataTables();
                                }
                            },
                            "fnPageChange" : function(oSettings){

                            }
                        });

                        $(elementIdParent).find("#"+elementId+"_filter").find("input").addClass("form-control");
                        $(elementIdParent).find("#"+elementId+"_length").find("select").addClass("form-control");
                        $("#"+elementId).css("width","100%");
                    };
                    
                    function vieweditdata(tableElementId, elementId){
                        $(tableElementId).on("click", elementId, function(){
                            var oid = $(this).data("oid");
                            var command = $(this).data("command");
                            var type = $(this).data("type");
                            var cif = $(this).data("cif");
                            var target = $(this).data("target");
                            var otheroid = $(this).data("otherOid");
                            window.location = "dslik/perlengkapandata/perlengkapandata.jsp?command=8&oid="+oid+"&type="+type+"&cif="+cif+"&activetab="+target+"&otheroid="+otheroid;
                        });
                    }
                    
                    function loadSummary(target, datafor, selector){
                        var usergroup = $("#usergroup").val();
                        var kodecabang = $("#kodecabang").val();
                        var command = <%= Command.NONE %>;
                        var periodeId = $("#periode").val();
                        var onDone = function(data){
                        };
                        var onSuccess = function(data){
                        };
                        var dataSend = {
                            "command" : command,
                            "FRM_FIELD_DATA_FOR" : datafor,
                            "usergroup" : usergroup,
                            "kodecabang" : kodecabang,
                            "periode" : periodeId,
                            "privupdate" : privupdate
                        };
                        getDataFunction(onDone, onSuccess, approot, command, dataSend, target, selector, false);
                    }
                    
                    //iCheck
                    iCheckBox = function(){
                        $("input[type='checkbox'], input[type='radio']").iCheck({
                            checkboxClass: 'icheckbox_minimal-blue',
                            radioClass: 'iradio_minimal-blue'
                        });
                    };
                    
                    function process($selector){
                        if(!$selector.length){
                            return;
                        }
                        var $input = $selector.eq(0);
                        var target = $input.data("target");
                        var datafor = $input.data("for");
                        var place = $input.data("selector");
                        var usergroup = $("#usergroup").val();
                        var kodecabang = $("#kodecabang").val();
                        var command = <%= Command.NONE %>;
                        var periodeId = $("#periode").val();
                        $(place).html("<i class='fa fa-refresh fa-spin'></i>");
                        
                        var dataSend = {
                            "command" : command,
                            "FRM_FIELD_DATA_FOR" : datafor,
                            "usergroup" : usergroup,
                            "kodecabang" : kodecabang,
                            "periode" : periodeId,
                            "privupdate" : privupdate
                        };
                        $.ajax({
                            type : "POST",
                            dataType : "json",
                            url : approot+"/"+target,
                            data : dataSend,
                            success : function(data){
                                $(place).html(data.FRM_FIELD_HTML);
                            }
                        }).always(function(){
                            process($selector.slice(1));
                        });
                    }
                    
                    modalSetting("#showlistmodal", "static", false, false);
                    modalSetting("#othermodal", "static", false, false);
                    showForm(".showlist", "#showlistmodal");
                    activeMenu("#home", "#home");
                    vieweditdata("#showListElement", ".edit");
                    
                    
                    
                    /*var target = ["AjaxDebiturIndividu", "AjaxDebiturBadanUsaha", "AjaxAgunan", "AjaxPenjamin", "AjaxKredit", "AjaxSuratBerharga", "AjaxKreditJoin", "AjaxIrrevocable", "AjaxBankGaransi", "AjaxFasilitasLainnya", "AjaxLaporanKeuanganDebitur", "AjaxPengurusPemilik"];
                    for(var i = 0; i < target.length; i++){
                        loadSummary(target[i], "loadsummary", "#"+target[i]);
                        loadSummary(target[i], "loadsummarynull", "#"+target[i]+"Null");
                    }*/
                    //loadSummary("AjaxKredit", "loadsummaryotherkredit", "#summaryotherkredit");
                    //loadSummary("AjaxDebiturIndividu", "loadsummaryotherdebitur", "#summaryotherdebitur");
                    //loadSummary("AjaxBankGaransi", "loadsummaryotherbankgaransi", "#summaryotherbankgaransi");
                    //loadSummary("AjaxAgunan", "loadsummaryotheragunan", "#summaryotheragunan");
                    //loadSummary("AjaxPengurusPemilik", "loadsummaryotherpenguruspemilik", "#summaryotherpenguruspemilik");
                    showDeleteList(".deletelist", "#othermodal");
                    $("form#clear").submit(function(){
                        var confirmTextg = "Anda yakin untuk menghapus data ini?";
                        var currentHtml = $("#savebtn").html();
                        var target = $("#target").val();
                        if(confirm(confirmTextg)){
                            $("#savebtn").html("Menghapus...").attr("disabled",true);
                            var dataSend = $(this).serialize();
                            var onDone = function(data){
                                $("#savebtn").removeAttr("disabled").html(currentHtml);
                                $("#othermodal").modal("hide");
                                switch(target){
                                    case "AjaxKredit" :
                                        loadSummary("AjaxKredit", "loadsummaryotherkredit", "#summaryotherkredit");
                                    break;
                                    
                                    case "AjaxDebiturIndividu" :
                                        loadSummary("AjaxDebiturIndividu", "loadsummaryotherdebitur", "#summaryotherdebitur");
                                    break;
                                    
                                    case "AjaxBankGaransi" :
                                        loadSummary("AjaxBankGaransi", "loadsummaryotherbankgaransi", "#summaryotherbankgaransi");
                                    break;
                                    
                                    case "AjaxAgunan" :
                                        loadSummary("AjaxAgunan", "loadsummaryotheragunan", "#summaryotheragunan");
                                    break;
                                    
                                    case "AjaxPengurusPemilik" :
                                        loadSummary("AjaxPengurusPemilik", "loadsummaryotherpenguruspemilik", "#summaryotherpenguruspemilik");
                                    break;
                                }
                            };
                            var onSuccess = function(data){
                            };
                            getDataFunction(onDone, onSuccess, approot, <%= Command.DELETEALL %>, dataSend, target, null, true);
                        }
                        return false;
                    });
                    
                     $("#downloadbtn").on('click', function(){
                            alert("xx");
                    });
                    
                    
                    process($(".summary"));
                });
                
                
            </script>
            
        </div><!-- ./wrapper -->?
    </body>
</html>
