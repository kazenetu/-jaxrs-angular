var myApp = angular.module('searchSample', ['webApiService','userService']);

myApp.controller('searchSample', ['$scope', 'webApiService','userService',
    function($scope,webApiService,userService){

        $scope.search = function() {
            $scope.searchResult =  webApiService.query('app/Service1/TestData',{});
        };

        $scope.userName = userService.getName();
    }
]);
