/*global angular, L*/
var appNewRestaurantCtrlModule = angular.module('app.NewRestaurantCtrl', []);

appNewRestaurantCtrlModule.controller('NewRestaurantCtrl', function ($scope, $timeout, $uibModalInstance, Restaurant, MAP_OPTIONS) {
    "use strict";

    $scope.tile = "Add new restaurant";
    $scope.marker = null;
    $scope.latLng = null;
    $scope.canEdit = true;

    $scope.setLatLng = function (latLng) {
        $scope.latLng = latLng;
    };

    $scope.close = function (refresh) {
        refresh = refresh || false;
        $uibModalInstance.close(refresh);
    };

    $scope.save = function () {
        var latLng = $scope.latLng;
        if (!$scope.name) {
            $scope.alertMessage = "Name cannot be empty.";
        } else if (!latLng) {
            $scope.alertMessage = "You must select location.";
        } else {
            $scope.alertMessage = null;
        }

        if ($scope.alertMessage) {
            return;
        }

        Restaurant.create($scope.name, $scope.description, latLng.lat, latLng.lng).then(
            function () {
                $scope.alertMessage = null;
                $scope.close(true);
            },
            function (response) {
                var message = (response.data && response.data.message) ? response.data.message : "";
                $scope.alertMessage = "Status code: " + response.status + " " + message;
            }
        );
    };
});