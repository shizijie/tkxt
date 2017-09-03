<#macro crudmetropage3 ext...>


<!DOCTYPE html>
<html lang="en"> 
<!-- BEGIN HEAD -->
<#assign s=JspTaglibs["/tags/struts-tags.tld"] /> 
<!-- 审核状态 -->
<#assign dispShzt={"0":"无","1":"审核中", "2":"审核通过","3":"审核退回","4":"未提交"}>
<!-- 是否通过 -->
<#assign dispSftg={"0":"不通过","1":"通过"}>
<!-- 通用是否 -->
<#assign dispYn={"0":"否","1":"是"}>
<head>

	<meta charset="utf-8">
	<title>题库系统</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1" >
	<!-- BEGIN GLOBAL MANDATORY STYLES -->
	<link type="text/css" rel="stylesheet" href="${base}/mainface/css/bootstrap.min.css">
	<link type="text/css" rel="stylesheet" href="${base}/mainface/css/bootstrap-responsive.min.css">
	<link type="text/css" rel="stylesheet" href="${base}/mainface/css/font-awesome.min.css">
	<link type="text/css" rel="stylesheet" href="${base}/mainface/css/style-metro.css">
	<link type="text/css" rel="stylesheet" href="${base}/mainface/css/style.css">
	<link type="text/css" rel="stylesheet" href="${base}/mainface/css/style-responsive.css">
	<link type="text/css" rel="stylesheet" href="${base}/mainface/css/select2_metro.css">
	
	<link id="style_color" type="text/css" rel="stylesheet" href="${base}/mainface/css/default.css">
	<link type="text/css" rel="stylesheet" href="${base}/mainface/css/uniform.default.css">
	
	<script type="text/javascript" src="${base}/mainface/js/jquery-1.10.1.min.js"></script>
	
	<link type="text/css" rel="stylesheet" href="${base}/scripts/pfw/pfw.page.metro.css"/>
	<script type="text/javascript" src="${base}/scripts/pfw/pfw.page.metro.js"></script>
	
	<link type="text/css" rel="stylesheet" href="${base}/scripts/jquery/jquery-validate/jquery.validate.css"/>
	<script type="text/javascript" src="${base}/scripts/jquery/jquery-validate/jquery.validate.pack.js"></script>
  	<script type="text/javascript" src="${base}/scripts/jquery/jquery-validate/localization/messages_cn.js"></script>
  	
	<link rel="stylesheet" type="text/css" href="${base}/mainface/icon/icon_skin/icon_skin.css">
	<link rel="stylesheet" type="text/css" href="${base}/mainface/icon/icon_skin/icon.css">
	
	<link rel="stylesheet" type="text/css" href="${base}/scripts/jquery/easyui/themes/default/easyui.css">
  	<link rel="stylesheet" type="text/css" href="${base}/scripts/jquery/easyui/themes/icon.css"> 
  	
	
	<script type="text/javascript" src="${base}/mainface/js/jquery-migrate-1.2.1.min.js"></script>
	<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
	<script type="text/javascript" src="${base}/mainface/js/jquery-ui-1.10.1.custom.min.js"></script>      
	<script type="text/javascript" src="${base}/mainface/js/bootstrap.min.js"></script>
	<script type="text/javascript" async="" src="${base}/mainface/js/dc.js">

	<script type="text/javascript" src="${base}/mainface/js/jquery.slimscroll.min.js"></script>
	<script type="text/javascript" src="${base}/mainface/js/jquery.blockui.min.js"></script>  
	<script type="text/javascript" src="${base}/mainface/js/jquery.cookie.min.js"></script>
	<script type="text/javascript" src="${base}/mainface/js/jquery.uniform.min.js"></script>
	<script type="text/javascript" src="${base}/mainface/js/select2.min.js"></script> 
	
	<script type="text/javascript" src="${base}/scripts/jquery/easyui/jquery.easyui.min.js"></script>
	
	<script type="text/javascript" src="${base}/scripts/js/select.js"></script>
	<script type="text/javascript" src="${base}/scripts/My97DatePicker/WdatePicker.js"></script>
	
	<link rel="shortcut icon" href="${base}/mainface/image/favicon.ico" />
	
	<!-- END GLOBAL MANDATORY STYLES -->

	<!--Form CSS-->

	
	<!--End Form CSS-->
	
	<!--解决jquery-1.10.1.min.js不支持$.browser问题-->
  	<script>
	(function(jQuery){ 
	
	if(jQuery.browser) return; 
	
	jQuery.browser = {}; 
	jQuery.browser.mozilla = false; 
	jQuery.browser.webkit = false; 
	jQuery.browser.opera = false; 
	jQuery.browser.msie = false; 
	
	var nAgt = navigator.userAgent; 
	jQuery.browser.name = navigator.appName; 
	jQuery.browser.fullVersion = ''+parseFloat(navigator.appVersion); 
	jQuery.browser.majorVersion = parseInt(navigator.appVersion,10); 
	var nameOffset,verOffset,ix; 
	
	// In Opera, the true version is after "Opera" or after "Version" 
	if ((verOffset=nAgt.indexOf("Opera"))!=-1) { 
	jQuery.browser.opera = true; 
	jQuery.browser.name = "Opera"; 
	jQuery.browser.fullVersion = nAgt.substring(verOffset+6); 
	if ((verOffset=nAgt.indexOf("Version"))!=-1) 
	jQuery.browser.fullVersion = nAgt.substring(verOffset+8); 
	} 
	// In MSIE, the true version is after "MSIE" in userAgent 
	else if ((verOffset=nAgt.indexOf("MSIE"))!=-1) { 
	jQuery.browser.msie = true; 
	jQuery.browser.name = "Microsoft Internet Explorer"; 
	jQuery.browser.fullVersion = nAgt.substring(verOffset+5); 
	} 
	// In Chrome, the true version is after "Chrome" 
	else if ((verOffset=nAgt.indexOf("Chrome"))!=-1) { 
	jQuery.browser.webkit = true; 
	jQuery.browser.name = "Chrome"; 
	jQuery.browser.fullVersion = nAgt.substring(verOffset+7); 
	} 
	// In Safari, the true version is after "Safari" or after "Version" 
	else if ((verOffset=nAgt.indexOf("Safari"))!=-1) { 
	jQuery.browser.webkit = true; 
	jQuery.browser.name = "Safari"; 
	jQuery.browser.fullVersion = nAgt.substring(verOffset+7); 
	if ((verOffset=nAgt.indexOf("Version"))!=-1) 
	jQuery.browser.fullVersion = nAgt.substring(verOffset+8); 
	} 
	// In Firefox, the true version is after "Firefox" 
	else if ((verOffset=nAgt.indexOf("Firefox"))!=-1) { 
	jQuery.browser.mozilla = true; 
	jQuery.browser.name = "Firefox"; 
	jQuery.browser.fullVersion = nAgt.substring(verOffset+8); 
	} 
	// In most other browsers, "name/version" is at the end of userAgent 
	else if ( (nameOffset=nAgt.lastIndexOf(' ')+1) < 
	(verOffset=nAgt.lastIndexOf('/')) ) 
	{ 
	jQuery.browser.name = nAgt.substring(nameOffset,verOffset); 
	jQuery.browser.fullVersion = nAgt.substring(verOffset+1); 
	if (jQuery.browser.name.toLowerCase()==jQuery.browser.name.toUpperCase()) { 
	jQuery.browser.name = navigator.appName; 
	} 
	} 
	// trim the fullVersion string at semicolon/space if present 
	if ((ix=jQuery.browser.fullVersion.indexOf(";"))!=-1) 
	jQuery.browser.fullVersion=jQuery.browser.fullVersion.substring(0,ix); 
	if ((ix=jQuery.browser.fullVersion.indexOf(" "))!=-1) 
	jQuery.browser.fullVersion=jQuery.browser.fullVersion.substring(0,ix); 
	
	jQuery.browser.majorVersion = parseInt(''+jQuery.browser.fullVersion,10); 
	if (isNaN(jQuery.browser.majorVersion)) { 
	jQuery.browser.fullVersion = ''+parseFloat(navigator.appVersion); 
	jQuery.browser.majorVersion = parseInt(navigator.appVersion,10); 
	} 
	jQuery.browser.version = jQuery.browser.majorVersion; 
	})(jQuery); 
  </script>
  <!--解决jquery-1.10.1.min.js不支持$.browser问题-->
	
	<!--让jquery validate的错误信息在输入域同处一行-->
	<style>
		label{
			display:inline;
		}
	</style>	

</head>

<!-- END HEAD -->

<!-- BEGIN BODY -->

<body class="page-header-fixed page-full-width">

	<!-- BEGIN HEADER -->

	<div class="header navbar navbar-inverse navbar-fixed-top">

		<!-- BEGIN TOP NAVIGATION BAR -->

		<div class="navbar-inner">

			<div class="container-fluid">

				<!-- BEGIN LOGO -->

				<a href="${base}" class="brand">

				&nbsp;&nbsp;&nbsp;<font color="#fff">题库后台系统</font>

				</a>

				<!-- END LOGO -->

			</div>

		</div>

		<!-- END TOP NAVIGATION BAR -->

	</div>

	<!-- END HEADER -->

	<!-- BEGIN CONTAINER -->   

	<div class="page-container row-fluid">

		<!-- BEGIN PAGE -->
		<div class="page-content">

			<!-- BEGIN PAGE CONTAINER-->
			<div class="container-fluid">
			
				<!--页面位置-->
				<div class="row-fluid">
					<div class="span12">
						<ul class="breadcrumb">
						<li><i class='icon-home'></i><i class='icon-angle-right'></i></li>
						<script>
							var urlstr = "${request.getRequestURI()?replace(base,'')}";

/*注释，原因：原来是考虑到input界面的postion问题，现所有的input界面改为不要postion
							if(urlstr.indexOf('!') != -1)
							{
								var tmpstr = urlstr.substring(urlstr.indexOf('!'),urlstr.indexOf('.'));
								urlstr = urlstr.replace(tmpstr,'');
								
							}
*/							
							$.ajax({ 
								type : "post", 
								url:"${base}/security/public_service!getUrlJosn.action",
								data:{pageurl:urlstr},
								success:function(data){
									if(typeof(returnVal) == "undefined")
									{
										var obj = eval("("+data+")");
										if (obj.length == 0) {
										} else {
											var str = "";
											for (var i =obj.length-1 ; i >=0; i--) {
												if(i==0){
												    str += "<a href='javascript:void(0)'>"+obj[i].name+"</a>";
												}else{
													str += "<a href='javascript:void(0)'>"+obj[i].name+"</a>&nbsp;>&nbsp;";	
												}
												$('title').html(obj[i].name);
											}
											$(".breadcrumb").append(str);
										}
									}	
							 	}
							});	
						</script>						
						</ul>
					</div>
				</div>
				<!--页面位置-->
			
			
			<#nested>
			
			
			</div>

			<!-- END PAGE CONTAINER--> 
			
		</div>

		<!-- END PAGE -->    

	</div>

	<!-- END CONTAINER -->

	<!-- BEGIN FOOTER -->


	<!-- END FOOTER -->

	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->

	<!-- BEGIN CORE PLUGINS -->

	
	<!--[if lt IE 9]>

	<script src="${base}/mainface/js/excanvas.min.js"></script>

	<script src="${base}/mainface/js/respond.min.js"></script>  

	<![endif]-->   

	

	<!-- END CORE PLUGINS  <script type="text/javascript" src="${base}/mainface/js/app.js"></script> -->

	
	<!--Form JS-->


	<!--Form JS-->


	<script>

		jQuery(document).ready(function() {    
			
			//App.init();
			
		   //checkbox init
		   	var test = $("input[type=checkbox]:not(.toggle), input[type=radio]:not(.toggle, .star)");
			if (test.size() > 0) {
			    test.each(function () {
				    if ($(this).parents(".checker").size() == 0) {
				        $(this).show();
				        $(this).uniform();
				    }
				});
			}
			//checkbox init
			
			//form表单提交弹出加载框
			$("form").bind("submit", function() {
					var subFlg = true;
					$(this).find("label[class='error']").each(function(){
						if($(this).css("display") != "none")
						{
							subFlg = false;
						}
					});
					if(!subFlg)
					{
						return false;
					}
					$("#modalsubmit").modal({backdrop: 'static', keyboard: false});		
			});
			//form表单提交弹出加载框

		});

	</script>

	<!-- END JAVASCRIPTS -->
	<div style="display: none;" data-focus-on="input:first" tabindex="-1" class="modal hide fade in" id="modalsubmit" style="width:340;height:100;display: block; margin-top: -155px;" aria-hidden="false">
		<div class="modal-body">
			<br><br>
			<center><img src='${base}/extend/image/progress.gif' />  系统正在处理中，请稍等......</center>
			<br><br>
		</div>
	</div>
	
	<div style="display: none;" data-focus-on="input:first" tabindex="-1" class="modal hide fade in" id="pfwmodal" style="display: block; margin-top: -155px;" aria-hidden="false">
		<div class="modal-body">
			 &nbsp;
		</div>
		<div class="modal-footer">
			<a href="#" class="btn blue" data-dismiss="modal">确定</a>
	        <a href="#" class="btn" data-dismiss="modal">取消</a>
	    </div>
	</div>


</body>
<!-- END BODY -->
</html>
		<!-- 显示Action中传过来的提示信息 -->
		<#if Request['promptInfo']?exists>
			<script>
				alert('${Request['promptInfo']?if_exists}');
			</script>
		</#if>
</#macro>