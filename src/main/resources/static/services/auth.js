angular.module('myApp.services', [])
.factory('AuthService', function($http) {
	var user = null;
  return {
    getUser: function() {
			return $http.post('api/whoami');
		}
  };
});