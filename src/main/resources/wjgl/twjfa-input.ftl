<#include "/macro/crud-metro-page3-nowebpst.ftl" >
<#if id?exists>
	<#assign extPosition="修改问卷"/>
<#else>
	<#assign extPosition="新增问卷"/>
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
						
						<form id="save_inputForm" class="form-horizontal  form-bordered" action="${base}/wjgl/twjfa!save.action" enctype="multipart/form-data">
							<@s.token />
							<#if id?exists>
								<input type="hidden" name="id" value="${id}"/>
							</#if>
							
							<div class="control-group">
								<label class="control-label">代码:<span class="required">*</span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="fadm" name="fadm" maxlength="20" value="${fadm?if_exists}"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">方案名称 :<span class="required">*</span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="famc" name="famc" maxlength="20" value="${famc?if_exists}"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">是否有效:</label>
								<div class="controls">
									<@s.select name="sfyx" id="sfyx" list=r"#{'1':'有效','0':'无效'}" value="${sfyx?if_exists}" cssClass="span10 m-wrap"/>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">问卷表头文字信息:</label>
								<div class="controls">
									<textarea  id="btconFck" name="btcontent" class="span10 m-wrap" >${btcontent?if_exists}</textarea>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">问卷表尾文字信息:</label>
								<div class="controls">
									<textarea  id="bwconFck" name="bwcontent" class="span10 m-wrap" >${bwcontent?if_exists}</textarea>
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

						<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
						<script type="text/javascript" src="../ckfinder/ckfinder.js"></script>
					
						<script type="text/javascript">
						
							CKEDITOR.replace( 'btcontent', 
							{ 
								toolbar : 'Basic',
								fullPage : true,
								filebrowserBrowseUrl : '${base}/ckfinder/ckfinder.html', 
								filebrowserImageBrowseUrl : '${base}/ckfinder/ckfinder.html?type=Images',
								filebrowserFlashBrowseUrl : '${base}/ckfinder/ckfinder.html?type=Flash',
								filebrowserUploadUrl : '${base}/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files',
								filebrowserImageUploadUrl : '${base}/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images',
								filebrowserFlashUploadUrl : '${base}/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash'
							});
							CKEDITOR.replace( 'bwcontent', 
							{ 
								toolbar : 'Basic',
								fullPage : true,
								filebrowserBrowseUrl : '${base}/ckfinder/ckfinder.html', 
								filebrowserImageBrowseUrl : '${base}/ckfinder/ckfinder.html?type=Images',
								filebrowserFlashBrowseUrl : '${base}/ckfinder/ckfinder.html?type=Flash',
								filebrowserUploadUrl : '${base}/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files',
								filebrowserImageUploadUrl : '${base}/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images',
								filebrowserFlashUploadUrl : '${base}/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash'
							});
							
							
							$(document).ready(function() {
								$("#save_inputForm").validate({
									rules: {
										fadm:{required:true,maxlength:20},
										famc:{required:true,maxlength:100},
										bz:{maxlength:20}
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
