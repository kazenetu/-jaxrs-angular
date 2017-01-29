var myApp = angular.module('webApiService', []);
myApp.service('webApiService', function($resource) {

  this.baseUri = "";

  /**
   * 基本URIを設定する
   */
  this.setBaseUri = function(uri){
    this.baseUri = uri;
  }

  /**
   * GETメソッド
   */
  this.get = function(action,params){
    var result = $resource(this.baseUri + action);
    return result.query(params);
  };

  /**
   * POSTメソッド
   */
  this.post = function(action,params){
    var result = $resource(this.baseUri + action);
    return result.post(params);
  };

});
 