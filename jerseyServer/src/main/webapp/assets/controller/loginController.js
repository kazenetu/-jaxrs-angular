var myApp = angular.module('loginController', ['webApiService','userService']);

myApp.controller('loginController', ['$scope','$location', 'webApiService','userService',
    function($scope,$location,webApiService,userService){
      $scope.id = "";
      $scope.password = "";
      $scope.isError = false;
      $scope.errorMsg = "";

      $scope.login = function() {
          var result =  webApiService.get('app/Service1/Login?id=:id',{id:$scope.id});
           result.$promise.then(function(response){
              if(result.result !== "OK"){
                $scope.errorMsg = "ログインできませんでした";
                $scope.isError = true;
              }else{
                $scope.isError = false;
                userService.setName($scope.id);
                $location.path('/main');
              }
           });

      };
    }
]);
