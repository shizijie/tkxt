<#include "/macro/crud-metro-page-blank.ftl" >

<@crudmetropageblank>

	<center><h2><b>${wjfa.famc!}--调查结果统计</b></h2></center>
	<table id="jgtjtb" class="table table-bordered">
		<tr>
			<td class="textfield" style="width:15%"><center><b>分类</b></center></td>
			<td class="textfield" style="width:35%"><center><b>问题</b></center></td>
			<td class="textfield" style="width:35%"><center><b>选项</b></center></td>
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
							<a href="javascript:void(0)" onclick="showpfwmodal('${base}/wjgl/twjpc!txmd.action?sfzgt=Y&pcid=${pcid!}&wjid=${wjid!}&wtid=${tmpflwt.id!}')">
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
									<a href="javascript:void(0)" onclick="showpfwmodal('${base}/wjgl/twjpc!txmd.action?sfzgt=N&pcid=${pcid!}&wjid=${wjid!}&wthdxid=${tmphdx.id!}')">
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
	<div style="display: none;" data-focus-on="input:first" tabindex="-1" class="modal hide fade in" id="pfwmodal2" style="display: block; margin-top: -155px;" aria-hidden="false">
		<div class="modal-body">
		</div>
		<div class="modal-footer">
	        <a href="javascript:;" class="btn" data-dismiss="modal">关闭</a>
	    </div>
	</div>

	<script>
	function showpfwmodal(url){
		$("#pfwmodal2").modal({backdrop: 'static', keyboard: false,remote:url});
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
	_w_table_rowspan("#jgtjtb",2)
	
	</script>
	
	<!--根据实际需要重新定义对话框的样式-->
	<style>
		#pfwmodal2{
			width: 800px;
			margin: 0 0 0 -370px; 
		}
	</style>
</@crudmetropageblank>
