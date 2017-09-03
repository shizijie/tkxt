<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] /> 

<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
	<meta charset="utf-8">
	<title>TQBase</title>
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
	<!-- END GLOBAL MANDATORY STYLES -->

	</script><script type="text/javascript" src="${base}/mainface/js/jquery-1.10.1.min.js"></script>
	
	<!--Form CSS-->

	
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

				<img alt="logo" src="${base}/mainface/image/logo.png">

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
							<#if crutUrl?index_of("home!index2.action") != -1>
								<li id="home" class="active">
									<a href="${base}/home!index2.action" >我的工作台</a>
									<span class="selected"></span>
								</li>							
							<#else>
								<li id="home">
									<a href="${base}/home!index2.action" >我的工作台</a>
								</li>							
							</#if>
						
							<#list menus as topmenu>
								<@security.authorize ifAnyGranted="${topmenu.authNames}">
									<li id="${topmenu.id}">
										<#if topmenu.children?size != 0>
											<#assign haveMenu = "no">
											<#list topmenu.children as bjo>
												<#if bjo.resourceType == "menu">
													<#assign haveMenu = "yes">
												</#if>
											</#list>
											<#if haveMenu == "yes">
												<a href="${base}/home!app2.action?parentId=${topmenu.id}" class="dropdown-toggle" data-toggle="dropdown">
													${topmenu.name!}
													<span class="arrow"></span>
												</a>
												<@genTreeMenu tmenu=topmenu />
											<#else>
												<a href="${base}/home!app2.action?parentId=${topmenu.id}">
													${topmenu.name!}
												</a>
											</#if>
										<#else>
											<a href="${base}/home!app2.action?parentId=${topmenu.id}">
												${topmenu.name!}
											</a>
										</#if>
									</li>
								</@security.authorize>  
							</#list>
						</ul>
					</div>
				</div>
				

				<!-- END HORIZANTAL MENU -->


				<!-- BEGIN TOP NAVIGATION MENU -->              

				<ul class="nav pull-right">
					<!--
					<li class="dropdown">
						<a href="${base}/j_spring_security_logout" class="dropdown-toggle" >
						<i class="icon-signout"></i>
							<font color="white">退出系统</font>
						</a>
					</li>
					-->
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
				<div class="row-fluid">
			
					<div class="span2 sidebar-content ">
						<ul class="ver-inline-menu tabbable margin-bottom-25" >
							<li class="active">
								<a href="#tab_1" data-toggle="tab">
								<i class="icon-briefcase"></i> 
								 简介
								</a> 
								<span class="after"></span>                                    
							</li>
							<li><a href="#tab_2" data-toggle="tab"><i class="icon-group"></i> 业务功能地图</a></li>
							<li><a href="#tab_3" data-toggle="tab"><i class="icon-info-sign"></i> 常见问题</a></li>
							<li><a href="#tab_4" data-toggle="tab"><i class="icon-leaf"></i> 帮助手册</a></li>
							<!--
							<li><a href="#tab_1" data-toggle="tab"><i class="icon-info-sign"></i> License Terms</a></li>
							<li><a href="#tab_2" data-toggle="tab"><i class="icon-tint"></i> Payment Rules</a></li>
							<li><a href="#tab_3" data-toggle="tab"><i class="icon-plus"></i> Other Questions</a></li>
							<li><a href="#tab_3" data-toggle="tab"><i class="icon-tasks"></i> Taks &amp; Resources</a></li>
							<li><a href="#tab_3" data-toggle="tab"><i class="icon-user"></i> User Management</a></li>
							-->
						</ul>
					</div>
					
					<div class="span7 ">
						<div class="row-fluid">
							<div class="span12">
								<h3 class="page-title">
									${curResource?if_exists.name?if_exists} <small>&nbsp;</small>
								</h3>
								<ul class="breadcrumb">
									<#assign postStr = "">
									<#assign flg = 0>
									  <#macro genPostion menue>
									  	<#if (menue?exists)>
									  		<#if (menue.parent?exists)>
									  			<#if flg == 0>
									  				<#assign postStr = "<li><a href='javascript:void(0)'> "+menue.name+"</a>" + postStr  >
									  			<#else>
									  				<#assign postStr = "<li><a href='javascript:void(0)'> "+menue.name+"</a><i class='icon-angle-right'></i>" + postStr  >
									  			</#if>
									  			<#assign flg = flg + 1>
									  			<@genPostion menue=menue.parent/>
									  		<#else>
									  			<#if flg == 0>
									  				<#assign postStr = "<li><i class='icon-home'></i><a href='javascript:void(0)'> "+menue.name+"</a>" + postStr  >
									  			<#else>
									  				<#assign postStr = "<li><i class='icon-home'></i><a href='javascript:void(0)'> "+menue.name+"</a><i class='icon-angle-right'></i>" + postStr  >
									  			</#if>
									  			
									  		</#if>
									  	</#if>
									  </#macro>
									  <@genPostion menue=curResource/>
									  ${postStr!}
									  <!--
									<li><a href="javascript:void(0)">Layouts</a><i class="icon-angle-right"></i>
									</li>
									<li>
										<a href="javascript:void(0)">Horzontal Menu 2</a>
									</li>
									-->
								</ul>
							</div>
						</div>
						<div class="row-fluid margin-bottom-20">
							<div class="span12">
								<div class="tab-content">
									${curResource?if_exists.mkjj?if_exists}
								</div>
							</div>
						</div>
					</div>	
					
					
					<div class="span3 sidebar-content ">
						
						<#assign portColor=['green','grey','red','yellow','purple','blue']>
						<#assign iconSkin=['skin-a','skin-c','skin-d','skin-e','skin-f','skin-g','skin-b','skin-h','skin-i','skin-j','skin-k','skin-l']>
						
						<div class="portlet box green">
							<div class="portlet-title line">
								<div class="caption"><i class="icon-cogs"></i><b>${curResource.name}</b></div>
								<div class="tools">
									<a class="expand" href="javascript:;"></a>
								</div>
							</div>
							<div class="portlet-body" style="display: none;">
								<div class="row-fluid">
									<div class="span12">
										<#if curResource?exists && curResource.children?size != 0>
											<#assign cCount2 = 0>
											<#list curResource.children as obj1>
												<#if obj1.resourceType == "url">
													<@security.authorize ifAnyGranted="${obj1.authNames}">
													<a href="${base+obj1.value!}" target="_blank">
														<div class="skin-father">
															<div class="${iconSkin[cCount2%iconSkin?size]}">
																<#if obj1.urlicon?exists && obj1.urlicon != "">
																	<i class="${obj1.urlicon!}"></i>
																<#else>
																	<i class="iconmy-computer04"></i>
																</#if>
																${obj1.name!}
															</div>
														</div>
													</a>
													</@security.authorize>
												</#if>
												<#assign cCount2 = cCount2 + 1>
											</#list>
										</#if>
									</div>
								</div>
							</div>
						</div>
							
					</div>		
					
						
				</div>
			</div>

			<!-- END PAGE CONTAINER--> 

		</div>

		<!-- END PAGE -->    

	</div>

	<!-- END CONTAINER -->

	<!-- BEGIN FOOTER -->

	<div class="footer">

		<div class="footer-inner">

			2013 &copy; Metronic by keenthemes.

		</div>

		<div class="footer-tools">

			<span class="go-top">

			<i class="icon-angle-up"></i>

			</span>

		</div>

	</div>

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


	<script>

		jQuery(document).ready(function() {    

			$('body').on('click', '.portlet .tools .collapse, .portlet .tools .expand', function (e) {
		        e.preventDefault();
		        
		        $(".portlet .tools .collapse, .portlet .tools .expand").each(function(){
		        	if ($(this).hasClass("collapse"))
		        	{
		        		$(this).removeClass("collapse").addClass("expand");
		        		var eltmp = $(this).closest(".portlet").children(".portlet-body");
		        		eltmp.slideUp(200);
		        	}
		        });
		        
		        var el = $(this).closest(".portlet").children(".portlet-body");
		        if ($(this).hasClass("collapse")) {
		            $(this).removeClass("collapse").addClass("expand");
		            el.slideUp(200);
		        } else {
		            $(this).removeClass("expand").addClass("collapse");
		            el.slideDown(200);
		        }
		    });
		    
		    $(".portlet .tools .collapse, .portlet .tools .expand").eq(0).trigger("click");

		});


		<#assign cResourceTopmenuId = "">
		
		<#macro genTopMenuId tmenu>
			<#if tmenu.parent?exists>
				<@genTopMenuId tmenu=tmenu.parent />
			<#else>
				<#assign cResourceTopmenuId = tmenu.id>
			</#if>
		</#macro>
		<@genTopMenuId tmenu=curResource />
		
		$('#${cResourceTopmenuId}').addClass("active");
		
	</script>

	<!-- END JAVASCRIPTS -->

</body>
	<!-- END BODY -->
</html>


