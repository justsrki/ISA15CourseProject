/*global angular, L*/
var appRestaurantMapCtrlModule = angular.module('app.RestaurantMapCtrl', []);

appRestaurantMapCtrlModule.controller('RestaurantMapCtrl', function ($rootScope, $scope, $timeout, MAP_OPTIONS) {
    "use strict";

    $scope.mapHidden = false;
    $scope.marker = null;

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

    // Add map to scope, invalidate
    $scope.initMap = function () {
        $timeout(function () {
            $scope.latlng = $scope.latlng || MAP_OPTIONS.center;
            var map = L.map('MapModal').setView($scope.latlng, MAP_OPTIONS.zoom);

            // Add open street layer
            L.tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                attribution: MAP_OPTIONS.attribution,
                minZoom: MAP_OPTIONS.minZoom,
                maxZoom: MAP_OPTIONS.maxZoom,
                bounds: MAP_OPTIONS.bounds
            }).addTo(map);

            // Add marker to map
            $scope.marker = L.marker(map.getCenter()).addTo(map);
            $scope.setLatLng($scope.marker.getLatLng());
            if ($scope.canEdit) {
                map.on('click', function (e) {
                    $scope.marker.setLatLng(e.latlng);
                    $scope.setLatLng($scope.marker.getLatLng());
                });
            }

            var icon = L.icon({
                iconUrl: 'images/location.png',
                iconSize:     [32, 32],
                iconAnchor:   [16, 16]
            });
            L.marker($rootScope.position, {icon: icon}).addTo(map);
        });
    };

    $scope.calculateHeight();

});