<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>

<!DOCTYPE html>

<html lang="en">

<% 
	String base = request.getContextPath(); 
%>

<head>

	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1" >

	<title>访问被拒绝</title>

	<meta content="width=device-width, initial-scale=1.0" name="viewport" />

	<meta content="" name="description" />

	<meta content="" name="author" />

	<!-- BEGIN GLOBAL MANDATORY STYLES -->

	<link href="<%=base%>/mainface/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>

	<link href="<%=base%>/mainface/css/style.css" rel="stylesheet" type="text/css"/>

	<!-- END GLOBAL MANDATORY STYLES -->

	<!-- BEGIN PAGE LEVEL STYLES -->

	<link href="<%=base%>/mainface/css/error.css" rel="stylesheet" type="text/css"/>


</head>

<!-- END HEAD -->

<!-- BEGIN BODY -->

<body class="page-404-full-page">

	<div class="row-fluid">

		<div class="span12 page-404">

			<div class="number">

				Denied

			</div>

			<div class="details">

				<h3>抱歉,您没有权限访问该页面</h3>

				<p>

					访问被拒绝<br />

				</p>

			</div>

		</div>

	</div>

</body>

<!-- END BODY -->

</html>
