var myApp = angular.module('userService', []);
myApp.service('userService', function() {
  this.name = "";

  this.setName = function(name){
    this.name = name;
    sessionStorage.setItem("userName",this.name);
  };

  this.getName = function(){

    if(this.name === ""){
      this.name = sessionStorage.getItem("userName");
    }
    if(this.name === null || this.name===""){
      return "ななし";
    }
    return this.name;
  };
});
 