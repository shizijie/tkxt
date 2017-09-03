<#include "/macro/crud-metro-page-blank.ftl" >

<@crudmetropageblank>

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

	<form id="user_form" action="${base}/wjgl/twjjg!savejg.action" method="post">
		<#if wjfa?exists>
			<input type="hidden" id="wjid" name="wjid" value="${wjfa.id}"/>
		</#if>
		<input type="hidden" id="pcid" name="pcid" value="${id!}" />
		
		<input type="hidden" id="resultSelectStr" name="resultSelectStr" value=""/>
		<input type="hidden" id="resultBlankStr" name="resultBlankStr" value=""/>
		<input type="hidden" id="tk2" name="tk2" value=""/>
	
		
		<table border="0">
			<#if wjfa?exists && wjfa.btcontent?exists>
				<tr>
					<td>${wjfa?if_exists.btcontent?if_exists}</td>
				</tr>
			</#if>	
			<#assign i = 1 />
			<#assign utidstr = "" />
			
			<#list wjfa.twjfafl as tmpfafl>
			<tr>
				<td>
					<b>${tmpfafl.flmc!}</b>
				</td>
			</tr>
				<#list tmpfafl.flwt as tmpflwt>
					<#assign utidstr = utidstr+tmpflwt.id+"," />
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
					<#assign i = i + 1 />			
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
			<tr>
				<td>
					<font color=red><b>您的姓名：</b></font><input type="text" name="txr" id="txr" value="" maxlength="23">&nbsp;&nbsp;&nbsp;&nbsp;
					<font color=red><b>联系电话：</b></font><input type="text" name="sjh" id="sjh" value="" maxlength="40">&nbsp;&nbsp;&nbsp;&nbsp;
					<font color=red><b>E-mail：</b></font><input type="text" name="yxdz" id="yxdz" value="" maxlength="40">
				
				</td>
				
			</tr>
		</table>
		
			<br><br>
			<div id="AdLayer2" style='position: absolute;visibility:hidden;z-index:1;background-color:#666600;width:80px;height:100px'>
				<center>
				<br>
					<input type="button" id="sub" name="sub" value="提交">
				<br>&nbsp;
				</center>
			</div>	
			
			<input type="hidden" id="utidstr" name="utidstr" value="${utidstr!}">
	
	</form>
	
	<script language="javascript">
	
		initEcAd();
		
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
				
				*/
				
				
				
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
				
				if(!confirm("问卷将提交，您是否确定？？"))
				{
					return false;
				}
				
				user_form.submit();					
				
			}
	    });				
	</script>
	
</@crudmetropageblank>






