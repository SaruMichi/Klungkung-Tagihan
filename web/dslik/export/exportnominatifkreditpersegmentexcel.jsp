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
    String file = "Laporan_Daftar_Nominatif_Kredit_Per_Kolektibiliti".toUpperCase();
    
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
            <table width="100%" border="1" cellpadding="0" cellspacing="0">
                <thead>
                    <tr>
                        <td align='left' colspan='6'>
                            PT. BANK PEMBANGUNAN DAERAH BALI<br>
                            JL. RAYA PUPUTAN - NITI MANDALA, RENON, DENPASAR
                        </td>
                        <td colspan="3"></td>
                        <td align='right' colspan='5'>
                            Rpt-ID : <%= userName %><br>
                            Tgl-Rpt : <%= Formater.formatDate(new Date(), "dd/MM/yyyy") %>
                        </td>
                    </tr>
                    <tr>
                        <td colspan='14' align='center'>
                            DAFTAR NOMINATIF KREDIT PER KOLEKTIBILITI<br>
                            PER : <%= periodeName %><br>
                        </td>
                    </tr>
                <%

                    if(listCabangReport.size() > 0){
                        for(int i = 0; i < listCabangReport.size(); i++){
                            CabangBank cabangBank = (CabangBank) listCabangReport.get(i);
                %>
                    <tr>
                        <td align='left' colspan='6'>
                            <%= cabangBank.getNamaCabang().toUpperCase()+"("+cabangBank.getKodeCabang()+")" %>
                        </td>
                        <td colspan="3"></td>
                        <td align='right' colspan='5'>
                        </td>
                    </tr>
                    <tr>
                        <th>No</th>
                        <th>Sub No <br>Urut</th>
                        <th>Nama Nasabah</th>
                        <th>Nomor PK</th>
                        <th>Tanggal Cair</th>
                        <th>Tanggal JT</th>
                        <th>Klt</th>
                        <th>Plafond</th>
                        <th>Saldo Akhir</th>
                        <th>Kelonggaran Tarik</th>
                        <th>Tunggakan Pokok</th>
                        <th>Tunggakan Bunga</th>
                        <th>Denda</th>
                        <th>Baki Debet</th>
                    </tr>
                    <tr>
                </thead>
                <%
                    int nourut=0; 
                    double toPlafon=0;
                    double bakidebit=0;
               Vector listContentDataKredit = PstKredit.listDistinct(0, 0, PstKredit.fieldNames[PstKredit.FLD_PERIODE_ID]+"='"+periodeId+"' "
                                    + "AND "+PstKredit.fieldNames[PstKredit.FLD_KODE_KANTOR_CABANG]+"='"+cabangBank.getKodeCabang()+"' "
                                    + "AND "+PstKredit.fieldNames[PstKredit.FLD_KODE_KONDISI]+"='"+PstAgunan.statusKeys[0]+"'",""+PstKredit.fieldNames[PstKredit.FLD_NO_REKENING]+" ASC");
                if(listContentDataKredit.size() > 0){
                    for(int iK = 0; iK < listContentDataKredit.size(); iK++){
                        Kredit contentDataJenisKredit = (Kredit) listContentDataKredit.get(iK);
                %>
                        <tbody>
                            <tr>
                                <td></td>
                                <td colspan="13" style='mso-number-format:"@"'><%= contentDataJenisKredit.getNoRekening() %></td>
                            </tr>
                            <%
                            Vector listKredit = PstKredit.listJoinReport(0, 0, "SUBSTRING(kredit."+PstKredit.fieldNames[PstKredit.FLD_NO_REKENING]+",6,2)='"+contentDataJenisKredit.getNoRekening()+"' "
                                    + " AND kredit."+PstKredit.fieldNames[PstKredit.FLD_PERIODE_ID]+"='"+periodeId+"' "
                                    + "AND kredit."+PstKredit.fieldNames[PstKredit.FLD_KODE_KANTOR_CABANG]+"='"+cabangBank.getKodeCabang()+"' "
                                    + "AND kredit."+PstKredit.fieldNames[PstKredit.FLD_KODE_KONDISI]+"='"+PstAgunan.statusKeys[0]+"'", PstKredit.fieldNames[PstKredit.FLD_NO_REKENING]+" ASC");
                            if(listKredit.size() > 0){
                                for(int iA = 0; iA < listKredit.size(); iA++){
                                    Kredit kredit = (Kredit) listKredit.get(iA);
                                    nourut=nourut+1;
                                    toPlafon=toPlafon+kredit.getPlafon();
                                    bakidebit=bakidebit+kredit.getBakiDebet();
                                    %>
                                        <tr>
                                            <td><%= (nourut) %></td>
                                            <td><%= (iA+1) %></td>
                                            <td><%= PstKredit.formatCredit("###.##.##.#####-#", kredit.getNoRekening()) %></td>
                                            <td><%= kredit.getNamaSingkat()%></td>
                                            <td></td>
                                            <td><%= Formater.formatDate(kredit.getTglMulai(), "yyyy-MM-dd") %></td>
                                            <td><%= Formater.formatDate(kredit.getTglTempo(), "yyyy-MM-dd") %></td>
                                            <td align="right"><%= Formater.formatNumber(kredit.getPlafonAwal(), "#,###.##") %></td>
                                            <td align="right"></td>
                                            <td align="right"></td>
                                            <td align="right"><%= Formater.formatNumber(kredit.getTunggakanPokok(), "#,###.##") %></td>
                                            <td align="right"><%= Formater.formatNumber(kredit.getTunggakanBunga(), "#,###.##") %></td>
                                            <td align="right"><%= Formater.formatNumber(kredit.getDenda(), "#,###.##") %></td>
                                            <td align="right"><%= Formater.formatNumber(kredit.getBakiDebet(), "#,###.##") %></td>
                                        </tr>
                                    <%
                                }
                            }
                    }
                            %>
                        </tbody>
                <%
                }%>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                     <td></td>
                    <td></td>
                    <td align="right"><%= Formater.formatNumber(toPlafon, "#,###.##") %></td>
                    <td align="right"></td>
                    <td align="right"></td>
                    <td align="right"></td>
                    <td align="right"></td>
                    <td align="right"></td>
                    <td align="right"><%= Formater.formatNumber(bakidebit, "#,###.##") %></td>
                </tr>
                <%
            }
        }
        %>
            </table>
    </body>
</html>
