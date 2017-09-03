//学生完善信息

Ext.define('app.view.QyWsxx',{
	extend:'Ext.form.Panel',
	xtype:'qywsxxview',
	alias:'widget.qywsxxview',
	//requires:''
	config:{
		id:'qywsxxview',
		fullscreen:true,
		items:[		
			{
				xtype:'fieldset',
				title: '第一步：确认您的个人信息',
				instructions:'完善您的个人信息，也能获得10个积分哦',				
				items: [
					{
						xtype: 'hiddenfield',
						name:'qyid',
						id:'qyid'
					},
					{
						xtype: 'textfield',
						label: '企业帐号',
						required:true,
						name:'qyyhzh',
						id:'qyyhzh'
					},
					{
						xtype: 'textfield',
						label: '企业名称',
						required:true,
						name:'qymc',
						id:'qymc'
					},
					{
						xtype: 'textfield',
						label: '组织机构编码',
						required:true,
						name:'zzjgdm',
						id:'zzjgdm'
					},
					{
						xtype: 'textfield',
						label: '地址',
						required:true,
						name:'qydz',
						id:'qydz'
					},
					{
						xtype: 'textfield',
						label: '企业联系人',
						required:true,
						name:'qylxr',
						id:'qylxr'
					},
					{
						xtype: 'textfield',
						label: '固定电话',
						required:true,
						name:'gdlxdh',
						id:'gdlxdh'
					},
					{
						xtype: 'textfield',
						label: '联系人手机',
						required:true,
						name:'sjlxdh',
						id:'sjlxdh'
					}
				]				
			},
				
			{
				xtype: 'button',
				text: '下一步',
				id:'nexButtonQy',
				ui: 'confirm'
			}		

			
			
		]
	},
	initialize:function(){
		this.callParent();
	}
});
