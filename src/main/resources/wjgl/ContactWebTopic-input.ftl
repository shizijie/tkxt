<#include '/macro/crud-metro-page3-nowebpst.ftl' >

<#if id?exists>
	<#assign extPosition='修改题目信息'/>
<#else>
	<#assign extPosition='创建题目信息'/>
</#if>
<#assign Eng={"0":"A","1":"B", "2":"C","3":"D","4":"E","5":"F","6":"G","7":"H","8":"I","9":"J"}>
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
						<form id="save_inputForm" class="form-horizontal  form-bordered" action="${base}/wjgl/contact_web_topic!save.action" method="post" enctype="multipart/form-data">
							<@s.token />
							<#if id?exists>
								<input type="hidden" name="id" value="${id}"/>
							</#if>
							
							
							<div class="row-fluid">
								<div class="span1"></div>
								<div class="span10">
							
							<!-- 在这里编写输入的元素 -->
							<div class="control-group">
								<label class="control-label">题目名称<span class="required">*</span></label>
								<div class="controls">
									<input type="text" class='span12' id="topicName" name="topicName"  value='${topicName!}'/>
								</div>
							</div>
							
							<div class="row-fluid">
								<div class="span6 ">
									<div class="control-group">
										<#assign faflid=entity.faflid?if_exists.id?if_exists>
										<label class="control-label">课程章节<span class="required">*</span></label>
										<div class="controls">
											<select data-placeholder=" " class="chosen span10" tabindex="-1" id="twjfafl"  name="twjfafl.id" onchange="knowledge()">
												<option value=""></option>
												<#list allfl as kc>
														<optgroup label="${kc.flmc!}">
												<#if (kc.children?size>0)>
													<#list kc?if_exists.children as ch>
														<option value="${ch.id}" <#if id?exists&&twjfafl.id==ch.id>selected</#if>>${ch.flmc!}</option>
													</#list>
												</#if>
												</#list>
											</select>
										</div>
										
									</div>
								</div>
								<div class="span3 ">
									<div class="control-group">
										<label class="control-label">是否有效<span class="required">*</span></label>
										<div class="controls">
											<select name="status" class="span12 m-wrap">
												<option value="1" <#if sfyx?exists && sfyx=="1">selected</#if>>有效</option>
												<option value="0" <#if sfyx?exists && sfyx=="0">selected</#if>>无效</option>
											</select>																								
										</div>
									</div>
								</div>
								
								<div class="span3 ">
									<div class="control-group">
										<label class="control-label">难易程度<span class="required">*</span></label>
										<div class="controls">
											<select name="complexity" id="complexity" class="span12 m-wrap">
												<option value="">==请选择==</option>
												<option value="A" <#if complexity?exists && complexity=="A">selected</#if>>容易</option>
												<option value="B" <#if complexity?exists && complexity=="B">selected</#if>>一般</option>
												<option value="C" <#if complexity?exists && complexity=="C">selected</#if>>中等</option>
												<option value="D" <#if complexity?exists && complexity=="D">selected</#if>>较难</option>
												<option value="E" <#if complexity?exists && complexity=="E">selected</#if>>困难</option>
											</select>
										</div>
									</div>
								</div>
							</div>
							
							
							<div class="control-group">
								<label class="control-label">知&nbsp;识&nbsp;点<span class="required">*</span></label>
								<div class="controls">
									<#if knowledgePoint?exists>
										<div id='btn'>
											<select id='selKnow' name='knowledgePoint' data-placeholder=' ' class='chosen span12' multiple='multiple' tabindex='6' >
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
								<label class="control-label">题目描述<span class="required">*</span></label>
								<div class="controls">
									<textarea type="text" required style="width:100%" id="textField" name="textField"  />${textField?if_exists}</textarea>
								</div>
							</div>
							
							<#if id??>
								<#list listTopicContent?if_exists as topic>
									<#if topic?if_exists.topicType?if_exists=="1">
										<div id='topic_${topic_index+1}' class='control-group'>
											<input type='hidden' name='list[${topic_index}].topicType' value='${topic?if_exists.topicType!}'>
											<input type='hidden' name='list[${topic_index}].serialNumber' value='${topic?if_exists.serialNumber!}'>
											<label class='control-label'>问题${topic_index+1}<span class='required'>*</span></label>
											<div class='controls'>
												<textarea type='text' required style='width:90%;height:100px' id='textField_${topic_index+1}' name='list[${topic_index}].topicText'  />${topic?if_exists.topicText!}</textarea>
												<br><br>&nbsp;&nbsp;&nbsp;&nbsp;
												<input type='file' name='list[${topic_index}].document' value='' />
												&nbsp;&nbsp;&nbsp;&nbsp;
												已上传附件：<a href='${base}/userfiles/contactWebTopic/topicImg/${topic?if_exists.topicPicture!}' target='_blank'><#if topic?if_exists.topicPicture?if_exists!="0">${topic?if_exists.oldPicName!}</#if></a>
												<br><br>
												<button type='button' style='margin-left:20px;margin-top:10px' class='btn blue' remark='optNumber_${topic_index+1}'  onclick='addOpt(this)'>新增选项</button>
												<button type='button' style='margin-left:20px;margin-top:10px' class='btn red'  remark='optNumber_${topic_index+1}'  onclick='delOpt(this)'>删除选项</button>
												<font color='red' style='margin-left:20px;margin-right:20px'>注：选项数量上限为10。</font>
												<span id='optNumber_${topic_index+1}_remark' ></span>
												<br>
												<input type='hidden' name='list[${topic_index}].isHave' value='${topic?if_exists.topicPicture!}'>
												<input type='hidden' name='list[${topic_index}].isHaveOld' value='${topic?if_exists.oldPicName!}'>
												<#list topic?if_exists.setTopicOptions?if_exists as opt>
													<div id='optNumber_${topic_index+1}_${opt_index+1}'>
													<br>
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;选项${Eng['${opt_index}']}：<input class='span6' required name='list[${topic_index}].listTopicOptions[${opt_index}].optionContent' value='${opt?if_exists.optionContent!}'>
													<input type='hidden' value='${opt?if_exists.serialNumber}' name='list[${topic_index}].listTopicOptions[${opt_index}].serialNumber'>
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<input type='file' name='list[${topic_index}].listTopicOptions[${opt_index}].document' value='' />
													&nbsp;&nbsp;&nbsp;&nbsp;
													已上传附件：<a href='${base}/userfiles/contactWebTopic/optionImg/${opt?if_exists.optionPicture!}' target='_blank'><#if opt?if_exists.optionPicture?if_exists!="0">${opt?if_exists.oldPicName!}</#if></a>
													</div>
													<input type='hidden' name='list[${topic_index}].listTopicOptions[${opt_index}].isHave' value='${opt?if_exists.optionPicture!}'>
													<input type='hidden' name='list[${topic_index}].listTopicOptions[${opt_index}].isHaveOld' value='${opt?if_exists.oldPicName!}'>
												</#list>
												<input type='hidden' id='optNumber_${topic_index+1}' value='${topic?if_exists.setTopicOptions?size+1}'>
												<br>
												<font color='red'>正确答案：</font><input required class='span6' name='list[${topic_index}].rightAnswers' value='${topic?if_exists.rightAnswers!}'>
												<font color='red' style='margin-left:50px'>是否顺序：</font>
												<select name='list[${topic_index}].isOrder'>
													<option value='1'<#if topic?if_exists.isOrder=="1">selected</#if>>顺序</option>
													<option value='0'<#if topic?if_exists.isOrder=="0">selected</#if>>乱序</option>
												</select>
											</div>
										</div>
									<#elseif topic?if_exists.topicType?if_exists=="2">
										<div id='topic_${topic_index+1}' class='control-group'>
											<input type='hidden' name='list[${topic_index}].topicType' value='${topic?if_exists.topicType!}'>
											<input type='hidden' name='list[${topic_index}].isOrder' value='3'>
											<input type='hidden' name='list[${topic_index}].serialNumber' value='${topic?if_exists.serialNumber!}'>
											<label class='control-label'>问题${topic_index+1}<span class='required'>*</span></label>
											<div class='controls'>
												<textarea type='text' required style='width:90%;height:100px' id='topic_${topic_index+1}_textField' remark='topic_${topic_index+1}'  oninput='textChange(this)' />${topic?if_exists.topicText!}</textarea>
												<br><br>&nbsp;&nbsp;&nbsp;&nbsp;
												<input type='file' name='list[${topic_index}].document' value='' />
												&nbsp;&nbsp;&nbsp;&nbsp;
												已上传附件：<a href='${base}/userfiles/contactWebTopic/topicImg/${topic?if_exists.topicPicture!}' target='_blank'><#if topic?if_exists.topicPicture?if_exists!="0">${topic?if_exists.oldPicName!}</#if></a>
												<br><br>
												<font color='red'>正确答案：</font><span id='topic_${topic_index+1}_example'>${topic?if_exists.rightAnswers!}</span>
												<input type='hidden' id='topic_${topic_index+1}_topicText' name='list[${topic_index}].topicText' value='${topic?if_exists.topicText!}'>
												<input type='hidden' id='topic_${topic_index+1}_rightAnswers' name='list[${topic_index}].rightAnswers' value=''>
												<br><br>
												<span id='topic_${topic_index+1}_answer' style='margin-left:30px'></span>
												<input type='hidden' name='list[${topic_index}].isHave' value='${topic?if_exists.topicPicture!}'>
												<input type='hidden' name='list[${topic_index}].isHaveOld' value='${topic?if_exists.oldPicName!}'>
											</div>
										</div>
											
									</#if>
								</#list>
							<#else>
							<div class="control-group">
								<label class="control-label">新增问题类型<span class="required">*</span></label>
								<div class="controls">
									<select class='span4' id="typeId" onchange='selType()'>
										<option value="">==请选择==</option>
										<option value="1">多选题</option>
										<option value="2">填空题</option>
									</select>
									<button type="button" style='margin-left:50px;margin-right:20px' class='btn blue' onclick='addType()'>新增题型</button>
									<font color='red' style='margin-right:30px'>注：题目数量上限为10。</font>
									<span id='remark'></span>
								</div>
							</div>
							</#if>
							
							<input type='hidden' id='addQue' <#if id??>value='${listTopicContent?if_exists?size+1}'<#else>value='1'</#if> name="addQue">
							<!-- 在这里编写输入的元素 -->
							</div>
								<div class="span1"></div>
							</div>
							
							
							<div class="row-fluid">
								<div class="span12">
									<div class="form-actions">
										<center>
											<button class="btn blue big" type="submit" id="commit" name="commit" onclick='return save()'>
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
						
							function save(){
								if($("#selKnow").val()==""){
									alert("请选择知识点！！");
									return false;
								}
								var number=Number($("#addQue").val())-1;
								if(number==0){
									alert("题目数量不能为0！！");
									return false;
								}
								for(var i=1;i<=number;i++){
									var answer="";
									var isbreak=false;
									$(".topic_"+i).each(function(){
										if($(this).val()!=""){
											answer+=$(this).val()+";";
										}else{
											alert("填空题答案需补充完整！！");
											isbreak=true;
											return false;
										}
									});
									if(isbreak){
										return false;
									}
									if(answer.indexOf(";")!=-1){
										answer=answer.substring(0,answer.length-1);
									}
									if(answer!=""){
										$("#topic_"+i+"_rightAnswers").val(answer);
									}
								}
							}
						
							function delType(obj){
								$("#remark").html("");
								var name=$(obj).attr('name');
								var delNumber=name.substring(name.indexOf("topic_")+6);
								var maxNumber=Number($("#addQue").val())-1;
								if(delNumber==maxNumber){
									$("#"+name).remove();
									$("#addQue").val(maxNumber);
								}else{
									alert("请先删除最后一项！！");								
								}
							}
						
							function getPlaceholderCount(strSource) {
							  var c = "input"; // 要计算的字符
							  var regex = new RegExp(c, 'g'); // 使用g表示整个字符串都要匹配
							  var result = strSource.match(regex);
							  var count = !result ? 0 : result.length;
							  return !result ? 0 : result.length;
							}
							
						
							function setAnswer(obj){
								var name=$(obj).attr('remark');
								var num=Number(getPlaceholderCount($("#"+name+"_example").html()));
								if(num>0){
									var html="";
									var str="<input class='span1 "+name+"'>";
									for(var i=0;i<num;i++){
										html+="   "+(i+1)+"："+str;
									}
									
									$("#"+name+"_answer").html(html);
								}else{
									$("#"+name+"_answer").html("<font color='red'>填空项个数不能为0！！</font>");
								}
							}
							
							function textChange(obj){
								var name=$(obj).attr('remark');
								$("#"+name+"_answer").html("");
								var html=$("#"+name+"_textField").val();
								var str="<input class='span1 "+name+"'>";
								$("#"+name+"_example").html(html.replaceAll("___",str));
								$("#"+name+"_topicText").val(html.replaceAll("___",str));
							}
							String.prototype.replaceAll = function (FindText, RepText) {
							    regExp = new RegExp(FindText, "g");
							    return this.replace(regExp, RepText);
							}
							
							
						
							function addOpt(obj){
								var name=$(obj).attr('remark');
								var optNumber=Number($("#"+name).val());
								var topNum=Number(name.substring(name.indexOf("_")+1))-1;
								$("#"+name+"_remark").html("");
								if(optNumber==11){
									$("#"+name+"_remark").html("<font color='red' style='margin-left:20px'>添加已达上限！！</font>");
									return;
								}
								var html="<div id='"+name+"_"+optNumber+"'><br>";
								html+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;选项"+_convert(optNumber)+"：<input class='span6' required name='list["+topNum+"].listTopicOptions["+(optNumber-1)+"].optionContent'>";
								html+="<input type='hidden'  value='"+optNumber+"' name='list["+topNum+"].listTopicOptions["+(optNumber-1)+"].serialNumber'>";
								html+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
								html+="<input type='file' name='list["+topNum+"].listTopicOptions["+(optNumber-1)+"].document' value='' />";
								html+="&nbsp;&nbsp;&nbsp;&nbsp;";
								html+="已上传附件：<a href='${base}/userfiles/contactWebTopic/optionImg/${entity.wtmcFileName!}' target='_blank'></a>";
								html+="</div>";
								$("#"+name).before(html);
								$("#"+name).val(optNumber+1);
							}
							
							function delOpt(obj){
								var name=$(obj).attr('remark');
								var optNumber=Number($("#"+name).val())-1;
								$("#"+name+"_remark").html("");
								if(optNumber==1){
									$("#"+name+"_remark").html("<font color='red' style='margin-left:20px'>删除已达上限！！</font>");
									return;
								}
								$("#"+name+"_"+optNumber).remove();
								$("#"+name).val(optNumber);
							}
							
							//添加题型
							function addType(){
								$("#remark").html("");
								selType();
								var topicNumber=Number($("#addQue").val());
								if(topicNumber==11){
									$("#remark").html("<font color='red'>添加已达上限！！</font>");
									return;
								}
								if($("#typeId").val()==1){
									$("#addQue").before(buildQue1(topicNumber));
								}
								if($("#typeId").val()==2){
									$("#addQue").before(buildQue2(topicNumber));
								}
								
							}
							
							//多选
							function buildQue1(number){
								var html="<div id='topic_"+number+"' class='control-group'>";
								html+="<input type='hidden' name='list["+(number-1)+"].topicType' value='1'>";
								html+="<input type='hidden' name='list["+(number-1)+"].serialNumber' value='"+number+"'>";
								html+="<label class='control-label'>问题"+number+"<span class='required'>*</span></label>";
								html+="<div class='controls'>";
								html+="<textarea type='text' required style='width:90%;height:100px' id='textField_"+number+"' name='list["+(number-1)+"].topicText'  /></textarea>";
								html+="<button class='btn btn-default btn-extra-sm' style='background-color: #ffffff;float:right;top:0' name='topic_"+number+"' type='button' onclick='delType(this)'>X</button>";
								html+="<br><br>";
								html+="&nbsp;&nbsp;&nbsp;&nbsp;";
								html+="<input type='file' name='list["+(number-1)+"].document' value='' />";
								html+="&nbsp;&nbsp;&nbsp;&nbsp;";
								html+="已上传附件：<a href='${base}/userfiles/contactWebTopic/topicImg/${topicPicture!}' target='_blank'></a>";
								html+="<br><br>";
								html+="<button type='button' style='margin-left:20px;margin-top:10px' class='btn blue' remark='optNumber_"+number+"'  onclick='addOpt(this)'>新增选项</button>";
								html+="<button type='button' style='margin-left:20px;margin-top:10px' class='btn red'  remark='optNumber_"+number+"'  onclick='delOpt(this)'>删除选项</button>";
								html+="<font color='red' style='margin-left:20px;margin-right:20px'>注：选项数量上限为10。</font>";
								html+="<span id='optNumber_"+number+"_remark' ></span>";
								html+="<div id='optNumber_"+number+"_1'><br><br>";
								html+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;选项A：<input class='span6' required name='list["+(number-1)+"].listTopicOptions[0].optionContent'>";
								html+="<input type='hidden' value='1' name='list["+(number-1)+"].listTopicOptions[0].serialNumber'>";
								html+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
								html+="<input type='file' name='list["+(number-1)+"].listTopicOptions[0].document' value='' />";
								html+="&nbsp;&nbsp;&nbsp;&nbsp;";
								html+="已上传附件：<a href='${base}/userfiles/contactWebTopic/optionImg/${entity.wtmcFileName!}' target='_blank'></a>";
								html+="</div>";
								html+="<input type='hidden' id='optNumber_"+number+"' value='2'>";
								html+="<br>"
								html+="<font color='red'>正确答案：</font><input required class='span6' name='list["+(number-1)+"].rightAnswers'>";
								html+="<font color='red' style='margin-left:50px'>是否顺序：</font>";
								html+="<select name='list["+(number-1)+"].isOrder'>";
								html+="<option value='1'>顺序</option>";
								html+="<option value='0'>乱序</option>";
								html+="</select>";
								html+="</div>";
								html+="</div>";
								var newNumber=Number(number)+1;
								$("#addQue").val(newNumber);
								return html;
							}
							
							
							//填空
							function buildQue2(number){
								var html="<div id='topic_"+number+"' class='control-group'>";
								html+="<input type='hidden' name='list["+(number-1)+"].topicType' value='2'>";
								html+="<input type='hidden' name='list["+(number-1)+"].isOrder' value='3'>";
								html+="<input type='hidden' name='list["+(number-1)+"].serialNumber' value='"+number+"'>";
								html+="<label class='control-label'>问题"+number+"<span class='required'>*</span></label>";
								html+="<div class='controls'>";
								html+="<textarea type='text' required style='width:90%;height:100px' id='topic_"+number+"_textField' remark='topic_"+number+"'  oninput='textChange(this)' /></textarea>";
								html+="<button class='btn btn-default btn-extra-sm' style='background-color: #ffffff;float:right;top:0' name='topic_"+number+"' type='button' onclick='delType(this)'>X</button>";
								html+="<br><br>";
								html+="&nbsp;&nbsp;&nbsp;&nbsp;";
								html+="<input type='file' name='list["+(number-1)+"].document' value='' />";
								html+="&nbsp;&nbsp;&nbsp;&nbsp;";
								html+="已上传附件：<a href='${base}/userfiles/contactWebTopic/topicImg/${topicPicture!}' target='_blank'></a>";
								html+="<br><br>";
								html+="<font color='red'>正确答案：</font><span id='topic_"+number+"_example'></span>";
								html+="<input type='hidden' id='topic_"+number+"_topicText' name='list["+(number-1)+"].topicText'>";
								html+="<input type='hidden' id='topic_"+number+"_rightAnswers' name='list["+(number-1)+"].rightAnswers'>";
								html+="<br><br>";
								//html+="<button class='btn blue' type='button' remark='topic_"+number+"' onclick='setAnswer(this)'>设置答案</button>";
								html+="<span id='topic_"+number+"_answer' style='margin-left:30px'></span>";
								html+="</div>";
								html+="</div>";
								var newNumber=Number(number)+1;
								$("#addQue").val(newNumber);
								
								return html;
							}
							
							function selType(){
								$("#remark").html("");
								if($("#typeId").val()==""){
									$("#remark").html("<font color='red'>请选择类型!!</font>");
									return;
								}
							}
						
							
							$(document).ready(function() {
								$("#save_inputForm").validate({
									rules: {
										"topicName":{required:true},
										"twjfafl.id":{required:true},
										"complexity":{required:true},
										"textField":{required:true}						
									}
								});
								$(".chosen").each(function () {
						            $(this).chosen({
						                allow_single_deselect: $(this).attr("data-with-diselect") === "1" ? true : false,
						                no_results_text: "没有找到"
						        	});
						    	});						
							});
							
							CKEDITOR.replace('textField',
							{
								fullPage: true,
								filebrowserBrowseUrl: '${base}/ckfinder/ckfinder.html',
								filebrowserImageBrowseUrl: '${base}/ckfinder/ckfinder.html?type=Images',
								filebrowserFlashBrowseUrl: '${base}/ckfinder/ckfinder.html?type=Flash',
								filebrowserUploadUrl: '${base}/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files',
								filebrowserImageUploadUrl: '${base}/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images',
								filebrowserFlashUploadUrl: '${base}/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash',
								width:1440,
								height:400
							});
							
							function knowledge(){
								$("#btn").remove();
								if($("#twjfafl").val()!=""){
									$.post(
										"${base}/wjgl/twjwt!getKnowledge.action",
										{id:$("#twjfafl").val()},
										function(data){
											if(data!=""){
												var html="<div id='btn'><select id='selKnow'  data-placeholder=' ' name='knowledgePoint' class='chosen span15' multiple='multiple' tabindex='6' >";
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
							
							function _convert(str)
							{
								str=(str-2).toString();
							    var arr = ["B","C","D","E","F","G","H","I","J"];
							    for (var i = 0; i < 9; i++) {
							        str = str.replace(i, arr[i]);
							    }
							    return str.trim();
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
