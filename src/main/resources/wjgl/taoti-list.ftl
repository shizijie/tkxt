<#include '/macro/crud-metro-page3.ftl' >

<@crudmetropage3>
	<form id="query_form" action="${base}/wjgl/taoti.action" method="post">
	
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
									课程
		      						<select class="m-wrap span12" name="filter_EQI_fafl.id" id="filter_EQI_fafl.id" >
							      		<option value="">==全部==</option>
							      		<#list fafllist as ojb>
							      			<option value="${ojb.id}"   <#if Parameters['filter_EQI_fafl.id']?exists && Parameters['filter_EQI_fafl.id']="${ojb.id}">selected</#if>>${ojb.flmc}</option>
							      		</#list>
							      	</select>
								</div>
						  		<div class="left">
									套题名称
									<input type="text" class="m-wrap span12" id="filter_LIKES_ttmc" name="filter_LIKES_ttmc"  value="${Parameters['filter_LIKES_ttmc']?if_exists}" maxlength="100"/>
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
								<div class="actions" id="pagemenubutton" ></div>
							</div>
							<div class="portlet-body">
								<table id="result_page_table">
								<#list page.result as obj>
									<!-- 在这里编写结果列 -->
									<tr title="${obj.id}">
									  	<td>
											<#if obj.fafl?exists>
												${obj.fafl.flmc}
											</#if>
										</td>
										<td>
									  		${obj.ttmc!}
									  	</td>
									  	<td>
									  		<#list obj.remark?if_exists?split(",") as mark>
									  			<#list mark?if_exists?split("|") as memu>
									  			<#if (memu_index<2)>
									  			${memu}
									  			</#if>
									  			</#list>
									  		</#list>
									  	</td>
										 <td>
									 		<a class="btn mini purple" href="javascript:;" onclick="window.open('${base}/wjgl/taoti!input.action?id=${obj.id}')"><i class="icon-edit"></i> 修改</a>
									 		<a class="btn mini" href="javascript:;" onclick="window.open('${base}/wjgl/taoti!dysj.action?id=${obj.id}')"><i class="icon-print"></i> 打印试卷</a>
										 </td>
									</tr>
								</#list>
								</table>									
	
								<script type="text/javascript">
								
									function add()
									{
											window.open('${base}/wjgl/taoti!input.action');
									}
									
									$('#query_form').pfwpage({
										page_button:[
											{b_name:'新增',b_function:add,bclass:'btn green',bicon:'icon-pencil'},
											{b_name:'删除',opttype:'delete',bclass:'btn red',bicon:'icon-trash',b_url:'${base}/wjgl/taoti!delete.action',confirmStr:'是否确认删除？',paramName:'checks',selectNum:'^[0-9]*[1-9][0-9]*$'}
										],
										page_col:
										[
											 //在这里编写结果集显示栏目名称，例：{col_name:'xxxx'};最后一列注意不能有","号
											  	{col_name:'课程'},
											  	{col_name:'套题'},
											  	{col_name:'题量'},
											 	{col_name:''}	
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