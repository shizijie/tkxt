<#include '/macro/crud-metro-page3-nowebpst.ftl' >

<#if id?exists>
	<#assign extPosition='修改班级'/>
<#else>
	<#assign extPosition='创建班级'/>
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
						<form id="save_inputForm" class="form-horizontal  form-bordered" action="${base}/wjgl/banji!save.action" method="post">
							<@s.token />
							<#if id?exists>
								<input type="hidden" name="id" value="${id}"/>
							</#if>
							<!-- 在这里编写输入的元素 -->
							<div class="control-group">
								<label class="control-label">班级名称:<span class="required">*</span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="bjmc" name="bjmc" value="${bjmc!}" maxlength="300" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">年级:<span class="required">*</span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="nj" name="nj" value="${nj!}" maxlength="20" />
								</div>
							</div>
							<!--
							<div class="control-group">
								<label class="control-label">班主任:<span class="required">*</span></label>
								<div class="controls">
									<input type="text" id="cxstBzrgh" name="cxstBzrgh" value="" maxlength="16" class="span2 m-wrap" />&nbsp;&nbsp;&nbsp;&nbsp;
									<a href="javascript:void(-1)" class="btn blue icn-only" onclick="czbzr()">查 <i class="m-icon-swapright m-icon-white"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;
									<select name="bzr.id" id="bzrid"  class="span4 m-wrap">
										<option value="">==请选择==</option>
										<#if id?exists && bzr?exists && bzr.id?exists>
											<option value="${bzr?if_exists.id?if_exists}" selected="selected">${bzr?if_exists.xm?if_exists}[${bzr?if_exists.gh?if_exists}]</option>
										</#if>
									</select>
								</div>
							</div>
							-->
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
									}
								});						
							});	
							function czbzr() {
								var fbzrIds = new Array();
								$('#fstBzr option').each(function(){
									//alert($(this).val());
									
								});
								//return;
								var jsmc1 = $("#cxstBzrgh").val();
								if (jsmc1 != "") {
									$.post(
										"${base}/wjgl/jiaoshi!getJsJsonStr.action",
										{filter_LIKES_xm: jsmc1},
										function(data) {
											var jsonData = eval(data);
											var rtnLength = jsonData.length;
											var htmlVal="<option value=\"\">==请选择==</option>";
											for (var i = 0; i < rtnLength; i++) {
												htmlVal = htmlVal + "<option value=\""+jsonData[i].id+"\">" + jsonData[i].xm+ "["+jsonData[i].gh+"]" + "</option>";
											}
											$("#bzrid").empty();
											$("#bzrid").append(htmlVal);
										}
									);	
								}
							}
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