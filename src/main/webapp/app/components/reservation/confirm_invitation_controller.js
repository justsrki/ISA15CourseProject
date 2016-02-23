/*global angular*/
var appConfirmInvitationCtrlModule = angular.module('app.ConfirmInvitationCtrl', []);

appConfirmInvitationCtrlModule.controller('ConfirmInvitationCtrl', function ($scope, $routeParams, $location, Reservation) {
    "use strict";

    $scope.invitationId = $routeParams.id;
    $scope.restaurantName = null;
    $scope.startDate = null;
    $scope.endDate = null;

    $scope.getReservation = function () {
        Reservation.getInvitationReservation($scope.invitationId).then(
            function (response) {
                $scope.restaurantName = response.data.restaurantName;
                $scope.startDate = response.data.startDate;
                $scope.endDate = response.data.endDate;
            },
            function (response) {
                var message = (response.data && response.data.message) ? response.data.message : "";
                $scope.alertMessage = "Status code: " + response.status + " " + message;
            }
        );
    };

    $scope.confirmInvitation = function (value) {
        Reservation.confirmInvitation($scope.invitationId, value).then(
            function () {
                $location.path("/home");
            },
            function (response) {
                var message = (response.data && response.data.message) ? response.data.message : "";
                $scope.alertMessage = "Status code: " + response.status + " " + message;
            }
        );
    };

    $scope.getReservation();


});