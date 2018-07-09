'use strict';
var app = angular.module('restAppUI.services');

app.service('TopicsService', function ($http) {
    var service = this;
    var tPath = '/rest/topics/';

    service.list = function () {
        return $http.get(tPath);
    };

    service.recentList = function () {
        return $http.get(tPath + 'recent/');
    };

    service.topic = function (id) {
        return $http.get(tPath + 'topic/' + id + '/');
    };

    service.userList = function (user) {
        return $http.get(tPath + 'user/' + user.id + '/');
    };


    service.create = function (newtopic) {
        return $http.post(tPath + 'new/', newtopic);
    };

    service.update = function (topic) {
        return $http.put(tPath + topic.id + 'edit/', topic);
    };

    // service.delete = function (id) {
    //     return $http.delete(getUrl('delete/' + id), {
    //         'id': id
    //     });
    // };
});