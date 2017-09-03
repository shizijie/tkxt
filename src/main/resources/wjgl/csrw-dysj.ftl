<script type="text/javascript" src="${base}/mainface/js/jquery-1.10.1.min.js"></script>
<#assign extPosition="打印试卷"/>
<#assign number={"1":"一", "2":"二", "3":"三","4":"四","5":"五","6":"六","7":"七","8":"八"}>
<#assign dispWtlx={"1":"单选题", "2":"多选题", "3":"判断题","5":"填空题","6":"计算题","7":"读图题","8":"综合题","9":"回路设计题"}>
	<form id="saveWord" action="${base}/wjgl/csrw!saveWord.action" method="post">
	<p style="float:left">
		<input type="button" value="打印" name="prnt" onclick="prn1_preview()">&nbsp;&nbsp;&nbsp;<button type="button" onclick="saveWord()">保存为word</button>
	</p>
	<br>
	<br>
	<br>
	<table border="0" id="jgtjdiv">
		<#if entity?exists && entity.rwmc?exists>
			<tr>
			<td><center>
						<input type="hidden" value="${entity.fafl?if_exists.flmc!}" name="exam">
						<h3><strong>上海公用事业学校</strong>(期中/期末：开卷/闭卷)</h3>
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
					<br/>
					<input type="hidden"  name='csrw[${wt_index}].zjxx' value="${number['${wt_index+1}']}、${key}，每题<#if key?index_of("机械制图")!=-1><#list entity?if_exists.jxScore?if_exists?split(",") as lx><#if lx?index_of(key?substring(6))!=-1>${lx?substring(lx?index_of(":")+1)}</#if></#list><#else><#list entity?if_exists.score?if_exists?split(",") as lx><#if lx?index_of(key)!=-1>${lx?substring(lx?index_of(':')+1)}</#if></#list></#if>分。" >
					<h4 id="test">
						${number['${wt_index+1}']}、${key}，每题
						<#if key?index_of("机械制图")!=-1>
						<#list entity?if_exists.jxScore?if_exists?split(",") as lx>
							<#if lx?index_of(key?substring(6))!=-1>${lx?substring(lx?index_of(":")+1)}</#if>
						</#list>
						<#else>
						<#list entity?if_exists.score?if_exists?split(",") as lx>
							<#if lx?index_of(key)!=-1>${lx?substring(lx?index_of(":")+1)}</#if>
						</#list>
						</#if>
						分。
					</h4>
				</td>
			</tr>
				<#if key?index_of("机械制图")!=-1>
					<#list wt.get(key)?if_exists as tmpflwt>
						<tr>
						<td><br>
							<input type="hidden" name="csrw[${wt_index}].list[${tmpflwt_index}].wtmc" value="${tmpflwt_index+1}、&nbsp;${tmpflwt.tmms!}">
							<b>${tmpflwt_index+1}、&nbsp;${tmpflwt.tmms!}</b>
							<br>
							<#if tmpflwt.tmFileName?if_exists!="">
								<input type="hidden" name="csrw[${wt_index}].list[${tmpflwt_index}].wtmcFileName" value="${tmpflwt.tmFileName!}">
								<img src='${base}/userfiles/kjzt/wtimg/${tmpflwt.tmFileName!}' />
							</#if>
						</td>
						</tr>
					</#list>
				<#else>
					<#list wt.get(key)?if_exists as tmpflwt>
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
				</#if>
			</#list>
		</#list>
		
				
		<tr><td>		
			<p>
			<strong>注意要点：</strong><br>
			一,表头及装订线格式不得更改<br/>
			二,期中/期末;开卷/闭卷二选一,多余删除<br/>
			三,考卷左下角适用班级,中间底部页码请填写完整<br/>
			四,正文字体和字号可按参考格式或按需调整,但需保持统一完整<br/>
			五,分值分配可按参考格式,表述清晰明确<br/>
			六,纸质试卷以打印件提交教务处,不得出现粘贴,手写等情况<br/>
			</p>			
		</td></tr>				
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
	    	//$.ajax({
	    	//	type:"POST",
	    	//	url:"${base}/wjgl/csrw!saveWord.action",
	    	//	data:$("#saveWord").serialize(),
	    	//	success:function(data){
	    	//			alert("转换成功！");
	    	//	}
	    	//});
	    	$("#saveWord").submit();
	    	setTimeout(function(){
	    		location.reload();
	    	},1000);
	    }
	</script>

