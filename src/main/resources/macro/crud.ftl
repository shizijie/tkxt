<#macro crud ext...>
<#assign s=JspTaglibs["/tags/struts-tags.tld"] /> 
<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] /> 
<#import "message.ftl" as msg>


<!-- 审核状态 -->
<#assign dispShzt={"0":"无","1":"审核中", "2":"审核通过","3":"审核退回","4":"未提交"}>
<!-- 是否通过 -->
<#assign dispSftg={"0":"不通过","1":"通过"}>
<!-- 通用是否 -->
<#assign dispYn={"0":"否","1":"是"}>


<!-- 处理页面中新增、修改、删除按钮的显示问题 -->
<#assign adu_hidden = "1">
<#if Session["curUserAuths"]?exists>
	<#assign curUserAuths = Session["curUserAuths"]>
	<#if (curUserAuths?index_of("A_ALLADU_BUTTON_HIDDEN")) != -1 >
		<#assign adu_hidden = "0">
	</#if>
</#if>


<html>
 <head>
  <meta http-equiv="content-type" content="text/html;charset=utf-8">
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">
  
  <title>TQBase</title>
  <link type="text/css" rel="stylesheet" href="${base}/extend/css/framework-crud.css"/>
  <link type="text/css" rel="stylesheet" href="${base}/scripts/popup/popup.css"/>
  <link type="text/css" rel="stylesheet" href="${base}/scripts/pfw/pfw.page.css"/>
  <link type="text/css" rel="stylesheet" href="${base}/scripts/jquery/jquery-validate/jquery.validate.css"/>
  <link rel="stylesheet" type="text/css" href="${base}/scripts/jquery/easyui/themes/default/easyui.css">
  <link rel="stylesheet" type="text/css" href="${base}/scripts/jquery/easyui/themes/icon.css"> 
  
  <link rel="stylesheet" type="text/css" href="${base}/mainface/css/tqbase.css"> 
  
  <script src="${base}/mainface/js/jquery-1.10.1.min.js" type="text/javascript"></script>
  <script type="text/javascript" src="${base}/scripts/pfw/pfw.page.min.js"></script>
  <script type="text/javascript" src="${base}/scripts/jquery/easyui/jquery.easyui.min.js"></script>
  <script type="text/javascript" src="${base}/scripts/popup/popup.js"></script>
  <script type="text/javascript" src="${base}/scripts/jquery/jquery-validate/jquery.validate.pack.js"></script>
  <script type="text/javascript" src="${base}/scripts/jquery/jquery-validate/localization/messages_cn.js"></script>
  <script type="text/javascript" src="${base}/scripts/My97DatePicker/WdatePicker.js"></script>
  <script type="text/javascript" src="${base}/scripts/js/select.js"></script>
  <script type="text/javascript" src="${base}/scripts/js/tablemerge.js"></script>  
  
  <!--解决jquery-1.10.1.min.js不支持$.browser问题-->
  <script>
	(function(jQuery){ 
	
	if(jQuery.browser) return; 
	
	jQuery.browser = {}; 
	jQuery.browser.mozilla = false; 
	jQuery.browser.webkit = false; 
	jQuery.browser.opera = false; 
	jQuery.browser.msie = false; 
	
	var nAgt = navigator.userAgent; 
	jQuery.browser.name = navigator.appName; 
	jQuery.browser.fullVersion = ''+parseFloat(navigator.appVersion); 
	jQuery.browser.majorVersion = parseInt(navigator.appVersion,10); 
	var nameOffset,verOffset,ix; 
	
	// In Opera, the true version is after "Opera" or after "Version" 
	if ((verOffset=nAgt.indexOf("Opera"))!=-1) { 
	jQuery.browser.opera = true; 
	jQuery.browser.name = "Opera"; 
	jQuery.browser.fullVersion = nAgt.substring(verOffset+6); 
	if ((verOffset=nAgt.indexOf("Version"))!=-1) 
	jQuery.browser.fullVersion = nAgt.substring(verOffset+8); 
	} 
	// In MSIE, the true version is after "MSIE" in userAgent 
	else if ((verOffset=nAgt.indexOf("MSIE"))!=-1) { 
	jQuery.browser.msie = true; 
	jQuery.browser.name = "Microsoft Internet Explorer"; 
	jQuery.browser.fullVersion = nAgt.substring(verOffset+5); 
	} 
	// In Chrome, the true version is after "Chrome" 
	else if ((verOffset=nAgt.indexOf("Chrome"))!=-1) { 
	jQuery.browser.webkit = true; 
	jQuery.browser.name = "Chrome"; 
	jQuery.browser.fullVersion = nAgt.substring(verOffset+7); 
	} 
	// In Safari, the true version is after "Safari" or after "Version" 
	else if ((verOffset=nAgt.indexOf("Safari"))!=-1) { 
	jQuery.browser.webkit = true; 
	jQuery.browser.name = "Safari"; 
	jQuery.browser.fullVersion = nAgt.substring(verOffset+7); 
	if ((verOffset=nAgt.indexOf("Version"))!=-1) 
	jQuery.browser.fullVersion = nAgt.substring(verOffset+8); 
	} 
	// In Firefox, the true version is after "Firefox" 
	else if ((verOffset=nAgt.indexOf("Firefox"))!=-1) { 
	jQuery.browser.mozilla = true; 
	jQuery.browser.name = "Firefox"; 
	jQuery.browser.fullVersion = nAgt.substring(verOffset+8); 
	} 
	// In most other browsers, "name/version" is at the end of userAgent 
	else if ( (nameOffset=nAgt.lastIndexOf(' ')+1) < 
	(verOffset=nAgt.lastIndexOf('/')) ) 
	{ 
	jQuery.browser.name = nAgt.substring(nameOffset,verOffset); 
	jQuery.browser.fullVersion = nAgt.substring(verOffset+1); 
	if (jQuery.browser.name.toLowerCase()==jQuery.browser.name.toUpperCase()) { 
	jQuery.browser.name = navigator.appName; 
	} 
	} 
	// trim the fullVersion string at semicolon/space if present 
	if ((ix=jQuery.browser.fullVersion.indexOf(";"))!=-1) 
	jQuery.browser.fullVersion=jQuery.browser.fullVersion.substring(0,ix); 
	if ((ix=jQuery.browser.fullVersion.indexOf(" "))!=-1) 
	jQuery.browser.fullVersion=jQuery.browser.fullVersion.substring(0,ix); 
	
	jQuery.browser.majorVersion = parseInt(''+jQuery.browser.fullVersion,10); 
	if (isNaN(jQuery.browser.majorVersion)) { 
	jQuery.browser.fullVersion = ''+parseFloat(navigator.appVersion); 
	jQuery.browser.majorVersion = parseInt(navigator.appVersion,10); 
	} 
	jQuery.browser.version = jQuery.browser.majorVersion; 
	})(jQuery); 
  </script>
  <!--解决jquery-1.10.1.min.js不支持$.browser问题-->
  
  
  <link rel="shortcut icon" href="${base}/mainface/image/favicon.ico" />
 </head>
 <body oncontextmenu="return false;" >
		<!-- head 先暂时去掉
		<div class="row-fluid">
			<div class="span12">
				<ul class="breadcrumb">
					<li>
						<i class="icon-home"></i>
						<a href="index.html">Home</a> 
						<i class="icon-angle-right"></i>
					</li>
					<li><a href="#">Dashboard</a></li>
				</ul>
			</div>
		</div>
		-->		 		
		<@msg.error>
			<@s.actionerror/>
			<@s.fielderror />
			<#if Parameters['error']?exists&&Parameters['error']=='2'>
				<@msg.error>验证码错误,请重试.</@msg.error> 
			<#else>
				<@msg.error>${Parameters['error']?if_exists}</@msg.error> 
			</#if>
			<@msg.error>${Parameters['errorInfo']?if_exists}</@msg.error> 
		</@msg.error>
		
		<div style="margin-left:16px;margin-right:8px;">
 			<#nested>
 		</div>

 	<form id="CONTNET_GOHOME_FORM" action="${base}/index.action" method="post" target="_self">
	</form>
 </body>

 
</html>

<script>
		function modalShowWindow(url){
	        var sURL = url;
			setRead=false;
			var WindowStyle="dialogHeight:600px; dialogWidth:900px; status=no; center:yes; resizable:yes";	                        
	        return tmp = window.showModalDialog(sURL, setRead, WindowStyle);
	    }
		_show = function(c, t) {
			var pop = new Popup( {
				contentType : 4,
				isReloadOnClose : false,
				width : 340,
				height : 80
			});
			pop.setContent("title", t ? t : "提示");
			pop.setContent("alertCon", c ? c : "对话框的内容");sh
			pop.build();
			pop.show();
		};
		
		$(".legend").html($(".legend").html()+"<font color=red> - 点击隐藏</font>");
			$(".legend").attr("style","cursor: pointer");
			$(".legend").click(function(){
				$(".legend").parent().children(":not('.legend')").toggle();
				var extStr = $(".legend").html();
				if(extStr.indexOf("隐藏") >= 0)
				{
					extStr = extStr.replace("隐藏","展开");
				}else
				{
					extStr = extStr.replace("展开","隐藏");
				}				
				$(".legend").html(extStr);
			});
			
		
		$(document).ready(function() {
			$("form").bind("submit", function() {
				var subFlg = true;
				$(this).find("label[class='error']").each(function(){
					if($(this).css("display") != "none")
					{
						subFlg = false;
					}
				});
				if(!subFlg)
				{
					return false;
				}
				var pop = new Popup( {
					contentType : 2,
					isReloadOnClose : false,
					width : 340,
					top : 0,
					height : 100
				});
				var strHtml = "<br><center><img src='${base}/extend/image/progress.gif' />  系统正在处理中，请稍等......</center>";
  	 			pop.setContent("contentHtml",strHtml);
            	pop.setContent("title","");	
				pop.build();
				pop.show();			
			});
			
		});
	    /*
	    $(document).ready(function() {
	    	$("input[type='button']").bind("click",function(){
	    		$(this).attr("disabled","true");
	    		alert("button");
	    		rvButtonDis(this);
	    	});
	    	$("input[type='submit']").bind("click",function(){
	    		$(this).attr("disabled","true");
	    		$(this).parents("form").trigger("submit");
	    		rvButtonDis(this);
	    	});	    	
	    });
	    function rvButtonDis(obj,val)
	    {
	    	setTimeout(function() { obj.disabled = false; }, 1000); 
	    }	    
		*/
		
		/*
		function selDep(selectname){
			v2 = selectname;
			$('#dd > div[class=panel] > div').append($('#depDiv'));
			$('#depDiv').show();
			$('#dd').dialog('open');
		}
		
		function close1(){
			$('#dd').dialog('close');
		}
		
			$('#dd').dialog({
				buttons:[{
					text:'确定',
					iconCls:'icon-ok',
					handler:function(){
						$("#depDiv  div[class*='tree-node']").each(function(){
							var tmp = $(this).find("span[class*='tree-checkbox tree-checkbox1']");
							if(tmp.size() >=1)
							{
								var tmpselect = document.getElementById(v2);
								var deslength = tmpselect.length;
								var nodeid = $(this).attr('node-id');
								var nodetext = $(this).find("span[class='tree-title']").text();
								if(!$('#checks > option[value='+nodeid+']').val())
								{
									tmpselect.options[deslength]= new Option(nodetext,nodeid);
								}
																
							}
							
							$('#dd').dialog('close');	
						});
					}
				},{
					text:'取消',
					handler:function(){
						$('#dd').dialog('close');
					}
				}]
			});
			
			
			$('#dd').dialog('close');
					
			
			$('#tt2').tree({
				checkbox: true,
				url: '${base}/system/department!inputTree.action'
			});	
		*/		
</script>
		<!-- 显示Action中传过来的提示信息 -->
		<#if Request['promptInfo']?exists>
			<script>
				_show('${Request['promptInfo']?if_exists}', '提示');
			</script>
		</#if>
</#macro>

  