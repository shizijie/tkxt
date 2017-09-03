<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] /> 

<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
	<meta charset="utf-8">
	<title>题库系统</title>
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


				<!-- BEGIN HORIZANTAL MENU -->

				<div class="navbar hor-menu hidden-phone hidden-tablet">
					<div class="navbar-inner">
						<ul class="nav">
							<#assign crutUrl = request.requestURL?if_exists>
							<#if crutUrl?index_of("home!index2.action") != -1 || crutUrl?index_of("home!index.action") != -1 >
								<li id="home" class="active">
									<a href="${base}/home!index2.action" >我的工作台</a>
									<span class="selected"></span>
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
			
				<!--导航栏子div
				<div class="row-fluid">
					<div class="span2 sidebar-content ">
					</div>
					<div class="span7 ">
					</div>	
					<div class="span3 sidebar-content ">
					</div>			
				</div>
				导航栏子div结束-->
				
				
				<!--提醒行-->
				<div class="row-fluid">
					<div class="span1" ></div>
					
					<div class="span10">
					<div class="row-fluid">
					
					
						<div class="span3" >
						</div>
						<div class="span3">
						</div>
						<div class="span3">
						</div>
						<div class="span3">
						</div>
					
					
					</div>
					</div>
					
					<div class="span1" ></div>
				</div>
				<!--提醒行-->
				
				<!--通知公告-->
				<div class="row-fluid">
					<div class="span1" ></div>
					
					<div class="span10">
						<div class="row-fluid">
						
						
<div class="span6">						
	<div class="portlet box green tabbable">
		<div class="portlet-title">
			<div class="caption"><i class="icon-reorder"></i>内部信息</div>
		</div>
		<div class="portlet-body">
			<div class="tabbable portlet-tabs">
				<ul class="nav nav-tabs">
					<li class=""><a data-toggle="tab" href="#portlet_tab2_3">Tab 3</a></li>
					<li class=""><a data-toggle="tab" href="#portlet_tab2_2">Tab 2</a></li>
					<li class="active"><a data-toggle="tab" href="#portlet_tab2_1">Tab 1</a></li>
				</ul>
				<div class="tab-content">
					<div id="portlet_tab2_1" class="tab-pane">
						<div class="alert">
							Check out the below dropdown menu. It will be opened as usual since there is enough space at the bottom.
						</div>
						<div class="btn-group">
							<a data-toggle="dropdown" href="#" class="btn red">
							<i class="icon-user"></i> Settings
							<i class="icon-angle-down"></i>
							</a>
							<ul class="dropdown-menu">
								<li><a href="#"><i class="icon-plus"></i> Add</a></li>
								<li><a href="#"><i class="icon-trash"></i> Edit</a></li>
								<li><a href="#"><i class="icon-remove"></i> Delete</a></li>
								<li class="divider"></li>
								<li><a href="#"><i class="i"></i> Full Settings</a></li>
							</ul>
						</div>
						<p>
							Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait.
						</p>
						<p>
							Deros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna.
						</p>
					</div>
					<div id="portlet_tab2_2" class="tab-pane active">
						<p>
							Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo.  
						</p>
						<p>
							<a target="_blank" href="ui_tabs_accordions.html#portlet_tab2_2" class="btn red">Activate this tab via URL</a> 
						</p>
					</div>
					<div id="portlet_tab2_3" class="tab-pane">
						<p>
							Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi.   
						</p>
						<p>
							<a target="_blank" href="ui_tabs_accordions.html#portlet_tab2_3" class="btn purple">Activate this tab via URL</a> 
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>						
</div>	

<div class="span6">						
	<div class="portlet box blue tabbable">
		<div class="portlet-title">
			<div class="caption"><i class="icon-reorder"></i>内部信息</div>
		</div>
		<div class="portlet-body">
			<div class="tabbable portlet-tabs">
				<ul class="nav nav-tabs">
					<li class=""><a data-toggle="tab" href="#portlet_tab2_3">Tab 3</a></li>
					<li class=""><a data-toggle="tab" href="#portlet_tab2_2">Tab 2</a></li>
					<li class="active"><a data-toggle="tab" href="#portlet_tab2_1">Tab 1</a></li>
				</ul>
				<div class="tab-content">
					<div id="portlet_tab2_1" class="tab-pane">
						<div class="alert">
							Check out the below dropdown menu. It will be opened as usual since there is enough space at the bottom.
						</div>
						<div class="btn-group">
							<a data-toggle="dropdown" href="#" class="btn red">
							<i class="icon-user"></i> Settings
							<i class="icon-angle-down"></i>
							</a>
							<ul class="dropdown-menu">
								<li><a href="#"><i class="icon-plus"></i> Add</a></li>
								<li><a href="#"><i class="icon-trash"></i> Edit</a></li>
								<li><a href="#"><i class="icon-remove"></i> Delete</a></li>
								<li class="divider"></li>
								<li><a href="#"><i class="i"></i> Full Settings</a></li>
							</ul>
						</div>
						<p>
							Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait.
						</p>
						<p>
							Deros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna.
						</p>
					</div>
					<div id="portlet_tab2_2" class="tab-pane active">
						<p>
							Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo.  
						</p>
						<p>
							<a target="_blank" href="ui_tabs_accordions.html#portlet_tab2_2" class="btn red">Activate this tab via URL</a> 
						</p>
					</div>
					<div id="portlet_tab2_3" class="tab-pane">
						<p>
							Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi.   
						</p>
						<p>
							<a target="_blank" href="ui_tabs_accordions.html#portlet_tab2_3" class="btn purple">Activate this tab via URL</a> 
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>						
</div>			
						
					</div>
					</div>
					<div class="span1" ></div>
				</div>
				<!--通知公告-->					
				


			</div>
			<!-- END PAGE CONTAINER--> 

		</div>

		<!-- END PAGE -->    

	</div>

	<!-- END CONTAINER -->

	<!-- BEGIN FOOTER -->

	<div class="footer">

		<div class="footer-inner">

			<!--2015 &copy; TQBase.-->

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


	<!-- END JAVASCRIPTS -->

</body>
	<!-- END BODY -->
</html>


