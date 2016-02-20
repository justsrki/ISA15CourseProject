/*global angular, L*/
var appEditRestaurantCtrlModule = angular.module('app.EditRestaurantCtrl', []);

appEditRestaurantCtrlModule.controller('EditRestaurantCtrl', function ($scope, $timeout, $uibModalInstance, Restaurant, MAP_OPTIONS) {
    "use strict";

    $scope.tile = "Edit restaurant";
    $scope.marker = null;

    /*jslint todo: true */
    // TODO: Make 3 controllers

    $scope.mapHidden = false;
    $scope.showMap = function (value) {
        $scope.mapHidden = !value;
        $scope.calculateHeight();
    };

    $scope.calculateHeight = function () {
        if ($scope.mapHidden) {
            $scope.mapHeight = 48;
        } else {
            $scope.mapHeight = 300;
        }
    };


    $scope.close = function (refresh) {
        refresh = refresh || false;
        $uibModalInstance.close(refresh);
    };

    $scope.save = function () {
        var latLng = $scope.marker.getLatLng();
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

    // Add map to scope, invalidate
    $scope.initMap = function () {
        $timeout(function () {
            var map = L.map('MapModal').setView($scope.latlng, MAP_OPTIONS.zoom);

            // Add open street layer
            L.tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                attribution: MAP_OPTIONS.attribution,
                minZoom: MAP_OPTIONS.minZoom,
                maxZoom: MAP_OPTIONS.maxZoom,
                bounds: MAP_OPTIONS.bounds
            }).addTo(map);

            // Add marker to map
            $scope.marker = L.marker(map.getCenter(), {draggable: true}).addTo(map);
            map.on('click', function (e) {
                $scope.marker.setLatLng(e.latlng);
            });
        });
    };

    $scope.calculateHeight();

});