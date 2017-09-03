<#include "/macro/crud-metro-page3.ftl" >

<#assign dispWtlx={"1":"单选题", "2":"多选题", "3":"判断题","5":"填空题","6":"计算题","7":"读图题","8":"综合题","9":"回路设计题"}>
<#assign dispWtjfgz={"1":"根据正确答案的分值计总分", "2":"(多选题)全答对得几分" ,"3":"(多选题)根据选择的答案正确数目，选对几个得几分","4":"填空单位计分","5":"人工主观计分"}>
<#assign dispNYCD={"A":"容易", "B":"一般" ,"C":"中等","D":"较难","E":"困难","NULL":"无"}>


<@crudmetropage3>

<form id="query_form" name="query_form" action="${base}/wjgl/twjwt.action" method="post">
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
									课程章节
		      						<select class="m-wrap span12" name="filter_EQI_faflid.id" id="filter_EQI_faflid.id" >
							      		<option value="">==全部==</option>
							      		<#list menus as ojb>
							      			<optgroup label="${ojb.flmc}"></optgroup>
							      			<#list ojb.children as ch>
							      			<option value="${ch.id}"   <#if Parameters['filter_EQI_faflid.id']?exists && Parameters['filter_EQI_faflid.id']="${ch.id}">selected</#if>>&nbsp;&nbsp;&nbsp;&nbsp;${ch.flmc}</option>
							      			</#list>
							      		</#list>
							      	</select>
								</div>
								<div class="left">
									题目
									<input class="m-wrap span12" type="text" name="filter_LIKES_wtmc"  value="${Parameters['filter_LIKES_wtmc']?if_exists}"/>
								</div>								
								<div class="left">
									状态
									<select name="filter_EQS_sfyx" class="m-wrap span12">
							      		<option value=""  	<#if Parameters.filter_EQS_sfyx?exists && Parameters.filter_EQS_sfyx="">selected</#if>>全部</option>
										<option value="1"   <#if Parameters.filter_EQS_sfyx?exists && Parameters.filter_EQS_sfyx="1">selected</#if>>有效</option>
										<option value="0"   <#if Parameters.filter_EQS_sfyx?exists && Parameters.filter_EQS_sfyx="0">selected</#if>>无效</option>
									</select>
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
												<#if obj.faflid?exists>
													<font style="font-weight:bold">${obj.faflid?if_exists.fparent?if_exists.flmc}</font>&nbsp;&nbsp;&nbsp;${obj.faflid.flmc}
												</#if>
											</td>
											<td>
												<#if obj.wtlx=="图片题">
													<img src='${base}/userfiles/kjzt/wtimg/${obj.wtmcFileName!}' />
												<#else>
													${obj.wtmc?if_exists}
												</#if>
												 
											</td>
											<td>
												<#if obj.wtlx?exists>
													${obj.wtlx!}
												</#if>
											</td>
											
											<td>
												<#if obj.nycd?exists>
													${dispNYCD[obj.nycd]}
												</#if>
											</td>
											<td>
												<center>
													<a class="btn mini purple" href="javascript:;" onclick="window.open('${base}/wjgl/twjwt!input.action?id=${obj.id!}')"><i class="icon-edit"></i> 修改</a>
													<#if obj.xzlx?exists && obj.xzlx!='3'>
														 <a class="btn mini purple" href="javascript:;" onclick="formReset();user_form.action='${base}/wjgl/supervision_item_answer.action?supervisionItemId=${obj.id}';"><i class="icon-edit"></i> 维护答案</a>
													</#if>		
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
										
										function add(){
											window.open('${base}/wjgl/twjwt!input.action');
										}
										
										function inport(){
											window.open('${base}/wjgl/twjwt!tmimp.action');
										}
									
											
										$('#query_form').pfwpage({
											
											page_button:[
												{b_name:'新增',b_function:add,bclass:'btn green',bicon:'icon-pencil'},
												{b_name:'题目导入',b_function:inport,bclass:'btn blue',bicon:'icon-stackexchange'},
												{b_name:'删除',opttype:'delete',bclass:'btn red',bicon:'icon-trash',b_url:'${base}/wjgl/twjwt!delete.action',confirmStr:'问题、答案将一起删除，是否确定删除？',paramName:'checks',selectNum:'^[0-9]*[1-9][0-9]*$'}
											],
											page_col:[
												{col_name:'课程',width:'25%'},
												{col_name:'题目',property:'wtmc'},
												{col_name:'题型',width:'15%'},
												
												{col_name:'难易程度',width:'5%'},
												{col_name:'操作',width:'10%'}	
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

