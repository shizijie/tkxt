<#include "/macro/crud.ftl" >

<@crud>
	<#if sfzgt == "N">
		<table id="jgtjtb" class="input_table">
			<tr>
				<td colspan="3">
					<center><font size="3"><b>选择该项人员清单</b></font></center>
				</td>
			</tr>
			<tr>
				<td class="textfield"><center><b>姓名</b></center></td>
				<td class="textfield"><center><b>联系电话</b></center></td>
				<td class="textfield"><center><b>E-mail</b></center></td>
			</tr>
			<#list gls as obj>
				<tr>
					<td>${obj[0]}</td>
					<td>${obj[1]}</td>
					<td>${obj[2]}</td>
				</tr>
			</#list>
		</table>
	</#if>
	
	<#if sfzgt == "Y">
		<table id="jgtjtb" class="input_table">
			<tr>
				<td colspan="4">
					<center><font size="3"><b>该项主观题人员填写清单</b></font></center>
				</td>
			</tr>
			<tr>
				<td class="textfield" style="width:15%"><center><b>姓名</b></center></td>
				<td class="textfield" style="width:15%"><center><b>联系电话</b></center></td>
				<td class="textfield" style="width:15%"><center><b>E-mail</b></center></td>
				<td class="textfield" style="width:55%"><center><b>填写内容</b></center></td>
			</tr>
			<#list gls as obj>
				<tr>
					<td>${obj[0]}</td>
					<td>${obj[1]}</td>
					<td>${obj[2]}</td>
					<td>${obj[3]}</td>
				</tr>
			</#list>
		</table>
	</#if>
		
</@crud>
