<#include '/macro/crud-metro-page3-nowebpst.ftl' >

<#if id?exists>
	<#assign extPosition='修改wjgl'/>
<#else>
	<#assign extPosition='设置Client端无效时间'/>
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
						<form id="save_inputForm" class="form-horizontal  form-bordered" action="${base}/wjgl/twjwxsj!save.action" method="post">
							<@s.token />
							<#if id?exists>
								<input type="hidden" name="id" value="${id}"/>
							</#if>
							<!-- 在这里编写输入的元素 -->
							<div class="control-group">
								<label class="control-label">时间类型:<span class="required">*</span></label>
								<div class="controls">
									<@s.select cssClass="span6 m-wrap" name="sjlx" id="sjlx" list=r"#{'1':'固定时间','2':'临时时间'}" value="${sjlx!}" onchange="changesjlx()"/>
								</div>
							</div>
							<div class="control-group" id="sjlx1">
								<label class="control-label">无效周:<span class="required">*</span></label>
								<div class="controls">
									<@s.select cssClass="span3 m-wrap" name="zhourq" id="zhourq" list=r"#{'1':'周一','2':'周二','3':'周三','4':'周四','5':'周五','6':'周六','7':'周日'}" value="${zhourq!}"/>
									至
									<@s.select cssClass="span3 m-wrap" name="zhourq2" id="zhourq2" list=r"#{'1':'周一','2':'周二','3':'周三','4':'周四','5':'周五','6':'周六','7':'周日'}" value="${zhourq2!}"/>
								</div>
							</div>
							<div class="control-group" id="sjlx2">
								<label class="control-label">无效日期:<span class="required">*</span></label>
								<div class="controls">
									<div class="input-append date form_datetime">
										<input readonly id="zhourqsj"  class="span3 m-wrap" style="width:200px" name="zhourq" value="${entity.zhourq?if_exists}" type="text"  />
										<span class="add-on" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'zhourqsj'})"><i class="icon-calendar"></i></span>
									</div>
									至
									<div class="input-append date form_datetime">
										<input readonly id="zhourq2sj"  class="span3 m-wrap" style="width:200px" name="zhourq2" value="${entity.zhourq2?if_exists}" type="text"  />
										<span class="add-on" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'zhourq2sj'})"><i class="icon-calendar"></i></span>
									</div>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">无效时间:<span class="required">*</span></label>
								<div class="controls">
									<div class="input-append date form_datetime">
										<input readonly id="kssj"  class="span3 m-wrap" style="width:200px" name="kssj" value="${entity.kssj?if_exists}" type="text"  />
										<span class="add-on" onclick="WdatePicker({dateFmt:'HH:mm:ss',el:'kssj'})"><i class="icon-calendar"></i></span>
									</div>
									 至 
									<div class="input-append date form_datetime">
										<input readonly id="jssj" class="span3 m-wrap" style="width:200px" name="jssj" value="${entity.jssj?if_exists}" type="text"  />
										<span class="add-on" onclick="WdatePicker({dateFmt:'HH:mm:ss',el:'jssj'})"><i class="icon-calendar"></i></span>
									</div>	
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">备注:</label>
								<div class="controls">
									<input type="text" class="span6 m-wrap" id="bz" name="bz" value="${bz!}" maxlength="20" />
								</div>
							</div>
							<div class="row-fluid">
								<div class="span12">
									<div class="form-actions">
										<center>
											<button class="btn blue big" type="button" id="commit" name="commit" onclick="cmt()" >
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
							function cmt(){
								var sjlxval = $("#sjlx").val();
								if(sjlxval == "1")
								{
									if($("#kssj").val() == "")
									{
										alert("请选择无效开始时间");
										return;
									}
									if($("#jssj").val() == "")
									{
										alert("请选择无效结束时间");
										return;
									}
									var kssjtmp = $("#zhourq").val() + $("#kssj").val();
									kssjtmp = kssjtmp.replace(/:/g,"");
									var jssjtmp = $("#zhourq2").val() + $("#jssj").val();
									jssjtmp = jssjtmp.replace(/:/g,"");
									if(jssjtmp - kssjtmp < 0)
									{
										alert("结束日期时间必须大于开始日期时间");
										return;
									}
								}
								if(sjlxval == "2")
								{
									if($("#zhourqsj").val() == "")
									{
										alert("请选择无效开始日期");
										return;
									}
									if($("#zhourq2sj").val() == "")
									{
										alert("请选择无效结束日期");
										return;
									}
									if($("#kssj").val() == "")
									{
										alert("请选择无效开始时间");
										return;
									}
									if($("#jssj").val() == "")
									{
										alert("请选择无效结束时间");
										return;
									}
									var kssjtmp = $("#zhourqsj").val() + $("#kssj").val();
									kssjtmp = kssjtmp.replace(/:/g,"");
									kssjtmp = kssjtmp.replace(/-/g,"");
									var jssjtmp = $("#zhourq2sj").val() + $("#jssj").val();
									jssjtmp = jssjtmp.replace(/:/g,"");
									jssjtmp = jssjtmp.replace(/-/g,"");
									if(jssjtmp - kssjtmp < 0)
									{
										alert("结束日期时间必须大于开始日期时间");
										return;
									}
								}
								save_inputForm.submit();
							}
							function changesjlx(){
								var sjlxval = $("#sjlx").val();
								if(sjlxval == "1")
								{
									$("#sjlx1").show();
									$("#sjlx2").hide();
								}else{
									$("#sjlx1").hide();
									$("#sjlx2").show();
								}
							}
							changesjlx();
							
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