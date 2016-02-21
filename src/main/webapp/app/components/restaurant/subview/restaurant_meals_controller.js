/*global angular*/
var appRestaurantMealsCtrlModule = angular.module('app.RestaurantMealsCtrl', []);

appRestaurantMealsCtrlModule.controller('RestaurantMealsCtrl', function ($scope, Restaurant) {
    "use strict";

    $scope.alertMessage = null;
    $scope.meals = [];
    $scope.mealsHidden = true;
    $scope.editMealId = null;

    $scope.name = null;
    $scope.description = null;
    $scope.price = null;

    $scope.showMeals = function (value) {
        $scope.mealsHidden = !value;
        $scope.calculateHeight();
    };

    $scope.getMeals = function () {
        Restaurant.getAllMeals($scope.restaurantId).then(
            function (response) {
                $scope.meals = response.data;
                $scope.calculateHeight();
            },
            function (response) {
                var message = (response.data && response.data.message) ? response.data.message : "";
                $scope.alertMessage = "Status code: " + response.status + " " + message;
            }
        );
    };

    $scope.createMeal = function () {
        if (!$scope.name) {
            $scope.alertMessage = 'Name cannot be empty.';
        } else if (!$scope.price) {
            $scope.alertMessage = 'Price cannot be empty.';
        } else if (!(parseFloat($scope.price) > 0)) {
            $scope.alertMessage = 'Price is not valid.';
        } else {
            $scope.alertMessage = null;
            $scope.price = parseFloat($scope.price);
        }

        if ($scope.alertMessage) {
            return;
        }

        Restaurant.createMeal($scope.restaurantId, $scope.name, $scope.description, $scope.price).then(
            function () {
                $scope.name = null;
                $scope.description = null;
                $scope.price = null;
                $scope.getMeals();
            },
            function (response) {
                var message = (response.data && response.data.message) ? response.data.message : "";
                $scope.alertMessage = "Status code: " + response.status + " " + message;
            }
        );
    };

    $scope.initEditMeal = function (meal) {
        $scope.editMealId = meal.id;
        $scope.name = meal.name;
        $scope.description = meal.description;
        $scope.price = meal.price;
    };

    $scope.editMeal = function () {
        if (!$scope.name) {
            $scope.alertMessage = 'Name cannot be empty.';
        } else if (!$scope.price) {
            $scope.alertMessage = 'Price cannot be empty.';
        } else if (!(parseFloat($scope.price) > 0)) {
            $scope.alertMessage = 'Price is not valid.';
        } else {
            $scope.alertMessage = null;
            $scope.price = parseFloat($scope.price);
        }

        if ($scope.alertMessage) {
            return;
        }

        Restaurant.editMeal($scope.editMealId, $scope.name, $scope.description, $scope.price).then(
            function () {
                $scope.editMealId = null;
                $scope.name = null;
                $scope.description = null;
                $scope.price = null;
                $scope.getMeals();
            },
            function (response) {
                var message = (response.data && response.data.message) ? response.data.message : "";
                $scope.alertMessage = "Status code: " + response.status + " " + message;
            }
        );
    };

    $scope.deleteMeal = function (id) {
        if (id === $scope.editMealId) {
            $scope.editMealId = null;
        }

        Restaurant.deleteMeal(id).then(
            function () {
                $scope.getMeals();
            },
            function (response) {
                var message = (response.data && response.data.message) ? response.data.message : "";
                $scope.alertMessage = "Status code: " + response.status + " " + message;
            }
        );
    };

    $scope.calculateHeight = function () {
        if ($scope.mealsHidden) {
            $scope.mealsHeight = 48;
        } else {
            $scope.mealsHeight = ($scope.canEdit ? 200 : 0) +
                Math.max($scope.meals.length + 1, 2) * 51 + ($scope.alertMessage ? 55 : 0);
        }
    };

    $scope.$watch('alertMessage', function () {
        $scope.calculateHeight();
    });

    $scope.init = function () {
        $scope.getMeals();
        $scope.calculateHeight();
    };

    $scope.init();

});