<#include "/macro/crud-metro-page3.ftl" >
<@crudmetropage3>

  <link href="${base}/scripts/treeTable/stylesheets/master.css" rel="stylesheet" type="text/css" />
  <script type="text/javascript" src="${base}/scripts/treeTable/javascripts/jquery.ui.js"></script>
  <link href="${base}/scripts/treeTable/stylesheets/jquery.treeTable.css" rel="stylesheet" type="text/css" />
  <script type="text/javascript" src="${base}/scripts/treeTable/javascripts/jquery.treeTable.js"></script>
  
	<#macro genTreeMenu children>
		<#if (children?exists&&children?size>0)>
			<#list children as obj>
				<tr title="${obj.id}" id="node-${obj.id}" <#if obj.fparent?exists >class="<#if (obj.children?exists&&obj.children?size>1)>parent</#if> child-of-node-${obj.fparent.id}"</#if>>
					<td>${obj.typeName?if_exists}</td>
					<td>${obj.remark?if_exists}</td>
					<td><#if obj.fparent?exists>${obj.fparent.typeName?if_exists}</#if></td>
					<td>
						<a class="btn mini purple" href="javascript:;" onclick="window.open('${base}/wjgl/jx_type!input.action?id=${obj.id}')"><i class="icon-edit"></i> 修改</a>
					</td>
				</tr>
				<#if obj.children?exists>
					<@genTreeMenu children=obj.children/>
				</#if>
			</#list>
		</#if>
	</#macro>

<form id="resource_form" name="resource_form" action="${base}/wjgl/jx_type.action" method="post">
	<div class="row-fluid">
		<div class="span12">
			<div class="row-fluid">
				
				<!--查询结果-->
				<div class="span12">
					<div class="portlet">
						<div class="portlet-title">
							<div class="caption"><i class="icon-reorder"></i>机械制图题库分类管理</div>
							<div class="actions" id="pagemenubutton" ></div>
						</div>
						<div class="portlet-body">
						
							<table id="resourceTable">
								<thead>
									<tr style="background-color: #E8F5F7">
										<th>名称</th>
										<th>备注</th>										
										<th>上级节点</th>
										<th>操作</th>
									</tr>
								</thead>
								<#if menus?exists>
								<tbody>
								<#list menus as obj>
									<tr title="${obj.id}" id="node-${obj.id}" <#if obj.fparent?exists >class="<#if (obj.children?exists&&obj.children?size>1)>parent</#if> child-of-node-${obj.fparent.id}"</#if>>
										<td>${obj.typeName?if_exists}</td>									
										<td>${obj.remark?if_exists}</td>
										<td><#if obj.fparent?exists>${obj.fparent.typeName?if_exists}</#if></td>
										<td>
											<a class="btn mini purple" href="javascript:;" onclick="window.open('${base}/wjgl/jx_type!input.action?id=${obj.id}')"><i class="icon-edit"></i> 修改</a>
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
									window.open('${base}/wjgl/jx_type!input.action');
								}
								
								$(document).ready(function(){
									$('#resource_form').pfwpage({
										page_button:[
										{b_name:'新增',b_function:add,bclass:'btn green',bicon:'icon-pencil'},
										{b_name:'批量删除',opttype:'delete',bclass:'btn red',bicon:'icon-trash',confirmStr:'是否确定删除？',b_url:'${base}/wjgl/jx_type!delete.action',paramName:'checks',selectNum:'^[0-9]*[1-9][0-9]*$'}
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