/*global angular, L*/
var appHomeCtrlModule = angular.module('app.HomeCtrl', []);

appHomeCtrlModule.controller('HomeCtrl', function ($rootScope, $scope, Restaurant, MAP_OPTIONS) {
    "use strict";

    $scope.map = L.map('Map').setView(MAP_OPTIONS.center, MAP_OPTIONS.zoom);
    L.tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: MAP_OPTIONS.attribution,
        minZoom: MAP_OPTIONS.minZoom,
        maxZoom: MAP_OPTIONS.maxZoom,
        bounds: MAP_OPTIONS.bounds
    }).addTo($scope.map);

    $scope.restaurants = [];

    $scope.getRestaurants = function () {
        Restaurant.getAll().then(
            function (response) {
                $scope.restaurants = response.data;
                $scope.showRestaurants();
            }
        );
    };

    $scope.showRestaurants = function () {
        var i, restaurant, marker;
        for (i in $scope.restaurants) {
            if ($scope.restaurants.hasOwnProperty(i)) {
                restaurant = $scope.restaurants[i];
                marker = L.marker([restaurant.latitude, restaurant.longitude]);
                marker.bindPopup('<b>' + restaurant.name + '</b>');
                marker.addTo($scope.map);
            }
        }
    };

    $scope.init = function () {
        $scope.getRestaurants();
    };

    $scope.init();


    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            $rootScope.position = [position.coords.latitude, position.coords.longitude];
            var icon = L.icon({
                iconUrl: 'images/location.png',
                iconSize:     [32, 32],
                iconAnchor:   [16, 16]
            });
            L.marker($rootScope.position, {icon: icon}).addTo($scope.map);
        });
    }

});