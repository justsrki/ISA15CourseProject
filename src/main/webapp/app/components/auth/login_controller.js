/*global angular*/
var appLoginCtrlModule = angular.module('app.LoginCtrl', []);

appLoginCtrlModule.controller('LoginCtrl', function ($rootScope, $scope, $location, Login, Oauth2, AccessToken, EMAIL_REGEX) {
    "use strict";

    if ($location.search().email) {
        $scope.email = $location.search().email;
    }

    $scope.alertMessage = null;

    $scope.successfulLogin = function (data) {
        AccessToken.setCredentials(data.userId, data.accessToken);
        $rootScope.display = data.role;
        $scope.alertMessage = null;
        $location.path('/');
    };

    $scope.login = function () {
        if (!$scope.email) {
            $scope.alertMessage = 'Email cannot be empty.';
        } else if (!EMAIL_REGEX.test($scope.email)) {
            $scope.alertMessage = 'Email is not valid.';
        } else if (!$scope.password) {
            $scope.alertMessage = 'Password cannot be empty.';
        } else {
            $scope.alertMessage = null;
        }

        if ($scope.alertMessage) {
            return;
        }

        Login.login($scope.email, $scope.password).then(
            function (response) {
                $scope.successfulLogin(response.data);
            },
            function (response) {
                var message = (response.data && response.data.message) ? response.data.message : "";
                $scope.alertMessage = "Status code: " + response.status + " " + message;
            }
        );
    };

    $scope.signup = function () {
        $location.path('/signup');
    };

    $scope.loginOAuth2 = function (accessToken, provider) {
        Oauth2.login(accessToken, provider).then(
            function (response) {
                $scope.successfulLogin(response.data);
            },
            function (response) {
                var message = (response.data && response.data.message) ? response.data.message : "";
                $scope.alertMessage = "Status code: " + response.status + " " + message;
            });
    };

    // Create Google plus login
    $scope.renderGoogleSignInButton = function () {
        gapi.signin2.render('GoogleSignInButton',
            {
                scope: 'https://www.googleapis.com/auth/plus.login',
                width: 200,
                height: 40,
                longtitle: true,
                theme: 'dark',
                onsuccess: function (response) {
                    $scope.loginOAuth2(response.getAuthResponse().access_token, 'google_plus')
                },
                onfailure: function (error) {
                    $scope.alertMessage = "Cannot login with Google Plus account."
                }
            }
        );
    };

    // Facebook login
    $scope.facebookLogin = function () {
        FB.login(function (response) {
            if (response.authResponse) {
                $scope.loginOAuth2(response.authResponse.accessToken, 'facebook');
            } else {
                $scope.alertMessage = "Cannot login with Facebook account."
            }
        }, {
            scope: 'public_profile,email'
        });
    };

    $scope.renderGoogleSignInButton();
});