
//用户登录成功之后，在本地内存中保存用户的登录信息

Ext.define('app.store.Wdjfstore', {
	
	extend : 'Ext.data.Store',
	config : {
		storeId:'wdjfstore',
		fields : ['wdjf'],
		//autoLoad:true,
		proxy: {
			type: 'jsonp',
			url: prop.baseUrl+'/app!getwdjf.action?loginName='+prop.userLoginName,
			reader: {
				type: 'json',
				rootProperty: 'resultinfo'
			}
		}
	}
});