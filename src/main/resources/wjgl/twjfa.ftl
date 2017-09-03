<#include "/macro/crud-metro-page3.ftl" >


<@crudmetropage3>

<form id="query_form" name="query_form" action="${base}/wjgl/twjfa.action" method="post">
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
									方案
		      						<input type="text" class="m-wrap span12" id="fa" name="filter_LIKES_fadm_OR_famc"  value="${Parameters['filter_LIKES_fadm_OR_famc']?if_exists}"/>
								</div>
								<center>
									<button class="btn blue" type="submit">查询 <i class="m-icon-swapright m-icon-white"></i></button>
									<button class="btn" type="button" id="rest">重置 </button>
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
												 ${obj.fadm?if_exists}
											</td>
											<td>
												${obj.famc?if_exists}
											</td>							
											<td>
												${dispYn[obj.sfyx]}
											</td>			
											<td>
												<center>
													<a class="btn mini purple" href="javascript:;" onclick="window.open('${base}/wjgl/twjfa!input.action?id=${obj.id!}')"><i class="icon-edit"></i> 修改</a>
													&nbsp;&nbsp;&nbsp;
													<a class="btn mini purple" href="javascript:;" onclick="window.open('${base}/wjgl/twjfa!dofafz.action?yfaid=${obj.id!}')"><i class="icon-copy"></i> 方案复制</a>
													&nbsp;&nbsp;&nbsp;
													<a class="btn mini purple" href="javascript:;" onclick="previewResult('${obj.id}')"><i class="icon-eye-open"></i> 问卷预览</a>
												</center>
											</td>					
										</tr>
									</#list>
								</table>									

									<script type="text/javascript">
									
										function add(){
											window.open('${base}/wjgl/twjfa!input.action');
										}
										
										function previewResult(faid)
										{
											window.open('${base}/wjgl/twjfa!preview.action?id='+faid);
										}
										
										$('#query_form').pfwpage({
											page_button:[
												{b_name:'新增',b_function:add,bclass:'btn green',bicon:'icon-pencil'},
												{b_name:'删除',opttype:'delete',bclass:'btn red',bicon:'icon-trash',b_url:'${base}/wjgl/twjfa!delete.action',paramName:'checks',selectNum:'^[0-9]*[1-9][0-9]*$'}
											],
											page_col:[
												{col_name:'方案代码',width:'20%'},
												{col_name:'方案名称',width:'20%'},
												{col_name:'是否有效',width:'20%'},
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
