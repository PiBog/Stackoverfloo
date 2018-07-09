'use strict';
var app = angular.module('restAppUI.services');

app.service('UsersService', function ($http) {
    var service = this;

    service.list = function () {
        return $http.get('/admin/users');
    };

    service.getUser = function (id) {
        return $http.get('/users/user/' + id);
    };

    service.insert = function (newuser) {
        return $http.post('/users/create', newuser);
    };

    service.update = function (user) {
        return $http.put('/admin/users/update/' + user.id, user);
    };

    // service.delete = function (id) {
    //     return $http.delete(getUrl('delete/' + id), {
    //         'id': id
    //     });
    // };
});