/*global angular*/
var appOauth2Module = angular.module('app.Oauth2', []);

appOauth2Module.factory('Oauth2', function ($http) {
    "use strict";
    return {
        login: function (accessToken, provider) {
            return $http({
                method: 'POST',
                url: 'api/oauth2',
                data: {
                    accessToken: accessToken,
                    provider: provider
                }
            });
        }
    };
});