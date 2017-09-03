
Ext.define('app.view.Mncs',{
	extend:'Ext.Carousel',
	xtype:'mncsview',
	alias:'widget.mncsview',
	//requires:''
	config:{
		id:'mncsview',
		fullscreen:true,
		direction:'horizontal',
		indicator:false,
		items:[
			
			{
				xtype:'titlebar',
				id:'ttbtop3',
				title:'模拟测试',
				docked:'top',
				items:[
					{
						xtype:'button',
						text: '结束测试',
						id:'backxscywjBt3',
						ui:'confirm',
						align:'right'
					},
					{
						xtype:'button',
						text: '0/0',
						id:'dtqk2',
						ui:'action',
						align:'left'
					}
					/*
					,
					{
						xtype:'button',
						text: '提交',
						id:'subButton2',
						align:'right',
						ui:'confirm'
					}
					*/
				]
			}
		]
	}
});
