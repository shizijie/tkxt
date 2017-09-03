<#include "/macro/crud.ftl" >

<@crud>

	<center><font size="5"><b>${wjfa.famc!}--调查结果统计</b></font></center>
	<table id="jgtjtb" class="input_table">
		<tr>
			<td class="textfield" style="width:15%"><center><b>分类</b></center></td>
			<td class="textfield" style="width:15%"><center><b>总计</b></center></td>
			<td class="textfield" style="width:35%"><center><b>问题</b></center></td>
			<td class="textfield" style="width:35%"><center><b>得分</b></center></td>
			<td class="textfield" style="width:15%"><center><b>调查统计</b></center></td>
		</tr>
		<#list wjfa.twjfafl as tmpfafl>
			<#list tmpfafl.flwt as tmpflwt>
				<#if tmpflwt.wtlx = "3" >
					<tr>
						<td>
							${tmpfafl.flmc!}
						</td>
						<td>
							${tmpflwt.wtmc!}
							</font>
						</td>
						<td>
							<font color=red>备注：主观题,无选项
						</td>
						<td>
							<#assign dps = 0>
							<#list gls as jj>
								<#if tmpflwt.id == jj[0]>
									<#assign dps = jj[1]>
								</#if>
							</#list>
							<a href="javascript:void(0)" onclick="modalShowWindow('${base}/wjgl/twjpc!txmd.action?sfzgt=Y&pcid=${pcid!}&wjid=${wjid!}&wtid=${tmpflwt.id!}')">
								<font color=blue size="2"><b>${dps!}</b></font>
							</a>
						</td>												
					</tr>						
				<#else>
					<#list tmpflwt.hdxlist as tmphdx>
						<tr>
							<td>
								${tmpfafl.flmc!}
							</td>
							<td>
								${tmpflwt.wtmc!}
							</td>
							<td>
								${tmphdx.damc!}
							</td>
							<td>
								<#assign dps = 0>
								<#list gls as jj>
									<#if tmphdx.id == jj[0]>
										<#assign dps = jj[1]>
									</#if>
								</#list>
								<#if dps != 0>
									<a href="javascript:void(0)" onclick="modalShowWindow('${base}/wjgl/twjpc!txmd.action?sfzgt=N&pcid=${pcid!}&wjid=${wjid!}&wthdxid=${tmphdx.id!}')">
										<font color=blue size="2"><b>${dps!}</b></font>
									</a>
								<#else>
										<font color=blue size="2"><b>${dps!}</b></font>
								</#if>
								
							</td>
						</tr>
					</#list>					
				</#if>				
			</#list>
		</#list>
	</table>

	<script>

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
	_w_table_rowspan("#jgtjtb",2)
	
	</script>
	
	
</@crud>
