<#include "/macro/crud-metro-page-blank.ftl" >

<@crudmetropageblank>

	<table border="0">
		<#if entity?exists && entity.btcontent?exists>
			<tr>
				<td>${entity?if_exists.btcontent?if_exists}</td>
			</tr>
		</#if>	
		<#list entity.twjfafl as tmpfafl>
		<tr>
			<td>
				<b>${tmpfafl.flmc!}</b>
			</td>
		</tr>
			<#list tmpfafl.flwt as tmpflwt>
				<tr>
					<td><br>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>${tmpflwt.wtmc!}</b>
					</td>
				</tr>
				<tr>
					<td><br>
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
		<#if entity?exists && entity.bwcontent?exists>
		<tr>
			<td>&nbsp;<br><br></td>
		</tr>
		<tr>
			<td>${entity?if_exists.bwcontent?if_exists}</td>
		</tr>
		</#if>
		
	</table>
	
</@crudmetropageblank>
