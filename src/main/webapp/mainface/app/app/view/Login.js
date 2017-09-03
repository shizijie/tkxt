
Ext.define('app.view.Login',{
	extend:'Ext.form.Panel',
	xtype:'loginview',
	config:{
		id:'loginview',
		fullscreen:true,
		items:[
			
			{
				xtype:'fieldset',
				title: '用户登录',
				instructions:'在线题库－用户登录入口',				
				items: [
					{
						xtype: 'textfield',
						label: '登录名',
						name:'loginName',
						id:'loginName'
						//,placeHolder:'请输入登录名'
					},
					{
						xtype: 'passwordfield',
						label: '密&nbsp;&nbsp;&nbsp;&nbsp;码',
						name:'password',
						id:'password'
						//,placeHolder:'请输入密码'
					}				
				]				
			},
				
			{
				xtype: 'button',
				text: '登   录',
				id:'loginButton',
				ui: 'confirm'
			},			
			
			{
				xtype:'toolbar',
				docked:'top',
				title:'在线题库'
			}
		]
	}
	/*
	,
	initialize:function(){
	
		this.callParent();
		
		// 获取之前保存的用户名密码
		var dataStore = Ext.create('Ext.data.Store', {
			model : "app.model.User"
		});
		dataStore.load();
		var index = dataStore.find('id', 1);
		var user = dataStore.getAt(index);

		if (user) {
			this.down('#loginName').setValue(user.get('loginName'));
			this.down('#password').setValue(user.get('password'));
		}
	}
	*/
});
