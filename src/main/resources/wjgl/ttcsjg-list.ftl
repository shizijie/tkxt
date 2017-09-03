<#include '/macro/crud-metro-page3.ftl' >

<@crudmetropage3>
	<form id="query_form" action="${base}/wjgl/ttcsjg.action" method="post">
	
		<div class="row-fluid">
			<div class="span12">
				<div class="row-fluid">
					<!--查询条件-->
					<div class="span2" >
						<div class="portlet box green">
							<div class="portlet-title line">
								<div class="caption">
									<i class="icon-search"></i>条件搜索
								</div>
							</div>
							<div class="portlet-body">
							 <!-- 在这里编写查询条件 -->
							 	<div class="left">
									班级
		      						<select class="m-wrap span12" name="filter_banji" id="filter_banji" >
							      		<option value="">==全部==</option>
							      		<#list listbj as ojb>
							      			<option value="${ojb.id}"   <#if Parameters['filter_banji']?exists && Parameters['filter_banji']="${ojb.id}">selected</#if>>${ojb.bjmc}[${ojb.nj}]</option>
							      		</#list>
							      	</select>
								</div>
							 	<div class="left">
									课程
		      						<select class="m-wrap span12" name="filter_kc" id="filter_kc"  onchange="sel()">
							      		<option value="" >==全部==</option>
							      		<#list fafllist as ojb>
							      			<option value="${ojb.id}"   <#if Parameters['filter_kc']?exists && Parameters['filter_kc']="${ojb.id}">selected</#if>>${ojb.flmc}</option>
							      		</#list>
							      	</select>
								</div>
								<div class="left">
									套题
		      						<select class="m-wrap span12" name="filter_taoti" id="filter_taoti" >
		      							<option value="">==全部==</option>
							      	</select>
								</div>
						  		<div class="left">
									学生姓名
									<input type="text" class="m-wrap span12" id="filter_LIKES_stuname" name="filter_LIKES_stuname"  value="${Parameters['filter_LIKES_stuname']?if_exists}" maxlength="100"/>
								</div>
								<center>
									<button class="btn blue" type="submit">查询 <i class="m-icon-swapright m-icon-white"></i></button>
									<button class="btn" type="button" id="reset">重置 </button>
								</center>
							</div>
						</div>
					</div>
					<!--查询条件-->
					
					
					<!--查询结果-->
					<div class="span10">
						<div class="portlet">
							<div class="portlet-title">
								<div class="caption"><i class="icon-reorder"></i>查询结果</div>
							</div>
							<div class="portlet-body">
								<table id="result_page_table" >
								<#list page.result as obj>
									<!-- 在这里编写结果列 -->
									<tr title="${obj.id}">
										<td style="vertical-align:middle">
									  		${obj.bjid.bjmc!}[${obj.bjid.nj!}]
									  	</td>
									  	<td style="vertical-align:middle">
									  		${obj.xm!}
									  	</td>
									  	<td>
											<#if listjg?exists>
												<#list listjg as jg>
													<#if jg.ztr.xm==obj.xm>
														<table >
														<tr>
															<td style="border-left:0" width="200px">${jg.ztkm.fafl.flmc!}---${jg.ztkm.ttmc!}</td>
															<td width="160px">${jg.bz1!}</td>
															<td width="110px">${jg.ztzl!}</td>
															<td width="120px">${jg.ztzql!}</td>
															<td width="110px">${jg.ztcwl!}</td>
															<td width="110px">${jg.bz2!}</td>
															<td width="150px">${jg.zttime!}</td>
														</tr>
														</table>
													</#if>
												</#list>
											</#if>
										</td>
										
									  	
									</tr>
								</#list>
								</table>									
								<script type="text/javascript">
									function ttsel(){
										if($("#filter_taoti").val()!=""){
											$("#selectid").val($("#filter_taoti").val());
										}
										alert($("#selectid").val());
									}
									<#if listtt?exists&&Parameters['filter_taoti']!="">
									console.log("${Parameters['filter_taoti']}");
									$("#filter_taoti option").remove();
									$("#filter_taoti").append("<option value=\""+"\">==全部==</option>");
										<#list listtt as tt>
											$("#filter_taoti").append("<option value=\""+"\"<#if Parameters['filter_taoti']?exists&&Parameters['filter_taoti']="${tt.id}">selected</#if>>${tt.ttmc}</option>");
										</#list>
									</#if>
									function add()
									{
									}
									
									$('#query_form').pfwpage({
										page_button:[
											{b_name:'新增',b_function:add,bclass:'btn green',bicon:'icon-pencil'},
											{b_name:'删除',opttype:'delete',bclass:'btn red',bicon:'icon-trash',b_url:'${base}/wjgl/taoti!delete.action',confirmStr:'是否确认删除？',paramName:'checks',selectNum:'^[0-9]*[1-9][0-9]*$'}
										],
										page_col:
										[
											 //在这里编写结果集显示栏目名称，例：{col_name:'xxxx'};最后一列注意不能有","号
											  	{col_name:'班级'},
											  	{col_name:'学生'},
											  	{col_name:'课程<font style="margin-left:200px">总题量</font><font style="margin-left:130px">完成量</font><font style="margin-left:80px">正确量</font><font style="margin-left:100px">错误量</font><font style="margin-left:90px">完成进度</font></font><font style="margin-left:100px">完成时间</font>'},
											  	
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
										
									$("#reset").click(function(){
										$("input[name^='filter_']").val("");
										$("select[name^='filter_']").val("");
									});
									
									function sel(){
										var kcid=$("#filter_kc").val();
										$("#filter_taoti option").remove();
										$("#filter_taoti").append("<option value=\""+"\">==全部==</option>");
										if(kcid==""){
											$("#filter_taoti").attr("disabled",true);
											return;
										}
										$.post(
											"${base}/wjgl/ttcsjg!gettaoti.action",
											{kcid:kcid},
											function(data){
												$("#filter_taoti").attr("disabled",false);
												$("#filter_taoti").append(data);
											}
										);
										
									}
									
							</script>								
															
							</div>
						</div>
					</div>
					<!--查询结果-->
				
				</div>
			</div>
		</div>
		
	</form>	
</@crudmetropage3>