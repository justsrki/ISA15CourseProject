/*global angular*/
var appReservationModule = angular.module('app.Reservation', []);

appReservationModule.factory('Reservation', function ($http) {
    "use strict";

    return {
        getTables: function (restaurantId, startDate, length) {
            return $http({
                method: 'POST',
                url: 'api/reservation/tables',
                data: {
                    restaurantId: restaurantId,
                    startDate: startDate,
                    length: length
                }
            });
        },
        createReservation: function (restaurantId, startDate, length, tables) {
            return $http({
                method: 'POST',
                url: 'api/reservation',
                data: {
                    restaurantId: restaurantId,
                    startDate: startDate,
                    length: length,
                    tables: tables
                }
            });
        }
    };
});