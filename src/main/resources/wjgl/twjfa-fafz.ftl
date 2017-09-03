<#include "/macro/crud.ftl" >

<@crud>
	<form id="catalog_inputForm" action="${base}/wjgl/twjfa!dofafz.action" method="post">
		<br><br>
		<table class="input_table">
		<tr>
			<td>
				<center>
					将&nbsp;&nbsp;&nbsp;
					<@s.select name="yfa" id="yfa" list="allfa" listKey="id" listValue="famc" style="width:300px" headerKey="" headerValue="==请选择=="   />
					&nbsp;&nbsp;&nbsp;&nbsp;
					复制到
					&nbsp;&nbsp;&nbsp;&nbsp;
					<@s.select name="mbfa" id="mbfa" list="allfa" listKey="id" listValue="famc" style="width:300px" headerKey="" headerValue="==请选择=="   />
				</center>
			</td>
		</tr>
		<tr>
			<td colspan=2>
				<center>
					<input type="submit" id="commit" name="commit" value="开始复制"/>
				</center>
			</td>			
		</tr>
		</table>
	</form>


	<script type="text/javascript">
		$(document).ready(function() {
			$("#catalog_inputForm").validate({
				rules: {
					yfa:{required:true},
					mbfa:{required:true}
				}
			});
		});
		
	
	</script>
	
</@crud>
