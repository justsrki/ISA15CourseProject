/*global angular*/
var appRestaurantCtrlModule = angular.module('app.RestaurantCtrl', []);

appRestaurantCtrlModule.controller('RestaurantCtrl', function ($scope, $uibModal, Restaurant) {
    "use strict";

    $scope.restaurants = [];

    $scope.init = function () {
        Restaurant.getAll().then(
            function (response) {
                $scope.restaurants = response.data;
            }
        );
    };

    $scope.book = function (id) {

    };

    $scope.edit = function (id) {
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
        scope.id = restaurant.id;
        scope.name = restaurant.name;
        scope.description = restaurant.description;
        scope.latlng = [restaurant.latitude, restaurant.longitude];

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

});