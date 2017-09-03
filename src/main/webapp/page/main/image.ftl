<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#include "/macro/crud.ftl" >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>上海新朋程培训中心</title>
<link href="${base}/page/pthink/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${base}/page/pthink/css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/page/pthink/js/jquery.js"></script>
  <script type="text/javascript" src="${base}/scripts/pfw/pfw.page.min.js"></script>
<script>
$(document).ready(function() {
$(".top_nav ul li").hover(function(){
	$(this).find("dl").eq(0).css("display","block");
	},function(){
	$(this).find("dl").eq(0).css("display","none");
}); 
});
</script>
</head>

<body>
<!--top-->
<div class="top_area">
  <div class="top_language">
     <a href="#">简体中文</a> |
    <a href="#">ENGLISH</a>
  </div>
  <#include "/page/pthink/include/menu.ftl" >
  </div>
<!--top end-->



<!--content-->
<div class="content">
<!--content left-->
<#include "/page/pthink/include/left.ftl" >
<!--content left end-->

<script type="text/javascript">
	$(document).ready(function() {
		$('#infoFrom').pfwpage({
			page_table:'#conTbl',
			page_pageNo:${page.pageNo},
			page_pageSize:${page.pageSize},
			page_orderBy:'id',
			page_order:'asc',
			page_totalPages:${page.totalPages},
			page_totalCount:${page.totalCount}
		});
	})
</script>
<!--content right-->


<div class="content_right">
	<form id="infoFrom" action="${base}/cms/cms_show!cmsShowInfo.action?menuId=${menuId}" method="post">
		<div class=" photo_2">
		    <ul>
	    		<#list page.result as obj>
	    			<li class="photo_l2">
	    				<a href="${base}/cms/cms_show!pageSubInfo.action?contentId=${obj.id}">
							<img src="../ico/${obj.imageFileName?if_exists}" width="50" height="34" />
						</a>
						<span>
						<a href="${base}/cms/cms_show!pageSubInfo.action?contentId=${obj.id}">
							${obj.articleName}
						</a>
						</span>
	    			</li>
	    		</#list>
		    </ul>
		</div>
		
		<center style=" display:block;widht:100%;height:40px;float:right;line-height:40px;padding-right:25px;">
			<table id="conTbl" >
			</table>
		</center>	
			
	</form>
</div>
<!--content right end-->
</div>
<!--content end-->


<#include "/page/pthink/include/foot.ftl" >


</body>
</html>
