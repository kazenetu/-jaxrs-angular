var myApp = angular.module('headerController', ["userService"]);

myApp.controller('headerController', ['$scope', 'userService',
    function($scope,userService){
        $scope.userName = function() {
            return userService.getName();
        };
        $scope.test = "AAA";
    }
]);
