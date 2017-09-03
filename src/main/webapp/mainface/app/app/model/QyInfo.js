
Ext.define('app.model.QyInfo', {
	extend : 'Ext.data.Model',
	config : {
		fields : ['qyyhzh','zzjgdm','qymc','qylxr','gdlxdh','sjlxdh','qydz'],
		validations:[
			{type:'presence',field:'qyyhzh',message:'请输入企业帐号'},
			{type:'presence',field:'zzjgdm',message:'请输入组织机构代码'},
			{type:'presence',field:'qymc',message:'请输入企业名称'},
			{type:'presence',field:'qylxr',message:'请输入企业联系人'},
			{type:'presence',field:'gdlxdh',message:'请输入固定电话'},
			{type:'presence',field:'sjlxdh',message:'请输入手机号'},
			{type:'presence',field:'qydz',message:'请输入地址'}
		]
	}
});