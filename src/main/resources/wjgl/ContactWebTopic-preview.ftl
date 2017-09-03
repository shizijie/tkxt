<#include "/macro/crud-metro-page3-nowebpst.ftl" >
<#assign Eng={"0":"A","1":"B", "2":"C","3":"D","4":"E","5":"F","6":"G","7":"H","8":"I","9":"J"}>

<@crudmetropage3>
	<div>
		<center>
			<h2>${entity.topicName!}</h2>
			<h3>${entity.textField!}</h3>
			<#list entity.setTopicContent?if_exists as topic>
			<h4>${topic_index+1}、${topic.topicText!}</h4>
			<#if topic?if_exists.topicPicture?if_exists!="0">
				<img src='${base}/userfiles/contactWebTopic/topicImg/${topic?if_exists.topicPicture!}' />
				<br>
			</#if>
			<#if topic?if_exists.topicType?if_exists=="1">
				<#list topic?if_exists.setTopicOptions?if_exists as opt>
					<h5>${Eng['${opt_index}']}、${opt?if_exists.optionContent!}</h5>
					<#if opt?if_exists.optionPicture?if_exists!="0">
						<img src='${base}/userfiles/contactWebTopic/optionImg/${opt?if_exists.optionPicture!}' />
						<br>
					</#if>
				</#list>
			<#else>
			</#if>
			</#list>
	    	
		</center>
	</div>
</@crudmetropage3>

