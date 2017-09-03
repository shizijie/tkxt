(function () {
      'use strict';
      
      angular.module('ionicApp', ['ionic'])

      /*
       * 常量配置
       */
      .constant('systemArgs',{'loginURL':'000','appURL':'http://localhost:8080/wxpt'})


      .config(['$stateProvider', '$urlRouterProvider', '$ionicConfigProvider', '$httpProvider', 
               function($stateProvider, $urlRouterProvider, $ionicConfigProvider, $httpProvider){

        //在 iOS中, tabs一直处于底部.
        //在android中, tabs一直在顶部
        //ionic中默认安装后导航在底部解决方案
		
    	  
    	  
      }])

      .controller('appCtrl', function($rootScope,$scope,$http,$timeout,systemArgs,$window,$state) {
        /*
        $scope.items = 
        [
          {"name":"html5"},
          {"name":"JavaScript"},
          {"name":"Css3"},
          {"name":"html5"},
          {"name":"JavaScript"},
          {"name":"Css3"},
          {"name":"html5"},
          {"name":"JavaScript"},
          {"name":"Css3"},
          {"name":"html5"},
          {"name":"JavaScript"},
          {"name":"Css3"}
        ];

        $scope.doFresh = function(){
          $http.get(systemArgs.appURL+'/test.json')
          .success(function(newItems){
            $scope.items = newItems;
          })
          .finally(function(){
            $scope.$broadcast('scroll.refreshComplete');
          });
        }

        //使用定时器是为了缓存一下加载过程，防止加载过快
        $scope.hasMore = true;
        $scope.loadMore = function()
        {
          $timeout(function(){
            if(!$scope.hasMore){
              $scope.$broadcast('scroll.infiniteScrollComplete');
              return;
            }else{
              $scope.hasMore = false;
              $scope.$broadcast('scroll.infiniteScrollComplete');
              return;
            }
          },2000);
        }
        */

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //统一页面跳转，实现页面的统一认证功能
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
             

        //根据URL进行页面跳转
        $rootScope.gotoURL = function(targetURL){ 
          
          //console.log("targetURL:" + targetURL);
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

        //http服务
        $scope.items = 
        [
          {"name":"html5JavaScriptJavaScriptJavaScriptJavaScriptJavaScriptJavaScriptJavaScriptJavaScriptJavaScriptJavaScript"},
          {"name":"在在在在在在在在在在在在在在在在在在"},
          {"name":"Css3"},
          {"name":"html5"},
          {"name":"JavaScript"}
        ];

        
      })
      .controller('whdIndexCtrl', function($rootScope,$scope,$http,$timeout,systemArgs,$ionicLoading,$window) {
    	  
    	  $scope.flList = [];//分类list
    	  $scope.rtList = [];//当前分类的新闻list
    	  $scope.dqflid = "";
    	  
    	  
    	  	$scope.doFresh = function(){
	          $http.get(systemArgs.appURL+'/app!getbdbyfl.action?dqflid='+$scope.dqflid+'&pageNum=1')
	          .success(function(newItems){
				$scope.rtList = [];
	            $scope.rtList = $scope.rtList.concat(newItems.resultinfo);
	            $scope.pageNum = 1;
	            if(newItems.resultinfo.length = 0 || newItems.totalPage <= 1){
					$scope.hasMore = false;  		  
				}else{
					$scope.hasMore = true;  
				}
	          })
	          .finally(function(){
	            $scope.$broadcast('scroll.refreshComplete');
	          });
	        }
	
	        //使用定时器是为了缓存一下加载过程，防止加载过快
	        $scope.pageNum = 0;
	        $scope.hasMore = false;
	        
	        $scope.loadMore = function()
	        {
	          $timeout(function(){
	            if(!$scope.hasMore){
	              $scope.$broadcast('scroll.infiniteScrollComplete');
	              return;
	            }else{
	              $scope.pageNum = $scope.pageNum + 1;
				  $http.get(systemArgs.appURL+'/app!getbdbyfl.action?dqflid='+$scope.dqflid+'&pageNum='+$scope.pageNum)
	              .success(function(newItems)
				  {
	                $scope.rtList = $scope.rtList.concat(newItems.resultinfo);
	                if(newItems.resultinfo.length == 0 || newItems.totalPage <= 1 || $scope.pageNum > newItems.totalPage ){
						$scope.hasMore = false;  
	                }
	                $scope.$broadcast('scroll.infiniteScrollComplete');
					return;
	              });
	            }
	          },1000);
	        }
            
    	  $scope.changeTab=function(cateid,index)
    	  {
    		  if(cateid != $scope.dqflid)
			  {
    			  $scope.rtList = [];//当前分类的新闻list
        		  
                  var a=document.getElementById('sub_header_list').getElementsByTagName('a');
                  for (var i = 0; i < $scope.flList.length; i++) {
                      a[i].className = "button button-clear";
                  }
                  a[index].className = "button button-clear sub_button_select";
                  $scope.dqflid = cateid;
                  $scope.pageNum = 0;
                  $scope.hasMore = true;
			  }
          }
    	  
    	  $http.get(systemArgs.appURL+'/app!getBaodaofl.action')
          .success(function(newItems){
            if(newItems.resultinfo.length > 0){
            	$scope.flList = $scope.flList.concat(newItems.resultinfo);
            	$scope.dqflid = newItems.resultinfo[0].id;
            	
            	$scope.pageNum = 0;
                $scope.hasMore = true;
            }
          })
          .error(function(rep){
        	  alert("请求错误，请重试");
          });
    	  
      })
      
      .controller('bdoxqCtrl', function($rootScope,$scope,$http,$timeout,systemArgs,$ionicLoading,$window,$stateParams) {
	        $scope.bdxq = "";
	        console.log($stateParams['bdid']);
	        $http.get(systemArgs.appURL+'/app!getbdxq.action?bdid='+$stateParams['bdid'])
	        .success(function(newItems){
	        	$scope.bdxq = newItems;
	        })
	        .error(function(rep){
	      	  alert("请求错误，请重试");
	        });
      });
      
})();
