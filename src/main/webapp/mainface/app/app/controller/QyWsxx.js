
Ext.define('app.controller.QyWsxx',{
	extend:'Ext.app.Controller',
	init:function(){
		
	},
	launch:function(){
		
	},
	config:{
		refs:{
			
			xsmainview:'xsmainview',
			qywsxxview:'qywsxxview',
			nexButtonQy:'#nexButtonQy',
			qyid:'#qyid',
			qyyhzh:'#qyyhzh',
			zzjgdm:'#zzjgdm',
			qymc:'#qymc',
			qylxr:'#qylxr',
			gdlxdh:'#gdlxdh',
			sjlxdh:'#sjlxdh',
			qydz:'#qydz'
			
		},
		control:{
			nexButtonQy:{
				tap:'doNext'
			}
		},
		routes:{
			'qywsxx':'toqywsxx'
		}
	},
	toqywsxx:function(){
		var xsmainview = this.getXsmainview(),
			qywsxxview = this.getQywsxxview();
		xsmainview.getLayout().setAnimation({type:'slide'});
		xsmainview.setActiveItem(qywsxxview);
		
		Ext.Viewport.mask();
		//初始化用户的基本信息
		Ext.data.JsonP.request({
			url:prop.baseUrl+'/app!getqywsxx.action',
			params:{loginName:prop.userLoginName},
			scope:this,
			success:function(data){
				if(data.success == 'true'){
					var qyinfdata = data.resultinfo;
					this.getQyyhzh().setValue(prop.userLoginName);
					this.getQyyhzh().disable();
					this.getQymc().setValue(qyinfdata.qymc);
					this.getQymc().disable();
					this.getZzjgdm().setValue(qyinfdata.zzjgdm);
					this.getQydz().setValue(qyinfdata.qydz);
					this.getQylxr().setValue(qyinfdata.qylxr);
					this.getGdlxdh().setValue(qyinfdata.gdlxdh);
					this.getSjlxdh().setValue(qyinfdata.sjlxdh);
					
				}else{
					Ext.Msg.alert(data.promptinfo);
				}
				
			}
		});	
		Ext.Viewport.unmask();
		
	},
	doNext:function(){
		var qyyhzhVal = this.getQyyhzh().getValue();
		var zzjgdmVal = this.getZzjgdm().getValue();
		var qymcVal = this.getQymc().getValue();
		var qylxrVal = this.getQylxr().getValue();
		var gdlxdhVal = this.getGdlxdh().getValue();
		var sjlxdhVal = this.getSjlxdh().getValue();
		var qydzVal = this.getQydz().getValue();
		var qyinfo = Ext.create('app.model.QyInfo',{
			qyyhzh:qyyhzhVal,
			zzjgdm:zzjgdmVal,
			qymc:qymcVal,
			qylxr:qylxrVal,
			gdlxdh:gdlxdhVal,
			sjlxdh:sjlxdhVal,
			qydz:qydzVal
		});
		var errors = qyinfo.validate();
		if(errors.isValid())
		{
			//提交更新数据库中毕业生的信息
			
			var params = {
				qyyhzh:qyyhzhVal,
				zzjgdm:zzjgdmVal,
				qymc:qymcVal,
				qylxr:qylxrVal,
				gdlxdh:gdlxdhVal,
				sjlxdh:sjlxdhVal,
				qydz:qydzVal
			};
			
			Ext.data.JsonP.request({
				url:prop.baseUrl+'/app!doqywsxx.action',
				params:params,
				scope:this,
				success:function(data){
					if(data.success == 'true'){
						this.redirectTo('xscywj');						
					}else{
						Ext.Msg.alert(data.promptinfo);
					}
				}
			});
			
			//this.redirectTo('xscywj');
		}else{
			var message = "";
            Ext.each(errors.items,function(rec){
                message += rec.getMessage()+"<br>";
            });
            Ext.Msg.alert("验证失败", message);  
		}
	}
});