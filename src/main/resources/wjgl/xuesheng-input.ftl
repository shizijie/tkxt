<#include '/macro/crud-metro-page3-nowebpst.ftl' >

<#if id?exists>
	<#assign extPosition='修改'/>
<#else>
	<#assign extPosition='新增'/>
</#if>

<@crudmetropage3>
	
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
						<form id="save_inputForm" class="form-horizontal  form-bordered" action="${base}/wjgl/xuesheng!save.action" method="post">
							<@s.token />
							<#if id?exists>
								<input type="hidden" name="id" value="${id}"/>
							</#if>
							<!-- 在这里编写输入的元素 -->
							<div class="control-group">
								<label class="control-label">学号:<span class="required">*</span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="xh" name="xh" value="${xh!}" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">姓名:<span class="required">*</span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="xm" name="xm" value="${xm!}" maxlength="300" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">班级:<span class="required">*</span></label>
								<div class="controls">
									<select id="bjid.id" name="bjid.id" class="m-wrap span10">
										<option value="">==请选择==</option>
										<#list bjlist as obj>
											<option value="${obj.id}" <#if bjid?exists && bjid.id == obj.id>selected</#if>>${obj.bjmc}</option>
										</#list>
									</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">性别:</label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="xb" name="xb" value="${xb!}" maxlength="300" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">备注:</label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="bz" name="bz" value="${bz!}" maxlength="30" />
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
								$("#save_inputForm").validate({
									rules: {
										<!-- 在这里编写验证规则 -->	
										xh:{required:true},
										xm:{required:true},
										"bjid.id":{required:true}
									}
								});						
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