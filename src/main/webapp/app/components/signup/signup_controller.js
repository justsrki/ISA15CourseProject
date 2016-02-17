/*global angular*/
var appSignUpCtrlModule = angular.module('app.SignUpCtrl', []);

appSignUpCtrlModule.controller('SignUpCtrl', function ($scope, $location, SignUp, EMAIL_REGEX) {
    "use strict";

    $scope.alertMessage = null;

    $scope.successfulSignUp = function () {
        $scope.alertMessage = null;
        $location.path('/login');
    };

    $scope.signup = function () {
        if (!$scope.firstName) {
            $scope.alertMessage = 'First name cannot be empty.';
        } else if (!$scope.lastName) {
            $scope.alertMessage = 'Last name cannot be empty.';
        } else if (!$scope.email) {
            $scope.alertMessage = 'Email cannot be empty.';
        } else if (!EMAIL_REGEX.test($scope.email)) {
            $scope.alertMessage = 'Email is not valid.';
        }  else if (!$scope.password) {
            $scope.alertMessage = 'Password cannot be empty.';
        } else if ($scope.password !== $scope.renteredPassword) {
            $scope.alertMessage = 'Passwords do not match.';
        } else {
            $scope.alertMessage = null;
        }

        if ($scope.alertMessage) {
            return;
        }

        SignUp.signup($scope.firstName, $scope.lastName, $scope.email, $scope.password)
            .success(function () {
                $scope.successfulSignUp();
            })
            .error(function (data, status) {
                $scope.alertMessage = "Status code: " + status + " " + data.message;
            })

    };
});