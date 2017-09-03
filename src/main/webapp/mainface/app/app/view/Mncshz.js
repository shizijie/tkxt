
Ext.define('app.view.Mncshz',{
	extend:'Ext.form.Panel',
	xtype:'mncshzview',
	alias:'widget.mncshzview',
	config:{
		id:'mncshzview',
		fullscreen:true,
		layout: 'vbox',
		/*
		defaults: {
			layout: {
				type: 'hbox',
				align: 'center'
			},
			defaults: {
				flex:1
			}
		},
		*/
		items: 
		[
			{
				xtype:'toolbar',
				docked:'top',
				title:'模拟测试结果',
				layout: 'vbox',
				items:[
					{
						xtype:'button',
						text: '返回',
						id:'back2mncsbut',
						width:'60px',
						ui:'action'
					}
				]
			}
		]
	}
	
});
