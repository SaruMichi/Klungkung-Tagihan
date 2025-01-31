<%@page import="com.dimata.dslik.entity.admin.AppObjInfo"%>
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar user panel -->
      <div class="user-panel">
          <%--<div class="pull-left image">
	  <img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image" />
	</div>--%>
	<div class="pull-left info">
            <%--<p>User's Name</p>--%>
	</div>
      </div>
      
      <!-- /.search form -->
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu">
	<li class="header">MAIN NAVIGATION</li>
        <%
            if(userSession.checkPrivG1(AppObjInfo.G1_HOME)){
                %>
                    <li class="treeview" id="home">
                        <a href="<%= approot %>/home_slik.jsp">
                        <i class="fa fa-home"></i> <span>Home</span>
                      </a>
                    </li>
                <%
            }
            if(userSession.checkPrivG1(AppObjInfo.G1_HOME)){
                %>
                    <li class="treeview" id="home">
                        <a href="<%= approot %>/home_check_data.jsp">
                        <i class="fa fa-check"></i> <span>Check Data</span>
                      </a>
                    </li>
                <%
            }
            if(userSession.checkPrivG1(AppObjInfo.G1_REPLIKASI)){
                %>
                    <li class="treeview" id="home">
                        <a href="<%= approot %>/home_check_replikasi.jsp">
                        <i class="fa fa-check-circle"></i> <span>Replikasi Database Status</span>
                      </a>
                    </li>
                <%
            }
            if(userSession.checkPrivG1(AppObjInfo.G1_PERIODE_DATA)){
                %>
                    <li class="treeview" id="periode">
                        <a href="#">
                          <i class="fa fa-th-list"></i>
                          <span>Periode Data</span>
                          <i class="fa fa-angle-left pull-right"></i>
                        </a>
                        <ul class="treeview-menu">
                            <%
                                if(userSession.checkPrivG1G2(AppObjInfo.G1_PERIODE_DATA, AppObjInfo.G2_PERIODE_DATA)){
                                    %>
                                        <li id="periodedata"><a href="<%= approot %>/dslik/masterdata/periode.jsp"><i class="fa fa-circle-o"></i>Periode Data</a></li>
                                    <%
                                }
                            %>
                        </ul>
                    </li>
                <%
            }
            
            if(userSession.checkPrivG1(AppObjInfo.G1_PROSES_TRANSFER_DATA)){
                %>
                    <li class="treeview" id="masterdata">
                        <a href="#">
                          <i class="fa fa-th-list"></i>
                          <span>Proses Transfer Data</span>
                          <i class="fa fa-angle-left pull-right"></i>
                        </a>
                        <ul class="treeview-menu">
                          <%--<li id="alldata"><a href="<%= approot %>/dslik/proses/transfer_data.jsp"><i class="fa fa-circle-o"></i> Transfer All Data</a></li>--%>
                          <%
                            if(userSession.checkPrivG1G2(AppObjInfo.G1_PROSES_TRANSFER_DATA, AppObjInfo.G2_TRANSFER_DEBITUR_DATA)){
                                %>
                                    <li id="debituronly"><a href="<%= approot %>/dslik/proses/transfer_data_debitur.jsp"><i class="fa fa-circle-o"></i> Transfer Debitur Data</a></li>
                                <%
                            }
                            
                            if(userSession.checkPrivG1G2(AppObjInfo.G1_PROSES_TRANSFER_DATA, AppObjInfo.G2_TRANSFER_KREDIT_DATA)){
                                %>
                                    <li id="kreditonly"><a href="<%= approot %>/dslik/proses/transfer_data_kredit.jsp"><i class="fa fa-circle-o"></i> Transfer Kredit Data</a></li>
                                <%
                            }
                            
                            if(userSession.checkPrivG1G2(AppObjInfo.G1_PROSES_TRANSFER_DATA, AppObjInfo.G2_TRANSFER_BANK_GARANSI_DATA)){
                                %>
                                    <li id="bankgaransionly"><a href="<%= approot %>/dslik/proses/transfer_data_bank_garansi.jsp"><i class="fa fa-circle-o"></i> Transfer Bank Garansi Data</a></li>
                                <%
                            }
                            
                            if(userSession.checkPrivG1G2(AppObjInfo.G1_PROSES_TRANSFER_DATA, AppObjInfo.G2_TRANSFER_AGUNAN_DATA)){
                                %>
                                    <li id="agunanonly"><a href="<%= approot %>/dslik/proses/transfer_data_agunan.jsp"><i class="fa fa-circle-o"></i> Transfer Agunan Data</a></li>
                                <%
                            }
                            
                            if(userSession.checkPrivG1G2(AppObjInfo.G1_PROSES_TRANSFER_DATA, AppObjInfo.G2_TRANSFER_PENGURUS_PEMILIK_DATA)){
                                %>
                                    <li id="pengurusonly"><a href="<%= approot %>/dslik/proses/transfer_data_pengurus.jsp"><i class="fa fa-circle-o"></i> Transfer Pengurus/Pemilik Data</a></li>
                                <%
                            }
                          %>
                        </ul>
                    </li>
                <%
            }
            if(userSession.checkPrivG1(AppObjInfo.G1_PERLENGKAPAN_DATA)){
                %>
                    <li class="treeview" id="modperlengkapandata">
                        <a href="#">
                          <i class="fa fa-th-list"></i>
                          <span>Perlengkapan Data</span>
                          <i class="fa fa-angle-left pull-right"></i>
                        </a>
                        <ul class="treeview-menu">
                            <%
                                if(userSession.checkPrivG1G2(AppObjInfo.G1_PERLENGKAPAN_DATA, AppObjInfo.G2_PROSES_PERLENGKAPAN_DATA)){
                                    %>
                                        <li id="perlengkapandata"><a href="<%= approot %>/dslik/perlengkapandata/perlengkapandata.jsp?command=8&oid=0&type=1&tablePerlengkapanDataElement_length=10"><i class="fa fa-circle-o"></i> Proses Pelengkapan Data</a></li>
                                    <%
                                }
                            %>
                          
                        </ul>
                    </li>
                <%
            }
            
            if(userSession.checkPrivG1(AppObjInfo.G1_APPROVE_DATA)){
                %>
                    <li class="treeview" id="modpelaporan">
                        <a href="#">
                          <i class="fa fa-th-list"></i>
                          <span>Approve Data</span>
                          <i class="fa fa-angle-left pull-right"></i>
                        </a>
                        <ul class="treeview-menu">
                            <%
                                if(userSession.checkPrivG1G2(AppObjInfo.G1_APPROVE_DATA, AppObjInfo.G2_APPROVE_DATA_CABANG)){
                                    %>
                                        <li id="kelengkapandata"><a href="<%= approot %>/dslik/masterdata/periode_cabang.jsp"><i class="fa fa-circle-o"></i> Approve Data Cabang</a></li>
                                    <%
                                }
                            %>
                          
                        </ul>
                    </li>
                <%
            }
            
            if(userSession.checkPrivG1(AppObjInfo.G1_PROSES_DELIMITED_TEXT)){
                %>
                    <li class="treeview" id="masterdata">
                        <a href="#">
                          <i class="fa fa-th-list"></i>
                          <span>Proses Delimited Text</span>
                          <i class="fa fa-angle-left pull-right"></i>
                        </a>
                        <ul class="treeview-menu">
                            <%
                                 if(userSession.checkPrivG1G2(AppObjInfo.G1_PROSES_DELIMITED_TEXT, AppObjInfo.G2_BULANAN)){
                                     %>
                                        <li id="chaneltype"><a href="<%= approot %>/dslik/proses/delimited_text.jsp"><i class="fa fa-circle-o"></i> Bulanan</a></li>
                                    <%
                                 }
                                 
                                 if(userSession.checkPrivG1G2(AppObjInfo.G1_PROSES_DELIMITED_TEXT, AppObjInfo.G2_INITIAL)){
                                     %>
                                        <li id="chaneltype"><a href="<%= approot %>/dslik/proses/delimited_text_summary.jsp"><i class="fa fa-circle-o"></i> Initial</a></li>
                                    <%
                                 }
                            %>
                        </ul>
                    </li>
                <%
            }
            
            if(userSession.checkPrivG1(AppObjInfo.G1_MODUL_LAPORAN)){
                %>
                    <li class="treeview" id="masterdata">
                        <a href="#">
                          <i class="fa fa-th-list"></i>
                          <span>Modul Pelaporan</span>
                          <i class="fa fa-angle-left pull-right"></i>
                        </a>
                        <ul class="treeview-menu">
                            <%
                                if(userSession.checkPrivG1G2(AppObjInfo.G1_MODUL_LAPORAN, AppObjInfo.G2_PER_SEGMENT_SUMMARY)){
                                    %>
                                        <li id="chaneltype"><a href="<%= approot %>/dslik/pelaporan/perbaikan_per_eror.jsp"><i class="fa fa-circle-o"></i> Per Detail Error</a></li>
                                        <li id="chaneltype"><a href="<%= approot %>/dslik/pelaporan/pelaporan_per_segment.jsp"><i class="fa fa-circle-o"></i> Per Segmen Summary</a></li>
                                    <%
                                }
                                if(userSession.checkPrivG1G2(AppObjInfo.G1_MODUL_LAPORAN, AppObjInfo.G2_PER_SEGMENT_DETAIL)){
                                    %>
                                        <li id="chaneltype"><a href="<%= approot %>/dslik/pelaporan/pelaporan_per_segment_detail.jsp"><i class="fa fa-circle-o"></i> Per Segmen Detail</a></li>
                                    <%
                                }
                                if(userSession.checkPrivG1G2(AppObjInfo.G1_MODUL_LAPORAN, AppObjInfo.G2_PERUBAHAN_DATA_SEGMENT)){
                                    %>
                                        <li id="chaneltype"><a href="<%= approot %>/dslik/pelaporan/perubahan_data_segment.jsp" style="display:none;"><i class="fa fa-circle-o"></i> Perubahan Data Segmen</a></li>
                                    <%
                                }
                                if(userSession.checkPrivG1G2(AppObjInfo.G1_MODUL_LAPORAN, AppObjInfo.G2_NOMINATIF_AGUNAN)){
                                    %>
                                        <li id="chaneltype"><a href="<%= approot %>/dslik/pelaporan/nominatifagunan.jsp"><i class="fa fa-circle-o"></i> Nominatif Agunan</a></li>
                                    <%
                                }
                                if(userSession.checkPrivG1G2(AppObjInfo.G1_MODUL_LAPORAN, AppObjInfo.G2_NOMINATIF_BANK_GARANSI)){
                                    %>
                                        <li id="chaneltype"><a href="<%= approot %>/dslik/pelaporan/nominatifbankgaransi.jsp"><i class="fa fa-circle-o"></i> Nominatif Bank Garansi Active</a></li>
                                        <li id="chaneltype"><a href="<%= approot %>/dslik/pelaporan/nominatifbankgaransi_lunas.jsp"><i class="fa fa-circle-o"></i> Nominatif Bank Garansi Lunas</a></li>
                                    <%
                                }
                                if(userSession.checkPrivG1G2(AppObjInfo.G1_MODUL_LAPORAN, AppObjInfo.G2_NOMINATIF_KREDIT_HAPUS_BUKU)){
                                    %>
                                        <li id="chaneltype"><a href="<%= approot %>/dslik/pelaporan/nominatifkredithapusbuku.jsp"><i class="fa fa-circle-o"></i> Nominatif Kredit Hapus Buku</a></li>
                                    <%
                                }
                                if(userSession.checkPrivG1G2(AppObjInfo.G1_MODUL_LAPORAN, AppObjInfo.G2_KREDIT_PER_KOLEKTIBILITAS)){
                                    %>
                                        <li id="chaneltype"><a href="<%= approot %>/dslik/pelaporan/nominatifkreditpersegment.jsp"><i class="fa fa-circle-o"></i> Nominatif Kredit Per Kolektibility</a></li>
                                    <%
                                }
                                if(userSession.checkPrivG1G2(AppObjInfo.G1_MODUL_LAPORAN, AppObjInfo.G2_PINJAMAN_TUTUP_PER_BULAN)){
                                    %>
                                        <li id="chaneltype"><a href="<%= approot %>/dslik/pelaporan/nominatifkreditpinjamantutupperbulan.jsp"><i class="fa fa-circle-o"></i> Nominatif Pinjaman Tutup<br> Per Bulan</a></li>
                                        <li id="chaneltype"><a href="<%= approot %>/dslik/pelaporan/rekapsummarykredit.jsp"><i class="fa fa-circle-o"></i> Rekap Summary Kredit</a></li>
                                        <li id="chaneltype"><a href="<%= approot %>/dslik/pelaporan/rekapsummaryagunan.jsp"><i class="fa fa-circle-o"></i> Rekap Summary Agunan</a></li>
                                        <li id="chaneltype"><a href="<%= approot %>/dslik/pelaporan/rekapsummaryabankgaransi.jsp"><i class="fa fa-circle-o"></i> Rekap Summary Bank Garansi</a></li>
                                        <li id="chaneltype"><a href="<%= approot %>/dslik/pelaporan/rekapsummarykredit_lbu.jsp"><i class="fa fa-circle-o"></i> Rekap Summary Kredit<br> Versi LBU</a></li>
                                        <li id="chaneltype"><a href="<%= approot %>/dslik/pelaporan/rekapsummaryabankgaransi_lbu.jsp"><i class="fa fa-circle-o"></i> Rekap Summary Bank Garansi<br> Versi LBU</a></li>
                                    <%
                                }
                            %>
                        </ul>
                    </li>
                <%
            }
            
            if(userSession.checkPrivG1(AppObjInfo.G1_MODUL_PEMELIHARAAN)){
                %>
                    <li class="treeview" id="masterdata">
                        <a href="#">
                          <i class="fa fa-th-list"></i>
                          <span>Modul Pemeliharaan</span>
                          <i class="fa fa-angle-left pull-right"></i>
                        </a>
                        <ul class="treeview-menu">
                            <%
                                if(userSession.checkPrivG1G2(AppObjInfo.G1_MODUL_PEMELIHARAAN, AppObjInfo.G2_LAPORAN_HISTORY_PERUBAHAN_DATA)){
                                    %>
                                        <li id="chaneltype"><a href="<%= approot %>/dslik/pelaporan/laporan_history_perubahan_data.jsp"><i class="fa fa-circle-o"></i> History Perubahan Data</a></li>
                                    <%
                                }
                            %>
                          <li id="chaneltype" style="display:none;"><a href="<%= approot %>/dslik/pelaporan/history_perubahan_data.jsp"><i class="fa fa-circle-o"></i> History Perubahan Data</a></li>
                          
                        </ul>
                    </li>
                <%
            }
            
            if(userSession.checkPrivG1(AppObjInfo.G1_MODUL_MASTER_DATA)){
                %>
                    <li class="treeview" id="masterdata">
                        <a href="#">
                          <i class="fa fa-th-list"></i>
                          <span>Modul Masterdata</span>
                          <i class="fa fa-angle-left pull-right"></i>
                        </a>
                        <ul class="treeview-menu">
                          <%
                            if(userSession.checkPrivG1G2(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_PROFILE_BANK)){
                                %>
                                    <li class="treeview" id="bankdata">
                                        <a href="#">
                                            <i class="fa fa-th-list"></i>
                                            <span>Profile Bank</span>
                                            <i class="fa fa-angle-left pull-right"></i>
                                        </a>
                                        <ul class="treeview-menu">
                                            <%
                                                if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_PROFILE_BANK, AppObjInfo.OBJ_BANK)){
                                                    %>
                                                        <li id="badanusaha"><a href="<%= approot %>/dslik/masterdata/bank.jsp"><i class="fa fa-circle-o"></i>Bank</a></li>
                                                    <%
                                                }
                                                
                                                if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_PROFILE_BANK, AppObjInfo.OBJ_CABANG_BANK)){
                                                    %>
                                                        <li id="badanusaha"><a href="<%= approot %>/dslik/masterdata/cabang_bank.jsp"><i class="fa fa-circle-o"></i>Cabang Bank</a></li>
                                                    <%
                                                }
                                            %> 
                                        </ul>
                                    </li>
                                <%
                            }
                            
                            if(userSession.checkPrivG1G2(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA)){
                                %>
                                    <li class="treeview" id="contectdata">
                                        <a href="#">
                                          <i class="fa fa-th-list"></i>
                                          <span>Mapping Content Data</span>
                                          <i class="fa fa-angle-left pull-right"></i>
                                        </a>
                                        <ul class="treeview-menu">
                                            <%
                                                if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_BENTUK_BADAN_USAHA)){
                                                    %>
                                                        <li id="badanusaha"><a href="<%= approot %>/dslik/contentdata/bentuk_badan_usaha.jsp"><i class="fa fa-circle-o"></i>Bentuk Badan Usaha</a></li>                   
                                            
                                                    <%
                                                }
                                                if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_BIDANG_USAHA)){
                                                    %>
                                                        <li id="chaneltype"><a href="<%= approot %>/dslik/contentdata/bidang_usaha.jsp"><i class="fa fa-circle-o"></i> Bidang Usaha</a></li>
                                            
                                                    <%
                                                }
                                                if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_CARA_RESTRUKTURISASI)){
                                                    %>
                                                        <li id="chaneltype"><a href="<%= approot %>/dslik/contentdata/cara_restrukturisasi.jsp"><i class="fa fa-circle-o"></i> Cara Restrukturisasi</a></li>
                                            
                                                    <%
                                                }
                                                if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_GOLONGAN_DEBITUR)){
                                                    %>
                                                        <li id="chaneltype"><a href="<%= approot %>/dslik/contentdata/golongan_debitur.jsp"><i class="fa fa-circle-o"></i> Golongan Debitur</a></li>
                                            
                                                    <%
                                                }
                                                if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_HUBUNGAN_DENGAN_PELAPOR)){
                                                    %>
                                                        <li id="chaneltype"><a href="<%= approot %>/dslik/contentdata/hub_dgn_pelapor.jsp"><i class="fa fa-circle-o"></i> Hub dgn Pelapor</a></li>
                                            
                                                    <%
                                                }
                                                if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_JABATAN)){
                                                    %>
                                                        <li id="chaneltype"><a href="<%= approot %>/dslik/contentdata/jabatan.jsp"><i class="fa fa-circle-o"></i> Jabatan</a></li>
                                            
                                                    <%
                                                }
                                                if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_JENIS_AGUNAN)){
                                                    %>
                                                        <li id="chaneltype"><a href="<%= approot %>/dslik/contentdata/jenis_agunan.jsp"><i class="fa fa-circle-o"></i> Jenis Agunan</a></li>
                                            
                                                    <%
                                                }
                                                if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_JENIS_FASILITAS)){
                                                    %>
                                                        <li id="chaneltype"><a href="<%= approot %>/dslik/contentdata/jenis_fasilitas.jsp"><i class="fa fa-circle-o"></i> Jenis Fasilitas</a></li>
                                            
                                                    <%
                                                }
                                                if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_JENIS_GARANSI)){
                                                    %>
                                                        <li id="chaneltype"><a href="<%= approot %>/dslik/contentdata/jenis_garansi.jsp"><i class="fa fa-circle-o"></i> Jenis Garansi</a></li>
                                            
                                                    <%
                                                }
                                                if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_JENIS_IDENTITAS)){
                                                    %>
                                                        <li id="chaneltype"><a href="<%= approot %>/dslik/contentdata/jenis_identitas.jsp"><i class="fa fa-circle-o"></i> Jenis Identitas</a></li>
                                            
                                                    <%
                                                }
                                                if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_JENIS_KREDIT)){
                                                    %>
                                                        <li id="chaneltype"><a href="<%= approot %>/dslik/contentdata/jenis_kredit.jsp"><i class="fa fa-circle-o"></i> Jenis Kredit</a></li>
                                           
                                                    <%
                                                }
                                                if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_JENIS_PEMERINGKAT)){
                                                    %>
                                                         <li id="chaneltype"><a href="<%= approot %>/dslik/contentdata/jenisPemeringkat.jsp"><i class="fa fa-circle-o"></i> Jenis Pemeringkat</a></li>
                                            
                                                    <%
                                                }
                                                if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_JENIS_PENGGUNAAN)){
                                                    %>
                                                        <li id="chaneltype"><a href="<%= approot %>/dslik/contentdata/jenisPenggunaan.jsp"><i class="fa fa-circle-o"></i> Jenis Penggunaan</a></li>
                                            
                                                    <%
                                                }
                                                if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_JENIS_PENGIKATAN)){
                                                    %>
                                                        <li id="chaneltype"><a href="<%= approot %>/dslik/contentdata/jenisPengikatan.jsp"><i class="fa fa-circle-o"></i> Jenis Pengikatan</a></li>
                                            
                                                    <%
                                                }
                                                if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_JENIS_SURAT_BERHARGA)){
                                                    %>
                                                        <li id="chaneltype"><a href="<%= approot %>/dslik/contentdata/jenisSuratBerharga.jsp"><i class="fa fa-circle-o"></i> Jenis Surat Berharga</a></li>
                                            
                                                    <%
                                                }
                                                if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_KABUPATEN_KOTA)){
                                                    %>
                                                        <li id="chaneltype"><a href="<%= approot %>/dslik/contentdata/kabupatenKota.jsp"><i class="fa fa-circle-o"></i> Kabupaten Kota</a></li>
                                            
                                                    <%
                                                }
                                                if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_KATEGORI_DEBITUR)){
                                                    %>
                                                        <%--<li id="chaneltype"><a href="<%= approot %>/dslik/contentdata/kantorCabang.jsp"><i class="fa fa-circle-o"></i> Kantor Cabang</a></li>--%>
                                                        <li id="chaneltype"><a href="<%= approot %>/dslik/contentdata/kategoriDebitur.jsp"><i class="fa fa-circle-o"></i> Kategori Debitur</a></li>
                                            
                                                    <%
                                                }
                                                if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_KODE_NEGARA_DOMISILI)){
                                                    %>
                                                        <li id="chaneltype"><a href="<%= approot %>/dslik/contentdata/kodeNegaraDomisili.jsp"><i class="fa fa-circle-o"></i> Kode Negara Domisili</a></li>
                                            
                                                    <%
                                                }
                                                if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_KODE_PEKERJAAN)){
                                                    %>
                                                        <li id="chaneltype"><a href="<%= approot %>/dslik/contentdata/kodePekerjaan.jsp"><i class="fa fa-circle-o"></i> Kode Pekerjaan</a></li>
                                            
                                                    <%
                                                }
                                                if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_KODE_VALUTA)){
                                                    %>
                                                        <li id="chaneltype"><a href="<%= approot %>/dslik/contentdata/kodeValuta.jsp"><i class="fa fa-circle-o"></i> Kode Valuta</a></li>
                                            
                                                    <%
                                                }
                                                if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_KOLEKTIBILITAS)){
                                                    %>
                                                        <li id="chaneltype"><a href="<%= approot %>/dslik/contentdata/kolektibilitas.jsp"><i class="fa fa-circle-o"></i> Kolektibilitas</a></li>
                                            
                                                    <%
                                                }
                                                if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_KONDISI)){
                                                    %>
                                                        <li id="chaneltype"><a href="<%= approot %>/dslik/contentdata/kondisi.jsp"><i class="fa fa-circle-o"></i> Kondisi</a></li>
                                            
                                                    <%
                                                }
                                                if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_LEMBAGA_PEMERINGKAT)){
                                                    %>
                                                        <li id="chaneltype"><a href="<%= approot %>/dslik/contentdata/lembagaPemeringkat.jsp"><i class="fa fa-circle-o"></i> Lembaga Pemeringkat</a></li>
                                            
                                                    <%
                                                }
                                                if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_NOMOR_IDENTITAS)){
                                                    %>
                                                        <li id="chaneltype"><a href="<%= approot %>/dslik/contentdata/nomorIdentitas.jsp"><i class="fa fa-circle-o"></i> Nomor Identitas</a></li>
                                                <%
                                                }
                                                if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_ORIENTASI_PENGGUNAAN)){
                                                    %>
                                                        <li id="chaneltype"><a href="<%= approot %>/dslik/contentdata/orientasiPenggunaan.jsp"><i class="fa fa-circle-o"></i> Orientasi Penggunaan</a></li>
                                            
                                                    <%
                                                }
                                                if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_SEBAB_MACET)){
                                                    %>
                                                        <li id="chaneltype"><a href="<%= approot %>/dslik/contentdata/sebabMacet.jsp"><i class="fa fa-circle-o"></i> Sebab Macet</a></li>
                                            
                                                    <%
                                                }
                                                if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_SEKTOR_EKONOMI)){
                                                    %>
                                                        <li id="chaneltype"><a href="<%= approot %>/dslik/contentdata/sektorEkonomi.jsp"><i class="fa fa-circle-o"></i> Sektor Ekonomi</a></li>
                                            
                                                    <%
                                                }
                                                if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_SIFAT_KREDIT)){
                                                    %>
                                                        <li id="chaneltype"><a href="<%= approot %>/dslik/contentdata/sifatKredit.jsp"><i class="fa fa-circle-o"></i> Sifat Kredit</a></li>
                                            
                                                    <%
                                                }
                                                if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_SKIM_AKAD_PEMBIAYAAN)){
                                                    %>
                                                        <li id="chaneltype"><a href="<%= approot %>/dslik/contentdata/skimAkadPembiayaan.jsp"><i class="fa fa-circle-o"></i> Skim Akad Pembiayaan</a></li>
                                            
                                                    <%
                                                }
                                                if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_STATUS_AGUNAN)){
                                                    %>
                                                        <li id="chaneltype"><a href="<%= approot %>/dslik/contentdata/statusAgunan.jsp"><i class="fa fa-circle-o"></i> Status Agunan</a></li>
                                            
                                                    <%
                                                }
                                                if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_STATUS_PENDIDIKAN)){
                                                    %>
                                                        <li id="chaneltype"><a href="<%= approot %>/dslik/contentdata/statusPendidikan.jsp"><i class="fa fa-circle-o"></i> Status Pendidikan</a></li>
                                            
                                                    <%
                                                }
                                                if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_STATUS_PERKAWINAN_DEBITUR)){
                                                    %>
                                                        <li id="chaneltype"><a href="<%= approot %>/dslik/contentdata/statusPerkawinan.jsp"><i class="fa fa-circle-o"></i> Status Perkawinan Debitur</a></li>
                                            
                                                    <%
                                                }
                                                if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_SUKU_BUNGA)){
                                                    %>
                                                        <li id="chaneltype"><a href="<%= approot %>/dslik/contentdata/sukuBunga.jsp"><i class="fa fa-circle-o"></i> Suku Bunga</a></li>
                                            
                                                    <%
                                                }
                                                if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_SUMBER_PENGHASILAN)){
                                                    %>
                                                        <li id="chaneltype"><a href="<%= approot %>/dslik/contentdata/sumberPenghasilan.jsp"><i class="fa fa-circle-o"></i> Sumber Penghasilan</a></li>
                                            
                                                    <%
                                                }
                                                if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_TAKEOVER)){
                                                    %>
                                                        <li id="chaneltype"><a href="<%= approot %>/dslik/contentdata/takeover.jsp"><i class="fa fa-circle-o"></i> Takeover</a></li>
                                            
                                                    <%
                                                }
                                                if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_TUJUAN_GARANSI)){
                                                    %>
                                                        <li id="chaneltype"><a href="<%= approot %>/dslik/contentdata/tujuanGaransi.jsp"><i class="fa fa-circle-o"></i> Tujuan Garansi</a></li>
                                            
                                                    <%
                                                }
                                                if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_TUJUAN_LC)){
                                                    %>
                                                        <li id="chaneltype"><a href="<%= approot %>/dslik/contentdata/tujuanLc.jsp"><i class="fa fa-circle-o"></i> Tujuan LC</a></li>
                                            
                                                    <%
                                                }
                                                if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_DSLIK_DEBITUR)){
                                                    %>
                                                        <li id="chaneltype"><a href="<%= approot %>/page/masterdata/chaneltype.jsp"><i class="fa fa-circle-o"></i> Dslik Debitur</a></li>
                                            
                                                    <%
                                                }
                                                if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_DSLIK_FASILITAS_LAIN)){
                                                    %>
                                                        <li id="chaneltype"><a href="<%= approot %>/page/masterdata/chaneltype.jsp"><i class="fa fa-circle-o"></i> Dslik Fasilitas Lain</a></li>
                                            
                                                    <%
                                                }
                                                if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_DSLIK_IRREVICABLE_LC)){
                                                    %>
                                                        <li id="chaneltype"><a href="<%= approot %>/page/masterdata/chaneltype.jsp"><i class="fa fa-circle-o"></i> Dslik Irrevocable LC</a></li>
                                                    
                                                    <%
                                                }
                                            %>
                                            
                                        </ul>
                                  </li>
                                <%
                            }
                          %>
                        </ul>
                      </li>
                <%
            }
            
            if(userSession.checkPrivG1(AppObjInfo.G1_CONFIGURASI)){
                %>
                    <li class="treeview" id="configurasi">
                        <a href="#">
                          <i class="fa fa-th-list"></i>
                          <span>Configurasi</span>
                          <i class="fa fa-angle-left pull-right"></i>
                        </a>
                        <ul class="treeview-menu">
                        <%
                            if(userSession.checkPrivG1G2(AppObjInfo.G1_CONFIGURASI, AppObjInfo.G2_CONFIGURASI_DATABASES)){
                                %>
                                <li id="databases"><a href="<%= approot %>/dslik/configurasi/configurasi_databases.jsp"><i class="fa fa-circle-o"></i>Configurasi Databases </a></li>
                                <%
                            }
                        %>
                          
                        </ul>
                    </li>
                <%
            }
            
            if(userSession.checkPrivG1(AppObjInfo.G1_ADMIN)){
                %>
                    <li class="treeview" id="system">
                        <a href="#">
                          <i class="fa fa-th-list"></i>
                          <span>System</span>
                          <i class="fa fa-angle-left pull-right"></i>
                        </a>
                        <ul class="treeview-menu">
                            <%
                                if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_ADMIN, AppObjInfo.G2_ADMIN_USER, AppObjInfo.OBJ_ADMIN_USER_USER)){
                                    %>
                                        <li id="user"><a href="<%= approot %>/dslik/user/dslik_userlist.jsp"><i class="fa fa-circle-o"></i>User List</a></li>
                                    <%
                                }
                                
                                if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_ADMIN, AppObjInfo.G2_ADMIN_USER, AppObjInfo.OBJ_ADMIN_USER_GROUP)){
                                    %>
                                        <li id="grouppriv"><a href="<%= approot %>/dslik/user/grouplist_new.jsp"><i class="fa fa-circle-o"></i>Group Privilage</a></li>
                                    <%
                                }
                                
                                if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_ADMIN, AppObjInfo.G2_ADMIN_USER, AppObjInfo.OBJ_ADMIN_USER_PRIVILEGE)){
                                    %>
                                        <li id="privilage"><a href="<%= approot %>/dslik/user/privilagelist_new.jsp"><i class="fa fa-circle-o"></i>Privilage</a></li>
                                    <%
                                }
                            %>
                          
                          
                        </ul>
                    </li>
                <%
            }
        %>
        
      </ul>
    </section>
    <!-- /.sidebar -->
</aside>