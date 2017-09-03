
Ext.define('app.controller.Mncshz',{
	extend:'Ext.app.Controller',
	init:function(){
		//todosomthing
		
	},
	
	config:{
		refs:{
			xsmainview:'xsmainview',
			mncshzview:'mncshzview',
			mncsview:'mncsview',
			back2mncsbut:'#back2mncsbut',
			mncshdqk:'#mncshdqk'
		},
		control:{
			back2mncsbut:{
				tap:'back2mncsButFun'
			}
		},
		routes:{
			'mncshz':'tomncshzview'
		}
	},
	back2mncsButFun:function(){
		var xsmainview = this.getXsmainview(),
			mncsview = this.getMncsview();
		xsmainview.getLayout().setAnimation({type:'slide',direction:'right'});
		xsmainview.setActiveItem(mncsview);
	},
	tomncshzview:function(){
		var xsmainview = this.getXsmainview(),
			mncshzview = this.getMncshzview();
			xsmainview.getLayout().setAnimation({type:'slide',direction:'left'});
		xsmainview.setActiveItem(mncshzview);
		
		var mncshdqk = this.getMncshdqk();
		mncshdqk.setHtml("<font color='blue'>正确题目数：</font>"+prop.zqsl+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color='green'>错误题目数：</font>"+prop.cwsl+"<br><font size='1px'>红色表示答错题目,白色表示正确题目,灰色表示未答题目</font>");
	}
});


	