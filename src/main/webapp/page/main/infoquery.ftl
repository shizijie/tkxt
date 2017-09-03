<!DOCTYPE html>
<html xml:lang="en" xmlns="http://www.w3.org/1999/xhtml">


<head>
	<link href="${base}/page/main/style/type.css" type="text/css" rel="stylesheet">
	<#include "/page/main/include/head.ftl" >
	
	<script type="text/javascript" src="${base}/scripts/pfw/pfw.page.min.js"></script>
</head>
<body>
<form id="infoFrom" name="infoFrom" action="${base}/cms/cms_show!infoquery.action" method="post">
	<div class="body_main">
	<#include "/page/main/include/menu.ftl" > 
  
   <!--中间-->
   
  <div class="mainframe"> 
    <!--层-左-->
    <div class="mainframe_01"> 
      <div class="TypePagelmNavigate">
        <h3><span>新闻搜索</span></h3>
        <input type="text" size="15" style="width:150px" value="" id="filter_LIKES_articleName" name="filter_LIKES_articleName" value="${Parameters['filter_LIKES_articleName']?if_exists}">
        <button type="submit" id="search"> 搜 索 </button>
      </div>
    </div>
    <!--层-右-->
    <div class="mainframe_02"> 
        <!--控件-->
        <div class="TypePageContent">
        	  <h3><span>搜索结果</span></h3>
        	  
	              <div class="typelist">
	                <ul class="list">
	                	<#list page.result as obj>
	                		<li>
	                			<a href="javascript:;" onclick="window.open('${base}/cms/cms_show!pageSubInfo.action?contentId=${obj.id}')">[${obj.cmsTMenu.menuName}]&nbsp;&nbsp;${obj.articleName}</a> <span class="new"></span><span class="pic"></span><span class="puber">管理员</span><span class="time">${obj.addDate[0..10]!}</span>
	                		</li>	
	                	</#list>	
	                </ul>
	                <center style=" display:block;widht:100%;height:40px;float:right;line-height:40px;padding-right:25px;">
	                <table id="conTbl" >
					</table>
					</center>
	              </div>
              
        </div>
    </div>
  </div>
  
  <#include "/page/main/include/foot.ftl" >

  
</div>
<form>
</body>
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
</html>