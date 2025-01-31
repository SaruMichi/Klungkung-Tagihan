<%@page import="com.dimata.dslik.entity.agunan.Agunan"%>
<%@page import="com.dimata.dslik.entity.agunan.PstAgunan"%>
<%@page import="com.dimata.dslik.entity.kredit.Kredit"%>
<%@page import="com.dimata.dslik.entity.kredit.PstKredit"%>
<%@page import="com.dimata.dslik.entity.masterdata.CabangBank"%>
<%@page import="com.dimata.util.Formater"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Vector"%>
<%@page import="com.dimata.dslik.entity.masterdata.PstCabangBank"%>
<%@page import="com.dimata.qdep.form.FRMQueryString"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../main/javainit_slik.jsp" %>
<!DOCTYPE html>
<%
    String cabang = FRMQueryString.requestString(request, "cabangpersegment");
    String file = "LAPORAN_NOMINATIF_AGUNAN";
    if(cabang.length() > 0){
        file+="_CABANG/CAPEM_"+cabang;
    }
    
    response.setContentType("application/x-msexcel"); 
    response.setHeader("Content-Disposition","attachment; filename="+file+".xls" ); 
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            long periodeId = FRMQueryString.requestLong(request, "periodepersegment");
            String statusreport = FRMQueryString.requestString(request, "statusreport");
            
            String periodeName = "";
            Periode entPeriode = new Periode();
            if(periodeId != 0){
                try{
                    entPeriode = PstPeriode.fetchExc(periodeId);
                    periodeName = entPeriode.getNama();
                }catch(Exception ex){

                }
            }
            
            String whereClauseCabang = "";
            if(cabang.length() > 0){
                whereClauseCabang = PstCabangBank.fieldNames[PstCabangBank.FLD_KODE_CABANG]+"='"+cabang+"'";
            }
            Vector listCabangReport = PstCabangBank.list(0, 0, whereClauseCabang, PstCabangBank.fieldNames[PstCabangBank.FLD_KODE_CABANG]+" ASC");
        %>
        <table border="1" width="100%" cellpadding="0" cellspacing="0">
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
                        if(listAgunan.size() > 0){
                            for(int iA = 0; iA < listAgunan.size(); iA++){
                                Agunan agunan = (Agunan) listAgunan.get(iA);
                                noUrut=noUrut+1;
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
                                        <td></td>
                                    </tr>
                                <%
                            }
                        }
                        no+=1;
                    }
                }
                        %>
                    </tbody>
            <%
            }
        }
    }
    %>
        </table>
    </body>
</html>
