/*global angular*/
var appLogsModule = angular.module('app.Logs', []);

appLogsModule.factory('Logs', function ($http) {
    "use strict";

    return {
        getAll: function () {
            return $http({
                method: 'GET',
                url: 'api/log'
            });
        },
        getById: function (id) {
            return $http({
                method: 'GET',
                url: 'api/log/' + id
            });
        }
    };
});