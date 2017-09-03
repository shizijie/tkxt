
<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] /> 

<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
	<meta charset="utf-8">
	<title>模拟测试</title>
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

				&nbsp;&nbsp;&nbsp;<font color="#fff">模拟测试</font>

				</a>

				<!-- END LOGO -->


				<!-- BEGIN HORIZANTAL MENU -->

				<div class="navbar hor-menu hidden-phone hidden-tablet">
					<div class="navbar-inner">
						<ul class="nav">
						
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
				<div class="row-fluid">
					<div class="span1" ></div>
					<div class="span10">
						
						<div class="row-fluid">
							<!--问题-->
							<div class="span6">
								<table  width="100%" border="0">
									<tr>
										<td id="wtmc"></td>
									</tr>
									<tr>
										<td id="wthdx">
											
										</td>
									</tr>
									<tr>
										<td id="hdqk">
											
										</td>
									</tr>
									<tr>
										<td id="wtjx">
											
										</td>
									</tr>
								</table>
							</div>
							<!--问题-->
							<!--答题情况 10*10-->
						<div class="span5">
							<div class="row-fluid">
								<br><h3  class='form-section'><b>答题情况</b></h3>
								<div class='alert alert-success' id="dttjs"><center><h4>正确数：0&nbsp;&nbsp;错误数：0</h4></center></div>
								<table width="100%" border="1" id="dthz">
									
								</table>
								<br>
								<div class='alert'>说明：&nbsp;&nbsp;&nbsp;交卷后才能查看答题情况<br><font color='red'>红色方格是</font>答错的题目,灰色方格是未答题目</div>
							</div>
						</div>
						<!--答题情况-->
						</div>
					</div>
					<div class="span1" ></div>
				</div>
				<!--提醒行-->
				
				<div class="row-fluid">
					<div class="span12">
						<div class="form-actions">
							<center>
								<button class="btn big" type="button" onclick="javascript:window.close()" >
									<i class="icon-remove"></i> 关闭
								</button>
							</center>									
						</div>

					</div>
				</div>
				
				<div style="display: none;" data-focus-on="input:first" tabindex="-1" class="modal hide fade in" id="modalsubmit" style="" aria-hidden="false">
					<div class="modal-body">
						<br><br>
						<center><img src='${base}/extend/image/progress.gif' />  系统正在处理中，请稍等......</center>
						<br><br>
					</div>
				</div>
				
				<script>
					var data = eval(${resultinfo!});
					var totalCount = data.totalCount;
					var wtArr = data.resultinfo.json1;//所有问题
					var indexI = 0;//点击定位
					var hdjgArr = new Array();
					var utidstr = "";
					var sfxstmfx = 1;
					
					var topicArr=data.resultinfo.json2;
					var zhtArr;
					var tktXh;
					var dxtSx;
					var dxtLx;
					var zhtNum =0;
					var topicIds="";
					
					var cwts = "${entity.cwsl!}";
					var zqts = "${entity.zqsl!}";
					var grade = "${entity.grade!}";
					//生成答题汇总表格
					var biaogestr = "";
					for(var j = 1;j <= wtArr.length+topicArr.length; j++)
					{
						if(j == 1){
							biaogestr += "<tr>";
						}
						if(j<=wtArr.length){
							biaogestr += "<td style='background-color:#DDDDDD;cursor:pointer' onclick='toshenchenwt("+(j-1)+")' id='bg_"+wtArr[j-1].id+"' ><h4><center>"+j+"</center></h4></td>";
						}else{
							biaogestr += "<td style='background-color:#DDDDDD;cursor:pointer' onclick='toTopic("+(j-wtArr.length-1)+","+wtArr.length+")' id='bg_"+topicArr[j-1-wtArr.length].id+"' ><h4><center>"+j+"</center></h4></td>";
						}
						if(j%20 == 0 || j == wtArr.length+topicArr.length){
							biaogestr += "</tr><tr>";
						}
					}
					$("#dthz").html(biaogestr);
					
					//回答结果情况
					<#list wjjgls as tmpwjjg>
							hdjgArr[${tmpwjjg_index}] = "wt_${tmpwjjg.wtid?if_exists.id!}:hdx_${tmpwjjg.yhhd!}:${tmpwjjg.sfzq!}";
						<#if tmpwjjg.yhhd?exists>
							<#if tmpwjjg.sfzq?if_exists == "1">
								$("#bg_${tmpwjjg.wtid?if_exists.id!}").css("background-color","white");
								$("#bg_${tmpwjjg.contactWebTopic?if_exists.id!}").css("background-color","white");
							<#else>
								$("#bg_${tmpwjjg.wtid?if_exists.id!}").css("background-color","red");
								$("#bg_${tmpwjjg.contactWebTopic?if_exists.id!}").css("background-color","red");
							</#if>
						<#else>
							$("#bg_${tmpwjjg.wtid?if_exists.id!}").css("background-color","#DDDDDD");
							$("#bg_${tmpwjjg.contactWebTopic?if_exists.id!}").css("background-color","#DDDDDD");
						</#if>
					</#list>
					
					//生成答题正确、错误情况
					$("#dttjs").html("<center><h4>得分："+grade+"&nbsp;&nbsp;正确数："+zqts+"&nbsp;&nbsp;错误数："+cwts+"&nbsp;&nbsp;未做题数："+(wtArr.length+topicArr.length-zqts-cwts)+"</h4></center>");
					
					function toshenchenwt(i)
					{
						dqwt = wtArr[i];
						
						//生成题目
						$("#wtmc").html("");//先清空
						$("#hdqk").hide();
						$("#wtjx").hide();
						
						$("#wtmc").html("<br><h3  class='form-section'><b>"+(i+1)+"、&nbsp;&nbsp;&nbsp;"+dqwt.wtmc+"</b></h3>");
						buildChoice(dqwt,i);
						//生成题目结束
						//考虑答题结果情况
						var hdstr = hdjgArr[i];
						buildAnswer(hdstr);
					}
					
					//生成做题人答案
					function buildAnswer(hdstr){
						if(typeof(hdstr) != "undefined")
						{
							var daanstr=hdstr.split(":");
							var hdxid = daanstr[1].substring(daanstr[1].indexOf("hdx_")+4);
							if(dqwt.wtlx!="填空题"){
								var str=hdxid.split(",");
								for(var i=0;i<str.length;i++){
									$("input[dadm="+str[i]+"]").attr("checked","true");
								}
							}else{
								var arr=hdxid.split(",");
								for(var i=0;i<arr.length;i++){
									$(":input[type='text']").eq(i).val(arr[i]);
								}
							}
							if(daanstr[2] == "1")
							{
								$("#hdqk").html("<h3><font color='green'>回答正确(√)</font></h3>");
							}else{
								$("#hdqk").html("<h3><font color='red'>回答错误(X)</font></h3>");
							}
							$("#wtjx").html("<div class='alert'><h3><font color='blue'><b>试题解析：</b></font><br>"+dqwt.bz+"<br></h3></div>");
							$("input[id^=hdx_"+dqwt.id+"]").each(function(rdobj){
								$(this).attr("disabled", true);
							});
							$("#hdqk").show();
							$("#wtjx").show();
						}
						
					}
					
					//综合题
					function toTopic(i,xh){
						$("#wtmc").html("");//先清空
						$("#hdqk").hide();
						$("#wtjx").hide();
						$("#wthdx").html("");
						zhtArr = new Array();
						//setAnswer();
						dqwt=topicArr[i];
						indexI=i+xh;
						//生成题目
						tktXh=new Array();
						dxtSx=new Array();
						dxtLx=new Array();
						var answerhtml="<br><h3  class='form-section' id='delwtid_"+dqwt.id+"' num='"+(i+xh)+"'><b>"+(i+1+xh)+"、&nbsp;&nbsp;&nbsp;"+dqwt.topicName+"</b></h3><br/>";
						answerhtml+="<h3>"+dqwt.textField+"</h3><br>";
						zhtNum=dqwt.setTopicContent.length;
						for(var j=0;j<dqwt.setTopicContent.length;j++){
							//zhtArr[j]=toType(dqwt.setTopicContent[j].topicType)+"_"+"";
							if(dqwt.setTopicContent[j].topicType==1){
								answerhtml+="<h3>("+(j+1)+") 、多选题</h3><br>";
							}else{
								answerhtml+="<h3>("+(j+1)+") 、填空题</h3><br>";
							}
							answerhtml+="<h4>"+dqwt.setTopicContent[j].topicText+"</h4><br>";
							if(dqwt.setTopicContent[j].topicType==1){
								if(dqwt.setTopicContent[j].isOrder==0){
									for(var k=0;k<dqwt.setTopicContent[j].setTopicOptions.length;k++){
										answerhtml+="<h4><input type='hidden'  class='topic_"+(j+1)+"' pid='"+dqwt.setTopicContent[j].setTopicOptions[k].id+"' pvalue='"+Eng(k)+"' num='"+j+"'>  "+Eng(k)+"、"+dqwt.setTopicContent[j].setTopicOptions[k].optionContent+"</h4><br>";
									}
									answerhtml+="请输入答案：<input id='topic_da_"+(j+1)+"' class='topic_da_"+(j+1)+"'><br>";
									dxtLx.push(j+1);							
								}else if(dqwt.setTopicContent[j].isOrder==1){
									for(var k=0;k<dqwt.setTopicContent[j].setTopicOptions.length;k++){
										answerhtml+="<h4><input type='checkbox' onclick='selbox(this)' class='topic_"+(j+1)+"' pid='"+dqwt.setTopicContent[j].setTopicOptions[k].id+"' pvalue='"+Eng(k)+"' num='"+j+"'>  "+Eng(k)+"、"+dqwt.setTopicContent[j].setTopicOptions[k].optionContent+"</h4><br>";
									}
									dxtSx.push(j+1);
								}
							}else{
								tktXh.push(j+1);
							}
							answerhtml+="<br>";
						}
						answerhtml+="<input type='hidden' id='tktIsOk' value=''><input type='hidden' id='dxtIsOk' value=''>";
						$("#wtmc").html(answerhtml);
						getAnswer(i,xh);
						
						if(sfxstmfx == "1")
						{
							$("input").each(function(rdobj){
								$(this).attr("disabled", true);
							});
						}
						$("#hdqk").show();
						$("#wtjx").show();
					
					}
					
					function getAnswer(i,xh){
						if(typeof(hdjgArr[i+xh])!="undefined"){
							var answer=hdjgArr[i+xh].toString();
							var str=answer.split(":");
							var ans=str[1].toString().split("|");
							for(var a=0;a<ans.length;a++){
								if(ans[a].toString().indexOf("dxt_")!=-1){
									var str1=ans[a].substring(8);
									if(typeof(dxtSx)!="undefined"){
										var arr1=str1.split(";");
										for(var m=0;m<dxtSx.length;m++){
											$(".topic_"+dxtSx[m]).each(function(k,n){
												for(var l=0;l<arr1.length;l++){
													if($(n).attr('pvalue')==arr1[l]){
														$(n).attr("checked","true");
													}
												}
											});
										}
									}
									if(typeof(dxtLx)!="undefined"){
										for(var u=0;u<dxtLx.length;u++){
											if(a==u){
												$(".topic_da_"+dxtLx[u]).val(str1);
											}
										}
									}
								}else if(ans[a].toString().indexOf("tkt_")!=-1){
									var str2=ans[a].substring(4);
									if(typeof(tktXh)!="undefined"){
										var arr2=str2.split(";");
										for(var j=0;j<tktXh.length;j++){
											$(".topic_"+tktXh[j]).each(function(o,n){
												$(n).val(arr2[o]);
											});
										}
									}
								}
							}
							if(str[2] == 1)
							{
								$("#hdqk").html("<h3><font color='green'>回答正确(√)</font></h3>");
							}else{
								$("#hdqk").html("<h3><font color='red'>回答错误(X)</font></h3>");
							}
							dqwt=topicArr[i];
							var html="<div class='alert'><h3><font color='blue'><b>试题解析：</b></font><br>";
							for(var a=0;a<dqwt.setTopicContent.length;a++){
								html+=dqwt.setTopicContent[a].rightAnswers+"<br>";
							}
							html+="</h3></div>";
							$("#wtjx").html(html);
						}
					}
					
					function Eng(num){
						num=num.toString();
						num=num.replace('0','A');
						num=num.replace('1','B');
						num=num.replace('2','C');
						num=num.replace('3','D');
						num=num.replace('4','E');
						num=num.replace('5','F');
						num=num.replace('6','G');
						num=num.replace('7','H');
						num=num.replace('8','I');
						num=num.replace('9','J');
						return num;
					}
					
					
					//生成选项
					function buildChoice(dqwt,i){
						var hdhtml="";
						for(var k = 0;k < dqwt.hdxlist.length;k++)
						{
							$("#wthdx").html("");//先清空
							var tmphdx = dqwt.hdxlist[k];
							var sfzqda = tmphdx.sfzqda;
							var dadm = tmphdx.dadm;
							if(dqwt.wtlx=="多选题"){
								hdhtml += "<br><label style='margin-top:20px'><input type='checkbox' name='answer_"+dqwt.id+"' id='hdx_"+dqwt.id+"_"+tmphdx.id+"' value='"+tmphdx.id+"' sfzqda='"+tmphdx.sfzqda+"' dadm='"+dadm+"'>&nbsp;&nbsp;&nbsp;<font size='5px'>"+tmphdx.dadm+"、"+tmphdx.damc+"</font></label>";
							}else if(dqwt.wtlx=="填空题"){
								hdhtml +="<br><label style='margin-top:20px'>"+tmphdx.dadm+"、<input type='text' class='m-wrap' style='margin-top:-5px' xl='tk' name='answer_"+dqwt.id+"' id='hdx_"+dqwt.id+"_"+tmphdx.id+"' pid='"+i+"' sfzqda='"+tmphdx.sfzqda+"' ></label>"
							}else{
								hdhtml += "<br><label style='margin-top:20px'><input type='radio' name='answer_"+dqwt.id+"' id='hdx_"+dqwt.id+"_"+tmphdx.id+"' value='"+tmphdx.id+"' sfzqda='"+tmphdx.sfzqda+"' dadm='"+dadm+"'>&nbsp;&nbsp;&nbsp;<font size='5px'>"+tmphdx.dadm+"、"+tmphdx.damc+"</font></label>";
							}
						}
						$("#wthdx").html(hdhtml);
					}
					
					
					toshenchenwt(0);
					
					
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


