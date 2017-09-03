

Ext.define('app.view.XsCywj',{
	extend:'Ext.tab.Panel',
	xtype:'xscywjview',
	alias:'widget.xscywjview',
	config:{
		id:'xscywjview',
		fullscreen:true,
		tabBarPosition: 'bottom',
		tabBar:{
			defaults:{
				margin:'0 7'
			}
		},
		items:[		
			
			{
				title: '章节练习',
				iconCls: 'refresh',
				items:
				[
					{
						xtype:'toolbar',
						title:'章节练习',
						docked:'top'
					}
				]
			},
			{
				
				title: '模拟测试',
				iconCls: 'time',
				items:
				[
					{
						xtype:'toolbar',
						title:'模拟测试',
						docked:'top'
					},
					{
						xtype:'button',
						text:'开始测试',
						id:'mlcsButton',
						margin:'170px 50px 50px 50px',
						ui:'confirm',
						iconCls:'arrow_right',
						iconAlign:'right'
					}
				]
			},
			{
				xtype:'formpanel',
				title: '错题集',
				iconCls: 'delete',
				items:
				[
					{
						xtype:'toolbar',
						title:'错题库',
						docked:'top'
					},
					{
						xtype:'label',
						id:'ctxxLabel',
						margin:'100px 10px 50px 10px'
						//html:'<center><b><font size="3px" >您共有</font>&nbsp;&nbsp;<font size="5px" color="red">198</font></font>&nbsp;&nbsp;<font size="3px">道错题</font></b></center>'
					},
					{
						xtype:'button',
						text:'进入练习',
						id:'ctlxButton',
						margin:'10px 10px 50px 10px',
						ui:'confirm',
						iconCls:'arrow_right',
						iconAlign:'right'
					}
				]
			},
			{
				xtype:'formpanel',
				title: '我',
				iconCls: 'user',
				items:[
					{
						html:'<center><img src="./css/images/nohead.jpg" style="width:100px;height:130px;margin-top:30px;"/>'
						+'<p id="yhxm"></p>'
						+'</center><br>'
					},
					{
						xtype:'button',
						id:'aqtcButton',
						text:'安全退出',
						ui:'confirm',
						//icon:'',
						margin:'0px 20px 0px 20px'
					},
					{
						xtype:'toolbar',
						docked:'top',
						title:'我'
						//style:'background-color:#60a410;background-image:none;'
					}
				]
			}
			/*
			{
				
				title: '个人信息',
				iconCls: 'user',
				items:[		
					{
						xtype:'fieldset',
						title: '请确认您的个人信息',
						instructions:'完善您的个人信息，也能获得10个积分哦',				
						items: [
							{
								xtype: 'textfield',
								label: '学号',
								required:true,
								name:'xh',
								id:'xh2'
							},
							{
								xtype: 'textfield',
								label: '姓名',
								required:true,
								name:'xm',
								id:'xm2'
							},
							{
								xtype: 'hiddenfield',
								name:'txsjbxxid',
								id:'txsjbxxid2'
							},
							{
								xtype: 'textfield',
								label: '就业单位',
								required:true,
								name:'qydwmc',
								id:'qydwmc2'
							},
							{
								xtype: 'textfield',
								label: '就业岗位',
								required:true,
								name:'jygw',
								id:'jygw2'
							},
							{
								xtype: 'textfield',
								label: '联系电话',
								required:true,
								name:'lxdh',
								id:'lxdh2'
							},
							{
								xtype: 'textfield',
								label: '邮箱地址',
								required:true,
								name:'yxdz',
								id:'yxdz2'
							}					
						]				
					},
						
					{
						xtype: 'button',
						text: '保存',
						id:'nexButton2',
						ui: 'confirm'
					}		
				]
				
			}
			*/
		]
	}
});
