
Ext.define('app.controller.Txwj2',{
	extend:'Ext.app.Controller',
	init:function(){
		//todosomthing
	},
	launch:function(){
		
	},
	/*
	listeners:{
			//幻灯片旋转事件
			 painted: function (sender, opts) {
				 sender.element.on('dragend',
				 function (e) {
					 if (sender.offset == 0) {
						 if (sender.getActiveIndex() == sender.getMaxItemIndex()) { //当幻灯片转到最后一页时加载主页
						  //你想要执行的js
						  console.log("11111111");
						 }
					 }
				 });
			 },
			 activeitemchange:function()
			 {
				console.log("22222222222");
			 }
		},
	*/
	config:{
		refs:{
			xsmainview:'xsmainview',
			txwjview2:'txwjview2',
			xscywjview:'xscywjview',
			backxscywjBt2:'#backxscywjBt2',
			subButton2:'#subButton2',
			dtqk:'#dtqk'
		},
		control:{
			backxscywjBt2:{
				tap:'backxscywjBtFun'
			},
			subButton2:{
				tap:'subButtonFun2'
			}
		},
		routes:{
			'txwj2/:flid':'totxwj2'
		}
		
		
		
	},
	subButtonFun2:function(){
	
		var xsmainview = this.getXsmainview(),
			txwjview2 = this.getTxwjview2(),
			xscywjview = this.getXscywjview();
		
		Ext.Msg.confirm('提示','是否确认提交',function(btn,text){
			if(btn == 'yes')
			{
				var tmpStr = '';
				var tmpStr1 = '';
				var tk2Str = '';
				
				var fields = txwjview2.getInnerItems();
				for(var i = 0;i < fields.length;i++)
				{
					var tmpformpanel = fields[i];
					//console.log(tmpformpanel.getValues());
					var fpValues = tmpformpanel.getValues();
					Object.getOwnPropertyNames(fpValues).forEach(function(val,idx,array){
						var tmpName = val;
						var tmpValues = fpValues[val];
						//console.log(tmpName + '->' +tmpValues);
						if(typeof(tmpValues) != 'undefined' && tmpValues != null)
						{
							if(Ext.isArray(tmpValues))
							{
								Ext.Array.each(tmpValues,function(val,index,itself){
									if(typeof(val) != 'undefined' && val != null)
									{
										if(tmpName.indexOf('answer_') >= 0)
										{
											tmpStr += tmpName+"|"+ val+",";
										}
										if(tmpName.indexOf('tk_') >= 0)
										{
											tmpStr1 += tmpName+"|"+ val+",";
										}
										if(tmpName.indexOf('tk2_') >= 0)
										{
											tk2Str += tmpName+"|"+ val+",";
										}
									}
								});
							}else{
								if(tmpValues != '')
								{
									if(tmpName.indexOf('answer_') >= 0)
									{
										tmpStr += tmpName+"|"+ tmpValues+",";
									}
									if(tmpName.indexOf('tk_') >= 0)
									{
										tmpStr1 += tmpName+"|"+ tmpValues+",";
									}
									if(tmpName.indexOf('tk2_') >= 0)
									{
										tk2Str += tmpName+"|"+ tmpValues+",";
									}
								}
							}
						}
						
					});
				}
				if(tmpStr != "")
					tmpStr = tmpStr.substring(0,tmpStr.length-1);
				if(tmpStr1 != "")
					tmpStr1 = tmpStr1.substring(0,tmpStr1.length-1);
				if(tk2Str != "")
					tk2Str = tk2Str.substring(0,tk2Str.length-1);	
					
				
				Ext.Ajax.request({
					url: prop.baseUrl+'/app!dosubwj.action',
					scope:this,
					params:{resultSelectStr:tmpStr,resultBlankStr:tmpStr1,tk2:tk2Str,loginName:prop.userLoginName,wjid:prop.selectWjid,pcid:prop.selectPcid},
					withCredentials: true,
					useDefaultXhrHeader: false,
					success: function(response)
					{
						if(response.responseText == '1')
						{
							Ext.Msg.alert('提交成功');
							this.redirectTo('xscywj');
							//xsmainview.setActiveItem(xscywjview);
						}else{
							Ext.Msg.alert('发生错误,无法提交');
						}
					},
					failure: function(response) {
						Ext.Msg.alert('发生错误,无法提交');
					}
				});		
				
			}else{
				
			}
		},this);
		
		
	
	},
	backxscywjBtFun:function(){
		var txwjview2 = this.getTxwjview2();
		txwjview2.removeAll();
		this.redirectTo("xscywj");
		/*
		var xsmainview = this.getXsmainview(),
			xscywjview = this.getXscywjview();
		xsmainview.getLayout().setAnimation({type:'slide',direction:'right'});
		xsmainview.setActiveItem(xscywjview);
		*/
	},
	shenchenwt:function(flid,pageNo){
		
		/*
		var xsmainview = this.getXsmainview(),
			txwjview2 = this.getTxwjview2();
		xsmainview.getLayout().setAnimation({type:'slide'});
		xsmainview.setActiveItem(txwjview2);
		*/
		
		var txwjview2 = this.getTxwjview2();
		
			
		var me = this;
		
		//test
		/*
		Ext.Ajax.request({
			url:prop.baseUrl+'/app!getNexwt.action',
			params:{flid:flid,pageNo:pageNo},
			scope:this,
			method:'post',
			async: false,
			//disableCaching:false,
			success:function(response){
				var txt = response.responseText;
				var obj = Ext.util.JSON.decode(txt);
				var wtArr = obj.resultinfo;
				//console.log(wtArr.resultinfo);
				
				for(var j = 0;j < wtArr.length;j++)
				{
					var tmpPanel = Ext.create('Ext.form.Panel',{
						html:'panel'+j
					});
					txwjview2.add(tmpPanel);
				}
				
			}
		});
		*/
		
		//test
		
		
		Ext.Viewport.mask();
		Ext.Viewport.setMasked({
			xtype : 'loadmask',
			message : "加载中..."
		});
		
		Ext.Ajax.request({
			url:prop.baseUrl+'/app!getNexwt.action',
			params:{flid:flid,pageNo:pageNo},
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
								
								//试题解析label
								var hdqk = Ext.create('Ext.Label',{
									id:'hdqk_'+tmpflwt.id,
									hidden:true,
									html:"",
									margin:'10 10 0 10'
								});
								tmpPanel.add(hdqk);
								
								//试题解析label
								var stjx = Ext.create('Ext.Label',{
									id:'stjx_'+tmpflwt.id,
									hidden:true,
									html:"<font color='blue'><b>试题解析：</b></font><br>"+tmpflwt.bz+'<br>',
									margin:'10 10 0 10'
								});
								tmpPanel.add(stjx);
								
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
												
												//正确题数增加
												prop.zqsl = prop.zqsl + 1;
												var dtqk = me.getDtqk();
												dtqk.setText(prop.zqsl + "/" + prop.cwsl);
												
												txwjview2.next();
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
												
												//错误题数增加
												prop.cwsl = prop.cwsl + 1;
												var dtqk = me.getDtqk();
												dtqk.setText(prop.zqsl + "/" + prop.cwsl);
												
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
											me.shenchenwt(flid,pageNo+1);
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
	totxwj2:function(flid){
		
		var xsmainview = this.getXsmainview(),
			txwjview2 = this.getTxwjview2();
		xsmainview.getLayout().setAnimation({type:'slide'});
		
		var dtqk = this.getDtqk();
		dtqk.setText('0/0');
		
		//txwjview2.removeAll();
		
		var pageNo = 1;
		
		prop.zqsl = 0;
		prop.cwsl = 0;
		
		this.shenchenwt(flid,pageNo);
		
		xsmainview.setActiveItem(txwjview2);
		txwjview2.setActiveItem(0);
		//txwjview2.next();
		
		
	}
});