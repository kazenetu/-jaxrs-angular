var myApp = angular.module('searchSample', ['webApiService']);

myApp.controller('searchSample', ['$scope', 'webApiService',
    function($scope,webApiService){

        $scope.search = function() {
            $scope.searchResult =  webApiService.query('app/Service1/TestData',{});
        };
    }
]);
