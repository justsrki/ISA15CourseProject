/*global angular*/
var appUsersCtrlModule = angular.module('app.UsersCtrl', []);

appUsersCtrlModule.controller('UsersCtrl', function ($scope, Users) {
    "use strict";

    $scope.alertMessage = null;
    $scope.users = [];

    $scope.getUsers = function () {
        Users.getAll().then(
            function (response) {
                $scope.users = response.data;
            },
            function (response) {
                var message = (response.data && response.data.message) ? response.data.message : "";
                $scope.alertMessage = "Status code: " + response.status + " " + message;
            }
        );
    };

    $scope.getUsers();

});