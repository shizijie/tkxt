<#include "/macro/crud-metro-page3-nowebpst.ftl" >
<#if id?exists>
	<#assign extPosition="修改题目"/>
<#else>
	<#assign extPosition="新增题目"/>
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
						
						<form id="form_inputForm" name="form_inputForm" class="form-horizontal" action="${base}/wjgl/twjwt!save.action" method="post" enctype="multipart/form-data">
							<@s.token />
							<#if id?exists && id != 0>
								<input type="hidden" name="id" value="${id!}"/>
							</#if>
							<input type="hidden" id="sxh" name="sxh" value="1">
							
							<input type="hidden" name="jfgzdm" id="jfgzdm" value="1">
				
						
						
							<div class="row-fluid">
								<div class="span1"></div>
								<div class="span10">
								
									<div class="row-fluid">
										<div class="span6 ">
											<div class="control-group">
												<#assign faflid=entity.faflid?if_exists.id?if_exists>
												<label class="control-label">课程章节<span class="required">*</span></label>
												<div class="controls">
													<select data-placeholder=" " class="chosen span10" tabindex="-1" id="faflid"  name="faflid.id" onchange="knowledge()">
														<option value=""></option>
														<#list allfl as kc>
																<optgroup label="${kc.flmc!}">
														<#if (kc.children?size>0)>
															<#if id==0>
															<#list kc.children as ch>
																<option value="${ch.id}" >${ch.flmc!}</option>
															</#list>
															<#else>
															<#list kc.children as ch>
																<option value="${ch.id}" <#if faflid==ch.id>selected</#if>>${ch.flmc!}</option>
															</#list>
															</#if>
														</#if>
														</#list>
													</select>
												</div>
												
											</div>
										</div>
										<div class="span6 ">
											<div class="control-group">
												<label class="control-label">是否有效&nbsp;&nbsp;</label>
												<div class="controls">
													<select name="sfyx" class="span12 m-wrap">
														<option value="1" <#if sfyx?exists && sfyx=="1">selected</#if>>有效</option>
														<option value="0" <#if sfyx?exists && sfyx=="0">selected</#if>>无效</option>
													</select>																								
												</div>
											</div>
										</div>
									</div>
									<div class="row-fluid">
										<div class="span6">
											<div class="control-group">
												<label class="control-label">题&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型<span class="required">*</span></label>
												<div class="controls">
													<select name="wtlx"	id="wtlx" class="span12 m-wrap" onchange="orderinfo()">
															<option value="">==请选择==</option>
														<#list dict as sel>
															<option value="${sel.name!}" <#if wtlx?exists && wtlx=="${sel.name!}">selected</#if>>${sel.name!}</option>
														</#list>
													</select>
												</div>																			
											</div>
										</div>
										
										<div class="span6 ">
											<div class="control-group">
												<label class="control-label">难易程度<span class="required">*</span></label>
												<div class="controls">
													<select name="nycd" id="nycd" class="span12 m-wrap">
														<option value="">==请选择==</option>
														<option value="NULL" <#if nycd?exists && nycd=="NULL">selected</#if>>无</option>
														<option value="A" <#if nycd?exists && nycd=="A">selected</#if>>容易</option>
														<option value="B" <#if nycd?exists && nycd=="B">selected</#if>>一般</option>
														<option value="C" <#if nycd?exists && nycd=="C">selected</#if>>中等</option>
														<option value="D" <#if nycd?exists && nycd=="D">selected</#if>>较难</option>
														<option value="E" <#if nycd?exists && nycd=="E">selected</#if>>困难</option>
													</select>
												</div>
											</div>
										</div>
										
									</div>
				      				
				      				<div class="control-group">
										<label class="control-label">知&nbsp;&nbsp;识&nbsp;&nbsp;点<span class="required">*</span></label>
										<div class="controls">
											<#if knowledgePoint?exists>
												<div id='btn'>
													<select id='sel1'  data-placeholder=' ' class='chosen span15' multiple='multiple' tabindex='6' >
														<#list allKp as kp>
														<option value="${kp.flmc}" <#list listKp as kn><#if kn?if_exists.flmc?if_exists=kp?if_exists.flmc?if_exists>selected</#if></#list>>${kp.fparent.flmc!}-${kp.flmc!}</option>
														</#list>
													</select>
												</div>
											</#if>
											<input type="hidden" id="knowledgePoint" name="knowledgePoint"/>
										</div>
									</div>
				      				
									<div class="control-group">
										<label class="control-label">题&nbsp;&nbsp;目<span class="required">*</span></label>
										<div class="controls">
											<input type="text" id="wtmc" name="wtmc" class="span15 m-wrap" value="${wtmc?if_exists}">
											<p/><p/>
											<@s.file id="wtmcFile" name="wtmcFile"  style="width:500"/>
											&nbsp;&nbsp;&nbsp;&nbsp;
											已上传附件：<a href="${base}/userfiles/kjzt/wtimg/${entity.wtmcFileName!}" target="_blank">${entity.wtmcFileName!}</a>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">题目解析</label>
										<div class="controls">
											<textarea id="bz" name="bz" class="span15 m-wrap" maxlength="200">${bz?if_exists}</textarea>
										</div>
									</div>
									
									<div class="control-group">
											<#if wtlx?exists && wtlx=="填空题">
												<div id='order' class='control-group' style='text-align:center'>
													<label class='control-label' >填空是否有序<span class='required'>*</span></label>
													<div class='controls'>
														<select name='isorder' class='span15 m-wrap'>
															<option value=''>==有序==</option>
															<option value='0' <#if isorder?if_exists=="0">selected</#if>>==无序==</option>
														</select>
													</div>
												</div>
											</#if>
											<input type="hidden" id="addorder">
										</div>

									
									<div class="control-group">
										<label class="control-label">题目选项</label>
										<div class="controls">
											<table  class="table table-bordered">
							      				<tr>
							      					<td class="textfield" style="width:4%"><center>选项代码</center></td>
													<td class="textfield" style="width:12%"><center>选项名称</center></td>
													<td class="textfield" style="width:6%"><center>是否正确答案</center></td>
												</tr>
												<#assign xxdmls=['A','B','C','D','E','F','G','H','I']>
							      				<#list 0..6 as t>
													<tr>
														<td>
															<center>
																${xxdmls[t_index]}
																<input type="hidden" name="dadm${t}" id="dadm${t}" value="${xxdmls[t_index]}">
																<input type="hidden" name="hdxid${t}" id="hdxid${t}">
															</center>
														</td>
														<td>
															<center>
																<input type="text" id="damc${t}" name="damc${t}" class="span10 m-wrap">
																<p/><p/>
																<@s.file id="damcFile${t}" name="damcFile${t}"  style="width:500"/>
																<p/>
																已上传附件：<div id="daimg${t}" ></div>
																<input type="hidden" id="oldimg${t}" name="oldimg" value=""></input>
																<#if t==0> 
																	<font color="red">注：如为填空题，答案在此填写</font>
																</#if>
															</center>
														</td>
														<input type="hidden" id="dafs${t}" name="dafs${t}" value="0">
														<td>
															<center>
																<select name="sfzqda${t}" style="width:200px" id="sfzqda${t}">
																	<option value="0" >否</option>
																	<option value="1" >是</option>
																</select>
															</center>
														</td>				
													</tr>			
												</#list>
												<tr>
													<td colspan="3"><font color="red">注意：请连续输入</font></td>
												</tr>
							      			</table>
										</div>
									</div>
									
									
									
								</div>
								<div class="span1"></div>
							</div>
							
							
							
							
							
							<div class="row-fluid">
								<div class="span12">
									<div class="form-actions">
										<center>
											<button class="btn blue big" type="submit" id="commit" name="commit" onclick="return cmt()">
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
						
						<script type="text/javascript" src="${base}/ckeditor/ckeditor.js"></script>
						<script type="text/javascript" src="${base}/ckfinder/ckfinder.js"></script>
						
						<script>
							function orderinfo(){
								if($("#wtlx").val()!="填空题"){
									return;
								}
								$("#order").remove();
								var html="<div id='order' class='control-group' style='text-align:center'>";
								html+="<label class='control-label' >填空是否有序<span class='required'>*</span></label>";
								html+="<div class='controls'>";
								html+="<select name='isorder' class='span15 m-wrap'>"
								html+="<option value=''>==有序==</option>";
								html+="<option value='0'>==无序==</option>";
								html+="</select>";
								html+="</div></div>";
								console.log(html);
								$("#addorder").before(html);
							}
						
							
							$(document).ready(function() {
							
								$(".chosen").each(function () {
						            $(this).chosen({
						                allow_single_deselect: $(this).attr("data-with-diselect") === "1" ? true : false,
						                no_results_text: "没有找到"
						        	});
						    	});
						    	$("#form_inputForm").validate({
									rules: {
										wtmc:{required:true},
										"faflid.id":{required:true},
										sxh:{number:true,required:true,maxlength:3}
									}
								});
						  	});	
							
							$("#form_inputForm").validate({
								rules: {
									wtlx:{required:true},
									"faflid.id":{required:true},
									sxh:{number:true,required:true,maxlength:3},
									nycd:{required:true}
								}
							});
							function cmt(){
								var wtlx = $("#wtlx").val();
								if($("#sel1").val()==""){
									alert("请选择知识点！！");
									return false;
								}
								$("#knowledgePoint").val($("#sel1").val());
								if(typeof(wtlx) != "undefined" && (wtlx == "1" || wtlx == "3"))
								{
									var i = 0
									$("select[name^='sfzqda']").each(function(){
										if($(this).val() == "1")
											i = i + 1;
									});
									if(i > 1)
									{
										alert("只能有一个正确答案");
										return false;
									}
								}
								
								var wtmc=CKEDITOR.instances.wtmc.getData();
								alert(wtmc);
								wtmc = wtmc.replace(/^\s+/g,"");
								if (wtmc =="")  
	                            {  
	                                alert("请填写问题内容.");  
	                                form_inputForm.wtmc.focus();  
	                                return false;  
	                            }
	                            
	                            var bz=CKEDITOR.instances.bz.getData();
								bz = bz.replace(/^\s+/g,"");
								if (bz =="")  
	                            {  
	                                alert("请填写话题内容.");  
	                                form_inputForm.bz.focus();  
	                                return false;  
	                            }
								
								return false;
								//form_inputForm.submit();
							}
							function knowledge(){
								$("#btn").remove();
								if($("#faflid").val()!=""){
									$.post(
										"${base}/wjgl/twjwt!getKnowledge.action",
										{id:$("#faflid").val()},
										function(data){
											if(data!=""){
												var html="<div id='btn'><select id='sel1'  data-placeholder=' ' class='chosen span15' multiple='multiple' tabindex='6' >";
												$.each($.parseJSON(data),function(i,n){
													html+="<option value='"+n.flmc+"'>"+n.fparent.flmc+"-"+n.flmc+"</option>";
												});
												html+="</select></div>";
												$("#knowledgePoint").before(html);
												$(".chosen").each(function () {
										            $(this).chosen({
										                allow_single_deselect: $(this).attr("data-with-diselect") === "1" ? true : false,
										                no_results_text: "没有找到"
										            });
										        });
											}
										}
									);
								}
							
							}
							
							<#if id?exists>
								//修改时问题回答项
								$.ajax({
									    type : "POST",
									    data : "{}",
									    url : '${base}/wjgl/twjwt!getHdxjson.action?id=${id}',
									    dataType : "json",
									    contentType : "application/json",
									    async:false,
									    success : function(data) {
									    	
									    	var obj2 = eval(data);
											if (typeof(obj2) != "undefined" && obj2 != null) {
												for (var i = 0; i < obj2.length; i++) {
													$("#hdxid"+i).val(obj2[i].id);	
													$("#damc"+i).val(obj2[i].damc);									
													$("#dafs"+i).val(obj2[i].dafs);
													$("#sfzqda"+i).find("option[value='"+obj2[i].sfzqda+"']").attr("selected",true);
													if (typeof(obj2[i].damcFileName) != "undefined" && obj2[i].damcFileName != null)
													{
														$("#daimg"+i).html("<a href='${base}/userfiles/kjzt/daimg/"+obj2[i].damcFileName+"' target='_blank'>"+obj2[i].damcFileName+"</a>");
														$("#oldimg"+i).val(obj2[i].damcFileName);
													}
													
												}
											}
									    },
									      error : function(XMLHttpRequest, textStatus,errorThrown) {
									      window.alert("服务器未成功加载该方案下的问题回答项:" + errorThrown);
									    }
								});				
								
							</#if>
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
