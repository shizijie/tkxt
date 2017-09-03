<#include '/macro/crud-metro-page3-nowebpst.ftl' >
<#assign dispJDLX={"1":"课程", "2":"章节", "3":"知识点"}>
<@crudmetropage3>

<link href="${base}/scripts/treeTable/stylesheets/master.css" rel="stylesheet" type="text/css" />
  <script type="text/javascript" src="${base}/scripts/treeTable/javascripts/jquery.ui.js"></script>
  <link href="${base}/scripts/treeTable/stylesheets/jquery.treeTable.css" rel="stylesheet" type="text/css" />
  <script type="text/javascript" src="${base}/scripts/treeTable/javascripts/jquery.treeTable.js"></script>
  
  <script type="text/javascript"  src="${base}/mainface/js/highcharts.js"></script>
  <script type="text/javascript"  src="${base}/mainface/js/highcharts-3d.js"></script>
  	
  	
	
				<#if id?exists>
					<input type="hidden" name="id" value="${id}"/>
				</#if>
				<!--现有题目信息-->
				<div class="form-horizontal  form-bordered">
				<div class="portlet-title">
					<div class="caption"><i class="icon-edit"></i>
					测试任务
					</div>
				</div>
				<div class="control-group">
					<div class="span6">
					<label class="control-label">任务名称:<span class="required">*</span></label>
					<div class="controls">
						${entity.rwmc!}
					</div>
					</div>
					<div class="span6">
					<label class="control-label">考试科目:<span class="required">*</span></label>
					<div class="controls">
						${entity.fafl?if_exists.flmc!}
					</div>
					</div>
				</div>
				<div class="control-group">
					<div class="span6">
					<label class="control-label">考试题量:<span class="required">*</span></label>
					<div class="controls">
						<#if entity.zjxx??>
						${entity.zjxx!}
						<br><br>	
						</#if>
						<#if entity.jxInfo??>
				  		机械制图：<br>
				  		<#list entity.jxInfo?if_exists?split(",") as lx>
				  			<#if (lx?substring(0,lx?index_of(":"))?length>=8)>
					  			${lx?substring(0,lx?index_of(":"))?substring(0,8)}
					  		<#else>
					  			${lx?substring(0,lx?index_of(":"))}
				  			</#if>
				  				${lx?substring(lx?index_of(":"),lx?length)}<br>
				  		</#list>
				  		</#if>
				  		<#if entity?if_exists.topicInfo?if_exists!="0">
				  		${entity.topicInfo!}
				  		</#if>
					</div>
					</div>
					<div class="span6">
					<label class="control-label">考试分值:<span class="required">*</span></label>
					<div class="controls">
						<#if entity.score??>
						${entity.score!}
						<br><br>
						</#if>
						<#if entity.jxScore??>
				  		机械制图：<br>
				  		<#list entity.jxScore?if_exists?split(",") as lx>
				  			<#if (lx?substring(0,lx?index_of(":"))?length>=8)>
					  			${lx?substring(0,lx?index_of(":"))?substring(0,8)}
					  		<#else>
					  			${lx?substring(0,lx?index_of(":"))}
				  			</#if>
				  				${lx?substring(lx?index_of(":"),lx?length)}<br>
				  		</#list>
				  		</#if>
				  		<#if entity?if_exists.topicScore?if_exists!="0"&&obj?if_exists.topicScore?if_exists!="">
				  		综合题：
				  		${entity.topicScore!}
				  		</#if>
					</div>
					</div>
				</div>
				<br/>
				
				<div class="row-fluid">
					<!--
					<div class="span3">
						<div class="portlet">
							<div class="portlet-title">
								<div class="caption"><i class="icon-reorder"></i>章节分布情况</div>
								<div class="actions" id="pagemenubutton" ></div>
							</div>
							<div class="portlet-body">
								<div id="zjfbqk"></div>
							</div>
						</div>
					</div>
					-->
					<div class="span4">
						<div class="portlet">
							<div class="portlet-title">
								<div class="caption"><i class="icon-reorder"></i>题型分布情况</div>
								<div class="actions" id="pagemenubutton" ></div>
							</div>
							<div class="portlet-body">
								<div id="txfbqk"></div>
							</div>
						</div>
					</div>
					<div class="span4">
						<div class="portlet">
							<div class="portlet-title">
								<div class="caption"><i class="icon-reorder"></i>难易程度分布情况</div>
								<div class="actions" id="pagemenubutton" ></div>
							</div>
							<div class="portlet-body">
								<div id="ndfbqk"></div>
							</div>
						</div>
					</div>
					<div class="span4">
						<div class="portlet">
							<div class="portlet-title">
								<div class="caption"><i class="icon-reorder"></i>知识点分布情况</div>
								<div class="actions" id="pagemenubutton" ></div>
							</div>
							<div class="portlet-body">
								<div id="zsdfbqk"></div>
							</div>
						</div>
					</div>
				</div>
				<!--现有题目信息-->
				
				<!--组卷操作-->
				<div class="row-fluid">
					<div class="span12">
						<div class="portlet">
							<div class="portlet-title">
								<div class="caption"><i class="icon-reorder"></i>1、设置题型量/难易度比例/知识点覆盖</div>
								<div class="actions" id="pagemenubutton" ></div>
							</div>
							<div class="portlet-body">
							
								<table class="table table-bordered">
									<thead style="background-color: #E8F5F7">
										<tr>
											<th rowspan="2" style="text-align: center;vertical-align:middle">题型</th>
											<th rowspan="2" style="text-align: center;vertical-align:middle">题量</th>
											<th colspan="2" style="text-align: center;vertical-align:middle"><center>难易程度比例（%）</center></th>
											<th colspan="2" style="text-align: center;vertical-align:middle">知识点覆盖</th>
											<th rowspan="2" style="text-align: center;vertical-align:middle">设置分值</th>
											<th rowspan="2" style="text-align: center;vertical-align:middle">组卷</th>
										</tr>
										<tr>
											<th style="text-align: center;vertical-align:middle">难易设置</th>
											<th style="text-align: center;vertical-align:middle">所设比例</th>
											<th style="text-align: center;vertical-align:middle">知识点设置</th>
											<th style="text-align: center;vertical-align:middle">所设比例</th>
										</tr>
									</thead>
									<#list dict as lx>
									<tr id="${lx.dm}" >
										<td><center>${lx.name}</center></td>
										<td>
											<center>
												<select id="${lx.dm}_tl" data-placeholder=' ' name="${lx.dm}_tl" class="chosen" style="width:80px" maxlength="3" value="">
													<option value=""></option>
													<#list 1..lx.remark?number as num>
													<option value="${num}">${num}</option>
													</#list>
												</select>
											</center>
										</td>
										
										
										<td>
											<center>
												<button class="btn mini purple" onclick="setNy('${lx.dm}')">难易设置</button>
												&nbsp;&nbsp;&nbsp;
												<button class="btn mini red" pid='ny_${lx.dm}' onclick='setRandom(this)'>重置为随机</button>
											</center>
										</td>
										<td>
											<center>
												<font color='red'><span id='ny_${lx.dm}'>随机</span></font>
												<input type='hidden' id='ny_hidden_${lx.dm}' value="">
											</center>
										</td>
										
										<td>
											<center>
												<button class="btn mini green" onclick="setKnow('${lx.dm}')">知识点设置</button>
												&nbsp;&nbsp;&nbsp;
												<button class="btn mini red" pid='know_${lx.dm}' onclick='setRandom(this)'>重置为随机</button>
											</center>
										</td>
										<td>
											<center>
												<font color='red'><span id='know_${lx.dm}'>随机</span></font>
												<input type='hidden' id='know_hidden_${lx.dm}' value="">
											</center>
										</td>
										
										
										
										<td>
											<center>
												<#if lx?if_exists.dm?if_exists=="zht">
												多选题：
												<input type="hidden" id="${lx.dm}_grade" name="${lx.dm}_grade" >
												<input type="text" id="${lx.dm}_grade_1" name="${lx.dm}_grade_1" style="width:40px" value="" maxlength="3">
												填空题：
												<input type="text" id="${lx.dm}_grade_2" name="${lx.dm}_grade_2" style="width:40px" value="" maxlength="3">
												<#else>
												<input type="text" id="${lx.dm}_grade" name="${lx.dm}_grade" style="width:40px" value="" maxlength="3">
												</#if>
											</center>
										</td>
										<td>
											<a class="btn mini blue" href="javascript:;" onclick="kszj('${lx.dm}')"><i class="icon-share"></i> 开始组${lx.name}</a>
											<font color='red'><span class='remark' id='remark_${lx.dm}'></span></font>
										</td>
									</tr>
									</#list>
									
								</table>
								
								<form id='jxtable' action="${base}/wjgl/csrw!saveJxzt.action" method="post">
								<#if id?exists>
									<input type="hidden" name="id" value="${id}"/>
								</#if>
								<table class="table table-bordered">
									<thead style="background-color: #E8F5F7">
									<tr>
										<th colspan="12" style="text-align: center;vertical-align:middle">机械制图模块</th>
									</tr>
									</thead>
									<tr>
										<td>
											<center><label class="control-label">知识点 \ 选择分类:<span class="required">*</span></label></center>
										</td>
										<td>
											<center>
												<select data-placeholder=" "  class="chosen span12" tabindex="-1" id="type"  name="type"  onchange="getJx()">
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
											</center>
										</td>
										<!--
										<td>
											<center>
												<select id="jxzt_tl" data-placeholder=' ' name="jxzt_tl" class="chosen" style="width:80px" maxlength="3">
													<option value=""></option>
													<#list 1..jxztNum?number as num>
													<option value="${num}">${num}</option>
													</#list>
												</select>
												<input type="hidden" id="addSec">
											</center>
										</td>
										<td>
											<center>
											<input type="text" id="jxzt_grade">
											</center>
										</td>
										<td>
											<center>
												<a class="btn mini blue" href="javascript:;" onclick="jxztzj()"><i class="icon-share"></i> 开始组机械制图题</a>
												<span id='jxzt_xx'></span>
											</center>
										</td>
										-->	
									</tr>
									<input id="addJx" type="hidden">
								</table>
								</form>								
							</div>
						</div>
					</div>
				</div>
				<!--组卷操作-->
				
				<!--组卷结果-->
				<div class="row-fluid">
					<div class="span12">
						<div class="portlet">
							<div class="portlet-title">
								<div class="caption"><i class="icon-reorder"></i>2、组卷结果</div>
								<div class="actions" id="pagemenubutton" ></div>
							</div>
							<div class="portlet-body">
								<table style="width:100%">
								<#list dict?chunk(4) as row>
									<tr>
										<#list row as obj>
										<td style="width:25%">
											<table class="table table-bordered" style="width:100%" id="${obj.dm}_zjjg">
												<thead style="background-color: #E8F5F7">
													<tr>
														<th colspan="2">${obj.name}</th>
													</tr>
												</thead>
												<tr>
													<td width="20%">生成题量</td>
													<td id="${obj.dm}_sctl"></td>
												</tr>
												<tr>
													<td>难易度占比分布</td>
													<td id="${obj.dm}_scndzb"></td>
												</tr>
												<tr>
													<td>知识点分布</td>
													<td id="${obj.dm}_sczsdfb"></td>
												</tr>
											</table>
										</td>
										</#list>
									</tr>
								</#list>
								</table>
								
							</div>
						</div>
					</div>
				</div>
				<!--组卷结果-->
				
				<#macro genTreeMenu children>
					<#if (children?exists&&children?size>0)>
						<#list children as obj>
							<tr title="${obj.id}" id="node-${obj.id}" <#if obj.fparent?exists >class="<#if (obj.children?exists&&obj.children?size>1)>parent</#if> child-of-node-${obj.fparent.id}"</#if>>
								<td>${obj.flmc?if_exists}</td>
								<td>${dispJDLX[obj.jdtype]}</td>
								<td>
									<#if obj.jdtype?exists && obj.jdtype == "3">
										<input type="text" id="zxdzb-${obj.id}" name="zxdzb-${obj.id}" pid='${obj.flmc?if_exists}' maxlength="3" style="width:40px">%
									</#if>
								</td>
							</tr>
							<#if obj.children?exists>
								<@genTreeMenu children=obj.children/>
							</#if>
						</#list>
					</#if>
				</#macro>
				
				<!--章节知识点模态框-->
				<div style="display: none;" tabindex="-1" class="modal hide fade in" id="zdzjModal"  aria-hidden="false">
					<div class="modal-body" style="height:100%">
						<!--设置章节知识点-->
						
						<table id="resourceTable" class="table table-bordered treeTable">
							<thead>
								<tr style="background-color: #E8F5F7">
									<th>名称</th>
									<th>类型</th>										
									<th>&nbsp;&nbsp;&nbsp;&nbsp;</th>
								</tr>
							</thead>
							<#assign obj = entity.fafl>
							<#if obj?exists>
							<tbody>
								<tr title="${obj.id}" id="node-${obj.id}" <#if obj.fparent?exists >class="<#if (obj.children?exists&&obj.children?size>1)>parent</#if> child-of-node-${obj.fparent.id}"</#if>>
									<td>${obj.flmc?if_exists}</td>									
									<td><#if obj.jdtype?exists>${dispJDLX[obj.jdtype]}</#if></td>
									<td>
										<#if obj.jdtype?exists && obj.jdtype == "3">
											<input type="text" id="zxdzb-${obj.id}" name="zxdzb-${obj.id}" pid='${obj.flmc}' maxlength="3" style="width:40px">%
										</#if>
									</td>
								</tr>
								<#if obj.children?exists>
									<@genTreeMenu children=obj.children/>
								</#if>
							</tbody>
							</#if>
							<input id='addKnowTbale' type='hidden'>
						</table>
						
						<!--设置章节知识点-->	
					</div>
					<div class="modal-footer">
						<a href="javascript:;"  class="btn blue" onclick="qdzsdfg()">确定</a>
				        <a href="javascript:;"  class="btn" data-dismiss="modal">关闭</a>
				    </div>
				</div>
				
				<!--难易模态框-->
				<div style="display: none;" tabindex="-1" class="modal hide fade in" id="nyModal"  aria-hidden="false">
					<div class="modal-body" style="height:100%">
						<!--设置难易-->
						<table id="resourceTable" class="table table-bordered treeTable">
							<thead>
								<tr style="background-color: #E8F5F7">
									<th>难易类型</th>
									<th>设置比例</th>
								</tr>
							</thead>
							<input id='addNyTbale' type='hidden'>
						</table>
						
						<!--设置难易-->	
					</div>
					<div class="modal-footer">
						<a href="javascript:;"  class="btn blue" onclick="saveNy()">确定</a>
				        <a href="javascript:;"  class="btn" data-dismiss="modal">关闭</a>
				    </div>
				</div>
				
				<style>
					#zdzjModal
					{
						width: 1000px;
						height:500px;
						margin: 0 0 0 -370px; 
					}
					#nyModal
					{
						width: 1000px;
						height:500px;
						margin: 0 0 0 -370px; 
					}
				</style>
				
				<script type="text/javascript">
					function setKnow(txstr){
						txtype = txstr;
						$("#showKnow").remove();
						$("#addKnowTbale").val(txtype);
						$("input[name^=zxdzb-]").each( function(){
							$(this).val("");
						});
						$('#zdzjModal').modal({backdrop: 'static', keyboard: false});
					}
					
				
				
				
					function setNy(txstr){
						txtype = txstr;
						$("#showNy").remove();
						$("#addNyTbale").val(txtype);
						$.post(
							"${base}/wjgl/csrw!getDistribute.action",
							{
								tx:txtype,
								flId:${entity.fafl?if_exists.id!},
								type:1
							},
							function(data){
								if(data!=""){
									var html="<tbody id='showNy'>";
									$.each(eval(data),function(i,n){
										html+="<tr><td>"+replaceNy(n[0])+"</td><td><input type='text' class='ny' name='"+n[0]+"' id='' >%</td></tr>";
									})
									html+="</tbody>";
									$("#addNyTbale").before(html);
								}
							}	
						);
						$('#nyModal').modal({backdrop: 'static', keyboard: false});
					}
					
					function saveNy(){
						var bili=0;
						var str="";
						var strSave="";
						var isTrue=true;
						$(".ny").each(function(){
							if(isNaN($(this).val())){
								alert("请输入合法数字");
								isTrue=false;
								return false;
							}
							if(Number($(this).val())<=0&&$(this).val()!=""){
								alert("请输入合法数字");
								isTrue=false;
								return false;
							}
							bili+=Number($(this).val());
							if(Number($(this).val())>0){
								str+=replaceNy($(this).attr('name'))+":"+Number($(this).val())+"%,";
								strSave+=$(this).attr('name')+":"+Number($(this).val())+",";
							}
						});
						if(!isTrue){
							return;
						}
						if(bili==100){
							if(str.indexOf(",")!=-1){
								$("#ny_"+$("#addNyTbale").val()).text(str.substring(0,str.length-1));
								$("#ny_hidden_"+$("#addNyTbale").val()).val(strSave.substring(0,strSave.length-1));
							}
							$("#nyModal").modal("hide");
						}else{
							alert("比例设置错误！！");
						}
					}
					
					function setRandom(obj){
						var name=$(obj).attr('pid');
						$("#"+name).text("随机");
					}
					
					function replaceNy(str){
						str=str.replace("A","容易");
						str=str.replace("B","一般");
						str=str.replace("C","中等");
						str=str.replace("D","较难");
						str=str.replace("E","困难");
						str=str.replace("NULL","无");
						return str;
					}
					
					function getInfo(){
						if($("#type").val()=="null"){
							$("#jxzt_tl").remove();
							$("#jxzt_tl_chzn").remove();
							var html="<select id='jxzt_tl' data-placeholder=' ' name='jxzt_tl' class='chosen' style='width:80px' maxlength='3'>";
							html+="<option value=''></option>";
							<#list 1..jxztNum?number as num>
							html+="<option value='"+${num}+"'>"+${num}+"</option>";
							</#list>
							html+="</select>";
							$("#addSec").before(html);
							$(".chosen").each(function () {
				            	$(this).chosen({
					                allow_single_deselect: $(this).attr("data-with-diselect") === "1" ? true : false,
					                no_results_text: "没有找到"
					        	});
							});						
						}else{
							$.post(
								"${base}/wjgl/csrw!getJxNumber.action",
								{jxztId:$("#type").val()},
								function(data){
									$("#jxzt_tl").remove();
									$("#jxzt_tl_chzn").remove();
									var html="<select id='jxzt_tl' data-placeholder=' ' name='jxzt_tl' class='chosen' style='width:80px' maxlength='3'>";
									html+="<option value=''></option>";
									for(var i=0;i<data;i++){
										html+="<option value="+(i+1)+">"+(i+1)+"</option>";
									}
									html+="</select>";
									$("#addSec").before(html);
									$(".chosen").each(function () {
						            	$(this).chosen({
							                allow_single_deselect: $(this).attr("data-with-diselect") === "1" ? true : false,
							                no_results_text: "没有找到"
							        	});
									});
								}
							);
						}
					}
					
					function getJx(){
						$(".test").remove();
						if($("#type").val()=="null"){
							return;
						}else{
							$.post(
								"${base}//wjgl/jxzj!getInfo.action",
								{id:$("#type").val()},
								function(data){
									var num=0;
									$(".test").remove();
									if(data!=""){
										var html="";
										$.each($.parseJSON(data),function(i,n){
											if(n.remark>0){
												html+="<tr class='test'><td>";
												html+="<center><label class='control-label'>"+n.typeName+"<input type='hidden' name='dxtmlist["+num+"].wtlx' value='"+n.id+"'/></label></center>";
												html+="</td>"
												html+="<td><center>";
												html+="<select id='jxzt_tl_"+num+"' data-placeholder='题量' name='dxtmlist["+num+"].wtmc' class='chosen span6'  maxlength='3'>";
												html+="<option value=''></option>";
												var number1=n.remark;
												for(var i=0;i<n.remark;i++){
													html+="<option value='"+(i+1)+"'>"+(i+1)+"</option>";
												}
												html+="</select></center></td>";
												html+="<td><center>";
												html+="<input type='text' placeholder='分值' id='jxzt_grade_"+num+"' name='dxtmlist["+num+"].bz'>";
												html+="</center></td></tr>";
												num++;
											}
										});
										if(html!=""){
											html+="<tr class='test'><td  colspan='3'><center>";
											html+="<a class='btn mini blue' href='javascript:;' onclick='jxztzj()' id='btnZj' num='"+num+"'><i class='icon-share'></i> 开始组机械制图题</a>";
											html+="<span id='jxzt_xx'></span>";
											html+="</center></td></tr>";
										}else{
											html="<tr class='test'><td colspan='3'><center><span ><font color='red'>暂无题目</font></span></center></td></tr>";
										}
										
										$("#addJx").before(html);
										$(".chosen").each(function () {
						            		$(this).chosen({
								                allow_single_deselect: $(this).attr("data-with-diselect") === "1" ? true : false,
								                no_results_text: "没有找到"
								        	});
										});
									}else{
										var html="<tr class='test'><td colspan='3'><center><span ><font color='red'>暂无题目</font></span></center></td></tr>";
										$("#addJx").before(html);
									}
								}
							);
						}
					}
					
					function jxztzj(){
						var number=Number($("#btnZj").attr('num'));
						var isNull=true;
						for(var i=0;i<number;i++){
							if($("#jxzt_grade_"+i).val()!=""&&isNaN($("#jxzt_grade_"+i).val())){
								alert("请输入正确分值！");
								return;
							}
							if(($("#jxzt_tl_"+i).val()!=""&&$("#jxzt_grade_"+i).val()=="")||($("#jxzt_tl_"+i).val()==""&&$("#jxzt_grade_"+i).val()!="")){
								alert("题量/分值不能为空！");
								return;
							}
							if($("#jxzt_tl_"+i).val()!=""||$("#jxzt_grade_"+i).val()!=""){
								isNull=false;
							}
						}
						if(isNull){
							alert("题量/分值不能为空！");
							return;
						}
						//$("#jxtable").submit();
						$.ajax({
							type:"POST",
							url:"${base}/wjgl/csrw!saveJxzt.action",
							data:$("#jxtable").serialize(),
							success:function(data){
								if(data=="ok"){
									$("#jxzt_xx").html("<font color='red'>出卷成功！</font>");
									alert("出卷成功！");
								}
							}
						});
					}
					
					showHighcharts();
					
					function showHighcharts(){
						var arr1=[];
						var arr2=[];
						var arr3=[];
						var res=eval(${dictjson});
						$.each(res.type,function(i,n){
							var obj={};
							obj.name=n.wtlx;
							obj.y=Number(n.bz);
							arr1.push(obj);
						});
						$.each(res.ny,function(i,n){
							var obj={};
							obj.name=n.wtlx;
							obj.y=Number(n.bz);
							arr2.push(obj);
						});
						$.each(res.know,function(i,n){
							var obj={};
							obj.name=n.wtlx;
							obj.y=Number(n.bz);
							arr3.push(obj);
						});
						$('#txfbqk').highcharts({
							chart: {
								plotBackgroundColor: null,
								plotBorderWidth: null,
								plotShadow: false
							},
							title: {
								text: ''
							},
							tooltip: {
								//pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
							},
							plotOptions: {
								pie: {
									allowPointSelect: true,
									cursor: 'pointer',
									dataLabels: {
								    	enabled: true,
								    	format: '<b>{point.name}</b>: {point.percentage:.1f} %',
								    	style: {
								    	color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
										}
									}
								}
							},
							series: [{
								type: 'pie',
								name: '题目数',
								data: arr1
							}]
						});
						$('#ndfbqk').highcharts({
							chart: {
								plotBackgroundColor: null,
								plotBorderWidth: null,
								plotShadow: false
							},
							title: {
								text: ''
							},
							tooltip: {
								 //pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
							},
							plotOptions: {
								pie: {
									allowPointSelect: true,
									cursor: 'pointer',
									dataLabels: {
										enabled: true,
										format: '<b>{point.name}</b>: {point.percentage:.1f} %',
										style: {
										color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
										}
									}
								}
							},
							series: [{
								type: 'pie',
								name: '题目数',
								data: arr2
							}]
						});
						$('#zsdfbqk').highcharts({
							chart: {
								plotBackgroundColor: null,
								plotBorderWidth: null,
								plotShadow: false
							},
							title: {
								text: ''
							},
							tooltip: {
								//pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
							},
							plotOptions: {
								pie: {
									allowPointSelect: true,
									cursor: 'pointer',
									dataLabels: {
										enabled: true,
										format: '<b>{point.name}</b>: {point.percentage:.1f} %',
										style: {
											color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
										}
									}
								}
							},
							series: [{
								type: 'pie',
								name: '题目数',
								data: arr3
							}]
						});
					}
									
					function qinchu(tmlx)
					{
						$("#"+tmlx+"_zsdfg_xs").html("");
						$("#"+tmlx+"_zsdfg_val").val("");
					}
					function kszj(tmlx)
					{
						//tl题量
						var tl = $("#"+tmlx+"_tl").val();
						var ny=false;
						var know=false;
						var nylength=${dxtmlist?size};
						var knowlength=${yxtmlist?size};
						var ny=0;
						var know=0;
						var nystr="";
						var knowstr="";
						nystr=$("#ny_hidden_"+tmlx).val();
						knowstr=$("#know_hidden_"+tmlx).val();
						//if((ny!=0&&ny!=100)||(know!=0&&know!=100)){
						//	alert("请填写正确比例");
						//	return;
						//}
						
						if(typeof(tl) != "undefined" && tl != "")
						{
						}else{
							alert("请填写题量");
							return;
						}
						if(tmlx=="zht"){
							if(($("#"+tmlx+"_grade_1").val().trim()=="")||($("#"+tmlx+"_grade_2").val().trim()=="")){
								console.log("1");
								alert("请填写分值！！");
								return;
							}
							$("#"+tmlx+"_grade").val("多选题:"+$("#"+tmlx+"_grade_1").val().trim()+",填空题:"+$("#"+tmlx+"_grade_2").val().trim());
						}else{
							if($("#"+tmlx+"_grade").val().trim()==""){
								console.log("2111");
								alert("请填写分值！！");
								return;
							}
						}
						
						if(nystr.length>0){
							nystr=nystr.substring(0,nystr.length-1);
						}
						if(knowstr.length>0){
							knowstr=knowstr.substring(0,knowstr.length-1);
						}
						//验证难度占比
						
						//提交组卷
						$.ajax({
						   type: "POST",
						   url: "${base}/wjgl/csrw!savePaper.action",
						   data: {
						   	tmlx:tmlx,
						   	rwid:"${id!}",
						   	tms:tl,
						   	ndfb:nystr,
						   	zsdfb:knowstr,
						   	grade:$("#"+tmlx+"_grade").val(),
						   },
						   success: function(msg){
						   		if(typeof(msg) != "undefined" && msg != "")
						   		{
						   			console.log(msg);
						   			var jsarr = msg.split("|");
						   			$("#"+tmlx+"_sctl").html(jsarr[0]);
						   			$("#"+tmlx+"_scndzb").html(jsarr[1]);
						   			$("#"+tmlx+"_sczsdfb").html(jsarr[2]);
						   			$("#remark_"+tmlx).html("出卷成功！！");
						   		}
						   		 
						   },
						   error:function(){
						   		$("#remark_"+tmlx).html("保存存时出错了！");
						   }
					   });
					   setTimeout("cleanRemark()","2000");
					}
					function cleanRemark(){
						$(".remark").each(function(){
							$(this).html("");
						});
					}
					
					$(document).ready(function(){
						
						/*
						$('#query_form').validate();
						
						$("input[type=text]").each( function(){
							$(this).rules( "add", {
								number:true
							})
						});
						*/
						
						$(".chosen").each(function () {
						            $(this).chosen({
						                allow_single_deselect: $(this).attr("data-with-diselect") === "1" ? true : false,
						                no_results_text: "没有找到"
						        	});
						});
						
						$("#resourceTable").treeTable();
	
					    $("#resourceTable .file, #resourceTable .folder").draggable({
					      helper: "clone",
					      opacity: .75,
					      refreshPositions: true, // Performance?
					      revert: "invalid",
					      revertDuration: 300,
					      scroll: true
					    });
					    $("#resourceTable .folder").each(function() {
					      $($(this).parents("tr")[0]).droppable({
					        accept: ".file, .folder",
					        drop: function(e, ui) { 
					          $($(ui.draggable).parents("tr")[0]).appendBranchTo(this);
					        },
					        hoverClass: "accept",
					        over: function(e, ui) {
					          if(this.id != ui.draggable.parents("tr.parent")[0].id && !$(this).is(".expanded")) {
					            $(this).expand();
					          }
					        }
					      });
					    });		
					    
					    $("table#resourceTable tbody tr").each(function(){
					    	var srcClass = $(this).attr("class");
					    	if(srcClass.indexOf("collapsed") >= 0)
					    	{
					    		$(this).find("td > span").trigger("click");
					    	}
					   });
				});
				var txtype = "";
				function qdzsdfg()
				{
					var flg = false;
					var allbl = 0;
					var xsstr = "";//界面上显示的字符串
					var hidstr = "";//隐藏域的值
					$("input[name^=zxdzb-]").each( function(){
						var vtmp = $(this).val();
						//console.log($(this).attr('pid'));
						allbl = Number(vtmp) + allbl;
						var vnam = $(this).attr("name");
						if(isNaN(vtmp))
						{
							flg = true;
						}else{
							var trtmp = $(this).parent().parent();
							
							if(typeof(vtmp)!="undefined"&&vtmp != "")
							{
								xsstr += trtmp.find("td").eq(0).html() + ":" + vtmp + "%,";
								hidstr += trtmp.find("td").eq(0).html() + ":" + vtmp + ",";
							}
							
						}
					});
					if(flg)
					{
						alert("输入的比例中填写了非法数字");
						return;
					}
					if(allbl != 100)
					{
						alert("知识点覆盖占比不为100%");
						return;
					}
					
					if(txtype != "")
					{
						$("#know_"+$("#addKnowTbale").val()).text(xsstr.substring(0,xsstr.length-1));
						$("#know_hidden_"+$("#addKnowTbale").val()).val(hidstr.substring(0,hidstr.length-1));
					}
					
					$("#zdzjModal").modal("hide");
				}
				
			</script>
			</div>	
</@crudmetropage3>