<#include '/macro/crud-metro-page3-nowebpst.ftl' >

<#if id?exists>
	<#assign extPosition='修改题目信息'/>
<#else>
	<#assign extPosition='创建题目信息'/>
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
						<form id="save_inputForm" name="save_inputForm" class="form-horizontal  form-bordered" action="${base}/wjgl/jxtm!save.action" method="post" enctype="multipart/form-data">
							<@s.token />
							<#if id?exists>
								<input type="hidden" name="id" value="${id}"/>
							</#if>
							<!-- 在这里编写输入的元素 -->
							<div class="control-group">
								<label class="control-label">题目类型:<span class="required">*</span></label>
								<div class="controls">
									<select data-placeholder=" " class="chosen span10" tabindex="-1" id="type.id"  name="type.id" >
														<option value=""></option>
														<#list allfl as kc>
														<#if (kc.children?size>0)>
																<#list kc.children as fp>
																	<optgroup label="${kc.typeName!}--${fp.typeName!}" >
																		<#if (fp.children?size>0)>
																		<#if !id?exists>
																		<#list fp.children as ch>
																			<option value="${ch.id}" >${ch.typeName!}</option>
																		</#list>
																		<#else>
																		<#list fp.children as ch>
																			<option value="${ch.id}" <#if type?if_exists.id?if_exists==ch.id>selected</#if>>${ch.typeName!}</option>
																		</#list>
																		</#if>
																		</#if>
																</#list>
														</#if>
																 </optgroup>
														</#list>
									</select>
								</div>
							</div>
							
							<!--
							<div class="control-group">
								 <label class="control-label">题目类型:<span class="required">*</span></label>
								 <div class="controls"> <#assign txid=entity.txid?if_exists.id?if_exists>
										<select class="chosen span10" tabindex="-1" data-placeholder=" " name="txid.id" id="txid" >
								      		<option value="">==全部==</option>
								      		<#list menus as kc>
								      			<option value="${kc.id}" <#if txid==kc.id>selected</#if>>${kc.txmc!}</option>
								      		</#list>
								      	</select>
							      	</div>
							</div> 
							-->
							<div class="control-group">
									<label class="control-label">题目描述和图片 :</label>
									<div class="controls">
										<input type="text" id="tmms" name="tmms" class="span10 m-wrap" value="${tmms?if_exists}">
										<p/><p/>
											<@s.file id="wttpFile" name="wttpFile"  style="width:500"/>
											&nbsp;&nbsp;&nbsp;&nbsp;
											已上传图标：<a href="${base}/userfiles/kjzt/wtimg/${entity.tmFileName!}" target="_blank">${entity.tmFileName!}</a>
									</div>
							</div>
												
												
							<div class="row-fluid">
								<div class="span12">
									<div class="form-actions">
										<center>
											<button class="btn blue big" type="submit" id="commit" name="commit"  >
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
										"txid.id":{required:true},
										"type.id":	{required:true}	
									}
								});	
								$(".chosen").each(function () {
						            $(this).chosen({
						                allow_single_deselect: $(this).attr("data-with-diselect") === "1" ? true : false,
						                no_results_text: "没有找到",
                                      	display_disabled_options:true
						            });
						        });						
							});	
							
							function cmt(){
								var wtlx = $("#txid").val();	
									if (wtlx =="" || wtlx == null)  
		                            {  
		                                alert("请选择问题类型.");  
		                                return false;  
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