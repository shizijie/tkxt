
(function () {
	'use strict';
	
    angular.module('ionicApp')

    .config(['$stateProvider', '$urlRouterProvider',
       function($stateProvider, $urlRouterProvider)
		{
			$stateProvider
			  .state('whdIndex', {
	            url: "/whdIndex",
	            views: {
	              'wbjV': {
	                templateUrl: "templates/whdIndex.html"
	              }
	            }
	          })
	          .state('bdoxq', {
	            url: "/bdoxq/:bdid",
	            views: {
	              'wbjV': {
	                templateUrl: "templates/bdoxq.html"
	              }
	            }
	            
	          })
	          ;

	         $urlRouterProvider.otherwise("/whdIndex");
		}
	]);

})();