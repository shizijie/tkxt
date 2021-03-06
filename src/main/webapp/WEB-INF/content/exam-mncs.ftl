
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
	<style>
		h1 {
			font-family:"微软雅黑";
			font-size:40px;
			margin:20px 0;
			border-bottom:solid 1px #ccc;
			padding-bottom:20px;
			letter-spacing:2px;
		}
		.time-item strong {
			background:#C71C60;
			color:#fff;
			line-height:49px;
			font-size:36px;
			font-family:Arial;
			padding:0 10px;
			margin-right:10px;
			border-radius:5px;
			box-shadow:1px 1px 3px rgba(0,0,0,0.2);
		}
		#day_show {
			float:left;
			line-height:49px;
			color:#c71c60;
			font-size:32px;
			margin:0 10px;
			font-family:Arial,Helvetica,sans-serif;
		}
		.item-title .unit {
			background:none;
			line-height:49px;
			font-size:24px;
			padding:0 10px;
			float:left;
		}
	</style>
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
			        	<input type="hidden" id="faflSelect" name="faflSelect" value="${faflid}"/>

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
						<center>
							<div class="time-item">
								<strong id="hour_show">0时</strong>
								<strong id="minute_show">0分</strong>
								<strong id="second_show">0秒</strong>
							</div>
						</center>
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
								<div class="row-fluid" id="lastNext">
									<div class="span12">
										<div class="form-actions">
											<center>
												<button class="btn big" type="button" id="lastWt" name="commit" onclick="lastWt()" >
													<i class="icon-arrow-left"></i> 上一题
												</button>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<button class="btn big" type="button" id="nextWt" name="commit" onclick="nextWt()" >
													<i class="icon-arrow-right"></i> 下一题
												</button>
											</center>									
										</div>
										<div class='alert'>说明：<font color='red'>红色方格是</font>答错的题目,白色方格是答正确的题目,灰色方格是未答题目</div>
									</div>
								</div>
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
								<button class="btn blue big" type="button" id="commit" name="commit" onclick="jiaojun()" >
									<i class="icon-ok"></i> 交卷
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<button class="btn big" type="button" onclick="javascript:window.close()" >
									<i class="icon-remove"></i> 退出
								</button>
							</center>									
						</div>

					</div>
				</div>
				
				<div style="display: none;" data-focus-on="input:first" tabindex="-1" class="modal hide fade in" id="modalsubmit" aria-hidden="false">
					<div class="modal-body">
						<br><br>
						<center><img src='${base}/extend/image/progress.gif' />  系统正在处理中，请稍等......</center>
						<br><br>
					</div>
				</div>
				
				<script>
					var data = eval(${resultinfo!});
					var totalCount = data.totalCount;
					var wtArr = data.resultinfo;//所有问题
					var indexI = 0;//点击定位
					var hdjgArr = new Array();
					var utidstr = "";
					var sfxstmfx = 0;
					var grade=0;   //分数
					var cwts = 0;
					var zqts = 0;
					//生成答题汇总表格
					var biaogestr = "";
					for(var j = 1;j <= wtArr.length; j++)
					{
						if(j == 1)
							biaogestr += "<tr>";
						biaogestr += "<td style='background-color:#DDDDDD;cursor:pointer' onclick='toshenchenwt("+(j-1)+")' id='bg_"+wtArr[j-1].id+"' ><h4><center>"+j+"</center></h4></td>";
						if(j%20 == 0 || j == totalCount)
							biaogestr += "</tr><tr>";
							
						//本次测试用题所有id
						utidstr += wtArr[j-1].id + ","
					}
					
					$("#dthz").html(biaogestr);
					
					
					//点击“下一题”按钮
					function nextWt()
					{	
						if(indexI==data.resultinfo.length-1){
							return;
						}
						shenchenwt(indexI+1);
						indexI++;
					}
					function lastWt()
					{
						if(indexI==0){
							return;
						}
						shenchenwt(indexI-1);
						indexI--;
					}
					
					function toshenchenwt(i)
					{	
						//为下一题按定位服务
						indexI = i;
						dqwt = wtArr[i];
						
						//生成题目
						$("#wtmc").html("");//先清空
						$("#hdqk").hide();
						$("#wtjx").hide();
						var answerhtml="<br><h3  class='form-section' id='delwtid_"+dqwt.id+"'><b>"+(i+1)+"、&nbsp;&nbsp;&nbsp;"+dqwt.wtmc+"</b></h3><br/>";
						if(dqwt.wtmcFileName!=""&&typeof(dqwt.wtmcFileName)!="undefined"){
							answerhtml+="<img src='${base}/userfiles/kjzt/wtimg/"+dqwt.wtmcFileName+"' />";
						}
						$("#wtmc").html(answerhtml);
						
						buildChoice(dqwt,i);
						//生成题目结束
						//考虑答题结果情况
						buildAnswer(hdjgArr[i]);
						
					}
					
					function shenchenwt(i)
					{
						dqwt = wtArr[i];
						if(i < wtArr.length)
						{
							$("#wtmc").html("");//先清空
							$("#hdqk").hide();
							$("#wtjx").hide();
							
							var answerhtml="<br><h3  class='form-section' id='delwtid_"+dqwt.id+"'><b>"+(i+1)+"、&nbsp;&nbsp;&nbsp;"+dqwt.wtmc+"</b></h3><br/>";
							if(dqwt.wtmcFileName!=""&&typeof(dqwt.wtmcFileName)!="undefined"){
								answerhtml+="<img src='${base}/userfiles/kjzt/wtimg/"+dqwt.wtmcFileName+"' />";
							}
							$("#wtmc").html(answerhtml);
							buildChoice(dqwt,i);
						}else{
							if(sfxstmfx == "1")
							{
								//最后一道题
								$("#hdqk").show();
								$("#wtjx").show();
							}else{
								//最后一道题
								$("#hdqk").hide();
								$("#wtjx").hide();
							}
						}
						//考虑答题结果情况
						buildAnswer(hdjgArr[i]);					
					}
					
					//记录填空题
					$(document).on('input propertychange','input',function(){
						var first=false;
						var html="";
						var id="";
						var wtid="";
						var answer=false;
						$(":input[type='text']").each(function(i, n) {
							if(!first){
								id=$(n).attr('pid');
								wtid=$(n).attr('name').substring(7);
								html+="tkt_"+wtid+":hdx_";
								html+=$(n).val().trim();
								first=true;
							}
							if(i>0){
								html+="|"+$(n).val().trim();
							}
							if(answer==false&&$(n).val().trim()!=""){
								answer=true;
							}
						});
						if(answer){
							$("#bg_"+wtid).css("background-color","white");
						}else{
							$("#bg_"+wtid).css("background-color","#DDDDDD");
						}
						hdjgArr[id]=html;
					}).find("input");

					
					//生成选项
					function buildChoice(dqwt,i){
						var hdhtml="";
						for(var k = 0;k < dqwt.hdxlist.length;k++)
						{
							$("#wthdx").html("");//先清空
							var tmphdx = dqwt.hdxlist[k];
							var sfzqda = tmphdx.sfzqda;
							if(dqwt.wtlx=="多选题"){
								hdhtml += "<br><label style='margin-top:20px'><input type='checkbox' name='answer_"+dqwt.id+"' id='hdx_"+dqwt.id+"_"+tmphdx.id+"' value='"+tmphdx.id+"' sfzqda='"+tmphdx.sfzqda+"' onclick='check_box(this,\""+i+"\",\""+dqwt.id+"\")'>&nbsp;&nbsp;&nbsp;<font size='5px'>"+tmphdx.dadm+"、"+tmphdx.damc+"</font></label>";
							}else if(dqwt.wtlx=="填空题"){
								hdhtml +="<br><label style='margin-top:20px'>"+tmphdx.dadm+"、<input type='text' class='m-wrap' style='margin-top:-5px' xl='tk' name='answer_"+dqwt.id+"' id='hdx_"+dqwt.id+"_"+tmphdx.id+"' pid='"+i+"' sfzqda='"+tmphdx.sfzqda+"' ></label>"
							}else{
								hdhtml += "<br><label style='margin-top:20px'><input type='radio' name='answer_"+dqwt.id+"' id='hdx_"+dqwt.id+"_"+tmphdx.id+"' value='"+tmphdx.id+"' sfzqda='"+tmphdx.sfzqda+"' onclick='daCheck(this,\""+i+"\""+",\""+dqwt.id+"\")'>&nbsp;&nbsp;&nbsp;<font size='5px'>"+tmphdx.dadm+"、"+tmphdx.damc+"</font></label>";
							}
							if(tmphdx.damcFileName!=""&&typeof(tmphdx.damcFileName)!="undefined"){
								hdhtml+="<img src='${base}/userfiles/kjzt/daimg/"+tmphdx.damcFileName+"' />";
							}
						}
						$("#wthdx").html(hdhtml);
					}
					
					//生成做题人答案
					function buildAnswer(hdstr){
						if(typeof(hdstr) != "undefined")
						{	
							var hdxid="";
							if(sfxstmfx == "1"){
								hdxid = hdstr.substring(hdstr.indexOf("hdx_")+4);
								hdxid = hdxid.split(":")[0];
							}else{
								hdxid = hdstr.substring(hdstr.indexOf("hdx_")+4);
							}
							if(hdstr.indexOf("xzt_")!=-1){
								if(hdxid.indexOf("|")!=-1){
									var ids=hdxid.split("|");
									for(var i=0;i<ids.length;i++){
										$("input[value="+ids[i]+"]").attr("checked","true");
										$("input[value="+ids[i]+"]").parent().attr("class","checked");
									}
								}else {
									$("input[value="+hdxid+"]").attr("checked","true");
									$("input[value="+hdxid+"]").parent().attr("class","checked");
								}
							}else{
								var arr=hdxid.split("|");
								for(var i=0;i<arr.length;i++){
									console.log(i+"====="+arr[i]);
									$(":input[type='text']").eq(i).val(arr[i]);
								}
							}
							
							/*已交卷显示*/
							if(sfxstmfx == "1")
							{
								if(dqwt.sfyx=="T"){
									$("#hdqk").html("<h3><font color='green'>回答正确(√)</font></h3>");
								}else{
									$("#hdqk").html("<h3><font color='red'>回答错误(X)</font></h3>");
								}	
							}
							$("#wtjx").html("<div class='alert'><h3><font color='blue'><b>试题解析：</b></font><br>"+dqwt.bz+"<br></h3></div>");
							if(sfxstmfx == "1")
							{
								$("#hdqk").show();
								$("#wtjx").show();
							}else{
								$("#hdqk").hide();
								$("#wtjx").hide();
							}
						}
						/*交卷之前任意让他可以改*/
						if(sfxstmfx == "1")
						{
							$("input[id^=hdx_"+dqwt.id+"]").each(function(rdobj){
								$(this).attr("disabled", true);
							});
						}
						
					}
					
					
					shenchenwt(indexI);
					
					//多选框
					function check_box(dqradio,iflg,wtid){
						
						$(dqradio).parent().attr("class","checked");
						hdjgArr[iflg] = "xzt_"+wtid+":hdx_";
						var ok=false;
						$(":checkbox").each(function(i, n) {
							if($(n).attr('checked')){
								if(!ok){
									hdjgArr[iflg]+=$(n).attr('value');
								}else{
									hdjgArr[iflg]+="|"+$(n).attr('value');
								}
								ok=true;
							}
						});
						if(ok==true){
							$("#bg_"+wtid).css("background-color","white");//表示已经做过了
						}else{
							$("#bg_"+wtid).css("background-color","#DDDDDD");
						}
					}
					
					//单选题
					function daCheck(dqradio,iflg,wtid)
					{	
						$(dqradio).parent().attr("class","checked");
						var sfzqda = $(dqradio).attr("sfzqda");
						indexI=Number(iflg)+1;
						
						$("#bg_"+wtid).css("background-color","white");//表示已经做过了
						
						hdjgArr[iflg] = "xzt_"+wtid+":hdx_"+$(dqradio).val();
						shenchenwt(Number(iflg)+1);
						
					}
					
					function jiaojun()
					{
						window.clearInterval(setval);
						sfxstmfx = "1";
						for(var i = 0;i < wtArr.length;i++)
						{
							trueOrFalse(i);
						}
						toshenchenwt(0);//回到第一题，并开始显示题目分析
						$("#dttjs").html("<center><h4>得分："+grade+"&nbsp;&nbsp;&nbsp;正确数："+zqts+"&nbsp;&nbsp;错误数："+cwts+"&nbsp;&nbsp;未做题数："+(wtArr.length-zqts-cwts)+"</h4></center>");
						
					
						
						$("#modalsubmit").modal({backdrop: 'static', keyboard: false});
						var fail=$("#faflSelect").val();
						
						//保存本次测试结果
						$.ajax({
						   type: "POST",
						   url: "${base}/wjgl/twjjg!savejg.action",
						   data: "cwts="+cwts+"&zqts="+zqts+"&utidstr="+utidstr+"&resultSelectStr="+hdjgArr+"&faflSelect="+fail+"&grade="+grade,
						   success: function(msg){
						    	//alert("已交卷");
						    	$("#commit").hide();
						    	$("#lastNext").hide();
						    	window.opener.document.forms[0].submit();
						    	$("#modalsubmit").modal("hide");
						    	//window.close();
						   },
						   error:function(){
						   		alert("保存存时出错了！");
						   }
						});
						
						
					}
					
					//存入错题库
					function saveFalse(wtid){
						$.ajax({
						   type: "POST",
						   url: "${base}/wjgl/twjyhctj!save2.action",
						   async:false,
						   data: "wtid="+wtid,
						   success: function(msg){
						   		
						   }
						});
					}
					
					function trueOrFalse(i)
					{
						dqwt = wtArr[i];
						var hdxls = dqwt.hdxlist;
						var zqdaid = "";
						var danorduo=false;
						var dastr=new Array();
						for(var k = 0;k < hdxls.length;k++)
						{
							var tmphdx = hdxls[k];
							dastr[k]=tmphdx.damc;
							var sfzqda = tmphdx.sfzqda;
							if(sfzqda == "1"&&danorduo==false)
							{
								zqdaid += tmphdx.id;
								danorduo=true;
							}else{
								if(sfzqda == "1"&&danorduo==true)
								{
									zqdaid += ","+tmphdx.id;
								}
							}
						}
						var hdstr = hdjgArr[i];
						var str=hdjgArr[i];
						var num=i;
						if(typeof(hdstr) != "undefined")
						{	
							//多选
							if(dqwt.wtlx=="多选题"){
								var hdxid = hdstr.substring(hdstr.indexOf("hdx_")+4);
								var hdxids=hdxid.split("|");
								var zqxids=zqdaid.split(",");
        						if(hdxids.sort().toString()== zqxids.sort().toString()){
        							$("#bg_"+dqwt.id).css("background-color","white");
        							var fl="${fl.tiliang!}";
        							var fs=fl.split("/");
        							for(var i=0;i<fs.length;i++){
        								var type=fs[i].split(":");
        								if(type[0]==dqwt.wtlx){
        									grade+=Number(type[2]);
        								}
        							}
        							dqwt.sfyx="T";
									zqts++;
        						}else{
        							$("#bg_"+dqwt.id).css("background-color","red");
        							saveFalse(dqwt.id);
        							dqwt.sfyx="F";
									cwts++;
        						}
							}else if(dqwt.wtlx=="填空题"){
								var hdxid = hdstr.substring(hdstr.indexOf("hdx_")+4);
								var ztrhuida="";
								var zqdaan="";
								//无序
								if(dqwt.isorder=="0"){
									var hdstr=hdxid.split("|");
									ztrhuida=hdstr.sort().toString();
									zqdaan=dastr.sort().toString();
								//有序
								}else{
									var hdstr=hdxid.split("|");
									ztrhuida=hdstr.toString();
									zqdaan=dastr.toString();
								}
								if(ztrhuida==zqdaan){
									$("#bg_"+dqwt.id).css("background-color","white");
									var fl="${fl.tiliang!}";
	        						var fs=fl.split("/");
	        						for(var i=0;i<fs.length;i++){
	        							var type=fs[i].split(":");
	        							if(type[0]==dqwt.wtlx){
	        								grade+=Number(type[2]);
	        							}
	        						}
	        						dqwt.sfyx="T";
									zqts++;
								}else{
									$("#bg_"+dqwt.id).css("background-color","red");
	        						//saveFalse(dqwt.id);
	        						dqwt.sfyx="F";
									cwts++;
								}
								
							}else{
								var hdxid = hdstr.substring(hdstr.indexOf("hdx_")+4);
								if(zqdaid == hdxid)
								{
									$("#bg_"+dqwt.id).css("background-color","white");
									var fl="${fl.tiliang!}";
									var fs=fl.split("/");
        							for(var i=0;i<fs.length;i++){
        								var type=fs[i].split(":");
        								if(type[0]==dqwt.wtlx){
        									grade+=Number(type[2]);
        								}
        							}
        							dqwt.sfyx="T";
									zqts++;
								}else{
									$("#bg_"+dqwt.id).css("background-color","red");
									saveFalse(dqwt.id);
									dqwt.sfyx="F";
									cwts++;
								}
							}
							if(dqwt.sfyx=="T"){
								hdjgArr[num]=str+":T";
							}else{
								hdjgArr[num]=str+":F";
							}
						}
					}
				</script>
				
				
				<script type="text/javascript">
				var intDiff = parseInt(5400);//倒计时总秒数量 45分钟
				var setval;
				function timer(intDiff){
					setval = window.setInterval(function(){
					var day=0,
						hour=0,
						minute=0,
						second=0;//时间默认值		
					if(intDiff > 0){
						day = Math.floor(intDiff / (60 * 60 * 24));
						hour = Math.floor(intDiff / (60 * 60)) - (day * 24);
						minute = Math.floor(intDiff / 60) - (day * 24 * 60) - (hour * 60);
						second = Math.floor(intDiff) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
					}
					if (minute <= 9) minute = '0' + minute;
					if (second <= 9) second = '0' + second;
					$('#day_show').html(day+"天");
					$('#hour_show').html('<s id="h"></s>'+hour+'时');
					$('#minute_show').html('<s></s>'+minute+'分');
					$('#second_show').html('<s></s>'+second+'秒');
					if(intDiff == 0)
					{
						window.clearInterval(setval);
						jiaojun();
					}
					intDiff--;
					}, 1000);
				} 
				$(function(){
					timer(intDiff);
				});	
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


