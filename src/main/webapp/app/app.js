/*global angular*/
var app = angular.module('app', ['app.controllers', 'app.services', 'app.directives', 'ngRoute', 'ui.bootstrap']);

app.config(function ($routeProvider, $locationProvider, $httpProvider) {
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
        .when('/signup', {
            templateUrl: 'app/components/signup/signup_view.html',
            controller: 'SignUpCtrl'
        })
        .when('/logout', {
            templateUrl: 'app/components/login/login_view.html',
            controller: 'LogoutCtrl'
        })
        .when('/restaurant', {
            templateUrl: 'app/components/restaurant/restaurant_view.html',
            controller: 'RestaurantCtrl'
        })
        .when('/profile', {
            templateUrl: 'app/components/profile/profile_view.html',
            controller: 'ProfileCtrl'
        })
        .otherwise('/');

    $locationProvider.html5Mode(true);

    // Add token to each request
    $httpProvider.interceptors.push('authHttpRequestInterceptor');
});

app.run(function ($rootScope) {
    "use strict";
    $rootScope.display = 'login';
});

