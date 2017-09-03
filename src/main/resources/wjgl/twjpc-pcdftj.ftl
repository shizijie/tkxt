<#include "/macro/crud.ftl" >

<@crud> 
	<form id="user_form" action="${base}/wjgl/twjpc!pcjgtj.action" method="post">
	
		<table id="user_page_search" >
			<tr>
		      <td class="textfield">批次名称</td>
		      <td colspan="3">
		      	<input type="text" id="filter_LIKES_pzmc" name="filter_LIKES_pzmc"  value="${Parameters['filter_LIKES_pzmc']?if_exists}"/>
		      </td>		      
		    </tr>
		     <tr> 
		      <td colspan=4>		      
		      	<center><input type="submit" value="查询"/></center>
		      </td>
		    </tr>
		</table>
				
		<table id="user_page_table">
			<#list page.result as obj>
			<tr title="${obj.id}">
				<td>
					 ${obj.pzmc?if_exists}
				</td>
				<td>
					${obj.kssj?if_exists}
				</td>
				<td>
					${obj.jssj?if_exists}
				</td>
				<td>
					<#list obj.flist as tmpfa>
						<a href="javascript:void(0)" onclick="window.open('${base}/wjgl/twjpc!jgtjview.action?pcid=${obj.id}&wjid=${tmpfa.id}')">
							<font color=red>${tmpfa.famc!}</font>
						</a>
						<br><br>
					</#list>
				</td>
			</tr>
			</#list>
		</table>
	</form>	
	<script type="text/javascript">
		
		
		$(document).ready(function(){
					
			$('#user_form').pfwpage({
				page_button:[
				],
				page_col:[
					{col_name:'批次名称'},
					{col_name:'开始时间'},
					{col_name:'结束时间'},
					{col_name:'使用问卷',width:'50%'}
					],
				page_table:'#user_page_table',
				page_table_search:'#user_page_search',
				page_pageNo:${page.pageNo},
				page_pageSize:${page.pageSize},
				page_orderBy:'${page.orderBy}',
				page_order:'${page.order}',
				page_totalPages:${page.totalPages},
				page_totalCount:${page.totalCount}
				});
		});

	</script>
</@crud>
