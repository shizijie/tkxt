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
<<#include "/page/pthink/include/left.ftl" >
<!--content left end-->
<!--content right-->
<div class="detail_right">
  <div class="detail_title">${media?if_exists.articleName}</div>
  <div class="detail_per"> <span>发布人：${media?if_exists.addUser?if_exists.loginName}</span><span>发布时间：${media?if_exists.addDate}</span></div>
  <div class="right_detail">
  	<OBJECT id=WMP height=400 standby="Loading..." width="650" classid="clsid:6BF52A52-394A-11D3-B153-00C04F79FAA6">
		<PARAM NAME="URL" VALUE="${base}/movie/${media?if_exists.fileName?if_exists}">
		<PARAM NAME="rate" VALUE="1">
		<PARAM NAME="balance" VALUE="0">
		<PARAM NAME="currentPosition" VALUE="0">
		<PARAM NAME="playCount" VALUE="1">
		<PARAM NAME="autoStart" VALUE="1">
		<PARAM NAME="currentMarker" VALUE="1">
		<PARAM NAME="invokeURLs" VALUE="-1">
		<PARAM NAME="baseURL" VALUE="">
		<PARAM NAME="volume" VALUE="50">
		<PARAM NAME="defaultFrame" VALUE="">
		<PARAM NAME="mute" VALUE="0">
		<PARAM NAME="uiMode" VALUE="full">
		<PARAM NAME="stretchToFit" VALUE="0">
		<PARAM NAME="windowlessVideo" VALUE="0">
		<PARAM NAME="enabled" VALUE="-1">
		<PARAM NAME="enableContextMenu" VALUE="-1">
		<PARAM NAME="fullScreen" VALUE="0">
		<PARAM NAME="SAMIStyle" VALUE="">
		<PARAM NAME="SAMILang" VALUE="">
		<PARAM NAME="SAMIFilename" VALUE="">
		<PARAM NAME="captioningID" VALUE="">
	</OBJECT>
	<br/>
	备注
	<br/>
	<font size="3">${cmsTMedia?if_exists.remark?if_exists}</font>
  </div> 
</div>
<!--content right end-->
</div>
<!--content end-->


  <#include "/page/pthink/include/foot.ftl" >

</body>
</html>
