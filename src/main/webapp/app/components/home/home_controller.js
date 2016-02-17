/*global angular*/
var appHomeCtrlModule = angular.module('app.HomeCtrl', []);

appHomeCtrlModule.controller('HomeCtrl', function ($scope, MAP_OPTIONS) {
    "use strict";

    $scope.map = L.map('Map').setView(MAP_OPTIONS.center, MAP_OPTIONS.zoom);
    L.tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="http://mapbox.com">Mapbox</a>',
        minZoom: MAP_OPTIONS.minZoom,
        maxZoom: MAP_OPTIONS.maxZoom,
        bounds: MAP_OPTIONS.bounds
    }).addTo($scope.map);

    $scope.map.on('click', function (e) {
        console.log(e.latlng);
        console.log($scope.map.zoom);
        console.log($scope.map)

    });

});