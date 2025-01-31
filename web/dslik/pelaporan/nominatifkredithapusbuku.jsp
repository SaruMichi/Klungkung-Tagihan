<%-- 
    Document   : pelaporan_per_segment
    Created on : Oct 2, 2016, 6:41:31 PM
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
<%@include file="../../main/javainit_slik.jsp" %>
<%@include file="../../main/checkuser_slik.jsp" %>
<%  
    boolean privView = userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_MODUL_LAPORAN, AppObjInfo.G2_NOMINATIF_KREDIT_HAPUS_BUKU, AppObjInfo.OBJ_NOMINATIF_KREDIT_HAPUS_BUKU, AppObjInfo.COMMAND_VIEW);
    boolean privPrint = userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_MODUL_LAPORAN, AppObjInfo.G2_NOMINATIF_KREDIT_HAPUS_BUKU, AppObjInfo.OBJ_NOMINATIF_KREDIT_HAPUS_BUKU, AppObjInfo.COMMAND_PRINT);
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
    String whereCabang="";
    if(privViewCheck) {
        cabangKey.add("");
        cabangVal.add("Semua Cabang/Capem");
    }
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
        <title>SLIK | Laporan Nominatif Kredit Hapus Buku</title>
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
                document.driver.action="nominatifkredithapusbuku.jsp";
                document.driver.submit();
                lockScreen('Proses Reporting Sedang Berlangsung, Silahkan menunggu...');
            }
            
            function cmdSearchExcel(){
                document.driver.command.value="<%=Command.SEARCH%>";
                document.driver.action="<%=approot%>/dslik/export/exportnominatifkredithapusbukuexcel.jsp";
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
                        <small>Nominatif Kredit Hapus Buku</small>
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
                                                    <div class="input-group">
                                                        <%= ControlCombo.drawBootsratap("periodepersegment", null, ""+periodeId, periodeKey, periodeVal, "", "form-control") %>
                                                        <span class="input-group-btn">
                                                            <button type='button' name='seach' id='search-btn-segment' class="btn btn-success pull-right" onclick="javascript:cmdSearch()" ><i class="fa fa-search"></i></button>
                                                        </span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-12">
                                                <h3 class="box-title">Laporan Nominatif Kredit Hapus Buku</h3>
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
                                                                            <td colspan="2"></td>
                                                                            <td align='right' colspan='5'>
                                                                                Rpt-ID : <%= userName %><br>
                                                                                Tgl-Rpt : <%= Formater.formatDate(new Date(), "dd/MM/yyyy") %>
                                                                            </td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td colspan='12' align='center'>
                                                                                DAFTAR NOMINATIF KREDIT HAPUS BUKU KONSOLIDASI<br>
                                                                                PER : <%= periodeName %><br>
                                                                            </td>
                                                                        </tr>
                                                                    <%
                                                                        int nourut=0; 
                                                                        double tunggakanPokok=0.0;
                                                                        if(listCabangReport.size() > 0){
                                                                            for(int i = 0; i < listCabangReport.size(); i++){
                                                                                CabangBank cabangBank = (CabangBank) listCabangReport.get(i);
                                                                    %>
                                                                        <tr>
                                                                            <td align='left' colspan='12'>
                                                                                <%= cabangBank.getNamaCabang().toUpperCase()+"("+cabangBank.getKodeCabang()+")" %>
                                                                            </td>
                                                                        </tr>
                                                                        <tr>
                                                                            <th>NO</th>
                                                                            <th>SUB NO <br>URUT</th>
                                                                            <th>NO REKENING</th>
                                                                            <th>NAMA NASABAH</th>
                                                                            <th>ALAMAT</th>
                                                                            <th>TGL HAPUS BUKU</th>
                                                                            <th>KUALITAS KREDIT</th>
                                                                            <th>TUNGGAKAN POKOK</th>
                                                                            <th>BUNGA</th>
                                                                            <th>DENDA</th>
                                                                            <th>TUNGGAKAN ADM</th>
                                                                            <th>SECT KRD</th>
                                                                        </tr>
                                                                    </thead>
                                                                    <%
                                                                    
                                                                    Vector listContentDataKredit = PstKredit.listDistinct(0, 0, PstKredit.fieldNames[PstKredit.FLD_PERIODE_ID]+"='"+periodeId+"' "
                                                                                        + "AND "+PstKredit.fieldNames[PstKredit.FLD_KODE_KANTOR_CABANG]+"='"+cabangBank.getKodeCabang()+"' "
                                                                                        + "AND "+PstKredit.fieldNames[PstKredit.FLD_KODE_KONDISI]+"='"+PstAgunan.statusKeys[2]+"'",""+PstKredit.fieldNames[PstKredit.FLD_NO_REKENING]+" ASC");
                                                                    if(listContentDataKredit.size() > 0){
                                                                        for(int iK = 0; iK < listContentDataKredit.size(); iK++){
                                                                            Kredit contentDataJenisKredit = (Kredit) listContentDataKredit.get(iK);
                                                                    %>
                                                                            <tbody>
                                                                                <tr>
                                                                                     <td></td>
                                                                                    <td colspan="11" style='mso-number-format:"@"'><%= contentDataJenisKredit.getNoRekening()%></td>
                                                                                </tr>
                                                                                <%
                                                                                Vector listKredit = PstKredit.listJoinReport(0, 0, "SUBSTRING(kredit."+PstKredit.fieldNames[PstKredit.FLD_NO_REKENING]+",6,2)='"+contentDataJenisKredit.getNoRekening()+"' "
                                                                                        + " AND kredit."+PstKredit.fieldNames[PstKredit.FLD_PERIODE_ID]+"='"+periodeId+"' "
                                                                                        + "AND kredit."+PstKredit.fieldNames[PstKredit.FLD_KODE_KANTOR_CABANG]+"='"+cabangBank.getKodeCabang()+"' "
                                                                                        + "AND kredit."+PstKredit.fieldNames[PstKredit.FLD_KODE_KONDISI]+"='"+PstAgunan.statusKeys[2]+"'", PstKredit.fieldNames[PstKredit.FLD_NO_REKENING]+" ASC");
                                                                                if(listKredit.size() > 0){
                                                                                    for(int k = 0; k < listKredit.size(); k++){
                                                                                        Kredit kredit = (Kredit) listKredit.get(k);
                                                                                         nourut=nourut+1;
                                                                                         tunggakanPokok=tunggakanPokok+kredit.getTunggakanPokok();
                                                                                        %>
                                                                                            <tr>
                                                                                                <td><%= nourut %></td>
                                                                                                <td><%= (k+1) %></td>
                                                                                                <td><%= PstKredit.formatCredit("###.##.##.#####-#", kredit.getNoRekening()) %></td>
                                                                                                <td><%= kredit.getNamaSingkat()%></td>
                                                                                                <td><%= kredit.getAlamat()%></td>
                                                                                                <td></td>
                                                                                                <td></td>
                                                                                                <td align="right"><%= Formater.formatNumber(kredit.getTunggakanPokok(), "#,###.##") %></td>
                                                                                                <td align="right"></td>
                                                                                                <td align="right"><%= Formater.formatNumber(kredit.getDenda(), "#,###.##") %></td>
                                                                                                <td align="right"></td>
                                                                                                <td align="right"></td>
                                                                                            </tr>
                                                                                        <%
                                                                                    }
                                                                                }
                                                                        }
                                                                                %>
                                                                            </tbody>
                                                                    <%
                                                                    }
                                                                }
                                                            }
                                                            %>
                                                                <tr>
                                                                    <td></td>
                                                                    <td></td>
                                                                    <td></td>
                                                                    <td></td>
                                                                    <td></td>
                                                                    <td></td>
                                                                    <td>Total </td>
                                                                    <td align="right"><%= Formater.formatNumber(tunggakanPokok, "#,###.##") %></td>
                                                                    <td align="right"></td>
                                                                    <td align="right"></td>
                                                                    <td align="right"></td>
                                                                    <td align="right"></td>
                                                                </tr>
                                                            
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
                                                                                <button class="btn btn-primary" type="button" onclick="javascript:cmdSearchExcel()()"><i class="fa fa-save"></i> Export Excel</button>
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
            <!-- Add the sidebar's background. This div must be placed
                 immediately after the control sidebar -->
            <div class='control-sidebar-bg'></div>
            <%@include file="../../template-component/footer-component.jsp" %>
            <%@include file="../../template-component/plugins-component.jsp" %>
            <%--
            <script type="text/javascript">
                $(document).ready(function(){

                    var approot = "<%= approot %>";

                    //SET ACTIVE MENU
                    var activeMenu = function(parentId, childId){
                        $(parentId).addClass("active").find(".treeview-menu").slideDown();
                        $(childId).addClass("active");
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
                    }

                    //DATE PICKER FUNCTION
                    var datePicker = function(contentId, formatDate){
                        $(contentId).datepicker({
                            format : formatDate
                        });
                        $(contentId).on('changeDate', function(ev){
                            $(this).datepicker('hide');
                        });
                    };

                    //MODAL SETTING
                    var modalSetting = function(elementId, backdrop, keyboard, show){
                        $(elementId).modal({
                            backdrop	: backdrop,
                            keyboard	: keyboard,
                            show	: show
                        });
                    };

                    function iCheckBox(){
                        $("input[type='checkbox'], input[type='radio']").iCheck({
                            checkboxClass: 'icheckbox_minimal',
                            radioClass: 'iradio_minimal'
                        });

                        $(".tickdelete").click(function(){
                           var checked = $(this).find(":checkbox:checked").length; 
                           if(checked == 0){
                               $(this).find(":checkbox").iCheck('check');
                           }else{
                               $(this).find(":checkbox").iCheck('uncheck');
                           }
                        });
                    }
                    datePicker(".datepicker", "yyyy-mm-dd");
                    $("#search-btn").click(function(){
                       var command = <%= Command.SEARCH %>;
                       var datafor = "searchsummerysegmen";
                       var periode = $("#periode").val();
                       var cabang = $("#cabang").val();
                       var dataSend = {
                           "FRM_FIELD_DATA_FOR" : datafor,
                           "command" : command,
                           "periode" : periode,
                           "cabang" : cabang
                       };
                       var onDone = function(data){
                       };
                       
                       var onSuccess = function(data){
                       };
                       getDataFunction(onDone, onSuccess, approot, command, dataSend, "AjaxPelaporan", "#reportsummary", false);
                    });
                    
                    $("#search-btn-segment").click(function(){
                       var command = <%= Command.SEARCH %>;
                       var datafor = "searchpersegment";
                       var periode = $("#periodepersegment").val();
                       var cabang = $("#cabangpersegment").val();
                       var segment = $("#segment").val();
                       var dataSend = {
                           "FRM_FIELD_DATA_FOR" : datafor,
                           "command" : command,
                           "periode" : periode,
                           "cabang" : cabang,
                           "segment" : segment
                       };
                       var onDone = function(data){
                       };
                       
                       var onSuccess = function(data){
                       };
                       getDataFunction(onDone, onSuccess, approot, command, dataSend, "AjaxPelaporan", "#reportsummarypersegment", false);
                    });
                });
              </script>
            --%>
        </div><!-- ./wrapper -->
        
        <%@include file="export.jsp" %>
    </body>
</html>
