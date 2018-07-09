'use strict';
var app = angular.module('restAppUI.controllers');

app.controller('LoginController', ['$rootScope', '$scope', '$http', '$location',
                                    function ($rootScope, $scope, $http, $location) {
    console.log('start logging LCtrl');

    var authenticate = function(credentials, callback) {

        var headers = credentials ? {authorization : "Basic "
            + btoa(credentials.username + ":" + credentials.password)
        } : {};

        $http.get('user', {headers : headers})
            .then(function successCallback(response){
            if (data.name) {
                $rootScope.authenticated = true;
            } else {
                $rootScope.authenticated = false;
            }
            callback && callback();
        },
            function errorCallback(response) {
            $rootScope.authenticated = false;
            callback && callback();
        });

    }

    authenticate();
    $scope.credentials = {};
    $scope.login = function() {
        authenticate($scope.credentials, function() {
            if ($rootScope.authenticated) {
                $location.path("/");
                $scope.error = false;
            } else {
                $location.path("/enter");
                $scope.error = true;
            }
        });
    };

    $scope.logout = function() {
        $http.post('logout', {}).success(function() {
            $rootScope.authenticated = false;
            $location.path("/");
        }).error(function(data) {
            $rootScope.authenticated = false;
        });
    }



}]);