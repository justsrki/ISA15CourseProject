/*global angular*/
var appRestaurantModule = angular.module('app.Restaurant', []);

appRestaurantModule.factory('Restaurant', function ($http) {
    "use strict";
    return {
        getAll: function () {
            return $http({
                method: 'GET',
                url: 'api/restaurant'
            });
        },
        getById: function (id) {
            return $http({
                method: 'GET',
                url: 'api/restaurant/' + id
            });
        },
        create: function (name, description, latitude, longitude) {
            return $http({
                method: 'POST',
                url: 'api/restaurant',
                data: {
                    name: name,
                    description: description,
                    latitude: latitude,
                    longitude: longitude
                }
            });
        },
        edit: function (id, name, description, latitude, longitude) {
            return $http({
                method: 'PUT',
                url: 'api/restaurant/' + id,
                data: {
                    name: name,
                    description: description,
                    latitude: latitude,
                    longitude: longitude
                }
            });
        },
        getManagers: function (id) {
            return $http({
                method: 'GET',
                url: 'api/restaurant/' + id + '/manager'
            });
        },
        createManager: function (id, email, firstName, lastName) {
            return $http({
                method: 'POST',
                url: 'api/restaurant/' + id + '/manager',
                data: {
                    email: email,
                    firstName: firstName,
                    lastName: lastName
                }
            });
        }
    };
});