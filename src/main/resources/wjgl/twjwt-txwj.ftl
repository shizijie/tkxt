<#include "/macro/crud-metro-page-blank.ftl" >

<@crudmetropageblank>

	<form id="user_form" action="${base}/wjgl/twjjg!savejg.action" method="post">
		<#if faflSelect?exists>
			<input type="hidden" id="faflSelect" name="faflSelect" value="${faflSelect}"/>
		</#if>
		<input type="hidden" id="resultSelectStr" name="resultSelectStr" value=""/>
		<input type="hidden" id="resultBlankStr" name="resultBlankStr" value=""/>
		<input type="hidden" id="tk2" name="tk2" value=""/>
		
		<#assign i = 1 />
		<#assign utidstr = "" />
		
		<#list menus as tmpflwt>
			<#assign utidstr = utidstr+tmpflwt.id+"," />
			<p>
				${tmpflwt.wtmc!}<br>
				<#if tmpflwt.wtlx = "3" >
					<#if tmpflwt.jfgzdm = "4">
						<input type="text" id="tk_${tmpflwt.id}" name="tk_${tmpflwt.id}" value="0" size="2" maxlength="2" class="{validate:{number:true}}">
					</#if>
					<#if tmpflwt.jfgzdm = "5">
						<input type="text" id="tk2_${tmpflwt.id}" name="tk2_${tmpflwt.id}"  maxlength="490" ><br>
					</#if>						
				</#if>
				<#list tmpflwt.hdxlist as tmphdx>
					<#if tmpflwt.wtlx = "1">
						<input type="radio" id="answer_${tmpflwt.id}" name="answer_${tmpflwt.id}" value="${tmphdx.id}">${tmphdx.damc}
					</#if>
					<#if tmpflwt.wtlx = "2">
						<input type="checkbox" id="answer_${tmpflwt.id}" name="answer_${tmpflwt.id}" value="${tmphdx.id}">${tmphdx.damc}
					</#if>
				</#list>
			</p>
			<#assign i = i + 1 />
		</#list>
		<input type="hidden" id="utidstr" name="utidstr" value="${utidstr!}">
		
		
		<br>
		<input type="button" id="sub" name="sub" value="提交">
	</form>
	
	
	<script language="javascript">
	
	    $("#sub").click(function(){	
			var express = /^[0-9]*$/;
			var flag = true;
			var tmpStr = "";
			var tmpStr1 = "";
			var tk2Str   = "";//所有主观题填空
			
			$("input[name^='tk_']").each(function(){
				if(!express.test($(this).val()))
				{
					alert("有非法输入数字，请检查");
					flag = false;
				}
			});
			
			if(flag)
			{
				$("input[name^='answer_']").each(function(){
					
					if($(this).attr("checked"))
					{
						tmpStr += $(this).attr("name")+"|"+ $(this).val()+",";
					}
				});

				$("input[name^='tk_']").each(function(){
					if($(this).val())
					{
						tmpStr1 += $(this).attr("name")+"|"+ $(this).val()+",";
					}
				});	
				$("input[name^='tk2_']").each(function(){
					if($(this).val())
					{
						tk2Str += $(this).attr("name")+"|"+ $(this).val()+"^";
					}
				});				

				if(tmpStr != "")
					tmpStr = tmpStr.substring(0,tmpStr.length-1);
				if(tmpStr1 != "")
					tmpStr1 = tmpStr1.substring(0,tmpStr1.length-1);
				if(tk2Str != "")
					tk2Str = tk2Str.substring(0,tk2Str.length-1);	
					
				user_form.resultSelectStr.value = 	tmpStr;
				user_form.resultBlankStr.value = 	tmpStr1;
				user_form.tk2.value = 	tk2Str;
				
				/*
				//验证用户是否全部填写完了
				var tmpstr = user_form.utidstr.value;
				tmpstr = tmpstr.substring(0,tmpstr.length-1);
				var tmpstrarr = tmpstr.split(",");
				var flgg = true;
				for(var i = 0 ;i < tmpstrarr.length; i++)
				{			
					var a = tmpstrarr[i];
					
					if(tmpStr.indexOf(a) < 0)
					{
						if(tmpStr1.indexOf(a) < 0)
						{
							if(tk2Str.indexOf(a) < 0)
							{
								flgg = false;
								break;						
							}
						}
					}									
				}
				
				if(!flgg)
				{
					alert("无法提交，题目有没填完的，请检查一下吧！");
					return false;
				}
				
				
				
				if(user_form.txr.value == "")
				{
					alert("为了方便我们联系您 ，请填写您的姓名");
					user_form.txr.focus();
					return false;
				}
				if(user_form.sjh.value == "")
				{
					alert("为了方便我们联系您 ，请填写您的联系电话");
					user_form.sjh.focus();
					return false;
				}
				if(user_form.yxdz.value == "")
				{
					alert("为了方便我们联系您 ，请填写您的E-mail");
					user_form.yxdz.focus();
					return false;
				}
				
				*/
				
				if(!confirm("问卷将提交，您是否确定？？"))
				{
					return false;
				}
				
				user_form.submit();					
				
			}
	    });				
	</script>
		
	<script type="text/javascript">
		
		
		
		$(document).ready(function(){
			<#if wjjgxqls?exists>
				<#list wjjgxqls as wjjgxq>
					<#if wjjgxq.tkda?exists>
						$("#tk_${wjjgxq.twjjgid.wtid.id?if_exists}").attr("value","${wjjgxq.tkda?if_exists}");
						$("#tk2_${wjjgxq.twjjgid.wtid.id?if_exists}").attr("value","${wjjgxq.tkda?if_exists}");
					<#else>
						$("input[value='${wjjgxq.twjwthdxid.id}']").attr("checked",true);
					</#if>
				</#list>
			</#if>	
		});
		
	</script>
	
</@crudmetropageblank>
