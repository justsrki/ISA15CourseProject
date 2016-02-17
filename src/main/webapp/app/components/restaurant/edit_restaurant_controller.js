/*global angular, L*/
var appEditRestaurantCtrlModule = angular.module('app.EditRestaurantCtrl', []);

appEditRestaurantCtrlModule.controller('EditRestaurantCtrl', function ($scope, $timeout, $uibModalInstance, Restaurant, MAP_OPTIONS, EMAIL_REGEX) {
    "use strict";

    $scope.tile = "Edit restaurant";
    $scope.marker = null;

    // Administrator
    $scope.managers = [];
    $scope.managersHidden = true;

    $scope.showManagers = function (value) {
        $scope.managersHidden = !value;
        $scope.calculateHeight();
    };

    // Manager
    $scope.calculateHeight = function () {
        if ($scope.managersHidden) {
            $scope.managersHeight = 48;
        } else {
            $scope.managersHeight = 200 + Math.max($scope.managers.length + 1, 2) * 37;
        }
    };

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

        Restaurant.edit($scope.id, $scope.name, $scope.description, latLng.lat, latLng.lng).then(
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

    $scope.getManagers = function () {
        Restaurant.getManagers($scope.id).then(
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

        Restaurant.createManager($scope.id, $scope.email, $scope.firstName, $scope.lastName).then(
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

    $scope.getManagers();
    $scope.calculateHeight();

});