<#include "/macro/crud.ftl" >

<@crud> 
	<form id="user_form" action="${base}/wjgl/twjjgtxr!rywjdf.action" method="post">
	
		<table id="user_page_search" >
			<tr>
				<td class="textfield">批次</td>
				<td>
			      	<select style="width:200px;" name="filter_EQS_pcid.id" id="pcid_id" onchange="loadfa(this.value)">
			      		<option value="">==全部==</option>
			      		<#list menus as ojb>
			      			<option value="${ojb.id}"   <#if Parameters['filter_EQS_pcid.id']?exists && Parameters['filter_EQS_pcid.id']="${ojb.id}">selected</#if>>${ojb.pzmc}</option>
			      		</#list>
			      	</select>				  					
				</td>
				<td class="textfield">方案</td>
				<td>
					<input type="hidden" name="sydckhui"  id="sydckhui" value="${Parameters['sydckhui']?if_exists}"/>
					<INPUT id="sydck" class="mini-treeselect" popupWidth="300" style="width:150px;" onvaluechanged="onValueChanged"
						oncloseclick="onCloseClick" showClose="true" expandOnLoad="true" autoCheckParent="false"
						showFolderCheckBox="true" checkRecursive="true"  parentField="pid"
						valueField="id" textField="famc" multiSelect="true" url="" text="请选择...">
				</td>
				<td class="textfield">填写人/联系电话/E-mail</td> 
				<td>
					<input type="text" name="filter_LIKES_txr_OR_sjh_OR_yxdz" id="filter_LIKES_txr_OR_sjh_OR_yxdz" value="${Parameters['filter_LIKES_txr_OR_sjh_OR_yxdz']?if_exists}" />
				</td>     
		    </tr>
		     <tr> 
		      <td colspan=6>		      
		      	<center>
		      		<input type="submit" value="查询"/>&nbsp;&nbsp;&nbsp;&nbsp;
		      		<input type="button" name="reset" id="reset" value="重置"/>
		      	</center>
		      </td>
		    </tr>
		</table>
				
		<table id="user_page_table">
			<#list page.result as obj>
			<tr title="${obj.id}">
				<td>
					 ${obj.txr?if_exists}
				</td>
				<td>
					${obj.sjh?if_exists}
				</td>
				<td>
					${obj.yxdz?if_exists}
				</td>
				<td>
					${obj.dxsj?if_exists}
				</td>
				<td>
					${obj.pcid?if_exists.pzmc?if_exists}
				</td>
				<td>
					${obj.wjid?if_exists.famc?if_exists}
				</td>
				<td>
					<a href="javascript:void(0)" onclick="window.open('${base}/wjgl/twjjgtxr!txrwjdf.action?id=${obj.id!}&pcid=${obj.pcid?if_exists.id?if_exists}')">
						${obj.ipdz!}
					</a>
				</td>
			</#list>
		</table>
	</form>	
	<script type="text/javascript">
		
		//分类的值发生变化时触发事件
		function onValueChanged(e) {
			var obj = e.sender;
			$("#sydckhui").val(obj.getValue());
		}
		//点击分类的X清空专业时触发事件
		function onCloseClick(e) {
		    var obj = e.sender;
		    obj.setValue("");
		    obj.setText("请选择...");
		    $("#sydckhui").val("");
		}	
		
		//点击重置按钮可调用方法，清空分类
		function resetFlXx() {
		    var obj = mini.get("sydck");
		    obj.setData("[]");
		    obj.setText("请选择...");
		    $("#sydckhui").val("");
		}		
		
		function loadfa(faVal){
			var obj = mini.get("sydck");
			 $.ajax({
			    type : "POST",
			    data : "{}",
			    url : '${base}/wjgl/twjpc!getFajsonBypc.action?pcid='+faVal,
			    dataType : "json",
			    contentType : "application/json",
			    success : function(data) {
			      obj.loadList(data, "id", "famc");
			      if ($("#sydckhui").val() != "") {
			        obj.setValue($("#sydckhui").val());
		          }
			    },
			    error : function(XMLHttpRequest, textStatus,errorThrown) {
			      window.alert("服务器未成功加载该方案下的问卷分类:" + errorThrown);
			    }
			 });
		}	
		
		$(document).ready(function(){
		
			//第一次页面加载时
			var faid=$("#pcid_id").val();
			if (faid != "undefine" && faid != "" && faid != null) {
				var obj = mini.get("sydck");
				if (obj != undefined) {
					loadfa(faid);
				}
			}
					
			$('#user_form').pfwpage({
				page_button:[
				],
				page_col:[
					{col_name:'填写人'},
					{col_name:'联系电话'},
					{col_name:'E-mail'},
					{col_name:'填写时间'},
					{col_name:'批次'},
					{col_name:'参与问卷'},
					{col_name:'得分'}
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
				
				
				$("input[value='全选']").remove();
				$("input[value='重选']").remove();
				
		});


		$("#reset").click(function(){
				$("input[name^='filter_']").val("");
				$("select[name^='filter_']").val("");
				$("[name='teacher']").val("");
				resetFlXx();
		});
		
		
			
	</script>
</@crud>
