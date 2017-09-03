<#include '/macro/crud-metro-page3-nowebpst.ftl' >

<#if id?exists>
	<#assign extPosition='修改分类'/>
<#else>
	<#assign extPosition='创建分类'/>
</#if>

<@crudmetropage3>
<link href="${base}/scripts/treeTable/stylesheets/master.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/scripts/treeTable/javascripts/jquery.ui.js"></script>
<link href="${base}/scripts/treeTable/stylesheets/jquery.treeTable.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/scripts/treeTable/javascripts/jquery.treeTable.js"></script>
	
		<div class="row-fluid">
			<div class="span12">
				<div class="row-fluid">
				<!--查询结果-->
				<div class="span12">
					<div class="portlet box green">
						<div class="portlet-title">
							<div class="caption"><i class="icon-edit"></i>
								${extPosition!}
							</div>
							<div class="actions" id="pagemenubutton" ></div>
						</div>
						<div class="portlet-body form">
						
						<!--内容表单-->
						<#macro genTreeMenu children>
							<#if (children?exists&&children?size>0)>
								<#list children as obj>
									<tr title="${obj.id}" id="node-${obj.id}" <#if obj.fparent?exists >class="<#if (obj.children?exists&&obj.children?size>1)>parent</#if> child-of-node-${obj.fparent.id}"</#if>>
										<td>${obj.typeName?if_exists}</td>
										<td>${obj.remark?if_exists}</td>
										<td>
											<#if obj.jdtype?exists && obj.jdtype != "3">
												<label class="radio">
													<input type="radio" name="fparent.id" id="fparent.id" value="${obj.id}" <#if (fparent?exists&&fparent.id?exists&&obj.id=fparent.id)>checked</#if>>
												</label>
											<#else>
												X
											</#if>
										</td>
									</tr>
									<#if obj.children?exists>
										<@genTreeMenu children=obj.children/>
									</#if>
								</#list>
							</#if>
						</#macro>
						
						<form id="save_inputForm" class="form-horizontal  form-bordered" action="${base}/wjgl/jx_type!save.action" method="post">
							<@s.token />
							<#if id?exists>
								<input type="hidden" name="id" value="${id}"/>
							</#if>
							<!-- 在这里编写输入的元素 -->
							<div class="control-group">
								<label class="control-label">分类:<span class="required">*</span></label>
								<div class="controls">
									<table id="resourceTable_all" border="1">
										<thead>
											<tr style="background-color: #E8F5F7">
												<th>名称</th>
												<th>备注</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<tr id="node-root">
												<td>学期</td>
												<td>&nbsp;</td>
												<td>
													<label class="radio">
														<input type="radio" name="fparent.id" id="fparent.id" value="" <#if !(fparent?exists&&fparent.id?exists)>checked</#if>>
													</label>
												</td>
											</tr>
										<#if menus?exists>
										<#list menus as obj>
											<tr title="${obj.id}" id="node-${obj.id}" <#if obj.fparent?exists >class="<#if (obj.children?exists&&obj.children?size>1)>parent</#if> child-of-node-${obj.fparent.id}"
												<#else>
													class="<#if (obj.children?exists&&obj.children?size>1)>parent</#if> child-of-node-root"
												</#if>>
												<td>${obj.typeName?if_exists}</td>
												<td>${obj.remark?if_exists}</td>
												<td>												
													<#if obj.jdtype?exists>
														<label class="radio">
															<input type="radio" name="fparent.id" id="fparent.id" value="${obj.id}" <#if (fparent?exists&&fparent.id?exists&&obj.id=fparent.id)>checked</#if>>
														</label>
													<#else>
														X
													</#if>
												</td>
											</tr>
											<#if obj.children?exists>
												<@genTreeMenu children=obj.children/>
											</#if>
										</#list>
										</#if>
										</tbody>
									</table>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">类别名称:<span class="required">*</span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="typeName" name="typeName" value="${typeName!}" maxlength="100" />
								</div>
							</div>

							<div class="control-group">
								<label class="control-label">备注:<span class="required">*</span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="remark" name="remark" value="${remark!}" maxlength="100" />
								</div>
							</div>


							<div class="row-fluid">
								<div class="span12">
									<div class="form-actions">
										<center>
											<button class="btn blue big" type="submit" id="commit" name="commit" >
												<i class="icon-ok"></i> 提交
											</button>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<button class="btn big" type="button" onclick="javascript:window.close()" >
												<i class="icon-remove"></i> 关闭
											</button>
										</center>									
									</div>

								</div>
							</div>
						</form>																	
						<script type="text/javascript">
							$(document).ready(function() {
								//为inputForm注册validate函数
								$("#save_inputForm").validate({
									rules: {
										flmc: "required",
										
									}
								});
								
							$("#resourceTable_all").treeTable();
							});
						</script>
						<!--内容表单-->
						</div>
					</div>
				</div>
				<!--查询结果-->
				
				</div>
			</div>
		</div>
		
</@crudmetropage3>
