
<%@page import="com.dimata.wpupload.entity.wpnamabadan.PstNamaBadan"%>
<%@page import="com.dimata.wpupload.entity.wpnamabadan.NamaBadan"%>
<%@page import="com.dimata.wpupload.entity.logspaymentwp.PstLogPaymentWp"%>
<%@page import="com.dimata.wpupload.ajax.AjaxPaymentChanel"%>
<%@page import="com.dimata.wpupload.entity.paymentchanel.PstPaymentChanel"%>
<%@page import="com.dimata.qdep.form.FRMQueryString"%>
<%@page import="com.dimata.util.Command"%>
<%@page import="com.dimata.wpupload.form.wpnamabadan.FrmNamaBadan"%>
<%@page import="com.dimata.wpupload.form.esptpd.FrmESPTPD"%>
<%@page import="com.dimata.wpupload.entity.esptpd.PstESPTPD"%>
<%@page import="com.dimata.wpupload.entity.wpuser.PstAppUserWP"%>
<%@page import="java.util.Date"%>
<%@page import="com.dimata.wpupload.entity.esptpd.ESPTPD"%>
<%@page import="java.util.Calendar"%>
<%@page import="com.dimata.gui.jsp.ControlList"%>
<%@page import="java.util.Vector"%>
<%@include file="../main/javainit-wp.jsp" %>

<!DOCTYPE html>
<html lang="en">
    <%    String submitrespon = FRMQueryString.requestString(request, "submitrespon");
        String masaPajakRespon = FRMQueryString.requestString(request, "masapajaklapor");
        String tahunPajakRespon = FRMQueryString.requestString(request, "tahunpajaklapor");
        String npwpdRespon = FRMQueryString.requestString(request, "npwpdpajaklapor");
        String messageStringRespon = FRMQueryString.requestString(request, "messageStringRespon");
        boolean submitResponUpload = false;
        if (submitrespon.equals("ok")) {
            submitResponUpload = true;
        }
        
        Vector listNamaBadan = new Vector(1,1);
        NamaBadan namaBadan = new NamaBadan();
        listNamaBadan = PstNamaBadan.listJoin(0, 0, 
                "viewUser."+PstAppUserWP.fieldNames[PstAppUserWP.FLD_USER_ID]+"='"+ userIdWP +"'", "");

        if(listNamaBadan.size() != 0){
            namaBadan = (NamaBadan) listNamaBadan.get(0);
        }
    %>     
    <head>
        <meta charset="utf-8">
        <title>Daftar Pelaporan Pajak - Wajib Pajak</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <meta name="apple-mobile-web-app-capable" content="yes">   
        <link href="../styles/wpupload/img/favicon.ico" rel="shortcut icon" />
        <link href="../styles/wpupload/css/bootstrap.min.css" rel="stylesheet">
        <link href="../styles/wpupload/css/bootstrap-responsive.min.css" rel="stylesheet">
        <link href="../styles/wpupload/css/font-awesome.css" rel="stylesheet">
        <link href="../styles/wpupload/css/font-google.css" rel="stylesheet"> 
        <link href="../styles/wpupload/css/style.css" rel="stylesheet">
        <!--Data Table -->
        <link href="../styles/wpupload/datatables-plugins/integration/bootstrap/2/dataTables.bootstrap.css" rel="stylesheet" type="text/css" />
        <link href="../styles/wpupload/datatables-responsive/css/dataTables.responsive.css" rel="stylesheet">
        <style type="text/css">
            .btn-pay-chanel{
                border: none;
                width: 500px;
                padding: 2%;
                border-radius: 5px;
                font-weight: bold;
                margin-bottom: 10px;
            }
            #paymentchanel{
                text-align: center;
            }
            #payChanel{
                margin-top: 40px;
            }
            .btn-succes{
                background-color: #34eb5b;
                color: #ffff;
                font-weight: bold;
                width: 500px;
                padding: 1%;
                border: none;
                border-radius: 5px;
            }#footerpay{
                margin-top: 30px;
            }#prosesPayment ul{
                list-style-type: none;
            }#continuePay:hover{
                background-color: #25a941;
            }#backPay{
                background-color: transparent;
                border: none;
            }#qrcode canvas{
                margin-top: 30px;
            }#headerQris #nmQris{
                padding-top: 75px;
                font-weight: bold;
                font-size: 18px;
            }#headerQris #nmidQris{
                font-size: 12px;
            }#footerQris{
                float: left;
            }#footerQris #doQris{
                margin-left: 35px
            }.removeleft{
                left: 0px !important;
            }#btnCopyCodeVa{
                border: 2px solid black;
                background-color: white;
                color: black;
                padding: 10px 28px;
                font-size: 16px;
                cursor: pointer;
                border-color: #04AA6D;
                color: green;
                border-radius: 10px;
                margin-top: 10px;
                width: 100%;
            }#btnCopyCodeVa:hover {
                background-color: #04AA6D;
                color: white;
            }.imgloading{
                animation: rotation 2s infinite linear;
                width: 30px;
            }.swal-button--danger{
                background: #7cd1f9;
            }.swal-button--danger:hover{
                background: #a7e0fa !important;
            }
            @media only screen and (max-width: 500px) {
                .btn-pay-chanel{
                    width: 300px;
                    padding: 20px;
                }#continuePay{
                    width: 300px;
                    padding: 20px;
                }
            }
            @media only screen and (min-width: 500px) {
                #vadivpayment{
                    width: 65%;
                }
            }
        </style>
        <!-- css optional untuk bootstrap, khusus untuk template ini -->
        <link href="../styles/wpupload/css/pages/optional.css" rel="stylesheet">
    </head>
    <body>
        <div class="navbar navbar-fixed-top">
            <div class="navbar-inner">
                <%@include file="wp-fixtop.jsp" %>
            </div>
        </div>

        <div class="subnavbar">
            <div class="subnavbar-inner">
                <div class="container">
                    <%
                        String activeHome = "";
                        String activeNamaBadan = "";
                        String activeList = "";
                        String activeNamaBadanPanduan = "";
                        activePembayaran = "active";
                        String activeManualTagihan="";
                    %>
                    <%@include file="wp-navbar.jsp" %>
                </div>
            </div>
        </div>
        <div class="main">
            <div class="main-inner">
                <div class="container">
                    <div class="row">
                        <div class="span4"><br></div>
                        <div class="span6" id="result">
                            <div class="widget " id="widget-content-modif" >
                                <div class="widget-content" id="selectPaymnet">
                                    <div>
                                        <div id="loadingPage" style="height: 600px">
                                            <center><img src="../images/loadingv1.gif" style="margin-top: 200px;"></center>
                                        </div>
                                        <div id="selectPayment" style="display: none;">
                                            <div class="row">
                                                <div class="col"><center><b><h3>Silahkan Pilih Metode Pembayaran Yang Diinginkan.</h3></b></center></div>
                                            </div>
                                            <div id="payChanel">
                                                <div class="row datapayment" id="datapayment" style="text-align: center">
                                                </div>
                                            </div>
                                            <div id="footerpay">
                                                <div class="row" style="text-align: center">
                                                    <div>
                                                        <button class="btn-succes" id="continuePay">Lanjutkan Pembayaran</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div id="prosesPayment">
                                    </div>
                                </div> 
                            </div> 
                        </div>
                    </div>
                </div>
            </div>
            <br><br><br>
        </div>
        <!-- /main -->
        <div class="footer">
            <div class="footer-inner">
                <div class="container">
                    <%@include file="wp-footer.jsp" %>
                </div>
            </div>
        </div>


        <style type="text/css">
            .widget{
                float:left;
                width:100%;
            }
            input, select, textarea{
                width:auto;
            }
            .container{
                width:95%;
            }
            .span6{
                width:48%;
                margin-left:1%;
            }
            .widget-content{
                padding: 20px 5px;
            }
            .span12{
                width:99%;
            }
            .example{
                width:100%;
            }
            .swal-modal .swal-text {
                text-align: center;
            }.widHeadTutor{
                background: #fff;
            }
        </style>
        
        <script src="../styles/wpupload/js/jquery-1.7.2.min.js"></script>
        <script src="../styles/wpupload/js/bootstrap.js"></script>
        <script src="../styles/wpupload/js/base.js"></script>
        <script src="../styles/wpupload/datatables/media/js/jquery.dataTables.min.js"></script>
        <script src="../styles/wpupload/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
        <script src="../styles/sweetAlert.js"></script>
        <script src="../styles/jquery.qrcode.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {
            
                var isMobile = false;
                if(/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent)){
                    isMobile = true;
                }
                
                chekStatusPay();
                function chekStatusPay(){
                    var data = "?command=<%=AjaxPaymentChanel.CHECK_STATUS_PAY%>&npwpd=<%=namaBadan.getNbNPWPD()%>";
                    var url = "<%= approot %>/AjaxPaymentChanel"+data;
                    var dataAjax = ajax(url,"POST","");
                    if(dataAjax.paystatus == <%=PstLogPaymentWp.CHOOES_METHOD_PAYMNET%> && dataAjax.status == '<%=Command.RES_OK%>'){
                        refreshdata();
                        $("#loadingPage").hide();
                        $("#selectPayment").show();
                    }else if(dataAjax.paystatus == <%=PstLogPaymentWp.PENDING_PAYMNET%> && dataAjax.status == '<%=Command.RES_OK%>'){
                        $("#loadingPage").show();
                        setTimeout(showMediaPay(dataAjax.tipepay),5000);
                        $("#prosesPayment").show();
                    }else{
                        alert();
                        $("#selectPayment").show();
                        $("#selectPayment").html("");
                        $("#selectPayment").html(dataAjax.msg);
                    }
                    
                    copyCode();
                }
                
                function contiuePay(){
                    $("#continuePay").click(function(){
                        var getIdPay = $("#<%=PstPaymentChanel.fieldNames[PstPaymentChanel.FLD_PAYMENT_ID]%>").val();
                        if(getIdPay >0){
                            swal({
                                title: "Pemberitahuan!",
                                text: "Apakah Benar Melakukan Pembayaran Dengan Metode Tersebut ?",
                                icon: "info",
                                buttons: true,
                                dangerMode: true,
                            }).then((confirm) => {
                                if (confirm) {
                                    $("#loadingPage").show();
                                    showMediaPay(getIdPay);
                                }else{
                                    swal("Batal Memilih Metode Pembayaran");
                                }
                            });
                        }else{
                            swal("Peringatan!", "Pilih Metode Pembayaran Terlebih Dahulu!", "error");
                        }
                    });
                }
                
                function showMediaPay(getIdPay){
                    var data = "?command=<%=Command.SSP_FORM %>&ssptype=<%=AjaxPaymentChanel.CHOOSE_PAY_CHANEL%>&payType="+getIdPay+"&userid=<%=namaBadan.getNbNPWPD()%>";
                    var url = "<%= approot %>/AjaxPaymentChanel"+data;
                    var dataAjax = ajax(url,"POST","");
                    var statusRes = 0;
                    
                    $("#loadingPage").hide();
                    $("#selectPayment").html("");
                    $("#prosesPayment").html(dataAjax.data);
                    if(getIdPay == <%=PstPaymentChanel.QRIS %>){
                        this.status = generateQRIS();
                    }else{
                        this.status = dataAjax.status;
                    }
                    
                    var incrm = 0;
                    var datasss = 0;
                    if(this.status == '<%=Command.RES_OK %>'){
                        var chek = setInterval(function(){
                            if(typeof(Worker) !== "undefined") {
                                rotate("imgloading",0,0); 
                                
                                //tidak di gunakan
                                if(isMobile && 1==2 ){
                                    $("#count").html(datasss);
                                    var data = getPayForMobileDavice(dataAjax.noid,dataAjax.bulan,dataAjax.tahun,dataAjax.tagihan);
                                    if(data.status == <%=Command.RES_OK%>){
                                        incrm = incrm + 1;
                                        if(incrm == 1){
                                            swal("Berhasil!", "Pembayaran Berhasil DI Lakukan", "success");
                                        }
                                        console.log("stop....");
                                        selectTypePayment();
                                        clearInterval(chek);
                                        clearInterval(chek);
                                    }
                                }else{
                                    const w = new Worker("<%=approot %>/styles/getpay_workers.js");
                                    w.postMessage([dataAjax.noid,dataAjax.bulan,dataAjax.tahun,dataAjax.tagihan,"<%= approot %>"]);

                                    var resApi = '';
                                    w.onmessage = function(event) {
                                        resApi = JSON.parse(event.data);
                                        $("#count").html(datasss);
                                        if(resApi.status == <%=Command.RES_OK%>){
                                            incrm = incrm + 1;
                                            if(incrm == 1){
                                                swal("Berhasil!", "Pembayaran Berhasil DI Lakukan", "success");
                                            }
                                            console.log("stop....");
                                            selectTypePayment();
                                            clearInterval(chek);
                                            clearInterval(chek);
                                        }
                                    };
                                    
                                    rotate("imgloading",360,1200);
                                }
                            }else{
                                alert("tidak bisa");
                            }
                            if(datasss == 15){
                                window.location.href = '<%=approot %>/wpupload/wp-pembayaran.jsp';
                            }
                            datasss = datasss +1;
                        }, 5000);
                    }else if(this.status == <%=Command.SSP_FORM%>){
                        selectTypePayment();
                    }else{
                        $(".checkpay").hide();
                    }
                    

                    hideTutor();
                    backPay();
                }
                
                function hideTutor(){
                    $(".showhidecontent").hide();
                    $(".showhide").click(function(){
                        var caret = $(this).find(".icon-caret-down").length;
                        if(caret > 0){
                            $(this).html("<i class='icon icon-caret-right' style='margin-left:0px;'></i>");
                        }else{
                            $(this).html("<i class='icon icon-caret-down' style='margin-left:0px;'></i>");
                        }
                        $(this).parents(".prosesPaymentTutor").find(".showhidecontent").slideToggle();
                    });
                    var width = $(window).width();
                    if(width < 768){
                        $(".showhidecontent").hide();
                        $(".showhide").html("<i class='icon icon-caret-right' style='margin:0px;'></i>");
                        $("#pelaporanpajak").parent().find(".showhide").html("<i class='icon icon-caret-down' style='margin:0px;'></i>");
                        $("#pelaporanpajak").show();
                    }
                }

                function selectBtnPay(){
                    $(".btn-pay-chanel").click(function (){
                        rmSelectedCL("removeselect");
                        var dataSel = $(this).data("css");
                        var valSlctPay = $(this).val();
                        $("#selectPayment #<%=PstPaymentChanel.fieldNames[PstPaymentChanel.FLD_PAYMENT_ID] %>").val(valSlctPay);
                        setSelected(dataSel);
                    });
                }
                function backPay(){
                    $("#backPay").click(function (){
                        swal({
                            title: "Pemberitahuan!",
                            text: "Apakah Benar Ingin Membatalkan Pembayaran Ini ?",
                            icon: "info",
                            buttons: true,
                            dangerMode: true,
                        }).then((confirm) => {
                            if (confirm) {
                                selectTypePayment();
                            }else{
                                swal("Batal Memilih Metode Pembayaran");
                            }
                        });
                    });
                }
                function selectTypePayment(){
                    var data = "?command=<%=AjaxPaymentChanel.RESET_STATUS_PAY%>&npwpd=<%=namaBadan.getNbNPWPD()%>";
                    var url = "<%= approot %>/AjaxPaymentChanel"+data;
                    var dataAjax = ajax(url,"POST","");

                    $("#selectPayment").show();
                    refreshdata();
                }
                
                function refreshdata(){
                    $("#prosesPayment").html("");
                    var data = "?command=<%=Command.SSP_FORM %>&ssptype=<%=AjaxPaymentChanel.PAY_CHANEL %>&userid=<%=namaBadan.getNbNPWPD()%>";
                    var url = "<%= approot %>/AjaxPaymentChanel"+data;
                    var dataAjax = ajax(url,"POST","");
                    
                    $("#loadingPage").hide();
                    $("#selectPayment").html("");
                    $("#selectPayment").html(dataAjax.data);
                    selectBtnPay();
                    contiuePay();
                }
                
                //set scc when select pay chanel 
                function setSelected(element){ 
                    $("#"+element).css("border"," 2px solid black");
                    $("#"+element).css("background-color","#00ba8b");
                    $("#"+element).css("color","#fff");
                }
                function rmSelectedID(element){
                    $("#"+element).css("border","");
                    $("#"+element).css("background-color","#f0f0f0");
                    $("#"+element).css("color","#000");
                }
                function rmSelectedCL(element){
                    $("."+element).css("border","");
                    $("."+element).css("background-color","#f0f0f0");
                    $("."+element).css("color","#000");
                }
                
                function generateQRIS(){ 
                    var data = "?command=<%=AjaxPaymentChanel.GENERATE_QRIS %>&npwpd=<%=namaBadan.getNbNPWPD()%>";
                    var url = "<%= approot %>/AjaxPaymentChanel"+data;
                    var dataAjax = ajax(url,"POST","");
                    
                    if(dataAjax.status == '<%=Command.RES_ERROR %>' || dataAjax.stsbyr == '<%=AjaxPaymentChanel.STATUS_BAYAR %>'){
                        $('#containerqris').html(dataAjax.msgview);
                    }else{
                        $('#qrcode').qrcode({width: 230,height: 230,text: dataAjax.qrValue});
                        $("#nmQris").html(dataAjax.merchantName);
                        $("#nmidQris").html(dataAjax.nmid);
                        $("#ctkOlehQris").html(dataAjax.ctkoleh);
                        $("#expireddate").html(dataAjax.expireddate);
                    }
                    return dataAjax.status;
                }
                
                function getPayForMobileDavice(noid,bulan,tahun,tagihan){
                    var data = "?command=<%=AjaxPaymentChanel.CHEK_PAY_ON_BANK %>&noid="+noid+"&bulan="+bulan+"&tahun="+tahun+"&tagihan="+tagihan;
                    var url = "<%= approot %>/AjaxPaymentChanel"+data;
                    var dataAjax = ajaxbg(url,"POST","");
                    return dataAjax;
                }
                
                function copyCode(){
                    $(".btnCopyCodeVa").click(function() {
                        var dtcode = $(this).data('code');
                        
                        var $temp = $("<input>");
                        $("body").append($temp);
                        $temp.val(dtcode).select();
                        document.execCommand("copy");
                        $temp.remove();
                        swal("VA Code Copied"); 
                    });
                }
                
                function rotate(element,rotate,duration){
                    $("."+element).animate(
                        { deg: rotate },
                        {
                          duration: duration,
                          step: function(now) {
                            $(this).css({ transform: 'rotate(' + now + 'deg)' });
                          }
                        }
                      );
                }
                
                //ajax function
                function ajax(url,type,datafrm){
                    var dataRes = '';
                    $.ajax({
                        'async'       : false,
                        'type'        : type,
                        'url'         : ""+url,
                        'data'        : datafrm,
                        'cache'       : false,
                        'async'       : false,
                        'dataType'    : 'json',
                        'success'   : function(data){
                            dataRes = data;
                        }
                    }); 
                    return dataRes;
                }
                
                //ajax function
                function ajaxbg(url,type,datafrm){
                    var dataRes = '';
                    $.ajax({
                        'async'       : false,
                        'type'        : type,
                        'url'         : ""+url,
                        'data'        : datafrm,
                        'cache'       : false,
                        'async'       : false,
                        'dataType'    : 'json',
                        'cache'       : false,
                        'crossDomain' : true,
                        'success'     : function(data){
                            dataRes = data;
                        }
                    }); 
                    return dataRes;
                }
            });
        </script>
    </body>
</html>
