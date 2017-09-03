
//用户登录成功之后，在本地内存中保存用户的登录信息

Ext.define('app.model.User', {
	extend : 'Ext.data.Model',
	config : {
		fields : ['id','loginName','password','name','roleList'],
		proxy : {
			type : 'localstorage',
			id : 'userProxy'
		}
	}
});