'use strict'

var app = angular.module('restAppUI',
    ['ngRoute', 'restAppUI.services', 'restAppUI.controllers']);

app.config(['$routeProvider', '$locationProvider', '$httpProvider',
            function ($routeProvider, $locationProvider, $httpProvider) {

    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false
    });
    $routeProvider
        .when('/', {
            templateUrl: 'views/main.html',
            controller: 'RecentTopicsController'
        })
        .when('/login.html', {
        templateUrl: 'views/login.html',
        controller: 'LoginController'
    })
        .when('/users', {
            templateUrl: 'views/users.html',
            controller: 'UsersListController'
        })
        .when('/profile', {
            templateUrl: 'views/user-detail.html',
            controller: 'UserViewController'
        })
        .when('/topics', {
            templateUrl: 'views/topics.html',
            controller: 'TopicsListController'
        })
        .when('/topics/:topicId', {
            templateUrl: 'views/topic-detail.html',
            controller: 'TopicViewController'
        })
        .when('/topics/new', {
            templateUrl: 'views/topic-new.html',
            controller: 'NewTopicController'
        })
        .otherwise({
            redirectTo: '/'
        });

    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

}]);

app.controller('MainController', ['$scope', function ($scope) {
    console.log('start logging MCtrl');

    $scope.currentUser = "Noob";


}]);

// app.controller('LoginController', ['$scope', function ($scope, $resource, $http, $httpParamSerializer, $cookies) {

    // $scope.data = {
    //     grant_type:"password",
    //     username: "",
    //     password: "",
    //     client_id: "clientIdPassword"
    // };
    // $scope.encoded = btoa("clientIdPassword:secret");
    //
    // $scope.login = function() {
    //     var req = {
    //         method: 'POST',
    //         url: "http://localhost:8080/oauth/token",
    //         headers: {
    //             "Authorization": "Basic " + $scope.encoded,
    //             "Content-type": "application/x-www-form-urlencoded; charset=utf-8"
    //         },
    //         data: $httpParamSerializer($scope.data)
    //     }
    //     $http(req).then(function(data){
    //         $http.defaults.headers.common.Authorization =
    //             'Bearer ' + data.data.access_token;
    //         $cookies.put("access_token", data.data.access_token);
    //         window.location.href="index";
    //     });
    // }


// }]);