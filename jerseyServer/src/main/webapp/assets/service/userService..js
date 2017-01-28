var myApp = angular.module('userService', []);
myApp.service('userService', function() {
  this.name = "";

  this.setName = function(name){
    this.name = name;
  };

  this.getName = function(){
    if(this.name === ""){
      return "ななし";
    }
    return this.name;
  };
});
 