<#macro crud ext...>
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

	<link href="${base}/mainface/css/blog.css" rel="stylesheet" type="text/css"/>
	<link href="${base}/mainface/css/search.css" rel="stylesheet" type="text/css"/>
	<!--Form CSS-->

<link rel="stylesheet" type="text/css" href="${base}/mainface/css/bootstrap-fileupload.css" />

	<link rel="stylesheet" type="text/css" href="${base}/mainface/css/jquery.gritter.css" />

	<link rel="stylesheet" type="text/css" href="${base}/mainface/css/chosen.css" />

	<link rel="stylesheet" type="text/css" href="${base}/mainface/css/select2_metro.css" />

	<link rel="stylesheet" type="text/css" href="${base}/mainface/css/jquery.tagsinput.css" />

	<link rel="stylesheet" type="text/css" href="${base}/mainface/css/clockface.css" />

	<link rel="stylesheet" type="text/css" href="${base}/mainface/css/bootstrap-wysihtml5.css" />

	<link rel="stylesheet" type="text/css" href="${base}/mainface/css/datepicker.css" />

	<link rel="stylesheet" type="text/css" href="${base}/mainface/css/timepicker.css" />

	<link rel="stylesheet" type="text/css" href="${base}/mainface/css/colorpicker.css" />

	<link rel="stylesheet" type="text/css" href="${base}/mainface/css/bootstrap-toggle-buttons.css" />

	<link rel="stylesheet" type="text/css" href="${base}/mainface/css/daterangepicker.css" />

	<link rel="stylesheet" type="text/css" href="${base}/mainface/css/datetimepicker.css" />

	<link rel="stylesheet" type="text/css" href="${base}/mainface/css/multi-select-metro.css" />

	<link href="${base}/mainface/css/bootstrap-modal.css" rel="stylesheet" type="text/css"/>	
	
	
	<!--End Form CSS-->
	
	
	
	<link rel="stylesheet" type="text/css" href="${base}/mainface/icon/icon_skin/icon_skin.css">
	<link rel="stylesheet" type="text/css" href="${base}/mainface/icon/icon_skin/icon.css">
	
	<script type="text/javascript" src="${base}/mainface/js/jquery-1.10.1.min.js"></script>
	<script type="text/javascript" src="${base}/scripts/pfw/pfw.page.metro.min.js"></script>
	

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

				<!-- BEGIN RESPONSIVE MENU TOGGLER -->

				<a data-target=".nav-collapse" data-toggle="collapse" class="btn-navbar collapsed" href="javascript:;">

				<img alt="" src="${base}/mainface/image/menu-toggler.png">

				</a>          

				<!-- END RESPONSIVE MENU TOGGLER -->            

				<!-- BEGIN TOP NAVIGATION MENU -->              

				<ul class="nav pull-right">

					<!-- BEGIN NOTIFICATION DROPDOWN -->   

					<li id="header_notification_bar" class="dropdown">

						<a data-toggle="dropdown" class="dropdown-toggle" href="#">

						<i class="icon-warning-sign"></i>

						<span class="badge">6</span>

						</a>

						<ul class="dropdown-menu extended notification">

							<li>

								<p>You have 14 new notifications</p>

							</li>

							<li>

								<a href="#">

								<span class="label label-success"><i class="icon-plus"></i></span>

								New user registered. 

								<span class="time">Just now</span>

								</a>

							</li>

							<li>

								<a href="#">

								<span class="label label-important"><i class="icon-bolt"></i></span>

								Server #12 overloaded. 

								<span class="time">15 mins</span>

								</a>

							</li>

							<li>

								<a href="#">

								<span class="label label-warning"><i class="icon-bell"></i></span>

								Server #2 not respoding.

								<span class="time">22 mins</span>

								</a>

							</li>

							<li>

								<a href="#">

								<span class="label label-info"><i class="icon-bullhorn"></i></span>

								Application error.

								<span class="time">40 mins</span>

								</a>

							</li>

							<li>

								<a href="#">

								<span class="label label-important"><i class="icon-bolt"></i></span>

								Database overloaded 68%. 

								<span class="time">2 hrs</span>

								</a>

							</li>

							<li>

								<a href="#">

								<span class="label label-important"><i class="icon-bolt"></i></span>

								2 user IP blocked.

								<span class="time">5 hrs</span>

								</a>

							</li>

							<li class="external">

								<a href="#">See all notifications <i class="m-icon-swapright"></i></a>

							</li>

						</ul>

					</li>

					<!-- END NOTIFICATION DROPDOWN -->

					<!-- BEGIN INBOX DROPDOWN -->

					<li id="header_inbox_bar" class="dropdown">

						<a data-toggle="dropdown" class="dropdown-toggle" href="#">

						<i class="icon-envelope"></i>

						<span class="badge">5</span>

						</a>

						<ul class="dropdown-menu extended inbox">

							<li>

								<p>You have 12 new messages</p>

							</li>

							<li>

								<a href="inbox.html?a=view">

								<span class="photo"><img alt="" src="${base}/mainface/image/avatar2.jpg"></span>

								<span class="subject">

								<span class="from">Lisa Wong</span>

								<span class="time">Just Now</span>

								</span>

								<span class="message">

								Vivamus sed auctor nibh congue nibh. auctor nibh

								auctor nibh...

								</span>  

								</a>

							</li>

							<li>

								<a href="inbox.html?a=view">

								<span class="photo"><img alt="" src="${base}/mainface/image/avatar3.jpg"></span>

								<span class="subject">

								<span class="from">Richard Doe</span>

								<span class="time">16 mins</span>

								</span>

								<span class="message">

								Vivamus sed congue nibh auctor nibh congue nibh. auctor nibh

								auctor nibh...

								</span>  

								</a>

							</li>

							<li>

								<a href="inbox.html?a=view">

								<span class="photo"><img alt="" src="${base}/mainface/image/avatar1.jpg"></span>

								<span class="subject">

								<span class="from">Bob Nilson</span>

								<span class="time">2 hrs</span>

								</span>

								<span class="message">

								Vivamus sed nibh auctor nibh congue nibh. auctor nibh

								auctor nibh...

								</span>  

								</a>

							</li>

							<li class="external">

								<a href="inbox.html">See all messages <i class="m-icon-swapright"></i></a>

							</li>

						</ul>

					</li>

					<!-- END INBOX DROPDOWN -->

					<!-- BEGIN TODO DROPDOWN -->

					<li id="header_task_bar" class="dropdown">

						<a data-toggle="dropdown" class="dropdown-toggle" href="#">

						<i class="icon-tasks"></i>

						<span class="badge">5</span>

						</a>

						<ul class="dropdown-menu extended tasks">

							<li>

								<p>You have 12 pending tasks</p>

							</li>

							<li>

								<a href="#">

								<span class="task">

								<span class="desc">New release v1.2</span>

								<span class="percent">30%</span>

								</span>

								<span class="progress progress-success ">

								<span class="bar" style="width: 30%;"></span>

								</span>

								</a>

							</li>

							<li>

								<a href="#">

								<span class="task">

								<span class="desc">Application deployment</span>

								<span class="percent">65%</span>

								</span>

								<span class="progress progress-danger progress-striped active">

								<span class="bar" style="width: 65%;"></span>

								</span>

								</a>

							</li>

							<li>

								<a href="#">

								<span class="task">

								<span class="desc">Mobile app release</span>

								<span class="percent">98%</span>

								</span>

								<span class="progress progress-success">

								<span class="bar" style="width: 98%;"></span>

								</span>

								</a>

							</li>

							<li>

								<a href="#">

								<span class="task">

								<span class="desc">Database migration</span>

								<span class="percent">10%</span>

								</span>

								<span class="progress progress-warning progress-striped">

								<span class="bar" style="width: 10%;"></span>

								</span>

								</a>

							</li>

							<li>

								<a href="#">

								<span class="task">

								<span class="desc">Web server upgrade</span>

								<span class="percent">58%</span>

								</span>

								<span class="progress progress-info">

								<span class="bar" style="width: 58%;"></span>

								</span>

								</a>

							</li>

							<li>

								<a href="#">

								<span class="task">

								<span class="desc">Mobile development</span>

								<span class="percent">85%</span>

								</span>

								<span class="progress progress-success">

								<span class="bar" style="width: 85%;"></span>

								</span>

								</a>

							</li>

							<li class="external">

								<a href="#">See all tasks <i class="m-icon-swapright"></i></a>

							</li>

						</ul>

					</li>

					<!-- END TODO DROPDOWN -->

					<!-- BEGIN USER LOGIN DROPDOWN -->

					<li class="dropdown user">

						<a data-toggle="dropdown" class="dropdown-toggle" href="#">

						<img src="${base}/mainface/image/avatar1_small.jpg" alt="">

						<span class="username">Bob Nilson</span>

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

			<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->

			<div class="modal hide" id="portlet-config">

				<div class="modal-header">

					<button type="button" class="close" data-dismiss="modal"></button>

					<h3>portlet Settings</h3>

				</div>

				<div class="modal-body">

					<p>Here will be a configuration form</p>

				</div>

			</div>

			<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->

			
			<!-- BEGIN PAGE CONTAINER-->
			
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="row-fluid">
				<div class="span12">
					<ul class="breadcrumb">
						<li>
							<i class="icon-home"></i>
							<a href="javascript:void(0)">Home</a> 
							<i class="icon-angle-right"></i>
						</li>
						<li>
							<a href="javascript:void(0)">Layouts</a>
							<i class="icon-angle-right"></i>
						</li>
						<li>
							<a href="javascript:void(0)">Horzontal Menu 2</a>
						</li>
					</ul>
				</div>
			</div>
			<div class="row-fluid margin-bottom-20">
				<div class="span12">

					<#nested>								

				</div>
			</div>
		</div>	
		
		<!--
		<div class="span3 sidebar-content ">
			<div class="portlet box green">
				<div class="portlet-title line">
					<div class="caption"><i class="icon-reorder"></i><b> 常见问题 </b></div>
					<div class="tools">
						<a class="collapse" href="javascript:;"></a>
					</div>
				</div>
				<div class="portlet-body" style="overflow:hidden">
					Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum. 					
				</div>
			</div>
		</div>			
		-->
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

	<script type="text/javascript" async="" src="${base}/mainface/js/dc.js"></script>
	
	<script type="text/javascript" src="${base}/mainface/js/jquery-1.10.1.min.js"></script>

	<script type="text/javascript" src="${base}/mainface/js/jquery-migrate-1.2.1.min.js"></script>

	<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->

	<script type="text/javascript" src="${base}/mainface/js/jquery-ui-1.10.1.custom.min.js"></script>      

	<script type="text/javascript" src="${base}/mainface/js/bootstrap.min.js"></script>

	<!--[if lt IE 9]>

	<script src="${base}/mainface/js/excanvas.min.js"></script>

	<script src="${base}/mainface/js/respond.min.js"></script>  

	<![endif]-->   

	<script type="text/javascript" src="${base}/mainface/js/jquery.slimscroll.min.js"></script>

	<script type="text/javascript" src="${base}/mainface/js/jquery.blockui.min.js"></script>  

	<script type="text/javascript" src="${base}/mainface/js/jquery.cookie.min.js"></script>

	<script type="text/javascript" src="${base}/mainface/js/jquery.uniform.min.js"></script>

	<!-- END CORE PLUGINS -->

	<!--Form JS-->

	<script type="text/javascript" src="${base}/mainface/js/ckeditor.js"></script>  

	<script type="text/javascript" src="${base}/mainface/js/bootstrap-fileupload.js"></script>

	<script type="text/javascript" src="${base}/mainface/js/chosen.jquery.min.js"></script>

	<script type="text/javascript" src="${base}/mainface/js/select2.min.js"></script>

	<script type="text/javascript" src="${base}/mainface/js/wysihtml5-0.3.0.js"></script> 

	<script type="text/javascript" src="${base}/mainface/js/bootstrap-wysihtml5.js"></script>

	<script type="text/javascript" src="${base}/mainface/js/jquery.tagsinput.min.js"></script>

	<script type="text/javascript" src="${base}/mainface/js/jquery.toggle.buttons.js"></script>

	<script type="text/javascript" src="${base}/mainface/js/bootstrap-datepicker.js"></script>

	<script type="text/javascript" src="${base}/mainface/js/bootstrap-datetimepicker.js"></script>

	<script type="text/javascript" src="${base}/mainface/js/clockface.js"></script>

	<script type="text/javascript" src="${base}/mainface/js/date.js"></script>

	<script type="text/javascript" src="${base}/mainface/js/daterangepicker.js"></script> 

	<script type="text/javascript" src="${base}/mainface/js/bootstrap-colorpicker.js"></script>  

	<script type="text/javascript" src="${base}/mainface/js/bootstrap-timepicker.js"></script>

	<script type="text/javascript" src="${base}/mainface/js/jquery.inputmask.bundle.min.js"></script>   

	<script type="text/javascript" src="${base}/mainface/js/jquery.input-ip-address-control-1.0.min.js"></script>

	<script type="text/javascript" src="${base}/mainface/js/jquery.multi-select.js"></script>   

	<script src="${base}/mainface/js/bootstrap-modal.js" type="text/javascript" ></script>

	<script src="${base}/mainface/js/bootstrap-modalmanager.js" type="text/javascript" ></script> 


	<!--Form JS-->


	<script src="${base}/mainface/js/app.js"></script>      
	
	<script src="${base}/mainface/js/form-components.js"></script>

	<script>

		jQuery(document).ready(function() {    

		   App.init();
		   
		   FormComponents.init();
		   

		});

	</script>

	<!-- END JAVASCRIPTS -->

<script type="text/javascript">  var _gaq = _gaq || [];  _gaq.push(['_setAccount', 'UA-37564768-1']);  _gaq.push(['_setDomainName', 'keenthemes.com']);  _gaq.push(['_setAllowLinker', true]);  _gaq.push(['_trackPageview']);  (function() {    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;    ga.src = '${base}/mainface/js/dc.js';    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);  })();</script>



</body><!-- END BODY --></html>
</#macro>