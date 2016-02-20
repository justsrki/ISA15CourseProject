/*global angular*/
var appMainCtrlModule = angular.module('app.MainCtrl', []);

appMainCtrlModule.controller('MainCtrl', function ($rootScope, $scope, $location, $route, $window, AccessToken) {
    "use strict";

    $scope.redirectToLogin = function () {
        if ($location.path().lastIndexOf('/login', 0) === 0) {
            $route.reload();
        } else {
            $location.path('/login');
        }
    };

    if (AccessToken.hasCredentials()) {
        AccessToken.info($rootScope.accessToken).then(function (response) {
            var data = response.data;
            AccessToken.setCredentials(data.userId, data.accessToken);
            $rootScope.display = data.role;
            $route.reload();
        },
            function () {
                $scope.redirectToLogin();
            });
    } else {
        $scope.redirectToLogin();
    }

});