angular.module('demo', [])
.controller('Hello', function($scope, $http) {
    $http.get('http://localhost:8080/findById?id=1').
        then(function(response) {
            $scope.greeting = response.data;
        });
});