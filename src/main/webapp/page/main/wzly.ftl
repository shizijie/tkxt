	<!DOCTYPE html>
	<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
	<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
	<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
	<!-- BEGIN HEAD -->
	
	<head>
	
		<meta charset="utf-8">
		<title>TQCms</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<meta name="author" content="">
		
		<!-- BEGIN GLOBAL MANDATORY STYLES -->
		<link type="text/css" rel="stylesheet" href="${base}/mainface/css/bootstrap.min.css">
		<link type="text/css" rel="stylesheet" href="${base}/mainface/css/bootstrap-responsive.min.css">
		<link type="text/css" rel="stylesheet" href="${base}/mainface/css/font-awesome.min.css">
		<link type="text/css" rel="stylesheet" href="${base}/mainface/css/style-metro.css">
		<link type="text/css" rel="stylesheet" href="${base}/mainface/css/style.css">
		<link type="text/css" rel="stylesheet" href="${base}/mainface/css/style-responsive.css">
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
		
		<script type="text/javascript" src="${base}/scripts/jquery/easyui/jquery.easyui.min.js"></script>
		
		<script type="text/javascript" src="${base}/scripts/js/select.js"></script>
		
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
	
		<!-- BEGIN BODY -->
		
		<body class="page-header-fixed page-full-width">
		
			<!-- BEGIN CONTAINER -->   
		
			<!--<div class="page-container row-fluid">-->
			<div class="">
		
				<!-- BEGIN PAGE -->
				<div class="page-content">
		
					<!-- BEGIN PAGE CONTAINER-->
					<div class="container-fluid">
					
						<!--Banner开始-->
						<div class="row-fluid">
							<div class="span12">
								<div class="row-fluid">
									<div class="span1"></div>
									<div class="span10">
										<#include "/page/main/include/head.ftl" >
									</div>
									<div class="span1"></div>
								</div>
							</div>
						</div>	
						<!--Banner结束-->
						<!--Menu开始-->
						<div class="row-fluid">
							<div class="span12">
								<#include "/page/main/include/menu.ftl" >
							</div>
						</div>
						<!--Menu结束-->
						
						<!--No.1开始-->
						<div class="row-fluid">
							<div class="span12">
								<div class="row-fluid">
									<div class="span2"></div>
									<div class="span8">
										<br>
										<center><h2>给我们留言</h2></center>
										<br>
										<form id="save_inputForm" class="form-horizontal" action="${base}/cms/cms_wzly!save.action" method="post">
											<div class="control-group">
												<label class="control-label">分类:<span class="required">*</span></label>
												<div class="controls">
													<@s.select cssClass="span10 m-wrap" id="lyfl.id" name="lyfl.id" list="lyflLs" listKey="id" listValue="name" headerKey="" headerValue="==请选择=="/>
												</div>
											</div>
											<div class="control-group">
												<label class="control-label">留言内容:<span class="required">*</span></label>
												<div class="controls">
													<textarea id="lynr" name="lynr" class="span10 m-wrap" rows="5" maxlength="100" ></textarea>
													<p><strong>注意：</strong> 最多只能输入100个字.</p>
												</div>
											</div>
											<div class="control-group">
												<label class="control-label">你的邮箱:<span class="required">*</span></label>
												<div class="controls">
													<input type="text" id="yhyx" name="yhyx" class="span10 m-wrap" maxlength="80" >
												</div>
											</div>
											<div class="control-group">
												<label class="control-label">联系电话:<span class="required">*</span></label>
												<div class="controls">
													<input type="text" id="lxdh" name="lxdh" class="span10 m-wrap" maxlength="80" >
												</div>
											</div>
											<div class="control-group">
												<label class="control-label">验证码:<span class="required">*</span></label>
												<div class="controls">
													<input type='text' name='j_captcha' id="verify" class="span2 m-wrap"/>
						                			<a href="#"><img id="captchaImg" src="${base}/jcaptcha.jpg?jcaptchafilter=1"  width=120 height=36 /></a>
												</div>
											</div>
											
											<div class="row-fluid">
												<div class="span12">
													<div class="form-actions">
														<center>
															<button class="btn blue big" type="submit" id="commit" name="commit" >
																<i class="icon-ok"></i> 提交
															</button>
															&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
															<button class="btn big" type="button" onclick="javascript:window.close()" >
																<i class="icon-remove"></i> 关闭
															</button>
														</center>									
													</div>
												</div>
											</div>
											<script type="text/javascript">	
												$(document).ready(function() {
													$("#save_inputForm").validate({
														rules: {
															<!-- 在这里编写验证规则 -->	
															"lyfl.id":{required:true},
															j_captcha:{required:true},
															yhyx:{required:true},
															lxdh:{required:true},
															lynr:{required:true}					
														}
													});						
												});	
											</script>
										</form>
									</div>
									<div class="span2"></div>
								</div>
							</div>
						</div>
						<!--No.1结束-->
						
						
						
						
						
					
					</div>
		
					<!-- END PAGE CONTAINER--> 
					
				</div>
		
				<!-- END PAGE -->    
		
			</div>
		
			<!-- END CONTAINER -->
		
			<!--[if lt IE 9]>
		
			<script src="${base}/mainface/js/excanvas.min.js"></script>
		
			<script src="${base}/mainface/js/respond.min.js"></script>  
		
			<![endif]-->   
		
			
		
			<!-- END CORE PLUGINS  <script type="text/javascript" src="${base}/mainface/js/app.js"></script> -->
		
			
			<!--Form JS-->
		
		
			<!--Form JS-->
			
			<script>
				$('#carousel-640752').carousel({
			      interval: 3000
			    });
				$('#carousel-640752').carousel();
				
				function sub()
				{
					if($("#j_username").val() == "")
					{
						alert("请输入用户名");
						$("#j_username").focus();
						return;
					}
					if($("#j_password").val() == "")
					{
						alert("请输入密码");
						$("#j_password").focus();
						return;
					}
					loginform.submit();
				}
				
			</script>
		
		</body>
		<!-- END BODY -->
		
	</html>
