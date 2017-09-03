<#include '/macro/crud-metro-page3-nowebpst.ftl' >

<#if id?exists>
	<#assign extPosition='修改任务'/>
<#else>
	<#assign extPosition='创建任务'/>
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
						<form id="save_inputForm" name="save_inputForm" class="form-horizontal  form-bordered" action="${base}/wjgl/csrw!save.action" method="post">
							<@s.token />
							<#if id?exists>
								<input type="hidden" name="id" value="${id}"/>
							</#if>
							<!-- 在这里编写输入的元素 -->
							<div class="control-group">
								<label class="control-label">任务名称:<span class="required">*</span></label>
								<div class="controls">
									<input type="text" class="span5 m-wrap" id="rwmc" name="rwmc" value="${rwmc!}" maxlength="100" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">考试课程:<span class="required">*</span></label>
								<div class="controls">
									<select name="fafl.id" id="faflid"  class="span4 m-wrap">
										<option value="">==请选择==</option>
										<#list fafllist as fafl>
											<#if id?exists && fafl?exists && fafl.id?exists>
												<option value="${fafl?if_exists.id?if_exists}" selected="selected">${fafl?if_exists.flmc?if_exists}</option>
											<#else>
												<option value="${fafl?if_exists.id?if_exists}" >${fafl?if_exists.flmc?if_exists}</option>
											</#if>
										</#list>
									</select>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">起止时间:<span class="required">*</span></label>
								<div class="controls">
										<input readonly id="kssj"  class="m-wrap" style="width:200px" name="kssj" value="${entity.kssj?if_exists}" type="text" onclick="WdatePicker({el:'kssj',dateFmt:'yyyy-MM-dd HH:mm:ss'})"  />
									&nbsp;&nbsp;至&nbsp;&nbsp;
										<input readonly id="jssj" class="m-wrap" style="width:200px" name="jssj" value="${entity.jssj?if_exists}" type="text" onclick="WdatePicker({el:'jssj',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">考试时长(分钟):<span class="required">*</span></label>
								<div class="controls">
									<input type="text" class="span1 m-wrap" id="kssc" name="kssc" value="${kssc!}" maxlength="5" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">考试班级:</label>
								<div class="controls">
									<@s.optiontransferselect 
				              
				              		  cssStyle="width:130px;height:230px"   doubleCssStyle="width:130px;height:230px" 
				              		  
						              name="leftSide" 
						              
						              leftTitle="待选班级"
						
						              rightTitle="已选班级" 
						              
						              list="bjlist"
						              
						              listKey="id"
						              
						              listValue="bjmc"
						
						              multiple="true" 
						              
						              allowAddAllToLeft="false" allowAddAllToRight="false" allowSelectAll="false" allowUpDownOnLeft="false" allowUpDownOnRight="false"
						
						              doubleName="selbjstr"
						              
						              doubleList="selbjlist"
						              
						              doubleListKey="id"
						              
						              doubleListValue="bjmc"
						              
						              doubleMultiple="true" />
						              <br>
						              <button style='margin-left:160px' type='button' onclick='test()'>∧设置考试学生</button>
								</div>
							</div>
							
							<div class='control-group'>
								<label class='control-label'>考试学生:</label>
								<#assign num=0>
								<#if id?exists>
									<#list selbjlist as bj>
									<div class='controls test'>
										${bj?if_exists.bjmc!}&nbsp;&nbsp;&nbsp;班级考试学生：<br>
										<#list mapStu.get('${bj.id}') as stu>
											${stu.xm!}&nbsp;&nbsp;&nbsp;<input type='checkbox' <#if testStu?if_exists?index_of('${stu.id}')!=-1>checked</#if> style='margin-left:10px;margin-right:10px' value='${stu.id}' id='radio_' name='listStu[${num}].id'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<#if (num+1)%11==0>
											<br>
										</#if>
										<#assign num=num+1>
										</#list>
									</div>
									</#list>
								</#if>
								<input type='hidden' id="addBox" name='addBox' value='${ksbj!}'>
								<input type='hidden' id='number' value='${num}'>
							</div>
							
							<div class="row-fluid">
								<div class="span12">
									<div class="form-actions">
										<center>
											<button class="btn blue big" type="submit" id="commit" name="commit" onclick="getAllOptionValue(save_inputForm.selbjstr)">
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
										rwmc:{required:true,maxlength:30},
										kssj:{required:true},
										jssj:{required:true,compareDate:"#kssj"},
										kssc:{required:true,number:true}					
									},
									messages:{
										jssj:{
											compareDate:"结束时间必须大于开始时间"
										}
									}
								});						
							});	
							jQuery.validator.addMethod("compareDate", function(value,element,param) {
								var startDate = jQuery(param).val();
					          	var date1 = new Date(Date.parse(startDate.replace(/-/g, "/")));
					            var date2 = new Date(Date.parse(value.replace(/-/g, "/")));
					            return date1 < date2;
					        });
					        
					        function test(){
					        	if($("#selbjstr").val()==null){
					        		alert("请点击选择班级");
					        		return;
					        	}else{
					        		var id=String($("#selbjstr").val());
					        		if(id.length>32){
					        			alert("请选择一个！");
					        			return;
					        		}
					        		if($("#addBox").val().indexOf(id)!=-1){
					        			alert("不能重复选择！");
					        			return;
					        		}
					        		var bj=String($("option[value='"+id+"']").text().trim());
					        		
					        		$.post(
					        			"${base}/wjgl/xuesheng!getListStu.action",
					        			{
					        				bjId:id
					        			},
					        			function(data){
					        				var html="<div class='controls test'>"+bj+"  班级考试学生：<br>";
					        				var obj=eval(data);
					        				var num=0;
					        				var j;
					        				if($("#number").val()==""){
					        					j=0;
					        				}else{
					        					j=Number($("#number").val())+1;
					        				}
					        				$.each(eval(data),function(i,n){
						        				html+=n.xm+"&nbsp;&nbsp;&nbsp;&nbsp;<input type='checkbox' checked style='margin-left:10px;margin-right:10px' value='"+n.id+"' id='radio_"+(i+j)+"' name='listStu["+(i+j)+"].id'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
					        					if((i+1)%11==0){
					        						html+="<br>";
					        					}
					        					num++;
					        				});
					        				html+="</div>";
					        				$("#addBox").before(html);
					        				for(var i=0;i<j+num;i++){
						        				$("#radio_"+i).show();
					        					$("#radio_"+i).uniform();
					        				}
					        				$("#number").val(j+num-1);
					        			}
					        		);
					        		$("#addBox").val($("#addBox").val()+id+",");
					        		
					        	}
					        }
					        $("input[value='<-']").click(function(){
					        	$("#addBox").val("");
					        	$(".test").remove();
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