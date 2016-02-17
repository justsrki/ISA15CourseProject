/*global angular*/
var appLogoutCtrlModule = angular.module('app.LogoutCtrl', []);

appLogoutCtrlModule.controller('LogoutCtrl', function ($rootScope, $scope, $location, AccessToken) {
    "use strict";

    $scope.logout = function () {
        AccessToken.clearCredentials();
        $rootScope.display = 'login';
        $location.path('/login');
    };

    $scope.logout();

});