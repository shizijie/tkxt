<#include "/macro/crud-metro-page3-nowebpst.ftl" >

<#assign extPosition="手动组卷"/>

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
						
						<form id="save_inputForm" name="save_inputForm" class="form-horizontal  form-bordered" action="${base}/wjgl/csrw!sdzjSave.action" method="post">
							<input type="hidden" name="id" value="${entity?if_exists.id}">
							<div class="row-fluid">
								<div  class="span12">
									<@s.optiontransferselect 
				              
				              		  cssStyle="width:800px;height:700px;overflow:scroll"   doubleCssStyle="width:800px;height:700px;overflow:scroll" 
				              		  
						              name="leftSide" 
						              
						              leftTitle="待选题目<div id='dxxx'></div>"
						
						              rightTitle="已选题目<div id='yxxx'></div>" 
						              
						              list="dxtmlist"
						              
						              listKey="id"
						              
						              listValue="wtmc"
						
						              multiple="true" 
						              
						              allowAddAllToLeft="false" allowAddAllToRight="false" allowSelectAll="false" allowUpDownOnLeft="false" allowUpDownOnRight="false"
						
						              doubleName="rightSide"
						              
						              doubleList="yxtmlist"
						              
						              doubleListKey="id"
						              
						              doubleListValue="wtmc"
						              
						              doubleMultiple="true" />
								</div>
							</div>
				
							<div class="row-fluid">
								<div class="span12">
									<div class="form-actions">
										<center>
											<button class="btn blue big" type="submit" id="commit" name="commit" onclick="getAllOptionValue(save_inputForm.rightSide)" >
												<i class="icon-ok"></i> 保存
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

