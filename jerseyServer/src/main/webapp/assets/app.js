var myApp = angular.module('App', ['headerController']);

/*
var myApp = angular.module('App', ['userService']);

myApp.controller('headerController', ['$scope', 'userService',
    function($scope,userService){
        $scope.userName = function() {
            return userService.getName();
        };
        $scope.test = "A";
    }
]);
*/