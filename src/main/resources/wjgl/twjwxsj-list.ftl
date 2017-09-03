<#include '/macro/crud-metro-page3.ftl' >

<#assign dispWxsjtype={"1":"固定时间", "2":"临时时间"}>

<@crudmetropage3>
	<form id="query_form" action="${base}/wjgl/twjwxsj.action" method="post">
	
		<div class="row-fluid">
			<div class="span12">
				<div class="row-fluid">
					<!--查询结果-->
					<div class="span12">
						<div class="portlet">
							<div class="portlet-title">
								<div class="caption"><i class="icon-reorder"></i>Client端无效时间列表</div>
								<div class="actions" id="pagemenubutton" ></div>
							</div>
							<div class="portlet-body">
								<table id="result_page_table">
								<#list page.result as obj>
									<!-- 在这里编写结果列 -->
									<tr title="${obj.id}">
									  	<td>
									  		${dispWxsjtype[obj.sjlx!]}
									  	</td>
									  	<td>
									  		${obj.zhourq!}
									  	</td>
									  	<td>
									  		${obj.zhourq2!}
									  	</td>
									  	<td>
									  		${obj.kssj!}
									  	</td>
									  	<td>
									  		${obj.jssj!}
									  	</td>
									  	<td>
									  		${obj.bz!}
									  	</td>
									  	<!--
										 <td>
									 		<a class="btn mini purple" href="javascript:;" onclick="window.open('${base}/wjgl/twjwxsj!input.action?id=${obj.id}')"><i class="icon-edit"></i> 修改</a>
										 </td>
										 -->
									</tr>
								</#list>
								</table>									
	
								<script type="text/javascript">
								
									function add()
									{
											window.open('${base}/wjgl/twjwxsj!input.action');
									}
									
									$('#query_form').pfwpage({
										page_button:[
											{b_name:'新增',b_function:add,bclass:'btn green',bicon:'icon-pencil'},
											{b_name:'删除',opttype:'delete',bclass:'btn red',bicon:'icon-trash',b_url:'${base}/wjgl/twjwxsj!delete.action',confirmStr:'是否确认删除？',paramName:'checks',selectNum:'^[0-9]*[1-9][0-9]*$'}
										],
										page_col:
										[
											 //在这里编写结果集显示栏目名称，例：{col_name:'xxxx'};最后一列注意不能有","号
											  	{col_name:'时间类型'},
											  	{col_name:'开始日期'},
											  	{col_name:'结束日期'},
											  	{col_name:'开始时间'},
											  	{col_name:'结束时间'},
											  	{col_name:'备注'}
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