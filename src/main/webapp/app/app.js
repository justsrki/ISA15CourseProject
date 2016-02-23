/*global angular*/
var app = angular.module('app', ['app.controllers', 'app.services', 'app.directives', 'ngRoute', 'ui.bootstrap', 'angularUtils.directives.dirPagination']);

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
            templateUrl: 'app/components/auth/login_view.html',
            controller: 'LoginCtrl'
        })
        .when('/signup', {
            templateUrl: 'app/components/signup/signup_view.html',
            controller: 'SignUpCtrl'
        })
        .when('/logout', {
            templateUrl: 'app/components/auth/login_view.html',
            controller: 'LogoutCtrl'
        })
        .when('/restaurant', {
            templateUrl: 'app/components/restaurant/restaurants_view.html',
            controller: 'RestaurantCtrl'
        })
        .when('/restaurant/:id', {
            templateUrl: 'app/components/reservation/reservation_view.html',
            controller: 'ReservationCtrl'
        })
        .when('/users', {
            templateUrl: 'app/components/users/users_view.html',
            controller: 'UsersCtrl'
        })
        .when('/profile', {
            templateUrl: 'app/components/profile/profile_view.html',
            controller: 'ProfileCtrl'
        })
        .when('/confirm/:id', {
            templateUrl: 'app/components/reservation/confirm_invitation_view.html',
            controller: 'ConfirmInvitationCtrl'
        })
        .when('/reservation/:id', {
            templateUrl: 'app/components/reservation/invitation_view.html',
            controller: 'InvitationCtrl'
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

