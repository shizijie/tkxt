
//用户登录成功之后，在本地内存中保存用户的登录信息

Ext.define('app.store.Wtflstore', {
	
	extend : 'Ext.data.Store',
	config : {
		storeId:'wtflstore',
		fields : ['id', 'flmc', 'sxh'],
		autoLoad:true,
		proxy: {
			type: 'jsonp',
			url: prop.baseUrl+'/app!getwdjf.action',
			reader: {
				type: 'json',
				rootProperty: 'resultinfo'
			}
		}
	}
});