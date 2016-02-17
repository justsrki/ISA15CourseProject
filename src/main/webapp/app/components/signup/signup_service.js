/*global angular*/
var appSignUpModule = angular.module('app.SignUp', []);

appSignUpModule.factory('SignUp', function ($http) {
    "use strict";
    return {
        signup: function (firstName, lastName, email, password) {
            return $http({
                method: 'POST',
                url: 'api/signup',
                data: {
                    firstName: firstName,
                    lastName: lastName,
                    email: email,
                    password: password
                }
            });
        }
    };
});