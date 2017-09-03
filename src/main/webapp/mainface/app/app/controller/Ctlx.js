
Ext.define('app.controller.Ctlx',{
	extend:'Ext.app.Controller',
	init:function(){
		//todosomthing
	},
	launch:function(){
		
	},
	config:{
		refs:{
			xsmainview:'xsmainview',
			ctlxview:'ctlxview',
			xscywjview:'xscywjview',
			backxscywjBt4:'#backxscywjBt4'
		},
		control:{
			backxscywjBt4:{
				tap:'backxscywjBtFun'
			}
		},
		routes:{
			'ctlx':'tothisview'
		}
	},
	backxscywjBtFun:function(){
		var ctlxview = this.getCtlxview();
		ctlxview.removeAll();
		this.redirectTo("xscywj");
		
	},
	shenchenwt:function(pageNo){
		
		
		var txwjview2 = this.getCtlxview();
			
		var me = this;
		var ids = "";
		
		//得到所有错题id数用于后台分页查询条件
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
		for (var i = 0; i < dataStore2.getCount(); i++) 
		{
			
			var record = dataStore2.getAt(i);
			if(record.get('id').indexOf(loginName) >= 0)
				ids += "'"+record.get('wtid')+"',"
		}
		if(ids != "")
			ids = ids.substring(0,ids.length-1);
		
		
		Ext.Viewport.mask();
		Ext.Viewport.setMasked({
			xtype : 'loadmask',
			message : "加载中..."
		});
		
		Ext.Ajax.request({
			url:prop.baseUrl+'/app!getCtlxdwt.action',
			params:{pageNo:pageNo,ids:ids},
			scope:this,
			async: false,
			method:'post',
			success:function(response){
				var txt = response.responseText;
				var data = Ext.util.JSON.decode(txt);
				
				if(data.success == 'true'){
					var totalCount = data.totalCount;
					var wtArr = data.resultinfo;
					var wtcount = wtArr.length;
					var txflg = 0;
					
						for(var j = 0;j < wtArr.length;j++)
						{
							var tmpflwt = wtArr[j];
							txflg = txflg + 1;
							
								var tmpPanel = Ext.create('Ext.form.Panel',{
									items:[
										{
											xtype:'titlebar',
											title: (parseInt(txflg)+(parseInt(pageNo)-1)*5) + ' / '+ totalCount,
											docked:'bottom',
											items:[
												{
													xtype:'button',
													text: '上一题',
													ui:'confirm-back',
													listeners:{
														tap:function(button,e,eOpts){
																txwjview2.previous();
															}
													}
												},
												{
													xtype:'button',
													text: '下一题',
													align:'right',
													ui:'confirm-forward',
													listeners:{
														tap:function(button,e,eOpts){
																txwjview2.next();
															}
													}
												}
											]
										}
									]
									
								});
								
								
								
								var wttext = Ext.create('Ext.Label',{
															html:tmpflwt.wtmc+'<br>',
															margin:'0 10 0 10'
														});
								tmpPanel.add(wttext);
								var wtField = Ext.create('Ext.form.FieldSet',{
									width:'95%',
									margin:'0 10 0 10'
								});
								tmpPanel.add(wtField);
								
								/*试题解析label*/
								var hdqk = Ext.create('Ext.Label',{
									id:'hdqk_'+tmpflwt.id,
									hidden:true,
									html:"",
									margin:'10 10 0 10'
								});
								tmpPanel.add(hdqk);
								
								/*试题解析label*/
								var stjx = Ext.create('Ext.Label',{
									id:'stjx_'+tmpflwt.id,
									hidden:true,
									html:"<font color='blue'><b>试题解析：</b></font><br>"+tmpflwt.bz+'<br>',
									margin:'10 10 0 10'
								});
								tmpPanel.add(stjx);
								
								/*从错题库中删除按钮*/
								var scctBut_ = Ext.create('Ext.Button',{
									text:'从错题库中移除',
									id:loginName+'_'+tmpflwt.id,
									margin:'10px 50px 10px 50px',
									iconCls:'action',
									//iconAlign:'right',
									listeners:{
										tap:function(button,e,eOpts)
										{
											var tmpstore = Ext.create('Ext.data.Store', {
												model : "app.model.Yhctj"
											});
											tmpstore.load();
											
											var index = tmpstore.find('id', button.getId());
											tmpstore.removeAt(index);
											tmpstore.sync();
											tmpstore.load();
											
											
											var actPanel = txwjview2.getActiveItem();
											actPanel.destroy();
											//txwjview2.next();
											
										}
									}
								});
								tmpPanel.add(scctBut_);
								
								if(tmpflwt.wtlx == '1')
								{
									for(var k = 0;k < tmpflwt.hdxlist.length;k++){
										var tmphdx = tmpflwt.hdxlist[k];
										var sfzqda = tmphdx.sfzqda;
										var radio_hd = Ext.create('Ext.field.Radio',{
											margin:'0 10 0 0',
											width:'95%',
											disabled:false,
											labelWrap:true,
											name:'answer_'+tmpflwt.id,
											id:'hdx_'+tmpflwt.id+'_'+tmphdx.id,
											value:tmphdx.id,
											label:tmphdx.damc,
											labelWidth:'70%'
											/*
											,
											listeners:{
												check:function(button,e,eOpts)
												{
													txwjview2.next();
												}
											}
											*/
										});
										if(sfzqda == "1")
										{
											radio_hd.addListener("check",function(button,e,eOpts){
												//显示回答情况及试题解析
												var tmpname = button.getName();
												var tmpwtid = tmpname.substring(tmpname.indexOf("_")+1);
												Ext.getCmp("hdqk_"+tmpwtid).setHtml("<font color='green'>回答正确(√)</font>");
												Ext.getCmp("hdqk_"+tmpwtid).show();
												Ext.getCmp("stjx_"+tmpwtid).show();
												
												//回答完成后，不能作更改了
												var thiswtAnswer = Ext.query("div[id^=hdx_"+tmpwtid+"]");
												Ext.Array.each(thiswtAnswer,function(el){
													var thisAnswer = Ext.getCmp(el.id);
													thisAnswer.disable();
												});
												
												//txwjview2.next();
											});
										}else{
											radio_hd.addListener("check",function(button,e,eOpts){
												
												//显示回答情况及试题解析
												var tmpname = button.getName();
												var tmpwtid = tmpname.substring(tmpname.indexOf("_")+1);
												Ext.getCmp("hdqk_"+tmpwtid).setHtml("<font color='red'>回答错误(X)</font>");
												Ext.getCmp("hdqk_"+tmpwtid).show();
												Ext.getCmp("stjx_"+tmpwtid).show();
												
												//回答完成后，不能作更改了
												var thiswtAnswer = Ext.query("div[id^=hdx_"+tmpwtid+"]");
												Ext.Array.each(thiswtAnswer,function(el){
													var thisAnswer = Ext.getCmp(el.id);
													thisAnswer.disable();
												});
												
												//将错题放入用户错题集中
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
												//console.log(dataStore2);
												var index = dataStore2.find('id', loginName+"_"+tmpwtid);
												//console.log(index);
												if(index == -1)
												{
													
													var yhctjlocal = Ext.create('app.model.Yhctj',{
														id:loginName+"_"+tmpwtid,
														loginName:loginName,
														wtid:tmpwtid
													});
													yhctjlocal.save();
												}
												//console.log(index);
												//var user = dataStore.getAt(index);
												
												//txwjview2.next();
												
											});
										}
										wtField.add(radio_hd);
									}
								}
								
								
								if(txflg%5 == 0)
								{
									var scflg = Ext.create('Ext.field.Hidden',{
															id:"scflg_"+tmpflwt.id,
															value:'0'
														});
									tmpPanel.add(scflg);
									//当定位到最后一道题时
									tmpPanel.addListener('painted', function(panel){
										
										var tmppanelfields = tmpPanel.getInnerItems();
										var tmpval;
										for(var i = 0;i < tmppanelfields.length;i++)
										{
											var tmpfield = tmppanelfields[i];
											if(tmpfield.getId().indexOf("scflg_") >= 0)
											{
												tmpval = tmpfield;
											}
										}
										
										
										if(typeof(tmpval) != 'undefined' && tmpval.getValue() == '0')
										{
											me.shenchenwt(pageNo+1);
											tmpval.setValue("1");
										}
									});
								}
								
								txwjview2.add(tmpPanel);
								
						}
						
					Ext.Viewport.unmask();
				}else{
					Ext.Msg.alert(data.promptinfo);
					Ext.Viewport.unmask();
				}
				
			}
		});
		
	},
	tothisview:function(){
		var xsmainview = this.getXsmainview();
			ctlxview = this.getCtlxview();
		xsmainview.getLayout().setAnimation({type:'slide'});
		
		
		ctlxview.removeAll();
		
		var pageNo = 1;
		
		this.shenchenwt(pageNo);
		
		xsmainview.setActiveItem(ctlxview);
		ctlxview.setActiveItem(0);
		
		
	}
});