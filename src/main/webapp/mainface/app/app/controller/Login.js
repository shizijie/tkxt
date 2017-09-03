
Ext.define('app.controller.Login',{
	extend:'Ext.app.Controller',
	init:function(){
		//todosomthing
		
	},
	
	config:{
		refs:{
			xsmainview:'xsmainview',
			loginview:'loginview',
			loginButton:'#loginButton',
			loginName:'#loginName',
			password:'#password'
			/*
			,
			loginview:{
				selector:'loginview',
				xtype:'loginview',
				autoCreate:true
			},
			*/
			
		},
		control:{
			loginButton:{
				tap:'doLogin'
			}
		},
		routes:{
			'login':'tologinview'
		}
	},
	doLogin:function(){
		Ext.Viewport.mask();
		Ext.Viewport.setMasked({
			xtype : 'loadmask',
			message : "用户登录中..."
		});
		var loginName = Ext.getCmp('loginName').getValue();
		var password = Ext.getCmp('password').getValue();
		var params = {
					loginName : loginName,
					password : password
				};

		Ext.data.JsonP.request({
			url:prop.baseUrl+'/app!dologin.action',
			params:params,
			scope:this,
			timeout: 10000,
			failure: function(response) {
				Ext.Msg.alert('连接超时,请检查网络');
				Ext.Viewport.unmask();
			},
			success:function(data){
				if(data.success == 'true'){
				
					/*
					Ext.regStore("userlocal",{
						model:'User',
						autoLoad: true
					});
					*/
					var userRoleList = data.resultinfo.roleList;
					
					var tmpRoleStr = "";
					var tmpuserLoginName = "";
					for(var i =0;i < userRoleList.length;i++){
						if(userRoleList[i].jsdm == prop.xsRoleStr || userRoleList[i].jsdm == prop.qiyeRoleStr)
						{
							tmpRoleStr = userRoleList[i].jsdm;
							tmpuserLoginName = loginName;
						}
					}
					
					/*
					var userstorelocal = Ext.getStore('userlocal');
					userstorelocal.create({
						id:1,
						loginName:loginName,
						password:password,
						name:data.resultinfo.name,
						roleList:tmpRoleStr
					});
					*/
					
					var userstorelocal = Ext.create('app.model.User',{
						id:1,
						loginName:loginName,
						password:password,
						name:data.resultinfo.name,
						roleList:tmpRoleStr
					});
						
					
					userstorelocal.save();
					
					prop.userLoginName = loginName;
					prop.userName = tmpuserLoginName;
					prop.userRoleStr = tmpRoleStr;
					
					if(tmpRoleStr == prop.xsRoleStr)
					{
						//this.redirectTo('xswsxx');
						this.redirectTo("xscywj");
						
						Ext.Viewport.unmask();
						
					}else if(tmpRoleStr == prop.qiyeRoleStr)
					{
						this.redirectTo("xscywj");
						//this.redirectTo('qywsxx');
						
						Ext.Viewport.unmask();
					}else{
						Ext.Msg.alert('非法身份,无法登录');
						Ext.Viewport.unmask();
					}
					
				}else{
					Ext.Msg.alert(data.promptinfo);
					Ext.Viewport.unmask();
				}
				
			}
		});			
		
	},
	tologinview:function(){
		
		var xsmainview = this.getXsmainview(),
			loginview = this.getLoginview();
		xsmainview.setActiveItem(loginview);
		var loginnameInput = this.getLoginName();
		var passwordInput = this.getPassword();
		loginnameInput.setValue("");
		passwordInput.setValue("");
		
		
		// 获取之前保存的用户名密码
		dataStore = Ext.create('Ext.data.Store', {
			model : "app.model.User"
		});
		dataStore.load();
		
		var index = dataStore.find('id', 1);
		var user = dataStore.getAt(index);
		//console.log(user.get('loginName'));
		if (user) 
		{
			console.log("---111");
			loginnameInput.setValue(user.get('loginName'));
			passwordInput.setValue(user.get('password'));
		}
	},
});


	