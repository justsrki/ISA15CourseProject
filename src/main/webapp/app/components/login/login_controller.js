/*global angular*/
var appLoginCtrlModule = angular.module('app.LoginCtrl', []);

appLoginCtrlModule.controller('LoginCtrl', function ($rootScope, $scope, $location, Login, GOOGLE_LOGIN_DATA) {
    "use strict";

    $scope.alertMessage = null;

    $scope.login = function () {
        if (!$scope.email) {
            $scope.alertMessage = 'Email cannot be empty!';
        } else if (!$scope.password) {
            $scope.alertMessage = 'Password cannot be empty';
        } else {
            $scope.alertMessage = null;
        }

        if ($scope.alertMessage) {
            return;
        }

        Login.login($scope.email, $scope.password)
            .success(function (data, status) {
                $rootScope.displayRole = data.role;
                $location.path('/');
                $scope.alertMessage = null;
            })
            .error(function (data, status) {
                $scope.alertMessage = "Status code: " + status;
            });
    };

    $scope.signup = function () {
        $location.path('/signup');
    };


    // Create Google plus login
    $scope.renderGoogleSignInButton = function() {
        gapi.signin2.render('GoogleSignInButton',
            {
                scope: 'https://www.googleapis.com/auth/plus.login',
                width: 200,
                height: 40,
                longtitle: true,
                theme: 'dark',
                onsuccess: function (googleUser) {
                    console.log(googleUser);
                },
                onfailure: function (error) {
                    console.log(error);
                }
            }
        );
    };



    $scope.facebookLogin = function () {
        FB.login(function(response){
            console.log(response);
            // Handle the response object, like in statusChangeCallback() in our demo
            // code.
        });
    };

    $scope.start = function() {
        $scope.renderGoogleSignInButton();
    };

    $scope.start();
});