<#include "/macro/crud-metro-page3-nowebpst.ftl" >
<#if !id?exists>
	<#assign extPosition="新加课程/章节/知识点"/>
<#else>
	<#assign extPosition="新加课程/章节/知识点"/>
</#if>
<#assign dispJDLX={"1":"课程", "2":"章节", "3":"知识点"}>
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
										<td>${obj.flmc?if_exists}</td>
										<td>${dispJDLX[obj.jdtype]}</td>
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
						<form id="save_inputForm" class="form-horizontal form-bordered" action="${base}/wjgl/twjfafl!save.action" method="post">
							<@s.token />
							
							<input type="hidden" name="isjxzt" value="${isjxzt}"/>
							
							<#if id?exists && id != 0>
								<input type="hidden" name="id" value="${id}"/>
							</#if>
							
							<div class="control-group">
								<label class="control-label">父类:</label>
								<div class="controls">
									<table id="resourceTable_all" border="1">
										<thead>
											<tr style="background-color: #E8F5F7">
												<th>名称</th>
												<th>类型</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<tr id="node-root">
												<td>根</td>
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
												<td>${obj.flmc?if_exists}</td>
												<td><#if obj.jdtype?exists>${dispJDLX[obj.jdtype]}</#if></td>
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
										</tbody>
									</table>											
								</div>
							</div>

							<div class="control-group">
								<label class="control-label">名称:<span class="required">*</span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap"  id="flmc" name="flmc" maxlength="40" value="${flmc?if_exists}"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">显示顺序:<span class="required">*</span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="xssx" name="xssx" maxlength="80" value="${xssx?if_exists}"/>
								</div>
							</div>						
							<input type="hidden" name="sfyx" value="1">						    							
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

	
	<script type="text/javascript">
		
	</script>
	
</@crudmetropage3>



