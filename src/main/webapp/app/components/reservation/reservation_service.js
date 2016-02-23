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
        },
        getReservation: function (id) {
            return $http({
                method: 'GET',
                url: 'api/reservation/' + id
            });
        },
        confirmInvitation: function (id, value) {
            return $http({
                method: 'PUT',
                url: 'api/invitation/' + id + '?value=' + value
            });
        },
        getInvitationReservation: function (id) {
            return $http({
                method: 'GET',
                url: 'api/invitation/' + id + '/reservation'
            });
        },
        inviteFriends: function (id, userIds) {
            return $http({
                method: 'POST',
                url: 'api/reservation/' + id,
                data: {
                    userIds: userIds
                }
            });
        }
    };
});