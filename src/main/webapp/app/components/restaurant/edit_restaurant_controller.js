/*global angular*/
var appEditRestaurantCtrlModule = angular.module('app.EditRestaurantCtrl', []);

appEditRestaurantCtrlModule.controller('EditRestaurantCtrl', function ($scope, $uibModalInstance, Restaurant) {
    "use strict";

    $scope.tile = "Edit restaurant";
    $scope.latLng = null;

    $scope.close = function (refresh) {
        refresh = refresh || false;
        $uibModalInstance.close(refresh);
    };

    $scope.setLatLng = function (latLng) {
        $scope.latLng = latLng;
    };

    $scope.save = function () {
        if (!$scope.name) {
            $scope.alertMessage = "Name cannot be empty.";
        } else if (!$scope.latLng) {
            $scope.alertMessage = "You must select location.";
        } else {
            $scope.alertMessage = null;
        }

        if ($scope.alertMessage) {
            return;
        }

        var latLng = $scope.latLng;
        Restaurant.edit($scope.restaurantId, $scope.name, $scope.description, latLng.lat, latLng.lng).then(
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