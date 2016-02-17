/*global angular*/
var appRestaurantCtrlModule = angular.module('app.RestaurantCtrl', []);

appRestaurantCtrlModule.controller('RestaurantCtrl', function ($scope, $uibModal) {
    "use strict";

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

});