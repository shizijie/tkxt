<#include "/macro/crud.ftl" >
<script>
	<#if Request["promptInfo"]?exists>
		
			alert('${Request["promptInfo"]}');
			try
			{
				if(typeof(window.opener.document.forms[0]) != "undefined")
				{
					var newInput = window.opener.document.createElement("input");
					newInput.type = "hidden";
					newInput.name = "fyclick";
					newInput.value = "Y";
					window.opener.document.forms[0].appendChild(newInput);
					window.opener.document.forms[0].submit();
				}
					
			}catch(e)
			{
				window.close();
			}
			window.close();
		
	<#else>
			try
			{
				if(typeof(window.opener.document.forms[0]) != "undefined")
				{
					var newInput = window.opener.document.createElement("input");
					newInput.type = "hidden";
					newInput.name = "fyclick";
					newInput.value = "Y";
					window.opener.document.forms[0].appendChild(newInput);
					window.opener.document.forms[0].submit();
				}
								
			}catch(e)
			{
				window.close();
			}
			window.close();

	</#if>
</script>