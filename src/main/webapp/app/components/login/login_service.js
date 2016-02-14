/*global angular*/
var appLoginModule = angular.module('app.Login', []);

appLoginModule.factory('Login', function ($http) {
    "use strict";

    return {
        login: function (email, password) {
            return $http({
                method: 'POST',
                url: 'api/user/login',
                data: {
                    email: email,
                    password: password
                }
            });
        }
    };

});