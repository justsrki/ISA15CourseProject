/*global angular*/
var appInvitationCtrlModule = angular.module('app.InvitationCtrl', []);

appInvitationCtrlModule.controller('InvitationCtrl', function ($scope, $routeParams, $location, Profile, Reservation) {
    "use strict";

    $scope.reservationId = $routeParams.id;
    $scope.users = [];

    $scope.getUsers = function () {
        Profile.getFollowing().then(
            function (response) {
                $scope.users = response.data;
                var user;
                for (user in $scope.users) {
                    if ($scope.users.hasOwnProperty(user)) {
                        $scope.users[user].invite = false;
                    }
                }
            },
            function (response) {
                var message = (response.data && response.data.message) ? response.data.message : "";
                $scope.alertMessage = "Status code: " + response.status + " " + message;
            }
        );
    };

    $scope.confirm = function (value) {
        Reservation.confirmInvitation($scope.invitationId, value).then(
            function () {
                $location.path('/home');
            },
            function (response) {
                var message = (response.data && response.data.message) ? response.data.message : "";
                $scope.alertMessage = "Status code: " + response.status + " " + message;
            }
        );
    };

    $scope.invite = function () {
        var user, ids = [];
        for (user in $scope.users) {
            if ($scope.users.hasOwnProperty(user) && $scope.users[user].invite) {
                ids.push($scope.users[user].id);
            }
        }

        Reservation.inviteFriends($scope.reservationId, ids).then(
            function () {
                $scope.goHome();
            }
        );
    };

    $scope.goHome = function () {
        $location.path('/home');
    };

    $scope.predicate = 'email';
    $scope.reverse = false;
    $scope.order = function (predicate) {
        $scope.reverse = ($scope.predicate === predicate) ? !$scope.reverse : false;
        $scope.predicate = predicate;
    };

    $scope.getUsers();

});