'use strict';
var app = angular.module('restAppUI.controllers');

app.controller('TopicsListController', ['$scope', 'TopicsService', function ($scope, TopicsService) {
    console.log('start logging TLCtrl');


    function topicsList() {
        TopicsService.list()
            .then(function (response) {
                $scope.topicsList = response.data;
            });
    }

    $scope.refreshTopics = function () {
        topicsList();
    }

    topicsList();

    // $scope.topicDetail = function(topicId){
    //     TopicsService.topic(topicId).then(function (response) {
    //         $scope.currentTopic = response.data;
    //     });
    //
    //
    // }
    //
    // $scope.refreshCurentTopic = function (topic) {
    //
    // }

}]);