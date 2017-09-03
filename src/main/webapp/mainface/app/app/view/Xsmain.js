Ext.define('app.view.Xsmain', {
    extend: 'Ext.Container',  
    xtype:'xsmainview',
    requires: [
		'app.view.Login',
		'app.view.XsWsxx',
		'app.view.QyWsxx',
		'app.view.XsCywj',
		'app.view.Txwj',
		'app.view.Txwj2',
		'app.view.Mncs',
		'app.view.Ctlx',
		'app.view.Mncshz'
    ],   
    config: {
        fullscreen:true,
        layout:'card',
        items:[
            {
                xtype: 'loginview'
            },
            {
                xtype: 'xswsxxview'
            },
			{
                xtype: 'qywsxxview'
            },
            {
                xtype: 'xscywjview'
            },
            {
                xtype: 'txwjview'
            },
            {
                xtype: 'txwjview2'
            }
            ,
            {
                xtype: 'mncsview'
            },
            {
                xtype: 'ctlxview'
            },
            {
                xtype: 'mncshzview'
            }
			
        ]
    }
});
