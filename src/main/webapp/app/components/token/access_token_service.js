/*global angular*/
var appAccessTokenModule = angular.module('app.AccessToken', []);

appAccessTokenModule.factory('AccessToken', function ($http, $window) {
    "use strict";
    return {
        info: function (userId, accessToken) {
            userId = userId || this.getUserId();
            accessToken = accessToken || this.getAccessToken();
            return $http({
                method: 'GET',
                url: 'api/accessToken?userId=' + userId + "&accessToken=" + accessToken
            });
        },
        hasCredentials: function () {
            return this.getUserId() && this.getAccessToken();
        },
        setCredentials: function (userId, accessToken) {
            this.setUserId(userId);
            this.setAccessToken(accessToken);
        },
        clearCredentials: function () {
            this.setCredentials(null, null);
        },
        setUserId: function (userId) {
            $window.sessionStorage.userId = userId;
        },
        getUserId: function () {
            return $window.sessionStorage.userId;
        },
        setAccessToken: function (accessToken) {
            $window.sessionStorage.accessToken = accessToken;
        },
        getAccessToken: function () {
            return $window.sessionStorage.accessToken;
        }
    };
});