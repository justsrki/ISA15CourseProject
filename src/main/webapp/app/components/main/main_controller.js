/*global angular*/
var appMainCtrlModule = angular.module('app.MainCtrl', []);

appMainCtrlModule.controller('MainCtrl', function ($rootScope, $scope, $location, Token) {
    "use strict";

    $scope.redirectToLogin = function () {
        $location.path('/');
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