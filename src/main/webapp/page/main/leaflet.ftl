<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>上海新朋程培训中心</title>
<link href="${base}/page/pthink/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${base}/page/pthink/css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/page/pthink/js/jquery.js"></script>
  <script type="text/javascript" src="${base}/scripts/pfw/pfw.page.min.js"></script>
<script>
$(document).ready(function() {
$(".top_nav ul li").hover(function(){
	$(this).find("dl").eq(0).css("display","block");
	},function(){
	$(this).find("dl").eq(0).css("display","none");
}); 
});
</script>
</head>

<body>
<!--top-->
<div class="top_area">
  <div class="top_language">
     <a href="#">简体中文</a> |
    <a href="#">ENGLISH</a>
  </div>
 <#include "/page/pthink/include/menu.ftl" >
  </div>
<!--top end-->



<!--content-->
<div class="content">
<!--content left-->
<#include "/page/pthink/include/left.ftl" >
<!--content left end-->

<!--content right-->
<div class="detail_right">
  <div class="detail_per"></div>
  <div class="right_detail">
  	${media?if_exists.articleContent?if_exists}
  </div> 
</div>
<!--content right end-->
</div>
<!--content end-->

 <#include "/page/pthink/include/foot.ftl" >

</body>
</html>
