<#include "/macro/crud-metro-page3-nowebpst.ftl" >

<@crudmetropage3>

	<form id="query_form"  action="${base}/wjgl/twjjg!bjdttj.action" name="query_form"  method="post">
		<div class="row-fluid">
			<div class="span1"></div>
			<div class="span10 booking-search">
				<div class="clearfix margin-bottom-10">
					<div class="control-group pull-left margin-right-20">
						<label><font color=red>开始时间：</font></label>
						<div class="controls">
							<input readonly id="ksrq" name="ksrq" value="${Parameters['ksrq']?if_exists}" type="text" class="m-wrap" onclick="WdatePicker({el:'ksrq',dateFmt:'yyyy-MM-dd'})"/>
						</div>
					</div>
					<div class="control-group pull-left margin-right-20">
						<label><font color=red>结束时间：</font></label>
						<div class="controls">
							<input readonly id="jsrq" name="jsrq" value="${Parameters['jsrq']?if_exists}" type="text" class="m-wrap" onclick="WdatePicker({el:'jsrq',dateFmt:'yyyy-MM-dd'})"/>
						</div>
					</div>
					
					<div class="control-group pull-left margin-right-20">
						<label>班级：</label>
						<div class="controls">
							<select id="bj" name="bj" class="m-wrap">
								<option value="">==全部==</option>
								<#list bjlist as obj>
									<option value="${obj.id}" <#if Parameters['bj']?exists && Parameters['bj'] == obj.id>selected</#if>>${obj.bjmc}</option>
								</#list>
							</select>
						</div>
					</div>
					
					<div class="control-group pull-left margin-right-20">
						<label>&nbsp;</label>
						<div class="controls">
							<button class="btn blue" name="sub" type="submit" value="sub">统计 <i class="m-icon-swapright m-icon-white"></i></button>
							<button class="btn" type="button" id="reset">重置 </button>
						</div>
					</div>
					
				</div>
			</div>
			<div class="span1"></div>
		</div>
		
		<#if Parameters['sub']?exists>
			<div class="row-fluid">
				<div class="span1"></div>
				<div class="span10">
					<p style="float:right">
						
						<input type="button" value="打印" name="prnt" onclick="prn1_preview()">
						<!--
						<input type="button" value="导出" onclick="doexpt()">
						-->
					</p>
				</div>
				<div class="span1"></div>
			</div>
			<div class="row-fluid" id="jgtjdiv">
				<div class="span1"></div>
				<div class="span10">
					<table id="jgtjtb" class="table table-bordered" style="width:100%">
						<tr>
							<td style="background:#E8F5F7"><center><b>班级</b></center></td>
							<td style="background:#E8F5F7" ><center><b>答题人次</b></center></td>
							<td style="background:#E8F5F7" ><center><b>最高分</b></center></td>
							<td style="background:#E8F5F7" ><center><b>最低分</b></center></td>
							<td style="background:#E8F5F7" ><center><b>平均分</b></center></td>
							<td style="background:#E8F5F7" ><center><b>100-90分</b></center></td>
							<td style="background:#E8F5F7" ><center><b>90-80分</b></center></td>
							<td style="background:#E8F5F7" ><center><b>80-70分</b></center></td>
							<td style="background:#E8F5F7" ><center><b>70-60分</b></center></td>
							<td style="background:#E8F5F7" ><center><b>60分以下</b></center></td>
						</tr>
						
						<#if bjdttjjgls?exists>
							<#list bjdttjjgls as tmpls>
								<tr>
									<#assign i = 0>
									<#list tmpls?split(",") as oj>
										<#if i == 2 || i == 3 || i == 4>
											<td style="text-align:center;vertical-align:middle;">${oj?number}</td>
										<#else>
											<td style="text-align:center;vertical-align:middle;">${oj!}</td>
										</#if>
										<#assign i = i + 1>
									</#list>
								</tr>
							</#list>
						</#if>
						
					</table>
				</div>
				<div class="span1"></div>
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
					ksrq:{required:true},
					jsrq:{required:true},
					bjlist:{required:true}
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
		
		_w_table_rowspan("#jgtjtb",1);
		//_w_table_rowspan("#jgtjtb",2)
		
		$("#reset").click(function(){
			$("#ksrq").val("");
			$("#jsrq").val("");
			$("#bj").val("");
		});
	</script>	

</@crudmetropage3>
