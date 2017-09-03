<#include "/macro/crud.ftl" >

<script language=javascript>
function initEcAd() {
	document.all.AdLayer2.style.posTop = -200;
	document.all.AdLayer2.style.visibility = 'visible'
	MoveRightLayer('AdLayer2');
}
function MoveRightLayer(layerName) {
	var x = 5;
	var y = 100;// 右侧广告距离页首高度
	var diff = (document.body.scrollTop + y - document.all.AdLayer2.style.posTop)*.40;
	var y = document.body.scrollTop + y - diff;
	eval("document.all." + layerName + ".style.posTop = y");
	eval("document.all." + layerName + ".style.posRight = x");
	setTimeout("MoveRightLayer('AdLayer2');", 20);
}
</script>

<@crud>
	<#assign zdf = 0>
	<div id="AdLayer2" style='position: absolute;visibility:hidden;z-index:1;background-color:#666600;width:150px;height:80px'>
		<center>
		<br><br>
			<font color=white size="3">
				<b>问卷总计得分：<div id="zdf"></div></b>
			</font>
		<br>&nbsp;
		</center>
	</div>	
	<table border="0">
		<#if wjfa?exists && wjfa.btcontent?exists>
			<tr>
				<td>${wjfa?if_exists.btcontent?if_exists}</td>
			</tr>
		</#if>	
		<#list wjfa.twjfafl as tmpfafl>
		<tr>
			<td>
				<b>${tmpfafl.flmc!}</b>
			</td>
		</tr>
			<#list tmpfafl.flwt as tmpflwt>
				<tr>
					<td><br>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<b>
							${tmpflwt.wtmc!}
							<#list entity.wjjglist as tmpwjjg>
								<#if tmpwjjg.wtid.id == tmpflwt.id >
									<font color=red>
										(得分:&nbsp;
										<#if tmpwjjg.df2?exists && tmpwjjg.df2 != "" >
											${tmpwjjg.df2}
											<#assign zdf = zdf + tmpwjjg.df2?number>
										<#else>
											${tmpwjjg.df}
											<#assign zdf = zdf + tmpwjjg.df?number>
										</#if>
										&nbsp;
										)
									</font>									
								</#if>
							</#list>
						</b>
					</td>
				</tr>
				<tr>
					<td></br>
						<#if tmpflwt.wtlx = "3" >
							<#if tmpflwt.jfgzdm = "4">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="text" id="tk_${tmpflwt.id}" name="tk_${tmpflwt.id}" value="0" size="2" maxlength="2" class="{validate:{number:true}}">
							</#if>
							<#if tmpflwt.jfgzdm = "5">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="text" id="tk2_${tmpflwt.id}" name="tk2_${tmpflwt.id}"  maxlength="490" ><br>
							</#if>						
						</#if>	
						<#list tmpflwt.hdxlist as tmphdx>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<#if tmpflwt.wtlx = "1">
								<input type="radio" id="answer_${tmpflwt.id}" name="answer_${tmpflwt.id}" value="${tmphdx.id}">${tmphdx.damc}
							</#if>
							<#if tmpflwt.wtlx = "2">
								<input type="checkbox" id="answer_${tmpflwt.id}" name="answer_${tmpflwt.id}" value="${tmphdx.id}">${tmphdx.damc}
							</#if>
							
						</#list>												
					</td>
				</tr>				
			</#list>
			<tr>
				<td>&nbsp;<br></td>
			</tr>
		</#list>
		<#if wjfa?exists && wjfa.bwcontent?exists>
		<tr>
			<td>&nbsp;<br><br></td>
		</tr>
		<tr>
			<td>${wjfa?if_exists.bwcontent?if_exists}</td>
		</tr>
		</#if>
		
	<table>
	<script type="text/javascript">
		
		initEcAd();
		$("#zdf").html('${zdf!}');
		
		$(document).ready(function(){
			<#list wjjgxqls as wjjgxq>
				<#if wjjgxq.tkda?exists>
					$("#tk_${wjjgxq.twjjgid.wtid.id?if_exists}").attr("value","${wjjgxq.tkda?if_exists}");
					$("#tk2_${wjjgxq.twjjgid.wtid.id?if_exists}").attr("value","${wjjgxq.tkda?if_exists}");
				<#else>
					$("input[value='${wjjgxq.twjwthdxid.id}']").attr("checked",true);
				</#if>
			</#list>	
		});
		
	</script>
	
</@crud>
