<#include '/macro/crud-metro-page3-nowebpst.ftl' >

<#if id?exists>
	<#assign extPosition='修改组卷'/>
<#else>
	<#assign extPosition='创建组卷'/>
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
						<form id="save_inputForm" class="form-horizontal  form-bordered" action="${base}/wjgl/jxzj!save.action" method="post">
							<@s.token />
							<#if id?exists>
								<input type="hidden" name="id" value="${id}"/>
							</#if>
							<!-- 在这里编写输入的元素 -->
							<div class="control-group">
								<label class="control-label">组卷名称:<span class="required">*</span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="zjmc" name="zjmc" value="${zjmc!}" maxlength="500" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">组卷类型:<span class="required">*</span></label>
								<div class="controls">
									<select name="zjtype" id="zjtype"  class="span10 m-wrap">
										<option value="">==请选择==</option>
										<option value="0" <#if zjtype?if_exists=="0">selected</#if>>日常</option>
										<option value="1" <#if zjtype?if_exists=="1">selected</#if>>考试</option>
									</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">选择分类:<span class="required">*</span></label>
								<div class="controls">
									<select data-placeholder=" "  class="chosen span10" tabindex="-1" id="type"  name="type.id"  onchange="getInfo()">
														<option value=""></option>
														<option value="null" <#if !type?exists> selected</#if>>所有学期</option>
														<#list allfl as kc>
																<optgroup label="${kc.typeName!}">
														<#if (kc.children?size>0)>
															<#if !id?exists>
															<#list kc.children as ch>
																<option value="${ch.id}" >${ch.typeName!}</option>
															</#list>
															<#else>
															<#list kc.children as ch>
																<option value="${ch.id}" <#if type?if_exists.id?if_exists==ch.id>selected</#if>>${ch.typeName!}</option>
															</#list>
															</#if>
														</#if>
														</#list>
									</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">组卷信息:<span class="required">*</span></label>
								<div class="controls">
									<font style="margin-right:10px" color="red">学年:</font><input type="text" placeholder="例：2016~2017" style="vertical-align: middle;width:1005px" class="m-wrap" id="xn" name="xn" value="${xn!}" maxlength="500" />
									<br/><br/>
									<font style="margin-right:10px" color="red">学期:</font><input type="text" placeholder="例：一" style="vertical-align: middle;width:1005px" class="m-wrap" id="xq" name="xq" value="${xq!}" maxlength="500" />
									<br/><br/>
									<font style="margin-right:10px" color="red">类型:</font><input type="text" placeholder="例：期末"style="vertical-align: middle;width:1005px" class="m-wrap" id="exam" name="exam" value="${exam!}" maxlength="500" />
								</div>
							</div>
							<#if id?exists>
							<#list reslist?if_exists as tx>
							<div class='control-group test'>
								<label class='control-label'>${tx.txmc}<input type='hidden' name='zjid' value='${tx.txid}'/></label>
								<div class='controls'>
									选题数量：<input type='text'  id='xtsl${tx_index}' name='xtsl' value='${tx.tmsl!}' maxlength='3' />&nbsp;&nbsp;
									每题分值：<input type='text'  id='mtfz${tx_index}' name='mtfz' value='${tx.mtfz!}' maxlength='3' /> 
									小计：<span name='txj' id='xj${tx_index}'>${tx.mhxj!}</span>
									<br>题型题量：<font color='red'><span name='dxtsy' id='dxtsy${tx_index}'>${tx.sytl!}</span></font>
								</div>
							</div>
							</#list>
							<div class='control-group test'>
								 <label class='control-label'>总分:</label>
								 <div class='controls'>
								 <font color='red'><span id='zf' name='zf'>${zf!}</span></font>
								 </div>
							</div>
							</#if>
							<input type="hidden" id="add"/>
							<input type="hidden" id="num" value="0"/>
							<input type="hidden" id="num2" value="${reslist?if_exists?size}"/>
							<div class="row-fluid">
								<div class="span12">
									<div class="form-actions">
										<center>
											<button class="btn blue big" type="submit" id="commit" onclick="return check()" name="commit" >
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
										zjtype:{required:true},
										zjmc:{required:true},
										"type.id":{required:true},
										xn:{required:true},
										xq:{required:true},
										exam:{required:true}
																
									}
								});
								$(".chosen").each(function () {
						            $(this).chosen({
						                allow_single_deselect: $(this).attr("data-with-diselect") === "1" ? true : false,
						                no_results_text: "没有找到",
                                      	display_disabled_options:true
						            });
						        });		
						        //$("#type_chzn").css({"cssText","margin-top:-5px!important;width:1058px"});
						        //$(".chosen-select").chosen({margin-top:-5px}); 					
							});	
							
							function getInfo(){
								$.post(
									"${base}//wjgl/jxzj!getInfo.action",
									{id:$("#type").val()},
									function(data){
										var num=0;
										$(".test").remove();
										if(data!=""){
											var html="";
											$.each($.parseJSON(data),function(i,n){
												html+="<div class='control-group test'>";
												html+="<label class='control-label'>"+n.typeName+"<input type='hidden' name='zjid' value='"+n.id+"'/></label>";
												html+="<div class='controls'>";
												html+="选题数量：<input type='text'  id='xtsl"+i+"' name='xtsl' value='0' maxlength='3'  />&nbsp;&nbsp;";
												html+="每题分值：<input type='text'  id='mtfz"+i+"' name='mtfz' value='0' maxlength='3'  />";
												html+="小计：<span name='txj' id='xj"+i+"'>0.0</span>";
												html+="<br>题型题量：<font color='red'><span name='dxtsy' id='dxtsy"+i+"'>"+n.remark+"</span></font>";
												html+="</div></div>";
												num++;
											});
											html+="<div class='control-group test'><label class='control-label'>总分:</label><div class='controls'><font color='red'><span id='zf' name='zf'>0</span></font></div></div>";
											$("#add").before(html);
										}else{
											var html="<div class='control-group test'><label class='control-label'>题库情况:</label><div class='controls'><span ><font color='red'>暂无</font></span></div></div>";
											$("#add").before(html);
										}
										$("#num").val(num);
									}
								);
							}
							
							$('.form :input').live('input propertychange', function() {   //进行相关操作  
								var num=$("#num").val();
								if(num!=0){
									var sum=0;
									for(var i=0;i<num;i++){
										if((Number($("#xtsl"+i).val())-Number($("#dxtsy"+i).html()))>=1){
											alert("数量过大！！");
											$("#xtsl"+i).val(0);
										}
										var a=Number($("#xtsl"+i).val())*Number($("#mtfz"+i).val());
										$("#xj"+i).text(a);
										sum+=a;
									}
									$("#zf").text(sum);
								}else{
									var num2=$("#num2").val();
									var sum2=0;
									for(var j=0;j<num2;j++){
										if((Number($("#xtsl"+j).val())-Number($("#dxtsy"+j).html()))>=1){
											alert("数量过大！！");
											$("#xtsl"+j).val(0);
										}
										var b=Number($("#xtsl"+j).val())*Number($("#mtfz"+j).val());
										$("#xj"+j).text(b);
										sum2+=b;
									}
									$("#zf").text(sum2);
								}
							});
							
							function check(){
								if($("#xtsl0").val()==undefined){
									alert("请填写完整信息！！");
									return false;
								}
								var num=$("#num").val();
								if(num!=0){
									for(var i=0;i<num;i++){
										if(($("#xtsl"+i).val()==0&&$("#mtfz"+i).val()!=0)||($("#xtsl"+i).val()!=0&&$("#mtfz"+i).val()==0)){
											alert("请填写题数/分值完整信息！！");
											return false;
										}
									}
								}else{
									var num2=$("#num2").val();
									for(var j=0;j<num2;j++){
										if(($("#xtsl"+j).val()==0&&$("#mtfz"+j).val()!=0)||($("#xtsl"+j).val()!=0&&$("#mtfz"+j).val()==0)){
											alert("请填写题数/分值完整信息！！");
											return false;
										}
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