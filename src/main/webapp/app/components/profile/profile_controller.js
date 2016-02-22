/*global angular*/
var appProfileCtrlModule = angular.module('app.ProfileCtrl', []);

appProfileCtrlModule.controller('ProfileCtrl', function ($scope, Profile, Users) {
    "use strict";

    $scope.users = null;
    $scope.firstName = null;
    $scope.lastName = null;
    $scope.email = null;

    $scope.getProfile = function () {
        Profile.getProfile().then(
            function (response) {
                $scope.firstName = response.data.firstName;
                $scope.lastName = response.data.lastName;
                $scope.email = response.data.email;
                $scope.alertMessage = null;
            },
            function (response) {
                var message = (response.data && response.data.message) ? response.data.message : "";
                $scope.alertMessage = "Status code: " + response.status + " " + message;
            }
        );
    };

    $scope.saveChanges = function () {
        if (!$scope.firstName) {
            $scope.alertMessage = 'First name cannot be empty.';
        } else if (!$scope.lastName) {
            $scope.alertMessage = 'Last name cannot be empty.';
        } else {
            $scope.alertMessage = null;
        }

        if ($scope.alertMessage) {
            return;
        }

        Profile.editProfile($scope.firstName, $scope.lastName).then(
            function () {
                $scope.alertMessage = null;
                $scope.getProfile();
            },
            function (response) {
                var message = (response.data && response.data.message) ? response.data.message : "";
                $scope.alertMessage = "Status code: " + response.status + " " + message;
            }
        );
    };

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

    $scope.follow = function (id) {
        Profile.follow(id).then(
            function () {
                $scope.getUsers();
                $scope.alertMessage = null;
            },
            function (response) {
                var message = (response.data && response.data.message) ? response.data.message : "";
                $scope.alertMessage = "Status code: " + response.status + " " + message;
            }
        );
    };

    $scope.unfollow = function (id) {
        Profile.unfollow(id).then(
            function () {
                $scope.getUsers();
                $scope.alertMessage = null;
            },
            function (response) {
                var message = (response.data && response.data.message) ? response.data.message : "";
                $scope.alertMessage = "Status code: " + response.status + " " + message;
            }
        );
    };

});