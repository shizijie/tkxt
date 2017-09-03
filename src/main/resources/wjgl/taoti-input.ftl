<#include '/macro/crud-metro-page3-nowebpst.ftl' >

<#if id?exists>
	<#assign extPosition='修改'/>
<#else>
	<#assign extPosition='创建'/>
</#if>
<@crudmetropage3>
<link href="${base}/scripts/treeTable/stylesheets/master.css" rel="stylesheet" type="text/css" />
  <script type="text/javascript" src="${base}/scripts/treeTable/javascripts/jquery.ui.js"></script>
  <link href="${base}/scripts/treeTable/stylesheets/jquery.treeTable.css" rel="stylesheet" type="text/css" />
  <script type="text/javascript" src="${base}/scripts/treeTable/javascripts/jquery.treeTable.js"></script>
  
  <script type="text/javascript"  src="${base}/mainface/js/highcharts.js"></script>
  <script type="text/javascript"  src="${base}/mainface/js/highcharts-3d.js"></script>


	
		<div class="row-fluid">
			<div class="span12">
				<div class="row-fluid">
				<!--查询结果-->
				<div class="span12">
					<div class="portlet box green">
						<div class="portlet-title">
							<div class="caption"><i class="icon-edit"></i>
								${extPosition!}
							</div>
							<div class="actions" id="pagemenubutton" ></div>
						</div>
						<div class="portlet-body form">
						
						<!--内容表单-->
						<form id="save_inputForm" name="save_inputForm" class="form-horizontal  form-bordered" action="${base}/wjgl/taoti!save.action" method="post">
							<@s.token />
								<input type="hidden" name="id" id="ttid" value="${id!}"/>
							<!-- 在这里编写输入的元素 -->
							<div class="control-group">
								<label class="control-label">套题名称:<span class="required">*</span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="ttmc" name="ttmc" value="${ttmc!}" maxlength="100" />
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">课程:<span class="required">*</span></label>
								<div class="controls">
									<select name="fafl.id" id="faflid" class="span10 m-wrap" onchange="getinfo()">
							      		<option value="0">==请选择==</option>
							      		<#list fafllist as ojb>
							      			<option value="${ojb.id}" <#if fafl?exists && fafl.id == ojb.id>selected</#if> >${ojb.flmc}</option>
							      		</#list>
							      	</select>
								</div>
							</div>
							<#if id?exists>
							<div class="control-group">
								<label class="control-label">套题详情:<span class="required">*</span></label>
								<div class="controls ">
									<table class="table table-bordered">
										<tr><td>题型</td><td>题数</td><td>难度占比</td><td>知识点占比</td></tr>
										<#list remark?split(",") as num>
											<tr>
												<#list num?split("|") as arr>
												<td>
													${arr}
												</td>
												</#list>
											</tr>
										</#list>
									</table>
								</div>
							</div>
							</#if>
							<!--饼状图-->
							<div class="row-fluid">
								<input id="add" type="hidden"/>
							</div>
							<!--填写框-->
							<div class="row-fluid">
								<input id="addInput" type="hidden"/>
							</div>
							<!--组卷结果-->
							<div class="row-fluid">
								<input id="addResult" type="hidden"/>
							</div>
							<input type="hidden" name="ndfb" id="ndfb">
							<input type="hidden" name="zsdfb" id="zsdfb">
							<input type="hidden" name="tms" id="tms">
							<input type="hidden" name="tmlx" id="tmlx">
							<div class="row-fluid">
								<div class="span12">
									<div class="form-actions">
										<center>
											<button class="btn blue big" type="button" onclick="javascript:window.opener.location.reload(); window.close()" >
												<i class="icon-remove"></i> 关闭
											</button>
										</center>									
									</div>

								</div>
							</div>
						</form>																	
						
						<style>
							#zdzjModal
							{
								width: 1000px;
								height:500px;
								margin: 0 0 0 -370px; 
							}
						</style>
								
						<script type="text/javascript">
							<#if id?exists>
								getinfo();
							</#if>
								
							$(document).ready(function() {
								$("#save_inputForm").validate({
									rules: {
										<!-- 在这里编写验证规则 -->	
									}
								});						
							});	
							
							function getinfo(){
								$("#lx").remove();
								$("#ny").remove();
								$("#know").remove();
								$("#tian").remove();
								$("#result").remove();
								$.ajaxSetup({   
						            async : false  
						        }); 
						        <!--ajax-->
								$.post(
									"${base}/wjgl/taoti!getTopic.action",
									{kcid:$("#faflid").val()},
									function(data){
										var typeres="";
										if($.parseJSON(data).type!=""){
											var htmllx="<div id='lx' class='span4'><div class='portlet'><div class=''>";
										    htmllx+="<div class='caption'><i class='icon-reorder'></i>题型分布情况</div>";
										    htmllx+="<div class='actions' id='pagemenubutton' ></div>";
										    htmllx+="</div><div class='portlet-body'><div id='txfbqk'></div>";
										    htmllx+="</div></div></div>";
											$("#add").before(htmllx);
											
											var htmlny="<div id='ny' class='span4'><div class='portlet'><div class=''>";
										    htmlny+="<div class='caption'><i class='icon-reorder'></i>难易程度分布情况</div>";
										    htmlny+="<div class='actions' id='pagemenubutton' ></div>";
										    htmlny+="</div><div class='portlet-body'><div id='ndfbqk'></div>";
										    htmlny+="</div></div></div>";
											$("#add").before(htmlny);
											
											var htmlknow="<div id='know' class='span4'><div class='portlet'><div class=''>";
										    htmlknow+="<div class='caption'><i class='icon-reorder'></i>知识点分布情况</div>";
										    htmlknow+="<div class='actions' id='pagemenubutton' ></div>";
										    htmlknow+="</div><div class='portlet-body'><div id='zsdfbqk'></div>";
										    htmlknow+="</div></div></div>";
											$("#add").before(htmlknow);
											
											var arr1=[];
											var arr2=[];
											var arr3=[];
											var res=$.parseJSON(data);
							                $.each(res.type,function(i,n){
							                	var obj={};
							                	obj.name=n.wtlx;
							                	obj.y=Number(n.bz);
							                	arr1.push(obj);
							                })
							                $.each(res.ny,function(i,n){
							                	var obj={};
							                	obj.name=n.wtlx;
							                	obj.y=Number(n.bz);
							                	arr2.push(obj);
							                })
							                $.each(res.know,function(i,n){
							                	var obj={};
							                	obj.name=n.wtlx;
							                	obj.y=Number(n.bz);
							                	arr3.push(obj);
							                })
							                //题型
											$('#txfbqk').highcharts({
										        chart: {
										            plotBackgroundColor: null,
										            plotBorderWidth: null,
										            plotShadow: false
										        },
										        title: {
										            text: ''
										        },
										        tooltip: {
										            //pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
										        },
										        plotOptions: {
										            pie: {
										                allowPointSelect: true,
										                cursor: 'pointer',
										                dataLabels: {
										                    enabled: true,
										                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
										                    style: {
										                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
										                    }
										                },
										                events: { 
											                click: function(e) {
											                	getPercent(e.point.name) 
											                } 
											            } 
										            }
										        },
										        series: [{
										            type: 'pie',
										            name: '题目数',
										            data:arr1 
										            
										        }]
										    });
										    
										    //难易
										    $('#ndfbqk').highcharts({
										        chart: {
										            plotBackgroundColor: null,
										            plotBorderWidth: null,
										            plotShadow: false
										        },
										        title: {
										            text: ''
										        },
										        tooltip: {
										            //pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
										        },
										        plotOptions: {
										            pie: {
										                allowPointSelect: true,
										                cursor: 'pointer',
										                dataLabels: {
										                    enabled: true,
										                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
										                    style: {
										                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
										                    }
										                }
										            }
										        },
										        series: [{
										            type: 'pie',
										            name: '题目数',
										            data: arr2
										        }]
										    });
										    
										    //知识点
										    $('#zsdfbqk').highcharts({
										        chart: {
										            plotBackgroundColor: null,
										            plotBorderWidth: null,
										            plotShadow: false
										        },
										        title: {
										            text: ''
										        },
										        tooltip: {
										            //pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
										        },
										        plotOptions: {
										            pie: {
										                allowPointSelect: true,
										                cursor: 'pointer',
										                dataLabels: {
										                    enabled: true,
										                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
										                    style: {
										                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
										                    }
										                }
										            }
										        },
										        series: [{
										            type: 'pie',
										            name: '题目数',
										            data: arr3
										        }]
										    });
										    var setNum="<div id='tian' class='span12'><div class='portlet'><div class=''><div class='caption'><i class='icon-reorder'></i>1、设置题型量/难易度比例/知识点覆盖</div><div class='actions' id='pagemenubutton' ></div></div><div class='portlet-body'>";
										    setNum+="<table class='table table-bordered'><thead style='background-color: #E8F5F7'>";
										    setNum+="<tr>";
										    setNum+="<th rowspan='2' style='text-align: center;vertical-align:middle'>题型</th>";
										    setNum+="<th rowspan='2' style='text-align: center;vertical-align:middle'>题量</th>";
										    if(res.ny.length>0){
											    setNum+="<th colspan='"+res.ny.length+"' style='text-align: center;vertical-align:middle'><center>难易程度比例（%）</center></th>";
										    }
										    if(res.know.length>0){
											    setNum+="<th colspan='"+res.know.length+"' style='text-align: center;vertical-align:middle'>知识点覆盖</th>";
										    }
										    if(res.ny.length>0||res.know.length>0){
											    setNum+="<th rowspan='2' style='text-align: center;vertical-align:middle'></th>";
										    }
										    setNum+="</tr>";
										    if(res.ny.length>0||res.know.length>0){
											    setNum+="<tr>";
											    $.each(res.ny,function(i,n){
											    	setNum+="<th style='text-align: center;vertical-align:middle'>"+n.wtlx+"</th>";
											    });
											    $.each(res.know,function(i,n){
											    	setNum+="<th style='text-align: center;vertical-align:middle'>"+n.wtlx+"</th>";
											    });
											    setNum+="</tr>";
										    }
										    setNum+="</thead>";
										    $.each(res.dictlist,function(i,n){
										    	setNum+="<tr id='"+n.dm+"' ><td><center>"+n.name+"</center></td><td><center>";
										    	setNum+="<select id='"+n.dm+"_tl' data-placeholder=' ' name='"+n.dm+"_tl' class='chosen' style='width:80px' maxlength='3' value=''>";
										    	setNum+="<option value=''></option>";
										    	for(var i=1;i<=Number(n.remark);i++){
										    		setNum+="<option value='"+i+"'>"+i+"</option>";
										    	}
										    	setNum+="</select></center></td>";
										    	if(res.ny.length>0){
										    		$.each(res.ny,function(i,ny){
											    		setNum+="<td><center><input type='text' id='"+n.dm+"_ny_"+i+"' name='"+ny.name+"' style='width:40px' value='' maxlength='3'>%</center></td>";
											    	});
										    	}
										    	setNum+="<input type='hidden' id='"+n.dm+"_bl_val' name='"+n.dm+"_bl_val' value=''>";
										    	if(res.know.length>0){
										    		$.each(res.know,function(i,kn){
											    		setNum+="<td><center><input type='text' id='"+n.dm+"_know_"+i+"' name='"+kn.name+"' style='width:40px' value='' maxlength='3'>%</center></td>";
											    	});
										    	}
										    	setNum+="<td>";
										    	//setNum+="<a class='btn mini red' href='javascript:;' onclick=\"getPercent('"+n.name+"')\"><i class='icon-share'></i> 查看"+n.name+"占比</a><br/>";
										    	setNum+="<a class='btn mini blue' href='javascript:;' onclick=\"kszj('"+n.name+"','"+n.dm+"','"+res.ny.length+"','"+res.know.length+"')\"><i class='icon-share'></i> 开始组"+n.name+"</a><br/><span id='"+n.dm+"_xx'></span>";
										    	setNum+="</td></tr>";
										    });
										    setNum+="</table></div></div>";
										    $("#addInput").before(setNum);
										    $(".chosen").each(function () {
												$(this).chosen({
											    	allow_single_deselect: $(this).attr("data-with-diselect") === "1" ? true : false,
											        no_results_text: "没有找到"
											    });
											});
											
											var setResult="<div id='result' class='span12'><div class='portlet'><div class=''><div class='caption'><i class='icon-reorder'></i>2、组卷结果</div>";
											setResult+="<div class='actions' id='pagemenubutton' ></div></div><div class='portlet-body'><table style='width:100%'>";
											setResult+="<tr>";
											$.each(res.dictlist,function(i,n){
												if(i%4==0){
													setResult+"</tr><tr>";
												}
												setResult+="<td style='width:25%'><table class='table table-bordered' style='width:100%' id='"+n.dm+"_zjjg'>";
												setResult+="<thead style='background-color: #E8F5F7'><tr><th colspan='2'>"+n.name+"</th></tr></thead>";
												setResult+="<tr><td width='20%'>生成题量</td><td id='"+n.dm+"_sctl'></td></tr>";
												setResult+="<tr><td width='20%'>难易度占比分布</td><td id='"+n.dm+"_scndzb'></td></tr>";
												setResult+="<tr><td width='20%'>知识点分布</td><td id='"+n.dm+"_sczsdfb'></td></tr></table></td>";
											});
											setResult+="</tr></table></div></div></div>";
											$("#addResult").before(setResult);
										}else{
											alert("此课程已无题！！");
										}
									}
								);
								<!--ajax-->
							}
							
							//查询占比
							function getPercent(name){
								$("#ny").remove();
								$("#know").remove();
								$.ajax({
									type:"POST",
									url:"${base}/wjgl/taoti!getPercent.action",
									data:{kc:$("#faflid").val(),tmlx:name},
									success:function(data){
										if(data!=""){
											var res=$.parseJSON(data);
											
											var htmlny="<div id='ny' class='span4'><div class='portlet'><div class=''>";
										    htmlny+="<div class='caption'><font color='red'><i class='icon-reorder'></i>"+name+"难易程度分布情况</font></div>";
										    htmlny+="<div class='actions' id='pagemenubutton' ></div>";
										    htmlny+="</div><div class='portlet-body'><div id='ndfbqk'></div>";
										    htmlny+="</div></div></div>";
											$("#add").before(htmlny);
											
											var htmlknow="<div id='know' class='span4'><div class='portlet'><div class=''>";
										    htmlknow+="<div class='caption'><font color='red'><i class='icon-reorder'></i>"+name+"知识点分布情况</font></div>";
										    htmlknow+="<div class='actions' id='pagemenubutton' ></div>";
										    htmlknow+="</div><div class='portlet-body'><div id='zsdfbqk'></div>";
										    htmlknow+="</div></div></div>";
											$("#add").before(htmlknow);
											
											var arr1=[];
											var arr2=[];
											
											$.each(res.ny,function(i,n){
							                	var obj={};
							                	obj.name=n.wtlx;
							                	obj.y=Number(n.bz);
							                	arr1.push(obj);
							                })
							                $.each(res.know,function(i,n){
							                	var obj={};
							                	obj.name=n.wtlx;
							                	obj.y=Number(n.bz);
							                	arr2.push(obj);
							                })
											
											//难易
										    $('#ndfbqk').highcharts({
										        chart: {
										            plotBackgroundColor: null,
										            plotBorderWidth: null,
										            plotShadow: false
										        },
										        title: {
										            text: ''
										        },
										        tooltip: {
										            //pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
										        },
										        plotOptions: {
										            pie: {
										                allowPointSelect: true,
										                cursor: 'pointer',
										                dataLabels: {
										                    enabled: true,
										                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
										                    style: {
										                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
										                    }
										                }
										            }
										        },
										        series: [{
										            type: 'pie',
										            name: '题目数',
										            data: arr1
										        }]
										    });
										    
										    //知识点
										    $('#zsdfbqk').highcharts({
										        chart: {
										            plotBackgroundColor: null,
										            plotBorderWidth: null,
										            plotShadow: false
										        },
										        title: {
										            text: ''
										        },
										        tooltip: {
										            //pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
										        },
										        plotOptions: {
										            pie: {
										                allowPointSelect: true,
										                cursor: 'pointer',
										                dataLabels: {
										                    enabled: true,
										                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
										                    style: {
										                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
										                    }
										                }
										            }
										        },
										        series: [{
										            type: 'pie',
										            name: '题目数',
										            data: arr2
										        }]
										    });
										}
									},
									error:function(){
									}
								});
							}
							
							
							//保存组卷信息
							function kszj(name,lx,ny,know){
								var nybl=0;
								var knowbl=0;
								var nystr="";
								var knowstr="";
								for(var i=0;i<Number(ny);i++){
									var nynum=$("#"+lx+"_ny_"+i).val();
									if(nynum!=""){
										nybl+=Number(nynum);
										nystr+=$("#"+lx+"_ny_"+i).attr("name")+":"+nynum+",";
									}
									//$("#"+lx+"_xx").html(11);
								}
								for(var i=0;i<Number(know);i++){
									var knownum=$("#"+lx+"_know_"+i).val();
									if(knownum!=""){
										knowbl+=Number(knownum);
										knowstr+=$("#"+lx+"_know_"+i).attr("name")+":"+knownum+",";
									}
									//$("#"+lx+"_xx").html(11);
								}
								if($("#ttmc").val()==""){
									alert("请填写套题名称！！");
									return;
								}
								if(nybl!=100&&nystr!=""){
									$("#"+lx+"_xx").html("<font color='red'>"+name+"设置比例不对</font>");
									setTimeout(function(){
							    		$("#"+lx+"_xx").html("");
							    	},1000);
							    	return;
								}
								if(knowbl!=100&&knowstr!=""){
									$("#"+lx+"_xx").html("<font color='red'>"+name+"设置比例不对</font>");
									setTimeout(function(){
							    		$("#"+lx+"_xx").html("");
							    	},1000);
							    	return;
								}
								if($("#"+lx+"_tl").val()==""){
									$("#"+lx+"_xx").html("<font color='red'>"+name+"题量不能为空</font>");
									setTimeout(function(){
							    		$("#"+lx+"_xx").html("");
							    	},1000);
							    	return;
								}
								$("#ndfb").val(nystr);
								$("#zsdfb").val(knowstr);
								$("#tms").val($("#"+lx+"_tl").val());
								$("#tmlx").val(lx);
								
								$.ajax({
								   type: "POST",
								   url: "${base}/wjgl/taoti!saveTaoTi.action",
								   data: {ttmc:$("#ttmc").val(),kc:$("#faflid").val(),tmlx:lx,ttid:$("#ttid").val(),tms:$("#"+lx+"_tl").val(),ndfb:nystr,zsdfb:knowstr},
								   success: function(msg){
								   		if(typeof(msg) != "undefined" && msg != "")
								   		{	
								   			alert("组卷成功！");
								   			$("#ttmc").attr("disabled","disabled");
											$("#faflid").attr("disabled","disabled");
								   			//getinfo();
								   			var jsarr = msg.split("|");
								   			$("#"+lx+"_sctl").html(jsarr[0]);
								   			$("#"+lx+"_scndzb").html(jsarr[1]);
								   			$("#"+lx+"_sczsdfb").html(jsarr[2]);
								   			if(jsarr.length==4){
								   				$("#ttid").val(jsarr[3]);
								   			}
								   		}
								   		 
								   },
								   error:function(){
								   		alert("保存存时出错了！");
								   }
							   });
							}
								
						</script>
						<!--内容表单-->
						</div>
					</div>
				</div>
				<!--查询结果-->
				
				</div>
			</div>
		</div>
		
</@crudmetropage3>