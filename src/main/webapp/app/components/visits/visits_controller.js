/*global angular*/
var appVisitsCtrlModule = angular.module('app.VisitsCtrl', []);

appVisitsCtrlModule.controller('VisitsCtrl', function ($scope, Visits) {
    "use strict";

    $scope.future = [];
    $scope.past = [];

    $scope.createRating = function (visit) {
        if (!visit.rating) {
            visit.rating = visit.newRating;
            Visits.setRating(visit.invitationId, visit.rating);
        }
    };

    $scope.getVisits = function () {
        Visits.getAll().then(
            function (response) {
                $scope.future = response.data.future;
                $scope.past = response.data.past;
                var visit;
                for (visit in $scope.past) {
                    if ($scope.past.hasOwnProperty(visit) && $scope.past[visit].rating > 0) {
                        $scope.past[visit].newRating = $scope.past[visit].rating;
                    }
                }
            },
            function (response) {
                var message = (response.data && response.data.message) ? response.data.message : "";
                $scope.alertMessage = "Status code: " + response.status + " " + message;
            }
        );
    };

    $scope.getVisits();

});