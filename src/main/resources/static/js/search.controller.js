angular.module("search", [])

    .controller("searchCtrl", function($scope, $http) {
        $scope.site = {};
        $scope.phrase = decodeURIComponent(window.location.search.slice(8));

        $http.get('http://localhost:8080/search/phrase=' + window.location.search.slice(8)).then(function (response) {
            $scope.site = response.data;
        });

        }
    );
