
Ext.define('app.view.Txwj',{
	extend:'Ext.form.Panel',
	xtype:'txwjview',
	alias:'widget.txwjview',
	//requires:''
	config:{
		id:'txwjview',
		fullscreen:true,
		items:[
			{
				xtype:'titlebar',
				id:'ttbtop',
				title:'填写问卷',
				docked:'top',
				items:[
					{
						xtype:'button',
						text: '返回',
						id:'backxscywjBt',
						ui:'back'
					},
					{
						xtype:'button',
						text: '提交',
						id:'subButton',
						align:'right',
						ui:'confirm'
					}
				]
			}
			/*
			,
			{
				xtype: 'hiddenfield',
				name:'resultSelectStr',
				id:'resultSelectStr',
				value:''
			},
			{
				xtype: 'hiddenfield',
				name:'resultBlankStr',
				id:'resultBlankStr',
				value:''
			},
			{
				xtype: 'hiddenfield',
				name:'tk2',
				id:'tk2',
				value:''
			},
			{
				xtype: 'hiddenfield',
				name:'pcid',
				id:'pcid',
				value:''
			},
			{
				xtype: 'hiddenfield',
				name:'wjid',
				id:'wjid',
				value:''
			}	
			*/
		]
	}
});
