Ext.application({
    name: 'app',
	views:['Xsmain','Login','XsWsxx','QyWsxx','XsCywj','Txwj','Txwj2','Mncs','Ctlx','Mncshz'],
	models:['User','XsInfo','QyInfo','Yhctj'],
	controllers:['Login','XsWsxx','QyWsxx','XsCywj','Txwj','Txwj2','Mncs','Ctlx','Mncshz'],
	//stores:['Wdjfstore','Userstore'],
    launch: function() {
	
		Ext.Loader.setConfig({
			enabled: true
		});
	
		Ext.fly('appLoadingIndicator').destroy();
	
		Ext.Viewport.add(Ext.create('app.view.Xsmain'));
		
		/*
		var lgview = Ext.create('app.view.Login');
		Ext.Viewport.add(lgview);
		
		// 获取之前保存的用户名密码
		var dataStore = Ext.create('Ext.data.Store', {
			model : "app.model.User"
		});
		dataStore.load();
		var index = dataStore.find('id', 1);
		var user = dataStore.getAt(index);

		if (user) {
			Ext.getCmp('loginName').setValue(user.get('loginName'));
			Ext.getCmp('password').setValue(user.get('password'));
		}
		*/
		
    }
});