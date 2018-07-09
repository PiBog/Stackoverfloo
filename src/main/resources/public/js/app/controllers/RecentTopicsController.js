'use strict';
var app = angular.module('restAppUI.controllers');

app.controller('RecentTopicsController', ['$scope', 'TopicsService', function ($scope, TopicsService) {
    console.log('start logging RTCtrl');

    function recentTopicsList() {

        console.log('call RTService');
        TopicsService.recentList()
            .then(successCallback, errorCallback);

        function successCallback(response) {
            $scope.recentTopicsList = response.data;
        };

        function errorCallback(error) {
            console.log(error);
        };
    }

    recentTopicsList();



}]);