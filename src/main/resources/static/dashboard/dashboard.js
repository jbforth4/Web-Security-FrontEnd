'use strict';

var myApp = angular.module('myApp.dashboard', ['ngRoute'])
.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/', {
    templateUrl: 'dashboard/dashboard.html',
    controller: DashboardCtrl,
		resolve: DashboardCtrl.resolve
  });
}]);

function DashboardCtrl($scope, $rootScope, $http, authService, isAuthenticated) {
	$rootScope.authenticated = isAuthenticated;

	$scope.serverResponse = '';
	$scope.responseBoxClass = '';
	$scope.showForm = false;
    $scope.formData = {};

    var setResponse = function(res, success) {
		$rootScope.authenticated = isAuthenticated;
		if (success) {
			$scope.responseBoxClass = 'alert-success';
		} else {
			$scope.responseBoxClass = 'alert-danger';
		}
		$scope.serverResponse = res;
		$scope.serverResponse.data = JSON.stringify(res.data, null, 2);
	};

	$scope.addNewUser = function uriToJSON(urijson){
		return JSON.parse(decodeURIComponent(urijson));
    };

	$scope.processForm = function(email, enabled, firstName, lastName, userName, password, phoneNumber) {
		var vm = this;
		var data = {
			email : email,
			enabled : enabled,
			firstName : firstName,
			lastName : lastName,
			userName : userName,
			password: password,
			phoneNumber : phoneNumber
		};
		$http({
			method  : 'POST',
			url     : 'api/addNewUser',
            dataType: "xml/html/script/json",
			data    : JSON.stringify(data),  // pass in data as strings
			headers : { 'Content-Type': 'application/json' } // set the headers so angular passing info as form data (not request payload)
		})
			.success(function(data) {
				alert("New user added!");
			})
			.error(function() {
				alert("error creating new user");
		});
    };

    $scope.getUser = function() {
        authService.getUser()
            .then(function(response) {
                setResponse(response, true);
            })
            .catch(function(response) {
                setResponse(response, false);
            });
    };

	$scope.showFormFunc = function() {
		$scope.showForm = true
    };

	$scope.getAllUserInfo = function() {
		$http.get('api/user/all')
		.then(function(response) {
			setResponse(response, true);
		})
		.catch(function(response) {
			setResponse(response, false);
		});
	}
}
DashboardCtrl.resolve = {
	isAuthenticated : function($q, $http) {
		var deferred = $q.defer();
		$http({method: 'GET', url: 'auth/refresh'})
		.success(function(data) {
			deferred.resolve(data.access_token !== null);
		})
		.error(function(data){
			deferred.resolve(false); // you could optionally pass error data here
		});
		return deferred.promise;
	}
};

DashboardCtrl.$inject = ['$scope', '$rootScope', '$http', 'AuthService', 'isAuthenticated'];

