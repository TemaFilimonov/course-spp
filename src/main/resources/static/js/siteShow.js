'use strict';
let App = angular.module('siteShow', ['ngSanitize']);

App.controller('siteCtrl',['$scope','$http','$location','$sce', function ($scope, $http, $location, $sce) {
    $scope.site = [];

    $http.get('http://localhost:8080/showsite/' + window.location.search.slice(6)).success(function (data) {
        $scope.site = $sce.trustAsHtml(data.source);
    });
}]);