/*global angular, L*/
var appRestaurantCtrlModule = angular.module('app.RestaurantCtrl', []);

appRestaurantCtrlModule.controller('RestaurantCtrl', function ($rootScope, $scope, $uibModal, $location, Restaurant) {
    "use strict";

    $scope.restaurants = [];

    $scope.init = function () {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function (position) {
                $rootScope.position = [position.coords.latitude, position.coords.longitude];
                if ($scope.restaurants.length !== 0) {
                    var restaurant, latLng1, latLng2;
                    for (restaurant in $scope.restaurants) {
                        if ($scope.restaurants.hasOwnProperty(restaurant)) {
                            latLng1 = L.latLng($rootScope.position[0], $rootScope.position[1]);
                            latLng2 = L.latLng($scope.restaurants[restaurant].latitude, $scope.restaurants[restaurant].longitude);
                            $scope.restaurants[restaurant].distance = latLng1.distanceTo(latLng2);
                        }
                    }
                }
            });
        }

        Restaurant.getAll().then(
            function (response) {
                $scope.restaurants = response.data;
                var restaurant, latLng1, latLng2;
                for (restaurant in $scope.restaurants) {
                    if ($scope.restaurants.hasOwnProperty(restaurant)) {
                        latLng1 = L.latLng($rootScope.position[0], $rootScope.position[1]);
                        latLng2 = L.latLng($scope.restaurants[restaurant].latitude, $scope.restaurants[restaurant].longitude);
                        $scope.restaurants[restaurant].distance = latLng1.distanceTo(latLng2);
                    }
                }
            },
            function (response) {
                var message = (response.data && response.data.message) ? response.data.message : "";
                $scope.alertMessage = "Status code: " + response.status + " " + message;
            }
        );
    };

    $scope.book = function (id) {
        $location.path('/restaurant/' + id);
    };

    $scope.open = function (id, canEdit) {
        var restaurant, modal, i, scope;

        for (i in $scope.restaurants) {
            if ($scope.restaurants.hasOwnProperty(i)) {
                if ($scope.restaurants[i].id === id) {
                    restaurant = $scope.restaurants[i];
                    break;
                }
            }
        }

        scope = $scope.$new(true);
        scope.restaurantId = restaurant.id;
        scope.name = restaurant.name;
        scope.description = restaurant.description;
        scope.latlng = [restaurant.latitude, restaurant.longitude];
        scope.display = $rootScope.display;
        scope.canEdit = canEdit;

        modal = $uibModal.open({
            animation: true,
            templateUrl: 'app/components/restaurant/restaurant_modal_view.html',
            controller: 'EditRestaurantCtrl',
            size: 'lg',
            scope: scope
        });

        modal.result.then(function (refresh) {
            if (refresh) {
                $scope.init();
            }
        });
    };

    $scope.add = function () {
        var modal = $uibModal.open({
            animation: true,
            templateUrl: 'app/components/restaurant/restaurant_modal_view.html',
            controller: 'NewRestaurantCtrl',
            size: 'lg'
        });

        modal.result.then(function (refresh) {
            if (refresh) {
                $scope.init();
            }
        });
    };

    $scope.init();

    $scope.predicate = 'distance';
    $scope.reverse = false;
    $scope.order = function (predicate) {
        $scope.reverse = ($scope.predicate === predicate) ? !$scope.reverse : false;
        $scope.predicate = predicate;
    };

});