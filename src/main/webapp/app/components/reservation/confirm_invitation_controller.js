/*global angular*/
var appConfirmInvitationCtrlModule = angular.module('app.ConfirmInvitationCtrl', []);

appConfirmInvitationCtrlModule.controller('ConfirmInvitationCtrl', function ($scope, $routeParams, Reservation) {
    "use strict";

    $scope.invitationId = $routeParams.id;
    $scope.restaurantName = null;
    $scope.startDate = null;
    $scope.endDate = null;

    $scope.getReservation = function () {
        Reservation.getReservation($scope.invitationId).then(
            function (response) {
                $scope.restaurantName = response.data.restaurantId;
                $scope.startDate = response.data.startDate;
                $scope.endDate = response.data.endDate;
            },
            function (response) {
                var message = (response.data && response.data.message) ? response.data.message : "";
                $scope.alertMessage = "Status code: " + response.status + " " + message;
            }
        );
    };

    $scope.confirm = function (value) {

    };


});