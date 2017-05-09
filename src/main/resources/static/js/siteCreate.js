angular.module("siteCreate", ['ngSanitize'])

    .controller("creationCtrl", function($scope, $http) {

            $scope.saveSite = function (action) {
                $scope.site =
                {
                    name: $scope.src.name,
                    ownerId: null,
                    createDate: null,
                    editDate: null,
                    source: null,
                    tags: $scope.src.tags
                } ;
                $http.post("save/site/", $scope.site , action).then(function (response) {
                    if(response.status === 200) {
                        window.location.replace('/edit?site=' + response.data.id);
                    }
                })
            };

        }
    );

