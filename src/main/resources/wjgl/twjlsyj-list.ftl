<#include '/macro/crud-metro-page3.ftl' >

<@crudmetropage3>
	<form id="query_form" action="${base}/wjgl/twjlsyj.action" method="post">
	
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
									帐号或姓名
									<input type="text" class="m-wrap span12" id="filter_LIKES_zh_OR_xm" name="filter_LIKES_zh_OR_xm"  value="${Parameters['filter_LIKES_zh_OR_xm']?if_exists}" maxlength="1"/>
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
									  		${obj.zh!}
									  	</td>
									  	<td>
									  		${obj.xm!}
									  	</td>
									  	<td>
									  		${obj.mm!}
									  	</td>
										 <td>
									 		<a class="btn mini purple" href="javascript:;" onclick="window.open('${base}/wjgl/twjlsyj!input.action?id=${obj.id}')"><i class="icon-edit"></i> 修改</a>
										 </td>
									</tr>
								</#list>
								</table>									
	
								<script type="text/javascript">
								
									function add()
									{
											window.open('${base}/wjgl/twjlsyj!input.action');
									}
									
									$('#query_form').pfwpage({
										page_button:[
											{b_name:'新增',b_function:add,bclass:'btn green',bicon:'icon-pencil'},
											{b_name:'删除',opttype:'delete',bclass:'btn red',bicon:'icon-trash',b_url:'${base}/wjgl/twjlsyj!delete.action',confirmStr:'是否确认删除？',paramName:'checks',selectNum:'^[0-9]*[1-9][0-9]*$'}
										],
										page_col:
										[
											 //在这里编写结果集显示栏目名称，例：{col_name:'xxxx'};最后一列注意不能有","号
											  	{col_name:'帐号'},
											  	{col_name:'姓名'},
											  	{col_name:'密码'},
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