/*global angular*/
var appTokenModule = angular.module('app.Token', []);

appTokenModule.factory('Token', function ($http, $window) {
    "use strict";
    return {
        info: function (token) {
            return $http({
                method: 'GET',
                url: 'api/token/' + token
            });
        },
        storeToken: function (token) {
            $window.sessionStorage.token = token;
        },
        getToken: function () {
            return $window.sessionStorage.token;
        }
    };
});