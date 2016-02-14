/*global angular*/
var app = angular.module('app', ['app.controllers', 'app.services', 'app.directives', 'ngRoute', 'ui.bootstrap']);

app.config(function ($routeProvider, $locationProvider) {
    "use strict";
    $routeProvider.otherwise('/furniture');
    
    $locationProvider.html5Mode(true);
});

app.run(function ($rootScope) {
    "use strict";
});

