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
						<!--天气预报开始-->
						<div class="row-fluid">
							<div class="span12">
								<div class="row-fluid">
									<div class="span1"></div>
									<div class="span10" style="padding: 10px 0 0 0">
										<!--
										<IFRAME height=35 marginHeight=0 src="http://i.tianqi.com/index.php?c=code&amp;id=1&amp;icon=1&amp;wind=1&amp;num=2&amp;py=shanghai" frameBorder=0 width=430 allowTransparency name=weather_inc marginWidth=0 scrolling=no></IFRAME>
										-->
									</div>
									<div class="span1"></div>
								</div>
							</div>
						</div>
						<!--天气预报结束-->
						
						<!--No.1开始-->
						<div class="row-fluid">
							<div class="span12">
								<div class="row-fluid">
									<div class="span1"></div>
									<div class="span10">
										<div class="row-fluid">
											
											<!--用户登录-->
											<div class="span3"  style="height:310px">
												<div class="portlet box blue">
													<div class="portlet-title line">
														<div class="caption">
															<i class="icon-search"></i> 用户登录
														</div>
													</div>
													<div class="portlet-body" style="height:160px">
														<#if user?exists && user.name?exists>
															<br>
															<h4>Hi,${user?if_exists.name?if_exists},欢迎回家</h4>
															<br>
															<p>
																<#if sfxtgly == true>
																	<a class="btn blue" href="${base}/home!index2.action"><i class="icon-plus"></i> 工作台</a>
																<#elseif sfxxy == true>
																	<a class="btn blue" href="${base}/home!xxglyindex.action"><i class="icon-plus"></i> 工作台</a>
																</#if>
																&nbsp;&nbsp;&nbsp;
																<a class="btn purple" href="${base}/j_spring_security_logout"><i class="icon-plus"></i> 退出系统</a>
															</p>
														<#else>
														<form class="form-vertical login-form" action="${base}/j_spring_security_check" method="post" name="loginform">
															<input type="hidden" name="j_captcha" id="verify" value="PthInk4!@(">
															<div class="control-group">
																<div class="controls">
																	<div class="input-icon left">
																		<i class="icon-user"></i>
																		<input class="m-wrap span12 placeholder-no-fix" type="text" placeholder="用户名" name="j_username"  id="j_username" 																			<#if Session?exists&&Session['SPRING_SECURITY_LAST_EXCEPTION']?exists>
																				value='${Session['SPRING_SECURITY_LAST_USERNAME']}'
																			</#if>
																		/>
																	</div>
																</div>
															</div>
															<div class="control-group">
																<div class="controls">
																	<div class="input-icon left">
																		<i class="icon-lock"></i>
																		<input class="m-wrap span12 placeholder-no-fix" type="password" placeholder="密码" name="j_password" id="j_password"/>
																	</div>
																</div>
															</div>
															<center>
																<button class="btn blue" type="button" onclick="sub()">登录 <i class="m-icon-swapright m-icon-white"></i></button>
																&nbsp;&nbsp;&nbsp;&nbsp;
																<button class="btn" type="button" >重置 </button>
															</center>
														</form>
														</#if>
														<#if Parameters['loginerror']?exists>
															<script>
																alert("用户名或密码错误");
															</script>
														</#if>
													</div>
												</div>
												
												<!--校长信箱-->
												<div class="top-news">
													<a class="btn purple" href="${base}/cms/cms_show!wzly.action" target="_blank">
													<span>校长信箱</span>
													<em>
													<i class="icon-tags"></i>&nbsp;&nbsp;写封信给校长
													</em>
													<i class="icon-envelope top-news-icon"></i>
													</a>
												</div>
												<!--校长信箱-->
												
											</div>
											<!--用户登录结束-->
											<!--图片新闻-->
											<div class="span5" style="height:310px">
												<div class="carousel slide" id="carousel-640752" >
													<ol class="carousel-indicators">
														<li class="active" data-slide-to="0" data-target="#carousel-640752">
														</li>
														<li data-slide-to="1" data-target="#carousel-640752">
														</li>
														<li data-slide-to="2" data-target="#carousel-640752">
														</li>
													</ol>
													<div class="carousel-inner" >
														<div class="item active" >
															<img alt="" src="${base}/page/main/images/image1.jpg" style="height:310px"/>
															<div class="carousel-caption" style="padding:0px">
																<h5 style="color:#fff">
																	棒球运动是一种以棒打球为主要特点，集体性
																</h5>
															</div>
														</div>
														<div class="item"  style="height:310px">
															<img alt="" src="${base}/page/main/images/image2.jpg" style="height:310px"/>
															<div class="carousel-caption" style="padding:0px">
																<h5 style="color:#fff">
																	冲浪是以海浪为动力，利用自身的高超技巧和平衡能力
																</h5>
															</div>
														</div>
														<div class="item"  style="height:310px">
															<img alt="" src="${base}/page/main/images/image5.jpg" style="height:310px"/>
															<div class="carousel-caption" style="padding:0px">
																<h5 style="color:#fff">
																	以自行车为工具比赛骑行速度的体育运动
																</h5>
															</div>
														</div>
													</div> <a data-slide="prev" href="#carousel-640752" class="left carousel-control">‹</a> <a data-slide="next" href="#carousel-640752" class="right carousel-control">›</a>
												</div>
											</div>
											<!--图片新闻结束-->	
											<!--通知公告-->
											<div class="span4"  style="height:310px">
												<div class="portlet box blue mynews" >
													<div class="portlet-title">
														<div class="caption">
															<i class="icon-bullhorn"></i>&nbsp;通知公告
														</div>
														<div class="tools">
															<#if map.get("tzgg")?exists>
																<a href="${base}/cms/cms_show!cmsShowInfo.action?menuId=${map.get("tzgg").id}" target="_blank"><font color="white">更多>></font></a>
															  </#if>
														</div>
													</div>
													<div class="portlet-body" style="height:250px">

														<ul class="unstyled">
														
														<#if  map.get("tzggList")?exists>
														    <#list map.get("tzggList") as obj>
												    			<#if obj_index < 9>
													    			<li>
																		<span class="sale-info">
																			<a target="_blank" href="${base}/cms/cms_show!pageSubInfo.action?contentId=${obj.id}"  title="${obj.articleName}">
																				<#if obj.articleName?exists && obj.articleName?length gt 20>
																					<#if obj.isTop?exists && obj.isTop=="1">
																						<font color="red">[置顶]</font>
																						${obj.articleName?substring(0,18)}....
																					<#else>
																						${obj.articleName?substring(0,20)}....
																					</#if>
																					
																				<#else>
																					<#if obj.isTop?exists && obj.isTop=="1">
																						<font color="red">[置顶]</font>
																					</#if>
																					${obj.articleName}
																				</#if>
																			</a>
																		</span> 
																		<span class="sale-num">${obj.addDate[0..10]!}</span>
																	</li>
																</#if>
															</#list>
														</#if> 
														</ul>
														
														
													</div>
												</div>
											</div>
											<!--通知公告结束-->
										</div>
									</div>
									<div class="span1"></div>
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
