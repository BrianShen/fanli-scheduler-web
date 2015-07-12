/**
 * Created by wei.shen on 2015/7/11.
 */
var fanliApp = angular.module("fanliApp",['ngRoute']);

fanliApp.config(function($routeProvider){
    $routeProvider.
        when('/',{
            templateUrl : 'assert/pages/page1.html'
        }).
        when('/page2',{
            templateUrl : 'assert/pages/page2.html'
        }).
        when('/page3/:name',{
            templateUrl : 'assert/pages/page3.html',
            controller : 'page3Ctrl'
        }).
        when('/page4/:id',{
            templateUrl : 'assert/pages/page4.html',
            controller : 'page4Ctrl'
        }).
        otherwise({
            redirectTo : '/'
        });

});

fanliApp.controller('page3Ctrl', function($scope,$routeParams){
    $scope.user_name = $routeParams.name;
});
fanliApp.controller('page4Ctrl', function($scope,$routeParams){
    $scope.post_id = $routeParams.id;
});