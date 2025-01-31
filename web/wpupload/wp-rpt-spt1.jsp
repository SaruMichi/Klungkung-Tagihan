<%@page import="com.dimata.webclient.AppSetting"%>
<%@page import="com.dimata.wpupload.entity.compareesptpd.CompareEsptpd"%>
<%@page import="com.dimata.wpupload.entity.compareesptpd.PstCompareEsptpd"%>
<%@page import="java.text.DecimalFormatSymbols"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.dimata.qdep.form.FRMQueryString"%>
<%@page import="com.dimata.gui.jsp.ControlCombo"%>
<%@page import="java.util.Calendar"%>
<%@page import="com.dimata.util.Command"%>
<%@page import="com.dimata.util.Formater"%>
<%@page import="java.util.Date"%>
<%@page import="com.dimata.wpupload.form.esptpd.FrmESPTPD"%>
<%@page import="com.dimata.wpupload.form.wpnamabadan.FrmNamaBadan"%>
<%@page import="com.dimata.wpupload.entity.esptpd.PstESPTPD"%>
<%@page import="com.dimata.wpupload.entity.wpuser.PstAppUserWP"%>
<%@page import="com.dimata.wpupload.entity.wpnamabadan.PstNamaBadan"%>
<%@page import="com.dimata.wpupload.entity.wpnamabadan.NamaBadan"%>
<%@page import="com.dimata.wpupload.entity.esptpd.ESPTPD"%>
<%@page import="java.util.Vector"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>


<%@include file="../main/javainit-wp.jsp" %>
<%
  
    String param = FRMQueryString.requestString(request, "param");
    String[] paramSplit = param.split(" ");
    String npwpd = paramSplit[0];
   
    if (paramSplit[1].length()<2){
        paramSplit[1] = "0" + paramSplit[1]; 
    }
    
    
    Vector listNamaBadan = new Vector(1,1);
    //Vector listESPTPD = new Vector (1,1);
    Vector listCompareESPTPD = new Vector(1,1);
    
    ESPTPD esptpd = new ESPTPD();
    NamaBadan namaBadan = new NamaBadan();
    CompareEsptpd compareEsptpd = new CompareEsptpd();
    
    String idUser="";
    if(userIdWP==null){
        idUser=npwpd;
        listNamaBadan = PstNamaBadan.listJoinSpt(0, 0, 
	    "viewUser."+PstAppUserWP.fieldNames[PstAppUserWP.FLD_NPWPD]+"='"+ idUser +"'", "");
        listCompareESPTPD = PstCompareEsptpd.listJoinAll(0, 0, "viewUser."+PstAppUserWP.fieldNames[PstAppUserWP.FLD_NPWPD]+"='"+idUser+"' and sptpd."+PstESPTPD.fieldNames[PstESPTPD.FLD_MASA_PAJAK]+"='"+paramSplit[1]+"' and sptpd."+PstESPTPD.fieldNames[PstESPTPD.FLD_TAHUN_PAJAK]+"='"+paramSplit[2]+"' and sptpd."+PstESPTPD.fieldNames[PstESPTPD.FLD_NO_SPTPD]+"='"+paramSplit[4]+"'", "");
    }else{
        listNamaBadan = PstNamaBadan.listJoinSpt(0, 0, 
	    "viewUser."+PstAppUserWP.fieldNames[PstAppUserWP.FLD_USER_ID]+"='"+ userIdWP +"'", "");
        listCompareESPTPD = PstCompareEsptpd.listJoinAll(0, 0, "viewUser."+PstAppUserWP.fieldNames[PstAppUserWP.FLD_USER_ID]+"='"+userIdWP+"' and sptpd."+PstESPTPD.fieldNames[PstESPTPD.FLD_MASA_PAJAK]+"='"+paramSplit[1]+"' and sptpd."+PstESPTPD.fieldNames[PstESPTPD.FLD_TAHUN_PAJAK]+"='"+paramSplit[2]+"' and sptpd."+PstESPTPD.fieldNames[PstESPTPD.FLD_NO_SPTPD]+"='"+paramSplit[4]+"'", "");
    }
   
       
    
    if(listNamaBadan.size() != 0){
	namaBadan = (NamaBadan) listNamaBadan.get(0);
    }
    
    //if(listESPTPD.size() != 0){
	//esptpd = (ESPTPD) listESPTPD.get(0);
    //}
    if (listCompareESPTPD.size()!=0){
        compareEsptpd = (CompareEsptpd) listCompareESPTPD.get(0);
    }
    
    
    String namaWajibPajak = namaBadan.getNbNamaBadan();
    int jmlNama = namaWajibPajak.length();
    if (jmlNama>52){
        namaWajibPajak = namaWajibPajak.substring(0,51);
    }else if(jmlNama<52){
        int selisih = 52-jmlNama;
        for (int i=1;i<=selisih;i++){
            namaWajibPajak = namaWajibPajak + " ";
        }
    } 
    
    String alamatWajibPajak = namaBadan.getNbAlamat();
    int jmlAlamat = alamatWajibPajak.length();
    if (jmlAlamat>52){
        alamatWajibPajak = alamatWajibPajak.substring(0,51);
    }else if(jmlAlamat<52){
        int selisih = 52-jmlAlamat;
        for (int i=1;i<=selisih;i++){
            alamatWajibPajak = alamatWajibPajak + " ";
        }
    } 
    
    char jenisPajak = npwpd.charAt(0);
    
    String masaPajak = paramSplit[1];
    String tahunPajak = paramSplit[2];
    String noTelepon = namaBadan.getnNoTelepon();
    int lampiran = Integer.parseInt(paramSplit[3]);
    
    int jmlNoTelepon = noTelepon.length();
    if (jmlNoTelepon<11){
        int selisih = 11-jmlNoTelepon;
        for(int i=1;i<=selisih;i++){
            noTelepon = noTelepon + " ";
        }
    }
    
    Double jumlahOmset = Double.valueOf(compareEsptpd.getCompareEJumlahOmzet());
    Double jumlahServiceTax = compareEsptpd.getCompareEServiceTax();
    Double jumlahTarif = compareEsptpd.getCompareETarif();
    Double jumlahServiceAwal = jumlahServiceTax * jumlahTarif;
    Double jumlahOmsetService = jumlahServiceAwal + jumlahOmset;
    Double jumlahpajak = compareEsptpd.getCompareEHarusBayar();//(jumlahOmsetService * jumlahTarif)/100;
    
    DecimalFormat numberFormat = (DecimalFormat) DecimalFormat.getCurrencyInstance();
    DecimalFormatSymbols numberFormatSymbol = new DecimalFormatSymbols();
    numberFormatSymbol.setCurrencySymbol("Rp. ");
    numberFormatSymbol.setMonetaryDecimalSeparator('.');
    numberFormatSymbol.setGroupingSeparator(',');
    numberFormat.setDecimalFormatSymbols(numberFormatSymbol);
   
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Report SPT 1</title>
    <link href="../styles/wpupload/css/bootstrap.min.css" rel="stylesheet">
    <link href="../styles/wpupload/css/bootstrap-responsive.min.css" rel="stylesheet">
    <link href="../styles/wpupload/css/font-google.css" rel="stylesheet">
    <link href="../styles/wpupload/css/font-awesome.css" rel="stylesheet">
    <link href="../styles/wpupload/css/style.css" rel="stylesheet">
    <script type="text/javascript" src="../styles/jquery.js"></script> 
    <script type="text/javascript" src="../styles/jQuery.print.js"></script> 
<style>
	body{
		font-size:12px;	
	}
	.squarelabel{
		float:left;
		height:20px;
		line-height:20px;
	}
	.square{
		float:left;
		width:20px;
		height:20px;
		border: thin solid grey;
		margin-left:20px;
                text-align: center;
	}
	
	.square2{
		float:left;
		width:20px;
		height:20px;
		border: thin solid grey;
		text-align: center;
	}
	.square3{
		float:left;
		width:20px;
		height:20px;
		border: thin solid grey;
		border-left:none;
                text-align: center;
	}
	.square4{
		float:left;
		width:20px;
		height:20px;
		border: thin solid grey;
		margin-left:20px;
                text-align: center;
	}
	.square5{
		float:left;
		width:20px;
		height:20px;
		border: thin solid grey;
		border-top:none;
                text-align: center;
		
	}
	.square6{
		float:left;
		width:20px;
		height:20px;
		border: thin solid grey;
		border-left:none;
		border-top:none;
                text-align: center;
	}
	.square7{
		float:left;
		width:20px;
		height:20px;
		border: thin solid grey;
		text-align:center;
		line-height:20px;
	}
	.square8{
		float:left;
		width:200px;
		height:20px;
		border: thin solid grey;
		border-left:none;
		text-align:right;
		line-height:20px;
                padding-right: 10px;
	}
	.square9{
		float:left;
		width:20px;
		height:20px;
		border-bottom: thin solid grey;
		margin-left:20px;
                text-align: center;
	}
	.square10{
		float:left;
		width:20px;
		height:20px;
		border-bottom: thin solid grey;
                text-align: center;
	}
	.space{
		margin-left:10px;
		}
	.space2{
		margin-left:20px;
		}
	.transform{
		/*transform: rotate(90deg);*/
	}
	.tablereport .container {
		width:20cm;
		height:35.56cm;
	}
	.tablereport table{
		margin-left:20px;
		}
        @media print{
            .btnprint {display:none;}
        }
</style>
</head>
<%
    
    
 %>
 <button style="float: right" class="btn btn-success btnprint" type="button">Cetak</button>
<body class="tablereport">
        <div class="container ">
    	<div class="row">
        	 <table width="100%" border="1">
              <tr>
                <td style="text-align:center" rowspan="2">
                    <%if(AppSetting.TYPE_KOP_WPONLINE==AppSetting.DISPENDA_GIANYAR){%>
                        <h2>SPTPD</h2>
                        DISAMPAIKAN KEPADA<br>
                        BUPATI GIANYAR<br>
                        MELALUI DINAS PENDAPATAN<br>
                        KABUPATEN GIANYAR 
                    <%}else if(AppSetting.TYPE_KOP_WPONLINE==AppSetting.DISPENDA_KLUNGKUNG){%>
                        <img src="../imgcompany/dimata_system_logo.png" width="100" border="none" />
                    <%}else{}%>
                </td>
                <td style="text-align:center">
                    <%if(AppSetting.TYPE_KOP_WPONLINE==AppSetting.DISPENDA_GIANYAR){%>
                        <h4>SURAT PEMBERITAHUAN PAJAK DAERAH</h4>
                    <%}else if(AppSetting.TYPE_KOP_WPONLINE==AppSetting.DISPENDA_KLUNGKUNG){%>
                        <h4>PEMERINTAH KABUPATEN KLUNGKUNG</h4>
                        <p>BADAN PENGELOLAAN KEUANGAN DAN PENDAPATAN DAERAH<br>
                        Jl. Untung Surapati No.2, TELP. NO (0366) 21496-25590<br>
                        Semarapura</p>
                    <%}else{}%>
                </td>
                <td rowspan="2" style="text-align:center;">
                     <%if(AppSetting.TYPE_KOP_WPONLINE==AppSetting.DISPENDA_GIANYAR){%>
                        <h4>MASA PAJAK</h4><br>
                        <label class="squarelabel" style="font-size:10px;">BULAN &nbsp;</label>
                        <div class="square2"><%= masaPajak.charAt(0) %></div>
                        <div class="square3"><%= masaPajak.charAt(1) %></div>
                        <label class="squarelabel" style="font-size:10px;"> &nbsp; TAHUN &nbsp;</label>
                        <div class="square2"><%=tahunPajak.charAt(2)%></div>
                        <div class="square3"><%=tahunPajak.charAt(3)%></div>
                    <%}else if(AppSetting.TYPE_KOP_WPONLINE==AppSetting.DISPENDA_KLUNGKUNG){%>
                        <p align="left">
                        <label style="font-size:10px;">NO. SPTPD   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: <%=compareEsptpd.getCompareENoSPTPD()%></label>
                        <label style="font-size:10px;">MASA PAJAK  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: <%= masaPajak.charAt(0) %><%= masaPajak.charAt(1) %></label>
                        <label style="font-size:10px;">TAHUN PAJAK &nbsp;&nbsp;&nbsp;: <%=tahunPajak.charAt(2)%><%=tahunPajak.charAt(3)%></label>
                        <p>
                    <%}else{}%>
                   
                    
                </td>
                
              </tr>
              <tr>
                <td style="text-align:center">
                    <label class="squarelabel space">JENIS PAJAK &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</label> 
                    
                    <div class="square">
                        <%
                            if (jenisPajak=='1'){ %>
                            &check;
                        <%    }
                        %>
                    </div>
                    <label class="squarelabel space ">HOTEL</label> 
                    <div class="square">
                        <%
                            if (jenisPajak=='2'){ %>
                            &check;   
                        <%   }
                        %>
                    </div>
                    <label class="squarelabel space">RESTORAN</label> 
                    <div class="square">
                        <%
                            if (jenisPajak=='3'){ %>
                            &check;
                        <%    }
                        %>
                    </div>
                    <label class="squarelabel space">HIBURAN</label> 
                </td>
               </tr>
            </table>
            
        </div>
        <div class="row">
       	  <table width="100%" border="0">
              <tr style="font-size:9px;">
                <td width="8%" height="36"><h6>PERHATIAN</h6></td>
                <td width="24%"><ul style="margin-top:10px;"><li>BACA DAHULU PETUNJUK PENGISIAN</li></ul></td>
                <td width="36%"><ul style="margin-top:10px;"><li>ISI DENGAN HURUF CETAK / KETIK DENGAN TINTA HITAM</li></ul></td>
                <td width="32%"><ul style="margin-top:10px; "><li>BERI TANDA &check; PADA KOTAK PILIHAN YANG SESUAI</li></ul></td>
              </tr>
            </table>
        </div>
        <div class="row">
       	  <table width="100%" border="1">
        	  <tr>
                      <td width="8%" class="transform"><center><img src="../styles/wpupload/Image/identitas.png"></center></td>
        	    <td width="92%">
               	  <table width="100%" border="0" style="margin-left:10px;">
                	  <tr>
                	    <td style="padding-left:10px;" width="20%">NPWPD</td>
                	    <td width="1%">:</td>
                	    <td width="79%">
                                <%
                                    try{
                                        %>
                                    <div class="square2"><%=npwpd.charAt(0)%></div>
                                    <div class="square4"><%=npwpd.charAt(2)%></div> 
                                    <div class="square3"><%=npwpd.charAt(3)%></div>
                                    <div class="square3"><%=npwpd.charAt(4)%></div>
                                    <div class="square3"><%=npwpd.charAt(5)%></div>
                                    <div class="square3"><%=npwpd.charAt(6)%></div>
                                    <div class="square3"><%=npwpd.charAt(7)%></div>
                                    <div class="square4"><%=npwpd.charAt(9)%></div>
                                    <div class="square3"><%=npwpd.charAt(10)%></div>
                                    <div class="square4"><%=npwpd.charAt(12)%></div>
                                    <div class="square3"><%=npwpd.charAt(13)%></div>
                                    <div class="square4"><%=npwpd.charAt(15)%></div>
                                    <div class="square4"><%=npwpd.charAt(17)%></div>
                                <%
                                    }catch(Exception ex){
                                    }
                                %>
                        	
                        </td>
              	    </tr>
                	  <tr>
                	    <td  style="padding-left:10px;">NAMA WAJIB PAJAK</td>
                	    <td>:</td>
                	    <td style="padding-right:10px;">
                            <% 
                                try{
                                    for (int i=0;i<=51;i++ ){ 
                                        if (i==0){
                                            out.println("<div class='square2'>" + namaWajibPajak.charAt(i) +"</div>");
                                        }else if (i>0 && i<26){
                                            out.println("<div class='square3'>" + namaWajibPajak.charAt(i) +"</div>");
                                        }else if (i==26){
                                            out.println("<div class='square5'>" + namaWajibPajak.charAt(i) +"</div>");
                                        }else{
                                            out.println("<div class='square6'>" + namaWajibPajak.charAt(i) +"</div>");
                                        }
                                }
                                }catch(Exception ex){
                                }
                                
                            %>
                            
                        </td>
              	    </tr>
                	  <tr>
                	    <td style="padding-left:10px;">ALAMAT</td>
                	    <td>:</td>
                	    <td style="padding-right:10px;">
                            <% 
                                try{
                                    for (int i=0;i<=51;i++ ){ 
                                        if (i==0){
                                            out.println("<div class='square2'>" + alamatWajibPajak.charAt(i) +"</div>");
                                        }else if (i>0 && i<26){
                                            out.println("<div class='square3'>" + alamatWajibPajak.charAt(i) +"</div>");
                                        }else if (i==26){
                                            out.println("<div class='square5'>" + alamatWajibPajak.charAt(i) +"</div>");
                                        }else{
                                            out.println("<div class='square6'>" + alamatWajibPajak.charAt(i) +"</div>");
                                        }
                                }
                                }catch(Exception ex){
                                }
                            %>
                            </td>
              	    </tr>
                	  <tr>
                	    <td style="padding-left:10px;">NO. TELEPON</td>
                	    <td>:</td>
                	    <td>
                                <% 
                                    try{
                                        for (int i=0;i<=3;i++ ){
                                        if (i==0){
                                            out.println("<div class='square2'>"+noTelepon.charAt(i)+"</div>");
                                        }else{
                                            out.println("<div class='square3'>"+noTelepon.charAt(i)+"</div>");
                                        }
                                    } 
                                   
                                    for (int i=4;i<=jmlNoTelepon-1;i++){
                                        if (i==4){
                                            out.println("<div class='square4'>"+noTelepon.charAt(i)+"</div>");
                                        }else{
                                            out.println("<div class='square3'>"+noTelepon.charAt(i)+"</div>");
                                        }
                                    }
                                }catch(Exception ex){
                                }
                                    
                                %>
                        	
                        </td>
              	    </tr>
           	    </table></td>
      	    </tr>
      	  </table>
        </div>
        <div class="row"><table><tr><td>&nbsp;</td></tr></table></div>
        <div class="row">
       	  <table width="100%" border="1">
        	  <tr>
        	    <td colspan="2">&nbsp;</td>
        	    <td width="31%" style="text-align:center">RUPIAH PENUH</td>
      	    </tr>
        	  <tr>
        	    <td width="8%" class="transform" width:"3%"><center><img src="../styles/wpupload/Image/Pendapatan.png"></center></td>
        	    <td colspan="2">
                	<table style="margin-left:10px;" width="100%" border="0">
                      <tr>
                        <td style="padding-left:10px;" width="65%">1. PENDAPATAN DARI JASA PENYEWAAN KAMAR ............................</td>
                        <td width="35%">
                            <div class="square7">1</div>
                            <div class="square8">
                                <%
                                if (jenisPajak=='1'){
                                    out.println(numberFormat.format(jumlahOmset));
                                }
                                %>
                                
                            </div>
                        </td>
                      </tr>
                      <tr>
                        <td style="padding-left:10px;">2. PENDAPATAN DARI JASA ATAU PRODUK HOTEL LAINNYA ............................</td>
                        <td><div class="square7">2</div><div class="square8"></div></td>
                      </tr>
                      <tr>
                        <td style="padding-left:10px;">3. PENDAPATAN DARI PENJUALAN MAKANAN DAN MINUMAN ............................</td>
                        <td>
                            <div class="square7">3</div>
                            <div class="square8">
                                <%
                                if (jenisPajak=='2'){
                                    out.println(numberFormat.format(jumlahOmset));
                                }
                                %>
                            </div>
                        </td>
                      </tr>
                      <tr>
                        <td style="padding-left:10px;">4. PENDAPATAN DARI PENJUALAN RESTORAN LAINNYA ............................</td>
                        <td><div class="square7">4</div><div class="square8"></div></td>
                      </tr>
                      <tr>
                        <td style="padding-left:10px;">5. PENDAPATAN DARI TIKET HIBURAN  ............................</td>
                        <td>
                            <div class="square7">5</div>
                            <div class="square8">
                                <%
                                if (jenisPajak=='3'){
                                    out.println(numberFormat.format(jumlahOmset));
                                }
                                %>
                            </div>
                        </td>
                      </tr>
                      <tr>
                        <td style="padding-left:10px;">6. PENDAPATAN DARI JASA ATAU SARANA HIBURAN LAINNYA ........</td>
                        <td><div class="square7">6</div><div class="square8"></div></td>
                      </tr>
                      <tr>
                        <td style="padding-left:10px;">7. LAIN-LAIN ..............................</td>
                        <td>
                            <div class="square7">7</div>
                            <div class="square8">
                                <% out.println(numberFormat.format(jumlahServiceAwal));%>
                            </div>
                        </td>
                      </tr>
                    </table>

                </td>
       	    </tr>
        	  <tr>
        	    <td class="transform" width:"3%"><center><img src="../styles/wpupload/Image/Pajak.png"></center></td>
        	    <td colspan="2">
               	  <table style="margin-left:10px;" width="100%" border="0">
                	  <tr>
                	    <td width="65%" style="padding-left:10px;">8. JUMLAH PENDAPATAN SEBAGAI DASAR PENGENAAN PAJAK ...........</td>
                	    <td width="35%">
                                <div class="square7">8</div>
                                <div class="square8">
                                    <%
                                        out.println(numberFormat.format(jumlahOmsetService));
                                    %>
                                </div>
                            </td>
              	    </tr>
                	  <tr>
                	    <td style="padding-left:10px;">9. Pajak Terutang (5%,10%,12%,12,5%,20%,25% X Jumlah Pada Angka 8) ....</td>
                            <td>
                                <div class="square7">9</div>
                                <div class="square8">
                                    <%
                                        out.println(numberFormat.format(jumlahpajak));
                                    %>
                                </div>
                            </td>
              	    </tr>
           	    </table></td>
       	    </tr>
        	  <tr>
        	    <td class="transform" width:"3%"><center><img src="../styles/wpupload/Image/setoran.png"></center></td>
        	    <td colspan="2">
                <table width="100%" border="0" style="margin-left:10px;">
        	      <tr>
        	        <td width="65%" style="padding-left:5px;">10. JUMLAH SETORAN PAJAK YANG TELAH DILAKUKAN .......</td>
        	        <td width="35%"><div class="square7">10</div><div class="square8"></td>
      	        </tr>
                <tr>
        	        <td style="padding-left:140px;">
                    	<label class="squarelabel ">TGL</label>
                        <label class="squarelabel space2 ">BLN</label>
                        <label class="squarelabel space2 ">THN</label>
                    </td>
        	        <td>&nbsp;</td>
      	        </tr>
                  <tr>
        	        <td style="padding-left:15px;">
                    	<label class="squarelabel space ">TGL. SETORAN</label>
                        <div class="square"></div>
                        <div class="square3"></div>
                        <div class="square3"></div>
                        <div class="square3"></div>
                        <div class="square3"></div>
                        <div class="square3"></div>
                        <label class="squarelabel space">NO. SSPD</label>
                        <div class="square9"></div><div class="square10"></div>
                        <div class="square10"></div><div class="square10"></div>
                    </td>
        	        <td>&nbsp;</td>
      	        </tr>
                <tr>
        	        <td style="padding-left:15px;">
                    	<label class="squarelabel space ">TGL. SETORAN</label>
                        <div class="square"></div>
                        <div class="square3"></div>
                        <div class="square3"></div>
                        <div class="square3"></div>
                        <div class="square3"></div>
                        <div class="square3"></div>
                        <label class="squarelabel space">NO. SSPD</label>
                        <div class="square9"></div></div><div class="square10"></div>
                        <div class="square10"></div><div class="square10"></div>
                    </td>
        	        <td>&nbsp;</td>
      	        </tr>
        	      <tr>
        	        <td style="padding-left:5px;">
                    	<label class="squarelabel">11. &nbsp;</label>
                    	<div class="square2"></div><label class="squarelabel space ">KEKURANGAN ATAU</label> 
                        <div class="square"></div>
                        <label class="squarelabel space ">KELEBIHAN SETORAN PAJAK ..............</label> 
                    </td>
        	        <td><div class="square7">11</div><div class="square8"></td>
      	        </tr>
        	      
      	      </table></td>
       	    </tr>
        	  <tr>
        	    <td class="transform" width:"3%"><center><img src="../styles/wpupload/Image/lampiran.png"></center></td>
        	    <td width="55%">
                	<table style="margin-left:10px;" width="100%" border="0">
                      <tr>
                        <td style="padding-left:8px;"><div class="square2"></div>
                        <label class="squarelabel space ">SSPD NO. .................................................................</label> </td>
                      </tr>
                      <tr>
                        <td style="padding-left:8px;"><div class="square2"></div><label class="squarelabel space ">RINCIAN PENDAPATAN</label> </td>
                      </tr>
                      <tr>
                        <td style="padding-left:8px;"><div class="square2"></div><label class="squarelabel space ">SURAT KUASA</label> </td>
                      </tr>
                    </table>

                </td>
        	    <td>
                	<table style="margin-left:0px;" width="100%" border="0">
                      <tr>
                        <td style="padding-left:8px;">
                        	<div class="square2"></div>
                        	<label class="squarelabel space ">......................................</label>
                        </td>
                      </tr>
                      <tr>
                        <td style="padding-left:8px;">
                        	<div class="square2"></div>
                        	<label class="squarelabel space ">......................................</label>
                        </td>
                      </tr>
                      <tr>
                        <td style="padding-left:8px;">
                        	<div class="square2"></div>
                        	<label class="squarelabel space ">......................................</label>
                        </td>
                      </tr>
                    </table>
                </td>
   	        </tr>
        	  <tr>
        	    <td rowspan="3" class="transform" width:"3%"><center><img src="../styles/wpupload/Image/tandatangan.png"></center></td>
        	    <td colspan="2" style="text-align:center; font-size:11px">
                	PERNYATAAN : Dengan ini menyadari sepenuhnya segala akibatnya termasuk sanksi-sanksi sesuai dengan peraturan dan perundang-undangan yang berlaku, saya menyatakan bahwa yang saya beritahukan dalam formulir ini beserta lampiran-lampirannya adalah benar
                </td>
       	    </tr>
             <tr>
               <td colspan="2">
               <table width="100%" border="0" style="margin-left:10px;">
                 <tr>
                   <td width="16%" style="padding-left:10px;">DIISI OLEH</td>
                   <td width="1%">:</td>
                   <td width="83%">
                   	<div class="square2"></div><label class="squarelabel space ">WAJIB PAJAK</label> 
                    <div class="square"></div><label class="squarelabel space ">KUASA</label> 
                   </td>
                 </tr>
                 <tr>
                   <td style="padding-left:10px;">NAMA LENGKAP</td>
                   <td>:</td>
                   <td>
                   	<div class="square2"></div><div class="square3"></div>
                    <div class="square3"></div><div class="square3"></div>
                    <div class="square3"></div><div class="square3"></div>
                    <div class="square3"></div><div class="square3"></div>
                    <div class="square3"></div><div class="square3"></div>
                    <div class="square3"></div><div class="square3"></div>
                    <div class="square3"></div><div class="square3"></div>
                    <div class="square3"></div><div class="square3"></div>
                    <div class="square3"></div><div class="square3"></div>
                    <div class="square3"></div><div class="square3"></div>
                    <div class="square3"></div><div class="square3"></div>
                    <div class="square3"></div><div class="square3"></div>
                    <div class="square3"></div><div class="square3"></div>
                   </td>
                 </tr>
                 <tr>
                   <td style="padding-left:10px;">JABATAN</td>
                   <td>:</td>
                   <td>
                   	<div class="square2"></div><div class="square3"></div>
                    <div class="square3"></div><div class="square3"></div>
                    <div class="square3"></div><div class="square3"></div>
                    <div class="square3"></div><div class="square3"></div>
                    <div class="square3"></div><div class="square3"></div>
                    <div class="square3"></div><div class="square3"></div>
                    <div class="square3"></div><div class="square3"></div>
                    <div class="square3"></div><div class="square3"></div>
                    <div class="square3"></div><div class="square3"></div>
                    <div class="square3"></div><div class="square3"></div>
                    <div class="square3"></div><div class="square3"></div>
                    <div class="square3"></div><div class="square3"></div>
                    <div class="square3"></div><div class="square3"></div>
                   </td>
                 </tr>
               </table></td>
       	    </tr>
            <tr>
              
        	    <td colspan="2">
                <table style="margin-left:10px;" width="100%" border="0">
        	      <tr>
        	        <td style="padding-left:480px;">TANDA TANGAN DAN STEMPEL</td>
      	        </tr>
        	      <tr>
        	        <td>&nbsp;</td>
      	        </tr>
               <tr>
        	        <td>&nbsp;</td>
      	        </tr>
                 
                 
        	      <tr>
        	        <td style="padding-left:480px;">.........................</td>
      	        </tr>
                 
      	      </table></td>
      	    </tr>
      	  </table>
        </div>
        <div class="row">
        	<table width="100%">
            	<tr>
                    <td style="float:right">
                        <%
                            if (lampiran==1){
                                out.println("Lembar 1 : Wajib Pajak ");
                            }else if (lampiran==2){
                                out.println("Lembar 2 : Fungsi Pendataan");
                            }else if (lampiran==3){
                                out.println("Lembar 3 : Fungsi Penagihan");
                            }else if (lampiran==4){
                                out.println("Lembar 4 : Bendahara Penerimaan");
                            }else if (lampiran==5){
                                out.println("Lembar 5 : Bendahara Penerimaan");
                            }else if (lampiran==6){
                                out.println("Lembar 6 : Bendahara Penerimaan");
                            }else if (lampiran==7){
                                out.println("Lembar 7 : Korwil");
                            }
                        %>
                        
                    </td>
                </tr>
            </table>
        </div>
</div>
       
    </body>
     <script>
        $(document).ready(function(){
            $('.btnprint').click(function(){
                 //$('.tablereport').show().printElement();
                 window.print();
             });

        });


        </script> 
        <button style="float: right" class="btn btn-success btnprint" type="button">Cetak</button>
</html>
