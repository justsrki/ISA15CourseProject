/*global angular*/
var appTokenModule = angular.module('app.Token', []);

appTokenModule.factory('Token', function ($http) {
    "use strict";
    return {
        info: function (token) {
            return $http({
                method: 'GET',
                url: 'api/token/' + token
            });
        }
    };
});