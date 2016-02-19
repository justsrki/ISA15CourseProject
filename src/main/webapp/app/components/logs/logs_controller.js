/*global angular*/
var appLogsCtrlModule = angular.module('app.LogsCtrl', []);

appLogsCtrlModule.controller('LogsCtrl', function ($scope, Logs) {
    "use strict";

    $scope.alertMessage = null;
    $scope.logs = [];

    $scope.getLogs = function () {
        Logs.getAll().then(
            function (response) {
                $scope.logs = response.data;
            },
            function (response) {
                var message = (response.data && response.data.message) ? response.data.message : "";
                $scope.alertMessage = "Status code: " + response.status + " " + message;
            }
        );
    };

    $scope.getLogs();
});