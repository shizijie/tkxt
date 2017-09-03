<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>上海朋程勤学文化传播有限公司</title>
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
  <div class="detail_title">${media?if_exists.articleName}
  <div class="detail_per"> <span>发布人：${media?if_exists.addUser?if_exists.loginName}</span><span>发布时间：${media?if_exists.addDate}</span></div>
  </div>
  <div class="right_detail">
  	<div id ="div1" style="text-align:center">
  	<img src="${base}/ico/${media?if_exists.imageFileName?if_exists}" usemap="#Map14"  style="WIDTH: 366px; HEIGHT: 475px" border="0" hspace="0" alt="" align="baseline"     >
     <map name="Map14" id="Map14"><area shape="rect" coords="0,0,700,900" href='http://www.pthink.com.cn/pcqk/${media?if_exists.remark?if_exists}/index.html' target="_blank">
     </map>
  	</div>
  	
  </div> 
</div>
<!--content right end-->
</div>
<!--content end-->



  <#include "/page/pthink/include/foot.ftl" >


</body>
</html>
