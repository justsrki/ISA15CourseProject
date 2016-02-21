/*global angular*/
var appRestaurantTablesCtrlModule = angular.module('app.RestaurantTablesCtrl', []);

appRestaurantTablesCtrlModule.controller('RestaurantTablesCtrl', function ($scope, Restaurant) {
    "use strict";

    $scope.alertMessage = null;
    $scope.tables = [];
    $scope.tablesHidden = true;
    $scope.editable = false;
    $scope.rows = null;
    $scope.columns = null;

    $scope.$watch('[rows, columns]', function () {
        if ($scope.editable) {
            var rows = parseInt($scope.rows, 10),
                columns = parseInt($scope.columns, 10),
                row,
                column,
                tables = new Array(rows);

            for (row = 0; row < rows; row += 1) {
                tables[row] = new Array(columns);
                for (column = 0; column < columns; column += 1) {
                    tables[row][column] = {
                        label: row * columns + column + 1,
                        row: row,
                        column: column,
                        type: 'table'
                    };
                }
            }
            $scope.tables = tables;
            $scope.calculateHeight();
        }
    });

    $scope.changeState = function (i, j) {
        if ($scope.editable && $scope.canEdit) {
            $scope.tables[i][j].type = $scope.tables[i][j].type === 'table' ? 'no_table' : 'table';
        }
    };

    $scope.showTables = function (value) {
        $scope.tablesHidden = !value;
        $scope.calculateHeight();
    };

    $scope.getTables = function () {
        Restaurant.getAllTables($scope.restaurantId).then(
            function (response) {
                $scope.tables = response.data.tables;
                if ($scope.tables.length === 0) {
                    $scope.editable = true;
                    $scope.rows = 0;
                    $scope.columns = 0;
                } else {
                    $scope.rows = response.data.rows;
                    $scope.columns = response.data.columns;
                }
            },
            function (response) {
                var message = (response.data && response.data.message) ? response.data.message : "";
                $scope.alertMessage = "Status code: " + response.status + " " + message;
            }
        );
    };

    $scope.createConfiguration = function () {
        if (!$scope.rows) {
            $scope.alertMessage = 'Rows value cannot be empty.';
        } else if (!(parseInt($scope.rows, 10) > 0)) {
            $scope.alertMessage = 'Rows value is not valid.';
        } else if (!$scope.columns) {
            $scope.alertMessage = 'Columns value cannot be empty.';
        } else if (!(parseInt($scope.columns, 10) > 0)) {
            $scope.alertMessage = 'Columns value is not valid.';
        } else {
            $scope.rows = parseInt($scope.rows, 10);
            $scope.columns = parseInt($scope.columns, 10);
            $scope.alertMessage = null;
        }

        if ($scope.alertMessage) {
            return;
        }

        Restaurant.createTables($scope.restaurantId, $scope.rows, $scope.columns, $scope.tables).then(
            function () {
                $scope.editable = false;
                $scope.getTables();
            },
            function (response) {
                var message = (response.data && response.data.message) ? response.data.message : "";
                $scope.alertMessage = "Status code: " + response.status + " " + message;
            }
        );

    };

    $scope.calculateHeight = function () {
        if ($scope.tablesHidden) {
            $scope.tablesHeight = 48;
        } else {
            $scope.tablesHeight = ($scope.editable && $scope.canEdit ? 110 : 0) +
                Math.max($scope.tables.length + 1, 2) * 37 + ($scope.alertMessage ? 55 : 0);
        }
    };

    $scope.$watch('alertMessage', function () {
        $scope.calculateHeight();
    });

    $scope.init = function () {
        $scope.getTables();
        $scope.calculateHeight();
    };

    $scope.init();

});