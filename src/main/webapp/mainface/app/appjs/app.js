 (function () {
      'use strict';
      
      angular.module('ionicApp', ['ionic'])

      /*
       * 常量配置
       */
      .constant('systemArgs',{'loginURL':'000','appURL':'http://192.168.31.247:8080/tkxt'})


      .config(['$stateProvider', '$urlRouterProvider', '$ionicConfigProvider', '$httpProvider', 
               function($stateProvider, $urlRouterProvider, $ionicConfigProvider, $httpProvider){

        //在 iOS中, tabs一直处于底部.
        //在android中, tabs一直在顶部
        //ionic中默认安装后导航在底部解决方案
    	  $ionicConfigProvider.platform.ios.tabs.style('standard');
          $ionicConfigProvider.platform.ios.tabs.position('bottom');            
          $ionicConfigProvider.platform.android.tabs.style('standard');
          $ionicConfigProvider.platform.android.tabs.position('bottom');

          $ionicConfigProvider.platform.ios.navBar.alignTitle('center');
          $ionicConfigProvider.platform.android.navBar.alignTitle('center');

          $ionicConfigProvider.platform.ios.backButton.previousTitleText('').icon('ion-ios-arrow-thin-left');
          $ionicConfigProvider.platform.android.backButton.previousTitleText('').icon('ion-android-arrow-back');        

          $ionicConfigProvider.platform.ios.views.transition('ios');
          $ionicConfigProvider.platform.android.views.transition('android');
    	  
    	  
      }])
      
      /*.animation('.fad', function () {
              return {
                enter: function(element, done) {
                      element.css({
                        opacity: 0
                      });
                      element.animate({
                        opacity: 1
                      }, 1000, done);
                    },
                leave: function (element, done) {
                    element.css({
                        opacity: 1
                    });
                    element.animate({
                        opacity: 0
                    }, 1000, done);
                }
              };
      })*/

      .controller('appCtrl', function($rootScope,$scope,$http,$timeout,systemArgs,$window,$state) {
    	  
    	  //用户监听
    	  $rootScope.$on('$stateChangeStart',function(event, toState, toParams, fromState, fromParams){
	  			if(toState.name=='login')return;// 如果是进入登录界面则允许
	  			// 如果用户不存在
	  			if(typeof(sessionStorage.loginName)=="undefined"){
	  				event.preventDefault();// 取消默认跳转行为
	  				$state.go("login");//跳转到登录界面
	  			}
  	      });
    	  //console.log(sessionStorage.loginName);
        

    	  //根据URL进行页面跳转
    	  $rootScope.gotoURL = function(targetURL){ 
          /**
           * 使用全局事件监听机制实现身份认证机制。
           * 参考main/js/app.js文件。
           */
          $window.location.href = targetURL;
          
          /**
           * 自定义身份认证机制。
           * 如果采用该方式，只能通过URL方式进行页面跳转。
           * 同时需要屏蔽掉main/js/app.js文件中相关监听功能。
           */
          /*
          if($rootScope.isLogin){        
            $window.location.href = targetURL;
          }else{
            $rootScope.nextURL = targetURL;
            $rootScope.openLoginModal(targetURL);       
          }
          */
        };

        
      })
       .controller('loginCtrl', function($rootScope,$scope,$http,$timeout,$state,$window,$ionicPopup,systemArgs) {
		
        $scope.doLogin =function(){
			$http
			(
				{
					method:"POST",
					url:systemArgs.appURL+'/app!dologin.action',
					params:
					{
						'loginName':$scope.loginName,
						'password':$scope.pwd
					}
				}
			).success(function(data, status, headers, config){
				if(data.success == "true")
				{
					sessionStorage.loginName=$scope.loginName;
					sessionStorage.loginXm=data.resultinfo.name;
					//$rootScope.loginName = $scope.loginName;//保存登录的用户名为全局变量
					//$rootScope.loginXm = data.resultinfo.name;//保存用户姓名
					//$rootScope.userPhoto = data.userPhoto;//保存用户照片
					$rootScope.gotoURL("#/tab/zjtest");
				}else{
					$ionicPopup.alert(
						{
						   title: '<div align="left"><i class="icon ion-android-alert  assertive "><strong>&nbsp;提示：</strong></i></div>',
						   template: '<div><strong>用户名或密码错误！</strong></div>',          
						   okText: '确定',
						   okType: 'button-calm'
						  }
					);
				}
			});
        };

      })
       .controller('zjtestCtrl', function($rootScope,$scope,$http,$timeout,$state,$window,$ionicPopup,systemArgs) {
    	   $http({
    		   method:"post",
    		   url:systemArgs.appURL+'/app!getallfl.action'
    	   })
    	   .success(function(data, status, headers, config){
    		   $scope.kclist=data.resultinfo;
    		   $scope.x="0";
    	   });
    	   $scope.change=function(x){
    		   if(x!=0){
    			   $scope.x=x;
    			   $http({
    				   method:"post",
    				   url:systemArgs.appURL+'/app!getallfl.action',
    				   params:{'kcid':x}
    			   })
    			   .success(function(data, status, headers, config){
    				   $scope.kc=data.resultinfo.children;
    			   });
    		   }
    	   }
    	   $scope.zjinfo=function(zjid,zjmc){
    		   $rootScope.gotoURL(encodeURI("#/zjlx/"+zjid+"/"+zjmc));
    	   };
    	   
    	   //模拟测试界面
    	   $scope.selkc=function(kcid,kcmc,tiliang){
    		   var confirmPopup = $ionicPopup.confirm({
                   title: '测试提示',
                   template: '你确定要进行'+kcmc+'测试吗？',
                   okText: '确定',
                   okType: 'button-positive',
                   cancelText: '取消',
                   cancelType: 'button-default'
                 });
                 confirmPopup.then(function(res) {
                   if(res==true) {
                	   if(typeof(tiliang)=="undefined"||tiliang==""){
                		   $ionicPopup.alert(
           						{
           						   title: '<div align="left"><i class="icon ion-android-alert  assertive "><strong>&nbsp;提示：</strong></i></div>',
           						   template: '<div><strong>暂未出卷！</strong></div>',          
           						   okText: '确定',
           						   okType: 'button-calm'
           						  }
           					);
                	   }else{
                		   tiliang=tiliang.replace(/\//g,",");
                		   $rootScope.gotoURL(encodeURI("#/dotest/"+kcid+"/"+kcmc+"/"+tiliang));
                	   }
                   }
                 });
    		   
    	   }
    	   
    	   //错题集
    	   $scope.selctjkc=function(kcid,kcmc,kcbz){
    		   if(kcbz==0){
    			   $ionicPopup.alert(
      			   {
      					title: '<div align="left"><i class="icon ion-android-alert  assertive "><strong>&nbsp;提示：</strong></i></div>',
      					template: '<div><strong>暂无错题！</strong></div>',          
      					okText: '确定',
      					okType: 'button-calm'
      			   }
      		   );
    		   }else{
    			   $rootScope.gotoURL(encodeURI("#/ctlx/"+kcid+"/"+kcmc));
    		   }
    	   }

      })
      .controller('zjlxCtrl', function($rootScope,$scope,$http,$timeout,$state,$window,$ionicPopup,systemArgs,$stateParams, $ionicLoading,$ionicModal) 

{
    	  $scope.title=$stateParams['mc'];
    	  $scope.listtm;
    	  $scope.sel=1;
    	  $scope.zqsl=0;
    	  $scope.cwsl=0;
    	  $scope.back=function(){
    		  $scope.modal.remove();
    		  window.history.back();
    	  };
    	  $ionicLoading.show({
    		  content: 'Loading',
  		      animation: 'fade-in',
  		      showBackdrop: true,
  		      maxWidth: 200,
  		      showDelay: 0
    	  });
    	  $timeout($http({
	   		   method:"post",
	   		   url:systemArgs.appURL+'/app!getZjlxtm.action',
	   		   params:{flid:$stateParams['id']}
		   	   })
		   	   .success(function(data, status, headers, config){
		   		   $ionicLoading.hide();
		   		   $scope.fuhao="、";
		   		   $scope.listtm=data.resultinfo;
		   		   $scope.num=data.resultinfo.length;
		   		   $scope.tm=data.resultinfo[0];
	   	   }),2000);
    	  
    	  
    	  var lastsxh=1;
          $scope.shuzi=[1,2,3,4,5,6,7,8,9,10];
          //模态框
          $ionicModal.fromTemplateUrl('templates/modal.html', {
      	    scope: $scope
      	  }).then(function(modal) {
      	    $scope.modal = modal;
      	  });
    	  //点击模态框
          $scope.mtk=false;
          var dt="";
          var tmary=new Array();
          var sytmary=new Array();
          var yici=false;
          $scope.showm=function(){
        	  if($scope.num%10==0){
        		  for(var j=1;j<=Math.ceil($scope.num/10);j++){
        			  tmary[j-1]=j;
        		  }
        	  }else{
        		  for(var j=1;j<Math.ceil($scope.num/10);j++){
        			  tmary[j-1]=j;
        		  }
        		  if($scope.num-10*Math.floor($scope.num/10)>0){
        			  var l=0;
        			  for(var k=10*Math.floor($scope.num/10)+1;k<=$scope.num;k++){
        				  sytmary[l]=k;
        				  l++;
        			  }
        		  }
        	  }
        	  $scope.zushu=tmary;
        	  $scope.sytm=sytmary;
        	  
        	  if(yici==false&&dt!=""){
        			var yida=dt.split(",");
        			for(var m=0;m<yida.length;m++){
        				var resyd="sty"+yida[m];
        				if($scope.listtm[yida[m]-1].sfyx==1){
        					$scope[resyd]={'background':'#4EEE94'};
        				}else{
        					$scope[resyd]={'background':'#FF4040'};
        				}
        			}
        	  }
        	  yici=true;
        	  var res="sty"+lastsxh;
        	  if($scope.listtm[lastsxh-1].remark==1||(($scope.listtm[lastsxh-1].wtlx=="多选题"||$scope.listtm[lastsxh-1].wtlx=="填空题")&&$scope.listtm[lastsxh-1].first=="0")){
        		   $scope[res]={'border':'solid 1px','border-color':'royalblue'};
        	  }else{
        		  	if($scope.listtm[lastsxh-1].sfyx==1){
        		  		$scope[res]={'border':'solid 1px','border-color':'royalblue','background':'#4EEE94'};
        		  	}else{
        		  		$scope[res]={'border':'solid 1px','border-color':'royalblue','background':'#FF4040'};
        		  	}
        	  }
        	  $scope.mtk=true;
        	  $scope.modal.show();
          }
    	  
    	  //跳题
    	  $scope.tminfo=function(sxh){
    		  $scope.zq="";
    		  $scope.cw="";
    		  $scope.bz="";
    		  $scope.tm=$scope.listtm[sxh-1];
    		  $scope.sel=sxh;
    		  $scope.da=$scope.listtm[$scope.sel-1].remark;
    		  if($scope.listtm[$scope.sel-1].sfyx=="false"){
    			  $scope.gai=false;
    		  }else{
    			  $scope.gai=true;
    		  }
    		  $("#"+lastsxh).css({'border':'','border-color':''});
    		  $("#"+sxh).css({'border':'solid 1px','border-color':'royalblue'});
    		  if($scope.listtm[sxh-1].sfyx==1){
    			  $scope.zq="回答正确";
    		  }
    		  if($scope.listtm[sxh-1].sfyx==0){
    			  $scope.cw="回答错误";
    			  $scope.bz=$scope.listtm[$scope.sel-1].bz;
    		  }
			  lastsxh=sxh;
			  if($scope.mtk==true){
				  $scope.modal.hide();
				  $scope.mtk=false;
			  }
			  if($scope.listtm[sxh-1].wtlx=="填空题"&&$scope.listtm[sxh-1].remark.toString()!=""&&$scope.listtm[sxh-1].remark.toString()!=1){
				  var length=$scope.listtm[sxh-1].hdxlist.length;
				  for(var i=0;i<length;i++){
					  $scope.listtm[sxh-1].hdxlist[i].inputValue=$scope.listtm[sxh-1].remark[i];
				  }
			  }
    	  }
    	  
    	  //答题
    	  $scope.xuanze=function(daan,zq){
    		  $scope.listtm[$scope.sel-1].remark=daan;
    		  $scope.gai=true;
    		  if($scope.mtk==false){
				  if(dt==""){
					  dt+=$scope.sel;
				  }else{
					  dt+=","+$scope.sel;
				  }
			  }
    		  if(zq==0){
    			  $scope.listtm[$scope.sel-1].sfyx=0;
    			  $scope.cw="回答错误";
    			  $scope.bz=$scope.listtm[$scope.sel-1].bz;
    			  $http({
    				  method:"post",
    				  url:systemArgs.appURL+'/app!bcct.action',
    		   		  params:{
    		   			  ctid:$scope.listtm[$scope.sel-1].id,
    		   			  name:sessionStorage.loginName
    		   		  }
    			  }).success(function(data){
    				  
    			  });
    			  $scope.cwsl++;
    			  $("#"+$scope.sel).css('background','#FF4040');
    		  }else{
    			  $scope.listtm[$scope.sel-1].sfyx=1;
    			  $scope.zq="回答正确";
    			  $scope.zqsl++;
    			  $("#"+$scope.sel).css('background','#4EEE94');
    			  $scope.next();
    			  
    		  }
    	  }
    	  
    	  //多选/填空  答题保存
    	  $scope.doSave=function(sxh){
    		  var isTrue=true;
    		  if($scope.mtk==false){
				  if(dt==""){
					  dt+=$scope.sel;
				  }else{
					  dt+=","+$scope.sel;
				  }
			  }
    		  if($scope.listtm[sxh-1].wtlx=="多选题"){
    			  var length=$scope.listtm[sxh-1].hdxlist.length;
    			  for(var i=0;i<length;i++){
    				  if(($scope.listtm[sxh-1].hdxlist[i].sfzqda=="1"&&$scope.listtm[sxh-1].remark.indexOf($scope.listtm[sxh-1].hdxlist[i].id)==-1)||
    						  ($scope.listtm[sxh-1].hdxlist[i].sfzqda=="0"&&$scope.listtm[sxh-1].remark.indexOf($scope.listtm[sxh-1].hdxlist[i].id)>=0)){
    					  isTrue=false;
    					  break;
    				  }
    			  }
    			  
    			  if(isTrue){
    				  $scope.listtm[sxh-1].sfyx=1;
    				  $scope.zqsl++;
    				  $scope.zq="回答正确";
    				  $("#"+$scope.sel).css('background','#4EEE94');
    				  $scope.next();
    			  }else{
    				  $scope.listtm[sxh-1].sfyx=0;
    				  $scope.cw="回答错误";
    				  $("#"+$scope.sel).css('background','#FF4040');
        			  $scope.bz=$scope.listtm[$scope.sel-1].bz;
        			  $scope.cwsl++;
    			  }
    		  }
    		  if($scope.listtm[sxh-1].wtlx=="填空题"){
    			  var length=$scope.listtm[sxh-1].hdxlist.length;
    			  $scope.trueDa=[];
    			  $scope.listtm[sxh-1].remark=[];
    			  $scope.ztrDa="";
    			  for(var i=0;i<length;i++){
    				  $scope.trueDa.push($scope.listtm[sxh-1].hdxlist[i].damc);
    				  $scope.listtm[sxh-1].remark.push($("#"+$scope.listtm[sxh-1].hdxlist[i].id).val().trim());
    			  }
    			  if($scope.listtm[sxh-1].isorder=="0"){
    				  $scope.trueDa=$scope.trueDa.sort();
    				  $scope.ztrDa=$scope.listtm[sxh-1].remark.sort().toString();
    			  }else{
    				  $scope.ztrDa=$scope.listtm[sxh-1].remark.toString();
    			  }
    			  if($scope.trueDa.toString()==$scope.ztrDa){
    				  $scope.listtm[sxh-1].sfyx=1;
    				  $scope.zqsl++;
    				  $scope.zq="回答正确";
    				  $("#"+$scope.sel).css('background','#4EEE94');
    				  $scope.next();
    			  }else{
    				  $scope.listtm[sxh-1].sfyx=0;
    				  $scope.cw="回答错误";
    				  $("#"+$scope.sel).css('background','#FF4040');
        			  $scope.bz=$scope.listtm[$scope.sel-1].bz;
        			  $scope.cwsl++;
    			  }
    		  }
    		  $scope.listtm[sxh-1].first=2;
    	  }
    	  //填空题记录
    	  $(document).on('input propertychange','input',function(){
    		  var length=$scope.listtm[$scope.sel-1].hdxlist.length;
    		  $scope.listtm[$scope.sel-1].remark=[];
    		  for(var i=0;i<length;i++){
    			  $scope.listtm[$scope.sel-1].remark.push($("#"+$scope.listtm[$scope.sel-1].hdxlist[i].id).val().trim());
    		  }
    		  //显示未答
    		  /*if($scope.listtm[$scope.sel-1].remark.toString()==""){
    			  $("#"+$scope.sel).css('background','transparent');
    		  }else{
    			  $("#"+$scope.sel).css('background','#ccc');
    		  }*/
    		  //显示未答
    		  $scope.listtm[$scope.sel-1].first="0";
		  }).find("input");
    	  //多选题
    	  $scope.isChecked=function(sxh,id){
    		  if($scope.listtm[sxh-1].first=="1"){
    			  $scope.listtm[sxh-1].remark=[];
    			  $scope.listtm[sxh-1].first="0";
    		  }
    		  if($scope.listtm[sxh-1].wtlx=="多选题"){
    			  return $scope.listtm[sxh-1].remark.indexOf(id)>=0;
    		  }
    	  }
    	  $scope.updateSelection=function($event,sxh,id){
    		  var checkbox=$event.target;
    		  var checked=checkbox.checked;
    		  if(checked){
    			  $scope.listtm[sxh-1].remark.push(id);
    		  }else{
    			  var idx=$scope.listtm[sxh-1].remark.indexOf(id);
    			  $scope.listtm[sxh-1].remark.splice(idx,1);
    		  }
    	  }
    	  
    	  
    	  $scope.last=function(){
    		  if(Number($scope.sel-1)>=1){
    			  $ionicLoading.show({
    				  content: 'Loading',
    	    		  animation: 'fade-in',
    	    		  showBackdrop: false,
    	    		  maxWidth: 200,
    	    		  showDelay: 0,
    	    		  template: '<p class="item-myicon"><ion-spinner icon="lines" style="font-size:100px!important" class="spinner-calm"></ion-spinner></p>'
    			  });
    			  $timeout(function(){
    				  $ionicLoading.hide();
    				  $scope.tminfo($scope.sel-1);
    			  },500);
    		  }
    	  }
    	  $scope.next=function(){
    		  if(Number($scope.num-$scope.sel-1)>=0){
    			  $ionicLoading.show({
    				  content: 'Loading',
    	    		  animation: 'fade-in',
    	    		  showBackdrop: false,
    	    		  maxWidth: 200,
    	    		  showDelay: 0,
    	    		  template: '<p class="item-myicon"><ion-spinner icon="lines" style="font-size:100px!important" class="spinner-calm"></ion-spinner></p>'
    			  });
    			  $timeout(function(){
    				  $ionicLoading.hide();
        			  $scope.tminfo(Number($scope.sel)+1);
    			  },500);
    		  }
    	  }
    	  $scope.onSwipeLeft=function(){
    		  $scope.next();
  	      }
    	  $scope.onSwipeRight=function(){
    		  $scope.last();
  	      }
      })
      .controller('dotestCtrl', function($rootScope,$scope,$http,$timeout,$state,$window,$ionicPopup,systemArgs,$stateParams, 

$ionicLoading,$interval, $ionicModal) {
    	  $scope.title=$stateParams['mc'];
    	  $scope.tiliang=$stateParams['tiliang'];
    	  $scope.listtm;
    	  $scope.sel=1;
    	  $scope.yidashu=0;   //已答数
    	  $scope.zqsl=0;
    	  $scope.cwsl=0;
    	  $scope.grade=0;
    	  $scope.num;
    	  $scope.jieshu=false;
    	  $scope.modal = null;
    	  $ionicLoading.show({
	  		    content: 'Loading',
	  		    animation: 'fade-in',
	  		    showBackdrop: true,
	  		    maxWidth: 200,
	  		    showDelay: 0
    	  });
    	  $timeout($http({
    		   method:"post",
	   		   url:systemArgs.appURL+'/app!dotest.action',
	   		   params:{kcid:$stateParams['id']}
    	  }).success(function(data, status, headers, config){
    		   $ionicLoading.hide();
    		   $scope.fuhao="、";
	   		   $scope.listtm=data.resultinfo;
	   		   $scope.num=data.resultinfo.length;
	   		   $scope.tm=data.resultinfo[0];
		   	   $scope.da=$scope.listtm[$scope.sel-1].remark;
	  		   if($scope.listtm[$scope.sel-1].sfyx>=0){
	  			   $scope.gai=true;
	  		   }else{
	  			   $scope.gai=false;
	  		   }
    	  }),2000);
    	  
    	  //定时器
    	  var h=1,m=29,s=59;
    	  var h1=0,m1=0,s1=0;
          $scope.date='1时29分59秒';
          $scope.time=$interval( function run(){
              --s;
              ++s1;
              if(s<0){
                  --m;
                  s=59;
                  ++m1;
                  s1=0;
              }
              if(m<0){
                  --h;
                  m=59;
                  ++h1;
                  m1=0;
              }
              if(h<0){
                  h=0;
                  s=0;
                  m=0;
              }
              $scope.date = h+'时'+m+'分'+s+'秒';
              $scope.date1=h1+'时'+m1+'分'+s1+'秒';
          },1000);
          
          var lastsxh=1;
          $scope.shuzi=[1,2,3,4,5,6,7,8,9,10];
          //模态框
          $ionicModal.fromTemplateUrl('templates/modal.html', {
      	    scope: $scope
      	  }).then(function(modal) {
      	    $scope.modal = modal;
      	  });

          
          //点击模态框
          var utidstr="";
          var ctj="";
          var hdjgArr=new Array();
          $scope.diyici=false;
          $scope.mtk=0;
          $scope.dt=[];
          var tmary=new Array();
          var sytmary=new Array();
          var yici=false;
          $scope.showm=function(){
        	  if($scope.num%10==0){
        		  for(var j=1;j<=Math.ceil($scope.num/10);j++){
        			  tmary[j-1]=j;
        		  }
        	  }else{
        		  for(var j=1;j<Math.ceil($scope.num/10);j++){
        			  tmary[j-1]=j;
        		  }
        		  if($scope.num-10*Math.floor($scope.num/10)>0){
        			  var l=0;
        			  for(var k=10*Math.floor($scope.num/10)+1;k<=$scope.num;k++){
        				  sytmary[l]=k;
        				  l++;
        			  }
        		  }
        	  }
        	  $scope.zushu=tmary;
        	  $scope.sytm=sytmary;
        	  //交卷状态进入
        	  if($scope.jieshu==true){
        		  if(!$scope.diyici){
        			  for(var l=0;l<$scope.listtm.length;l++){
        				  utidstr+=$scope.listtm[l].id+",";
        				  $scope.listtm[l].first=2;
        				  if($scope.listtm[l].remark!=1&&$scope.listtm[l].remark.toString()!=""){
        					  if($scope.listtm[l].wtlx=="填空题"){
        						  hdjgArr[l]="tkt_";
        					  }else{
        						  hdjgArr[l]="xzt_";
        					  }
        					  hdjgArr[l] += $scope.listtm[l].id+":hdx_"+$scope.listtm[l].remark.toString().replace(/[,]/g,"|")+":";
        					  if($scope.listtm[l].sfyx==1){
        						  hdjgArr[l]+="T";
        					  }else{
        						  hdjgArr[l]+="F";
        					  }
        				  }
        				  //错误
        				  if($scope.listtm[l].sfyx==0){
        					  var res1="sty"+Number(l+1);
        					  $scope[res1]={'background':'#FF4040'};
        					  $scope.cwsl++;
        					  ctj+=$scope.listtm[l].id+",";
        				  }
        				  //正确
        				  if($scope.listtm[l].sfyx==1){
        					  var gradeStr=$scope.tiliang.split(",");
        					  for(var n=0;n<gradeStr.length;n++){
        						  var gradeNum=gradeStr[n].split(":");
        						  if(gradeNum[0]==$scope.listtm[l].wtlx){
        							  $scope.grade+=Number(gradeNum[2]);
        							  break;
        						  }
        					  }
        					  var res2="sty"+Number(l+1);
        					  $scope[res2]={'background':'#4EEE94'};
        					  $scope.zqsl++;
        				  }
        			  }
        			  var over="sty"+lastsxh;
        			  if($scope.listtm[lastsxh-1].sfyx==1){
        				  $scope[over]={'border':'solid 1px','border-color':'royalblue','background':'#4EEE94'};
        			  }
        			  if($scope.listtm[lastsxh-1].sfyx==0){
        				  $scope[over]={'border':'solid 1px','border-color':'royalblue','background':'#FF4040'};
        			  }
        			  if($scope.listtm[lastsxh-1].sfyx=="false"){
        				  $scope[over]={'border':'solid 1px','border-color':'royalblue'};
        			  }
        			  
        			  $scope.diyici=true;
        			  $scope.result="一共用时"+$scope.date1+"    得分："+$scope.grade;
        			  var arr=hdjgArr.join(",");
        			  console.log(arr);
        			  $http({
            			  method:"post",
           	   		   	  url:systemArgs.appURL+'/app!bcjg.action',
           	   		      params:{
           	   		    	  utidstr:utidstr,
           	   		          resultSelectStr:arr,
           	   		    	  faflSelect:$stateParams['id'],
           	   		    	  cwts:$scope.cwsl,
           	   		    	  zqts:$scope.zqsl,
           	   		    	  name:$rootScope.loginName,
           	   		    	  ctid:ctj,
           	   		    	  grade:$scope.grade
           	   		      }
            		  }).success(function(data){
            			  
            		  });
        		  }
        	  }else{
        		  //未交卷进入
        		  if(yici==false&&$scope.dt.toString()!=""){
        			  for(var m=0;m<$scope.dt.length;m++){
        				  var resyd="sty"+$scope.dt[m];
        				  $scope[resyd]={'background':'#ccc'};
        			  }
        		  }
        		  yici=true;
        		  var res="sty"+lastsxh;
        		  console.log($scope.listtm[lastsxh-1].remark);
        		  if($scope.listtm[lastsxh-1].remark==1||(($scope.listtm[lastsxh-1].wtlx=="多选题"||$scope.listtm[lastsxh-1].wtlx=="填空题")&&$scope.listtm[lastsxh-1].first=="0")){
        			  $scope[res]={'border':'solid 1px','border-color':'royalblue'};
        		  }else{
        			  $scope[res]={'border':'solid 1px','border-color':'royalblue','background':'#ccc'};
        		  }
        		  //$scope[res]={'background':'#ccc'};
        	  }
        	  $scope.modal.show();
        	  $scope.mtk=true;
          }
    	  //交卷
          $scope.save=false;
    	  $scope.jiaojuan=function(){
    		    var confirmPopup = $ionicPopup.confirm({
                  title: '交卷提示',
                  template: '你确定要交卷吗?一旦交卷不可修改!!',
                  okText: '确定',
                  okType: 'button-positive',
                  cancelText: '取消',
                  cancelType: 'button-default'
                });
                confirmPopup.then(function(res) {
                  if(res==true&&$scope.jieshu==false) {
                	  /*if($scope.save==false){
                		  $http({
                			  method:"post",
               	   		   	  url:systemArgs.appURL+'/app!savejg.action',
               	   		      params:{kcid:$stateParams['id']}
                		  }).success(function(data){
                			  
                		  });
                		  $scope.save=true;
                	  }*/
                	  $interval.cancel($scope.time);
                	  $scope.jieshu=true;
                	  $scope.gai=true;
                	  $scope.showm();
                  } else {
                	  if($scope.jieshu==true){
                		  $ionicPopup.alert(
                				  {
                					  title: '<div align="left"><i class="icon ion-android-alert  assertive "><strong>&nbsp;提示：</strong></i></div>',
                					  template: '<div><strong>您已提交,不能重复提交！</strong></div>',          
                					  okText: '确定',
                					  okType: 'button-calm'
                				  }
                		  );
                	  }
                  }
                });
    	  };
    	  
    	  //答题
    	  $scope.xuanze=function(daan,zq){
    		  if($scope.jieshu==false){
    			  if($scope.listtm[$scope.sel-1].remark==1){
    				  if($scope.mtk==0){
    					  $scope.dt.push($scope.sel);
        			  }
    			  }
    			  $("#"+$scope.sel).css('background','#ccc');
    			  $scope.listtm[$scope.sel-1].remark=daan;
    			  //$scope.gai=true;               //答完不能修改
    			  $scope.yidashu++;
    			  if(zq==0){
    				  $scope.listtm[$scope.sel-1].sfyx=0;
    			  }else{
    				  $scope.listtm[$scope.sel-1].sfyx=1;
    			  }
    			  $scope.next();
    		  }
    	  }
    	  
    	  
    	  //多选题
    	  $scope.isChecked=function(sxh,id){
    		  if($scope.listtm[sxh-1].first=="1"){
    			  $scope.listtm[sxh-1].remark=[];
    			  $scope.listtm[sxh-1].first="0";
    		  }
    		  if($scope.listtm[sxh-1].wtlx=="多选题"){
    			  return $scope.listtm[sxh-1].remark.indexOf(id)>=0;
    		  }
    	  }
    	  $scope.record=false;
    	  //填空题记录
    	  $(document).on('input propertychange','input',function(){
    		  var length=$scope.listtm[$scope.sel-1].hdxlist.length;
    		  $scope.listtm[$scope.sel-1].remark=[];
    		  $scope.trueDa=[];
    		  $scope.ztrDa="";
    		  for(var i=0;i<length;i++){
    			  $scope.trueDa.push($scope.listtm[$scope.sel-1].hdxlist[i].damc);
    			  $scope.listtm[$scope.sel-1].remark.push($("#"+$scope.listtm[$scope.sel-1].hdxlist[i].id).val().trim());
    		  }
    		  if($scope.listtm[$scope.sel-1].remark.toString()==""){
    			  if($scope.mtk==0){
    				  var idx=$scope.dt.indexOf($scope.sel);
    				  $scope.dt.splice(idx,1);
    			  }
    			  $("#"+$scope.sel).css('background','transparent');
    		  }else{
    			  if($scope.mtk==0){
    				  $scope.dt.push($scope.sel);
    			  }
    			  $("#"+$scope.sel).css('background','#ccc');
    			  if($scope.listtm[$scope.sel-1].isorder==0){
    				  $scope.trueDa=$scope.trueDa.sort();
    				  $scope.ztrDa=$scope.listtm[$scope.sel-1].remark.sort().toString();
    			  }else{
    				  $scope.ztrDa=$scope.listtm[$scope.sel-1].remark.toString();
    			  }
    			  if($scope.trueDa.toString()==$scope.ztrDa){
    				  $scope.listtm[$scope.sel-1].sfyx=1;
    			  }else{
    				  $scope.listtm[$scope.sel-1].sfyx=0;
    			  }
    		  }
    		  console.log($scope.listtm[$scope.sel-1].remark);
		  }).find("input");
    	  
    	  //多选题记录
    	  $scope.updateSelection=function($event,sxh,id){
    		  var checkbox=$event.target;
    		  var checked=checkbox.checked;
    		  if(checked){
    			  $scope.listtm[sxh-1].remark.push(id);
    		  }else{
    			  var idx=$scope.listtm[sxh-1].remark.indexOf(id);
    			  $scope.listtm[sxh-1].remark.splice(idx,1);
    		  }
    		  if($scope.listtm[sxh-1].remark.toString()==""){
    			  if($scope.mtk==0){
    				  var idx=$scope.dt.indexOf($scope.sel);
    				  $scope.dt.splice(idx,1);
    			  }
    			  $("#"+$scope.sel).css('background','transparent');
    		  }else{
    			  if($scope.mtk==0){
    				  $scope.dt.push($scope.sel);
    			  }
    			  $("#"+$scope.sel).css('background','#ccc');
    			  var isTrue=true;
    			  var length=$scope.listtm[sxh-1].hdxlist.length;
    			  for(var i=0;i<length;i++){
    				  if(($scope.listtm[sxh-1].hdxlist[i].sfzqda=="1"&&$scope.listtm[sxh-1].remark.indexOf($scope.listtm[sxh-1].hdxlist[i].id)==-1)||
    						  ($scope.listtm[sxh-1].hdxlist[i].sfzqda=="0"&&$scope.listtm[sxh-1].remark.indexOf($scope.listtm[sxh-1].hdxlist[i].id)>=0)){
    					  isTrue=false;
    					  break;
    				  }
    			  }
    			  if(isTrue){
    				  $scope.listtm[sxh-1].sfyx=1;
    			  }else{
    				  $scope.listtm[sxh-1].sfyx=0;
    			  }
    		  }
    	  }
    	  
    	  //跳题
    	  $scope.tminfo=function(sxh){
    		  $scope.record=true;
    		  $scope.tm=$scope.listtm[sxh-1];
			  $scope.sel=sxh;
			  $scope.da=$scope.listtm[$scope.sel-1].remark;
    		  if($scope.jieshu==true){
    			  $scope.cw="";
    			  $scope.zq="";
    			  $scope.bz="";
    			  if($scope.listtm[$scope.sel-1].sfyx==0){
    				  $scope.cw="回答错误";
        			  $scope.bz=$scope.listtm[$scope.sel-1].bz;
    			  }
    			  if($scope.listtm[$scope.sel-1].sfyx==1){
    				  $scope.zq="回答正确";
    			  }
    			  $scope.gai=true;
    		  }
    		  angular.element("#"+lastsxh).css({'border':'','border-color':''});
    		  $("#"+sxh).css({'border':'solid 1px','border-color':'royalblue'});
			  lastsxh=sxh;
			  if($scope.mtk==true){
				  $scope.modal.hide();
				  $scope.mtk=false;
			  }
			  if($scope.listtm[sxh-1].wtlx=="填空题"&&$scope.listtm[sxh-1].remark.toString()!=""&&$scope.listtm[sxh-1].remark.toString()!=1){
				  var length=$scope.listtm[sxh-1].hdxlist.length;
				  for(var i=0;i<length;i++){
					  $scope.listtm[sxh-1].hdxlist[i].inputValue=$scope.listtm[sxh-1].remark[i];
				  }
			  }
    	  }
    	  $scope.back=function(){
    		  if($scope.jieshu==false){
    			  var confirmPopup = $ionicPopup.confirm({
                      title: '退出提示',
                      template: '你确定要退出吗?一旦退出需重新答题!!',
                      okText: '确定',
                      okType: 'button-positive',
                      cancelText: '取消',
                      cancelType: 'button-default'
                    });
                  confirmPopup.then(function(res) {
                      if(res==true) {
                    	  $scope.modal.remove();
                    	  $rootScope.gotoURL("#/tab/mntest");
                      }
    		      });
    		  }else{
    			  $scope.modal.remove();
    			  $rootScope.gotoURL("#/tab/mntest");
    		  }
    	  }
    	  $scope.last=function(){
    		  if(Number($scope.sel-1)>=1){
    			  $ionicLoading.show({
    				  content: 'Loading',
    	    		  animation: 'fade-in',
    	    		  showBackdrop: false,
    	    		  maxWidth: 200,
    	    		  showDelay: 0,
    	    		  template: '<p class="item-myicon"><ion-spinner icon="lines" style="font-size:100px!important" class="spinner-calm"></ion-spinner></p>'
    			  });
    			  $timeout(function(){
    				  $ionicLoading.hide();
    				  $scope.tminfo($scope.sel-1);
    			  },500);
    		  }
    	  }
    	  $scope.next=function(){
    		  if(Number($scope.num-$scope.sel-1)>=0){
    			  $ionicLoading.show({
    				  content: 'Loading',
    	    		  animation: 'fade-in',
    	    		  showBackdrop: false,
    	    		  maxWidth: 200,
    	    		  showDelay: 0,
    	    		  template: '<p class="item-myicon"><ion-spinner icon="lines" style="font-size:100px!important" class="spinner-calm"></ion-spinner></p>'
    			  });
    			  $timeout(function(){
    				  $ionicLoading.hide();
        			  $scope.tminfo(Number($scope.sel)+1);
    			  },500);
    		  }
    	  }
    	  $scope.onSwipeLeft=function(){
    		  $scope.next();
  	      }
    	  $scope.onSwipeRight=function(){
    		  $scope.last();
  	      }
    	  
      })
      .controller('ctlxCtrl', function($rootScope,$scope,$http,$timeout,$state,$window,$ionicPopup,systemArgs,$stateParams, $ionicLoading,$ionicModal) 

{
    	  $scope.title=$stateParams['mc'];
    	  $scope.listtm;
    	  $scope.sel=1;
    	  $scope.zqsl=0;
    	  $scope.cwsl=0;
    	  $scope.back=function(){
    		  var confirmPopup = $ionicPopup.confirm({
                  title: '退出提示',
                  template: '你确定要退出吗?一旦退出需重新答题!!',
                  okText: '确定',
                  okType: 'button-positive',
                  cancelText: '取消',
                  cancelType: 'button-default'
                });
              confirmPopup.then(function(res) {
                  if(res==true) {
                	  $scope.modal.remove();
            		  window.history.back();
                  }
		      });
    	  };
    	  $ionicLoading.show({
    		    content: 'Loading',
    		    animation: 'fade-in',
    		    showBackdrop: true,
    		    maxWidth: 200,
    		    showDelay: 0
    	  });
    	  $timeout($http({
	   		   method:"post",
	   		   url:systemArgs.appURL+'/app!getctlxtm.action',
	   		   params:{
	   			   flid:$stateParams['id'],
	   			   name:$rootScope.loginName
	   		   }
		   	   })
		   	   .success(function(data, status, headers, config){
		   		   $ionicLoading.hide();
		   		   $scope.fuhao="、";
		   		   $scope.listtm=data.resultinfo;
		   		   $scope.num=data.resultinfo.length;
		   		   $scope.tm=data.resultinfo[0];
	   	   }),2000);
    	  
    	  
    	  //删除题目
    	  $scope.del=function(){
    		  var confirmPopup = $ionicPopup.confirm({
                  title: '删除提示',
                  template: '你确定要删除此题吗?!!',
                  okText: '确定',
                  okType: 'button-positive',
                  cancelText: '取消',
                  cancelType: 'button-default'
                });
              confirmPopup.then(function(res) {
                  if(res==true) {
                	  $http({
            			  method:"post",
            			  url:systemArgs.appURL+'/app!delct.action',
            			  params:{id:$scope.listtm[$scope.sel-1].id}
            		  }).then(function successCallback(data){
            			  $ionicPopup.alert(
          						{
          						   title: '<div align="left"><i class="icon ion-android-alert  assertive "><strong>&nbsp;提示：</strong></i></div>',
          						   template: '<div><strong>下次进入生效！</strong></div>',          
          						   okText: '确定',
          						   okType: 'button-calm'
          						  }
          					);
            		  },function errorCallback(data){
            			  
            		  });
                  }
		      });
    		 
    	  }
    	  
    	  var lastsxh=1;
          $scope.shuzi=[1,2,3,4,5,6,7,8,9,10];
          //模态框
          $ionicModal.fromTemplateUrl('templates/modal.html', {
      	    scope: $scope
      	  }).then(function(modal) {
      	    $scope.modal = modal;
      	  });
    	  //点击模态框
          $scope.mtk=false;
          var dt="";
          var tmary=new Array();
          var sytmary=new Array();
          var yici=false;
          $scope.showm=function(){
        	  if($scope.num%10==0){
        		  for(var j=1;j<=Math.ceil($scope.num/10);j++){
        			  tmary[j-1]=j;
        		  }
        	  }else{
        		  for(var j=1;j<Math.ceil($scope.num/10);j++){
        			  tmary[j-1]=j;
        		  }
        		  if($scope.num-10*Math.floor($scope.num/10)>0){
        			  var l=0;
        			  for(var k=10*Math.floor($scope.num/10)+1;k<=$scope.num;k++){
        				  sytmary[l]=k;
        				  l++;
        			  }
        		  }
        	  }
        	  $scope.zushu=tmary;
        	  $scope.sytm=sytmary;
        	  
        	  if(yici==false&&dt!=""){
        			var yida=dt.split(",");
        			for(var m=0;m<yida.length;m++){
        				var resyd="sty"+yida[m];
        				if($scope.listtm[yida[m]-1].sfyx==1){
        					$scope[resyd]={'background':'#4EEE94'};
        				}else{
        					$scope[resyd]={'background':'#FF4040'};
        				}
        			}
        	  }
        	  yici=true;
        	  var res="sty"+lastsxh;
        	  if($scope.listtm[lastsxh-1].remark==1||(($scope.listtm[lastsxh-1].wtlx=="多选题"||$scope.listtm[lastsxh-1].wtlx=="填空题")&&$scope.listtm[lastsxh-1].first=="0")){
        		  $scope[res]={'border':'solid 1px','border-color':'royalblue'};
        	  }else{
        		  	if($scope.listtm[lastsxh-1].sfyx==1){
        		  		$scope[res]={'border':'solid 1px','border-color':'royalblue','background':'#4EEE94'};
        		  	}else{
        		  		$scope[res]={'border':'solid 1px','border-color':'royalblue','background':'#FF4040'};
        		  	}
        	  }
        	  $scope.mtk=true;
        	  $scope.modal.show();
          }
    	  
    	  //跳题
    	  $scope.tminfo=function(sxh){
    		  $scope.zq="";
    		  $scope.cw="";
    		  $scope.bz="";
    		  $scope.tm=$scope.listtm[sxh-1];
    		  $scope.sel=sxh;
    		  $scope.da=$scope.listtm[$scope.sel-1].remark;
    		  if($scope.listtm[$scope.sel-1].sfyx=="false"){
    			  $scope.gai=false;
    		  }else{
    			  $scope.gai=true;
    		  }
    		  $("#"+lastsxh).css({'border':'','border-color':''});
    		  $("#"+sxh).css({'border':'solid 1px','border-color':'royalblue'});
    		  if($scope.listtm[sxh-1].sfyx==1){
    			  $scope.zq="回答正确";
    		  }
    		  if($scope.listtm[sxh-1].sfyx==0){
    			  $scope.cw="回答错误";
    			  $scope.bz=$scope.listtm[$scope.sel-1].bz;
    		  }
			  lastsxh=sxh;
			  if($scope.mtk==true){
				  $scope.modal.hide();
				  $scope.mtk=false;
			  }
			  if($scope.listtm[sxh-1].wtlx=="填空题"&&$scope.listtm[sxh-1].remark.toString()!=""&&$scope.listtm[sxh-1].remark.toString()!=1){
				  var length=$scope.listtm[sxh-1].hdxlist.length;
				  for(var i=0;i<length;i++){
					  $scope.listtm[sxh-1].hdxlist[i].inputValue=$scope.listtm[sxh-1].remark[i];
				  }
			  }
    	  }
    	  
    	  //单选/判断  答题
    	  $scope.xuanze=function(daan,zq){
    		  $scope.listtm[$scope.sel-1].remark=daan;
    		  $scope.gai=true;
    		  if($scope.mtk==false){
				  if(dt==""){
					  dt+=$scope.sel;
				  }else{
					  dt+=","+$scope.sel;
				  }
			  }
    		  if(zq==0){
    			  $scope.listtm[$scope.sel-1].sfyx=0;
    			  $scope.cw="回答错误";
    			  $scope.bz=$scope.listtm[$scope.sel-1].bz;
    			  $scope.cwsl++;
    			  $("#"+$scope.sel).css('background','#FF4040');
    		  }else{
    			  $scope.listtm[$scope.sel-1].sfyx=1;
    			  $scope.zq="回答正确";
    			  $scope.zqsl++;
    			  $("#"+$scope.sel).css('background','#4EEE94');
    			  $scope.next();
    			  
    		  }
    	  }
    	  
    	  //多选/填空  答题保存
    	  $scope.doSave=function(sxh){
    		  var isTrue=true;
    		  if($scope.mtk==false){
				  if(dt==""){
					  dt+=$scope.sel;
				  }else{
					  dt+=","+$scope.sel;
				  }
			  }
    		  if($scope.listtm[sxh-1].wtlx=="多选题"){
    			  var length=$scope.listtm[sxh-1].hdxlist.length;
    			  for(var i=0;i<length;i++){
    				  if(($scope.listtm[sxh-1].hdxlist[i].sfzqda=="1"&&$scope.listtm[sxh-1].remark.indexOf($scope.listtm[sxh-1].hdxlist[i].id)==-1)||
    						  ($scope.listtm[sxh-1].hdxlist[i].sfzqda=="0"&&$scope.listtm[sxh-1].remark.indexOf($scope.listtm[sxh-1].hdxlist[i].id)>=0)){
    					  isTrue=false;
    					  break;
    				  }
    			  }
    			  
    			  if(isTrue){
    				  $scope.listtm[sxh-1].sfyx=1;
    				  $scope.zqsl++;
    				  $scope.zq="回答正确";
    				  $("#"+$scope.sel).css('background','#4EEE94');
    				  $scope.next();
    			  }else{
    				  $scope.listtm[sxh-1].sfyx=0;
    				  $scope.cw="回答错误";
    				  $("#"+$scope.sel).css('background','#FF4040');
        			  $scope.bz=$scope.listtm[$scope.sel-1].bz;
        			  $scope.cwsl++;
    			  }
    		  }
    		  if($scope.listtm[sxh-1].wtlx=="填空题"){
    			  var length=$scope.listtm[sxh-1].hdxlist.length;
    			  $scope.trueDa=[];
    			  $scope.listtm[sxh-1].remark=[];
    			  $scope.ztrDa="";
    			  for(var i=0;i<length;i++){
    				  $scope.trueDa.push($scope.listtm[sxh-1].hdxlist[i].damc);
    				  $scope.listtm[sxh-1].remark.push($("#"+$scope.listtm[sxh-1].hdxlist[i].id).val().trim());
    			  }
    			  if($scope.listtm[sxh-1].isorder=="0"){
    				  $scope.trueDa=$scope.trueDa.sort();
    				  $scope.ztrDa=$scope.listtm[sxh-1].remark.sort().toString();
    			  }else{
    				  $scope.ztrDa=$scope.listtm[sxh-1].remark.toString();
    			  }
    			  if($scope.trueDa.toString()==$scope.ztrDa){
    				  $scope.listtm[sxh-1].sfyx=1;
    				  $scope.zqsl++;
    				  $scope.zq="回答正确";
    				  $("#"+$scope.sel).css('background','#4EEE94');
    				  $scope.next();
    			  }else{
    				  $scope.listtm[sxh-1].sfyx=0;
    				  $scope.cw="回答错误";
    				  $("#"+$scope.sel).css('background','#FF4040');
        			  $scope.bz=$scope.listtm[$scope.sel-1].bz;
        			  $scope.cwsl++;
    			  }
    		  }
    		  $scope.listtm[sxh-1].first=2;
    	  }
    	  
    	  
    	  //填空题记录
    	  $(document).on('input propertychange','input',function(){
    		  var length=$scope.listtm[$scope.sel-1].hdxlist.length;
    		  $scope.listtm[$scope.sel-1].remark=[];
    		  for(var i=0;i<length;i++){
    			  $scope.listtm[$scope.sel-1].remark.push($("#"+$scope.listtm[$scope.sel-1].hdxlist[i].id).val().trim());
    		  }
    		  //显示未答
    		  /*if($scope.listtm[$scope.sel-1].remark.toString()==""){
    			  $("#"+$scope.sel).css('background','transparent');
    		  }else{
    			  $("#"+$scope.sel).css('background','#ccc');
    		  }*/
    		  //显示未答
    		  $scope.listtm[$scope.sel-1].first="0";
		  }).find("input");
    	  //多选题
    	  $scope.isChecked=function(sxh,id){
    		  if($scope.listtm[sxh-1].first=="1"){
    			  $scope.listtm[sxh-1].remark=[];
    			  $scope.listtm[sxh-1].first="0";
    		  }
    		  if($scope.listtm[sxh-1].wtlx=="多选题"){
    			  return $scope.listtm[sxh-1].remark.indexOf(id)>=0;
    		  }
    	  }
    	  $scope.updateSelection=function($event,sxh,id){
    		  var checkbox=$event.target;
    		  var checked=checkbox.checked;
    		  if(checked){
    			  $scope.listtm[sxh-1].remark.push(id);
    		  }else{
    			  var idx=$scope.listtm[sxh-1].remark.indexOf(id);
    			  $scope.listtm[sxh-1].remark.splice(idx,1);
    		  }
    	  }
    	  
    	  
    	  $scope.last=function(){
    		  if(Number($scope.sel-1)>=1){
    			  $ionicLoading.show({
    				  content: 'Loading',
    	    		  animation: 'fade-in',
    	    		  showBackdrop: false,
    	    		  maxWidth: 200,
    	    		  showDelay: 0,
    	    		  template: '<p class="item-myicon"><ion-spinner icon="lines" style="font-size:100px!important" class="spinner-calm"></ion-spinner></p>'
    			  });
    			  $timeout(function(){
    				  $ionicLoading.hide();
    				  $scope.tminfo($scope.sel-1);
    			  },500);
    		  }
    	  }
    	  $scope.next=function(){
    		  if(Number($scope.num-$scope.sel-1)>=0){
    			  $ionicLoading.show({
    				  content: 'Loading',
    	    		  animation: 'fade-in',
    	    		  showBackdrop: false,
    	    		  maxWidth: 200,
    	    		  showDelay: 0,
    	    		  template: '<p class="item-myicon"><ion-spinner icon="lines" style="font-size:100px!important" class="spinner-calm"></ion-spinner></p>'
    			  });
    			  $timeout(function(){
    				  $ionicLoading.hide();
        			  $scope.tminfo(Number($scope.sel)+1);
    			  },500);
    		  }
    	  }
    	  $scope.onSwipeLeft=function(){
    		  $scope.next();
  	      }
    	  $scope.onSwipeRight=function(){
    		  $scope.last();
  	      }
      })
      .controller('meCtrl', function($rootScope,$scope,$http,$timeout,$state,$window,$ionicPopup,systemArgs,$stateParams, $ionicLoading,$ionicModal) 

{			
    	  $scope.name=sessionStorage.loginXm;
    	  $scope.exit=function(){
    		  var confirmPopup = $ionicPopup.confirm({
                  title: '退出提示',
                  template: '你确定要安全退出吗?!!',
                  okText: '确定',
                  okType: 'button-positive',
                  cancelText: '取消',
                  cancelType: 'button-default'
                });
              confirmPopup.then(function(res) {
                  if(res==true) {
                	  sessionStorage.clear();
                	  $rootScope.gotoURL("#/login");
                  }
		      });
    	  }

	  });
      
})();
