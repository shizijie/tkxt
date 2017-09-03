<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#include "/macro/crud.ftl" >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>上海新朋程培训中心</title>
<link href="${base}/page/pthink/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${base}/page/pthink/css/main.css" rel="stylesheet" type="text/css" />


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


<div class="content_left">

	<div class="contact">
		<h4><img src="${base}/page/pthink/images/left_title1.jpg" /></h4>
		<div class="contact_us">
			 <ul>
				 <li>地址：上海市民星路435号</li>
				 <li>邮编：200093</li>
				 <li>电话：65583716</li>
				 <li>客服热线：12345678</li>
				 <li>电子邮件：123@hotmail.com</li>
			 </ul>
			 <span class="btn">
			 	<input type="button" class="contact_btn1" value="" onclick="window.open('http://wpa.qq.com/msgrd?v=3&amp;uin=413689710&amp;site=qq&amp;menu=yes')"/>&nbsp;&nbsp;
				<input type="button" class="contact_btn2" value="" onclick="window.open('http://wpa.qq.com/msgrd?v=3&amp;uin=1002721843&amp;site=qq&amp;menu=yes')"/>
		     </span> 
		</div>
	</div>    
     
	<div class="inter">
	 <h4><img src="${base}/page/pthink/images/left_title2.jpg" /></h4>
	 <ul>
	    <li><a href="#"><img src="${base}/page/pthink/images/infer_pic.jpg" /></a><em><a href="#">美国高考炙手可热，托福报名自动化</a></em></li>
	 </ul>
	</div>    
     
</div>
<!--content left end-->
<!--content right-->
<div class="detail_right">
	<#if user?exists>
		<div class="detail_title">你已登陆</div>
	<#else>
  <div class="detail_title">用户注册</div>
  <div class="right_detail">
	  <@crud>
		<script type="text/javascript">
				$(document).ready(function() {
					$("#userForm").validate({
						rules: {
							loginName: {required:true,
								remote:{url:"${base}/cms/cms_show!checkLoginName.action",dataType: "json",data:{id:"${id?if_exists}"}}
							},
							username: {required:true},
							passWrod:{required:true,minlength:3},
							passwordConfirm: {equalTo:"#passWrod"},
							email:'email'
						},
						messages: {
							loginName: {remote: "用户登录名已存在"},
							passwordConfirm: {equalTo: "两次密码输入不一致"}
						}
					});
					
				});
		</script>
	  	<form id="userForm" action="${base}/cms/cms_show!registerSave.action" method="post">
			<@s.token />
			<table class="input_table">
			<tr>
				<td class="textfield">登录名:</td>
				<td><input type="text" id="loginName" name="loginName" size="40" value="${loginName?if_exists}" style="width:280px;"/></td>
			</tr>
			<tr>
				<td class="textfield">姓名:</td>
				<td><input type="text" id="name" name="username" size="40" value="${username?if_exists}" style="width:280px;"/></td>
			</tr>
			<tr>
				<td class="textfield">密码:</td>
				<td><input type="password" id="passWrod" name="passWrod" size="40" value="" style="width:280px;"/></td>
			</tr>
			<tr>
				<td class="textfield">确认密码:</td>
				<td><input type="password" id="passwordConfirm" name="passwordConfirm" size="40" value="" style="width:280px;"/>
				</td>
			</tr>
			<tr>
				<td class="textfield">邮箱:</td>
				<td><input type="text" id="email" name="email" size="40" value="${email?if_exists}" style="width:280px;"/></td>
			</tr>
			<tr>
				<td class="textfield">单位:</td>
				<td><input id="unitTxt"  type="text" name="unit" size="40" value="${unit?if_exists}" style="width:280px;"/></td>
			</tr>
			<tr>
				<td class="textfield">职务:</td>
				<td><input id="jobTxt" type="text" name="job" size="40" value="${job?if_exists}" style="width:280px;"/></td>
			</tr>
			<tr>
				<td class="textfield">公司地址:</td>
				<td><input id="addrTxt" type="text" name="addr" size="40" value="${addr?if_exists}" style="width:280px;"/></td>
			</tr>
			<tr>
				<td class="textfield">联系电话:</td>
				<td><input id="mobileTxt" type="text" name="mobile" size="40" value="${mobile?if_exists}" style="width:280px;"/></td>
			</tr>
			<tr>
				<td colspan="2">
					<center>
						<input type="submit" value="提交" style="BORDER-RIGHT: #7b9ebd 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #7b9ebd 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#cecfde); BORDER-LEFT: #7b9ebd 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #7b9ebd 1px solid"/>&nbsp;&nbsp;&nbsp;
						<input type="reset" value="重置" style="BORDER-RIGHT: #7b9ebd 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #7b9ebd 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#cecfde); BORDER-LEFT: #7b9ebd 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #7b9ebd 1px solid"/>
					</center>
				</td>
			</tr>
			</table>
		</form>
	</@crud>
  </div> 
  </#if>
  
</div>
<!--content right end-->
</div>
<!--content end-->



<#include "/page/pthink/include/foot.ftl" >


</body>
</html>
