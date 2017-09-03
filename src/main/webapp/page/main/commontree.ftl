<!DOCTYPE html>
<html xml:lang="en" xmlns="http://www.w3.org/1999/xhtml">


<head>
	<link href="${base}/page/main/style/type.css" type="text/css" rel="stylesheet">
	<#include "/page/main/include/head.ftl" >
</head>

<style type="text/css">

	//html{overflow:hidden;}//隐藏整个页面的滚动条；

</style>

  <script type="text/javascript" src="${base}/scripts/pfw/pfw.page.min.js"></script>

<body>

	<div class="body_main">
	<#include "/page/main/include/menu.ftl" > 
  
  
  
   <!--中间-->
  <div class="mainframe"> 
    <!--层-左-->
    <div class="mainframe_01"> 
      <div class="TypePagelmNavigate">
        <h3><span>${cmsTMenu?if_exists.menuName?if_exists}</span></h3>
        <div style="float:left;padding-left:30px;">
        
        	<#macro genTreeMenu tmenu nbspstr>
        		<#if tmenu.children?exists && tmenu.children?size gt 0>
					<#list tmenu.children as obj>
						<a href="javascript:;" onclick="rightifram('${base}/cms/cms_show!treepage.action?menuId=${obj.id}')">${nbspstr}${obj.menuName}</a>
						<br><br>
						<#if obj.children?exists && obj.children?size gt 0>
							<@genTreeMenu tmenu=obj nbspstr = nbspstr + "&nbsp;&nbsp;&nbsp;" />
						</#if>
					</#list>        			
        		</#if>
        	</#macro>
        	
        	<@genTreeMenu tmenu=cmsTMenu nbspstr="" />
        
        </div>
      </div>
    </div>
    <!--层-右-->
    <div class="mainframe_02" > 
        <!--控件-->
        <div class="TypePageContent" id="TypePageContentmail">

			
          	
        </div>
        
    </div>
  </div>
  
  
  <#include "/page/main/include/foot.ftl" >

  
</div>
</body>

<script type="text/javascript" language="javascript">

    function iFrameHeight() 
    {
        var ifm= document.getElementById("Main");
        var subWeb = document.frames ? document.frames["Main"].document :ifm.contentDocument;
        if(ifm != null && subWeb != null) {
        	ifm.height = subWeb.body.scrollHeight + 100;
        }
    }

	//动态给右侧ifram赋值
	function rightifram(url){
		//先清空右侧文本
		$("#TypePageContentmail").html("<IFRAME onLoad='iFrameHeight()' id='Main' width='100%' height='100%' name='Main' scrolling='auto'  frameborder='0' src='"+url+"' ></IFRAME>");
	}

	$("#TypePageContentmail").html("<IFRAME onLoad='iFrameHeight()' id='Main' width='100%' height='100%' name='Main' scrolling='auto'  frameborder='0' src='${base}/cms/cms_show!treepage.action?menuId=${cmsTMenu.id}' ></IFRAME>");
	

</script>


</html>