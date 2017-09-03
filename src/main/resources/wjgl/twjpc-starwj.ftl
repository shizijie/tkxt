<br><br>
<center>
<table>
	<tr>
		<#if entity.flist?size == 1>
			<#list entity.flist as tmptwjfa>
				<script>
					window.location.href = "${base}/wjgl/twjpc!txwj.action?wjid=${tmptwjfa.id!}&id=${id!}";
				</script>				
			</#list>
		<#else>
			<#list entity.flist as tmptwjfa>
				<td>	
					<a href="${base}/wjgl/twjpc!txwj.action?wjid=${tmptwjfa.id!}&id=${id!}" target="_blank" style="text-decoration:none;">
						<img src="${base}/images/import.jpg" border="0"><br>${tmptwjfa.famc!}</img>
					</a>	
				</td>
				<td width="100px">
					&nbsp;
				</td>
			</#list>			
		</#if>

	</tr>
</table>
</center>
