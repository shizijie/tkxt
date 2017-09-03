
//用户登录成功之后，在本地内存中保存用户的登录信息

Ext.define('app.store.Userstore', {
	extend : 'Ext.data.Store',
	storeId: "usersStore",
    model: "app.model.User",
	proxy : {
		type : 'localstorage'
	}
});