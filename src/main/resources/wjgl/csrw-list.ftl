<#include '/macro/crud-metro-page3.ftl' >

<@crudmetropage3>

	<form id="query_form" name="query_form" action="${base}/wjgl/csrw.action" method="post">
	
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
									任务名称
									<input type="text" class="m-wrap span12" id="filter_LIKES_rwmc" name="filter_LIKES_rwmc"  value="${Parameters['filter_LIKES_rwmc']?if_exists}" maxlength="100"/>
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
									  		${obj.rwmc!}
									  	</td>
									  	<td>
									  		${obj.fafl?if_exists.flmc!}
									  	</td>
									  	<td>
									  		${obj.kssj!}
									  	</td>
									  	<td>
									  		${obj.jssj!}
									  	</td>
									  	<td>
									  		${obj.kssc!}
									  	</td>
									  	<td>
									  		${obj.ksbjmcs!}
									  	</td>
									  	<td>
									  		<#if obj.zjxx??>
									  		${obj.zjxx!}
									  		<br><br>
									  		</#if>
									  		<#if obj.jxInfo??>
									  		机械制图：<br>
									  		<#list obj.jxInfo?if_exists?split(",") as lx>
									  			<#if (lx?substring(0,lx?index_of(":"))?length>=8)>
										  			${lx?substring(0,lx?index_of(":"))?substring(0,8)}
										  		<#else>
										  			${lx?substring(0,lx?index_of(":"))}
									  			</#if>
									  				${lx?substring(lx?index_of(":"),lx?length)}<br>
									  		</#list>
									  		</#if>
									  		<#if obj?if_exists.topicInfo?if_exists!="0">
									  		${obj.topicInfo!}
									  		</#if>
									  	</td>
									  	<td>
									  		<#if obj.score??>
									  		${obj.score!}
									  		<br><br>
									  		</#if>
									  		<#if obj.jxScore??>
									  		机械制图：<br>
									  		<#list obj.jxScore?if_exists?split(",") as lx>
									  			<#if (lx?substring(0,lx?index_of(":"))?length>=8)>
										  			${lx?substring(0,lx?index_of(":"))?substring(0,8)}
										  		<#else>
										  			${lx?substring(0,lx?index_of(":"))}
									  			</#if>
									  				${lx?substring(lx?index_of(":"),lx?length)}<br>
									  		</#list>
									  		</#if>
									  		<#if obj?if_exists.topicScore?if_exists!="0"&&obj?if_exists.topicScore?if_exists!="">
									  		综合题：
									  		${obj.topicScore!}
									  		</#if>
									  	</td>
										 <td>
									 		<a class="btn mini purple" href="javascript:;" onclick="window.open('${base}/wjgl/csrw!input.action?id=${obj.id}')"><i class="icon-edit"></i> 修改</a>
									 		<!--
									 		<a class="btn mini blue" href="javascript:;" onclick="seflg('${obj.id}');$('#zdzjModal').modal({backdrop: 'static', keyboard: false});"><i class="icon-share"></i> 开始组卷</a>
									 		<a class="btn mini green" href="javascript:;" onclick="window.open('${base}/wjgl/csrw!sdzj.action?id=${obj.id}')"><i class="icon-hand-up"></i> 手动组卷</a>
									 		-->
									 		<a class="btn mini blue" href="javascript:;" onclick="window.open('${base}/wjgl/csrw!startPaper.action?id=${obj.id}')"><i class="icon-share"></i> 开始组卷</a>
									 		<a class="btn mini" href="javascript:;" onclick="window.open('${base}/wjgl/csrw!dysj.action?id=${obj.id}')"><i class="icon-print"></i> 打印试卷</a>
										 </td>
									</tr>
								</#list>
								</table>									
	
								<div style="display: none;" tabindex="-1" class="modal hide fade in" id="taotizjModal"  aria-hidden="false">
									<div class="modal-body" style="height:80%">
										<table id="result_page_table" class="table table-striped table-bordered table-advance table-hover">
											<thead>
												<tr>
													<th>名称</th>
													<th>题量</th>
												</tr>
											</thead>
											<tbody id="taotizjtable">
												
											</tbody>
										</table>
									</div>
									<div class="modal-footer">
								        <a href="javascript:;"  class="btn" data-dismiss="modal">关闭</a>
								    </div>
								</div>
								
								<div style="display: none;" tabindex="-1" class="modal hide fade in" id="zdzjModal"  aria-hidden="false">
									<div class="modal-body" style="height:80%">
										<table class="table table-bordered">
											<thead style="background-color: #E8F5F7">
												<tr>
													<th rowspan="2">题型</th>
													<th>难易程度</th>
													<th>题目来源</th>
													<th>验证信息</th>
												</tr>
												<tr>
													<th>难易程度</th>
													<th>题目来源</th>
													<th>验证信息</th>
												</tr>
											</thead>
											
											<tr id="dxt">
												<td>单选题</td>
											</tr>
											<tr id="pdt">
												<td>判断题</td>
											</tr>
											<tr id="tkt">
												<td>填空题</td>
											</tr>
											<tr id="jst">
												<td>计算题</td>
											</tr>
											<tr id="dtt">
												<td>读图题</td>
											</tr>
											<tr id="zht">
												<td>综合题</td>
											</tr>
											<tr id="hlsjt">
												<td>回路设计题</td>
											</tr>
										</table>
										<!--
										<center>
											<br>
											<br>
											单选题数量：<input type="text" name="dxtsl" id="dxtsl">
											<br>
											<br>
											判断题数量：<input type="text" name="pdtsl" id="pdtsl">
											<br>
											<br>
											<a href="javascript:;" onclick="autoZj()" class="btn green big"><i class="icon-random"></i> 开始自动组卷</a>
										</center>
										-->
									</div>
									<div class="modal-footer">
								        <a href="javascript:;"  class="btn" data-dismiss="modal">关闭</a>
								    </div>
								</div>
								
								<style>
									#zdzjModal
									{
										width: 1000px;
										height:500px;
										margin: 0 0 0 -370px; 
									}
								</style>
	
	
								<script type="text/javascript">
									function Test1(){
										$.post("${base}/wjgl/csrw!test.action",{},function(data){});
									}
								
									var flgid = "";
									
									function seflg(id)
									{
										flgid = id;
									}
									function add()
									{
											window.open('${base}/wjgl/csrw!input.action');
									}
									function autoZj()
									{
										var dxtsl = $("#dxtsl").val();
										var pdtsl = $("#pdtsl").val();
										if(dxtsl != "")
										{
											if(isNaN(dxtsl))
											{
												alert("单选题数量请输入数字");
												return;
											}
										}else{
											alert("单选题数量不能为空");
											return;
										}
										if(pdtsl != "")
										{
											if(isNaN(pdtsl))
											{
												alert("单选题数量请输入数字");
												return;
											}
										}else{
											alert("判断题数量不能为空");
											return;
										}
										$.ajax({
										   type: "POST",
										   url: "${base}/wjgl/csrw!autoZj.action",
										   data: "dxtsl="+dxtsl+"&pdtsl="+pdtsl+"&csrwid="+flgid,
										   success: function(msg){
										   		alert(msg);
										    	//$("#zdzjModal").hide();
										    	query_form.submit();
										    	$("#zdzjModal").modal("hide");
										    	//window.close();
										   },
										   error:function(){
										   		alert("保存存时出错了！");
										   }
										});
									}
									$('#query_form').pfwpage({
										page_button:[
											{b_name:'新增',b_function:add,bclass:'btn green',bicon:'icon-pencil'},
											{b_name:'删除',opttype:'delete',bclass:'btn red',bicon:'icon-trash',b_url:'${base}/wjgl/csrw!delete.action',confirmStr:'是否确认删除？',paramName:'checks',selectNum:'^[0-9]*[1-9][0-9]*$'}
										],
										page_col:
										[
											 //在这里编写结果集显示栏目名称，例：{col_name:'xxxx'};最后一列注意不能有","号
											  	{col_name:'任务名称'},
											  	{col_name:'课程'},
											  	{col_name:'开始时间'},
											  	{col_name:'结束时间'},
											  	{col_name:'考试时长'},
											  	{col_name:'考试班级'},
											  	{col_name:'题量'},
											  	{col_name:'分值'},
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