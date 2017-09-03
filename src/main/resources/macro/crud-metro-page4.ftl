<#macro crudmetropage4 ext...>
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
	
	<link type="text/css" rel="stylesheet" href="${base}/scripts/pfw/pfw.page.metro.css"/>
	
	</script><script type="text/javascript" src="${base}/mainface/js/jquery-1.10.1.min.js"></script>
	<script type="text/javascript" src="${base}/scripts/pfw/pfw.page.metro.js"></script>
	
	
	
	<!-- END GLOBAL MANDATORY STYLES -->

	<!--Form CSS-->

	
	<!--End Form CSS-->
	
	<link rel="stylesheet" type="text/css" href="${base}/mainface/icon/icon_skin/icon_skin.css">
	<link rel="stylesheet" type="text/css" href="${base}/mainface/icon/icon_skin/icon.css">
	
	

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
							$.ajax({ 
								type : "post", 
								url:"${base}/security/public_service!getUrlJosn.action",
								data:{pageurl:"${request.getRequestURI()?replace(base,'')}"},
								success:function(data){
									if(typeof(returnVal) == "undefined")
									{
										var obj = eval("("+data+")");
										if (obj.length == 0) {
										} else {
											var str = "";
											for (var i =obj.length-1 ; i >=0; i--) {
												if(i==0){
												    str += "<li><a href='javascript:void(0)'>"+obj[i].name+"</a></li>";
												}else{
													str += "<li><a href='javascript:void(0)'>"+obj[i].name+"</a><i class='icon-angle-right'></i></li>";	
												}
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

	<div class="footer">

		<div class="footer-inner">
			
			2015 &copy; TQBase.

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


</body><!-- END BODY --></html>
</#macro>