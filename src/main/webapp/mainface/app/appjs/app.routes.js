
(function () {
	'use strict';
	
    angular.module('ionicApp')

    .config(['$stateProvider', '$urlRouterProvider','$httpProvider',
       function($stateProvider, $urlRouterProvider)
		{	
    		
			$stateProvider
				.state('tab', {
                url: "/tab",
                abstract: true,
                templateUrl: "templates/tabs.html"
				})

	            .state('tab.zjtest', {
	                url: '/zjtest',
	                views: {
	                    'zjtest': {
	                        templateUrl: 'templates/zjtest.html',
	                    }
	                }
	            })
	            .state('tab.mntest', {
	                url: '/mntest',
	                views: {
	                    'mntest': {
	                        templateUrl: 'templates/mntest.html',
	                    }
	                }
	            })
	            .state('tab.ctk', {
	                url: '/ctk',
	                views: {
	                    'ctk': {
	                        templateUrl: 'templates/ctk.html',
	                    }
	                }
	            })
	            .state('tab.me', {
	                url: '/me',
	                views: {
	                    'me': {
	                        templateUrl: 'templates/me.html',
	                    }
	                }
	            })
	            .state('zjlx',{
				url:"/zjlx/:id/:mc",
				cache:'false',
				templateUrl:"templates/zjlx.html"
				})
				.state('mnlx',{
				url:"/dotest/:id/:mc/:tiliang",
				cache:'false',
				templateUrl:"templates/dotest.html"
				})
				.state('ctlx',{
				url:"/ctlx/:id/:mc",
				cache:'false',
				templateUrl:"templates/ctlx.html"
				})
	            .state('login',{
				url:"/login",
				cache:'false',
				templateUrl:"templates/login.html"
				})
		        $urlRouterProvider.otherwise("/login");
		        //$state.go('wbjIndex');
		}
	]);

})();