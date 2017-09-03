<#include '/macro/crud-metro-page3-nowebpst.ftl' >

<#if id?exists>
	<#assign extPosition='分配学生'/>
<#else>
	<#assign extPosition='分配学生'/>
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
						<form id="save_inputForm" name="save_inputForm" class="form-horizontal  form-bordered" action="${base}/wjgl/banji!dofpxs.action" method="post">
							<@s.token />
							<#if id?exists>
								<input type="hidden" name="id" value="${id}"/>
							</#if>
							<!-- 在这里编写输入的元素 -->
							<div class="control-group">
								<label class="control-label">班级名称:</label>
								<div class="controls">
									${entity?if_exists.bjmc!}
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">年级:</label>
								<div class="controls">
									${entity?if_exists.nj!}
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">班主任:</label>
								<div class="controls">
									${entity?if_exists.bzr?if_exists.xm?if_exists}
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">分配学生:</label>
								<div class="controls">
									<input type="text" id="que_xm" >
									<input type="button" value="查询" onclick="createSelect()" class="btn green">
									<table>
										<tr>
											<td><center>待选学生</center></td>
											<td>&nbsp;</td>
											<td><center>班级学生</center></td>
										</tr>
										<tr>
											<td>
										      	<select id="searchSelect" name="selectClass" MULTIPLE size="10" onDblClick="moveSelectedOption(this.form['selectClass'], this.form['checks'])" style="height:300px">		         	 
											     	 <#list xslst?if_exists as obj>
											         		<option value="${obj.id?if_exists}">${obj.xm?if_exists}[${obj.xh?if_exists}]</option>
											         </#list>
											    </select>
											</td>
											<td>
											  <input OnClick="moveSelectedOption(this.form['selectClass'], this.form['checks']);" class="mbtn" type="button" value="&gt;">
											  <br><br>
											  <input OnClick="moveSelectedOption(this.form['checks'], this.form['selectClass']);" class="mbtn" type="button" value="&lt;">
											</td>
											<td>
												<input type="hidden" name="sfzb" id="userStr">
										        <select id="checks" name="checks" MULTIPLE size="10" onDblClick="moveSelectedOption(this.form['checks'], this.form['selectClass'])" style="height:300px">
											        <#list xslst2?if_exists as obj>
											         		<option value="${obj.id?if_exists}">${obj.xm?if_exists}[${obj.xh?if_exists}]</option>
											         </#list>
										        </select>
											</td>
										</tr>
									</table>
								</div>
							</div>
							
									
							<div class="row-fluid">
								<div class="span12">
									<div class="form-actions">
										<center>
											<button class="btn blue big" type="submit" id="commit" name="commit" onclick="getAllOptionValue(save_inputForm.checks);" >
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
										checks:{required:true}						
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
							
							var userOpts=$("#searchSelect option");
							var fzrOpts=$("#checks option");
						    function createSelect(){
								var allOpts=[];
								var tmpOpts=$("#checks option");
								for(var i=0;i<fzrOpts.length;i++){				
									allOpts.push(fzrOpts[i]);
								}
								for(var j=0;j<userOpts.length;j++){
									allOpts.push(userOpts[j]);
								}
								var retOpts=[];
								for(var i=0;i<allOpts.length;i++){
									var flag=0;
									for(var j=0;j<tmpOpts.length;j++){
										if(allOpts[i].value==tmpOpts[j].value){
											flag=1;
											break;
										}
									}
									if(flag==0){
										retOpts.push(allOpts[i]);
									}
								}
								
								var xm=$("#que_xm").val();
								$("#searchSelect").html("");
								if(xm!=""){
									var tx;
									for(var i=0;i<retOpts.length;i++){
										tx=retOpts[i].text+"";
										if(tx.indexOf(xm)!=-1){
											$("#searchSelect").append(retOpts[i]);
										}
									}
								}else{
									for(var i=0;i<retOpts.length;i++){					
										$("#searchSelect").append(retOpts[i]);
									}
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