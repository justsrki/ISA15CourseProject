/*global angular*/
var appProfileModule = angular.module('app.Profile', []);

appProfileModule.factory('Profile', function ($http) {
    "use strict";

    return {
        getProfile: function () {
            return $http({
                method: 'GET',
                url: 'api/user/profile'
            });
        },
        editProfile: function (firstName, lastName) {
            return $http({
                method: 'PUT',
                url: 'api/user/profile',
                data: {
                    firstName: firstName,
                    lastName: lastName
                }
            });
        },
        follow: function (id) {
            return $http({
                method: 'POST',
                url: 'api/user/friend/' + id
            });
        },
        unfollow: function (id) {
            return $http({
                method: 'DELETE',
                url: 'api/user/friend/' + id
            });
        },
        getFollowing: function () {
            return $http({
                method: 'GET',
                url: 'api/user/friend'
            });
        }
    };

});