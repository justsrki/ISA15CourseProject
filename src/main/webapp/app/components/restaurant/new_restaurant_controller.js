/*global angular, L*/
var appNewRestaurantCtrlModule = angular.module('app.NewRestaurantCtrl', []);

appNewRestaurantCtrlModule.controller('NewRestaurantCtrl', function ($scope, $timeout, $uibModalInstance, Restaurant, MAP_OPTIONS) {
    "use strict";

    $scope.tile = "Add new restaurant";
    $scope.marker = null;

    $scope.initMap = function () {
        $timeout(function () {
            var map = L.map('MapModal').setView(MAP_OPTIONS.center, MAP_OPTIONS.zoom);

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