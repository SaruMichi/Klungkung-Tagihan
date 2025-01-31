<%-- 
    Document   : menu_left_mobile
    Created on : Aug 8, 2014, 2:06:00 PM
    Author     : dimata005
--%>

<%@page import="com.dimata.saras.entity.admin.AppObjInfo"%>
<%@page import="com.dimata.qdep.form.FRMQueryString"%>
<aside class="left-side sidebar-offcanvas">
    <!-- sidebar: style can be found in sidebar.less -->
    <%
      int menu = FRMQueryString.requestInt(request, "menu");
      int tree = FRMQueryString.requestInt(request, "tree");
    %>
    <section class="sidebar">
        <div class="user-panel">
            <div class="pull-left image">
                <img src="<%=approot%>/styles/bootstrap3.1/img/avatar3.png" class="img-circle" alt="User Image" />
            </div>
            <div class="pull-left info">
                <p>Hello, <%=userName%></p>
            </div>
        </div>
        <ul class="sidebar-menu">
            <% if(userSession.checkPrivG1G2(AppObjInfo.G1_PROSES_INTEGRASI, AppObjInfo.G2_PHR)){ %>
                <li <%=tree==1?"class=\"treeview active\"":"class=\"treeview\""%> >
                    <a href="#">
                        <i class="fa fa-edit"></i> <span>PHR</span>
                        <i class="fa fa-angle-left pull-right"></i>
                    </a>
                    <ul class="treeview-menu">
                        <% if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_PROSES_INTEGRASI,AppObjInfo.G2_PHR,AppObjInfo.OBJ_UPLOAD_MANUAL_PHR)){ %>
                            <li <%=menu==1?"class=\"active\"":""%>>
                                <a href="<%=approot%>/dtaxintegrationsimda/data/manual_phr_hotel.jsp?menu=1&tree=1">
                                    <i class="fa fa-th"></i> <span>PHR - Hotel</span>
                                </a>
                            </li>
                        <%}%>
                        <% if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_PROSES_INTEGRASI,AppObjInfo.G2_PHR,AppObjInfo.OBJ_UPLOAD_MANUAL_PHR)){ %>
                            <li <%=menu==2?"class=\"active\"":""%>>
                                <a href="<%=approot%>/dtaxintegrationsimda/data/manual_phr_restoran.jsp?menu=2&tree=1">
                                    <i class="fa fa-th"></i> <span>PHR - Restoran</span>
                                </a>
                            </li>
                        <%}%>
                        <% if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_PROSES_INTEGRASI,AppObjInfo.G2_PHR,AppObjInfo.OBJ_UPLOAD_MANUAL_PHR)){ %>
                            <li <%=menu==3?"class=\"active\"":""%>>
                                <a href="<%=approot%>/dtaxintegrationsimda/data/manual_phr_hiburan.jsp?menu=3&tree=1">
                                    <i class="fa fa-th"></i> <span>PHR - Hiburan</span>
                                </a>
                            </li>
                        <%}%>
                    </ul>
                </li>
            <%}%>
            <!-- PBB -->
            <%--
            <% if(userSession.checkPrivG1G2(AppObjInfo.G1_PROSES_INTEGRASI, AppObjInfo.G2_PBB)){ %>
                <li <%=tree==2?"class=\"treeview active\"":"class=\"treeview\""%> >
                    <a href="#">
                        <i class="fa fa-edit"></i> <span>PBB</span>
                        <i class="fa fa-angle-left pull-right"></i>
                    </a>
                    <ul class="treeview-menu">
                        <% if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_PROSES_INTEGRASI,AppObjInfo.G2_PBB,AppObjInfo.OBJ_UPLOAD_FILE_PBB)){ %>
                            <li <%=menu==4?"class=\"active\"":""%>>
                                 <a href="<%=approot%>/dtaxintegration/data/pbb.jsp?menu=4&tree=2">
                                    <i class="fa fa-th"></i> <span>Upload File PBB</span>
                                </a>
                            </li>
                        <%}%>
                        <% if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_PROSES_INTEGRASI,AppObjInfo.G2_PBB,AppObjInfo.OBJ_UPLOAD_MANUAL_PBB)){ %>
                            <li <%=menu==5?"class=\"active\"":""%>>
                                <a href="<%=approot%>/dtaxintegration/data/manual_pbb.jsp?menu=5&tree=2">
                                    <i class="fa fa-th"></i> <span>Upload Manual PBB</span>
                                </a>
                            </li>
                        <%}%>
                        <% if(userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_PROSES_INTEGRASI,AppObjInfo.G2_PBB,AppObjInfo.OBJ_DELETE_ALL_INSTANSI_PBB, AppObjInfo.COMMAND_VIEW)){ %>
                            <li <%=menu==30?"class=\"active\"":""%>>
                                <a href="<%=approot%>/dtaxintegration/data/delete_instansi_pbb.jsp?menu=30&tree=2">
                                    <i class="fa fa-th"></i> <span>Delete Semua Tagihan PBB</span>
                                </a>
                            </li>
                        <%}%>
                        <% if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_PROSES_INTEGRASI,AppObjInfo.G2_PBB,AppObjInfo.OBJ_DOWNLOAD_PAYMENT_PBB)){ %>
                            <li <%=menu==6?"class=\"active\"":""%>>
                                <a href="<%=approot%>/dtaxintegration/payment/payment_pbb.jsp?menu=6&tree=2">
                                    <i class="fa fa-th"></i> <span>Manual Payment PBB</span>
                                </a>
                            </li>
                        <%}%>
                    </ul>
                </li>
            <%}%>
            <!--BPHTB-->
            <% if(userSession.checkPrivG1G2(AppObjInfo.G1_PROSES_INTEGRASI, AppObjInfo.G2_BPHTB)){ %>
                <li <%=tree==3?"class=\"treeview active\"":"class=\"treeview\""%> >
                    <a href="#">
                        <i class="fa fa-edit"></i> <span>BPHTB</span>
                        <i class="fa fa-angle-left pull-right"></i>
                    </a>
                    <ul class="treeview-menu">
                        <% if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_PROSES_INTEGRASI,AppObjInfo.G2_BPHTB,AppObjInfo.OBJ_UPLOAD_OTOMATIS_BPHTB)){ %>
                            <li <%=menu==7?"class=\"active\"":""%>>
                                 <a href="<%=approot%>/dtaxintegration/data/automatic_bphtb.jsp?menu=7&tree=3">
                                    <i class="fa fa-th"></i> <span>Otomatis BPHTB</span>
                                </a>
                            </li>
                        <%}%>
                        <% if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_PROSES_INTEGRASI,AppObjInfo.G2_BPHTB,AppObjInfo.OBJ_UPLOAD_MANUAL_BPHTB)){ %>
                            <li <%=menu==8?"class=\"active\"":""%>>
                                <a href="<%=approot%>/dtaxintegration/data/manual_bphtb.jsp?menu=8&tree=3">
                                    <i class="fa fa-th"></i> <span>Upload Manual BPHTB</span>
                                </a>
                            </li>
                        <%}%>
                        <% if(userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_PROSES_INTEGRASI,AppObjInfo.G2_BPHTB,AppObjInfo.OBJ_DELETE_ALL_INSTANSI_BPHTB, AppObjInfo.COMMAND_VIEW)){ %>
                            <li <%=menu==32?"class=\"active\"":""%>>
                                <a href="<%=approot%>/dtaxintegration/data/delete_instansi_bphtb.jsp?menu=32&tree=3">
                                    <i class="fa fa-th"></i> <span>Delete Semua Tagihan BPHTB</span>
                                </a>
                            </li>
                        <%}%>
                        <% if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_PROSES_INTEGRASI,AppObjInfo.G2_BPHTB,AppObjInfo.OBJ_DOWNLOAD_PAYMENT_BPHTB)){ %>
                            <li <%=menu==9?"class=\"active\"":""%>>
                                <a href="<%=approot%>/dtaxintegration/payment/payment_bphtb.jsp?menu=9&tree=3">
                                    <i class="fa fa-th"></i> <span>Manual Payment BPHTB</span>
                                </a>
                            </li>
                        <%}%>
                    </ul>
                </li>
            <%}%>
            <!-- Retribusi -->
           <% if(userSession.checkPrivG1G2(AppObjInfo.G1_PROSES_INTEGRASI, AppObjInfo.G2_RETRIBUSI)){ %>
                <li <%=tree==4?"class=\"treeview active\"":"class=\"treeview\""%> >
                    <a href="#">
                        <i class="fa fa-edit"></i> <span>Retribusi</span>
                        <i class="fa fa-angle-left pull-right"></i>
                    </a>
                    <ul class="treeview-menu">
                        <% if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_PROSES_INTEGRASI,AppObjInfo.G2_RETRIBUSI,AppObjInfo.OBJ_UPLOAD_OTOMATIS_RETRIBUSI)){ %>
                            <li <%=menu==10?"class=\"active\"":""%>>
                                <a href="<%=approot%>/dtaxintegration/data/automatic_retribusi.jsp?menu=10&tree=4">
                                    <i class="fa fa-th"></i> <span>Otomatis Retribusi</span>
                                </a>
                            </li>
                        <%}%>
                        <% if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_PROSES_INTEGRASI,AppObjInfo.G2_RETRIBUSI,AppObjInfo.OBJ_UPLOAD_MANUAL_RETRIBUSI)){ %>
                            <li <%=menu==11?"class=\"active\"":""%>>
                                <a href="<%=approot%>/dtaxintegration/data/manual_retribusi.jsp?menu=11&tree=4">
                                    <i class="fa fa-th"></i> <span>Upload Manual Retribusi</span>
                                </a>
                            </li>
                        <%}%>
                        <% if(userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_PROSES_INTEGRASI,AppObjInfo.G2_RETRIBUSI,AppObjInfo.OBJ_DELETE_ALL_INSTANSI_RETRIBUSI, AppObjInfo.COMMAND_VIEW)){ %>
                            <li <%=menu==33?"class=\"active\"":""%>>
                                <a href="<%=approot%>/dtaxintegration/data/delete_instansi_retribusi.jsp?menu=33&tree=4">
                                    <i class="fa fa-th"></i> <span>Delete Semua Tagihan Retribusi</span>
                                </a>
                            </li>
                        <%}%>
                        <% if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_PROSES_INTEGRASI,AppObjInfo.G2_RETRIBUSI,AppObjInfo.OBJ_DOWNLOAD_PAYMENT_RETRIBUSI)){ %>
                            <li <%=menu==12?"class=\"active\"":""%>>
                                 <a href="<%=approot%>/dtaxintegration/payment/payment_retribusi.jsp?menu=12&tree=4">
                                    <i class="fa fa-th"></i> <span>Manual Payment Retribusi</span>
                                </a>
                            </li>
                        <%}%>
                    </ul>
                </li>
            <%}%>
            
            <% if(userSession.checkPrivG1(AppObjInfo.G1_REPORT)){ %>
                <li <%=tree==5?"class=\"treeview active\"":"class=\"treeview\""%> >
                    <a href="#">
                        <i class="fa fa-edit"></i> <span>Report</span>
                        <i class="fa fa-angle-left pull-right"></i>
                    </a>
                    <ul class="treeview-menu">
                        <% if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_REPORT,AppObjInfo.G2_REPORT_PHR, AppObjInfo.OBJ_REPORT_DETAIL_PHR)){ %>
                            <li <%=menu==13?"class=\"active\"":""%> >
                                <a href="<%=approot%>/dtaxintegration/report/report_phr.jsp?menu=13&tree=5">
                                    <i class="fa fa-th"></i> <span>Report PHR</span>
                                </a>
                            </li>
                        <%}%>
                        <% if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_REPORT,AppObjInfo.G2_REPORT_PHR, AppObjInfo.OBJ_REPORT_GLOBAL_PHR)){ %>
                            <li <%=menu==14?"class=\"active\"":""%> >
                                <a href="<%=approot%>/dtaxintegration/report/report_global_phr.jsp?menu=14&tree=5">
                                    <i class="fa fa-th"></i> <span>Report Global PHR</span>
                                </a>
                            </li>
                        <%}%>
                        <% if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_REPORT,AppObjInfo.G2_REPORT_PBB, AppObjInfo.OBJ_REPORT_DETAIL_PBB)){ %>
                            <li <%=menu==15?"class=\"active\"":""%> >
                                <a href="<%=approot%>/dtaxintegration/report/report_pbb.jsp?menu=15&tree=5">
                                    <i class="fa fa-th"></i> <span>Report PBB</span>
                                </a>
                            </li>
                            <li <%=menu==151?"class=\"active\"":""%> >
                                <a href="<%=approot%>/dtaxintegration/report/report_pbb_harian.jsp?menu=151&tree=5">
                                    <i class="fa fa-th"></i> <span>Report PBB Per Hari</span>
                                </a>
                            </li>
                            <li <%=menu==152?"class=\"active\"":""%> >
                                <a href="<%=approot%>/dtaxintegration/report/report_pbb_bulanan.jsp?menu=152&tree=5">
                                    <i class="fa fa-th"></i> <span>Report PBB Per Bulan</span>
                                </a>
                            </li>
                        <%}%>
                        <% if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_REPORT,AppObjInfo.G2_REPORT_PBB, AppObjInfo.OBJ_REPORT_GLOBAL_PBB)){ %>
                            <li <%=menu==25?"class=\"active\"":""%> >
                                <a href="<%=approot%>/dtaxintegration/report/report_global_pbb.jsp?menu=25&tree=5">
                                    <i class="fa fa-th"></i> <span>Report Global PBB</span>
                                </a>
                            </li>
                        <%}%>
                        <% if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_REPORT,AppObjInfo.G2_REPORT_BPHTB, AppObjInfo.OBJ_REPORT_DETAIL_BPHTB)){ %>
                            <li <%=menu==16?"class=\"active\"":""%> >
                                <a href="<%=approot%>/dtaxintegration/report/report_bphtb.jsp?menu=16&tree=5">
                                    <i class="fa fa-th"></i> <span>Report BPHTB</span>
                                </a>
                            </li>
                        <%}%>
                        <% if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_REPORT,AppObjInfo.G2_REPORT_BPHTB, AppObjInfo.OBJ_REPORT_GLOBAL_BPHTB)){ %>
                            <li <%=menu==26?"class=\"active\"":""%> >
                                <a href="<%=approot%>/dtaxintegration/report/report_global_bphtb.jsp?menu=26&tree=5">
                                    <i class="fa fa-th"></i> <span>Report Global BPHTB</span>
                                </a>
                            </li>
                        <%}%>
                        <% if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_REPORT,AppObjInfo.G2_REPORT_RETRIBUSI, AppObjInfo.OBJ_REPORT_DETAIL_RETRIBUSI)){ %>
                            <li <%=menu==18?"class=\"active\"":""%> >
                                <a href="<%=approot%>/dtaxintegration/report/report_retribusi.jsp?menu=18&tree=5">
                                    <i class="fa fa-th"></i> <span>Report Retribusi</span>
                                </a>
                            </li>
                        <%}%>
                        <% if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_REPORT,AppObjInfo.G2_REPORT_RETRIBUSI, AppObjInfo.OBJ_REPORT_GLOBAL_RETRIBUSI)){ %>
                            <li <%=menu==27?"class=\"active\"":""%> >
                                <a href="<%=approot%>/dtaxintegration/report/report_global_retribusi.jsp?menu=27&tree=5">
                                    <i class="fa fa-th"></i> <span>Report Global Retribusi</span>
                                </a>
                            </li>
                        <%}%>
                        <%if(userSession.checkPrivG1G2(AppObjInfo.G1_PROSES_INTEGRASI,AppObjInfo.G2_PHR)){ %>
                              <li <%=menu==101?"class=\"active\"":""%>>
                                  <a href="<%=approot%>/dtaxintegration/report/print_payment_bank.jsp?menu=101&tree=5">
                                      <i class="fa fa-th"></i> <span>Cetak Pembayaran</span>
                                  </a>
                              </li>
                          <%}%>
                    </ul>
                </li>
            <%}%>
            --%>
            <li <%=tree==6?"class=\"treeview active\"":"class=\"treeview\""%> >
                    <a href="#">
                        <i class="fa fa-edit"></i> <span>Payment</span>
                        <i class="fa fa-angle-left pull-right"></i>
                    </a>
                    <ul class="treeview-menu">
                            <%if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_PROSES_INTEGRASI,AppObjInfo.G2_PHR,AppObjInfo.OBJ_DOWNLOAD_ALL_PAYMENT)){ %>
                              <li <%=menu==19?"class=\"active\"":""%>>
                                  <a href="<%=approot%>/dtaxintegrationsimda/payment/manual_data_pajak.jsp?menu=19&tree=6">
                                      <i class="fa fa-th"></i> <span>PHR</span>
                                  </a>
                              </li>
                            <%}%>
                          <%--
                          <%if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_PROSES_INTEGRASI,AppObjInfo.G2_PBB,AppObjInfo.OBJ_DOWNLOAD_PAYMENT_PBB)){ %>
                              <li <%=menu==40?"class=\"active\"":""%>>
                                  <a href="<%=approot%>/dtaxintegration/payment/automatic_payment_pbb.jsp?menu=40&tree=6">
                                      <i class="fa fa-th"></i> <span>Otomatis Payment PBB</span>
                                  </a>
                              </li>
                          <%}%>
                          <%if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_PROSES_INTEGRASI,AppObjInfo.G2_PBB,AppObjInfo.OBJ_DOWNLOAD_PAYMENT_BPHTB)){ %>
                              <li <%=menu==89?"class=\"active\"":""%>>
                                  <a href="<%=approot%>/dtaxintegration/payment/automatic_payment_bphtb.jsp?menu=89&tree=6">
                                      <i class="fa fa-th"></i> <span>Otomatis Payment BPHTB</span>
                                  </a>
                              </li>
                          <%}%>
                           <%if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_PROSES_INTEGRASI,AppObjInfo.G2_PBB,AppObjInfo.OBJ_DOWNLOAD_PAYMENT_RETRIBUSI)){ %>
                              <li <%=menu==90?"class=\"active\"":""%>>
                                  <a href="<%=approot%>/dtaxintegration/payment/automatic_payment_retribusi.jsp?menu=90&tree=6">
                                      <i class="fa fa-th"></i> <span>Otomatis Payment Retribusi</span>
                                  </a>
                              </li>
                          <%}%>  
                          --%>
                    </ul>
            </li>
            <%--
            <% if(userSession.checkPrivG1(AppObjInfo.G1_ADMIN)){%>
                <li <%=tree==7?"class=\"treeview active\"":"class=\"treeview\""%> >
                        <a href="#">
                            <i class="fa fa-edit"></i> <span>Sistem</span>
                            <i class="fa fa-angle-left pull-right"></i>
                        </a>
                        <ul class="treeview-menu">
                            <% if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_ADMIN,AppObjInfo.G2_ADMIN_USER,AppObjInfo.OBJ_ADMIN_USER_USER)){ %>
                                <li <%=menu==20?"class=\"active\"":""%>>
                                    <a href="<%=approot%>/system/userlist.jsp?menu=20&tree=7">
                                        <i class="fa fa-th"></i> <span>User</span>
                                    </a>
                                </li>
                            <%}%>

                            <% if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_ADMIN,AppObjInfo.G2_ADMIN_USER,AppObjInfo.OBJ_ADMIN_USER_GROUP)){ %>
                                <li <%=menu==23?"class=\"active\"":""%>>
                                    <a href="<%=approot%>/system/grouplist_new.jsp?menu=23&tree=7">
                                        <i class="fa fa-th"></i> <span>Group Privilage</span>
                                    </a>
                                </li>
                            <%}%>

                            <% if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_ADMIN,AppObjInfo.G2_ADMIN_USER,AppObjInfo.OBJ_ADMIN_USER_PRIVILEGE)){ %>
                                <li <%=menu==22?"class=\"active\"":""%>>
                                    <a href="<%=approot%>/system/privilagelist_new.jsp?menu=22&tree=7">
                                        <i class="fa fa-th"></i> <span>Privilage</span>
                                    </a>
                                </li>
                            <%}%>
                        </ul>
                </li>
            <%}%>
            --%>
            <%--
            <li <%=tree==8?"class=\"treeview active\"":"class=\"treeview\""%> >
                <a href="#">
                    <i class="fa fa-edit"></i> <span>Online</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                     <% if(userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_WP_UPLOAD,AppObjInfo.G2_REPORT_WP_UPLOAD, AppObjInfo.OBJ_REPORT_WP_UPLOAD, AppObjInfo.COMMAND_VIEW)){ %>
                        <li <%=menu==60?"class=\"active\"":""%>>
                            <a href="<%=approot%>/dtaxintegration/report/report_wp.jsp?menu=60&tree=8">
                                <i class="fa fa-th"></i> <span>Report Online WP</span>
                            </a>
                        </li>
                    <%}%>
                    <% if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_ADMIN,AppObjInfo.G2_ADMIN_USER,AppObjInfo.OBJ_ADMIN_USER_PRIVILEGE)){ %>
                        <li <%=menu==61?"class=\"active\"":""%>>
                            <a href="<%=approot%>/dtaxintegration/masterdata/user-online.jsp?menu=61&tree=8">
                                <i class="fa fa-th"></i> <span>User Online</span>
                            </a>
                        </li>
                    <%}%>
                    <% if(userSession.checkPrivG1G2Obj(AppObjInfo.G1_ADMIN,AppObjInfo.G2_ADMIN_USER,AppObjInfo.OBJ_ADMIN_USER_PRIVILEGE)){ %>
                        <li <%=menu==62?"class=\"active\"":""%>>
                            <a href="<%=approot%>/dtaxintegration/masterdata/setting-email.jsp?menu=62&tree=8">
                                <i class="fa fa-th"></i> <span>Setting Email</span>
                            </a>
                        </li>
                    <%}%>
                </ul>
            </li>  
            --%>
            <li>
                <a href="<%=approot%>/logout_simda.jsp">
                   <span>Logout</span>
                </a>
            </li>
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>
