<%-- 
    Document   : perbaikan_per_eror_excel
    Created on : Jan 18, 2017, 8:22:06 PM
    Author     : dimata005
--%>
<%@page import="com.dimata.dslik.entity.debitur.PstDebitur"%>
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

    public final static String[] segmenEror = {
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

    public String drawReportSummary(int iCommand, Vector objectClass, long periodeId) {

        ControlList ctrlist = new ControlList();

        ctrlist.setAreaWidth("100%");
        ctrlist.setListStyle("listgen");
        ctrlist.setTitleStyle("tableheader");
        ctrlist.setCellStyle("cellStyle");
        ctrlist.setHeaderStyle("tableheader");
        ctrlist.setBorder(1);

        ctrlist.addHeader("<b>NO</b>", "200px");//1
        ctrlist.addHeader("<b>Cabang</b>", "200px");//2
        for (int i = 0; i < segmenNames.length; i++) {
            ctrlist.addHeader("<b>"+segmenNames[i]+"</b>", "200px");//2
        }

        ctrlist.setLinkSufix("");
        Vector lstData = ctrlist.getData();
        Vector lstLinkData = ctrlist.getLinkData();
        ctrlist.reset();

        int index = -1;
        int count = 0;
        Vector rowx = new Vector(1, 1);
        double total = 0.0;

        for (int i = 0; i < objectClass.size(); i++) {
            CabangBank cabangBank = (CabangBank) objectClass.get(i);
            count = count + 1;
            rowx = new Vector(1, 1);

            rowx.add("" + count);//1
            rowx.add("" + cabangBank.getNamaCabang());//2
            for (int iS = 0; iS < segmenNames.length; iS++) {
                int summary = getSummaryReport(iS, cabangBank.getKodeCabang(), periodeId);
                rowx.add("" + summary);
            }
            lstData.add(rowx);
        }

        return ctrlist.draw();
    }

    public int getSummaryReport(int segmen, String cabang, long periode) {
        int getSummary = 0;

        switch (segmen) {
            case SEGMEN_AGUNAN:
                getSummary = PstAgunan.getCount("" + PstAgunan.fieldNames[PstAgunan.FLD_KODE_KANTOR_CABANG] + "='" + cabang + "' "
                        + "AND " + PstAgunan.fieldNames[PstAgunan.FLD_PERIODE_ID] + "='" + periode + "'");
                break;

            case SEGMEN_BANK_GARANSI:
                getSummary = PstBankGaransi.getCount("" + PstBankGaransi.fieldNames[PstBankGaransi.FLD_KODE_KANTOR_CABANG] + "='" + cabang + "' "
                        + "AND " + PstBankGaransi.fieldNames[PstBankGaransi.FLD_PERIODE_ID] + "='" + periode + "'");
                break;

            case SEGMEN_DEBITUR_BADAN_USAHA:
                 getSummary = PstDebitur.getCountJoin(""// + PstDebitur.fieldNames[PstDebitur.FLD_KODE_KANTOR_CABANG] + "='" + cabang + "' "
                        + "" + PstDebitur.fieldNames[PstDebitur.FLD_PERIODE_ID] + "='" + periode + "' "
                        + "AND " + PstDebitur.fieldNames[PstDebitur.FLD_KODE_JENIS_NSB] + "!='1'",""+periode, cabang);
                break;

            case SEGMEN_FASILITAS_LAINNYA:
                getSummary = PstFasilitasLain.getCount("" + PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_KODE_KANTOR_CABANG] + "='" + cabang + "' "
                        + "AND " + PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_PERIODE_ID] + "='" + periode + "'");
                break;

            case SEGMEN_IRREVOCABLE_LC:
                getSummary = PstIrrevocableLc.getCount("" + PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_KODE_KANTOR_CABANG] + "='" + cabang + "' "
                        + "AND " + PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_PERIODE_ID] + "='" + periode + "'");
                break;

            case SEGMEN_KREDIT:
                getSummary = PstKredit.getCount("" + PstKredit.fieldNames[PstKredit.FLD_KODE_KANTOR_CABANG] + "='" + cabang + "' "
                        + "AND " + PstKredit.fieldNames[PstKredit.FLD_PERIODE_ID] + "='" + periode + "'");
                break;

            case SEGMEN_KREDIT_JOIN:
                getSummary = PstKreditJoinAccount.getCount("" + PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_KODE_KANTOR_CABANG] + "='" + cabang + "' "
                        + "AND " + PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_PERIODE_ID] + "='" + periode + "'");
                break;

            case SEGMEN_LAPORAN_KEUANGAN_DEBITUR:
                getSummary = PstLaporanKeuanganDebitur.getCount("" + PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_KODE_KANTOR_CABANG] + "='" + cabang + "' "
                        + "AND " + PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_PERIODE_ID] + "='" + periode + "'");
                break;

            case SEGMEN_PENGURUS_PEMILIK:
                getSummary = PstPengurusAtauPemilik.getCount("" + PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_KODE_KANTOR_CABANG] + "='" + cabang + "' "
                        + "AND " + PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_PERIODE_ID] + "='" + periode + "'");
                break;

            case SEGMEN_PENJAMIN:
                getSummary = PstPenjamin.getCount("" + PstPenjamin.fieldNames[PstPenjamin.FLD_KODE_KANTOR_CABANG] + "='" + cabang + "' "
                        + "AND " + PstPenjamin.fieldNames[PstPenjamin.FLD_PERIODE_ID] + "='" + periode + "'");
                break;

            case SEGMEN_SURAT_BERHARGA:
                getSummary = PstSuratBerharga.getCount("" + PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_KODE_KANTOR_CABANG] + "='" + cabang + "' "
                        + "AND " + PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_PERIODE_ID] + "='" + periode + "'");
                break;
            default:
                getSummary = PstDebitur.getCountJoin(""// + PstDebitur.fieldNames[PstDebitur.FLD_KODE_KANTOR_CABANG] + "='" + cabang + "' "
                        + "" + PstDebitur.fieldNames[PstDebitur.FLD_PERIODE_ID] + "='" + periode + "' "
                        + "AND " + PstDebitur.fieldNames[PstDebitur.FLD_KODE_JENIS_NSB] + "='1'",""+periode, cabang);
                break;
        }

        return getSummary;
    }
    
    public String searchSummarySegmen(HttpServletRequest request, int iCommand) {
        String returnData = "";
        String cabang = FRMQueryString.requestString(request, "cabang");
        long periodeId = FRMQueryString.requestLong(request, "periodepersegment");
        String whereClause = "";
        if (cabang.length() > 0) {
            if (whereClause.length() > 0) {
                whereClause += " AND " + PstCabangBank.fieldNames[PstCabangBank.FLD_KODE_CABANG] + "='" + cabang + "'";
            } else {
                whereClause += " " + PstCabangBank.fieldNames[PstCabangBank.FLD_KODE_CABANG] + "='" + cabang + "'";
            }
        }
        Vector listCabang = PstCabangBank.list(0, 0, whereClause, "" + PstCabangBank.fieldNames[PstCabangBank.FLD_NAMA_CABANG] + " ASC");

        returnData = drawReportSummary(iCommand, listCabang, periodeId);

        //Get Periode
        return returnData;
    }
%>
<%@include file="../../main/javainit_slik.jsp" %>
<%@include file="../../main/checkuser_slik.jsp" %>

<%  
    int iCommand = FRMQueryString.requestCommand(request);
    String cabang = FRMQueryString.requestString(request, "cabangpersegment");
    long periodeId = FRMQueryString.requestLong(request, "periodepersegment");
    
%>
<%
    String file = "LAPORAN_PER_SEGMEN_SUMMARY";
    if(cabang.length() > 0){
        file += "_CABANG/CAPEM_"+cabang;
    }
    response.setContentType("application/x-msexcel"); 
    response.setHeader("Content-Disposition","attachment; filename="+file+".xls" ); 
%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>SLIK | Laporan Per Segment Detail</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
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
        </script>
    </head>
    <body class="<%= skin%>">
        <input type="hidden" name="command" id="command" value="<%= Command.NONE%>">
        <input type="hidden" name="approot" id="approot" value="<%= approot%>">
        <div class="wrapper">
            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Main content -->
                <%
                    if(true){
                %>
               <section class="content">
                    <form name="driver"  method ="post" action="" role="form">
                        <input type="hidden" name="menu" value="16">
                        <input type="hidden" name="tree" value="5">
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="box">
                                    <div class="box-body">
                                        <%if(true){%>
                                            <div class="row">
                                                <div class="col-md-12" id="reportsummarypersegment">
                                                    <div class='row'>
                                                        <div class='col-md-12'>
                                                            <div class='form-group'>
                                                                <%= searchSummarySegmen(request, iCommand) %>
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
                                </div><!-- /.box -->
                            </div>
                        </div>         
                    </form>
                </section><!-- /.content -->
                <%
                    }
                %>
            </div>
            <div class='control-sidebar-bg'></div>
        </div><!-- ./wrapper -->
        
    </body>
</html>
