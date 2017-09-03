			<#include "/macro/crud-metro-page3-nowebpst.ftl" >

<#assign extPosition="打印试卷"/>
<#assign number={"1":"一", "2":"二", "3":"三","4":"四","5":"五","6":"六","7":"七","8":"八"}>
<@crudmetropage3>
	<form id="saveWord" action="${base}/wjgl/taoti!saveWord.action" method="post">
	<p style="float:left">
		<input type="button" value="打印" name="prnt" onclick="prn1_preview()">
		&nbsp;&nbsp;&nbsp;<button type="button" onclick="saveWord()">保存为word</button>
	</p>
	
	<table border="0" id="jgtjdiv">
		<#if entity?exists && entity.ttmc?exists>
			<td><center>
						<input type="hidden" value="${entity?if_exists.ttmc?if_exists}" name="title">
						<input type="hidden" value="${entity.fafl?if_exists.flmc!}" name="exam">
						<h3><strong>${entity?if_exists.ttmc?if_exists}</strong></h3>
						<h3>________学年&nbsp;&nbsp;第____学期&nbsp;&nbsp;课程名称_《${entity.fafl?if_exists.flmc!}》_</h3>
						<h4>专业__________班级__________姓名__________学号__________</h4>
						<table id="result_page_table"border="1" width="100%"height="80">
						<tr height="50">
						<td>一</td>
						<td>二</td>
						<td>三</td>
						<td>四</td>
						<td>五</td>
						<td>六</td>
						<td>七</td>
						<td>八</td>
						<td>九</td>
						<td>十</td>
						<td>十一</td>
						<td>十二</td>
						<td>总分</td>						
						</tr>	
						<tr height="30">
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>					
						</tr>					
						</table>
					</center></td>
			</tr>
		</#if>	
		<#list cswt?if_exists as wt>
		<#list wt.keySet() as key>
			<tr>
				<td>
					<input type="hidden"  name='csrw[${wt_index}].zjxx' value="${number['${wt_index+1}']}、${key}" >
					<br/>
					<h4>${number['${wt_index+1}']}、${key}</h4>
				</td>
			</tr>
		
			<#list wt.get(key) as tmpflwt>
			<tr>
				<td><br>
					<input type="hidden" name="csrw[${wt_index}].list[${tmpflwt_index}].wtmc" value="${tmpflwt_index+1}、&nbsp;${tmpflwt.wtmc!}">
					<b>${tmpflwt_index+1}、&nbsp;${tmpflwt.wtmc!}</b>
					<br>
					<#if tmpflwt.wtmcFileName?if_exists!="">
						<input type="hidden" name="csrw[${wt_index}].list[${tmpflwt_index}].wtmcFileName" value="${tmpflwt.wtmcFileName!}">
						<img src='${base}/userfiles/kjzt/wtimg/${tmpflwt.wtmcFileName!}' />
					</#if>
				</td>
			</tr>
			<tr>
				<td><br>
					<#list tmpflwt.hdxlist as tmphdx>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<#if key!="填空题">
							<input type="hidden" name="csrw[${wt_index}].list[${tmpflwt_index}].hdx[${tmphdx_index}].damc" value="${tmphdx.dadm}、${tmphdx.damc}">
							${tmphdx.dadm}、${tmphdx.damc}
						</#if>
						<#if (tmphdx.damcFileName)??&&(tmphdx.damcFileName)!="">
							<input type="hidden" name="csrw[${wt_index}].list[${tmpflwt_index}].hdx[${tmphdx_index}].damcFileName" value="${tmphdx.damcFileName!}">
							<img src='${base}/userfiles/kjzt/daimg/${tmphdx.damcFileName!}' />
						</#if>
					</#list>	
					<br>											
				</td>
				</tr>
				</#list>
			</#list>
		</#list>
		
		<tr>
			<td>&nbsp;<br></td>
		</tr>
		
	</table>
	</form>
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
			 //LODOP.PRINT_INIT("答题统计结果");
			 
			 var content="";
			 var contentHeight=0;
			 
			 var tmpcot = $("#jgtjdiv").clone();
			 tmpcot.find("table").attr("border","1");
			 content = tmpcot.html();
			 LODOP.SET_PRINT_STYLEA(1, "FontSize", 14);
			 //LODOP.ADD_PRINT_HTM(0,0,"100%","100%","<center><h1>答题统计结果</h1></center>");
			 LODOP.ADD_PRINT_HTM(50,0,"100%","100%",content);
			 LODOP.NewPageA();
			 LODOP.PREVIEW();
	    };
	    function saveWord(){
	    	$("#saveWord").submit();
	    	setTimeout(function(){
	    		location.reload();
	    	},1000);
	    }
	</script>
</@crudmetropage3>

