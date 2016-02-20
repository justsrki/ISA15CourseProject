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
        createManager: function (restaurantId, email, firstName, lastName) {
            return $http({
                method: 'POST',
                url: 'api/restaurant/' + restaurantId + '/manager',
                data: {
                    email: email,
                    firstName: firstName,
                    lastName: lastName
                }
            });
        },
        getAllMeals: function (id) {
            return $http({
                method: 'GET',
                url: 'api/restaurant/' + id + '/meal'
            });
        },
        getMeal: function (id) {
            return $http({
                method: 'GET',
                url: 'api/restaurant/meal/' + id
            });
        },
        createMeal: function (restaurantId, name, description, price) {
            return $http({
                method: 'POST',
                url: 'api/restaurant/' + restaurantId + '/meal',
                data: {
                    name: name,
                    description: description,
                    price: price
                }
            });
        },
        editMeal: function (id, name, description, price) {
            return $http({
                method: 'PUT',
                url: 'api/restaurant/meal/' + id,
                data: {
                    name: name,
                    description: description,
                    price: price
                }
            });
        },
        deleteMeal: function (id) {
            return $http({
                method: 'DELETE',
                url: 'api/restaurant/meal/' + id
            });
        },
        getAllTables: function (restaurantId) {
            return $http({
                method: 'GET',
                url: 'api/restaurant/' + restaurantId + '/table'
            });
        },
        createTables: function (restaurantId, rows, columns, tables) {
            return $http({
                method: 'POST',
                url: 'api/restaurant/' + restaurantId + '/table',
                data: {
                    rows: rows,
                    columns: columns,
                    tables: tables
                }
            });
        }

    };
});