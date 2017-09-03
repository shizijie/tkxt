
<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] /> 

<!DOCTYPE html>
<html lang="en"> 
<head>
	<meta charset="utf-8">
	<title>在线题库考试系统</title>
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
	
	<link type="text/css" rel="stylesheet" href="${base}/scripts/pfw/pfw.page.metro.css"/>
	<script type="text/javascript" src="${base}/scripts/pfw/pfw.page.metro.js"></script>
	
	<!--Form CSS-->

	<script type="text/javascript"  src="${base}/mainface/js/highcharts.js"></script>
	<script type="text/javascript"  src="${base}/mainface/js/highcharts-3d.js"></script>
	
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

				&nbsp;&nbsp;&nbsp;<font color="#fff">在线题库考试系统</font>

				</a>

				<!-- END LOGO -->

				<#macro genTreeMenu tmenu>
					<#if tmenu.children?exists && tmenu.children?size gt 0>
						<ul class="dropdown-menu">
							<#list tmenu.children as obj>
								<#if obj.resourceType == "menu">
									<@security.authorize ifAnyGranted="${obj.authNames}">
										
										<#--直接安层次显示菜单树，不考虑urlmenu情况
										<#assign isurlmenu = "no">
										<#list urlmenus as tmpurlmenu>
											<#if tmpurlmenu.id == obj.id>
												<#assign isurlmenu = "yes">
											</#if>
										</#list>
										-->
										<#assign haveMenu = "no">
										<#assign haveUrl = "no">
										<#list obj.children as tmpobj>
											<#if tmpobj.resourceType == "menu">
												<#assign haveMenu = "yes">
											</#if>
											<#if tmpobj.resourceType == "url">
												<#assign haveUrl = "yes">
											</#if>
										</#list>
										
										<#if haveMenu == "yes">
											<li class="dropdown-submenu">
												<#--如果有url，则允许点击-->
												<#if haveUrl == "yes">
													<#--如果有定义页面的取自定义页面，没有调统一的home!app2.action-->
													<#if obj.value?exists && obj.value?index_of(".action") == -1>
														<a href="${base}/home!app2.action?parentId=${obj.id}" tabindex="-1">
															${obj.name!}
															<span class="arrow"></span>
														</a>
													<#else>
														<a href="${base}/${obj.value!}" tabindex="-1">
															${obj.name!}
															<span class="arrow"></span>
														</a>
													</#if>
												<#else>
													<a href="javascript:;" tabindex="-1">
														${obj.name!}
														<span class="arrow"></span>
													</a>	
												</#if>
												
												<@genTreeMenu tmenu=obj />
											</li>
										<#else>
											<li>
												<#--如果有url，则允许点击-->
												<#if haveUrl == "yes">
													<#--如果有定义页面的取自定义页面，没有调统一的home!app2.action-->
													<#if obj.value?exists && obj.value?index_of(".action") == -1>
														<a href="${base}/home!app2.action?parentId=${obj.id}">${obj.name!}</a>
													<#else>
														<a href="${base}/${obj.value!}">${obj.name!}</a>
													</#if>
												<#else>
													<a href="javascript:;">${obj.name!}</a>
												</#if>
												
											</li>
										</#if>
									</@security.authorize>
								</#if>
							</#list>
						</ul>
					</#if>
				</#macro>

				<!-- BEGIN HORIZANTAL MENU -->

				<div class="navbar hor-menu hidden-phone hidden-tablet">
					<div class="navbar-inner">
						<ul class="nav">
							<#assign crutUrl = request.requestURL?if_exists>
							<#if crutUrl?index_of("home!index2.action") != -1 || crutUrl?index_of("home!index.action") != -1 >
								<li id="home" class="active">
									<a href="${base}/home!index2.action" >我的工作台</a>
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

	<div class="" style="margin-top:42px">

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
				导航栏子div-->
				<div class="row-fluid">
					<div class="span12">
					</div>
				</div>
				
				
				<!--提醒行-->
				<div class="row-fluid" >
					<div class="span2" ></div>
					
					<div class="span8">
					<div class="row-fluid">
						<div class="row-fluid" >

						<div class="span3 responsive" data-tablet="span3" data-desktop="span3">
							<a href="javascript:;" style="cursor:hand;text-decoration:none;" onclick="selectmn()">
								<div class="dashboard-stat green">
									<div class="visual">
										<i class="icon-pushpin"></i>
									</div>
									<div class="details">
										<div class="number">模拟测试</div>
										<div class="desc">随机模拟测试</div>
									</div>
									<a class="more" href="#" onclick="selectmn()" >
									点击进入 <i class="m-icon-swapright m-icon-white"></i>
									</a>                 
								</div>
							</a>
						</div>
						<div class="span3 responsive" data-tablet="span3" data-desktop="span3">
							<a href="javascript:;" style="cursor:hand;text-decoration:none;" onclick="showtaoti()">
								<div class="dashboard-stat yellow">
									<div class="visual">
										<i class="icon-pushpin"></i>
									</div>
									<div class="details">
										<div class="number">套题练习</div>
										<div class="desc">随机套题测试</div>
									</div>
									<a class="more" href="#" onclick="showtaoti()" >
									点击进入 <i class="m-icon-swapright m-icon-white"></i>
									</a>                 
								</div>
							</a>
						</div>
						<div class="span3 responsive" data-tablet="span3" data-desktop="span3">
							<a href="javascript:;" style="cursor:hand;text-decoration:none;" onclick="selectZj()">
								<div class="dashboard-stat blue">
									<div class="visual">
										<i class="icon-pushpin"></i>
									</div>
									<div class="details">
										<div class="number">
											章节练习
										</div>
										<div class="desc">                           
											按章节进行练习
										</div>
									</div>
									<a class="more" href="#" onclick="selectZj()">
									点击进入 <i class="m-icon-swapright m-icon-white"></i>
									</a>                 
								</div>
							</a>
						</div>
						
						<div class="span3 responsive" data-tablet="span3  fix-offset" data-desktop="span3">
							<a href="javascript:;" style="cursor:hand;text-decoration:none;" onclick="ctlx()">
								<div class="dashboard-stat purple">
									<div class="visual">
										<i class="icon-flag"></i>
									</div>
									<div class="details">
										<div class="number">
										<script>
											var yhcts = '0';
										</script>
										<#if Request['yhcts']?exists>
											${Request['yhcts']?if_exists}
											<script>
												var yhcts = '${Request['yhcts']?if_exists}';
											</script>
										<#else>
											0
										</#if>
										</div>
										<div class="desc">错题练习</div>
									</div>
									<a class="more" href="javascript:;" onclick="ctlx()">
									点击进入 <i class="m-icon-swapright m-icon-white"></i>
									</a>                 
								</div>
							</a>
						</div>

					</div>
					</div>
					</div>
					
					<div class="span2" ></div>
				</div>
				<!--提醒行-->
				
				<div class="row-fluid">
					<div class="span2"></div>
						<div class="span8">
							<h3 class="form-section"><b><font color="red">未完成测试任务</font></b></h3>
							<form id="user_form1" name="user_form" action="${base}/home!index2.action" method="post">
								<table id="result_page_table" class="table table-striped table-bordered table-advance table-hover">
									<thead>
										<tr>
											<th>名称</th>
											<th>测试科目</th>
											<th>起止时间</th>
											<th>考试时长</th>
											<th>参与班级</th>
											<th>题量</th>
											<th></th>
										</tr>
									</thead>
									<#list page3.result as obj>
										<tr title="${obj.id}">
											<td>
												 ${obj.rwmc?if_exists}
											</td>
											<td>
												 ${obj.fafl.flmc?if_exists}
											</td>
											<td>
												${obj.kssj?if_exists}&nbsp;&nbsp;至&nbsp;&nbsp; ${obj.jssj?if_exists}
											</td>	
											<td>
												${obj.kssc?if_exists}分钟
											</td>
											<td>
												${obj.ksbjmcs?if_exists}
											</td>
											<td>
												${obj.zjxx!}
												<#if obj?if_exists.topicInfo?if_exists!="0">
										  		${obj.topicInfo!}
										  		</#if>
											</td>	
											<td>
												<#assign sfycs = "0">
												<#list wdrwcsjl as wcrw>
													<#if wcrw?if_exists.csrw?if_exists.id?exists>
														<#if wcrw?if_exists.csrw?if_exists.id == obj.id>
															<#assign sfycs = "1">
														</#if>
													</#if>
												</#list>
												
												<#if sfycs == "1">
													<font color="green">已完成测试</font>
												<#else>
													<#if ((parentId?date('yyyy-MM-dd HH:mm:ss')) gte (obj.kssj?date('yyyy-MM-dd HH:mm:ss'))) && ((parentId?date('yyyy-MM-dd HH:mm:ss')) lte (obj.jssj?date('yyyy-MM-dd HH:mm:ss'))) >
														<a class="btn mini blue" href="javascript:;" onclick="window.open('${base}/exam!rwcs.action?rwid=${obj.id}&faflid=${obj.fafl.id}')"><i class="icon-edit"></i> 进入测试</a>
													<#else>
														<font color="red">未完成已过期</font>
													</#if>
													
												</#if>
												
											</td>
										</tr>
									</#list>
								</table>
							</form>
						</div>
					</div>
				</div>
				
				
				
				<div class="row-fluid">
					<div class="span2"></div>
						<div class="span8">
							<h3 class="form-section"><b>历史测试记录</b></h3>
							<form id="user_form" name="user_form" action="${base}/home!index2.action" method="post">
								<table id="result_page_table" class="table table-striped table-bordered table-advance table-hover">
									<thead>
										<tr>
											<th>ip地址</th>
											<th>测试科目</th>
											<th>测试时间</th>
											<th>测试得分</th>
											<th>题目总数</th>
											<th>正确</th>
											<th>错误</th>
											<th>未作答</th>
										</tr>
									</thead>
									<#list page.result as obj>
										<tr title="${obj.id}">
											<td>
												 ${obj.ipdz?if_exists}
											</td>
											<td>
												${obj.faflid?if_exists.flmc?if_exists}
											</td>
											<td>
												${obj.dxsj?if_exists}
											</td>
											<td>
												${obj.grade?if_exists}
											</td>	
											<td>
												${obj.tmzs?if_exists}
											</td>
											<td>
												${obj.zqsl?if_exists}
											</td>
											<td>
												${obj.cwsl?if_exists}
											</td>
											<td>
												${obj.tmzs?if_exists?number-obj.zqsl?if_exists?number-obj.cwsl?if_exists?number}
											</td>	
										</tr>
									</#list>
								</table>
							</form>
						</div>
							
									<!--
									<div style="display: none;" data-focus-on="input:first" tabindex="-1" class="modal hide fade in" id="zjselectModal"  aria-hidden="false">
										<div class="modal-body" style="height:80%">
												<table id="table1" class="table table-striped table-bordered table-advance table-hover">
													<thead>
														<tr>
															<th>课程章节</th>
															<th>操作</th>
														</tr>
													</thead>
													<#list lctxs as fafl>
														<tr title="${fafl.id}">
															<td width="70%">
																 ${fafl.flmc?if_exists}
															</td>
															<td>
																<a class="btn mini purple" href="javascript:;" onclick="tozjlx('${fafl.id!}');"><i class="icon-edit"></i> 进行练习</a>
															</td>		
														</tr>
													</#list>
												</table>
										</div>
										<div class="modal-footer">
									        <a href="#" class="btn" data-dismiss="modal">关闭</a>
									    </div>
									</div> -->
									
									<div style="display: none;" data-focus-on="input:first" tabindex="-1" class="modal hide fade in" id="zjselectmn"  aria-hidden="false">
										<div class="modal-body" style="height:80%">
												<table id="table2" class="table table-striped table-bordered table-advance table-hover">
													<thead>
														<tr>
															<th>课程章节</th>
															<th>操作</th>
														</tr>
													</thead>
													<#list lctxs as fafl>
														<tr title="${fafl.id}">
															<td width="70%">
																 ${fafl.flmc?if_exists}
															</td>
															<td>
																<a class="btn mini purple" href="javascript:;" onclick="tomn('${fafl.id!}','${fafl.tiliang!}');"><i class="icon-edit"></i> 进入测试</a>
															</td>		
														</tr>
													</#list>
												</table>
										</div>
										<div class="modal-footer">
									        <a href="#" class="btn" data-dismiss="modal">关闭</a>
									    </div>
									</div>
									
									<div style="display: none;" data-focus-on="input:first" tabindex="-1" class="modal hide fade in" id="zhangjie"  aria-hidden="false">
										<div class="modal-body" style="height:80%">
												<table id="table5" class="table table-striped table-bordered table-advance table-hover">
													<thead>
														<tr>
															<th>请选择章节练习课程</th>
														</tr>
													</thead>
													
														<tr title="">
															<td >
																<select class="span 8" id="kcsel1" onchange="selzj()">
																	<option value="">--请选择课程--</option>
																<#list lctxs as fafl>
																	<option value="${fafl.id}">${fafl.flmc?if_exists}</option>
																</#list>
																</select>
															</td>
														</tr>
												</table>
												<table id="table6" class="table table-striped table-bordered table-advance table-hover" style="border-collapse:collapse">
												</table>
										</div>
										<div class="modal-footer">
									        <a href="#" class="btn" data-dismiss="modal">关闭</a>
									    </div>
									</div>
									
									<div style="display: none;" data-focus-on="input:first" tabindex="-1" class="modal hide fade in" id="taoti"  aria-hidden="false">
										<div class="modal-body" style="height:80%">
												<table id="table3" class="table table-striped table-bordered table-advance table-hover">
													<thead>
														<tr>
															<th>请选择套题练习课程</th>
														</tr>
													</thead>
													
														<tr title="">
															<td >
																<select class="span 8" id="kcsel" onchange="sel()">
																	<option value="">--请选择课程--</option>
																<#list lctxs as fafl>
																	<option value="${fafl.id}">${fafl.flmc?if_exists}</option>
																</#list>
																</select>
															</td>
														</tr>
												</table>
												<table id="table4" class="table table-striped table-bordered table-advance table-hover" style="border-collapse:collapse">
												</table>
										</div>
										<div class="modal-footer">
									        <a href="#" class="btn" data-dismiss="modal">关闭</a>
									    </div>
									</div>
				</div>	
									
									
									
									
									<style>
										#zhangjie{
											width: 800px;
											height:500px;
											margin: 0 0 0 -370px; 
										}
										#zjselectmn{
											width: 800px;
											height:500px;
											margin: 0 0 0 -370px; 
										}
										#taoti{
											width: 800px;
											height:500px;
											margin: 0 0 0 -370px; 
										}
									</style>
									
									<script>
										function ctlx()
										{
											if(yhcts != "0")
											{
												window.open('${base}/exam!ctlx.action');
											}else
											{
												alert("您没有错题");
											}
										}
										$(document).ready(function(){
											$('#user_form').pfwpage({
												page_button:[
												],
												page_col:[
													],
												page_table:'#result_page_table',
												page_table_search:'#result_page_table',
												page_pageNo:${page.pageNo},
												page_pageSize:${page.pageSize},
												page_orderBy:'${page.orderBy}',
												page_order:'${page.order}',
												page_totalPages:${page.totalPages},
												page_totalCount:${page.totalCount}
											});
											$('#user_form1').pfwpage({
												page_button:[
												],
												page_col:[
													],
												page_table:'#result_page_table',
												page_table_search:'#result_page_table',
												page_pageNo:${page3.pageNo},
												page_pageSize:${page3.pageSize},
												page_orderBy:'${page3.orderBy}',
												page_order:'${page3.order}',
												page_totalPages:${page3.totalPages},
												page_totalCount:${page3.totalCount}
											});
											
											$("#quanxuan").remove();
											$("#chongxuan").remove();
										});
										
										
										function selectZj()
										{
											$("#zhangjie").modal({backdrop: 'static', keyboard: false});
										}
									
										function selectmn()
										{
											$("#zjselectmn").modal({backdrop: 'static', keyboard: false});
										}
										function tomn(faid,tiliang)
										{	
											if(tiliang==""||tiliang==null||tiliang==undefined){
												alert("暂未出卷！！");
												return;
											}
											window.open(encodeURI('${base}/exam!mncs.action?flid='+faid));
											$("#zhangjie").modal("hide");
										}
										function showtaoti(){
											$("#taoti").modal({backdrop:'static',keyboard:false});
										}
										function sel(){
											$("#table4").html("");
											if($("#kcsel").val()!=""){
												$.post(
													"${base}/exam!gettaoti.action",
													{flid:$("#kcsel").val()},
													function(data){
														if(eval(data)!=""){
															var html="<tr><th>套题名称</th><th>题量</th><th>操作</th></tr>";
															$.each(eval(data),function(i,n){
																html+="<tr><td width=\"50%"+"\">"+n.ttmc+"</td>";
																html+="<td>";
																var str=n.remark.split(",");
																for(var i=0;i<str.length;i++){
																	var str2=str[i].split("|");
																	html+=str2[0]+":"+str2[1]+" ";
																}
																html+="</td>";
																html+="<td>"+"<a class=\"btn mini purple"+"\" onclick="+"\"taotiinfo('"+n.id+"');\"><i class="+"\"icon-edit"+"\"></i> 进入测试</a>";
																html+="&nbsp;&nbsp;<a class=\"btn mini red"+"\" onclick="+"\"qingkong('"+n.id+"');\"><i class="+"\"icon-edit"+"\"></i> 清空进度</a>"+"</td></tr>";
															});
															html+="";
															$("#table4").html(html);
														}else{
															return;
														}
													}
												)
											}
										}
										function selzj(){
											$("#table6").html("");
											if($("#kcsel1").val()!=""){
												$.post(
													"${base}/exam!getzhangjie.action",
													{kcid:$("#kcsel1").val()},
													function(data){
														var html="<tr><th>章节名称</th><th>题型</th><th>操作</th></tr>";
														$.each(eval(data),function(i,n){
															html+="<tr><td width=\"50%"+"\">"+n.flmc+"</td>";
															html+="<td>"+n.tiliang+"</td>";
															html+="<td>"+"<a class=\"btn mini purple"+"\" onclick="+"\"zjinfo('"+n.id+"');\"><i class="+"\"icon-edit"+"\"></i> 进入测试</a>";
														});
														html+="";
														$("#table6").html(html);
													}
												);
											}
										}
										function taotiinfo(id){
											window.open('${base}/exam!ttcs.action?ttid='+id);
											$("#taoti").modal("hide");
										}
										function qingkong(id){
											var msg=confirm("您确定清空之前的做题进度吗??");
											if(msg==true){
												$.post(
													"${base}/exam!qingkong.action",
													{id:id},
													function(data){
														alert(data);
													}
												)
											}else{
												return;
											}
										}
										function zjinfo(id){
											window.open('${base}/exam!zjlx.action?flid='+id);
											$("#zhangjie").modal("hide");
										}
			
									</script>
									
					
				
				
			</div>
			<!-- END PAGE CONTAINER--> 

		</div>

		<!-- END PAGE -->    

	</div>

	<!-- END CONTAINER -->

	<!-- BEGIN FOOTER -->


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


