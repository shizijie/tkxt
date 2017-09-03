<#include "/macro/crud-metro-page3-nowebpst.ftl" >

<@crudmetropage3>
	
	<div class="row-fluid">
		<div class="span12">
			<div class="row-fluid">

				<!--查询结果-->
				<div class="span12">
					<div class="portlet box green">
						<div class="portlet-title">
							<div class="caption"><i class="icon-edit"></i>
								学生批量导入
							</div>
							<div class="actions" id="pagemenubutton" ></div>
						</div>
						<div class="portlet-body form">
						
						<!--内容表单-->
						
						<form id="form_inputForm" name="form_inputForm" class="form-horizontal" action="${base}/wjgl/xuesheng!dotjimp.action" method="post" enctype="multipart/form-data">
						
							<div class="row-fluid">
								<div class="span1"></div>
								<div class="span10">
								
									<div class="control-group">
										<label class="control-label">导入文件:<span class="required">*</span></label>
										<div class="controls">
											<@s.file name="drfj" id="drfj" value="" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		      								<a href="${base}/wjgl/xuesheng!drportDate.action?doloadFlag=0" style="text-decoration:none;"><font color=red size=2>下载模板</font></a>
		      								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		      								<button class="btn blue" type="submit" id="commit" name="commit">
												<i class="icon-ok"></i> 执行导入
											</button>
											
										</div>
									</div>

									
									
								</div>
								<div class="span1"></div>
							</div>
							
							<div class="row-fluid">
								<div class="span1"></div>
								<div class="span10">
								
									<div class="alert alert-block alert-success fade in">
										<h3 class="alert-heading">导入结果：</h3>
										<p>
											导入成功数据有：${succont?if_exists} &nbsp;&nbsp;条
										</p>
										<p>
											导入失败数据有：${errcont?if_exists} &nbsp;&nbsp;条
										</p>
										
										<#if errList?exists>
											<p><h4>其中：</h4></p>
											<#list errList as obj>
												<p>${obj}</p>
											</#list>
										</#if>
										
									</div>
								
								</div>
								<div class="span1"></div>
							</div>
							
						</form>																	

						<script>
							$("#form_inputForm").validate({
								rules: {
									drfj:{required:true}
								}
							});
							function cmt(){
								var wtlx = $("#wtlx").val();
								if(typeof(wtlx) != "undefined" && wtlx == "1")
								{
									var i = 0
									$("select[name^='sfzqda']").each(function(){
										if($(this).val() == "1")
											i = i + 1;
									});
									if(i > 1)
									{
										alert("单选题，只能有一个正确答案");
										return;
									}
								}
								form_inputForm.submit();
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
