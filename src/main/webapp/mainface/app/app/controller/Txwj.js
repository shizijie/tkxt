
Ext.define('app.controller.Txwj',{
	extend:'Ext.app.Controller',
	init:function(){
		//todosomthing
	},
	launch:function(){
		
	},
	config:{
		refs:{
			xsmainview:'xsmainview',
			txwjview:'txwjview',
			backxscywjBt:'#backxscywjBt',
			fldset:'#fldset',
			resultSelectStr:'#resultSelectStr',
			resultBlankStr:'#resultBlankStr',
			tk2:'#tk2',
			pcid:'#pcid',
			wjid:'#wjid',
			subButton:'#subButton'
		},
		control:{
			backxscywjBt:{
				tap:'backxscywjBtFun'
			},
			subButton:{
				tap:'subButtonFun'
			}
		},
		routes:{
			'txwj':'totxwj'
		}
	},
	subButtonFun:function(){
	
		var xsmainview = this.getXsmainview(),
			txwjview = this.getTxwjview();
		//console.log(JSON.stringify(txwjview.getValues()));
		//var tmparr = txwjview.query("[name='resultSelectStr']");
		//console.log(tmparr);
		//console.log(tmparr);
		//console.log(txwjview.getValues());
		
		var fields = txwjview.getFields();
		var express = /^[0-9]*$/;
		var flag = true;
		var tmpStr = "";
		var tmpStr1 = "";
		var tk2Str   = "";//所有主观题填空
		
		for(var name in fields)
		{
			if(name.indexOf('tk_') >= 0)
			{
				var tkfield = fields[name];
				if(!express.test(tkfield.getValue()))
				{
					Ext.Msg.alert("有非法数字,请检查");
					flag = false;
				}
			}
		}
		if(flag)
		{
			for(var name in fields)
			{
				if(name.indexOf('answer_') >= 0)
				{
					var radioOrchkboxfield = fields[name];
					if(Ext.isArray(radioOrchkboxfield))
					{
						for(var i = 0;i < radioOrchkboxfield.length;i++)
						{
							var tmpfield = radioOrchkboxfield[i];
							if(tmpfield.isChecked())
							{
								tmpStr += name+"|"+ tmpfield.getValue()+",";
							}
						}
					}
					
				}
				
				if(name.indexOf('tk_') >= 0)
				{
					var tkfield = fields[name];
					var tkfieldVal = tkfield.getValue();
					if(typeof(tkfieldVal) != "undefined" && tkfieldVal != "")
					{
						tmpStr1 += name+"|"+ tkfieldVal+",";
					}
				}
				
				if(name.indexOf('tk2_') >= 0)
				{
					var tk2field = fields[name];
					var tk2fieldVal = tk2field.getValue();
					if(typeof(tk2fieldVal) != "undefined" && tk2fieldVal != "")
					{
						tk2Str += name+"|"+ tk2fieldVal+",";
					}
				}
			}
		}
		
		if(tmpStr != "")
			tmpStr = tmpStr.substring(0,tmpStr.length-1);
		if(tmpStr1 != "")
			tmpStr1 = tmpStr1.substring(0,tmpStr1.length-1);
		if(tk2Str != "")
			tk2Str = tk2Str.substring(0,tk2Str.length-1);	
		
		Ext.Ajax.request({
			url: prop.baseUrl+'/app!dosubwj.action',
			params:{resultSelectStr:tmpStr,resultBlankStr:tmpStr1,tk2:tk2Str},
			withCredentials: true,
			useDefaultXhrHeader: false,
			success: function(response)
			{
				console.log(response.responseText+'-----------1');
			},
			failure: function(response) {
				console.log('-----------2');
			},
			callback: function(response, successful) {
				console.log('-----------3'+successful);
			}
		});
	
	},
	backxscywjBtFun:function(){
		this.redirectTo("xscywj");
	},
	totxwj:function(){
		var xsmainview = this.getXsmainview(),
			txwjview = this.getTxwjview();
		xsmainview.getLayout().setAnimation({type:'slide'});
		xsmainview.setActiveItem(txwjview);
		
		txwjview.removeAll();
		
		var resultSelectStr = Ext.create('Ext.field.Hidden',{
			name:'resultSelectStr',
			id:'resultSelectStr',
			value:''
		});
		txwjview.add(resultSelectStr);
		
		var resultBlankStr = Ext.create('Ext.field.Hidden',{
			name:'resultBlankStr',
			id:'resultBlankStr',
			value:''
		});
		txwjview.add(resultBlankStr);

		var tk2 = Ext.create('Ext.field.Hidden',{
			name:'tk2',
			id:'tk2',
			value:''
		});
		txwjview.add(tk2);

		var pcid = Ext.create('Ext.field.Hidden',{
			name:'pcid',
			id:'pcid',
			value:''
		});
		txwjview.add(pcid);

		var wjid = Ext.create('Ext.field.Hidden',{
			name:'wjid',
			id:'wjid',
			value:''
		});
		txwjview.add(wjid);		
		
		
		Ext.Viewport.mask();
		Ext.Viewport.setMasked({
			xtype : 'loadmask',
			message : "加载中..."
		});
		
		Ext.data.JsonP.request({
			url:prop.baseUrl+'/app!getwj.action',
			params:{wjid:prop.selectWjid,pcid:prop.selectPcid},
			scope:this,
			success:function(data){
				if(data.success == 'true'){
					this.getPcid().setValue(data.pcid);
					this.getWjid().setValue(data.wjid);
					var twjfaflArr = data.resultinfo.twjfafl;
					for(var i = 0;i < twjfaflArr.length;i++)
					{
						var tmptwjfafl = twjfaflArr[i];
						var flField;
						var wtField;
						if(tmptwjfafl.flmc != ""){
							/*
							flField = Ext.create('Ext.form.FieldSet',{
								title:tmptwjfafl.flmc
							});
							*/
							flField = Ext.create('Ext.Label',{
													html:tmptwjfafl.flmc
												});
							txwjview.add(flField);
							
						}
						
						for(var j = 0;j < tmptwjfafl.flwt.length;j++)
						{
							var tmpflwt = tmptwjfafl.flwt[j];
							
							
							wtField = Ext.create('Ext.form.FieldSet',{
								//title:tmpflwt.wtmc
							});
							
							
							wttext = Ext.create('Ext.Label',{html:tmpflwt.wtmc});
							txwjview.add(wttext);
							
							if(tmpflwt.wtlx == '3'){
								if(tmpflwt.jfgzdm == '4')
								{
									var tk_txt = Ext.create('Ext.field.Text',{
										//id:'tk_'+tmpflwt.id,
										name:'tk_'+tmpflwt.id,
										value:0,
										maxLength:2
									});
									wtField.add(tk_txt);
									txwjview.add(wtField);
								}
								if(tmpflwt.jfgzdm == '5'){
									var tk2_txt = Ext.create('Ext.field.Text',{
										//id:'tk2_'+tmpflwt.id,
										name:'tk2_'+tmpflwt.id,
										maxLength:490
									});
									wtField.add(tk2_txt);
									txwjview.add(wtField);
								}
							}
							for(var k = 0;k < tmpflwt.hdxlist.length;k++)
							{
								var tmphdx = tmpflwt.hdxlist[k];
								if(tmpflwt.wtlx == '1')
								{
									var radio_hd = Ext.create('Ext.field.Radio',{
										//id:'answer_'+tmphdx.id,
										name:'answer_'+tmpflwt.id,
										label:tmphdx.damc,
										labelWidth:'70%',
										value:tmphdx.id
									});
									wtField.add(radio_hd);
									txwjview.add(wtField);
								}
								if(tmpflwt.wtlx == '2')
								{
									var checkbox_hd = Ext.create('Ext.field.Checkbox',{
										//id:'answer_'+tmphdx.id,
										name:'answer_'+tmpflwt.id,
										label:tmphdx.damc,
										labelWidth:'70%',
										value:tmphdx.id
									});
									wtField.add(checkbox_hd);	
									txwjview.add(wtField);
									
								}
							}
							
							/*
							if(typeof(wtField) != "undefined")
							{
								if(typeof(flField) != "undefined")
								{
									flField.add(wtField);
									txwjview.add(flField);
								}else{
									txwjview.add(wtField);
								}
								
							}
							*/
						}


					
					}
					
					Ext.Viewport.unmask();
				}else{
					Ext.Msg.alert(data.promptinfo);
					Ext.Viewport.unmask();
				}
				
			}
		});
		/*
		var pcfaStore = Ext.create('Ext.data.Store',{
			autoLoad:true,
			fields: ['faid', 'famc', 'kssj', 'jssj', 'pcid'],
			proxy: {
				type: 'jsonp',
				url: prop.baseUrl+'/app!getpcfa.action?loginName='+prop.userLoginName,
				reader: {
					type: 'json',
					rootProperty: 'resultinfo'
				}
			}
		});
		var pafaList=Ext.create('Ext.List',{
			height:'100%',
            store:pcfaStore,
            itemTpl:'<div class="list-item-title">{famc}</div><div class="list-item-narrative">{kssj} 至 {jssj}</div>',
            emptyText:'<div class="notes-list-empty-text">没有需要您参与的问卷</div>',
			onItemDisclosure:function(record,element,index,e)
			{
				prop.selectWjid = pcfaStore.getAt(index).get('faid');
				prop.selectPcid = pcfaStore.getAt(index).get('pcid');
				console.log("==="+prop.selectWjid);
				console.log("==="+prop.selectPcid);
			}
        });
		*/
		
	},
	show:function(){
		console.log('show');
	}
});