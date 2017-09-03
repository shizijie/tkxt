<#include '/macro/crud-metro-page3-nowebpst.ftl' >

<#if id?exists>
	<#assign extPosition='修改wjgl'/>
<#else>
	<#assign extPosition='创建wjgl'/>
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
						<form id="save_inputForm" class="form-horizontal  form-bordered" action="${base}/wjgl/topic_content!save.action" method="post">
							<@s.token />
							<#if id?exists>
								<input type="hidden" name="id" value="${id}"/>
							</#if>
							<!-- 在这里编写输入的元素 -->
							<div class="control-group">
								<label class="control-label">类型:<span class="required">*</span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="topicType" name="topicType" value="${topicType!}" maxlength="1" />
								</div>
							</div>

							<div class="control-group">
								<label class="control-label">序号:<span class="required">*</span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="serialNumber" name="serialNumber" value="${serialNumber!}" maxlength="2" />
								</div>
							</div>

							<div class="control-group">
								<label class="control-label">内容图片:<span class="required">*</span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="topicPicture" name="topicPicture" value="${topicPicture!}" maxlength="30" />
								</div>
							</div>

							<div class="control-group">
								<label class="control-label">正确答案:<span class="required">*</span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="rightAnswers" name="rightAnswers" value="${rightAnswers!}" maxlength="100" />
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
