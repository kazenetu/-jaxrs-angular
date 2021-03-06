var myApp = angular.module('App', 
[
    'ngResource'
    ,'ngRoute'
    ,'headerController'
    ,'loginController'

    ,'searchSample'
]);

myApp.config(['$routeProvider',function($routeProvider){
  $routeProvider
    .when('/', {
        templateUrl: 'view/login.html'
    })
    .when('/main', {
        templateUrl: 'view/main.html',
    })
    .otherwise({ redirectTo: '/'});
}]);