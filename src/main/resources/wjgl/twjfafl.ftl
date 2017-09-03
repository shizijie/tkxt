<#include "/macro/crud-metro-page3.ftl" >
<#assign dispJDLX={"1":"课程", "2":"章节", "3":"知识点"}>
<@crudmetropage3>

  <link href="${base}/scripts/treeTable/stylesheets/master.css" rel="stylesheet" type="text/css" />
  <script type="text/javascript" src="${base}/scripts/treeTable/javascripts/jquery.ui.js"></script>
  <link href="${base}/scripts/treeTable/stylesheets/jquery.treeTable.css" rel="stylesheet" type="text/css" />
  <script type="text/javascript" src="${base}/scripts/treeTable/javascripts/jquery.treeTable.js"></script>
  
	<#macro genTreeMenu children>
		<#if (children?exists&&children?size>0)>
			<#list children as obj>
				<tr title="${obj.id}" id="node-${obj.id}" <#if obj.fparent?exists >class="<#if (obj.children?exists&&obj.children?size>1)>parent</#if> child-of-node-${obj.fparent.id}"</#if>>
					<td>${obj.flmc?if_exists}</td>
					<td>${dispJDLX[obj.jdtype]}</td>
					<td><#if obj.fparent?exists>${obj.fparent.flmc?if_exists}</#if></td>
					<td>${obj.xssx?if_exists}</td>
					<td>
						<a class="btn mini purple" href="javascript:;" onclick="window.open('${base}/wjgl/twjfafl!input.action?id=${obj.id}')"><i class="icon-edit"></i> 修改</a>
					</td>
				</tr>
				<#if obj.children?exists>
					<@genTreeMenu children=obj.children/>
				</#if>
			</#list>
		</#if>
	</#macro>

<form id="resource_form" name="resource_form" action="${base}/wjgl/twjfafl.action" method="post">
<input type="hidden" name="isjxzt" value="${isjxzt}"/>
	<div class="row-fluid">
		<div class="span12">
			<div class="row-fluid">
				
				<!--查询结果-->
				<div class="span12">
					<div class="portlet">
						<div class="portlet-title">
							<div class="caption"><i class="icon-reorder"></i>课程知识信息列表</div>
							<div class="actions" id="pagemenubutton" ></div>
						</div>
						<div class="portlet-body">
						
							<table id="resourceTable">
								<thead>
									<tr style="background-color: #E8F5F7">
										<th>名称</th>
										<th>类型</th>										
										<th>上级节点</th>
										<th>显示顺序</th>
										<th>&nbsp;&nbsp;&nbsp;&nbsp;</th>
									</tr>
								</thead>
								<#if menus?exists>
								<tbody>
								<#list menus as obj>
									<tr title="${obj.id}" id="node-${obj.id}" <#if obj.fparent?exists >class="<#if (obj.children?exists&&obj.children?size>1)>parent</#if> child-of-node-${obj.fparent.id}"</#if>>
										<td>${obj.flmc?if_exists}</td>									
										<td><#if obj.jdtype?exists>${dispJDLX[obj.jdtype]}</#if></td>
										<td><#if obj.fparent?exists>${obj.fparent.flmc?if_exists}</#if></td>
										<td>${obj.xssx?if_exists}</td>		
										<td>

											<a class="btn mini purple" href="javascript:;" onclick="window.open('${base}/wjgl/twjfafl!input.action?id=${obj.id}')"><i class="icon-edit"></i> 修改</a>

										</td>
									</tr>
									<#if obj.children?exists>
										<@genTreeMenu children=obj.children/>
									</#if>
								</#list>
								</tbody>
								</#if>
							</table>
							
							<script type="text/javascript">
								function add(){
									<#if isjxzt=="" >
									window.open('${base}/wjgl/twjfafl!input.action');
									</#if>
									<#if isjxzt!="">
									window.open('${base}/wjgl/twjfafl!input.action?isjxzt=1');
									</#if>
								}
								
								$(document).ready(function(){
									$('#resource_form').pfwpage({
										page_button:[
										{b_name:'新增',b_function:add,bclass:'btn green',bicon:'icon-pencil'},
										{b_name:'批量删除',opttype:'delete',bclass:'btn red',bicon:'icon-trash',confirmStr:'是否确定删除？',b_url:'${base}/wjgl/twjfafl!delete.action',paramName:'checks',selectNum:'^[0-9]*[1-9][0-9]*$'}
										],
										page_table:'#resourceTable',
										page_able:false
									});
									
									$("#resourceTable").treeTable();

								    $("#resourceTable .file, #resourceTable .folder").draggable({
								      helper: "clone",
								      opacity: .75,
								      refreshPositions: true, // Performance?
								      revert: "invalid",
								      revertDuration: 300,
								      scroll: true
								    });
								    $("#resourceTable .folder").each(function() {
								      $($(this).parents("tr")[0]).droppable({
								        accept: ".file, .folder",
								        drop: function(e, ui) { 
								          $($(ui.draggable).parents("tr")[0]).appendBranchTo(this);
								        },
								        hoverClass: "accept",
								        over: function(e, ui) {
								          if(this.id != ui.draggable.parents("tr.parent")[0].id && !$(this).is(".expanded")) {
								            $(this).expand();
								          }
								        }
								      });
								    });		
								    							
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