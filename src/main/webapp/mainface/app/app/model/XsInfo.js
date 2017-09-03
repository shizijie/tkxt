
//学生用户的基本信息
/*
 *xh：学号
 *xm：姓名
 *jydw：就业单位
 *jygw：就业岗位
 *lxdh：联系电话
 *email：E-Mail
*/

Ext.define('app.model.XsInfo', {
	extend : 'Ext.data.Model',
	config : {
		fields : ['id','xh','xm','qydwmc','jygw','lxdh','yxdz','txsjbxxid'],
		validations:[
			{type:'presence',field:'xh',message:'请输入学号'},
			{type:'presence',field:'xm',message:'请输入姓名'},
			{type:'presence',field:'qydwmc',message:'请输入就业单位'},
			{type:'presence',field:'jygw',message:'请输入就业岗位'},
			{type:'presence',field:'lxdh',message:'请输入联系电话'},
			{type:'presence',field:'yxdz',message:'请输入邮箱'}
		]
	}
});