/*global angular*/
var app = angular.module('app', ['app.controllers', 'app.services', 'app.directives', 'ngRoute', 'ui.bootstrap']);

app.config(function ($routeProvider, $locationProvider) {
    "use strict";
    $routeProvider
        .when('/', {
            redirectTo: '/home'
        })
        .when('/home', {
            templateUrl: 'app/components/home/home_view.html',
            controller: 'HomeCtrl'
        })
        .when('/login', {
            templateUrl: 'app/components/login/login_view.html',
            controller: 'LoginCtrl'
        })
        .when('/login/:email', {
            templateUrl: 'app/components/login/login_view.html',
            controller: 'LoginCtrl'
        })
        .when('/signup', {
            templateUrl: 'app/components/signup/signup_view.html',
            controller: 'SignUpCtrl'
        })
        .when('/logout', {
            templateUrl: '',
            controller: 'LogoutCtrl'
        })
        .when('/profile', {
            templateUrl: 'app/components/profile/profile_view.html',
            controller: 'ProfileCtrl'
        })
        .otherwise('/');

    $locationProvider.html5Mode(true);
});

app.run(function ($rootScope, $window) {
    "use strict";
    $rootScope.display = 'login';
    if ($window.sessionStorage.token) {
        $rootScope.accessToken = $window.sessionStorage.token;
    }
});

