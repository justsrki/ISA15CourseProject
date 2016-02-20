/*global angular*/
var appRestaurantManagersCtrlModule = angular.module('app.RestaurantManagersCtrl', []);

appRestaurantManagersCtrlModule.controller('RestaurantManagersCtrl', function ($scope, Restaurant, EMAIL_REGEX) {
    "use strict";

    $scope.alertMessage = null;
    $scope.managers = [];
    $scope.managersHidden = true;

    $scope.showManagers = function (value) {
        $scope.managersHidden = !value;
        $scope.calculateHeight();
    };

    $scope.getManagers = function () {
        Restaurant.getManagers($scope.restaurantId).then(
            function (response) {
                $scope.managers = response.data;
                $scope.calculateHeight();
            },
            function (response) {
                var message = (response.data && response.data.message) ? response.data.message : "";
                $scope.alertMessage = "Status code: " + response.status + " " + message;
            }
        );
    };

    $scope.createManager = function () {
        if (!$scope.firstName) {
            $scope.alertMessage = 'First name cannot be empty.';
        } else if (!$scope.lastName) {
            $scope.alertMessage = 'Last name cannot be empty.';
        } else if (!$scope.email) {
            $scope.alertMessage = 'Email cannot be empty.';
        } else if (!EMAIL_REGEX.test($scope.email)) {
            $scope.alertMessage = 'Email is not valid.';
        } else {
            $scope.alertMessage = null;
        }

        if ($scope.alertMessage) {
            return;
        }

        Restaurant.createManager($scope.restaurantId, $scope.email, $scope.firstName, $scope.lastName).then(
            function () {
                $scope.email = null;
                $scope.firstName = null;
                $scope.lastName = null;
                $scope.getManagers();
            },
            function (response) {
                var message = (response.data && response.data.message) ? response.data.message : "";
                $scope.alertMessage = "Status code: " + response.status + " " + message;
            }
        );
    };

    $scope.calculateHeight = function () {
        if ($scope.managersHidden) {
            $scope.managersHeight = 48;
        } else {
            $scope.managersHeight = 200 + Math.max($scope.managers.length + 1, 2) * 37 + ($scope.alertMessage ? 55 : 0);
        }
    };

    $scope.$watch('alertMessage', function () {
        $scope.calculateHeight();
    });

    $scope.init = function () {
        $scope.getManagers();
        $scope.calculateHeight();
    };

    $scope.init();

});