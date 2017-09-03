<#macro crudmetropage2 ext...>
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

				<a href="index.html" class="brand">

				<img alt="logo" src="${base}/mainface/image/logo.png">

				</a>

				<!-- END LOGO -->

				<!-- BEGIN HORIZANTAL MENU -->

				<div class="navbar hor-menu hidden-phone hidden-tablet">

					<div class="navbar-inner">

						<ul class="nav">

							<li>

								<a href="index.html">

								菜单1

								</a>

							</li>

							<li class="active">

								<a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">

								<span class="selected"></span>

								菜单2

								<span class="arrow"></span>     

								</a>

								<ul class="dropdown-menu">

									<li class="active">

										<a href="layout_horizontal_menu2.html">

										子菜单1                    </a>

									</li>

									<li class="dropdown-submenu">

										<a href="javascript:;" tabindex="-1">

										子菜单2

										<span class="arrow"></span>

										</a>

										<ul class="dropdown-menu">

											<li><a href="#" tabindex="-1">子菜单1</a></li>

											<li class="dropdown-submenu">

												<a href="javascript:;" tabindex="-1">子菜单2<span class="arrow"></span></a>

												<ul class="dropdown-menu">

													<li><a href="index.html" tabindex="-1">子菜单1</a></li>

													<li><a href="index.html" tabindex="-1">Third level link</a></li>

												</ul>

											</li>

										</ul>

									</li>

								</ul>

								<b class="caret-out"></b>                        

							</li>

						</ul>

					</div>

				</div>

				<!-- END HORIZANTAL MENU -->


				<!-- BEGIN TOP NAVIGATION MENU -->              

				<ul class="nav pull-right">

					<!-- BEGIN USER LOGIN DROPDOWN -->

					<li class="dropdown user">

						<a data-toggle="dropdown" class="dropdown-toggle" href="#">

						<img src="${base}/mainface/image/avatar1_small.jpg" alt="">

						<span class="username">杨学华</span>

						<i class="icon-angle-down"></i>

						</a>

						<ul class="dropdown-menu">

							<li><a href="extra_profile.html"><i class="icon-user"></i> 个人资料</a></li>

							<li class="divider"></li>

							<li><a href="login.html"><i class="icon-key"></i> 注销</a></li>

						</ul>

					</li>

					<!-- END USER LOGIN DROPDOWN -->

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
			
			<#nested>

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

		   //App.init();
		   
		   //FormComponents.init();

		});

	</script>

	<!-- END JAVASCRIPTS -->



</body><!-- END BODY --></html>
</#macro>