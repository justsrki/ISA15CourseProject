/*global angular*/
var appVisitsModule = angular.module('app.Visits', []);

appVisitsModule.factory('Visits', function ($http) {
    "use strict";

    return {
        getAll: function () {
            return $http({
                method: 'GET',
                url: 'api/invitation/visits'
            });
        },
        setRating: function (id, value) {
            return $http({
                method: 'POST',
                url: 'api/invitation/' + id + '/rating',
                data: {
                    value: value
                }
            });
        }
    };

});