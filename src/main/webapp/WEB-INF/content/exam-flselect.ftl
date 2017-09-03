<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] /> 

<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
	<meta charset="utf-8">
	<title>题库后台系统</title>
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
	<link rel="stylesheet" type="text/css" href="${base}/mainface/css/profile.css">
	<!-- END GLOBAL MANDATORY STYLES -->

	</script><script type="text/javascript" src="${base}/mainface/js/jquery-1.10.1.min.js"></script>
	
	<!--Form CSS-->

	<script type="text/javascript"  src="${base}/mainface/js/highcharts.js"></script>
	<script type="text/javascript"  src="${base}/mainface/js/highcharts-3d.js"></script>
	
	<!--End Form CSS-->
	
	<link rel="stylesheet" type="text/css" href="${base}/mainface/icon/icon_skin/icon_skin.css">
	<link rel="stylesheet" type="text/css" href="${base}/mainface/icon/icon_skin/icon.css">
	
	
	<link rel="shortcut icon" href="${base}/mainface/image/favicon.ico" />

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

				<#macro genTreeMenu tmenu>
					<#if tmenu.children?exists && tmenu.children?size gt 0>
						<ul class="dropdown-menu">
							<#list tmenu.children as obj>
								<#if obj.resourceType == "menu">
									<@security.authorize ifAnyGranted="${obj.authNames}">
										
										<#--直接安层次显示菜单树，不考虑urlmenu情况
										<#assign isurlmenu = "no">
										<#list urlmenus as tmpurlmenu>
											<#if tmpurlmenu.id == obj.id>
												<#assign isurlmenu = "yes">
											</#if>
										</#list>
										-->
										<#assign haveMenu = "no">
										<#assign haveUrl = "no">
										<#list obj.children as tmpobj>
											<#if tmpobj.resourceType == "menu">
												<#assign haveMenu = "yes">
											</#if>
											<#if tmpobj.resourceType == "url">
												<#assign haveUrl = "yes">
											</#if>
										</#list>
										
										<#if haveMenu == "yes">
											<li class="dropdown-submenu">
												<#--如果有url，则允许点击-->
												<#if haveUrl == "yes">
													<#--如果有定义页面的取自定义页面，没有调统一的home!app2.action-->
													<#if obj.value?exists && obj.value?index_of(".action") == -1>
														<a href="${base}/home!app2.action?parentId=${obj.id}" tabindex="-1">
															${obj.name!}
															<span class="arrow"></span>
														</a>
													<#else>
														<a href="${base}/${obj.value!}" tabindex="-1">
															${obj.name!}
															<span class="arrow"></span>
														</a>
													</#if>
												<#else>
													<a href="javascript:;" tabindex="-1">
														${obj.name!}
														<span class="arrow"></span>
													</a>	
												</#if>
												
												<@genTreeMenu tmenu=obj />
											</li>
										<#else>
											<li>
												<#--如果有url，则允许点击-->
												<#if haveUrl == "yes">
													<#--如果有定义页面的取自定义页面，没有调统一的home!app2.action-->
													<#if obj.value?exists && obj.value?index_of(".action") == -1>
														<a href="${base}/home!app2.action?parentId=${obj.id}">${obj.name!}</a>
													<#else>
														<a href="${base}/${obj.value!}">${obj.name!}</a>
													</#if>
												<#else>
													<a href="javascript:;">${obj.name!}</a>
												</#if>
												
											</li>
										</#if>
									</@security.authorize>
								</#if>
							</#list>
						</ul>
					</#if>
				</#macro>

				<!-- BEGIN HORIZANTAL MENU -->

				<div class="navbar hor-menu hidden-phone hidden-tablet">
					<div class="navbar-inner">
						<ul class="nav">
							<#assign crutUrl = request.requestURL?if_exists>
							<#if crutUrl?index_of("home!index2.action") != -1 || crutUrl?index_of("home!index.action") != -1 >
								<li id="home" class="active">
									<a href="${base}/home!index2.action" >我的工作台</a>
								</li>							
							<#else>
								<li id="home">
									<a href="${base}/home!index2.action" >我的工作台</a>
								</li>							
							</#if>
						
						</ul>
					</div>
				</div>
				
				

				<!-- END HORIZANTAL MENU -->


				<!-- BEGIN TOP NAVIGATION MENU -->              

				<ul class="nav pull-right">
					
					<!-- 用户登录信息 -->
					<li class="dropdown user">
						<a data-toggle="dropdown" class="dropdown-toggle" href="#">
						<#if userPhoto?exists>
			        		<img src="${base}/mainface/image/userphoto/${userPhoto}" alt="" style="width:29px;height:29px">
			        	<#else>
			        		<img src="${base}/mainface/image/userphoto/avatar2_small.jpg" alt="">
			        	</#if>
						<span class="username">你好，${usr?if_exists.name?if_exists}</span>
						<i class="icon-angle-down"></i>
						</a>
						<ul class="dropdown-menu">
							<li><a href="${base}/security/user!userprofile.action" target="_blank"><i class="icon-user"></i> 个人资料</a></li>
							<li class="divider"></li>
							<li><a href="${base}/j_spring_security_logout"><i class="icon-key"></i> 注销</a></li>
						</ul>
					</li>
					<!-- 用户登录信息 -->
					

				</ul>

				<!-- END TOP NAVIGATION MENU --> 

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
			
				<!--导航栏子div
				<div class="row-fluid">
					<div class="span2 sidebar-content ">
					</div>
					<div class="span7 ">
					</div>	
					<div class="span3 sidebar-content ">
					</div>			
				</div>
				导航栏子div-->
				<div class="row-fluid">
					<div class="span12">
					</div>
				</div>
				
				
				<!--提醒行-->
				<div class="row-fluid">
					<div class="span2" ></div>
					<div class="span8">
						<div class="row-fluid">
							<h3 class="form-section"><b>练习章节</b></h3>
							<table  width="100%" border="0">
								<#list allfl as obj>
									<tr title="${obj.id}">
									  	<td style="font-size:20px">
									  		${obj.flmc!}
									  	</td>
									  	<td>
									  		<center>
									  			<button class="btn purple" onclick="window.open('${base}/wjgl/twjwt!txwj3.action?kcid=${obj.id!}')">进行练习 <i class="m-icon-swapright m-icon-white"></i></button>
									  		</center>
									  	</td>
									</tr>
								</#list>
							</table>
						</div>
					</div>
					<div class="span2" ></div>
				</div>
				<!--提醒行-->
				
				
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

	
	<script type="text/javascript" src="${base}/mainface/js/jquery-migrate-1.2.1.min.js"></script>
	<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
	<script type="text/javascript" src="${base}/mainface/js/jquery-ui-1.10.1.custom.min.js"></script>      
	<script type="text/javascript" src="${base}/mainface/js/bootstrap.min.js"></script>
	<script type="text/javascript" async="" src="${base}/mainface/js/dc.js">

	<!--[if lt IE 9]>

	<script src="${base}/mainface/js/excanvas.min.js"></script>

	<script src="${base}/mainface/js/respond.min.js"></script>  

	<![endif]-->   

	<script type="text/javascript" src="${base}/mainface/js/jquery.slimscroll.min.js"></script>
	<script type="text/javascript" src="${base}/mainface/js/jquery.blockui.min.js"></script>  
	<script type="text/javascript" src="${base}/mainface/js/jquery.cookie.min.js"></script>
	<script type="text/javascript" src="${base}/mainface/js/jquery.uniform.min.js"></script>


	<!-- END CORE PLUGINS  <script type="text/javascript" src="${base}/mainface/js/app.js"></script> -->

	<!--Form JS-->


	<!--Form JS-->


	<!-- END JAVASCRIPTS -->

</body>
	<!-- END BODY -->
</html>


