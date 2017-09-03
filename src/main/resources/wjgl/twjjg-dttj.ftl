<#include "/macro/crud-metro-page3-nowebpst.ftl" >

<@crudmetropage3>

	<form id="query_form"  action="${base}/wjgl/twjjg!dttj.action" name="query_form"  method="post">
		<div class="row-fluid">
			<div class="span4">
				<div class="alert">
					问题分类：
					<@s.select name="selfl" id="selfl" list="allfl" listKey="id" listValue="flmc" cssClass="m-wrap span12" multiple="true" />
					
		      		<p>
		      			<input type="submit" value="统计" name="sub" >
		      			
		      			<!--
			      			<div class="controls">
			      				<h4>排序方式：</h4>
			      				<label class="radio">
									<input type="radio" name="paixu" value="1" >按题目次序
								</label>
								<label class="radio">
									<input type="radio" name="paixu" value="1" >按答题次数
								</label>
								<label class="radio">
									<input type="radio" name="paixu" value="2">按正确率
								</label>
							</div>
						-->
		      		</p>
				</div>
				
			</div>
		</div>
		
		<#if Parameters['sub']?exists>
			<div class="row-fluid">
				<div class="span12">
					<p style="float:right">
						<input type="button" value="打印" name="prnt" onclick="prn1_preview()">
						<input type="button" value="导出" onclick="doexpt()">
					</p>
				</div>
			</div>
			<div class="row-fluid" id="jgtjdiv">
				<div class="span12">
					<table id="jgtjtb" class="table table-bordered" >
						<tr>
							<td class="textfield" style="width:15%"><center><b>分类</b></center></td>
							<td class="textfield" style="width:35%"><center><b>问题</b></center></td>
							<td class="textfield" style="width:10%"><center><b>答题人次</b></center></td>
							<td class="textfield" style="width:10%"><center><b>正确</b></center></td>
							<td class="textfield" style="width:10%"><center><b>错误</b></center></td>
							<td class="textfield" style="width:10%"><center><b>正确率</b></center></td>
							<td class="textfield" style="width:10%"><center><b>错误率</b></center></td>
						</tr>
						
						<#if dttjls?exists>
							<#list dttjls as tmpls>
								<tr>
									<td style="text-align:center;vertical-align:middle;">${tmpls[0]}</td>
									<td>${tmpls[1]}</td>
									<td>${tmpls[2]}</td>
									<td>${tmpls[3]}</td>
									<td>${tmpls[4]}</td>
									<td>${(tmpls[3]/tmpls[2])*100}%</td>
									<td>${(tmpls[4]/tmpls[2])*100}%</td>
								</tr>
							</#list>
						</#if>
						
					</table>
				
				</div>
			</div>
		
			<#-- 打印方法 -->
			<script language="JavaScript" type="text/JavaScript" src="${base}/scripts/js/LodopFuncs.js"></script>
			<object id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0> 
				<embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0 pluginspage="install_lodop32.exe"></embed>
			</object>
			<script>
				//打印方法
				function prn1_preview() {	
					 //var LODOP = getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));    
					 var LODOP = getLodop();
					 LODOP.PRINT_INIT("答题统计结果");
					 
					 var content="";
					 var contentHeight=0;
					 
					 var tmpcot = $("#jgtjdiv").clone();
					 tmpcot.find("table").attr("border","1");
					 content = tmpcot.html();
					 LODOP.SET_PRINT_STYLEA(1, "FontSize", 14);
					 LODOP.ADD_PRINT_HTM(0,0,"100%","100%","<center><h1>答题统计结果</h1></center>");
					 LODOP.ADD_PRINT_HTM(50,0,"100%","100%",content);
					 LODOP.NewPageA();
					 LODOP.PREVIEW();
					
			    };
			</script>		
		</#if>
		
	</form>
	<script>
		$(document).ready(function() {
			$("#query_form").validate({
				rules: {
					selfl:{required:true}
				}
			});
		});
		
		function doexpt()
		{
			  var form1 = $("<form>");  
	          form1.attr('style','display:none');  
	          form1.attr('target','');
	          form1.attr('method','post');  
	          form1.attr('action',"${base}/wjgl/twjjg!dttjexpt.action"); 

			  $("#selfl").clone().prependTo(form1);    	          
	          $("body").append(form1);
	          
	          form1.submit(); 
	          form1.remove(); 
	          
	          return;
		}
		
		function _w_table_rowspan(_w_table_id,_w_table_colnum){  
		    _w_table_firsttd = "";  
		    _w_table_currenttd = "";  
		    _w_table_SpanNum = 0;  
		    _w_table_Obj = $(_w_table_id + " tr td:nth-child(" + _w_table_colnum + ")");  
		    _w_table_Obj.each(function(i){  
		        if(i==0){  
		            _w_table_firsttd = $(this);  
		            _w_table_SpanNum = 1;  
		        }else{  
		            _w_table_currenttd = $(this);  
		            if(_w_table_firsttd.text()==_w_table_currenttd.text()){  
		                _w_table_SpanNum++;  
		                _w_table_currenttd.hide(); //remove();   
		                _w_table_firsttd.attr("rowSpan",_w_table_SpanNum);  
		            }else{  
		                _w_table_firsttd = $(this);  
		                _w_table_SpanNum = 1;  
		            }  
		        }  
		    });   
		}  		
		
		_w_table_rowspan("#jgtjtb",1)
		//_w_table_rowspan("#jgtjtb",2)
	</script>	

</@crudmetropage3>
