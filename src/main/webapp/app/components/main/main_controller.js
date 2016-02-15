/*global angular*/
var appMainCtrlModule = angular.module('app.MainCtrl', []);

appMainCtrlModule.controller('MainCtrl', function ($rootScope, $scope, $location, $route, Token) {
    "use strict";

    $scope.redirectToLogin = function () {
        if ('/login' == $location.path()) {
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