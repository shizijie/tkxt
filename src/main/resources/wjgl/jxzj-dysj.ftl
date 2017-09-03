<#include "/macro/crud-metro-page3-nowebpst.ftl" >

<#assign extPosition="打印试卷"/>

<@crudmetropage3>
	<form id="saveWord" action="${base}/wjgl/jxtx!saveWord.action" method="post">
	<p style="float:left">
		<input type="button" value="打印" name="prnt" onclick="prn1_preview()" style="display:none">&nbsp;&nbsp;&nbsp;<button type="button" onclick="saveWord()">保存为word</button>
	</p>
	<br/><br/>
	
	
	<table border="0" id="jgtjdiv" style="width:650px;float:left">
	<!-- style="height:891px;width:1260px"-->
	
		<tr>
			<td align="center">
					<b>${entity.zjmc!}--样本如下：</b>
			</td>
		</tr>
		
		<tr>
			<td>
				<center>
					<h3></h3>
					<font size=4>上海市工业技术学校试卷</font>（${entity.exam!}:闭卷）
					<h3></h3>
				</center>
			</td>
		</tr>
		<tr>
			<td  width:630px;>
				<center>
					<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${entity.xn!}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>学年&nbsp;&nbsp;&nbsp;&nbsp;第<u>&nbsp;&nbsp;&nbsp;${entity.xq!}&nbsp;&nbsp;&nbsp;</u>学期&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;课程名称<u>&nbsp;&nbsp;&nbsp;机械制图&nbsp;&nbsp;&nbsp;</u>
				</center>
			</td>
		</tr>
		<input type="hidden" name="xn" id="xn" value="${entity.xn!}"/>
		<input type="hidden" name="xq" id="xq" value="${entity.xq!}"/>
		<input type="hidden" name="exam" id="exam" value="${entity.exam!}"/>
		<tr>
			<td>
				<center>
				<table>
					<tr>
						<td style="border:1px solid #000000;width:30px;height:20px;text-align:center;" valign="top">&nbsp;一&nbsp;</td>
						<td style="border:1px solid #000000;width:30px;height:20px;text-align:center" valign="top">&nbsp;二&nbsp;</td>
						<td style="border:1px solid #000000;width:30px;height:20px;text-align:center" valign="top">&nbsp;三&nbsp;</td>
						<td style="border:1px solid #000000;width:30px;height:20px;text-align:center" valign="top">&nbsp;四&nbsp;</td>
						<td style="border:1px solid #000000;width:30px;height:20px;text-align:center" valign="top">&nbsp;五&nbsp;</td>
						<td style="border:1px solid #000000;width:30px;height:20px;text-align:center" valign="top">&nbsp;六&nbsp;</td>
						<td style="border:1px solid #000000;width:30px;height:20px;text-align:center" valign="top">&nbsp;七&nbsp;</td>
						<td style="border:1px solid #000000;width:30px;height:20px;text-align:center" valign="top">&nbsp;八&nbsp;</td>
						<td style="border:1px solid #000000;width:30px;height:20px;text-align:center" valign="top">&nbsp;九&nbsp;</td>
						<td style="border:1px solid #000000;width:30px;height:20px;text-align:center" valign="top">&nbsp;十&nbsp;</td>
						<td style="border:1px solid #000000;width:30px;height:20px;text-align:center" valign="top">&nbsp;十&nbsp;<br/>&nbsp;一&nbsp;</td>
						<td style="border:1px solid #000000;width:30px;height:20px;text-align:center" valign="top">&nbsp;十&nbsp;<br/>&nbsp;二&nbsp;</td>
						<td style="border:1px solid #000000;width:30px;height:20px;text-align:center" valign="top">&nbsp;总&nbsp;<br/>&nbsp;分&nbsp;</td>
					</tr>
					<tr>
						<td style="border:1px solid #000000;width:30px;height:30px;text-align:center"></td>
						<td style="border:1px solid #000000;width:30px;height:20px;text-align:center"></td>
						<td style="border:1px solid #000000;width:30px;height:20px;text-align:center"></td>
						<td style="border:1px solid #000000;width:30px;height:20px;text-align:center"></td>
						<td style="border:1px solid #000000;width:30px;height:20px;text-align:center"></td>
						<td style="border:1px solid #000000;width:30px;height:20px;text-align:center"></td>
						<td style="border:1px solid #000000;width:30px;height:20px;text-align:center"></td>
						<td style="border:1px solid #000000;width:30px;height:20px;text-align:center"></td>
						<td style="border:1px solid #000000;width:30px;height:20px;text-align:center"></td>
						<td style="border:1px solid #000000;width:30px;height:20px;text-align:center"></td>
						<td style="border:1px solid #000000;width:30px;height:20px;text-align:center"></td>
						<td style="border:1px solid #000000;width:30px;height:20px;text-align:center"></td>
						<td style="border:1px solid #000000;width:30px;height:20px;text-align:center"></td>
					</tr>
				</table>
				</center>
			</td>
		</tr>
		<input type="hidden" value="${id}" name="zjid">
		
		<#list reslist as rlist>
			<tr>
				<td valign="top">
				<input type="hidden" value="${rlist.txmc}" name="testPaper[${rlist_index}].zjmc">
				<input type="hidden" value="${rlist.bz}" name="testPaper[${rlist_index}].zjxx">
				<div style="width:610px;"><b>${rlist.txmc}。</b>${rlist.bz}</div>
				<br/>
				
					<#list rlist.jtmlist as jlist>
							<#if (jlist_index+1)%2==1>
								<br/>
							</#if>
							<input type="hidden" value="${jlist_index+1}" name="testPaper[${rlist_index}].list[${jlist_index}].tmms">
							<input type="hidden" value="${jlist.tmFileName}" name="testPaper[${rlist_index}].list[${jlist_index}].tmFileName">
							${jlist_index+1}.&nbsp;<img src="../userfiles/kjzt/wtimg/${jlist.tmFileName?if_exists}" />
					</#list>
			<br/>
				</td>
			</tr>
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

