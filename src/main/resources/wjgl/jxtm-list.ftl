<#include '/macro/crud-metro-page3.ftl' >

<@crudmetropage3>
	<form id="query_form" action="${base}/wjgl/jxtm.action" method="post">
	
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
									题库分类
		      						<select class="chosen span12" tabindex="-1" data-placeholder=" " name="filter_EQI_type.id" id="filter_EQI_type.id" >
							      		<option value="">==全部==</option>
							      		<#list allfl as kc>
												<#if (kc.children?size>0)>
													<#list kc.children as ch>
							      					<optgroup label="${kc.typeName!}-${ch.typeName!}">
														<#if (ch.children?size>0)>
														<#list ch.children as ren>
														<option value="${ren.id}" <#if Parameters['filter_EQI_type.id']?exists && Parameters['filter_EQI_type.id']="${ren.id}"> selected</#if>>${ren.typeName!}</option>
														</#list>
														</#if>
													</#list>
												</#if>
							      		</#list>
							      	</select>
								</div>
								
								<!--
						  		<div class="left">
									题型
		      						<select class="chosen span12" tabindex="-1" data-placeholder=" " name="filter_EQI_txid.id" id="filter_EQI_txid.id" >
							      		<option value="">==全部==</option>
							      		<#list menus as kc>
							      			<option value="${kc.id}" <#if Parameters['filter_EQI_txid.id']?exists && Parameters['filter_EQI_txid.id']="${kc.id}"> selected</#if>>${kc.txmc!}</option>
							      		</#list>
							      	</select>
								</div>
								-->
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
											${obj.type?if_exists.fparent?if_exists.fparent?if_exists.typeName?if_exists}-${obj.type?if_exists.fparent?if_exists.typeName?if_exists}
										</td>
										<td>
											${obj.type?if_exists.typeName?if_exists}
										</td>
									  	<td>
									  		<img src="../userfiles/kjzt/wtimg/${obj.tmFileName?if_exists}" style="width:219.75pt;height:178.3pt"/>
									  	</td>
										 <td>
									 		<a class="btn mini purple" href="javascript:;" onclick="window.open('${base}/wjgl/jxtm!input.action?id=${obj.id}')"><i class="icon-edit"></i> 修改</a>
										 </td>
									</tr>
								</#list>
								</table>									
	
								<script type="text/javascript">
								
									function add()
									{
											window.open('${base}/wjgl/jxtm!input.action');
									}
									
									$('#query_form').pfwpage({
										page_button:[
											{b_name:'新增',b_function:add,bclass:'btn green',bicon:'icon-pencil'},
											{b_name:'删除',opttype:'delete',bclass:'btn red',bicon:'icon-trash',b_url:'${base}/wjgl/jxtm!delete.action',confirmStr:'是否确认删除？',paramName:'checks',selectNum:'^[0-9]*[1-9][0-9]*$'}
										],
										page_col:
										[
											 //在这里编写结果集显示栏目名称，例：{col_name:'xxxx'};最后一列注意不能有","号
											  	{col_name:'题目分类'},
											  	{col_name:'题目类型'},
											  	{col_name:'题目图片'},
											 	{col_name:'操作'}	
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