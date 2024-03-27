/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.wpupload.ajax;

import com.dimata.dtaxintegration.entity.payment.PaymentPhr;
import com.dimata.gui.jsp.ControlCombo;
import com.dimata.gui.jsp.ControlLine;
import com.dimata.gui.jsp.ControlList;
import com.dimata.qdep.form.FRMMessage;
import com.dimata.qdep.form.FRMQueryString;
import com.dimata.util.Command;
import com.dimata.util.Formater;
import com.dimata.webclient.AppSetting;
import com.dimata.wpupload.entity.compareesptpd.CompareEsptpd;
import com.dimata.wpupload.entity.compareesptpd.PstCompareEsptpd;
import com.dimata.wpupload.entity.esptpd.ESPTPD;
import com.dimata.wpupload.entity.esptpd.PstESPTPD;
import com.dimata.wpupload.entity.wpnamabadan.NamaBadan;
import com.dimata.wpupload.entity.wpnamabadan.PstNamaBadan;
import com.dimata.wpupload.entity.wpuser.PstAppUserWP;
import com.dimata.wpupload.entity.esptpd.ViewAllSimpatda;
import com.dimata.wpupload.entity.esptpd.PstViewAllSimpatda;
import com.dimata.wpupload.entity.esptpd.ViewPembayaranSimpatda;
import com.dimata.wpupload.entity.esptpd.PstViewPembayaranSimpatda;
import com.dimata.wpupload.form.esptpd.CtrlESPTPD;
import com.dimata.wpupload.form.esptpd.FrmESPTPD;
import com.dimata.wpupload.form.wpnamabadan.FrmNamaBadan;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Ardiadi
 */
public class PelaporanPajakHandler extends HttpServlet {
    
    private String searchTerm;
    private String colName;
    private int colOrder;
    private String dir;
    private int start;
    private int amount;
    
    private String[] indoMonth = {
	    "Januari",
	    "Februari",
	    "Maret",
	    "April",
	    "Mei",
	    "Juni",
	    "Juli",
	    "Agustus",
	    "September",
	    "Oktober",
	    "November",
	    "Desember"
	};

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	response.setContentType("text/html;charset=UTF-8");
	
	
	//GET REQUEST
	int iCommand = FRMQueryString.requestCommand(request);
        
	String masaPajak = FRMQueryString.requestString(request, FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_MASA_PAJAK]);
	String tahunPajak = FRMQueryString.requestString(request, FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_TAHUN_PAJAK]);
        String npwpdpelapor = FRMQueryString.requestString(request, FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_NPWPD]);
	String npwpdId = FRMQueryString.requestString(request, "user");
	int comparePeriod = 0;
	if(Integer.parseInt(masaPajak) >= 10){
	    masaPajak = masaPajak;
	    comparePeriod = Integer.parseInt(tahunPajak+""+masaPajak);
	}else{
	    comparePeriod = Integer.parseInt(tahunPajak+"0"+Integer.parseInt(masaPajak));
	    masaPajak = "0"+Integer.parseInt(masaPajak);
	}
	
	int currentPeriod = Integer.parseInt(Formater.formatDate(new Date(), "yyyyMM"));
	
	HttpSession session = request.getSession();
	String approot = FRMQueryString.requestString(request, "approot");
	String[] indoMonth = {
	    "Januari",
	    "Februari",
	    "Maret",
	    "April",
	    "Mei",
	    "Juni",
	    "Juli",
	    "Agustus",
	    "September",
	    "Oktober",
	    "November",
	    "Desember"
	};
	
	String masaPajakDisplay = indoMonth[Integer.parseInt(masaPajak)-1];
	String msgString = "";
	int iErrCode = FRMMessage.NONE;
	
	//GET CURRENT MONTH
	Calendar cal = Calendar.getInstance();
	cal.setTime(new Date());
	int month = cal.get(Calendar.MONTH);
	
	
	//GET CURRENT YEAR
	int currentYearCheck = Calendar.getInstance().get(Calendar.YEAR);
	
	CtrlESPTPD ctrlESPTPD = new CtrlESPTPD(request);
	ControlLine ctrLine = new ControlLine();
	
	iErrCode = ctrlESPTPD.action(iCommand, 0, request);
	FrmESPTPD frmESPTPD = ctrlESPTPD.getForm();
	
	ESPTPD esptpd = ctrlESPTPD.getESPTPD();
	msgString = ctrlESPTPD.getMessage();
	
	String returnData ="";
	if(iCommand == Command.SAVE && npwpdId != null){
	    if(comparePeriod < currentPeriod){
		if(msgString.equals("EXIST")){
		    returnData+=""
		    + "<h3 class='alert alert-error'><i class='icon icon-warning-sign'></i> Gagal Menyimpan Laporan Pajak</h3>"
		    + "<p>"
			+ "Laporan pajak anda dengan masa pajak bulan <b>"+masaPajakDisplay+"</b> dan tahun pajak <b>"+tahunPajak+"</b> sudah ada, mohon cek kembali laporan pajak anda<br><br>"
			+ "Daftar laporan pajak anda dapat dilihat pada halaman <b><a href='"+approot+"/wpupload/wp-listlaporan.jsp'>Daftar Pelaporan Pajak</a></b>"
		    + "</p>"
		    + "<input type='hidden' name='messageString' value='"+msgString+"' id='msgString'>"
                    + "<input type='hidden' name='npwpdpajaklapor' value='"+npwpdpelapor+"' id='npwpdpajaklapor'>"
                    + "<input type='hidden' name='masapajaklapor' value='"+masaPajak+"' id='masapajaklapor'>"
                    + "<input type='hidden' name='rootpro' value='"+approot+"' id='rootpro'>"
                    + "<input type='hidden' name='tahunpajaklapor' value='"+tahunPajak+"' id='tahunpajaklapor'>";        
		}else{
		    returnData += "<h3 class='alert alert-success'><i class='icon icon-ok'></i> Berhasil Menyimpan Laporan Pajak</h3>"
		    + "<p>"
			+ "Laporan pajak anda dengan masa pajak bulan <b>"+masaPajakDisplay+"</b> dan tahun pajak <b>"+tahunPajak+"</b> berhasil disimpan<br><br>"
			+ "Daftar laporan pajak anda dapat dilihat pada halaman <b><a href='"+approot+"/wpupload/wp-listlaporan.jsp'>Daftar Pelaporan Pajak</a></b>"
		    + "</p>"
                    + "<br><br>"
                    + "<input type='hidden' name='messageString' value='"+msgString+"' id='msgString'>"
                    + "<input type='hidden' name='npwpdpajaklapor' value='"+npwpdpelapor+"' id='npwpdpajaklapor'>"
                    + "<input type='hidden' name='masapajaklapor' value='"+masaPajak+"' id='masapajaklapor'>"
                    + "<input type='hidden' name='rootpro' value='"+approot+"' id='rootpro'>"
                    + "<input type='hidden' name='tahunpajaklapor' value='"+tahunPajak+"' id='tahunpajaklapor'>";
		}
	    }else{
		returnData+=""
		+ "<h3 class='alert alert-error'><i class='icon icon-warning-sign'></i> Gagal Menyimpan Laporan Pajak</h3>"
		+ "<p>"
		    + "Tidak bisa menyimpan laporan pajak anda pada periode berjalan dan periode kedepan<br>"
		    + "Periode berjalan saat ini adalah <b>"+indoMonth[month]+" "+currentYearCheck+"</b>"
		+ "</p>"+
                "<input type='hidden' name='messageString' value='notok' id='msgString'>"
                + "<input type='hidden' name='npwpdpajaklapor' value='"+npwpdpelapor+"' id='npwpdpajaklapor'>"
                + "<input type='hidden' name='masapajaklapor' value='"+masaPajak+"' id='masapajaklapor'>"
                + "<input type='hidden' name='tahunpajaklapor' value='"+tahunPajak+"' id='tahunpajaklapor'>"        
                + "<input type='hidden' name='rootpro' value='"+approot+"' id='rootpro'>";        
	    }
	    
        
	}else if(iCommand == Command.NONE && npwpdId != null){
	    Vector listNamaBadan = new Vector(1,1);
	    Vector listESPTPD = new Vector (1,1);
            Vector listCompareESPTPD = new Vector(1,1);
            
	    ESPTPD getEsptpd = new ESPTPD();
	    NamaBadan namaBadan = new NamaBadan();
            CompareEsptpd compareEsptpd = new CompareEsptpd();
            double jumlahService=0;
            

	    listNamaBadan = PstNamaBadan.listJoin(0, 0, 
		    "viewUser."+PstAppUserWP.fieldNames[PstAppUserWP.FLD_USER_ID]+"='"+ npwpdId+"'", "");

	    //listESPTPD = PstESPTPD.listJoin(0, 0, 
		   // "viewUser."+PstAppUserWP.fieldNames[PstAppUserWP.FLD_USER_ID]+"='"+ npwpdId+"' "
		    //+ "AND sptpd."+PstESPTPD.fieldNames[PstESPTPD.FLD_MASA_PAJAK]+"='"+masaPajak+"' "
		    //+ "AND sptpd."+PstESPTPD.fieldNames[PstESPTPD.FLD_TAHUN_PAJAK]+"='"+tahunPajak+"'", "");
            listCompareESPTPD = PstCompareEsptpd.listJoin(0, 0, "viewUser."+PstAppUserWP.fieldNames[PstAppUserWP.FLD_USER_ID]+"='"+npwpdId+"' and sptpd."+PstESPTPD.fieldNames[PstESPTPD.FLD_MASA_PAJAK]+"='"+masaPajak+"' and sptpd."+PstESPTPD.fieldNames[PstESPTPD.FLD_TAHUN_PAJAK]+"='"+tahunPajak+"'", "");
	
	    if(listNamaBadan.size() != 0){
		namaBadan = (NamaBadan) listNamaBadan.get(0);
	    }

	    //if(listESPTPD.size() != 0){
		//esptpd = (ESPTPD) listESPTPD.get(0);
	    //}
            if (listCompareESPTPD.size()!=0){
                compareEsptpd = (CompareEsptpd) listCompareESPTPD.get(0);
                
                //add opie-eyek tambahan kolom untuk input service tax
                if(compareEsptpd.getCompareEServiceTax()!=0){
                    jumlahService = compareEsptpd.getCompareEServiceTax() * compareEsptpd.getCompareETarif();
                }else{
                    jumlahService=0;
                }
            }
	    
	    returnData+=
	    
	    "<div class='row'>"
		+ "<div class='100%' style='margin-left:25px;'>"
		    + "<div class='widget'>"
			+ "<div class='widget-header'>"
			    + "<i class='icon icon-info-sign'></i> "
			    + "<h3>INFORMASI NAMA BADAN</h3>"
			+ " </div>"
			+ "<div class='widget-content'>"
			    + "<div class='form-horizontal'>"
				+ "<div class='control-group'>"
				    + "<label class='control-label' for='"+FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_NPWPD]+"'>No NPWPD</label>"
				    + "<div class='controls'>"
					+ "<input type='text' id='"+FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_NPWPD]+"' name='"+FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_NPWPD]+"' class='span4' value='"+namaBadan.getNbNPWPD()+"' readonly='readonly'>"
				    + "</div>"
				+ "</div>"
				+ "<div class='control-group'>"
				    + "<label class='control-label' for='"+FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_NAMA]+"'>Nama Wajib Pajak</label>"
				    + "<div class='controls'>"
					+ "<input type='text' id='"+FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_NAMA]+"' name='"+FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_NAMA]+"' class='span4' value='"+namaBadan.getNbNama()+"' readonly='readonly'>"
				    + "</div>"
				+ "</div>"
				+ "<div class='control-group'>"
				    + "<label class='control-label' for='"+FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_NAMA_BADAN]+"'>Nama Badan</label>"
				    + "<div class='controls'>"
					+ "<input type='text' id='"+FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_NAMA_BADAN]+"' name='"+FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_NAMA_BADAN]+"' class='span4' value='"+namaBadan.getNbNamaBadan()+"' readonly='readonly'>"
				    + "</div>"
				+ "</div>"
				+ "<div class='control-group'>"
				    + "<label class='control-label' for='"+FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_ALAMAT]+"'>Alamat</label>"
				    + "<div class='controls'>"
					+ "<input type='text' id='"+FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_ALAMAT]+"' name='"+FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_ALAMAT]+"' class='span4' value='"+namaBadan.getNbAlamat()+"' readonly='readonly'>"
				    + "</div>"
				+ "</div>"
				+ "<div class='control-group'>"
				    + "<label class='control-label' for='"+FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_KELURAHAN]+"'>Kelurahan</label>"
				    + "<div class='controls'>"
					+ "<input type='text' id='"+FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_KELURAHAN]+"' name='"+FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_KELURAHAN]+"' class='span4'  value='"+namaBadan.getNbKelurahan()+"' readonly='readonly'>"
				    + "</div>"
				+ "</div>"
				+ "<div class='control-group'>"
				    + "<label class='control-label' for='"+FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_KECAMATAN]+"'>Kecamatan</label>"
				    + "<div class='controls'>"
					+ "<input type='text' id='"+FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_KECAMATAN]+"' name='"+FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_KECAMATAN]+"' class='span4' value='"+namaBadan.getNbKecamatan()+"' readonly='readonly'>"
				    + "</div>"
				+ "</div>"
				+ "<div class='control-group'>"
				    + "<label class='control-label' for='"+FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_KABUPATEN]+"'>Kabupaten</label>"
				    + "<div class='controls'>"
					+ "<input type='text' id='"+ FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_KABUPATEN]+"' name='"+FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_KABUPATEN]+"' class='span4' value='"+namaBadan.getNbKabupaten()+"' readonly='readonly'>"
				    + "</div>"
				+ "</div>"
				+ "<div class='control-group'>"
				    + "<label class='control-label' for='"+FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_TGL_REKAM]+"'>Tanggal Rekam</label>"
				    + "<div class='controls'>"
					+ "<input type='text' id='"+FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_TGL_REKAM]+"' name='"+ FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_TGL_REKAM] +"' class='span4' value='"+Formater.formatDate(compareEsptpd.getCompareETglRekam(), "yyyy-MM-dd")+"' readonly='readonly'>"
				    + "</div>"
				+ "</div>"
				+ "<div class='control-group'>"
				    + "<label class='control-label' for='"+ FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_ID_REKAM] +"'>User ID Rekam</label>"
				    + "<div class='controls'>"
					+ "<input type='text' id='"+FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_ID_REKAM]+"' name='"+FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_ID_REKAM]+"' class='span4' value='"+compareEsptpd.getCompareEIdRekam()+"' readonly='readonly'>"
				    + "</div>"
				+ "</div>"
			    + "</div>"
			+ "</div>"
		    + "</div>"
		    + "<div class='widget'>"
			+ "<div class='widget-header'>"
			    + "<i class='icon icon-info-sign'></i>"
			    + "<h3>INFORMASI PENTING</h3>"
			+ "</div>"
			+ "<div class='widget-content'>"
			    + "<div class='form-horizontal'>"
				+ "<div class='control-group'>"
				    + "<label class='control-label' for='"+FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_KODE_JENIS_USAHA]+"'>Pajak</label>"
				    + "<div class='controls'>"
					+ "<input type='text' id='"+FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_KODE_JENIS_USAHA]+"' name='"+FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_KODE_JENIS_USAHA]+"' class='span1' readonly='readonly' value='"+compareEsptpd.getCompareEKodePajak()+"'> &nbsp; "
					+ "<input type='text' id='"+FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_JENIS_USAHA]+"' name='"+FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_JENIS_USAHA]+"' class='span3' readonly='readonly' value='"+namaBadan.getNbJenisUsaha()+"'>"
				    + "</div>"
				+ "</div>" 
				+ "<div class='control-group'>"
				    + "<label class='control-label' for='"+FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_KODE_JENIS_WP]+"'>Rekening</label>"
				    + "<div class='controls'>"
					+ "<input type='text' id='"+FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_KODE_JENIS_WP]+"' name='"+FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_KODE_JENIS_WP]+"' class='span1' readonly='readonly' value='"+compareEsptpd.getCompareENoRekening()+"'> &nbsp; "
					+ "<input type='text' id='"+FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_SUBJENIS_WP]+"' name='"+FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_SUBJENIS_WP]+"' class='span3' readonly='readonly' value='"+compareEsptpd.getCompareENoSubrekening()+"'>"
				    + "</div>"
				+ "</div>"
				+ "<div class='control-group'>"
				    + "<label class='control-label' for='"+FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_KD_LOKASI]+"'>Sub Lokasi</label>"
				    + "<div class='controls'>"
					+ "<input type='text' id='"+FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_KD_LOKASI]+"' name='"+FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_KD_LOKASI]+"' class='span1' value='"+compareEsptpd.getCompareEKodeLokasi()+"' > &nbsp; "
					+ "<input type='text' id='sublokasi' name='sublokasi' class='span3'>"
				    + "</div>"
				+ "</div>"
			    + "</div>"
			+ "</div>"
		    + "</div>"
		    +"</div>"
		+ "<div class='100%' style='margin-left:25px;'>"
		    + "<div class='widget'>"
			+ "<div class='widget-header'>"
			    + "<i class='icon icon-reorder'></i>" 
			    + "<h3>PELAPORAN PAJAK</h3>"
			+ "</div>"
			+ "<div class='widget-content'>"
			    + "<div class='form-horizontal'>"
				+ "<input type='hidden' name='"+FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_NPWPD]+"' value='"+compareEsptpd.getCompareENpwpd()+"'>"
				+ "<input type='hidden' name='command' value='"+Command.SAVE+"'>"
				+ "<input type='hidden' name='"+FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_NIP_REKAM]+"' value='"+compareEsptpd.getCompareENIPRekam()+"'>"
				+ "<input type='hidden' name='"+FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_NIP_UBAH]+"' value='"+namaBadan.getNbNip()+"'>"
				+ "<input type='hidden' name='"+FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_ID_REKAM]+"' value='"+compareEsptpd.getCompareEIdRekam()+"'>"
				+ "<input type='hidden' name='"+FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_ID_UBAH]+"' value='"+namaBadan.getNbUserId()+"'>"   
				+ "<input type='hidden' name='"+FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_KODE_PAJAK]+"' value='"+compareEsptpd.getCompareEKodePajak()+"'>"
				+ "<input type='hidden' name='"+FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_NO_REKENING]+"' value='"+compareEsptpd.getCompareENoRekening()+"'>"
				+ "<input type='hidden' name='"+FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_NO_SUBREKENING]+"' value='"+compareEsptpd.getCompareENoSubrekening()+"'>"
				+ "<input type='hidden' name='"+FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_KODE_LOKASI]+"' id='"+FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_KODE_LOKASI]+"' value='"+compareEsptpd.getCompareEKodeLokasi()+"'>"
				+ "<input type='hidden' name='approot' value='11."+approot+"'>"
				+ "<div class='control-group'>"
				    + "<label class='control-label' for='"+FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_NO_SPTPD]+"'>No PTPD</label>"
				    + "<div class='controls'>"
					+ "<input type='text' id='"+FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_NO_SPTPD]+"' name='"+FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_NO_SPTPD]+"' class='span4' disabled='true' value='"+compareEsptpd.getCompareENoSPTPD()+"'>"
				    + "</div>"
				+ "</div>"

				+ "<div class='control-group' id='target-1'>"

				    + "<label class='control-label' for='"+FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_MASA_PAJAK]+"'>Masa Pajak</label>"
				    + "<div class='controls'>";
					    
					    String monthDisplay = "";
					    if(Integer.parseInt(masaPajak)<10){
						monthDisplay="0"+Integer.parseInt(masaPajak);
					    }else{
						monthDisplay=""+Integer.parseInt(masaPajak);
					    }


					    Vector month_key = new Vector(1,1);
					    Vector month_value = new Vector(1,1);
					    
					    for(int i=0;i<12;i++){
						
						int getDisplayKey = i+1;
						if(Integer.parseInt(masaPajak) == getDisplayKey){
						    if(getDisplayKey<10){
							month_key.add("0"+getDisplayKey);
						    }else{
							month_key.add(""+getDisplayKey);
						    }
						    month_value.add(""+indoMonth[i]);
						}
					    }
					    returnData+=ControlCombo.drawBootsratap(FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_MASA_PAJAK], null, monthDisplay, month_key, month_value, "required='required' readonly", "span4");
				    returnData+=
				    "</div>"
				+ "</div> "

				+ "<div class='control-group' id='target-2'>"

				    + "<label class='control-label' for='"+FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_TAHUN_PAJAK]+"'>Tahun</label>"
				    + "<div class='controls'>";
					    int currentYear = Integer.parseInt(tahunPajak);
					    Vector years_key = new Vector(1,1);
					    Vector years_value = new Vector(1,1);
					    for(int i=currentYear-5;i<=currentYear;i++){
						if(currentYear == i){
						years_key.add(""+i);
						years_value.add(""+i);
						}
					    }
					    
					    returnData+=ControlCombo.drawBootsratap(FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_TAHUN_PAJAK], null, String.valueOf(currentYear), years_key, years_value, "required='required' readonly", "span4");
				    returnData+=""
				    + "</div>"
				+ "</div>"

				+ "<div class='control-group' id='target-3'>"

				    + "<label class='control-label' for='jatuh_tempo'>Tanggal Jatuh Tempo</label>"
				    + "<div class='controls'>"
					+ "<input type='text' id='jatuh_tempo' name='jatuh_tempo' class='span4' readonly='readonly' required='required'>"
				    + "</div>"
				+ "</div>"

				+ "<div class='control-group' id='target-4'>"

				    + "<label class='control-label' for='"+FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_JUMLAM_OMZET]+"'>Jumlah Omset</label>"
				    + "<div class='controls'>"
					+ "<input type='text' id='"+FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_JUMLAM_OMZET]+"' name='"+FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_JUMLAM_OMZET]+"' class='span4 calPajak inputform'  value='"+compareEsptpd.getCompareEJumlahOmzet()+"'>"
				    + "</div>"
				+ "</div>"
                                            
                                //update opie-eyek tambah jumlah service
                               + "<div class='control-group' id='target-5'>"
				    + "<label class='control-label' for='"+FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_JUMLAM_SERVICE]+"'>Jumlah Service</label>"
				    + "<div class='controls'>"
					+ "<input type='text' id='"+FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_JUMLAM_SERVICE]+"' name='"+FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_JUMLAM_SERVICE]+"' class='span4 calPajak inputform'  value='"+Formater.formatNumber(jumlahService, "###.###")+"'>"
				    + "</div>"
				+ "</div>"

				+ "<div class='control-group' id='target-6'>"

				    + "<label class='control-label' for='"+FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_TARIF]+"'>Tarif</label>"
				    + "<div class='controls'>"
					+ "<input type='text' id='"+FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_TARIF]+"' name='"+FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_TARIF]+"' class='span4 calPajak' required='required' value='"+compareEsptpd.getCompareETarif()+"' readonly='readonly'>"
				    + "</div>"
				+ "</div> "

				+ "<div class='control-group' id='target-7'>"

				    + "<label class='control-label' for='"+FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_JUMLAH_PAJAK]+"'>Jumlah Pajak</label>"
				    + "<div class='controls'>"
					+ "<input type='text' id='"+FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_JUMLAH_PAJAK]+"' name='"+FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_JUMLAH_PAJAK]+"' class='span4 calPajak' required='required' value='"+Formater.formatNumber(compareEsptpd.getCompareEJumlahPajak(), "###.###")+"' readonly='readonly'>"
				    + "</div>"
				+ "</div>"

				+ "<div class='control-group' id='target-8'>"

				    + "<label class='control-label' for='"+FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_SERVICE_TAX]+"'>Service Tax</label>"
				    + "<div class='controls'>"
					+ "<input type='text' id='"+FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_SERVICE_TAX]+"' name='"+FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_SERVICE_TAX]+"' class='span4 calPajak' required='required' value='"+Formater.formatNumber(compareEsptpd.getCompareEServiceTax(), "###.###")+"' readonly='readonly' >"
				    + "</div>"
				+ "</div>"

				+ "<div class='control-group' id='target-9'>"

				    + "<label class='control-label' for='"+FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_HARUS_DIBAYAR]+"'>Harus Dibayar</label>"
				    + "<div class='controls'>"
					+ "<input type='text' id='"+FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_HARUS_DIBAYAR]+"' name='"+FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_HARUS_DIBAYAR]+"' class='span4' required='required' value='"+Formater.formatNumber(compareEsptpd.getCompareEHarusBayar(), "###.###")+"' readonly='readonly'>"
				    + "</div>"
				+ "</div>"

				+ "<div class='control-group' id='target-10'>"

				    + "<label class='control-label' for='"+FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_KETERANGAN]+"'>Keterangan</label>"
				    + "<div class='controls'>"
					+ "<textarea id='"+FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_KETERANGAN]+"' name='"+FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_KETERANGAN]+"' class='span4 inputform' >"+compareEsptpd.getCompareEKeterangan()+"</textarea>"
				    + "</div>"
				+ "</div>"
				

			    + "</div>"
			+ "</div>"

		   + "</div>"

		+ "</div>"

	    + "</div>";
	}
	response.getWriter().println(returnData);
    }
    public String drawList(Vector objectClass, String approot) {
        //2
        ControlList ctrlist = new ControlList();
        
        ctrlist.addHeader("NPWPD","10%");
        ctrlist.addHeader("Masa Pajak", "10%");
        ctrlist.addHeader("Tahun Pajak", "10%");
        ctrlist.addHeader("Jumlah Omset", "10%");
        ctrlist.addHeader("Tarif", "10%");
	ctrlist.addHeader("Jumlah Pajak","10%");
	ctrlist.addHeader("Service Tax","10%");
        ctrlist.addHeader("Denda","10%");
	ctrlist.addHeader("Pengurangan","10%");
	ctrlist.addHeader("Harus Diabayar","10%");
	ctrlist.addHeader("Keterangan","10%");
	
        ctrlist.setLinkSufix("");
        Vector lstData = ctrlist.getData();
        Vector lstLinkData = ctrlist.getLinkData();
	String[] indoMonth = {
	    "Januari",
	    "Februari",
	    "Maret",
	    "April",
	    "Mei",
	    "Juni",
	    "Juli",
	    "Agustus",
	    "September",
	    "Oktober",
	    "November",
	    "Desember"
	};
	Calendar cal = Calendar.getInstance();
	cal.setTime(new Date());
	int month = cal.get(Calendar.MONTH);
        //membuat link menuju ke edit
        //ctrlist.setLinkPrefix("javascript:cmdEdit('");
        //ctrlist.setLinkSufix("')");
        ctrlist.reset();
        
        int index = -1;
        DecimalFormat numberFormat = (DecimalFormat) DecimalFormat.getCurrencyInstance();
	DecimalFormatSymbols numberFormatSymbol = new DecimalFormatSymbols();
	
	numberFormatSymbol.setCurrencySymbol("Rp. ");
	numberFormatSymbol.setMonetaryDecimalSeparator('.');
	numberFormatSymbol.setGroupingSeparator(',');
	
	numberFormat.setDecimalFormatSymbols(numberFormatSymbol);
        Vector rowx = new Vector(1,1);
        for(int i = 0; i < objectClass.size(); i++) {
            ESPTPD esptpd = (ESPTPD) objectClass.get(i);
            
            rowx = new Vector(1,1);
	    rowx.add(""+esptpd.getENPWPD());
            
	    rowx.add(""+indoMonth[Integer.parseInt(esptpd.getEMasaPajak())-1]);
            rowx.add(""+esptpd.getETahunPajak());
	    rowx.add(""+numberFormat.format(Double.parseDouble(esptpd.getEJumlahOmzet())));
	    rowx.add(""+esptpd.getETarif());
	    rowx.add(""+numberFormat.format(esptpd.getEJumlahPajak()));
	    rowx.add(""+numberFormat.format(esptpd.getEServiceTax()));
	    rowx.add(""+numberFormat.format(esptpd.getEDenda()));
	    rowx.add(""+numberFormat.format(esptpd.getEPengurangan()));
	    rowx.add(""+numberFormat.format(esptpd.getEHarusBayar()));
	    rowx.add(""+esptpd.getEKeterangan());
            
	    lstData.add(rowx);
        }
        
        
        return ctrlist.drawBootstrapStrip();
    }
    
    public String drawListOnline(Vector objectClass, String approot) {
        //2
        ControlList ctrlist = new ControlList();
        
        ctrlist.addHeader("NPWPD","10%");
        ctrlist.addHeader("Tanggal", "10%");
        ctrlist.addHeader("Masa Pajak", "10%");
        ctrlist.addHeader("Tahun Pajak", "10%");
        ctrlist.addHeader("Pokok", "10%");
	ctrlist.addHeader("Denda","10%");
	ctrlist.addHeader("Jumlah","10%");
	
        ctrlist.setLinkSufix("");
        Vector lstData = ctrlist.getData();
        Vector lstLinkData = ctrlist.getLinkData();
	String[] indoMonth = {
	    "Januari",
	    "Februari",
	    "Maret",
	    "April",
	    "Mei",
	    "Juni",
	    "Juli",
	    "Agustus",
	    "September",
	    "Oktober",
	    "November",
	    "Desember"
	};
	Calendar cal = Calendar.getInstance();
	cal.setTime(new Date());
	int month = cal.get(Calendar.MONTH);
        //membuat link menuju ke edit
        //ctrlist.setLinkPrefix("javascript:cmdEdit('");
        //ctrlist.setLinkSufix("')");
        ctrlist.reset();
        
        int index = -1;
        DecimalFormat numberFormat = (DecimalFormat) DecimalFormat.getCurrencyInstance();
	DecimalFormatSymbols numberFormatSymbol = new DecimalFormatSymbols();
	
	numberFormatSymbol.setCurrencySymbol("Rp. ");
	numberFormatSymbol.setMonetaryDecimalSeparator('.');
	numberFormatSymbol.setGroupingSeparator(',');
	
	numberFormat.setDecimalFormatSymbols(numberFormatSymbol);
        Vector rowx = new Vector(1,1);
        for(int i = 0; i < objectClass.size(); i++) {
            PaymentPhr paymentPhr = (PaymentPhr) objectClass.get(i);
            
            rowx = new Vector(1,1);
	    rowx.add(""+paymentPhr.getNpwpd());
            rowx.add(""+paymentPhr.getTanggal());
	    rowx.add(""+indoMonth[Integer.parseInt(paymentPhr.getMasaPajak())-1]);
            rowx.add(""+paymentPhr.getTahunPajak());
	    rowx.add(""+numberFormat.format(paymentPhr.getPokok()));
	    rowx.add(""+numberFormat.format(paymentPhr.getDenda()));
	    rowx.add(""+numberFormat.format(paymentPhr.getJumlahBayar()));
            
	    lstData.add(rowx);
        }
        
        
        return ctrlist.drawBootstrapStrip();
    }
    
    public String drawListCompare(Vector objectClass, String approot) {
        //2
        ControlList ctrlist = new ControlList();
        
        ctrlist.addHeader("NPWPD","10%");
        ctrlist.addHeader("Masa Pajak", "10%");
        ctrlist.addHeader("Tahun Pajak", "10%");
        ctrlist.addHeader("Jumlah Omset", "10%");
        ctrlist.addHeader("Tarif", "10%");
	ctrlist.addHeader("Jumlah Pajak","10%");
	ctrlist.addHeader("Service Tax","10%");
        ctrlist.addHeader("Denda","10%");
	ctrlist.addHeader("Pengurangan","10%");
	ctrlist.addHeader("Harus Diabayar","10%");
	ctrlist.addHeader("Keterangan","10%");
	ctrlist.addHeader("Aksi","10%");
	
        ctrlist.setLinkSufix("");
        Vector lstData = ctrlist.getData();
        Vector lstLinkData = ctrlist.getLinkData();
	String[] indoMonth = {
	    "Januari",
	    "Februari",
	    "Maret",
	    "April",
	    "Mei",
	    "Juni",
	    "Juli",
	    "Agustus",
	    "September",
	    "Oktober",
	    "November",
	    "Desember"
	};
	Calendar cal = Calendar.getInstance();
	cal.setTime(new Date());
	int month = cal.get(Calendar.MONTH);
        //membuat link menuju ke edit
        //ctrlist.setLinkPrefix("javascript:cmdEdit('");
        //ctrlist.setLinkSufix("')");
        ctrlist.reset();
        
        int index = -1;
        DecimalFormat numberFormat = (DecimalFormat) DecimalFormat.getCurrencyInstance();
	DecimalFormatSymbols numberFormatSymbol = new DecimalFormatSymbols();
	
	numberFormatSymbol.setCurrencySymbol("Rp. ");
	numberFormatSymbol.setMonetaryDecimalSeparator('.');
	numberFormatSymbol.setGroupingSeparator(',');
	
	numberFormat.setDecimalFormatSymbols(numberFormatSymbol);
        Vector rowx = new Vector(1,1);
        for(int i = 0; i < objectClass.size(); i++) {
            CompareEsptpd compareEsptpd = (CompareEsptpd) objectClass.get(i);
            
            rowx = new Vector(1,1);
	    rowx.add(""+compareEsptpd.getCompareENpwpd());
            
	    rowx.add(""+indoMonth[Integer.parseInt(compareEsptpd.getCompareEMasaPajak())-1]);
            rowx.add(""+compareEsptpd.getCompareETahunPajak());
	    rowx.add(""+numberFormat.format(Double.parseDouble(compareEsptpd.getCompareEJumlahOmzet())));
	    rowx.add(""+compareEsptpd.getCompareETarif());
	    rowx.add(""+numberFormat.format(compareEsptpd.getCompareEJumlahPajak()));
	    rowx.add(""+numberFormat.format(compareEsptpd.getCompareEServiceTax()));
	    rowx.add(""+numberFormat.format(compareEsptpd.getCompareEDenda()));
	    rowx.add(""+numberFormat.format(compareEsptpd.getCompareEPengurangan()));
	    rowx.add(""+numberFormat.format(compareEsptpd.getCompareEHarusBayar()));
            rowx.add("Status");
	    rowx.add(""+compareEsptpd.getCompareEKeterangan());
	    String datalampiran= "<div class=\"dropdown\">\n" 
                    + "<button class=\"btn btn-success dropdown-toggle\" type=\"button\" id=\"menu1\" data-toggle=\"dropdown\">Action\n" 
                    + "</button>\n" 
                    + "<ul class=\"dropdown-menu \" role=\"menu\" aria-labelledby=\"menu1\" style=\"position: absolute;top:-10%;margin-left:-200%\">\n"
                    + "<li class='editLaporan' data-masa-pajak='"+compareEsptpd.getCompareEMasaPajak()+"'"
                    + "data-tahun-pajak='"+compareEsptpd.getCompareETahunPajak()+"' "
                    + "data-npwpd='"+compareEsptpd.getCompareENpwpd()+"' "
                    + "data-approot='"+approot+"'"
                    + "role=\"presentation\"><a role=\"menuitem\" tabindex=\"-1\" href=\"#\">"
                    + "<i class=\"icon icon-pencil\"></i> Edit</a></li>\n" 
                    + "<li class=\"divider\"></li>\n" 
                    + "<li class='printLaporan' data-masa-pajak='"+compareEsptpd.getCompareEMasaPajak()+"'"
                    + "data-tahun-pajak='"+compareEsptpd.getCompareETahunPajak()+"' "
                    + "data-npwpd='"+compareEsptpd.getCompareENpwpd()+"' "
                    + "data-approot='"+approot+"'"
                    + "data-lampiran='1'"
                    + "role=\"presentation\"><a role=\"menuitem\" tabindex=\"-1\" href=\"#\">"
                    + "<i class=\"icon icon-print\"></i> Cetak Lampiran 1</a></li>\n" ;
                            
                    if(AppSetting.LAMPIRAN_PRINT==AppSetting.DISPENDA_GIANYAR){
                        datalampiran=datalampiran
                    + "<li class='printLaporan' data-masa-pajak='"+compareEsptpd.getCompareEMasaPajak()+"'"
                    + "data-tahun-pajak='"+compareEsptpd.getCompareETahunPajak()+"' "
                    + "data-npwpd='"+compareEsptpd.getCompareENpwpd()+"' "
                    + "data-approot='"+approot+"'"
                    + "data-lampiran='2'"
                    + "role=\"presentation\"><a role=\"menuitem\" tabindex=\"-1\" href=\"#\">"
                    + "<i class=\"icon icon-print\"></i> Cetak Lampiran 2</a></li>\n" 

                    + "<li class='printLaporan' data-masa-pajak='"+compareEsptpd.getCompareEMasaPajak()+"'"
                    + "data-tahun-pajak='"+compareEsptpd.getCompareETahunPajak()+"' "
                    + "data-npwpd='"+compareEsptpd.getCompareENpwpd()+"' "
                    + "data-approot='"+approot+"'"
                    + "data-lampiran='3'"
                    + "role=\"presentation\"><a role=\"menuitem\" tabindex=\"-1\" href=\"#\">"
                    + "<i class=\"icon icon-print\"></i> Cetak Lampiran 3</a></li>\n" 
                    + "<li class='printLaporan' data-masa-pajak='"+compareEsptpd.getCompareEMasaPajak()+"'"
                    + "data-tahun-pajak='"+compareEsptpd.getCompareETahunPajak()+"' "
                    + "data-npwpd='"+compareEsptpd.getCompareENpwpd()+"' "
                    + "data-approot='"+approot+"'"
                    + "data-lampiran='4'"
                    + "role=\"presentation\"><a role=\"menuitem\" tabindex=\"-1\" href=\"#\">"
                    + "<i class=\"icon icon-print\"></i> Cetak Lampiran 4</a></li>\n" 
                    + "<li class='printLaporan' data-masa-pajak='"+compareEsptpd.getCompareEMasaPajak()+"'"
                    + "data-tahun-pajak='"+compareEsptpd.getCompareETahunPajak()+"' "
                    + "data-npwpd='"+compareEsptpd.getCompareENpwpd()+"' "
                    + "data-approot='"+approot+"'"
                    + "data-lampiran='5'"
                    + "role=\"presentation\"><a role=\"menuitem\" tabindex=\"-1\" href=\"#\">"
                    + "<i class=\"icon icon-print\"></i> Cetak Lampiran 5</a></li>\n" 
                    + "<li class='printLaporan' data-masa-pajak='"+compareEsptpd.getCompareEMasaPajak()+"'"
                    + "data-tahun-pajak='"+compareEsptpd.getCompareETahunPajak()+"' "
                    + "data-npwpd='"+compareEsptpd.getCompareENpwpd()+"' "
                    + "data-approot='"+approot+"'"
                    + "data-lampiran='6'"
                    + "role=\"presentation\"><a role=\"menuitem\" tabindex=\"-1\" href=\"#\">"
                    + "<i class=\"icon icon-print\"></i> Cetak Lampiran 6</a></li>\n" 
                    + "<li class='printLaporan' data-masa-pajak='"+compareEsptpd.getCompareEMasaPajak()+"'"
                    + "data-tahun-pajak='"+compareEsptpd.getCompareETahunPajak()+"' "
                    + "data-npwpd='"+compareEsptpd.getCompareENpwpd()+"' "
                    + "data-approot='"+approot+"'"
                    + "data-lampiran='7'"
                    + "role=\"presentation\"><a role=\"menuitem\" tabindex=\"-1\" href=\"#\">"
                    + "<i class=\"icon icon-print\"></i> Cetak Lampiran 7</a></li>\n" 

                    + "</ul>\n" 
                                    + "</div>";
                    } 
            rowx.add(""+datalampiran);
	    lstData.add(rowx);
        }
        
        
        return ctrlist.drawBootstrapStrip();
    }
    
    public JSONObject listCompareESPTPD (HttpServletRequest request, HttpServletResponse response,String user){
        JSONObject result = new JSONObject();
       
        String[] cols = {""+PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_NPWPD]+"", 
                         ""+PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_MASA_PAJAK]+"", 
                         ""+PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_TAHUN_PAJAK]+"", 
                         ""+PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_JUMLAH_OMZET]+"", 
                         ""+PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_TARIF]+"",
                         ""+PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_JUMLAH_PAJAK]+"",
                         ""+PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_SERVICE_TAX]+"",
                         ""+PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_DENDA]+"",
                         ""+PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_PENGURANGAN]+"",
                         ""+PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_HARUS_DIBAYAR]+"",
                         ""+PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_KETERANGAN]+""};
    
        
        int amount = 10;
        int start = 0;
        int col = 0;
        String dir = "asc";
        String sStart = request.getParameter("iDisplayStart");
        String sAmount = request.getParameter("iDisplayLength");
        String sCol = request.getParameter("iSortCol_0");
        String sdir = request.getParameter("sSortDir_0");
        
        if (sStart != null) {
            start = Integer.parseInt(sStart);
            if (start < 0) {
                start = 0;
            }
        }
        if (sAmount != null) {
            amount = Integer.parseInt(sAmount);
            if (amount < 10) {
                amount = 10;
            }
        }
        if (sCol != null) {
            col = Integer.parseInt(sCol);
            if (col < 0)
                col = 0;
        }
        if (sdir != null) {
            if (!sdir.equals("asc"))
            dir = "desc";
        }
        String whereClause = "viewUser."+PstAppUserWP.fieldNames[PstAppUserWP.FLD_USER_ID]+"='"+user+"'";
        
        Vector listCompareESPTPD = PstCompareEsptpd.listJoin(0, 0, "viewUser."+PstAppUserWP.fieldNames[PstAppUserWP.FLD_USER_ID]+"='"+user+"'", "");
        
        listCompareESPTPD = PstCompareEsptpd.listJoinDenda(0, 0, "viewUser."+PstAppUserWP.fieldNames[PstAppUserWP.FLD_USER_ID]+"='"+user+"'", "",listCompareESPTPD);
        
        listCompareESPTPD = PstCompareEsptpd.listJoinESPTPDNOL(0, 0, "viewUser."+PstAppUserWP.fieldNames[PstAppUserWP.FLD_USER_ID]+"='"+user+"' AND sptpd.JUMLAH_OMZET=0 AND sptpd.JUMLAH_PAJAK=0 ", "",listCompareESPTPD);
        
        int total = -1;
        total =listCompareESPTPD.size();
        String colName = cols[col];
        this.amount = amount;
        this.searchTerm = request.getParameter("sSearch");
        this.colName = colName;
        this.dir = dir;
        this.start = start;
        this.colOrder = col;
        
        try {
            result = getDataCompareESPTPD(total, request,user);
        } catch(Exception ex){
            System.out.println(ex);
        }
       
       return result;
    }
    
    public JSONObject getDataCompareESPTPD(int total, HttpServletRequest request,String user) throws JSONException{
        
        int totalAfterFilter = total;
        JSONObject result = new JSONObject();
        JSONArray array = new JSONArray();
        String approot = request.getParameter("approot");
        CompareEsptpd compareEsptpd = new CompareEsptpd();
        String order ="";
        
        //format decimal
      
        String pattern = "###,###.###";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);

        
        
        String whereClause = "viewUser."+PstAppUserWP.fieldNames[PstAppUserWP.FLD_USER_ID]+"='"+user+"'";
        
        if (this.searchTerm == null){
            whereClause += "";                  
        }else{
            whereClause +=" and(";
            whereClause +=" sptpd."+PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_NPWPD]+" like '%"+this.searchTerm+"%'";
            whereClause +=" or sptpd."+PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_MASA_PAJAK]+" like '%"+this.searchTerm+"%'";
            whereClause +=" or sptpd."+PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_TAHUN_PAJAK]+" like '%"+this.searchTerm+"%'";
            whereClause +=" or sptpd."+PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_JUMLAH_OMZET]+" like '%"+this.searchTerm+"%'";
            whereClause +=" or sptpd."+PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_TARIF]+" like '%"+this.searchTerm+"%'";
            whereClause +=" or sptpd."+PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_JUMLAH_PAJAK]+" like '%"+this.searchTerm+"%'";
            whereClause +=" or sptpd."+PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_SERVICE_TAX]+" like '%"+this.searchTerm+"%'";
            whereClause +=" or sptpd."+PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_DENDA]+" like '%"+this.searchTerm+"%'";
            whereClause +=" or sptpd."+PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_PENGURANGAN]+" like '%"+this.searchTerm+"%'";
            whereClause +=" or sptpd."+PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_HARUS_DIBAYAR]+" like '%"+this.searchTerm+"%'";
            whereClause +=" or sptpd."+PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_KETERANGAN]+" like '%"+this.searchTerm+"%'";
            whereClause += ")";
        }
        
        if (this.colOrder>=0){
            order += "sptpd."+colName+" "+dir+"";
        }
        
        Vector listCompareESPTPD = PstCompareEsptpd.listJoin(start, amount,whereClause,order);
        listCompareESPTPD = PstCompareEsptpd.listJoinDenda(start, amount,whereClause,order,listCompareESPTPD);
        listCompareESPTPD = PstCompareEsptpd.listJoinESPTPDNOL(0, 0, "viewUser."+PstAppUserWP.fieldNames[PstAppUserWP.FLD_USER_ID]+"='"+user+"' AND sptpd.JUMLAH_OMZET=0 AND sptpd.JUMLAH_PAJAK=0 ", "",listCompareESPTPD);
        String button= new String();
        
        for (int i =0 ; i<=listCompareESPTPD.size()-1;i++){
            compareEsptpd = (CompareEsptpd) listCompareESPTPD.get(i);
            JSONArray ja = new JSONArray();
            
            
            button ="";
            ja.put(compareEsptpd.getCompareENpwpd());
            ja.put(indoMonth[Integer.parseInt(compareEsptpd.getCompareEMasaPajak())-1]);
            ja.put(compareEsptpd.getCompareETahunPajak());
            ja.put("Rp. "+decimalFormat.format(Double.parseDouble(compareEsptpd.getCompareEJumlahOmzet())));
            ja.put(compareEsptpd.getCompareETarif());
            ja.put("Rp. "+decimalFormat.format(compareEsptpd.getCompareEJumlahPajak()));
            ja.put("Rp. "+decimalFormat.format(compareEsptpd.getCompareEServiceTax()));
            ja.put("Rp. "+decimalFormat.format(compareEsptpd.getCompareEDenda()));
            ja.put("Rp. "+decimalFormat.format(compareEsptpd.getCompareEPengurangan()));
            ja.put("Rp. "+decimalFormat.format(compareEsptpd.getCompareEHarusBayar()));
            
            ja.put(""+compareEsptpd.getNamaDocumentUpload());
            
            String id = compareEsptpd.getCompareENpwpd().replace(".", "");
            boolean transferDataBank = PstESPTPD.getCountHistory(id, compareEsptpd.getCompareETahunPajak(),compareEsptpd.getCompareEMasaPajak());
            if(transferDataBank){
                ja.put("Data Sudah di Bank");
            }else{
                ja.put("Dalam Proses");
            }
            
            if(compareEsptpd.getCompareEDenda()==0){
                button += "<div class=\"dropdown\">\n" 
                    + "<button class=\"btn btn-success dropdown-toggle\" type=\"button\" id=\"menu1\" data-toggle=\"dropdown\">Action\n" 
                    + "</button>\n" 
                    + "<ul class=\"dropdown-menu \" role=\"menu\" aria-labelledby=\"menu1\" style=\"position: absolute;top:-10%;margin-left:-200%\">\n"
//                    + "<li class='editLaporan' data-masa-pajak='"+compareEsptpd.getCompareEMasaPajak()+"'"
//                    + "data-tahun-pajak='"+compareEsptpd.getCompareETahunPajak()+"' "
//                    + "data-npwpd='"+compareEsptpd.getCompareENpwpd()+"' "
//                    + "data-approot='"+approot+"'"
//                    + "role=\"presentation\"><a role=\"menuitem\" tabindex=\"-1\" style='cursor:pointer'>"
//                    + "<i class=\"icon icon-pencil\"></i> Edit</a></li>\n" 
                        
                    + "<li class='uploadDocument' data-masa-pajak='"+compareEsptpd.getCompareEMasaPajak()+"'"
                    + "data-tahun-pajak='"+compareEsptpd.getCompareETahunPajak()+"' "
                    + "data-npwpd='"+compareEsptpd.getCompareENpwpd()+"' "
                    + "data-approot='"+approot+"'"
                    + "role=\"presentation\"><a role=\"menuitem\" tabindex=\"-1\" style='cursor:pointer'>"
                    + "<i class=\"icon icon-upload\"></i> Upload Document</a></li>\n" 
                    + "<li class=\"divider\"></li>\n" 
                    + "<li class='printLaporan' data-masa-pajak='"+compareEsptpd.getCompareEMasaPajak()+"'"
                    + "data-tahun-pajak='"+compareEsptpd.getCompareETahunPajak()+"' "
                    + "data-npwpd='"+compareEsptpd.getCompareENpwpd()+"' "
                    + "data-nosptpd='"+compareEsptpd.getCompareENoSPTPD()+"' "
                    + "data-approot='"+approot+"'"
                    + "data-lampiran='1'"
                    + "role=\"presentation\"><a role=\"menuitem\" tabindex=\"-1\" style='cursor:pointer'>"
                    + "<i class=\"icon icon-print\"></i> Cetak Lampiran 1</a></li>\n" ;
            }
            
            
            if(AppSetting.LAMPIRAN_PRINT==AppSetting.DISPENDA_GIANYAR){
                button=button
                    + "<li class='printLaporan' data-masa-pajak='"+compareEsptpd.getCompareEMasaPajak()+"'"
                    + "data-tahun-pajak='"+compareEsptpd.getCompareETahunPajak()+"' "
                    + "data-npwpd='"+compareEsptpd.getCompareENpwpd()+"' "
                    + "data-approot='"+approot+"'"
                    + "data-lampiran='2'"
                    + "role=\"presentation\"><a role=\"menuitem\" tabindex=\"-1\" style='cursor:pointer'>"
                    + "<i class=\"icon icon-print\"></i> Cetak Lampiran 2</a></li>\n" 
                    + "<li class='printLaporan' data-masa-pajak='"+compareEsptpd.getCompareEMasaPajak()+"'"
                    + "data-tahun-pajak='"+compareEsptpd.getCompareETahunPajak()+"' "
                    + "data-npwpd='"+compareEsptpd.getCompareENpwpd()+"' "
                    + "data-approot='"+approot+"'"
                    + "data-lampiran='3'"
                    + "role=\"presentation\"><a role=\"menuitem\" tabindex=\"-1\" style='cursor:pointer'>"
                    + "<i class=\"icon icon-print\"></i> Cetak Lampiran 3</a></li>\n" 
                    + "<li class='printLaporan' data-masa-pajak='"+compareEsptpd.getCompareEMasaPajak()+"'"
                    + "data-tahun-pajak='"+compareEsptpd.getCompareETahunPajak()+"' "
                    + "data-npwpd='"+compareEsptpd.getCompareENpwpd()+"' "
                    + "data-approot='"+approot+"'"
                    + "data-lampiran='4'"
                    + "role=\"presentation\"><a role=\"menuitem\" tabindex=\"-1\" style='cursor:pointer'>"
                    + "<i class=\"icon icon-print\"></i> Cetak Lampiran 4</a></li>\n" 
                    + "<li class='printLaporan' data-masa-pajak='"+compareEsptpd.getCompareEMasaPajak()+"'"
                    + "data-tahun-pajak='"+compareEsptpd.getCompareETahunPajak()+"' "
                    + "data-npwpd='"+compareEsptpd.getCompareENpwpd()+"' "
                    + "data-approot='"+approot+"'"
                    + "data-lampiran='5'"
                    + "role=\"presentation\"><a role=\"menuitem\" tabindex=\"-1\" style='cursor:pointer'>"
                    + "<i class=\"icon icon-print\"></i> Cetak Lampiran 5</a></li>\n" 
                    + "<li class='printLaporan' data-masa-pajak='"+compareEsptpd.getCompareEMasaPajak()+"'"
                    + "data-tahun-pajak='"+compareEsptpd.getCompareETahunPajak()+"' "
                    + "data-npwpd='"+compareEsptpd.getCompareENpwpd()+"' "
                    + "data-approot='"+approot+"'"
                    + "data-lampiran='6'"
                    + "role=\"presentation\"><a role=\"menuitem\" tabindex=\"-1\" style='cursor:pointer'>"
                    + "<i class=\"icon icon-print\"></i> Cetak Lampiran 6</a></li>\n" 
                    + "<li class='printLaporan' data-masa-pajak='"+compareEsptpd.getCompareEMasaPajak()+"'"
                    + "data-tahun-pajak='"+compareEsptpd.getCompareETahunPajak()+"' "
                    + "data-npwpd='"+compareEsptpd.getCompareENpwpd()+"' "
                    + "data-approot='"+approot+"'"
                    + "data-lampiran='7'"
                    + "role=\"presentation\"><a role=\"menuitem\" tabindex=\"-1\" style='cursor:pointer'>"
                    + "<i class=\"icon icon-print\"></i> Cetak Lampiran 7</a></li>\n" 
                    + "</ul>\n" 
                    + "</div>";
            }
            ja.put(button);
            array.put(ja);
         
        }
        
        totalAfterFilter = total;
        
        try {
            result.put("iTotalRecords", total);
            result.put("iTotalDisplayRecords", totalAfterFilter);
            result.put("aaData", array);
        } catch (Exception e) {

        }
        
        return result;
    }
    
    public JSONObject listViewAllSimpatda (HttpServletRequest request, HttpServletResponse response,String user){
        JSONObject result = new JSONObject();
       
        String[] cols = {""+PstViewAllSimpatda.fieldNames[PstViewAllSimpatda.FLD_NPWPD]+"", 
                         ""+PstViewAllSimpatda.fieldNames[PstViewAllSimpatda.FLD_MASA_PAJAK]+"", 
                         ""+PstViewAllSimpatda.fieldNames[PstViewAllSimpatda.FLD_TAHUN_PAJAK]+"", 
                         ""+PstViewAllSimpatda.fieldNames[PstViewAllSimpatda.FLD_NAMA]+"", 
                         ""+PstViewAllSimpatda.fieldNames[PstViewAllSimpatda.FLD_ALAMAT]+"",
                         ""+PstViewAllSimpatda.fieldNames[PstViewAllSimpatda.FLD_POKOK]+"",
                         ""+PstViewAllSimpatda.fieldNames[PstViewAllSimpatda.FLD_DENDA]+"",
                         ""+PstViewAllSimpatda.fieldNames[PstViewAllSimpatda.FLD_JUMLAH]+"",
                         ""+PstViewAllSimpatda.fieldNames[PstViewAllSimpatda.FLD_INSTANSI]+"",
                         "ACTION"};
      
        int amount = 10;
        int start = 0;
        int col = 0;
        String dir = "asc";
        String sStart = request.getParameter("iDisplayStart");
        String sAmount = request.getParameter("iDisplayLength");
        String sCol = request.getParameter("iSortCol_0");
        String sdir = request.getParameter("sSortDir_0");
        
        if (sStart != null) {
            start = Integer.parseInt(sStart);
            if (start < 0) {
                start = 0;
            }
        }
        if (sAmount != null) {
            amount = Integer.parseInt(sAmount);
            if (amount < 10) {
                amount = 10;
            }
        }
        if (sCol != null) {
            col = Integer.parseInt(sCol);
            if (col < 0)
                col = 0;
        }
        if (sdir != null) {
            if (!sdir.equals("asc"))
            dir = "desc";
        }
        String whereClause = ""+PstAppUserWP.TBL_APPUSERWP+"."+PstAppUserWP.fieldNames[PstAppUserWP.FLD_USER_ID]+"='"+user+"'";
      
        int total = -1;
        Vector listViewAllSimpatda = PstViewAllSimpatda.listJoin(0,0, whereClause, "");
        total =listViewAllSimpatda.size();
        String colName = cols[col];
        this.amount = amount;
        this.searchTerm = request.getParameter("sSearch");
        this.colName = colName;
        this.dir = dir;
        this.start = start;
        this.colOrder = col;
        
        try {
            result = getDataViewAllSimpatda(total, request,user);
        } catch(Exception ex){
            System.out.println(ex);
        }
       
       return result;
    }
    
    public JSONObject getDataViewAllSimpatda(int total, HttpServletRequest request,String user) throws JSONException{
        
        int totalAfterFilter = total;
        JSONObject result = new JSONObject();
        JSONArray array = new JSONArray();
        String approot = request.getParameter("approot");
        ViewAllSimpatda viewAllSimpatda = new ViewAllSimpatda();
        String order ="";
        
        //format decimal
      
        String pattern = "###,###.###";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);

        String whereClause = ""+PstAppUserWP.TBL_APPUSERWP+"."+PstAppUserWP.fieldNames[PstAppUserWP.FLD_USER_ID]+"='"+user+"'";
        
        if (this.searchTerm == null){
            whereClause += "";                  
        }else{
            whereClause +=" and(";
            whereClause +=" "+PstViewAllSimpatda.TBL_VIEW_ALL_SIMPATDA+"."+PstViewAllSimpatda.fieldNames[PstViewAllSimpatda.FLD_MASA_PAJAK]+" like '%"+this.searchTerm+"%'";
            whereClause +=" or "+PstViewAllSimpatda.TBL_VIEW_ALL_SIMPATDA+"."+PstViewAllSimpatda.fieldNames[PstViewAllSimpatda.FLD_TAHUN_PAJAK]+" like '%"+this.searchTerm+"%'";
            whereClause +=" or "+PstViewAllSimpatda.TBL_VIEW_ALL_SIMPATDA+"."+PstViewAllSimpatda.fieldNames[PstViewAllSimpatda.FLD_NAMA]+" like '%"+this.searchTerm+"%'";
            whereClause +=" or "+PstViewAllSimpatda.TBL_VIEW_ALL_SIMPATDA+"."+PstViewAllSimpatda.fieldNames[PstViewAllSimpatda.FLD_ALAMAT]+" like '%"+this.searchTerm+"%'";
            whereClause +=" or "+PstViewAllSimpatda.TBL_VIEW_ALL_SIMPATDA+"."+PstViewAllSimpatda.fieldNames[PstViewAllSimpatda.FLD_POKOK]+" like '%"+this.searchTerm+"%'";
            whereClause +=" or "+PstViewAllSimpatda.TBL_VIEW_ALL_SIMPATDA+"."+PstViewAllSimpatda.fieldNames[PstViewAllSimpatda.FLD_DENDA]+" like '%"+this.searchTerm+"%'";
            whereClause +=" or "+PstViewAllSimpatda.TBL_VIEW_ALL_SIMPATDA+"."+PstViewAllSimpatda.fieldNames[PstViewAllSimpatda.FLD_JUMLAH]+" like '%"+this.searchTerm+"%'";
            whereClause +=" or "+PstViewAllSimpatda.TBL_VIEW_ALL_SIMPATDA+"."+PstViewAllSimpatda.fieldNames[PstViewAllSimpatda.FLD_INSTANSI]+" like '%"+this.searchTerm+"%'";
            whereClause += ")";
        }
        
        if (this.colOrder>=0){
            order += ""+PstViewAllSimpatda.TBL_VIEW_ALL_SIMPATDA+"."+colName+" "+dir+"";
        }
        
        Vector listViewAllSimpatda = PstViewAllSimpatda.listJoin(start, amount,whereClause,order);
        
        for (int i =0 ; i<=listViewAllSimpatda.size()-1;i++){
            viewAllSimpatda = (ViewAllSimpatda) listViewAllSimpatda.get(i);
            JSONArray ja = new JSONArray();
            
            try{
            ja.put(viewAllSimpatda.getNpwpd());
                if(Integer.parseInt(viewAllSimpatda.getMasaPajak())==0){
                    viewAllSimpatda.setMasaPajak("0");
                }
            ja.put(indoMonth[Integer.parseInt(viewAllSimpatda.getMasaPajak())-1]);
            ja.put(viewAllSimpatda.getTahunPajak());
            ja.put(viewAllSimpatda.getNama());
            ja.put(viewAllSimpatda.getAlamat());
            ja.put("Rp. "+decimalFormat.format(viewAllSimpatda.getPokok()));
            ja.put("Rp. "+decimalFormat.format(viewAllSimpatda.getDenda()));
            ja.put("Rp. "+decimalFormat.format(viewAllSimpatda.getJumlah()));
            
            //ja.put(viewAllSimpatda.getInstansi());
            boolean isEsptpd = PstESPTPD.checkESPTDEXIST("NPWPD='"+viewAllSimpatda.getNpwpd()+"' and MASA_PAJAK='"+viewAllSimpatda.getMasaPajak()+"' and TAHUN_PAJAK='"+viewAllSimpatda.getTahunPajak()+"' and DENDA='0' and JUMLAH_PAJAK='"+viewAllSimpatda.getPokok()+"' ");
            if(isEsptpd){
                ja.put("Online");
            }else{
                ja.put("Input Offline");
            }
            
            String button = "<div class=\"dropdown\">\n" 
                    + "<button class=\"btn btn-success dropdown-toggle\" type=\"button\" id=\"menu1\" data-toggle=\"dropdown\">Print\n" 
                    + "</button>\n" 
                    + "<ul class=\"dropdown-menu \" role=\"menu\" aria-labelledby=\"menu1\" style=\"position: absolute;top:-10%;margin-left:-200%\">\n"
                    + "<li class='printLaporan' data-masa-pajak='"+viewAllSimpatda.getMasaPajak()+"'"
                    + "data-tahun-pajak='"+viewAllSimpatda.getTahunPajak()+"' "
                    + "data-npwpd='"+viewAllSimpatda.getNpwpd()+"' "
                    + "data-nosptpd='"+viewAllSimpatda.getNosptpd()+"' "
                    + "data-approot='"+approot+"'"
                    + "data-lampiran='1'"
                    + "role=\"presentation\"><a role=\"menuitem\" tabindex=\"-1\" style='cursor:pointer'>"
                    + "<i class=\"icon icon-print\"></i> Cetak Lampiran 1</a></li>\n" ;
            
            ja.put(""+button);
            array.put(ja);
            }catch(Exception ex){
                
        }
        
        }
        
        totalAfterFilter = total;
        
        try {
            result.put("iTotalRecords", total);
            result.put("iTotalDisplayRecords", totalAfterFilter);
            result.put("aaData", array);
        } catch (Exception e) {

        }
        
        return result;
    }
    
    public JSONObject listViewPembayaranSimpatda (HttpServletRequest request, HttpServletResponse response,String user){
        JSONObject result = new JSONObject();
       
        String[] cols = {""+PstViewPembayaranSimpatda.fieldNames[PstViewPembayaranSimpatda.FLD_NPWPD]+"", 
                         ""+PstViewPembayaranSimpatda.fieldNames[PstViewPembayaranSimpatda.FLD_NO_REKENING]+"", 
                         ""+PstViewPembayaranSimpatda.fieldNames[PstViewPembayaranSimpatda.FLD_MASA_PAJAK]+"", 
                         ""+PstViewPembayaranSimpatda.fieldNames[PstViewPembayaranSimpatda.FLD_TAHUN_PAJAK]+"", 
                         ""+PstViewPembayaranSimpatda.fieldNames[PstViewPembayaranSimpatda.FLD_JENIS_SETORAN]+"",
                         ""+PstViewPembayaranSimpatda.fieldNames[PstViewPembayaranSimpatda.FLD_TOTAL_OMZET]+"",
                         ""+PstViewPembayaranSimpatda.fieldNames[PstViewPembayaranSimpatda.FLD_TARIF]+"",
                         ""+PstViewPembayaranSimpatda.fieldNames[PstViewPembayaranSimpatda.FLD_TOTAL_PAJAK]+"",
                         ""+PstViewPembayaranSimpatda.fieldNames[PstViewPembayaranSimpatda.FLD_KETERANGAN]+"",
                         ""+PstViewPembayaranSimpatda.fieldNames[PstViewPembayaranSimpatda.FLD_TGL_REKAM]+"",
                         ""+PstViewPembayaranSimpatda.fieldNames[PstViewPembayaranSimpatda.FLD_SETORAN]+""};
      
        int amount = 10;
        int start = 0;
        int col = 0;
        String dir = "asc";
        String sStart = request.getParameter("iDisplayStart");
        String sAmount = request.getParameter("iDisplayLength");
        String sCol = request.getParameter("iSortCol_0");
        String sdir = request.getParameter("sSortDir_0");
        
        if (sStart != null) {
            start = Integer.parseInt(sStart);
            if (start < 0) {
                start = 0;
            }
        }
        if (sAmount != null) {
            amount = Integer.parseInt(sAmount);
            if (amount < 10) {
                amount = 10;
            }
        }
        if (sCol != null) {
            col = Integer.parseInt(sCol);
            if (col < 0)
                col = 0;
        }
        if (sdir != null) {
            if (!sdir.equals("asc"))
            dir = "desc";
        }
        String whereClause = ""+PstAppUserWP.TBL_APPUSERWP+"."+PstAppUserWP.fieldNames[PstAppUserWP.FLD_USER_ID]+"='"+user+"'";
      
        int total = -1;
        Vector listViewPembayaranSimpatda = PstViewPembayaranSimpatda.listJoin(0,0, whereClause, "");
        total =listViewPembayaranSimpatda.size();
        String colName = cols[col];
        this.amount = amount;
        this.searchTerm = request.getParameter("sSearch");
        this.colName = colName;
        this.dir = dir;
        this.start = start;
        this.colOrder = col;
        
        try {
            result = getDataViewPembayaranSimpatda(total, request,user);
        } catch(Exception ex){
            System.out.println(ex);
        }
       
       return result;
    }
    
    public JSONObject getDataViewPembayaranSimpatda(int total, HttpServletRequest request,String user) throws JSONException{
        
        int totalAfterFilter = total;
        JSONObject result = new JSONObject();
        JSONArray array = new JSONArray();
        String approot = request.getParameter("approot");
        ViewPembayaranSimpatda viewPembayaranSimpatda = new ViewPembayaranSimpatda();
        String order ="";
        
        //format decimal
      
        String pattern = "###,###.###";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);

        String whereClause = ""+PstAppUserWP.TBL_APPUSERWP+"."+PstAppUserWP.fieldNames[PstAppUserWP.FLD_USER_ID]+"='"+user+"'";
        
        if (this.searchTerm == null){
            whereClause += "";                  
        }else{
            whereClause +=" and(";
            whereClause +=" "+PstViewPembayaranSimpatda.TBL_VIEW_PEMBAYARAN_SIMPATDA+"."+PstViewPembayaranSimpatda.fieldNames[PstViewPembayaranSimpatda.FLD_NO_REKENING]+" like '%"+this.searchTerm+"%'";
            whereClause +=" or "+PstViewPembayaranSimpatda.TBL_VIEW_PEMBAYARAN_SIMPATDA+"."+PstViewPembayaranSimpatda.fieldNames[PstViewPembayaranSimpatda.FLD_MASA_PAJAK]+" like '%"+this.searchTerm+"%'";
            whereClause +=" or "+PstViewPembayaranSimpatda.TBL_VIEW_PEMBAYARAN_SIMPATDA+"."+PstViewPembayaranSimpatda.fieldNames[PstViewPembayaranSimpatda.FLD_TAHUN_PAJAK]+" like '%"+this.searchTerm+"%'";
            whereClause +=" or "+PstViewPembayaranSimpatda.TBL_VIEW_PEMBAYARAN_SIMPATDA+"."+PstViewPembayaranSimpatda.fieldNames[PstViewPembayaranSimpatda.FLD_JENIS_SETORAN]+" like '%"+this.searchTerm+"%'";
            whereClause +=" or "+PstViewPembayaranSimpatda.TBL_VIEW_PEMBAYARAN_SIMPATDA+"."+PstViewPembayaranSimpatda.fieldNames[PstViewPembayaranSimpatda.FLD_TOTAL_OMZET]+" like '%"+this.searchTerm+"%'";
            whereClause +=" or "+PstViewPembayaranSimpatda.TBL_VIEW_PEMBAYARAN_SIMPATDA+"."+PstViewPembayaranSimpatda.fieldNames[PstViewPembayaranSimpatda.FLD_TARIF]+" like '%"+this.searchTerm+"%'";
            whereClause +=" or "+PstViewPembayaranSimpatda.TBL_VIEW_PEMBAYARAN_SIMPATDA+"."+PstViewPembayaranSimpatda.fieldNames[PstViewPembayaranSimpatda.FLD_TOTAL_PAJAK]+" like '%"+this.searchTerm+"%'";
            whereClause +=" or "+PstViewPembayaranSimpatda.TBL_VIEW_PEMBAYARAN_SIMPATDA+"."+PstViewPembayaranSimpatda.fieldNames[PstViewPembayaranSimpatda.FLD_KETERANGAN]+" like '%"+this.searchTerm+"%'";
            whereClause +=" or "+PstViewPembayaranSimpatda.TBL_VIEW_PEMBAYARAN_SIMPATDA+"."+PstViewPembayaranSimpatda.fieldNames[PstViewPembayaranSimpatda.FLD_TGL_REKAM]+" like '%"+this.searchTerm+"%'";
            whereClause +=" or "+PstViewPembayaranSimpatda.TBL_VIEW_PEMBAYARAN_SIMPATDA+"."+PstViewPembayaranSimpatda.fieldNames[PstViewPembayaranSimpatda.FLD_SETORAN]+" like '%"+this.searchTerm+"%'";
            whereClause += ")";
        }
        
        if (this.colOrder>=0){
            order += ""+PstViewPembayaranSimpatda.TBL_VIEW_PEMBAYARAN_SIMPATDA+"."+colName+" "+dir+"";
        }
        
        Vector listViewPembayaranSimpatda = PstViewPembayaranSimpatda.listJoin(start, amount,whereClause,order);
        
        for (int i =0 ; i<=listViewPembayaranSimpatda.size()-1;i++){
            viewPembayaranSimpatda = (ViewPembayaranSimpatda) listViewPembayaranSimpatda.get(i);
            JSONArray ja = new JSONArray();
            ja.put(viewPembayaranSimpatda.getNpwpd());
            ja.put(viewPembayaranSimpatda.getNoRekening());
            ja.put(indoMonth[Integer.parseInt(viewPembayaranSimpatda.getMasaPajak())-1]);
            ja.put(viewPembayaranSimpatda.getTahunPajak());
            ja.put(viewPembayaranSimpatda.getJenisSetoran());
            ja.put("Rp. "+decimalFormat.format(viewPembayaranSimpatda.getTotalOmzet()));
            ja.put(viewPembayaranSimpatda.getTarif());
            ja.put("Rp. "+decimalFormat.format(viewPembayaranSimpatda.getTotalPajak()));
            ja.put(viewPembayaranSimpatda.getKeterangan());
            ja.put(viewPembayaranSimpatda.getTglRekam());
            ja.put(viewPembayaranSimpatda.getSetoran());
            array.put(ja);
        }
        
        totalAfterFilter = total;
        
        try {
            result.put("iTotalRecords", total);
            result.put("iTotalDisplayRecords", totalAfterFilter);
            result.put("aaData", array);
        } catch (Exception e) {

        }
        
        return result;
    }
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
        int iCommand = Integer.parseInt(request.getParameter("command"));
        if (iCommand==Command.LOAD){
            int table = Integer.parseInt(request.getParameter("table"));
            if (table==1){
                //loadEcompareSptPd
                JSONObject result = new JSONObject();
                String user = request.getParameter("user");
                result = listCompareESPTPD(request, response,user);
                response.setContentType("application/json");
                response.setHeader("Cache-Control", "no-store");
                PrintWriter out = response.getWriter();
                out.print(result);
            }else if (table==2){ 
                //load View All Simpatda
                JSONObject result = new JSONObject();
                String user = request.getParameter("user");
                result = listViewAllSimpatda(request, response,user);
                response.setContentType("application/json");
                response.setHeader("Cache-Control", "no-store");
                PrintWriter out = response.getWriter();
                out.print(result);
               
            }else if (table==3){
                //load View Pembayaran Simpatda
                JSONObject result = new JSONObject();
                String user = request.getParameter("user");
                result = listViewPembayaranSimpatda(request, response,user);
                response.setContentType("application/json");
                response.setHeader("Cache-Control", "no-store");
                PrintWriter out = response.getWriter();
                out.print(result);
            }
        }else{
            processRequest(request, response);
        }
	
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	processRequest(request, response);
    }

   
    @Override
    public String getServletInfo() {
	return "Short description";
    }// </editor-fold>
}
