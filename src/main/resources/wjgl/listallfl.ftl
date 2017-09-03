<#include "/macro/crud-metro-page-blank.ftl" >

<@crudmetropageblank>

<#list allfl as tmpfafl>
	<p>
		<a href="${base}/wjgl/twjwt!txwj.action?flid=${tmpfafl.id}" target="_blank">${tmpfafl.flmc}</a>
	</p>
</#list>


</@crudmetropageblank>