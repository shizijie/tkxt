//学生完善信息

Ext.define('app.view.XsWsxx',{
	extend:'Ext.form.Panel',
	xtype:'xswsxxview',
	alias:'widget.xswsxxview',
	//requires:''
	config:{
		id:'xswsxxview',
		fullscreen:true,
		items:[		
			{
				xtype:'fieldset',
				title: '第一步：确认您的个人信息',
				instructions:'完善您的个人信息，也能获得10个积分哦',				
				items: [
					{
						xtype: 'textfield',
						label: '学号',
						required:true,
						name:'xh',
						id:'xh'
					},
					{
						xtype: 'textfield',
						label: '姓名',
						required:true,
						name:'xm',
						id:'xm'
					},
					{
						xtype: 'hiddenfield',
						name:'txsjbxxid',
						id:'txsjbxxid'
					},
					{
						xtype: 'textfield',
						label: '就业单位',
						required:true,
						name:'qydwmc',
						id:'qydwmc'
					},
					{
						xtype: 'textfield',
						label: '就业岗位',
						required:true,
						name:'jygw',
						id:'jygw'
					},
					{
						xtype: 'textfield',
						label: '联系电话',
						required:true,
						name:'lxdh',
						id:'lxdh'
					},
					{
						xtype: 'textfield',
						label: '邮箱地址',
						required:true,
						name:'yxdz',
						id:'yxdz'
					}
				]				
			},
				
			{
				xtype: 'button',
				text: '下一步',
				id:'nexButton',
				ui: 'confirm'
			}		

			
			
		]
	},
	initialize:function(){
		this.callParent();
		/*
		this.down('#xm').setValue(prop.userName);
		
		Ext.data.JsonP.request({
			url:prop.baseUrl+'/app!getxswsxx.action',
			params:{loginName:prop.userLoginName},
			scope:this,
			success:function(data){
				if(data.success == 'true'){
					this.down('#txsjbxxid').setValue(data.resultinfo.txsjbxx.id);
					this.down('#xh').setValue(data.resultinfo.txsjbxx.xh);
					this.down('#xh').disable();
					this.down('#xm').setValue(data.resultinfo.txsjbxx.xm);
					this.down('#xm').disable();
					this.down('#qydwmc').setValue(data.resultinfo.qydwmc);
					this.down('#jygw').setValue(data.resultinfo.jygw);
					this.down('#lxdh').setValue(data.resultinfo.lxdh);
					this.down('#yxdz').setValue(data.resultinfo.yxdz);
				}else{
					Ext.Msg.alert(data.promptinfo);
				}
				
			}
		});	
		*/
	}
});
