
Ext.define('app.controller.XsWsxx',{
	extend:'Ext.app.Controller',
	init:function(){
		//todosomthing
	},
	launch:function(){
		
	},
	config:{
		refs:{
			
			xsmainview:'xsmainview',
			xswsxxview:'xswsxxview',
			/*
			xswsxxview:{
				selector:'xswsxxview',
				xtype:'xswsxxview',
				autoCreate:true
			},
			*/
			txsjbxxid:'#txsjbxxid',
			xh:'#xh',
			xm:'#xm',
			qydwmc:'#qydwmc',
			jygw:'#jygw',
			lxdh:'#lxdh',
			yxdz:'#yxdz',
			nexButton:'#nexButton'
		},
		control:{
			nexButton:{
				tap:'doNext'
			}
		},
		routes:{
			'xswsxx':'toxswsxx'
		}
	},
	toxswsxx:function(){
		var xsmainview = this.getXsmainview(),
			xswsxxview = this.getXswsxxview();
		xsmainview.getLayout().setAnimation({type:'slide'});
		xsmainview.setActiveItem(xswsxxview);
		
		Ext.Viewport.mask();
		//初始化用户的基本信息
		Ext.data.JsonP.request({
			url:prop.baseUrl+'/app!getxswsxx.action',
			params:{loginName:prop.userLoginName},
			scope:this,
			success:function(data){
				if(data.success == 'true'){
					this.getTxsjbxxid().setValue(data.resultinfo.txsjbxx.id);
					this.getXh().setValue(data.resultinfo.txsjbxx.xh);
					this.getXh().disable();
					this.getXm().setValue(data.resultinfo.txsjbxx.xm);
					this.getXm().disable();
					this.getQydwmc().setValue(data.resultinfo.qydwmc);
					this.getJygw().setValue(data.resultinfo.jygw);
					this.getLxdh().setValue(data.resultinfo.lxdh);
					this.getYxdz().setValue(data.resultinfo.yxdz);
					
				}else{
					Ext.Msg.alert(data.promptinfo);
				}
				
			}
		});	
		Ext.Viewport.unmask();
		
	},
	doNext:function(){
		var txsjbxxidVal = this.getTxsjbxxid().getValue();
		var xhVal = this.getXh().getValue();
		var xmVal = this.getXm().getValue();
		var qydwmcVal = this.getQydwmc().getValue();
		var jygwVal = this.getJygw().getValue();
		var lxdhVal = this.getLxdh().getValue();
		var yxdzVal = this.getYxdz().getValue();
		var xsinfo = Ext.create('app.model.XsInfo',{
			id:1,
			txsjbxxid:txsjbxxidVal,
			xh:xhVal,
			xm:xmVal,
			qydwmc:qydwmcVal,
			jygw:jygwVal,
			lxdh:lxdhVal,
			yxdz:yxdzVal
		});
		var errors = xsinfo.validate();
		if(errors.isValid())
		{
			//提交更新数据库中毕业生的信息
			
			var params = {
				txsjbxxid:txsjbxxidVal,
				xh:xhVal,
				xm:xmVal,
				qydwmc:qydwmcVal,
				jygw:jygwVal,
				lxdh:lxdhVal,
				yxdz:yxdzVal
			};
			Ext.data.JsonP.request({
				url:prop.baseUrl+'/app!doxswsxx.action',
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
		}else{
			var message = "";
            Ext.each(errors.items,function(rec){
                message += rec.getMessage()+"<br>";
            });
            Ext.Msg.alert("验证失败", message);  
		}
	}
});