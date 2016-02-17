/*global angular*/
var appMainCtrlModule = angular.module('app.MainCtrl', []);

appMainCtrlModule.controller('MainCtrl', function ($rootScope, $scope, $location, $route, $window, Token) {
    "use strict";

    $scope.redirectToLogin = function () {
        if ($location.path().lastIndexOf('/login', 0) === 0) {
            $route.reload();
        } else {
            $location.path('/login');
        }
    };

    if ($rootScope.token) {
        Token.info($rootScope.token)
            .success(function () {

            })
            .error(function() {
                $scope.redirectToLogin();
            });
    } else {
        $scope.redirectToLogin();
    }

});