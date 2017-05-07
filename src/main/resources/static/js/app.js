'use strict';


var App = angular.module('profile', []);

App.controller('profileUserCtrl',['$scope','$http','$location', function ($scope, $http, $location) {
  $scope.user = [];
  $scope.site = [];

  $http.get('http://localhost:8080/user/info/' + window.location.search.slice(4)).success(function (data) {
    $scope.user = data;
  });
  $http.get('http://localhost:8080/site/info/' + window.location.search.slice(4)).success(function (data) {
    $scope.site = data;
  });
}]);

App.controller('favoriteCtrl',['$scope','$http','$location', function ($scope, $http, $location) {
  $scope.user = [];
  $scope.site = [];

  $http.get('http://localhost:8080/user/info/' + window.location.search.slice(4)).success(function (data) {
    $scope.user = data;
  });
  $http.get('http://localhost:8080/user/favorite/' + window.location.search.slice(4)).success(function (data) {
    $scope.site = data;
  });
}]);