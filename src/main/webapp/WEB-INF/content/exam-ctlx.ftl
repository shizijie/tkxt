<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] /> 

<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
	<meta charset="utf-8">
	<title>错题练习</title>
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

				&nbsp;&nbsp;&nbsp;<font color="#fff">错题练习</font>

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

	<div class="page-container row-fluid">

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
							<div class="span7">
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
								<div class="row-fluid">
									<div class="span12">
										<div class="form-actions">
											<center>
												<button class="btn big" type="button" id="lastWt" name="commit" onclick="lastWt()" >
													<i class="icon-arrow-left"></i> 上一题
												</button>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<button class="btn big" type="button" id="nextWt" name="commit" onclick="nextWt()" >
													<i class="icon-arrow-right"></i> 下一题
												</button>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<button class="btn blue big" type="button" id="commit" name="commit" onclick="removeCtk()" >
													<i class="icon-trash"></i> 从错题库中删除
												</button>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<button class="btn big" type="button" onclick="window.close()" >
													<i class="icon-home"></i> 退出练习
												</button>
											</center>									
										</div>
				
									  </div>
								  </div>
						</div>
							<!--问题-->
							<!--答题情况 10*10-->
						<div class="span4">
							<div class="row-fluid">
								<br><h3  class='form-section'><b>答题情况</b></h3>
								<div class='alert alert-success' id="dttjs"><center><h4>正确数：0&nbsp;&nbsp;错误数：0</h4></center></div>
								<table width="100%" border="1" id="dthz">
									
								</table>
								<br>
								<div class='alert'>说明：<font color='red'>红色方格是</font>答错的题目,白色方格是答正确的题目,灰色方格是未答题目</div>
							</div>
						</div>
						<!--答题情况-->
						</div>
					</div>
					<div class="span1" ></div>
				</div>
				<!--提醒行-->
				
				
				
				<div style="display: none;" data-focus-on="input:first" tabindex="-1" class="modal hide fade in" id="modalsubmit" style="width:340;height:100;display: block; margin-top: -155px;" aria-hidden="false">
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
					var count = new Array();
					var isTrue=new Array();
					var utidstr = "";
					var num=0;
					var ztjg="";
					var cwts = 0;
					var zqts = 0;
					
					//生成答题汇总表格
					var biaogestr = "";
					for(var j = 1;j <= wtArr.length; j++)
					{
						if(j == 1)
							biaogestr += "<tr>";
						biaogestr += "<td style='background-color:#DDDDDD;cursor:pointer' onclick='toshenchenwt("+(j-1)+")' id='bg_"+wtArr[j-1].id+"' pid='"+(j-1)+"'><h4><center>"+j+"</center></h4></td>";
						if(j%10 == 0 || j == totalCount)
							biaogestr += "</tr><tr>";
							
						//本次测试用题所有id
						utidstr += wtArr[j-1].id + ","
					}
					
					$("#dthz").html(biaogestr);
					
					//点击“下一题”按钮
					function nextWt()
					{	
						var wtid=$("#wtmc").children().eq(1).attr('id').substring(8);
						num = Number($("#bg_"+wtid).attr('pid'));
						if(num==data.resultinfo.length-1){
							return;
						}
						toshenchenwt(num+1);
					}
					function lastWt()
					{
						var wtid=$("#wtmc").children().eq(1).attr('id').substring(8);
						num = Number($("#bg_"+wtid).attr('pid'));
						if(num==0){
							return;
						}
						toshenchenwt(num-1);
					}
					
					//多选题右边框验证
					function yanzheng(){
						var wtid=$("#wtmc").children().eq(1).attr('id').substring(8);
						
						var zq=true;
						var hdstr=hdjgArr[$("#bg_"+wtid).text()-1];
						if(hdstr==undefined||hdstr.indexOf("xzt_")>0){
							return;
						}
						var number=$("#bg_"+wtid).attr('pid');
						if(typeof(isTrue[number])!="undefined"){
							return true;
						}
						
						if(hdstr.indexOf("tkt_")>=0){
							var constr=hdstr.substring(hdstr.indexOf("hdx_")+4);
							dqwt = wtArr[number];
							var ztrhuida="";
							var zqdaan="";
							var dastr=new Array();
							var hdxls=dqwt.hdxlist;
							for(var k = 0;k < hdxls.length;k++)
							{
								var tmphdx = hdxls[k];
								dastr[k]=tmphdx.damc;
							}
							//无序
							if(dqwt.isorder=="0"){
								var hdstr=constr.split("|");
								ztrhuida=hdstr.sort().toString();
								zqdaan=dastr.sort().toString();
							//有序
							}else{
								var hdstr=constr.split("|");
								ztrhuida=hdstr.toString();
								zqdaan=dastr.toString();
							}
							if(ztrhuida!=zqdaan){
								zq=false;
							}
						}else{
							$("input[type='checkbox']").each(function(i, n) {
								var check=$(n).attr('checked')=="checked";
								if((check==true&&$(n).attr('sfzqda')=="0")||check==false&&$(n).attr('sfzqda')=="1"){
									zq=false;
									return false;
								}
							});
						}
						if(typeof(count[wtid])=="undefined"){
							if(ztjg==""||typeof(ztjg)=="undefined"){
								ztjg+=wtid;
							}else{
								ztjg+="/"+wtid;
							}
						}
						if(zq==true){
							$("#bg_"+wtid).css("background-color","white");
							isTrue[number]=true;
							if(typeof(count[wtid]) == "undefined"){
								var num=hdjgArr[$("#bg_"+wtid).text()-1].indexOf("hdx_");
								ztjg+=","+"1"+",";
								zqts++;
								count[wtid]=true;
							}
						}else{
							$("#bg_"+wtid).css("background-color","red");
							isTrue[number]=false;
							if(typeof(count[wtid]) == "undefined"){
								var num=hdjgArr[$("#bg_"+wtid).text()-1].indexOf("hdx_");
								ztjg+=","+"0"+",";
								saveFalse(wtid);   //存入错题库
								cwts++;
								count[wtid]=true;
							}
						}
						if(hdstr.indexOf("tkt_")>=0){
							ztjg+=hdstr.substring(hdstr.indexOf("hdx_")+4);
						}else{
							ztjg+=hdjgArr[$("#bg_"+wtid).text()-1].substring(num+4);
						}
						$("#dttjs").html("<center><h4>正确数："+zqts+"&nbsp;&nbsp;错误数："+cwts+"</h4></center>");	
						return isTrue[number];
					}
					
					//记录填空题
					$(document).on('input propertychange','input',function(){
						var first=false;
						var html="";
						var id="";
						var wtid="";
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
						});
						hdjgArr[id]=html;
					}).find("input");
					
					
					function toshenchenwt(i)
					{
						//为下一题按定位服务
						if(i==0){
							var hdstr1 = hdjgArr[0];
						}else{
							var wtid=$("#wtmc").children().eq(1).attr('id').substring(8);
							var hdstr1 = hdjgArr[$("#bg_"+wtid).attr('pid')];
						}
						if(typeof(hdstr1) != "undefined"&&hdstr1.substring(hdstr1.indexOf("hdx_")+4)!=""&&hdstr1.indexOf("xzt_")==-1){
							if(!yanzheng()){
								$("#hdqk").html("<h3><font color='red'>回答错误(X)</font></h3>");
								$("#wtjx").html("<div class='alert'><h3><font color='blue'><b>试题解析：</b></font><br>"+dqwt.bz+"<br></h3></div>");
								$("#hdqk").show();
								$("#wtjx").show();
								$("input[id^=hdx_"+dqwt.id+"]").each(function(rdobj){
									$(this).attr("disabled", true);
								});
								return;
							}
						}
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
						var hdxls = dqwt.hdxlist;
						var hdhtml = "";
						var wtjx = dqwt.bz;
						if(dqwt.ttbz!="1"&&typeof(dqwt.ttbz)!="undefined"){
							hdhtml+=haveSave(hdxls,dqwt);
						}else{
							hdhtml+=noZuo(hdxls,dqwt,i);
						}
						if(dqwt.sfyx=="F"){
							$("#hdqk").html("<h3><font color='red'>回答错误(X)</font></h3>");
							$("#wtjx").html("<div class='alert'><h3><font color='blue'><b>试题解析：</b></font><br>"+dqwt.bz+"<br></h3></div>");
							$("#hdqk").show();
							$("#wtjx").show();
						}
						$("#wthdx").html(hdhtml);
						if(dqwt.wtlx=="填空题"&&dqwt.ttbz!="1"&&typeof(dqwt.ttbz)!="undefined"){
							var daan=dqwt.ttbz.split("|");
							for(var i=0;i<daan.length;i++){
								$(":input[type='text']").eq(i).val(daan[i]);
							}
						}
						//生成题目结束
						//考虑答题结果情况
						var hdstr = hdjgArr[i];
						if(typeof(hdstr) != "undefined"&&hdstr.substring(hdstr.indexOf("hdx_")+4)!="")
						{
							showok(dqwt,hdstr,i);
							$("#hdqk").show();
							$("#wtjx").show();
						}
						
					}
					
					//显示正确错误
					function showok(dqwt,hdstr,i){
						var num=i;
						var hdxid = hdstr.substring(hdstr.indexOf("hdx_")+4);
						if(dqwt.wtlx=="填空题"){
							var daan=hdxid.split("|");
							for(var i=0;i<daan.length;i++){
								$(":input[type='text']").eq(i).val(daan[i]);
							}
						}else{
							var ids=hdxid.split("?");
							for(var i=0;i<ids.length;i++){
								$("input[value="+ids[i]+"]").attr("checked","true");
								$("input[value="+ids[i]+"]").parent().attr("class","checked");
							}
						}
						$("input[id^=hdx_"+dqwt.id+"]").each(function(rdobj){
							$(this).attr("disabled", true);
						});
						if(isTrue[num]==true){
							$("#hdqk").html("<h3><font color='green'>回答正确(√)</font></h3>");
						}
						if(isTrue[num]==false){
							$("#hdqk").html("<h3><font color='red'>回答错误(X)</font></h3>");
						}
						$("#wtjx").html("<div class='alert'><h3><font color='blue'><b>试题解析：</b></font><br>"+dqwt.bz+"<br></h3></div>");
					}
					
					//已存题验证
					function haveSave(hdxls,dqwt){
						var hdhtml="";
						for(var k = 0;k < hdxls.length;k++)
						{
							$("#wthdx").html("");//先清空
							var tmphdx = hdxls[k];
							var sfzqda = tmphdx.sfzqda;
							var daan=tmphdx.dadm;
							if(dqwt.wtlx=="多选题"){
								var yizuo=dqwt.ttbz.split("?");
								var have=false;
								for(var i=0;i<yizuo.length;i++){
									if(yizuo[i]==tmphdx.id){
										have=true;
										break;
									}
								}
								if(have){
									hdhtml += "<br><label style='margin-top:20px'><input type='checkbox' name='answer_"+dqwt.id+"' id='hdx_"+dqwt.id+"_"+tmphdx.id+"' value='"+tmphdx.id+"' sfzqda='"+tmphdx.sfzqda+"' daan='"+tmphdx.dadm+"' disabled checked >&nbsp;&nbsp;&nbsp;<font size='5px'>"+tmphdx.dadm+"、"+tmphdx.damc+"</font></label>";
								}else{
									hdhtml += "<br><label style='margin-top:20px'><input type='checkbox' name='answer_"+dqwt.id+"' id='hdx_"+dqwt.id+"_"+tmphdx.id+"' value='"+tmphdx.id+"' sfzqda='"+tmphdx.sfzqda+"' daan='"+tmphdx.dadm+"' disabled>&nbsp;&nbsp;&nbsp;<font size='5px'>"+tmphdx.dadm+"、"+tmphdx.damc+"</font></label>";
								}
							}else if(dqwt.wtlx=="填空题"){
								hdhtml +="<br><label style='margin-top:20px'>"+tmphdx.dadm+"、<input type='text' readonly class='m-wrap' style='margin-top:-5px' lx='tk' name='answer_"+dqwt.id+"' id='hdx_"+dqwt.id+"_"+tmphdx.id+"' pid='"+i+"' sfzqda='"+tmphdx.sfzqda+"' ></label>"
							}else{
								if(dqwt.ttbz==daan){
									hdhtml += "<br><label style='margin-top:20px'><input type='radio' name='answer_"+dqwt.id+"' id='hdx_"+dqwt.id+"_"+tmphdx.id+"' value='"+tmphdx.id+"' sfzqda='"+tmphdx.sfzqda+"' daan='"+tmphdx.dadm+"' disabled checked >&nbsp;&nbsp;&nbsp;<font size='5px'>"+tmphdx.dadm+"、"+tmphdx.damc+"</font></label>";
								}else{
									hdhtml += "<br><label style='margin-top:20px'><input type='radio' name='answer_"+dqwt.id+"' id='hdx_"+dqwt.id+"_"+tmphdx.id+"' value='"+tmphdx.id+"' sfzqda='"+tmphdx.sfzqda+"' daan='"+tmphdx.dadm+"' disabled  >&nbsp;&nbsp;&nbsp;<font size='5px'>"+tmphdx.dadm+"、"+tmphdx.damc+"</font></label>";
								}
							}
							if(tmphdx.damcFileName!=""&&typeof(tmphdx.damcFileName)!="undefined"){
								hdhtml+="<img src='${base}/userfiles/kjzt/daimg/"+tmphdx.damcFileName+"' />";
							}
						}
						return hdhtml;
					}
					
					//未答题显示
					function noZuo(hdxls,dqwt,i){
						var hdhtml="";
						for(var k = 0;k < hdxls.length;k++)
						{
							$("#wthdx").html("");//先清空
							var tmphdx = hdxls[k];
							var sfzqda = tmphdx.sfzqda;
							var daan=tmphdx.dadm;
							if(dqwt.wtlx=="多选题"){
								hdhtml += "<br><label style='margin-top:20px'><input type='checkbox' name='answer_"+dqwt.id+"' id='hdx_"+dqwt.id+"_"+tmphdx.id+"' value='"+tmphdx.id+"' sfzqda='"+tmphdx.sfzqda+"' daan='"+tmphdx.dadm+"'  onclick='check_box(this,\""+i+"\",\""+dqwt.id+"\")'>&nbsp;&nbsp;&nbsp;<font size='5px'>"+tmphdx.dadm+"、"+tmphdx.damc+"</font></label>";
							}else if(dqwt.wtlx=="填空题"){
								hdhtml +="<br><label style='margin-top:20px'>"+tmphdx.dadm+"、<input type='text' class='m-wrap' style='margin-top:-5px' lx='tk' name='answer_"+dqwt.id+"' id='hdx_"+dqwt.id+"_"+tmphdx.id+"' pid='"+i+"' sfzqda='"+tmphdx.sfzqda+"' ></label>"
							}else{
								hdhtml += "<br><label style='margin-top:20px'><input type='radio' name='answer_"+dqwt.id+"' id='hdx_"+dqwt.id+"_"+tmphdx.id+"' value='"+tmphdx.id+"' sfzqda='"+tmphdx.sfzqda+"' daan='"+tmphdx.dadm+"'  onclick='daCheck(this,\""+i+"\",\""+dqwt.id+"\")'>&nbsp;&nbsp;&nbsp;<font size='5px'>"+tmphdx.dadm+"、"+tmphdx.damc+"</font></label>";
							}
							if(tmphdx.damcFileName!=""&&typeof(tmphdx.damcFileName)!="undefined"){
								hdhtml+="<img src='${base}/userfiles/kjzt/daimg/"+tmphdx.damcFileName+"' />";
							}
						}
						return hdhtml;
					}
					
					//第一次进入，生成第一道题目
					toshenchenwt(0);
					
					//多选框
					function check_box(dqradio,iflg,wtid){
						$(dqradio).parent().attr("class","checked");
						hdjgArr[iflg] = "dxt_"+wtid+":hdx_";
						var ok=false;
						$(":checkbox").each(function(i, n) {
							if($(n).attr('checked')){
								if(!ok){
									hdjgArr[iflg]+=$(n).attr('value');
								}else{
									hdjgArr[iflg]+="?"+$(n).attr('value');
								}
								ok=true;
							}
						});
					}
					
					
					function daCheck(dqradio,iflg,wtid)
					{	
						var ztda=($(dqradio).attr("daan"));
						if(ztjg==""||typeof(ztjg)=="undefined"){
							ztjg+=wtid;
						}else{
							ztjg+="/"+wtid;
						}
						indexI=Number(iflg);
						$(dqradio).parent().attr("class","checked");
						var sfzqda = $(dqradio).attr("sfzqda");
						if(sfzqda == "0")
						{	
							ztjg+=","+"0"+","+ztda;
							$("#hdqk").html("<h3><font color='red'>回答错误(X)</font></h3>");
							$("#bg_"+wtid).css("background-color","red");
							cwts++;
							isTrue[iflg]=false;
						}
						if(sfzqda == "1")
						{	
							ztjg+=","+1+","+ztda;
							$("#hdqk").html("<h3><font color='green'>回答正确(√)</font></h3>");
							$("#bg_"+wtid).css("background-color","white");
							zqts++;
							isTrue[iflg]=true;
						}
						$("#dttjs").html("<center><h4>正确数："+zqts+"&nbsp;&nbsp;错误数："+cwts+"</h4></center>");
						$("#wtjx").html("<div class='alert'><h3><font color='blue'><b>试题解析：</b></font><br>"+wtArr[iflg].bz+"<br></h3></div>");
						
						$("input[id^=hdx_"+wtid+"]").each(function(rdobj){
							$(this).attr("disabled", true);
						});
						
						hdjgArr[iflg] = "xzt_"+wtid+":hdx_"+$(dqradio).val();
						$("#hdqk").show();
						$("#wtjx").show();
						//答对了，自动跳到下一题，答错了，则停在原来的地方
						if(sfzqda == "1"){
							nextWt();
						}
					}
					
					//从错题库中删除
					function removeCtk()
					{
						var wtidstr = $("h3[id^=delwtid_]").attr("id");
						var wtids = wtidstr.substring(wtidstr.indexOf("_")+1);
						
						//错题库中删除
						$.ajax({
						   type: "POST",
						   url: "${base}/wjgl/twjyhctj!delete2.action",
						   async:false,
						   data: "wtid="+wtids,
						   success: function(msg){
						     	alert("已从错题库中删除，下次进入生效");
						   },
						   error:function(msg){
						   		alert("从题库中删除时发生了错误");
						   }
						});
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


