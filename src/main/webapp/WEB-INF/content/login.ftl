<#include "/common/include.ftl">
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
	<title>题库系统</title>
	<meta content="width=device-width, initial-scale=1.0" name="viewport" />
	<meta content="" name="description" />
	<meta content="" name="author" />
	<!-- BEGIN GLOBAL MANDATORY STYLES -->
	<link href="${base}/mainface/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
	<link href="${base}/mainface/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>
	<link href="${base}/mainface/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
	<link href="${base}/mainface/css/style-metro.css" rel="stylesheet" type="text/css"/>
	<link href="${base}/mainface/css/style.css" rel="stylesheet" type="text/css"/>
	<link href="${base}/mainface/css/style-responsive.css" rel="stylesheet" type="text/css"/>
	<link href="${base}/mainface/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>
	<link href="${base}/mainface/css/uniform.default.css" rel="stylesheet" type="text/css"/>
	<!-- END GLOBAL MANDATORY STYLES -->
	<!-- BEGIN PAGE LEVEL STYLES -->
	<link href="${base}/mainface/css/login.css" rel="stylesheet" type="text/css"/>
	<!-- END PAGE LEVEL STYLES -->
	<link rel="shortcut icon" href="${base}/mainface/image/favicon.ico" />
</head>
<!-- END HEAD -->

<!-- BEGIN BODY -->
<body class="login" style="background-image: url(${base}/mainface/image/bg/bg111.png)">
	<!-- BEGIN LOGO -->
	<div class="logo">
		<font size="6px" color="white">在线题库考试系统</font>
		<!--yxh cancle
		<img src="${base}/mainface/image/logo-big.png" alt="" />
		--> 
	</div>
	<!-- END LOGO -->

	<!-- BEGIN LOGIN -->
	<div class="content">
		<!-- BEGIN LOGIN FORM -->
		<form class="form-vertical login-form" action="${base}/j_spring_security_check" method="post">
		<input type="hidden" name="j_captcha" id="verify" value="PthInk4!@(">
			<h3 class="form-title">系统登录区</h3>
			<div class="alert alert-error hide">
				<button class="close" data-dismiss="alert"></button>
				<span>
					<#if Session?exists&&Session['SPRING_SECURITY_LAST_EXCEPTION']?exists>
						<#if Session['SPRING_SECURITY_LAST_EXCEPTION'].message == "Bad credentials">
							用户名或密码错误
						<#else>
							${Session['SPRING_SECURITY_LAST_EXCEPTION'].message!}
						</#if>
					</#if>
				</span>
			</div>
			<div class="control-group">
				<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
				<label class="control-label visible-ie8 visible-ie9">用户名</label>
				<div class="controls">
					<div class="input-icon left">
						<i class="icon-user"></i>
						<input class="m-wrap placeholder-no-fix" type="text" placeholder="用户名" name="j_username" 
							<#if Session?exists&&Session['SPRING_SECURITY_LAST_EXCEPTION']?exists>
								value='${Session['SPRING_SECURITY_LAST_USERNAME']}'
							</#if>
						/>
					</div>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label visible-ie8 visible-ie9">密码</label>
				<div class="controls">
					<div class="input-icon left">
						<i class="icon-lock"></i>
						<input class="m-wrap placeholder-no-fix" type="password" placeholder="密码" name="j_password"/>
					</div>
				</div>
			</div>
			<div class="form-actions">
				<label class="checkbox">
				<input type="checkbox" name="remember" value="1"/> 记住我
				</label>
				<button type="submit" class="btn green pull-right">
				登录 <i class="m-icon-swapright m-icon-white"></i>
				</button>            
			</div>
			<div class="forget-password">
				<h4>登录注意事项</h4>
				<p>
		            1、登录用户名为您的学号或证件号<br />
		            2、首次登录后请立即修改密码<br />
		            3、浏览器请使用IE7.0以上版本<br />
				</p>
			</div>
		</form>
		<!-- END LOGIN FORM -->        
	</div>

	<!-- END LOGIN -->

	<!-- BEGIN COPYRIGHT -->
	<div class="copyright">
		<!-- yxh cancle
		2013 &copy; Metronic. Admin Dashboard Template.
		-->
	</div>
	<!-- END COPYRIGHT -->
	
	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
	<!-- BEGIN CORE PLUGINS -->
	<script src="${base}/mainface/js/jquery-1.10.1.min.js" type="text/javascript"></script>
	<script src="${base}/mainface/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
	<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
	<script src="${base}/mainface/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>      
	<script src="${base}/mainface/js/bootstrap.min.js" type="text/javascript"></script>
	<!--[if lt IE 9]>
	<script src="${base}/mainface/js/excanvas.min.js"></script>
	<script src="${base}/mainface/js/respond.min.js"></script>  
	<![endif]-->   
	<script src="${base}/mainface/js/jquery.slimscroll.min.js" type="text/javascript"></script>
	<script src="${base}/mainface/js/jquery.blockui.min.js" type="text/javascript"></script>  
	<script src="${base}/mainface/js/jquery.cookie.min.js" type="text/javascript"></script>
	<script src="${base}/mainface/js/jquery.uniform.min.js" type="text/javascript" ></script>
	<!-- END CORE PLUGINS -->

	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<script src="${base}/mainface/js/jquery.validate.min.js" type="text/javascript"></script>
	<!-- END PAGE LEVEL PLUGINS -->

	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	
	<!-- END PAGE LEVEL SCRIPTS --> 

	<script>
		jQuery(document).ready(function() {     
		
		  $('.login-form').validate({
	            errorElement: 'label', //default input error message container
	            errorClass: 'help-inline', // default input error message class
	            focusInvalid: false, // do not focus the last invalid input
	            rules: {
	            	j_username: {
	                    required: true
	                },
	                j_password: {
	                    required: true
	                },
	                remember: {
	                    required: false
	                }
	            },
	            messages: {
	            	j_username: {
	                    required: "请填写用户名"
	                },
	                j_password: {
	                    required: "请填写密码"
	                }
	            },
	
	            /** yxh cancle
	            invalidHandler: function (event, validator) { //display error alert on form submit   
	                $('.alert-error', $('.login-form')).show();
	            },
	            **/
	
	            highlight: function (element) { // hightlight error inputs
	                $(element)
	                    .closest('.control-group').addClass('error'); // set error class to the control group
	            },
	
	            success: function (label) {
	                label.closest('.control-group').removeClass('error');
	                label.remove();
	            },
	
	            errorPlacement: function (error, element) {
	                error.addClass('help-small no-left-padding').insertAfter(element.closest('.input-icon'));
	            },
	
	            submitHandler: function (form) {
	            	form.submit();
	            }
	        });
	
	        $('.login-form input').keypress(function (e) {
	            if (e.which == 13) {
	                if ($('.login-form').validate().form()) {
	                	$('.login-form').submit();
	                }
	                return false;
	            }
	        });
		  
		});
		<#if Session?exists&&Session['SPRING_SECURITY_LAST_EXCEPTION']?exists>
		  	 $('.alert-error', $('.login-form')).show();
		</#if>	
	</script>
	<!-- END JAVASCRIPTS -->
<!-- END BODY -->
</html>
