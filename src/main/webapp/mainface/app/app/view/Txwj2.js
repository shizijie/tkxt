
Ext.define('app.view.Txwj2',{
	extend:'Ext.Carousel',
	xtype:'txwjview2',
	alias:'widget.txwjview2',
	//requires:''
	config:{
		id:'txwjview2',
		fullscreen:true,
		direction:'horizontal',
		indicator:false,
		items:[
			
			{
				xtype:'titlebar',
				id:'ttbtop2',
				title:'章节练习',
				docked:'top',
				items:[
					{
						xtype:'button',
						text: '结束练习',
						id:'backxscywjBt2',
						ui:'confirm',
						align:'right'
					},
					{
						xtype:'button',
						text: '0/0',
						id:'dtqk',
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
