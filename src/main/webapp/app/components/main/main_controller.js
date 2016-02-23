/*global angular*/
var appMainCtrlModule = angular.module('app.MainCtrl', []);

appMainCtrlModule.controller('MainCtrl', function ($rootScope, $scope, $location, $route, AccessToken) {
    "use strict";

    if ($location.search().token && $location.search().userId) {
        AccessToken.setCredentials($location.search().userId, $location.search().token);
    }

    if ($location.search().redirect) {
        $scope.redirect = $location.search().redirect;
    }


    $scope.redirectToLogin = function () {
        if ($location.path().lastIndexOf('/login', 0) === 0) {
            $route.reload();
        } else {
            $location.path('/login');
        }
    };

    if (AccessToken.hasCredentials()) {
        AccessToken.info($rootScope.accessToken).then(
            function (response) {
                var data = response.data;
                AccessToken.setCredentials(data.userId, data.accessToken);
                $rootScope.display = data.role;
                if ($scope.redirect) {
                    $location.path($scope.redirect);
                } else {
                    $route.reload();
                }
            },
            function () {
                $scope.redirectToLogin();
            }
        );
    } else {
        $scope.redirectToLogin();
    }

});