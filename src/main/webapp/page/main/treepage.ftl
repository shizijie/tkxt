<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>南洋模范中学</title>
	<link rel="stylesheet" type="text/css" href="${base}/page/main/style/type2.css">
	<script type="text/javascript" src="${base}/page/main/js/jquery.js"></script>
	<script type="text/javascript" src="${base}/scripts/pfw/pfw.page.min.js"></script>
</head>

<body >

  <#assign postStr = "">
  <#assign flg = 0>
  <#macro genPostion menue>
  	<#if (menue?exists)>
  			<#assign postStr =  "<a href='${base}/cms/cms_show!treepage.action?menuId="+menue.id+"'>"+menue.menuName + "</a> > " + postStr  >
  		<#if (menue.menuParent?exists)>
  			<#assign flg = flg + 1>
  			<@genPostion menue=menue.menuParent/>
  		</#if>
  	</#if>
  </#macro>
  <@genPostion menue=cmsTMenu/>
 
<form id="infoFrom" action="#" method="post"> 
<div class="mainframe_02"> 
    <!--控件-->
    <div class="TypePageContent">
          <h3><span>${postStr!}</span></h3>
          
          <#if cmsTMenu.style == "1" || cmsTMenu.style == "2" || cmsTMenu.style == "6">
          	<form id="infoFrom" action="${base}/cms/cms_show!treepage.action?menuId=${menuId}" method="post">
          	<div class="typelist">
          		<ul class="list">
					<#list page.result as obj>
						<li>
							<#if cmsTMenu.style == "6">
								<a href="javascript:;" onclick="window.open('${base}/cms/cms_show!pageSubInfo.action?contentId=${obj.id}')">[${obj.cmsTMenu.menuName}]&nbsp;&nbsp;
									<#if obj.articleName?length gt 40>
										${obj.articleName?substring(0,40)}....
									<#else>
										${obj.articleName}
									</#if>
								</a> <span class="new"></span><span class="pic"></span><span class="puber">管理员</span><span class="time">${obj.addDate[0..10]!}</span>
							<#else>
								<a href="javascript:;" onclick="window.open('${base}/cms/cms_show!pageSubInfo.action?contentId=${obj.id}')">
								<#if obj.articleName?length gt 40>
										${obj.articleName?substring(0,40)}....
									<#else>
										${obj.articleName}
									</#if>
								</a> <span class="new"></span><span class="pic"></span><span class="puber">管理员</span><span class="time">${obj.addDate[0..10]!}</span>
							</#if>
							 
						</li>
					</#list> 
				</ul>        
				
				<center style=" display:block;widht:100%;height:40px;float:right;line-height:40px;padding-right:25px;">
	                <table id="conTbl" >
					</table>
					</center>
				 	
          	</div>
          	</form>
          <#elseif cmsTMenu.style == "3">
          	<div class="type_con">
          		${media?if_exists.remark?if_exists}
          	</div>
          </#if>
        </div>
        
</div>
</form>

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