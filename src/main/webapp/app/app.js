/*global angular*/
var app = angular.module('app', ['app.controllers', 'app.services', 'app.directives', 'ngRoute', 'ui.bootstrap']);

app.config(function ($routeProvider, $locationProvider) {
    "use strict";
    $routeProvider
        .when('/login', {
            templateUrl: 'app/components/login/login_view.html',
            controller: 'LoginCtrl'
        })
        .when('/logout', {
            templateUrl: '',
            controller: 'LogoutCtrl'
        })
        .when('/profile', {
            templateUrl: 'app/components/login/profile_view.html',
            controller: 'ProfileCtrl'
        })
        .otherwise('/');
    
    $locationProvider.html5Mode(true);
});

app.run(function ($rootScope, $window) {
    "use strict";
    $rootScope.displayRole = 'login';
    console.log($window.sessionStorage.token);
    if (!$window.sessionStorage.token) {
        $window.sessionStorage.token = "dasdas";
    }
});

