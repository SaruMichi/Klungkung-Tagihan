/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dtaxintegration.ajax;

import com.dimata.dtaxintegration.entity.bi.DataPajak;
import com.dimata.dtaxintegration.entity.bi.DataTarget;
import com.dimata.dtaxintegration.entity.bi.PajakType;
import com.dimata.dtaxintegration.entity.bi.PajakTypeDetail;
import com.dimata.dtaxintegration.entity.bi.PstDataPajak;
import com.dimata.dtaxintegration.entity.bi.PstDataTarget;
import com.dimata.dtaxintegration.entity.bi.PstPajakType;
import com.dimata.dtaxintegration.entity.bi.PstPajakTypeDetail;
import com.dimata.dtaxintegration.form.bi.FrmDataPajak;
import com.dimata.dtaxintegration.form.bi.FrmPajakTypeDetail;
import com.dimata.gui.jsp.ControlList;
import com.dimata.qdep.form.FRMQueryString;
import com.dimata.util.Command;
import com.dimata.util.Formater;
import com.dimata.webclient.AppSetting;
import java.io.IOException;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Ardiadi
 */
public class ChartHandler extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	
	//DATE DATA
	Date currentDate = new Date();
	
	//STRING DATA
	String viewtype = FRMQueryString.requestString(request, "FRM_FIELD_VIEW_TYPE");
	String returnChart = "";
	String frmMsg = "";
	String dataChart = FRMQueryString.requestString(request, "FRM_FIELD_CHART_DATA_FOR");
	String chartType = FRMQueryString.requestString(request, "FRM_FIELD_CHART_TYPE");
	String padaBulan = FRMQueryString.requestString(request, "FRM_FIELD_BULAN");
	String sampaiDengan = FRMQueryString.requestString(request, FrmDataPajak.fieldNames[FrmDataPajak.FRM_FIELD_TANGGALBAYAR]);
	String sampaiDenganDashboard = FRMQueryString.requestString(request, FrmDataPajak.fieldNames[FrmDataPajak.FRM_FIELD_TANGGALBAYAR]+"_DASHBOARD");
	String returnHTML = "";
	String dataCategory = FRMQueryString.requestString(request, FrmDataPajak.fieldNames[FrmDataPajak.FRM_FIELD_TAHUN]);
	String pajakTypeDetailName = FRMQueryString.requestString(request, FrmPajakTypeDetail.fieldNames[FrmPajakTypeDetail.FRM_FIELD_PAJAK_DETAIL_NAME]);
	
	String tahun = "";
	String bulan = "";
	String[] padaBulans = padaBulan.split("-");
	if(padaBulan.length() == 0 || padaBulan.equals("")){
	    tahun = Formater.formatDate(currentDate,"yyyy");
	    bulan = Formater.formatDate(currentDate,"MM");
	}else{
	    tahun = padaBulans[0];
	    bulan = padaBulans[1];
	}
	
	//DATE DATA
	Date sampaiDenganDate = new Date();
	if(sampaiDengan.length() > 0 && !sampaiDengan.equals("")){
	    sampaiDenganDate = Formater.formatDate(sampaiDengan, "yyyy-MM-dd");
	}
	
	
	//INT DATA
	int iCommand = FRMQueryString.requestCommand(request);
	int iErrCode = 0;
	int month = Integer.parseInt(Formater.formatDate(sampaiDenganDate, "MM"));
	int year = Integer.parseInt(Formater.formatDate(sampaiDenganDate, "yyyy"));
	int color = FRMQueryString.requestInt(request, "FRM_FIELD_COLOR");
	
	
	//DOUBLE DATA
	double divine = 1000000000.00;
	
	//JSONOBJECT
	JSONObject colors = new JSONObject();
	JSONArray colorArrays = new JSONArray();
	try{
	    colors.put("orange", "#ED7D31");
	    colors.put("blue","#5B9BD5");
	    colors.put("yellow","#FFC000");
	    colors.put("gray","#A5A5A5");
	    colors.put("green","#70AD47");
	    colors.put("blackGray","#7F7F7F");
	    
	    colorArrays.put("#ED7D31");
	    colorArrays.put("#5B9BD5");
	    colorArrays.put("#FFC000");
	    colorArrays.put("#A5A5A5");
	    colorArrays.put("#70AD47");
	    colorArrays.put("#7F7F7F");
	}catch(Exception ex){
	}
	
	JSONArray seriesDatas = new JSONArray();
	JSONObject seriesData = new JSONObject();
	JSONObject chart = new JSONObject();
	JSONObject chartData = new JSONObject();
	JSONArray chartDatas = new JSONArray();
	JSONArray categories = new JSONArray();
	JSONObject categoriesTitle = new JSONObject();
	JSONObject chartOptions = new JSONObject();
	JSONObject titleChart = new JSONObject();
	JSONObject subtitleChart = new JSONObject();
	JSONObject xAxisChart = new JSONObject();
	JSONObject yAxisChart = new JSONObject();
	JSONObject tooltipChart = new JSONObject();
	JSONObject plotOptions = new JSONObject();
	
	
	//ARRAYLIST DATA
	ArrayList<Double> datas = new ArrayList<Double>();
	ArrayList<String> listCategories = new ArrayList<String>();
	
	
	//LONG DATA
	long oidPajakType = FRMQueryString.requestLong(request, "FRM_FIELD_PAJAK_TYPE");
	long oidPajakTypeDetail = FRMQueryString.requestLong(request, FrmDataPajak.fieldNames[FrmDataPajak.FRM_FIELD_PAJAKDETAILID]);
	
	//COMMAND NONE
	if(iCommand == Command.NONE){
	    
	    
	//////RENDER CHART
	    if(viewtype.equals("renderchart")){
		
		
		if(dataChart.equals("targetvsrealisasi")){
		    try{
			listCategories.add("Total");
			generateBarChart(chartType, dataChart, "", 
				"", "", chart,listCategories, "Miliar");
			
			//GET TARGET
			double totalTarget = PstDataTarget.getTotalTarget(0, 0, 
			""+PstDataTarget.fieldNames[PstDataTarget.FLD_TAHUN_TARGET]+"='"+tahun+"'", "");
			totalTarget = totalTarget/divine;
			
			//REALISASI
			String whereClause = "";
			if(AppSetting.SQL_VERSION == AppSetting.DBSVR_ORACLE){
			    whereClause= whereClause+" EXTRACT(YEAR FROM "+ PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+tahun+"'";
			}else{
			     whereClause = whereClause+" EXTRACT(YEAR FROM "+ PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+tahun+"'";;
			}
			double realisasi = PstDataPajak.getTotalRealisasi(0, 0, whereClause, "");
			realisasi = realisasi/divine;

			//REALISASI DATA
			seriesData.put("name", "Realisasi");
			datas.add(Double.parseDouble(Formater.formatNumber(realisasi,"#.##")));
			seriesData.put("color", colors.get("blue"));
			seriesData.put("data", datas);
			seriesDatas.put(seriesData);

			//TARGET DATA
			seriesData = new JSONObject();
			datas = new ArrayList<Double>();
			datas.add(Double.parseDouble(Formater.formatNumber(totalTarget, "#.##")));
			seriesData.put("name", "Target");
			seriesData.put("data", datas);
			seriesData.put("color", colors.get("orange"));
			seriesDatas.put(seriesData);
		    }catch(Exception ex){
			
		    }
		    
		    
		}else if(dataChart.equals("totalpajak")){
		    try{
			
			listCategories.add("Total");
			generateBarChart(chartType, dataChart, "", "", "", chart, listCategories, "%");
			double totalTarget = PstDataTarget.getTotalTarget(0, 0, 
				""+PstDataTarget.fieldNames[PstDataTarget.FLD_TAHUN_TARGET]+"='"+tahun+"'", "");
			totalTarget = totalTarget/divine;
			
			//REALISASI
			String whereClause = "";
			if(AppSetting.SQL_VERSION == AppSetting.DBSVR_ORACLE){
			    whereClause= whereClause+" EXTRACT(YEAR FROM "+ PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+tahun+"'";
			}else{
			     whereClause = whereClause+" EXTRACT(YEAR FROM "+ PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+tahun+"'";;
			}
			double realisasi = PstDataPajak.getTotalRealisasi(0, 0, whereClause, "");
			realisasi = realisasi/divine;
			
			double sisaTarget = 0;
			double realisasiPersen = (realisasi/totalTarget)*100;
			if((totalTarget - realisasi) >= 0){
			    sisaTarget = ((totalTarget-realisasi)/totalTarget)*100;
			}
			 
			
			//REALISASI
			seriesData.put("name", "Total Pajak");
			chartData.put("name", "Sisa Target");
			chartData.put("y", Double.parseDouble(Formater.formatNumber(sisaTarget, "#.##")));
			chartData.put("color", colors.get("orange"));
			chartDatas.put(chartData);
			chartData = new JSONObject();
			chartData.put("name", "Realisasi");
			chartData.put("y", Double.parseDouble(Formater.formatNumber(realisasiPersen, "#.##")));
			chartData.put("color", colors.get("blue"));
			chartDatas.put(chartData);
			
			seriesData.put("data", chartDatas);
			seriesDatas.put(seriesData);
		    }catch(Exception ex){
			
		    }
		}else if(dataChart.equals("pendapatanperjenispajak") || dataChart.equals("pendapatanperjenispajakhariini") || dataChart.equals("pendapatanpadabulantertentu") || dataChart.equals("komposisiphr") || dataChart.equals("top10wpcolumn") || dataChart.equals("perdesa")){
		    try{
			String targetWhere = "";
			String realisasiWhere = "";
			if(dataChart.equals("pendapatanperjenispajakhariini")){
			    tahun = ""+year;
			    bulan = ""+bulan;
                            if(AppSetting.SQL_VERSION == AppSetting.DBSVR_ORACLE){
                                realisasiWhere += " AND TO_CHAR(dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+",'YYYY-MM-DD')<='"+sampaiDengan+"' ";
                            }else{
                                realisasiWhere += " AND dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+"<='"+sampaiDengan+"' ";
                            }
			    
			}else if(dataChart.equals("pendapatanpadabulantertentu")){
			    tahun = ""+tahun;
			    bulan = ""+bulan;
                            realisasiWhere += " AND EXTRACT(MONTH FROM dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+bulan+"'";
			}
			
			if(!padaBulan.equals("") || padaBulan.length() > 0){
			    targetWhere += " AND target."+PstDataTarget.fieldNames[PstDataTarget.FLD_BULAN]+"='"+(Integer.parseInt(bulan)-1)+"'";
			    //realisasiWhere += " AND dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_BULAN]+"='"+bulan+"'";
			    
			}
			
			
			
			Vector listJenisPajak = new Vector(1,1);
			String realisasiText = "";
			String groupBy = "";
			if(dataChart.equals("komposisiphr")){
			    listJenisPajak = PstPajakType.list(0,0,""+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_TYPE_ID]+"='"+PstPajakType.PHR+"'","");
			    Vector listPajakDetail = PstPajakTypeDetail.list(0, 0, PstPajakTypeDetail.fieldNames[PstPajakTypeDetail.FLD_PAJAK_TYPE_ID]+"='"+PstPajakType.PHR+"'", "");
			    if(listPajakDetail.size() != 0){
				for(int i = 0; i < listPajakDetail.size(); i++){
				    PajakTypeDetail pajakTypeDetail = (PajakTypeDetail) listPajakDetail.get(i);
				    listCategories.add(""+pajakTypeDetail.getPajakDetailName());
				}
			    }
			    listJenisPajak = listPajakDetail;
			    realisasiText = "Realisasi YTD";
			    groupBy = "pajakDetail."+PstPajakTypeDetail.fieldNames[PstPajakTypeDetail.FLD_PAJAK_DETAIL_NAME];
			    
			}else if(dataChart.equals("top10wpcolumn")){
			    listJenisPajak = PstDataPajak.listPajakWP(0, 0, 
				    "EXTRACT(YEAR FROM "+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+tahun+"' "
				    + "AND "+PstDataTarget.fieldNames[PstDataPajak.FLD_PAJAKDETAILID]+"='"+oidPajakTypeDetail+"'", 
				    PstDataPajak.fieldNames[PstDataPajak.FLD_JUMLAHPAJAK]+" DESC", 
				    PstDataPajak.fieldNames[PstDataPajak.FLD_ID]+","+PstDataPajak.fieldNames[PstDataPajak.FLD_NAMA]);
			    
			    int batas = 0;
                            if(listJenisPajak.size() >= 10){
                                batas = 10;
                            }else{
                                batas = listJenisPajak.size();
                            }
			    if(listJenisPajak.size() != 0){
				for(int i = 0; i < batas; i++){
				    DataPajak dataPajak = (DataPajak) listJenisPajak.get(i);
				    listCategories.add(dataPajak.getNama());
				}
			    }
			    realisasiText = "Pendapatan";
			}else if(dataChart.equals("perdesa")){
			    listJenisPajak = PstDataPajak.getTotalRealisasiCrossDBKelurahan(0, 0, 
				    "EXTRACT(YEAR FROM dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+tahun+"' ", 
				    ""+PstDataPajak.fieldNames[PstDataPajak.FLD_JUMLAHPAJAK]+" DESC", 
				    "dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_KELURAHAN]+",kelurahan.NM_KELURAHAN,kecamatan.NM_KECAMATAN");
			    
			    
			    if(listJenisPajak.size() != 0){
				for(int i = 0; i < listJenisPajak.size(); i++){
				    DataPajak dataPajak = (DataPajak) listJenisPajak.get(i);
				    listCategories.add(dataPajak.getNamaKelurahan());
				}
			    }
			    realisasiText = "Pendapatan";
			}else{
			    listJenisPajak = PstPajakType.listAll();
			    if(listJenisPajak.size() != 0){
				for(int i = 0; i < listJenisPajak.size(); i++){
				    PajakType pajakType = (PajakType) listJenisPajak.get(i);
				    listCategories.add(""+pajakType.getPajakTypeName());
				}
			    }
			    realisasiText = "Realisasi";
			    groupBy = "pajakType."+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_NAME];
			}
			
			generateBarChart(chartType, dataChart, "", "", "", chart, listCategories, "Miliar");
			
			if(dataChart.equals("top10wpcolumn") || dataChart.equals("perdesa")){
			    //PEMBAGI KHUSUS TOP WP
                            //divine = 1000000;
			    datas = new ArrayList<Double>();
                            int batas = 0;
                            if(dataChart.equals("perdesa")){
                                if(listJenisPajak.size() >= 20){
                                    batas = 20;
                                }else{
                                    batas = listJenisPajak.size();
                                }
                            }else{
                                if(listJenisPajak.size() >= 10){
                                    batas = 10;
                                }else{
                                    batas = listJenisPajak.size();
                                }
                            }
			    if(listJenisPajak.size() != 0){
				for(int i = 0; i < batas; i++){
				    DataPajak dataPajak = (DataPajak) listJenisPajak.get(i);
				    
				    
				    double pencapaian = dataPajak.getJumlahPajak()/divine;
				    datas.add(Double.parseDouble(Formater.formatNumber(pencapaian,"#.##")));
				    
				    
				}
			    }
			    seriesData.put("name", "Pendapatan");
			    seriesData.put("data", datas);
			    seriesDatas.put(seriesData);
			}else{
			    seriesData = new JSONObject();
			    seriesData.put("name", realisasiText);
			    for(int i = 0; i < listJenisPajak.size(); i++){

				Vector listRealisasiPerJenisPajak = new Vector(1,1);
				if(dataChart.equals("komposisiphr")){
				    PajakTypeDetail pajakType = (PajakTypeDetail) listJenisPajak.get(i);
                                        
                                        listRealisasiPerJenisPajak = PstDataPajak.listPendapatanPerJenisPajak(0, 0,
					    "EXTRACT(YEAR FROM dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+tahun+"' "
					    + "AND pajakDetail."+PstPajakTypeDetail.fieldNames[PstPajakTypeDetail.FLD_PAJAK_DETAIL_ID]+"='"+pajakType.getOID()+"'", 
					    "");
                                    
				}else{
				    PajakType pajakType = (PajakType) listJenisPajak.get(i);
				    //REALISASI
                                    if(groupBy.equals("")){
                                        listRealisasiPerJenisPajak = PstDataPajak.listPendapatanPerJenisPajak(0, 0,
					    "EXTRACT(YEAR FROM dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+tahun+"' "
					    + "AND pajakType."+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_TYPE_ID]+"='"+pajakType.getOID()+"'"
					    + realisasiWhere, 
					    "");
                                    }else{
                                        listRealisasiPerJenisPajak = PstDataPajak.listPendapatanPerJenisPajak(0, 0,
					    "EXTRACT(YEAR FROM dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+tahun+"' "
					    + "AND pajakType."+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_TYPE_ID]+"='"+pajakType.getOID()+"'"
					    + realisasiWhere, 
					    "", groupBy);
                                    }
				    
				}


				DataPajak dataPajak;
				if(listRealisasiPerJenisPajak.size() != 0){
				    dataPajak = (DataPajak) listRealisasiPerJenisPajak.get(0);
				}else{
				    dataPajak = new DataPajak();
				}
				double realisasi = dataPajak.getJumlahPajak()/divine;
				datas.add(Double.parseDouble(Formater.formatNumber(realisasi, "#.##")));
			    }

			    seriesData.put("color", colors.get("blue"));
			    seriesData.put("data", datas);
			    seriesDatas.put(seriesData);

			    //TARGET
			    seriesData = new JSONObject();
			    datas = new ArrayList<Double>();
			    if(listJenisPajak.size() != 0){
				for(int i = 0; i < listJenisPajak.size(); i++){
				    Vector listPajakPerJenisPajak = new Vector(1,1);
				    if(dataChart.equals("komposisiphr")){
					PajakTypeDetail pajakType = (PajakTypeDetail) listJenisPajak.get(i);
					//DATA TARGET
					listPajakPerJenisPajak = PstDataTarget.listPerJenisPajak(0, 0,
						"target."+PstDataTarget.fieldNames[PstDataTarget.FLD_TAHUN_TARGET]+"='"+tahun+"' "
						+ "AND pajakDetail."+PstPajakTypeDetail.fieldNames[PstPajakTypeDetail.FLD_PAJAK_DETAIL_ID]+"='"+pajakType.getOID()+"'", 
						"");
				    }else{
					PajakType pajakType = (PajakType) listJenisPajak.get(i);
					//DATA TARGET
					listPajakPerJenisPajak = PstDataTarget.listPerJenisPajak(0, 0,
						"target."+PstDataTarget.fieldNames[PstDataTarget.FLD_TAHUN_TARGET]+"='"+tahun+"' "
						+ "AND pajakType."+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_TYPE_ID]+"='"+pajakType.getOID()+"'"
						+ targetWhere, 
						"", groupBy);
				    }

				    DataTarget dataTarget;
				    if(listPajakPerJenisPajak.size() != 0){
					dataTarget = (DataTarget) listPajakPerJenisPajak.get(0);
				    }else{
					dataTarget = new DataTarget();
				    }
				    datas.add(Double.parseDouble(Formater.formatNumber(dataTarget.getJumlah()/divine, "#.##")));
				}
			    }

			    seriesData.put("name", "Target");
			    seriesData.put("data", datas);
			    seriesData.put("color", colors.get("orange"));
			    seriesDatas.put(seriesData);
			}
			
			
			
			
		    }catch(Exception ex){
			
		    }
		}else if(dataChart.equals("persentasependapatanperjenispajak") || dataChart.equals("persentasependapatanperjenispajakhariini") || dataChart.equals("persenpendapatanpadabulantertentu") || dataChart.equals("persenkomposisiphr") || dataChart.equals("persenperkecamatan")){
		    try{
			listCategories.add("Total");
			generateBarChart(chartType, dataChart, "", "", "", chart, listCategories, "%");
			
			
			//TEST ONLY
			String[] colorsNumber = {"blue", "orange", "gray", "yellow", "blackGray"};
			
			String totalPendapatanWhere = "";
			String realisasiWhere = "";
			
			if(dataChart.equals("persentasependapatanperjenispajakhariini")){
			    tahun = ""+year;
			    bulan = ""+bulan;
			    if(AppSetting.SQL_VERSION == AppSetting.DBSVR_ORACLE){
                                realisasiWhere += " AND TO_CHAR(dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+",'YYYY-MM-DD')<='"+sampaiDengan+"' ";
                            }else{
                                realisasiWhere += " AND dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+"<='"+sampaiDengan+"' ";
                            }
			}else if(dataChart.equals("persenpendapatanpadabulantertentu")){
			    tahun = ""+tahun;
			    bulan = ""+bulan;
			    realisasiWhere += " AND EXTRACT(MONTH FROM dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+Integer.parseInt(bulan)+"' ";
			}
			
			if(!padaBulan.equals("") || padaBulan.length() > 0){
			    totalPendapatanWhere = " AND EXTRACT(MONTH FROM "+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+Integer.parseInt(bulan)+"'";
			    //realisasiWhere += " AND dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_BULAN]+"='"+bulan+"'";
			    
			}
			seriesData.put("name", "Pendapatan");
			
			double totalPendapatan = 0; 
			Vector listPajakType = new Vector(1,1);
			if(dataChart.equals("persenkomposisiphr")){
			    totalPendapatan = PstDataPajak.getTotalRealisasiPerJenisPajak(0, 0, 
				    "pajakType."+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_TYPE_ID]+"='"+PstPajakType.PHR+"' "
				    + "AND EXTRACT(YEAR FROM dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+tahun+"'", "");
			    listPajakType = PstPajakTypeDetail.listJoin(0, 0, 
				    "pajakType."+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_TYPE_ID]+"='"+PstPajakType.PHR+"'", "");
			}else if(dataChart.equals("persenperkecamatan")){
			    totalPendapatan = PstDataPajak.getTotalRealisasi(0, 0, 
				    "EXTRACT(YEAR FROM "+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+tahun+"'", "");
			    listPajakType = PstDataPajak.getTotalRealisasiCrossDBKecamatan(0, 0, 
				    "EXTRACT(YEAR FROM dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+tahun+"' ", 
				    "kecamatan.NM_KECAMATAN ASC", "dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_KECAMATAN]+",kecamatan.NM_KECAMATAN");
			}else{
			    totalPendapatan = PstDataPajak.getTotalRealisasi(0, 0, 
				"EXTRACT(YEAR FROM "+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+tahun+"'"
				+ totalPendapatanWhere, 
				"");
			    listPajakType = PstPajakType.listAll();
			}
			
			
			if(dataChart.equals("persentasependapatanperjenispajak") || dataChart.equals("persentasependapatanperjenispajakhariini") || dataChart.equals("persenpendapatanpadabulantertentu") || dataChart.equals("persenkomposisiphr")){
			    if(listPajakType.size() != 0){
				for(int i = 0; i < listPajakType.size(); i++){

				    Vector listRealisasiPerJenisPajak = new Vector(1,1);
				    String dataName = "";
				    if(dataChart.equals("persenkomposisiphr")){
					PajakTypeDetail pajakType = (PajakTypeDetail) listPajakType.get(i);
					//REALISASI
					listRealisasiPerJenisPajak = PstDataPajak.listPendapatanPerJenisPajakDetail(0, 0,
						"EXTRACT(YEAR FROM dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+tahun+"' "
						+ "AND pajakDetail."+PstPajakTypeDetail.fieldNames[PstPajakTypeDetail.FLD_PAJAK_DETAIL_ID]+"='"+pajakType.getOID()+"'", 
						"", "pajakDetail."+PstPajakTypeDetail.fieldNames[PstPajakTypeDetail.FLD_PAJAK_DETAIL_NAME]);
					dataName = pajakType.getPajakDetailName();
				    }else{
					PajakType pajakType = (PajakType) listPajakType.get(i);

					//REALISASI
					listRealisasiPerJenisPajak = PstDataPajak.listPendapatanPerJenisPajak(0, 0,
						"EXTRACT(YEAR FROM dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+tahun+"' "
						+ "AND pajakType."+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_TYPE_ID]+"='"+pajakType.getOID()+"'"
						+ realisasiWhere, 
						"", "pajakType."+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_NAME]);
					dataName = pajakType.getPajakTypeName();
				    }


				    DataPajak dataPajak;
				    if(listRealisasiPerJenisPajak.size()!=0){
					dataPajak = (DataPajak) listRealisasiPerJenisPajak.get(0);
				    }else{
					dataPajak = new DataPajak();
				    }
				    double pendapatan = (dataPajak.getJumlahPajak()/totalPendapatan)*100;
				    chartData = new JSONObject();
				    chartData.put("name", ""+dataName);
				    chartData.put("y", Double.parseDouble(Formater.formatNumber(pendapatan,"#.##")));
				    chartData.put("color", colors.get(colorsNumber[i]));
				    chartDatas.put(chartData);
				}
			    }
			}else if(dataChart.equals("persenperkecamatan")){
			    if(listPajakType.size() != 0){
                                int batas = 0;
                                if(listPajakType.size() >= 10){
                                    batas = 10;
                                }else{
                                    batas = listPajakType.size();
                                }
				for(int i = 0; i < batas; i++){
				    DataPajak dataPajak = (DataPajak) listPajakType.get(i);
				    double pendapatan = (dataPajak.getJumlahPajak()/totalPendapatan)*100;
				    chartData = new JSONObject();
				    chartData.put("name", ""+dataPajak.getNamaKecamatan());
				    chartData.put("y", Double.parseDouble(Formater.formatNumber(pendapatan,"#.##")));
				    chartDatas.put(chartData);
				}
			    }
			} 
			
			
			seriesData.put("data", chartDatas);
			seriesDatas.put(seriesData);
		    }catch(Exception ex){
			
		    }
		}else if(dataChart.equals("pencapaian")){
		    
		    DateFormatSymbols dfs = new DateFormatSymbols();
		    String[] monthName = dfs.getMonths();
		    generateAreaChart(chartType, dataChart, "", "", "", chart, listCategories, "", oidPajakType);
		    
		    JSONArray pencapaianObject = new JSONArray();
		    JSONArray targetObject = new JSONArray();
		    JSONArray diviasiObject = new JSONArray();
		    JSONArray pencapaianPersenObject = new JSONArray();
		    ArrayList<Object> dataObjectPencapaians = new ArrayList<Object>();
		    ArrayList<Object> dataObjectTargets = new ArrayList<Object>();
		    ArrayList<Object> dataObjectDiviasis = new ArrayList<Object>();
		    ArrayList<Object> dataObjectPencapaianPersens = new ArrayList<Object>();
		    
		    if(monthName.length != 0){
			for(int i = 0; i < monthName.length-1; i++){
			    String realisasiWhere = "";
			    String monthNumber = "";
			    if((i+1) > 9){
				monthNumber =""+(i+1);
			    }else{
				monthNumber = "0"+(i+1);
			    }
			    
			    
			    if(AppSetting.SQL_VERSION == AppSetting.DBSVR_ORACLE){
                                realisasiWhere += " AND TO_CHAR(dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+",'YYYY-MM-DD')<='"+sampaiDengan+"' ";
                            }else{
                                realisasiWhere += " AND dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+"<='"+sampaiDengan+"' ";
                            }
			    
			    
			    Date dateDisplay = Formater.formatDate(tahun+"-"+monthNumber+" UTC", "yyyy-MM z");
			    
			    //GET PENCAPAIAN PER MONTH
			    double realisasi = 0;
			    Vector listRealisasiPerJenisPajak = PstDataPajak.listPendapatanPerJenisPajak(0, 0,
				"EXTRACT(YEAR FROM dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+tahun+"' "
				+ "AND EXTRACT(MONTH FROM dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+(i+1)+"' "
				+ "AND pajakType."+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_TYPE_ID]+"='"+oidPajakType+"'"
				+ ""+realisasiWhere, 
				"", "pajakType."+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_NAME]);

			    DataPajak dataPajak;
			    if(listRealisasiPerJenisPajak.size() != 0){
				dataPajak = (DataPajak) listRealisasiPerJenisPajak.get(0);
			    }else{
				dataPajak = new DataPajak();
			    }
			    realisasi = dataPajak.getJumlahPajak()/divine;
			    
			    
			    
			    //GET TARGET PER MONTH
			    double target = 0;
			    target = PstDataTarget.getTotalTargetPerPajakType(0, 0, 
				"pajakDetail."+PstPajakTypeDetail.fieldNames[PstPajakTypeDetail.FLD_PAJAK_TYPE_ID]+"='"+oidPajakType+"' "
				+ "AND target."+PstDataTarget.fieldNames[PstDataTarget.FLD_TAHUN_TARGET]+"='"+tahun+"' "
				+ "AND target."+PstDataTarget.fieldNames[PstDataTarget.FLD_BULAN]+"='"+i+"' ", "");
			    
			    target = target/divine;
			    
			    
			    //SET DATA PENCAPAIAN
			    dataObjectPencapaians = new ArrayList<Object>();
			    dataObjectPencapaians.add(dateDisplay.getTime());
			    dataObjectPencapaians.add(Double.parseDouble(Formater.formatNumber(realisasi,"#.##")));
			    pencapaianObject.put(dataObjectPencapaians);
			    
			    
			    //SET DATA TARGET
			    dataObjectTargets = new ArrayList<Object>();
			    dataObjectTargets.add(dateDisplay.getTime());
			    dataObjectTargets.add(Double.parseDouble(Formater.formatNumber(target,"#.##")));
			    targetObject.put(dataObjectTargets);
			    
			    
			    //SET DATA DIVIASI
			    dataObjectDiviasis = new ArrayList<Object>();
			    dataObjectDiviasis.add(dateDisplay.getTime());
			    dataObjectDiviasis.add(Double.parseDouble(Formater.formatNumber(realisasi-target,"#.##")));
			    diviasiObject.put(dataObjectDiviasis);
			    
			    //SET DATA PERSEN PENCAPAIAN
			    double pencapaianPersen = 0;
			    if(target != 0){
				pencapaianPersen = (realisasi/target)*100;
			    }
			    dataObjectPencapaianPersens = new ArrayList<Object>();
			    dataObjectPencapaianPersens.add(dateDisplay.getTime());
			    dataObjectPencapaianPersens.add(Double.parseDouble(Formater.formatNumber(pencapaianPersen,"#.##")));
			    pencapaianPersenObject.put(dataObjectPencapaianPersens);
			}
		    }
		    
		    
		   
		    try{
			 //PUT PENCAPAIAN
			seriesData.put("name", "Pencapaian");
			seriesData.put("color", colors.get("blue"));
			seriesData.put("data", pencapaianObject);
			seriesDatas.put(seriesData);
			
			
			//PUT TARGET
			seriesData = new JSONObject();
			seriesData.put("name", "Target");
			seriesData.put("color", colors.get("orange"));
			seriesData.put("data", targetObject);
			seriesDatas.put(seriesData);
			
			
			//PUT DIVIASI
			seriesData = new JSONObject();
			seriesData.put("name", "Deviasi");
			seriesData.put("color", colors.get("gray"));
			seriesData.put("data", diviasiObject);
			seriesDatas.put(seriesData);
			//PUT DIVIASI
			seriesData = new JSONObject();
			seriesData.put("name", "Pencapaian %");
			seriesData.put("color", colors.get("yellow"));
			seriesData.put("data", pencapaianPersenObject);
			seriesDatas.put(seriesData);
		    }catch(Exception ex){
			
		    }
		    
		    
		    
	    ///// AKUMALIS PAJAK PERHARI
		}else if(dataChart.equals("pendapatanpajakakumulasi") || dataChart.equals("pendapatanpajakakumulasiphr") || dataChart.equals("pendapatanperhari") || dataChart.equals("akumulasiperjenispajak")){
		    
		    //CHART OPTIONS
		    generateLineChart(chartType, dataChart, "", "", "", chart, listCategories, "");
		    
		    //GET DATE IN A MONTH & DATE TO DISPLAY
		    Calendar calendar = Calendar.getInstance();
		    Date getDate = Formater.formatDate(padaBulan+"-01", "yyyy-MM-dd");
		    calendar.setTime(getDate);
		    int dateOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		    
		    
		    //DATA OBJECT
		    JSONArray pencapaianObject = new JSONArray();
		    JSONArray targetObject = new JSONArray();
		    ArrayList<Object> dataObjectPencapaians = new ArrayList<Object>();
		    ArrayList<Object> dataObjectTargets = new ArrayList<Object>();
		    
		    //GET AKUMULASI PHR ONLY
		    String realisasiWhere = "";
		    String realisasiGroup = "";
		    String pajakTypeWhere = "";
		    if(dataChart.equals("pendapatanpajakakumulasiphr")){
			realisasiWhere = " AND pajakType."+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_TYPE_ID]+"='"+PstPajakType.PHR+"'";
		    }else if(dataChart.equals("pendapatanperhari")){
			realisasiGroup = "pajakType."+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_NAME];
		    }else if(dataChart.equals("akumulasiperjenispajak")){
			pajakTypeWhere = PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_TYPE_ID]+"!='"+PstPajakType.PHR+"'";
			realisasiGroup = "pajakType."+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_NAME];
		    }
		    
		    
		    if(dataChart.equals("pendapatanperhari") || dataChart.equals("akumulasiperjenispajak")){
			seriesDatas = new JSONArray();
			Vector listPajakType = PstPajakType.list(0,0,pajakTypeWhere,"");
			if(listPajakType.size() != 0){
			    for(int pt = 0; pt < listPajakType.size(); pt++){
				PajakType pajakType = (PajakType) listPajakType.get(pt);
				seriesData = new JSONObject();
				try{
				    int numberOfColor = pt;
				    if(dataChart.equals("pendapatanperhari")){
					numberOfColor = numberOfColor;
				    }else{
					numberOfColor = numberOfColor+1;
				    }
				    seriesData.put("name", ""+pajakType.getPajakTypeName());
				    seriesData.put("color", ""+colorArrays.get(numberOfColor));
				}catch(Exception ex){
				    
				}
				
				
				//LOOP THE DATE
				pencapaianObject = new JSONArray();
				double realisasi = 0;
				for(int i = 1; i <= dateOfMonth; i++){
				    String date = "";
				    if(i > 9){
					date = ""+i;
				    }else{
					date = "0"+i;
				    }
				    //DATE TO DISPLAY TO CHART
				    Date dateDisplay = Formater.formatDate(padaBulan+"-"+date+" UTC","yyyy-MM-dd z");

				    //GET REALISASI VALUE
				    
				    Vector listRealisasiPerJenisPajak = new Vector(1,1);

                                    if(AppSetting.SQL_VERSION == AppSetting.DBSVR_ORACLE){
                                        if(realisasiWhere.equals("")){
                                            listRealisasiPerJenisPajak = PstDataPajak.listPendapatanPerJenisPajak(0, 0,""
                                                + "TO_CHAR(dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+",'YYYY-MM-DD')='"+padaBulan+"-"+i+"' "
                                                + "AND pajakType."+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_TYPE_ID]+"='"+pajakType.getOID()+"'"
                                                + realisasiWhere,"");
                                        }else{
                                            listRealisasiPerJenisPajak = PstDataPajak.listPendapatanPerJenisPajak(0, 0,""
                                                + "TO_CHAR(dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+",'YYYY-MM-DD')='"+padaBulan+"-"+i+"' "
                                                + "AND pajakType."+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_TYPE_ID]+"='"+pajakType.getOID()+"'"
                                                + realisasiWhere, 
                                                "", realisasiGroup);
                                        }
                                        
                                    }else{
                                        listRealisasiPerJenisPajak = PstDataPajak.listPendapatanPerJenisPajak(0, 0,""
                                            + "dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+"='"+padaBulan+"-"+i+"' "
                                            + "AND pajakType."+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_TYPE_ID]+"='"+pajakType.getOID()+"'"
                                            + realisasiWhere, 
                                            "", realisasiGroup);
                                    }
				    

				    DataPajak dataPajak;



				    //ONLY ONE CATEGORY
				    if(listRealisasiPerJenisPajak.size() != 0){
					dataPajak = (DataPajak) listRealisasiPerJenisPajak.get(0);
				    }else{
					dataPajak = new DataPajak();
				    }
				    if(dataChart.equals("pendapatanperhari")){
					realisasi= dataPajak.getJumlahPajak()/divine;
				    }else{
					realisasi+= dataPajak.getJumlahPajak()/divine;
				    }
				    

				    //TARGET VALUE
				    double target = PstDataTarget.getTotalTarget(0, 0, 
					    PstDataTarget.fieldNames[PstDataTarget.FLD_TAHUN_TARGET]+"='"+tahun+"' "
					    + "AND "+PstDataTarget.fieldNames[PstDataTarget.FLD_BULAN]+"='"+bulan+"'", 
					    "");
				    target = target/divine;

				    //SET DATA TARGET
				    
				    dataObjectPencapaians = new ArrayList<Object>();
				    dataObjectPencapaians.add(dateDisplay.getTime());
				    dataObjectPencapaians.add(Double.parseDouble(Formater.formatNumber(realisasi,"#.##")));
				    pencapaianObject.put(dataObjectPencapaians);

				}
				
				try{
				    seriesData.put("data", pencapaianObject);
				    seriesDatas.put(seriesData);
				}catch(Exception ex){
				    
				}
				
				
			    }
			}
		    }else{
			//LOOP THE DATE
			double realisasi = 0;
			for(int i = 1; i <= dateOfMonth; i++){
			    String date = "";
			    if(i > 9){
				date = ""+i;
			    }else{
				date = "0"+i;
			    }
			    //DATE TO DISPLAY TO CHART
			    Date dateDisplay = Formater.formatDate(padaBulan+"-"+date+" UTC","yyyy-MM-dd z");

			    //GET REALISASI VALUE
			    
			    Vector listRealisasiPerJenisPajak = new Vector(1,1);

                            if(AppSetting.SQL_VERSION == AppSetting.DBSVR_ORACLE){
                                
                                if(realisasiGroup.equals("")){
                                    
                                    listRealisasiPerJenisPajak = PstDataPajak.listPendapatanPerJenisPajak(0, 0,
                                        "EXTRACT(YEAR FROM dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+tahun+"' "
                                        + " AND TO_CHAR(dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+",'YYYY-MM-DD')='"+padaBulan+"-"+date+"'"
                                        + realisasiWhere, 
                                        "");
                                }else{
                                    
                                    listRealisasiPerJenisPajak = PstDataPajak.listPendapatanPerJenisPajak(0, 0,
                                        "EXTRACT(YEAR FROM dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+tahun+"' "
                                        + " AND TO_CHAR(dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+",'YYYY-MM-DD')='"+padaBulan+"-"+date+"'"
                                        + realisasiWhere, 
                                        "", realisasiGroup);
                                }
                            }else{
                                listRealisasiPerJenisPajak = PstDataPajak.listPendapatanPerJenisPajak(0, 0,
                                    "EXTRACT(YEAR FROM dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+tahun+"' "
                                    + " AND dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+"='"+padaBulan+"-"+i+"'"
                                    + realisasiWhere, 
                                    "", realisasiGroup);
                            }
			    

			    DataPajak dataPajak;



			    //ONLY ONE CATEGORY
			    if(listRealisasiPerJenisPajak.size() != 0){
				dataPajak = (DataPajak) listRealisasiPerJenisPajak.get(0);
			    }else{
				dataPajak = new DataPajak();
			    }

			    if(dataChart.equals("pendapatanpajakakumulasi") || dataChart.equals("pendapatanpajakakumulasiphr")){
				realisasi += dataPajak.getJumlahPajak()/divine;
			    }else{
				realisasi= dataPajak.getJumlahPajak()/divine;
			    }

			    //TARGET VALUE
			    double target = PstDataTarget.getTotalTarget(0, 0, 
				    PstDataTarget.fieldNames[PstDataTarget.FLD_TAHUN_TARGET]+"='"+tahun+"' "
				    + "AND "+PstDataTarget.fieldNames[PstDataTarget.FLD_BULAN]+"='"+bulan+"'", 
				    "");
			    target = target/divine;



			    //SET DATA TARGET
			    dataObjectTargets = new ArrayList<Object>();
			    dataObjectTargets.add(dateDisplay.getTime());
			    dataObjectTargets.add(Double.parseDouble(Formater.formatNumber(target,"#.##")));
			    targetObject.put(dataObjectTargets);

			    //SET DATA TARGET
			    dataObjectPencapaians = new ArrayList<Object>();
			    dataObjectPencapaians.add(dateDisplay.getTime());
			    dataObjectPencapaians.add(Double.parseDouble(Formater.formatNumber(realisasi,"#.##")));
			    pencapaianObject.put(dataObjectPencapaians);

			}

			//PUT TARGET
			try{
			    if(dataChart.equals("pendapatanpajakakumulasi")){

				//TARGET
				seriesData = new JSONObject();
				seriesData.put("name", "Target");
				seriesData.put("color", colors.get("green"));
				seriesData.put("data", targetObject);
				seriesDatas.put(seriesData);

				//REALISASI
				seriesData = new JSONObject();
				seriesData.put("name", "Realisasi");
				seriesData.put("color", colors.get("blue"));
				seriesData.put("data", pencapaianObject);
				seriesDatas.put(seriesData);
			    }else if(dataChart.equals("pendapatanpajakakumulasiphr")){
				//REALISASI PHR
				seriesData = new JSONObject();
				seriesData.put("name", "PHR");
				seriesData.put("color", colors.get("blue"));
				seriesData.put("data", pencapaianObject);
				seriesDatas.put(seriesData);
			    }
			}catch(Exception ex){

			}
		    }
		    
		    
		    
		}else if(dataChart.equals("perbandingan2tahun") || dataChart.equals("perbandinganperjenispajak") || dataChart.equals("perbandinganperjenispajaknonphr") || dataChart.equals("perbandingan3tahun") || dataChart.equals("proyeksipendapatan") || dataChart.equals("proyeksiperjenispajak") || dataChart.equals("perbandingankecamatan")){
		    try{
			
			String[] dataCategories = dataCategory.split(",");
			Vector listPajakType = new Vector(1,1);
			Vector listKecamatan = new Vector(1,1);
			if(dataChart.equals("perbandinganperjenispajaknonphr")){
			    listPajakType = PstPajakType.list(0,0,""+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_TYPE_ID]+"!='"+PstPajakType.PHR+"'","");
			}else if((dataChart.equals("perbandinganperjenispajak") || dataChart.equals("proyeksiperjenispajak")) && oidPajakType != 0){
			    listPajakType = PstPajakType.list(0,0,""+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_TYPE_ID]+"='"+oidPajakType+"'","");
			}else if(dataChart.equals("perbandingankecamatan")){
			    listPajakType = PstPajakTypeDetail.listAll();
			}else{
			    listPajakType = PstPajakType.listAll();
			}
			
			
			if(dataChart.equals("perbandingan2tahun")){
			    listCategories.add("Perbandingan Pendapatan");
			}else if(dataChart.equals("perbandinganperjenispajak") || dataChart.equals("perbandinganperjenispajaknonphr")){
			    
			    if(listPajakType.size() != 0){
				for(int i = 0; i < listPajakType.size(); i++){
				    PajakType pajakType = (PajakType) listPajakType.get(i);
				    listCategories.add(""+pajakType.getPajakTypeName());
				}
			    }
			}else if((dataChart.equals("perbandinganperjenispajak") || dataChart.equals("proyeksiperjenispajak")) && oidPajakType != 0){
			    listCategories = new ArrayList<String>();
			    if(dataChart.equals("perbandinganperjenispajak")){
				if(dataCategories.length != 0){
				    for(int i = 0; i < dataCategories.length; i++){

					listCategories.add(""+dataCategories[i]);
				    }
				}
			    }else if(dataChart.equals("proyeksiperjenispajak")){
				if(listPajakType.size() != 0){
				    for(int i = 0; i < listPajakType.size(); i++){
					PajakType pajakType = (PajakType) listPajakType.get(i);
					listCategories.add(pajakType.getPajakTypeName());
				    }
				}
			    }
			    
			}else if(dataChart.equals("perbandingan3tahun") || dataChart.equals("proyeksipendapatan")){
			    for(int i = 0; i < dataCategories.length; i++){
				listCategories.add(dataCategories[i]);
			    }
			}else if(dataChart.equals("perbandingankecamatan")){
			    listKecamatan = PstDataPajak.getTotalRealisasiCrossDBKecamatan(0, 0, 
				    "EXTRACT(YEAR FROM dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+tahun+"' ", 
				    "kecamatan.NM_KECAMATAN ASC", 
				    "dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_KECAMATAN]+",kecamatan.NM_KECAMATAN");
			    if(listKecamatan.size() != 0){
                                int batas = 0;
                                if(listKecamatan.size() >= 10){
                                    batas = 10;
                                }else{
                                    batas = listKecamatan.size();
                                }
				for(int i = 0; i < batas; i++){
				    DataPajak dataPajak = (DataPajak) listKecamatan.get(i);
				    listCategories.add(dataPajak.getNamaKecamatan());
				}
			    }
			    
			}
			
			if(dataChart.equals("perbandingan3tahun")){
			    generateStackChart(chartType, dataChart, "", "", "", chart, listCategories, "Miliar");
			}else if((dataChart.equals("perbandinganperjenispajak") || dataChart.equals("proyeksiperjenispajak")) && oidPajakType != 0){
			    generateBarChartDinamis(chartType, dataChart, "", "", "", chart, listCategories, "Miliar", oidPajakType);
			}else{
			    generateBarChart(chartType, dataChart, "", "", "", chart, listCategories, "Miliar");
			}
			
			
			//REALISASI
			if(dataChart.equals("perbandingan2tahun") || dataChart.equals("perbandinganperjenispajak") || dataChart.equals("perbandinganperjenispajaknonphr") || dataChart.equals("proyeksiperjenispajak")){
			    for(int i = 0; i < dataCategories.length; i++){
				String whereClause = "";
				if(dataChart.equals("perbandingan2tahun")){
				    if(AppSetting.SQL_VERSION == AppSetting.DBSVR_ORACLE){
					whereClause= " EXTRACT(YEAR FROM "+ PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+dataCategories[i]+"'";
				    }else{
					 whereClause = " EXTRACT(YEAR FROM "+ PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+dataCategories[i]+"'";;
				    }
				    double realisasi = PstDataPajak.getTotalRealisasi(0, 0, whereClause, "");
				    realisasi = realisasi/divine;

				    //TARGET DATA

				    seriesData = new JSONObject();
				    datas = new ArrayList<Double>();
				    datas.add(Double.parseDouble(Formater.formatNumber(realisasi, "#.##")));
				}else if(dataChart.equals("perbandinganperjenispajak") || dataChart.equals("perbandinganperjenispajaknonphr") || dataChart.equals("proyeksiperjenispajak")){

				    datas = new ArrayList<Double>();
				    if(listPajakType.size() != 0){
					for(int p = 0; p < listPajakType.size(); p++){
					    PajakType pajakType = (PajakType) listPajakType.get(p);
					    double realisasi = 0;
					    if(i == (dataCategories.length-1) && dataChart.equals("proyeksiperjenispajak")){
						if(AppSetting.SQL_VERSION == AppSetting.DBSVR_ORACLE){
						    whereClause= " EXTRACT(YEAR FROM dataPajak."+ PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+dataCategories[i-1]+"' AND pajakType."+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_TYPE_ID]+"='"+pajakType.getOID()+"'";
						}else{
						     whereClause = " EXTRACT(YEAR FROM dataPajak."+ PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+dataCategories[i-1]+"' AND pajakType."+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_TYPE_ID]+"='"+pajakType.getOID()+"'";
						}
						realisasi = PstDataPajak.getTotalRealisasiPerJenisPajak(0, 0, whereClause, "");
						
						double target = PstDataTarget.getTotalKenaikanPerPajakType(0, 0, 
							"target."+PstDataTarget.fieldNames[PstDataTarget.FLD_TAHUN_TARGET]+"='"+dataCategories[i]+"' "
							+ "AND pajakDetail."+PstPajakTypeDetail.fieldNames[PstPajakTypeDetail.FLD_PAJAK_TYPE_ID]+"='"+pajakType.getOID()+"'", "");
						
						realisasi = (realisasi+((realisasi*target)/100))/divine;
					    }else{
						if(AppSetting.SQL_VERSION == AppSetting.DBSVR_ORACLE){
						    whereClause= " EXTRACT(YEAR FROM dataPajak."+ PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+dataCategories[i]+"' AND pajakType."+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_TYPE_ID]+"='"+pajakType.getOID()+"'";
						}else{
						     whereClause = " EXTRACT(YEAR FROM dataPajak."+ PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+dataCategories[i]+"' AND pajakType."+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_TYPE_ID]+"='"+pajakType.getOID()+"'";
						}
						realisasi = PstDataPajak.getTotalRealisasiPerJenisPajak(0, 0, whereClause, "");
						realisasi = realisasi/divine;
					    }
					    

					    

					    datas.add(Double.parseDouble(Formater.formatNumber(realisasi, "#.##")));
					}
				    }
				}

				seriesData = new JSONObject();
				seriesData.put("name", "Total "+dataCategories[i]);
				seriesData.put("data", datas);

				if(dataChart.equals("perbandingan2tahun")){
				    seriesData.put("color", colors.get("blue"));
				}else if((dataChart.equals("perbandinganperjenispajak") || dataChart.equals("proyeksiperjenispajak")) && oidPajakType != 0){
				    seriesData.put("color", colorArrays.get(color));
				}else if(dataChart.equals("perbandinganperjenispajak") || dataChart.equals("perbandinganperjenispajaknonphr")){
				    seriesData.put("color", colorArrays.get(i));
				}
				
				seriesDatas.put(seriesData);
			    }
			}else if(dataChart.equals("perbandingan3tahun") || dataChart.equals("proyeksipendapatan")){
			    for(int p = listPajakType.size()-1; p >=0; p--){
				String whereClause = "";
				PajakType pajakType = (PajakType) listPajakType.get(p);
				datas = new ArrayList<Double>();
				seriesData = new JSONObject();
				if(listPajakType.size() != 0){
				    for(int i = 0; i < dataCategories.length; i++){


					if(AppSetting.SQL_VERSION == AppSetting.DBSVR_ORACLE){
					    whereClause= " EXTRACT(YEAR FROM dataPajak."+ PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+dataCategories[i]+"' AND pajakType."+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_TYPE_ID]+"='"+pajakType.getOID()+"'";
					}else{
					     whereClause = " EXTRACT(YEAR FROM dataPajak."+ PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+dataCategories[i]+"' AND pajakType."+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_TYPE_ID]+"='"+pajakType.getOID()+"'";
					}
					double realisasi = PstDataPajak.getTotalRealisasiPerJenisPajak(0, 0, whereClause, "");
					realisasi = realisasi/divine;

					//TARGET DATA
					if(dataChart.equals("proyeksipendapatan") && (dataCategories.length-1) == i){
					    realisasi = PstDataPajak.getTotalRealisasiPerJenisPajak(0, 0, 
						    "EXTRACT(YEAR FROM dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+dataCategories[i-1]+"' "
						    + "AND pajakType."+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_TYPE_ID]+"='"+pajakType.getOID()+"'", "");

					    double kenaikan = PstDataTarget.getTotalKenaikanPerPajakType(0, 0, 
						    "target."+PstDataTarget.fieldNames[PstDataTarget.FLD_TAHUN_TARGET]+"='"+dataCategories[i]+"' "
						    + "AND pajakDetail."+PstPajakTypeDetail.fieldNames[PstPajakTypeDetail.FLD_PAJAK_TYPE_ID]+"='"+pajakType.getOID()+"'", "");

					    double proyeksi = (realisasi+((realisasi*kenaikan)/100))/divine;
					    realisasi = proyeksi;


					}


					datas.add(Double.parseDouble(Formater.formatNumber(realisasi, "#.##")));
				    }
				}
				

				
				seriesData.put("name", ""+pajakType.getPajakTypeName());
				seriesData.put("data", datas);
				seriesData.put("color", colorArrays.get(p));
				
				seriesDatas.put(seriesData);
			    }
			}else if(dataChart.equals("perbandingankecamatan")){
			    if(listPajakType.size() != 0){
				for(int i = 0; i < listPajakType.size(); i++){
				    PajakTypeDetail pajakTypeDetail = (PajakTypeDetail) listPajakType.get(i);
				    datas = new ArrayList<Double>();
				    seriesData = new JSONObject();
                                    int batas = 0;
                                    if(listKecamatan.size() >= 0){
                                        batas = 10;
                                    }else{
                                        batas = listKecamatan.size();
                                    }
				    if(listKecamatan.size() != 0){
					for(int k = 0; k < batas; k++){
					    DataPajak dataPajak = (DataPajak) listKecamatan.get(k);
					    double pendapatan = PstDataPajak.getTotalRealisasi(0, 0, 
						    "EXTRACT(YEAR FROM "+ PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+tahun+"' "
						    + "AND "+PstDataPajak.fieldNames[PstDataPajak.FLD_PAJAKDETAILID]+"='"+pajakTypeDetail.getOID()+"' "
						    + "AND "+PstDataPajak.fieldNames[PstDataPajak.FLD_KECAMATAN]+"='"+dataPajak.getKodeKecamatan()+"'","");
					    pendapatan = pendapatan/divine;
					    datas.add(Double.parseDouble(Formater.formatNumber(pendapatan,"#.##")));
					}
				    }
				    seriesData.put("name", ""+pajakTypeDetail.getPajakDetailName());
				    seriesData.put("data", datas);

				    seriesDatas.put(seriesData);
				}
			    }
			}
			
			    
			   
			
			
		    }catch(Exception ex){
			ex.printStackTrace();
		    }
		    
		    
		}else if(dataChart.equals("perbandinganperbulan") || dataChart.equals("perbandinganperbulan3tahun") || dataChart.equals("proyeksiperbulan")){
		    
		    DateFormatSymbols dfs = new DateFormatSymbols();
		    String[] monthName = dfs.getMonths();
		    for(int i = 0; i < monthName.length-1; i++){
			listCategories.add(monthName[i].substring(0,3));
		    }
		    //CHART OPTIONS
		    generateLineChartMonth(chartType, dataChart, "", "", "", chart, listCategories, "");
		    
		    
		    
		    //DATA OBJECT
		    JSONArray pencapaianObject = new JSONArray();
		    JSONArray targetObject = new JSONArray();
		    JSONArray selisihObject = new JSONArray();
		    ArrayList<Object> dataObjectPencapaians = new ArrayList<Object>();
		    ArrayList<Object> dataObjectTargets = new ArrayList<Object>();
		    
		    String[] dataCategories = dataCategory.split(",");
		    if(dataCategories.length != 0){
			for(int i = 0; i < dataCategories.length; i++){
			    double target = 0;
			    double selisih = 0;
			    try{
				seriesData = new JSONObject();
				pencapaianObject = new JSONArray();
				targetObject = new JSONArray();
				selisihObject = new JSONArray();
				seriesData.put("name", "Total "+dataCategories[i]);
				seriesData.put("color", ""+colorArrays.get(i));
				double realisasi = 0;
				if(monthName.length != 0){
				    for(int m = 0; m < monthName.length-1; m++){
					String displayMonth = "01";
					if((i+1) > 9){
					    displayMonth = tahun+"-"+(m+1);
					}else{
					    displayMonth = tahun+"-0"+(m+1);
					}
					
					
					pencapaianObject.put(Double.parseDouble(Formater.formatNumber(realisasi,"#.##")));
					
					if(i == (dataCategories.length-1) && dataChart.equals("proyeksiperbulan")){
					    double getRealisasi = PstDataPajak.getTotalRealisasi(0, 0, 
						    "EXTRACT(YEAR FROM "+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+dataCategories[i-1]+"' "
						    + "AND EXTRACT(MONTH FROM "+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+(m+1)+"'", "");
					    double getTarget = PstDataTarget.getTotalKenaikanPerPajakType(0, 0, 
						    "target."+PstDataTarget.fieldNames[PstDataTarget.FLD_TAHUN_TARGET]+"='"+dataCategories[i]+"' "
						    + "AND target."+PstDataTarget.fieldNames[PstDataTarget.FLD_BULAN]+"='"+m+"'", "");
					    
					    realisasi += ((realisasi+(getRealisasi*getTarget)/100))/divine;
					}else{
					    Date dateDisplay = Formater.formatDate(displayMonth+" UTC", "yyyy-MM z");
					    double getRealisasi = PstDataPajak.getTotalRealisasi(0, 0, 
						    "EXTRACT(YEAR FROM "+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+dataCategories[i]+"' "
						    + "AND EXTRACT(MONTH FROM "+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+(m+1)+"'", "");
					    realisasi += getRealisasi/divine;

					    if(i == dataCategories.length-1){
						target = PstDataTarget.getTotalTarget(0, 0, 
						    ""+PstDataTarget.fieldNames[PstDataTarget.FLD_BULAN]+"='"+m+"' "
						    + "AND "+PstDataTarget.fieldNames[PstDataTarget.FLD_TAHUN_TARGET]+"='"+dataCategories[dataCategories.length-1]+"'", "");
						target = target/divine;
						targetObject.put(Double.parseDouble(Formater.formatNumber(target,"#.##")));
						selisih = target-(getRealisasi/divine);
						selisihObject.put(Double.parseDouble(Formater.formatNumber(selisih,"#.##")));
					    }
					}
				    }
				}
				
				seriesData.put("data", pencapaianObject);
				seriesDatas.put(seriesData);
				
				if(i == dataCategories.length-1 && dataChart.equals("perbandinganperbulan")){
				    seriesData = new JSONObject();
				    seriesData.put("name", "Target "+dataCategories[i]);
				    seriesData.put("color", ""+colorArrays.get(dataCategories.length));
				    seriesData.put("data", targetObject);
				    seriesDatas.put(seriesData);
				    
				    seriesData = new JSONObject();
				    seriesData.put("name", "Selisih");
				    seriesData.put("color", ""+colorArrays.get(dataCategories.length+1));
				    seriesData.put("data", selisihObject);
				    seriesDatas.put(seriesData);
				}
			    }catch(Exception ex){
				
			    }
			}
		    }
		}else if(dataChart.equals("pencapaianphrline") || dataChart.equals("pencapaianphrstackline") || dataChart.equals("top10wpline")){
		    DateFormatSymbols dfs = new DateFormatSymbols();
		    String[] monthName = dfs.getMonths();
		    for(int i = 0; i < monthName.length-1; i++){
			listCategories.add(monthName[i].substring(0,3)+"-"+tahun.substring(2));
		    }
		    //CHART OPTIONS
		    
		    if(dataChart.equals("pencapaianphrline")){
			
			generateLineChartMonth(chartType, dataChart, "", "", "", chart, listCategories, "");
		    }else if(dataChart.equals("pencapaianphrstackline")){
			generateStackChart(chartType, dataChart, "", "", "", chart, listCategories, "");
		    }else if(dataChart.equals("top10wpline")){
			generateLineChartMonth(chartType, dataChart, "", "", "", chart, listCategories, "");
		    }
		    
		    
		    
		    Vector listPhr = new Vector(1,1);
		    if(dataChart.equals("pencapaianphrline") || dataChart.equals("pencapaianphrstackline")){
			listPhr = PstPajakTypeDetail.listJoin(0, 0, 
			    "pajakType."+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_TYPE_ID]+"='"+PstPajakType.PHR+"'", "");
		    }else if(dataChart.equals("top10wpline")){
			listPhr  = PstDataPajak.listPajakWP(0, 0, 
				    "EXTRACT(YEAR FROM "+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+tahun+"' "
				    + "AND "+PstDataTarget.fieldNames[PstDataPajak.FLD_PAJAKDETAILID]+"='"+oidPajakTypeDetail+"'", 
				    PstDataPajak.fieldNames[PstDataPajak.FLD_JUMLAHPAJAK]+" DESC", 
				    PstDataPajak.fieldNames[PstDataPajak.FLD_ID]+","+PstDataPajak.fieldNames[PstDataPajak.FLD_NAMA]);
		    }
		    
		    //DATA OBJECT
		    JSONArray pencapaianObject = new JSONArray();
		    JSONArray targetObject = new JSONArray();
		    JSONArray selisihObject = new JSONArray();
		    ArrayList<Object> dataObjectPencapaians = new ArrayList<Object>();
		    ArrayList<Object> dataObjectTargets = new ArrayList<Object>();
		    
		    String[] dataCategories = dataCategory.split(",");
                    int batas = 0;
                    if(listPhr.size() >= 10){
                        batas = 10;
                    }else{
                        batas = listPhr.size();
                    }
		    if(listPhr.size() != 0){
			for(int i = 0; i < batas; i++){
			    String dataName = "";
			    String dataOid = "";
			    String top10WhereClause = "";
			    if(dataChart.equals("top10wpline")){
				DataPajak dataPajak = (DataPajak) listPhr.get(i);
				dataName = dataPajak.getNama();
				dataOid = ""+oidPajakTypeDetail;
				top10WhereClause = " AND dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_ID]+"='"+dataPajak.getId()+"'";
			    }else{
				PajakTypeDetail pajakTypeDetail = (PajakTypeDetail) listPhr.get(i);
				dataName = pajakTypeDetail.getPajakDetailName();
				dataOid = ""+pajakTypeDetail.getOID();
			    }
			    
			    double target = 0;
			    double selisih = 0;
			    try{
				seriesData = new JSONObject();
				pencapaianObject = new JSONArray();
				targetObject = new JSONArray();
				selisihObject = new JSONArray();
				seriesData.put("name", ""+dataName);
				
				if(dataChart.equals("pencapaianphrline") || dataChart.equals("pencapaianphrstackline")){
				    seriesData.put("color", ""+colorArrays.get(i));
				}
				
				double realisasi = 0;
				if(monthName.length != 0){
				    for(int m = 0; m < monthName.length-1; m++){
					String displayMonth = "01";
					if((i+1) > 9){
					    displayMonth = tahun+"-"+(m+1);
					}else{
					    displayMonth = tahun+"-0"+(m+1);
					}
					Date dateDisplay = Formater.formatDate(displayMonth+" UTC", "yyyy-MM z");
					double getRealisasi = PstDataPajak.getTotalRealisasiPerJenisPajak(0, 0, 
						"EXTRACT(YEAR FROM dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+tahun+"' "
						+ "AND EXTRACT(MONTH FROM dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+(m+1)+"' "
						+ "AND pajakDetail."+PstPajakTypeDetail.fieldNames[PstPajakTypeDetail.FLD_PAJAK_DETAIL_ID]+"='"+dataOid+"'"
						+ ""+top10WhereClause, "");
					if(dataChart.equals("top10wpline")){
					    realisasi = getRealisasi/divine;
					}else{
					    realisasi += getRealisasi/divine;
					}
					
					
					
					
					pencapaianObject.put(Double.parseDouble(Formater.formatNumber(realisasi,"#.##")));
					
				    }
				}
				
				seriesData.put("data", pencapaianObject);
				seriesDatas.put(seriesData);
				
				
			    }catch(Exception ex){
				
			    }
			}
		    }
		}else if(dataChart.equals("targetpajak") || dataChart.equals("targetpajaknonphr") || dataChart.equals("persentasetargetpajak") || dataChart.equals("targetpajakphrhotel") || dataChart.equals("targetpajakphrnonhotel")){
		    DateFormatSymbols dfs = new DateFormatSymbols();
		    String[] monthName = dfs.getMonths();
		    double totalTarget = 0;
		    
		    
		    Vector listPajakType = new Vector();
		    if(dataChart.equals("targetpajak") || dataChart.equals("persentasetargetpajak")){
			listPajakType = PstPajakType.listAll();
		    }else if(dataChart.equals("targetpajaknonphr")){
			listPajakType = PstPajakType.list(0, 0, 
				""+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_TYPE_ID]+"!='"+PstPajakType.PHR+"'", "");
		    }else if(dataChart.equals("targetpajakphrhotel")){
			listPajakType = PstPajakTypeDetail.list(0, 0, 
				""+PstPajakTypeDetail.fieldNames[PstPajakTypeDetail.FLD_PAJAK_TYPE_ID]+"='"+PstPajakType.PHR+"'", "");
		    }else if(dataChart.equals("targetpajakphrnonhotel")){
			listPajakType = PstPajakTypeDetail.list(0, 0, 
				""+PstPajakTypeDetail.fieldNames[PstPajakTypeDetail.FLD_PAJAK_DETAIL_ID]+"!='"+PstPajakTypeDetail.PHR_HOTEL+"' "
				+ "AND "+PstPajakTypeDetail.fieldNames[PstPajakTypeDetail.FLD_PAJAK_TYPE_ID]+"='"+PstPajakType.PHR+"'", "");
		    }
		    
		    
		    if(dataChart.equals("targetpajak") || dataChart.equals("targetpajaknonphr") || dataChart.equals("targetpajakphrhotel") || dataChart.equals("targetpajakphrnonhotel")){
			
			for(int i = 0; i < monthName.length-1; i++){
			    listCategories.add(monthName[i].substring(0,3)+"-"+tahun.substring(2));
			}
			generateLineChartMonth(chartType, dataChart, "", "", "", chart, listCategories, "");
		    }else if(dataChart.equals("persentasetargetpajak")){
			listCategories.add("Total Target");
			
			totalTarget = PstDataTarget.getTotalTarget(0, 0, 
				""+PstDataTarget.fieldNames[PstDataTarget.FLD_TAHUN_TARGET]+"='"+tahun+"'", "");
			generateBarChart(chartType, dataChart, "", "", "", chart, listCategories, "%");
		    }
		    
		    //DATA OBJECT
		    JSONArray targetObject = new JSONArray();
		    ArrayList<Object> dataObjectTargets = new ArrayList<Object>();
		    
		    if(listPajakType.size() != 0){
			for(int i = 0; i < listPajakType.size(); i++){
			    String pajakName="";
			    long pajakOid = 0;
			    if(dataChart.equals("targetpajakphrhotel") || dataChart.equals("targetpajakphrnonhotel")){
				PajakTypeDetail pajakType = (PajakTypeDetail) listPajakType.get(i);
				pajakName = pajakType.getPajakDetailName();
				pajakOid = pajakType.getOID();
			    }else{
				PajakType pajakType = (PajakType) listPajakType.get(i);
				pajakName = pajakType.getPajakTypeName();
				pajakOid = pajakType.getOID();
			    }
			    
			    int getColor = 0;
			    if(dataChart.equals("targetpajaknonphr")){
				getColor = i+1;
			    }else{
				getColor = i;
			    }
			    try{
				seriesData = new JSONObject();
				chartData = new JSONObject();
				
				double target = 0;
				
				if(dataChart.equals("persentasetargetpajak")){
				   
				    
				    target = PstDataTarget.getTotalTargetPerPajakType(0, 0, 
					    "target."+PstDataTarget.fieldNames[PstDataTarget.FLD_TAHUN_TARGET]+"='"+tahun+"' "
					    + "AND pajakDetail."+PstPajakTypeDetail.fieldNames[PstPajakTypeDetail.FLD_PAJAK_TYPE_ID]+"='"+pajakOid+"'", 
					    "");
				    
				    target = (target/totalTarget)*100;
				    
				    if(pajakOid == PstPajakType.PHR){
					chartData.put("name", "Total "+pajakName);
				    }else{
					chartData.put("name", ""+pajakName);
				    }
				    
				    chartData.put("y", Double.parseDouble(Formater.formatNumber(target, "#.##")));
				    chartData.put("color", colorArrays.get(getColor));
				    targetObject.put(chartData);
				    
				}else if(dataChart.equals("targetpajak") || dataChart.equals("targetpajaknonphr") || dataChart.equals("targetpajakphrhotel") || dataChart.equals("targetpajakphrnonhotel")){
				    
				    targetObject = new JSONArray();
				    if(pajakOid == PstPajakType.PHR && (dataChart.equals("targetpajak") || dataChart.equals("targetpajaknonphr"))){
					seriesData.put("name", "Total "+pajakName);
				    }else{
					seriesData.put("name", ""+pajakName);
				    }
				    

				    seriesData.put("color", ""+colorArrays.get(getColor));
				    if(monthName.length != 0){
					for(int m = 0; m < monthName.length-1; m++){
					    String displayMonth = "01";
					    if((i+1) > 9){
						displayMonth = tahun+"-"+(m+1);
					    }else{
						displayMonth = tahun+"-0"+(m+1);
					    }
					    double getTarget = 0;
					    if(dataChart.equals("targetpajak") || dataChart.equals("targetpajaknonphr")){
						getTarget = PstDataTarget.getTotalTargetPerPajakType(0, 0, 
						    "target."+PstDataTarget.fieldNames[PstDataTarget.FLD_TAHUN_TARGET]+"='"+tahun+"' "
						    + "AND target."+PstDataTarget.fieldNames[PstDataTarget.FLD_BULAN]+"='"+m+"' "
						    + "AND pajakDetail."+PstPajakTypeDetail.fieldNames[PstPajakTypeDetail.FLD_PAJAK_TYPE_ID]+"='"+pajakOid+"'", "");
					    }else{
						getTarget = PstDataTarget.getTotalTargetPerPajakType(0, 0, 
						    "target."+PstDataTarget.fieldNames[PstDataTarget.FLD_TAHUN_TARGET]+"='"+tahun+"' "
						    + "AND target."+PstDataTarget.fieldNames[PstDataTarget.FLD_BULAN]+"='"+m+"' "
						    + "AND pajakDetail."+PstPajakTypeDetail.fieldNames[PstPajakTypeDetail.FLD_PAJAK_DETAIL_ID]+"='"+pajakOid+"'", "");
					    }
					    
					    target += getTarget/divine;
					    targetObject.put(Double.parseDouble(Formater.formatNumber(target,"#.##")));
					    
					}
				    }
				    
				    seriesData.put("data", targetObject);
				
				    seriesDatas.put(seriesData);
				}
				
				
				
			    }catch(Exception ex){
				
			    }
			}
			
			if(dataChart.equals("persentasetargetpajak")){
			    try{
				seriesData.put("name" , "Total Target");
				seriesData.put("data", targetObject);
				seriesDatas.put(seriesData);
			    }catch(Exception ex){
				
			    }
			    
			}
		    }
		}
		    
		
		
		try{
		    
		    chart.put("series", seriesDatas);
		}catch(Exception ex){
		    
		}
		
		
	    }else if(viewtype.equals("pencapaiantable")){
		returnHTML = drawTabelPendapatanPajak(currentDate, divine, oidPajakType, month);
	    }else if(viewtype.equals("tabelperbandingan2tahun")){
		String[] perbandingan = dataCategory.split(",");
		returnHTML = drawTabelPerbandingan2Tahun(currentDate, divine, perbandingan);
	    }else if(viewtype.equals("tabelperbandinganperbulan")){
		String[] perbandingan = dataCategory.split(",");
		returnHTML = drawTabelPerbandinganPerBulan(currentDate, divine, perbandingan);
	    }else if(viewtype.equals("tabelperbandingan3tahun")){
		String[] perbandingan = dataCategory.split(",");
		returnHTML = drawTabelPerbandingan3Tahun(currentDate, divine, perbandingan);
	    }else if(viewtype.equals("tabelkomposisiphr")){
		returnHTML = drawTabelPendapatanPajak(divine, tahun);
	    }else if(viewtype.equals("tabelpencapaianphrperbulan")){
		returnHTML = drawTabelPendapatanPajakPHR(tahun, divine, oidPajakType, month);
	    }else if(viewtype.equals("tabelproyeksipendapatan")){
		String[] perbandingan = dataCategory.split(",");
		returnHTML = drawTabelProyeksiPendapatan(currentDate, divine, perbandingan);
	    }else if(viewtype.equals("tabeltargetpajak")){
		returnHTML = drawTableTargetPajak(tahun, divine);
	    }else if(viewtype.equals("tabeltop10wp")){
		returnHTML = drawTabelTop10WP(tahun, divine, oidPajakType, month, oidPajakTypeDetail, pajakTypeDetailName);
	    }else if(viewtype.equals("tabelperdesa")){
		returnHTML = drawTabelTop20Desa(tahun, divine, oidPajakType, month, oidPajakTypeDetail, pajakTypeDetailName);
	    }else if(viewtype.equals("tabelperkecamatan")){
		returnHTML = drawTabelPerKecamatan(tahun, divine, oidPajakType, month, oidPajakTypeDetail, pajakTypeDetailName);
	    }else if(viewtype.equals("reportperhari")){
		returnHTML = drawReportPerHari(sampaiDenganDashboard);
	    }else if(viewtype.equals("reportperbulan")){
		returnHTML = drawReportPerBulan(sampaiDenganDashboard);
	    }
	    
	}else if(iCommand == Command.SAVE){
	    
	}
	
	
	
	
	JSONObject jSONObject = new JSONObject();
	
	try{
	    jSONObject.put("CHART_DATA", chart);
	    jSONObject.put("HTML_DATA",returnHTML);
	}catch(JSONException ex){
	    returnChart = "{'CHART_DATA':'"+ex.toString()+"'}";
	}
	
	response.getWriter().println(jSONObject);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
	return "Short description";
    }// </editor-fold>
    
    
    //GENERATE SPLINE / LINE / AREA CHART
    public void generateAreaChart(String chartType, String dataChart, 
	    String titleChartName, String subtitleChartName, 
	    String categoriesTitleName, JSONObject chart,
	    ArrayList<String> listCategories, String toolTips, long oidPajakType){
	
	//SET CHART OPTIONS
	JSONArray categories = new JSONArray();
	
	JSONObject categoriesTitle = new JSONObject();
	
	JSONObject chartOptions = new JSONObject();
	JSONObject titleChart = new JSONObject();
	JSONObject subtitleChart = new JSONObject();
	JSONObject xAxisChart = new JSONObject();
	JSONObject yAxisChart = new JSONObject();
	JSONObject tooltipChart = new JSONObject();
	JSONObject plotOptions = new JSONObject();
	JSONObject month = new JSONObject();
	
	
	
	try{
	    chartOptions.put("type", chartType);
	    chartOptions.put("renderTo", dataChart+""+oidPajakType);
	    titleChart.put("text", titleChartName);

	    subtitleChart.put("text", "");
	    categoriesTitle.put("text", "");
	    xAxisChart.put("type", "datetime");
	    month.put("month", "%b-%y");
	    xAxisChart.put("dateTimeLabelFormats", month);

	    chart.put("chart", chartOptions);
	    chart.put("title", titleChart);
	    chart.put("subtitle", subtitleChart);
	    chart.put("xAxis",xAxisChart);
	}catch(Exception ex){

	}
    }
    
    //GENERATE LINE CHART
    public void generateLineChart(String chartType, String dataChart, 
	    String titleChartName, String subtitleChartName, 
	    String categoriesTitleName, JSONObject chart,
	    ArrayList<String> listCategories, String toolTips){
	
	//SET CHART OPTIONS
	JSONArray categories = new JSONArray();
	
	JSONObject categoriesTitle = new JSONObject();
	
	JSONObject chartOptions = new JSONObject();
	JSONObject titleChart = new JSONObject();
	JSONObject subtitleChart = new JSONObject();
	JSONObject xAxisChart = new JSONObject();
	JSONObject yAxisChart = new JSONObject();
	JSONObject tooltipChart = new JSONObject();
	JSONObject plotOptions = new JSONObject();
	JSONObject rangeOptions = new JSONObject();
	JSONObject month = new JSONObject();
	JSONObject label = new JSONObject();
	
	
	
	try{
	    chartOptions.put("type", chartType);
	    chartOptions.put("renderTo", dataChart);
	    titleChart.put("text", titleChartName);

	    subtitleChart.put("text", "");
	    categoriesTitle.put("text", "");
	    xAxisChart.put("type", "datetime");
	    month.put("day","%e-%b-%y");
	    xAxisChart.put("dateTimeLabelFormats", month);
	    xAxisChart.put("tickInterval", 24*3600*1000);
	    label.put("rotation", -90);
	    label.put("align", "right");
	    xAxisChart.put("labels", label);
	    rangeOptions.put("pointRange", 24*3600);
	    plotOptions.put("series", rangeOptions);
		    
	    chart.put("chart", chartOptions);
	    chart.put("title", titleChart);
	    chart.put("subtitle", subtitleChart);
	    chart.put("xAxis",xAxisChart);
	    chart.put("plotOptions", plotOptions);
	}catch(Exception ex){

	}
    }
    
    //GENERATE LINE CHART
    public void generateLineChartMonth(String chartType, String dataChart, 
	    String titleChartName, String subtitleChartName, 
	    String categoriesTitleName, JSONObject chart,
	    ArrayList<String> listCategories, String toolTips){
	
	//SET CHART OPTIONS
	JSONArray categories = new JSONArray();
	
	JSONObject categoriesTitle = new JSONObject();
	
	JSONObject chartOptions = new JSONObject();
	JSONObject titleChart = new JSONObject();
	JSONObject subtitleChart = new JSONObject();
	JSONObject xAxisChart = new JSONObject();
	JSONObject yAxisChart = new JSONObject();
	JSONObject tooltipChart = new JSONObject();
	JSONObject plotOptions = new JSONObject();
	JSONObject rangeOptions = new JSONObject();
	JSONObject month = new JSONObject();
	JSONObject label = new JSONObject();
	
	
	
	try{
	    chartOptions.put("type", chartType);
	    chartOptions.put("renderTo", dataChart);
	    titleChart.put("text", titleChartName);

	    subtitleChart.put("text", "");
	    categoriesTitle.put("text", "");
	    xAxisChart.put("categories", listCategories);
	    
	    chart.put("chart", chartOptions);
	    chart.put("title", titleChart);
	    chart.put("subtitle", subtitleChart);
	    chart.put("xAxis",xAxisChart);
	    chart.put("plotOptions", plotOptions);
	}catch(Exception ex){

	}
    }
    

    
    
    //GENERATE BAR & COLUMN CHART
    public void generateBarChart(String chartType, String dataChart, 
	    String titleChartName, String subtitleChartName, 
	    String categoriesTitleName, JSONObject chart,
	    ArrayList<String> listCategories, String toolTips){
	
	//SET CHART OPTIONS
	JSONArray categories = new JSONArray();
	
	JSONObject categoriesTitle = new JSONObject();
	
	JSONObject chartOptions = new JSONObject();
	JSONObject titleChart = new JSONObject();
	JSONObject subtitleChart = new JSONObject();
	JSONObject xAxisChart = new JSONObject();
	JSONObject yAxisChart = new JSONObject();
	JSONObject tooltipChart = new JSONObject();
	JSONObject plotOptions = new JSONObject();
	
	
	
	try{
	    chartOptions.put("type", chartType);
	    chartOptions.put("renderTo", dataChart);
	    titleChart.put("text", titleChartName);

	    subtitleChart.put("text", "");
	    categoriesTitle.put("text", "");
	    xAxisChart.put("categories", listCategories);
	    xAxisChart.put("title", categoriesTitle);

	    yAxisChart.put("min", 0);
	    yAxisChart.put("title", categoriesTitle);

	    tooltipChart.put("valueSuffix", " "+toolTips);

	    chart.put("chart", chartOptions);
	    chart.put("title", titleChart);
	    chart.put("subtitle", subtitleChart);
	    chart.put("xAxis",xAxisChart);
	    chart.put("yAxis", yAxisChart);
	    chart.put("tooltip", tooltipChart);
	}catch(Exception ex){

	}
    }
    
    public void generateBarChartDinamis(String chartType, String dataChart, 
	    String titleChartName, String subtitleChartName, 
	    String categoriesTitleName, JSONObject chart,
	    ArrayList<String> listCategories, String toolTips, long oidPajakType){
	
	//SET CHART OPTIONS
	JSONArray categories = new JSONArray();
	
	JSONObject categoriesTitle = new JSONObject();
	
	JSONObject chartOptions = new JSONObject();
	JSONObject titleChart = new JSONObject();
	JSONObject subtitleChart = new JSONObject();
	JSONObject xAxisChart = new JSONObject();
	JSONObject yAxisChart = new JSONObject();
	JSONObject tooltipChart = new JSONObject();
	JSONObject plotOptions = new JSONObject();
	
	
	
	try{
	    chartOptions.put("type", chartType);
	    chartOptions.put("renderTo", dataChart+""+oidPajakType);
	    titleChart.put("text", titleChartName);

	    subtitleChart.put("text", "");
	    categoriesTitle.put("text", "");
	    xAxisChart.put("categories", listCategories);
	    xAxisChart.put("title", categoriesTitle);

	    yAxisChart.put("min", 0);
	    yAxisChart.put("title", categoriesTitle);

	    tooltipChart.put("valueSuffix", " "+toolTips);

	    chart.put("chart", chartOptions);
	    chart.put("title", titleChart);
	    chart.put("subtitle", subtitleChart);
	    chart.put("xAxis",xAxisChart);
	    chart.put("yAxis", yAxisChart);
	    chart.put("tooltip", tooltipChart);
	}catch(Exception ex){

	}
    }
    
    
    //GENERATE STACKED BAR & COLUMN CHART
    public void generateStackChart(String chartType, String dataChart, 
	    String titleChartName, String subtitleChartName, 
	    String categoriesTitleName, JSONObject chart,
	    ArrayList<String> listCategories, String toolTips){
	
	//SET CHART OPTIONS
	JSONArray categories = new JSONArray();
	
	JSONObject categoriesTitle = new JSONObject();
	
	JSONObject chartOptions = new JSONObject();
	JSONObject titleChart = new JSONObject();
	JSONObject subtitleChart = new JSONObject();
	JSONObject xAxisChart = new JSONObject();
	JSONObject yAxisChart = new JSONObject();
	JSONObject tooltipChart = new JSONObject();
	JSONObject plotOptions = new JSONObject();
	JSONObject dataLabels = new JSONObject();
	JSONObject stackLabels = new JSONObject();
	JSONObject columnPlot = new JSONObject();
	
	
	
	try{
	    chartOptions.put("type", chartType);
	    chartOptions.put("renderTo", dataChart);
	    titleChart.put("text", titleChartName);

	    subtitleChart.put("text", "");
	    categoriesTitle.put("text", "");
	    xAxisChart.put("categories", listCategories);
	    xAxisChart.put("title", categoriesTitle);

	    yAxisChart.put("min", 0);
	    yAxisChart.put("title", categoriesTitle);
	    stackLabels.put("enabled", "true");
	    yAxisChart.put("stackLabels", stackLabels);

	    tooltipChart.put("valueSuffix", " "+toolTips);
	    
	    dataLabels.put("enabled", "true");
	    columnPlot.put("stacking", "normal");
	    columnPlot.put("dataLabels", dataLabels);
	    plotOptions.put("column", columnPlot);

	    chart.put("chart", chartOptions);
	    chart.put("title", titleChart);
	    chart.put("subtitle", subtitleChart);
	    chart.put("xAxis",xAxisChart);
	    chart.put("yAxis", yAxisChart);
	    chart.put("tooltip", tooltipChart);
	    chart.put("plotOptions", plotOptions);
	}catch(Exception ex){

	}
    }
    
    public String drawTabelPendapatanPajak(Date currenDate, double pembagi, long oidPajakType, int month){
	
	    Vector resultHeader = PstPajakType.listAll();


	    ControlList ctrlist = new ControlList();

	    ctrlist.setAreaWidth("100%");

	    ctrlist.setListStyle("listarea");

	    ctrlist.setTitleStyle("listheader");

	    ctrlist.setCellStyle("table_cell");

	    ctrlist.setHeaderStyle("listheader");

	    ctrlist.setBorder(1);

	    ctrlist.addHeader("","10%");

	    DateFormatSymbols dfs = new DateFormatSymbols();
	    String[] monthName = dfs.getMonths();
	    if(monthName.length != 0){
		for (int i = 0; i < monthName.length-1; i++) {
		    ctrlist.addHeader(""+monthName[i].substring(0,3)+"-"+Formater.formatDate(currenDate,"yy"),"10%");
		}
	    }
	    ctrlist.addHeader("Total","10%");


	    ctrlist.setLinkRow(1);

	    ctrlist.setLinkSufix("");

	    Vector lstData = ctrlist.getData();

	    Vector lstLinkData = ctrlist.getLinkData();

	    ctrlist.setLinkPrefix("javascript:cmdEdit('");

	    ctrlist.setLinkSufix("')");

	    ctrlist.reset();

	    Vector rowx = new Vector();
	    rowx.add("Pencapaian");
	    double realisasiTotal = 0;
	    if(monthName.length != 0){
		for(int i = 0; i < monthName.length-1; i++){
		    String monthNumber = "";
		    if((i+1) > 9){
			monthNumber =""+(i+1);
		    }else{
			monthNumber = "0"+(i+1);
		    }
		    double realisasi = 0;
		    if((i+1)<= month){
			Vector listRealisasiPerJenisPajak = PstDataPajak.listPendapatanPerJenisPajak(0, 0,
				"EXTRACT(YEAR FROM dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+Formater.formatDate(currenDate, "yyyy")+"' "
				+ "AND EXTRACT(MONTH FROM dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+(i+1)+"' "
				+ "AND pajakType."+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_TYPE_ID]+"='"+oidPajakType+"'", 
				"", "pajakType."+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_NAME]);
			DataPajak dataPajak;
			if(listRealisasiPerJenisPajak.size() != 0){
			    dataPajak = (DataPajak) listRealisasiPerJenisPajak.get(0);
			}else{
			    dataPajak = new DataPajak();
			}

			realisasi = dataPajak.getJumlahPajak()/pembagi;
			
		    }else{
			realisasi = 0;
		    }
		    rowx.add(""+Formater.formatNumber(realisasi,"#.##"));
		    realisasiTotal+=realisasi;
		}
	    }
	    rowx.add(Formater.formatNumber(realisasiTotal,"#.##"));
	    lstData.add(rowx);

	    rowx = new Vector();
	    rowx.add("Target");
	    double targetTotal = 0;
	    if(monthName.length != 0){

		for(int i = 0; i < monthName.length-1; i++){
		    String monthNumber = "";
		    if((i+1) > 9){
			monthNumber =""+(i+1);
		    }else{
			monthNumber = "0"+(i+1);
		    }
		    double target = 0;
		    if((i+1) <= month){
			target = PstDataTarget.getTotalTargetPerPajakType(0, 0, 
			    "pajakDetail."+PstPajakTypeDetail.fieldNames[PstPajakTypeDetail.FLD_PAJAK_TYPE_ID]+"='"+oidPajakType+"' "
			    + "AND target."+PstDataTarget.fieldNames[PstDataTarget.FLD_TAHUN_TARGET]+"='"+Formater.formatDate(currenDate,"yyyy")+"' "
			    + "AND target."+PstDataTarget.fieldNames[PstDataTarget.FLD_BULAN]+"='"+i+"' ", "");
			target = target/pembagi;
		    }else{
			target = 0;
		    }
		    
		    rowx.add(""+Formater.formatNumber(target, "#.##"));
		    targetTotal +=  target;
		}


	    }
	    rowx.add(Formater.formatNumber(targetTotal, "#.##"));
	    lstData.add(rowx);

	    rowx = new Vector();
	    rowx.add("Diviasi");

	    if(monthName.length != 0){

		for(int i = 0; i < monthName.length-1; i++){
		    String monthNumber = "";
		    if((i+1) > 9){
			monthNumber =""+(i+1);
		    }else{
			monthNumber = "0"+(i+1);
		    }
		    
		    double target = 0;
		    double realisasi = 0;
		    if((i+1) <= month){
			target = PstDataTarget.getTotalTargetPerPajakType(0, 0, 
			    "pajakDetail."+PstPajakTypeDetail.fieldNames[PstPajakTypeDetail.FLD_PAJAK_TYPE_ID]+"='"+oidPajakType+"' "
			    + "AND target."+PstDataTarget.fieldNames[PstDataTarget.FLD_TAHUN_TARGET]+"='"+Formater.formatDate(currenDate,"yyyy")+"' "
			    + "AND target."+PstDataTarget.fieldNames[PstDataTarget.FLD_BULAN]+"='"+i+"' ", "");

			Vector listRealisasiPerJenisPajak = PstDataPajak.listPendapatanPerJenisPajak(0, 0,
				"EXTRACT(YEAR FROM dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+Formater.formatDate(currenDate, "yyyy")+"' "
				+ "AND EXTRACT(MONTH FROM dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+(i+1)+"' "
				+ "AND pajakType."+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_TYPE_ID]+"='"+oidPajakType+"'", 
				"", "pajakType."+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_NAME]);
			DataPajak dataPajak;
			if(listRealisasiPerJenisPajak.size() != 0){
			    dataPajak = (DataPajak) listRealisasiPerJenisPajak.get(0);
			}else{
			    dataPajak = new DataPajak();
			}
			realisasi = dataPajak.getJumlahPajak()/pembagi;
		    }else{
			target=0;
			realisasi=0;
		    }
		    
		    rowx.add(""+Formater.formatNumber(realisasi-target, "#.##"));
		}


	    }
	    rowx.add(Formater.formatNumber(realisasiTotal-targetTotal, "#.##"));
	    lstData.add(rowx);

	    rowx = new Vector();
	    rowx.add("Pencapaian %");

	    if(monthName.length != 0){

		for(int i = 0; i < monthName.length-1; i++){
		    String monthNumber = "";
		    if((i+1) > 9){
			monthNumber =""+(i+1);
		    }else{
			monthNumber = "0"+(i+1);
		    }
		    
		    double target = 0;
		    double realisasi = 0;
		    if((i+1) <= month){
			
		    }else{
			target = PstDataTarget.getTotalTargetPerPajakType(0, 0, 
			    "pajakDetail."+PstPajakTypeDetail.fieldNames[PstPajakTypeDetail.FLD_PAJAK_TYPE_ID]+"='"+oidPajakType+"' "
			    + "AND target."+PstDataTarget.fieldNames[PstDataTarget.FLD_TAHUN_TARGET]+"='"+Formater.formatDate(currenDate,"yyyy")+"' "
			    + "AND target."+PstDataTarget.fieldNames[PstDataTarget.FLD_BULAN]+"='"+i+"' ", "");

			Vector listRealisasiPerJenisPajak = PstDataPajak.listPendapatanPerJenisPajak(0, 0,
				"EXTRACT(YEAR FROM dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+Formater.formatDate(currenDate, "yyyy")+"' "
				+ "AND EXTRACT(MONTH FROM dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+(i+1)+"' "
				+ "AND pajakType."+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_TYPE_ID]+"='"+oidPajakType+"'", 
				"", "pajakType."+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_NAME]);
			DataPajak dataPajak;
			if(listRealisasiPerJenisPajak.size() != 0){
			    dataPajak = (DataPajak) listRealisasiPerJenisPajak.get(0);
			}else{
			    dataPajak = new DataPajak();
			}
			realisasi = dataPajak.getJumlahPajak()/pembagi;
		    }
		    
		    if(target != 0){
			rowx.add(""+Formater.formatNumber((realisasi/target)*100, "#.##")+"%");
		    }else{
			rowx.add("0%");
		    }
		    
		}


	    }
	    rowx.add(Formater.formatNumber((realisasiTotal/targetTotal)*100,"#.##")+"%");
	    lstData.add(rowx);


	    return ctrlist.drawBootstrapStrip();

    }
    public String drawTabelPendapatanPajak(double pembagi, String tahun){
	
	    Vector resultHeader = PstPajakTypeDetail.listJoin(0, 0, 
		    "pajakType."+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_TYPE_ID]+"='"+PstPajakType.PHR+"'", "");


	    ControlList ctrlist = new ControlList();

	    ctrlist.setAreaWidth("100%");

	    ctrlist.setListStyle("listarea");

	    ctrlist.setTitleStyle("listheader");

	    ctrlist.setCellStyle("table_cell");

	    ctrlist.setHeaderStyle("listheader");

	    ctrlist.setBorder(1);

	    ctrlist.addHeader("","10%");

	    if((resultHeader!=null)&&(resultHeader.size()!=0)){
		for (int i = 0; i < resultHeader.size(); i++) {
		    PajakTypeDetail pajakType = (PajakTypeDetail) resultHeader.get(i);
		    ctrlist.addHeader(""+pajakType.getPajakDetailName(),"10%");
		}
	    }
	    ctrlist.addHeader("Total","10%");


	    ctrlist.setLinkRow(1);

	    ctrlist.setLinkSufix("");

	    Vector lstData = ctrlist.getData();

	    Vector lstLinkData = ctrlist.getLinkData();

	    ctrlist.setLinkPrefix("javascript:cmdEdit('");

	    ctrlist.setLinkSufix("')");

	    ctrlist.reset();

	    Vector rowx = new Vector();
	    
	    rowx.add("Realisasi YTD");
	    ArrayList<Double> listRealisasi = new ArrayList<Double>();
	    ArrayList<Double> listTarget = new ArrayList<Double>();
	    double realisasiTotal = 0;
	    if(resultHeader.size() != 0){
		for(int i = 0; i < resultHeader.size(); i++){
		    PajakTypeDetail pajakTypeDetail = (PajakTypeDetail) resultHeader.get(i);
		    Vector listRealisasiPerJenisPajak = PstDataPajak.listPendapatanPerJenisPajakDetail(0, 0,
			    "EXTRACT(YEAR FROM dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+tahun+"' "
			    + "AND pajakDetail."+PstPajakTypeDetail.fieldNames[PstPajakTypeDetail.FLD_PAJAK_DETAIL_ID]+"='"+pajakTypeDetail.getOID()+"'", 
			    "", "pajakDetail."+PstPajakTypeDetail.fieldNames[PstPajakTypeDetail.FLD_PAJAK_DETAIL_NAME]);
		    DataPajak dataPajak;
		    if(listRealisasiPerJenisPajak.size() != 0){
			dataPajak = (DataPajak) listRealisasiPerJenisPajak.get(0);
		    }else{
			dataPajak = new DataPajak();
		    }

		    double realisasi = dataPajak.getJumlahPajak()/pembagi;
		    rowx.add(""+Formater.formatNumber(realisasi,"#.##"));
		    realisasiTotal+=realisasi;
		    listRealisasi.add(realisasi);
		}
	    }
	    rowx.add(""+Formater.formatNumber(realisasiTotal,"#.##"));
	    lstData.add(rowx);
	    
	    rowx = new Vector();
	    rowx.add("Target "+tahun);
	    double targetTotal = 0;
	    if(resultHeader.size() != 0){

		for(int i = 0; i < resultHeader.size(); i++){
		    PajakTypeDetail pajakType = (PajakTypeDetail) resultHeader.get(i);
		    double target = PstDataTarget.getTotalTargetPerPajakType(0, 0, 
			    "pajakDetail."+PstPajakTypeDetail.fieldNames[PstPajakTypeDetail.FLD_PAJAK_DETAIL_ID]+"='"+pajakType.getOID()+"' "
			    + "AND target."+PstDataTarget.fieldNames[PstDataTarget.FLD_TAHUN_TARGET]+"='"+tahun+"'", "");
		    target = target/pembagi;
		    rowx.add(""+Formater.formatNumber(target, "#.##"));
		    targetTotal +=  target;
		    listTarget.add(target);
		}


	    }
	    rowx.add(Formater.formatNumber(targetTotal, "#.##"));
	    lstData.add(rowx);
	    
	    rowx = new Vector(1,1);
	    rowx.add("Deviasi");
	    if(resultHeader.size() != 0){

		for(int i = 0; i < resultHeader.size(); i++){
		    double deviasi = listRealisasi.get(i)-listTarget.get(i);
		    rowx.add(""+Formater.formatNumber(deviasi,"#.##"));
		}
	    }
	    rowx.add(""+Formater.formatNumber(realisasiTotal-targetTotal,"#.##"));
	    lstData.add(rowx);
	    
	    rowx = new Vector(1,1);
	    rowx.add("% Realisasi");
	    if(resultHeader.size() != 0){

		for(int i = 0; i < resultHeader.size(); i++){
		    double persenRealisasi = 0;
		    if(listTarget.get(i) != 0){
			persenRealisasi = (listRealisasi.get(i)/listTarget.get(i))*100;
		    }
		    
		    rowx.add(""+Formater.formatNumber(persenRealisasi,"#.##")+"%");
		}
	    }
	    double persenRealisasiTotal = 0;
	    if(targetTotal != 0){
		persenRealisasiTotal = (realisasiTotal/targetTotal)*100;
		rowx.add(""+Formater.formatNumber(persenRealisasiTotal,"#.##")+"%");
	    }
	    
	    lstData.add(rowx);
	    
	    rowx = new Vector(1,1);
	    rowx.add("% Sisa Target");
	    if(resultHeader.size() != 0){

		for(int i = 0; i < resultHeader.size(); i++){
		    double persenRealisasi = 100;
		    if(listTarget.get(i) != 0){
			persenRealisasi = 100-((listRealisasi.get(i)/listTarget.get(i))*100);
		    }
		    
		    rowx.add(""+Formater.formatNumber(persenRealisasi,"#.##")+"%");
		}
	    }
	    if(targetTotal != 0){
		persenRealisasiTotal = 100-((realisasiTotal/targetTotal)*100);
		rowx.add(""+Formater.formatNumber(persenRealisasiTotal,"#.##")+"%");
	    }
	    
	    lstData.add(rowx);
	    
	    return ctrlist.drawBootstrapStrip();

    }
    
    public String drawTabelPerbandingan2Tahun(Date currenDate, double pembagi, String[] perbandingan){
	
	    ControlList ctrlist = new ControlList();

	    ctrlist.setAreaWidth("100%");

	    ctrlist.setListStyle("listarea");

	    ctrlist.setTitleStyle("listheader");

	    ctrlist.setCellStyle("table_cell");

	    ctrlist.setHeaderStyle("listheader");

	    ctrlist.setBorder(1);

	    ctrlist.addHeader("","10%");
	    if(perbandingan.length != 0){
		for(int i = 0; i < perbandingan.length; i++){
		    ctrlist.addHeader("Total "+perbandingan[i],"10%");
		}
	    }
	    ctrlist.addHeader("Selisih","10%");

	    ctrlist.setLinkRow(1);

	    ctrlist.setLinkSufix("");

	    Vector lstData = ctrlist.getData();

	    Vector lstLinkData = ctrlist.getLinkData();

	    ctrlist.setLinkPrefix("javascript:cmdEdit('");

	    ctrlist.setLinkSufix("')");

	    ctrlist.reset();

	    Vector rowx = new Vector();
	    Vector listPajakType = PstPajakType.listAll();
	    
	    if(listPajakType.size() != 0){
		for(int i = 0; i < listPajakType.size(); i++){
		    PajakType pajakType = (PajakType) listPajakType.get(i);
		    rowx = new Vector();
		    ArrayList<Double> getPerbandingan = new ArrayList<Double>();
		    rowx.add(""+pajakType.getPajakTypeName());
		    
		    if(perbandingan.length != 0){
			for(int p = 0; p < perbandingan.length; p++){
			    String whereClause = "";
			    if(AppSetting.SQL_VERSION == AppSetting.DBSVR_ORACLE){
				whereClause= whereClause+" EXTRACT(YEAR FROM dataPajak."+ PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+perbandingan[p]+"' AND pajakType."+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_TYPE_ID]+"='"+pajakType.getOID()+"'";
			    }else{
				 whereClause = whereClause+" EXTRACT(YEAR FROM dataPajak."+ PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+perbandingan[p]+"' AND pajakType."+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_TYPE_ID]+"='"+pajakType.getOID()+"'";
			    }
			    double realisasi = PstDataPajak.getTotalRealisasiPerJenisPajak(0, 0, whereClause, "");
			    realisasi = realisasi/pembagi;
			    rowx.add(""+Formater.formatNumber(realisasi,"#.##"));
			    getPerbandingan.add(realisasi);
			}
		    }
		    
		    double selisih = 0;
		    if(getPerbandingan.size() != 0){
			for(int p = getPerbandingan.size()-1; p >= 0; p--){
			    if((p+1) == getPerbandingan.size()){
				selisih+= getPerbandingan.get(p);
			    }else{
				selisih-=getPerbandingan.get(p);
			    }
			}
		    }
		    rowx.add(""+Formater.formatNumber(selisih,"#.##"));
		    lstData.add(rowx);
		}
	    }
	    return ctrlist.drawBootstrapStrip();

    }
    public String drawTabelPerbandingan3Tahun(Date currenDate, double pembagi, String[] perbandingan){
	
	    ControlList ctrlist = new ControlList();

	    ctrlist.setAreaWidth("100%");

	    ctrlist.setListStyle("listarea");

	    ctrlist.setTitleStyle("listheader");

	    ctrlist.setCellStyle("table_cell");

	    ctrlist.setHeaderStyle("listheader");

	    ctrlist.setBorder(1);

	    ctrlist.addHeader("","10%");
	    if(perbandingan.length != 0){
		for(int i = 0; i < perbandingan.length; i++){
		    ctrlist.addHeader("Total "+perbandingan[i],"10%");
		}
	    }

	    ctrlist.setLinkRow(1);

	    ctrlist.setLinkSufix("");

	    Vector lstData = ctrlist.getData();

	    Vector lstLinkData = ctrlist.getLinkData();

	    ctrlist.setLinkPrefix("javascript:cmdEdit('");

	    ctrlist.setLinkSufix("')");

	    ctrlist.reset();

	    Vector rowx = new Vector();
	    Vector listPajakType = PstPajakType.listAll();
	    
	    if(listPajakType.size() != 0){
		Vector listRealisasi = new Vector(1,1);
		for(int i = 0; i < listPajakType.size(); i++){
		    PajakType pajakType = (PajakType) listPajakType.get(i);
		    rowx = new Vector();
		    ArrayList<Double> getPerbandingan = new ArrayList<Double>();
		    rowx.add(""+pajakType.getPajakTypeName());
		    
		    if(perbandingan.length != 0){
			for(int p = 0; p < perbandingan.length; p++){
			    String whereClause = "";
			    if(AppSetting.SQL_VERSION == AppSetting.DBSVR_ORACLE){
				whereClause= whereClause+" EXTRACT(YEAR FROM dataPajak."+ PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+perbandingan[p]+"' AND pajakType."+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_TYPE_ID]+"='"+pajakType.getOID()+"'";
			    }else{
				 whereClause = whereClause+" EXTRACT(YEAR FROM dataPajak."+ PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+perbandingan[p]+"' AND pajakType."+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_TYPE_ID]+"='"+pajakType.getOID()+"'";
			    }
			    double realisasi = PstDataPajak.getTotalRealisasiPerJenisPajak(0, 0, whereClause, "");
			    realisasi = realisasi/pembagi;
			    rowx.add(""+Formater.formatNumber(realisasi,"#.##"));
			    getPerbandingan.add(realisasi);
			}
		    }
		    lstData.add(rowx);
		}
		rowx = new Vector();
		rowx.add("Total");
		if(perbandingan.length != 0){
		    for(int p = 0; p < perbandingan.length; p++){
			String whereClause = "";
			if(AppSetting.SQL_VERSION == AppSetting.DBSVR_ORACLE){
			    whereClause= whereClause+" EXTRACT(YEAR FROM dataPajak."+ PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+perbandingan[p]+"'";
			}else{
			     whereClause = whereClause+" EXTRACT(YEAR FROM dataPajak."+ PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+perbandingan[p]+"'";
			}
			double realisasi = PstDataPajak.getTotalRealisasiPerJenisPajak(0, 0, whereClause, "");
			realisasi = realisasi/pembagi;
			rowx.add(""+Formater.formatNumber(realisasi,"#.##"));
			listRealisasi.add(realisasi);
		    }
		}
		lstData.add(rowx);
		
		rowx = new Vector();
		rowx.add("% Up");
		if(listRealisasi.size() != 0){
		    for(int p = 0; p < listRealisasi.size(); p++){
			double persenUp = 0;
			if(p != 0){
			    if(Double.parseDouble(listRealisasi.get(p-1).toString()) != 0){
				double lastRealisasi = Double.parseDouble(listRealisasi.get(p-1).toString());
				double currentRealisasi = Double.parseDouble(listRealisasi.get(p).toString());
				persenUp = ((currentRealisasi-lastRealisasi)/lastRealisasi)*100;
			    }
			}
			rowx.add(""+Formater.formatNumber(persenUp,"#.##")+"%");
		    }
		}
		lstData.add(rowx);
	    }
	    return ctrlist.drawBootstrapStrip();

    }
    
    public String drawTabelProyeksiPendapatan(Date currenDate, double pembagi, String[] perbandingan){
	
	    ControlList ctrlist = new ControlList();

	    ctrlist.setAreaWidth("100%");

	    ctrlist.setListStyle("listarea");

	    ctrlist.setTitleStyle("listheader");

	    ctrlist.setCellStyle("table_cell");

	    ctrlist.setHeaderStyle("listheader");

	    ctrlist.setBorder(1);

	    ctrlist.addHeader("","10%");
	    if(perbandingan.length != 0){
		for(int i = 0; i < perbandingan.length; i++){
		    ctrlist.addHeader("Total "+perbandingan[i],"10%");
		}
	    }

	    ctrlist.setLinkRow(1);

	    ctrlist.setLinkSufix("");

	    Vector lstData = ctrlist.getData();

	    Vector lstLinkData = ctrlist.getLinkData();

	    ctrlist.setLinkPrefix("javascript:cmdEdit('");

	    ctrlist.setLinkSufix("')");

	    ctrlist.reset();

	    Vector rowx = new Vector();
	    Vector listPajakType = PstPajakType.listAll();
	    
	    if(listPajakType.size() != 0){
		Vector listRealisasi = new Vector(1,1);
		for(int i = 0; i < listPajakType.size(); i++){
		    PajakType pajakType = (PajakType) listPajakType.get(i);
		    rowx = new Vector();
		    ArrayList<Double> getPerbandingan = new ArrayList<Double>();
		    rowx.add(""+pajakType.getPajakTypeName());
		    
		    if(perbandingan.length != 0){
			for(int p = 0; p < perbandingan.length; p++){
			    String whereClause = "";
			    
			    
			    if(p == perbandingan.length-1){
				
				double realisasi = PstDataPajak.getTotalRealisasiPerJenisPajak(0, 0, 
					"EXTRACT(YEAR FROM dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+perbandingan[p-1]+"' "
					+ "AND pajakType."+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_TYPE_ID]+"='"+pajakType.getOID()+"'", "");
				
				double target = PstDataTarget.getTotalKenaikanPerPajakType(0, 0, 
					"target."+PstDataTarget.fieldNames[PstDataTarget.FLD_TAHUN_TARGET]+"='"+perbandingan[p]+"' "
					+ "AND pajakDetail."+PstPajakTypeDetail.fieldNames[PstPajakTypeDetail.FLD_PAJAK_TYPE_ID]+"='"+pajakType.getOID()+"'", "");
				
				double proyeksi = (realisasi+((realisasi*target)/100))/pembagi;
				rowx.add(""+Formater.formatNumber(proyeksi,"#.##"));
				getPerbandingan.add(proyeksi);
			    }else{
				if(AppSetting.SQL_VERSION == AppSetting.DBSVR_ORACLE){
				    whereClause= whereClause+" EXTRACT(YEAR FROM dataPajak."+ PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+perbandingan[p]+"' AND pajakType."+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_TYPE_ID]+"='"+pajakType.getOID()+"'";
				}else{
				     whereClause = whereClause+" EXTRACT(YEAR FROM dataPajak."+ PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+perbandingan[p]+"' AND pajakType."+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_TYPE_ID]+"='"+pajakType.getOID()+"'";
				}
				double realisasi = PstDataPajak.getTotalRealisasiPerJenisPajak(0, 0, whereClause, "");
				realisasi = realisasi/pembagi;
				rowx.add(""+Formater.formatNumber(realisasi,"#.##"));
				getPerbandingan.add(realisasi);
			    }
			}
		    }
		    lstData.add(rowx);
		}
		rowx = new Vector();
		rowx.add("");
		if(perbandingan.length != 0){
		    for(int p = 0; p < perbandingan.length; p++){
			String whereClause = "";
			if(p == perbandingan.length-1){
				
			    double realisasi = PstDataPajak.getTotalRealisasiPerJenisPajak(0, 0, 
				    "EXTRACT(YEAR FROM dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+perbandingan[p-1]+"' ", "");

			    double target = PstDataTarget.getTotalKenaikanPerPajakType(0, 0, 
				    "target."+PstDataTarget.fieldNames[PstDataTarget.FLD_TAHUN_TARGET]+"='"+perbandingan[p]+"' ", "");

			    double proyeksi = (realisasi+((realisasi*target)/100))/pembagi;
			    rowx.add(""+Formater.formatNumber(proyeksi,"#.##"));
			}else{
			    if(AppSetting.SQL_VERSION == AppSetting.DBSVR_ORACLE){
				whereClause= whereClause+" EXTRACT(YEAR FROM dataPajak."+ PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+perbandingan[p]+"'";
			    }else{
				 whereClause = whereClause+" EXTRACT(YEAR FROM dataPajak."+ PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+perbandingan[p]+"'";
			    }
			    double realisasi = PstDataPajak.getTotalRealisasiPerJenisPajak(0, 0, whereClause, "");
			    realisasi = realisasi/pembagi;
			    rowx.add(""+Formater.formatNumber(realisasi,"#.##"));
			}
		    }
		}
		lstData.add(rowx);
	    }
	    return ctrlist.drawBootstrapStrip();

    }
    
    public String drawTabelPerbandinganPerBulan(Date currenDate, double pembagi, String[] perbandingan){
	
	    ControlList ctrlist = new ControlList();

	    ctrlist.setAreaWidth("100%");

	    ctrlist.setListStyle("listarea");

	    ctrlist.setTitleStyle("listheader");

	    ctrlist.setCellStyle("table_cell");

	    ctrlist.setHeaderStyle("listheader");

	    ctrlist.setBorder(1);

	    ctrlist.addHeader("","10%");
	    if(perbandingan.length != 0){
		for(int i = 0; i < perbandingan.length; i++){
		    ctrlist.addHeader("Total "+perbandingan[i],"10%");
		}
	    }
	    ctrlist.addHeader("Target "+perbandingan[perbandingan.length-1]);
	    ctrlist.addHeader("Selisih","10%");

	    ctrlist.setLinkRow(1);

	    ctrlist.setLinkSufix("");

	    Vector lstData = ctrlist.getData();

	    Vector lstLinkData = ctrlist.getLinkData();

	    ctrlist.setLinkPrefix("javascript:cmdEdit('");

	    ctrlist.setLinkSufix("')");

	    ctrlist.reset();

	    DateFormatSymbols dfs = new DateFormatSymbols();
	    String[] monthName = dfs.getMonths();
	    Vector rowx = new Vector();
	    if(monthName.length != 0){
		for(int i = 0; i < monthName.length-1; i++){
		    rowx = new Vector();
		    ArrayList<Double> getPerbandingan = new ArrayList<Double>();
		    rowx.add(""+monthName[i]);
		    double selisih = 0;
		    if(perbandingan.length != 0){
			for(int p = 0; p < perbandingan.length; p++){
			    String whereClause = "";
			    if(AppSetting.SQL_VERSION == AppSetting.DBSVR_ORACLE){
				whereClause= whereClause+" EXTRACT(YEAR FROM dataPajak."+ PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+perbandingan[p]+"' AND EXTRACT(MONTH FROM dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+(i+1)+"'";
			    }else{
				 whereClause = whereClause+" EXTRACT(YEAR FROM dataPajak."+ PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+perbandingan[p]+"' AND EXTRACT(MONTH FROM dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+(i+1)+"'";
			    }
			    double realisasi = PstDataPajak.getTotalRealisasiPerJenisPajak(0, 0, whereClause, "");
			    realisasi = realisasi/pembagi;
			    rowx.add(""+Formater.formatNumber(realisasi,"#.##"));
			    
			    if(p == perbandingan.length-1){
				double target = PstDataTarget.getTotalTarget(0, 0,
					PstDataTarget.fieldNames[PstDataTarget.FLD_TAHUN_TARGET]+"='"+perbandingan[p]+"' "
					+ "AND "+PstDataTarget.fieldNames[PstDataTarget.FLD_BULAN]+"='"+i+"'", "");
				target = target/pembagi;
				selisih = target-realisasi;
				rowx.add(""+Formater.formatNumber(target,"#.##"));
				rowx.add(""+Formater.formatNumber(selisih,"#.###"));
			    }
			    getPerbandingan.add(realisasi);
			}
		    }
		    lstData.add(rowx);
		}
	    }
	    return ctrlist.drawBootstrapStrip();

    }
    
    public String drawTabelPendapatanPajakPHR(String tahun, double pembagi, long oidPajakType, int month){
	
	    Vector resultHeader = PstPajakTypeDetail.listJoin(0,0,
		    "pajakType."+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_TYPE_ID]+"='"+PstPajakType.PHR+"'","");


	    ControlList ctrlist = new ControlList();

	    ctrlist.setAreaWidth("100%");

	    ctrlist.setListStyle("listarea");

	    ctrlist.setTitleStyle("listheader");

	    ctrlist.setCellStyle("table_cell");

	    ctrlist.setHeaderStyle("listheader");

	    ctrlist.setBorder(1);

	    ctrlist.addHeader("Periode","10%");

	    DateFormatSymbols dfs = new DateFormatSymbols();
	    String[] monthName = dfs.getMonths();
	    if(monthName.length != 0){
		for (int i = 0; i < monthName.length-1; i++) {
		    ctrlist.addHeader(""+monthName[i].substring(0,3)+"-"+tahun.substring(2),"10%");
		}
	    }


	    ctrlist.setLinkRow(1);

	    ctrlist.setLinkSufix("");

	    Vector lstData = ctrlist.getData();

	    Vector lstLinkData = ctrlist.getLinkData();

	    ctrlist.setLinkPrefix("javascript:cmdEdit('");

	    ctrlist.setLinkSufix("')");

	    ctrlist.reset();

	    Vector rowx = new Vector();
	    if(resultHeader.size() != 0){
		for(int phr = 0; phr < resultHeader.size(); phr++){
		    PajakTypeDetail pajakType = (PajakTypeDetail) resultHeader.get(phr);
		    rowx = new Vector();
		    rowx.add(""+pajakType.getPajakDetailName());
		    double realisasiTotal = 0;
		    double realisasi = 0;
		    if(monthName.length != 0){
			for(int i = 0; i < monthName.length-1; i++){
			    String monthNumber = "";
			    if((i+1) > 9){
				monthNumber =""+(i+1);
			    }else{
				monthNumber = "0"+(i+1);
			    }
			    
			    if((i+1)<= month){
				Vector listRealisasiPerJenisPajak = PstDataPajak.listPendapatanPerJenisPajakDetail(0, 0,
					"EXTRACT(YEAR FROM dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+tahun+"' "
					+ "AND EXTRACT(MONTH FROM dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+(i+1)+"' "
					+ "AND pajakDetail."+PstPajakTypeDetail.fieldNames[PstPajakTypeDetail.FLD_PAJAK_DETAIL_ID]+"='"+pajakType.getOID()+"'", 
					"", "pajakDetail."+PstPajakTypeDetail.fieldNames[PstPajakTypeDetail.FLD_PAJAK_DETAIL_NAME]);
				DataPajak dataPajak;
				if(listRealisasiPerJenisPajak.size() != 0){
				    dataPajak = (DataPajak) listRealisasiPerJenisPajak.get(0);
				}else{
				    dataPajak = new DataPajak();
				}

				realisasi += dataPajak.getJumlahPajak()/pembagi;

			    }else{
				realisasi += 0;
			    }
			    rowx.add(""+Formater.formatNumber(realisasi,"#.##"));
			    realisasiTotal+=realisasi;
			}
		    }
		    lstData.add(rowx);
		}
	    }
	    
	    

	    rowx = new Vector();
	    rowx.add("Total");
	    
	    double realisasiTotal = 0;
	    if(monthName.length != 0){
		for(int i = 0; i < monthName.length-1; i++){
		    String monthNumber = "";
		    if((i+1) > 9){
			monthNumber =""+(i+1);
		    }else{
			monthNumber = "0"+(i+1);
		    }
		    double realisasi = 0;
		    if((i+1)<= month){
			Vector listRealisasiPerJenisPajak = PstDataPajak.listPendapatanPerJenisPajak(0, 0,
				"EXTRACT(YEAR FROM dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+tahun+"' "
				+ "AND EXTRACT(MONTH FROM dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+(i+1)+"' "
				+ "AND pajakType."+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_TYPE_ID]+"='"+PstPajakType.PHR+"'", 
				"", "pajakDetail."+PstPajakTypeDetail.fieldNames[PstPajakTypeDetail.FLD_PAJAK_DETAIL_NAME]);
			DataPajak dataPajak;
			if(listRealisasiPerJenisPajak.size() != 0){
			    dataPajak = (DataPajak) listRealisasiPerJenisPajak.get(0);
			}else{
			    dataPajak = new DataPajak();
			}

			realisasi += dataPajak.getJumlahPajak()/pembagi;

		    }else{
			realisasi += 0;
		    }
		    realisasiTotal+=realisasi;
		    rowx.add(""+Formater.formatNumber(realisasiTotal,"#.##"));
		}
		
	    }
	    lstData.add(rowx);


	    return ctrlist.drawBootstrapStrip();

    }
    
    public String drawTableTargetPajak(String tahun, double divine){
	String tabelHTML = "";
	tabelHTML+=""
	+ "<table class='table table-bordered table-striped'>"
	    + "<thead>"
		+ "<tr>"
		    + "<th rowspan='2'>Bulan</th>";
		    Vector listPajakType = PstPajakType.listAll();
		    if(listPajakType.size() != 0){
			for(int i = 0; i < listPajakType.size(); i++){
			    PajakType pajakType = (PajakType) listPajakType.get(i);
			    if(pajakType.getOID() == PstPajakType.PHR){
				Vector listPajakDetail = PstPajakTypeDetail.list(0, 0, 
					""+PstPajakTypeDetail.fieldNames[PstPajakTypeDetail.FLD_PAJAK_TYPE_ID]+"='"+pajakType.getOID()+"'", "");
				tabelHTML +=""
				+ "<th colspan='"+(listPajakDetail.size()+1)+"' align='center'>"
				    + ""+pajakType.getPajakTypeName()+""
				+ "</th>";
			    }else{
				tabelHTML +=""
				+ "<th rowspan='2' align='center'>"
				    + ""+pajakType.getPajakTypeName()+""
				+ "</th>";
			    }
			}
		    }
		    tabelHTML+=""
		    + "<th rowspan='2' align='center'>"
			+ "Total Target"
		    + "</th>"
		    + ""
		    + "</tr>"
		    + "<tr>";
		
			Vector listPajakDetail = PstPajakTypeDetail.list(0, 0, 
				""+PstPajakTypeDetail.fieldNames[PstPajakTypeDetail.FLD_PAJAK_TYPE_ID]+"='"+PstPajakType.PHR+"'", "");
			if(listPajakDetail.size() != 0){
			    for(int pd = 0; pd < listPajakDetail.size(); pd++){
				PajakTypeDetail pajakTypeDetail = (PajakTypeDetail) listPajakDetail.get(pd);
				tabelHTML+=""
				+ "<th>"
				    + ""+pajakTypeDetail.getPajakDetailName()
				+ "</th>";
			    }
			}
			tabelHTML+=""
			+ "<th>"
			    + "Total PHR"
			+ "</th>"
		    + "</tr>"
		+ "</thead>";
		    
		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] monthName = dfs.getMonths();
		
		tabelHTML+=""
		+ "<tbody>";
		if(monthName.length != 0){
		    for(int i = 0; i < monthName.length-1; i++){
			tabelHTML+=""
			+ "<tr>"
			    + "<td>"+monthName[i]+"</td>";
			    if(listPajakType.size() != 0){
				double target = 0;
				double totalTarget=0;
				for(int pt = 0; pt < listPajakType.size(); pt++){
				    PajakType pajakType = (PajakType) listPajakType.get(pt);
				    if(pajakType.getOID() == PstPajakType.PHR){
					listPajakDetail =  PstPajakTypeDetail.list(0, 0, 
						""+PstPajakTypeDetail.fieldNames[PstPajakTypeDetail.FLD_PAJAK_TYPE_ID]+"='"+pajakType.getOID()+"'", 
						"");
					if(listPajakDetail.size() != 0){
					    for(int pd = 0; pd < listPajakDetail.size(); pd++){
						PajakTypeDetail pajakTypeDetail = (PajakTypeDetail) listPajakDetail.get(pd);
						target = PstDataTarget.getTotalTarget(0, 0, 
							""+PstDataTarget.fieldNames[PstDataTarget.FLD_TAHUN_TARGET]+"='"+tahun+"' "
							+ "AND "+PstDataTarget.fieldNames[PstDataTarget.FLD_BULAN]+"='"+i+"' "
							+ "AND "+PstDataTarget.fieldNames[PstDataTarget.FLD_PAJAK_DETAIL_ID]+"='"+pajakTypeDetail.getOID()+"'","");
						target = target/divine;
						tabelHTML+=""
						+ "<td>"
						    + ""+Formater.formatNumber(target, "#.##")
						+ "</td>";
					    }
					}
				    }
				    
					
				    target = PstDataTarget.getTotalTargetPerPajakType(0, 0, 
					    "target."+PstDataTarget.fieldNames[PstDataTarget.FLD_BULAN]+"='"+i+"' "
					    + "AND target."+PstDataTarget.fieldNames[PstDataTarget.FLD_TAHUN_TARGET]+"='"+tahun+"' "
					    + "AND pajakDetail."+PstPajakTypeDetail.fieldNames[PstPajakTypeDetail.FLD_PAJAK_TYPE_ID]+"='"+pajakType.getOID()+"'", "");
				    target = target/divine;
				    tabelHTML+=""
				    + "<td>"
					    + ""+Formater.formatNumber(target,"#.##")
				    + "</td>";
				    totalTarget+=target;
				}
				
				tabelHTML+=""
				+ "<td>"
				    + ""+Formater.formatNumber(totalTarget,"#.##")
				+ "</td>"
			    + "</tr>";
			    
			}
		    }
		}
		tabelHTML+=""
		+ "</tbody>"
	    + "</table>";
	return tabelHTML;
    }
    
    public String drawTabelTop10WP(String tahun, double pembagi, long oidPajakType, int month, long oidPajakTypeDetail, String pajakTypeDetailName){
	
	    ControlList ctrlist = new ControlList();

	    ctrlist.setAreaWidth("100%");

	    ctrlist.setListStyle("listarea");

	    ctrlist.setTitleStyle("listheader");

	    ctrlist.setCellStyle("table_cell");

	    ctrlist.setHeaderStyle("listheader");

	    ctrlist.setBorder(1);

	    ctrlist.addHeader("Urutan","10%");
	    ctrlist.addHeader("Nama WP","10%");

	    DateFormatSymbols dfs = new DateFormatSymbols();
	    String[] monthName = dfs.getMonths();
	    if(monthName.length != 0){
		for (int i = 0; i < monthName.length-1; i++) {
		    ctrlist.addHeader(""+monthName[i].substring(0,3)+"-"+tahun.substring(2),"10%");
		}
	    }
	    ctrlist.addHeader("Total","10%");
	    ctrlist.addHeader("%","10%");

	    ctrlist.setLinkRow(1);

	    ctrlist.setLinkSufix("");

	    Vector lstData = ctrlist.getData();

	    Vector lstLinkData = ctrlist.getLinkData();

	    ctrlist.setLinkPrefix("javascript:cmdEdit('");

	    ctrlist.setLinkSufix("')");

	    ctrlist.reset();
	    
	    Vector listWP = PstDataPajak.listPajakWP(0, 0, 
		"EXTRACT(YEAR FROM "+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+tahun+"' "
		+ "AND "+PstDataTarget.fieldNames[PstDataPajak.FLD_PAJAKDETAILID]+"='"+oidPajakTypeDetail+"'", 
		PstDataPajak.fieldNames[PstDataPajak.FLD_JUMLAHPAJAK]+" DESC", 
		PstDataPajak.fieldNames[PstDataPajak.FLD_ID]+","+PstDataPajak.fieldNames[PstDataPajak.FLD_NAMA]);
	    double totalPajak = PstDataPajak.getTotalRealisasi(0, 0,
		    "EXTRACT(YEAR FROM "+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+tahun+"' "
		    + "AND "+PstDataPajak.fieldNames[PstDataPajak.FLD_PAJAKDETAILID]+"='"+oidPajakTypeDetail+"'", "");
	    totalPajak = totalPajak/pembagi;
	    
	    
	    String listWPClause = "";
	    double grandTotal = 0;
	    Vector rowx = new Vector();
            int batas = 0;
            if(listWP.size() >= 10){
                batas = 10;
            }else{
                batas = listWP.size();
            }
	    if(listWP.size() != 0){
		int noUrut = 1;
		for(int phr = 0; phr < batas; phr++){
		    DataPajak dataPajak = (DataPajak) listWP.get(phr);
		    
		    if(listWPClause.length() == 0){
			listWPClause += ""+PstDataPajak.fieldNames[PstDataPajak.FLD_ID]+"='"+dataPajak.getId()+"'";
		    }else{
			listWPClause += " OR "+PstDataPajak.fieldNames[PstDataPajak.FLD_ID]+"='"+dataPajak.getId()+"'";
		    }
		    
		    rowx = new Vector();
		    rowx.add(""+noUrut);
		    rowx.add(""+dataPajak.getNama());
		    double realisasiTotal = 0;
		    
		    if(monthName.length != 0){
			for(int i = 0; i < monthName.length-1; i++){
			    String monthNumber = "";
			    if((i+1) > 9){
				monthNumber =""+(i+1);
			    }else{
				monthNumber = "0"+(i+1);
			    }
			    
			    double realisasi = 0;
			    realisasi = PstDataPajak.getTotalRealisasiPerJenisPajak(0, 0,
				    "EXTRACT(YEAR FROM dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+tahun+"' "
				    + "AND EXTRACT(MONTH FROM dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+(i+1)+"' "
				    + "AND pajakDetail."+PstPajakTypeDetail.fieldNames[PstPajakTypeDetail.FLD_PAJAK_DETAIL_ID]+"='"+oidPajakTypeDetail+"' "
				    + "AND dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_ID]+"='"+dataPajak.getId()+"'","");


			    realisasi = realisasi/pembagi;
			    
			    rowx.add(""+Formater.formatNumber(realisasi,"#.##"));
			    realisasiTotal+=realisasi;
			}
		    }
		    rowx.add(""+Formater.formatNumber(realisasiTotal,"#.##"));
		    
		    double persentasePerWP = (realisasiTotal/totalPajak)*100;
		    rowx.add(""+Formater.formatNumber(persentasePerWP,"#.##")+"%");
		    lstData.add(rowx);
		    noUrut+=1;
		    grandTotal+=realisasiTotal;
		}
		
	    }
	    
	    listWPClause = " AND ("+listWPClause+")";
	    rowx = new Vector();
	    rowx.add("");
	    rowx.add("Total Top 10");
	    if(monthName.length != 0){
		for(int i = 0; i < monthName.length-1; i++){
		    double totalTop10 = PstDataPajak.getTotalRealisasi(0, 0, 
			    "EXTRACT(YEAR FROM "+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+tahun+"' "
			    + "AND EXTRACT(MONTH FROM "+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+(i+1)+"'"
			    + listWPClause, "");
		    totalTop10 = totalTop10/pembagi;
		    rowx.add(""+Formater.formatNumber(totalTop10,"#.##"));
			    
		}
	    }
	    rowx.add(""+Formater.formatNumber(grandTotal,"#.##"));
	    rowx.add("");
	    lstData.add(rowx);
	    
	    rowx = new Vector();
	    rowx.add("");
	    rowx.add("Total Semua "+pajakTypeDetailName);
	    if(monthName.length != 0){
		for(int i = 0; i < monthName.length-1; i++){
		    double totalTop10 = PstDataPajak.getTotalRealisasi(0, 0, 
			    "EXTRACT(YEAR FROM "+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+tahun+"' "
			    + "AND EXTRACT(MONTH FROM "+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+(i+1)+"' "
			    + "AND "+PstDataPajak.fieldNames[PstDataPajak.FLD_PAJAKDETAILID]+"='"+oidPajakTypeDetail+"'", "");
		    totalTop10 = totalTop10/pembagi;
		    rowx.add(""+Formater.formatNumber(totalTop10,"#.##"));
			    
		}
	    }
	    rowx.add(""+Formater.formatNumber(totalPajak,"#.##"));
	    rowx.add("");
	    lstData.add(rowx);


	    return ctrlist.drawBootstrapStrip();

    }
    
    public String drawTabelTop20Desa(String tahun, double pembagi, long oidPajakType, int month, long oidPajakTypeDetail, String pajakTypeDetailName){
	
	    ControlList ctrlist = new ControlList();

	    ctrlist.setAreaWidth("100%");

	    ctrlist.setListStyle("listarea");

	    ctrlist.setTitleStyle("listheader");

	    ctrlist.setCellStyle("table_cell");

	    ctrlist.setHeaderStyle("listheader");

	    ctrlist.setBorder(1);

	    ctrlist.addHeader("No","10%");
	    ctrlist.addHeader("Kecamatan","10%");
	    ctrlist.addHeader("Desa");
	    
	    Vector listPajakDetail = PstPajakTypeDetail.listAll();
	    
	    if(listPajakDetail.size() != 0){
		for (int i = 0; i < listPajakDetail.size(); i++) {
		    PajakTypeDetail pajakTypeDetail = (PajakTypeDetail) listPajakDetail.get(i);
		    ctrlist.addHeader(""+pajakTypeDetail.getPajakDetailName(),"10%");
		}
	    }
	    ctrlist.addHeader("Total","10%");

	    ctrlist.setLinkRow(1);

	    ctrlist.setLinkSufix("");

	    Vector lstData = ctrlist.getData();

	    Vector lstLinkData = ctrlist.getLinkData();

	    ctrlist.setLinkPrefix("javascript:cmdEdit('");

	    ctrlist.setLinkSufix("')");

	    ctrlist.reset();
	    
	    Vector listDesa = PstDataPajak.getTotalRealisasiCrossDBKelurahan(0, 0, 
		    "EXTRACT(YEAR FROM dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+tahun+"' ", 
		    ""+PstDataPajak.fieldNames[PstDataPajak.FLD_JUMLAHPAJAK]+" DESC", 
		    "dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_KELURAHAN]+",kelurahan.NM_KELURAHAN,kecamatan.NM_KECAMATAN");
	    
	    
	    Vector rowx = new Vector();
            
            int batas = 0;
            if(listDesa.size() >= 20){
                batas = 20;
            }else{
                batas = listDesa.size();
            }
	    if(listDesa.size() != 0){
		int noUrut = 1;
		for(int phr = 0; phr < batas; phr++){
		    DataPajak dataPajak = (DataPajak) listDesa.get(phr);
		    
		    rowx = new Vector();
		    rowx.add(""+noUrut);
		    rowx.add(""+dataPajak.getNamaKelurahan());
		    rowx.add(""+dataPajak.getNamaKecamatan());
		    double realisasiTotal = 0;
		    
		    if(listPajakDetail.size() != 0){
			for(int i = 0; i < listPajakDetail.size(); i++){
			    PajakTypeDetail pajakTypeDetail = (PajakTypeDetail) listPajakDetail.get(i);
			    double realisasi = 0;
			    realisasi = PstDataPajak.getTotalRealisasi(0, 0, 
				"EXTRACT(YEAR FROM "+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+tahun+"' "
				+ "AND "+PstDataPajak.fieldNames[PstDataPajak.FLD_KELURAHAN]+"='"+dataPajak.getKodeKelurahan()+"' "
				+ "AND "+PstDataPajak.fieldNames[PstDataPajak.FLD_PAJAKDETAILID]+"='"+pajakTypeDetail.getOID()+"'",
				"");


			    realisasi = realisasi/pembagi;
			    
			    rowx.add(""+Formater.formatNumber(realisasi,"#.##"));
			    realisasiTotal+=realisasi;
			}
		    }
		    rowx.add(""+Formater.formatNumber(realisasiTotal,"#.##"));
		    lstData.add(rowx);
		    noUrut+=1;
		}
		
	    }


	    return ctrlist.drawBootstrapStrip();

    }
    
    public String drawTabelPerKecamatan(String tahun, double pembagi, long oidPajakType, int month, long oidPajakTypeDetail, String pajakTypeDetailName){
	
	    ControlList ctrlist = new ControlList();

	    ctrlist.setAreaWidth("100%");

	    ctrlist.setListStyle("listarea");

	    ctrlist.setTitleStyle("listheader");

	    ctrlist.setCellStyle("table_cell");

	    ctrlist.setHeaderStyle("listheader");

	    ctrlist.setBorder(1);

	    ctrlist.addHeader("Kecamatan","10%");
	    
	    Vector listPajakDetail = PstPajakTypeDetail.listAll();
	    
	    if(listPajakDetail.size() != 0){
		for (int i = 0; i < listPajakDetail.size(); i++) {
		    PajakTypeDetail pajakTypeDetail = (PajakTypeDetail) listPajakDetail.get(i);
		    ctrlist.addHeader(""+pajakTypeDetail.getPajakDetailName(),"10%");
		}
	    }
	    ctrlist.addHeader("Total","10%");

	    ctrlist.setLinkRow(1);

	    ctrlist.setLinkSufix("");

	    Vector lstData = ctrlist.getData();

	    Vector lstLinkData = ctrlist.getLinkData();

	    ctrlist.setLinkPrefix("javascript:cmdEdit('");

	    ctrlist.setLinkSufix("')");

	    ctrlist.reset();
	    
	    Vector listDesa = PstDataPajak.getTotalRealisasiCrossDBKecamatan(0, 0, 
		    "EXTRACT(YEAR FROM dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+tahun+"' ", 
		    ""+PstDataPajak.fieldNames[PstDataPajak.FLD_JUMLAHPAJAK]+" DESC", 
		    "dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_KECAMATAN]+",kecamatan.NM_KECAMATAN");
	    
	    
	    Vector rowx = new Vector();
	    double grandTotal = 0;
	    if(listDesa.size() != 0){
		int noUrut = 1;
                int batas = 0;
		if(listDesa.size() >= 10){
                    batas = 10;
                }else{
                    batas = listDesa.size();
                }
		for(int phr = 0; phr < batas; phr++){
		    DataPajak dataPajak = (DataPajak) listDesa.get(phr);
		    
		    rowx = new Vector();
		    rowx.add(""+dataPajak.getNamaKecamatan());
		    double realisasiTotal = 0;
		    
		    if(listPajakDetail.size() != 0){
			for(int i = 0; i < listPajakDetail.size(); i++){
			    PajakTypeDetail pajakTypeDetail = (PajakTypeDetail) listPajakDetail.get(i);
			    double realisasi = 0;
			    realisasi = PstDataPajak.getTotalRealisasi(0, 0, 
				"EXTRACT(YEAR FROM "+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+tahun+"' "
				+ "AND "+PstDataPajak.fieldNames[PstDataPajak.FLD_KECAMATAN]+"='"+dataPajak.getKodeKecamatan()+"' "
				+ "AND "+PstDataPajak.fieldNames[PstDataPajak.FLD_PAJAKDETAILID]+"='"+pajakTypeDetail.getOID()+"'",
				"");


			    realisasi = realisasi/pembagi;
			    
			    rowx.add(""+Formater.formatNumber(realisasi,"#.##"));
			    realisasiTotal+=realisasi;
			}
		    }
		    rowx.add(""+Formater.formatNumber(realisasiTotal,"#.##"));
		    lstData.add(rowx);
		    noUrut+=1;
		    grandTotal+=realisasiTotal;
		}
		
	    }
	    rowx = new Vector();
	    rowx.add("<b>Grand Total</b>");
	    if(listPajakDetail.size() != 0){
		for(int i = 0; i < listPajakDetail.size(); i++){
		    rowx.add("");
		}
	    }
	    rowx.add(""+Formater.formatNumber(grandTotal,"#.##"));
	    lstData.add(rowx);

	    return ctrlist.drawBootstrapStrip();

    }
    
    public String drawReportPerHari(String sampaiDengan){
	String dataHtml = "";
	dataHtml+=""
	+ "<table class='table table-bordered table-striped'>"
	    + "<thead>"
		+ "<tr>"
		    + "<th rowspan='2'><center>Jenis Pajak</center></th>"
		    + "<th colspan='3'><center>PENDAPATAN HARI INI</center></th>"
		    + "<th colspan='3'><center>PENDAPATAN MINGGU INI</center></th>"
		+ "</tr>"
		+ "<tr>"
		    + "<th><center>POKOK</center></th>"
		    + "<th><center>DENDA</center></th>"
		    + "<th><center>JUMLAH</center></th>"
		    + "<th><center>POKOK</center></th>"
		    + "<th><center>DENDA</center></th>"
		    + "<th><center>JUMLAH</center></th>"
		+ "</tr>"
	    + "</thead>"
	    + "<tbody>";
	    double grandTotalPokokPerHari = 0;
	    double grandTotalDendaPerHari = 0;
	    double grandTotalRealisasiPerHari = 0;
	    double grandTotalPokokPerMinggu = 0;
	    double grandTotalDendaPerMinggu = 0;
	    double grandTotalRealisasiPerMinggu = 0;
	    
	    Vector listJenisPajak = PstPajakTypeDetail.listAll();
	    Date getdate = Formater.formatDate(sampaiDengan, "dd MMMM yyyy");
	    Date date = Formater.reFormatDate(getdate, "yyyy-MM-dd");
	    if(listJenisPajak.size() != 0){
		
		for(int i = 0; i < listJenisPajak.size(); i++){
		    PajakTypeDetail pajakTypeDetail = (PajakTypeDetail) listJenisPajak.get(i);
		    
		    double totalPokokPerHari = 0;
		    double totalDendaPerHari = 0;
		    double totalRealisasiPerHari = 0;
		    
		    //GET DATE 1 WEEK
		    
		    Calendar c = Calendar.getInstance();
		    c.setTime(date);
		    int iDate = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
		    c.add(Calendar.DATE,-iDate);
		    Date startDate = c.getTime();
		    c.add(Calendar.DATE, 6);
		    Date end = c.getTime();
		    
		    double totalPokokPerMinggu = 0;
		    double totalDendaPerMinggu = 0;
		    double totalRealisasiPerMinggu = 0;
		    
		    if(AppSetting.SQL_VERSION == AppSetting.DBSVR_ORACLE){
			
			totalPokokPerHari = PstDataPajak.getTotalPokok(0, 0, 
			    ""+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+"=TO_DATE('"+Formater.formatDate(date, "yyyy-MM-dd")+"', 'YYYY-MM-DD') "
			    + "AND "+PstDataPajak.fieldNames[PstDataPajak.FLD_PAJAKDETAILID]+"='"+pajakTypeDetail.getOID()+"'", "");
			
			totalDendaPerHari = PstDataPajak.getTotalDenda(0, 0, 
			    ""+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+"=TO_DATE('"+Formater.formatDate(date, "yyyy-MM-dd")+"', 'YYYY-MM-DD') "
			    + "AND "+PstDataPajak.fieldNames[PstDataPajak.FLD_PAJAKDETAILID]+"='"+pajakTypeDetail.getOID()+"'", "");
			
			totalRealisasiPerHari = PstDataPajak.getTotalRealisasi(0, 0, 
			    ""+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+"=TO_DATE('"+Formater.formatDate(date, "yyyy-MM-dd")+"', 'YYYY-MM-DD') "
			    + "AND "+PstDataPajak.fieldNames[PstDataPajak.FLD_PAJAKDETAILID]+"='"+pajakTypeDetail.getOID()+"'", "");
			
			totalPokokPerMinggu = PstDataPajak.getTotalPokok(0, 0, 
			    ""+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+" BETWEEN TO_DATE('"+Formater.formatDate(startDate,"yyyy-MM-dd")+"','YYYY-MM-DD') "
			    + "AND TO_DATE('"+Formater.formatDate(end,"yyyy-MM-dd")+"', 'YYYY-MM-DD') "
			    + "AND "+PstDataPajak.fieldNames[PstDataPajak.FLD_PAJAKDETAILID]+"='"+pajakTypeDetail.getOID()+"'", "");
			
			totalDendaPerMinggu = PstDataPajak.getTotalDenda(0, 0, 
			   ""+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+" BETWEEN TO_DATE('"+Formater.formatDate(startDate,"yyyy-MM-dd")+"','YYYY-MM-DD') "
			    + "AND TO_DATE('"+Formater.formatDate(end,"yyyy-MM-dd")+"', 'YYYY-MM-DD') "
			    + "AND "+PstDataPajak.fieldNames[PstDataPajak.FLD_PAJAKDETAILID]+"='"+pajakTypeDetail.getOID()+"'", "");
			
			totalRealisasiPerMinggu = PstDataPajak.getTotalRealisasi(0, 0, 
			    ""+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+" BETWEEN TO_DATE('"+Formater.formatDate(startDate,"yyyy-MM-dd")+"','YYYY-MM-DD') "
			    + "AND TO_DATE('"+Formater.formatDate(end,"yyyy-MM-dd")+"', 'YYYY-MM-DD') "
			    + "AND "+PstDataPajak.fieldNames[PstDataPajak.FLD_PAJAKDETAILID]+"='"+pajakTypeDetail.getOID()+"'", "");
		    }else{
                        totalPokokPerHari = PstDataPajak.getTotalPokok(0, 0, 
			    ""+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+"='"+Formater.formatDate(date, "yyyy-MM-dd")+"' "
			    + "AND "+PstDataPajak.fieldNames[PstDataPajak.FLD_PAJAKDETAILID]+"='"+pajakTypeDetail.getOID()+"'", "");
			
			totalDendaPerHari = PstDataPajak.getTotalDenda(0, 0, 
			    ""+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+"='"+Formater.formatDate(date, "yyyy-MM-dd")+"' "
			    + "AND "+PstDataPajak.fieldNames[PstDataPajak.FLD_PAJAKDETAILID]+"='"+pajakTypeDetail.getOID()+"'", "");
			
			totalRealisasiPerHari = PstDataPajak.getTotalRealisasi(0, 0, 
			    ""+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+"='"+Formater.formatDate(date, "yyyy-MM-dd")+"' "
			    + "AND "+PstDataPajak.fieldNames[PstDataPajak.FLD_PAJAKDETAILID]+"='"+pajakTypeDetail.getOID()+"'", "");
			
			totalPokokPerMinggu = PstDataPajak.getTotalPokok(0, 0, 
			    ""+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+" BETWEEN '"+Formater.formatDate(startDate,"yyyy-MM-dd")+"' "
			    + "AND '"+Formater.formatDate(end,"yyyy-MM-dd")+"' "
			    + "AND "+PstDataPajak.fieldNames[PstDataPajak.FLD_PAJAKDETAILID]+"='"+pajakTypeDetail.getOID()+"'", "");
			
			totalDendaPerMinggu = PstDataPajak.getTotalDenda(0, 0, 
			   ""+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+" BETWEEN '"+Formater.formatDate(startDate,"yyyy-MM-dd")+"' "
			    + "AND '"+Formater.formatDate(end,"yyyy-MM-dd")+"' "
			    + "AND "+PstDataPajak.fieldNames[PstDataPajak.FLD_PAJAKDETAILID]+"='"+pajakTypeDetail.getOID()+"'", "");
			
			totalRealisasiPerMinggu = PstDataPajak.getTotalRealisasi(0, 0, 
			    ""+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+" BETWEEN '"+Formater.formatDate(startDate,"yyyy-MM-dd")+"' "
			    + "AND '"+Formater.formatDate(end,"yyyy-MM-dd")+"' "
			    + "AND "+PstDataPajak.fieldNames[PstDataPajak.FLD_PAJAKDETAILID]+"='"+pajakTypeDetail.getOID()+"'", "");
		    }
		    
		    dataHtml+=""
		    + "<tr>"
			+ "<td>"+pajakTypeDetail.getPajakDetailName()+"</td>"
			+ "<td align='right'>"+Formater.formatNumber(totalPokokPerHari,"#,###")+"</td>"
			+ "<td align='right'>"+Formater.formatNumber(totalDendaPerHari,"#,###")+"</td>"
			+ "<td align='right'>"+Formater.formatNumber(totalRealisasiPerHari,"#,###")+"</td>"
			+ "<td align='right'>"+Formater.formatNumber(totalPokokPerMinggu,"#,###")+"</td>"
			+ "<td align='right'>"+Formater.formatNumber(totalDendaPerMinggu,"#,###")+"</td>"
			+ "<td align='right'>"+Formater.formatNumber(totalRealisasiPerMinggu,"#,###")+"</td>"
		    + "</tr>";
		    grandTotalPokokPerHari+=totalPokokPerHari;
		    grandTotalDendaPerHari+=totalDendaPerHari;
		    grandTotalRealisasiPerHari+=totalRealisasiPerHari;
		    
		    grandTotalPokokPerMinggu+=totalPokokPerMinggu;
		    grandTotalDendaPerMinggu+=totalDendaPerMinggu;
		    grandTotalRealisasiPerMinggu+=totalRealisasiPerMinggu;
		}
	    }
	    dataHtml+=""
		+ "<tr>"
		    + "<td align='right'>"
			+ "<b>TOTAL</b>"
		    + "</td>"
		    + "<td align='right'>"+Formater.formatNumber(grandTotalPokokPerHari, "#,###")+"</td>"
		    + "<td align='right'>"+Formater.formatNumber(grandTotalDendaPerHari, "#,###")+"</td>"
		    + "<td align='right'>"+Formater.formatNumber(grandTotalRealisasiPerHari, "#,###")+"</td>"
		    + "<td align='right'>"+Formater.formatNumber(grandTotalPokokPerMinggu, "#,###")+"</td>"
		    + "<td align='right'>"+Formater.formatNumber(grandTotalDendaPerMinggu, "#,###")+"</td>"
		    + "<td align='right'>"+Formater.formatNumber(grandTotalRealisasiPerMinggu, "#,###")+"</td>"
		+ "</tr>"
	    + "</tbody>"
	+ "</table>";
	return dataHtml;
    }
    
    public String drawReportPerBulan(String sampaiDengan){
	String dataHtml = "";
	dataHtml+=""
	+ "<table class='table table-bordered table-striped'>"
	    + "<thead>"
		+ "<tr>"
		    + "<th rowspan='2'><center>Jenis Pajak</center></th>"
		    + "<th colspan='3'><center>PENDAPATAN BULAN INI</center></th>"
		    + "<th colspan='3'><center>PENDAPATAN TAHUN INI</center></th>"
		+ "</tr>"
		+ "<tr>"
		    + "<th><center>POKOK</center></th>"
		    + "<th><center>DENDA</center></th>"
		    + "<th><center>JUMLAH</center></th>"
		    + "<th><center>POKOK</center></th>"
		    + "<th><center>DENDA</center></th>"
		    + "<th><center>JUMLAH</center></th>"
		+ "</tr>"
	    + "</thead>"
	    + "<tbody>";
	    double grandTotalPokokPerBulan = 0;
	    double grandTotalDendaPerBulan = 0;
	    double grandTotalRealisasiPerBulan = 0;
	    double grandTotalPokokPerTahun = 0;
	    double grandTotalDendaPerTahun = 0;
	    double grandTotalRealisasiPerTahun = 0;
	    
	    Vector listJenisPajak = PstPajakTypeDetail.listAll();
	    if(listJenisPajak.size() != 0){
		
		for(int i = 0; i < listJenisPajak.size(); i++){
		    PajakTypeDetail pajakTypeDetail = (PajakTypeDetail) listJenisPajak.get(i);
		    int getMonth = Integer.parseInt(Formater.formatDate(sampaiDengan, "dd MMMM yyyy", "MM"));
                    int getYearMonth = Integer.parseInt(Formater.formatDate(sampaiDengan, "dd MMMM yyyy", "yyyy"));
		    double totalPokokPerBulan = PstDataPajak.getTotalPokok(0, 0, 
			    "EXTRACT(MONTH FROM "+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+getMonth+"' "
                            + "AND EXTRACT(YEAR FROM "+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+getYearMonth+"' "        
			    + "AND "+PstDataPajak.fieldNames[PstDataPajak.FLD_PAJAKDETAILID]+"='"+pajakTypeDetail.getOID()+"'", "");
		    double totalDendaPerBulan = PstDataPajak.getTotalDenda(0, 0, 
			    "EXTRACT(MONTH FROM "+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+getMonth+"' "
                            + "AND EXTRACT(YEAR FROM "+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+getYearMonth+"' "                
			    + "AND "+PstDataPajak.fieldNames[PstDataPajak.FLD_PAJAKDETAILID]+"='"+pajakTypeDetail.getOID()+"'", "");
		    double totalRealisasiPerBulan = PstDataPajak.getTotalRealisasi(0, 0, 
			    "EXTRACT(MONTH FROM "+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+getMonth+"' "
                            + "AND EXTRACT(YEAR FROM "+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+getYearMonth+"' "                
			    + "AND "+PstDataPajak.fieldNames[PstDataPajak.FLD_PAJAKDETAILID]+"='"+pajakTypeDetail.getOID()+"'", "");
		    

		    int getYear = Integer.parseInt(Formater.formatDate(sampaiDengan, "dd MMMM yyyy", "yyyy"));
		    double totalPokokPerTahun = PstDataPajak.getTotalPokok(0, 0, 
			    "EXTRACT(YEAR FROM "+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+getYear+"' "
			    + "AND "+PstDataPajak.fieldNames[PstDataPajak.FLD_PAJAKDETAILID]+"='"+pajakTypeDetail.getOID()+"'", "");
		    double totalDendaPerTahun = PstDataPajak.getTotalDenda(0, 0, 
			   "EXTRACT(YEAR FROM "+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+getYear+"' "
			    + "AND "+PstDataPajak.fieldNames[PstDataPajak.FLD_PAJAKDETAILID]+"='"+pajakTypeDetail.getOID()+"'", "");
		    double totalRealisasiPerTahun = PstDataPajak.getTotalRealisasi(0, 0, 
			    "EXTRACT(YEAR FROM "+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+getYear+"' "
			    + "AND "+PstDataPajak.fieldNames[PstDataPajak.FLD_PAJAKDETAILID]+"='"+pajakTypeDetail.getOID()+"'", "");
		    dataHtml+=""
		    + "<tr>"
			+ "<td>"+pajakTypeDetail.getPajakDetailName()+"</td>"
			+ "<td align='right'>"+Formater.formatNumber(totalPokokPerBulan,"#,###")+"</td>"
			+ "<td align='right'>"+Formater.formatNumber(totalDendaPerBulan,"#,###")+"</td>"
			+ "<td align='right'>"+Formater.formatNumber(totalRealisasiPerBulan,"#,###")+"</td>"
			+ "<td align='right'>"+Formater.formatNumber(totalPokokPerTahun,"#,###")+"</td>"
			+ "<td align='right'>"+Formater.formatNumber(totalDendaPerTahun,"#,###")+"</td>"
			+ "<td align='right'>"+Formater.formatNumber(totalRealisasiPerTahun,"#,###")+"</td>"
		    + "</tr>";
		    grandTotalPokokPerBulan+=totalPokokPerBulan;
		    grandTotalDendaPerBulan+=totalDendaPerBulan;
		    grandTotalRealisasiPerBulan+=totalRealisasiPerBulan;
		    
		    grandTotalPokokPerTahun+=totalPokokPerTahun;
		    grandTotalDendaPerTahun+=totalDendaPerTahun;
		    grandTotalRealisasiPerTahun+=totalRealisasiPerTahun;
		}
	    }
	    dataHtml+=""
		+ "<tr>"
		    + "<td align='right'>"
			+ "<b>TOTAL</b>"
		    + "</td>"
		    + "<td align='right'>"+Formater.formatNumber(grandTotalPokokPerBulan, "#,###")+"</td>"
		    + "<td align='right'>"+Formater.formatNumber(grandTotalDendaPerBulan, "#,###")+"</td>"
		    + "<td align='right'>"+Formater.formatNumber(grandTotalRealisasiPerBulan, "#,###")+"</td>"
		    + "<td align='right'>"+Formater.formatNumber(grandTotalPokokPerTahun, "#,###")+"</td>"
		    + "<td align='right'>"+Formater.formatNumber(grandTotalDendaPerTahun, "#,###")+"</td>"
		    + "<td align='right'>"+Formater.formatNumber(grandTotalRealisasiPerTahun, "#,###")+"</td>"
		+ "</tr>"
	    + "</tbody>"
	+ "</table>";
	return dataHtml;
    }
}


