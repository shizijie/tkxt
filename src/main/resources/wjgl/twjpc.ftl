<#include "/macro/crud-metro-page3.ftl" >

<@crudmetropage3>

<form id="user_form" action="${base}/wjgl/twjpc.action" method="post">
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
								<div class="left">
									批次名称
		      						<input type="text" class="m-wrap span12" id="filter_LIKES_pzmc" name="filter_LIKES_pzmc"  value="${Parameters['filter_LIKES_pzmc']?if_exists}"/>
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
										<tr title="${obj.id}">
											<td>
												 ${obj.pzmc?if_exists}
											</td>
											<td>
												${obj.kssj?if_exists}
											</td>
											<td>
												${obj.jssj?if_exists}
											</td>
											<td>
												<#list obj.flist as tmpfa>
													${tmpfa.famc!};
												</#list>
											</td>		
											<td>
												<center>
													<a class="btn mini purple" href="javascript:;" onclick="window.open('${base}/wjgl/twjpc!input.action?id=${obj.id!}')" ><i class="icon-edit"></i> 修改</a>
													&nbsp;&nbsp;&nbsp;
													<a class="btn mini purple" href="javascript:;" onclick="window.open('${base}/wjgl/twjpc!starwj.action?id=${obj.id!}')" ><i class="icon-edit"></i> 进入问卷</a>
												</center>
											</td>					
										</tr>
									</#list>
								</table>									

									<script type="text/javascript">
		
										function add(){
											window.open('${base}/wjgl/twjpc!input.action');
										}
										
										$(document).ready(function(){
													
											$('#user_form').pfwpage({
												page_button:[
													{b_name:'新增',b_function:add,bclass:'btn green',bicon:'icon-pencil'},
													{b_name:'删除',opttype:'delete',bclass:'btn red',bicon:'icon-trash',b_url:'${base}/wjgl/twjpc!delete.action',paramName:'checks',selectNum:'^[0-9]*[1-9][0-9]*$'}
												],
												page_col:[
													{col_name:'批次名称'},
													{col_name:'开始时间'},
													{col_name:'结束时间'},
													{col_name:'使用问卷'},
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
										});
								
										function previewResult(taskBatchID){
											var url="${base}/wjgl/task_batch!taskpreview.action?taskBatchID="+taskBatchID;
											var WindowStyle="dialogHeight:700px; dialogWidth:1000px; status=no; center:yes; resizable:yes";
											var ret=window.open(url);
										}		
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
