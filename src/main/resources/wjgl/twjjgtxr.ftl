<#include "/macro/crud-metro-page3.ftl" >

<@crudmetropage3>

<form id="query_form"  action="${base}/wjgl/twjjgtxr.action" name="query_form"  method="post">
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
									所属分类
		      						<select class="m-wrap span12" name="filter_EQI_faflid.id" id="filter_EQS_faflid.id" >
							      		<option value="">==全部==</option>
							      		<#list menus as ojb>
							      			<option value="${ojb.id}"   <#if Parameters['filter_EQI_faflid.id']?exists && Parameters['filter_EQI_faflid.id']="${ojb.id}">selected</#if>>${ojb.flmc}</option>
							      		</#list>
							      	</select>
								</div>
								<div class="left">
									班级
		      						<select class="m-wrap span12" name="banji" id="banji" >
							      		<option value="">==全部==</option>
							      		<#list listbj as ojb>
							      			<option value="${ojb.id}"   <#if Parameters['banji']?exists && Parameters['banji']="${ojb.id}">selected</#if>>${ojb.bjmc}[${ojb.nj}]</option>
							      		</#list>
							      	</select>
								</div>
								<div class="left">
									做题人
									<input type="text" class="m-wrap span12" name="filter_LIKES_txr_OR_sjh" id="filter_LIKES_txr_OR_sjh" value="${Parameters['filter_LIKES_txr_OR_sjh']?if_exists}" >
								</div>
								<p>
								<center>
									<button class="btn blue" type="submit">查询 <i class="m-icon-swapright m-icon-white"></i></button>
									<button class="btn" type="button" id="reset">重置 </button>
								</center>
								</p>
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
												 ${obj.sjh?if_exists}[${obj.xuesheng?if_exists.xh?if_exists}]
											</td>
											<td>
												${obj.xuesheng?if_exists.bjid?if_exists.bjmc?if_exists}
											</td>
											<td>
												${obj.faflid?if_exists.flmc?if_exists}
											</td>
											<td>
												${obj.dxsj?if_exists}
											</td>
											<td>
												${obj.tmzs?if_exists}
											</td>
											<td>
												${obj.zqsl?if_exists}
											</td>
											<td>
												${obj.cwsl?if_exists}
											</td>	
											<td>
												${obj.tmzs?if_exists-obj.zqsl?if_exists-obj.cwsl?if_exists}
											</td>
											<td>
												${obj.grade!}													
											</td>
											<td>
												<#if obj.csrw?exists>
													任务测试
												<#else>
													随机测试
												</#if>
											</td>
											<td>
												<center>
													<a class="btn mini purple" href="javascript:;" onclick="window.open('${base}/wjgl/twjjgtxr!txrwj.action?id=${obj.id!}')"><i class="icon-edit"></i> 查看做题情况</a>
												</center>
											</td>					
										</tr>
									</#list>
								</table>									

									<script type="text/javascript">
									
										$("#reset").click(function(){
											$("input[name^='filter_']").val("");
											$("select[name^='filter_']").val("");
										});
										
										$('#query_form').pfwpage({
										
											page_button:[
												{b_name:'删除',opttype:'delete',bclass:'btn red',bicon:'icon-trash',b_url:'${base}/wjgl/twjjgtxr!delete.action',confirmStr:'是否确认删除？',paramName:'checks',selectNum:'^[0-9]*[1-9][0-9]*$'}
											],
											page_col:[
												{col_name:'做题人',property:'txr',sort:true},
												{col_name:'学号'},
												{col_name:'测试课程'},
												{col_name:'做题时间',property:'dxsj',sort:true},
												{col_name:'题目总数'},
												{col_name:'正确'},
												{col_name:'错误'},
												{col_name:'未做答'},
												{col_name:'得分'},
												{col_name:'方式'},
												{col_name:'',width:'20%'}
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
