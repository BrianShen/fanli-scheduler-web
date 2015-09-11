/**
 * Created by wei.shen on 2015/7/16.
 */

fanliApp.controller("sidebarCtrl",function($scope,$location) {
    $scope.isActive = function (route) {
        return route === $location.path();
    }
});
