/*global angular*/
var appUsersModule = angular.module('app.Users', []);

appUsersModule.factory('Users', function ($http) {
    "use strict";

    return {
        getAll: function () {
            return $http({
                method: 'GET',
                url: 'api/user'
            });
        },
        getById: function (id) {
            return $http({
                method: 'GET',
                url: 'api/user/' + id
            });
        }
    };

});