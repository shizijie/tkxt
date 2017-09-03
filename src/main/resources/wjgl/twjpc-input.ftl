<#include "/macro/crud-metro-page3-nowebpst.ftl" >
<#if id?exists>
	<#assign extPosition="修改批次"/>
<#else>
	<#assign extPosition="新增批次"/>
</#if>

<#assign dispTbkg={"0":"关", "1":"开"}>

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
						
						<form id="save_inputForm" class="form-horizontal  form-bordered" action="${base}/wjgl/twjpc!save.action" method="post">
							<@s.token />
							<#if id?exists>
								<input type="hidden" name="id" value="${id}"/>
							</#if>
							<div class="control-group">
								<label class="control-label">批次名称 :<span class="required">*</span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="pzmc" name="pzmc" maxlength="40" value="${pzmc?if_exists}"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">开始时间 :<span class="required">*</span></label>
								<div class="controls">
									<div class="input-append date form_datetime">
										<input readonly id="kssj"  class="m-wrap" style="width:90px" name="kssj" value="${entity.kssj?if_exists}" type="text"  />
										<span class="add-on" onclick="WdatePicker({el:'kssj'})"><i class="icon-calendar"></i></span>
									</div>
									至
									<div class="input-append date form_datetime">
										<input readonly id="jssj" class="m-wrap" style="width:90px" name="jssj" value="${entity.jssj?if_exists}" type="text"  />
										<span class="add-on" onclick="WdatePicker({el:'jssj'})"><i class="icon-calendar"></i></span>
									</div>					
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">开关 :</label>
								<div class="controls">
									<@s.select name="tbkg" list=r"#{'0':'关','1':'开'}"  value="${tbkg?default('1')}"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">选用问卷:</label>
								<div class="controls">
								
								
									
									<@s.optiontransferselect 
				              
				              		  cssStyle="width:130px;height:230px"   doubleCssStyle="width:130px;height:230px" 
				              		  
						              name="leftSide" 
						              
						              leftTitle="待选方案"
						
						              rightTitle="已选方案" 
						              
						              list="falist"
						              
						              listKey="id"
						              
						              listValue="famc"
						
						              multiple="true" 
						              
						              allowAddAllToLeft="false" allowAddAllToRight="false" allowSelectAll="false" allowUpDownOnLeft="false" allowUpDownOnRight="false"
						
						              doubleName="selfastr"
						              
						              doubleList="selfalist"
						              
						              doubleListKey="id"
						              
						              doubleListValue="famc"
						              
						              doubleMultiple="true" />
								</div>
							</div>
				
							<div class="row-fluid">
								<div class="span12">
									<div class="form-actions">
										<center>
											<button class="btn blue big" type="submit" id="commit" name="commit" onclick="getAllOptionValue(save_inputForm.selfastr)" >
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
							jQuery.validator.addMethod("compareDate", function(value,element,param) {
								var startDate = jQuery(param).val();
					          	var date1 = new Date(Date.parse(startDate.replace(/-/g, "/")));
					            var date2 = new Date(Date.parse(value.replace(/-/g, "/")));
					            return date1 < date2;
					        });		
							$(document).ready(function() {
								$("#save_inputForm").validate({
									rules: {
										pzmc:{required:true,maxlength:20},
										kssj:{required:true},
										jssj:{required:true,compareDate:"#kssj"},
										bz:{maxlength:15}
									},
									messages:{
										jssj:{
											compareDate:"结束时间必须大于开始时间"
										}
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

