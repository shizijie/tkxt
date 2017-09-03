
Ext.define('app.view.Ctlx',{
	extend:'Ext.Carousel',
	xtype:'ctlxview',
	alias:'widget.ctlxview',
	//requires:''
	config:{
		id:'ctlxview',
		fullscreen:true,
		direction:'horizontal',
		indicator:false,
		items:[
			
			{
				xtype:'titlebar',
				id:'ttbtop4',
				title:'错题练习',
				docked:'top',
				items:[
					{
						xtype:'button',
						text: '结束练习',
						id:'backxscywjBt4',
						ui:'confirm-back'
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
