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
                userService.setId($scope.id);
                userService.setName(result.name);
                $location.path('/main');
              }
           });
      };

      $scope.updateTest = function(){
          var result =  webApiService.post('app/Service1/updateTest',{id:$scope.id});
           result.$promise.then(function(response){
              if(result.result !== "OK"){
                $scope.errorMsg = "更新失敗！";
                $scope.isError = true;
              }else{
                $scope.errorMsg = "更新成功";
                $scope.isError = true;
              }
           },function(response){
             if(response.status == 401)
                $scope.errorMsg = "認証失敗！";
                $scope.isError = true;
           });
      }
    }
]);
