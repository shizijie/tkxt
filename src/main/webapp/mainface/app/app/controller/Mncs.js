
Ext.define('app.controller.Mncs',{
	extend:'Ext.app.Controller',
	init:function(){
		//todosomthing
	},
	launch:function(){
		
	},
	listeners:{
		painted:function(){
			console.log("----www");
		}
	},
	config:{
		refs:{
			xsmainview:'xsmainview',
			mncsview:'mncsview',
			mncshzview:'mncshzview',
			xscywjview:'xscywjview',
			backxscywjBt3:'#backxscywjBt3',
			ttbtop3:'#ttbtop3',
			dtqk2:'#dtqk2'
		},
		control:{
			backxscywjBt3:{
				tap:'backxscywjBtFun'
			},
			dtqk2:{
				tap:'tomncshz'
			}
		},
		routes:{
			'mncs':'tomncs'
		}
	},
	tomncshz:function(){
		this.redirectTo("mncshz");
	},
	backxscywjBtFun:function(){
		var mncsview = this.getMncsview();
		mncsview.removeAll();
		this.redirectTo("xscywj");
		
		//跳到测试汇总界面
		//this.redirectTo("mncshz");
		
	},
	shenchenwt:function(pageNo){
		
		/*
		var xsmainview = this.getXsmainview(),
			txwjview2 = this.getTxwjview2();
		xsmainview.getLayout().setAnimation({type:'slide'});
		xsmainview.setActiveItem(txwjview2);
		*/
		var xsmainview = this.getXsmainview();
		var txwjview2 = this.getMncsview();
		var mncshzview = this.getMncshzview();
			
		var me = this;
		
		//第一次进入，后台将第一随机生成的id号记录到session中，不然题目会有重复
		var cxscflg = "0";
		if(pageNo > 1)
		{
			cxscflg = "1";
		}
		
		Ext.Viewport.mask();
		Ext.Viewport.setMasked({
			xtype : 'loadmask',
			message : "用户登录中..."
		});
		
		Ext.Ajax.request({
			url:prop.baseUrl+'/app!getRandwt.action',
			params:{tms:prop.sjsctms,cxscflg:cxscflg,pageNo:pageNo},
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
					
					//第一次进入，根据题目总数，生成mncshz（模拟测试汇总界面）的九宫格
					if(pageNo == 1)
					{	
						mncshzview.removeAll();
						var tp2 = Ext.create('Ext.Panel',
							{
								xtype:'panel',
								id:'mncshdqk',
								height:'60px',
								padding:'10px',
								html:''
							}
						);
						mncshzview.add(tp2);
						var fpnl;
						var t = 0;
						for(var k = 0 ;k < totalCount; k++)
						{
							if(k%8 == 0)
							{
								fpnl = Ext.create('Ext.Panel',{
									layout: {
										type: 'hbox',
										align: 'center'
									},
									defaults: {
										flex:1
									}
								});
							}
							t = t + 1;
							var tmppnl = Ext.create('Ext.Panel',{
								items:[
									{
										xtype: 'button',
										text: t,
										id:'tb_'+k,
										labelCls:'mncshz-but-label',
										style:'background-color:#ccc;background-image:none;border-color:#fff;border:1px solid #fff;border-radius:0em',
										listeners:{
											tap:function(button,e,eOpts)
											{
												xsmainview.getLayout().setAnimation({type:'slide',direction:'right'});
												xsmainview.setActiveItem(txwjview2);
												txwjview2.setActiveItem(parseInt(button.getText())-1);
											}
										}

									}
								]
							});
							if(typeof(fpnl) != "undefined")
								fpnl.add(tmppnl);
							if(t%8 == 0 || t == totalCount)
								mncshzview.add(fpnl);
							
						}
						
					}
					
						for(var j = 0;j < wtArr.length;j++)
						{
							var tmpflwt = wtArr[j];
							txflg = txflg + 1;
							//用于更新mncshz中的button的background-color;
							//var tbk = parseInt(txflg)+(parseInt(pageNo)-1)*5;
							
								var tmpPanel = Ext.create('Ext.form.Panel',{
									items:[
										{
											xtype:'titlebar',
											id:'pnttbar_'+tmpflwt.id,
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
												var dtqk = me.getDtqk2();
												dtqk.setText(prop.zqsl + "/" + prop.cwsl);
												
												//更新mncshz中的方格的background-color
												var thistttbar =  Ext.getCmp("pnttbar_"+tmpwtid).getTitle();
												var wtIndex = thistttbar.substring(0,thistttbar.indexOf("/")).trim();
												Ext.getCmp("tb_"+(parseInt(wtIndex)-1)).setStyle("background-color:white");
												
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
												var index = dataStore2.find('id', loginName+"_"+tmpwtid);
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
												var dtqk = me.getDtqk2();
												dtqk.setText(prop.zqsl + "/" + prop.cwsl);
												
												
												//更新mncshz中的方格的background-color
												var thistttbar =  Ext.getCmp("pnttbar_"+tmpwtid).getTitle();
												var wtIndex = thistttbar.substring(0,thistttbar.indexOf("/")).trim();
												Ext.getCmp("tb_"+(parseInt(wtIndex)-1)).setStyle("background-color:red");
												
												txwjview2.next();
												
											});
										}
										wtField.add(radio_hd);
									}
								}
								
								/*暂时注释分页加载功能*/
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
					Ext.Viewport.unmask();
				}
				
			}
		});
		
	},
	tomncs:function(){
		
		var xsmainview = this.getXsmainview();
			mncsview = this.getMncsview();
		xsmainview.getLayout().setAnimation({type:'slide'});
		
		mncsview.removeAll();
		
		var pageNo = 1;
		
		prop.zqsl = 0;
		prop.cwsl = 0;
		hdxArr = new Array();
		
		this.shenchenwt(pageNo);
		
		xsmainview.setActiveItem(mncsview);
		mncsview.setActiveItem(0);
		
	}
});