/**
 * Created by Артем Константинович on 06.10.2016.
 */
//noinspection JSAnnotator
/**
 * The controller doesn't do much more than setting the initial data model
 */
angular.module("dnd", ['dndLists','ngSanitize','summernote'])

    .controller("editCtrl", function($scope, $http) {
        $scope.source = [];


        $scope.models = {
            selected: null,
            templates: [
                {type: "item", code: ""},
                {type: "container", columns: [[], []]}
            ],
            dropzones: {
                "A": []
            }
        };

        $http.get('/site/source/' + window.location.search.slice(6)).success(function (data) {
            $scope.source = data;
            $scope.models.dropzones.A = $scope.source.A;
        });

        $scope.$watch('models.dropzones', function (model) {
            $scope.modelAsJson = angular.toJson(model, true);
        }, true);

        $scope.savePage = function (action) {
            var source = $scope.modelAsJson;
            $http.post("edit/source/"+ window.location.search.slice(6), source, action)};

    }
);