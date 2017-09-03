
Ext.define('app.controller.XsCywj',{
	extend:'Ext.app.Controller',
	init:function(){
		//todosomthing
	},
	launch:function(){
		
	},
	config:{
		refs:{
			xsmainview:'xsmainview',
			xscywjview:'xscywjview',
			txwjview:'txwjview',
			xswsxxview:'xswsxxview',
			txsjbxxid:'#txsjbxxid2',
			xh:'#xh2',
			xm:'#xm2',
			qydwmc:'#qydwmc2',
			jygw:'#jygw2',
			lxdh:'#lxdh2',
			yxdz:'#yxdz2',
			nexButton2:'#nexButton2',
			qyyhzh2:'#qyyhzh2',
			qymc2:'#qymc2',
			zzjgdm2:'#zzjgdm2',
			qydz2:'#qydz2',
			qylxr2:'#qylxr2',
			gdlxdh2:'#gdlxdh2',
			sjlxdh2:'#sjlxdh2',
			mlcsButton:'#mlcsButton',
			ctxxLabel:'#ctxxLabel',
			ctlxButton:'#ctlxButton',
			aqtcButton:'#aqtcButton'
		},
		control:{
			
			xscywjview:{
				activeitemchange:'tabchange'
			}
			,
			mlcsButton:{
				tap:'mlcsStart'
			},
			ctlxButton:{
				tap:'toCtlx'
			}
			,
			aqtcButton:{
				tap:'exitapp'
			}
			
		},
		routes:{
			'xscywj':'toxscywj'
		}
	},
	exitapp:function(){
		
		//清除本地用户数据
		var dataStore = Ext.create('Ext.data.Store', {
			model : "app.model.User"
		});
		dataStore.getProxy().clear();
		dataStore.load();
		
		/*
		//清除本地用户错题集数据
		var dataStore2 = Ext.create('Ext.data.Store', {
			model : "app.model.Yhctj"
		});
		dataStore2.getProxy().clear();
		dataStore2.load();
		*/
		this.redirectTo("login");
		
	},
	mlcsStart:function(){
		this.redirectTo("mncs");
	},
	toCtlx:function(){
		console.log("11");
		this.redirectTo("ctlx");
	},
	tabchange:function (xscywjview, value, oldValue, eOpts){
		var title = value.config.title;
		if(title == "错题集")
		{
			//得到错题总数
			var dataStore = Ext.create('Ext.data.Store', {
				model : "app.model.User"
			});
			dataStore.load();
			var index = dataStore.find('id', 1);
			var user = dataStore.getAt(index);
			var loginName = user.get('loginName');
			
			var dataStore2 = Ext.create('Ext.data.Store', {
				model : "app.model.Yhctj"
			});
			dataStore2.load();
			var ctCount = 0;
			for (var i = 0; i < dataStore2.getCount(); i++) 
			{
				
				var record = dataStore2.getAt(i);
				if(record.get('id').indexOf(loginName) >= 0)
					ctCount++;
			}
			var ctxxLabel = this.getCtxxLabel()
			ctxxLabel.setHtml('<center><b><font size="3px" >您共有</font>&nbsp;&nbsp;<font size="5px" color="red">'+ctCount+'</font></font>&nbsp;&nbsp;<font size="3px">道错题</font></b></center>');
			//如果没有错题，就不让点击进去
			/*
			if(ctCount == 0)
			{
				var ctlxButton = this.getCtlxButton();
				//console.log("---11");
				//ctlxButton.clearListeners();
			}
			*/
			
		}
		
		if (title == "参与问卷"){
		
			var tmpstore = Ext.getStore("pcfaStore");
			tmpstore.load();
			
			xscywjview.getAt(0).removeAll();
			var pafaList=Ext.create('Ext.List',{
				height:'100%',
				store:tmpstore,
				itemTpl:'<div class="list-item-title">{famc}</div><div class="list-item-narrative">{kssj} 至 {jssj}</div>',
				emptyText:'<div class="notes-list-empty-text">没有需要您参与的问卷</div>',
				listeners:{
					itemtap:{fn:this.totxwj,scope:this}
				}

			});
			xscywjview.getAt(0).add(pafaList);
		}
		if (title == "我的积分")
		{
			if(prop.userRoleStr == prop.xsRoleStr){
				Ext.data.JsonP.request({
					url:prop.baseUrl+'/app!getwdjf.action',
					params:{loginName:prop.userLoginName},
					scope:this,
					success:function(data){
						if(data.success == 'true'){
							xscywjview.getAt(2).setHtml('<br><br><br><center><b><font size="5">截止到目前，您总共获得了   <font color="red">'+data.resultinfo.wdjf+'</font>   个积分</font></b></center>');			
						}else{
							Ext.Msg.alert(data.promptinfo);
						}
						
					}
				});	
			}
			
			if(prop.userRoleStr == prop.qiyeRoleStr)
			{
				Ext.data.JsonP.request({
					url:prop.baseUrl+'/app!getqywdjf.action',
					params:{loginName:prop.userLoginName},
					scope:this,
					success:function(data){
						if(data.success == 'true'){
							xscywjview.getAt(2).setHtml('<br><br><br><center><b><font size="5">截止到目前，您总共获得了   <font color="red">'+data.resultinfo.wdjf+'</font>   个积分</font></b></center>');			
						}else{
							Ext.Msg.alert(data.promptinfo);
						}
						
					}
				});
			}
			
		}
		if (title == "我")
		{
			var dataStore = Ext.create('Ext.data.Store', {
				model : "app.model.User"
			});
			dataStore.load();
			var index = dataStore.find('id', 1);
			var user = dataStore.getAt(index);
			var loginName = user.get('loginName');
			var name = user.get('name');
			
			var ttt = Ext.get("yhxm");
			ttt.dom.innerHTML = name+"("+loginName+")";
		}
	},
	toxscywj:function(){
		var xsmainview = this.getXsmainview(),
			xscywjview = this.getXscywjview();
			
		xsmainview.getLayout().setAnimation({type:'slide',direction:'right'});
		xsmainview.setActiveItem(xscywjview);
		
		xscywjview.setActiveItem(0);
		
		xscywjview.getAt(0).removeAll();
		var pcfaStore = Ext.create('Ext.data.Store',{
			id:'pcfaStore',
			autoLoad:true,
			fields: ['id', 'flmc', 'sxh'],
			proxy: {
				type: 'jsonp',
				url: prop.baseUrl+'/app!getallfl.action?loginName='+prop.userLoginName,
				reader: {
					type: 'json',
					rootProperty: 'resultinfo'
				}
			}
		});
		var pafaList=Ext.create('Ext.List',{
			height:'100%',
            store:pcfaStore,
            itemTpl:'<div class="list-item-title">{flmc}</div>',
            emptyText:'<div class="notes-list-empty-text">未设置章节</div>',
			listeners:{
				itemtap:{fn:this.totxwj,scope:this}
			}

        });
		xscywjview.getAt(0).add(pafaList);
		
		/*
		////////////////////////////////////////////////////////////////////////////更新错题集panel
		//得到错题总数
		var dataStore = Ext.create('Ext.data.Store', {
			model : "app.model.User"
		});
		dataStore.load();
		var index = dataStore.find('id', 1);
		var user = dataStore.getAt(index);
		var loginName = user.get('loginName');
		
		var dataStore2 = Ext.create('Ext.data.Store', {
			model : "app.model.Yhctj"
		});
		dataStore2.load();
		var ctCount = 0;
		for (var i = 0; i < dataStore2.getCount(); i++) 
		{
			
			var record = dataStore2.getAt(i);
			if(record.get('id').indexOf(loginName) >= 0)
				ctCount++;
		}
		var ctxxLabel = this.getCtxxLabel()
		ctxxLabel.setHtml('<center><b><font size="3px" >您共有</font>&nbsp;&nbsp;<font size="5px" color="red">'+ctCount+'</font></font>&nbsp;&nbsp;<font size="3px">道错题</font></b></center>');
		
		
		//////////////////////////////////////////////////////////////////////////////更新用户信息
		var dataStore = Ext.create('Ext.data.Store', {
			model : "app.model.User"
		});
		dataStore.load();
		var index = dataStore.find('id', 1);
		var user = dataStore.getAt(index);
		var loginName = user.get('loginName');
		var name = user.get('name');
		
		var ttt = Ext.get("yhxm");
		ttt.dom.innerHTML = name+"("+loginName+")";
		*/
	},
	totxwj:function(list, index, target, record, e, eOpts){
		//prop.selectWjid = record.get('id');
		//prop.selectPcid = record.get('pcid');
		
		//this.redirectTo("txwj");
		this.redirectTo("txwj2/"+record.get('id'));
		
	}
});