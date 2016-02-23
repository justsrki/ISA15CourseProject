/*global angular*/
var appReservationCtrlModule = angular.module('app.ReservationCtrl', []);

appReservationCtrlModule.controller('ReservationCtrl', function ($scope, $routeParams, $location, Reservation) {
    "use strict";
    $scope.restaurantId = $routeParams.id;

    $scope.init = function () {
        $scope.alertMessage = null;

        $scope.startDate = new Date();
        $scope.minDate = new Date();
        $scope.startTime = new Date();
        $scope.startTime.setHours(17);
        $scope.startTime.setMinutes(0);
        $scope.length = null;
        $scope.disabled = false;

        $scope.tables = [];
        $scope.rows = null;
        $scope.columns = null;
    };

    $scope.show = function () {
        var length, startDate = new Date($scope.startDate);
        startDate.setHours($scope.startTime.getHours());
        startDate.setMinutes($scope.startTime.getMinutes());
        startDate.setSeconds(0);
        startDate.setMilliseconds(0);

        if ($scope.minDate.getTime() > startDate.getTime()) {
            $scope.alertMessage = "Date cannot be in the past.";
        } else if (!(parseInt($scope.length, 10) > 0)) {
            $scope.alertMessage = "Length must be positive number.";
        } else {
            length = parseInt($scope.length, 0);
            $scope.alertMessage = null;
        }

        if ($scope.alertMessage) {
            return;
        }

        Reservation.getTables($scope.restaurantId, startDate, length).then(
            function (response) {
                $scope.rows = response.data.rows;
                $scope.columns = response.data.columns;
                $scope.tables = response.data.tables;
                $scope.disabled = true;
            },
            function (response) {
                var message = (response.data && response.data.message) ? response.data.message : "";
                $scope.alertMessage = "Status code: " + response.status + " " + message;
            }
        );
    };

    $scope.toggle = function (row, column) {
        if ($scope.tables[row][column].type === 'table') {
            $scope.tables[row][column].type = 'selected';
        } else if ($scope.tables[row][column].type === 'selected') {
            $scope.tables[row][column].type = 'table';
        }
    };

    $scope.getSelectedTables = function () {
        var selected = [], i, j;
        for (i = 0; i < $scope.rows; i += 1) {
            for (j = 0; j < $scope.columns; j += 1) {
                if ($scope.tables[i][j].type === 'selected') {
                    selected.push($scope.tables[i][j].id);
                }
            }
        }
        return selected;
    };

    $scope.createReservation = function () {
        var selected = $scope.getSelectedTables(),
            length = parseInt($scope.length, 0),
            startDate = new Date($scope.startDate);

        startDate.setHours($scope.startTime.getHours());
        startDate.setMinutes($scope.startTime.getMinutes());
        startDate.setSeconds(0);
        startDate.setMilliseconds(0);


        if ($scope.minDate.getTime() > startDate.getTime()) {
            $scope.alertMessage = "Date cannot be in the past.";
        } else if (!(length > 0)) {
            $scope.alertMessage = "Length must be positive number.";
        } else if (selected.length === 0) {
            $scope.alertMessage = "You must select al least 1 table.";
        } else {
            $scope.alertMessage = null;
        }

        if ($scope.alertMessage) {
            return;
        }

        Reservation.createReservation($scope.restaurantId, startDate, length, selected).then(
            function (response) {
                $location.path('/reservation/' + response.data.id);
            },
            function (response) {
                var message = (response.data && response.data.message) ? response.data.message : "";
                $scope.alertMessage = "Status code: " + response.status + " " + message;
            }
        );
    };

    $scope.resetDate = function () {
        $scope.init();
    };

    $scope.init();

});