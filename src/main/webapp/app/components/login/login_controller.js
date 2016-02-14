/*global angular*/
var appLoginCtrlModule = angular.module('app.LoginCtrl', []);

appLoginCtrlModule.controller('LoginCtrl', function ($rootScope, $scope, $location, Login) {
    "use strict";

    $scope.alertMessage = '';

    $scope.login = function () {
        if (!$scope.username) {
            $scope.alertMessage = 'Username cannot be empty!';
        } else if (!$scope.password) {
            $scope.alertMessage = 'Password cannot be empty';
        }

        if ($scope.alertMessage) {
            return;
        }

        Login.login($scope.username, $scope.password)
            .success(function (data, status) {
                $rootScope.displayRole = data.role;
                $location.path('/');
                $scope.alertMessage = null;
            })
            .error(function (data, status) {
                $scope.alertMessage = "Status code: " + status;
            });
    };

    $scope.signup = function () {
        $location.path('/signup');
    }
	
});