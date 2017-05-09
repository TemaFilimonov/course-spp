'use strict';


var App = angular.module('profile', []);

App.controller('profileUserCtrl',['$scope','$http','$location', function ($scope, $http, $location) {
  $scope.user = [];
  $scope.site = [];
  $scope.favorites = [];

  $http.get('http://localhost:8080/user/info/' + window.location.search.slice(4)).success(function (data) {
    $scope.user = data;
      $http.get('http://localhost:8080/user/favorite/' + data.id).then(function (response) {
          $scope.favorites = response.data;
      });
  });
  $http.get('http://localhost:8080/site/info/' + window.location.search.slice(4)).success(function (data) {
    $scope.site = data;
  });

  $scope.addToFavorite = function(site, index) {
      $http.post('http://localhost:8080/favorite/add/' + site.id, undefined);
      $scope.site[index].inFavorite = true;
  }
}]);

App.controller('favoriteCtrl',['$scope','$http','$location', function ($scope, $http, $location) {
  $scope.user = [];
  $scope.site = [];

  $http.get('http://localhost:8080/user/info/' + window.location.search.slice(4)).then(function (response) {
    $scope.user = response.data;
  });
  $http.get('http://localhost:8080/user/favorite/' + window.location.search.slice(4)).then(function (response) {
    $scope.site = response.data;
  });
}]);